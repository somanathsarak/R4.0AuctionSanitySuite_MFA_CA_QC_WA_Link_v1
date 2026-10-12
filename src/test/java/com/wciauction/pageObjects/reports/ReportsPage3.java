package com.wciauction.pageObjects.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ReportsPage3 {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	private By btnBidsAuditReports_CA_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Audit Report for event " + prop.getProperty("eventNameCA") + "']");
	private By btnPostEventOperationsReport_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("eventNameCA") + "']");
	private By btnFinancialSettlementReport_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("eventNameCA") + "']");
	private By btnBidsReports_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Report for event " + prop.getProperty("eventNameCA") + "']");
	private By btnResultsReports_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("eventNameCA") + "']");
	private By btnAllowanceDeliveryReport_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("eventNameCA") + "']");
	private By btnQualifiedBiddersReport_CA_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("eventNameCA") + "']");

	// QuebecSingleAuction
	private By btnBidsAuditReports_QC_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Audit Report for event " + prop.getProperty("eventNameQC") + "']");
	private By btnPostEventOperationsReport_QC_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("eventNameQC") + "']");
	private By btnFinancialSettlementReport_QC_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("eventNameQC") + "']");
	private By btnBidsReports_QC_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Report for event " + prop.getProperty("QuebecSingleAuction") + "']");
	private By btnResultsReports_QC_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("eventNameQC") + "']");
	private By btnAllowanceDeliveryReport_QC_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("eventNameQC") + "']");
	private By btnQualifiedBiddersReport_QC_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("eventNameQC") + "']");

	// WashingtonSingleAuction
	private By btnBidsAuditReports_WA_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Audit Report for event " + prop.getProperty("eventNameWS") + "']");
	private By btnPostEventOperationsReport_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("eventNameWS") + "']");
	private By btnFinancialSettlementReport_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("eventNameWS") + "']");
	private By btnBidsReports_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Report for event " + prop.getProperty("eventNameWS") + "']");
	private By btnResultsReports_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("eventNameWS") + "']");
	private By btnAllowanceDeliveryReport_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("eventNameWS") + "']");
	private By btnQualifiedBiddersReport_WA_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("eventNameWS") + "']");

	// JointAuction
	private By btnBidsAuditReports_JA_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Audit Report for event " + prop.getProperty("Event_Name") + "']");
	private By btnPostEventOperationsReport_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("Event_Name") + "']");
	private By btnFinancialSettlementReport_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("Event_Name") + "']");
	private By btnBidsReports_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name") + "']");
	private By btnResultsReports_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name") + "']");
	private By btnAllowanceDeliveryReport_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("Event_Name") + "']");
	private By btnQualifiedBiddersReport_JA_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("Event_Name")
					+ "']");

	// ReserveSaleCalifornia
	private By btnBidsAuditReports_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By btnPostEventOperationsReport_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By btnFinancialSettlementReport_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By btnFinancialSettlementReport_CA_RS_FSA = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("ResSaleEventAfterCertify") + "']");
	private By btnBidsReports_CA_RS_Single_Auction = By.xpath(
			"//button[@aria-label='Download Bids Report for event " + prop.getProperty("ReserveSaleEventName") + "']");
	private By btnResultsReports_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("ReserveSaleEventName")
					+ "']");
	private By btnAllowanceDeliveryReport_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By btnQualifiedBiddersReport_CA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");

	// ReserveSaleWashington
	private By btnBidsAuditReports_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnPostEventOperationsReport_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Post Event Operations Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnFinancialSettlementReport_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnBidsReports_WA_RS_Single_Auction = By.xpath("//button[@aria-label='Download Bids Report for event "
			+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnResultsReports_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Results Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnAllowanceDeliveryReport_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");
	private By btnQualifiedBiddersReport_WA_RS_Single_Auction = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("ReserveSaleEventName_WA") + "']");

	@FindBy(xpath = "//*[@title='Reports']")
	private WebElement btnReports;
	
	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement searchreport;

	@FindBy(xpath = "//*[text()='Search Reports']")
	private WebElement searchreport2;

	@FindBy(xpath = "//*[@aria-label='Event Name']")
	private WebElement eventNameoptions;

	@FindBy(xpath = "//*[text()='SEARCH']")
	private WebElement btnSearch;

	public ReportsPage3() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }

	public void searchReportsByEventNameForCASingleAuction() throws InterruptedException {
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
	} catch (Exception e) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
	}
		btnReports.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameCA"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']"))
		.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void downloadbidsAuditReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnBidsAuditReports_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnBidsAuditReports_CA_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnPostEventOperationsReport_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnPostEventOperationsReport_CA_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnFinancialSettlementReport_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnFinancialSettlementReport_CA_Single_Auction).click();
	}

	public void downloadBidsReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnBidsReports_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnBidsReports_CA_Single_Auction).click();
	}

	public void downloadResultsReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnResultsReports_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnResultsReports_CA_Single_Auction).click();
	}

	public void downloadAllowanceDeliveryReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnAllowanceDeliveryReport_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnAllowanceDeliveryReport_CA_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForCASingleAuction() throws InterruptedException {
	    
	    WebElement element = driver.findElement(btnQualifiedBiddersReport_CA_Single_Auction);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnQualifiedBiddersReport_CA_Single_Auction).click();
	}

	public void searchReportsByEventNameForQCSingleAuction() throws InterruptedException {
		try {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
	} catch (Exception e) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
	}
	    searchreport.click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
	    searchreport2.click();
	    Thread.sleep(5000);
	    eventNameoptions.click();
	    eventNameoptions.sendKeys(prop.getProperty("eventNameQC"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
	    driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']")).click();
	    btnSearch.click();
	}

	public void downloadbidsAuditReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReports_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports_QC_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnPostEventOperationsReport_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnPostEventOperationsReport_QC_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_QC_Single_Auction).click();
	}

	public void downloadBidsReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsReports_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReports_QC_Single_Auction).click();
	}

	public void downloadResultsReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnResultsReports_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultsReports_QC_Single_Auction).click();
	}

	public void downloadAllowanceDeliveryReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnAllowanceDeliveryReport_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowanceDeliveryReport_QC_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForQCSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnQualifiedBiddersReport_QC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBiddersReport_QC_Single_Auction).click();
	}

	public void searchReportsByEventNameForWASingleAuction() throws InterruptedException {
	    
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
	    eventNameoptions.click();
	    eventNameoptions.sendKeys(prop.getProperty("eventNameWS"));
	    Actions action = new Actions(driver);
	    WebElement element1 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']"));
	    action.moveToElement(element1).build().perform();
	    driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']")).click();
	    WebElement element2 = btnSearch;
	    action.moveToElement(element2).build().perform();
	    btnSearch.click();
	}

	public void downloadbidsAuditReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReports_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports_WA_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnPostEventOperationsReport_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnPostEventOperationsReport_WA_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_WA_Single_Auction).click();
	}

	public void downloadBidsReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsReports_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReports_WA_Single_Auction).click();
	}

	public void downloadResultsReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnResultsReports_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultsReports_WA_Single_Auction).click();
	}

	public void downloadAllowanceDeliveryReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnAllowanceDeliveryReport_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowanceDeliveryReport_WA_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForWASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnQualifiedBiddersReport_WA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBiddersReport_WA_Single_Auction).click();
	}

	public void searchReportsByEventNameForJointAuction() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameoptions.click();
		eventNameoptions.sendKeys(prop.getProperty("Event_Name"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']")).click();
		btnSearch.click();
	}

	public void downloadbidsAuditReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReports_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports_JA_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnPostEventOperationsReport_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnPostEventOperationsReport_JA_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_JA_Single_Auction).click();
	}

	public void downloadBidsReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsReports_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReports_JA_Single_Auction).click();
	}

	public void downloadResultsReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnResultsReports_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultsReports_JA_Single_Auction).click();
	}

	public void downloadAllowanceDeliveryReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnAllowanceDeliveryReport_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowanceDeliveryReport_JA_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForJASingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnQualifiedBiddersReport_JA_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBiddersReport_JA_Single_Auction).click();
	}

	public void searchReportsByEventNameForReserveSaleCalifornia() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		searchreport2.click();
		eventNameoptions.click();
		eventNameoptions.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "Enter report name.");
		Actions action = new Actions(driver);
		WebElement element = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']"));
		action.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']")).click();
		WebElement element2 = btnSearch;
		action.moveToElement(element2).build().perform();
		btnSearch.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search report button.");
