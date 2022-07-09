package com.asp.driver;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.asp.constants.FrameworkConstants;
import com.asp.exceptions.BrowserInvocationFailedException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Used to create the Chrome browser instance with required options.
 * 
 * @author Anjan S P
 *
 */
public class GetChromeDriver {

	private GetChromeDriver() {
	}

	/**
	 * Used to open the chrome browser
	 * 
	 * @param incognito
	 * @return ChromeDriver
	 */
	static ChromeDriver driver(boolean incognito) {
		WebDriverManager.chromedriver().setup();

		ChromeOptions co = new ChromeOptions();
		if (incognito) {
			co.addArguments("--incognito");
		}
		Map<String, Object> prefs = new HashedMap<String, Object>();
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.default_directory", FrameworkConstants.getTempPath());
		co.setExperimentalOption("prefs", prefs);

		try {
			return new ChromeDriver(co);
		} catch (SessionNotCreatedException e) {
			throw new BrowserInvocationFailedException("Failed to open the browser instance.", e);
		}
	}
}
