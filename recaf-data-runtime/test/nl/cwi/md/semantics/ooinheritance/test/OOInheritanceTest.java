package nl.cwi.md.semantics.ooinheritance.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.ooinheritance.impl.BaseInheritanceImpl;

public class OOInheritanceTest {

	interface A {

		String foo();

		String bar();

		String name();

		String thisFoo();

		static A New() {
			Cell<A> self = new Cell<>();
			A proxy = A.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static A New(Cell<? extends A> self) {
			BaseInheritanceImpl<A> alg = new BaseInheritanceImpl<A>();
			return alg.Interface(A.class, self, new Object(), alg.Body(alg.Method("foo", new Formal[0], new Closure() {
				public Object apply(A self, Object parent) {
					return "I am A";
				}
			}), alg.Method("bar", new Formal[0], new Closure() {
				public Object apply(A self, Object parent) {
					return "bar";
				}
			}), alg.Method("thisFoo", new Formal[0], new Closure() {
				public Object apply(A self, Object parent) {
					return self.foo();
				}
			})));
		}
	}

	interface B extends A {

		String foo();

		String parentFoo();

		static B New() {
			Cell<B> self = new Cell<>();
			B proxy = B.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static B New(Cell<? extends B> self) {
			BaseInheritanceImpl<B> alg = new BaseInheritanceImpl<B>();
			A parent = A.New(self);
			B current = alg.Interface(B.class, self, parent, alg.Body(alg.Method("foo", new Formal[0], new Closure() {
				public Object apply(B self, A parent) {
					return "I am B";
				}
			}), alg.Method("parentFoo", new Formal[0], new Closure() {
				public Object apply(B self, A parent) {
					return parent.foo();
				}
			})));
			return current;
		}
	}
	
	interface C extends B{
		static C New() {
			Cell<C> self = new Cell<>();
			C proxy = C.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static C New(Cell<? extends C> self) {
			BaseInheritanceImpl<C> alg = new BaseInheritanceImpl<C>();
			B parent = B.New(self);
			C current = alg.Interface(C.class, self, parent, alg.Body());
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

		default String parentFoo() {
			return AJ.super.foo();
		}
	}
	
	interface CJ extends BJ{
		
	}

	@Test
	public void test3() {
		A a = A.New();
		B b = B.New();
		C c = C.New();
		System.out.println(c.bar());
		System.out.println(a.foo());
		System.out.println(b.foo());
		System.out.println(a.bar());
		System.out.println(b.bar());
		System.out.println(b.parentFoo());
		System.out.println(b.thisFoo());
		System.out.println(c.bar());
		System.out.println(c.parentFoo());
		System.out.println(c.thisFoo());
		System.out.println(c.foo());
		
		System.out.println("----");
		AJ aj = new AJ() {
		};
		BJ bj = new BJ() {
		};
		BJ cj = new BJ() {
		};
		System.out.println(aj.foo());
		System.out.println(bj.foo());
		System.out.println(aj.bar());
		System.out.println(bj.bar());
		System.out.println(bj.parentFoo());
		System.out.println(bj.thisFoo());
		System.out.println(cj.bar());
		System.out.println(cj.parentFoo());
		System.out.println(cj.thisFoo());
		System.out.println(cj.foo());

		assertEquals(aj.foo(), a.foo());
		assertEquals(aj.bar(), a.bar());
		assertEquals(aj.thisFoo(), a.thisFoo());
		assertEquals(bj.foo(), b.foo());
		assertEquals(bj.bar(), b.bar());
		assertEquals(bj.parentFoo(), b.parentFoo());
		assertEquals(cj.bar(), c.bar());
		assertEquals(cj.parentFoo(), c.parentFoo());
		assertEquals(cj.thisFoo(), c.thisFoo());
		assertEquals(cj.foo(), c.foo());
		
	}

}
