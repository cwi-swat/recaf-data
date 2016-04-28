package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;

import nl.cwi.md.semantics.alg.JustFields;
import nl.cwi.md.semantics.impl.JustFieldsPrint;

public interface Point2 {
	static JustFields<String, Point2> alg = new JustFieldsPrint<Point2>();
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	
	static String New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		return alg.Interface(Point2.class, fields);
	}
	
}
