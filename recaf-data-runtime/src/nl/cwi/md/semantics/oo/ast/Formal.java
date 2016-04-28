package nl.cwi.md.semantics.oo.ast;

public class Formal {
	private String name;
	private Class<?> type;
	private boolean isVarArg = false;
	
	public Formal(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public Formal(String name, Class<?> type, boolean isVararg) {
		super();
		this.name = name;
		this.type = type;
		this.isVarArg = isVararg;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public boolean isVarArg() {
		return isVarArg;
	}
	
}
