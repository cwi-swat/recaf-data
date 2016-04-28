package nl.cwi.md.semantics.alg;

@SuppressWarnings("unchecked")
public interface JustMethods<T, M, F, C> {
	T Interface(Class<T> iface, M... methods);
	M Method(String name, C body, F... formals); // Field
	F Formal(String name, Class<?> type); // Key
}
