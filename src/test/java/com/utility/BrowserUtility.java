package com.utility;
 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {

	private static  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger=  LoggerUtility.getLogger(this.getClass());
	
	

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);   // initialize instance variable driver
	
	}
	
	public BrowserUtility(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set( new ChromeDriver());
		}
		else if(browserName.equalsIgnoreCase("edgebrowser")) {
			driver.set(new EdgeDriver());  
			}
		else {
			System.err.println("Invalid Browser");
		}

	}
	
	public BrowserUtility(Browser browserName) {
		logger.info("Lounching brouser"+ browserName);
		if (browserName==Browser.CHROME) {
			driver.set(new ChromeDriver()); 
		}
		else if (browserName==Browser.EDGE) {
			driver.set(new EdgeDriver());  
			}
		else if (browserName==Browser.FIREFOX) {
			driver.set(new FirefoxDriver());  
			}

	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Lounching brouser"+ browserName);
		if (browserName==Browser.CHROME ) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver(options)); 
		}
			else {
				driver.set(new ChromeDriver());
			}
		}
		else if (browserName==Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=new");
				options.addArguments("disable-gpu");
				options.addArguments("--no-sandbox");  // essential for running in some environments
				options.addArguments("--disable-dev-shm-usage"); // reduce memory issues
				options.addArguments("--window-size=1920,1080"); // full-screen resolution
				driver.set(new EdgeDriver(options));
			}else {
				driver.set(new EdgeDriver());
			}
			  
			}
			
		else if (browserName==Browser.FIREFOX) {
			
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=new");
				
				driver.set(new FirefoxDriver(options));
			}
				else {
			driver.set(new FirefoxDriver());  
			}
		}
			

	}



	public void goToWebsite(String url) {
		logger.info("visiting the website" + url);

		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maxifizing browser window");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
	
		 logger.info("Finding element with locator" + locator);
		WebElement element = driver.get().findElement(locator);
		 
		element.click();
	}

	public void enterText(By locator, String TextToEnter) {
		WebElement element =driver.get().findElement(locator);
		logger.info("element fount and trying to enter text" + TextToEnter);
		element.sendKeys(TextToEnter);
	}
	
	public String getVisibleText(By locator) {
		
		WebElement element = driver.get().findElement(locator);
		logger.info("element fount and returning values" + element.getText());
		return element.getText();
	}
	
	public String takeScreenShot(String name) {
		
		TakesScreenshot screenshot = (TakesScreenshot)driver.get();
		File screenshotData= screenshot.getScreenshotAs(OutputType.FILE);
		Date date= new Date();
		SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
		String timeStamp= format.format(date);
		
		String path= "./screenshots/"+name+ " - "+ timeStamp +".png";
		File screenshotFile= new File(path );
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public void quit() {
	    logger.info("Closing the browser");
	    getDriver().quit();      // close the browser
	    setDriver(null);         // clean up the ThreadLocal reference (optional but recommended)
	}
}
