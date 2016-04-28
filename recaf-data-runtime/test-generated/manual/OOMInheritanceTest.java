package manual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import generated.D;

public class OOMInheritanceTest {

	interface AJ {
		default String foo() {
			return "I am A";
		}

		default String bar() {
			return "bar";
		}

		default String thisFoo() {
			return this.foo();
		}
	}

	interface BJ extends AJ {
		default String foo() {
			return "I am B";
		}

		default String b() {
			return "b";
		}

	}

	interface CJ extends AJ {
		default String foo() {
			return "I am C";
		}

		default String c() {
			return "c";
		}
	}

	interface DJ extends BJ, CJ {
		default String foo() {
			return "I am D";
		}

		default String parentFoo() {
			return BJ.super.foo();
		}

	}

	@Test
	public void test() {
		D d = D.New();
		System.out.println(d.foo());
		System.out.println(d.parentFoo());
		System.out.println(d.b());
		System.out.println(d.c());
		System.out.println(d.bar());
		System.out.println(d.thisFoo());
		System.out.println("----");
		DJ dj = new DJ() {
		};
		System.out.println(dj.foo());
		System.out.println(dj.bar());
		System.out.println(dj.parentFoo());
		System.out.println(dj.thisFoo());
		System.out.println(dj.b());
		System.out.println(dj.c());

		assertEquals(dj.foo(), d.foo());
		assertEquals(dj.bar(), d.bar());
		assertEquals(dj.thisFoo(), d.thisFoo());
		assertEquals(dj.parentFoo(), d.parentFoo());
		assertEquals(dj.b(), d.b());
		assertEquals(dj.c(), d.c());

	}

}
