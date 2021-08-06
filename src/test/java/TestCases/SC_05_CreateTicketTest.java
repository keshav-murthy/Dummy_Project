package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.CreateTicketPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.MyTicketsPage;
import Pages.NetSuitePage;
import Pages.NetsuiteLoginPage;
import Pages.TicketDetailsPage;
import Pages.ValidationPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_05_CreateTicketTest extends TestBase {

	protected String ticketID;
	protected String randomAsset;

	@BeforeMethod
	public void openPage() {

		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_createTicket() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myassets = new MyAssetsPage(driver);
		randomAsset = myassets.selectRandomAsset();
		assetsandservices.clickOnCreateTickets();
		CreateTicketPage createticket = new CreateTicketPage(driver);
		createticket.addingTicketProcedure(randomAsset);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.validSearchVerification(randomAsset);
		myTickets.clickTicket(randomAsset);
		TicketDetailsPage ticketdetails = new TicketDetailsPage(driver);
		ticketID = ticketdetails.getTicketDetails();
	}

//	@Test(priority = 2)
	public void TC_02_netsuiteValidation() {

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNetsuiteDetails(randomAsset);
		ValidationPage validation = new ValidationPage(driver);
		validation.getNetsuiteRepairID();
		validation.createdTicketValidation(ticketID);
	}
}