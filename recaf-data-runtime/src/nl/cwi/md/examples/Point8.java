package nl.cwi.md.examples;

import java.util.function.BiFunction;

import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.JustMethodsEval;
import nl.cwi.md.semantics.impl.MethodR;

public interface Point8 {
	static JustMethodsEval<Point8> alg = new JustMethodsEval<Point8>(){
		@Override
		public MethodR<Point8, FormalR> Method(String name, BiFunction<Point8, Object[], Object> body,
				FormalR... formals) {
			return super.Method(name, 
					(self, args) -> {
						System.out.println("INFO: Before calling method " + name);
						Object val = body.apply(self, args);
						System.out.println("INFO: After calling method " + name);
						return val;
					},
					formals);
		}
	};
	
	Integer getX();
	Integer getY();
	
	
	static Point8 New(){
		return alg.Interface(Point8.class, 
				alg.Method("getX", (self, args) -> { return 3;}),
				alg.Method("getY", (self, args) -> { return 7;}));
	}
	
}

