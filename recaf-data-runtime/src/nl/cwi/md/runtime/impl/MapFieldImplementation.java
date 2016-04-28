package nl.cwi.md.runtime.impl;

import java.util.Map;

import nl.cwi.md.runtime.protocol.FieldImplementationProtocol;

public interface MapFieldImplementation extends FieldImplementationProtocol {
	Map<String, Object> fieldValues(Object target);
	
	default Object fieldAccess(Object target, String name){
		return fieldValues(target).get(name);
		
	}
	default Object fieldWrite(Object target, String name, Object val){
		Object old = fieldValues(target).containsKey(name)?fieldValues(target).get(name):null; 
		fieldValues(target).put(name, val);
		return old;
	}
	
}
