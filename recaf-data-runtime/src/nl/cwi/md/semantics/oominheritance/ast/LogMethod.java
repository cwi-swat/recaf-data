package nl.cwi.md.semantics.oominheritance.ast;

import java.util.Arrays;

import nl.cwi.md.semantics.alg.Closure;
import nl.cwi.md.semantics.oo.ast.Formal;

public class LogMethod<T> extends MMethod<T> {

	public LogMethod(String name, Formal[] formals, Closure body) {
		super(name, formals, body);
	}

	@Override
	public Object handle(T self, Object[] parents, Object[] args) {
		System.out.println("[INFO] Calling method " + this.name() + " with arguments : [" + String.join(", ", 
				Arrays.asList(args).stream().map(x -> { return x.toString();}).toArray(l -> { return new String[l];})) + "]");
		return super.handle(self, parents, args);
	}

}
