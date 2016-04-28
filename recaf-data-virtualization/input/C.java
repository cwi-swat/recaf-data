package generated;

import nl.cwi.md.semantics.oominheritance.impl.BaseMInheritanceImpl;
import nl.cwi.md.annos.Method;
import nl.cwi.md.annos.Managed;  
 
@Managed(alg = BaseMInheritanceImpl.class) 
public interface C extends generated.A {
	@Method
	default String foo() {
		return "I am C";
	}

	@Method
	default String c() { 
		return "c";
	}
}