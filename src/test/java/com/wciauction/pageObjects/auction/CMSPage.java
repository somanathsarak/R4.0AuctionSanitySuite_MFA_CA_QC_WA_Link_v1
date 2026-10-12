package com.wciauction.pageObjects.auction;
 
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 
import com.aventstack.extentreports.Status;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.itextpdf.layout.element.Paragraph;
 
 
public class CMSPage {
 
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public String textAfterEditDocumentFirstTitle;
	public String textBeforeEditDocumentFirstTitle;
 
	@FindBy(xpath = "//a[@href='/cms']")
	private WebElement cmsMenu;
 
	@FindBy(xpath = "//button[@id='HOME']")
	private WebElement hOMESectionUnderCMS;
 
	@FindBy(xpath = "//*[contains(text(),'Publications Pane')]")
	private WebElement publicationsPaneSubSection;
 
	@FindBy(xpath = "(//iframe[@title='Rich Text Area'])[1]")
	private WebElement iframeHeader;
 
	@FindBy(xpath = "(//*[contains(text(),'Publications Pane')]/following::input[@name='label'])[1]")
	private WebElement noticesText;
 
	@FindBy(xpath = "(//*[contains(text(),'Publications Pane')]/following::input[@name='label'])[2]")
	private WebElement releasesText;
 
	@FindBy(xpath = "//span[contains(text(),'SAVE')]")
	private WebElement saveButton;
 
	@FindBy(xpath = "//span[contains(text(),'PUBLISH')]")
	private WebElement publishButton;
 
	@FindBy(xpath = "//*[contains(text(),'Please revisit the modified pages after few minutes as it may take up to 5 minutes for the changes to be reflected.')]")
	private WebElement successMessageAfterPublish;
 
	@FindBy(xpath = "(//input[@name='title'])[4]")
	private WebElement documentTitleFourthAdded;
 
	@FindBy(xpath = "(//input[@name='title'])[3]")
	private WebElement documentTitleFourthAddedThird;
 
	@FindBy(xpath = "(//input[@name='title'])[3]")
	private WebElement documentTitleThirdAdded;
 
	@FindBy(xpath = "(//*[contains(text(),'Publications Pane')]/following::input[@name='url'])[1]")
	private WebElement urlFromFirstDocumentOfNotices;
 
	@FindBy(xpath = "(//*[contains(text(),'Publications Pane')]/following::span[contains(text(),'UPLOAD DOCUMENT')])[4]")
	private WebElement uploadButtonFromFirstDocumentOfNotices;
 
	@FindBy(xpath = "(//*[contains(text(),'Publications Pane')]/following::span[contains(text(),'UPLOAD DOCUMENT')])[3]")
	private WebElement uploadButtonFromThirdDocumentOfNotices;
 
	@FindBy(xpath = "//*[contains(text(),'File uploaded successfully.')]")
	private WebElement successMessageAfterFileUpload;
 
	@FindBy(xpath = "//*[contains(text(),'Success')]/following::button")
	private WebElement successMessageCloseButton;
 
	@FindBy(xpath = "(//span[contains(text(),'Add Document')])[1]")
	private WebElement addDocumentButtonOfNotices;
 
	@FindBy(xpath = "(//input[@name='date'])[4]")
	private WebElement dateAddedNewDocument;
 
	@FindBy(xpath = "(//input[@name='date'])[3]")
	private WebElement dateAddedNewDocumentThird;
 
	@FindBy(xpath = "//a[contains(@href,'/TestFile')]")
	private WebElement addedTestFile;
 
	@FindBy(xpath = "(//*[@value='Sample Document Added CMS']/following::*[@data-icon='trash-alt'])[1]")
	private WebElement deleteAddedDocument;
 
