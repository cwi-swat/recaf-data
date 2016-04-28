package nl.cwi.md.semantics.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Pair;
import nl.cwi.md.semantics.alg.MethodsAndFields3;

public class MethodsAndFieldsEval3<T> extends JustMethodsArbitraryClosureEval3<T> implements
		MethodsAndFields3<T, Map<String, BiFunction<T, Object[], Object>>, Pair<String, BiFunction<T, Object[], Object>>, FormalR> {

	@Override
	public Pair<String, BiFunction<T, Object[], Object>> Field(String name, Class<?> type) {
		final Object[] var = new Object[1];
		return new Pair<>(name, 
				(self, args) -> {
					if (args.length == 1) {
						Object[] varargs = (Object[]) args[0];
						if (varargs.length == 0)
							return var[0];
						else if (varargs.length == 1){
							if (type.isInstance(varargs[0])) {
								var[0] = varargs[0];
								return var[0];
							} else
								throw new RuntimeException();
						}	
						else // varargs cannot be greater than 1
							throw new RuntimeException();
					}
					else
						// Wrong code generation. It should never be reachable
						throw new RuntimeException();
				});
	}

	

}
