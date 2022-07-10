package com.asp.rahulshetty;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.asp.driver.Driver;
import com.asp.driver.DriverManager;
import com.asp.enums.ConfigProperties;
import com.asp.pages.LoginPage;
import com.asp.utils.PropertyUtils;
import com.asp.utils.ScreenshotUtils;

class LoginPageTest {

	@BeforeMethod
	void initilization() {
		Driver.initDriver();
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
	}

	@AfterMethod
	void termination() {
		Driver.quitDriver();
	}

	@Test
	void loginTest1() throws IOException {
		new LoginPage().enterUserName("rahulshettyacademy").enterPassword("learning").clickUserRadioButton()
				.selectType("Teacher").clickAgreeTandC().clickSignInBtn();
		ScreenshotUtils.getScreenshot("Login Page");
	}

	@Test
	void loginTest2() throws IOException {
		new LoginPage().enterUserName("Anjan").enterPassword("password").clickUserRadioButton().selectType("Student")
				.clickAgreeTandC().clickSignInBtn();
		ScreenshotUtils.getScreenshot("Login Page");
	}
}