	@FindBy(xpath = "//span[contains(text(),'CONTINUE')]")
	private WebElement continueButton;
 
 
	public CMSPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}
 
	public void validateHomePage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Welcome to the WCI, Inc. Auction Platform", "Upcoming Events", "Administration"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/",
				expectedTexts
				);
	}
 
	public void validateHomePageWA () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Welcome to the Washington Allowance Auction and Allowance Price Containment Reserve Auction Platform", "Upcoming Events", "Administration"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/",
				expectedTexts
				);
	}
 
	public void validateReportPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Reports", "Search Reports"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/report",
				expectedTexts
				);
	}
 
	public void validateReportPageQC () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Reports", "Search Reports"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/report",
				expectedTexts
				);
	}
 
	public void validateMonitoringPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Submitted Bids", "SEARCH SUBMITTED BIDS BY", "Current Auction", "Advance Auction"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/auctionmonitoring",
				expectedTexts
				);
	}
 
	public void validateEventCreationPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Event Creation", "Event Basics", "Event Name - French"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/eventadministration/create",
				expectedTexts
				);
	}
 
	public void validateEventPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Event Administration", "List of Events", "CREATE NEW EVENT"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/eventadministration",
				expectedTexts
				);
	}
 
	public void validateUsersPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"User Administration", "List of Users", "How User Administration Works"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/useradministration",
				expectedTexts
				);
	}
 
	public void validateReservePricePage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Auction Reserve Price Administration", "How Auction Reserve Price Administration Works", "List of Events"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/reservepriceadministrations",
				expectedTexts
				);
	}
 
	public void validateReservePricePageWA () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Auction Floor Price Administration", "How Auction Floor Price Administration Works", "List of Events"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/reservepriceadministrations",
				expectedTexts
				);
	}
 
	public void validateReservePricePageQC () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Minimum Price Administration", "How Price Administration Works", "List of Events"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/reservepriceadministrations",
				expectedTexts
				);
	}
 
	public void validateReserveSaleCertificationPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Reserve Sale Certification", "List of Reserve Sales", "How Reserve Sale Certification Works", "TIER ONE SOLD"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/reservesalecertification",
				expectedTexts
				);
	}
 
	public void validateReserveSaleCertificationPageWA () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Allowance Price Containment Reserve Certification", "List of Allowance Price Containment Reserve", "How Allowance Price Containment Reserve Certification Works", "TIER ONE SOLD"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/reservesalecertification",
				expectedTexts
				);
	}
 
	public void validateSaleCertificationPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Sale by Mutual Agreement Certification", "List of Sale by Mutual Agreement", "How Sale by Mutual Agreement Certification Works", "CATEGORY A SOLD"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/sbmacertification",
				expectedTexts
				);
	}
 
	public void validateAuctionCertificationPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Auction Certification", "List of Auctions", "How Auction Certification Works", "CURRENT AUCTION SETTLEMENT PRICE"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/auctioncertification",
				expectedTexts
				);
	}
 
	public void validateAuctionCertificationPageQC () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Auction Certification", "List of Auctions", "How Auction Certification Works", "CURRENT FINAL SALE PRICE"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/auctioncertification",
				expectedTexts
				);
	}
 
	public void validateExecuteAPIPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Execute API", "Get Entities & Users", "GetQualifiedBidder", "GetBidLimitations"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/executeapi",
				expectedTexts
				);
	}
 
	public void validateExecuteAPIPageWA () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Execute API", "Get Entities & Users"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/executeapi",
				expectedTexts
				);
	}
 
	public void validateCMSPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Home", "Setup Credential", "Reset Security Question Section", "PREVIEW", "PUBLISH"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/cms",
				expectedTexts
				);
	}
 
	public void validateConfigurationsPage () {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		List<String> expectedTexts = Arrays.asList(
				"Configurations", "Login Settings", "Event Edit Settings", "Wiring Instructions", "Contact Us Page Email Address"
				);
		validatePageContent(
				driver,
				"https://www.practice-wci-auction.org/configuration",
				expectedTexts
				);
	}
 
	public static void validatePageContent(WebDriver driver, String url, List<String> expectedTexts) {
		// Step 1: Navigate to URL
		driver.get(url);
 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 
		// Step 3: Validate Page Title
		String pageTitle = driver.getTitle();
		Assert.assertNotNull(pageTitle, "Page title is null");
		Assert.assertNotEquals(pageTitle.trim(), "", "Page title is empty");
		System.out.println("Page title displayed as = " + pageTitle);
		ObjectRepo.getTest().log(Status.PASS, "Page title displayed as = " + pageTitle);
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
 
		// Step 4: Validate expected text using assertEquals
		WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		localWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		String fullPageText = driver.findElement(By.tagName("body")).getText();
 
		for (String expected : expectedTexts) {
			if (expected != null && !expected.trim().isEmpty()) {
				boolean isPresent = false;
				int retryCount = 0;
				// Retry up to 3 times if text not found
				while (!isPresent && retryCount < 3) {
					isPresent = fullPageText.contains(expected);
					if (!isPresent) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException ie) {
							Thread.currentThread().interrupt();
						}
						fullPageText = driver.findElement(By.tagName("body")).getText();
						retryCount++;
					}
				}
				try {
					Assert.assertEquals(isPresent, true, "Expected text NOT found on page: " + expected + "\nActual page text (truncated): " + fullPageText.substring(0, Math.min(500, fullPageText.length())));
					System.out.println("✅ Verified Text: " + expected);
					ObjectRepo.getTest().log(Status.PASS, "✅ Verified Text: " + expected);
					System.out.println("------------------------------------------");
					ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
				} catch (AssertionError ae) {
					System.out.println("❌ Assertion failed for text: " + expected);
					System.out.println("Actual page text (truncated): " + fullPageText.substring(0, Math.min(500, fullPageText.length())));
					throw ae;
				}
			}
		}
		System.out.println("🎉 Page validation completed successfully.");
		ObjectRepo.getTest().log(Status.PASS, "🎉 Page validation completed successfully.");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void deleteAddedDocumentFromNotices() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView(true);", deleteAddedDocument);
		wait.until(ExpectedConditions.elementToBeClickable(deleteAddedDocument));
		deleteAddedDocument.click();
		System.out.println("Step: Click on Delete icon corresponding to document");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Delete icon corresponding to document");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		continueButton.click();
		System.out.println("Step: Click on CONTINUE button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on CONTINUE button");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void validatePdfDownload() throws InterruptedException {
 
		wait.until(ExpectedConditions.elementToBeClickable(addedTestFile));
 
		// Store parent window
		String parentWindow = driver.getWindowHandle();
		Thread.sleep(5000);  // small wait to ensure stability
		// Click on PDF link (opens in new tab)
		try {
			wait.until(ExpectedConditions.elementToBeClickable(addedTestFile));
			addedTestFile.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(addedTestFile));
			addedTestFile.click();
		}
 
		// Wait for new tab to open
		wait.until(driver -> driver.getWindowHandles().size() > 1);
 
		// Switch to new tab
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}
 
		String currentUrl = driver.getCurrentUrl();
		System.out.println("PDF opened in new tab: " + currentUrl);
		ObjectRepo.getTest().log(Status.PASS, "PDF opened in new tab: " + currentUrl);
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
 
		// Optional validation
		Assert.assertTrue(currentUrl.contains("/TestFile"), "PDF is not opened properly!");
 
		// Close PDF tab
		driver.close();
 
		// Switch back to original tab
		driver.switchTo().window(parentWindow);
 
		System.out.println("Switched back to original tab successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Switched back to original tab successfully.");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void editTheNoticesDocumentThirdNewAdded() throws IOException {
		wait.until(ExpectedConditions.visibilityOf(documentTitleFourthAddedThird));
		documentTitleFourthAddedThird.sendKeys("Sample Document Added CMS");
		System.out.println("Step: Enter the document title");
		ObjectRepo.getTest().log(Status.PASS, "Step: Enter the document title");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateAddedNewDocumentThird.sendKeys("01 Jan 2031");
		System.out.println("Step: Enter the date for document");
		ObjectRepo.getTest().log(Status.PASS, "Step: Enter the date for document");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(uploadButtonFromThirdDocumentOfNotices));
		uploadFile(uploadButtonFromThirdDocumentOfNotices, createRandomPdf());
		System.out.println("Step: Click on Upload Document button and upload the file");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Upload Document button and upload the file");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(successMessageAfterFileUpload));
		String successMessage = successMessageAfterFileUpload.getText();
		System.out.println("Success Message after File Upload = " + successMessage);
		ObjectRepo.getTest().log(Status.PASS, "Success Message after File Upload = " + successMessage);
		Assert.assertEquals(successMessage, "File uploaded successfully.");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(successMessageCloseButton));
		js.executeScript("arguments[0].click();", successMessageCloseButton);
	}
 
	public void editTheNoticesDocumentFourthNewAdded() throws IOException {
		wait.until(ExpectedConditions.visibilityOf(documentTitleFourthAdded));
		documentTitleFourthAdded.sendKeys("Sample Document Added CMS");
		System.out.println("Step: Enter the document title");
		ObjectRepo.getTest().log(Status.PASS, "Step: Enter the document title");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		dateAddedNewDocument.sendKeys("01 June 2031");
		System.out.println("Step: Enter the date for document");
		ObjectRepo.getTest().log(Status.PASS, "Step: Enter the date for document");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(uploadButtonFromFirstDocumentOfNotices));
		uploadFile(uploadButtonFromFirstDocumentOfNotices, createRandomPdf());
		System.out.println("Step: Click on Upload Document button and upload the file");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Upload Document button and upload the file");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(successMessageAfterFileUpload));
		String successMessage = successMessageAfterFileUpload.getText();
		System.out.println("Success Message after File Upload = " + successMessage);
		ObjectRepo.getTest().log(Status.PASS, "Success Message after File Upload = " + successMessage);
		Assert.assertEquals(successMessage, "File uploaded successfully.");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(successMessageCloseButton));
		js.executeScript("arguments[0].click();", successMessageCloseButton);
	}
 
	public void clickingOnDddDocumentButtonOfNotices() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(addDocumentButtonOfNotices));
		addDocumentButtonOfNotices.click();
		System.out.println("Step: Click on Add Document Button Of Notices");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Add Document Button Of Notices");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void clickingOnPublishButton() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(publishButton));
		publishButton.click();
		System.out.println("Step: Click on PUBLISH button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on PUBLISH button");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(successMessageAfterPublish));
		String successMessage = successMessageAfterPublish.getText();
		System.out.println("Success Message after publish = " + successMessage);
		ObjectRepo.getTest().log(Status.PASS, "Success Message after publish = " + successMessage);
		Assert.assertEquals(successMessage, "Please revisit the modified pages after few minutes as it may take up to 5 minutes for the changes to be reflected.");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(successMessageCloseButton));
		successMessageCloseButton.click();
	}
 
	public void clickingOnSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		js.executeScript("arguments[0].click();", saveButton);
		System.out.println("Step: Click on SAVE button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on SAVE button");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void clickingOnPublicationsPaneSectionUnderCMS() {
		wait.until(ExpectedConditions.elementToBeClickable(publicationsPaneSubSection));
		publicationsPaneSubSection.click();
		System.out.println("Step: Click on Publications Pane Sub Section");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Publications Pane Sub Section");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void clickingOnHomeSectionUnderCMS() {
		wait.until(ExpectedConditions.elementToBeClickable(hOMESectionUnderCMS));
		hOMESectionUnderCMS.click();
		System.out.println("Step: Click on Home section");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on Home section");
		System.out.println("------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
	}
 
	public void navigateOnCMSPage() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try{
			wait.until(ExpectedConditions.elementToBeClickable(cmsMenu));
			cmsMenu.click();
			System.out.println("Step: Click on CMS Menu");
			ObjectRepo.getTest().log(Status.PASS, "Step: Click on CMS Menu");
			System.out.println("------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		}
		catch(Exception e){
			wait.until(ExpectedConditions.elementToBeClickable(cmsMenu));
			cmsMenu.click();
			System.out.println("Step: Click on CMS Menu");
			ObjectRepo.getTest().log(Status.PASS, "Step: Click on CMS Menu");
			System.out.println("------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "------------------------------------------");
		}
	}
 
	// ======================= FILE UPLOAD (UNCHANGED) =======================
	public static void uploadFile(WebElement uploadElement, String relativePath) {
 
		String filePath = relativePath;
 
		try {
			uploadElement.sendKeys(filePath);
		} catch (Exception e) {
			try {
				uploadElement.click();
				StringSelection selection = new StringSelection(filePath);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
 
				Robot robot = new Robot();
				robot.delay(2000);
 
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
 
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
 
			} catch (Exception ex) {
				throw new RuntimeException("File upload failed", ex);
			}
		}
	}
 
	public static String createRandomPdf() throws IOException {
 
		String timeStamp = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
 
		String randomFileName = "TestFile_" + timeStamp + ".pdf";
 
		// OS independent folder path
		Path folderPath = Paths.get(System.getProperty("user.dir"), "GeneratedPDFs");
 
		// Create folder if not exists
		if (!Files.exists(folderPath)) {
			Files.createDirectories(folderPath);
		}
 
		// ✅ Clear existing PDF files before creating new one
		Files.list(folderPath)
		.filter(path -> path.toString().endsWith(".pdf"))
		.forEach(path -> {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
 
		// Create new file path
		Path fullPath = folderPath.resolve(randomFileName);
 
		PdfWriter writer = new PdfWriter(fullPath.toString());
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);
 
		Random random = new Random();
 
		document.add(new Paragraph("Auto Generated PDF"));
		document.add(new Paragraph("Random Number: " + random.nextInt(10000)));
		document.add(new Paragraph("Execution Time: " + timeStamp));
 
		document.close();
 
		System.out.println("PDF Created: " + fullPath.toAbsolutePath());
		ObjectRepo.getTest().log(Status.PASS, "PDF Created: " + fullPath.toAbsolutePath());
 
		return fullPath.toAbsolutePath().toString();
	}
 
 
	public static WebDriver getDriver() {
 
		String downloadPath = System.getProperty("user.dir") + "\\Downloads";
 
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);   // ✅ IMPORTANT
		prefs.put("plugins.always_open_pdf_externally", true);  // ✅ VERY IMPORTANT
 
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
 
		options.addArguments("--start-maximized");  // optional but good
 
		// ✅ If running headless (VERY IMPORTANT for CI)
		// options.addArguments("--headless=new");
 
		return new ChromeDriver(options);
	}
 
 
	public static void clearDownloadFolder(String downloadDir) {
		File folder = new File(downloadDir);
		if (folder.exists()) {
			for (File file : folder.listFiles()) {
				file.delete();
			}
		}
	}
 
	public boolean waitForFileDownload(String downloadDir, String filePrefix, int timeoutSeconds) {
 
		File folder = new File(downloadDir);
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);
 
		while (System.currentTimeMillis() < endTime) {
 
			File[] files = folder.listFiles();
 
			if (files != null) {
 
				for (File file : files) {
 
					System.out.println("Checking file: " + file.getName());
 
					if (file.getName().startsWith(filePrefix)
&& file.getName().toLowerCase().endsWith(".pdf")
&& file.length() > 0) {     // ✅ ensure file is not empty
 
						System.out.println("Download completed: " + file.getName());
						return true;
					}
				}
			}
 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
 
		System.out.println("Download not completed within timeout.");
		ObjectRepo.getTest().log(Status.PASS, "Download not completed within timeout.");
		return false;
	}
 
 
}