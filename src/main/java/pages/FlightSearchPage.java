package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BaseClass;
import utils.TestData;

public class FlightSearchPage extends BaseClass {

	// Sayfadaki elemanları bulma metodları, locators
	public WebElement flight() {
		return getDriver()
				.findElement(By.cssSelector("div[class='Flight-module_RadioWrapper__2vLOZ'] div:nth-child(2)"));
	}

	public WebElement fromCityInput() {
		return getDriver().findElement(By.name("origin"));
	}

	public WebElement toCityInput() {
		return getDriver().findElement(By.name("destination"));
	}

	public WebElement departureDateLabel() {
		return getDriver()
				.findElement(By.cssSelector("div[data-testid='enuygun-homepage-flight-departureDate-label']"));
	}

	public WebElement departureDateInput() {
		String dynamicDepartureDate = TestData.DEPARTURE_DATE; // TestData'dan kalkış tarihini al
		return driver.findElement(By.cssSelector("button[title='" + dynamicDepartureDate + "']")); // Dinamik buton
	}

	public WebElement returnDateLabel() {
		return getDriver().findElement(By.cssSelector("div[data-testid='enuygun-homepage-flight-returnDate-label']"));
	}

	public WebElement returnDateInput() {
		String dynamicReturnDate = TestData.RETURN_DATE; // TestData'dan dönüş tarihini al
		return driver.findElement(By.cssSelector("button[title='" + dynamicReturnDate + "']")); // Dinamik buton
	}

	public WebElement searchButton() {
		return getDriver().findElement(By.cssSelector("button[type='submit']"));
	}

	public WebElement returnTimeCard() {
		return getDriver().findElement(
				By.cssSelector("div[class='ctx-filter-departure-return-time card-header'] span[class='card-title']"));
	}

	public WebElement sliderHandle1() {
		return getDriver().findElement(By.xpath("//div[@class='rc-slider-handle rc-slider-handle-1']"));
	}

	public WebElement sliderHandle2() {
		return getDriver().findElement(By.xpath("//div[@class='rc-slider-handle rc-slider-handle-2']"));

	}

	public List<WebElement> flightDepartureTime() {
		return getDriver().findElements(By.className("flight-departure-time"));

	}

	public WebElement airlineCard() {
		return getDriver().findElement(By.cssSelector(".ctx-filter-airline.card-header"));

	}

	public WebElement selectAirlineTK() {
		return getDriver().findElement(By.cssSelector("label[for='TKairlines']"));

	}

	public WebElement sortPrice() {
		return getDriver().findElement(By.cssSelector("span[class='search__filter_sort-PRICE_ASC']"));

	}

	public WebElement transitCard() {
		return getDriver().findElement(By.cssSelector(".ctx-filter-transit.card-header"));

	}

	public WebElement directTransit() {
		return getDriver().findElement(By.cssSelector(".search__filter_stopCount-0.--span-3"));

	}

	public WebElement moreButton() {
		return getDriver().findElement(By.cssSelector(".custom-select-alternate.tr"));

	}

	public WebElement sortDeparture() {
		return getDriver().findElement(By.cssSelector(".sort-dropdown-item.search__filter_sort-DEPARTURE_ASC"));

	}

	// Sayfada yapılacak işlemler
	public void searchFlight() {
		flight().click();
		fromCityInput().sendKeys(TestData.FROM_CITY); // Kalkış Şehri
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.TAB).perform();
		toCityInput().sendKeys(TestData.TO_CITY); // Varış Şehri
		actions.sendKeys(Keys.TAB).perform();
		departureDateLabel().click();
		// sendKeys(TestData.DEPARTURE_DATE); // Kalkış tarihi
		returnDateLabel().click();
		// sendKeys(TestData.RETURN_DATE); // Dönüş tarihi
		searchButton().click(); // Ucuz Bilet Bul butonuna tıklar
	}

	// Bir sonraki sayfa gelene kadar beklemek için
	public void getPage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 saniye bekle
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='searchFormGraphic col tr']")

				));

	}

	// Kalkış saatini filtrelemek için yapılacak işlemler
	public void filterDepartureHours() {

		int moveDistance1 = (int) (1440 * (100 / 1439.0));
		int moveDistance2 = (int) (840 * (100 / 1439.0));

		returnTimeCard().click(); // Gidiş kalkış / varış saatleri kartı seçildi

		Actions actions = new Actions(driver);
		// DragAndDrop
		actions.dragAndDropBy(sliderHandle1(), moveDistance1, 0).perform();
		System.out.println("1 seçildi");// sliderda 10:00'ı ayarla
		actions.dragAndDropBy(sliderHandle2(), -moveDistance2, 0).perform();
		System.out.println("2 seçildi"); // sliderda 18:00'ı ayarla

	}

	public class InvalidTimeException extends Exception {
		public InvalidTimeException(String message) {
			super(message);
		}
	}

	// Uçuş saatlerinin 10:-00-18:00 arasında olup olmadığını kontrol eder
	public void checkDepartureHours() {

		try {
			// Tüm öğeleri alın
			List<WebElement> elements = flightDepartureTime();

			// Listeyi kontrol et
			for (WebElement element : elements) {
				// Elementin içeriğini al
				String timeText = element.getText();

				// Saat formatını kontrol et ve saati ayır
				String[] timeParts = timeText.split(":");
				int hour = Integer.parseInt(timeParts[0]);

				// Saat 10:00 ile 18:00 arasında mı kontrol et
				if (hour >= 10 && hour <= 18) {
					System.out.println("Saat " + timeText + " 10:00 ile 18:00 arasında.");
				} else {
					throw new InvalidTimeException("Saat " + timeText + " 10:00 ile 18:00 arasında değil.");
				}
			}

			System.out.println("className flight-departure-time olan tüm sonuçlar kontrol edildi");

		} catch (InvalidTimeException e) {
			System.out.println("Hata: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Bir hata oluştu: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// TK uçuşlarını artan olarak filtreler
	public void filterAirline() {
		// Airline card elementini görünür hale getirmek için sayfayı kaydır
		WebElement airlineCardElement = airlineCard();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", airlineCardElement);

		airlineCard().click();// Havayolları kartını seçer
		try {
			WebElement tkAirlineElement = selectAirlineTK();
			tkAirlineElement.click(); // THY Havayolunu seçer
		} catch (NoSuchElementException e) {
			System.out.println("Havayolu bulunamadı.");
		}
		sortPrice().click();// Fiyat artan olarak filtreler

	}

	// Direk uçuşları aratır, kalkış erkenden geçe olacak şekilde filtreler
	public void filterTransit() {
		// Transit card elementini görünür hale getirmek için sayfayı kaydır
		// WebElement transitCardElement = transitCard();
		// ((JavascriptExecutor)
		// getDriver()).executeScript("arguments[0].scrollIntoView(true);",
		// transitCardElement);

		// transitCard().click();
		directTransit().click();// direkuçuşları seçer
		moreButton().click();// daha fazla butonuna tıklar
		sortDeparture().click();// kalkış erkenden geçe sıralar

	}

}