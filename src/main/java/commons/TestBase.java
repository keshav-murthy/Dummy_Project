package commons;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.ListenerTest;

@Listeners(ListenerTest.class)
public class TestBase {

	public WebDriver driver;

	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		this.driver = createDriver();
		setUpWebDriver(driver);
		InitializePropertyFile.loadPropertyFile();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			// driver.close();
			driver.quit();
		}
	}

	private ChromeDriver createDriver() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = (ChromeOptions) getOptions("chrome");
		return new ChromeDriver(option);

//		WebDriverManager.firefoxdriver().setup();
//		return new FirefoxDriver();
	}

	private MutableCapabilities getOptions(String browserName) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(browserName);

		if (browserName.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			return options.merge(cap);
		}

		if (browserName.equals("internetExplorer")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			return options.merge(cap);
		}

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-gpu");
			options.addArguments("--disable-print-preview");
			options.addArguments("--disable-web-security");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--incognito");
			return options.merge(cap);
		}

		throw new RuntimeException(browserName + " is an invalid browserName.");
	}

	private static void setUpWebDriver(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
	}

//	public void maintainSameSession() {
//		Set<Cookie> allCookies = driver.manage().getCookies();
//		for (Cookie cookie : allCookies) {
//			driver.manage().addCookie(cookie);
//		}
//	}
}