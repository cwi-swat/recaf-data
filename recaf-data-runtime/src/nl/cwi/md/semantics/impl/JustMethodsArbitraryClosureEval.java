package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.alg.JustMethodsArbitraryClosure;

public class JustMethodsArbitraryClosureEval<T>
		implements JustMethodsArbitraryClosure<T, MethodArbitraryClosureR<T, FormalR>, FormalR> {
	private static final Method OBJECT_HASHCODE = getObjectMethod("hashCode");
	
	private int instances = 0;

	private static Method getObjectMethod(String name, Class... types) {
		try {
			// null 'types' is OK.
			return Object.class.getMethod(name, types);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T Interface(Class<T> iface, MethodArbitraryClosureR<T, FormalR> ... methods) {
		final Cell<T> self = new Cell<T>(null);
		final Map<String, MethodArbitraryClosureR<T, FormalR>> methodsMap = new HashMap<>();
		for (MethodArbitraryClosureR<T, FormalR> m : methods)
			methodsMap.put(m.getName(), m);
		final int hash = instances++;
		self.setValue((T) java.lang.reflect.Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (OBJECT_HASHCODE.equals(method)) {
							return hash;
						}
						if (methodsMap.containsKey(method.getName())) {
							// type check needed
							return methodsMap.get(method.getName()).apply(self.getValue(), args);
						} else
							throw new Exception();
					}
				}));
		return self.getValue();

	}

	@Override
	public MethodArbitraryClosureR<T, FormalR> Method(String name, FormalR[] formals, Closure body) {
		return new MethodArbitraryClosureR<T, FormalR>(name, body, Arrays.asList(formals));
	}

	@Override
	public FormalR Formal(String name, Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}


}
