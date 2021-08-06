package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class BasePage {

	protected WebDriver driver;

	protected static ExplicitWait wait;
	protected static Actions action;
	protected static JavaScriptHelper js;
	protected static Random random = new Random();

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new ExplicitWait(driver);
		js = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public static void click(WebElement webElement) {
		click(webElement, Timeouts.EXPLICIT);
	}

	public static void click(WebElement webElement, int timeOutInSeconds) {
		wait.forElementToBeClickable(webElement, timeOutInSeconds, Timeouts.POLLING_INTERVAL);
		js.clickElement(webElement);
	}

	public static void sendKeys(WebElement webElement, String value, int timeOutInSeconds) {
		click(webElement, timeOutInSeconds);
		webElement.clear();
		webElement.sendKeys(value);
	}

	public static void sendKeys(WebElement webElement, String value) {
		sendKeys(webElement, value, Timeouts.EXPLICIT);
	}

	public void executeJavascript(String string) {
		js.executeScript(string);
	}

	public void executeJavascript(String string, WebElement element) {
		js.executeScript(string, element);
	}

	public void dropDownMethod(WebElement element, String option, String value) {

		Select s = new Select(element);
		if (option.equalsIgnoreCase("Value")) {
			s.selectByValue(value);
		} else if (option.equalsIgnoreCase("VisibleText")) {
			s.selectByVisibleText(value);
		} else if (option.equalsIgnoreCase("Index")) {
			int i = Integer.parseInt(value);
			s.selectByIndex(i);
		}
	}

	/* This method will validate partial text */
	public static boolean getTextPartialValidate(WebElement element, String value) {
		boolean flag = false;
		System.out.println("Extracted text is:" + element.getText());
		if (element.getText().contains(value)) {
			System.out.println("Partial Text comparsion is successful");
			flag = true;
		} else {
			System.out.println("Partial Text comparsion is unsuccessful");
		}
		return flag;

	}

	/* This is wrapper method to check the web element is displayed on the page */
	public boolean isElementPresence(WebElement webElement) {
		return webElement.isEnabled();
	}

	/* This method will clear the Text */
	public void clearText(WebElement element) {
		element.clear();
	}

	/* This method will scroll the web page till bottom of the page */
	public static void scrollToBottom() {

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	/* This method will scroll the web page till top of the web page */
	public static void scrollToTop() {

		js.executeScript("window.scrollTo(0,0)");

	}

	public static void scrollToPixel(int px) {
		js.executeScript("window.scrollTo(0," + px + ")");
	}

	/* This is wrapper method to check the web element is displayed on the page */
	public boolean isElementDisplayed(WebElement webElement) {
		return webElement.isDisplayed();
	}

	/* This method will scroll the web page Vertically till the element is found */
	public static void scrollToElementView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	/* This method will enter value in the input textbox */
	public static void javaScriptSendValue(WebElement element, String val) {
		js.executeScript("arguments[0].value='" + val + "';", element);

	}

	/* This method will click on the web element using javascriptexecutor */
	public static void javaScriptClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);

	}

	public static void actionsEnter() {
		js.actionEnter();
	}

	public WebElement FindElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public static void pause(Integer milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sleep(long millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void windowHandling() {

		Set<String> windowID = driver.getWindowHandles();
		for (String i : windowID)
			driver.switchTo().window(i);
	}

	public void defaultWindow() {

		Set<String> windowID = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowID);
		for (int i = 0; i < window.size(); i++)
			driver.switchTo().window(window.get(0));
	}

	public void closeCurrentWindow() {

		Set<String> windowID = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowID);
		for (int i = 0; i < window.size(); i++)
			driver.switchTo().window(window.get(1)).close();
		;
	}

	public void clickOnOKAlert() {

		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public String genRandomString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 15) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	public void moveToElement(WebElement element, WebElement element2) {

		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public WebElement selectRandomElement(List<WebElement> element) {

		int randomNumberIndex = random.nextInt(element.size());
		wait.forElementToBeVisible(element.get(randomNumberIndex));
		WebElement randomElement = element.get(randomNumberIndex);
		System.out.println("The model selected is :------" + randomElement.getText());

		return randomElement;
	}
}