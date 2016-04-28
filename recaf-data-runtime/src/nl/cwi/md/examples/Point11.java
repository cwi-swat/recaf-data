package nl.cwi.md.examples;

import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.JustMethodsArbitraryClosureEval;

public interface Point11 {
	static JustMethodsArbitraryClosureEval<Point11> alg = new JustMethodsArbitraryClosureEval<Point11>();
	
	Integer getX();
	Integer getY();
	
	interface MyFunc {
	}
	
	static Point7 New(){
//		return alg.Interface(Point7.class, 
//				alg.Method("getX", new FormalR[0], new MyFunc() {
//					int apply(Point7 self) {
//						return 3;
//					}
//				}),
//				alg.Method("getY", new FormalR[0], (Point7 self) -> { return 7;}));
		return null;
	}
	
}
