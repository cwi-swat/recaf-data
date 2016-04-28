package nl.cwi.md.semantics.oo.ast;

import java.util.HashMap;
import java.util.Map;

public class Constructor<T> implements Member<T>{

	private String name;
	private Formal[] formals;
	
	public Constructor(String name, Formal[] formals) {
		super();
		this.name = name;
		this.formals = formals;
	}

	@Override
	public Object handle(T self, Object[] args) {
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
		return name;
	}

}
