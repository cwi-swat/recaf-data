package manual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import generated.D;
import nl.cwi.md.semantics.oominheritance.impl.LogMInheritanceImpl;

public class LogMInheritanceTest {

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
		D d = D.New(new LogMInheritanceImpl<>());
		DJ dj = new DJ() {};
		
		assertEquals(dj.foo(), d.foo());
		assertEquals(dj.bar(), d.bar());
		assertEquals(dj.thisFoo(), d.thisFoo());
		assertEquals(dj.parentFoo(), d.parentFoo());
		assertEquals(dj.b(), d.b());
		assertEquals(dj.c(), d.c());

	}

}
