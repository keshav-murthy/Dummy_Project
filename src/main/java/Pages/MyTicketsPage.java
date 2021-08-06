package Pages;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MyTicketsPage extends BasePage {

	protected static Random r = new Random();
	protected static String actualResult;
	protected static WebElement eachData;
	protected static int k = 2;
	Xls_Reader reader = new Xls_Reader(
			System.getProperty("user.dir") + File.separator + "src/test/resources/BlankData.xlsx");
	String sheetName = "MyTickets";
	XSSFWorkbook workbook = null;

	@FindBy(xpath = "//h1//span")
	WebElement pageHeader;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//input[@value='Create Ticket']")
	WebElement createTicket;

	@FindBy(xpath = "//tbody//tr//td[@class='sorting_1']")
	List<WebElement> textSorting;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> dateSorting;

	@FindBy(xpath = "//th[text()='Ticket ID']")
	WebElement ticketID;

	@FindBy(xpath = "//th[text()='Serial Number']")
	WebElement serialNumber;

	@FindBy(xpath = "//tbody//a[1]")
	WebElement firstTicket;

	@FindBy(xpath = "//th[text()='Title']")
	WebElement title;

	@FindBy(xpath = "//div[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//div[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//div[text()='Issue Type']")
	WebElement type;

	@FindBy(xpath = "//div[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Created Date']")
	WebElement createdDate;

	@FindBy(xpath = "//th[text()='Last Updated']")
	WebElement lastUpdated;

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

	@FindBy(xpath = "//table//thead//tr//th")
	List<WebElement> columnHeading;

	@FindBy(xpath = "//tbody//tr//td")
	List<WebElement> tableData;

	@FindBy(xpath = "//tbody//tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> ticketIDData;

	@FindBy(xpath = "//tbody//tr//td[4]")
	List<WebElement> manufacturerData;

	@FindBy(xpath = "//tbody//tr//td[5]")
	List<WebElement> modelData;

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

	private static final Logger lOGGER = LogManager.getLogger(MyTicketsPage.class.getName());

	public MyTicketsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnCreateTicket() {

		wait.forElementToBeVisible(createTicket);
		click(createTicket);
		lOGGER.info("Clicking on Create Ticket button");
	}

	public void myTicketPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");

		wait.forElementToBeVisible(tableBody);
		lOGGER.info("displaying details of page after entering into My Tickets page :----");
		lOGGER.info(tableBody.getText());
	}

	public void validSearchVerification(String searchElement) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchElement);
		lOGGER.info("Entering the required data in search field");
		try {
			actualResult = driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]"))
					.getText();
		} catch (StaleElementReferenceException e) {
			actualResult = driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]"))
					.getText();
		}
		String expectedResult = searchElement;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void clickTicket(String searchElement) {

		wait.forPage();
		driver.findElement(By.xpath("//td//a[contains(text()," + "'" + searchElement + "'" + ")]")).click();

	}

