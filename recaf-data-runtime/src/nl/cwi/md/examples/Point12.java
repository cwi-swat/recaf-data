package nl.cwi.md.examples;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.JustMethodsArbitraryClosureEval2;

public interface Point12 {
	static JustMethodsArbitraryClosureEval2<Point12> alg = new JustMethodsArbitraryClosureEval2<Point12>() {

	};

	Integer getX();

	Integer getY();

	static Point12 New() {
		return alg.Interface(Point12.class, alg.Method("getX", new FormalR[0], new Closure() {
			public Object apply(Point12 self) {
				return 3;
			}
		}), alg.Method("getY", new FormalR[0], new Closure() {
			public Object apply(Point12 self) {
				return 7;
			}
		}));
	}

}
