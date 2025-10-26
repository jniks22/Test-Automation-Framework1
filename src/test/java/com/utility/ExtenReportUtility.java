package com.utility;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenReportUtility {
	
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest>  extentTest = new ThreadLocal<ExtentTest>();
	
	public static void setUpSparkReporter(String reportName) {
		ExtentSparkReporter extentSparReportter = new ExtentSparkReporter(System.getProperty("user.dir") + "//"+reportName);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparReportter);
	}

	public  static void createdExtentTest(String testName) {
		
		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
		
	}
	
	public static  ExtentTest getTest() {
		
		return extentTest.get();
	}
	
	public static void flushReport() {
		
		extentReports.flush();
	}
}
