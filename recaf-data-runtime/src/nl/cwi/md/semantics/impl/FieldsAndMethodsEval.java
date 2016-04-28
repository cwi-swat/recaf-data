package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.FieldsAndMethods;

public class FieldsAndMethodsEval<T> implements FieldsAndMethods<T, T> {

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Map<String, Class<?>> fields,
			Map<String, BiFunction<T, Object[], Object>> methods, Map<String, Class<?>[]> methodSignatures) {
		final Map<String, Object> fieldValues = new HashMap<>();
		for (String fName : fields.keySet()) {
			fieldValues.put(fName, null);
		}
		final Cell<T> self = new Cell<T>(null);
		self.setValue((T) java.lang.reflect.Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (methods.containsKey(method.getName())) {
							BiFunction<T, Object[], Object> fun = methods.get(method.getName());
							// type check needed
							return fun.apply(self.getValue(), args);
						}
						if (fields.containsKey(method.getName())) {
							// We assume an array as argument, as getter/setter
							// is implemented via a vararg method
							if (args.length == 1) {
								Object[] varargs = (Object[]) args[0];
								if (varargs.length == 0)
									return fieldValues.get(method.getName());
								else if (varargs.length == 1) {
									if (fields.get(method.getName()).isInstance(varargs[0])) {
										fieldValues.put(method.getName(), varargs[0]);
										return fieldValues.get(method.getName());
									} else
										throw new Exception();
								} else // varargs cannot be greater than 1
									throw new Exception();
							} else
								throw new Exception();
						} else
							throw new Exception();
					}
				}));
		return self.getValue();
	}

}
