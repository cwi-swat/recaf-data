package nl.cwi.md.semantics.oo.ast;

public class Formal {
	private String name;
	private Class<?> type;
	private boolean isVarArg = false;
	private Class<?> typeArgument;

	public Formal(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Formal(String name, Class<?> type, boolean isVararg, Class<?> typeArgument) {
		this(name, type, isVararg);
		this.typeArgument = typeArgument;
	}

	public Formal(String name, Class<?> type, boolean isVararg) {
		this(name, type);
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

	public Class<?> getTypeArgument() {
		return typeArgument;
	}

}
