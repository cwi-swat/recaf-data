package nl.cwi.md.semantics.oo.alg.impl;

import nl.cwi.md.semantics.oo.alg.Fields;
import nl.cwi.md.semantics.oo.ast.Body;
import nl.cwi.md.semantics.oo.ast.Field;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oo.ast.Member;

public class FieldsImpl<T> extends BaseImpl<T> implements Fields<T, Body<T>, Member<T>, Formal> {

	@Override
	public Member<T> Field(Formal formal) {
		return new Field<T>(formal);
	}

}
