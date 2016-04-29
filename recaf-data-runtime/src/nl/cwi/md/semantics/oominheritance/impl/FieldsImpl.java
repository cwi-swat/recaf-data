package nl.cwi.md.semantics.oominheritance.impl;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.Fields;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Field;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public class FieldsImpl<T> extends BaseMInheritanceImpl<T> implements Fields<T, Body<T>, Member<T>, Formal> {

	@Override
	public Member<T> Field(String name, Class<?> type, Formal... formals) {
		return new Field<T>(new Formal(name, type));
	}
}
