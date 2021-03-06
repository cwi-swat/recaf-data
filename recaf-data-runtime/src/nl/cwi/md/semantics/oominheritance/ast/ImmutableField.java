package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Map;

import nl.cwi.md.semantics.oo.ast.Formal;

public class ImmutableField<T> extends Field<T> {

	public ImmutableField(Formal formal) {
		super(formal);
	}
	
	public void setInitialValue(Map<String,Object> map){
		super.setValue(map.get(getFormal().getName()));
	}
	
	@Override
	protected void setValue(Object v) {
		throw new IllegalStateException("Immutable field cannot be overwritten.");
	}
	

}
