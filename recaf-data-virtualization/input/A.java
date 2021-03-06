package generated;

import nl.cwi.md.semantics.oominheritance.alg.BaseMInheritanceAST;
import nl.cwi.md.semantics.oominheritance.impl.BaseMInheritanceImpl;
import nl.cwi.md.annos.Method;
import nl.cwi.md.annos.Managed;  
 
@Managed
public interface A{
	
	@Algebra
	static BaseMInheritanceAST algebra = new BaseMInheritanceImpl();
	
	@Method
	default String foo() {
		return "I am A";
	}

	@Method
	default String bar() {
		return "bar";
	}
	
	@Method
	default String thisFoo() {
		return this.foo();
	}
}