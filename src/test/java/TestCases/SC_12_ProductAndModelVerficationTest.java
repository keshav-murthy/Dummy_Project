package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.DesktopPrinterPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MobileComputerPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_12_ProductAndModelVerficationTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test(priority = 1)
	public void TC_01_verifyMobileScanner() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.selectMobileScanner();
		MobileComputerPage mobile = new MobileComputerPage(driver);
		mobile.verifyProductTitle();
		mobile.verifyProductDescription();
		mobile.verifyProducts();
	}

//	@Test(priority = 2)
	public void TC_02_verifyDesktopPrinters() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.selectDesktopPrinter();
		DesktopPrinterPage desktop = new DesktopPrinterPage(driver);
		desktop.verifyProductTitle();
		desktop.verifyProductDescription();
		desktop.verifyProducts();
		desktop.selectRandomModel();
		desktop.verifyModel();
	}
}