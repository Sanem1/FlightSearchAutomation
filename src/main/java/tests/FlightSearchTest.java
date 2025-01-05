package tests;

import pages.FlightSearchPage;
import utils.ExtentReportManager;
import org.testng.annotations.Test;



public class FlightSearchTest extends FlightSearchPage {

	@Test
	public void Case1() {
		try {
			// Test adımlarını başlatıyoruz
			ExtentReportManager.startTest("Flight Search Test 1", "Checking departureTime filter functionality");

			searchFlight();
			getPage();
			filterDepartureHours();
			checkDepartureHours();

			// Test geçerse başarı durumu raporlanır
			ExtentReportManager.logPass(driver, "Case 1 passed");

		} catch (Exception e) {
			// Test başarısız olursa hata raporu ve ekran görüntüsü alınır
			ExtentReportManager.logFail(driver, "Case 1 failed: " + e.getMessage());
		}
	}

}