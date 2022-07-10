package com.asp.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

/**
 * 
 * This class implements the WebDriverEventListener, which is included under
 * events. The purpose of implementing this interface is to override all the
 * methods and define certain useful Log statements which would be
 * displayed/logged as the application under test is being run.
 * 
 * Do not call any of these methods, instead these methods will be invoked
 * automatically as an when the action done (click, findBy etc).
 * 
 * @author Anjan S P
 *
 */
public class WebEventListener implements WebDriverListener {

	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		System.out.println("Trying to find Element By : " + locator.toString());
	}

	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) {
		System.out.println("Found Element By : " + locator.toString());
	}

	@Override
	public void beforeGet(WebDriver driver, String url) {
		System.out.println("Trying to get the URL: " + url);
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		System.out.println("Navigated to URL: " + url);
	}

	@Override
	public void beforeGetTitle(WebDriver driver) {
		System.out.println("Trying to get the Current Window Title");
	}

	@Override
	public void afterGetTitle(WebDriver driver, String result) {
		System.out.println("Got the title: " + result);
	}

	@Override
	public void beforeFindElements(WebDriver driver, By locator) {
		System.out.println("Trying to find Elements By : " + locator.toString());
	}

	@Override
	public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
		System.out.println("Found " + result.size() + " Elements By : " + locator.toString());
	}

	@Override
	public void beforeGetWindowHandles(WebDriver driver) {
		System.out.println("Trying to get the number of windows currently opened");
	}

	@Override
	public void afterGetWindowHandles(WebDriver driver, Set<String> result) {
		System.out.println("Found " + result.size() + " windows opened");
	}

	@Override
	public void beforeClick(WebElement element) {
		System.out.println("Trying to click element: " + element.toString());
	}

	@Override
	public void afterClick(WebElement element) {
		System.out.println("Clicked element: " + element.toString());
	}

	@Override
	public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		System.out.println(
				"Trying to send the keys " + Arrays.toString(keysToSend) + " to element: " + element.toString());
	}

	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		System.out.println(
				"Successfully sent the keys " + Arrays.toString(keysToSend) + " to element: " + element.toString());
	}
}