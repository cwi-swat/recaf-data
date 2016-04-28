package nl.cwi.md.semantics.ooinheritance.alg;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.ooinheritance.ast.Body;

@SuppressWarnings("unchecked")
public interface BaseInheritance<T, B, M, F> {
	T Interface(Class<T> iface, Cell<? extends T> self, Object parent, Body<T> body, Object...args);
	B Body(M...members);
	M Method(String name, F[] formals, Closure body); // Field
	F Formal(String name, Class<?> type); // Key
}
