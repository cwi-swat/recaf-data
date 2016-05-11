package nl.cwi.md.semantics.oominheritance.ast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.md.RecafUtils;
import nl.cwi.md.semantics.alg.ProxyProvider;
import nl.cwi.md.semantics.oo.ast.Formal;

public class MSImmutableInterface<T> implements InvocationHandler, ProxyProvider<T> {
	public static Map<List<Object>, Object> msHeap = new HashMap<List<Object>, Object>();

	private Body<T> body;
	private Object[] parents;
	private T self;
	private T proxy;

	public MSImmutableInterface(Object algebra, Class<T> iface, Class<?>[] parentIfaces, T self, Body<T> body,
			Object[] initArgs) {
		super();

		List<Object> key = computeKey(iface, initArgs);
		if (msHeap.containsKey(key)) {
			this.proxy = (T) msHeap.get(key);
		} else {
			Map<String, Object> store = null;
			for (Member<T> m : body.getMembers()) {
				if (m instanceof Constructor) {
					Constructor<T> c = (Constructor<T>) m;
					if (initArgs.length == c.getFormals().length) {
						store = c.computeStore(initArgs);
					}
				}
			}

			
			this.body = body;
			this.proxy = (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[] { iface }, this);
			if (self == null)
				this.self = this.proxy;
			else
				this.self = self;
			
			for (Member<T> m : body.getMembers()) {
				if (m instanceof ImmutableField) {
					ImmutableField<T> f = (ImmutableField<T>) m;
					// let field decide itself to get the value from the map
					// no need to have the indirection via positions.
					f.setInitialValue(this.self, store);
				}
			}
			
			this.parents = RecafUtils.reflectiveParentsNew(algebra, parentIfaces, this.self, initArgs);
			msHeap.put(key, this.proxy);
		}

	}

	private List<Object> computeKey(Class<T> iface, Object[] initArgs) {
		List<Object> k = new ArrayList<>();
		k.add(iface);
		for (int i = 0; i < initArgs.length; i++) {
			k.add(initArgs[i]);
		}
		return k;
	}

	@Override
	public T getProxy() {
		return proxy;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return body.invoke(method.getName(), self, parents, args == null ? new Object[0] : args);
		} catch (UnsupportedOperationException e) {
			for (int i = 0; i < parents.length; i++) {
				if (Arrays.asList(parents[i].getClass().getMethods()).stream().anyMatch(m -> {
					return m.getName().equals(method.getName())
							&& Arrays.equals(m.getParameterTypes(), method.getParameterTypes());
				})) {
					Method m = parents[i].getClass().getMethod(method.getName(), method.getParameterTypes());
					m.setAccessible(true);
					return m.invoke(parents[i], args);
				}
			}
			throw new UnsupportedOperationException();
		}
	}

}
