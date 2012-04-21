package denis.kozhevnikov.basket.utils;

/**
 * Annotation used in Junits to explicitly say what class is under testing.
 */
public @interface ClassUnderTest {
	Class<?> value();
}