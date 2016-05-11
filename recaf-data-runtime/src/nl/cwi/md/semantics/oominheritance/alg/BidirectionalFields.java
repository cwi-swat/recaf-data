package nl.cwi.md.semantics.oominheritance.alg;

public interface BidirectionalFields<T, B, M, F> extends BaseMInheritance<T, B, M, F>{
	M Container(F head, F... formals);
}
