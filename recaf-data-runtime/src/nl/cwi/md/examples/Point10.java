package nl.cwi.md.examples;

import nl.cwi.md.semantics.impl.WithFieldsEvalWithFieldsAdvice;

public interface Point10 {
	static WithFieldsEvalWithFieldsAdvice<Point10> alg = new WithFieldsEvalWithFieldsAdvice<Point10>(){
		@Override
		public Object adviceField(String name, Object o) {
			System.err.println("INFO: Accessing field " + name );
			return o;
		}
		
	};
	
	Integer x(Integer...xs);
	
	static Point10 New(){
		return alg.Interface(Point10.class, 
				alg.Field("x", Integer.class));
	}
	
}

