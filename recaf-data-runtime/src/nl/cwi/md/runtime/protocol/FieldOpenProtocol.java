package nl.cwi.md.runtime.protocol;

public interface FieldOpenProtocol {
	Object handleFieldAccess(Object target, String name);
	Object handleFieldWrite(Object target, String name, Object val);
}
