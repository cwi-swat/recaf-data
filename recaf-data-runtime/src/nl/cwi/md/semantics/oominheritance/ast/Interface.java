package nl.cwi.md.semantics.oominheritance.ast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import nl.cwi.md.RecafUtils;

public class Interface<T> implements InvocationHandler {
	private Body<T> body;
	private Object[] parents;
	private T self;
	private T proxy;

	public Interface(Object algebra, Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body, Object[] initArgs) {
		super();
		this.body = body;
		this.proxy = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface }, this);
		if (self == null)
			this.self = this.proxy;
		else
			this.self = self;
		this.parents = RecafUtils.reflectiveParentsNew(algebra, parentIfaces, this.self, initArgs);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return body.invoke(method.getName(), self, parents, args == null ? new Object[0] : args);
		} catch (UnsupportedOperationException e) {
			for (int i = 0; i < parents.length; i++) {
				if (Arrays.asList(parents[i].getClass().getMethods()).stream().anyMatch(m -> {
					return m.getName().equals(method.getName())
							&&  Arrays.equals(m.getParameterTypes(), method.getParameterTypes());
				})) {
					Method m = parents[i].getClass().getMethod(method.getName(), method.getParameterTypes());
					m.setAccessible(true);
					return m.invoke(parents[i], args);
				}
			}
			throw new UnsupportedOperationException();
		}
	}

	public T getProxy() {
		return proxy;
	}

}
