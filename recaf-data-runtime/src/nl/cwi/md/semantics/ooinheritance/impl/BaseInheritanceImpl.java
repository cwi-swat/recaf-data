package nl.cwi.md.semantics.ooinheritance.impl;

import java.lang.reflect.Proxy;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.ooinheritance.alg.BaseInheritance;
import nl.cwi.md.semantics.ooinheritance.ast.Body;
import nl.cwi.md.semantics.ooinheritance.ast.Interface;
import nl.cwi.md.semantics.ooinheritance.ast.MMethod;
import nl.cwi.md.semantics.ooinheritance.ast.Member;

public class BaseInheritanceImpl<T> implements BaseInheritance<T, Body<T>, Member<T>, Formal> {

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Cell<? extends T> self, Object parent,
			Body<T> body, Object... args) {
		Interface<T> ast = new Interface<T>(parent, body, args);
		T proxy = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{ iface}, 
				ast);
		ast.setSelf(self);
		return proxy;
		
	}

	@Override
	public Body<T> Body(Member<T>... members) {
		return new Body<>(members);
	}

	@Override
	public Member<T> Method(String name, Formal[] formals,
			Closure body) {
		return new MMethod<>(name, formals, body);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Formal Formal(String name, Class<?> type) {
		return new Formal(name, type);
	}

}
