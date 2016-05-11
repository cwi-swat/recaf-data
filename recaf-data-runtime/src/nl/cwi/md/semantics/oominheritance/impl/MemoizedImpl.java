package nl.cwi.md.semantics.oominheritance.impl;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.Memoized;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Member;
import nl.cwi.md.semantics.oominheritance.ast.MemoizedMethod;

public class MemoizedImpl<T> extends BaseMInheritanceImpl<T> implements Memoized<T, Body<T>, Member<T>, Formal> {

	@Override
	public Member<T> MemoizedMethod(Formal head, Closure body,
			nl.cwi.md.semantics.oo.ast.Formal... formals) {
		return new MemoizedMethod<>(head, formals, body);
	}
}
