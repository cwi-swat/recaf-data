package nl.cwi.md.semantics.oominheritance.alg;

@SuppressWarnings("unchecked")
public interface ImmutableFields<T, B, M, F> extends BaseMInheritance<T, B, M, F>{
	M Constructor(String name, Class<?> retType, F... formals);  
}
