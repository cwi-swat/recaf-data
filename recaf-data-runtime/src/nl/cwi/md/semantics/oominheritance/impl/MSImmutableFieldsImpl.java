package nl.cwi.md.semantics.oominheritance.impl;


import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.ImmutableFields;
import nl.cwi.md.semantics.oominheritance.alg.ImmutableFieldsAST;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Constructor;
import nl.cwi.md.semantics.oominheritance.ast.ImmutableField;
import nl.cwi.md.semantics.oominheritance.ast.MSImmutableInterface;
import nl.cwi.md.semantics.oominheritance.ast.Member;


public class MSImmutableFieldsImpl<T> extends FieldsImpl<T> implements ImmutableFieldsAST<T>, ImmutableFields<T, Body<T>, Member<T>, Formal>{
	@Override
	public T Interface(Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body, Object... initArgs){
		// BAD SMELL: Why we have to repeat the getProxy call?
		return new MSImmutableInterface<T>(this, iface, parentIfaces, self, body, initArgs).getProxy();
	};
	
	@Override
	public Member<T> Field(String name, Class<?> retType, Formal...formals) {
		return new ImmutableField<T>(new Formal(name, retType));
	}

	@Override
	public Member<T> Constructor(String name, Class<?> retType, Formal... formals) {
		return new Constructor<T>(name, formals);
	}
}
