package com.qa.heartsApp.TestCase;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.lang.reflect.Method;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.qa.heartsApp.BaseClass.TestBase;
import com.qa.heartsApp.Pages.LoginPage;
import com.qa.heartsApp.Utilities.TestUtility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class LoginTests extends TestBase {
	LoginPage loginPage;
	String sheetName = "Login";
	
	@BeforeMethod
	public void setup() {

		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			// dc.setCapability("app", property.getProperty("App_file_Path"));
			dc.setCapability(CapabilityType.VERSION, "10");
			dc.setCapability("deviceName", property.getProperty("DeviceName"));
			dc.setCapability(MobileCapabilityType.UDID, property.getProperty("UDID"));
			dc.setCapability("automationName", "UiAutomator1");
			dc.setCapability("appActivity", property.getProperty("AppActivity"));
			dc.setCapability("appPackage", property.getProperty("AppPackage"));

			driver = new AndroidDriver<AndroidElement>(new URL(property.getProperty("Hub_url")), dc);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.setLogLevel(Level.INFO);

		} catch (Exception exp) {
			System.out.println("Cause is :" + exp.getCause());
			System.out.println("Message is :" + exp.getMessage());
			exp.printStackTrace();

		}
	}
	@DataProvider
	public Object[][] getLoginlists() throws InvalidFormatException {
		Object data[][] = TestUtility.getTestData(sheetName);
		return data;
	}

	@Test(priority = 0, enabled = true, dataProvider = "getLoginlists")
	public void testLogin(String Username, String Password, Method method) {

		LoginPage loginPage = new LoginPage();
		extentTest = extent.startTest(method.getName());
		Log.info("TestLogin Started ..................");
		try {
			loginPage.LoginUserPage(Username, Password);

			Log.info("Test Completed ..................");
			TestUtility.takeScreenshotAtEndOfTest();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
