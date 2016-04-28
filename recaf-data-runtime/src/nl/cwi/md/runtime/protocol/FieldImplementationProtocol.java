package nl.cwi.md.runtime.protocol;

public interface FieldImplementationProtocol {
	Object fieldAccess(Object target, String name);
	Object fieldWrite(Object target, String name, Object val);
}
