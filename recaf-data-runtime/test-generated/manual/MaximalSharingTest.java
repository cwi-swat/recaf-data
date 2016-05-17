package manual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import generated.Lit;
import nl.cwi.md.semantics.oominheritance.ast.MSImmutableInterface;
import nl.cwi.md.semantics.oominheritance.impl.MSImmutableFieldsImpl;


public class MaximalSharingTest {

	@Test
	public void test1() {
		MSImmutableFieldsImpl<Lit> algebra = new MSImmutableFieldsImpl<>();
		Lit l1 = Lit.New(algebra, 2);
		Lit l2 = Lit.New(algebra, 2);
		for (List<Object> key : MSImmutableInterface.msHeap.keySet()){
			System.out.println(key);
		}
		System.out.println(l1.n());
		System.out.println(l2.n());
		assertEquals(Integer.valueOf(2), l1.n());
		assertEquals(Integer.valueOf(2), l2.n());
		assertTrue(l1 == l2);

	}

}
