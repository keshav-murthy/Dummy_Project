package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AddContractPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.MyContractsPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_10_AddContractTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test()
	public void TC_01_addContract() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		String randomAsset = myAsset.selectRandomAsset();
		assetsandservices.clickOnMyContracts();
		MyContractsPage mycontracts = new MyContractsPage(driver);
		mycontracts.clickOnAddContract();
		AddContractPage addcontract = new AddContractPage(driver);
		addcontract.addingContractProcedure(randomAsset);
	}
}