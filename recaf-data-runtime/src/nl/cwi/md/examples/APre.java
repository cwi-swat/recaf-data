package nl.cwi.md.examples;

import nl.cwi.md.annos.Constructor;
import nl.cwi.md.annos.Managed;
import nl.cwi.md.annos.Method;
import nl.cwi.md.semantics.impl.InheritanceEval;

@Managed(alg = InheritanceEval.class)
public interface APre {
	
	@Method
	default  String bar(){
		return "bar";
	}
	
		@Method
	default String foo(){
		return "I am A";
	}
	
	@Constructor
	public static A New(){
		return null;
	}

}
