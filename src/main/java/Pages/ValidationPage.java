package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ValidationPage extends BasePage {

	// ----> NetSuite Page Global Variables
	protected static String netsuitesDateCreated;
	protected static String netsuitesDateClosed;
	protected static String netsuitesStatus;
	protected static String netsuitesSerialNumber;
	protected static String netsuitesType;
	protected static String netsuitesIssueDescription;
	protected static String netsuitesRepairID;
	protected static String netsuitesAssetSerialNumber;
	protected static String netsuitesCommentMessage;

	// ----> Barcodes Page Global Variables
	protected static String barcodesTicketID;
	protected static String barcodesDateCreated;
	protected static String barcodesDateClosed;
	protected static String barcodesStatus;
	protected static String barcodesSerialNumber;
	protected static String barcodesManufacturer;
	protected static String barcodesModel;
	protected static String barcodesType;
	protected static String barcodesIssueDescription;
	protected static String barcodesRepairID;

	// ----> NetSuite Page Locators
	@FindBy(xpath = "//input[@id='_searchstring']")
	WebElement search;

	@FindBy(xpath = "(//span[contains(text(),'MyAccount Testing')])[1]")
	WebElement dropdownSelect;

	@FindBy(xpath = "//span[@id='startdate_fs_lbl_uir_label']//following-sibling::span")
	WebElement nsDateCreated;

	@FindBy(xpath = "//span[@id='enddate_fs_lbl_uir_label']//following-sibling::span")
	WebElement dateClosed;

	@FindBy(xpath = "//span[@id='status_fs_lbl_uir_label']//following-sibling::span")
	WebElement nsStatus;

	@FindBy(xpath = "//span[@id='custevent_serial_number_1_fs_lbl_uir_label']//following-sibling::span")
	WebElement nsSerialNumber;

	@FindBy(xpath = "//div[text()='Manufacturer']")
	WebElement nsManufacturer;

	@FindBy(xpath = "//div[text()='Model']")
	WebElement nsModel;

	@FindBy(xpath = "//span[@id='custevent_problem_item_category1_fs_lbl_uir_label']//following-sibling::span")
	WebElement nsType;

	@FindBy(xpath = "//span[@id='custevent_problem_description_1_fs_lbl_uir_label']//following-sibling::span")
	WebElement nsIssueDescription;

	@FindBy(xpath = "//span[@id='custevent_case_repair_id_fs_lbl_uir_label']//following-sibling::span")
	WebElement repairID;

	@FindBy(xpath = "//span[@id='name_fs_lbl_uir_label']//following-sibling::span")
	WebElement name;

	@FindBy(xpath = "(//td[@id='div__lab3']//div[contains(text(),'Date')])[1]")
	WebElement dateColumn;

	@FindBy(xpath = "(//div[contains(text(),'Message')]//ancestor::div[@id='messages__div']//tbody//td)[5]")
	WebElement message;

	// ------>Barcodes Page Locators

	@FindBy(xpath = "//h2")
	WebElement bcTicketID;

	@FindBy(xpath = "//p[@class='last-update']//span//following-sibling::span")
	WebElement bcLastUpdated;

	@FindBy(xpath = "//label[text()='Date Created']//parent::div//p")
	WebElement bcDateCreated;

	@FindBy(xpath = "//label[text()='Status']//parent::div//p")
	WebElement bcStatus;

	@FindBy(xpath = "//label[text()='Serial Number']//parent::div//p")
	WebElement bcSerialNumber;

	@FindBy(xpath = "//label[text()='Manufacturer']//parent::div//p")
	WebElement bcManufacturer;

	@FindBy(xpath = "//label[text()='Model']//parent::div//p")
	WebElement bcModel;

	@FindBy(xpath = "//label[text()='Type']//parent::div//p")
	WebElement bcType;

	@FindBy(xpath = "//label[text()='Issue Description:']//parent::div//p")
	WebElement bcIssueDescription;

	private static final Logger lOGGER = LogManager.getLogger(ValidationPage.class.getName());

	public ValidationPage(WebDriver driver) {
		super(driver);
	}

	public void getNetsuitesDetails() {

		wait.forElementToBeVisible(nsDateCreated);
		netsuitesDateCreated = nsDateCreated.getText();
		lOGGER.info("Fetching created date detail " + netsuitesDateCreated);

		wait.forElementToBeVisible(dateClosed);
		netsuitesDateClosed = dateClosed.getText();
		lOGGER.info("Fetching date closed detail " + netsuitesDateClosed);
	}

	public void getNetsuiteStatusDetails() {

		wait.forElementToBeVisible(nsStatus);
		netsuitesStatus = nsStatus.getText();
		lOGGER.info("Fetching status detail " + netsuitesStatus);
	}

	public void remaiming() {

		wait.forElementToBeVisible(nsType);
		netsuitesType = nsType.getText();
		lOGGER.info("Fetching type detail " + netsuitesType);

		wait.forElementToBeVisible(nsIssueDescription);
		netsuitesIssueDescription = nsIssueDescription.getText();
		lOGGER.info("Fetching issue description detail " + netsuitesIssueDescription);
	}

	public void getNetsuiteRepairID() {

		wait.forElementToBeVisible(repairID);
		netsuitesRepairID = repairID.getText();
		lOGGER.info("Fetching repair ID detail " + netsuitesRepairID);
	}

	public void getNetsuiteSerialNumber() {

		wait.forElementToBeVisible(nsSerialNumber);
		netsuitesSerialNumber = nsSerialNumber.getText();
		lOGGER.info("Fetching serial number detail " + netsuitesSerialNumber);
	}

	public void getNetsuiteAssetSerialNumber() {

		wait.forElementToBeVisible(name);
		netsuitesAssetSerialNumber = name.getText();
		lOGGER.info("Fetching serial number detail " + netsuitesAssetSerialNumber);
	}

	public void getNetsuiteMessage() {

		scrollToElementView(dateColumn);
		wait.forElementToBeVisible(dateColumn);
		js.clickElement(dateColumn);
		wait.forElementToBeVisible(dateColumn);
		js.clickElement(dateColumn);
		pause(2000);
		wait.forElementToBeVisible(message);
		netsuitesCommentMessage = message.getText();
		lOGGER.info("Fetching message details of comments " + netsuitesCommentMessage);
	}

	public void getBarcodesDetails() {

		wait.forElementToBeVisible(bcTicketID);
		barcodesTicketID = bcTicketID.getText();
		lOGGER.info("Fetching ticket ID detail " + barcodesTicketID);

		wait.forElementToBeVisible(bcDateCreated);
		barcodesDateCreated = bcDateCreated.getText();
		lOGGER.info("Fetching created date detail " + barcodesDateCreated);

		wait.forElementToBeVisible(bcLastUpdated);
		barcodesDateClosed = bcLastUpdated.getText();
		lOGGER.info("Fetching last updated detail " + barcodesDateClosed);
	}

	public void getBarcodesStatusDetails() {

		wait.forElementToBeVisible(bcStatus);
		barcodesStatus = bcStatus.getText();
		lOGGER.info("Fetching status detail " + barcodesStatus);
	}

	public void remaining() {

		wait.forElementToBeVisible(bcSerialNumber);
		barcodesSerialNumber = bcSerialNumber.getText();
		lOGGER.info("Fetching serial number detail " + barcodesSerialNumber);

		wait.forElementToBeVisible(bcManufacturer);
		barcodesManufacturer = bcManufacturer.getText();
		lOGGER.info("Fetching manufacturer detail " + barcodesManufacturer);

		wait.forElementToBeVisible(bcModel);
		barcodesModel = bcModel.getText();
		lOGGER.info("Fetching model detail " + barcodesModel);

		wait.forElementToBeVisible(bcType);
		barcodesType = bcType.getText();
		lOGGER.info("Fetching type detail " + barcodesType);

		wait.forElementToBeVisible(bcIssueDescription);
		barcodesIssueDescription = bcIssueDescription.getText();
		lOGGER.info("Fetching issue description detail " + barcodesIssueDescription);
	}

	public void validation() {

		Assert.assertEquals(barcodesTicketID, "Ticket: " + netsuitesRepairID);
		// Assert.assertEquals(barcodesDateClosed+"10:46 am", netsuitesDateClosed);
		// Assert.assertEquals(barcodesDateCreated, netsuitesDateCreated);
		Assert.assertEquals(barcodesStatus, netsuitesStatus);
		Assert.assertEquals(barcodesSerialNumber, netsuitesSerialNumber);
		Assert.assertEquals(barcodesType, netsuitesType);
		Assert.assertEquals(barcodesIssueDescription, netsuitesIssueDescription);
	}

	public void statusValidation() {

		Assert.assertEquals(barcodesStatus, netsuitesStatus);
	}

	public void createdTicketValidation(String TicketID) {

		Assert.assertEquals(TicketID, netsuitesRepairID);
	}

	public void serialNumberValidation(String serialNumber) {

		Assert.assertEquals(serialNumber, netsuitesSerialNumber);
	}

	public void assetSerialNumberValidation(String assetSerialNumber) {

		Assert.assertEquals(assetSerialNumber, netsuitesAssetSerialNumber);
	}

	public void netsuiteCommentMessageValidation(String message) {

		Assert.assertEquals(message, netsuitesCommentMessage);
	}

	public void barcodesCommentMessageValidation(String barcodesComments, String netsuiteComments) {

		Assert.assertEquals(barcodesComments, netsuiteComments);
	}

	public void contractDetailsValidation(String barcodesContract, String netsuiteContract) {

		Assert.assertEquals(barcodesContract, netsuiteContract);
	}
}
