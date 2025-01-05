package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	// WebDriverManager ile ChromeDriver'ı otomatik olarak kur
	public static WebDriver getDriver() {

		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

}