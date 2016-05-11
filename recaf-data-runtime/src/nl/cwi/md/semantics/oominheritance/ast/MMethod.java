package nl.cwi.md.semantics.oominheritance.ast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;

public class MMethod<T> implements Member<T> {

	private Formal head;
	private Formal[] formals;
	private Closure body;

	public MMethod(Formal head, Formal[] formals, Closure body) {
		super();
		this.head = head;
		this.formals = formals;
		this.body = body;
	}

	@Override
	public Object handle(T self, Object[] parents, Object[] args) {
		Method[] ms = body.getClass().getDeclaredMethods();
		Method m = Arrays.asList(ms).stream().filter(me -> {
			return me.getName().equals("apply");
		}).findAny().get();
		Object[] newArgs = new Object[args.length + 1 + parents.length];
		newArgs[0] = self;
		for (int i = 0; i < parents.length; i++)
			newArgs[i+1] = parents[i];
		for (int i =  0; i < args.length; i++)
			newArgs[parents.length + 1 + i] = args[i];
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
		return head.getName();
	}

}
