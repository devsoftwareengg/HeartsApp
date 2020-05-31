package com.qa.heartsApp.TestCase;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.lang.reflect.Method;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.qa.heartsApp.BaseClass.TestBase;
import com.qa.heartsApp.Pages.LoginPage;
import com.qa.heartsApp.Utilities.TestUtility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import io.appium.java_client.MobileElement;

public class TestLogin extends TestBase {
	LoginPage loginPage;
	String sheetName = "Login";
	@Rule
    public TestWatcher watcher = Factory.createWatcher();
	//private static EnhancedAndroidDriver<AndroidElement> driver;
	
	@BeforeClass
	public static void setup() {

		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			// dc.setCapability("app", property.getProperty("App_file_Path"));
			dc.setCapability(CapabilityType.VERSION, "10");
			dc.setCapability("deviceName", "Pixel 2");
			dc.setCapability(MobileCapabilityType.UDID, "FA7AE1A02036");
			dc.setCapability("automationName", "UiAutomator1");
			dc.setCapability("appActivity", "com.hfn.unified.MainActivity");
			dc.setCapability("appPackage", "com.hfn.unified");
			
			driver = Factory.createAndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.setLogLevel(Level.INFO);

		} catch (Exception exp) {
			System.out.println("Cause is :" + exp.getCause());
			System.out.println("Message is :" + exp.getMessage());
			exp.printStackTrace();

		}
	}
//	@DataProvider
//	public Object[][] getLoginlists() throws InvalidFormatException {
//		Object data[][] = TestUtility.getTestData(sheetName);
//		return data;
//	}

	@Test
	public void testLogin() {

		LoginPage loginPage = new LoginPage();
		//extentTest = extent.startTest(method.getName());
		Log.info("TestLogin Started ..................");
		try {
			loginPage.LoginUserPage();

			Log.info("Test Completed ..................");
			TestUtility.takeScreenshotAtEndOfTest();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}
}
