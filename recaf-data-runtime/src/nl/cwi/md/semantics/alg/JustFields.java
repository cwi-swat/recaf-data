package nl.cwi.md.semantics.alg;

import java.util.Map;

public interface JustFields<O, T> {
	O Interface(Class<T> iface, Map<String, Class<?>> fields);
}
