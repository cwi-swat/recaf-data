package generated;

import nl.cwi.md.semantics.oominheritance.impl.ImmutableFieldsImpl;


@Managed
public interface Lit {
	@Algebra
	static ImmutableFieldsImpl algebra = new ImmutableFieldsImpl();
	
	@Field
	Integer n(Integer... xs);
	
	//The return type is irrelevant. Convention is Object.
	//Is name also irrelevant? Convention is the same as the type.
	@Constructor
	Object Lit(Integer n); 
	 
}