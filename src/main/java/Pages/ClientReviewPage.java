package Pages;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class ClientReviewPage extends BasePage {

	@FindBy(xpath = "//a[contains(text(),'Close')]")
	WebElement closeCookies;

	@FindBy(xpath = "//h3//a")
	List<WebElement> companyName;

	@FindBy(xpath = "//div[@class='reviews-link']//a")
	WebElement reviews;

	@FindBy(xpath = "//h5[contains(text(),'the project ')]//parent::div//child::h2//a")
	List<WebElement> project;

	@FindBy(xpath = "//div[@class='field field-name-project-type field-inline']//div[@class='field-item']//span")
	List<WebElement> companyType;

	@FindBy(xpath = "//div[@class='abs-aligned']//div[@class='field field-name-cost field-inline']//div[@class='field-item'][1]")
	List<WebElement> estimation;

	@FindBy(xpath = "//div[@class='abs-aligned']//div[@class='field field-name-project-length field-inline']//div[@class='field-item'][1]")
	List<WebElement> duration;

	@FindBy(xpath = "//div[@class='group-reviewer']//div[@class='field-name-title']//div[@class='field-item']")
	List<WebElement> reviewer;

	@FindBy(xpath = "//div[@class='group-reviewer']//div[@class='field-name-full-name-display']//div[@class='field-item']")
	List<WebElement> reviewerName;

	@FindBy(xpath = "//div[@class='group-interview']//div[@class='field-name-user-industry field-inline']//div")
	List<WebElement> clientType;

	@FindBy(xpath = "//div[@class='group-interview']//div[@class='field-name-company-size field-inline']//div")
	List<WebElement> totalEmployees;

	@FindBy(xpath = "//div[@class='group-interview']//div[@class='field-name-location field-inline']//div")
	List<WebElement> location;

	@FindBy(xpath = "//div[@class='field-items-buttons']//button//i[@class='arrow']")
	List<WebElement> fullReview;

	@FindBy(xpath = "//div[@class='field-item']//p//strong")
	List<WebElement> question;

	@FindBy(xpath = "//div[@class='field-item']//p[2]")
	List<WebElement> answer;

	@FindBy(xpath = "//button[@title='Save']")
	WebElement save;

	private static final Logger lOGGER = LogManager.getLogger(ClientReviewPage.class.getName());

	public ClientReviewPage(WebDriver driver) {
		super(driver);
	}

	public void selectCompany(Xls_Reader reader, String sheetName) throws IOException {

		wait.forElementToBeVisible(closeCookies);
		click(closeCookies);

		for (int k = 0; k < companyName.size(); k++) {

			String companyInExcel = reader.getCellData(sheetName, "Company Name", (k * 10) + 2);
			String companyInApplication = companyName.get(k).getText();
			String nextCellData = reader.getCellData(sheetName, "Projects", (k * 10) + 2);

			if ((companyInExcel.contains(companyInApplication) == false) || (nextCellData.equals(""))) {

				companyName.get(k).click();
				reader.setCellData(sheetName, "Company Name", (k * 10) + 2, companyInApplication);

				windowHandling();
				lOGGER.info("Switched to new window");

				for (int i = 0; i < project.size(); i++) {
					wait.forPage();

					try {
						reader.setCellData(sheetName, "Projects", (k * 10) + i + 2, project.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Projects", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Project Type", (k * 10) + i + 2, companyType.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Project Type", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Estimation", (k * 10) + i + 2, estimation.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Estimation", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Duration", (k * 10) + i + 2, duration.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Duration", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Reviewer", (k * 10) + i + 2, reviewer.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Reviewer", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Reviewer Name", (k * 10) + i + 2, reviewerName.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Reviewer Name", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Client Industry", (k * 10) + i + 2, clientType.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Client Industry", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Total Employees", (k * 10) + i + 2,
								totalEmployees.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Total Employees", (k * 10) + i + 2, " ");
					}

					try {
						reader.setCellData(sheetName, "Location", (k * 10) + i + 2, location.get(i).getText());
					} catch (IndexOutOfBoundsException e) {
						reader.setCellData(sheetName, "Location", (k * 10) + i + 2, " ");
					}
				}
				defaultWindow();
			}
		}
	}
}