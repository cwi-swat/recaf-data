package nl.cwi.md.semantics.alg;

@SuppressWarnings("unchecked")
public interface CaseClass<T, P, B, M, F> extends MethodsAndFields3<T, B, M, F> {
	T Interface(Class<P> parentClass, Class<T> iface, B body);
	M ImmutableField(String name, Object value);
}
