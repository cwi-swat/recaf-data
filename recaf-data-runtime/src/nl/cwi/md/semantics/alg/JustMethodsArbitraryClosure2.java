package nl.cwi.md.semantics.alg;

@SuppressWarnings("unchecked")
public interface JustMethodsArbitraryClosure2<T, M, F> {
	T Interface(Class<T> iface, M... methods);
	M Method(String name,  F[] formals, Closure body); // Field
	F Formal(String name, Class<?> type); // Key
}
