package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.alg.Closure;

@SuppressWarnings("unchecked")
public interface BaseMInheritance<T, B, M, F> {
	T Interface(Class<T> iface, Class<?>[] parentIfaces, T self, B body, Object... args);
	B Body(M...members);
	M Method(F head, Closure body, F... formals); // Field
	F Formal(String name, Class<?> type, boolean isVarArg, Class<?> typeArg); // Key
	F Formal(String name, Class<?> type, boolean isVararg);
	F Formal(String name, Class<?> type);
}
