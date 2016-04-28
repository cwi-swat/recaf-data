package nl.cwi.md.semantics.oo.alg.impl;


import java.lang.reflect.Proxy;

import nl.cwi.md.semantics.oo.alg.ImmutableFields;
import nl.cwi.md.semantics.oo.ast.Body;
import nl.cwi.md.semantics.oo.ast.Constructor;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oo.ast.ImmutableField;
import nl.cwi.md.semantics.oo.ast.ImmutableInterface;
import nl.cwi.md.semantics.oo.ast.Member;

public class ImmutableFieldsImpl<T> extends FieldsImpl<T> implements ImmutableFields<T, Body<T>, Member<T>, Formal>{
	@Override
	public T Interface(java.lang.Class<T> iface, Body<T> body, Object... args) {
		return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{ iface}, 
				new ImmutableInterface<T>(iface, body, args));
	};
	@Override
	public Member<T> Field(Formal formal) {
		return new ImmutableField<T>(formal);
	}

	@Override
	public Member<T> Constructor(String name, Formal... formals) {
		return new Constructor<T>(name, formals);
	}
}
