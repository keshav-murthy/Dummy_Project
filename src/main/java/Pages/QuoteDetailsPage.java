package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class QuoteDetailsPage extends BasePage {

	@FindBy(xpath = "//div[@class='breadcrumbs']//span//strong")
	WebElement pageHeader;

	@FindBy(xpath = "//div[@class='quote-info']//h2")
	WebElement quoteID;

	@FindBy(xpath = "//p//strong//following-sibling::a")
	WebElement relatedTicket;

	@FindBy(xpath = "//p[@class='status']//following-sibling::p[1]")
	WebElement status;

	@FindBy(xpath = "//button[@title='Review & Pay']//span//span")
	WebElement approveAndPay;

	@FindBy(xpath = "//p//strong//span")
	WebElement grandTotal;

	private static final Logger lOGGER = LogManager.getLogger(QuoteDetailsPage.class.getName());

	public QuoteDetailsPage(WebDriver driver) {
		super(driver);
	}

	public String getQuoteID() {

		wait.forElementToBeVisible(quoteID);
		lOGGER.info("Fetching the Quote ID from the details page" + quoteID.getText());
		return quoteID.getText();
	}

	public String getRelatedTicketID() {

		wait.forElementToBeVisible(relatedTicket);
		lOGGER.info("Fetching the related Ticket ID from the details page" + relatedTicket.getText());
		return relatedTicket.getText();
	}

	public String getStatus() {

		wait.forElementToBeVisible(status);
		lOGGER.info("Fetching the status from the details page" + status.getText());
		return status.getText();
	}

	public String getGrandTotal() {

		wait.forElementToBeVisible(grandTotal);
		lOGGER.info("Fetching the Grand Total from the details page" + grandTotal.getText());
		return grandTotal.getText();
	}

	public void clickOnApproveAndPayButton() {

		wait.forElementToBeClickable(approveAndPay);
		js.clickElement(approveAndPay);
		lOGGER.info("Clicking on Approve and Pay button");
	}
}