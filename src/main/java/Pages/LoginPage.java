package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//a[@href='/store/customer/account/']")
	WebElement myAccount;

	@FindBy(xpath = "//span[contains(text(),'my account')]")
	WebElement myAccountButton;

	@FindBy(xpath = "//input[@name='login[username]']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@name='login[password]']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@title='Login']")
	WebElement loginButton;

	@FindBy(xpath = "(//button[@id='send2'])[1]")
	WebElement signinButton;

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void userLoginProcedure(String username, String password) {

		wait.forElementToBeVisible(myAccount);
		js.clickElement(myAccount);
		lOGGER.info("clicked on My Account");

		String pageTitle = driver.getTitle();

		if (pageTitle.contains("Customer Login")) {

			wait.forElementToBeVisible(myAccountButton);
			js.clickElement(myAccountButton);
			lOGGER.info("clicked on My Account");

			wait.forElementToBeVisible(usernameField);
			sendKeys(usernameField, username);
			lOGGER.info("Entering the username/ email");

			wait.forElementToBeVisible(passwordField);
			sendKeys(passwordField, password);
			lOGGER.info("entering the password");

			wait.forElementToBeVisible(signinButton);
			js.clickElement(signinButton);
			lOGGER.info("clicked on Sign-In Button");
		} else {
			wait.forElementToBeVisible(usernameField);
			sendKeys(usernameField, username);
			lOGGER.info("Entering the username/ email");

			wait.forElementToBeVisible(passwordField);
			sendKeys(passwordField, password);
			lOGGER.info("entering the password");

			wait.forElementToBeVisible(loginButton);
			js.clickElement(loginButton);
			lOGGER.info("clicked on Login Button");
		}
	}
}