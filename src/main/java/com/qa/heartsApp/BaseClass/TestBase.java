package com.qa.heartsApp.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.qa.heartsApp.Utilities.TestUtility;
import com.qa.heartsApp.Utilities.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestBase {

	public static AndroidDriver<AndroidElement> driver = null;
	public static Properties property;
	public static Logger Log;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	public TestBase() {
		Log = Logger.getLogger(this.getClass()); // Logger Implementation
		try {
			property = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/qa/heartsApp/Configuration/PreConfiguration.properties");
			property.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Before
//	public void setExtent()
//	{
//		TestUtility.setDateForLog4j();
//		//Telling System Where Exactly Extent Report has to be Generated under Project.
//		extent = new ExtentReports(System.getProperty("user.dir") + "/TestResults/ExtentReport" + TestUtility.getSystemDate() + ".html");
//		extent.addSystemInfo("Host Name", "Devesh Pixel Android 10 device");
//		extent.addSystemInfo("User Name", "Devesh Kumar");
//		extent.addSystemInfo("Environment", "Automation Testing");
//	}
	
//	@AfterClass
//	public  void endReport()
//	{
//		extent.flush();
//		extent.close();
//	}
	
//	@After
//	public void tearDown(ITestResult result) throws IOException
//	{
//		if(result.getStatus()==ITestResult.FAILURE)
//		{
//			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getName()); //To Add Name in Extent Report.
//			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable()); //To Add Errors and Exceptions in Extent Report.
//		
//			String screenshotPath = TestUtility.getScreenshot(driver, result.getName());
//			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //To Add Screenshot in Extent Report.
//		}
//		else if(result.getStatus()==ITestResult.SKIP)
//		{
//			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
//		}
//		else if(result.getStatus()==ITestResult.SUCCESS)
//		{
//			extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
//		}
//		extent.endTest(extentTest); //Ending Test and Ends the Current Test and Prepare to Create HTML Report.
//		driver.quit();
//		Log.info("Browser Terminated");
//		Log.info("-----------------------------------------------");
//	}
}
