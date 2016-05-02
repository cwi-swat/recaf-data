package generated;

import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.annos.Method;
import nl.cwi.md.annos.Managed;  
 
@Managed
public interface B extends generated.A {
	@Algebra
	BaseMInheritanceAST algebra();
	
	@Method
	default String foo() {
		return "I am B";
	}

	@Method
	default String b() {
		return "b";
	}
 
} 