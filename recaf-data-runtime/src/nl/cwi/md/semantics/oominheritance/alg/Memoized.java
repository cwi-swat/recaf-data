package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;

public interface Memoized<T, B, M, F> extends BaseMInheritance<T, B, M, F>{
	M MemoizedMethod(F head, Closure body, F... formals);
}
