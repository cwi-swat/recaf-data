package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;

public interface Memoized<T, B, M, F> extends BaseMInheritance<T, B, M, F>{
	M MemoizedMethod(String name, Class<?> retType, Closure body, F... formals);
}
