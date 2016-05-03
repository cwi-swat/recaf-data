package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public interface FieldsAST<T> extends BaseMInheritanceAST<T>, Fields<T, Body<T>, Member<T>, Formal> {
	Member<T> Field(String name, Class<?> retType, Formal... formals);
}
