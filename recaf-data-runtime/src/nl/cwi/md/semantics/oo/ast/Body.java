package nl.cwi.md.semantics.oo.ast;

public class Body<T> {
	private Member<T>[] members;

	public Member<T>[] getMembers() {
		return members;
	}

	public Body(Member<T>[] members) {
		super();
		this.members = members;
	}
	
	public Object invoke(String name, T self, Object[] args){
		for (Member<T>  m : members){
			if (m.name().equals(name))
				return m.handle(self, args);
		}
		throw new RuntimeException();
	}
	
}
