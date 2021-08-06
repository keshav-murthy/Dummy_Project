package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class MyAssetsAndServicesPage extends BasePage {

	String pageTitle = driver.getTitle();

	@FindBy(xpath = "//button[@name='agree']")
	WebElement agreeButton;

	@FindBy(xpath = "//p[@class='rma-link report']//span[contains(text(),'Report')]")
	WebElement reportDashboard;

	@FindBy(xpath = "//p[@class='rma-link repair']//span[contains(text(),'My')]")
	WebElement myTickets;

	@FindBy(xpath = "//p[@class='rma-link assets']//span[contains(text(),'My')]")
	WebElement myAssets;

	@FindBy(xpath = "//p[@class='rma-link contracts']//span[contains(text(),'My')]")
	WebElement myContracts;

	@FindBy(xpath = "//p[@class='rma-link quotes']//span[contains(text(),'My')]")
	WebElement myQuotes;

	@FindBy(xpath = "//p[@class='rma-link tickets']//span[contains(text(),'Create')]")
	WebElement createTicket;

	private static final Logger lOGGER = LogManager.getLogger(MyAssetsAndServicesPage.class.getName());

	public MyAssetsAndServicesPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnReportDashboard() {

		wait.forElementToBeVisible(reportDashboard);
		reportDashboard.click();
		lOGGER.info("Clicking on Report Dashboard Page Button");
	}

	public void clickOnMyTickets() {

		wait.forElementToBeVisible(myTickets);
		js.clickElement(myTickets);
		lOGGER.info("Clicking on My Tickets Page Button");
	}

	public void clickOnMyAssets() {

		if (pageTitle.contains("To access the Assets & Services portal")) {

			wait.forElementToBeVisible(agreeButton);
			js.clickElement(agreeButton);
			lOGGER.info("clicked on terms & conditions Agree button");
		}

		wait.forElementToBeVisible(myAssets);
		js.clickElement(myAssets);
		lOGGER.info("Clicking on My Assets Page Button");
	}

	public void clickOnMyContracts() {

		wait.forElementToBeVisible(myContracts);
		js.clickElement(myContracts);
		lOGGER.info("Clicking on My Contracts Page Button");
	}

	public void clickOnMyQuotes() {

		wait.forElementToBeVisible(myQuotes);
		myQuotes.click();
		lOGGER.info("Clicking on My Quotes Page Button");
	}

	public void clickOnCreateTickets() {

		wait.forElementToBeVisible(createTicket);
		createTicket.click();
		lOGGER.info("Clicking on Create Ticket Page Button");
	}
}