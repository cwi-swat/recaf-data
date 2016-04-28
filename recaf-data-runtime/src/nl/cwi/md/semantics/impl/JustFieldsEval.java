package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nl.cwi.md.semantics.alg.JustFields;

public class JustFieldsEval<T> implements JustFields<T,T> {

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Map<String, Class<?>> fields) {
		final Map<String, Object> fieldValues = new HashMap<>();
		for (String fName : fields.keySet()) {
			fieldValues.put(fName, null);
		}
		return (T) java.lang.reflect.Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (fields.containsKey(method.getName())) {
							// We assume an array as argument, as getter/setter is implemented via a vararg method
							if (args.length == 1) {
								Object[] varargs = (Object[]) args[0];
								if (varargs.length == 0)
									return fieldValues.get(method.getName());
								else if (varargs.length == 1){
									if (fields.get(method.getName()).isInstance(varargs[0])) {
										fieldValues.put(method.getName(), varargs[0]);
										return fieldValues.get(method.getName());
									} else
										throw new Exception();
								}	
								else // varargs cannot be greater than 1
									throw new Exception();
							} else
								throw new Exception();
						} else
							throw new Exception();
					}
				});

	}

}
