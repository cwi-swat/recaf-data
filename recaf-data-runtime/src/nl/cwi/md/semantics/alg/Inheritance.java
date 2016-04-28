package nl.cwi.md.semantics.alg;

@SuppressWarnings("unchecked")
public interface Inheritance<T, P, B, M, F> extends MethodsAndFields3<T, B, M, F>{
	T Interface(Class<P> parentClass, P parent, Class<T> iface, B body);
}
