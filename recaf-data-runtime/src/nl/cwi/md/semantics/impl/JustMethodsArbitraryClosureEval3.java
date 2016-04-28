package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Pair;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.alg.JustMethodsArbitraryClosure3;

public class JustMethodsArbitraryClosureEval3<T> implements
		JustMethodsArbitraryClosure3<T, Map<String, BiFunction<T, Object[], Object>>, Pair<String, BiFunction<T, Object[], Object>>, FormalR> {

	@Override
	public T Interface(Class<T> iface, Map<String, BiFunction<T, Object[], Object>> methods) {
		return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{ iface }, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						return methods.get(method.getName()).apply((T) proxy, args==null?new Object[0]:args);
					}
				});
	}

	@Override
	public Map<String, BiFunction<T, Object[], Object>> Body(Pair<String, BiFunction<T, Object[], Object>>... methods) {
		Map<String, BiFunction<T, Object[], Object>> map =
				new HashMap<>();
		for (Pair<String, BiFunction<T, Object[], Object>> m: methods)
			map.put(m.getFst(), m.getSnd());
		return map;
	}

	@Override
	public Pair<String, BiFunction<T, Object[], Object>> Method(String name, FormalR[] formals, Closure body) {
		return new Pair<>(name, 
				(self, args) -> {
					Method[] ms = body.getClass().getDeclaredMethods();
					Method m = Arrays.asList(ms).stream().filter(me -> {
						return me.getName().equals("apply");
					}).findAny().get();
					Object[] newArgs = new Object[args.length + 1];
					newArgs[0] = self;
					for (int i = 0; i < args.length; i++)
						newArgs[i + 1] = args[i];
					try {
						m.setAccessible(true);
						return m.invoke(body, newArgs);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				});
	}

	@Override
	public FormalR Formal(String name, Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}


}
