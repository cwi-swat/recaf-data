package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public interface MemoizedAST<T> extends Memoized<T, Body<T>, Member<T>, Formal>{
	@Override
	Member<T> MemoizedMethod(Formal head, Closure body, Formal... formals);
}
