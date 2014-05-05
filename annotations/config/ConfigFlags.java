package com.bjculk.automod.annotations.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Container interface for field type specifiers
 * @author Benjamin
 *
 */
public @interface ConfigFlags {
	/**
	 * Marker to indicate a field as a single boolean flag
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Flag {
		boolean value() default false;
	}
	/**
	 * Marker to indicate a fields as an array of boolean flags
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Flags {
		boolean[] value() default {false};
	}
	/**
	 * Marker to indicate a field as a single floating point value
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Float {
		double value() default 0.0;
	}
	/**
	 * Marker to indicate a fields as an array of floating point values
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Floats {
		double[] value() default {0.0};
	}
	/**
	 * Marker to indicate a field as a single integral value
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Integer {
		int value() default 0;
	}
	/**
	 * Marker to indicate a fields as an array of integral values
	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Integers {
		int[] value() default {0};
	}
	
	/**
	 * Marker to indicate a fields as an array of strings
	 * 	 * @author Benjamin
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Strings {
		String[] value() default {""};
	}
	
}
