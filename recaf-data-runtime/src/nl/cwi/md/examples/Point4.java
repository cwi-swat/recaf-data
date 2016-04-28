package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.semantics.alg.FieldsAndMethods;
import nl.cwi.md.semantics.impl.FieldsAndMethodsEvalBase;

public interface Point4{
	static FieldsAndMethods<Point4, Point4> alg = new FieldsAndMethodsEvalBase<Point4>() {};
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	
	static Point4 New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		Map<String, BiFunction<Point4, Object[], Object>> methods = new HashMap<>();
		Map<String, Class<?>[]> signatures = new HashMap<>();
		
		return alg.Interface(Point4.class, fields, methods, signatures);
	}
	
}
