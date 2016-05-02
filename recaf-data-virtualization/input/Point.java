package generated;

import nl.cwi.md.semantics.oominheritance.impl.FieldsImpl;

@Managed
public interface Point {
	@Algebra
	static FieldsImpl algebra = new FieldsImpl();
	
	@Field
	Integer x(Integer... xs);
	
	@Field
	Integer y(Integer... ys);

	@Method
	default Integer distance(){
		return (int) Math.sqrt(this.x()*this.x() + this.y() * this.y());
	} 
	 
}
