package com.asp.driver;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.asp.constants.FrameworkConstants;
import com.asp.exceptions.BrowserInvocationFailedException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Used to create the Firefox browser instance with required options.
 * 
 * @author Anjan S P
 *
 */
public class GetFirefoxDriver {

	private GetFirefoxDriver() {
	}

	/**
	 * Used to open Firefox browser
	 * 
	 * @param privateBrowser
	 * @return FirefoxDriver
	 */
	static FirefoxDriver driver(boolean privateBrowser) {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		if (privateBrowser) {
			options.addArguments("-private");
		}
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", FrameworkConstants.getTempPath());
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.download.viewableInternally.enabledTypes", "");
		options.addPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/pdf;text/plain;application/text;text/xml;application/xml");
		options.addPreference("pdfjs.disabled", true);

		try {
			return new FirefoxDriver(options);
		} catch (SessionNotCreatedException e) {
			throw new BrowserInvocationFailedException("Failed to open the browser instance.", e);
		}
	}
}
