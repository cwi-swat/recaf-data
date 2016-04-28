package nl.cwi.md.semantics.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.runtime.impl.MapFieldImplementation;
import nl.cwi.md.semantics.alg.WithFields;

public class WithFieldsEval<T> extends JustMethodsEval<T> implements WithFields<T, MethodR<T, FormalR>,FormalR, BiFunction<T, Object[], Object>>, MapFieldImplementation{

	private Map<Object, Map<String, Object>> objects = new HashMap<>();
	
	@Override
	public T Interface(Class<T> iface, MethodR<T, FormalR>... methods) {
		T obj = super.Interface(iface, methods);
		objects.put(obj, new HashMap<>());
		return obj;
	};
	
	@Override
	public MethodR<T, FormalR> Field(String name, Class<?> type) {
		return super.Method(name,
				(self, args) ->{
					if (args.length == 1) {
						Object[] varargs = (Object[]) args[0];
						if (varargs.length == 0)
							return fieldAccess(self, name);
						else if (varargs.length == 1){
							if (type.isInstance(varargs[0])) {
								return fieldWrite(self, name, varargs[0]);
							} else
								throw new RuntimeException();
						}	
						else // varargs cannot be greater than 1
							throw new RuntimeException();
					}
					else
						// Wrong code generation. It should never be reachable
						throw new RuntimeException();
				}
			);
	}

	@Override
	public Map<String, Object> fieldValues(Object target) {
		return objects.get(target);
	}


	
}
