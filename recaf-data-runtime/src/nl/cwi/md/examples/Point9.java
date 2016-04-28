package nl.cwi.md.examples;

import java.util.function.BiFunction;

import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.MethodR;
import nl.cwi.md.semantics.impl.WithFieldsEval;

public interface Point9 {
	static WithFieldsEval<Point9> alg = new WithFieldsEval<Point9>(){
		@Override 
		public MethodR<Point9,FormalR> Field(String name, java.lang.Class<?> type) {
			MethodR<Point9, FormalR> method = super.Field(name, type);
			BiFunction<Point9, Object[], ?> original = method.getBody();
			method.setBody((self, args) -> {
				System.err.println("INFO: Accessing field " + name );
				return original.apply(self, args);
			});
			return method;
		};
	};
	
	Integer x(Integer...xs);
	
	static Point9 New(){
		return alg.Interface(Point9.class, 
				alg.Field("x", Integer.class));
	}
	
}

