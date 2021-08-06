package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class TicketDetailsPage extends BasePage {

	@FindBy(xpath = "//li[@class='item Ticket Details']//strong")
	WebElement ticketDetailsHeading;

	@FindBy(xpath = "//h2")
	WebElement ticketID;

	@FindBy(xpath = "//p[@class='last-update']//span//following-sibling::span")
	WebElement lastUpdated;

	@FindBy(xpath = "//label[text()='Date Created']//parent::div//p")
	WebElement dateCreated;

	@FindBy(xpath = "//label[text()='Status']//parent::div//p")
	WebElement status;

	@FindBy(xpath = "//label[text()='Serial Number']//parent::div//p")
	WebElement serialNumber;

	@FindBy(xpath = "//label[text()='Manufacturer']//parent::div//p")
	WebElement manufacturer;

	@FindBy(xpath = "//span//strong//label[text()='Model']//parent::div//p")
	WebElement model;

	@FindBy(xpath = "//label[text()='Type']//parent::div//p")
	WebElement type;

	@FindBy(xpath = "//label[text()='Issue Description:']//parent::div//p")
	WebElement issueDescription;

	@FindBy(xpath = "//textarea[@id='message']")
	WebElement commentSection;

	@FindBy(xpath = "//span[text()='Send']")
	WebElement send;

	@FindBy(xpath = "(//div[@class='ticket-message'])[1]")
	WebElement ticketMessage;

	private static final Logger lOGGER = LogManager.getLogger(TicketDetailsPage.class.getName());

	public TicketDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void ticketDetailsPageVerification(String expected) {

		wait.forElementToBeVisible(ticketDetailsHeading);
		String actual = ticketDetailsHeading.getText();
		try {
			Assert.assertEquals(actual, expected);
			lOGGER.info("Verifying Page Heading of Tickets details page");
		} catch (java.lang.AssertionError e) {
			System.out.println("No tickets created yet for this asset");
		}
	}

	public String getTicketDetails() {

		wait.forElementToBeVisible(ticketID);
		lOGGER.info("Fetching ticket ID detail");
		String s = ticketID.getText();
		System.out.println(s);
		return s.substring(s.lastIndexOf(":") + 2);
	}

	public String getCreationDateDetails() {

		wait.forElementToBeVisible(dateCreated);
		String barcodesDateCreated = dateCreated.getText();
		lOGGER.info("Fetching created date detail");
		return barcodesDateCreated;
	}

	public String getLastUpdatedDetails() {

		wait.forElementToBeVisible(lastUpdated);
		String barcodesDateClosed = lastUpdated.getText();
		lOGGER.info("Fetching last updated detail");
		return barcodesDateClosed;
	}

	public String getStatusDetails() {

		wait.forElementToBeVisible(status);
		String barcodesStatus = status.getText();
		lOGGER.info("Fetching status detail");
		return barcodesStatus;
	}

	public String getSerilNumberDetails() {

		wait.forElementToBeVisible(serialNumber);
		String barcodesSerialNumber = serialNumber.getText();
		lOGGER.info("Fetching serial number detail");
		return barcodesSerialNumber;
	}

	public String getManufacturerDetails() {

		wait.forElementToBeVisible(manufacturer);
		String barcodesManufacturer = manufacturer.getText();
		lOGGER.info("Fetching manufacturer detail");
		return barcodesManufacturer;
	}

	public String getModelDetails() {

		wait.forElementToBeVisible(model);
		String barcodesModel = model.getText();
		lOGGER.info("Fetching model detail");
		return barcodesModel;
	}

	public String getTypeDetails() {

		wait.forElementToBeVisible(type);
		String barcodesType = type.getText();
		lOGGER.info("Fetching type detail");
		return barcodesType;
	}

	public String getIssuesDetails() {

		wait.forElementToBeVisible(issueDescription);
		String barcodesIssueDescription = issueDescription.getText();
		lOGGER.info("Fetching issue description detail");
		return barcodesIssueDescription;
	}

	public String getDetails() {

		return getIssuesDetails();
	}

	public void enterComments(String comments) {

		wait.forElementToBeVisible(commentSection);
		sendKeys(commentSection, comments);
		lOGGER.info("Entering the comments for the ticket");

		wait.forElementToBeVisible(send);
		click(send);
		lOGGER.info("Clicking on Send button");
	}

	public String getTicketMessageDetails() {

		wait.forElementToBeVisible(ticketMessage);
		lOGGER.info("Fetching ticket Message detail");
		String s = ticketMessage.getText();
		System.out.println(s);
		return s.substring(s.lastIndexOf(":") + 2);
	}
}
