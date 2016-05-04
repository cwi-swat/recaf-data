package generated;

import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.annos.Method;
import nl.cwi.md.annos.Managed;  
 
@Managed 
public interface C extends generated.A {
	@Algebra
	static BaseMInheritanceAST algebra = null;
	
	@Method
	default String foo() {
		return "I am C";
	}

	@Method
	default String c() { 
		return "c";
	}
}