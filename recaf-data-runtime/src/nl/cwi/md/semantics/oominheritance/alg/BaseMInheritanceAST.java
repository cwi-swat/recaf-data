package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Member;

@SuppressWarnings("unchecked")
public interface BaseMInheritanceAST<T> extends BaseMInheritance<T, Body<T>, Member<T>, Formal>{
	@Override
	T Interface(Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body, Object... args);
	
	@Override
	Body<T> Body(Member<T>...members);
	
	@Override
	Member<T> Method(Formal head, Closure body, Formal... formals); // Field
	
	@Override
	Formal Formal(String name, Class<?> type, boolean isVararg, Class<?> typeArg); // Key
	
	@Override
	Formal Formal(String name, Class<?> type, boolean isVararg); 
}
