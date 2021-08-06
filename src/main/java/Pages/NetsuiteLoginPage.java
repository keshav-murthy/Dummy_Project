package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class NetsuiteLoginPage extends BasePage {

	@FindBy(xpath = "//input[@id='userName']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@id='login-submit']")
	WebElement loginButton;

	@FindBy(xpath = "//td[contains(text(),'Question:')]//following-sibling::td")
	WebElement question;

	@FindBy(xpath = "//input[@class='input hidepassword' and @type='password' ]")
	WebElement answer;

	@FindBy(xpath = "//input[@value='Submit']")
	WebElement submitButton;

	private static final Logger lOGGER = LogManager.getLogger(NetsuiteLoginPage.class.getName());

	public NetsuiteLoginPage(WebDriver driver) {
		super(driver);
	}

	public void userLoginProcedure(String username, String password) {

		wait.forElementToBeVisible(usernameField);
		sendKeys(usernameField, username);
		lOGGER.info("Entering the username/ email");

		wait.forElementToBeVisible(passwordField);
		sendKeys(passwordField, password);
		lOGGER.info("entering the password");

		wait.forElementToBeVisible(loginButton);
		click(loginButton);
		lOGGER.info("clicked on Login Button");
	}

	public void enterAnswerToSecurityQuestion(String answer_01, String answer_02, String answer_03) {

		String securityQuestion = question.getText();

		if (securityQuestion.equalsIgnoreCase("What was your childhood nickname?")) {

			wait.forElementToBeVisible(answer);
			sendKeys(answer, answer_01);
			lOGGER.info("entering the answer to the question");

			wait.forElementToBeVisible(submitButton);
			click(submitButton);
			lOGGER.info("clicked on submit Button");
		}

		else if (securityQuestion.equalsIgnoreCase("What is the middle name of your oldest child?")) {

			wait.forElementToBeVisible(answer);
			sendKeys(answer, answer_02);
			lOGGER.info("entering the answer to the question");

			wait.forElementToBeVisible(submitButton);
			click(submitButton);
			lOGGER.info("clicked on submit Button");
		}

		else {

			wait.forElementToBeVisible(answer);
			sendKeys(answer, answer_03);
			lOGGER.info("entering the answer to the question");

			wait.forElementToBeVisible(submitButton);
			click(submitButton);
			lOGGER.info("clicked on submit Button");
		}
	}
}