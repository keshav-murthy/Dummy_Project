package TestCases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Pages.ExportPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.ReportDashboardPage;
import commons.InitializePropertyFile;
import listener.ListenerTest;

@Listeners(ListenerTest.class)
public class SC_11_ExportToCSVTest {

	public static String username = "rwalter+1st@barcodesinc.com";
	public static String password = "barcodes123";
	public static String Widget_Title = "Total Tickets MTD";
	public WebDriver driver;
	ExportPage export = new ExportPage(driver);

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {

		driver = export.setUpWebDriver();
		InitializePropertyFile.loadPropertyFile();
	}

	@Test(priority = 1)
	public void TC_01_dashboardExport() throws IOException {

		export.againHittingUrl();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(username, password);
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
		dashboard.clickOnViewReport(Widget_Title);
		export.downloadingFile();
	}

	@Test(priority = 2)
	public void TC_02_myTicketsExport() throws IOException {

		export.againHittingUrl();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(username, password);
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyTickets();
		export.downloadingFile();
	}

	@Test(priority = 3)
	public void TC_03_myAssetsExport() throws IOException {

		export.againHittingUrl();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(username, password);
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyAssets();
		export.downloadingFile();
	}

	@Test(priority = 4)
	public void TC_04_myContractsExport() throws InterruptedException, IOException {

		export.againHittingUrl();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(username, password);
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
		assetsandservices.clickOnMyContracts();
		export.downloadingFile();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			// driver.close();
			driver.quit();
		}
	}
}