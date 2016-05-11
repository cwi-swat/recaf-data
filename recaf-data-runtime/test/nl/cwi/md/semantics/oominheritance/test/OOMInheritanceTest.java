package nl.cwi.md.semantics.oominheritance.test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oominheritance.impl.BaseMInheritanceImpl;

public class OOMInheritanceTest {

	interface A {

		String foo();

		String bar();

		String thisFoo();

		static A New(Object... initArgs) {
			return A.New(null, initArgs);
		}

		static A New(A self, Object... initArgs) {
			BaseMInheritanceImpl<A> alg = new BaseMInheritanceImpl<A>();
			Class<?>[] parentIfaces = new Class<?>[0];
			Object[] parents = new Object[parentIfaces.length];
			return alg.Interface(A.class, parentIfaces, self,
					alg.Body(alg.Method(alg.Formal("foo", String.class), new Closure() {
						public Object apply(A self, Map<Class<?>, Object> parents) {
							return "I am A";
						}
					}), alg.Method(alg.Formal("bar", String.class), new Closure() {
						public Object apply(A self) {
							return "bar";
						}
					}), alg.Method(alg.Formal("thisFoo", String.class), new Closure() {
						public Object apply(A self) {
							return self.foo();
						}
					})));
		}
	}

	interface B extends A {

		String foo();

		String b();

		static B New(Object... initArgs) {
			return B.New(null, initArgs);
		}

		static B New(B self, Object... initArgs) {
			BaseMInheritanceImpl<B> alg = new BaseMInheritanceImpl<B>();
			Class<?>[] parentIfaces = new Class<?>[] { A.class };
			B current = alg.Interface(B.class, parentIfaces, self,
					alg.Body(alg.Method(alg.Formal("foo", String.class), new Closure() {
						public Object apply(B self, A super$0) {
							return "I am B";
						}
					}), alg.Method(alg.Formal("b", String.class), new Closure() {
						public Object apply(B self, A super$0) {
							return "b";
						}
					})));
			return current;
		}
	}

	interface C extends A {

		String foo();

		String c();

		static C New(Object... initArgs) {
			return C.New(null, initArgs);
		}

		static C New(C self, Object... initArgs) {
			BaseMInheritanceImpl<C> alg = new BaseMInheritanceImpl<C>();
			Class<?>[] parentIfaces = new Class<?>[] { A.class };
			C current = alg.Interface(C.class, parentIfaces, self,
					alg.Body(alg.Method(alg.Formal("foo", String.class), new Closure() {
						public Object apply(C self, A super$0) {
							return "I am C";
						}
					}), alg.Method(alg.Formal("c", String.class), new Closure() {
						public Object apply(C self, A super$0) {
							return "c";
						}
					})));
			return current;
		}
	}

	interface D extends B, C {

		String foo();

		String parentFoo();

		static D New(Object... initArgs) {
			return D.New(null, initArgs);
		}

		static D New(D self, Object... initArgs) {
			BaseMInheritanceImpl<D> alg = new BaseMInheritanceImpl<D>();
			Class<?>[] parentIfaces = new Class<?>[] { B.class, C.class };
			D current = alg.Interface(D.class, parentIfaces, self,
					alg.Body(alg.Method(alg.Formal("foo", String.class), new Closure() {
						public Object apply(D self, B super$0, C super$1) {
							return "I am D";
						}
					}), alg.Method(alg.Formal("parentFoo", String.class), new Closure() {
						public Object apply(D self, B super$0, C super$1) {
							return super$0.foo();
						}
					})));
			return current;
		}
	}

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
	public void test3() {
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
