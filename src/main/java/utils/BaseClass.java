package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    protected WebDriver driver;  // WebDriver örneğini tutacak değişken
    protected ExtentReports extent;
    protected ExtentTest test;
    // Testlerden önce çalışacak metod
    @BeforeMethod
    public void setUp() {
    	extent = ExtentReportManager.getInstance();
    	
        driver = WebDriverFactory.getDriver();  
        System.out.println("WebDriver başarıyla başlatıldı.");
        driver.manage().window().maximize();
        driver.get("https://www.enuygun.com/ucak-bileti");
        
}


    // Testlerden sonra çalışacak metod
    @AfterMethod
    public void tearDown() {
    	try {
                if (driver != null) {
                driver.quit();  // WebDriver'ı kapat
                System.out.println("WebDriver başarıyla kapatıldı.");
                
            }
        } catch (Exception e) {
            System.err.println("WebDriver kapatılırken bir hata oluştu: " + e.getMessage());
            e.printStackTrace();  // Hata yığınını konsola yazdır
        }
    		ExtentReportManager.finalizeReport();
      
    }
    
    public WebDriver getDriver() {
        return driver;
    }
}