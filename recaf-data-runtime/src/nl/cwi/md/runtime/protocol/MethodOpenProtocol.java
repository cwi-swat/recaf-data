package nl.cwi.md.runtime.protocol;

import java.util.function.BiFunction;

public interface MethodOpenProtocol<T> {
	Object handleMethodCall(String name, BiFunction<T, Object[], Object>  fun, T target, Object[] args);
}
