package nl.cwi.md.semantics.oominheritance.ast;

import nl.cwi.md.semantics.oo.ast.Formal;

public class Field<T> implements Member<T> {

	private Formal formal;
	private Object value;

	public Field(Formal formal) {
		super();
		this.formal = formal;
	}

	protected void setValue(T self, Object v) {
		this.value = v;
	}

	protected Object getValue() {
		return value;
	}

	public Formal getFormal() {
		return formal;
	}

	@Override
	public Object handle(T self, Object[] parents, Object[] args){
		// Why consider varargs and not varargs?
		if (args.length == 0)
			return getValue();
		if (args.length == 1) {
			Object[] varargs = (Object[]) args[0];
			if (varargs.length == 0)
				return getValue();
			else if (varargs.length == 1) {
				// if (type.isInstance(varargs[0])) {
				setValue(self, varargs[0]);
				return varargs[0];

			} else // varargs cannot be greater than 1
				throw new RuntimeException();
		} else
			// Wrong code generation. It should never be reachable
			throw new RuntimeException();
	}

	@Override
	public String name() {
		return formal.getName();
	}

}
