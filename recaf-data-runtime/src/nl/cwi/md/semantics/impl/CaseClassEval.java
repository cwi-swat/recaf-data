package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Pair;
import nl.cwi.md.semantics.alg.CaseClass;
import nl.cwi.md.semantics.alg.Closure;

public class CaseClassEval<T, P> extends MethodsAndFieldsEval3<T> implements
		CaseClass<T, P, Map<String, BiFunction<T, Object[], Object>>, Pair<String, BiFunction<T, Object[], Object>>, FormalR> {

	private Map<String, Object> immutableFields = new HashMap<>();

	@Override
	public T Interface(Class<P> parentClass, Class<T> iface, Map<String, BiFunction<T, Object[], Object>> body) {
		T me = super.Interface(iface, body);
		return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface, parentClass },
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName() == "equals") {
							T another = (T) args[0];
							for (String field : immutableFields.keySet()) {
								Method m;
								try {
									m = another.getClass().getDeclaredMethod(field, new Class<?>[0]);
									m.setAccessible(true);
									Object anoField = m.invoke(another, new Object[0]);
									if (!immutableFields.get(field).equals(anoField))
										return Boolean.valueOf(false);
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
										| NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
							return Boolean.valueOf(true);
						}
						return me.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(me, args);

					}
				});

	}

	@Override
	public Pair<String, BiFunction<T, Object[], Object>> ImmutableField(String name, Object value) {
		immutableFields.put(name, value);
		return new Pair<>(name, (self, args) -> {
			if (args.length == 0)
				return value;
			else
				// Wrong code generation. It should never be reachable
				throw new RuntimeException();
		});
	}

}