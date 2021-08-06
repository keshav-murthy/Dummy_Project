package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ReportDashboardPage extends BasePage {

	protected static List<String> widgetTitle = new ArrayList<String>();;
	protected static Random r = new Random();

	@FindBy(tagName = "h3")
	List<WebElement> widget;

	@FindBy(xpath = "//div[@class='widget-title']//h3")
	List<WebElement> widgetTitles;

	@FindBy(xpath = "//div[@class='widget-title']//h3[contains(text(),'Tickets')]")
	List<WebElement> ticketWidgetTitles;

	private static final Logger lOGGER = LogManager.getLogger(ReportDashboardPage.class.getName());

	public ReportDashboardPage(WebDriver driver) {
		super(driver);
	}

	public void verifyWidgetsInDashboard() {

		for (int i = 0; i < widget.size(); i++) {
			wait.forElementToBeVisible(widget.get(i));
			widgetTitle.add(widget.get(i).getText());
			System.out.println(widgetTitle.get(i));
		}
		Assert.assertEquals(17, (widgetTitle.size()),
				"Please check the console whether all the available Widget Title have been printed correctly or not");
		lOGGER.info("Verifying all the widgets and also printing the Titles of all the Widget available in dashboard");
	}

	public void clickOnViewReport(String widgetTitle) {

		WebElement viewReport = driver.findElement(By.xpath(
				"//h3[contains(text()," + "'" + widgetTitle + "'" + ")]//ancestor::div[@class='inner']//div//a"));
		wait.forElementToBeVisible(viewReport);
		js.clickElement(viewReport);
	}

	public String selectRandomWidgetWithFilter() {

		String ignoredWidgets = "Total Tickets YTD Total Tickets MTD Top 5 Location Submitted Tickets Repair Ticket Average Turnaround Time (Days)";
		wait.forPage(2000);
		int randomNumberIndex = r.nextInt(widgetTitles.size());
		String randomWidget = widgetTitles.get(randomNumberIndex).getText();
		System.out.println("The Widget title before ignoring is :------" + randomWidget);
		while ((ignoredWidgets.contains(randomWidget)) == true) {
			randomNumberIndex = r.nextInt(widgetTitles.size());
			randomWidget = widgetTitles.get(randomNumberIndex).getText();
			System.out.println("The Widget title after ignoring is :------" + randomWidget);
		}
		return randomWidget;
	}

	public String selectRandomWidget() {

		int randomNumberIndex = r.nextInt(widgetTitles.size());
		wait.forElementToBeVisible(widgetTitles.get(randomNumberIndex));
		String randomWidget = widgetTitles.get(randomNumberIndex).getText();
		System.out.println("The Widget title is :------" + randomWidget);
		return randomWidget;
	}

	public String selectRandomTicketWidget() {

//		String ignoredWidgets = "Tickets By Asset Type Total Tickets YTD Repair Tickets By Asset Type";
		wait.forPage();
		int randomNumberIndex = r.nextInt(ticketWidgetTitles.size());
		String randomWidget = (ticketWidgetTitles.get(randomNumberIndex)).getText();
//		while ((ignoredWidgets.contains(randomWidget)) == false) {
//			randomNumberIndex = r.nextInt(ticketWidgetTitles.size());
//			randomWidget = ticketWidgetTitles.get(randomNumberIndex).getText();
//			System.out.println("The Widget title after ignoring is :------" + randomWidget);
//		}
		return randomWidget;
	}
}