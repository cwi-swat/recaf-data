package nl.cwi.md.semantics.noformals.impl;

import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Pair;
import nl.cwi.md.TriFunction;
import nl.cwi.md.semantics.noformals.alg.MethodsAndFields3b;

public class MethodsAndFieldsEval3b<T> extends JustMethodsArbitraryClosureEval3b<T> implements
		MethodsAndFields3b<T, Map<String, BiFunction<T, Object[], Object>>, Pair<String, BiFunction<T, Object[], Object>>> {

	@Override
	public Pair<String, BiFunction<T, Object[], Object>> Field(String name) {
		Object[] val = new Object[1];
		return new Pair<>(name, 
				(self, args) -> {
					if (args.length == 1) {
						Object[] varargs = (Object[]) args[0];
						if (varargs.length == 0)
							return val[0];
						else if (varargs.length == 1){
							//if (type.isInstance(varargs[0])) {
								val[0] = varargs[0];
								return varargs[0];
							
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
