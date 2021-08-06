package Pages;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class MyQuotesPage extends BasePage {

	@FindBy(xpath = "//span//strong")
	WebElement pageHeader;

	@FindBy(xpath = "//div//h2")
	WebElement accountTitle;

	@FindBy(xpath = "//tbody//tr//td[1]//a")
	List<WebElement> quotes;

	@FindBy(xpath = "//tbody//tr//td[3]//a")
	List<WebElement> ticketIDs;

	@FindBy(xpath = "//tbody//tr//td[7]//a")
	List<WebElement> viewQuotes;

	private static final Logger lOGGER = LogManager.getLogger(MyQuotesPage.class.getName());

	public MyQuotesPage(WebDriver driver) {
		super(driver);
	}

	public String getQuoteNumber() {

		lOGGER.info("Selecting random Quote from the list");
		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(quotes.size());
		System.out.println("Selected Random Quote is :------" + quotes.get(nextRandomNumberIndex).getText());
		return quotes.get(nextRandomNumberIndex).getText();
	}

	public void clickOnViewQuote() {

		lOGGER.info("Clicking on random Quote from the list");
		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(quotes.size());
		System.out.println("Selected Random Quote is :------" + quotes.get(nextRandomNumberIndex).getText());
		js.clickElement(quotes.get(nextRandomNumberIndex));
	}
}