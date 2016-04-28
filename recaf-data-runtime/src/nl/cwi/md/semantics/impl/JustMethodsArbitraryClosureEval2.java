package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import nl.cwi.md.TriFunction;
import nl.cwi.md.WrongMethod;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.alg.JustMethodsArbitraryClosure2;

public class JustMethodsArbitraryClosureEval2<T>
		implements JustMethodsArbitraryClosure2<T, TriFunction<String, T, Object[], Object>, FormalR> {

	@Override
	public FormalR Formal(String name, Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TriFunction<String, T, Object[], Object> Method(String name, FormalR[] formals, Closure body) {
		return (nam, self, args) -> {
			if (name.equals(nam)) {
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
			} else
				throw new WrongMethod();
		};
	}

	@Override
	public T Interface(Class<T> iface, TriFunction<String, T, Object[], Object>... methods) {
		return (T) java.lang.reflect.Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						for (TriFunction<String, T, Object[], Object> m : methods) {
							try {
								return m.apply(method.getName(), (T) proxy, args == null ? new Object[0] : args);
							} catch (WrongMethod t) {
								continue;
							}
						}
						throw new RuntimeException("Not method found");
					}
				});
	}

}
