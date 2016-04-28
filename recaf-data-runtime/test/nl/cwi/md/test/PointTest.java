package nl.cwi.md.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.cwi.md.examples.A;
import nl.cwi.md.examples.B;
import nl.cwi.md.examples.Lit;
import nl.cwi.md.examples.Point10;
import nl.cwi.md.examples.Point12;
import nl.cwi.md.examples.Point13;
import nl.cwi.md.examples.Point14;
import nl.cwi.md.examples.Point9;

public class PointTest {

	// @Test
	// public void test1() {
	// Point point = Point.New();
	// point.x(3);
	// System.out.println(point.x());
	// assertEquals(Integer.valueOf(3), point.x());
	//
	// }
	//
	// @Test
	// public void test2() {
	// String point = Point2.New();
	// System.out.println(point);
	// assertEquals("final class nl.cwi.md.examples.Point2{\n\tfield
	// java.lang.Integer x;\n\tfield java.lang.Integer y;\n}", point);
	// }
	//
	// @Test
	// public void test3() {
	// Point3 point = Point3.New();
	// point.x(3);
	// point.y(4);
	// System.out.println(point.distance());
	// assertEquals(Integer.valueOf(5), point.distance());
	//
	// }
	//
	// @Test
	// public void test4() {
	// Point4 point = Point4.New();
	// point.x(3);
	// System.out.println(point.x());
	// assertEquals(Integer.valueOf(3), point.x());
	//
	// }
	//
	// @Test
	// public void test5() {
	// Point5 point = Point5.New();
	// point.x(3);
	// System.out.println(point.x());
	// assertEquals(Integer.valueOf(3), point.x());
	//
	// }
	//
	// @Test
	// public void test6() {
	// Point6 point = Point6.New();
	// point.x(3);
	// point.y(4);
	// System.out.println(point.distance());
	// assertEquals(Integer.valueOf(5), point.distance());
	// }
	//
	// @Test
	// public void test7() {
	// Point7 point = Point7.New();
	// System.out.println(point.getX());
	// System.out.println(point.getY());
	// assertEquals(Integer.valueOf(3), point.getX());
	//
	// }
	//
	// @Test
	// public void test8() {
	// Point8 point = Point8.New();
	// System.out.println(point.getX());
	// System.out.println(point.getY());
	// assertEquals(Integer.valueOf(3), point.getX());
	//
	// }
	//
	@Test
	public void test9() {
		Point9 point = Point9.New();
		System.out.println(point.x(3));
		System.out.println(point.x());
		assertEquals(Integer.valueOf(3), point.x());

	}

	@Test
	public void test10() {
		Point10 point = Point10.New();
		System.out.println(point.x(3));
		System.out.println(point.x());
		assertEquals(Integer.valueOf(3), point.x());

	}

	@Test
	public void test12() {
		Point12 point = Point12.New();
		System.out.println(point.getX());
		System.out.println(point.getY());
		assertEquals(Integer.valueOf(3), point.getX());

	}

	@Test
	public void test13() {
		Point13 point = Point13.New();
		System.out.println(point.getX());
		System.out.println(point.getY());
		assertEquals(Integer.valueOf(3), point.getX());

	}
	
	@Test
	public void test14() {
		Point14 point = Point14.New();
		point.x(3);
		point.y(4);
		System.out.println(point.distance());
		assertEquals(Integer.valueOf(5), point.distance());

	}
	
	@Test
	public void test15() {
		A a = A.New();
		B b = B.New();
		System.out.println(a.foo());
		System.out.println(b.foo());
		System.out.println(a.bar());
		System.out.println(b.bar());
		

	}
	
	@Test
	public void test16() {
		Lit l1 = Lit.New(3);
		Lit l2 = Lit.New(3);
		System.out.println(l1.n());
		System.out.println(l2.n());
		System.out.println(l2.equals(l1));
		

	}
}
