package nl.cwi.md.semantics.oominheritance.ast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;

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
	public Object handle(T self, Map<Class<?>, Object> parents, Object[] args) {
		Method[] ms = body.getClass().getDeclaredMethods();
		Method m = Arrays.asList(ms).stream().filter(me -> {
			return me.getName().equals("apply");
		}).findAny().get();
		Object[] newArgs = new Object[args.length + 2];
		newArgs[0] = self;
		newArgs[1] = parents;
		for (int i = 0; i < args.length; i++)
			newArgs[i + 2] = args[i];
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
