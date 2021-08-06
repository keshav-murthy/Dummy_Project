package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyTicketsPage;
import Pages.RandomInputPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_03_MyTicketsTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_pageRenders() {


		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.myTicketPageVerification(InitializePropertyFile.property.getProperty("SC_03_Page_Title"));
	}

	@Test(priority = 2)
	public void TC_02_verifySearch() {


		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.validSearchVerification();
		myTickets.invalidSearchVerification();
	}

	@Test(priority = 3)
	public void TC_03_sortingColumns() {


		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.sortingVerification();
	}

	@Test(priority = 4)
	public void TC_04_verifyPagination() {


		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.paginationVerification();
	}

	@Test(priority = 5)
	public void TC_05_createTicketPageVerification() {


		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.clickOnCreateTicket();
		myTickets.createTicketPageVerification(
				InitializePropertyFile.property.getProperty("SC_03_Create_Ticket_Page_Title"));
	}

	@Test(priority = 6)
	public void TC_06_blankColumnVerification() {

		RandomInputPage random = new RandomInputPage(driver);
		String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		MyTicketsPage myTickets = new MyTicketsPage(driver);
		myTickets.blankColumnVerification(randomUser);
	}
}