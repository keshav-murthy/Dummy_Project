package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class CreateTicketPage extends BasePage {

	@FindBy(xpath = "//h1[@class='page-title']")
	WebElement pageHeader;

	@FindBy(xpath = "//input[@id='support_troubleshoot']")
	WebElement support;

	@FindBy(xpath = "//input[@id='device_repair']")
	WebElement repair;

	@FindBy(xpath = "//label[text()='Issue Title']//parent::div//div//input")
	WebElement issueTitle;

	@FindBy(xpath = "(//div[@class='input-box']//textarea)[2]")
	WebElement issueDescription;

	@FindBy(xpath = "//input[@class='file_attachment']")
	WebElement chooseFile;

	@FindBy(xpath = "//input[@class='input-text contactdetail_name']")
	WebElement contactName;

	@FindBy(xpath = "//input[@class='input-text contactdetail_email']")
	WebElement contactEmail;

	@FindBy(xpath = "//input[@class='input-text contactdetail_phone']")
	WebElement contactPhone;

	@FindBy(xpath = "//input[@type='select-one']")
	WebElement serialNumber;

	@FindBy(xpath = "//button[text()='Select']")
	WebElement select;

	@FindBy(xpath = "//input[@class='model']")
	WebElement model;

	@FindBy(xpath = "//input[@class='manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//select[@title='Issue']")
	WebElement productDropDown;

	@FindBy(xpath = "//select[@title='Type']")
	WebElement issueDropDown;

	@FindBy(xpath = "//button[text()='Send']")
	WebElement send;

	@FindBy(xpath = "(//a[contains(text(),'Go to dashboard')])[2]")
	WebElement dashboard;

	private static final Logger lOGGER = LogManager.getLogger(CreateTicketPage.class.getName());

	public CreateTicketPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnSupportTicketRequest() {

		wait.forElementToBeVisible(support);
		click(support);
		lOGGER.info("clicking on Support button");
	}

	public void clickOnRepairTicketRequest() {

		wait.forElementToBeVisible(repair);
		click(repair);
		lOGGER.info("clicking on Repair button");
	}

	public void addingTicketProcedure(String serialNo) {

		clickOnSupportTicketRequest();

		wait.forElementToBeVisible(issueTitle);
		sendKeys(issueTitle, "Issues related to the printer");
		lOGGER.info("Enter Issue Title ");

//		wait.forElementToBeVisible(contactName);
//		sendKeys(contactName, "Demo");
//		lOGGER.info("Enter Contact Name");
//
//		wait.forElementToBeVisible(contactEmail);
//		sendKeys(contactEmail, "Demo.Automation@Gmail.com");
//		lOGGER.info("Enter Contact Email");
//
//		wait.forElementToBeVisible(contactPhone);
//		sendKeys(contactPhone, "9876543210");
//		lOGGER.info("Enter Contact Phone");

		wait.forElementToBeVisible(serialNumber);
		sendKeys(serialNumber, serialNo);
		serialNumber.sendKeys(Keys.ENTER);
		lOGGER.info("Enter already created asset serial number");

		wait.forElementToBeVisible(select);
		click(select);
		lOGGER.info("clicking on Select button");

		wait.forElementToBeVisible(model);
		sendKeys(model, "Automation Testing");
		lOGGER.info("Enter Model");

		wait.forElementToBeVisible(manufacturer);
		sendKeys(manufacturer, "Panasonic");
		lOGGER.info("Enter Manufacturer");

		wait.forElementToBeVisible(productDropDown);
		dropDownMethod(productDropDown, "VisibleText", "Printer");
		lOGGER.info("Select Product from the dropdown");

		wait.forElementToBeVisible(issueDropDown);
		dropDownMethod(issueDropDown, "VisibleText", "Wifi Failure");
		lOGGER.info("Select Issue from the dropdown");

		wait.forElementToBeVisible(issueDescription);
		sendKeys(issueDescription,
				"My printer is unable to connect via Wifi and this issue is creating lot of problems for remote operation");
		lOGGER.info("Enter Details Of Issues");

		wait.forElementToBeVisible(send);
		click(send);
		lOGGER.info("clicking on send button");

		wait.forElementToBeVisible(dashboard);
		click(dashboard);
		lOGGER.info("clicking on dashboard button");
	}

	public void createTicketPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of Add Ticket page");
	}
}
