package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyAssetsPage extends BasePage {

	protected static String actualResult;
	protected static Random r = new Random();
	protected static WebElement eachData;
	protected static List<WebElement> assetDataList = new ArrayList<WebElement>();
	protected static String locationData;

	@FindBy(xpath = "//h1//span")
	WebElement pageHeader;

	@FindBy(xpath = "//div[@class='top']//input[@value='Add Asset']")
	WebElement addAsset;

	@FindBy(xpath = "//div[@class='top']//input[@value='Add Contract']")
	WebElement addContract;

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

	@FindBy(xpath = "//table//thead//tr//th")
	List<WebElement> columnHeading;

	@FindBy(xpath = "//tbody//tr//td")
	List<WebElement> tableData;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//tbody//tr//td[1]//a")
	List<WebElement> assetSerialNumber;

	@FindBy(xpath = "//tbody//tr//td[7]")
	List<WebElement> OEMContract;

	@FindBy(xpath = "//tbody//tr//td[8]")
	List<WebElement> trueSupportContract;

	@FindBy(xpath = "//tbody//tr//td[9]//a")
	List<WebElement> location;

	@FindBy(xpath = "//label[text()='Last Address Shipped To: ']//following-sibling::span")
	WebElement locationDetails;

	@FindBy(xpath = "//tbody//a[1]")
	WebElement firstAsset;

	@FindBy(xpath = "//tbody//tr//td[@class='sorting_1']")
	List<WebElement> textSorting;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> dateSorting;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//th[text()='Subject']")
	WebElement subject;

	@FindBy(xpath = "//th//div[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//th//div[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//th//div[text()='Type']")
	WebElement type;

	@FindBy(xpath = "//th[text()='Created Date']")
	WebElement createdDate;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//ul[@itemtype='https://schema.org/BreadcrumbList']")
	WebElement header;

	@FindBy(xpath = "//tbody//td[1]//a")
	List<WebElement> assets;

	@FindBy(xpath = "//select[@name='assetsTable_length']")
	WebElement tableLengthDropDown;

	private static final Logger lOGGER = LogManager.getLogger(MyAssetsPage.class.getName());

	public MyAssetsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnAddAsset() {

		wait.forElementToBeVisible(addAsset);
		click(addAsset);
		lOGGER.info("Clicking on Add Asset button");

	}

	public void clickOnAddContract() {

		wait.forElementToBeVisible(addContract);
		click(addContract);
		lOGGER.info("Clicking on Add Contract button");
	}

	public void verifyAsset(String random) {

		wait.forPage();
		WebElement element = driver.findElement(By.xpath("//a[contains(text()," + "'" + random + "'" + ")]"));
		wait.forElementToBeVisible(element);
		js.clickElement(element);
	}

	public void clickOnTBDFilter() {

		wait.forElementToBeVisible(selectTBD);
		click(selectTBD);
		lOGGER.info("Clicking on TDB Filter button");
	}

	public void clickOnMC9090Filter() {

		wait.forElementToBeVisible(selectMC9090);
		click(selectMC9090);
		lOGGER.info("Clicking on MC9090 Filter button");
	}

	public void clickOnPDTFilter() {

		wait.forElementToBeVisible(selectPDT);
		click(selectPDT);
		lOGGER.info("Clicking on PDT Filter button");
	}

	public void enterSearchField(String searchSerialNumber) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchSerialNumber);
		lOGGER.info("Entering the required data in search field");
	}

	public void applyFilters() {

		pause(5000);
		wait.forElementToBeVisible(header);
		click(header);
		lOGGER.info("Clicking on Header of the page");

		wait.forElementToBeVisible(manufacturerFilters);
		Actions action = new Actions(driver);
		action.moveToElement(manufacturerFilters).click().build().perform();
		lOGGER.info("Clicking on Manufacturer Filters button");
		clickOnTBDFilter();

		wait.forElementToBeVisible(modelFilters);
		click(modelFilters);
		lOGGER.info("Clicking on Model Filters button");
		clickOnMC9090Filter();

		wait.forElementToBeVisible(typeFilters);
		click(typeFilters);
		lOGGER.info("Clicking on Type Filters button");
		clickOnPDTFilter();

		pause(5000);
		wait.forElementToBeVisible(tableBody);
		lOGGER.info("displaying details of table after applying filters :----" + tableBody.getText());
	}

	public void validSearchVerification(String searchSerialNumber) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchSerialNumber);
		lOGGER.info("Entering the required data in search field");
		try {
			actualResult = driver.findElement(By.xpath("//a[contains(text()," + "'" + searchSerialNumber + "'" + ")]"))
					.getText();
		} catch (StaleElementReferenceException e) {
			actualResult = driver.findElement(By.xpath("//a[contains(text()," + "'" + searchSerialNumber + "'" + ")]"))
					.getText();
		}
		String expectedResult = searchSerialNumber;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void sortingVerification() {

		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
		lOGGER.info("Sorting the Manufacturer column in Descending order");
		printTableContentOfText();

		wait.forElementToBeVisible(model);
		click(model);
		wait.forElementToBeVisible(model);
		click(model);
		lOGGER.info("Sorting the Model column in Descending order");
		printTableContentOfText();

		wait.forElementToBeVisible(type);
		click(type);
		wait.forElementToBeVisible(type);
		click(type);
		lOGGER.info("Sorting the type column in Descending order");
		printTableContentOfText();

		wait.forElementToBeVisible(createdDate);
		click(createdDate);
		lOGGER.info("Sorting the Created Date column in Ascending order");
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

	public void addAssetPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");
	}

	public void clickOnFirstAsset() {

		wait.forElementToBeVisible(firstAsset);
		click(firstAsset);
		lOGGER.info("Clicking on first Asset from the list");
	}

	public String selectRandomAsset() {

		String ignoredAssets = "12345 00001 Not Available";
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");

		wait.forPage(2000);
		int randomNumberIndex = r.nextInt(assets.size());
		String randomAsset = assets.get(randomNumberIndex).getText();
		System.out.println("The Asset serial no before ignoring is :------" + randomAsset);
		while ((ignoredAssets.contains(randomAsset)) == true) {
			randomNumberIndex = r.nextInt(assets.size());
			randomAsset = assets.get(randomNumberIndex).getText();
			System.out.println("The Asset serial no after ignoring is :------" + randomAsset);
		}

		return randomAsset;
	}

	public void blankColumnVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		for (int i = 0; i < tableRows.size(); i++) {
			for (int j = 0; j < columnHeading.size(); j++) {

				if ((j % 9) > 3) {
					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j) + "]"));

					if ((eachData.getText().isEmpty()) == true) {
						String serialNumber = assetSerialNumber.get(i).getText();
						String columnName = columnHeading.get((j) % columnHeading.size()).getText();
						System.out.println("One/Many column in this Asset " + serialNumber + " is blank ");
						System.out.println("The blank Column name is " + columnName);
					}
				}
			}
		}
		if ((eachData.getText().isEmpty()) == false)
			System.out.println("There are no blank columns in this Asset listing");
	}

	public void contractDetailsVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");
		wait.forPage();

		for (int i = 0; i < OEMContract.size(); i++) {
			String OEMContractData = OEMContract.get(i).getText();
			String trueSupportData = trueSupportContract.get(i).getText();
			if ((OEMContractData.equals("No") == false) || (trueSupportData.equals("No") == false)) {
				WebElement assetData = assetSerialNumber.get(i);
				assetDataList.add(assetData);
			}
		}
		if (assetDataList.size() == 0)
			System.out.println("There are no contracts associated with this asset");
		else {
			int randomNumberIndex = r.nextInt(assetDataList.size());
			wait.forPage();
			js.clickElement(assetDataList.get(randomNumberIndex));
			AssetDetailsPage assetdetail = new AssetDetailsPage(driver);
			assetdetail.getFirstContractName();
			assetDataList.clear();
		}
	}

	public void locationVerfication() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");
		wait.forPage();

		if (location.size() > 0) {
			int randomNumberIndex = r.nextInt(location.size());
			locationData = location.get(randomNumberIndex).getText();
			System.out.println("Selected location is ----------> " + locationData+"----->");
			js.clickElement(location.get(randomNumberIndex));
			AssetDetailsPage assetdetail = new AssetDetailsPage(driver);
			assetdetail.locationValidation(locationData);
//			System.out.println("Selected location is ----------2" + locInPage);

		} else
			System.out.println("There is no location mentioned for these list of Assets");
	}

	public void validSearchVerification() {

		String ignoredAssets = "Not Available";
		String randomRepairID;
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "50");

		int randomNumberIndex = r.nextInt(assetSerialNumber.size());
		wait.forElementToBeVisible(assetSerialNumber.get(randomNumberIndex));
		try {
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
		} catch (StaleElementReferenceException e) {
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
		}
		while ((ignoredAssets.contains(randomRepairID)) == true) {
			randomNumberIndex = r.nextInt(assetSerialNumber.size());
			randomRepairID = assetSerialNumber.get(randomNumberIndex).getText();
			System.out.println("The Asset after ignoring is :------" + randomRepairID);
		}
		System.out.println("Valid search element to be entered is  :------" + randomRepairID);

		wait.forElementToBeVisible(search);
		scrollToTop();
		sendKeys(search, randomRepairID);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(
				driver.findElement(By.xpath("//td//a[contains(text()," + "'" + randomRepairID + "'" + ")]")));
		String actualResult = driver
				.findElement(By.xpath("//td//a[contains(text()," + "'" + randomRepairID + "'" + ")]")).getText();
		String expectedResult = randomRepairID;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void invalidSearchVerification() {

		String n = genRandomString();
		wait.forElementToBeVisible(search);
		scrollToTop();
		System.out.println("Invalid search element to be entered is :------" + n);
		sendKeys(search, n);
		search.sendKeys(Keys.ENTER);
		lOGGER.info("Entering the required data in search field");
		try {
			wait.forElementToBeVisible(emptyTable);
			System.out.println(emptyTable.getText());
		} catch (StaleElementReferenceException e) {
			wait.forElementToBeVisible(emptyTable);
			System.out.println(emptyTable.getText());
		}
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}
}