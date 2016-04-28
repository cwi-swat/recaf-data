package nl.cwi.md.semantics.oo.ast;

import java.util.Map;

public class ImmutableField<T> extends Field<T> {

	public ImmutableField(Formal formal) {
		super(formal);
	}
	
	public void setInitialValue(Map<String,Object> map){
		super.setValue(map.get(getFormal().getName()));
	}
	
	@Override
	protected void setValue(Object v) {
		throw new IllegalStateException("Immutable field canno\t be overwritten.");
	}
	

}
