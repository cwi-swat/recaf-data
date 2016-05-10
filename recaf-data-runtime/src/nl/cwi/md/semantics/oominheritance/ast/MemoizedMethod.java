package nl.cwi.md.semantics.oominheritance.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;

public class MemoizedMethod<T> extends MMethod<T> {

	public static Map<List<Object>, Object> memoTable = new HashMap<>();

	public MemoizedMethod(String name, Formal[] formals, Closure body) {
		super(name, formals, body);
	}

	private List<Object> computeKey(Class<?> iface, Object[] initArgs) {
		List<Object> k = new ArrayList<>();
		k.add(iface);
		k.add(name());
		for (int i = 0; i < initArgs.length; i++) {
			k.add(initArgs[i]);
		}
		return k;
	}

	@Override
	public Object handle(T self, Object[] parents, Object[] args) {
		List<Object> key = computeKey(self.getClass(), args);
		if (!memoTable.containsKey(key)) {
			memoTable.put(key, super.handle(self, parents, args));
		}
		return memoTable.get(key);
	}

}
