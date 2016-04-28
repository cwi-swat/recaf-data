package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;

@SuppressWarnings("unchecked")
public interface BaseMInheritance<T, B, M, F> {
	T Interface(Class<T> iface, Class<?>[] parentIfaces, T self, B body, Object... args);
	B Body(M...members);
	M Method(String name, Class<?> retType, F[] formals, Closure body); // Field
	F Formal(String name, Class<?> type); // Key
}
