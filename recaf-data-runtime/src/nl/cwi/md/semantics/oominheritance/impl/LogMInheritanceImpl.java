package nl.cwi.md.semantics.oominheritance.impl;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritance;
import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.LogMethod;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public class LogMInheritanceImpl<T> extends BaseMInheritanceImpl<T> implements BaseMInheritanceAST<T>, BaseMInheritance<T, Body<T>, Member<T>, Formal> {
	@Override
	public Member<T> Method(Formal head, Closure body, Formal... formals) {
		return new LogMethod<>(head, formals, body);
	}

}
