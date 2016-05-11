package generated;

import nl.cwi.md.semantics.oominheritance.impl.BidirectionalFieldsImpl;

@Managed
public interface Student {
	@Algebra
	static BidirectionalFieldsImpl algebra = new BidirectionalFieldsImpl();
	
	@Field
	String name(String...ss);
	
	@Field
	Integer birthYear(Integer...is);
	
	@Field
	Course course(Course... cs);	 
}
