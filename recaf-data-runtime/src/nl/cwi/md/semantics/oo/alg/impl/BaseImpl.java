package nl.cwi.md.semantics.oo.alg.impl;

import java.lang.reflect.Proxy;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.alg.Base;
import nl.cwi.md.semantics.oo.ast.Body;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oo.ast.Interface;
import nl.cwi.md.semantics.oo.ast.MMethod;
import nl.cwi.md.semantics.oo.ast.Member;

public class BaseImpl<T> implements Base<T, Body<T>, Member<T>, Formal> {

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Body<T> body, Object... args) {
		return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{ iface}, 
				new Interface<T>(iface, body, args));
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Body<T> Body(nl.cwi.md.semantics.oo.ast.Member<T>... members) {
		return new Body<>(members);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Member<T> Method(String name, nl.cwi.md.semantics.oo.ast.Formal[] formals,
			Closure body) {
		return new MMethod<>(name, formals, body);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Formal Formal(String name, Class<?> type) {
		return new Formal(name, type);
	}

}
