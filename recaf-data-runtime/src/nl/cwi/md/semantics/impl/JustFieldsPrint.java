package nl.cwi.md.semantics.impl;

import java.util.Map;

import nl.cwi.md.semantics.alg.JustFields;

public class JustFieldsPrint<T> implements JustFields<String, T> {

	@Override
	public String Interface(Class<T> iface, Map<String, Class<?>> fields) {
		String[] fieldsStr = fields.keySet().stream().map(name -> "\tfield " + fields.get(name).getName() + " " + name + ";\n")
				.toArray(l -> new String[fields.size()]);
		return "final class " + iface.getName() + "{\n" + String.join("", fieldsStr) + "}";

	}

}
