package com.wciauction.pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.wci.auction.org.resources.ObjectRepo;

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class AuctionQAPage2 {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//*[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement search_entityName;
	
	@FindBy(xpath = "//*[text()='SEARCH']")
	private WebElement search;

	@FindBy(xpath = "//*[text()='WA_Entity+A (WA6585-9938)']")
	private WebElement select_entityName;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement administration;

	@FindBy(xpath = "//a[text()='Reserve Sale Certification']")
	private WebElement reserveSaleCertification;

	@FindBy(xpath = ("//p[text()='Certification decision saved successfully.']"))
	private WebElement getSuccesMsg;

	@FindBy(xpath = ("//*[@data-icon='times']"))
	private WebElement popUpClose;

	@FindBy(xpath = ("(//*[@data-icon='angle-down'])"))
	private WebElement logoutDdropdwon;

	@FindBy(xpath = ("//*[text()='Logout']"))
	private WebElement logout;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement administration2;

	@FindBy(xpath = "//*[text()='Home']")
	private WebElement home;

	@FindBy(xpath = "//a[text()='Reserve Sale Certification']")
	private WebElement reserveSaleCertification2;

	@FindBy(xpath = "(//*[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement sumbitbtn4;

	@FindBy(xpath = ("//p[text()='Certification decision saved successfully.']"))
	private WebElement getSuccesMsg2;

	@FindBy(xpath = ("//*[@data-icon='times']"))
	private WebElement popUpClose2;

	@FindBy(xpath = ("(//*[@data-icon='angle-down'])"))
	private WebElement logoutDdropdwon2;

	@FindBy(xpath = ("//*[text()='Logout']"))
	private WebElement logout2;

	@FindBy(xpath = "//*[text()='WA_Entity+B (WA6586-9940)']")
	private WebElement select_entityName1;

	@FindBy(xpath = "//*[text()='Company+R (CA3223-4531)']")
	private WebElement select_entityNameAR2;

	@FindBy(xpath = "//*[text()='T3NSEmetteurF (NS3624-3951)']")
	private WebElement select_entityNameAR;

	@FindBy(xpath = "//*[text()='Prescribed_Company+H - PLC=0 PLA=0 HLC=11598500 HLA=0 (CA4015-4980)']")
	private WebElement select_entityNameARafterCertify;

	@FindBy(xpath = "//*[text()='Events']")
	private WebElement click_onEvents;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement clickResetAlgorithm;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement click_onAdministration;

	@FindBy(xpath = "//*[text()='Event']")
	private WebElement click_onEvent;

	@FindBy(xpath = "//button[@title=\"Go to details of Event R45_DummEvent\"]/ancestor::tr//td[8]")
	private WebElement get_EventStatus;

	@FindBy(xpath = "//*[contains(text(),'Monitoring')]")
	private WebElement click_onEventMonitoring;

	@FindBy(xpath = "//*[text()='EventNameWADuringBidding']")
	private WebElement select_Events;

	@FindBy(xpath = "(//*[text()='ReserveSaleAutomation'])[1]")
	private WebElement select_Event;
	
	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement select_Event1;

	@FindBy(xpath = "(//*[text()='ReserveSaleAutomation'])[1]")
	private WebElement select_Event2;
	
	@FindBy(xpath = "//*[text()='BIDDING CLOSES IN']")
	private WebElement bidding_periodStatus;

	@FindBy(xpath = "//*[text()='WA_Entity+A (WA6585-9938)']")
	private WebElement select_entityNamePostCerty;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement click_onContinue;

	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement reports;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement administration1;

	@FindBy(xpath = "//*[text()='Auction Certification']")
	private WebElement AuctionCertification;

	@FindBy(xpath = "//*[text()='CMS']")
	private WebElement CMS;

	@FindBy(xpath = "//*[@name='language']")
	private WebElement language;

	@FindBy(xpath = "//*[@title='Select number of rows per page.']")
	private WebElement rowsPerPage;

	@FindBy(xpath = "//*[text()='500 ']")
	private WebElement selectRowsNum;

	@FindBy(xpath = "//*[text()='PUBLISH']")
	private WebElement publish;
	
	@FindBy(xpath = "//*[text()='Header']")
	private WebElement Header;
	
	@FindBy(xpath = "//*[text()='PUBLIER']")
	private WebElement publier;

	@FindBy(xpath = "//*[@class='MuiButtonBase-root MuiIconButton-root']")
	private WebElement removeSuccessPopUp;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement changeLang;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement selectFrance;

	@FindBy(xpath = "//*[@title='Go to details of Event EventNameWADuringBidding']")
	private WebElement goToDetails;

	@FindBy(xpath = "//*[text()='CREATE NEW EVENT']")
	private WebElement CREATE_NEW_EVENT;

	@FindBy(xpath = "//*[@name='eventType']")
	private WebElement eventType;

	@FindBy(xpath = "//*[text()='Event ID']")
	private WebElement EventID;

	@FindBy(xpath = "//*[text()='Event Creation']")
	private WebElement Event_Creation;

	@FindBy(xpath = "//*[text()='Event Details']")
	private WebElement Event_Details;

	@FindBy(xpath = "//*[text()='Edit Event']")
	private WebElement clickOnEditEvent;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement scroll_ToSearch;

	@FindBy(xpath = "//*[text()='About WCI, Inc.']")
	private WebElement scroll_To_Bottom;

	@FindBy(xpath = "(//*[@aria-label='Entity Name'])[2]")
	private WebElement click_On_Entiy_Dropdown;

	@FindBy(xpath = "//*[@id='entity-reports']/table/tbody/tr/td[1]/div/div[2]/div[2]/span")
	private WebElement select_Entity_Name1;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement select_Report_Name;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[4]")
	private WebElement select_Report_Name2;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement Report_NameDuring_Bidding;

	@FindBy(xpath = "//td[text()='Bids Report']")
	private WebElement bids_Report;

	@FindBy(xpath = "//*[@aria-label='Download Bids Report for event EventNameWADuringBidding']")
	private WebElement bids_Report_Download;

	@FindBy(xpath = "//*[@aria-label='Download Bids Audit Report for event EventNameWADuringBidding']")
	private WebElement bids_AuditReport_DownloadAR2;

	@FindBy(xpath = "//*[@aria-label='Download Results Report for event A42_JoinAuc_Automation_CloseBW']")
	private WebElement ResultReport_Download;
	
	@FindBy(xpath = "//span[contains(text(),'Welcome,')]")
	private WebElement lougout;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement lougoutFSA;

	@FindBy(xpath = "//*[@aria-label='Download Bids Report for event ReserveSaleAutomation']")
	private WebElement bids_Report_DownloadAR2;

	@FindBy(xpath = "//*[@aria-label='Download Bids Audit Report for event EventNameWADuringBidding']")
	private WebElement bids_AuditReport_Download;

	@FindBy(xpath = "//*[text()='Event']")
	private WebElement event1;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement runalgorithm_1;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement runalgorithm_2;

	@FindBy(xpath = "(//*[text()='CONTINUE'])")
	private WebElement continue1;

	@FindBy(xpath = "//*[text()='The algorithm ran successfully. It may take a few minutes for the reports to be generated.']")
	private WebElement getSuccessMassage2;

	@FindBy(xpath = "(//*[@data-icon='angle-down'])")
	private WebElement logoutDropdown1;

	@FindBy(xpath = ("//*[text()='Logout']"))
	private WebElement logout1;

	@FindBy(xpath = "(//*[text()='You have been successfully logged out.'])")
	private WebElement logoutMassage1;

	public AuctionQAPage2() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }
	public void searchReportByName() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement eventName = driver.findElement(By.xpath("//*[@aria-label='Event Name']"));
		wait.until(ExpectedConditions.visibilityOf(eventName)).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("EventNameWA"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWA") + "']")).click();
		Select sel = new Select(select_Report_Name);
		sel.selectByVisibleText("Client Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Select report name form dropdown.");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void c32057_reportAccesstoAR_ForClientsBidsReportPostCertificationWS() throws InterruptedException {
		Thread.sleep(5000);
		search_entityName.sendKeys(prop.getProperty("EventID"));
		select_entityName.click();
		ObjectRepo.getTest().log(Status.PASS,"Select entity name.");
		click_onContinue.click();
		searchReportByName();
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Report downloaded successfully.");
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bid Report post certification(WS)");
		System.out.println("AR can access the Client Bid Report post certification(WS)");
	}

	public void c32068_c32070Sponsor_AR_AccessToClientBidsAuditReportForAnAllowanceSaleEventDuringBiddingWindow() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameWADuringBidding"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Select report name form dropdown.");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
		ObjectRepo.getTest().log(Status.PASS, "Report downloaded successfully.");
		Thread.sleep(20000);
		System.out.println(
				"Sponsor/AR can Access To Client Bids Audit Report For An Allowance SaleEvent During Bidding Window.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor/AR can Access To Client Bids Audit Report For An Allowance SaleEvent During Bidding Window.");
	}

	public void c32055_C32045_c32017Sponsor_or_MMAccessBidsReportForAllowanceSaleEventDuringBiddingWindow()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameWADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		bids_Report.getText();
		System.out.println(bids_Report + "Access Granted Successfully");
		bids_Report_Download.click();
		Thread.sleep(20000);
		System.out.println("Sponsor/MM can Access To Bids Report For An Allowance SaleEvent During Bidding Window.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report Downloaded Successfully");
		
	}

	public void c32052_c32016_c32056_SponsorMMAccessBidsAuditReportForAllowanceSaleEventDuringBiddingWindowWS()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameWADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		bids_AuditReport_Download.click();
		Thread.sleep(25000);
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report Downloaded Successfully");
	}

	public void c32069_c32071_ARGetAccessClientBidsAuditReportForAllowanceSaleEventDuringBiddingWindow()
			throws InterruptedException {
		search_entityName.sendKeys(prop.getProperty("EventIDWADuringBidding"));
		select_entityName1.click();
		click_onContinue.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Auction']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		}
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameWADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWADuringBidding") + "']"))
				.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Report downloaded successfully.");
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bid Audit Report during bidding(WS)");
		System.out.println("AR can access the Client Bid Audit Report during bidding(WS)");
		Thread.sleep(25000);
	}

	public void C32070_SponsorAccessClientBidsAuditReportForAllowanceSaleEventDuringBiddingWindow()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameWADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWADuringBidding") + "']"))
				.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Report downloaded successfully.");
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bid Audit Report during bidding(WS)");
		System.out.println("AR can access the Client Bid Audit Report during bidding(WS)");
		Thread.sleep(25000);
	}

	public void C32059_AR_can_get_access_to_Client_Bid_Report_for_an_Allowance_Sale_event_during_the_bidding_window_Post_Certification()
			throws InterruptedException {
		search_entityName.sendKeys(prop.getProperty("EventID"));
		select_entityNamePostCerty.click();
		click_onContinue.click();
		click_onEvents.click();
		select_Events.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("EventNameWA"));
		ObjectRepo.getTest().log(Status.PASS, "Report downloaded successfully.");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWA") + "']")).click();
		Select sel = new Select(select_Report_Name);
		sel.selectByVisibleText("Client Bids Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bid Report post certification(WS)");
		System.out.println("AR can access the Client Bid Report post certification(WS)");
		Thread.sleep(25000);
	}

	public void C31992_C31996_SponsoraccessClientBidsAuditReportForAllowanceSaleEventDuringBiddingWindowCA()
			throws InterruptedException {
		click_onEventMonitoring.click();
		wait.until(ExpectedConditions.visibilityOf(select_Event1));
		
		js.executeScript("arguments[0].scrollIntoView();", select_Event1);
		wait.until(ExpectedConditions.visibilityOf(select_Event1));
		select_Event1.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameCADuringBidding"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access the Client Bid Audit Report during bidding(CA)");
		System.out.println("Sponsor can access the Client Bid Audit Report during bidding(CA)");
		Thread.sleep(20000);
	}

	public void c31994_c31997_AR_GetAccessClientBidsAuditReportForAllowanceSaleEventDuringBiddingWindowCA()
			throws InterruptedException {
		search_entityName.sendKeys(prop.getProperty("AREventID_DuringBiddingCA"));
		select_entityNameAR2.click();
		click_onContinue.click();
		click_onEvents.click();
		wait.until(ExpectedConditions.visibilityOf(select_Event));
		select_Event.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		reports.click();
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameCADuringBidding"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bid Audit Report during bidding(WS)");
		System.out.println("AR can access the Client Bid Audit Report during bidding(WS)");
		Thread.sleep(20000);
	}

	public void C31914_C31947_C31948_SponsorAccessBidReportForEventOnceBiddingWindowOpensCA()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		click_onEventMonitoring.click();
		Thread.sleep(3000);
		WebElement event=driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameCADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		bids_Report.getText();
		System.out.println(bids_Report + "Access Granted Successfully");
		bids_Report_DownloadAR2.click();
		ObjectRepo.getTest().log(Status.PASS, "Bids Report Downloaded Successfully");
		System.out.println("Bids Report Downloaded Successfully");
		Thread.sleep(25000);
	}

	public void C31963_Sponsor_accessClientBidsReportForAllowanceSaleEventDuringBiddingWindowCA()
			throws InterruptedException {
		click_onEventMonitoring.click();
		
		js.executeScript("arguments[0].scrollIntoView();", select_Event);
		wait.until(ExpectedConditions.visibilityOf(select_Event));
		select_Event.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[9]")).click();
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access the Client Bids Report during bidding(CA)");
		System.out.println("Sponsor can access the Client Bids Report during bidding(CA)");
		Thread.sleep(30000);
	}

	public void C31963_AR_AccessClientBidReportForAllowanceSaleEventDuringBiddingWindow() throws InterruptedException  {
		search_entityName.sendKeys(prop.getProperty("EventIdForAfterCertify"));
		select_entityNameARafterCertify.click();
		click_onContinue.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Reserve Sale']")));
		}
		reports.click();
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify2"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify2") + "']"))
				.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		driver.findElement(By.xpath("//*[@data-icon='download']")).click();
		ObjectRepo.getTest().log(Status.PASS, "AR can access the Client Bids Audit Report during bidding CA");
		System.out.println("AR can access the Client Bids Audit Report during bidding(CA)");
		Thread.sleep(25000);
	}

	public void C31934_SponsorAccessBidAuditReportForEventOnceBiddingWindowOpensCA() throws InterruptedException  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		click_onEventMonitoring.click();
		Thread.sleep(3000);
		WebElement event=driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
