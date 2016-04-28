package nl.cwi.md.examples;

import nl.cwi.md.semantics.impl.CaseClassEval;

public interface Lit {
	
	static CaseClassEval<Lit, Exp> alg = new CaseClassEval<Lit, Exp>() {
		
	};
	
	Integer n();
	
	static Lit New(Object... n) {
		return alg.Interface(Exp.class, Lit.class,
				alg.Body(
						alg.ImmutableField("n", n)));
						
	}
}
