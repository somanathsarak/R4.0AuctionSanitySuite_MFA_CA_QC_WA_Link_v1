package com.wciauction.pageObjects.auction;

import static constants.EventNameConstants.eventNameCA;
import static constants.EventNameConstants.eventNameEng;
import static constants.EventNameConstants.eventNameQC;
import static constants.EventNameConstants.eventName_PE1;
import static constants.EventNameConstants.eventName_PE10;
import static constants.EventNameConstants.eventName_PE2;
import static constants.EventNameConstants.eventName_PE3;
import static constants.EventNameConstants.eventName_PE4;
import static constants.EventNameConstants.eventName_PE5;
import static constants.EventNameConstants.eventName_PE6;
import static constants.EventNameConstants.eventName_PE7;
import static constants.EventNameConstants.eventName_PE8;
import static constants.EventNameConstants.eventName_PE9;
import static constants.ThreadConstants.driver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.wci.auction.org.resources.CommonMethods;
import com.wci.auction.org.resources.DownloadQualifiedBidderReport;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.reports.DownloadReportsPage;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.EntityData;
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class JointAuctionTieBreakerPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public static String path;
	public String date1;
	public static Actions action;
	String randomString;
	public static String newEventName;
	XSSFWorkbook wb;
	
	@FindBy(xpath = "//*[text()='FX Rate submitted.']")
	private WebElement QCFxRateText;

	@FindBy(xpath = "(//span[text()='CONFIRM'])[1]")
	private WebElement firstSponsorConfirmBtn;

	@FindBy(xpath = "(//span[text()='CONFIRM'])[2]")
	private WebElement secondSponsorConfirmBtn;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithm1;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement btnRunAlgorithm2;

	private By btnQualifiedBidderReports_PE1 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE1 + "']");
	private By btnResultReports_PE1 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE1 + "']");
	private By btnQualifiedBidderReports_PE2 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE2 + "']");
	private By btnQualifiedBidderReports_PE3 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE3 + "']");
	private By btnQualifiedBidderReports_PE4 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE4 + "']");
	private By btnQualifiedBidderReports_PE5 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE5 + "']");
	private By btnQualifiedBidderReports_PE6 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE6 + "']");
	private By btnQualifiedBidderReports_PE7 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE7 + "']");
	private By btnQualifiedBidderReports_PE8 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE8 + "']");
	private By btnQualifiedBidderReports_PE9 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE9 + "']");
	private By btnQualifiedBidderReports_PE10 = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventName_PE10 + "']");
	private By btnResultReports_PE2 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE2 + "']");
	private By btnResultReports_PE3 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE3 + "']");
	private By btnResultReports_PE4 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE4 + "']");
	private By btnResultReports_PE5 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE5 + "']");
	private By btnResultReports_PE6 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE6 + "']");
	private By btnResultReports_PE7 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE7 + "']");
	private By btnResultReports_PE8 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE8 + "']");
	private By btnResultReports_PE9 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE9 + "']");
	private By btnResultReports_PE10 = By
			.xpath("//button[@aria-label='Download Results Report for event " + eventName_PE10 + "']");
	private By eventDetailsBtn_PE1 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE1 + "'])[1]");
	private By eventDetailsBtn_PE2 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE2 + "'])[1]");
	private By eventDetailsBtn_PE3 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE3 + "'])[1]");
	private By eventDetailsBtn_PE4 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE4 + "'])[1]");
	private By eventDetailsBtn_PE5 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE5 + "'])[1]");
	private By eventDetailsBtn_PE6 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE6 + "'])[1]");
	private By eventDetailsBtn_PE7 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE7 + "'])[1]");
	private By eventDetailsBtn_PE8 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE8 + "'])[1]");
	private By eventDetailsBtn_PE9 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE9 + "'])[1]");
	private By eventDetailsBtn_PE10 = By.xpath("(//*[@title='Go to details of Event " + eventName_PE10 + "'])[1]");
	private By eventDetailsBtnReport = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name") + "'])[1]");
	private By eventDetailsBtnReportQC = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name") + "'])[1]");
	private By btnEntity5 = By.xpath("//span[text()='" + EntityData.QCFirstEntity + "']");
	private By Selectentity1_PE1 = By.xpath("//span[text()='" + prop.getProperty("CAFirstEntity_PE1") + "']");
	private By Selectentity2_PE1 = By.xpath("//span[text()='" + prop.getProperty("CASecondEntity_PE1") + "']");
	private By eventDetailsBtnWA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameWS") + "'])[1]");
	private By eventDetailsBtnCASingleAUction = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameCA") + "'])[1]");
	private By eventDetailsBtnCA2 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName") + "'])[1]");
	private By eventDetailsBtnRSQCR70 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name_RE1") + "'])[1]");
	private By eventDetailsBtnRS_WA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName_WA") + "'])[1]");
	private By eventnameonbidpage = By.xpath("(//*[text()='" + eventNameEng + "'])[1]");
	private By eventnameonbidpageCalJointAuction = By
			.xpath("(//*[text()='" + prop.getProperty("Event_Name") + "'])[1]");
	private By eventnameonbidpage_PE1 = By.xpath("(//*[text()='" + eventName_PE1 + "'])[1]");
	// (remaining similar lines truncated for brevity)
	private By Selectentity5_PE10 = By.xpath("//span[text()='" + prop.getProperty("QCSecondEntity_PE10") + "']");
	private By btnEntity6 = By.xpath("//span[text()='" + EntityData.QCSecondEntity + "']");
	private By btnEntity7 = By.xpath("//span[text()='" + prop.getProperty("CAThirdEntity") + "']");
	private By btnDownloadResultReport = By
			.xpath("//*[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name") + "']");
	private By btnDownloadQualifiedBidderReport = By.xpath(
			"//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("Event_Name") + "']");

	@FindBy(xpath = "//p[text()='JointAuctionAutomation']")
	private WebElement runningEventName;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement runningEventName1;

	@FindBy(xpath = "//*[@aria-label='secondAuctionCertified']")
	private WebElement QCcertificationbutton;

	@FindBy(xpath = "(//*[@name='vintages'])[1]")
	private WebElement dropDownVintage1;

	@FindBy(xpath = "(//*[@name='vintages'])[2]")
	private WebElement dropDownVintage2;

	@FindBy(xpath = "//p[contains(normalize-space(.), 'Run algorithm proposed')]")
	private WebElement runAlgorithmProposedSuccessMsg;

	@FindBy(xpath = "//p[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'two sponsors have confirmed')]")
	private WebElement twoSponsorsConfirmSuccessMsg;

	@FindBy(xpath = "(//select[@name='vintageType'])[1]")
	private WebElement drpdownVintageType1;

	@FindBy(xpath = "(//select[@name='vintageType'])[2]")
	private WebElement drpdownVintageType2;

	@FindBy(xpath = "(//select[@name='processingOrder'])[1]")
	private WebElement drpdownProCessOrder1;

	@FindBy(xpath = "(//select[@name='processingOrder'])[2]")
	private WebElement drpdownProCessOrder2;

	@FindBy(xpath = "//select[@name='algorithm']")
	private WebElement dropdownAlgorithm;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[1]")
	private WebElement eventTypeDropDown;

	@FindBy(xpath = "//div[@id='demo-customized-select']")
	private WebElement ParticipatingJurisdictions;

	@FindBy(xpath = "(//div[@id='demo-customized-select'])[2]")
	private WebElement currencyAcc;

	@FindBy(xpath = "//*[text()='Events']")
	private WebElement btnevent;

	@FindBy(xpath = "//*[text()='You have been successfully logged out.']")
	private WebElement Logoutmessage;

	@FindBy(xpath = "//*[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement ApprovalMessage;

	@FindBy(xpath = "(//*[text()='Auction Certification'])[1]")
	private WebElement AuctionCertificationButton;

	@FindBy(xpath = "(//*[text()='Reserve Sale Certification'])[1]")
	private WebElement reserveSaleCertificationButton;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement submitBtncertification;

	@FindBy(xpath = "(//*[text()='Sale Certification'])[1]")
	private WebElement SBMACertificationButton;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement submitBtncertificationsubmitpopup;

	@FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
	private WebElement btnCreateEvent;

	@FindBy(xpath = "//span[text()='SAVE']")
	private WebElement btnSave;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//button[@title='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//*[text()='Minimum Price']")
	private WebElement btnMinPrice;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnsubmitqc;

	private By btnsubmitqc1 = By.xpath("(//*[text()='" + prop.getProperty("Event_Name") + "'])/following::td[8]");

	private By btnsubmitCal1 = By.xpath(
			"(//*[text()='" + prop.getProperty("Event_Name") + "']/ancestor::tr/following-sibling::tr/td[4])[1]");
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

	@FindBy(xpath = "(//*[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement btnAlertSubmit;

	@FindBy(xpath = "//a[text()='Reports']")
	private WebElement btnReport;

	@FindBy(xpath = "//span[text()='Search Reports']")
	private WebElement btnSearchReport;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement reportNameDropDown1;

	@FindBy(xpath = "(//input[@aria-label='Event Name'])")
	private WebElement Bdropdwnname;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[1]")
	private WebElement textBoxFxCal;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input Mui-disabled Mui-disabled'])[1]")
	private WebElement textBoxFxQc;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventName;

	@FindBy(xpath = "//*[@id='eventNameInFrench']")
	private WebElement frenchEventName;

	@FindBy(xpath = "//input[@name='USD']")
	private WebElement textBoxUSD;

	@FindBy(xpath = "//input[@name='CAD']")
	private WebElement textBoxCAD;

	@FindBy(xpath = "//input[@id='eventId']")
	private WebElement textBoxEventID;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[1]")
	private WebElement textBoxVintage1;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[2]")
	private WebElement textBoxVintage2;

	@FindBy(xpath = "(//input[@name='eventBiddingPeriodOpen'])")
	private WebElement dateBiddingPeroidOpen;

	@FindBy(xpath = "//input[@name='eventBiddingPeriodClose']")
	private WebElement dateBiddingPeriodClose;

	@FindBy(xpath = "//*[@name='eventApplicationOpen']")
	private WebElement dateEventAppOpen;

	@FindBy(xpath = "//*[@name='eventApplicationClose']")
	private WebElement dateEventAppClose;

	@FindBy(xpath = "//input[@name='resultsAvailable']")
	private WebElement dateBidResultAvail;

	@FindBy(xpath = "//input[@name='eventClosure']")
	private WebElement dateEventClosure;

	@FindBy(xpath = "//input[@name='financialSettlement']")
	private WebElement dateFinantialSettlement;

	@FindBy(xpath = "//*[text()='ADD BID']")
	private WebElement btnAddbid;

	@FindBy(xpath = "//input[@id='bidPrice']")
	private WebElement textBidPrice;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement textBidLots;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement dropdownvintage;

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

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//a[text()='Events']")
	private WebElement btnEvents;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement btnContineu;

	@FindBy(xpath = "//a[@href='/report']")
	private WebElement btnReports;

	@FindBy(xpath = "//button//*[text()='Search Reports']")
	private WebElement btnSearchReports;

	@FindBy(xpath = "//button[@title='SEARCH']")
	private WebElement btnSearch;

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

	@FindBy(xpath = "(//*[@aria-label='firstAuctionCertified'])[1]")
	private WebElement auctionCerButton;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithmCA;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement btnRunAlgorithmQC;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
	private WebElement btnCalifornia;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[3]")
	private WebElement btnQuebec;

	@FindBy(xpath = "//*[text()='USD']")
	private WebElement tagUSD;

	@FindBy(xpath = "//*[text()='CAD']")
	private WebElement tagCAD;

	@FindBy(xpath = "//*[text()='The event has been created successfully.']")
	private WebElement successmsg;
	WebElement btnWashinton;

	private By Selectentity1_PE2;
	private By Selectentity1_PE3;
	private By Selectentity1_PE5;
	private By Selectentity1_PE6;
	private By Selectentity1_PE7;
	private By Selectentity1_PE8;
	private By Selectentity1_PE9;
	private By Selectentity1_PE10;
	private By Selectentity1_PE4;
	private By Selectentity2_PE4;
	private By Selectentity2_PE5;
	private By Selectentity2_PE6;
	private By Selectentity2_PE7;
	private By Selectentity2_PE8;
	private By Selectentity2_PE9;
	private By Selectentity2_PE10;
	private By Selectentity3_PE4;
	private By Selectentity4_PE4;
	private By Selectentity5_PE4;
	private WebElement eventnameonbidpage_PE2;
	private WebElement eventnameonbidpage_PE3;
	private WebElement eventnameonbidpage_PE5;
	private WebElement eventnameonbidpage_PE6;
	private WebElement eventnameonbidpage_PE7;
	private WebElement eventnameonbidpage_PE8;
	private WebElement eventnameonbidpage_PE9;
	private WebElement eventnameonbidpage_PE10;
	private WebElement eventnameonbidpage_PE4;
	private By Selectentity3_PE1;
	private By Selectentity4_PE1;
	private By Selectentity2_PE2;
	private By Selectentity2_PE3;
	private By Selectentity5_PE1;
	private By Selectentity3_PE2;
	private By Selectentity3_PE3;
	private By Selectentity3_PE5;
	private By Selectentity3_PE6;
	private By Selectentity3_PE7;
	private By Selectentity3_PE8;
	private By Selectentity3_PE9;
	private By Selectentity3_PE10;
	private By Selectentity6_PE1;
	private By Selectentity4_PE2;
	private By Selectentity4_PE3;
	private By Selectentity4_PE5;
	private By Selectentity4_PE6;
	private By Selectentity4_PE7;
	private By Selectentity4_PE8;
	private By Selectentity4_PE9;
	private By Selectentity4_PE10;
	private By Selectentity7_PE1;
	private By Selectentity5_PE2;
	private By Selectentity5_PE3;
	private By Selectentity5_PE5;
	private By Selectentity5_PE6;
	private By Selectentity5_PE7;
	private By Selectentity5_PE8;
	private By Selectentity5_PE9;

	public JointAuctionTieBreakerPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void loginsponsor() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
			username.sendKeys(prop.getProperty("UserSponsorCa"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("California sponsor2 login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorCa"));
			password.sendKeys(prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("California sponsor2 login successfully");
		}
	}

	public void confirmRunAlgorithmByFirstSponsor() throws InterruptedException {
		Thread.sleep(20000);
		wait.until(ExpectedConditions.elementToBeClickable(firstSponsorConfirmBtn));
		firstSponsorConfirmBtn.click();
		runAlgorithmProposedSuccessMsg.isDisplayed();
		System.out.println("✅ Run Algorithm Proposed");
	}

	public void confirmRunAlgorithmBySecondSponsor() throws InterruptedException {
		Thread.sleep(30000);
		wait.until(ExpectedConditions.visibilityOf(secondSponsorConfirmBtn));
		secondSponsorConfirmBtn.click();
		twoSponsorsConfirmSuccessMsg.isDisplayed();
		System.out.println("✅ Run Algorithm Proposed");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithm1));
		btnRunAlgorithm1.click();
		wait.until(ExpectedConditions.visibilityOf(btnContineu));
		btnContineu.click();
		ObjectRepo.getTest().log(Status.PASS, "Run Algorithm was successfully executed by the second sponsor.");
		Thread.sleep(10000);
	}

	public void runAlgorithm2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithm1));
			btnRunAlgorithm1.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithm2));
			btnRunAlgorithm2.click();
		}
		wait.until(ExpectedConditions.visibilityOf(btnContineu));
		btnContineu.click();
		ObjectRepo.getTest().log(Status.PASS, "Run Algorithm was successfully executed by the second sponsor.");
		Thread.sleep(10000);
	}

	public void ApprovedRSCertificationButtonFirstSponsor(String CertificationButton, String EventName)
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			btnAdminstration.click();
		} catch (Exception e) {
			btnAdminstration.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on administration button.");
		WebElement CertificationButn = driver.findElement(By.xpath("(//*[text()='" + CertificationButton + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(CertificationButn)).click();
		Thread.sleep(8000);
//		WebElement element1 =driver.findElement(By.xpath("//*[text()='"+EventName+"']/ancestor::td/following-sibling::td[7]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		WebElement element2 = driver.findElement(By.xpath("//*[text()='" + EventName
				+ "']/ancestor::td/following-sibling::td[8]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		js.executeScript("arguments[0].scrollIntoView();", element2);
		Thread.sleep(8000);
//		wait.until(ExpectedConditions.elementToBeClickable(element111));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", element2);
		}
		WebElement submitBtncertification1 = driver.findElement(
				By.xpath("(//*[text()='" + EventName + "']//following::button[@name='approval1Submit'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification1));
		try {
			submitBtncertification1.click();
		} catch (Exception e) {
			submitBtncertification1.click();
		}
		Thread.sleep(7000);
		System.out.println("Certification decision saved successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Certification decision saved successfully.");
		System.out.println("Certification submitted by first sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Certification submitted by first sponsor.");
	}

	public void ApprovedSbmandApcrCertificationButtonFirstSponsor(String CertificationButton, String EventName)
			throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			btnAdminstration.click();
		} catch (Exception e) {
			btnAdminstration.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on administration button.");
		WebElement CertificationButn = driver.findElement(By.xpath("(//*[text()='" + CertificationButton + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(CertificationButn)).click();
		Thread.sleep(8000);
		WebElement element2 = driver.findElement(By.xpath("//*[text()='" + EventName
				+ "']/ancestor::td/following-sibling::td[7]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		js.executeScript("arguments[0].scrollIntoView();", element2);
		Thread.sleep(8000);
//		wait.until(ExpectedConditions.elementToBeClickable(element111));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", element2);
		}
		WebElement submitBtncertification1 = driver.findElement(
				By.xpath("(//*[text()='" + EventName + "']//following::button[@name='approval1Submit'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification1));
		try {
			submitBtncertification1.click();
		} catch (Exception e) {
			submitBtncertification1.click();
		}
		Thread.sleep(7000);
		System.out.println("Certification decision saved successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Certification decision saved successfully.");
		System.out.println("Certification submitted by first sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Certification submitted by first sponsor.");
	}

	public void acceptRSCertificationButtonSecondSponsor(String CertificationButton, String EventName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			btnAdminstration.click();
		} catch (Exception e) {
			btnAdminstration.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on administration button.");
		WebElement CertificationButn = driver.findElement(By.xpath("(//*[text()='" + CertificationButton + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(CertificationButn)).click();
		System.out.println(
				"Step: Click on the toggle button to Approve by the 2nd Sponsor, under 'Certification' column for the event which is to certify.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step: Click on the toggle button to Approve by the 2nd Sponsor, under 'Certification' column for the event which is to certify.");
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		WebElement element1 =driver.findElement(By.xpath("//*[text()='"+EventName+"']/ancestor::td/following-sibling::td[7]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		WebElement element2 = driver.findElement(By.xpath("//*[text()='" + EventName
				+ "']/ancestor::td/following-sibling::td[8]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		js.executeScript("arguments[0].scrollIntoView();", element2);
//		wait.until(ExpectedConditions.elementToBeClickable(element111));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		}
		System.out.println("Step: Click on the 'Submit' button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the 'Submit' button");
		WebElement submitBtncertification1 = driver.findElement(
				By.xpath("(//*[text()='" + EventName + "']//following::button[@name='approval1Submit'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification1));
		try {
			submitBtncertification1.click();
		} catch (Exception e) {
			submitBtncertification1.click();
		}
		System.out.println("Step: Click on the 'Submit' button on the warning pop-up");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the 'Submit' button on the warning pop-up");
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertificationsubmitpopup));
		submitBtncertificationsubmitpopup.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void acceptSbmaAndApcrCertificationButtonSecondSponsor(String CertificationButton, String EventName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			btnAdminstration.click();
		} catch (Exception e) {
			btnAdminstration.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on administration button.");
		WebElement CertificationButn = driver.findElement(By.xpath("(//*[text()='" + CertificationButton + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(CertificationButn)).click();
		System.out.println(
				"Step: Click on the toggle button to Approve by the 2nd Sponsor, under 'Certification' column for the event which is to certify.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step: Click on the toggle button to Approve by the 2nd Sponsor, under 'Certification' column for the event which is to certify.");
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element2 = driver.findElement(By.xpath("//*[text()='" + EventName
				+ "']/ancestor::td/following-sibling::td[7]/descendant-or-self::*[@data-icon='check-circle'][1]"));
		js.executeScript("arguments[0].scrollIntoView();", element2);
//		wait.until(ExpectedConditions.elementToBeClickable(element111));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(element2)).click();
		}
		System.out.println("Step: Click on the 'Submit' button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the 'Submit' button");
		WebElement submitBtncertification1 = driver.findElement(
				By.xpath("(//*[text()='" + EventName + "']//following::button[@name='approval1Submit'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification1));
		try {
			submitBtncertification1.click();
		} catch (Exception e) {
			submitBtncertification1.click();
		}
		System.out.println("Step: Click on the 'Submit' button on the warning pop-up");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the 'Submit' button on the warning pop-up");
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertificationsubmitpopup));
		submitBtncertificationsubmitpopup.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void AuctionCertificationButtonca() throws InterruptedException {
		AuctionCertificationButton.click();
		WebElement element111 = auctionCerButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		Thread.sleep(7000);
		ObjectRepo.getTest().log(Status.PASS, "Event Certification was successfully executed by the first sponsor.");
	}

	public void reserveSaleCertificationButtonca() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(reserveSaleCertificationButton));
			reserveSaleCertificationButton.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(reserveSaleCertificationButton));
			reserveSaleCertificationButton.click();
		}
		Thread.sleep(5000);
		WebElement element111 = auctionCerButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		Thread.sleep(7000);
	}

	public void AuctionCertificationButtonqc() throws InterruptedException {
		AuctionCertificationButton.click();
		WebElement element111 = QCcertificationbutton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification)).click();
		submitBtncertification.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertificationsubmitpopup)).click();
		Thread.sleep(10000);
		ObjectRepo.getTest().log(Status.PASS, "Event Certification was successfully executed by the first sponsor.");
	}

	public void FirstSponsorCertificationButton(String certificationType) throws InterruptedException {
		WebElement firstCertification = driver.findElement(By.xpath("(//*[text()='" + certificationType + "'])[1]"));
		firstCertification.click();
		WebElement element111 = auctionCerButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		Thread.sleep(7000);
		ObjectRepo.getTest().log(Status.PASS, "Event Certification was successfully executed by the second sponsor.");
	}

	public void secondSponsorCertificationButton(String certificationType) throws InterruptedException {
		WebElement firstCertification = driver.findElement(By.xpath("(//*[text()='" + certificationType + "'])[1]"));
		ObjectRepo.getTest().log(Status.PASS, "Selected Certification Type.");
		firstCertification.click();
		WebElement element111 = QCcertificationbutton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertification)).click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertificationsubmitpopup)).click();
		Thread.sleep(10000);
		ObjectRepo.getTest().log(Status.PASS, "Event Certification was successfully executed by the sponsor.");
	}

	public void searchQualifiedbidderReportFunctionality() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameForJointAuction();
		WebElement element2 = wait
				.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadQualifiedBidderReport));
		js.executeScript("arguments[0].scrollIntoView();", element2);
		driver.findElement(btnDownloadQualifiedBidderReport).click();
		Thread.sleep(25000);
		File getLatestFile = getLatestFilefromDir(folderPath);
		String fileName = getLatestFile.getName();
		System.out.println("fileName : " + fileName);
	}

	public void clickreportmenu() throws Exception {
		WebElement element = btnReport;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}

	public void btnsearchreport() throws InterruptedException {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport));
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	}

	public void ReportEventSelectfromDropdown_PE1() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE2() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE3() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE5() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE6() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE7() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE8() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE9() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE10() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;

		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage_PE1);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ReportEventSelectfromDropdown_PE4() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;

		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(eventnameonbidpage);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void QualifiedBiddersReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Qualified Bidders Report");
	}

	public void ResultReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Results Report");
	}

	public void searchQualifiedbidderReportFunctionality_PE1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE1();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE1();
	}

	public void searchQualifiedbidderReportFunctionality_PE2() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE2();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE2();
	}

	public void searchQualifiedbidderReportFunctionality_PE3() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE3();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE3();
	}

	public void searchQualifiedbidderReportFunctionality_PE5() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE5();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE5();
	}

	public void searchQualifiedbidderReportFunctionality_PE6() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE6();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE6();
	}

	public void searchQualifiedbidderReportFunctionality_PE7() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE7();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE7();
	}

	public void searchQualifiedbidderReportFunctionality_PE8() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE8();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE8();
	}

	public void searchQualifiedbidderReportFunctionality_PE9() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE9();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE9();
	}

	public void searchQualifiedbidderReportFunctionality_PE10() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE10();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE10();
	}

	public void searchQualifiedbidderReportFunctionality_PE4() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE4();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport_PE4();
	}

	public void searchResultReportFunctionality_PE1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE1();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE1();
	}

	public void searchResultReportFunctionality_PE2() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE2();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE2();
	}

	public void searchResultReportFunctionality_PE3() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE3();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE3();
	}

	public void searchResultReportFunctionality_PE5() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE5();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE5();
	}

	public void searchResultReportFunctionality_PE6() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE6();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE6();
	}

	public void searchResultReportFunctionality_PE7() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE7();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE7();
	}

	public void searchResultReportFunctionality_PE8() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE8();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE8();
	}

	public void searchResultReportFunctionality_PE9() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE9();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE9();
	}

	public void searchResultReportFunctionality_PE10() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE10();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE10();
	}

	public void searchResultReportFunctionality_PE4() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result Report is  selected from dropdown");
		ReportEventSelectfromDropdown_PE4();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultReport_PE4();
	}

	public void clicksearchbtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
		btnSearch.click();
	}

	public void downloadongoingeventQualifiedBiddersReport_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE1));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE1);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE2() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE2));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE2);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE3));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE3);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE5() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE5));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE5);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE7() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE7));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE7);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE8() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE8));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE8);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE9() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE9));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE9);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE10() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE10));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE10);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE6() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE6));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE6);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventQualifiedBiddersReport_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports_PE4));
		WebElement abc7 = driver.findElement(btnQualifiedBidderReports_PE4);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Qualified Bidder Report Download Button");
	}

	public void downloadongoingeventResultReport_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE1));
		WebElement abc7 = driver.findElement(btnResultReports_PE1);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE2() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE2));
		WebElement abc7 = driver.findElement(btnResultReports_PE2);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE3));
		WebElement abc7 = driver.findElement(btnResultReports_PE3);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE5() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE5));
		WebElement abc7 = driver.findElement(btnResultReports_PE5);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE6() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE6));
		WebElement abc7 = driver.findElement(btnResultReports_PE6);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE7() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE7));
		WebElement abc7 = driver.findElement(btnResultReports_PE7);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE8() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE8));
		WebElement abc7 = driver.findElement(btnResultReports_PE8);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE9() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE9));
		WebElement abc7 = driver.findElement(btnResultReports_PE9);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE10() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE10));
		WebElement abc7 = driver.findElement(btnResultReports_PE10);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void downloadongoingeventResultReport_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports_PE4));
		WebElement abc7 = driver.findElement(btnResultReports_PE4);

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Result Report Download Button");
	}

	public void getQualifiedBidderReportData() throws Exception {
		DownloadQualifiedBidderReport report = new DownloadQualifiedBidderReport();
		String colomn1name = report.getStringData("Qualified Bidders Report", 5, 0);
		String regGenAccNo1 = report.getStringData("Qualified Bidders Report", 6, 0);
		String regGenAccNo2 = report.getStringData("Qualified Bidders Report", 7, 0);
		String regGenAccNo3 = report.getStringData("Qualified Bidders Report", 8, 0);
		String regGenAccNo4 = report.getStringData("Qualified Bidders Report", 9, 0);
		String regGenAccNo5 = report.getStringData("Qualified Bidders Report", 10, 0);
		String colomn2name = report.getStringData("Qualified Bidders Report", 5, 1);
		String entityLegalName1 = report.getStringData("Qualified Bidders Report", 6, 1);
		String entityLegalName2 = report.getStringData("Qualified Bidders Report", 7, 1);
		String entityLegalName3 = report.getStringData("Qualified Bidders Report", 8, 1);
		String entityLegalName4 = report.getStringData("Qualified Bidders Report", 9, 1);
		String entityLegalName5 = report.getStringData("Qualified Bidders Report", 10, 1);
		String colomn3name = report.getStringData("Qualified Bidders Report", 5, 2);
		String entityOperatingName1 = report.getStringData("Qualified Bidders Report", 6, 2);
		String entityOperatingName2 = report.getStringData("Qualified Bidders Report", 7, 2);
		String entityOperatingName3 = report.getStringData("Qualified Bidders Report", 8, 2);
		String entityOperatingName4 = report.getStringData("Qualified Bidders Report", 9, 2);
		String entityOperatingName5 = report.getStringData("Qualified Bidders Report", 10, 2);
		String colomn4name = report.getStringData("Qualified Bidders Report", 5, 3);
		String entityType1 = report.getStringData("Qualified Bidders Report", 6, 3);
		String entityType2 = report.getStringData("Qualified Bidders Report", 7, 3);
		String entityType3 = report.getStringData("Qualified Bidders Report", 8, 3);
		String entityType4 = report.getStringData("Qualified Bidders Report", 9, 3);
		String entityType5 = report.getStringData("Qualified Bidders Report", 10, 3);
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
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameForJointAuction();
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadResultReport));
		driver.findElement(btnDownloadResultReport).click();
		Thread.sleep(25000);
	}

	public void fetchExcelDataforresultReport() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventNameEng + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE1() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE1 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE2() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE2 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE3() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE3 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE5() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE5 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE6() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE6 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE7() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE7 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE8() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE8 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE9() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE9 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE10() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE10 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforresultReport_PE4() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + eventName_PE4 + " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStringDataRR(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericDataRR(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	public void fetchExcelDataforQualifiedBidderReport() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - " + prop.getProperty("event.name")
				+ " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - "
				+ prop.getProperty("event.name") + " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void fetchExcelDataforQualifiedBidderReport_PE2() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE2 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventNameEng
				+ " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE3() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE3 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventNameEng
				+ " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE5() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE5 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE5
				+ " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE6() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE6 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE6
				+ " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE7() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE7 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE7
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE8() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE8 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE8
				+ " " + date1 + ".xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE9() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE9 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE9
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE10() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE10 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE10
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE4() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE4 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventNameEng
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fetchExcelDataforQualifiedBidderReport_PE1() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventName_PE1 + " " + date1 + ".xlsx");
		System.out.println("filepath:=" + folderPath + File.separator + "Qualified Bidders Report - " + eventNameEng
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStringData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericData(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	public void getQualifiedBidderData_PE1() throws InterruptedException {

		// Registry General Acc No
		String colomn1name = getStringData("Qualified Bidders Report", 5, 0);
		String regGenAccNo1 = getStringData("Qualified Bidders Report", 6, 0);
		String regGenAccNo2 = getStringData("Qualified Bidders Report", 7, 0);
		String regGenAccNo3 = getStringData("Qualified Bidders Report", 8, 0);
		String regGenAccNo4 = getStringData("Qualified Bidders Report", 9, 0);
		String regGenAccNo5 = getStringData("Qualified Bidders Report", 10, 0);
		String regGenAccNo6 = getStringData("Qualified Bidders Report", 11, 0);
		String regGenAccNo7 = getStringData("Qualified Bidders Report", 12, 0);

		// Entity legal name
		String colomn2name = getStringData("Qualified Bidders Report", 5, 1);
		String entityLegalName1 = getStringData("Qualified Bidders Report", 6, 1);
		String entityLegalName2 = getStringData("Qualified Bidders Report", 7, 1);
		String entityLegalName3 = getStringData("Qualified Bidders Report", 8, 1);
		String entityLegalName4 = getStringData("Qualified Bidders Report", 9, 1);
		String entityLegalName5 = getStringData("Qualified Bidders Report", 10, 1);
		String entityLegalName6 = getStringData("Qualified Bidders Report", 11, 1);
		String entityLegalName7 = getStringData("Qualified Bidders Report", 12, 1);

		// Entity Operating Name
		String colomn3name = getStringData("Qualified Bidders Report", 5, 2);
		String entityOperatingName1 = getStringData("Qualified Bidders Report", 6, 2);
		String entityOperatingName2 = getStringData("Qualified Bidders Report", 7, 2);
		String entityOperatingName3 = getStringData("Qualified Bidders Report", 8, 2);
		String entityOperatingName4 = getStringData("Qualified Bidders Report", 9, 2);
		String entityOperatingName5 = getStringData("Qualified Bidders Report", 10, 2);
		String entityOperatingName6 = getStringData("Qualified Bidders Report", 11, 2);
		String entityOperatingName7 = getStringData("Qualified Bidders Report", 12, 2);

		// Entity type
		String colomn4name = getStringData("Qualified Bidders Report", 5, 3);
		String entityType1 = getStringData("Qualified Bidders Report", 6, 3);
		String entityType2 = getStringData("Qualified Bidders Report", 7, 3);
		String entityType3 = getStringData("Qualified Bidders Report", 8, 3);
		String entityType4 = getStringData("Qualified Bidders Report", 9, 3);
		String entityType5 = getStringData("Qualified Bidders Report", 10, 3);
		String entityType6 = getStringData("Qualified Bidders Report", 11, 3);
		String entityType7 = getStringData("Qualified Bidders Report", 12, 3);

		// Entity Jurisdiction
		String colomn5name = getStringData("Qualified Bidders Report", 5, 4);
		String entJurisdiction1 = getStringData("Qualified Bidders Report", 6, 4);
		String entJurisdiction2 = getStringData("Qualified Bidders Report", 7, 4);
		String entJurisdiction3 = getStringData("Qualified Bidders Report", 8, 4);
		String entJurisdiction4 = getStringData("Qualified Bidders Report", 9, 4);
		String entJurisdiction5 = getStringData("Qualified Bidders Report", 10, 4);
		String entJurisdiction6 = getStringData("Qualified Bidders Report", 11, 4);
		String entJurisdiction7 = getStringData("Qualified Bidders Report", 12, 4);

		// FSA Acc No
		String colomn6name = getStringData("Qualified Bidders Report", 5, 5);
		String fsaAccNum1 = getStringData("Qualified Bidders Report", 6, 5);
		String fsaAccNum2 = getStringData("Qualified Bidders Report", 7, 5);
		String fsaAccNum3 = getStringData("Qualified Bidders Report", 8, 5);
		String fsaAccNum4 = getStringData("Qualified Bidders Report", 9, 5);
		String fsaAccNum5 = getStringData("Qualified Bidders Report", 10, 5);
		String fsaAccNum6 = getStringData("Qualified Bidders Report", 11, 5);
		String fsaAccNum7 = getStringData("Qualified Bidders Report", 12, 5);

		// Bid Guarantee Currency
		String colomn7name = getStringData("Qualified Bidders Report", 5, 5);
		String bidGuaranteeCurrency1 = getStringData("Qualified Bidders Report", 6, 6);
		String bidGuaranteeCurrency2 = getStringData("Qualified Bidders Report", 7, 6);
		String bidGuaranteeCurrency3 = getStringData("Qualified Bidders Report", 8, 6);
		String bidGuaranteeCurrency4 = getStringData("Qualified Bidders Report", 9, 6);
		String bidGuaranteeCurrency5 = getStringData("Qualified Bidders Report", 10, 6);
		String bidGuaranteeCurrency6 = getStringData("Qualified Bidders Report", 11, 6);
		String bidGuaranteeCurrency7 = getStringData("Qualified Bidders Report", 12, 6);

		// Total bid Guarantee value
		String colomn11name = getStringData("Qualified Bidders Report", 5, 11);
		double totalbidGuaranteeCurrency1 = getNumericData("Qualified Bidders Report", 6, 11);
		double totalbidGuaranteeCurrency2 = getNumericData("Qualified Bidders Report", 7, 11);
		double totalbidGuaranteeCurrency3 = getNumericData("Qualified Bidders Report", 8, 11);
		double totalbidGuaranteeCurrency4 = getNumericData("Qualified Bidders Report", 9, 11);
		double totalbidGuaranteeCurrency5 = getNumericData("Qualified Bidders Report", 10, 11);
		double totalbidGuaranteeCurrency6 = getNumericData("Qualified Bidders Report", 11, 11);
		double totalbidGuaranteeCurrency7 = getNumericData("Qualified Bidders Report", 12, 11);

		// Purchase limit for current auction
		String colomn12name = getStringData("Qualified Bidders Report", 5, 12);
		double purchaselimit1 = getNumericData("Qualified Bidders Report", 6, 12);
		double purchaselimit2 = getNumericData("Qualified Bidders Report", 7, 12);
		double purchaselimit3 = getNumericData("Qualified Bidders Report", 8, 12);
		double purchaselimit4 = getNumericData("Qualified Bidders Report", 9, 12);
		double purchaselimit5 = getNumericData("Qualified Bidders Report", 10, 12);
		double purchaselimit6 = getNumericData("Qualified Bidders Report", 11, 12);
		double purchaselimit7 = getNumericData("Qualified Bidders Report", 12, 12);

		// Holding limit for current auction
		String colomn14name = getStringData("Qualified Bidders Report", 5, 14);
		double holdingLimit1 = getNumericData("Qualified Bidders Report", 6, 14);
		double holdingLimit2 = getNumericData("Qualified Bidders Report", 7, 14);
		double holdingLimit3 = getNumericData("Qualified Bidders Report", 8, 14);
		double holdingLimit4 = getNumericData("Qualified Bidders Report", 9, 14);
		double holdingLimit5 = getNumericData("Qualified Bidders Report", 10, 14);
		double holdingLimit6 = getNumericData("Qualified Bidders Report", 11, 14);
		double holdingLimit7 = getNumericData("Qualified Bidders Report", 12, 14);
		double value1 = holdingLimit1;
		DecimalFormat formatter1 = new DecimalFormat("#,###");
		String formattedValue1 = formatter1.format(value1);
		double value2 = holdingLimit2;
		DecimalFormat formatter2 = new DecimalFormat("#,###");
		String formattedValue2 = formatter2.format(value2);
		double value3 = holdingLimit3;
		DecimalFormat formatter3 = new DecimalFormat("#,###");
		String formattedValue3 = formatter3.format(value3);
		double value4 = holdingLimit4;
		DecimalFormat formatter4 = new DecimalFormat("#,###");
		String formattedValue4 = formatter4.format(value4);
		double value5 = holdingLimit5;
		DecimalFormat formatter5 = new DecimalFormat("#,###");
		String formattedValue5 = formatter5.format(value5);
		double value6 = holdingLimit6;
		String formattedValue6 = formatter4.format(value6);
		double value7 = holdingLimit7;
		String formattedValue7 = formatter4.format(value7);
		System.out.println(colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
				+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
				+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency1 + ",  " + colomn12name + " : " + purchaselimit1 + ",  " + colomn14name
				+ " : " + formattedValue1);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
						+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
						+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency1 + ",  " + colomn12name + " : " + purchaselimit1 + ",  "
						+ colomn14name + " : " + formattedValue1);

		System.out.println(colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
				+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
				+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency2 + ",  " + colomn12name + " : " + purchaselimit2 + ",  " + colomn14name
				+ " : " + formattedValue2);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
						+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
						+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency2 + ",  " + colomn12name + " : " + purchaselimit2 + ",  "
						+ colomn14name + " : " + formattedValue2);

		System.out.println(colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
				+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
				+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency3 + ",  " + colomn12name + " : " + purchaselimit3 + ",  " + colomn14name
				+ " : " + formattedValue3);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
						+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
						+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency3 + ",  " + colomn12name + " : " + purchaselimit3 + ",  "
						+ colomn14name + " : " + formattedValue3);

		System.out.println(colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
				+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
				+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency4 + ",  " + colomn12name + " : " + purchaselimit4 + ",  " + colomn14name
				+ " : " + formattedValue4);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
						+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
						+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency4 + ",  " + colomn12name + " : " + purchaselimit4 + ",  "
						+ colomn14name + " : " + formattedValue4);

		System.out.println(colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
				+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
				+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency5 + ",  " + colomn12name + " : " + purchaselimit5 + ",  " + colomn14name
				+ " : " + formattedValue5);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
						+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
						+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency5 + ",  " + colomn12name + " : " + purchaselimit5 + ",  "
						+ colomn14name + " : " + formattedValue5);
		System.out.println(colomn1name + " : " + regGenAccNo6 + ",  " + colomn2name + " : " + entityLegalName6 + ",  "
				+ colomn3name + " : " + entityOperatingName6 + ",  " + colomn4name + " : " + entityType6 + ",  "
				+ colomn5name + " : " + entJurisdiction6 + ",  " + colomn6name + " : " + fsaAccNum6 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency6 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency6 + ",  " + colomn12name + " : " + purchaselimit6 + ",  " + colomn14name
				+ " : " + formattedValue6);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo6 + ",  " + colomn2name + " : " + entityLegalName6 + ",  "
						+ colomn3name + " : " + entityOperatingName6 + ",  " + colomn4name + " : " + entityType6 + ",  "
						+ colomn5name + " : " + entJurisdiction6 + ",  " + colomn6name + " : " + fsaAccNum6 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency6 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency6 + ",  " + colomn12name + " : " + purchaselimit6 + ",  "
						+ colomn14name + " : " + formattedValue6);
		System.out.println(colomn1name + " : " + regGenAccNo7 + ",  " + colomn2name + " : " + entityLegalName7 + ",  "
				+ colomn3name + " : " + entityOperatingName7 + ",  " + colomn4name + " : " + entityType7 + ",  "
				+ colomn5name + " : " + entJurisdiction7 + ",  " + colomn6name + " : " + fsaAccNum7 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency7 + ",  " + colomn11name + " : "
				+ totalbidGuaranteeCurrency7 + ",  " + colomn12name + " : " + purchaselimit7 + ",  " + colomn14name
				+ " : " + formattedValue7);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo7 + ",  " + colomn2name + " : " + entityLegalName7 + ",  "
						+ colomn3name + " : " + entityOperatingName7 + ",  " + colomn4name + " : " + entityType7 + ",  "
						+ colomn5name + " : " + entJurisdiction7 + ",  " + colomn6name + " : " + fsaAccNum7 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency7 + ",  " + colomn11name + " : "
						+ totalbidGuaranteeCurrency7 + ",  " + colomn12name + " : " + purchaselimit7 + ",  "
						+ colomn14name + " : " + formattedValue7);

	}

	public void getQualifiedBidderData() throws InterruptedException {

		// Registry General Acc No
		String colomn1name = getStringData("Qualified Bidders Report", 5, 0);
		String regGenAccNo1 = getStringData("Qualified Bidders Report", 6, 0);
		String regGenAccNo2 = getStringData("Qualified Bidders Report", 7, 0);
		String regGenAccNo3 = getStringData("Qualified Bidders Report", 8, 0);
		String regGenAccNo4 = getStringData("Qualified Bidders Report", 9, 0);
		String regGenAccNo5 = getStringData("Qualified Bidders Report", 10, 0);

		// Entity legal name
		String colomn2name = getStringData("Qualified Bidders Report", 5, 1);
		String entityLegalName1 = getStringData("Qualified Bidders Report", 6, 1);
		String entityLegalName2 = getStringData("Qualified Bidders Report", 7, 1);
		String entityLegalName3 = getStringData("Qualified Bidders Report", 8, 1);
		String entityLegalName4 = getStringData("Qualified Bidders Report", 9, 1);
		String entityLegalName5 = getStringData("Qualified Bidders Report", 10, 1);

		// Entity Operating Name
		String colomn3name = getStringData("Qualified Bidders Report", 5, 2);
		String entityOperatingName1 = getStringData("Qualified Bidders Report", 6, 2);
		String entityOperatingName2 = getStringData("Qualified Bidders Report", 7, 2);
		String entityOperatingName3 = getStringData("Qualified Bidders Report", 8, 2);
		String entityOperatingName4 = getStringData("Qualified Bidders Report", 9, 2);
		String entityOperatingName5 = getStringData("Qualified Bidders Report", 10, 2);

		// Entity type
		String colomn4name = getStringData("Qualified Bidders Report", 5, 3);
		String entityType1 = getStringData("Qualified Bidders Report", 6, 3);
		String entityType2 = getStringData("Qualified Bidders Report", 7, 3);
		String entityType3 = getStringData("Qualified Bidders Report", 8, 3);
		String entityType4 = getStringData("Qualified Bidders Report", 9, 3);
		String entityType5 = getStringData("Qualified Bidders Report", 10, 3);

		// Entity Jurisdiction
		String colomn5name = getStringData("Qualified Bidders Report", 5, 4);
		String entJurisdiction1 = getStringData("Qualified Bidders Report", 6, 4);
		String entJurisdiction2 = getStringData("Qualified Bidders Report", 7, 4);
		String entJurisdiction3 = getStringData("Qualified Bidders Report", 8, 4);
		String entJurisdiction4 = getStringData("Qualified Bidders Report", 9, 4);
		String entJurisdiction5 = getStringData("Qualified Bidders Report", 10, 4);

		// FSA Acc No
		String colomn6name = getStringData("Qualified Bidders Report", 5, 5);
		String fsaAccNum1 = getStringData("Qualified Bidders Report", 6, 5);
		String fsaAccNum2 = getStringData("Qualified Bidders Report", 7, 5);
		String fsaAccNum3 = getStringData("Qualified Bidders Report", 8, 5);
		String fsaAccNum4 = getStringData("Qualified Bidders Report", 9, 5);
		String fsaAccNum5 = getStringData("Qualified Bidders Report", 10, 5);

		// Bid Guarantee Currency
		String colomn7name = getStringData("Qualified Bidders Report", 5, 5);
		String bidGuaranteeCurrency1 = getStringData("Qualified Bidders Report", 6, 6);
		String bidGuaranteeCurrency2 = getStringData("Qualified Bidders Report", 7, 6);
		String bidGuaranteeCurrency3 = getStringData("Qualified Bidders Report", 8, 6);
		String bidGuaranteeCurrency4 = getStringData("Qualified Bidders Report", 9, 6);
		String bidGuaranteeCurrency5 = getStringData("Qualified Bidders Report", 10, 6);

		// Total bid Guarantee value
		String colomn11name = getStringData("Qualified Bidders Report", 5, 11);
		double totalbidGuaranteeCurrency1 = getNumericData("Qualified Bidders Report", 6, 11);
		double totalbidGuaranteeCurrency2 = getNumericData("Qualified Bidders Report", 7, 11);
		double totalbidGuaranteeCurrency3 = getNumericData("Qualified Bidders Report", 8, 11);
		double totalbidGuaranteeCurrency4 = getNumericData("Qualified Bidders Report", 9, 11);
		double value1 = totalbidGuaranteeCurrency1;
		DecimalFormat formatter = new DecimalFormat("#,###");
		String formattedValue1 = formatter.format(value1);
		double value2 = totalbidGuaranteeCurrency2;
		String formattedValue2 = formatter.format(value2);
		double value3 = totalbidGuaranteeCurrency3;
		String formattedValue3 = formatter.format(value3);
		double value4 = totalbidGuaranteeCurrency4;
		String formattedValue4 = formatter.format(value4);
		double value5 = totalbidGuaranteeCurrency4;
		String formattedValue5 = formatter.format(value5);
		// Purchase limit for current auction
		String colomn12name = getStringData("Qualified Bidders Report", 5, 12);
		double purchaselimit1 = getNumericData("Qualified Bidders Report", 6, 12);
		double purchaselimit2 = getNumericData("Qualified Bidders Report", 7, 12);
		double purchaselimit3 = getNumericData("Qualified Bidders Report", 8, 12);
		double purchaselimit4 = getNumericData("Qualified Bidders Report", 9, 12);
		double purchaselimit5 = getNumericData("Qualified Bidders Report", 10, 12);
		double value6 = purchaselimit1;
		DecimalFormat formatter1 = new DecimalFormat("#,###");
		String formattedValue6 = formatter1.format(value6);
		double value7 = purchaselimit2;
		String formattedValue7 = formatter1.format(value7);
		double value8 = purchaselimit3;
		String formattedValue8 = formatter1.format(value8);
		double value9 = purchaselimit4;
		String formattedValue9 = formatter1.format(value9);
		double value10 = purchaselimit5;
		String formattedValue10 = formatter1.format(value10);
		// Holding limit for current auction
		String colomn14name = getStringData("Qualified Bidders Report", 5, 14);
		double holdingLimit1 = getNumericData("Qualified Bidders Report", 6, 14);
		double holdingLimit2 = getNumericData("Qualified Bidders Report", 7, 14);
		double holdingLimit3 = getNumericData("Qualified Bidders Report", 8, 14);
		double holdingLimit4 = getNumericData("Qualified Bidders Report", 9, 14);
		double holdingLimit5 = getNumericData("Qualified Bidders Report", 10, 14);
		double value11 = holdingLimit1;
		// DecimalFormat formatter1 = new DecimalFormat("#,###");
		String formattedValue11 = formatter1.format(value11);
		double value12 = holdingLimit2;
		String formattedValue12 = formatter1.format(value12);
		double value13 = holdingLimit3;
		String formattedValue13 = formatter1.format(value13);
		double value14 = holdingLimit4;
		String formattedValue14 = formatter1.format(value14);
		double value15 = holdingLimit5;
		String formattedValue15 = formatter1.format(value15);
		System.out.println(colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
				+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
				+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : " + formattedValue1 + ",  "
				+ colomn12name + " : " + formattedValue6 + ",  " + colomn14name + " : " + formattedValue11);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo1 + ",  " + colomn2name + " : " + entityLegalName1 + ",  "
						+ colomn3name + " : " + entityOperatingName1 + ",  " + colomn4name + " : " + entityType1 + ",  "
						+ colomn5name + " : " + entJurisdiction1 + ",  " + colomn6name + " : " + fsaAccNum1 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency1 + ",  " + colomn11name + " : " + formattedValue1
						+ ",  " + colomn12name + " : " + formattedValue6 + ",  " + colomn14name + " : "
						+ formattedValue11);

		System.out.println(colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
				+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
				+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : " + formattedValue2 + ",  "
				+ colomn12name + " : " + formattedValue7 + ",  " + colomn14name + " : " + formattedValue12);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo2 + ",  " + colomn2name + " : " + entityLegalName2 + ",  "
						+ colomn3name + " : " + entityOperatingName2 + ",  " + colomn4name + " : " + entityType2 + ",  "
						+ colomn5name + " : " + entJurisdiction2 + ",  " + colomn6name + " : " + fsaAccNum2 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency2 + ",  " + colomn11name + " : " + formattedValue2
						+ ",  " + colomn12name + " : " + formattedValue7 + ",  " + colomn14name + " : "
						+ formattedValue12);

		System.out.println(colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
				+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
				+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : " + formattedValue3 + ",  "
				+ colomn12name + " : " + formattedValue8 + ",  " + colomn14name + " : " + formattedValue13);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo3 + ",  " + colomn2name + " : " + entityLegalName3 + ",  "
						+ colomn3name + " : " + entityOperatingName3 + ",  " + colomn4name + " : " + entityType3 + ",  "
						+ colomn5name + " : " + entJurisdiction3 + ",  " + colomn6name + " : " + fsaAccNum3 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency3 + ",  " + colomn11name + " : " + formattedValue3
						+ ",  " + colomn12name + " : " + formattedValue8 + ",  " + colomn14name + " : "
						+ formattedValue13);

		System.out.println(colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
				+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
				+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : " + formattedValue4 + ",  "
				+ colomn12name + " : " + formattedValue9 + ",  " + colomn14name + " : " + formattedValue14);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo4 + ",  " + colomn2name + " : " + entityLegalName4 + ",  "
						+ colomn3name + " : " + entityOperatingName4 + ",  " + colomn4name + " : " + entityType4 + ",  "
						+ colomn5name + " : " + entJurisdiction4 + ",  " + colomn6name + " : " + fsaAccNum4 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency4 + ",  " + colomn11name + " : " + formattedValue4
						+ ",  " + colomn12name + " : " + formattedValue9 + ",  " + colomn14name + " : "
						+ formattedValue14);

		System.out.println(colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
				+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
				+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
				+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : " + formattedValue5 + ",  "
				+ colomn12name + " : " + formattedValue10 + ",  " + colomn14name + " : " + formattedValue15);

		ObjectRepo.getTest().log(Status.PASS,
				colomn1name + " : " + regGenAccNo5 + ",  " + colomn2name + " : " + entityLegalName5 + ",  "
						+ colomn3name + " : " + entityOperatingName5 + ",  " + colomn4name + " : " + entityType5 + ",  "
						+ colomn5name + " : " + entJurisdiction5 + ",  " + colomn6name + " : " + fsaAccNum5 + ",  "
						+ colomn7name + " : " + bidGuaranteeCurrency5 + ",  " + colomn11name + " : " + formattedValue5
						+ ",  " + colomn12name + " : " + formattedValue10 + ",  " + colomn14name + " : "
						+ formattedValue15);

	}

	public void validateResultReport() throws Exception {
		// Data from result report

		String legalName1 = getStringDataRR("FINAL RESULTS", 1, 1);
		String legalName2 = getStringDataRR("FINAL RESULTS", 2, 1);
		String legalName3 = getStringDataRR("FINAL RESULTS", 3, 1);
		String legalName4 = getStringDataRR("FINAL RESULTS", 4, 1);
		String legalName5 = getStringDataRR("FINAL RESULTS", 5, 1);

		double num1 = getNumericDataRR("FINAL RESULTS", 1, 2);
		double num2 = getNumericDataRR("FINAL RESULTS", 2, 2);
		double num3 = getNumericDataRR("FINAL RESULTS", 3, 2);
		double num4 = getNumericDataRR("FINAL RESULTS", 4, 2);
		double num5 = getNumericDataRR("FINAL RESULTS", 5, 2);

		double value1 = num1;
		DecimalFormat formatter1 = new DecimalFormat("#,###");
		String formattedValue1 = formatter1.format(value1);
		double value2 = num2;
		DecimalFormat formatter2 = new DecimalFormat("#,###");
		String formattedValue2 = formatter2.format(value2);
		double value3 = num3;
		DecimalFormat formatter3 = new DecimalFormat("#,###");
		String formattedValue3 = formatter3.format(value3);
		double value4 = num4;
		DecimalFormat formatter4 = new DecimalFormat("#,###");
		String formattedValue4 = formatter4.format(value4);
		double value5 = num5;
		DecimalFormat formatter5 = new DecimalFormat("#,###");
		String formattedValue5 = formatter5.format(value5);

		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");

		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName1 + " Granted With " + formattedValue1 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName2 + " Granted With " + formattedValue2 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName3 + " Granted With " + formattedValue3 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName4 + " Granted With " + formattedValue4 + " Emission Units for Current Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"Entity: " + legalName5 + " Granted With " + formattedValue5 + " Emission Units for Current Auction");
	}

	public void validateResultReport_PE1() throws Exception {

		// Data from result report

		String legalName1 = getStringDataRR("FINAL RESULTS", 1, 1);
		String legalName2 = getStringDataRR("FINAL RESULTS", 2, 1);
		String legalName3 = getStringDataRR("FINAL RESULTS", 3, 1);
		String legalName4 = getStringDataRR("FINAL RESULTS", 4, 1);
		String legalName5 = getStringDataRR("FINAL RESULTS", 5, 1);
		String legalName6 = getStringDataRR("FINAL RESULTS", 6, 1);
		String legalName7 = getStringDataRR("FINAL RESULTS", 7, 1);

		double num1 = getNumericDataRR("FINAL RESULTS", 1, 2);
		double num2 = getNumericDataRR("FINAL RESULTS", 2, 2);
		double num3 = getNumericDataRR("FINAL RESULTS", 3, 2);
		double num4 = getNumericDataRR("FINAL RESULTS", 4, 2);
		double num5 = getNumericDataRR("FINAL RESULTS", 5, 2);
		double num6 = getNumericDataRR("FINAL RESULTS", 6, 2);
		double value1 = num1;
		DecimalFormat formatter1 = new DecimalFormat("#,###");
		String formattedValue1 = formatter1.format(value1);

		double value2 = num2;
		DecimalFormat formatter2 = new DecimalFormat("#,###");
		String formattedValue2 = formatter2.format(value2);
		double value3 = num3;
		DecimalFormat formatter3 = new DecimalFormat("#,###");
		String formattedValue3 = formatter3.format(value3);
		double value4 = num4;
		DecimalFormat formatter4 = new DecimalFormat("#,###");
		String formattedValue4 = formatter4.format(value4);
		double value5 = num5;
		DecimalFormat formatter5 = new DecimalFormat("#,###");
		String formattedValue5 = formatter5.format(value5);
		double value6 = num6;
		String formattedValue6 = formatter1.format(value6);
		double value7 = num2;
		String formattedValue7 = formatter1.format(value7);
		double num1_advance = getNumericDataRR("FINAL RESULTS", 1, 3);
		double num2_advance = getNumericDataRR("FINAL RESULTS", 2, 3);
		double num3_advance = getNumericDataRR("FINAL RESULTS", 3, 3);
		double num4_advance = getNumericDataRR("FINAL RESULTS", 4, 3);
		double value_Advance_1 = num1_advance;
		DecimalFormat formatter_advance1 = new DecimalFormat("#,###");
		String formattedValue_advnace1 = formatter_advance1.format(value_Advance_1);

		double value_Advance_2 = num2_advance;
		DecimalFormat formatter_advance2 = new DecimalFormat("#,###");
		String formattedValue_advnace2 = formatter_advance2.format(value_Advance_2);
		double value_Advance_3 = num3_advance;
		DecimalFormat formatter_advance3 = new DecimalFormat("#,###");
		String formattedValue_advnace3 = formatter_advance3.format(value_Advance_3);
		double value_Advance_4 = num4_advance;
		DecimalFormat formatter_advance4 = new DecimalFormat("#,###");
		String formattedValue_advnace4 = formatter_advance4.format(value_Advance_4);
		double value_Advance_5 = num1_advance;
		DecimalFormat formatter_advance5 = new DecimalFormat("#,###");
		String formattedValue_advnace5 = formatter_advance5.format(value_Advance_5);
		double value_Advance_6 = num1_advance;
		DecimalFormat formatter_advance6 = new DecimalFormat("#,###");
		String formattedValue_advnace6 = formatter_advance6.format(value_Advance_6);
		double value_Advance_7 = num1_advance;
		DecimalFormat formatter_advance7 = new DecimalFormat("#,###");
		String formattedValue_advnace7 = formatter_advance7.format(value_Advance_7);

		ObjectRepo.getTest().log(Status.PASS, "Results validated from Result report ");

		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName1 + " Granted With " + formattedValue1
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName2 + " Granted With " + formattedValue2
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName3 + " Granted With " + formattedValue3
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName4 + " Granted With " + formattedValue4
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName5 + " Granted With " + formattedValue5
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName6 + " Granted With " + formattedValue6
				+ " Emission Units for Current Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName7 + " Granted With " + formattedValue7
				+ " Emission Units for Current Auction Allowance");

		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName1 + " Granted With " + formattedValue_advnace1
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName2 + " Granted With " + formattedValue_advnace2
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName3 + " Granted With " + formattedValue_advnace3
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName4 + " Granted With " + formattedValue_advnace4
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName5 + " Granted With " + formattedValue_advnace5
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName6 + " Granted With " + formattedValue_advnace6
				+ " Emission Units for Advance Auction Allowance");
		ObjectRepo.getTest().log(Status.PASS, "Entity: " + legalName7 + " Granted With " + formattedValue_advnace7
				+ " Emission Units for Advance Auction Allowance");
	}

	public void eventCertification() throws InterruptedException {
		JointAuctionTieBreakerPage login = new JointAuctionTieBreakerPage();
		login.sponsorLoginCa2();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		login.clickbtnAdmin();
		driver.findElement(By.xpath("(//*[text()='Auction Certification'])[1]")).click();
		driver.findElement(By.xpath("(//*[@aria-label='firstAuctionCertified'])[1]")).click();
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]")).click();
		login.Logout();
	}

	public void eventCertificationDualApproval() throws InterruptedException {
		JointAuctionTieBreakerPage login = new JointAuctionTieBreakerPage();
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameForJointAuction();
		driver.findElement(By.xpath("//*[@aria-label='Download Financial Settlement Report for event "
				+ prop.getProperty("event.name") + "']")).click();
		Thread.sleep(20000);
	}

	public void getDataFromFSReport() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Settlement Report - "
				+ prop.getProperty("event.name") + " " + date1 + ".xlsx");
		System.out.println("path :" + folderPath + "Financial Settlement Report - " + prop.getProperty("event.name")
				+ " " + date1 + ".xlsx");
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getStringDataFS(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
	}

	public double getNumericDataFS(String Sheetname, int row, int col) {
		return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	public void getDataFromFinancialSettlementReport() throws Exception {

		String currentSettPriceUSD = getStringDataFS("Financial Settlement Report", 4, 0);
		String currentSettPriceCAD = getStringDataFS("Financial Settlement Report", 5, 0);
		String settlementpriceUSD = getStringDataFS("Financial Settlement Report", 4, 1);
		double settlementpriceCAD = getNumericDataFS("Financial Settlement Report", 5, 1);

		String Colomn1 = getStringDataFS("Financial Settlement Report", 7, 0);
		String regGenAccNo1 = getStringDataFS("Financial Settlement Report", 8, 0);
		String regGenAccNo2 = getStringDataFS("Financial Settlement Report", 9, 0);
		String regGenAccNo3 = getStringDataFS("Financial Settlement Report", 10, 0);
		String regGenAccNo4 = getStringDataFS("Financial Settlement Report", 11, 0);
		String regGenAccNo5 = getStringDataFS("Financial Settlement Report", 12, 0);

		String Colomn12 = getStringDataFS("Financial Settlement Report", 7, 11);
		double totalBidGuarantee1 = getNumericDataFS("Financial Settlement Report", 8, 11);
		double totalBidGuarantee2 = getNumericDataFS("Financial Settlement Report", 9, 11);
		double totalBidGuarantee3 = getNumericDataFS("Financial Settlement Report", 10, 11);
		double totalBidGuarantee4 = getNumericDataFS("Financial Settlement Report", 11, 11);
		double totalBidGuarantee5 = getNumericDataFS("Financial Settlement Report", 12, 11);

		String Colomn13 = getStringData("Financial Settlement Report", 7, 12);
		double currentSuccBidAllowances1 = getNumericDataFS("Financial Settlement Report", 8, 12);
		double currentSuccBidAllowances2 = getNumericDataFS("Financial Settlement Report", 9, 12);
		double currentSuccBidAllowances3 = getNumericDataFS("Financial Settlement Report", 10, 12);
		double currentSuccBidAllowances4 = getNumericDataFS("Financial Settlement Report", 11, 12);
		double currentSuccBidAllowances5 = getNumericDataFS("Financial Settlement Report", 12, 12);

		String Colomn15 = getStringData("Financial Settlement Report", 7, 14);
		double totalsuccessfullbidcost1 = getNumericDataFS("Financial Settlement Report", 8, 14);
		double totalsuccessfullbidcost2 = getNumericDataFS("Financial Settlement Report", 9, 14);
		double totalsuccessfullbidcost3 = getNumericDataFS("Financial Settlement Report", 10, 14);
		double totalsuccessfullbidcost4 = getNumericDataFS("Financial Settlement Report", 11, 14);
		double totalsuccessfullbidcost5 = getNumericDataFS("Financial Settlement Report", 12, 14);

		String Colomn16 = getStringDataFS("Financial Settlement Report", 7, 15);
		double amountOwed1 = getNumericDataFS("Financial Settlement Report", 8, 15);
		double amountOwed2 = getNumericDataFS("Financial Settlement Report", 9, 15);
		double amountOwed3 = getNumericDataFS("Financial Settlement Report", 10, 15);
		double amountOwed4 = getNumericDataFS("Financial Settlement Report", 11, 15);
		double amountOwed5 = getNumericDataFS("Financial Settlement Report", 12, 15);

		String Colomn17 = getStringDataFS("Financial Settlement Report", 7, 16);
		String amountOwedBy1 = getStringDataFS("Financial Settlement Report", 8, 16);
		String amountOwedBy2 = getStringDataFS("Financial Settlement Report", 9, 16);
		String amountOwedBy3 = getStringDataFS("Financial Settlement Report", 10, 16);
		String amountOwedBy4 = getStringDataFS("Financial Settlement Report", 11, 16);
		String amountOwedBy5 = getStringDataFS("Financial Settlement Report", 12, 16);

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
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo1 + " , " + Colomn12 + " : " + totalBidGuarantee1 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances1 + " , " + Colomn15 + " : " + totalsuccessfullbidcost1
						+ " , " + Colomn16 + " : " + amountOwed1 + " , " + Colomn17 + " : " + amountOwedBy1);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo2 + " , " + Colomn12 + " : " + totalBidGuarantee2 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances2 + " , " + Colomn15 + " : " + totalsuccessfullbidcost2
						+ " , " + Colomn16 + " : " + amountOwed2 + " , " + Colomn17 + " : " + amountOwedBy2);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo3 + " , " + Colomn12 + " : " + totalBidGuarantee3 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances3 + " , " + Colomn15 + " : " + totalsuccessfullbidcost3
						+ " , " + Colomn16 + " : " + amountOwed3 + " , " + Colomn17 + " : " + amountOwedBy3);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo4 + " , " + Colomn12 + " : " + totalBidGuarantee4 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances4 + " , " + Colomn15 + " : " + totalsuccessfullbidcost4
						+ " , " + Colomn16 + " : " + amountOwed4 + " , " + Colomn17 + " : " + amountOwedBy4);
		ObjectRepo.getTest().log(Status.PASS,
				Colomn1 + " : " + regGenAccNo5 + " , " + Colomn12 + " : " + totalBidGuarantee5 + " , " + Colomn13
						+ " : " + currentSuccBidAllowances5 + " , " + Colomn15 + " : " + totalsuccessfullbidcost5
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

	public void gettextBoxEvent() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(EventUtils.jointAuctionCAQCID);
	}

	public void gettextBoxEvent_PE1() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE1"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE1"));
	}

	public void gettextBoxEvent_PE2() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE2"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE2"));
	}

	public void gettextBoxEvent_PE3() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE3"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE3"));
	}

	public void gettextBoxEvent_PE5() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE5"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE5"));
	}

	public void gettextBoxEvent_PE6() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE6"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE6"));
	}

	public void gettextBoxEvent_PE7() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE7"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE7"));
	}

	public void gettextBoxEvent_PE8() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE8"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE8"));
	}

	public void gettextBoxEvent_PE9() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE9"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE9"));
	}

	public void gettextBoxEvent_PE10() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE10"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE10"));
	}

	public void gettextBoxEvent_PE4() {

		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_PE4"));
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + prop.getProperty("CITSS_Event_ID_PE4"));
	}

	public void selectEventTypeFromDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("Auction");
	}

	public void enterEventName(String jointAuctionEventName) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH/mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		// newEventName = RandomEventNameGenerator.generateAndUpdateEventName();
		// System.out.println("Generated event name: " + newEventName);
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(prop.getProperty("Event_Name"));
	}

	public void enterEventName_PE1() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE1);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE1);
	}

	public void enterEventName_PE2() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE2);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE2);
	}

	public void enterEventName_PE3() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE3);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE3);
	}

	public void enterEventName_PE5() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE5);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE5);
	}

	public void enterEventName_PE6() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE6);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE6);
	}

	public void enterEventName_PE7() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE7);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE7);
	}

	public void enterEventName_PE8() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE8);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE8);
	}

	public void enterEventName_PE9() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE9);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE9);
	}

	public void enterEventName_PE10() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE10);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE10);
	}

	public void enterEventName_PE4() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(eventName_PE4);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventName_PE4);
	}

	public void selectJurisdictionForJointAuction() throws InterruptedException {
		ParticipatingJurisdictions.click();
		try {
			btnCalifornia.click();
			btnQuebec.click();
		} catch (Exception e) {
			btnWashinton.click();
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH/mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);

		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(eventNameEng + "FrenchLang");
	}

	public void selectJurisdictionForJointAuctionA42() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A42");
	}

	public void selectJurisdictionForJointAuctionA45() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A45");
	}

	public void selectJurisdictionForJointAuctionA46() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A46");
	}

	public void selectJurisdictionForJointAuctionA47() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A47");
	}

	public void selectJurisdictionForJointAuctionA48() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A48");
	}

	public void selectJurisdictionForJointAuctionA49() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A49");
	}

	public void selectJurisdictionForJointAuctionA50() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A50");
	}

	public void selectJurisdictionForJointAuctionA51() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A51");
	}

	public void selectJurisdictionForJointAuctionA56() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A56");
	}

	public void selectJurisdictionForJointAuctionA57() throws InterruptedException {
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
		frenchEventName.sendKeys("frenchEventName" + "A57");
	}

	public void currencyAccForjointAuction() throws InterruptedException {
		try {
			// ////
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			// ////
			currencyAcc.click();
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

	public void currencyAccForjointAuction_PE1() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE1"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE1"));
	}

	public void currencyAccForjointAuction_PE2() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE2"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE2"));
	}

	public void currencyAccForjointAuction_PE3() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE3"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE3"));
	}

	public void currencyAccForjointAuction_PE5() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE5"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE5"));
	}

	public void currencyAccForjointAuction_PE6() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE6"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE6"));
	}

	public void currencyAccForjointAuction_PE7() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE7"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE7"));
	}

	public void currencyAccForjointAuction_PE8() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}

		tagUSD.click();

		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE8"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE8"));
	}

	public void currencyAccForjointAuction_PE9() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}
		tagUSD.click();
		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE9"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE9"));
	}

	public void currencyAccForjointAuction_PE10() throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}
		tagUSD.click();
		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD_PE10"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD_PE10"));
	}

	public void selectVintage() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances"));
	}

	public void selectVintage_PE1() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE1"));
		WebElement dropdownvint2 = dropDownVintage2;
		Select select1 = new Select(dropdownvint2);
		select1.selectByVisibleText("2024");
		textBoxVintage2.sendKeys(prop.getProperty("TotalAllowances_Advance_PE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Advance Event Vintage Entered : " + prop.getProperty("TotalAllowances_Advance_PE1"));
	}

	public void selectVintage_PE2() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE2"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE2"));
	}

	public void selectVintage_PE3() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE3"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE3"));
	}

	public void selectVintage_PE5() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE5"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE5"));
	}

	public void selectVintage_PE6() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE6"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE6"));
	}

	public void selectVintage_PE7() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE7"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE7"));
	}

	public void selectVintage_PE8() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE8"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE8"));
	}

	public void selectVintage_PE9() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE9"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE9"));
	}

	public void selectVintage_PE10() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE10"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE10"));
	}

	public void selectVintage_PE4() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE4"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE4"));
	}

	public void selectVintageType() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage1));
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText("Current");
	}

	public void selectVintageType_PE1() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage1));
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText("Current");
		WebElement dropdownvintType1 = drpdownVintageType2;
		Select select1 = new Select(dropdownvintType1);
		select1.selectByVisibleText("Advance");
	}

	public void selectVintageType_PE2() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage1));
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText("Current");
	}

	public void selectProcessingOrder() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
	}

	public void selectProcessingOrder_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
		WebElement dropdownProOrder1 = drpdownProCessOrder2;
		Select select1 = new Select(dropdownProOrder1);
		select1.selectByVisibleText("2");
	}

	public void selectProcessingOrder_PE2() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
		WebElement element = driver.findElement(By.xpath("(//*[@data-icon='trash-alt'])[2]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}

	public void selectDates(int biddingStartTime) throws InterruptedException {
		ZoneId easternTimeZone = ZoneId.of("America/New_York");
		LocalDate today = LocalDate.now(easternTimeZone);
		LocalDate tomorrow = today.plusDays(1);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date1 = today.format(dateFormat);
		System.out.println("Today's date: " + date1);
		String date2 = tomorrow.format(dateFormat);
		System.out.println("Tomorrow's date: " + date2);

		LocalTime myDateObj = LocalTime.now(easternTimeZone);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);

		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(biddingStartTime).plusSeconds(20);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.plusHours(0).minusMinutes(1);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.plusHours(0).plusMinutes(0);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3);

		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(8).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(6).plusMinutes(40);

		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);

		WebElement bidPeriodOpen = dateBiddingPeroidOpen;

		js.executeScript("arguments[0].scrollIntoView();", bidPeriodOpen);

		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + " " + BiddingStart);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']"))
				.sendKeys(date1 + " " + bidGuaranteeSubmissionDeadline);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));

		action.moveToElement(element).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));

		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + " " + BiddingEnd);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + " " + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + " " + applicationClose);

		dateBidResultAvail.click();
		dateBidResultAvail.sendKeys(date2 + " " + resultsAvailable);

		dateBiddingPeriodClose.click();
		dateEventClosure.click();
		dateEventClosure.sendKeys(date2 + " " + eventClosure);

		dateBiddingPeriodClose.click();
		dateFinantialSettlement.click();
		dateFinantialSettlement.sendKeys(date2 + " " + financialSettlement);
	}

	public void selectDates_PE2() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1); // Adding 1 day to current date
		Date tomorrow = calendar.getTime();
		String date1 = dateFormat.format(date); // Today's date
		System.out.println("todays date :" + date1);
		String date2 = dateFormat.format(tomorrow); // Tomorrow's date
		System.out.println("Tomorrow's date :" + date2);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);
		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(4);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(5);
		System.out.println("application Close time :" + applicationClose);
		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3);
		;
		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(6).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(8).plusMinutes(40);
		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + " " + BiddingStart);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']"))
				.sendKeys(date1 + " " + bidGuaranteeSubmissionDeadline);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));
		action.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + " " + BiddingEnd);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + " " + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + " " + applicationClose);
		dateBidResultAvail.click();
		dateBidResultAvail.sendKeys(date2 + " " + resultsAvailable);
		dateEventClosure.click();
		dateEventClosure.sendKeys(date2 + " " + eventClosure);
		dateFinantialSettlement.click();
		dateFinantialSettlement.sendKeys(date2 + " " + financialSettlement);
	}

	public void selectDates_PE3() throws InterruptedException {
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(5).plusSeconds(45);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(30).plusSeconds(10);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(5);
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
		WebElement element2 = dateEventAppClose;

		js.executeScript("arguments[0].scrollIntoView();", element2);

		element2.click();
		dateEventAppClose.sendKeys(date1 + applicationClose);
	}

	public void selectDates_PE1() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1); // Adding 1 day to current date
		Date tomorrow = calendar.getTime();
		String date1 = dateFormat.format(date); // Today's date
		System.out.println("todays date :" + date1);
		String date2 = dateFormat.format(tomorrow); // Tomorrow's date
		System.out.println("Tomorrow's date :" + date2);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);
		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(4);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(5);
		System.out.println("application Close time :" + applicationClose);
		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3);
		;
		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(8).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(6).plusMinutes(40);
		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + " " + BiddingStart);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']"))
				.sendKeys(date1 + " " + bidGuaranteeSubmissionDeadline);
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));
		action.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + " " + BiddingEnd);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + " " + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + " " + applicationClose);
		dateBidResultAvail.click();
		dateBidResultAvail.sendKeys(date2 + " " + resultsAvailable);
		dateEventClosure.click();
		dateEventClosure.sendKeys(date2 + " " + eventClosure);
		dateFinantialSettlement.click();
		dateFinantialSettlement.sendKeys(date2 + " " + financialSettlement);
	}

	public void selectAlgorithm() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		try {
			driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		js.executeScript("arguments[0].scrollIntoView();", btnSave);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='The event has been created successfully.']")));
		String alert = driver.findElement(By.xpath("//*[text()='The event has been created successfully.']")).getText();
		System.out.println(alert);
		Assert.assertEquals("The event has been created successfully.", alert);
		ObjectRepo.getTest().log(Status.PASS, "The event has been created successfully.");
	}

	public void selectAlgorithm_PE1() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		String alert = successmsg.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The event has been created successfully.");
	}

	public void generateString() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomString = ("Event_" + generatedString);
	}

	public void createEventForJointAuction(String eventName) throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent(); // Event Id
		selectEventTypeFromDropDown();
		GenericUtils gm = new GenericUtils();
		gm.enterEventName(eventName);
		selectJurisdictionForJointAuction();
		currencyAccForjointAuction();
		selectVintage();
		selectVintageType();
		selectProcessingOrder(); // Dates and Times
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_PE1() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE1(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE1();
		selectJurisdictionForJointAuctionA42();
		currencyAccForjointAuction_PE1();
		selectVintage_PE1();
		selectVintageType_PE1();
		selectProcessingOrder_PE1(); // Dates and Times
		selectDates_PE1();
		selectAlgorithm_PE1();
	}

	public void createEventForJointAuction_PE2() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE2(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE2();
		selectJurisdictionForJointAuctionA45();
		currencyAccForjointAuction_PE2();
		selectVintage_PE2();
		selectVintageType_PE2();
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates_PE2();
		selectAlgorithm_PE1();
	}

	public void createEventForJointAuction_PE3() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE3(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE3();
		selectJurisdictionForJointAuctionA46();
		currencyAccForjointAuction_PE3();
		selectVintage_PE3();
		selectVintageType_PE2();// Has only current Allowance --IT will work
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE5() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE5();
		selectEventTypeFromDropDown();
		enterEventName_PE5();
		selectJurisdictionForJointAuctionA48();
		currencyAccForjointAuction_PE5();
		selectVintage_PE5();
		selectVintageType_PE2();
		selectProcessingOrder_PE2();
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE6() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE6(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE6();
		selectJurisdictionForJointAuctionA49();
		currencyAccForjointAuction_PE6();
		selectVintage_PE6();
		selectVintageType_PE2();// Has only current Allowance --IT will work
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE7() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE7(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE7();
		selectJurisdictionForJointAuctionA50();
		currencyAccForjointAuction_PE7();
		selectVintage_PE7();
		selectVintageType_PE2();// Has only current Allowance --IT will work
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE8() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE8(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE8();
		selectJurisdictionForJointAuctionA51();
		currencyAccForjointAuction_PE8();
		selectVintage_PE8();
		selectVintageType_PE2();// Has only current Allowance --IT will work
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE9() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE9(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE9();
		selectJurisdictionForJointAuctionA56();
		currencyAccForjointAuction_PE9();
		selectVintage_PE9();
		selectVintageType_PE2();// Has only current Allowance --IT will work
		selectProcessingOrder_PE2(); // Dates and Times
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE10() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE10(); // Event Id
		selectEventTypeFromDropDown();
		enterEventName_PE10();
		selectJurisdictionForJointAuctionA57();
		currencyAccForjointAuction_PE10();
		selectVintage_PE10();
		selectVintageType_PE2();
		selectProcessingOrder_PE2();
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void createEventForJointAuction_PE4() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEvent_PE4();
		selectEventTypeFromDropDown();
		enterEventName_PE4();
		selectJurisdictionForJointAuctionA47();
		currencyAccForjointAuction_PE3();
		selectVintage_PE4();
		selectVintageType_PE2();
		selectProcessingOrder_PE2();
		selectDates(5);
		selectAlgorithm_PE1();

	}

	public void CaliforniaJuridictionApprovalForEventCreation() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
			btnEvent.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
			btnEvent.click();
		}
		WebElement ref1 = driver.findElement(eventDetailsBtnReport);
		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.elementToBeClickable(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE1() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE1);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE1).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");
	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE2() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE2);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE2).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");

	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE3() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE3);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE3).click();
		//
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");

	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE5() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE5);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE5).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");

	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE6() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE6);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE6).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");

	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE7() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE7);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE7).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");
	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE8() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE8);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE8).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");

	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE9() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE9);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE9).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");
	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE10() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE10);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE10).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();

		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");
	}

	public void CaliforniaJuridictionApprovalForEventCreation_PE4() throws InterruptedException {

		WebElement element1 = driver.findElement(eventDetailsBtn_PE4);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn_PE4).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the event creation has been proposed by the first sponsor.");
	}

	public void runtimesponsorLoginCa() throws InterruptedException {
		try {
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		} catch (Exception e) {

			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		}
		Thread.sleep(5000);
	}

	public void secondApproval() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtnReport);
		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		Thread.sleep(3000);
		popupBtn.click();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE1() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE1);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE2() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE2);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE3() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE3);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE5() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE5);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE6() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE6);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE7() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE7);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE8() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();

		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE8);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE9() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE9);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE10() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE10);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void SecondApproval_PE4() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE4);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE1() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE1);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE2() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE2);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE3() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE3);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE5() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE5);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE6() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE6);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE7() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE7);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE8() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE8);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE9() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE9);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE10() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE10);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {
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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApproval_PE4() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref1 = driver.findElement(eventDetailsBtn_PE4);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		try {

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
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void setFXrateQC() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {
			try {

				WebElement element = textBoxFxQc;
				js.executeScript("arguments[0].scrollIntoView();", element);
				textBoxFxQc.click();
				textBoxFxQc.sendKeys(UsersData.FXRate);
				ObjectRepo.getTest().log(Status.PASS, "FX rate entered for Quebec.");
			} catch (Exception e) {

				WebElement element = textBoxFxCal;
				js.executeScript("arguments[0].scrollIntoView();", element);
				textBoxFxCal.click();
				textBoxFxCal.sendKeys(UsersData.FXRate);
				ObjectRepo.getTest().log(Status.PASS, "FX rate entered for California.");
			}
		} catch (Exception e) {
			System.out.println("fx rate set for quebec juridiction ");
		}
		try {
			try {
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
				btnsubmitqc.click();
				ObjectRepo.getTest().log(Status.PASS, "Clicked on submit button.");
				// driver.findElement(btnsubmitqc).click();
				Thread.sleep(5000);
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
				btnsubmitCal.click();
				// driver.findElement(btnsubmitCal).click();
				Thread.sleep(5000);
				ObjectRepo.getTest().log(Status.PASS, "Clicked on submit button.");
			}
		} catch (Exception e) {
			System.out.println("Click On Submit button. ");
		}

	}

	public void setFXrateQC_PE1() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {

			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {

			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE1"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE1"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE2() throws InterruptedException {

		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {

			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {

			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE2"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE2"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE2"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE2"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE3() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE3"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE3"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE3"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE3"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {

			btnsubmitqc.click();
		} catch (Exception e) {

			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE5() throws InterruptedException {

		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE5"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE5"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE5"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE5"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE6() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE6"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE6"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE6"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE6"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE7() throws InterruptedException {

		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE7"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE7"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE7"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE7"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE8() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE8"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE8"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE8"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE8"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE9() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE9"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE9"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE9"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE9"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			btnsubmitqc.click();
		} catch (Exception e) {
			btnsubmitCal.click();
		}
		btnAlertSubmit.click();
	}

	public void setFXrateQC_PE10() throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE10"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE10"));
		} catch (Exception e) {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE10"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE10"));
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
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
			btnAdminstration.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
			btnAdminstration.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on administration button.");
		}
		try {
			btnReservePrice.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on Reserve Price button.");
		} catch (Exception e1) {
			btnReservePrice2.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on Reserve Price button.");
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(UsersData.FXRate);
			ObjectRepo.getTest().log(Status.PASS, "FX rate entered for California.");
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(UsersData.FXRate);
			ObjectRepo.getTest().log(Status.PASS, "FX rate entered for Quebec.");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on submit button.");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on submit button.");
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		Thread.sleep(5000);
		System.out.println("fx rate set for Joint Auction ");
		ObjectRepo.getTest().log(Status.PASS, "fx rate set for Joint Auction.");
	}
	
	public void setFXrateForQuebecJurisdiction(String eventName, String qcFxRate) {
		clickbtnAdmin();
		try {
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice)).click();
		}
