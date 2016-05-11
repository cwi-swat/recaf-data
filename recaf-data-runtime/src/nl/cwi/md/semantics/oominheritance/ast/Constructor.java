package nl.cwi.md.semantics.oominheritance.ast;

import java.util.HashMap;
import java.util.Map;

import nl.cwi.md.semantics.oo.ast.Formal;

public class Constructor<T> implements Member<T>{

	private Formal head;
	private Formal[] formals;
	
	public Constructor(Formal head, Formal[] formals) {
		super();
		this.head = head;
		this.formals = formals;
	}

	@Override
	public Object handle(T self, Object[] parents, Object[] args){
		throw new RuntimeException();
	}

	public Formal[] getFormals() {
		return formals;
	}

	public Map<String, Object> computeStore(Object[] initArgs) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i<formals.length; i++){
			map.put(formals[i].getName(), initArgs[i]);
		}
		return map;
	}

	public String name() {
		return head.getName();
	}

}
