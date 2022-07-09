package com.asp.driver;

import java.util.Objects;

import com.asp.enums.ConfigProperties;
import com.asp.utils.PropertyUtils;

/**
 * Driver class is responsible for invoking and closing the browsers.
 * 
 * It is also responsible for setting the driver variable to DriverManager which
 * handles the thread safety for the WebDriver instance.
 * 
 * 
 * @author Anjan S P
 * @see com.asp.driver.DriverManager
 */

public final class Driver {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private Driver() {
	}

	/**
	 * Gets the browser value and initialize the browser based on that. Browser
	 * value will be passed from ConfigFile
	 * 
	 * @author Anjan S P
	 * 
	 */
	public static void initDriver() {
		if (Objects.isNull(DriverManager.getDriver())) {
			String browser = System.getProperty("browser");
			if (Objects.isNull(browser)) {
				browser = PropertyUtils.get(ConfigProperties.BROWSER);
			}
			if (browser.contains("chrome")) {
				if (browser.contains("incognito")) {
					DriverManager.setDriver(GetChromeDriver.driver(true));
				} else {
					DriverManager.setDriver(GetChromeDriver.driver(false));
				}
			} else if (browser.contains("firefox")) {
				if (browser.contains("private")) {
					DriverManager.setDriver(GetFirefoxDriver.driver(true));
				} else {
					DriverManager.setDriver(GetFirefoxDriver.driver(false));
				}
			}
		}
	}

	/**
	 * Terminates the browser instance. Sets the threadlocal to default value, i.e
	 * null.
	 * 
	 * @author Anjan S P
	 */
	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
