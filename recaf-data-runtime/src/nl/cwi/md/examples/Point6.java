package nl.cwi.md.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.semantics.alg.FieldsAndMethods;
import nl.cwi.md.semantics.impl.FieldsAndMethodsEvalBase;

public interface Point6{
	static FieldsAndMethods<Point6, Point6> alg = new FieldsAndMethodsEvalBase<Point6>() {
		@Override
		public Object handleFieldAccess(Object target, String name){
			Object val = super.handleFieldAccess(target, name);
			System.out.println("INFO: Accessing field " + name + " -> " + val);
			return val;
		}
		
		@Override
		public Object handleFieldWrite(Object target, String name, Object val){
			Object v = super.handleFieldWrite(target, name, val);
			System.out.println("INFO: Writing field " + name + " with value " + val);
			return v;
		}
		
		@Override
		public Object handleMethodCall(String name, BiFunction<Point6, Object[], Object>  fun, Point6 target, Object[] args){
			System.out.println("INFO: Before calling method " + name);
			Object val = super.handleMethodCall(name, fun, target, args);
			System.out.println("INFO: After calling method " + name);
			return val;
		}
		
	};
	
	Integer x(Integer... xs);
	Integer y(Integer... xs);
	Integer distance();
	
	static Point6 New(){
		Map<String, Class<?>> fields = new HashMap<>();
		fields.put("x", Integer.class);
		fields.put("y", Integer.class);
		Map<String, BiFunction<Point6, Object[], Object>> methods = new HashMap<>();
		Map<String, Class<?>[]> signatures = new HashMap<>();
		methods.put("distance", 
				(self, args) -> {
					return Integer.valueOf((int) Math.sqrt(self.x()*self.x() + self.y()*self.y()));
				});
		return alg.Interface(Point6.class, fields, methods, signatures);
	}
	
	
}
