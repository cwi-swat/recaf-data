package nl.cwi.md.semantics.oominheritance.alg;
public interface Fields<T, B, M, F> extends BaseMInheritance<T, B, M, F>{
	M Field(String name, Class<?> retType, F[] formals);  
}
