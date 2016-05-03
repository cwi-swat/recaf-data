package nl.cwi.md.semantics.oominheritance.alg;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Member;

@SuppressWarnings("unchecked")
public interface ImmutableFieldsAST<T> extends FieldsAST<T>, ImmutableFields<T, Body<T>, Member<T>, Formal> {
	Member<T> Constructor(String name, Class<?> retType, Formal... formals);
}
