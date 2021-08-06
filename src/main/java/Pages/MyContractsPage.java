package Pages;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyContractsPage extends BasePage {

	protected static Random r = new Random();

	@FindBy(xpath = "//h1//span")
	WebElement pageHeader;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//input[@value='Add Contract']")
	WebElement addContract;

	@FindBy(xpath = "//tbody//td[1]//a")
	List<WebElement> contracts;

	@FindBy(xpath = "//tbody//tr//td[@class='sorting_1']")
	List<WebElement> textSorting;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> dateSorting;

	@FindBy(xpath = "//th[text()='Ticket ID']")
	WebElement ticketID;

	@FindBy(xpath = "//th[text()='Serial Number']")
	WebElement serialNumber;

	@FindBy(xpath = "//th[text()='Title']")
	WebElement title;

	@FindBy(xpath = "//div[text()='Contract Type']")
	WebElement contractType;

	@FindBy(xpath = "//th[text()='Contract Item']")
	WebElement contractItem;

	@FindBy(xpath = "//th[text()='Related Asset']")
	WebElement relatedAsset;

	@FindBy(xpath = "//div[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Created Date']")
	WebElement createdDate;

	@FindBy(xpath = "//th[text()='Expiring Date']")
	WebElement expiryDate;

	@FindBy(xpath = "//div[text()='Manufacturer']//ancestor::th//div[@class='sorting-block']")
	WebElement manufacturerFilters;

	@FindBy(xpath = "//div[text()='Model']//ancestor::th//div[@class='sorting-block']")
	WebElement modelFilters;

	@FindBy(xpath = "//div[text()='Type']//ancestor::th//div[@class='sorting-block']")
	WebElement typeFilters;

	@FindBy(xpath = "//input[@value='TBD']")
	WebElement selectTBD;

	@FindBy(xpath = "//input[@value='MC9090']")
	WebElement selectMC9090;

	@FindBy(xpath = "//input[@value='PDT']")
	WebElement selectPDT;

	@FindBy(xpath = "//tbody")
	WebElement tableBody;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//div[@class='dataTables_info']")
	WebElement dataTableInfo;

	@FindBy(xpath = "//a[@class='paginate_button current']")
	WebElement currentPage;

	@FindBy(xpath = "//span[@class='ellipsis']//following-sibling::a")
	WebElement lastPage;

	@FindBy(xpath = "//a[contains(text(),'>')]")
	WebElement nextArrow;

	@FindBy(xpath = "//ul[@itemtype='https://schema.org/BreadcrumbList']")
	WebElement header;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	private static final Logger lOGGER = LogManager.getLogger(MyContractsPage.class.getName());

	public MyContractsPage(WebDriver driver) {
		super(driver);
	}

	public void myContractsPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Contracts page");

		wait.forElementToBeVisible(tableBody);
		lOGGER.info("displaying details of page after entering into My Contracts page :----");
		lOGGER.info(tableBody.getText());
	}

	public void verifyValidSearchedSerialNumber(String searchSerialNumber) {

		String actualResult = driver
				.findElement(By.xpath("//a[contains(text()," + "'" + searchSerialNumber + "'" + ")]")).getText();
		String expectedResult = searchSerialNumber;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void verifyInvalidSearchedSerialNumber(String searchSerialNumber) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchSerialNumber);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(emptyTable);
		System.out.println(emptyTable.getText());
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}

	public void sortingVerification() {

		wait.forElementToBeVisible(contractType);
		click(contractType);
		wait.forElementToBeVisible(contractType);
		click(contractType);
		lOGGER.info("Sorting the Contract Type column in Descending order");
		printTableContentOfText();

		wait.forElementToBeVisible(contractItem);
		click(contractItem);
		wait.forElementToBeVisible(contractItem);
		click(contractItem);
		lOGGER.info("Sorting the Contract Item column in Descending order");
		printTableContentOfDate();

		wait.forElementToBeVisible(relatedAsset);
		click(relatedAsset);
		wait.forElementToBeVisible(relatedAsset);
		click(relatedAsset);
		lOGGER.info("Sorting the Related Asset column in Descending order");
		printTableContentOfDate();

		wait.forElementToBeVisible(expiryDate);
		click(expiryDate);
		lOGGER.info("Sorting the Expiry Date column in Ascending order");
		printTableContentOfDate();

	}

	public void printTableContentOfText() {

		List<WebElement> contentText = textSorting;
		for (int i = 0; i < contentText.size(); i++) {
			pause(2000);
			System.out.println(
					"displaying details of table after sorting of column :----" + contentText.get(i).getText());
		}
	}

	public void printTableContentOfDate() {

		List<WebElement> contentDate = dateSorting;
		for (int i = 0; i < contentDate.size(); i++) {
			pause(2000);
			System.out.println(
					"displaying details of table after sorting of column :----" + contentDate.get(i).getText());
		}
	}

	public void clickOnAddContract() {

		wait.forElementToBeVisible(addContract);
		click(addContract);
		lOGGER.info("Clicking on Add Contract button");
	}

	public void validSearchVerification() {

		String randomContractID;
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		int randomNumberIndex = r.nextInt(contracts.size());
		wait.forElementToBeVisible(contracts.get(randomNumberIndex));
		try {
			randomContractID = contracts.get(randomNumberIndex).getText();
		} catch (StaleElementReferenceException e) {
			randomContractID = contracts.get(randomNumberIndex).getText();
		}
		System.out.println("Valid search element to be entered is  :------" + randomContractID);

		wait.forElementToBeVisible(search);
		sendKeys(search, randomContractID);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(
				driver.findElement(By.xpath("//td//a[contains(text()," + "'" + randomContractID + "'" + ")]")));
		String actualResult = driver
				.findElement(By.xpath("//td//a[contains(text()," + "'" + randomContractID + "'" + ")]")).getText();
		String expectedResult = randomContractID;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void invalidSearchVerification() {

		String n = Integer.toString(r.nextInt(10000));
		wait.forElementToBeVisible(search);
		System.out.println("Invalid search element to be entered is :------" + n);
		sendKeys(search, n);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(emptyTable);
		System.out.println(emptyTable.getText());
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}
}