//	public void invalidSearchVerification(String searchElement) {
//
//		wait.forElementToBeVisible(search);
//		sendKeys(search, searchElement);
//		lOGGER.info("Entering the required data in search field");
//		wait.forElementToBeVisible(emptyTable);
//		System.out.println(emptyTable.getText());
//		lOGGER.info("Verifying search field with Invalid Serial Number");
//	}

	public void sortingVerification() {

		wait.forElementToBeVisible(manufacturer);
		click(manufacturer);
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

		wait.forElementToBeVisible(lastUpdated);
		click(lastUpdated);
		lOGGER.info("Sorting the Last Updated column in Ascending order");
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

	public void paginationVerification() {

		boolean pages = lastPage.isDisplayed();

		if (pages == true) {

			int lastpage = Integer.parseInt(lastPage.getText());

			for (int i = 0; i < lastpage - 1; i++) {

				pause(1500);
				wait.forElementToBeVisible(currentPage);
				System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

				wait.forElementToBeVisible(dataTableInfo);
				System.out.println("Total contents in this page are :- " + dataTableInfo.getText());

				wait.forElementToBeVisible(nextArrow);
				click(nextArrow);
			}
		} else {
			wait.forElementToBeVisible(currentPage);
			System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

			wait.forElementToBeVisible(dataTableInfo);
			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		}
		pause(1500);
		wait.forElementToBeVisible(currentPage);
		System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

		wait.forElementToBeVisible(dataTableInfo);
		System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
	}

	public void createTicketPageVerification(String expected) {

		wait.forElementToBeVisible(pageHeader);
		String actual = pageHeader.getText();
		actual = actual.substring(actual.indexOf("1") + 3);
		Assert.assertEquals(actual, expected);
		lOGGER.info("Verifying Page Heading of My Tickets page");
	}

	public void clickOnFirstTicket() {

		wait.forElementToBeVisible(firstTicket);
		click(firstTicket);
		lOGGER.info("Clicking on first Ticket from the list");
	}

	public String selectRandomTicket() {

		List<WebElement> quotes = driver.findElements(By.xpath("//tbody//tr//td[1]//a"));

		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(quotes.size());
		System.out.println("Selected Random Ticket ID is :------" + quotes.get(nextRandomNumberIndex).getText());
		return quotes.get(nextRandomNumberIndex).getText();
	}

	public void blankColumnVerification(String username) {

		reader.removeColumn(sheetName, 0);
		reader.removeColumn(sheetName, 1);
		reader.removeColumn(sheetName, 2);

		reader.addColumn(sheetName, "USERNAME");
		reader.addColumn(sheetName, "TICKET ID");
		reader.addColumn(sheetName, "COLUMN NAME");

		String userInFile = reader.getCellData(sheetName, "USERNAME", k);
		String nextData = reader.getCellData(sheetName, "TICKET ID", k);
		if ((userInFile.contains(username) == false) && (nextData.isEmpty() == true))
			reader.setCellData(sheetName, "USERNAME", k, username);

		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		try {
			for (int i = 0; i < tableRows.size(); i++) {
				for (int j = 0; j < columnHeading.size() - 2; j++) {

					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j + 1) + "]//a"));
					if ((eachData.getText().isEmpty()) == true) {

						String ticketID = ticketIDData.get(i).getText();
						String columnName = columnHeading.get((j) % columnHeading.size()).getText();
						if (!((columnName.equals("Manufacturer")) || (columnName.equals("Model")))) {
//							System.out.println("One/Many column in this Ticket ID " + ticketID + " is blank ");
//							System.out.println("The blank Column name is " + columnName);
							reader.setCellData(sheetName, "TICKET ID", k, ticketID);
							reader.setCellData(sheetName, "COLUMN NAME", k, columnName);
							k++;
						} else
							reader.setCellData(sheetName, "TICKET ID", k, "There are no Blank data");
					}
				}
			}
			if ((eachData.getText().isEmpty()) == true)
				System.out.println("There are no blank columns in this Ticket lists");
		} catch (NoSuchElementException e) {

			wait.forElementToBeVisible(emptyTable);
			emptyTable.isDisplayed();
			System.out.println(emptyTable.getText());
		}
	}

	public void validSearchVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		int randomNumberIndex = r.nextInt(ticketIDData.size());
		wait.forElementToBeVisible(ticketIDData.get(randomNumberIndex));
		String randomRepairID = ticketIDData.get(randomNumberIndex).getText();
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

		wait.forPage();
		String n = genRandomString();
		wait.forElementToBeVisible(search);
		scrollToTop();
		System.out.println("Invalid search element to be entered is :------" + n);
		lOGGER.info("Entering the required data in search field");
		sendKeys(search, n);
		search.sendKeys(Keys.ENTER);
		try {
			System.out.println(emptyTable.getText());
		} catch (Exception e) {
//			System.out.println(emptyTable.getText());
			driver.navigate().refresh();
			invalidSearchVerification();
		}
		lOGGER.info("Verifying search field with Invalid Ticket ID");
	}

	public void blankColumnVerification() {

		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");
		wait.forPage();

		try {
			for (int i = 0; i < tableRows.size(); i++) {
				for (int j = 0; j < columnHeading.size() - 2; j++) {

					eachData = driver.findElement(By.xpath("//tbody//tr[" + (i + 1) + "]//td[" + (j + 1) + "]//a"));

					String ticketID = ticketIDData.get(i).getText();
					String columnName = columnHeading.get((j) % columnHeading.size()).getText();
					if (!((columnName.equals("Manufacturer")) || (columnName.equals("Model")))) {
						lOGGER.info("One/Many column in this Ticket ID " + ticketID + " is blank ");
						lOGGER.info("The blank Column name is " + columnName);
					}
				}
			}
			if ((eachData.getText().isEmpty()) == true)
				lOGGER.info("There are no blank columns in this Ticket lists");
		} catch (

		NoSuchElementException e) {

			wait.forElementToBeVisible(emptyTable);
			emptyTable.isDisplayed();
			lOGGER.info(emptyTable.getText());
		}
	}
}