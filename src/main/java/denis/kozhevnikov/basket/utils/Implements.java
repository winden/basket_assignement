package denis.kozhevnikov.basket.utils;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

/**
 * Annotation to put on methods inherited from interfaces.
 * Name of the interface is passed as a parameter. I.e.
 *  public class A implements Foo {
 *  	@Override
 *  	@Implements(Foo.class)
 *  	public void doStuff()
 *  }
 * So that if class implements several interfaces and/or extends a concrete class
 * it is easily seen which interface this method came from.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Implements {
	Class<?> value();
}