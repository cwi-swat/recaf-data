package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Map;

import nl.cwi.md.Cell;

public class ImmutableInterface<T> extends Interface<T> {
	
	public ImmutableInterface(Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body, Object[] initArgs) {
		super(iface, parentIfaces, self, body, initArgs);
		Map<String, Object> store = null;
		for (Member<T> m : body.getMembers()){
			if (m instanceof Constructor){
				Constructor<T> c = (Constructor<T>) m;
				if (initArgs.length == c.getFormals().length){
					store = c.computeStore(initArgs);
				}
			}
		}
		for (Member<T> m : body.getMembers()){
			if (m instanceof ImmutableField){
				ImmutableField<T> f = (ImmutableField<T>) m;
				// let field decide itself to get the value from the map
				// no need to have the indirection via positions.
				f.setInitialValue(store);
			}
		}
		
	}

}
