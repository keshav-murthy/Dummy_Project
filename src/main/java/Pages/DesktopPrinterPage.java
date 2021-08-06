package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class DesktopPrinterPage extends BasePage {

	@FindBy(xpath = "//div[@class='product_title']//h1")
	WebElement productTitle;

	@FindBy(xpath = "//div[@class='product_description']")
	WebElement productDescription;

	@FindBy(xpath = "//span[@class='modelname']")
	List<WebElement> products;

	@FindBy(xpath = "//div[@id='filter_Model_popular']//ul//li//a")
	List<WebElement> models;

	private static final Logger lOGGER = LogManager.getLogger(DesktopPrinterPage.class.getName());

	public DesktopPrinterPage(WebDriver driver) {
		super(driver);
	}

	public void verifyProductTitle() {

		wait.forElementToBeVisible(productTitle);
		Assert.assertEquals(productTitle.getText(), "Desktop Barcode Label Printer");
	}

	public void verifyProductDescription() {

		wait.forElementToBeVisible(productDescription);
		Assert.assertTrue(productDescription.isDisplayed());
	}

	public void verifyProducts() {

		for (int i = 0; i < products.size(); i++) {
			wait.forElementToBeVisible(products.get(i));
			scrollToElementView(products.get(i));
			Assert.assertTrue(products.get(i).isDisplayed());
		}
	}

	public void selectRandomModel() {

		wait.forElementToBeVisible(models.get(1));
		scrollToElementView(models.get(1));
		WebElement randomModel = selectRandomElement(models);
		wait.forElementToBeVisible(randomModel);
		js.clickElement(randomModel);
	}

	public void verifyModel() {

		verifyProductTitle();
		verifyProducts();
	}
}