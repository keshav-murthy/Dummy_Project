package Pages;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class AddAssetPage extends BasePage {

	@FindBy(xpath = "//input[@type='select-one']")
	WebElement searchProduct;

//	@FindBy(xpath = "//div[contains(text(),'DAT536070-K03 ( Model: 123 Easy ID)')]")
//	WebElement selectProduct;

	@FindBy(xpath = "//div[@class='selectize-dropdown-content']//div[5]")
	WebElement selectProduct;
	
	@FindBy(xpath = "//input[@id='serial_no']")
	WebElement assetSerialNumber;

	@FindBy(xpath = "//input[@id='model']")
	WebElement model;

	@FindBy(xpath = "//input[@id='manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//button[@title='Save']")
	WebElement save;

	private static final Logger lOGGER = LogManager.getLogger(AddAssetPage.class.getName());

	public AddAssetPage(WebDriver driver) {
		super(driver);
	}

	public void addingAssetProcedure(String random) {

		wait.forElementToBeVisible(searchProduct);
		sendKeys(searchProduct, "123");
		pause(3000);
		js.clickElement(selectProduct);
//		searchProduct.sendKeys(Keys.DOWN);
//		searchProduct.sendKeys(Keys.DOWN);
//		searchProduct.sendKeys(Keys.DOWN);
//		searchProduct.sendKeys(Keys.DOWN);
//		searchProduct.sendKeys(Keys.ENTER);
		lOGGER.info("Enter search product");

		wait.forElementToBeVisible(assetSerialNumber);
		sendKeys(assetSerialNumber, random);
		lOGGER.info("Enter Asset Serial Number");

//		wait.forElementToBeVisible(model);
//		sendKeys(model, "Automation Testing");
//		lOGGER.info("Enter Model");
//
//		wait.forElementToBeVisible(manufacturer);
//		sendKeys(manufacturer, "Panasonic");
//		lOGGER.info("Enter a Manufacturer");

		wait.forElementToBeVisible(save);
		click(save);
		lOGGER.info("clicking on Save button");
	}

	public void existingSerialNumberAssetCreation(String random) {

		wait.forElementToBeVisible(searchProduct);
		sendKeys(searchProduct, "DAT536070-K03 ( Model: 123 Easy ID)");
		searchProduct.sendKeys(Keys.ENTER);
		lOGGER.info("Enter search product");

		wait.forElementToBeVisible(assetSerialNumber);
		sendKeys(assetSerialNumber, random);
		lOGGER.info("Enter Asset Serial Number");

		wait.forElementToBeVisible(model);
		String randomManufacturer = UUID.randomUUID().toString();
		sendKeys(model, randomManufacturer);
		lOGGER.info("Enter a random Model");

		wait.forElementToBeVisible(manufacturer);
		sendKeys(manufacturer, "Panasonic");
		lOGGER.info("Enter a Manufacturer");

		wait.forElementToBeVisible(save);
		click(save);
		lOGGER.info("clicking on Save button");
	}
}
