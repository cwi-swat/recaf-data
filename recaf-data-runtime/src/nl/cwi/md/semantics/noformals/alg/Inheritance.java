package nl.cwi.md.semantics.noformals.alg;

import nl.cwi.md.semantics.alg.MethodsAndFields3;

@SuppressWarnings("unchecked")
public interface Inheritance<T, P, B, M, F> extends MethodsAndFields3<T, B, M, F>{
	T Interface(Class<P> parentClass, P parent, Class<T> iface, B body);
}
