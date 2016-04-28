package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.semantics.alg.FieldsAndMethods;
import nl.cwi.md.semantics.impl.FieldsAndMethodsEval;

public interface Point3 {
	static FieldsAndMethods<Point3, Point3> alg = new FieldsAndMethodsEval<Point3>();
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	
	Integer distance();
	
	static Point3 New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		Map<String, BiFunction<Point3, Object[], Object>> methods = new HashMap<>();
		Map<String, Class<?>[]> signatures = new HashMap<>();
		methods.put("distance", 
				(self, args) -> {
					return Integer.valueOf((int) Math.sqrt(self.x()*self.x() + self.y()*self.y()));
				});
		return alg.Interface(Point3.class, fields, methods, signatures);
	}
	
}
