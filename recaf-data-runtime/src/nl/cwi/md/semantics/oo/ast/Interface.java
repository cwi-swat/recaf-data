package nl.cwi.md.semantics.oo.ast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

public class Interface<T> implements InvocationHandler {
	private Class<?> iface;
	private Body<T> body;

	public Interface(Class<?> iface, Body<T> body, Object... initArgs) {
		super();
		this.iface = iface;
		this.body = body;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return body.invoke(method.getName(), (T) proxy, args == null ? new Object[0] : args);
	}

}
