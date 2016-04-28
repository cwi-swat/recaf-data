package nl.cwi.md.semantics.ooinheritance.ast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import nl.cwi.md.Cell;

public class Interface<T> implements InvocationHandler {
	private Body<T> body;
	private Object parent;
	private Cell<? extends T> self;

	public Interface(Object parent, Body<T> body, Object... initArgs) {
		super();
		this.body = body;
		this.parent = parent;
	}

	public void setSelf(Cell<? extends T> self) {
		this.self = self;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return body.invoke(method.getName(), self.getValue(), parent, args == null ? new Object[0] : args);
		} catch (UnsupportedOperationException e) {
			Method m = parent.getClass().getMethod(method.getName(), method.getParameterTypes());
			m.setAccessible(true);
			return m.invoke(parent, args);
		}
	}

}
