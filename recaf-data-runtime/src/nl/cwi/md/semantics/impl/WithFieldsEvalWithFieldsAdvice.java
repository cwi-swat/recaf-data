package nl.cwi.md.semantics.impl;

import java.util.function.BiFunction;

public abstract class WithFieldsEvalWithFieldsAdvice<T> extends WithFieldsEval<T> {
	
	@Override
	public MethodR<T, FormalR> Field(String name, Class<?> type) {
		MethodR<T, FormalR> method = super.Field(name, type);
		BiFunction<T, Object[], ?> original = method.getBody();
		method.setBody((self, args) -> {
			Object o = original.apply(self, args);
			return adviceField(name, o); 
		});
		return method;
	}

	public abstract Object adviceField(String name, Object o);
}
