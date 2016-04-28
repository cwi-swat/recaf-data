package nl.cwi.md.examples;

import nl.cwi.md.annos.Constructor;
import nl.cwi.md.annos.Managed;
import nl.cwi.md.annos.Method;
import nl.cwi.md.semantics.impl.InheritanceEval;

@Managed(alg = InheritanceEval.class)
public interface BPre extends APre {
	
	@Method
	default String foo(){
		return "I am B";
	}
	
	@Constructor
	public static B New(){
		return null;
	}

}