//		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']")).click();
//		btnSearch.click();
	}

	public void downloadbidsAuditReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReports_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports_CA_RS_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnPostEventOperationsReport_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnPostEventOperationsReport_CA_RS_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_CA_RS_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForCA_RSFSA() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_CA_RS_FSA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_CA_RS_FSA).click();
		ObjectRepo.getTest().log(Status.PASS, "Download report successfully.");
	}
	
	public void downloadBidsReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsReports_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReports_CA_RS_Single_Auction).click();
	}

	public void downloadResultsReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnResultsReports_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultsReports_CA_RS_Single_Auction).click();
		ObjectRepo.getTest().log(Status.PASS, "Download report successfully.");
	}

	public void downloadAllowanceDeliveryReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnAllowanceDeliveryReport_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowanceDeliveryReport_CA_RS_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForCA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnQualifiedBiddersReport_CA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBiddersReport_CA_RS_Single_Auction).click();
	}

	public void searchReportsByEventNameForReserveSaleWashington() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Search Reports']")));
		searchreport2.click();
		Thread.sleep(5000);
		eventNameoptions.click();
		eventNameoptions.sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		Actions action = new Actions(driver);
		WebElement element1 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']"));
		action.moveToElement(element1).build().perform();
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']")).click();
		WebElement element2 = btnSearch;
		action.moveToElement(element2).build().perform();
		btnSearch.click();
	}
	public void searchReportsByEventNameForReserveSaleWashingtonFSA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(searchreport));
		searchreport.click();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Search Reports']")));
		searchreport2.click();
		eventNameoptions.click();
		eventNameoptions.sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		Actions action = new Actions(driver);
		WebElement element1 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']"));
		action.moveToElement(element1).build().perform();
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']")).click();
		WebElement element2 = btnSearch;
		action.moveToElement(element2).build().perform();
		btnSearch.click();
	}

	public void downloadbidsAuditReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReports_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports_WA_RS_Single_Auction).click();
	}

	public void downloadPostEventOperationsReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnPostEventOperationsReport_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnPostEventOperationsReport_WA_RS_Single_Auction).click();
	}

	public void downloadFinancialSettlementReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnFinancialSettlementReport_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReport_WA_RS_Single_Auction).click();
	}

	public void downloadBidsReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsReports_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReports_WA_RS_Single_Auction).click();
	}

	public void downloadResultsReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnResultsReports_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultsReports_WA_RS_Single_Auction).click();
	}

	public void downloadAllowanceDeliveryReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnAllowanceDeliveryReport_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowanceDeliveryReport_WA_RS_Single_Auction).click();
	}

	public void downloadQualifiedBidderReportForWA_RSSingleAuction() throws InterruptedException {
		
		WebElement element = driver.findElement(btnQualifiedBiddersReport_WA_RS_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBiddersReport_WA_RS_Single_Auction).click();
		Thread.sleep(20000);
	}

}
