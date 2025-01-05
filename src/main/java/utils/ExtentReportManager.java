package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	// Instance of ExtentReports
	public static ExtentReports getInstance() {
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") + "/custom-reports/ExtentReport.html";
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);

			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle("Automation Test Report");
			htmlReporter.config().setReportName("Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Tester", "Sanem Tel Uludag");
		}
		return extent;
	}

	// Start a new test
	public static void startTest(String testName, String description) {
		test = extent.createTest(testName, description);
	}

	// Log pass results
	public static void logPass(WebDriver driver, String message) {
		if (test != null) {
			test.pass(message);
			takeScreenshot(driver);
		}
	}

	// Log fail results and capture screenshot
	public static void logFail(WebDriver driver, String message) {
		if (test != null) {
			test.fail(message);
			takeScreenshot(driver);
		}
	}

	// Take screenshot
	public static void takeScreenshot(WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			String screenshotPath = System.getProperty("user.dir") + "/custom-reports/screenshots/"
					+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(srcFile, new File(screenshotPath));
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Finalize the report
	public static void finalizeReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}