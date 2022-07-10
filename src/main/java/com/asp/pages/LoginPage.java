package com.asp.pages;

import org.openqa.selenium.By;

import com.asp.enums.WaitStrategy;

public class LoginPage extends BasePage {

	// Login page
	private final By username = By.id("username");
	private final By password = By.id("password");
	private final By adminRadioBtn = By.xpath("//*[@value='admin']/../span[@class='checkmark']");
	private final By userRadioBtn = By.xpath("//*[@value='user']/../span[@class='checkmark']");
	private final By typeDD = By.xpath("//select");
	private final By termsCheckBox = By.id("terms");
	private final By signInBtn = By.id("signInBtn");
	private final By okayBtn = By.id("okayBtn");

	public LoginPage enterUserName(String userName) {
		sendKeys(username, userName, WaitStrategy.CLICKABLE, "Username");
		return this;
	}

	public LoginPage enterPassword(String password) {
		sendKeys(this.password, password, WaitStrategy.CLICKABLE, "Password");
		return this;
	}

	public LoginPage clickAdminRadioButton() {
		click(adminRadioBtn, WaitStrategy.CLICKABLE, "Admin Radio Button");
		return this;
	}

	public LoginPage clickUserRadioButton() {
		click(userRadioBtn, WaitStrategy.CLICKABLE, "User Radio Button");
		click(okayBtn, WaitStrategy.CLICKABLE, "Okay");
		return this;
	}

	public LoginPage selectType(String type) {
		selectValue(typeDD, type, WaitStrategy.TEXTPRESENTINELEMENT, "Type");
		return this;
	}

	public LoginPage clickAgreeTandC() {
		click(termsCheckBox, WaitStrategy.CLICKABLE, "Terms and Conditions");
		return this;
	}

	public LoginPage clickSignInBtn() {
		click(signInBtn, WaitStrategy.CLICKABLE, "Sign In");
		return this;
	}
}
