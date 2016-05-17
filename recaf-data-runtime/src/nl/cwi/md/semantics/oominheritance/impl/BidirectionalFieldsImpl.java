package nl.cwi.md.semantics.oominheritance.impl;

import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.alg.BidirectionalFields;
import nl.cwi.md.semantics.oominheritance.ast.Body;
import nl.cwi.md.semantics.oominheritance.ast.Container;
import nl.cwi.md.semantics.oominheritance.ast.Member;

public class BidirectionalFieldsImpl<T> extends FieldsImpl<T> implements BidirectionalFields<T, Body<T>, Member<T>, Formal> {
	
	@Override
	public Member<T> Container(String inverseField, Formal head, Formal... formals) {
		return new Container<T>(inverseField, head, this);
	}
}
