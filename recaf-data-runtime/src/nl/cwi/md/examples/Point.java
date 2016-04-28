package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;

import nl.cwi.md.semantics.alg.JustFields;
import nl.cwi.md.semantics.impl.JustFieldsEval;

public interface Point {
	static JustFields<Point, Point> alg = new JustFieldsEval<Point>();
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	
	static Point New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		return alg.Interface(Point.class, fields);
	}
	
}
