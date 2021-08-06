package commons;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class ExplicitWait extends GenericHelper {

	private static Logger log = LogManager.getLogger(ExplicitWait.class);
	private WebDriver driver;
	private JavaScriptHelper js;

	public ExplicitWait(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.js = new JavaScriptHelper(driver);
	}

	private WebDriverWait getWait() {
		return getWait(Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollingEveryInMilliSec);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	public void setImplicitWait(long timeout) {
		setImplicitWait(timeout, TimeUnit.SECONDS);
	}

	public WebElement forElementToBePresent(By locator) {
		log.info("Waiting for element to be present: " + locator);
		return waitFor(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement forElementToBeVisible(By locator) {
		log.info("Waiting for element to be visible: " + locator);
		return forElementToBeVisible(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(By locator, int timeoutInSeconds) {
		log.info("Waiting for element to be visible: " + locator);
		return forElementToBeVisible(locator, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(WebElement webElement) {
		log.info("Waiting for element to be visible: " + webElement);
		return forElementToBeVisible(webElement, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(WebElement webElement, int timeoutInSeconds) {
		log.info("Waiting for element to be visible: " + webElement);
		return forElementToBeVisible(webElement, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(By locator, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for locator to be visible: " + locator);
		return waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeOutInSeconds,
				pollingEveryInMilliSec);
	}

	public WebElement forElementToBeVisible(WebElement webElement, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for element to be visible: " + webElement);
		return waitFor(ExpectedConditions.visibilityOf(webElement), timeOutInSeconds, pollingEveryInMilliSec);
	}

	public boolean forTextToBePresentInElementValue(WebElement element, String searchedText) {
		log.info("Waiting for text " + searchedText + "to be present in element: " + element);
		return waitFor(ExpectedConditions.textToBePresentInElementValue(element, searchedText));
	}

	public WebElement forElementToBeClickable(By element) {
		log.info("Waiting for element to be clickable: " + element);
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(By element, int timeoutInSeconds) {
		log.info("Waiting for element to be clickable: " + element);
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element) {
		log.info("Waiting for element to be clickable: " + element);
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element, int timeoutInSeconds) {
		log.info("Waiting for element to be clickable: " + element);
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for element to be clickable: " + element);
		return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMilliSec);
	}

	public WebElement forElementToBeClickable(By element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for element to be clickable: " + element);
		forPage();
		return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMilliSec);
	}

	public boolean forInvisibilityOfElement(WebElement element) {
		log.info("Waiting for invisibility of element: " + element);
		return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(By locator) {
		log.info("Waiting for invisibility of element: " + locator);
		return forInvisibilityOfElement(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds) {
		log.info("Waiting for invisibility of element: " + element);
		return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for invisibility of element: " + element);
		return waitFor(ExpectedConditions.invisibilityOf(element), timeOutInSeconds, pollingEveryInMilliSec);
	}

	public boolean forInvisibilityOfElement(By locator, int timeOutInSeconds, int pollingEveryInMilliSec) {
		log.info("Waiting for invisibility of element: " + locator);
		return waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), timeOutInSeconds,
				pollingEveryInMilliSec);
	}

	public boolean forPage() {
		return forPage(Timeouts.PAGE);
	}

	public boolean forPage(int timeoutInSeconds) {
		log.debug("Waiting for the document to load");
		WebDriverWait wait = getWait(timeoutInSeconds, Timeouts.POLLING_INTERVAL);
		return wait.until(jQueryLoad()) && wait.until(documentLoad());
	}

	public void until(Function expectedCondition) {
		getWait().until(expectedCondition);
	}

	private <V> V waitFor(Function expectedCondition) {
		return waitFor(expectedCondition, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	private <V> V waitFor(Function expectedCondition, int timeOutInSeconds, int pollingEveryInMilliSec) {
		setImplicitWait(0);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMilliSec);
		V result = (V) wait.until(expectedCondition);
		setImplicitWait(Timeouts.IMPLICIT);
		return result;
	}

	private Function<WebDriver, Boolean> textExistInElement(final WebElement element, final String searchedText) {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String textFromElement = element.getText();
				if (textFromElement.contains(searchedText)) {
					return true;
				}
				return false;
			}
		};
	}

	private Function<WebDriver, Boolean> elementIsPresentInDOM(final By locator) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElements(locator).size() > 0;
			}
		};
	}

	private Function<WebDriver, Boolean> jQueryLoad() {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) js.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
	}

	private Function<WebDriver, Boolean> documentLoad() {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};
	}
}