package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Map;

public class Body<T> {
	private Member<T>[] members;

	public Member<T>[] getMembers() {
		return members;
	}

	public Body(Member<T>[] members) {
		super();
		this.members = members;
	}
	
	public Object invoke(String name, T self, Map<Class<?>, Object> parents, Object[] args){
		for (Member<T>  m : members){
			if (m.name().equals(name))
				return m.handle(self, parents, args);
		}
		throw new UnsupportedOperationException();
	}
	
}
