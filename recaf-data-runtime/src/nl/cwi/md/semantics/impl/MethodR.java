package nl.cwi.md.semantics.impl;

import java.util.List;
import java.util.function.BiFunction;

public class MethodR<T, F> {
	private String name;
	private BiFunction<T, Object[], Object> body;
	private List<F> formals;
	
	public MethodR(String name, BiFunction<T, Object[], Object> body, List<F> formals) {
		super();
		this.name = name;
		this.body = body;
		this.formals = formals;
	}


	public String getName() {
		return name;
	}

	public BiFunction<T, Object[], Object> getBody() {
		return body;
	}

	public List<F> getFormals() {
		return formals;
	}

	
	public Object apply(T tgt, Object[] args) {
		return body.apply(tgt, args);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBody(BiFunction<T, Object[], Object> body) {
		this.body = body;
	}

	public void setFormals(List<F> formals) {
		this.formals = formals;
	}

}
