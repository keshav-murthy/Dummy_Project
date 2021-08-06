package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AddAssetPage;
import Pages.AssetDetailsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.NetSuitePage;
import Pages.NetsuiteLoginPage;
import Pages.RandomInputPage;
import Pages.ValidationPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_07_CreateAssetTest extends TestBase {

	protected String randomAsset;
	protected String random;

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_existingSerialNumber() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myAsset = new MyAssetsPage(driver);
		randomAsset = myAsset.selectRandomAsset();
		myAsset.clickOnAddAsset();
		AddAssetPage addAsset = new AddAssetPage(driver);
		addAsset.addingAssetProcedure(randomAsset);
		myAsset.enterSearchField(randomAsset);
		myAsset.verifyAsset(randomAsset);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.fetchAssetDetails(randomAsset);
	}

//	@Test(priority = 2)
	public void TC_02_newSerialNumber() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		MyAssetsPage myassets = new MyAssetsPage(driver);
		myassets.clickOnAddAsset();
		RandomInputPage randomInput = new RandomInputPage(driver);
		random = randomInput.selectRandomInput();
		AddAssetPage addassets = new AddAssetPage(driver);
		addassets.addingAssetProcedure(random);
		myassets.enterSearchField(random);
		myassets.verifyAsset(random);
		AssetDetailsPage assetdetails = new AssetDetailsPage(driver);
		assetdetails.fetchAssetDetails(random);
	}

//	@Test(priority = 3,,dependsOnMethods="TC_02_newSerialNumber")
	public void TC_03_createdAssetValidation() {

		driver.navigate().to(InitializePropertyFile.property.getProperty("NetSuite_URL"));
		NetsuiteLoginPage login = new NetsuiteLoginPage(driver);
		login.userLoginProcedure(InitializePropertyFile.property.getProperty("NetSuite_Username"),
				InitializePropertyFile.property.getProperty("NetSuite_Password"));
		login.enterAnswerToSecurityQuestion(InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_01"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_02"),
				InitializePropertyFile.property.getProperty("NetSuite_Security_Answer_03"));
		NetSuitePage netsuite = new NetSuitePage(driver);
		netsuite.displayNewAssetDetails(random);
		ValidationPage validation = new ValidationPage(driver);
		validation.getNetsuiteAssetSerialNumber();
		validation.assetSerialNumberValidation(random);
	}
}