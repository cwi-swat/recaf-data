package nl.cwi.md.semantics.oominheritance.ast;

public interface Member<T> {
	Object handle(T self, Object[] parents, Object[] args);
	String name();
}	
