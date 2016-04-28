package nl.cwi.md.semantics.oo.ast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import nl.cwi.md.semantics.alg.Closure;

public class MMethod<T> implements Member<T> {

	private String name;
	private Formal[] formals;
	private Closure body;

	public MMethod(String name, Formal[] formals, Closure body) {
		super();
		this.name = name;
		this.formals = formals;
		this.body = body;
	}

	@Override
	public Object handle(T self, Object[] args) {
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
	}

	@Override
	public String name() {
		return name;
	}

}
