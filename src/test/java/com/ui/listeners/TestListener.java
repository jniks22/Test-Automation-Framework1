package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtenReportUtility;
import com.utility.LoggerUtility;
 
public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparReportter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtenReportUtility.createdExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		logger.info(result.getMethod().getMethodName() + " " + "Passed");
		ExtenReportUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "Passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "Fail");
		logger.error(result.getThrowable().getMessage());
		ExtenReportUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "Failed");
		ExtenReportUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

		Object testclass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase) testclass).getInstance();
		logger.info("Capturing screenshot for failed test");
		String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching screenshot to HTML file");
		ExtenReportUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + "  " + "Skipped");
		ExtenReportUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "Skipped");
	}

	public void onStart(ITestContext context) {
		logger.info("Test suit Started execution");
		ExtenReportUtility.setUpSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		// not implemented
		logger.info("Test Suit completed");
		ExtenReportUtility.flushReport();
	}
}