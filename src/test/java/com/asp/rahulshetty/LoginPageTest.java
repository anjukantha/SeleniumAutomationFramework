package com.asp.rahulshetty;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.asp.annotations.FrameworkAnnotation;
import com.asp.driver.Driver;
import com.asp.driver.DriverManager;
import com.asp.enums.CategoryType;
import com.asp.enums.ConfigProperties;
import com.asp.listeners.ListenerClass;
import com.asp.pages.LoginPage;
import com.asp.utils.PropertyUtils;
import com.asp.utils.ScreenshotUtils;

@Listeners(ListenerClass.class)
class LoginPageTest {

	@BeforeMethod
	void initilization() {
		Driver.initDriver();
		DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
	}

	@AfterClass
	void termination() {
		Driver.quitDriver();
	}

	@FrameworkAnnotation(category = CategoryType.SMOKE)
	@Test
	void loginTest1() throws IOException {
		new LoginPage().enterUserName("rahulshettyacademy").enterPassword("learning").clickUserRadioButton()
				.selectType("Teacher").clickAgreeTandC().clickSignInBtn();
		ScreenshotUtils.captureSheetshotAsPNG("Login Page");
	}

	@Test
	void loginTest2() throws IOException {
		new LoginPage().enterUserName("Anjan").enterPassword("password").clickUserRadioButton().selectType("Student")
				.clickAgreeTandC().clickSignInBtn();

		ScreenshotUtils.captureSheetshotAsPNG("Login Page");
	}
}
