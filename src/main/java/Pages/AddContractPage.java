package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class AddContractPage extends BasePage {

	@FindBy(xpath = "//li[@class='item add contract']//strong")
	WebElement pageHeader;

	@FindBy(xpath = "//input[@id='support_troubleshoot']")
	WebElement support;

	@FindBy(xpath = "//input[@id='device_repair']")
	WebElement repair;

	@FindBy(xpath = "//div[@class='input-box']//textarea")
	WebElement contentDescription;

	@FindBy(xpath = "//input[@class='input-text contactdetail_name']")
	WebElement contactName;

	@FindBy(xpath = "//input[@class='input-text contactdetail_email']")
	WebElement contactEmail;

	@FindBy(xpath = "//input[@class='input-text contactdetail_phone']")
	WebElement contactPhone;

	@FindBy(xpath = "//input[@type='select-one']")
	WebElement serialNumber;

	@FindBy(xpath = "//div[@class='selectize-dropdown-content']//div[1]")
	WebElement dropDownSelect;

	@FindBy(xpath = "//button[contains(text(),'Select')]")
	WebElement select;

	@FindBy(xpath = "//span[contains(text(),'+')]")
	WebElement plus;

	@FindBy(xpath = "(//select[@name='contracttype'])[1]")
	WebElement contractType;

	@FindBy(xpath = "//button[contains(text(),'Send')]")
	WebElement send;

	@FindBy(xpath = "(//a[contains(text(),'Go to dashboard')])[2]")
	WebElement dashboard;

	private static final Logger lOGGER = LogManager.getLogger(AddContractPage.class.getName());

	public AddContractPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnSupportContractRequest() {

		wait.forElementToBeVisible(support);
		click(support);
		lOGGER.info("clicking on Support button");
	}

	public void clickOnRepairContractRequest() {

		wait.forElementToBeVisible(repair);
		click(repair);
		lOGGER.info("clicking on Repair button");
	}

	public void addingContractProcedure(String random) {

		clickOnSupportContractRequest();
		wait.forElementToBeVisible(contentDescription);
		sendKeys(contentDescription, "Just a demo contract for testing POV");
		lOGGER.info("Enter Enter Details Of Contract");

		wait.forElementToBeVisible(contactName);
		sendKeys(contactName, "Demo");
		lOGGER.info("Enter Contact Name");

		wait.forElementToBeVisible(contactEmail);
		sendKeys(contactEmail, "Demo.Automation@Gmail.com");
		lOGGER.info("Enter Contact Email");

		wait.forElementToBeVisible(contactPhone);
		sendKeys(contactPhone, "9876543210");
		lOGGER.info("Enter Contact Phone");

		wait.forElementToBeVisible(serialNumber);
		sendKeys(serialNumber, random);
		lOGGER.info("Enter Serial Number");

		wait.forElementToBeVisible(dropDownSelect);
		click(dropDownSelect);
		lOGGER.info("clicking on asset serial from drop down select button");

		wait.forElementToBeVisible(select);
		click(select);
		lOGGER.info("clicking on Select button");

		wait.forElementToBeVisible(contractType);
		dropDownMethod(contractType, "index", "1");
		lOGGER.info("clicking on contract type drop down");

		wait.forElementToBeVisible(send);
		click(send);
		lOGGER.info("clicking on Send button");

//		wait.forElementToBeVisible(dashboard);
//		click(dashboard);
//		lOGGER.info("clicking on dashboard button");
	}

	public void addContractPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of Add Contract page");
	}
}
