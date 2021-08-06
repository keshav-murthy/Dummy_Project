package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class MobileComputerPage extends BasePage {

	@FindBy(xpath = "//div[@class='product_title']//h1")
	WebElement productTitle;

	@FindBy(xpath = "//div[@class='product_description']")
	WebElement productDescription;

	@FindBy(xpath = "//span[@class='modelname']")
	List<WebElement> products;

	private static final Logger lOGGER = LogManager.getLogger(MobileComputerPage.class.getName());

	public MobileComputerPage(WebDriver driver) {
		super(driver);
	}

	public void verifyProductTitle() {

		wait.forElementToBeVisible(productTitle);
		Assert.assertEquals(productTitle.getText(), "Mobile Handheld Computer");
	}

	public void verifyProductDescription() {

		wait.forElementToBeVisible(productDescription);
		Assert.assertTrue(productDescription.isDisplayed());
	}

	public void verifyProducts() {

		for (int i = 0; i < products.size(); i++) {
			scrollToElementView(products.get(i));
			wait.forElementToBeVisible(products.get(i));
			Assert.assertTrue(products.get(i).isDisplayed()==false);
		}
	}
}