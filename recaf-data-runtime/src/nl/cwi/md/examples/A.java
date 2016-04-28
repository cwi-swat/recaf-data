package nl.cwi.md.examples;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.InheritanceEval;

public interface A {
	
	static InheritanceEval<A, Object> alg = new InheritanceEval<A, Object>() {
		
	};
	
	String foo();
	String bar();
	
	static A New() {
		return alg.Interface(A.class, 
				alg.Body(
						alg.Method("foo", new FormalR[0],
							new Closure(){
								public Object apply(A self){
									return "I am A";
								}
							}),
						alg.Method("bar", new FormalR[0],
								new Closure(){
									public Object apply(A self){
										return "bar";
									}
								})
						));
	}
}
