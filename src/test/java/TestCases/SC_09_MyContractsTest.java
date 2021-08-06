package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AddContractPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyContractsPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_09_MyContractsTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
	}

	@Test(priority = 1)
	public void TC_01_contractDetailList() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.myContractsPageVerification(InitializePropertyFile.property.getProperty("SC_09_Page_Title_01"));
	}

	@Test(priority = 2)
	public void TC_02_verifySearch() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.validSearchVerification();
		mycontracts
				.invalidSearchVerification();
	}

	@Test(priority = 3)
	public void TC_03_sortingColumns() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.sortingVerification();
	}

	@Test(priority = 4)
	public void TC_04_addContract() {

		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.clickOnAddContract();
		AddContractPage addContract = new AddContractPage(driver);
		addContract.addContractPageVerification(InitializePropertyFile.property.getProperty("SC_09_Page_Title_02"));
	}
}