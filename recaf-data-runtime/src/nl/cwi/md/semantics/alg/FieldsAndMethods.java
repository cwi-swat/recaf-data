package nl.cwi.md.semantics.alg;

import java.util.Map;
import java.util.function.BiFunction;

public interface FieldsAndMethods<O,T> {
	O Interface(Class<T> iface, Map<String, Class<?>> fields, Map<String, BiFunction<T, Object[], Object>> methods, Map<String, Class<?>[]> methodSignatures);
}
