module lang::recaf::dat::Recaffeinate

import lang::java::Java8;
import List;
import Set;
import String;
import IO;  
import util::Maybe;

InterfaceMemberDec* removeAnnoss(InterfaceMemberDec* mds) =
	top-down visit(mds){
		case InterfaceMemberDec md => removeAnnos(md)
	};

InterfaceMemberDec removeAnnos((InterfaceMemberDec)`@Algebra <BeforeConstant* bcs> <Type algTy> <Id algebra> = <Expr e>;`)
	 = (InterfaceMemberDec) `<BeforeConstant* bcs> <Type algTy> <Id algebra> = <Expr e>;`;

	
InterfaceMemberDec removeAnnos((InterfaceMemberDec) `@<TypeName annoType> <TypeParams? tp> <ResultType rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t>;`)
	 = (InterfaceMemberDec) `<TypeParams? tp> <ResultType rt> <Id methodName> (<{FormalParam ","}* fps> ) <Throws? t>;`;
	  

InterfaceMemberDec removeAnnos((InterfaceMemberDec) `@<TypeName annoType> <BeforeDefaultMethod*  _> default <TypeParams? tp> <ResultType rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t> <MethodBody mb>`)
	= (InterfaceMemberDec) `<TypeParams? tp> <ResultType rt> <Id methodName> (<{FormalParam ","}* fps> ) <Throws? t>;`;
	
InterfaceMemberDec removeAnnos(InterfaceMemberDec dec) = {
 return dec;
};

Block selfize(Block b, map[TypeName, int] parents) ={
	return visit(b){
		case (Expr) `this` => (Expr) `self`
		case (Expr) `<TypeName ty>.super.<Id method>(<{Expr ","}* args>)` => (Expr) `<Id parent>.<Id method>(<{Expr ","}* args>)`
			when int idx := parents[ty],
				 Id parent := [Id] "super$<idx>"
	}};

map[TypeName, int] computeParentsMap(ArrayInit ai){
	int i = 0;
	map[TypeName, int] m = ();
	visit (ai){
		case (Expr) `<TypeName tn>.class`: {
			m += (tn:i);
			i = i + 1;	
		} 
	}
	return m;
}

{FormalParam ","}* fromParentsToFormals(Id dataName, ArrayInit ai){
	MethodDecHead mdec = (MethodDecHead) `void f(<TypeName dataName> self)`; // ugly hack
	int i = 0;
	visit (ai){
		case (Expr) `<Type ty>.class`: {
			if ((MethodDecHead) `void f(<{FormalParam ","}* fps>)` := mdec){
				Id superId = [Id] "super$<i>";
				i = i + 1;
				FormalParam fp = (FormalParam) `<Type ty> <Id superId>`;
				mdec = (MethodDecHead) `void f(<{FormalParam ","}* fps>, <FormalParam fp>)`;
			}	
		} 
	}
	if ((MethodDecHead) `void f(<{FormalParam ","}* fps>)` := mdec) {
    	return fps;
  	}
  	// cannot come here.
}

{Expr ","}* toFormals(Id alg, {FormalParam ","}* fps){
	Expr call = (Expr) `f()`; // ugly hack
	visit (fps){
		case (FormalParam) `<BeforeVar* _> <Type ty> <VarDecId id>`: {
			if ((Expr) `f(<{Expr ","}* args>)` := call){
				StringLiteral name = [StringLiteral] "\"<id>\"";
				Expr e = (Expr) `<Id alg>.Formal(<StringLiteral name>, <Type ty>.class, false)`;
				call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;
			}	
		} 
		case (FormalParam) `<BeforeVar* _> <Type ty> ... <VarDecId id>`: {
			if ((Expr) `f(<{Expr ","}* args>)` := call){
				StringLiteral name = [StringLiteral] "\"<id>\"";
				Expr e = (Expr) `<Id alg>.Formal(<StringLiteral name>, <Type ty>.class, true)`;
				call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;
			}	
		} 
	}
	if ((Expr) `f(<{Expr ","}* args>)` := call) {
    	return args;
  	}
  	// cannot come here.
}


// No methods returning void (<Type rt> instead of <ReturnType rt>)
{Expr ","}* methods2sepExps(Id dataName, InterfaceMemberDec* mds, Id alg, ArrayInit iTypes) {
  Expr call = (Expr) `f()`; // ugly hack
  visit (mds) {
    case (AbstractMethodDec) `@Algebra <BeforeAbstractMethod*  _> <TypeParams? tp> <Type rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t>;`: {
    };
  	case (AbstractMethodDec) `@<TypeName annoType> <BeforeAbstractMethod*  _> <TypeParams? tp> <Type rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t>;`: { 
  		if ((Expr) `f(<{Expr ","}* args>)` := call){
  			StringLiteral m = [StringLiteral] "\"<methodName>\"";
  			{Expr ","}* formals = toFormals(alg, fps);
  			Expr e = (Expr) `<Id alg>.<Id annoType>(<StringLiteral m>, 
  							'						<Type rt>.class, 
  							'						 <{Expr ","}* formals>)`;
    		call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;	
  		}
  	}
  	case (DefaultMethodDec) `@<TypeName annoType> <BeforeDefaultMethod*  _> default <TypeParams? tp> <Type rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t> <Block b>`: { 
  		if ((Expr) `f(<{Expr ","}* args>)` := call){
  			StringLiteral m = [StringLiteral] "\"<methodName>\"";
  			{Expr ","}* formals = toFormals(alg, fps);
  			{FormalParam ","}* cloFps = fromParentsToFormals(dataName, iTypes);
  			Block newBlock = selfize(b, computeParentsMap(iTypes));
  			Expr e = (Expr) 
  				`<Id alg>.<Id annoType>(<StringLiteral m>, <Type rt>.class, 
  				'	 new nl.cwi.md.semantics.alg.Closure(){
  				'		public Object apply(<{FormalParam ","}* cloFps>){
  				'			<Block newBlock>
  				'		}
  				'	},
  				'<{Expr ","}* formals>)`;
    		call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;	
  		}
  	}
  };
  if ((Expr) `f(<{Expr ","}* args>)` := call) {
    return args;
  }
  // cannot come here.
}


