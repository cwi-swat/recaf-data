package nl.cwi.md.semantics.ooinheritance.ast;

public interface Member<T> {
	Object handle(T self, Object parent, Object[] args);
	String name();
}	
