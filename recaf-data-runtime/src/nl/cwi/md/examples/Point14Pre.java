package nl.cwi.md.examples;

import nl.cwi.md.annos.Constructor;
import nl.cwi.md.annos.Field;
import nl.cwi.md.annos.Managed;
import nl.cwi.md.annos.Method;
import nl.cwi.md.semantics.impl.MethodsAndFieldsEval3;

@Managed(alg = MethodsAndFieldsEval3.class)
public interface Point14Pre {
	
	@Field
	Integer x(Integer... xs);
	
	@Field
	Integer y(Integer... ys);

	@Method
	default Integer distance(){
		return (int) Math.sqrt(this.x()*this.x() + this.y() * this.y());
	}
	
	@Constructor
	public static Point14 New(){
		return null;
	}

	public static Point14 New(Integer x, Integer y){
		Point14 point14 = New();
		point14.x(x);
		point14.y(y);
		return point14;
	}

}
