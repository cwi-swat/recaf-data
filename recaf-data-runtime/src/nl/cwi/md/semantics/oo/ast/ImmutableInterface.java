package nl.cwi.md.semantics.oo.ast;

import java.lang.reflect.Method;
import java.util.Map;

public class ImmutableInterface<T> extends Interface<T> {
	
	public ImmutableInterface(Class<?> iface, Body<T> body, Object[] initArgs) {
		super(iface, body, initArgs);
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

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// equals
		return super.invoke(proxy, method, args);
	}

}
