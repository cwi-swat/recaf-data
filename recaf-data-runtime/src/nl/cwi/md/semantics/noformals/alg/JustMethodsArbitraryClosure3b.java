package nl.cwi.md.semantics.noformals.alg;

import nl.cwi.md.semantics.alg.Closure;

@SuppressWarnings("unchecked")
public interface JustMethodsArbitraryClosure3b<T, B, M> {
	T Interface(Class<T> iface, B methods);
	B Body(M... methods);
	M Method(String name, Closure body); // Field
}
