package com.asp.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import com.asp.enums.ConfigProperties;
import com.asp.listeners.WebEventListener;
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
			WebDriver original = null;
			switch (browser) {
			case "chrome":
				original = GetChromeDriver.driver(false);
				break;
			case "chrome-incognito":
				original = GetChromeDriver.driver(true);
				break;
			case "firefox":
				original = GetFirefoxDriver.driver(false);
				break;
			case "firefox-private":
				original = GetFirefoxDriver.driver(true);
				break;
			case "edge":
				original = GetEdgeDriver.driver(false);
				break;
			case "edge-inprivate":
				original = GetEdgeDriver.driver(true);
				break;
			case "grid": {
				EdgeOptions eo = new EdgeOptions();
				eo.setPlatformName("Windows 10");
				try {
					original = new RemoteWebDriver(new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL)), eo);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
				break;
			default:
				original = GetEdgeDriver.driver(false);
				break;
			}
			WebEventListener listener = new WebEventListener();
			WebDriver decorated = new EventFiringDecorator(listener).decorate(original);
			DriverManager.setDriver(decorated);
			DriverManager.getDriver().manage().window().maximize();
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
