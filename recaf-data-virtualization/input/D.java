package generated;

import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.semantics.oominheritance.impl.BaseMInheritanceImpl;
import nl.cwi.md.annos.Method;
import nl.cwi.md.annos.Managed;

@Managed
public interface D extends generated.B, generated.C { 
	@Algebra
	static BaseMInheritanceAST algebra = null;
	
	@Method
	default String foo() {
		return "I am D";
	}

	@Method 
	default String parentFoo() {
		// TODO: B must be fully qualified
		// return B.super.foo();
		return generated.B.super.foo();
	}

}