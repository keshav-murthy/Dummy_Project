package Pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class RandomInputPage extends BasePage {

	protected static String username;
	protected static String[] randomUsers;
	protected static Random r = new Random();

	@FindBy(xpath = "//input[@id='serial_no']")
	WebElement assetSerialNumber;

	public RandomInputPage(WebDriver driver) {
		super(driver);
	}

	public String selectRandomInput() {

		int min = 10000;
		int max = 100000000;
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return Integer.toString((r.nextInt((max - min) + 1) + min));
	}

	public String selectRandomUsername() {

		randomUsers = new String[] { "dmech+alaskan@barcodesinc.com", "dmech+alphia@barcodesinc.com",
				"dmech+arrowhead@barcodesinc.com", "dmech+banner@barcodesinc.com", "dmech+barrett@barcodesinc.com",
				"dmech+becker@barcodesinc.com", "dmech+bergstrom@barcodesinc.com", "dmech+db@barcodesinc.com",
				"dmech+dominium@barcodesinc.com", "dmech+englewood@barcodesinc.com",
				"dmech+fashionforms@barcodesinc.com", "dmech+futurefoam@barcodesinc.com", "dmech+gicon@barcodesinc.com",
				"dmech+greco@barcodesinc.com", "dmech+greenworks@barcodesinc.com", "dmech+houston@barcodesinc.com",
				"dmech+ims@barcodesinc.com", "dmech+inwk@barcodesinc.com", "dmech+jfc@barcodesinc.com",
				"dmech+kaltire@barcodesinc.com", "dmech+landair@barcodesinc.com", "dmech+marvin@barcodesinc.com",
				"dmech+medspeed@barcodesinc.com", "dmech+meijer@barcodesinc.com", "dmech+menasha@barcodesinc.com",
				"dmech+newhudson@barcodesinc.com", "dmech+nordic@barcodesinc.com", "dmech+ocr@barcodesinc.com",
				"dmech+penske@barcodesinc.com", "dmech+purfoods@barcodesinc.com", "dmech+rakuten2@barcodesinc.com",
				"dmech+rawlings@barcodesinc.com", "dmech+ruan@barcodesinc.com", "dmech+sanford@barcodesinc.com",
				"dmech+satellite@barcodesinc.com", "dmech+sheetz1@barcodesinc.com", "dmech+siemens@barcodesinc.com",
				"dmech+siemens1@barcodesinc.com", "dmech+snapon@barcodesinc.com", "dmech+ts@barcodesinc.com",
				"dmech+usexpediters@barcodesinc.com", "dmech+veritiv@ocr.ca", "dmech+wineshipping@barcodesinc.com",
				"dmech+worley@barcodesinc.com", };
		int randomNumberIndex = r.nextInt(randomUsers.length);
		String randomUsername = randomUsers[randomNumberIndex];
		randomNumberIndex = r.nextInt(randomUsers.length);
		randomUsername = randomUsers[randomNumberIndex];
		System.out.println("The Username selected is :------" + randomUsername);

		return randomUsername;
	}
}