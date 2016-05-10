package generated;

import nl.cwi.md.semantics.oominheritance.impl.MemoizedImpl;

@Managed
public interface Fibonacci {
	@Algebra
	static MemoizedImpl algebra = new MemoizedImpl();

	@MemoizedMethod
	default Integer memoFib(Integer x) {
		if (x < 2) {
			return x;
		} else {
			Integer x1 = this.memoFib(x - 1);
			Integer x2 = this.memoFib(x - 2);
			return x1 + x2;
		}
	}

	@Method
	default Integer fib(Integer x) {
		if (x < 2) {
			return x;
		} else {
			Integer x1 = this.fib(x - 1);
			Integer x2 = this.fib(x - 2);
			return x1 + x2;
		}
	}
	
}