//		CommonMethods.waitForElementToBeClickable(btnMinPrice);
 
		// Declare XPaths within method using the eventName
		String xpathTextBoxFxQc;
		String xpathBtnSubmitQc;
		try {
			xpathTextBoxFxQc = String.format(
					"//td[p[contains(text(), '%s')]]/parent::tr//following-sibling::tr[1]//input[contains(@aria-label, 'Enter FX Rate for QC')]",
					eventName);
			xpathBtnSubmitQc = String.format(
					"//td[p[contains(text(), '%s')]]/parent::tr//following-sibling::tr[1]//td//button", eventName);
			// Find elements using the constructed XPaths
			WebElement textBoxFxQc = driver.findElement(By.xpath(xpathTextBoxFxQc));
			WebElement btnSubmitQc = driver.findElement(By.xpath(xpathBtnSubmitQc));
 
			js.executeScript("arguments[0].scrollIntoView();", textBoxFxQc);
			CommonMethods commonMethods2 = new CommonMethods();
			commonMethods2.waitForElementToBeClickable(textBoxFxQc);
 
			textBoxFxQc.sendKeys(qcFxRate);
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + qcFxRate);
			System.out.println("fx rate set for quebec juridiction ");
 
			commonMethods2.waitForElementToBeClickable(btnSubmitQc);
			commonMethods2.waitForElementToBeVisible(QCFxRateText);
			wait.until(ExpectedConditions.visibilityOf(QCFxRateText));
 
			String alert = QCFxRateText.getText();
			System.out.println(alert);
			Assert.assertEquals(alert, "FX Rate submitted.");
			ObjectRepo.getTest().log(Status.PASS, "FX Rate submitted.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			String xpathTextBoxFxCal = String.format(
					"//td[p[contains(text(), '%s')]]/following-sibling::td//input[contains(@aria-label, 'Enter FX Rate for QC')]",
					eventName);
			String xpathBtnSubmitCal = String.format("//td[p[contains(text(), '%s')]]/following-sibling::td//button",
					eventName);
			WebElement textBoxFxCal = driver.findElement(By.xpath(xpathTextBoxFxCal));
			WebElement btnSubmitCal = driver.findElement(By.xpath(xpathBtnSubmitCal));
			js.executeScript("arguments[0].scrollIntoView();", textBoxFxCal);
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(textBoxFxCal);
			textBoxFxCal.sendKeys(qcFxRate);
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + qcFxRate);
 
			commonMethods.waitForElementToBeClickable(btnSubmitCal);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
 
	public void setFXrateForCaliforniaJurisdiction(String eventName, String caFxRate) {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		CommonMethods commonMethods3 = new CommonMethods();
		commonMethods3.waitForElementToBeClickable(btnAdminstration);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice)).click();
		}
		String xpathTextBoxFxQc;
		String xpathBtnSubmitQc;
		try {
			xpathTextBoxFxQc = String.format(
					"//td[p[contains(text(), '%s')]]/parent::tr//following-sibling::tr[1]//input[contains(@aria-label, 'Enter FX Rate for CA')]",
					eventName);
			xpathBtnSubmitQc = String.format(
					"//td[p[contains(text(), '%s')]]/parent::tr//following-sibling::tr[1]//td//button", eventName);
			WebElement textBoxFxQc = driver.findElement(By.xpath(xpathTextBoxFxQc));
			WebElement btnSubmitQc = driver.findElement(By.xpath(xpathBtnSubmitQc));
			js.executeScript("arguments[0].scrollIntoView();", textBoxFxQc);
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(textBoxFxQc);
			textBoxFxQc.sendKeys(caFxRate);
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + caFxRate);
			System.out.println("fx rate set for quebec juridiction ");
			commonMethods.waitForElementToBeClickable(btnSubmitQc);
			commonMethods.waitForElementToBeClickable(btnConfirmSubmit);
			Thread.sleep(5000);
		} catch (Exception e) {
			String xpathTextBoxFxCal = String.format(
					"//td[p[contains(text(), '%s')]]/following-sibling::td//input[contains(@aria-label, 'Enter FX Rate for CA')]",
					eventName);
			String xpathBtnSubmitCal = String.format("//td[p[contains(text(), '%s')]]/following-sibling::td//button",
					eventName);
			WebElement textBoxFxCal = driver.findElement(By.xpath(xpathTextBoxFxCal));
			WebElement btnSubmitCal = driver.findElement(By.xpath(xpathBtnSubmitCal));
			js.executeScript("arguments[0].scrollIntoView();", textBoxFxCal);
			CommonMethods commonMethods2 = new CommonMethods();
			commonMethods2.waitForElementToBeClickable(textBoxFxCal);
			textBoxFxCal.sendKeys(caFxRate);
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + caFxRate);
			commonMethods2.waitForElementToBeClickable(btnSubmitCal);
			commonMethods2.waitForElementToBeClickable(btnConfirmSubmit);
			System.out.println("fx rate set for Joint Auction ");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void setFXrateCal_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {

			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE1"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE1"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE2() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE2"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE2"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE2"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE2"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE3() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE3"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE3"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE3"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE3"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE5() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE5"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE5"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE5"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE5"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE6() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE6"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE6"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE6"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE6"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE7() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE7"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE7"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE7"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE7"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE8() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE8"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE8"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE8"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE8"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE9() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE9"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE9"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE9"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE9"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");
	}

	public void setFXrateCal_PE10() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			btnReservePrice.click();
		} catch (Exception e1) {
			btnReservePrice2.click();
		}
		try {

			WebElement element = textBoxFxCal;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxCal.click();
			textBoxFxCal.sendKeys(prop.getProperty("FXRate_PE10"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE10"));
		} catch (Exception e) {

			WebElement element = textBoxFxQc;
			js.executeScript("arguments[0].scrollIntoView();", element);
			textBoxFxQc.click();
			textBoxFxQc.sendKeys(prop.getProperty("FXRate_PE10"));
			ObjectRepo.getTest().log(Status.PASS, "FX Rate Entered : " + prop.getProperty("FXRate_PE10"));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			btnsubmitCal.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			btnsubmitqc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirmSubmit));
		btnConfirmSubmit.click();
		System.out.println("fx rate set for Joint Auction ");

	}

	public void executeAPIGetQualifiedBidder() throws InterruptedException {
		try {
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			} catch (Exception e) {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			}
			btnSettings.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
			btnExecuteAPI.click();
			ObjectRepo.getTest().log(Status.PASS, "Navigate to Execute API Page.");
			WebElement element1 = APIGetQualifiedBidder;

			js.executeScript("arguments[0].scrollIntoView();", element1);
			APIGetQualifiedBidder.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(APIGetBidLimitations));
			APIGetBidLimitations.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(APIexecutionPopupbtn));
		APIexecutionPopupbtn.click();
		Thread.sleep(8000);
		System.out.println("Get Qualified bidder API  Executed successfully");
		ObjectRepo.getTest().log(Status.PASS, "Get Qualified bidder API  Executed successfully");

	}

	public void executeAPIPostUserRelationShip() throws InterruptedException {
		try {
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			} catch (Exception e) {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			}
			wait.until(ExpectedConditions.elementToBeClickable(btnSettings));
			WebElement abc = btnSettings;
			Actions action = new Actions(driver);
			action.moveToElement(abc).build().perform();
			action.clickAndHold(abc).build().perform();
			action.click(abc).build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
			btnExecuteAPI.click();

			WebElement element1 = driver.findElement(By.xpath("(//button[@title='Execute'])[3]"));
			js.executeScript("arguments[0].scrollIntoView();", element1);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(APIGetQualifiedBidder));
			APIGetQualifiedBidder.click();
		}
		APIexecutionPopupbtn.click();
		System.out.println("Get Qualified bidder API  Executed successfully");
		ObjectRepo.getTest().log(Status.PASS, "Get Qualified bidder API  Executed successfully");

	}

	public void executeAPIGetBidLimitations() throws InterruptedException {
		try {
			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			} catch (Exception e) {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			}
			wait.until(ExpectedConditions.elementToBeClickable(btnSettings));
			btnSettings.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
			btnExecuteAPI.click();

			WebElement element2 = APIGetBidLimitations;
			js.executeScript("arguments[0].scrollIntoView();", element2);
			wait.until(ExpectedConditions.elementToBeClickable(APIGetBidLimitations));
			APIGetBidLimitations.click();
		} catch (Exception e) {
			APIGetBidLimitations.click();
		}
		APIexecutionPopupbtn.click();
		Thread.sleep(20000);
		System.out.println("Get Bid Limitation API  Executed successfully");
		ObjectRepo.getTest().log(Status.PASS, "Get Bid Limitation API  Executed successfully");

	}

	public void executeApisForWashingtonReserveSale() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnSettings));
		WebElement abc = btnSettings;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
		btnExecuteAPI.click();

		WebElement element2 = APIGetQualifiedBidder;
		js.executeScript("arguments[0].scrollIntoView();", element2);
		APIGetQualifiedBidder.click();

		APIexecutionPopupbtn.click();

		String Msg = driver.findElement(By.xpath("//tbody//tr[2]/td[4]")).getText();
		if (Msg.contains("Success")) {
			System.out.println("Get Qualified Bidder API executed successfully");

			WebElement element3 = APIGetBidLimitations;
			js.executeScript("arguments[0].scrollIntoView();", element3);

			element3.click();
			APIGetQualifiedBidder.click();
			System.out.println("Get Bid Limitation API executed successfully");

		} else {
			WebElement element1 = driver.findElement(By.xpath("(//*[text()='Execute'])[4]"));
			js.executeScript("arguments[0].scrollIntoView();", element1);
			driver.findElement(By.xpath("(//*[text()='Execute'])[4]")).click();

			APIexecutionPopupbtn.click();

			String Msg2 = driver.findElement(By.xpath("//tbody//tr[4]/td[4]")).getText();
			Assert.assertEquals(Msg2, "Success");
			driver.navigate().refresh();

			js.executeScript("arguments[0].scrollIntoView();", element2);
			APIGetBidLimitations.click();

			js.executeScript("arguments[0].scrollIntoView();", element2);
			APIGetQualifiedBidder.click();

		}

	}

	public void ARLoginQC3() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(UsersData.AR_USerName2);
			password.sendKeys(UsersData.password);
			btnLogin2.click();
			// ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(UsersData.AR_USerName2);
			password.sendKeys(UsersData.password);
			btnLogin.click();
		}

	}

	public void ARLogin(String UserName, String Password) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(UserName);
			password.sendKeys(Password);
			btnLogin2.click();
			// ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(UserName);
			password.sendKeys(Password);
			btnLogin.click();
		}

	}

	public void ARLoginCA_PE1() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "AR Username Entered  : " + prop.getProperty("AR_USerName1_PE1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS, "AR Passward Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1_PE1") + " AR login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("AR_USerName1_PE1") + " AR login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "AR Username Entered  : " + prop.getProperty("AR_USerName1_PE1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS, "AR Passward Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName1_PE1") + " AR login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("AR_USerName1_PE1") + " AR login successfully");
		}

	}

	public void clickOnnextQC() throws InterruptedException {

		WebElement abc = btnNextQC;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();

	}

	public void SelectEntity1() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity5));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity(String entityID) {
		WebElement entityName = driver.findElement(By.xpath("//span[text()='" + entityID + "']"));
		wait.until(ExpectedConditions.visibilityOf(entityName));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity1_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE1));
		driver.findElement(Selectentity1_PE1).click();

		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE1") + " :Entity Account Selected By Account Representative");

	}

	public void SelectEntity1_PE2() throws InterruptedException {
		driver.findElement(Selectentity1_PE2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE2") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity1_PE3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE3));
		driver.findElement(Selectentity1_PE3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE3") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity1_PE5() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE5));
		driver.findElement(Selectentity1_PE5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE5") + " :Entity Account Selected By Account Representative");

	}

	public void SelectEntity1_PE6() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE6));
		driver.findElement(Selectentity1_PE6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE6") + " :Entity Account Selected By Account Representative");

	}

	public void SelectEntity1_PE7() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE7));
		driver.findElement(Selectentity1_PE7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE7") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity1_PE8() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE8));
		driver.findElement(Selectentity1_PE8).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE8") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity1_PE9() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE9));
		driver.findElement(Selectentity1_PE9).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE9") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity1_PE10() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE10));
		driver.findElement(Selectentity1_PE10).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE10") + " :Entity Account Selected By Account Representative");

	}

	public void SelectEntity1_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity1_PE4));
		driver.findElement(Selectentity1_PE4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFirstEntity_PE4") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE4));
		driver.findElement(Selectentity2_PE4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE4") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE5() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE5));
		driver.findElement(Selectentity2_PE5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE5") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE6() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE6));
		driver.findElement(Selectentity2_PE6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE6") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE7() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE7));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity2_PE7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE7") + " :Entity Account Selected By Account Representative");
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity2_PE8() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE8));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity2_PE8).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE8") + " :Entity Account Selected By Account Representative");
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity2_PE9() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE9));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity2_PE9).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE9") + " :Entity Account Selected By Account Representative");
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity2_PE10() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE10));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity2_PE10).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE10") + " :Entity Account Selected By Account Representative");
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity3_PE4() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE4));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity3_PE4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE4") + " :Entity Account Selected By Account Representative");
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");

	}

	public void SelectEntity4_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE4));
		driver.findElement(Selectentity4_PE4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE4") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE4() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE4));
		driver.findElement(Selectentity5_PE4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE4") + " :Entity Account Selected By Account Representative");
	}

	public void selectEventForBiddingE2E() throws InterruptedException {

		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Events'])")));
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']")));
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']")).click();
			System.out.println("Navigae to particular Event: " + prop.getProperty("Event_Name"));
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + prop.getProperty("Event_Name"));
		}

	}

	public void selectEventForBiddingE2EUpdated(String eventName) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Events'])")));
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + eventName + "']")));
			driver.findElement(By.xpath("//*[text()='" + eventName + "']")).click();
			System.out.println("Navigae to particular Event: " + eventName);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventName);
		}
	}

	public void selectEventForBidding(String eventname) throws InterruptedException {

		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			WebElement eventName = driver.findElement(By.xpath("//*[text()='" + eventname + "']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Events'])")));
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(eventName));
			eventName.click();
			System.out.println("Navigae to particular Event: " + prop.getProperty("Event_Name"));
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + prop.getProperty("Event_Name"));
		}

	}

	public void selectEventForBiddingE2EJointAuction() throws InterruptedException {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(eventnameonbidpageCalJointAuction));
			driver.findElement(eventnameonbidpageCalJointAuction).click();

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		}

	}

	public void selectEventForBidding_PE1() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));

			btnEvent.click();

			WebElement event = driver.findElement(By.xpath("//a[text()='" + prop.getProperty("Event_Name_PE1") + "']"));

			js.executeScript("arguments[0].scrollIntoView();", event);
			driver.findElement((By) event).click();

			//
			//
			// driver.findElement(By.xpath("//*[text()='Events']")).click();
			//
			// driver.findElement(By.xpath("//a[text()='" +
			// prop.getProperty("Event_Name_PE1") + "']")).click();
			// System.out.println("Navigate to particular Event: " +
			// prop.getProperty("Event_Name_PE1"));
			// ObjectRepo.getTest().log(Status.PASS, "Navigate to particular Event: " +
			// prop.getProperty("Event_Name_PE1") + "");
			//

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE2.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE3() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE3.click();

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE5() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE5.click();

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE6() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE6.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE7() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE7.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE8() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE8.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE9() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE9.click();

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE10() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE10.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBidding_PE4() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			eventnameonbidpage_PE4.click();

		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBiddingMonitoringtestcase() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnevent));
			btnEvent.click();

			runningEventName.click();
		} catch (Exception e) {

			driver.findElement(By.xpath("//*[text()='Auction']")).click();

		}

	}

	public void selectEventForBiddingMonitoringtestcase1() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Events']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Auction']")));
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[text()='Events']")).click();
		wait.until(ExpectedConditions.visibilityOf(runningEventName1));
		runningEventName1.click();
	}

	public void BiddingByARforTestData1() throws IOException, InterruptedException {
		System.out.println("Step : Select multiple bids from submitted bids table.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Select multiple bids from submitted bids table.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(6000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(EntityData.Bid_Price1);

		textBidLots.sendKeys(EntityData.Bid_Lot1);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE1() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE2() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE3() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE5() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE6() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE8() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE9() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1_PE10() throws IOException, InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();

		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(6000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(EntityData.Bid_Price2);

		textBidLots.sendKeys(EntityData.Bid_Lot2);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE2() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(6000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(EntityData.Bid_Price3);

		textBidLots.sendKeys(EntityData.Bid_Lot3);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		//
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE2() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(EntityData.Bid_Price4);

		textBidLots.sendKeys(EntityData.Bid_Lot4);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE2() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE3() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE5() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE6() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			WebElement element = btnAddbid;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();

			try {
				action.click(element).build().perform();
			} catch (Exception f) {
				element.click();
			}
			textBidPrice.sendKeys(EntityData.Bid_Price_Current_PE10);

			textBidLots.sendKeys(EntityData.Bid_Lot1_Current_PE10);
			dropdownvintage.sendKeys("Current");
			btnSubmit.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
			btnConfirm.click();

			String Massage2 = alertMsg2.getText();
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

//		public void BiddingByARforTestData2() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(6000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2"));
//			ObjectRepo.getTest().log(Status.PASS, "Bid price entered successfully.");
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2"));
//			ObjectRepo.getTest().log(Status.PASS, "Bid Lot entered successfully.");
//			dropdownvintage.sendKeys("Current");
//			ObjectRepo.getTest().log(Status.PASS, "User selected 'Vintage' from the dropdown menu.");
//			btnSubmit.click();
//			ObjectRepo.getTest().log(Status.PASS, "Clicked on Submit button successfully.");
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			ObjectRepo.getTest().log(Status.PASS, "Clicked on Confirm button successfully.");
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData2_PE1() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE1"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE1"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData2_PE2() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(6000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE2"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE2"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData2_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData2_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData2_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData2_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData2_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData2_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			//		                             
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData3_PE1() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Advance_PE1"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Advance_PE1"));
//			dropdownvintage.sendKeys("Advance");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData3_PE2() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE2"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE2"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData3_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData3_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData3_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData3_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData3_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData3_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE1() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE1"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE1"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE2() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE2"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE2"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData4_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData6_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData7_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData8_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData9_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData10_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData11_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData12_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData13_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData14_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData14_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData14_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}

//		public void BiddingByARforTestData14_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData14_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData14_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData15_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE3() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE3"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE3"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE5() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE6() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE6"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE6"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE8() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE8"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE8"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE9() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE9"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE9"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData16_PE10() throws Exception {
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//			try {
//				Thread.sleep(4000);
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
//				btnAddbid.click();
//			} catch (Exception e) {
//				btnAddbid.click();
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE10"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE10"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//
//		public void BiddingByARforTestData5() throws Exception {
//			Thread.sleep(2000);
//			driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//>>>>>>> 003a2e4d5111e6d080d6468f412ecc986f654f9c
//			try {
//				Actions actions = new Actions(driver);
//				actions.moveToElement(btnAddbid).click().build().perform();
//			} catch (Exception ex) {
//				System.out.println("Failed to click element using Actions: " + ex.getMessage());
//				try {
//
//					js.executeScript("arguments[0].click();", btnAddbid);
//				} catch (Exception jsEx) {
//					System.out.println("All click attempts failed: " + jsEx.getMessage());
//					throw jsEx; // Rethrow to indicate failure
//				}
//			}
//			textBidPrice.sendKeys(prop.getProperty("Bid_Price5"));
//			textBidLots.sendKeys(prop.getProperty("Bid_Lot5"));
//			dropdownvintage.sendKeys("Current");
//			btnSubmit.click();
//			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
//			btnConfirm.click();
//
//			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
//			String Massage2 = alertMsg2.getText();
//			System.out.println(Massage2);
//			alertPopup.click();
//		}
//	}

	public void BiddingByARforTestData5_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
			}
			textBidPrice.sendKeys(EntityData.Bid_Price6);

			textBidLots.sendKeys(EntityData.Bid_Lot6);
			dropdownvintage.sendKeys("Current");
			btnSubmit.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
			btnConfirm.click();

			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage2 = alertMsg2.getText();
			System.out.println(Massage2);
			alertPopup.click();

		}

	}

	public void BiddingByARforTestData6_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
			}
			textBidPrice.sendKeys(EntityData.Bid_Price7);

			textBidLots.sendKeys(EntityData.Bid_Lot7);
			dropdownvintage.sendKeys("Current");
			btnSubmit.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
			btnConfirm.click();

			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage2 = alertMsg2.getText();
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData7_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
			}
			textBidPrice.sendKeys(EntityData.Bid_Price8);
			textBidLots.sendKeys(EntityData.Bid_Lot8);
			dropdownvintage.sendKeys("Current");
			btnSubmit.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
			btnConfirm.click();

			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage2 = alertMsg2.getText();
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData8_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			wait.until(ExpectedConditions.elementToBeClickable(alertPopup)).click();
		}
	}

	public void BiddingByARforTestData9_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		wait.until(ExpectedConditions.elementToBeClickable(alertPopup)).click();
		;
	}

	public void BiddingByARforTestData10() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData10_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE1"));
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
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData11_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
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
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData12_PE1() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE1"));
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

	public void BiddingByARforTestData5_PE2() throws Exception {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData13_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE1"));
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

	public void BiddingByARforTestData6_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData14_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
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

	public void BiddingByARforTestData7_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
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
				} catch (Exception eq) {
					wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
					Massage2 = alertMsg2.getText();
				}
				System.out.println(Massage2);
				alertPopup.click();
			}
		}
	}

	public void BiddingByARforTestData15_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE1"));
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

	public void BiddingByARforTestData9_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData22_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price22_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot22_Current_PE1"));
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

	public void BiddingByARforTestData13_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData25_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price25_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot25_Current_PE1"));
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

	public void BiddingByARforTestData17_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE3() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE5() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE6() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE8() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE9() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17_PE10() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE3() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE5() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE6() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE8() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE9() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE10() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE3() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE5() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE6() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE8() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE9() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE10() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE5() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE6() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE8() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE9() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE9"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE10() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData16_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE1"));
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

	public void BiddingByARforTestData10_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData23_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price23_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot23_Current_PE1"));
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

	public void BiddingByARforTestData14_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData26_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price26_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot26_Current_PE1"));
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
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData17_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot17_Current_PE1"));
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

	public void BiddingByARforTestData11_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData24_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price24_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot24_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
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

	public void BiddingByARforTestData16_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE2() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData27_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price27_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot27_Current_PE1"));
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

	public void BiddingByARforTestData28_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price28_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot28_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
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
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
			ObjectRepo.getTest().log(Status.PASS, "User logged out successfully.");
		}
	}

	public void BiddingByARforTestData18_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot18_Current_PE1"));
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

	public void BiddingByARforTestData12_PE2() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Massage2 = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData19_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot19_Current_PE1"));
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
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(10000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			Actions actions = new Actions(driver);
			actions.moveToElement(btnAddbid).click().build().perform();
		} catch (Exception e) {
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(btnAddbid).click().build().perform();
			} catch (Exception ex) {
				System.out.println("Failed to click element using Actions: " + ex.getMessage());
				try {

					js.executeScript("arguments[0].click();", btnAddbid);
				} catch (Exception jsEx) {
					System.out.println("All click attempts failed: " + jsEx.getMessage());
					throw jsEx; // Rethrow to indicate failure
				}
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
			} catch (Exception eq) {
				wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
				Massage2 = alertMsg2.getText();
			}
			System.out.println(Massage2);
			alertPopup.click();
		}
	}

	public void BiddingByARforTestData20_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE1"));
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

	public void BiddingByARforTestData21_PE1() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price21_Advance_PE1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot21_Advance_PE1"));
		dropdownvintage.sendKeys("Advance");
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
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity6));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(btnEntity6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity2_PE1() throws InterruptedException {
		////
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE1));
		// driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or
		// Entity ID']"))
		// .sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(Selectentity2_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		// ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecondEntity_PE1") + " :Entity Account Selected By Account Representative");
	}

	public void ARLoginQC2() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("AR_USerName1"));
		password.sendKeys(prop.getProperty("AR_PassWord"));
		btnLogin2.click();
	}

	public void ARLoginQC_PE1() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2_PE1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS, "AR Passward Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2_PE1") + " AR login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("AR_USerName2_PE1") + " AR login successfully");
			// ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2_PE1"));
			ObjectRepo.getTest().log(Status.PASS, "AR Username Entered  : " + prop.getProperty("AR_USerName2_PE1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS, "AR Passward Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2_PE1") + " AR login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("AR_USerName2_PE1") + " AR login successfully");
		}

	}

	public void SelectEntity3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void SelectEntity3_PE1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE1));
		driver.findElement(Selectentity3_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE1") + " :Entity Account Selected By Account Representative");
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

	public void SelectEntity4_PE1() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE1));
		driver.findElement(Selectentity4_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE1") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE2() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE2));
		driver.findElement(Selectentity2_PE2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE2") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity2_PE3() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity2_PE3));
		driver.findElement(Selectentity2_PE3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CASecoundEntity_PE3") + " :Entity Account Selected By Account Representative");
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

	public void SelectEntity5_PE1() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE1));
		driver.findElement(Selectentity5_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCSecoundEntity_PE1") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE2() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE2));
		driver.findElement(Selectentity3_PE2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCSecoundEntity_PE2") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE3() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE3));
		driver.findElement(Selectentity3_PE3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE3") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE5() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE5));
		driver.findElement(Selectentity3_PE5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE5") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE6() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE6));
		driver.findElement(Selectentity3_PE6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE6") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE7() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE7));
		driver.findElement(Selectentity3_PE7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE7") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE8() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE8));
		driver.findElement(Selectentity3_PE8).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE8") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE9() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE9));
		driver.findElement(Selectentity3_PE9).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE9") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity3_PE10() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity3_PE10));
		driver.findElement(Selectentity3_PE10).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAThirdEntity_PE10") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity6_PE1() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity6_PE1));
		driver.findElement(Selectentity6_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCThirdEntity_PE1") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE2() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE2));
		driver.findElement(Selectentity4_PE2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCThirdEntity_PE2") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE3() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE3));
		driver.findElement(Selectentity4_PE3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE3") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE5() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE5));
		driver.findElement(Selectentity4_PE5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE5") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE6() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE6));
		driver.findElement(Selectentity4_PE6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE6") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE7() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE7));
		driver.findElement(Selectentity4_PE7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE7") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE8() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE8));
		driver.findElement(Selectentity4_PE8).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("CAFourthEntity_PE8") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE9() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE9));
		driver.findElement(Selectentity4_PE9).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE9") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity4_PE10() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity4_PE10));
		driver.findElement(Selectentity4_PE10).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE10") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity7_PE1() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity7_PE1));
		driver.findElement(Selectentity7_PE1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFourthEntity_PE1") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE2() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE2));
		driver.findElement(Selectentity5_PE2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFourthEntity_PE2") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE3() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE3));
		try {
			driver.findElement(Selectentity5_PE3).click();
		} catch (Exception e) {
			driver.findElement(Selectentity5_PE3).click();
		}
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE3") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE5() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE5));
		try {
			driver.findElement(Selectentity5_PE5).click();
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
					.click();
			ObjectRepo.getTest().log(Status.PASS,
					prop.getProperty("QCFirstEntity_PE5") + " :Entity Account Selected By Account Representative");
		} catch (Exception e) {
			driver.findElement(Selectentity5_PE5).click();
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
					.click();
			ObjectRepo.getTest().log(Status.PASS,
					prop.getProperty("QCFirstEntity_PE5") + " :Entity Account Selected By Account Representative");
		}
	}

	public void SelectEntity5_PE6() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE6));
		driver.findElement(Selectentity5_PE6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE6") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE7() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE7));
		driver.findElement(Selectentity5_PE7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE7") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE8() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE8));
		driver.findElement(Selectentity5_PE8).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCFirstEntity_PE8") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE9() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE9));
		driver.findElement(Selectentity5_PE9).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCSecondEntity_PE9") + " :Entity Account Selected By Account Representative");
	}

	public void SelectEntity5_PE10() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Selectentity5_PE10));
		driver.findElement(Selectentity5_PE10).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS,
				prop.getProperty("QCSecondEntity_PE10") + " :Entity Account Selected By Account Representative");
	}

	public void clickbtnEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on button Event.");
	}

	public void getCurrentEvent() throws InterruptedException {
		WebElement ref1 = driver.findElement(eventDetailsBtnReport);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE1() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE1);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE2() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE2);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE3() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE3);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE5() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE5);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE6() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE6);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE7() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE7);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE8() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE8);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE9() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE9);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE10() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE10);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEvent_PE4() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtn_PE4);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");

	}

	public void getCurrentEventQC() throws InterruptedException {
		WebElement ref1 = driver.findElement(eventDetailsBtnReportQC);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");

	}

	public void getCurrentEventWA() throws InterruptedException {
		WebElement ref1 = driver.findElement(eventDetailsBtnWA);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");

	}

	public void getCurrentEventCA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(eventDetailsBtnCASingleAUction));
		WebElement ref1 = driver.findElement(eventDetailsBtnCASingleAUction);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");
		Thread.sleep(20000);

	}

	public void getCurrentEventRSCA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(eventDetailsBtnCA2));
		WebElement ref1 = driver.findElement(eventDetailsBtnCA2);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to particular event ");

	}

	public void getCurrentEventRSQCR70() throws InterruptedException {

		WebElement ref1 = driver.findElement(eventDetailsBtnRSQCR70);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to particular event ");

	}

	public void getCurrentEventRS_WA() throws InterruptedException {
		WebElement ref1 = driver.findElement(eventDetailsBtnRS_WA);

		js.executeScript("arguments[0].scrollIntoView();", ref1);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to particular event ");

	}

	public void runAlgorithm() throws InterruptedException {
		Thread.sleep(7000);
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithm1));
			btnRunAlgorithm1.click();
			Thread.sleep(10000);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithm2));
			btnRunAlgorithm2.click();
			Thread.sleep(10000);
		}
		ObjectRepo.getTest().log(Status.PASS, "Run Algorithm was successfully executed by the first sponsor.");

	}

	public void RuntimeSponsorLoginQc() throws InterruptedException {
		try {
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorCa") + " Sponsor (Quebec) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorCa") + " Sponsor (Quebec) login successfully");
		}

	}

	public void changeLangauge() throws InterruptedException {
		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Évènements']")));
			}
			wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
			langBtnFrench.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(langBtnEng));
			langBtnEng.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Langauge changed successfully from french to english ");
		//
		//
		// WebElement element = langBtnFrench;
		// js.executeScript("arguments[0].scrollIntoView();", element);
		// langBtnFrench.click();
		//
		// langBtnEng.click();
		// System.out.println("Langauge changed successfully from french to english ");

	}

	public void Logout() throws InterruptedException {
		Thread.sleep(5000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
			btnProfile.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
			btnLogout.click();
		} catch (Exception e) {
			System.out.println("Not able to logout.");
		}

	}

	public void clickbtnAdmin() {
		// driver.navigate().refresh();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration)).click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Clicked on button Administration.");
	}

	public void clickOnnext() {
		WebElement abc = btnNext;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();

	}

	public void handleSecurityQue() {
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		Thread.sleep(5000);
		System.out.println(prop.getProperty("event.name"));
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
		username.sendKeys(prop.getProperty("UserSponsorCa2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorCa") + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorCa") + " Sponsor (California) login successfully");
	}

	public void sponsorLoginQc() throws InterruptedException {
		Thread.sleep(4000);
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
		}

	}

	public void closeBidding() throws InterruptedException {

		driver.findElement(By.xpath("//*[text()='Edit Event']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		ZoneId easternTimeZone = ZoneId.of("America/New_York");
		ZonedDateTime zonedDateTime = ZonedDateTime.now(easternTimeZone);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = zonedDateTime.format(myFormatObj);
		System.out.println("Current time: " + formattedDate);
		int num2 = Integer.parseInt(formattedDate.substring(0, 2));
		int num = Integer.parseInt(formattedDate.substring(3, 5));
		LocalTime time = LocalTime.of(num2, num);
		LocalTime biddingEnd = time.plusMinutes(2).plusSeconds(50);
		DateTimeFormatter biddingFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String biddingEndFormatted = biddingEnd.format(biddingFormatObj);
		System.out.println("Bidding end time: " + biddingEndFormatted);

		LocalDate myDateObj2 = zonedDateTime.toLocalDate();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedCurrentDate = myDateObj2.format(dateFormatter);
		System.out.println("Current date: " + formattedCurrentDate);

		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(Keys.CONTROL, "a");
		dateBiddingPeriodClose.sendKeys(Keys.DELETE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(formattedCurrentDate + " " + biddingEndFormatted);
		ObjectRepo.getTest().log(Status.PASS,
				"Bidding End Time changed to : " + formattedCurrentDate + biddingEndFormatted);
		driver.findElement(By.xpath("//*[text()='SAVE']")).click();
		Thread.sleep(20000);
		driver.navigate().refresh();
	}

	public void closeBiddingUpdated() throws InterruptedException {

		driver.findElement(By.xpath("//*[text()='Edit Event']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on edit event button.");
		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		ZoneId easternTimeZone = ZoneId.of("America/New_York");
		ZonedDateTime zonedDateTime = ZonedDateTime.now(easternTimeZone);
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = zonedDateTime.format(myFormatObj);
		System.out.println("Current time: " + formattedDate);
		int num2 = Integer.parseInt(formattedDate.substring(0, 2));
		int num = Integer.parseInt(formattedDate.substring(3, 5));
		LocalTime time = LocalTime.of(num2, num);
		LocalTime biddingEnd = time.plusMinutes(3);
		DateTimeFormatter biddingFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String biddingEndFormatted = biddingEnd.format(biddingFormatObj);
		System.out.println("Bidding end time: " + biddingEndFormatted);

		LocalDate myDateObj2 = zonedDateTime.toLocalDate();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedCurrentDate = myDateObj2.format(dateFormatter);
		System.out.println("Current date: " + formattedCurrentDate);

		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(Keys.CONTROL, "a");
		dateBiddingPeriodClose.sendKeys(Keys.DELETE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(Keys.BACK_SPACE);
		dateBiddingPeriodClose.sendKeys(formattedCurrentDate + " " + biddingEndFormatted);
		ObjectRepo.getTest().log(Status.PASS,
				"Bidding End Time changed to : " + formattedCurrentDate + biddingEndFormatted);
		driver.findElement(By.xpath("//*[text()='SAVE']")).click();
		Thread.sleep(20000);
		driver.navigate().refresh();
	}

	public void SBMACertificationButtonSecondSponcer() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(SBMACertificationButton));
		SBMACertificationButton.click();
		WebElement element111 = QCcertificationbutton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		submitBtncertificationsubmitpopup.click();
		Thread.sleep(10000);
	}

	public void SBMACertificationButtonQC() throws InterruptedException {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(SBMACertificationButton));
			SBMACertificationButton.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(SBMACertificationButton));
			SBMACertificationButton.click();
		}
		Thread.sleep(5000);
		WebElement element111 = auctionCerButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		Thread.sleep(7000);
	}

}
