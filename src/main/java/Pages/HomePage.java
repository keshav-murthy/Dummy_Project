package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class HomePage extends BasePage {

	String pageTitle = driver.getTitle();

	@FindBy(xpath = "//span[contains(text(),'My Assets')]")
	WebElement myAssets;

	@FindBy(xpath = "(//span[contains(text(),'TRUE') or contains(text(),'True')])[1]")
	WebElement trueView;

	@FindBy(xpath = "//button[@name='agree']")
	WebElement agreeButton;

	@FindBy(xpath = "(//li//a[contains(text(),'Computing')])[1]")
	WebElement computingTablets;

	@FindBy(xpath = "//a[text()='Mobile Handheld Computer']")
	WebElement mobileComputers;

	@FindBy(xpath = "(//a[@href='/cats/printing.htm'])[1]")
	WebElement barcodePrinting;

	@FindBy(xpath = "//a[text()='Barcode Label Printer']")
	WebElement barcodeLabelPrinter;

	@FindBy(xpath = "//a[text()='Desktop printers' or text()='desktop printers']")
	WebElement desktopPrinters;

	private static final Logger lOGGER = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnMyAssetsAndServices() {

//		if (pageTitle.contains("To access the Assets & Services portal")) {
//
//			wait.forElementToBeVisible(agreeButton);
//			js.clickElement(agreeButton);
//			lOGGER.info("clicked on terms & conditions Agree button");
//		}
//
//		wait.forElementToBeVisible(myAssets);
//		click(myAssets);
//		lOGGER.info("clicked on My Assets & Services Button");
//
//		if (pageTitle.contains("To access the Assets & Services portal")) {
//
//			wait.forElementToBeVisible(agreeButton);
//			js.clickElement(agreeButton);
//			lOGGER.info("clicked on terms & conditions Agree button");

		wait.forPage(3000);
		try {
			if (pageTitle.contains("To access the Assets & Services portal")) {

				wait.forElementToBeVisible(agreeButton);
				js.clickElement(agreeButton);
				lOGGER.info("clicked on terms & conditions Agree button");
			}

			wait.forElementToBeVisible(trueView);
			wait.forElementToBeClickable(trueView);
			js.clickElement(trueView);
			lOGGER.info("clicked on True View Button");

		} catch (TimeoutException f) {
			wait.forElementToBeVisible(myAssets);
			wait.forElementToBeClickable(myAssets);
			js.clickElement(myAssets);
			lOGGER.info("clicked on My Assets & Services Button");
		}

		if (pageTitle.contains("To access the Assets & Services portal")) {

			wait.forElementToBeVisible(agreeButton);
			js.clickElement(agreeButton);
			lOGGER.info("clicked on terms & conditions Agree button");
		}
	}

	public void verifyServicePortal() {

		try {
			wait.forElementToBeVisible(trueView);
			boolean actual = trueView.isDisplayed();
			Assert.assertTrue(actual);
			lOGGER.info("Verifying the presence of True View Button");
		} catch (Exception e) {

			wait.forElementToBeVisible(myAssets);
			boolean actual = myAssets.isDisplayed();
			Assert.assertTrue(actual);
			lOGGER.info("Verifying the presence of My Assets & Services Button");
		}
	}

	public void selectMobileScanner() {

		wait.forElementToBeVisible(computingTablets);
		js.clickElement(computingTablets);
		wait.forElementToBeVisible(mobileComputers);
		js.clickElement(mobileComputers);
	}

	public void selectDesktopPrinter() {

		wait.forElementToBeVisible(barcodePrinting);
		click(barcodePrinting);
		wait.forElementToBeVisible(barcodeLabelPrinter);
		click(barcodeLabelPrinter);
		wait.forElementToBeVisible(desktopPrinters);
		click(desktopPrinters);
	}
}