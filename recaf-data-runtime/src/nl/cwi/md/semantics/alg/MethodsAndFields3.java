package nl.cwi.md.semantics.alg;

public interface MethodsAndFields3<T, B, M, F> extends JustMethodsArbitraryClosure3<T, B, M, F> {
	M Field(String name, Class<?> type); // Field
}
