package Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ViewReportPage extends BasePage {

	protected static int i;
	protected static Random r = new Random();
	protected static ArrayList<String> obtainedList = new ArrayList<String>();
	protected static ArrayList<String> sortedList = new ArrayList<String>();

	@FindBy(xpath = "//h1//span")
	WebElement reportHeader;

	@FindBy(xpath = "//input[@id='from_date']")
	WebElement fromDate;

	@FindBy(xpath = "//input[@id='to_date']")
	WebElement toDate;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> table;

	@FindBy(xpath = "//tbody//tr//td[8]")
	List<WebElement> dateTable;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> repairIDList;

	@FindBy(xpath = "//tbody//tr//td[2]")
	List<WebElement> serialNoList;

	@FindBy(xpath = "//th[text()='Serial No']")
	WebElement serialNo;

	@FindBy(xpath = "//th[text()='Subject']")
	WebElement subject;

	@FindBy(xpath = "//th[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//th[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//th[text()='Type']")
	WebElement type;

	@FindBy(xpath = "//th[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Last Updated']")
	WebElement lastUpdated;

	@FindBy(xpath = "//div[@class='dataTables_info']")
	WebElement dataTableInfo;

	@FindBy(xpath = "//a[@class='paginate_button current']")
	WebElement currentPage;

	@FindBy(xpath = "//a[@class='paginate_button next']//preceding-sibling::span//a")
	List<WebElement> pages;

	@FindBy(xpath = "//a[contains(text(),'>')]")
	WebElement nextArrow;

	@FindBy(xpath = "//table//th[3]")
	WebElement firstColumn;

	@FindBy(xpath = "//table//tr//td[3]")
	List<WebElement> firstColumnData;

	@FindBy(xpath = "//table//th[4]")
	WebElement secondColumn;

	@FindBy(xpath = "//table//tr//td[4]")
	List<WebElement> secondColumnData;

	@FindBy(xpath = "//table//th[5]")
	WebElement thirdColumn;

	@FindBy(xpath = "//table//tr//td[5]")
	List<WebElement> thirdColumnData;

	@FindBy(xpath = "//table//th[6]")
	WebElement fourthColumn;

	@FindBy(xpath = "//table//tr//td[6]")
	List<WebElement> fourthColumnData;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	private static final Logger lOGGER = LogManager.getLogger(ViewReportPage.class.getName());

	public ViewReportPage(WebDriver driver) {
		super(driver);
	}

	public void verifyReportDetails(String expected) {

		wait.forElementToBeVisible(reportHeader);
		String actual = reportHeader.getText();
		Assert.assertTrue(expected.contains(actual));

		lOGGER.info("Verifying the report details page Heading");
	}

	public void clickOnViewReport(String widgetTitle) {

		WebElement viewReport = driver.findElement(By.xpath(
				"//h3[contains(text()," + "'" + widgetTitle + "'" + ")]//ancestor::div[@class='inner']//div//a"));
		scrollToElementView(viewReport);
		viewReport.click();
	}

	public void validSearchVerification() {

		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		int randomNumberIndex = r.nextInt(repairIDList.size());
		wait.forElementToBeVisible(repairIDList.get(randomNumberIndex));
		String randomRepairID = repairIDList.get(randomNumberIndex).getText();
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
		lOGGER.info("Entering the required data in search field");
		try {
			System.out.println(emptyTable.getText());
		} catch (StaleElementReferenceException e) {
			System.out.println(emptyTable.getText());
		}
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}

	public void paginationVerification() {

		int lastPage = 0;
		System.out.println(pages.size());
		if ((pages.size()) == 0) {

//			try {
//				wait.forPage();
//				wait.forElementToBeVisible(dataTableInfo);
//			} catch (TimeoutException e) {
//				driver.navigate().refresh();
//				wait.forPage();
//				wait.forElementToBeVisible(dataTableInfo);
//			}
//			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		} else {
			String last = driver
					.findElement(By.xpath(
							"//a[@class='paginate_button next']//preceding-sibling::span//a[" + pages.size() + "]"))
					.getText();
			try {
				lastPage = Integer.parseInt(last);
			} catch (Exception e) {
				lastPage = 1;
			}
			for (i = 0; i < lastPage - 1; i++) {

				wait.forPage(1500);
				wait.forElementToBeVisible(currentPage);
				Assert.assertEquals(currentPage.getText(), Integer.toString(i + 1));
//				System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

				wait.forElementToBeVisible(dataTableInfo);
//				System.out.println("Total contents in this page are :- " + dataTableInfo.getText());

				wait.forElementToBeVisible(nextArrow);
				click(nextArrow);
			}

			wait.forPage(1500);
			wait.forElementToBeVisible(currentPage);
			Assert.assertEquals(currentPage.getText(), Integer.toString(i + 1));
//			System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

			wait.forElementToBeVisible(dataTableInfo);
//			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		}
	}

//	public void sortingVerification() {
//
//		wait.forElementToBeVisible(manufacturer);
//		click(manufacturer);
//		wait.forElementToBeVisible(manufacturer);
//		click(manufacturer);
//		lOGGER.info("Sorting the Manufacturer column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(model);
//		click(model);
//		wait.forElementToBeVisible(model);
//		click(model);
//		lOGGER.info("Sorting the Model column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(type);
//		click(type);
//		wait.forElementToBeVisible(type);
//		click(type);
//		lOGGER.info("Sorting the type column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(lastUpdated);
//		click(lastUpdated);
//		lOGGER.info("Sorting the Last Updated column in Ascending order");
//		printTableContent();
//	}

	public void printTableContent() {

		try {
			while (emptyTable.isDisplayed() == true) {
				System.out.println(emptyTable.getText());
				break;
			}
		} catch (NoSuchElementException e) {
			List<WebElement> content = table;
			for (int i = 0; i < content.size(); i++) {
				pause(500);
//			for (int i = 0; i < firstColumnData.size(); i++) {
//				obtainedList.add(firstColumnData.get(i).getText());
//			}
//			Assert.assertTrue(sortedList.equals(obtainedList));
				System.out.println(
						"displaying details of table after sorting of column :----" + content.get(i).getText());
			}
		}
	}

	public void dateRangeVerification(String startDate, String endDate) {

		wait.forPage();
		wait.forElementToBeVisible(fromDate);
		javaScriptSendValue(fromDate, startDate);
		lOGGER.info("Entering the starting date in date field");

//		wait.forElementToBeVisible(fromDate);
//		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].setAttribute('value','" + startDate + "');", fromDate);
//
//		wait.forElementToBeVisible(toDate);
//		js.executeScript("arguments[0].setAttribute('value','" + endDate + "');", toDate);

		wait.forElementToBeVisible(toDate);
		javaScriptSendValue(toDate, endDate);
		lOGGER.info("Entering the ending date in date field");

		wait.forElementToBeVisible(dateTable.get(0));
		List<WebElement> dateContent = dateTable;
		for (int i = 0; i < dateContent.size(); i++) {
			System.out.println(
					"displaying details of table after sorting of column :----" + dateContent.get(i).getText());
		}
	}

	public void settingDownloadPath() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePref = new HashMap<>();
		chromePref.put("download.default_directory", "/Project_Barcodes");
		options.setExperimentalOption("prefs", chromePref);
	}

	public void sortingVerification() {

//		wait.forElementToBeVisible(tableLengthDropDown);
//		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

//		pause(1000);
//		firstColumnSortingVerification();
//		pause(1000);
//		secondColumnSortingVerification();
//		pause(1000);
//		thirdColumnSortingVerification();
//		pause(1000);
//		fourthColumnSortingVerification();

		pause(1000);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		pause(1000);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		lOGGER.info("Sorting the " + firstColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		pause(1000);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		lOGGER.info("Sorting the " + secondColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		pause(1000);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		lOGGER.info("Sorting the " + thirdColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(fourthColumn);
		click(fourthColumn);
		lOGGER.info("Sorting the " + fourthColumn.getText() + " column in Ascending order");
		printTableContent();
	}

	public void blankRepairIDVerification(String widgetTitle) {

		System.out.println("The Ticket Widget title selected is :------" + reportHeader.getText());
		int count = 0;
		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		try {
			for (int i = 0; i < repairIDList.size(); i++) {

				String repairID = repairIDList.get(i).getText();
				String serialNo = serialNoList.get(i).getText();
				if (repairID.equals("")) {
					System.out.println("There is a blank Repair ID in " + widgetTitle
							+ " widget and Serial No associated with it is " + serialNo);
					count = count + 1;
				}
			}
			System.out.println("Total number of blank Repair ID's are " + count);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(emptyTable.getText());
		}
	}

	public void firstColumnSortingVerification() {

		for (int i = 0; i < firstColumnData.size(); i++) {
//			wait.forElementToBeVisible(firstColumnData.get(i));
			sortedList.add(firstColumnData.get(i).getText());
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		pause(1000);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		lOGGER.info("Sorting the " + firstColumn.getText() + " column in Descending order");
		pause(500);
		for (int i = 0; i < firstColumnData.size(); i++) {
//			wait.forElementToBeVisible(firstColumnData.get(i));
			obtainedList.add(firstColumnData.get(i).getText());
		}
		Assert.assertTrue(sortedList.containsAll(obtainedList));
		sortedList.clear();
		obtainedList.clear();
	}

	public void secondColumnSortingVerification() {

		for (int i = 0; i < secondColumnData.size(); i++) {
//			wait.forElementToBeVisible(secondColumnData.get(i));
			sortedList.add(secondColumnData.get(i).getText());
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		pause(1000);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		lOGGER.info("Sorting the " + secondColumn.getText() + " column in Descending order");
		pause(500);
		for (int i = 0; i < secondColumnData.size(); i++) {
//			wait.forElementToBeVisible(secondColumnData.get(i));
			obtainedList.add(secondColumnData.get(i).getText());
		}
		Assert.assertTrue(sortedList.containsAll(obtainedList));
		sortedList.clear();
		obtainedList.clear();
	}

	public void thirdColumnSortingVerification() {

		for (int i = 0; i < thirdColumnData.size(); i++) {
//			wait.forElementToBeVisible(thirdColumnData.get(i));
			sortedList.add(thirdColumnData.get(i).getText());
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		pause(1000);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		lOGGER.info("Sorting the " + thirdColumn.getText() + " column in Descending order");
		pause(500);
		for (int i = 0; i < thirdColumnData.size(); i++) {
//			wait.forElementToBeVisible(thirdColumnData.get(i));
			obtainedList.add(thirdColumnData.get(i).getText());
		}
		Assert.assertTrue(sortedList.containsAll(obtainedList));
		sortedList.clear();
		obtainedList.clear();
	}

	public void fourthColumnSortingVerification() {

		for (int i = 0; i < fourthColumnData.size(); i++) {
//			wait.forElementToBeVisible(fourthColumnData.get(i));
			sortedList.add(fourthColumnData.get(i).getText());
		}
		Collections.sort(sortedList);
		wait.forElementToBeVisible(fourthColumn);
		click(fourthColumn);
		lOGGER.info("Sorting the " + fourthColumn.getText() + " column in Ascending order");
		pause(500);
		for (int i = 0; i < fourthColumnData.size(); i++) {
//			wait.forElementToBeVisible(fourthColumnData.get(i));
			obtainedList.add(fourthColumnData.get(i).getText());
		}
		Assert.assertTrue(sortedList.containsAll(obtainedList));
		sortedList.clear();
		obtainedList.clear();
	}
}