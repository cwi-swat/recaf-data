package manual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import generated.Fibonacci;
import generated.Point;

public class MemoizationTest {

	@Test
	public void test1() {
		Fibonacci point = Fibonacci.New();
		int result1 = point.memoFib(36);
		System.out.println(result1);
		int result2 = point.fib(36);
		System.out.println(result2);
		assertEquals(14930352, result1);
		assertEquals(14930352, result2);

	}

}
