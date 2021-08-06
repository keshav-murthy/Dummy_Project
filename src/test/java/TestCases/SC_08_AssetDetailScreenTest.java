package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AddContractPage;
import Pages.AssetDetailsPage;
import Pages.CreateTicketPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.NetSuitePage;
import Pages.NetsuiteLoginPage;
import Pages.RandomInputPage;
import Pages.TicketDetailsPage;
import Pages.ValidationPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_08_AssetDetailScreenTest extends TestBase {

	public static boolean flag;

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_addContractPageVerification() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.clickOnAddContract();
		AddContractPage addcontract = new AddContractPage(driver);
		addcontract.addContractPageVerification(InitializePropertyFile.property.getProperty("SC_08_Page_Title_01"));
	}

	@Test(priority = 2)
	public void TC_02_createTicketPageVerification() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.clickOnCreateTicket();
		CreateTicketPage createTicket = new CreateTicketPage(driver);
		createTicket.createTicketPageVerification(InitializePropertyFile.property.getProperty("SC_08_Page_Title_02"));
	}

//	@Test(priority = 3)
	public void TC_03_contractDetailsValidation() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetail = new AssetDetailsPage(driver);
		String barcodesContractName = assetdetail.getFirstContractName();

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNetsuiteAssetDetails(randomAsset);
		String netsuiteContractName = netsuite.getFirstContractName();
		ValidationPage validation = new ValidationPage(driver);
		validation.contractDetailsValidation(barcodesContractName, netsuiteContractName);
	}

	@Test(priority = 4)
	public void TC_04_ticketDetailsOfAsset() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.ticketDetailsOfAsset();
	}

	@Test(priority = 5)
	public void TC_05_ticketDetailsPage() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		flag = assetdetails.clickOnFirstTicket();
		if (flag == true) {
			TicketDetailsPage ticketdetails = new TicketDetailsPage(driver);
			ticketdetails
					.ticketDetailsPageVerification(InitializePropertyFile.property.getProperty("SC_08_Page_Title_03"));
		}
	}

	@Test(priority = 6)
	public void TC_06_realImageVerification() {

		RandomInputPage random = new RandomInputPage(driver);
		String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.imageVerfication();
	}

	@Test(priority = 7)
	public void TC_07_contractVerification() {

		RandomInputPage random = new RandomInputPage(driver);
		String randomUser = random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.contractDetailsVerification();
	}

	@Test(priority = 8)
	public void TC_08_locationVerification() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		myAsset.locationVerfication();
	}
}