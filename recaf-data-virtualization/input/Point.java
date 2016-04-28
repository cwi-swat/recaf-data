package generated;

import nl.cwi.md.semantics.oominheritance.impl.FieldsImpl;

@Managed(alg = FieldsImpl.class)
public interface Point {
	
	@Field
	Integer x(Integer... xs);
	
	@Field
	Integer y(Integer... ys);

	@Method
	default Integer distance(){
		return (int) Math.sqrt(this.x()*this.x() + this.y() * this.y());
	} 
	 
}
