package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{								
	
	
	Logger logger=  LoggerUtility.getLogger(this.getClass());

//this class will follow page object design pattern

	//1. if your creating any variables inside page classes first type of variables will be  locators:: by class is there 
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains (text(), 'Sign in')]");
	
	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		//goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}




	public HomePage(WebDriver lambdadriver) {
		super(lambdadriver);
	}




	public LoginPage goToLoginPage() {  //page function
		logger.info("trying to perform click to go sign in page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
		
		
	}
}