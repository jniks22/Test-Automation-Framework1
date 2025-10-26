package com.ui.tests;
 
import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;


@Listeners({com.ui.listeners.TestListener.class})

public class LoginTest extends TestBase {
	

	Logger logger=  LoggerUtility.getLogger(this.getClass());

	@Test(description = "Verify if valid user is login", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		// WebDriver driver = new ChromeDriver(); // lounch brouser window

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Nikhil Jawale");

	}
	
/*	@Test(description = "Verify if valid user is login", groups = { "e2e",
	"sanity" }, dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
public void loginCSVTest(User user) {

// WebDriver driver = new ChromeDriver(); // lounch brouser window

assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
		"Nikhil Jawale");

}


	@Test(description = "Verify if valid user is login", groups = { "e2e",
	"sanity" }, dataProviderClass = com.ui.dataprovider.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",
	retryAnalyzer= com.ui.listeners.MyRetryAnalizer.class)
public void loginExcelTest(User user) {

// WebDriver driver = new ChromeDriver(); // lounch brouser window



assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
		"Nikhil Jawale1");*/

	
}