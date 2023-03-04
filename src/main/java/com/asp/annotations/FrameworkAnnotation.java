package com.asp.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.asp.enums.CategoryType;

/**
 * Framework Annotation is user built annotation which is annotated on top of
 * test methods to log the author details and category details to the extent
 * report.
 * 
 * Runtime retention value indicate that this annotation will be available at
 * run time for reflection operations.
 * 
 * @author Anjan S P
 * @see com.asp.enums.CategoryType
 */

@Retention(RUNTIME)
@Target(METHOD)
public @interface FrameworkAnnotation {
	/**
	 * Store the authors who created the tests in String[] Mandatory to enter
	 * at-least a value
	 * 
	 * @author Anjan S P
	 */
	public String[] author() default { "Default Author" };

	/**
	 * Stores the category in form of Enum Array. Include the possible values in
	 * {@link com.asp.enums.CategoryType}
	 * 
	 * @author Anjan S P
	 */
	public CategoryType[] category() default { CategoryType.SMOKE };

}
