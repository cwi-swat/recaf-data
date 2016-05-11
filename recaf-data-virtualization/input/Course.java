package generated;

import nl.cwi.md.semantics.oominheritance.impl.BidirectionalFieldsImpl;
import java.util.List;

@Managed
public interface Course {
	@Algebra
	static BidirectionalFieldsImpl algebra = new BidirectionalFieldsImpl();
	
	@Field
	String name(String...ss);
	
	@Container
	Collection<Student> students(Collection<Student>...ls);
	 
}
