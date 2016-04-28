package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Map;

public interface Member<T> {
	Object handle(T self, Map<Class<?>, Object> parents, Object[] args);
	String name();
}	
