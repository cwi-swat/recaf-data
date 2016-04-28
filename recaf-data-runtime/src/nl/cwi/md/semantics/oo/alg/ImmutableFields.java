package nl.cwi.md.semantics.oo.alg;

public interface ImmutableFields<T, B, M, F> extends Base<T, B, M, F>{
	M Constructor(String name, F... formals);  
}
