package com.bjculk.automod.annotations.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker annotation for generic string configuration fields
 * @author Benjamin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigField {
	/**
	 * The category for this config option
	 */
	String category() default "general";
	/**
	 * The key for the config option.
	 * <br />
	 * NOTE: Named value instead of key to allow for short-form annotations
	 * 
	 */
	String value();
	String defaultValue() default "";
	String comment() default "";
}
