package tests;

import pages.FlightSearchPage;
import utils.ExtentReportManager;
import org.testng.annotations.Test;

public class FlightSearchTest3 extends FlightSearchPage {

	@Test
	public void Case3() {
		try {
			// Test2 adımlarını başlatıyoruz
			ExtentReportManager.startTest("Flight Search Test 3", "Filtering direct flights that may have high impact");

			searchFlight();
			getPage();
			filterTransit();

			// Test geçerse başarı durumu raporlanır
			ExtentReportManager.logPass(driver, "Case 3 passed");

		} catch (Exception e) {
			// Test başarısız olursa hata raporu ve ekran görüntüsü alınır
			ExtentReportManager.logFail(driver, "Case 3 failed: " + e.getMessage());
		}
	}
}