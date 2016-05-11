package nl.cwi.md.semantics.oominheritance.impl;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritance;
import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Interface;
import nl.cwi.md.semantics.oominheritance.ast.MMethod;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public class BaseMInheritanceImpl<T> implements BaseMInheritanceAST<T>, BaseMInheritance<T, Body<T>, Member<T>, Formal> {

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body,
			Object... args) {
		return new Interface<T>(this, iface, parentIfaces, self, body, args).getProxy();
	}

	@Override
	public Body<T> Body(Member<T>... members) {
		return new Body<>(members);
	}

	@Override
	public Member<T> Method(Formal head, Closure body, Formal... formals) {
		return new MMethod<>(head, formals, body);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Formal Formal(String name, Class<?> type, boolean isVararg) {
		return new Formal(name, type, isVararg);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Formal Formal(String name, Class<?> type, boolean isVararg, Class<?> typeArg) {
		return new Formal(name, type, isVararg, typeArg);
	}

	@Override
	public nl.cwi.md.semantics.oo.ast.Formal Formal(String name, Class<?> type) {
		return new Formal(name, type, false);
	}

}
