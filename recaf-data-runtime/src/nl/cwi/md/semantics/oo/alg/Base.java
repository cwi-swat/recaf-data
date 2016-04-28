package nl.cwi.md.semantics.oo.alg;

import nl.cwi.md.semantics.alg.Closure;

@SuppressWarnings("unchecked")
public interface Base<T, B, M, F> {
	T Interface(Class<T> iface, B body, Object... args);
	B Body(M...members);
	M Method(String name, F[] formals, Closure body); // Field
	F Formal(String name, Class<?> type); // Key
}
