package nl.cwi.md.semantics.alg;

public interface WithFields<T, M, F, C> extends JustMethods<T, M, F, C> {
	M Field(String name, Class<?> type); // Field
}
