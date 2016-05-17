package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Collection;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.impl.FieldsImpl;
import nl.cwi.md.util.AwareCollection2;

public class Container<T> implements Member<T> {
	private String inverseField;
	private Formal formal;
	private Object value;
	
	
	public Container(String inverseField, Formal formal, FieldsImpl<T> ast) {
		super();
		this.inverseField = inverseField;
		this.formal = formal;
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

	private Object getValue() {
		return value;
	}

	protected void setValue(T self, Object v) {
		if (v instanceof Collection){
			this.value = AwareCollection2
					.buildAwareCollection(inverseField, self, (Collection<?>) v);
		}
		else
			this.value = v;
	}

	@Override
	public String name() {
		return formal.getName();
	}

}
