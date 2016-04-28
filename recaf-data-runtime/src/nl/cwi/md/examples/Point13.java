package nl.cwi.md.examples;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.JustMethodsArbitraryClosureEval2;
import nl.cwi.md.semantics.impl.JustMethodsArbitraryClosureEval3;

public interface Point13 {
	static JustMethodsArbitraryClosureEval3<Point13> alg = new JustMethodsArbitraryClosureEval3<Point13>() {

	};

	Integer getX();

	Integer getY();

	static Point13 New() {
		return alg.Interface(Point13.class, alg.Body(alg.Method("getX", new FormalR[0], new Closure() {
			public Object apply(Point13 self) {
				return 3;
			}
		}), alg.Method("getY", new FormalR[0], new Closure() {
			public Object apply(Point13 self) {
				return 7;
			}
		})));
	}

}
