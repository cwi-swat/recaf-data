package nl.cwi.md.semantics.oominheritance.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import nl.cwi.md.Cell;
import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;
import nl.cwi.md.semantics.oominheritance.impl.BaseMInheritanceImpl;

public class OOMInheritanceTest {

	interface A {

		String foo();

		String bar();

		String thisFoo();

		static A New() {
			Cell<A> self = new Cell<>();
			A proxy = A.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static A New(Cell<? extends A> self) {
			BaseMInheritanceImpl<A> alg = new BaseMInheritanceImpl<A>();
			Class<?>[] parentIfaces = new Class<?>[0];
			Object[] parents = new Object[parentIfaces.length];
			return alg.Interface(A.class, parentIfaces, self,
					alg.Body(alg.Method("foo", String.class, new Formal[0], new Closure() {
				public Object apply(A self, Map<Class<?>, Object> parents) {
					return "I am A";
				}
			}), alg.Method("bar", String.class, new Formal[0], new Closure() {
				public Object apply(A self, Map<Class<?>, Object> parents) {
					return "bar";
				}
			}), alg.Method("thisFoo", String.class, new Formal[0], new Closure() {
				public Object apply(A self, Map<Class<?>, Object> parents) {
					return self.foo();
				}
			})));
		}
	}

	interface B extends A {

		String foo();

		String b();

		static B New() {
			Cell<B> self = new Cell<>();
			B proxy = B.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static B New(Cell<? extends B> self) {
			BaseMInheritanceImpl<B> alg = new BaseMInheritanceImpl<B>();
			Class<?>[] parentIfaces = new Class<?>[] { A.class };
			B current = alg.Interface(B.class, parentIfaces, self, alg.Body(alg.Method("foo", String.class, new Formal[0], new Closure() {
				public Object apply(B self, Map<Class<?>, Object> parents) {
					return "I am B";
				}
			}), alg.Method("b", String.class, new Formal[0], new Closure() {
				public Object apply(B self, Map<Class<?>, Object> parents) {
					return "b";
				}
			})));
			return current;
		}
	}

	interface C extends A {

		String foo();

		String c();

		static C New() {
			Cell<C> self = new Cell<>();
			C proxy = C.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static C New(Cell<? extends C> self) {
			BaseMInheritanceImpl<C> alg = new BaseMInheritanceImpl<C>();
			Class<?>[] parentIfaces = new Class<?>[] { A.class };
			C current = alg.Interface(C.class, parentIfaces, self, alg.Body(alg.Method("foo", String.class, new Formal[0], new Closure() {
				public Object apply(C self, Map<Class<?>, Object> parents) {
					return "I am C";
				}
			}), alg.Method("c", String.class, new Formal[0], new Closure() {
				public Object apply(C self, Map<Class<?>, Object> parents) {
					return "c";
				}
			})));
			return current;
		}
	}

	interface D extends B, C {

		String foo();

		String parentFoo();

		static D New() {
			Cell<D> self = new Cell<>();
			D proxy = D.New(self);
			self.setValue(proxy);
			return proxy;
		}

		static D New(Cell<? extends D> self) {
			BaseMInheritanceImpl<D> alg = new BaseMInheritanceImpl<D>();
			Class<?>[] parentIfaces = new Class<?>[] { B.class, C.class };
			D current = alg.Interface(D.class, parentIfaces, self,
					alg.Body(alg.Method("foo", String.class, new Formal[0], new Closure() {
						public Object apply(D self, Map<Class<?>, Object> parents) {
							return "I am D";
						}
					}), alg.Method("parentFoo", String.class, new Formal[0], new Closure() {
						public Object apply(D self, Map<Class<?>, Object> parents) {
							return ((B) parents.get(B.class)).foo();
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
