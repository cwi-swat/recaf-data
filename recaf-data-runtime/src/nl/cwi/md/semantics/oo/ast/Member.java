package nl.cwi.md.semantics.oo.ast;

public interface Member<T> {
	Object handle(T self, Object[] args);
	String name();
}	
