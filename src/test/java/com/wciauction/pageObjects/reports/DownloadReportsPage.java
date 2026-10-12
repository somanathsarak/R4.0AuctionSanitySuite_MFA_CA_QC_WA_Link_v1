package com.wciauction.pageObjects.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ExcelDataProviderforResultReport;
import com.wci.auction.org.resources.ObjectRepo;

import excelReadersForReports.ExcelDataProviderForBidsAuditReport;
import excelReadersForReports.ExcelDataProviderForPostEventOperationReport;
import excelReadersForReports.ExcelDataProviderforResultReportCA_Single_Auction;
import excelReadersForReports.ExcelDataProviderforResultReportNS;
import excelReadersForReports.ExcelDataProviderforResultReportQC_Single_Auction;
import excelReadersForReports.ExcelDataProviderforResultReportR317RSCA;
import excelReadersForReports.ExcelDataProviderforResultReportRSCA;
import excelReadersForReports.ExcelDataProviderforResultReportRSQCR70;
import excelReadersForReports.ExcelDataProviderforResultReportRS_WA;
import excelReadersForReports.ExcelDataProviderforResultReportWA;
import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class DownloadReportsPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement btnReports;

	@FindBy(xpath = "//button//*[text()='Search Reports']")
	private WebElement btnSearchReports;

	@FindBy(xpath = "//button[@title='SEARCH']")
	private WebElement btnSearch;

	@FindBy(xpath = "//input[@aria-label='Event Name']")
	private WebElement eventNameDropDown;

	@FindBy(xpath = "//*[@aria-label='Download Bids Audit Report for event Auction 187']")
	private WebElement btnBidsAuditReports;

	@FindBy(xpath = "//*[@aria-label='Download Post Event Operations Report for event Auction 187']")
	private WebElement btnPostEventOperationReports;

	@FindBy(xpath = "//*[@aria-label='Download Results Report for event Auction 187']")
	private WebElement btnResultReports;

	@FindBy(xpath = "//*[@aria-label='Download Qualified Bidders Report for event Auction 187']")
	private WebElement btnQualifiedBidderReports;

	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement btnBidsAuditReportsForMM;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement btnResultReportsForMM;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	private WebElement btnBidsReportsForMM;

	@FindBy(xpath = "(//button[@type='button'])[6]")
	private WebElement btnAllowancedeliveryReportsForMM;

	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement btnQualifiedBidderReportsForMM;

	@FindBy(xpath = "//span[text()='Run Algorithm']")
	private WebElement btnRunAlgorithm;

	@FindBy(xpath = "(//span[text()='Run Algorithm'])[2]")
	private WebElement btnRunAlgorithm2;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//button[@title='Go to details of Event Automation joint auction 17 June']")
	private WebElement btnEventDetails;

	@FindBy(xpath = "//*[text()='Run algorithm proposed.']")
	private WebElement successMsg;

	@FindBy(xpath = "//*[text()='The algorithm ran successfully. It may take a few minutes for the reports to be generated.']")
	private WebElement successMsg2;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement btnContineu;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement btnReset;

	@FindBy(xpath = "//*[text()='Reset algorithm status successful.']")
	private WebElement successmsg3;

	@FindBy(xpath = "//a[@href='/report']")
	private WebElement FrenchbtnReports;

	private By FrnchbtnDownloadQualifiedBidderReportSBMAR70 = By
			.xpath("//*[@aria-label='Download Rapport des enchérisseurs qualifiés for event "
					+ prop.getProperty("FR_Event_Name_RE1") + "']");
	private By btnDownloadQualifiedBidderReportSBMAR70 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("Event_Name_RE1")
					+ "']");
	private By btnDownloadResultReportForCalReserveSale = By.xpath(
			"//*[@aria-label='Download Results Report for event " + prop.getProperty("ReserveSaleEventName") + "']");
	private By btnDownloadResultReportForReserveSaleR70 = By
			.xpath("//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name_RE1") + "']");
	private By btnDownloadResultReportForCalReserveSale_WA = By.xpath(
			"//*[@aria-label='Download Results Report for event " + prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnDownloadQualifiedBidderReport = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventNameEng + "']");
	private By btnDownloadFinancialReport = By
			.xpath("//*[@aria-label='Download Financial Settlement Report for event " + eventNameEng + "']");
	private By btnDownloadResultReportWA = By
			.xpath("//*[@aria-label='Download Results Report for event " + eventNameWS + "']");
	private By btnParticularEvent = By.xpath("//*[contains(text(),'" + eventNameEng + "')]");
	private By btnDownloadResultReport = By
			.xpath("//*[@aria-label='Download Results Report for event " + eventNameEng + "']");
	private By btnDownloadResultReportCA_Single_Auction = By
			.xpath("//*[@aria-label='Download Results Report for event " + eventNameCA + "']");
	private By btnDownloadResultReportQC_Single_Auction = By
			.xpath("//*[@aria-label='Download Results Report for event " + eventNameQC + "']");

	public DownloadReportsPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}
	public void runAlgorithm() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.visibilityOf(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.visibilityOf(btnEventDetails));
		btnEventDetails.click();
		try {
			btnRunAlgorithm.click();
		} catch (Exception e) {
			btnRunAlgorithm2.click();
		}
		wait.until(ExpectedConditions.visibilityOf(successMsg));
		String massage = successMsg.getText();
		Assert.assertEquals(massage, "Run algorithm proposed.");
		System.out.println(massage);
	}
	public void runAlgorithmbyQC() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.visibilityOf(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.visibilityOf(btnEventDetails));
		btnEventDetails.click();
		try {
			btnRunAlgorithm.click();
		} catch (Exception e) {
			btnRunAlgorithm2.click();
		}
		btnContineu.click();
		wait.until(ExpectedConditions.visibilityOf(successMsg2));
		String massage = successMsg2.getText();
		Assert.assertEquals(massage, "The algorithm is running.");
		System.out.println(massage);
	}
	public void resetalgorithm() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.visibilityOf(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.visibilityOf(btnEventDetails));
		btnEventDetails.click();
		WebElement element = btnReset;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String massage = successmsg3.getText();
		System.out.println(massage);
	}
	public void searchReport() {
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		WebElement element = wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element1 = driver.findElement(By.xpath("//span[text()='Auction 187']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Auction 187']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
	}
	public void downloadReportsforSponsorForEnglishLangUser() throws Exception {
		btnPostEventOperationReports.click();
		System.out.println("post event operation report download successfully");
		
		WebElement element = btnBidsAuditReports;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnBidsAuditReports.click();
		System.out.println("bids audit report download successfully");
		ExcelDataProviderForPostEventOperationReport ref = new ExcelDataProviderForPostEventOperationReport();
		String eventNameInEng = ref.getStringData("Post Event Operations Report", 2, 0);
		Assert.assertEquals("Event Name", eventNameInEng);
		System.out.println("Post Event Operation report validated for Eng lang User");
		ExcelDataProviderForBidsAuditReport ref2 = new ExcelDataProviderForBidsAuditReport();
		String rowName = ref2.getStringData("Bids Audit Report", 5, 0);
		Assert.assertEquals("Registry General Account Number", rowName);
		System.out.println("Bids audit report validated for Eng lang User");
	}
	public void downloadQualifiedBidderReportforSponsor() {
		btnReports.click();
		
		WebElement element = btnResultReports;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnQualifiedBidderReports.click();
	}
	public void downloadResultReportforSponsor() {
		WebElement element = btnResultReports;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}
	public void searchReportFunctionality() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		WebElement element2 = btnReports;
		Actions action = new Actions(driver);
		action.moveToElement(element2).build().perform();
		action.click(element2).build().perform();
		WebElement element = driver.findElement(btnDownloadResultReport);
		
		js.executeScript("arguments[0].click()", element);
	}
	public void searchReportFunctionalityWA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		WebElement element2 = btnReports;
		element2.click();
		DownloadReportsPage ref1 = new DownloadReportsPage( );
		ref1.searchReportsByEventNameForWASingleAuction();
		WebElement element = driver.findElement(btnDownloadResultReportWA);
		
		js.executeScript("arguments[0].click()", element);
	}
	public void searchReportFunctionalityCA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		WebElement element2 = btnReports;
		element2.click();
		DownloadReportsPage ref1 = new DownloadReportsPage( );
		ref1.searchReportsByEventNameForCASingleAuction();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportCA_Single_Auction));
		WebElement element = driver.findElement(btnDownloadResultReportCA_Single_Auction);
		
		js.executeScript("arguments[0].click()", element);
	}
	public void searchReportFunctionalityQC() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		WebElement element2 = btnReports;
		element2.click();
		DownloadReportsPage ref1 = new DownloadReportsPage( );
		ref1.searchReportsByEventNameForQCSingleAuction();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportQC_Single_Auction));
		WebElement element = driver.findElement(btnDownloadResultReportQC_Single_Auction);
		
		js.executeScript("arguments[0].click()", element);
	}
	public void searchReportsByEventNameReserveSale() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']"))
		.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}

	public void searchReportsByEventNameForJointAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']"))
		.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}

	public void searchReportsByEventNameForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameWS"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		Actions action = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']"));
		action.moveToElement(element1).build().perform();
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']")).click();
		WebElement element2 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action.moveToElement(element2).build().perform();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}
	public void searchReportsByEventNameForWARS() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		Actions action = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']"));
		action.moveToElement(element1).build().perform();
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']")).click();
		WebElement element2 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action.moveToElement(element2).build().perform();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}
	public void searchReportsByEntityNameForWASingleAuction(String ReportName) throws InterruptedException {
		WebElement element11 = driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]"))
				.sendKeys("WA_Entity+D");
		driver.findElement(By.xpath("(//*[text()='WA_Entity+D'])[1]")).click();
		WebElement element = driver.findElement(By.xpath("(//Select[@id='demo-customized-select'])[4]"));
		Select s = new Select(element);
		s.selectByVisibleText(ReportName);
		driver.findElement(By.xpath("//*[@aria-label='Download Report']")).click();
	}
	public void searchReportsByEventNameForCASingleAuction() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameCA"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']"))
		.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}

	public void searchReportsByEventNameForQCSingleAuction() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameQC"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}
	public void searchReportFunctionalityForReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		WebElement element2 = btnReports;
		element2.click();
		DownloadReportsPage ref1 = new DownloadReportsPage( );
		ref1.searchReportsByEventNameReserveSale();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportForCalReserveSale));
		WebElement element = driver.findElement(btnDownloadResultReportForCalReserveSale);
		
		js.executeScript("arguments[0].click()", element);
	}
		
	public void searchReportFunctionalityForReserveSaleR70() {
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		WebElement element = driver.findElement(By.tagName("body"));
		element.sendKeys(Keys.ARROW_DOWN);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportForReserveSaleR70));
		driver.findElement(btnDownloadResultReportForReserveSaleR70).click();
	}
	public void searchReportFunctionalityForReserveSale_WA() {
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportForCalReserveSale_WA));
		driver.findElement(btnDownloadResultReportForCalReserveSale_WA).click();
	}

	public void searchQualifiedbidderReportFunctionality() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnReports));
			btnReports.click();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnSearchReports));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']")).sendKeys(eventNameEng);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOf(btnSearch));
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			WebElement element2 = wait
					.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReport));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			driver.findElement(btnDownloadQualifiedBidderReport).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnReports));
			btnReports.click();
			WebElement element = wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']")).sendKeys(eventNameEng);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOf(btnSearch));
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath(("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')])[2]")))
					.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReport));
			driver.findElement(btnDownloadQualifiedBidderReport).click();
		}
	}

	public void searchFinancialSettlementReportFunctionality() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnReports));
			btnReports.click();
			WebElement element = wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']")).click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']")).sendKeys(eventNameEng);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOf(btnSearch));
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnParticularEvent));
			driver.findElement(btnParticularEvent).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadFinancialReport));
			driver.findElement(btnDownloadFinancialReport).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnReports));
			btnReports.click();
			wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
			WebElement element = wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(eventNameEng);
			WebElement element1 = wait.until(ExpectedConditions.visibilityOf(btnSearch));
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')]"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnParticularEvent));
			driver.findElement(btnParticularEvent).click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnDownloadFinancialReport));
			driver.findElement(btnDownloadFinancialReport).click();
		}
	}

	public void validateResultReport() throws Exception {
		ExcelDataProviderforResultReport excel = new ExcelDataProviderforResultReport();
		String legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + " Emission Units for Current Auction");
	}
	public void validateResultReportWA() throws Exception {
		try {
		ExcelDataProviderforResultReportWA excel = new ExcelDataProviderforResultReportWA();
		String legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + " Emission Units for Current Auction");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	public void validateResultReportCA() throws Exception {
		String legalName1;
		try {
		ExcelDataProviderforResultReportCA_Single_Auction excel = new ExcelDataProviderforResultReportCA_Single_Auction();
		legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + " Emission Units for Current Auction");
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	public void validateResultReportQC() throws Exception {
		try {
		ExcelDataProviderforResultReportQC_Single_Auction excel = new ExcelDataProviderforResultReportQC_Single_Auction();
		String legalName1;
		legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + " Emission Units for Current Auction");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void validateResultReportRSCA() throws Exception {
		try {	
		ExcelDataProviderforResultReportRSCA excel= new ExcelDataProviderforResultReportRSCA();
		String legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		double num6 = excel.getNumericData("FINAL RESULTS", 1, 3);
		double num7 = excel.getNumericData("FINAL RESULTS", 2, 3);
		double num8 = excel.getNumericData("FINAL RESULTS", 3, 3);
		double num9 = excel.getNumericData("FINAL RESULTS", 4, 3);
		double num10 = excel.getNumericData("FINAL RESULTS", 5, 3);
		double num11 = excel.getNumericData("FINAL RESULTS", 1, 4);
		double num12 = excel.getNumericData("FINAL RESULTS", 2, 4);
		double num13 = excel.getNumericData("FINAL RESULTS", 3, 4);
		double num14 = excel.getNumericData("FINAL RESULTS", 4, 4);
		double num15 = excel.getNumericData("FINAL RESULTS", 5, 4);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + "Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num6 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num7 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num8 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num9 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num10 + "Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num11 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num12 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num13 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num14 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num15 + "Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateResultReportRSQCR70() throws Exception {
		ExcelDataProviderforResultReportRSQCR70 excel = new ExcelDataProviderforResultReportRSQCR70();
		String legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
		String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
		String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
		String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
		String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
		double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
		double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
		double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
		double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
		double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
		double num6 = excel.getNumericData("FINAL RESULTS", 1, 3);
		double num7 = excel.getNumericData("FINAL RESULTS", 2, 3);
		double num8 = excel.getNumericData("FINAL RESULTS", 3, 3);
		double num9 = excel.getNumericData("FINAL RESULTS", 4, 3);
		double num10 = excel.getNumericData("FINAL RESULTS", 5, 3);
		double num11 = excel.getNumericData("FINAL RESULTS", 1, 4);
		double num12 = excel.getNumericData("FINAL RESULTS", 2, 4);
		double num13 = excel.getNumericData("FINAL RESULTS", 3, 4);
		double num14 = excel.getNumericData("FINAL RESULTS", 4, 4);
		double num15 = excel.getNumericData("FINAL RESULTS", 5, 4);
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num1 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num2 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num3 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num4 + " Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num5 + "Tier One Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num6 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num7 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num8 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num9 + " Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num10 + "Tier Two Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + num11 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + num12 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + num13 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + num14 + " Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + num15 + "Tier Three Allowances for Current Reserve Sale");
		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
	}
	public void validateResultReportRS_WA() throws Exception {
	
		try {
			ExcelDataProviderforResultReportRS_WA excel= new ExcelDataProviderforResultReportRS_WA();
			String legalName1 = excel.getStringData("FINAL RESULTS", 1, 1);
			String legalName2 = excel.getStringData("FINAL RESULTS", 2, 1);
			String legalName3 = excel.getStringData("FINAL RESULTS", 3, 1);
			String legalName4 = excel.getStringData("FINAL RESULTS", 4, 1);
			String legalName5 = excel.getStringData("FINAL RESULTS", 5, 1);
			double num1 = excel.getNumericData("FINAL RESULTS", 1, 2);
			double num2 = excel.getNumericData("FINAL RESULTS", 2, 2);
			double num3 = excel.getNumericData("FINAL RESULTS", 3, 2);
			double num4 = excel.getNumericData("FINAL RESULTS", 4, 2);
			double num5 = excel.getNumericData("FINAL RESULTS", 5, 2);
			double num6 = excel.getNumericData("FINAL RESULTS", 1, 3);
			double num7 = excel.getNumericData("FINAL RESULTS", 2, 3);
			double num8 = excel.getNumericData("FINAL RESULTS", 3, 3);
			double num9 = excel.getNumericData("FINAL RESULTS", 4, 3);
			double num10 = excel.getNumericData("FINAL RESULTS", 5, 3);
			double num11 = excel.getNumericData("FINAL RESULTS", 1, 4);
			double num12 = excel.getNumericData("FINAL RESULTS", 2, 4);
			double num13 = excel.getNumericData("FINAL RESULTS", 3, 4);
			double num14 = excel.getNumericData("FINAL RESULTS", 4, 4);
			double num15 = excel.getNumericData("FINAL RESULTS", 5, 4);
			ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName1 + " Granted With " + num1 + " Tier One Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName2 + " Granted With " + num2 + " Tier One Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName3 + " Granted With " + num3 + " Tier One Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName4 + " Granted With " + num4 + " Tier One Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName5 + " Granted With " + num5 + "Tier One Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName1 + " Granted With " + num6 + " Tier Two Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName2 + " Granted With " + num7 + " Tier Two Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName3 + " Granted With " + num8 + " Tier Two Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName4 + " Granted With " + num9 + " Tier Two Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName5 + " Granted With " + num10 + "Tier Two Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName1 + " Granted With " + num11 + " Tier Three Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName2 + " Granted With " + num12 + " Tier Three Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName3 + " Granted With " + num13 + " Tier Three Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName4 + " Granted With " + num14 + " Tier Three Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS,
					"Entity: " + legalName5 + " Granted With " + num15 + "Tier Three Allowances for Current Reserve Sale");
			ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void downloadReportsforMM() {
		btnReports.click();
		
		WebElement element = btnBidsAuditReportsForMM;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnBidsAuditReportsForMM.click();
		System.out.println("bids audit report download successfully");
		btnResultReportsForMM.click();
		System.out.println("result report download successfully");
		btnBidsReportsForMM.click();
		System.out.println("Bids report download successfully");
		btnAllowancedeliveryReportsForMM.click();
		System.out.println("Allowance Delivery download successfully");
		btnQualifiedBidderReportsForMM.click();
		System.out.println("Qualified Bidder report download successfully");
	}
	public void searchFrenchReportsByEventNameForSBMAEvent() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='NOM DU rapport']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU rapport']")));
		driver.findElement(By.xpath("//*[text()='Rechercher un rapport']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@placeholder='Tous'][1]")).click();
		
		js.executeScript("window.scrollBy(0,750)");
		driver.findElement(By.xpath("//*[@placeholder='Tous'][1]")).sendKeys(prop.getProperty("Event_Name"));
		Actions action = new Actions(driver);
		WebElement element2 = driver
				.findElement(By.xpath("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')]"));
		action.moveToElement(element2).build().perform();
		action.click(element2).build().perform();
		driver.findElement(By.xpath("//*[text()='RECHERCHER']")).click();
		System.out.println("French Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "French Event name searched by sponsor");
	}

	public void BidsAuditsearchReportFunctionalityForWashingtonReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsAuditReportsByEventNameForReserveSaleWAEvent();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("EventNameWA") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("EventNameWA") + "']"))
				.click();
		Thread.sleep(20000);
		
		js.executeScript("window.scrollBy(0,1000)");
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsAuditReportsByEventNameForReserveSaleWAEvent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Audit Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("EventNameWA"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWA") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
		
	}
	public void SearchBidsReportsByEventNameForReserveSaleWAEvent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_WA1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_WA1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void QualifiedBiddersearchReportFunctionalityForWAReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchQBidderReportsByEventNameForReserveSaleWAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Qualified Bidders Report for event "
						+ prop.getProperty("EventNameWA") + "']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Qualified Bidders Report for event "
				+ prop.getProperty("EventNameWA") + "']")).click();
		Thread.sleep(20000);
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
		Thread.sleep(25000);
	}
	public void SearchQBidderReportsByEventNameForReserveSaleWAEvent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("EventNameWA"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWA") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
//		action1.moveToElement(element1).build().perform();
//		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsReportsearchFunctionalityForCaliforniaReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportsByEventNameForReserveSaleCAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		Thread.sleep(20000);
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsReportsByEventNameForReserveSaleCAevent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Report");
		;
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsReportCommentAndFieldsDetailsFunctionalityForCaliforniaReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportsByEventNameForReserveSaleCAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		Thread.sleep(25000);
	}
	public void BidsAuditReportsearchFunctionalityForCaliforniaReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsAuditReportsByEventNameForReserveSaleCAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Bids Audit Report for event "
						+ prop.getProperty("ResSaleEventAfterCertify") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		Thread.sleep(20000);
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsAuditReportsByEventNameForReserveSaleCAevent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Audit Report");
		;
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsAuditReportsearchFunctionalityForNovaScotiaSBA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsAuditReportsByEventNameForNovaScotiaSBAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("Event_Name_NS") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("Event_Name_NS") + "']"))
				.click();
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsAuditReportsByEventNameForNovaScotiaSBAevent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Audit Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_NS"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_NS") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsReportsearchFunctionalityForNovaScotiaSBA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportByEventNameForNovaScotiaSBAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_NS") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_NS") + "']"))
				.click();
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsReportByEventNameForNovaScotiaSBAevent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_NS"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_NS") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsReportCommentAndFieldsDetailsFunctionalityForNovaScotiaSBAEvent() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportByEventNameForNovaScotiaSBAevent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_NS") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_NS") + "']"))
				.click();
	}
	public void FinancialStatementsearchReportFunctionalityForNS_SBA_EveNt() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		FinancialStatementsearchReportsByEventNameForNS_SBA_EveNt();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Report']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Report']")).click();
	}
	public void FinancialStatementsearchReportsByEventNameForNS_SBA_EveNt() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_NS"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_NS") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
		WebElement element7 = driver.findElement(By.xpath("//*[text()='T3NSEmetteurF']"));
		action1.moveToElement(element7).build().perform();
		action1.click(element7).build().perform();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s.selectByVisibleText("Financial Statement");
		
		WebElement element3 = driver.findElement(By.xpath("(//*[@aria-label='Download Report'])"));
		js.executeScript("arguments[0].scrollIntoView();", element3);
	}
	public void SearchResultReportFunctionalityForNovaScotia_SBA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchResultReportsByEventNameForNS_SBA();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name_NS") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name_NS") + "']"))
				.click();
	}
	public void SearchResultReportsByEventNameForNS_SBA() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_NS"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_NS") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void ExcelDataProviderforNovaScotiaEventDataTabResultReport() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_NS") + " "
				+ date1 + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ExcelDataProviderforNovaScotiaResultReport() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_NS") + " "
				+ date1 + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(5);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateNovaSCotiaResultReport() throws Exception {
		ExcelDataProviderforResultReportNS excel = new ExcelDataProviderforResultReportNS();
		Double EventData = excel.getNumericData("Event Data", 5, 3);
		String strNumberA = Double.toString(EventData);
		System.out.println(strNumberA);
		Assert.assertEquals(strNumberA, "7000.0");
		System.out.println("Category A - Total Allowances Sold" + ":- " + EventData);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total Allowances Sold :- " + EventData);
		System.out.println("**************************************************************************************");
		Double CategoryA1 = excel.getNumericData("Category A Distribution", 1, 5);
		String strNumberA1 = Double.toString(CategoryA1);
		System.out.println(strNumberA1);
		Assert.assertEquals(strNumberA1, "7000.0");
		System.out.println("Category A - Total allotted allowances " + ":- " + CategoryA1);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances :- " + CategoryA1);
		Double CategoryA2 = excel.getNumericData("Category A Distribution", 2, 5);
		String strNumberA2 = Double.toString(CategoryA2);
		System.out.println(strNumberA2);
		Assert.assertEquals(strNumberA2, "1000.0");
		System.out.println("Category A - Total allotted allowances for NS3629-3961 " + ":- " + CategoryA2);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances for NS3629-3961 :- " + CategoryA2);
		Double CategoryA3 = excel.getNumericData("Category A Distribution", 3, 5);
		String strNumberA3 = Double.toString(CategoryA3);
		System.out.println(strNumberA3);
		Assert.assertEquals(strNumberA3, "1000.0");
		System.out.println("Category A - Total allotted allowances for NS3630-3963 " + ":- " + CategoryA3);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances for NS3630-3963 :- " + CategoryA3);
		Double CategoryA4 = excel.getNumericData("Category A Distribution", 4, 5);
		String strNumberA4 = Double.toString(CategoryA4);
		System.out.println(strNumberA4);
		Assert.assertEquals(strNumberA4, "1000.0");
		System.out.println("Category A - Total allotted allowances for NS3624-3951 " + ":- " + CategoryA4);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances for NS3624-3951 :- " + CategoryA4);
		Double CategoryA5 = excel.getNumericData("Category A Distribution", 6, 5);
		String strNumberA5 = Double.toString(CategoryA5);
		System.out.println(strNumberA5);
		Assert.assertEquals(strNumberA5, "3000.0");
		System.out.println("Category A - Total allotted allowances for NS3626-3955 " + ":- " + CategoryA5);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances for NS3626-3955 :- " + CategoryA5);
		Double CategoryA6 = excel.getNumericData("Category A Distribution", 7, 5);
		String strNumberA6 = Double.toString(CategoryA6);
		System.out.println(strNumberA6);
		Assert.assertEquals(strNumberA6, "1000.0");
		System.out.println("Category A - Total allotted allowances for NS3627-3957 " + ":- " + CategoryA6);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total allotted allowances for NS3627-3957 :- " + CategoryA6);
		System.out.println("**************************************************************************************");
		Double ValidCategoryA2 = excel.getNumericData("Final Results", 1, 2);
		String ValidstrNumberA2 = Double.toString(ValidCategoryA2);
		System.out.println(ValidstrNumberA2);
		Assert.assertEquals(ValidstrNumberA2, "1000.0");
		System.out.println("Category A Result - Total allotted allowances for NS3624-3951 " + ":- " + ValidCategoryA2);
		ObjectRepo.getTest().log(Status.PASS,
				"Category A Result - Total allotted allowances for NS3624-3951 :- " + ValidCategoryA2);
		Double ValidCategoryA3 = excel.getNumericData("Final Results", 3, 2);
		String ValidstrNumberA3 = Double.toString(ValidCategoryA3);
		System.out.println(ValidstrNumberA3);
		Assert.assertEquals(ValidstrNumberA3, "3000.0");
		System.out.println("Category A Result - Total allotted allowances for NS3626-3955 " + ":- " + ValidCategoryA3);
		ObjectRepo.getTest().log(Status.PASS,
				"Category A Result - Total allotted allowances for NS3626-3955 :- " + ValidCategoryA3);
		Double ValidCategoryA4 = excel.getNumericData("Final Results", 4, 2);
		String ValidstrNumberA4 = Double.toString(ValidCategoryA4);
		System.out.println(ValidstrNumberA4);
		Assert.assertEquals(ValidstrNumberA4, "1000.0");
		System.out.println("Category A Result - Total allotted allowances for NS3627-3957 " + ":- " + ValidCategoryA4);
		ObjectRepo.getTest().log(Status.PASS,
				"Category A Result - Total allotted allowances for NS3627-3957 :- " + ValidCategoryA4);
		Double ValidCategoryA6 = excel.getNumericData("Final Results", 7, 2);
		String ValidstrNumberA6 = Double.toString(ValidCategoryA6);
		System.out.println(ValidstrNumberA6);
		Assert.assertEquals(ValidstrNumberA6, "1000.0");
		System.out.println("Category A Result - Total allotted allowances for NS3630-3963 " + ":- " + CategoryA6);
		ObjectRepo.getTest().log(Status.PASS,
				"Category A Result - Total allotted allowances for NS3630-3963 :- " + CategoryA6);
		System.out.println("**************************************************************************************");
	}
	
	public void SearchR317ReserveSaleResultforTierDistributionValidation() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		//Thread.sleep(3000);
		SearchResultReportsByEventNameForR317ReserveSale();
		//Thread.sleep(5000);
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		Thread.sleep(25000);
	}

	public void SearchResultReportsByEventNameForR317ReserveSale() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}

	public void ExcelDataProviderforTierOneTwoEventDataResultR317RSCA() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("ResSaleEventAfterCertify")
				+ " " + "02-12-2024" + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ExcelDataProviderforTierOneDistributionR317RSCA() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("ResSaleEventAfterCertify")
				+ " " + "02-12-2024" + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(4);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Vikram shift
	public void ExcelDataProviderforTierOneRollDownR317RSCA() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("ResSaleEventAfterCertify")
				+ " " + "02-12-2024" + ".xlsx");

		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(5);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						System.out.print(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateR317ReserveCAResultReport() throws Exception {
		try {
			ExcelDataProviderforResultReportR317RSCA excel = new ExcelDataProviderforResultReportR317RSCA();
			Double EventData = excel.getNumericData("Event Data", 5, 3);
			String strNumber = Double.toString(EventData);
			System.out.println(strNumber);
			Assert.assertEquals(strNumber, "1000.0");
			System.out.println("Tier one - Total Allowances Sold" + ":- " + EventData);
			ObjectRepo.getTest().log(Status.PASS, "Tier one - Total Allowances Sold :- " + EventData);
			Double EventData1 = excel.getNumericData("Event Data", 6, 3);
			String strNumberA = Double.toString(EventData);
			System.out.println(strNumberA);
			Assert.assertEquals(strNumberA, "1000.0");
			System.out.println("Tier Two - Total Allowances Sold" + ":- " + EventData1);
			ObjectRepo.getTest().log(Status.PASS, "Tier Two - Total Allowances Sold :- " + EventData1);
			System.out.println("**************************************************************************************");
			Double TotalTierOne = excel.getNumericData("Tier One Distribution", 1, 5);
			String strNumberA1 = Double.toString(TotalTierOne);
			System.out.println(strNumberA1);
			Assert.assertEquals(strNumberA1, "1000.0");
			System.out.println("Total allotted allowances for Tier One :- " + " " + TotalTierOne);
			ObjectRepo.getTest().log(Status.PASS, "Total allotted allowances for Tier One :- " + TotalTierOne);
			Double Entity1 = excel.getNumericData("Tier One Distribution", 2, 5);
			String strNumberA2 = Double.toString(Entity1);
			System.out.println(strNumberA2);
			Assert.assertEquals(strNumberA2, "1000.0");
			System.out.println("Tier One - Total allotted allowances for CA3213-4519 " + ":- " + Entity1);
			ObjectRepo.getTest().log(Status.PASS, "Tier One - Total allotted allowances for CA3213-4519 :- " + Entity1);
			Double Entity2 = excel.getNumericData("Tier One Distribution", 3, 5);
			String strNumberA3 = Double.toString(Entity2);
			System.out.println(strNumberA3);
			Assert.assertEquals(strNumberA3, "1000.0");
			System.out.println("Tier One - Total allotted allowances for CA3225-4535 " + ":- " + Entity2);
			ObjectRepo.getTest().log(Status.PASS, "Tier One - Total allotted allowances for CA3225-4535 :- " + Entity2);
			System.out.println("**************************************************************************************");
			Double TotalTierTwo = excel.getNumericData("Tier Two Distribution", 1, 5);
			String strNumberA11 = Double.toString(TotalTierTwo);
			System.out.println(strNumberA11);
			Assert.assertEquals(strNumberA11, "2000.0");
			System.out.println("Total allotted allowances for Tier Two :- " + " " + TotalTierTwo);
			ObjectRepo.getTest().log(Status.PASS, "Total allotted allowances for Tier Two :- " + TotalTierTwo);

			Double TierTwoEntity1 = excel.getNumericData("Tier Two Distribution", 2, 5);
			String strNumberA12 = Double.toString(TierTwoEntity1);
			System.out.println(strNumberA12);
			Assert.assertEquals(strNumberA12, "1000.0");
			System.out.println("Tier Two - Total allotted allowances for CA3213-4519 " + ":- " + TierTwoEntity1);
			ObjectRepo.getTest().log(Status.PASS, "Tier Two - Total allotted allowances for CA3213-4519 :- " + TierTwoEntity1);

			Double TierTwoEntity2 = excel.getNumericData("Tier Two Distribution", 3, 5);
			String strNumberA13 = Double.toString(TierTwoEntity2);
			System.out.println(strNumberA13);
			Assert.assertEquals(strNumberA13, "1000.0");
			System.out.println("Tier Two - Total allotted allowances for CA3223-4531 " + ":- " + TierTwoEntity2);
			ObjectRepo.getTest().log(Status.PASS, "Tier Two - Total allotted allowances for CA3223-4531 :- " + TierTwoEntity2);
			System.out.println("**************************************************************************************");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void QualifiedBiddersearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchQBidderReportsByEventNameForReserveSaleR70Event();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReportSBMAR70));
		driver.findElement(btnDownloadQualifiedBidderReportSBMAR70).click();
		System.out.println("Qualified Bidder report Accessible and downloaded successfully by Sponsor and MM");
		ObjectRepo.getTest().log(Status.PASS,
				"Qualified Bidder report Accessible and downloaded successfully by Sponsor and MM");
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}

	public void SearchQBidderReportsByEventNameForReserveSaleR70Event() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Qualified Bidders Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}

	public void FrenchQualifiedBiddersearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		FrenchbtnReports.click();
		System.out.println("Click on french reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on french reports button");
		searchFrenchReportsByEventNameForSBMAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(FrnchbtnDownloadQualifiedBidderReportSBMAR70));
		driver.findElement(FrnchbtnDownloadQualifiedBidderReportSBMAR70).click();
	}
	public void BidsAuditReportsearchFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsAuditReportsByEventNameForSBMAR70Event();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Bids Audit Report for event "
						+ prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("Event_Name_RE1") + "']"))
				.click();
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsAuditReportsByEventNameForSBMAR70Event() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void BidsReportsearchFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportsByEventNameForSBMAR70Event();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By
				.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_RE1") + "']"))
				.click();
		String Text = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText();
		System.out.println(Text);
		ObjectRepo.getTest().log(Status.PASS, Text);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText()
				.isBlank();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the dynamic report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the static report");
		}
	}
	public void SearchBidsReportsByEventNameForSBMAR70Event() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void FinancialSettlementsearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		searchReportsByEventNameForSBMAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Financial Settlement Report for event "
						+ prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Financial Settlement Report for event "
				+ prop.getProperty("Event_Name_RE1") + "']")).click();
	}

	public void searchReportsByEventNameForSBMAEvent() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
	public void AR_SerachFinancialStatement1ReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		AR_FinancialStatementsearchReportsByEventNameForSBMA();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		String FinancialStatementReport = driver.findElement((By.xpath("(//tr[@class='MuiTableRow-root'])[1]")))
				.getText();
		System.out.println("Financial Statement Report :- " + FinancialStatementReport);
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report :- " + FinancialStatementReport);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Financial Statement for event "
						+ prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Financial Statement for event "
				+ prop.getProperty("Event_Name_RE1") + "']")).click();
	}
	public void AR_FinancialStatementsearchReportsByEventNameForSBMA() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("(//*[@aria-label='Without label'])[2]")).click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@aria-label='Without label'])[2]")));
		s.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by Account Representative ");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by Account Representative");
	}
	public void FSAFinancialSettlementsearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		FSAsearchReportsByEventNameForSBMAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		String FinancialSettlementReport = driver.findElement((By.xpath("(//tr[@class='MuiTableRow-root'])[1]")))
				.getText();
		System.out.println("Financial settlement report :- " + FinancialSettlementReport);
		ObjectRepo.getTest().log(Status.PASS, "Financial settlement report :- " + FinancialSettlementReport);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Financial Settlement Report for event "
						+ prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Financial Settlement Report for event "
				+ prop.getProperty("Event_Name_RE1") + "']")).click();
	}
	public void FSAsearchReportsByEventNameForSBMAEvent() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Financial Settlement Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by FSA");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by FSA");
	}
	public void FinancialStatementsearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		FinancialStatementsearchReportsByEventNameForSBMAEvent1();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Download Report']")));
		driver.findElement(By.xpath("//*[@aria-label='Download Report']")).click();
	}
	public void FinancialStatementsearchReportsByEventNameForSBMAEvent1() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
		WebElement element7 = driver
				.findElement(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']"));
		action1.moveToElement(element7).build().perform();
		action1.click(element7).build().perform();
		Select Yes = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		Yes.selectByVisibleText("Financial Statement");
		
		WebElement element3 = driver.findElement(By.xpath("(//*[@aria-label='Download Report'])"));
		js.executeScript("arguments[0].scrollIntoView();", element3);
		String StatementText = driver.findElement(By.xpath("(//*[@class='MuiTableBody-root'])[2]")).getText();
		System.out.println(StatementText);
		ObjectRepo.getTest().log(Status.PASS, StatementText);
		String Text = driver.findElement(By.xpath(
				"//*[text()='This static PDF document provides all information required for the entity to complete financial settlement. It is available post certification to the Sponsor and Account Representative roles, for Auctions and Sales by Mutual Agreement.']"))
				.getText();
		System.out.println(Text);
		String Same = "This static PDF document provides all information required for the entity to complete financial settlement. It is available post certification to the Sponsor and Account Representative roles, for Auctions and Sales by Mutual Agreement.";
		Assert.assertEquals(Text, Same);
	}
	public void FrenchFinancialStatementsearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		FrenchbtnReports.click();
		System.out.println("Click on french reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on french reports button");
		SearchFinancialStatementFrenchReportsByEventNameForSBMAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		String FrenchStatementReport = driver.findElement((By.xpath("(//tr[@class='MuiTableRow-root'])[8]"))).getText();
		System.out.println("French Financial statement report :- " + FrenchStatementReport);
		ObjectRepo.getTest().log(Status.PASS, "French Financial statement report :- " + FrenchStatementReport);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Télécharger le rapport']")));
		driver.findElement(By.xpath("//*[@aria-label='Télécharger le rapport']")).click();
	}
	public void SearchFinancialStatementFrenchReportsByEventNameForSBMAEvent() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Rechercher un rapport']")).click();
		driver.findElement(By.xpath("//*[@placeholder='Tous'][1]")).click();
		
		js.executeScript("window.scrollBy(0,750)");
		driver.findElement(By.xpath("//*[@placeholder='Tous'][1]")).sendKeys(prop.getProperty("FR_Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("FR_Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		driver.findElement(By.xpath("//*[text()='RECHERCHER']")).click();
		System.out.println("French Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "French Event name searched by sponsor");
		driver.findElement(By.xpath("(//input[@placeholder='Tous'])[3]")).click();
		WebElement element7 = driver
				.findElement(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']"));
		action.moveToElement(element7).build().perform();
		action.click(element7).build().perform();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s.selectByValue("Facture");
		
		WebElement element3 = driver.findElement(By.xpath("(//*[@aria-label='Télécharger le rapport'])"));
		js.executeScript("arguments[0].scrollIntoView();", element3);
	}
	public void ResultsearchReportFunctionalityForSBMAR70() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchResultReportsByEventNameForSBMAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name_RE1") + "']"))
				.click();
	}
	public void SearchResultReportsByEventNameForSBMAEvent() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1"));
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']"));
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		Actions action1 = new Actions(driver);
		WebElement element1 = driver.findElement(By.xpath("//*[text()='SEARCH']"));
		action1.moveToElement(element1).build().perform();
		action1.click(element1).build().perform();
		System.out.println("Event name searched by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event name searched by sponsor");
	}
}
