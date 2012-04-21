package denis.kozhevnikov.basket.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to put on factory methods (primarily for static factory methods instead of constructors). 
 * So they visually distinguished easily.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface FactoryMethod {}