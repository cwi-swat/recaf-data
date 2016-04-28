package nl.cwi.md;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RecafUtils {
	public static <T> Object[] reflectiveParentsNew(Class<?>[] ifaces, T self, Object... initArgs){
		Object[] r =new Object[ifaces.length];
		for (int i = 0; i < ifaces.length; i++) {
			Method m = Arrays.asList(ifaces[i].getDeclaredMethods()).stream().filter(me -> {
				return me.getName().equals("New") && me.getParameterTypes().length == 2;
			}).findAny().get();
			m.setAccessible(true);
			try {
				r[i] = m.invoke(null, new Object[] { self, initArgs });
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Wrong New invocation: " + m.toString());
			}
		}
		return r;
	}
}