Expr methods2alg(InterfaceMemberDec* mds, Id dataName, Id alg, ArrayInit iTypes ) {
  {Expr ","}* exprs = methods2sepExps(dataName, mds, alg, iTypes);
  Expr call = (Expr) `<Id alg>.Interface(<Id dataName>.class, 
  					 '					  new Class\<?\>[] <ArrayInit iTypes>,
  					 '					 self, 
  					 '					 <Id alg>.Body(<{Expr ","}* exprs>),
  					 '					 initArgs)`;
  return call;
}

default ArrayInit toArrayInit(InterfaceDecHead head) = { 
	println(head);
	return (ArrayInit) `{}`;};
	
tuple[Type, bool, Expr] getAlgebraInfo(InterfaceMemberDec* mds){
	top-down visit(mds){
		case (InterfaceMemberDec)  `@Algebra <BeforeConstant* bcs> <Type algTy> <Id algebra> = null;`:{
		 	return <algTy, false, (Expr) `null`>;
		}
		case (InterfaceMemberDec)  `@Algebra <BeforeConstant* bcs> <Type algTy> <Id algebra> = <Expr e>;`:{
		 	return <algTy, true, (Expr) `<Id algebra>`>;
		}
	};
	return <(Type) `Object`, false, (Expr) `null`>;
}


start[CompilationUnit] recaffeinate(start[CompilationUnit] cu) {
   return top-down visit (cu) {
   	  case (InterfaceDec)`@Managed <BeforeInterface* bi> interface <Id name>{<InterfaceMemberDec* mds>}`
        => algInfo.hasDefault
        	?
        	
        	(InterfaceDec) 
            `<BeforeInterface* bi> interface <Id name>{
			'	<InterfaceMemberDec* newMds>
        	'	public static <Id name> New(Object... initArgs){
        	'		return <Id name>.New(<Expr algField>, null, initArgs);
        	'	}
        	'
        	'	public static <Id name> New(<Id name> self, Object... initArgs){
        	'		return <Id name>.New(<Expr algField>, self, initArgs);
        	'	}
        	'
        	'	public static <Id name> New(<TypeName algType>\<<Id name>\> alg, Object... initArgs){
        	'		return <Id name>.New(alg, null, initArgs);
        	'	}
        	'
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, <Id name> self, Object... initArgs){
        	'	return <Expr returnExpr>;
        	'	}
        	'}` 
        	
        	:
        	
        	(InterfaceDec) 
            `<BeforeInterface* bi> interface <Id name>{
			'	<InterfaceMemberDec* newMds>
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, Object... initArgs){
        	'		return <Id name>.New(alg, null, initArgs);
        	'	}
        	'
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, <Id name> self, Object... initArgs){
        	'	return <Expr returnExpr>;
        	'	}
        	'}`
        	when InterfaceMemberDec* newMds := removeAnnoss(mds),
        		 ArrayInit iTypes := (ArrayInit) `{}`,
        		 tuple[Type ty, bool hasDefault, Expr algField] algInfo := getAlgebraInfo(mds),
        		 Type algType := algInfo.ty,
        		 Expr algField := algInfo.algField,
        		 Expr returnExpr := methods2alg(mds, name, (Id) `alg`, iTypes)
        		 
        case (InterfaceDec)`@Managed <BeforeInterface* bi> interface <Id name> extends <{InterfaceType ","}+ parentTypes> {<InterfaceMemberDec* mds>}`
        => algInfo.hasDefault
        	?
        	
        	(InterfaceDec) 
            `<BeforeInterface* bi> interface <Id name> extends <{InterfaceType ","}+ parentTypes>{
			'	<InterfaceMemberDec* newMds>
        	'	public static <Id name> New(Object... initArgs){
        	'		return <Id name>.New(<Expr algField>, null, initArgs);
        	'	}
        	'
        	'	public static <Id name> New(<Id name> self, Object... initArgs){
        	'		return <Id name>.New(<Expr algField>, self, initArgs);
        	'	}
        	'
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, Object... initArgs){
        	'		return <Id name>.New(alg, null, initArgs);
        	'	}
        	'
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, <Id name> self, Object... initArgs){
        	'	return <Expr returnExpr>;
        	'	}
        	'}` 
        	
        	:
        	
        	(InterfaceDec) 
            `<BeforeInterface* bi> interface <Id name> extends <{InterfaceType ","}+ parentTypes>{
			'	<InterfaceMemberDec* newMds>
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, Object... initArgs){
        	'		return <Id name>.New(alg, null, initArgs);
        	'	}
        	'
        	'	public static  <Id name> New(<TypeName algType>\<<Id name>\> alg, <Id name> self, Object... initArgs){
        	'	return <Expr returnExpr>;
        	'	}
        	'}` 
        	
        	when InterfaceMemberDec* newMds := removeAnnoss(mds),
        		 ArrayInit iTypes :=  [ArrayInit] "{<intercalate(", ", [(Expr) `<Type ty>.class` | InterfaceType ty <- parentTypes])>}",
        		 tuple[Type ty, bool hasDefault, Expr algField] algInfo := getAlgebraInfo(mds),
        		 Type algType := algInfo.ty,
        		 Expr algField := algInfo.algField,
        		 Expr returnExpr := methods2alg(mds, name, (Id) `alg`, iTypes)
    }
}