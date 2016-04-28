package nl.cwi.md.semantics.alg;

@SuppressWarnings("unchecked")
public interface JustMethodsArbitraryClosure3<T, B, M, F> {
	T Interface(Class<T> iface, B methods);
	B Body(M... methods);
	M Method(String name,  F[] formals, Closure body); // Field
	F Formal(String name, Class<?> type); // Key
}
