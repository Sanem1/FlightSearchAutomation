package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestData {

	public static final String FROM_CITY = "İstanbul"; // Kalkış şehri
	public static final String TO_CITY = "Ankara"; // Varış şehri
	public static final String DEPARTURE_DATE = getToday(); // Kalkış tarihi (bugünün tarihi)
	public static final String RETURN_DATE = getTomorrow(); // Dönüş tarihi (3 gün sonrası)

	// Bugünün tarihini al
	private static String getToday() {
		LocalDate today = LocalDate.now();
		return today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	// Yarının tarihini al
	private static String getTomorrow() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		return tomorrow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
