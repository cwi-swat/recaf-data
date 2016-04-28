package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Cell;
import nl.cwi.md.runtime.impl.MapFieldImplementation;
import nl.cwi.md.runtime.protocol.FieldOpenProtocol;
import nl.cwi.md.runtime.protocol.MethodOpenProtocol;
import nl.cwi.md.semantics.alg.FieldsAndMethods;

public abstract class FieldsAndMethodsEvalBase<T> implements FieldsAndMethods<T,T>, MapFieldImplementation, FieldOpenProtocol, MethodOpenProtocol<T> {
	
	final Map<Object, Map<String, Object>> allFields = new HashMap<>();
	
	public Map<String, Object> fieldValues(Object target){
		return allFields.get(target);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, Map<String, Class<?>> fields,
			Map<String, BiFunction<T, Object[], Object>> methods, Map<String, Class<?>[]> methodSignatures) {
		final Cell<T> self = new Cell<T>(null);
		allFields.put(self, new HashMap<>());
		for (String fName : fields.keySet()) {
			fieldValues(self).put(fName, null);
		}
		self.setValue((T) java.lang.reflect.Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (methods.containsKey(method.getName())){
							BiFunction<T, Object[], Object> fun = methods.get(method.getName());
							// type check needed
							return handleMethodCall(method.getName(), fun, self.getValue(), args);
						}
						if (fields.containsKey(method.getName())) {
							// We assume an array as argument, as getter/setter is implemented via a vararg method
							if (args.length == 1) {
								Object[] varargs = (Object[]) args[0];
								if (varargs.length == 0)
									return handleFieldAccess(self, method.getName());
								else if (varargs.length == 1){
									if (fields.get(method.getName()).isInstance(varargs[0])) {
										return handleFieldWrite(self, method.getName(), varargs[0]);
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
				}));
		return self.getValue();
	}
	
	public Object methodCall(BiFunction<T, Object[], Object>  fun, T t, Object[] args){
		return fun.apply(t, args);
	}
	
	@Override
	public Object handleFieldAccess(Object identity, String name){
		return fieldAccess(identity, name);
	}
	
	@Override
	public Object handleFieldWrite(Object identity, String name, Object val){
		return fieldWrite(identity, name, val);
	}

	@Override
	public Object handleMethodCall(String name, BiFunction<T, Object[], Object>  fun, T t, Object[] args){
		return methodCall(fun, t, args);
		
	}


}
