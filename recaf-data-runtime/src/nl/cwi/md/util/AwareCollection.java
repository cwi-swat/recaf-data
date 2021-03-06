package nl.cwi.md.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;

public class AwareCollection{

	public static <C, T> Collection<T> buildAwareCollection(C self, Collection<T> delegated) {
		return (Collection<T>) Proxy.newProxyInstance(delegated.getClass().getClassLoader(), new Class<?>[]{ Collection.class}, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if (method.getName() == "add"){
							Object arg = args[0];
							// Naive heuristics. Get me the first method that returns something of the type that I want...
							// We can add more heuristics, i.e., that this method should have varargs
							// but maybe it's better to preserve this kind of semantic info with annotations (not removing them
							// when virtualizing)
							
							Method m = findMethodInClassThatReturnsType(arg.getClass(), self.getClass());
							Object arr = Array.newInstance(self.getClass(), 1);
							Array.set(arr, 0, self);
							m.invoke(arg, new Object[]{ arr });
						}
						return method.invoke(delegated, args);
					}
				});
	
	}
	
	private static Method findMethodInClassThatReturnsType(Class<?> originalClass, Class<?> retType){
		return Arrays.asList(originalClass.getDeclaredMethods())
				.stream().filter(m -> { return m.getReturnType().isAssignableFrom(retType);}).findFirst().get();
			
	}
}
