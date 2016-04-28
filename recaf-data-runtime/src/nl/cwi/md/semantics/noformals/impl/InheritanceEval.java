package nl.cwi.md.semantics.noformals.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.function.BiFunction;

import nl.cwi.md.Pair;
import nl.cwi.md.semantics.alg.Inheritance;
import nl.cwi.md.semantics.impl.FormalR;
import nl.cwi.md.semantics.impl.MethodsAndFieldsEval3;

public class InheritanceEval<T, P> extends MethodsAndFieldsEval3<T> implements
		Inheritance<T, P, Map<String, BiFunction<T, Object[], Object>>, Pair<String, BiFunction<T, Object[], Object>>, FormalR> {

	@Override
	public T Interface(Class<P> parentClass, P parent, Class<T> iface, Map<String, BiFunction<T, Object[], Object>> body) {
		T me = super.Interface(iface, body);
		if (parent == null)
			return me;
		else{
			return (T) Proxy.newProxyInstance(iface.getClassLoader(), new Class<?>[]{iface, parentClass},
					new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							try{
								return me.getClass().getMethod(method.getName(), method.getParameterTypes())
										.invoke(me, args);
							}catch(InvocationTargetException e){
								return parent.getClass().getMethod(method.getName(), method.getParameterTypes())
										.invoke(parent, args);
							}
								
						}
					});
		}
			
	}

	

}
