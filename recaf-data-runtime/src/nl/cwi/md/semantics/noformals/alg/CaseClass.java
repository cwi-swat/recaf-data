package nl.cwi.md.semantics.noformals.alg;

@SuppressWarnings("unchecked")
public interface CaseClass<T, P, B, M> extends MethodsAndFields3b<T, B, M> {
	T Interface(Class<P> parentClass, Class<T> iface, B body);
	M ImmutableField(String name);
}
