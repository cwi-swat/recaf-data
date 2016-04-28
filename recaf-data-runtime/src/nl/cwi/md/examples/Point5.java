package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.semantics.alg.FieldsAndMethods;
import nl.cwi.md.semantics.impl.FieldsAndMethodsEvalBase;

public interface Point5{
	static FieldsAndMethods<Point5, Point5> alg = new FieldsAndMethodsEvalBase<Point5>() {
		@Override
		public Object handleFieldAccess(Object target, String name){
			Object val = super.handleFieldAccess(target, name);
			System.out.println("INFO: Accessing field " + name + " -> " + val);
			return val;
		}
		
	};
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	
	static Point5 New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		Map<String, BiFunction<Point5, Object[], Object>> methods = new HashMap<>();
		Map<String, Class<?>[]> signatures = new HashMap<>();
		
		return alg.Interface(Point5.class, fields, methods, signatures);
	}
	
}
