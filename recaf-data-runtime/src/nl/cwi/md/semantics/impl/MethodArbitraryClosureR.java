package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MethodArbitraryClosureR<T, F> {
	private String name;
	private Object body;
	private List<F> formals;
	
	public MethodArbitraryClosureR(String name, Object body, List<F> formals) {
		super();
		this.name = name;
		this.body = body;
		this.formals = formals;
	}


	public String getName() {
		return name;
	}

	public Object getBody() {
		return body;
	}

	public List<F> getFormals() {
		return formals;
	}

	
	public Object apply(T tgt, Object... args) {
		Method m = body.getClass().getMethods()[0];
		Object[] newArgs = new Object[args.length+1];
		newArgs[0] = tgt;
		for (int i=0; i<args.length; i++) newArgs[i+1] = args[i];
		try {
			return m.invoke(body, newArgs);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public void setFormals(List<F> formals) {
		this.formals = formals;
	}

}
