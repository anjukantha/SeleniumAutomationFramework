package com.asp.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
	static String browser;

	/**
	 * Private constructor to avoid external instantiation
	 */
	private Driver() {
	}

	static {
		browser = System.getProperty("browser");
		if (Objects.isNull(browser)) {
			browser = PropertyUtils.get(ConfigProperties.BROWSER);
		}
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
			switch (browser) {
			case "chrome":
				DriverManager.setDriver(GetChromeDriver.driver(false));
				break;
			case "chrome-incognito":
				DriverManager.setDriver(GetChromeDriver.driver(true));
				break;
			case "firefox":
				DriverManager.setDriver(GetFirefoxDriver.driver(false));
				break;
			case "firefox-private":
				DriverManager.setDriver(GetFirefoxDriver.driver(true));
				break;
			case "edge":
				DriverManager.setDriver(GetEdgeDriver.driver(false));
				break;
			case "edge-inprivate":
				DriverManager.setDriver(GetEdgeDriver.driver(true));
				break;
			case "grid": {
				EdgeOptions eo = new EdgeOptions();
				eo.setPlatformName("Windows 10");
				try {
					DriverManager.setDriver(
							new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), eo));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
				break;
			default:
				DriverManager.setDriver(GetEdgeDriver.driver(false));
				break;
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
