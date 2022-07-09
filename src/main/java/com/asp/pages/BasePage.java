package com.asp.pages;

import static com.asp.enums.LogType.PASS;
import static com.asp.reports.Logger.log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.asp.driver.DriverManager;
import com.asp.enums.WaitStrategy;
import com.asp.factories.ExplicitWaitFactory;

public class BasePage {

	/**
	 * Locates element by given wait strategy, performs the clicking operation on
	 * webelement and writes the pass even to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by           By Locator of the webelement
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.asp.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected void click(By by, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy,
				DriverManager.getDriver().findElement(by), null);
		element.click();
		log(PASS, elementname + " is clicked");
	}

	/**
	 * Locates element by given wait strategy, sends the value to located webelement
	 * and writes the pass even to the extent report.
	 * 
	 * @author Anjan S P
	 * @param by           By Locator of the webelement
	 * @param value        value to be send the text box
	 * @param waitstrategy Strategy to find webelement. Known strategies
	 *                     {@link com.asp.enums.WaitStrategy}
	 * @param elementname  Name of the element that needs to be logged in the
	 *                     report.
	 */
	protected void sendKeys(By by, String value, WaitStrategy waitstrategy, String elementname) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy,
				DriverManager.getDriver().findElement(by), value);
		element.sendKeys(value);
		log(PASS, value + " is entered successfully in " + elementname);
	}

	/**
	 * Return page title of webpage in String
	 * 
	 * @author Anjan S P
	 * @return Page title of the webpage where the selenium is currently
	 *         interacting.
	 */
	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}

}
