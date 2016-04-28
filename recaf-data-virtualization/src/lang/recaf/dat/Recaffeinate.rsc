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
	
InterfaceMemberDec removeAnnos((InterfaceMemberDec) `@<TypeName annoType> <TypeParams? tp> <ResultType rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t>;`)
	{ return (InterfaceMemberDec) `<TypeParams? tp> <ResultType rt> <Id methodName> (<{FormalParam ","}* fps> ) <Throws? t>;`;
	  }

InterfaceMemberDec removeAnnos((InterfaceMemberDec) `@<TypeName annoType> <BeforeDefaultMethod*  _> default <TypeParams? tp> <ResultType rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t> <MethodBody mb>`)
	= (InterfaceMemberDec) `<TypeParams? tp> <ResultType rt> <Id methodName> (<{FormalParam ","}* fps> ) <Throws? t>;`;
	
InterfaceMemberDec removeAnnos(InterfaceMemberDec dec) = {
 return dec;
};

Block selfize(Block b, map[TypeName, int] parents) =
	visit(b){
		case (Expr) `this` => (Expr) `self`
		case (Expr) `<TypeName ty>.super.<Id method>(<{Expr ","}* args>)` => (Expr) `<Id parent>.<Id method>(<{Expr ","}* args>)`
			when int idx := parents[ty],
				 Id parent := [Id] "super$<idx>"
	};

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

{Expr ","}* toFormals({FormalParam ","}* fps){
	Expr call = (Expr) `f()`; // ugly hack
	visit (fps){
		case (FormalParam) `<BeforeVar* _> <Type ty> <VarDecId id>`: {
			if ((Expr) `f(<{Expr ","}* args>)` := call){
				StringLiteral name = [StringLiteral] "\"<id>\"";
				Expr e = (Expr) `new nl.cwi.md.semantics.oo.ast.Formal(<StringLiteral name>, <Type ty>.class)`;
				call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;
			}	
		} 
		case (FormalParam) `<BeforeVar* _> <Type ty> ... <VarDecId id>`: {
			if ((Expr) `f(<{Expr ","}* args>)` := call){
				StringLiteral name = [StringLiteral] "\"<id>\"";
				Expr e = (Expr) `new nl.cwi.md.semantics.oo.ast.Formal(<StringLiteral name>, <Type ty>.class, true)`;
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
  	case (AbstractMethodDec) `@<TypeName annoType> <BeforeAbstractMethod*  _> <TypeParams? tp> <Type rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t>;`: { 
  		if ((Expr) `f(<{Expr ","}* args>)` := call){
  			StringLiteral m = [StringLiteral] "\"<methodName>\"";
  			{Expr ","}* formals = toFormals(fps);
  			Expr e = (Expr) `<Id alg>.<Id annoType>(<StringLiteral m>, 
  							'						<Type rt>.class, 
  							'						new nl.cwi.md.semantics.oo.ast.Formal[]{ <{Expr ","}* formals> })`;
    		call = (Expr)`f(<Expr e>, <{Expr ","}* args>)`;	
  		}
  	}
  	case (DefaultMethodDec) `@<TypeName annoType> <BeforeDefaultMethod*  _> default <TypeParams? tp> <Type rt> <Id methodName>(<{FormalParam ","}* fps>) <Throws? t> <Block b>`: { 
  		if ((Expr) `f(<{Expr ","}* args>)` := call){
  			StringLiteral m = [StringLiteral] "\"<methodName>\"";
  			{Expr ","}* formals = toFormals(fps);
  			{FormalParam ","}* cloFps = fromParentsToFormals(dataName, iTypes);
  			Block newBlock = selfize(b, computeParentsMap(iTypes));
  			Expr e = (Expr) 
  				`<Id alg>.<Id annoType>(<StringLiteral m>, <Type rt>.class, 
  				'	new nl.cwi.md.semantics.oo.ast.Formal[]{ <{Expr ","}* formals> },
  				'	 new nl.cwi.md.semantics.alg.Closure(){
  				'		public Object apply(<{FormalParam ","}* cloFps>){
  				'			<Block newBlock>
  				'		}
  				'	})`;
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

start[CompilationUnit] recaffeinate(start[CompilationUnit] cu) {
   return top-down visit (cu) {
      case (InterfaceDec)`@Managed(alg = <Id algType>.class) public interface <Id name> extends <{InterfaceType ","}+ parentTypes>{<InterfaceMemberDec* mds>}`
        => (InterfaceDec)`public interface <Id name> extends <{InterfaceType ","}+ parentTypes>{
        	'	<InterfaceMemberDec* newMds>
        	'	public static <Id name> New(Object... initArgs){
        	'		return <Id name>.New(null, initArgs);
        	'	}
        	'
        	'	public static <Id name> New(<Id name> self, Object... initArgs){
        	'	<Id algType>\<<Id name>\> alg = new <Id algType>\<<Id name>\>();	
        	'	return <Expr returnExpr>;
        	'	}
        	'}`
        	when InterfaceMemberDec* newMds := removeAnnoss(mds),
        		 ArrayInit iTypes :=  [ArrayInit] "{<intercalate(", ", [(Expr) `<Type ty>.class` | InterfaceType ty <- parentTypes])>}",
        		 Expr returnExpr := methods2alg(mds, name, (Id) `alg`, iTypes)
        		 
        case (InterfaceDec)`@Managed(alg = <Id algType>.class) public interface <Id name>{<InterfaceMemberDec* mds>}`
        => (InterfaceDec)`public interface <Id name>{
        	'	<InterfaceMemberDec* newMds>
        	'	public static <Id name> New(Object... initArgs){
			'		return <Id name>.New(null, initArgs);
        	'	}
        	'
        	'	public static <Id name> New(<Id name> self, Object... initArgs){
        	'	<Id algType>\<<Id name>\> alg = new <Id algType>\<<Id name>\>();	
        	'	return <Expr returnExpr>;
        	'	}
        	'}`
        	when InterfaceMemberDec* newMds := removeAnnoss(mds),
        		 Expr returnExpr := methods2alg(mds, name, (Id) `alg`, (ArrayInit) `{}`)
    }
}