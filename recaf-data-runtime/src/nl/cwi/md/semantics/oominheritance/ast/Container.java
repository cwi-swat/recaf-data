package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Collection;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.util.AwareCollection;

public class Container<T> extends Field<T> {

	
	public Container(Formal formal) {
		super(formal);
	}

	@Override
	protected void setValue(T self, Object v) {
		if (v instanceof Collection){
			super.setValue(self, AwareCollection.buildAwareCollection(self, (Collection<?>) v));
		}
		else
			super.setValue(self, v);
	}

}
