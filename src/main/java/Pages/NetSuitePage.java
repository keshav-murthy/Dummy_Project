package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;
import commons.Timeouts;

public class NetSuitePage extends BasePage {

	@FindBy(xpath = "//input[@id='_searchstring']")
	WebElement search;

	@FindBy(xpath = "//a[contains(text(),'Serial Number Asset')]")
	WebElement assetDropdown;

	@FindBy(xpath = "//a[@id='noResultsFound']")
	WebElement noResult;

	@FindBy(xpath = "(//span[contains(text(),'MyAccount Testing')])[1]")
	WebElement dropdownSelect;

	@FindBy(xpath = "//input[@id='edit']")
	WebElement edit;

	@FindBy(xpath = "(//img[@class='checkboximage'])[22]")
	WebElement newMessage;

	@FindBy(xpath = "//textarea[@id='incomingmessage']")
	WebElement message;

	@FindBy(xpath = "//input[@value='Save']")
	WebElement save;

	@FindBy(xpath = "//span[@id='startdate_fs_lbl_uir_label']//following-sibling::span")
	WebElement dateCreated;

	@FindBy(xpath = "//span[@id='enddate_fs_lbl_uir_label']//following-sibling::span")
	WebElement dateClosed;

	@FindBy(xpath = "//span[@id='status_fs_lbl_uir_label']//following-sibling::span")
	WebElement status;

	@FindBy(xpath = "//span[@id='custevent_serial_number_1_fs_lbl_uir_label']//following-sibling::span")
	WebElement serialNumber;

	@FindBy(xpath = "//div[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//div[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//span[@id='custevent_problem_item_category1_fs_lbl_uir_label']//following-sibling::span")
	WebElement type;

	@FindBy(xpath = "//span[@id='custevent_problem_description_1_fs_lbl_uir_label']//following-sibling::span")
	WebElement issueDescription;

	@FindBy(xpath = "//span[@id='custevent_case_repair_id_fs_lbl_uir_label']//following-sibling::span")
	WebElement repairID;

	@FindBy(xpath = "//span[@id='name_fs_lbl_uir_label']//following-sibling::span")
	WebElement name;

	@FindBy(xpath = "(//a[text()='Service Contract']//ancestor::div[@class='uir-field-wrapper']//span//span//a)[2]")
	WebElement firstContractName;

	@FindBy(xpath = "(//a[text()='Service Contract']//ancestor::div[@class='uir-field-wrapper']//span//span//a)[3]")
	WebElement secondContractName;

	private static final Logger lOGGER = LogManager.getLogger(NetSuitePage.class.getName());

	public NetSuitePage(WebDriver driver) {
		super(driver);
	}

	public void displayNetsuiteDetails(String searchfield) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchfield);
		lOGGER.info("Entering the search field");

		wait.forElementToBeVisible(dropdownSelect);
		click(dropdownSelect);
		lOGGER.info("Selecting from the dropdown Select");
	}

	public void displayNewNetsuiteDetails(String searchfield) {

		pause(Timeouts.WAITFORDATABASEUPLOAD);
		wait.forElementToBeVisible(search);
		sendKeys(search, searchfield);
		lOGGER.info("Entering the search field");

		wait.forElementToBeVisible(dropdownSelect);
		click(dropdownSelect);
		lOGGER.info("Selecting from the dropdown Select");
	}

	public void getDetails() {

		wait.forElementToBeVisible(dateCreated);
		String netsuitesDateCreated = dateCreated.getText();
		lOGGER.info("Fetching created date detail");

		wait.forElementToBeVisible(dateClosed);
		String netsuitesDateClosed = dateClosed.getText();
		lOGGER.info("Fetching date closed detail");

		wait.forElementToBeVisible(status);
		String netsuitesStatus = status.getText();
		lOGGER.info("Fetching status detail");

		wait.forElementToBeVisible(serialNumber);
		String netsuitesSerialNumber = serialNumber.getText();
		lOGGER.info("Fetching serial number detail");

		wait.forElementToBeVisible(type);
		String netsuitesType = type.getText();
		lOGGER.info("Fetching type detail");

		wait.forElementToBeVisible(issueDescription);
		String netsuitesIssueDescription = issueDescription.getText();
		lOGGER.info("Fetching issue description detail");

		wait.forElementToBeVisible(repairID);
		String netsuitesRepairID = repairID.getText();
		lOGGER.info("Fetching repair ID detail");
	}

	public void displayNetsuiteAssetDetails(String searchfield) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchfield);
		lOGGER.info("Entering the search field");

		wait.forElementToBeVisible(assetDropdown);
		click(assetDropdown);
		lOGGER.info("Selecting the newly created Asset from the dropdown select");
	}

	public void displayNewAssetDetails(String searchfield) {

		pause(Timeouts.WAITFORDATABASEUPLOAD);
		wait.forElementToBeVisible(search);
		sendKeys(search, searchfield);
		lOGGER.info("Entering the search field");

		wait.forElementToBeVisible(assetDropdown);
		click(assetDropdown);
		lOGGER.info("Selecting the newly created Asset from the dropdown select");
	}

	public void enterNetsuiteComment(String netsuiteComment) {

		wait.forElementToBeVisible(edit);
		click(edit);
		lOGGER.info("Clicking on Edit button to edit the ticket details");

		wait.forElementToBeVisible(newMessage);
		click(newMessage);
		lOGGER.info("Clicking on New Message checkbox to enter new message");

		wait.forElementToBeVisible(message);
		sendKeys(message, netsuiteComment);
		lOGGER.info("Entering the comments in the message text area");

		scrollToTop();

		wait.forElementToBeVisible(save);
		click(save);
		lOGGER.info("Clicking on Save button to save the entered comments");

		clickOnOKAlert();
	}

	public String getFirstContractName() {

		wait.forPage(2000);
		try {
			lOGGER.info("Clicking on name of first contract from the list");
			return firstContractName.getText();
		} catch (NoSuchElementException e) {
			System.out.println("No contract is associated with this asset.");
			return "No contract is associated with this asset.";
		}
	}

	public String getSecondContractName() {

		wait.forElementToBeVisible(secondContractName);
		lOGGER.info("Clicking on name of second contract from the list");
		return secondContractName.getText();
	}
}