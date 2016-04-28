package nl.cwi.md.examples;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.MethodsAndFieldsEval3;

public interface Point14 {
	static MethodsAndFieldsEval3<Point14> alg = new MethodsAndFieldsEval3<Point14>() {
	
	};

	Integer x(Integer... xs);
	Integer y(Integer... ys);

	Integer distance();
	
	static Point14 New() {
		return alg.Interface(Point14.class, 
				alg.Body(
						alg.Field("x", Integer.class),
						alg.Field("y", Integer.class),
						alg.Method("distance", new FormalR[0],
							new Closure(){
								public Object apply(Point14 self){
									return Integer.valueOf((int) Math.sqrt(self.x()*self.x() + self.y()*self.y()));
								}
							})));
	}

}
