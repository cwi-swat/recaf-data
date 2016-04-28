package nl.cwi.md.examples;

import nl.cwi.md.semantics.impl.JustMethodsEval;

public interface Point7 {
	static JustMethodsEval<Point7> alg = new JustMethodsEval<Point7>();
	
	Integer getX();
	Integer getY();
	
	
	static Point7 New(){
		return alg.Interface(Point7.class, 
				alg.Method("getX", (self, args) -> { return 3;}),
				alg.Method("getY", (self, args) -> { return 7;}));
	}
	
}
