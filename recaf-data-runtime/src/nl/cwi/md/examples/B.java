package nl.cwi.md.examples;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.InheritanceEval;

public interface B extends A {

	static InheritanceEval<B, A> alg = new InheritanceEval<B, A>() {

	};

	String foo();

	static B New() {
		return alg.Interface(A.class, A.New(), B.class, alg.Body(alg.Method("foo", new FormalR[0], new Closure() {
			public Object apply(B self) {
				return "I am B";
			}
		})));
	}
}
