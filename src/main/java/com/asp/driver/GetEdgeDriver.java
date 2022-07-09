package com.asp.driver;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.asp.constants.FrameworkConstants;
import com.asp.exceptions.BrowserInvocationFailedException;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Used to create the Chrome browser instance with required options.
 * 
 * @author Anjan S P
 *
 */
public class GetEdgeDriver {

	private GetEdgeDriver() {
	}

	/**
	 * Used to open the edge browser
	 * 
	 * @param incognito
	 * @return EdgeDriver
	 */
	static EdgeDriver driver(boolean inprivate) {
		WebDriverManager.edgedriver().setup();

		EdgeOptions eo = new EdgeOptions();
		if (inprivate) {
			eo.addArguments("-inprivate");
		}
		Map<String, Object> prefs = new HashedMap<>();
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("download.default_directory", FrameworkConstants.getTempPath());
		eo.setExperimentalOption("prefs", prefs);

		try {
			return new EdgeDriver(eo);
		} catch (SessionNotCreatedException e) {
			throw new BrowserInvocationFailedException("Failed to open the browser instance.", e);
		}
	}
}
