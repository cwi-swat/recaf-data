package manual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import generated.Lit;


public class ImmutableTest {

	@Test
	public void test1() {
		Lit l1 = Lit.New(2);
		Lit l2 = Lit.New(2);
		System.out.println(l1.n());
		System.out.println(l2.n());
		assertEquals(Integer.valueOf(2), l1.n());
		assertEquals(Integer.valueOf(2), l2.n());
		//System.out.println(l2.equals(l1));

	}

}