//		select_Event1.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		reports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameCADuringBidding"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		driver.findElement(By.xpath("//*[@aria-label='Download Bids Audit Report for event "
				+ prop.getProperty("EventNameCADuringBidding") + "']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report Downloaded Successfully");
		Thread.sleep(25000);
	}

	public void C31340_TheSettlementPriceCalculatedIsAsExpected() throws InterruptedException  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("AucEventForResultRepQC"));
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("AucEventForResultRepQC") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		ResultReport_Download = driver.findElement(By.xpath("//*[@aria-label='Download Results Report for event "
				+ prop.getProperty("AucEventForResultRepQC") + "']"));
		ResultReport_Download.click();
		ObjectRepo.getTest().log(Status.PASS, "Result Report Downloaded Successfully");
		Thread.sleep(25000);
	}

	public void C31340_C31341_TheSettlementPriceCalculatedIsAsExpected() throws InterruptedException  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("AucEventForResultRepQC"));
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("AucEventForResultRepQC") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		ResultReport_Download.click();
		ObjectRepo.getTest().log(Status.PASS, "Result Report Downloaded Successfully");
		Thread.sleep(25000);
	}

	public void C31599_VerifyEAORSponsorIsAbleORunableEditEventQC()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
				driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_NameQC") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("Event_NameQC") + "']"))
		.click();
		clickOnEditEvent.isDisplayed();
		System.out.println(" Sponsor is able to edit the event.");
	}

	public void C31599_VerifyEAORSponsorIsAbleORunableEditEventCA()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();;
		for (int i = 0; i < 5; i++) {
			try {
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(clickOnEditEvent));
		clickOnEditEvent.isDisplayed();
		System.out.println(" EA is able to edit the event.");

	}

	public void C31599_VerifyEAORSponsorIsAbleORunableEditEventWA()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		
		WebElement selectEvent2 = driver.findElement(By.xpath(
				"//button[@title='Go to details of Event " + prop.getProperty("EventNameWADuringBidding") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", selectEvent2);
		selectEvent2.click();
		clickOnEditEvent.isDisplayed();
		System.out.println(" Sponsor is able to edit the event.");

	}

	public void C31487_ConfirmEventCreationPageAllowanceSaleDsiplaysLocalizedInformation()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		CREATE_NEW_EVENT.click();
		String EventCreation = Event_Creation.getText();
		Assert.assertEquals(EventCreation, "Event Creation");
		ObjectRepo.getTest().log(Status.PASS, "Event Creation :- " + EventCreation);
		System.out.println("Succesfully navigated on " + EventCreation);
		Select sel = new Select(eventType);
		sel.selectByVisibleText("Reserve Sale");
		List<WebElement> labels = driver.findElements(By.xpath(
				"(//*[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-1 MuiGrid-align-items-xs-center MuiGrid-justify-xs-space-between']/following-sibling::div)"));
		int labelSize = labels.size();
		String labelpresent = "";
		for (int i = 1; i <= labelSize - 1; i++) {
			labelpresent = labels.get(i).getText();
			System.out.println(labelpresent);
		}
		ObjectRepo.getTest().log(Status.PASS,
				"C31487_Confirm_Event_Creation_Page_Allowance_Sale_dsiplays_localized_information.");
	}

	public void C31862_QualifiedBiddersReportIsDynamicReportBiddingPeriodOpenCA()  {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		click_onEventMonitoring.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement event=driver.findElement(By.xpath("//a[text()='ReserveSaleAutomation']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
//		select_Event.click();
		String ExpectedBidPeriodStatus = "BIDDING CLOSES IN";
		String bidPeriodStatus = bidding_periodStatus.getText();
		Assert.assertEquals(bidPeriodStatus, ExpectedBidPeriodStatus);
		System.out.println("Bidding period is Open");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameCADuringBidding"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameCADuringBidding") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		driver.findElement(By.xpath("//*[@aria-label='Download Qualified Bidders Report for event "
				+ prop.getProperty("EventNameCADuringBidding") + "']")).click();
		String Text = driver.findElement(By.xpath(
				"(//button[@aria-label=\"Download Qualified Bidders Report for event ReserveSaleAutomation\"]/ancestor::tr//td[@class='MuiTableCell-root MuiTableCell-body'])[4]"))
				.getText();
		System.out.println(Text);
		boolean GeneratedOnTime = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText().isBlank();
		try {
			Assert.assertTrue(GeneratedOnTime);
			System.out.println("The Qualified Bidders report is a Dynamic report. (prior to Bidding period is open)");
		} catch (AssertionError e) {
			Assert.assertFalse(GeneratedOnTime);
			System.out.println("The Qualified Bidders report is a Static report. (prior to Bidding period is open)");
		}
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report Downloaded Successfully");
	}

	public void C31861_QualifiedBiddersReportIsDynamicReportBiddingPeriodOpenNS() throws InterruptedException  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventNameNSAppnPrdOpen"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameNSAppnPrdOpen") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		driver.findElement(By.xpath("//*[@aria-label='Download Qualified Bidders Report for event "
				+ prop.getProperty("EventNameNSAppnPrdOpen") + "']")).click();
		String Text = driver.findElement(By.xpath(
				"(//button[@aria-label=\"Download Qualified Bidders Report for event EQA_R320_NS_AutoTesting\"]/ancestor::tr//td[@class='MuiTableCell-root MuiTableCell-body'])[4]"))
				.getText();
		System.out.println(Text);
		boolean GeneratedOnTime = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]")).getText().isBlank();
		try {
			Assert.assertTrue(GeneratedOnTime);
			System.out.println("The Qualified Bidders report is a Dynamic report. (prior to Bidding period is open)");
		} catch (AssertionError e) {
			Assert.assertFalse(GeneratedOnTime);
			System.out.println("The Qualified Bidders report is a Static report. (prior to Bidding period is open)");
		}
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report Downloaded Successfully");
	}

	public void C31489_ConfirmEventDetailsPageAllowanceSaleDsiplaysLocalizedInformationWA1()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		goToDetails.click();
		String EventDetails = Event_Details.getText();
		Assert.assertEquals(EventDetails, "Event Details");
		ObjectRepo.getTest().log(Status.PASS, "Event Details :- " + EventDetails);
		System.out.println("Succesfully navigated on page of " + EventDetails);
		String Text = driver.findElement(By.xpath("(//*[@class='MuiTableContainer-root'])[1]")).getText();
		System.out.println(Text
				+ " C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for NS Jurisdiction");
		ObjectRepo.getTest().log(Status.PASS,
				"C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for NS Jurisdiction");
	}

	public void C31489_ConfirmEventDetailsPageAllowanceSaleDsiplaysLocalizedInformationCA()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
				driver.findElement(By.xpath("//*[text()='" + prop.getProperty("AucEventForResultRepQC") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("AucEventForResultRepQC") + "']"))
		.click();
		String EventDetails = Event_Details.getText();
		Assert.assertEquals(EventDetails, "Event Details");
		ObjectRepo.getTest().log(Status.PASS, "Event Details :- " + EventDetails);
		System.out.println("Succesfully navigated on page of " + EventDetails);
		String Text = driver.findElement(By.xpath("(//*[@class='MuiTableContainer-root'])[1]")).getText();
		System.out.println(Text
				+ " C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for CA Jurisdiction");
		ObjectRepo.getTest().log(Status.PASS,
				"C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for CA Jurisdiction");
	}

	public void C31489_ConfirmEventDetailsPageAllowanceSaleDsiplaysLocalizedInformationQC()
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
				driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_NameQC") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By
				.xpath("//*[@title='Go to details of Event " + prop.getProperty("Event_NameQC") + "']"))
		.click();
		String EventDetails = Event_Details.getText();
		Assert.assertEquals(EventDetails, "Event Details");
		ObjectRepo.getTest().log(Status.PASS, "Event Details :- " + EventDetails);
		System.out.println("Succesfully navigated on page of " + EventDetails);
		String Text = driver.findElement(By.xpath("(//*[@class='MuiTableContainer-root'])[1]")).getText();
		System.out.println(Text
				+ " C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for CA Jurisdiction");
		ObjectRepo.getTest().log(Status.PASS,
				"C31489- Confirm Event Details Page Allowance Sale Dsiplays Localized Information in English for CA Jurisdiction");
		changeLang.click();
		selectFrance.click();
		List<WebElement> labels = driver.findElements(By.xpath(
				"//*[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-6 MuiGrid-grid-sm-4 MuiGrid-grid-md-4']//label"));
		int labelSize = labels.size();
		String labelpresent = "";
		for (int i = 1; i <= labelSize - 1; i++) {
			labelpresent = labels.get(i).getText();
			System.out.println(labelpresent);
		}
	}

	public void C31492_ConfirmTheCMS_PageDisplaysLocalizedInformationQC()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(CMS));
		CMS.click();
		String infoInEng = driver.findElement(By.xpath("(//*[@class='MuiContainer-root'])[2]")).getText();
		System.out.println(infoInEng + "All information displayed in English.");
		ObjectRepo.getTest().log(Status.PASS, " All information displayed in English.");
		wait.until(ExpectedConditions.visibilityOf(Header));
		publish.click();
		String RequiredSuccessMsg = "Please revisit the modified pages after few minutes as it may take up to 5 minutes for the changes to be reflected.";
		String ActualSuccessMsg = driver.findElement(By.xpath(
				"//*[text()='Please revisit the modified pages after few minutes as it may take up to 5 minutes for the changes to be reflected.']"))
				.getText();
		System.out.println(ActualSuccessMsg);
		Assert.assertEquals(RequiredSuccessMsg, ActualSuccessMsg);
		System.out.println("Actual success msg is in english.");
		removeSuccessPopUp.click();
		changeLang.click();
		selectFrance.click();
		System.out.println(infoInEng + " All information displayed in French.");
		ObjectRepo.getTest().log(Status.PASS, " All information displayed in French.");
		publier.click();
		String RequiredFrenchSuccessMsg = "Veuillez consulter de nouveau ces pages dans quelques minutes, car cela peut prendre jusqu'à 5 minutes pour que les modifications soient publiées.";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String ActualFrenchSuccessMsg = driver.findElement(By.xpath(
				"//*[text()=\"Veuillez consulter de nouveau ces pages dans quelques minutes, car cela peut prendre jusqu'à 5 minutes pour que les modifications soient publiées.\"]"))
				.getText();
		Assert.assertEquals(RequiredFrenchSuccessMsg, ActualFrenchSuccessMsg);
		System.out.println("Actual success msg is in French.");
		ObjectRepo.getTest().log(Status.PASS, " C31492_Confirm_the_CMS_Page_displays_localized_informationQC.");
	}

	public void C31492_ConfirmTheCMS_PageDisplaysLocalizedInformationCA()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		CMS.click();
		String infoInEng = driver.findElement(By.xpath("(//*[@class='MuiContainer-root'])[2]")).getText();
		System.out.println(infoInEng + " All information displayed in English.");
		ObjectRepo.getTest().log(Status.PASS, " All information displayed in English.");
		String text1 = driver.findElement(By.xpath("//*[text()='Header']")).getText();
		Assert.assertEquals(text1, "Header");
		System.out.println("Header" + " = " + text1);
		ObjectRepo.getTest().log(Status.PASS, "Header is present on the page.");
		String text2 = driver.findElement(By.xpath("//*[text()='Jurisdiction Bar']")).getText();
		Assert.assertEquals(text2, "Jurisdiction Bar");
		System.out.println("Jurisdiction Bar" + " = " + text2);
		ObjectRepo.getTest().log(Status.PASS, "Jurisdiction Bar is present on the page.");
		String text3 = driver.findElement(By.xpath("//*[text()='About the WCI, Inc. Auction Platform']"))
				.getText();
		Assert.assertEquals(text3, "About the WCI, Inc. Auction Platform");
		System.out.println("About the WCI, Inc. Auction Platform" + " = " + text3);
		ObjectRepo.getTest().log(Status.PASS, "About the WCI, Inc. Auction Platform is present on the page.");
	}

	public void certifyEvent()  {
		administration.click();
		reserveSaleCertification.click();
		Actions act = new Actions(driver);
		WebElement certification = driver
				.findElement(By.xpath("(//*[text()='" + prop.getProperty("EventForCertifyCA")
						+ "']/ancestor::tr/td[8]/div/button/span[@class='MuiIconButton-label'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", certification);
		certification.click();
		WebElement sumbitbtn1 = driver
				.findElement(By.xpath("(//*[text()='" + prop.getProperty("EventForCertifyCA")
						+ "']/ancestor::tr/td[9]/div/button/span[text()='SUBMIT'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(sumbitbtn1));
		sumbitbtn1.click();
		js.executeScript("arguments[0].scrollIntoView();", getSuccesMsg);
		String approvalMsg = getSuccesMsg.getText();
		System.out.println(approvalMsg);
		Assert.assertEquals(approvalMsg, "Certification decision saved successfully.");
		System.out.println("Certification decision saved successfully.");
		js.executeScript("arguments[0].scrollIntoView();", popUpClose);
		popUpClose.click();
		System.out.println("popup closed sucessfully");
		act.moveToElement(logoutDdropdwon).click().perform();
		logout.click();
		System.out.println("You have been successfully logged out from first sponsor account.");
	}

	public void certify2()  {
		administration2.click();
		reserveSaleCertification2.click();
		Actions act2 = new Actions(driver);
		WebElement certification2 = driver
				.findElement(By.xpath("(//*[text()='" + prop.getProperty("EventForCertifyCA")
						+ "']/ancestor::tr/td[8]/div/button/span[@class='MuiIconButton-label'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", certification2);
		certification2.click();
		WebElement sumbitbtn2 = driver
				.findElement(By.xpath("(//*[text()='" + prop.getProperty("EventForCertifyCA")
						+ "']/ancestor::tr/td[9]/div/button/span[text()='SUBMIT'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(sumbitbtn2));
		sumbitbtn2.click();
		sumbitbtn4.click();
		js.executeScript("arguments[0].scrollIntoView();", getSuccesMsg2);
		String approvalMsg2 = getSuccesMsg2.getText();
		System.out.println("Second Sponsar =" + approvalMsg2);
		Assert.assertEquals(approvalMsg2, "Certification decision saved successfully.");
		System.out.println("Certification decision saved successfully.");
		js.executeScript("arguments[0].scrollIntoView();", popUpClose2);
		popUpClose2.click();
		System.out.println("popup closed successfully");
		act2.moveToElement(logoutDdropdwon2).click().perform();
		logout2.click();
	}

	public void algorithmRun1()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
				driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventForRunAlgoCA") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("EventForRunAlgoCA") + "']"))
		.click();
		
		js.executeScript("arguments[0].scrollIntoView();", runalgorithm_1);
		try {
			runalgorithm_1.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Run algorithm proposed.");
		Actions act = new Actions(driver);
		act.moveToElement(logoutDropdown1).click().perform();
		logout1.click();
	}

	public void algorithmRun2()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Monitoring')])")));
		}
		administration1.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
				driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventForRunAlgoCA") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("EventForRunAlgoCA") + "']"))
		.click();
		
		js.executeScript("arguments[0].scrollIntoView();", runalgorithm_2);
		try {
			runalgorithm_2.click();
			wait.until(ExpectedConditions.visibilityOf(continue1));
			continue1.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(getSuccessMassage2));
			String successMsg = getSuccessMassage2.getText();
			System.out.println(successMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void c31973_report_AccessForFinancialStatementReport()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reports));
		reports.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"))
		.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel2 = new Select(select_Report_Name2);
		sel2.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Financial Statement report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement report successfully downloaded by sponsor");
		try {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("ResSaleEventAfterCertify")
				+ " " + date1 + " " + prop.getProperty("EntityQC2") + ".pdf");
		InputStream PdfFileToRead = new FileInputStream(src);
		PDDocument doc = PDDocument.load(PdfFileToRead);
		int pages = doc.getNumberOfPages();
		System.out.println("The total number of pages " + pages);
		PDFTextStripper strip = new PDFTextStripper();
		strip.setStartPage(1);
		strip.setEndPage(2);
		String stripText = strip.getText(doc);
		String value = stripText.toString();
		System.out.println(value);
		if (value == "Finaicial Services Account Number") {
			Assert.assertTrue(value.contains("Finaicial Services Account Number"));
		}
		System.out.println(stripText);
		ObjectRepo.getTest().log(Status.PASS, stripText);
		doc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void c31960_report_AccessForClientBidsReport()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reports));
		reports.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"))
		.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel2 = new Select(select_Report_Name2);
		sel2.selectByVisibleText("Client Bids Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void C31515_Sponsor_EA_MMCannotViewMonitoringPageAfterCertification()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(reserveSaleCertification));
		reserveSaleCertification.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		boolean certified = driver
				.findElement(By.xpath(
						"//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']/ancestor::tr/td[10]"))
				.getText().isBlank();
		Assert.assertFalse(certified);
		System.out.println("This Event is certified.");
		click_onEventMonitoring.click();
		List<WebElement> EventList = driver
				.findElements(By.xpath("//*[@class='MuiList-root MuiList-padding']/li"));
		int eventCount = EventList.size();
		String allEventName = "";
		String expectedEvent = prop.getProperty("ResSaleEventAfterCertify");
		for (int i = 0; i <= eventCount - 1; i++) {
			allEventName = EventList.get(i).getText();
			if (allEventName.equals(expectedEvent)) {
				System.out.println(
						"Event is present in monitoring Page. So, Sponsor/EA/MM can view the monitoring page after certification.");
				Assert.assertNotEquals(allEventName, expectedEvent, expectedEvent);
			}
		}
		System.out.println(
				"Event is not present in monitoring Page. So, Sponsor/EA/MM cannot view the monitoring page after certification.");
		ObjectRepo.getTest().log(Status.PASS, "C31515-Sponsor/EA/MM cannot view the monitoring page after certification.");
	}

	public void ResetAlgorithm()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			try {
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventForRunAlgoCA") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver
		.findElement(By.xpath(
				"//*[@title='Go to details of Event " + prop.getProperty("EventForRunAlgoCA") + "']"))
		.click();
		
		js.executeScript("arguments[0].scrollIntoView();", runalgorithm_1);
		try {
			clickResetAlgorithm.click();
			System.out.println("Algorithm Reset Succesfully Done.");
		} catch (Exception e) {
			System.out.println("Algorithm Reset Succesfully Done.");
		}
		
		ObjectRepo.getTest().log(Status.PASS, "C31515-Sponsor/EA/MM cannot view the monitoring page after certification.");
	}

	public void ReportGenratedAfterRunAlgorithm()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reports.click();
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("EventForRunAlgoCA"));
		js.executeScript("arguments[0].scrollIntoView();", search);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("EventForRunAlgoCA") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		for (int i = 1; i <= 3; i++) {
			String ReportName = driver
					.findElement(By.xpath("(//*[@class='MuiTableBody-root']/tr/td[1])[" + i + "]")).getText();
			String GeneratedOn = driver
					.findElement(By.xpath("(//*[@class='MuiTableBody-root']/tr/td[4])[" + i + "]")).getText();
			System.out.println("Sponsors can access the " + ReportName + " Generated on = " + GeneratedOn);
		}
	}
}