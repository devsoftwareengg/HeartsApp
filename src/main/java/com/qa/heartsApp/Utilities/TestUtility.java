package com.qa.heartsApp.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.heartsApp.BaseClass.TestBase;

public class TestUtility extends TestBase {
	// Here we write all common methods which are available for all the Classes.

	// 1. These 2 variable we used in TestBase Class for Page Load and Implicit
	// Wait.
	public static long Page_Load_TimeOut = 60;
	public static long Implicit_Wait = 60;

	// 2. Excel Sheet Path - DataProvider Utility.
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "/src/main/resources/HeartsAppTestsData.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	// 3. Screenshot Utility.
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + System.currentTimeMillis() + ".png"));
	}

	// 4. Explicit Wait for Click on any Element.
	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// 5. Explicit Wait to Wait on an Element.
	public static void waitForElement(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	// 6. Explicit Wait to Wait on an Element.
	public static void waitForElements(WebDriver driver, List<WebElement> elements, int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	// 7. Extent Report - 1
	public static String getSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	// 8. Extent Report - 2
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		// We have generated Date now.
		String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// After execution, you could see a folder "FailedTestsScreenshots"
		// Under Source folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// 9. Set Date For Log4J
	public static void setDateForLog4j() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy hhmmss");
		System.setProperty("current_date", dateFormat.format(new Date()));
		PropertyConfigurator.configure("./src/main/resources/log4j.properties");
	}

	// 10. To Select Values from DropDown
	public static void selectValueFromDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	// 11. ElementNotVisibleException
	public static void elementNotVisibleException(List<WebElement> element) {
		List<WebElement> radioButtons = element;
		int count = radioButtons.size();
		System.out.println("Total Radio Buttons ::: " + count);

		for (int i = 0; i < count; i++) {
			int x = radioButtons.get(i).getLocation().getX();
			if (x != 0) {
				radioButtons.get(i).click();
				break;
			}
		}
	}

	// 12. Clicking on Element with the help of JavaScriptExecutor
	public static void clickUsingJavaScriptExecutor(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
}
