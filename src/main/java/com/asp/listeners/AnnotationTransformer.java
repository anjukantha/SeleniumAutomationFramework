package com.asp.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Implements {@link org.testng.IAnnotationTransformer} to leverage certain
 * functionality like updating the annotations of test methods at runtime.
 * Please make sure to add the listener details in the testng.xml file
 * 
 * @author Anjan S P
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * Helps in setting retry analyzer annotation to all the test methods at run
	 * time.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryFailedTests.class);
	}
}
