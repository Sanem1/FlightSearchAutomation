package tests;

import pages.FlightSearchPage;
import utils.ExtentReportManager;
import org.testng.annotations.Test;


public class FlightSearchTest2 extends FlightSearchPage {

	@Test
	public void Case2() {
		try {
			// Test2 adımlarını başlatıyoruz
			ExtentReportManager.startTest("Flight Search Test 2", "Filtering TK flights by ascending order");

			searchFlight();
			getPage();
			filterAirline();

			// Test geçerse başarı durumu raporlanır
			ExtentReportManager.logPass(driver, "Case 2 passed");

		} catch (Exception e) {
			// Test başarısız olursa hata raporu ve ekran görüntüsü alınır
			ExtentReportManager.logFail(driver, "Case 2 failed: " + e.getMessage());
		}
	}
}
