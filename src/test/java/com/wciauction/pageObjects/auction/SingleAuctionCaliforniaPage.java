package com.wciauction.pageObjects.auction;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
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

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.DownloadFinancialSettlementReport;
import com.wci.auction.org.resources.DownloadQualifiedBidderReport;
import com.wci.auction.org.resources.ObjectRepo;

import excelReadersForReports.ExcelDataProviderforResultReport;
import static constants.EventNameConstants.*;
import static constants.ThreadConstants.driver;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class SingleAuctionCaliforniaPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	String randomString;
	public String date1;

	@FindBy(xpath = "(//*[@name='vintages'])[1]")
	private WebElement dropDownVintage1;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[1]")
	private WebElement textBoxVintage1;

	@FindBy(xpath = "(//select[@name='vintageType'])[1]")
	private WebElement drpdownVintageType1;

	@FindBy(xpath = "(//select[@name='processingOrder'])[1]")
	private WebElement drpdownProCessOrder1;

	@FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
	private WebElement btnCreateEvent;

	@FindBy(xpath = "//input[@id='eventId']")
	private WebElement textBoxEventID;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[1]")
	private WebElement eventTypeDropDown;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventName;

	@FindBy(xpath = "//div[@id='demo-customized-select']")
	private WebElement ParticipatingJurisdictions;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
	private WebElement btnCalifornia;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[3]")
	private WebElement btnQuebec;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[4]")
	private WebElement btnWashinton;

	@FindBy(xpath = "//*[@id='eventNameInFrench']")
	private WebElement frenchEventName;

	@FindBy(xpath = "(//div[@id='demo-customized-select'])[2]")
	private WebElement currencyAcc;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement tagUSD;

	@FindBy(xpath = "(//input[@type='checkbox'])[3]")
	private WebElement tagCAD;

	@FindBy(xpath = "//input[@name='USD']")
	private WebElement textBoxUSD;

	@FindBy(xpath = "//input[@name='CAD']")
	private WebElement textBoxCAD;

	@FindBy(xpath = "(//input[@name='eventBiddingPeriodOpen'])")
	private WebElement dateBiddingPeroidOpen;

	@FindBy(xpath = "//input[@name='eventBiddingPeriodClose']")
	private WebElement dateBiddingPeriodClose;

	@FindBy(xpath = "//*[@name='eventApplicationOpen']")
	private WebElement dateEventAppOpen;

	@FindBy(xpath = "//input[@name='eventApplicationClose']")
	private WebElement dateEventAppClose;

	@FindBy(xpath = "//select[@name='algorithm']")
	private WebElement dropdownAlgorithm;

	@FindBy(xpath = "//span[text()='SAVE']")
	private WebElement btnSave;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnApproveCal;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithm1;

	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement rowsPerPage;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement rowsPerPage500;

	@FindBy(xpath = "//button[@title='Settings']")
	private WebElement btnSettings;

	@FindBy(xpath = "//a[text()='Execute API']")
	private WebElement btnExecuteAPI;

	@FindBy(xpath = "(//button[@title='Execute'])[2]")
	private WebElement APIGetQualifiedBidder;

	@FindBy(xpath = "(//button[@title='Execute'])[3]")
	private WebElement APIGetBidLimitations;

	@FindBy(xpath = "//span[text()='Execute API']")
	private WebElement APIexecutionPopupbtn;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//li[text()='English']")
	private WebElement langBtnEng;

	@FindBy(xpath = "//*[@title='Log in']")
	private WebElement btnLogin;

	@FindBy(xpath = "//input[@id='enter_email']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='enter_passphrase']")
	private WebElement password;

	@FindBy(xpath = "(//span[text()='Log in'])[2]")
	private WebElement btnLogin2;

	@FindBy(xpath = "//label[@class='MuiFormLabel-root Mui-required']")
	private WebElement securityQue;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInput-input']")
	private WebElement securityAnswer;

	@FindBy(xpath = "//span[contains(text(),'NEXT')]")
	private WebElement btnNext;

	@FindBy(xpath = "//span[contains(text(),'SOUMETTRE')]")
	private WebElement btnNextQC;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "//button[@aria-label='approval2Status']")
	private WebElement btnSecondApproval;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//button[@title='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//*[text()='Minimum Price']")
	private WebElement btnMinPrice;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[1]")
	private WebElement textBoxFxCal;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input Mui-disabled Mui-disabled'])[1]")
	private WebElement textBoxFxQc;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnsubmitqc;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnsubmitCal;

	@FindBy(xpath = "//*[contains(text(),'Welcome, ')]")
	private WebElement btnProfile;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//*[@href='/reservepriceadministrations']")
	private WebElement btnReservePrice2;

	@FindBy(xpath = "//button[@title='SUBMIT']")
	private WebElement btnConfirmSubmit;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root']")
	private WebElement btnAlertSubmit;

	@FindBy(xpath = "(//*[@title='Go to details of Event '])[1]")
	private WebElement eventDetailsBtn;

	@FindBy(xpath = "//*[text()='ADD BID']")
	private WebElement btnAddbid;

	@FindBy(xpath = "//input[@id='bidPrice']")
	private WebElement textBidPrice;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement textBidLots;

	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;

	@FindBy(xpath = "//span[text()='CONFIRM']")
	private WebElement btnConfirm;

	@FindBy(xpath = "//*[text()='Bid successfully added.']")
	private WebElement alertMsg;

	@FindBy(xpath = "//*[text()='Bid successfully submitted.']")
	private WebElement alertMsg2;

	@FindBy(xpath = "//*[@data-icon='times']")
	private WebElement alertPopup;

	@FindBy(xpath = "(//span[text()='Run Algorithm'])[2]")
	private WebElement btnRunAlgorithm2;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//a[@href='/report']")
	private WebElement btnReports;

	@FindBy(xpath = "//button//*[text()='Search Reports']")
	private WebElement btnSearchReports;

	@FindBy(xpath = "//button[@title='SEARCH']")
	private WebElement btnSearch;

	private By currentEvent = By.xpath("//*[@aria-label='Go to details of Event " + eventNameEng + "']");
	private By currentEventResSale = By.xpath("//*[@aria-label='Go to details of Event " + eventNameEng + "']");
	private By eventDetailsBtnCA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name") + "'])[1]");
	private By btnEntity5 = By.xpath("//span[text()='" + prop.getProperty("QCFirstEntity") + "']");
	private By btnEntity6 = By.xpath("//span[text()='" + prop.getProperty("QCSecondEntity") + "']");
	private By btnEntity7 = By.xpath("//span[text()='" + prop.getProperty("CAThirdEntity") + "']");
	private By btnParticularEvent = By.xpath("//*[contains(text(),'" + eventNameEng + "')]");
	private By btnDownloadResultReport = By
			.xpath("//*[@aria-label='Download Results Report for event " + eventNameEng + "']");
	private By btnDownloadResultReportForCalReserveSale = By
			.xpath("//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_NameForCA") + "']");
	private By btnDownloadQualifiedBidderReport = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventNameEng + "']");
	private By btnDownloadFinancialReport = By
			.xpath("//*[@aria-label='Download Financial Settlement Report for event " + eventNameEng + "']");

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement dropdownvintage;

	
	public SingleAuctionCaliforniaPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }

	public void loginsponsor() throws InterruptedException {
		username.sendKeys(prop.getProperty("UserSponsorCa"));
		password.sendKeys(prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("California sponsor2 login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California sponsor2 login successfully");
	}

	public void runAlgorithm2() throws InterruptedException {
		
		WebElement element1 = btnRunAlgorithm2;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
	}

	public void searchQualifiedbidderReportFunctionality() throws InterruptedException {
		try {

			btnReports.click();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnSearchReports));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();

			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']"))
					.sendKeys(eventNameEng);

			WebElement element1 = btnSearch;
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();

			WebElement element2 = wait
					.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReport));
			js.executeScript("arguments[0].scrollIntoView();", element2);

			driver.findElement(btnDownloadQualifiedBidderReport).click();
		} catch (Exception e) {

			btnReports.click();
			WebElement element = btnSearchReports;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']"))
					.sendKeys(eventNameEng);

			WebElement element1 = btnSearch;
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath(("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')])[2]")))
					.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReport));
			driver.findElement(btnDownloadQualifiedBidderReport).click();
		}
	}

	public void getQualifiedBidderReportData() throws Exception {
		DownloadQualifiedBidderReport report = new DownloadQualifiedBidderReport();

		// Registry General Acc No
		String colomn1name = report.getStringData("Qualified Bidders Report", 5, 0);
		String regGenAccNo1 = report.getStringData("Qualified Bidders Report", 6, 0);
		String regGenAccNo2 = report.getStringData("Qualified Bidders Report", 7, 0);
		String regGenAccNo3 = report.getStringData("Qualified Bidders Report", 8, 0);
		String regGenAccNo4 = report.getStringData("Qualified Bidders Report", 9, 0);
		String regGenAccNo5 = report.getStringData("Qualified Bidders Report", 10, 0);

		// Entity legal name
		String colomn2name = report.getStringData("Qualified Bidders Report", 5, 1);
		String entityLegalName1 = report.getStringData("Qualified Bidders Report", 6, 1);
		String entityLegalName2 = report.getStringData("Qualified Bidders Report", 7, 1);
		String entityLegalName3 = report.getStringData("Qualified Bidders Report", 8, 1);
		String entityLegalName4 = report.getStringData("Qualified Bidders Report", 9, 1);
		String entityLegalName5 = report.getStringData("Qualified Bidders Report", 10, 1);

		// Entity Operating Name
		String colomn3name = report.getStringData("Qualified Bidders Report", 5, 2);
		String entityOperatingName1 = report.getStringData("Qualified Bidders Report", 6, 2);
		String entityOperatingName2 = report.getStringData("Qualified Bidders Report", 7, 2);
		String entityOperatingName3 = report.getStringData("Qualified Bidders Report", 8, 2);
		String entityOperatingName4 = report.getStringData("Qualified Bidders Report", 9, 2);
		String entityOperatingName5 = report.getStringData("Qualified Bidders Report", 10, 2);

		// Entity type
		String colomn4name = report.getStringData("Qualified Bidders Report", 5, 3);
		String entityType1 = report.getStringData("Qualified Bidders Report", 6, 3);
		String entityType2 = report.getStringData("Qualified Bidders Report", 7, 3);
		String entityType3 = report.getStringData("Qualified Bidders Report", 8, 3);
		String entityType4 = report.getStringData("Qualified Bidders Report", 9, 3);
		String entityType5 = report.getStringData("Qualified Bidders Report", 10, 3);

		// Entity Jurisdiction
		String colomn5name = report.getStringData("Qualified Bidders Report", 5, 4);
		String entJurisdiction1 = report.getStringData("Qualified Bidders Report", 6, 4);
		String entJurisdiction2 = report.getStringData("Qualified Bidders Report", 7, 4);
		String entJurisdiction3 = report.getStringData("Qualified Bidders Report", 8, 4);
		String entJurisdiction4 = report.getStringData("Qualified Bidders Report", 9, 4);
		String entJurisdiction5 = report.getStringData("Qualified Bidders Report", 10, 4);

		// FSA Acc No
		String colomn6name = report.getStringData("Qualified Bidders Report", 5, 5);
		String fsaAccNum1 = report.getStringData("Qualified Bidders Report", 6, 5);
		String fsaAccNum2 = report.getStringData("Qualified Bidders Report", 7, 5);
		String fsaAccNum3 = report.getStringData("Qualified Bidders Report", 8, 5);
		String fsaAccNum4 = report.getStringData("Qualified Bidders Report", 9, 5);
		String fsaAccNum5 = report.getStringData("Qualified Bidders Report", 10, 5);

		// Bid Guarantee Currency
		String colomn7name = report.getStringData("Qualified Bidders Report", 5, 5);
		String bidGuaranteeCurrency1 = report.getStringData("Qualified Bidders Report", 6, 6);
		String bidGuaranteeCurrency2 = report.getStringData("Qualified Bidders Report", 7, 6);
		String bidGuaranteeCurrency3 = report.getStringData("Qualified Bidders Report", 8, 6);
		String bidGuaranteeCurrency4 = report.getStringData("Qualified Bidders Report", 9, 6);
		String bidGuaranteeCurrency5 = report.getStringData("Qualified Bidders Report", 10, 6);

		// Total bid Guarantee value
		String colomn11name = report.getStringData("Qualified Bidders Report", 5, 11);
		double totalbidGuaranteeCurrency1 = report.getNumericData("Qualified Bidders Report", 6, 11);
		double totalbidGuaranteeCurrency2 = report.getNumericData("Qualified Bidders Report", 7, 11);
		double totalbidGuaranteeCurrency3 = report.getNumericData("Qualified Bidders Report", 8, 11);
		double totalbidGuaranteeCurrency4 = report.getNumericData("Qualified Bidders Report", 9, 11);
		double totalbidGuaranteeCurrency5 = report.getNumericData("Qualified Bidders Report", 10, 11);

		// Purchase limit for current auction
		String colomn12name = report.getStringData("Qualified Bidders Report", 5, 12);
		double purchaselimit1 = report.getNumericData("Qualified Bidders Report", 6, 12);
		double purchaselimit2 = report.getNumericData("Qualified Bidders Report", 7, 12);
		double purchaselimit3 = report.getNumericData("Qualified Bidders Report", 8, 12);
		double purchaselimit4 = report.getNumericData("Qualified Bidders Report", 9, 12);
		double purchaselimit5 = report.getNumericData("Qualified Bidders Report", 10, 12);

		// Holding limit for current auction
		String colomn14name = report.getStringData("Qualified Bidders Report", 5, 14);
		long holdingLimit1 = report.getNumericData1("Qualified Bidders Report", 6, 14);
		long holdingLimit2 = report.getNumericData1("Qualified Bidders Report", 7, 14);
		long holdingLimit3 = report.getNumericData1("Qualified Bidders Report", 8, 14);
		long holdingLimit4 = report.getNumericData1("Qualified Bidders Report", 9, 14);
		long holdingLimit5 = report.getNumericData1("Qualified Bidders Report", 10, 14);

		System.out.println(colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
				+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
				+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency1 + ",  " + colomn12name + " : " + purchaselimit1 + ",  " + colomn14name
				+ " : " + holdingLimit1);

		System.out.println(colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
				+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
				+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency2 + ",  " + colomn12name + " : " + purchaselimit2 + ",  " + colomn14name
				+ " : " + holdingLimit2);

		System.out.println(colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
				+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
				+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency3 + ",  " + colomn12name + " : " + purchaselimit3 + ",  " + colomn14name
				+ " : " + holdingLimit3);

		System.out.println(colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
				+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
				+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency4 + ",  " + colomn12name + " : " + purchaselimit4 + ",  " + colomn14name
				+ " : " + holdingLimit4);

		System.out.println(colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
				+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
				+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency5 + ",  " + colomn12name + " : " + purchaselimit5 + ",  " + colomn14name
				+ " : " + holdingLimit5);

	}

	public void searchReportFunctionality() throws InterruptedException {
		WebElement element2 = btnReports;
		Actions action = new Actions(driver);
		action.moveToElement(element2).build().perform();
		action.click(element2).build().perform();
		WebElement element = driver.findElement(btnDownloadResultReport);
		
		js.executeScript("arguments[0].click()", element);
	}

	public void validateResultReport() throws Exception {
		ExcelDataProviderforResultReport excel = new ExcelDataProviderforResultReport();

		excel.getStringData("FINAL RESULTS", 1, 1);
		excel.getStringData("FINAL RESULTS", 2, 1);
		excel.getStringData("FINAL RESULTS", 3, 1);
		excel.getStringData("FINAL RESULTS", 4, 1);
		excel.getStringData("FINAL RESULTS", 5, 1);

		excel.getNumericData("FINAL RESULTS", 1, 2);
		excel.getNumericData("FINAL RESULTS", 2, 2);
		excel.getNumericData("FINAL RESULTS", 3, 2);
		excel.getNumericData("FINAL RESULTS", 4, 2);
		excel.getNumericData("FINAL RESULTS", 5, 2);

		String firstresult = prop.getProperty("Bidder1Allowances");
		String secondresult = prop.getProperty("Bidder2Allowances");
		String thirdresult = prop.getProperty("Bidder3Allowances");
		String fourthresult = prop.getProperty("Bidder4Allowances");
		String fifthresult = prop.getProperty("Bidder5Allowances");

		Double.parseDouble(firstresult);
		Double.parseDouble(secondresult);
		Double.parseDouble(thirdresult);
		Double.parseDouble(fourthresult);
		Double.parseDouble(fifthresult);
	}

	public void eventCertification() throws InterruptedException {
		SingleAuctionCaliforniaPage login = new SingleAuctionCaliforniaPage();
		login.sponsorLoginCa2();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		login.clickbtnAdmin();
		driver.findElement(By.xpath("(//*[text()='Auction Certification'])[1]")).click();
		driver.findElement(By.xpath("//*[@aria-label='firstAuctionCertified']")).click();
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]")).click();
		login.Logout();
	}

	public void eventCertificationDualApproval() throws InterruptedException {
		SingleAuctionCaliforniaPage login = new SingleAuctionCaliforniaPage();
		login.sponsorLoginQc();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		login.clickbtnAdmin();
		driver.findElement(By.xpath("(//*[text()='Auction Certification'])[1]")).click();

		driver.findElement(By.xpath("//*[@aria-label='secondAuctionCertified']")).click();

		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]"))
				.click();
	}

	public void searchFinancialSettlementReportFunctionality() throws InterruptedException {
		try {

			btnReports.click();
			WebElement element = btnSearchReports;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']")).click();
			driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']"))
					.sendKeys(eventNameEng);

			WebElement element1 = btnSearch;
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')]"))
					.click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnParticularEvent));
			driver.findElement(btnParticularEvent).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadFinancialReport));
			driver.findElement(btnDownloadFinancialReport).click();
		} catch (Exception e) {

			btnReports.click();
			WebElement element = btnSearchReports;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
			btnSearchReports.click();
			driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(eventNameEng);

			WebElement element1 = btnSearch;
			
			js.executeScript("arguments[0].scrollIntoView();", element1);
			action.moveToElement(element1).build().perform();
			action.click(element1).build().perform();
			driver.findElement(By.xpath("//*[contains(text(),'" + prop.getProperty("Event_Name") + "')]"));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnParticularEvent));
			driver.findElement(btnParticularEvent).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadFinancialReport));
			driver.findElement(btnDownloadFinancialReport).click();
		}
	}

	public void getDataFromFinancialSettlementReport() throws Exception {

		DownloadFinancialSettlementReport ref = new DownloadFinancialSettlementReport();

		String currentSettPriceUSD = ref.getStringData("Financial Settlement Report", 4, 0);
		String currentSettPriceCAD = ref.getStringData("Financial Settlement Report", 5, 0);
		String settlementpriceUSD = ref.getStringData("Financial Settlement Report", 4, 1);
		double settlementpriceCAD = ref.getNumericData("Financial Settlement Report", 5, 1);

		String Colomn1 = ref.getStringData("Financial Settlement Report", 7, 0);
		String regGenAccNo1 = ref.getStringData("Financial Settlement Report", 8, 0);
		String regGenAccNo2 = ref.getStringData("Financial Settlement Report", 9, 0);
		String regGenAccNo3 = ref.getStringData("Financial Settlement Report", 10, 0);
		String regGenAccNo4 = ref.getStringData("Financial Settlement Report", 11, 0);
		String regGenAccNo5 = ref.getStringData("Financial Settlement Report", 12, 0);

		String Colomn12 = ref.getStringData("Financial Settlement Report", 7, 11);
		double totalBidGuarantee1 = ref.getNumericData("Financial Settlement Report", 8, 11);
		double totalBidGuarantee2 = ref.getNumericData("Financial Settlement Report", 9, 11);
		double totalBidGuarantee3 = ref.getNumericData("Financial Settlement Report", 10, 11);
		double totalBidGuarantee4 = ref.getNumericData("Financial Settlement Report", 11, 11);
		double totalBidGuarantee5 = ref.getNumericData("Financial Settlement Report", 12, 11);

		String Colomn13 = ref.getStringData("Financial Settlement Report", 7, 12);
		double currentSuccBidAllowances1 = ref.getNumericData("Financial Settlement Report", 8, 12);
		double currentSuccBidAllowances2 = ref.getNumericData("Financial Settlement Report", 9, 12);
		double currentSuccBidAllowances3 = ref.getNumericData("Financial Settlement Report", 10, 12);
		double currentSuccBidAllowances4 = ref.getNumericData("Financial Settlement Report", 11, 12);
		double currentSuccBidAllowances5 = ref.getNumericData("Financial Settlement Report", 12, 12);

		String Colomn15 = ref.getStringData("Financial Settlement Report", 7, 14);
		double totalsuccessfullbidcost1 = ref.getNumericData("Financial Settlement Report", 8, 14);
		double totalsuccessfullbidcost2 = ref.getNumericData("Financial Settlement Report", 9, 14);
		double totalsuccessfullbidcost3 = ref.getNumericData("Financial Settlement Report", 10, 14);
		double totalsuccessfullbidcost4 = ref.getNumericData("Financial Settlement Report", 11, 14);
		double totalsuccessfullbidcost5 = ref.getNumericData("Financial Settlement Report", 12, 14);

		String Colomn16 = ref.getStringData("Financial Settlement Report", 7, 15);
		double amountOwed1 = ref.getNumericData("Financial Settlement Report", 8, 15);
		double amountOwed2 = ref.getNumericData("Financial Settlement Report", 9, 15);
		double amountOwed3 = ref.getNumericData("Financial Settlement Report", 10, 15);
		double amountOwed4 = ref.getNumericData("Financial Settlement Report", 11, 15);
		double amountOwed5 = ref.getNumericData("Financial Settlement Report", 12, 15);

		String Colomn17 = ref.getStringData("Financial Settlement Report", 7, 16);
		String amountOwedBy1 = ref.getStringData("Financial Settlement Report", 8, 16);
		String amountOwedBy2 = ref.getStringData("Financial Settlement Report", 9, 16);
		String amountOwedBy3 = ref.getStringData("Financial Settlement Report", 10, 16);
		String amountOwedBy4 = ref.getStringData("Financial Settlement Report", 11, 16);
		String amountOwedBy5 = ref.getStringData("Financial Settlement Report", 12, 16);

		System.out.println(currentSettPriceUSD + " : " + settlementpriceUSD);
		System.out.println(currentSettPriceCAD + " : " + settlementpriceCAD);

		System.out.println(Colomn1 + " : " + regGenAccNo1 + " , " + Colomn12 + " : " + totalBidGuarantee1 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances1 + " , " + Colomn15 + " : " + totalsuccessfullbidcost1
				+ " , " + Colomn16 + " : " + amountOwed1 + " , " + Colomn17 + " : " + amountOwedBy1);
		System.out.println(Colomn1 + " : " + regGenAccNo2 + " , " + Colomn12 + " : " + totalBidGuarantee2 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances2 + " , " + Colomn15 + " : " + totalsuccessfullbidcost2
				+ " , " + Colomn16 + " : " + amountOwed2 + " , " + Colomn17 + " : " + amountOwedBy2);
		System.out.println(Colomn1 + " : " + regGenAccNo3 + " , " + Colomn12 + " : " + totalBidGuarantee3 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances3 + " , " + Colomn15 + " : " + totalsuccessfullbidcost3
				+ " , " + Colomn16 + " : " + amountOwed3 + " , " + Colomn17 + " : " + amountOwedBy3);
		System.out.println(Colomn1 + " : " + regGenAccNo4 + " , " + Colomn12 + " : " + totalBidGuarantee4 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances4 + " , " + Colomn15 + " : " + totalsuccessfullbidcost4
				+ " , " + Colomn16 + " : " + amountOwed4 + " , " + Colomn17 + " : " + amountOwedBy4);
		System.out.println(Colomn1 + " : " + regGenAccNo5 + " , " + Colomn12 + " : " + totalBidGuarantee5 + " , "
				+ Colomn13 + " : " + currentSuccBidAllowances5 + " , " + Colomn15 + " : " + totalsuccessfullbidcost5
				+ " , " + Colomn16 + " : " + amountOwed5 + " , " + Colomn17 + " : " + amountOwedBy5);
	}

	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	public void clickbtnCreateEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateEvent));
		btnCreateEvent.click();
	}

	public void generateString() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomString = ("Event_" + generatedString);
	}

	public void gettextBoxEvent() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID"));
	}

	public void selectEventTypeFromDropDown() {
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("Auction");
	}

	public void enterEventName() {
		eventName.sendKeys(eventNameEng);
	}

	public void selectJurisdictionForJointAuction() throws InterruptedException {
		ParticipatingJurisdictions.click();
		try {
			btnCalifornia.click();
			btnQuebec.click();
		} catch (Exception e) {
			btnWashinton.click();
		}
		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("Événement test");
	}

	public void currencyAccForjointAuction() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
			;
		}
		tagUSD.click();
		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD"));
	}

	public void selectVintage() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2022");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances"));
	}

	public void selectVintageType() {
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText("Current");
	}

	public void selectProcessingOrder() throws InterruptedException {
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
	}

	public void selectDates() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);

		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(4).plusSeconds(10);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(12).plusSeconds(40);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(3);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(1);
		System.out.println("application Close time :" + applicationClose);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + BiddingStart);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));
		action.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + BiddingEnd);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + applicationClose);
	}

	public void selectAlgorithm() throws Exception {
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		btnSave.click();
	}

	public void createEventForJointAuction() throws Exception {

		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdictionForJointAuction();
		currencyAccForjointAuction();
		selectVintage();
		selectVintageType();
		selectProcessingOrder(); // Dates and Times
		selectDates();
		selectAlgorithm();
	}

	public void CaliforniaJuridictionApprovalForEventCreation() throws InterruptedException {

		
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		btnApproveCal.click();
		btnSubmit1.click();
		System.out.println("First Sponsor approved succesfully");
	}

	public void runtimesponsorLoginCa() throws InterruptedException {
		try {
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			password.sendKeys("Test@1234");
			btnLogin.click();
			System.out.println("California sponsor2 login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			password.sendKeys("Test@1234");
			btnLogin2.click();
			System.out.println("California sponsor2 login successfully");
		}
	}

	public void secondApproval() throws InterruptedException {
		SingleAuctionCaliforniaPage ref = new SingleAuctionCaliforniaPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref2 = eventDetailsBtn;
		
		js.executeScript("arguments[0].scrollIntoView();", ref2);
		Actions action = new Actions(driver);
		action.moveToElement(ref2).build().perform();
		action.click(ref2).build().perform();
		System.out.println("Navigate to particular event ");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
			WebElement element1 = btnApprovalQue;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
			btnApprovalQue.click();
		} catch (Exception e) {

			btnApprovalQue.click();
		}
		try {
			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}
		WebElement abc1 = popupBtn;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
	}

	public void setFXrateQC() throws InterruptedException {
		driver.navigate().refresh();
		SingleAuctionCaliforniaPage ref = new SingleAuctionCaliforniaPage();
		ref.clickbtnAdmin();

		try {
			btnMinPrice.click();
		} catch (Exception e1) {
			btnReservePrice.click();
			;
		}
		try {
			
			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate"));
		} catch (Exception e) {
			
			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();

	}

	public void setFXrateCal() throws InterruptedException {
		try {
			SingleAuctionCaliforniaPage ref = new SingleAuctionCaliforniaPage();
			ref.clickbtnAdmin();
		} catch (Exception e1) {
			WebElement abc1 = btnAdminstration;
			Actions action = new Actions(driver);
			action.moveToElement(abc1).build().perform();

			btnAdminstration.click();
		}
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {
			
			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate"));
		} catch (Exception e) {
			
			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate"));
		}
		try {
			btnsubmitCal.click();
		} catch (Exception e) {
			btnsubmitqc.click();
		}
		btnConfirmSubmit.click();
		System.out.println("fx rate set for California juridiction ");
	}

	public void executeAPIGetQualifiedBidder() {
		try {
			List<WebElement> links = driver.findElements(By.xpath("//a"));
			links.size();
			for (int i = 0; i <= links.size() - 1; i++) {
				links.get(i).getText();
				System.out.println(links.get(i).getText());
			}

			wait.until(ExpectedConditions.elementToBeClickable(btnSettings));
			WebElement abc = btnSettings;
			Actions action = new Actions(driver);
			action.moveToElement(abc).build().perform();
			action.clickAndHold(abc).build().perform();
			action.click(abc).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
			btnExecuteAPI.click();
			
			WebElement element1 = APIGetQualifiedBidder;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			APIGetQualifiedBidder.click();
		} catch (Exception e) {
			APIGetQualifiedBidder.click();
		}
		APIexecutionPopupbtn.click();
		System.out.println("Get Qualified bidder API  Executed successfully");
	}

	public void executeAPIGetBidLimitations() throws InterruptedException {
		try {
			
			WebElement element1 = APIGetBidLimitations;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			APIGetBidLimitations.click();
		} catch (Exception e) {
			APIGetBidLimitations.click();
		}
		APIexecutionPopupbtn.click();
		System.out.println("Get Bid Limitation API  Executed successfully");
	}

	public void ARLoginQC3() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin.click();
		}

	}

	public void clickOnnextQC() throws InterruptedException {

		WebElement abc = btnNextQC;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
	}

	public void SelectEntity1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity5));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void selectEventForBiddingE2E() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			driver.findElement(By.xpath("//*[text()='" + eventNameEng + "']")).click();
		}

	}

	public void BiddingByARforTestData1() throws IOException, InterruptedException {
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		action.click(element).build().perform();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5() throws Exception {
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		action.click(element).build().perform();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		wait.until(ExpectedConditions.elementToBeClickable(alertPopup)).click();
		;
	}

	public void BiddingByARforTestData10() throws Exception {
		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20() throws Exception {

		try {
			WebElement element = btnAddbid;
			
			js.executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void SelectEntity2() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity6));
		driver.findElement(btnEntity6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void ARLoginQC2() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}

	}

	public void SelectEntity3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void SelectEntity4() {
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CAFourthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void SelectEntity5() {
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CAFifthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void clickbtnEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void getCurrentEvent() {

		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		
		WebElement element1 = driver.findElement(currentEvent);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(currentEvent).click();

	}

	public void runAlgorithm() throws InterruptedException {
		
		WebElement element1 = btnRunAlgorithm1;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
	}

	public void RuntimeSponsorLoginQc() throws InterruptedException {
		try {
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			btnLogin.click();
			System.out.println("Quebec sponsor login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("Quebec sponsor login successfully");
		}
	}

	public void changeLangauge() throws InterruptedException {
		
		WebElement element = langBtnFrench;
		js.executeScript("arguments[0].scrollIntoView();", element);
		langBtnFrench.click();

		langBtnEng.click();
		System.out.println("Langauge changed successfully from french to english ");

	}

	public void Logout() throws InterruptedException {
		try {
			driver.navigate().refresh();

			wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
			btnProfile.click();
			btnLogout.click();
//			driver.navigate().refresh();

		} catch (Exception e) {
			driver.navigate().refresh();

			wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
			btnProfile.click();
			btnLogout.click();
//			driver.navigate().refresh();

		}

	}

	public void clickbtnAdmin() {
		try {

			WebElement abc = wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
			Actions action = new Actions(driver);
			action.moveToElement(abc).build().perform();

			action.clickAndHold(abc).build().perform();
			action.click(abc).build().perform();
			// ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		} catch (Exception e) {
			btnAdminstration.click();
		}

	}

	public void clickOnnext() {

		WebElement abc = btnNext;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
//		////                               

	}

	public void handleSecurityQue() throws InterruptedException {

		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);

		if (str.contains("job?")) {
			securityAnswer.sendKeys("Answer_1");
		} else if (str.contains("teacher?")) {
			securityAnswer.sendKeys("Answer_2");
		} else if (str.contains("card?")) {
			securityAnswer.sendKeys("Answer_3");
		} else if (str.contains("emploi?")) {
			securityAnswer.sendKeys("Answer_1");
		} else if (str.contains("année?")) {
			securityAnswer.sendKeys("Answer_2");
		} else if (str.contains("crédit?")) {
			securityAnswer.sendKeys("Answer_3");
		} else {
			System.out.println("None of the above security question");
		}

	}

	public void sponsorLoginCa2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorCa"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("California sponsor2 login successfully");
			// ObjectRepo.getTest().log(Status.PASS, "California sponsor2 login successfully");
		} catch (Exception e) {
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorCa"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("California sponsor2 login successfully");
		}

	}

	public void sponsorLoginQc() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("Quebec sponsor login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("Quebec sponsor login successfully");
		}
	}

	public void ARLoginCA() throws InterruptedException {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("Password1"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("Password1"));
			btnLogin.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		}
	}

	public void selectEventForBidding() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			driver.findElement(By.xpath("//*[text()='" + eventNameEng + "']")).click();
			System.out.println("Navigae to particular Event: " + eventNameEng);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameEng);
		}
	}

	public void searchReportFunctionalityForCalIndAuction() throws InterruptedException {
		btnReports.click();
		WebElement element = btnSearchReports;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		btnSearchReports.click();
		driver.findElement(By.xpath("//input[@id='auto' and @aria-label='Event Name']"))
				.sendKeys(prop.getProperty("Event_NameForCA"));

		WebElement element1 = btnSearch;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		action.moveToElement(element1).build().perform();
		action.click(element1).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReportForCalReserveSale));
		driver.findElement(btnDownloadResultReportForCalReserveSale).click();

	}

	public void getCurrentEventForIndividualAuctionCal() {

		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		
		WebElement element1 = driver.findElement(currentEventResSale);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(currentEventResSale).click();
	}


	public void CASelectEntity1() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity5));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("QCFirstEntity"));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SecondApprovalBySameJurisdiction() throws InterruptedException {
		WebElement abc = btnAdminstration;
		Actions action = new Actions(driver);
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.elementToBeClickable(popupBtn));
		WebElement abc11 = popupBtn;
		action.moveToElement(abc11).build().perform();
		action.clickAndHold(abc11).build().perform();
		action.click(abc11).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void firstApproval() throws InterruptedException {

		WebElement element = driver.findElement(By.xpath("(//button[@type='button'])[10]"));
		try {
			driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
		} catch (Exception e) {
			element.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();

	}

	public void gettextBoxEventForCA() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String eventid = randomString = ("Event_" + generatedString);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(eventid);
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
	}

	public void selectJurisdiction() throws InterruptedException {
		ParticipatingJurisdictions.click();
		try {
			btnCalifornia.click();
		} catch (Exception e) {
			btnWashinton.click();
		}
		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("ABC");
	}

	public void currencyAccForCal() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).click().build().perform();
		} catch (Exception e) {
			currencyAcc.click();
			;
		}
		tagUSD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.clickAndHold(abc4).build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD"));
	}

	public void createEventForCal() throws Exception {

		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForCA(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[1]")).click();
		selectVintage();
		selectVintageType();
		selectProcessingOrder(); // Dates and Times
		selectDates();
		selectAlgorithm();
	}

}
