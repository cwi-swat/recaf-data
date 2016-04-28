package nl.cwi.md.semantics.oo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.cwi.md.semantics.oo.alg.impl.BaseImpl;
import nl.cwi.md.semantics.oo.alg.impl.FieldsImpl;
import nl.cwi.md.semantics.oo.alg.impl.ImmutableFieldsImpl;
import nl.cwi.md.semantics.oo.ast.Formal;

public class OOTest {

	interface Foo {
		String foo();

		public static Foo New() {
			BaseImpl<Foo> alg = new BaseImpl<Foo>();
			return alg.Interface(Foo.class,
					alg.Body(alg.Method("foo", new Formal[] {}, new nl.cwi.md.semantics.alg.Closure() {
						public Object apply(Foo self) {
							{
								return "foo";
							}
						}
					})));
		}
	}

	// @Test
	public void test1() {
		Foo f = Foo.New();
		assertEquals("foo", f.foo());
	}

	interface Point {
		Integer x(Integer... xs);

		Integer y(Integer... ys);

		Integer distance();

		public static Point New() {
			FieldsImpl<Point> alg = new FieldsImpl<Point>();
			return alg.Interface(Point.class,
					alg.Body(alg.Method("distance", new Formal[0], new nl.cwi.md.semantics.alg.Closure() {
						public Object apply(Point self) {
							{
								return (int) Math.sqrt(self.x() * self.x() + self.y() * self.y());
							}
						}
					}), alg.Field(alg.Formal("x", Integer.class)), alg.Field(alg.Formal("y", Integer.class))));
		}
	}
	
	

	@Test
	public void test2() {
		Point point = Point.New();
		point.x(3);
		point.y(4);
		System.out.println(point.distance());
		assertEquals(Integer.valueOf(5), point.distance());

	}

	interface Lit {

		Integer n();

		static Lit New(Object... args) {
			ImmutableFieldsImpl<Lit> alg = new ImmutableFieldsImpl<Lit>() {

			};
			return alg.Interface(Lit.class, alg.Body(alg.Constructor("Lit", alg.Formal("n", Integer.class)),
					alg.Field(alg.Formal("n", Integer.class))), args);
		}

	}

	@Test
	public void test3() {
		Lit l1 = Lit.New(2);
		Lit l2 = Lit.New(2);
		assertEquals(Integer.valueOf(2), l1.n());

	}

}
