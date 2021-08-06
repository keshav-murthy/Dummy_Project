package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyTicketsPage;
import Pages.NetSuitePage;
import Pages.NetsuiteLoginPage;
import Pages.TicketDetailsPage;
import Pages.ValidationPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_04_TicketDetailTest extends TestBase {

	protected static String ticketID;

	@BeforeMethod
	public void openPage() {

		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_statusValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		ticketID = myTickets.selectRandomTicket();
		myTickets.validSearchVerification(ticketID);
		myTickets.clickTicket(ticketID);
		ValidationPage validation = new ValidationPage(driver);
		validation.getBarcodesStatusDetails();

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNetsuiteDetails(ticketID);
		validation.getNetsuiteStatusDetails();
		validation.statusValidation();
	}

	@Test(priority = 2)
	public void TC_02_commentsValidationInNetSuite() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		ticketID = myTickets.selectRandomTicket();
		myTickets.validSearchVerification(ticketID);
		myTickets.clickTicket(ticketID);
		TicketDetailsPage ticketdetails = new TicketDetailsPage(driver);
		ticketdetails.enterComments(InitializePropertyFile.property.getProperty("SC_04_Barcodes_Comments"));

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNetsuiteDetails(ticketID);
		ValidationPage validation = new ValidationPage(driver);
		validation.getNetsuiteMessage();
		validation.netsuiteCommentMessageValidation(
				InitializePropertyFile.property.getProperty("SC_04_Barcodes_Comments"));
	}

	@Test(priority = 3)
	public void TC_03_commentsValidationInBarcodes() {

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNetsuiteDetails(ticketID);
		netsuite.enterNetsuiteComment(InitializePropertyFile.property.getProperty("SC_04_NetSuite_Comments"));

		driver.navigate().to(InitializePropertyFile.property.getProperty("url"));
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.validSearchVerification(ticketID);
		myTickets.clickTicket(ticketID);
		TicketDetailsPage ticketdetail = new TicketDetailsPage(driver);
		String barcodesComments = ticketdetail.getTicketMessageDetails();
		ValidationPage validation = new ValidationPage(driver);
		validation.barcodesCommentMessageValidation(barcodesComments,
				InitializePropertyFile.property.getProperty("SC_04_NetSuite_Comments"));
	}
}