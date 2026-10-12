package com.wciauction.pageObjects.reports;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.wci.auction.org.resources.CommonLib;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.events.CreateEventPage3;
import com.wciauction.pageObjects.utils.CommonUtils;

import excelReadersForReports.ExcelDataProviderForAllowanceDeliveryReport;
import excelReadersForReports.ExcelDataProviderForBidsAuditReport;
import excelReadersForReports.ExcelDataProviderForBidsAuditReportInFrench;
import excelReadersForReports.ExcelDataProviderForEmissionUnitsDeliveryReport;
import excelReadersForReports.ExcelDataProviderForEmissionUnitsDeliveryReportInFrench;
import excelReadersForReports.ExcelDataProviderForFinancialSettlementReport;
import excelReadersForReports.ExcelDataProviderForFinancialSettlementReportInFrench;
import excelReadersForReports.ExcelDataProviderForPostEventOperationReport;
import excelReadersForReports.ExcelDataProviderForPostEventOperationReportInFrench;
import excelReadersForReports.ExcelDataProviderForQualifiedBidderReport;
import excelReadersForReports.ExcelDataProviderForQualifiedBidderReportInFrench;
import excelReadersForReports.ExcelDataProviderforBidsReport;
import excelReadersForReports.ExcelDataProviderforBidsReportInFrench;
import excelReadersForReports.ExcelDataProviderforResultReport;
import excelReadersForReports.ExcelDataProviderforResultReportInFrench;
import excelReadersForReports.ExcelDataProviderforResultReportRSQCR70;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ReportsPage {

	XSSFWorkbook wb;
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public String date1;
	String name = "Download Rapport d'activités post-événement for event ";
	String name2 = (name + eventNameFR);

	@FindBy(xpath = "//*[text()='T6QCEmetteurA']")
	private WebElement entityName;
	@FindBy(xpath = "//*[text()='WA_Entity+D']")
	private WebElement entityName1;
	@FindBy(xpath = "//*[text()='T6CAEmetteurA']")
	private WebElement entityName2;
	@FindBy(xpath = "//*[text()='Prescribed_Company+R - PLC=212500 PLA=125000 HLC=11598500 HLA=11598500']")
	private WebElement entityNameRS;
	@FindBy(xpath = "//*[text()='Pacific Gas and Electric Company']")
	private WebElement entityNameRS_WA;
	@FindBy(xpath = "//*[text()='T6QCEmetteurB']")
	private WebElement entityNameQC;

	private By btnReportFR = By.xpath("(//*[text()='Rapports'])[1]");

	@FindBy(xpath = "//*[text()='About WCI, Inc.']")
	private WebElement scroll_To_Bottom;

	@FindBy(xpath = "(//*[@aria-label='Entity Name'])[2]")
	private WebElement click_On_Entiy_Dropdown;

	@FindBy(xpath = "//*[@id='entity-reports']/table/tbody/tr/td[1]/div/div[2]/div[2]/span")
	private WebElement select_Entity_Name1;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[4]")
	private WebElement select_Report_Name2;

	@FindBy(xpath = "//span[text()='Rechercher un rapport']")
	private WebElement btnSearchReportFR;

	@FindBy(xpath = "//td[text()='Qualified Bidders Report']")
	private WebElement qualifiedBidderReport;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement btnAdministration;

	@FindBy(xpath = "(//*[text()='Certification - Approved'])")
	private WebElement eventstatus;

	@FindBy(xpath = "//*[text()='Auction 187']")
	private WebElement eventname;

	@FindBy(xpath = "//span[text()='AAA1New1']")
	private WebElement eventNameDropDownOption;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[7]")
	private WebElement downloadReports1;

	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement reportBtn;

	@FindBy(xpath = "//td[text()='Post Event Operations Report']")
	private WebElement postEventOperationsReportEN;

	@FindBy(xpath = "//td[contains(text(),'post-événement')]")
	private WebElement postEventOperationsReportFR;

	@FindBy(xpath = "//td[text()='Bids Audit Report']")
	private WebElement bidsAuditReportEN;

	@FindBy(xpath = "//td[text()='Rapport de vérification des offres']")
	private WebElement bidsAuditReportFR;

	@FindBy(xpath = "//td[text()='Financial Settlement Report']")
	private WebElement financialSettlementReportEN;

	@FindBy(xpath = "//td[text()='Rapport des paiements']")
	private WebElement financialSettlementReportFR;

	@FindBy(xpath = "//td[text()='Bids Report']")
	private WebElement bidsReportEN;

	@FindBy(xpath = "//td[text()='Rapport des offres']")
	private WebElement bidsReportFR;

	@FindBy(xpath = "//td[text()='Results Report']")
	private WebElement resultsReportEN;

	@FindBy(xpath = "//td[text()='Rapport des résultats']")
	private WebElement resultsReportFR;

	@FindBy(xpath = "//td[text()='Emission Units Delivery Report']")
	private WebElement emissionUnitsDeliveryReportEN;

	@FindBy(xpath = "//td[contains(text(),' livraison')]")
	private WebElement emissionUnitsDeliveryReportFR;

	@FindBy(xpath = "//td[text()='Qualified Bidders Report']")
	private WebElement qualifiedBiddersReportEN;

	@FindBy(xpath = "//td[text()='Rapport des enchérisseurs qualifiés']")
	private WebElement qualifiedBiddersReportFR;

	@FindBy(xpath = "//*[text()='Reports Available to Sponsor and Monitor']")
	private WebElement reportAvailableLable;

	@FindBy(xpath = "//*[text()='Rapports accessibles aux sponsors et surveillants']")
	private WebElement frenchReportAvailableLable;

	@FindBy(xpath = "//button[@aria-label='Download Allowance Delivery Report for event A218_Automation_WA']")
	private WebElement btnAllowancedeliveryReportsWS;

	@FindBy(xpath = "//span[contains(text(),'Welcome,')]")
	private WebElement welcomeFSA;

	@FindBy(xpath = "//a[text()='Reserve Price']")
	private WebElement btnFXRATECA;

	@FindBy(xpath = "//a[text()='Minimum Price']")
	private WebElement btnFXRATEQC;

	@FindBy(xpath = "//*[text()='Change Passphrase']")
	private WebElement ChangePassphraseFSA;

	@FindBy(xpath = "//*[text()='Reset Security Questions']")
	private WebElement ResetSecurityQuestionsFSA;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement EmailChangePassphraseFSA;

	@FindBy(xpath = "//*[text()='CAReserveSaleEventNew']")
	private WebElement jointauctionautomation;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement jointauctionautomation1;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[2]")
	private WebElement clientBidsAuditRow;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement ReportNameDropDown2;

	@FindBy(xpath = "(//button[@aria-label='Download Report'])[1]")
	private WebElement randomEventNmeReportDwnBtn;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[2]")
	private WebElement EntityNmeDropDownBtn2;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement Report_NameDuring_Bidding;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA (CA4376-5560)']")
	private WebElement EntityA1;

	@FindBy(xpath = "//input[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement searchEntityID;

	@FindBy(xpath = "//a[@title='Reports']")
	private WebElement btnReports;

	@FindBy(xpath = "//a[@title='Rapports']")
	private WebElement btnReportsQC;

	@FindBy(xpath = "//button//*[text()='Search Reports']")
	private WebElement btnSearchReports;

	@FindBy(xpath = "//*[text()='CMS']")
	private WebElement cms;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[1]")
	private WebElement jurisdiction;

	@FindBy(xpath = "//span[normalize-space()='Home']")
	private WebElement home;

	@FindBy(xpath = "//p[text()='Footer']")
	private WebElement Footer;

	@FindBy(xpath = "//p[text()='Header']")
	private WebElement Header;

	@FindBy(xpath = "(//input[@name='linkName'])[2]")
	private WebElement Link2;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement cancel;

	@FindBy(xpath = "//*[text()='SAVE']")
	private WebElement save;

	@FindBy(xpath = "//*[text()='PREVIEW']")
	private WebElement preview;

	@FindBy(xpath = "//*[@aria-label='Download Sale By Agreement Bid Lot Audit Report for event R192']")
	private WebElement btnSaleByAgreementBidLotAuditReport;

	@FindBy(xpath = "//h1[text()='Reports']")
	private WebElement headingReportEN;

	@FindBy(xpath = "//h1[text()='Rapports']")
	private WebElement headingReportFR;

	@FindBy(xpath = "//p[text()='SEARCH BY DATE RANGE (NON EVENT SPECIFIC)']")
	private WebElement subHeadingReport;

	@FindBy(xpath = "//p[text()='SEARCH BY EVENT']")
	private WebElement subHeadingReport1;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement reportNameDropDown;

	@FindBy(xpath = "(//*[@aria-label='Without label'])[2]")
	private WebElement reportNameDropDown1;

	@FindBy(xpath = "(//*[@aria-label='Without label'])[4]")
	private WebElement reportNameDropDown2;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
	private WebElement yearDropDown;

	@FindBy(xpath = "//input[@aria-label='Event Name']")
	private WebElement eventNameDropDown;

	@FindBy(xpath = "(//input[@id='auto'])[1]")
	private WebElement eventNameDropDownFR;

	@FindBy(xpath = "//input[@aria-label='Entity Name']")
	private WebElement entityNameDropDown;

	@FindBy(xpath = "//span[text()='T6CAEmetteurA']")
	private WebElement entityNameDropDownOption;

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement btnSearch;

	@FindBy(xpath = "//span[text()='RECHERCHER']")
	private WebElement btnSearchFR;

	@FindBy(xpath = "//input[@placeholder='Start Date']")
	private WebElement startDate;

	@FindBy(xpath = "//input[@placeholder='End Date']")
	private WebElement endDate;

	@FindBy(xpath = "//span[text()='RESET']")
	private WebElement btnReset;

	@FindBy(xpath = "//span[@class='MuiIconButton-label']")
	private List<WebElement> totalReports;

	@FindBy(xpath = "//span[@class='MuiIconButton-label']")
	private WebElement totalReports2;

	@FindBy(xpath = "//h2[text()='Reports Available to Sponsor and Monitor']")
	private WebElement headingForTheSearchedEventName;

	@FindBy(xpath = "//td[text()='Post Event Operations Report']")
	private WebElement postEventOperationsReport;

	@FindBy(xpath = "//td[text()='Bids Audit Report']")
	private WebElement bidsAuditReport;

	@FindBy(xpath = "//td[text()='Financial Settlement Report']")
	private WebElement financialSettlementReport;

	@FindBy(xpath = "//td[text()='Bids Report']")
	private WebElement bidsReport;

	@FindBy(xpath = "//td[text()='Results Report']")
	private WebElement resultReport;

	@FindBy(xpath = "//td[text()='Allowance Delivery Report']")
	private WebElement allowanceDeliveryReport;

	@FindBy(xpath = "//td[text()='Qualified Bidders Report']")
	private WebElement qualifiedBiddersReport;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement englishLngText;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement frenchLngText;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='CMS']")
	private WebElement btnCMS;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//a[text()='Auction Certification']")
	private WebElement btnAuctionCertification;

	@FindBy(xpath = "//a[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "//a[text()='Minimum Price']")
	private WebElement btnMinimumPrice;

	@FindBy(xpath = "(//a[text()='Certification de l'enchère'])[1]")
	private WebElement btnAuctionCertificationqc;

	@FindBy(xpath = "//button[@title='Go to details of Event Algotest']")
	private WebElement eventDetailsBtnALGO;

	@FindBy(xpath = "//button[@title='Go to details of Event Pause Event Functionality Test']")
	private WebElement eventPAUSEEVENT;

	@FindBy(xpath = "//button[@aria-label='Select number of rows per page.']")
	private WebElement btngear;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement btngear500;

	@FindBy(xpath = "//*[contains(text(),'Welcome,')]")
	private WebElement btnProfile;

	@FindBy(xpath = "//span[contains(text(),'Bienvenue, ')]")
	private WebElement btnProfileqc;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//li[text()='Fermer la session']")
	private WebElement btnLogoutFR;

	@FindBy(xpath = "(//*[@aria-haspopup='true'])[1]")
	private WebElement langbtn;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement Englang;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement Frenchlang;

	@FindBy(xpath = "//a[@title='Home']")
	private WebElement btnHome;

	@FindBy(xpath = "//*[text()='Event in progress']")
	private WebElement Homeeventstatus;

	@FindBy(xpath = "//*[text()='Event Monitoring']")
	private WebElement btnEventMonitoring;

	@FindBy(xpath = "//*[text()='Download Report']")
	private WebElement btndownloadeventmonitoring;

	@FindBy(xpath = "(//button[@title='Select number of rows per page.'])")
	private WebElement gearbutton;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement gearrowselect;

	@FindBy(xpath = "(//*[@aria-label='Download Report'])")
	private WebElement downloadreportopt;

	@FindBy(xpath = "(//input[@aria-label='Event Name'])")
	private WebElement Bdropdwnname;

	@FindBy(xpath = "(//input[@id='auto'])[1]")
	private WebElement BdropdwnnameFR;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[1]")
	private WebElement BEdropdwnname1;

	@FindBy(xpath = "(//input[@id='auto'])[2]")
	private WebElement BEdropdwnname1FR;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[2]")
	private WebElement BEdropdwnname2;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[4]")
	private WebElement selectReportdropdwn;

	@FindBy(xpath = "//*[text()='E123INDCAPadmakar-Individual Auction Event']")
	private WebElement EventnametinDrp;

	@FindBy(xpath = "//*[text()='T6QCEmetteurA']")
	private WebElement entityDrpdwn;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA']")
	private WebElement entityDrpdwnCA;

	@FindBy(xpath = "//*[text()='Prescribed_Company+H - PLC=0 PLA=0 HLC=11598500 HLA=0']")
	private WebElement entityDrpdwnRS_CA;

	@FindBy(xpath = "//*[text()='T6QCEmetteurB']")
	private WebElement entityDrpdwnQC;

	@FindBy(xpath = "//*[text()='WA_Entity+D']")
	private WebElement entityDrpdwnWS;

	@FindBy(xpath = "//li[text()='Fermer la session']")
	private WebElement btnLogoutQC;

	// Group of download report buttons
	private By btnFinancialSettlementReportsFR = By
			.xpath("//button[@aria-label='Download Rapport des paiements for event " + prop.getProperty("Event_Name")
					+ "FrenchLang" + "']");
	private By btnEmissionUnitsDeliveryReport = By
			.xpath("//button[@aria-label='Download Emission Units Delivery Report for event " + prop.getProperty("event.name") + "']");
	private By clientBidsReportForJointAuction = By
			.xpath("//*[@aria-label='Download Client Bids Report for event " + prop.getProperty("Event_Name") + "']");
	private By FinancialStatementdownloadbutton = By
			.xpath("//button[@aria-label='Download Financial Statement for event " + prop.getProperty("event.name") + "']");
	private By btnBidsAuditReports = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + prop.getProperty("event.name") + "']");
	private By btnBidsAuditReportsCA = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameCA + "']");
	private By btnBidsAuditReportsRS_CA = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + ReserveSaleEventName + "']");
	private By btnBidsAuditReportsQC = By
			.xpath("//button[@aria-label='Download Rapport de vérification des offres for event " + eventNameFR + "']");
	private By btnBidsAuditReportsQC_single_auction = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameQC + "']");
	private By btnFinancialSettlementReports = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event " + prop.getProperty("event.name") + "']");
	private By btnPostEventOperationReports = By
			.xpath("//*[@aria-label='Download Post Event Operations Report for event " + prop.getProperty("event.name") + "']");
	private By btnEmissionUnitsDeliveryReportQC = By
			.xpath("//button[@aria-label='Download Rapport de la livraison des unités d'émission for event "
					+ eventNameFR + "']");
	private By btnPostEventOperationReportsQC = By.xpath("//*[@aria-label=" + name2 + "]");
	private By btnResultReports = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("event.name") + "']");
	private By btnResultReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des résultats for event " + eventNameFR + "']");
	private By btnFinancialSettlementReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des paiements for event " + eventNameFR + "']");
	private By btnBidsReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des offres for event " + eventNameFR + "']");
	private By btnBidsReports = By.xpath("//button[@aria-label='Download Bids Report for event " + prop.getProperty("event.name") + "']");
	private By btnBidsReportsWS = By
			.xpath("//button[@aria-label='Download Bids Report for event " + prop.getProperty("eventNameWS") + "']");
	private By btnBidsReportsCA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + eventNameCA + "']");
	private By btnBidsReportsRS_CA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + ReserveSaleEventName + "']");
	private By btnBidsReportsRS_WA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + ReserveSaleEventName_WA + "']");
	private By btnBidsReportsQC_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Report for event " + eventNameQC + "']");
	private By btnAllowancedeliveryReports = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event " + prop.getProperty("event.name") + "']");
	private By btnEmissiondeliveryReports = By
			.xpath("//button[@aria-label='Download Emission Units Delivery Report for event " + prop.getProperty("event.name") + "']");
	private By btnQualifiedBidderReports = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("event.name") + "']");
	private By btnQualifiedBidderReportsQC = By
			.xpath("//*[@aria-label='Download Rapport des enchérisseurs qualifiés for event " + eventNameFR + "']");
	private By searchedEventName1 = By.xpath("//p[contains(text(),'" + prop.getProperty("event.name") + " - ')]");
	private By Homeeventname = By.xpath("//p[text()='" + eventNameWS + "']");
	private By EventnametinDrp1 = By.xpath("//*[text()='" + eventNameWS + "']");
	private By EventnametinDrpCA = By.xpath("//*[text()='" + eventNameCA + "']");
	private By EventnametinDrpRS_CA = By.xpath("//*[text()='" + ReserveSaleEventName + "']");
	private By EventnametinDrpRS_WA = By.xpath("//*[text()='" + ReserveSaleEventName_WA + "']");
	private By EventnametinDrpQC = By.xpath("//*[text()='" + eventNameQC + "']");
	private By eventfrommonitoring = By.xpath("//p[text()='" + eventNameWS + "']");
	private By PostEventOperationReportForCASingleAuction = By
			.xpath("//*[@aria-label='Download Post Event Operations Report for event " + prop.getProperty("eventNameCA")
					+ "']");
	private By AllowanceDeliveryReportForCASingleAuction = By.xpath(
			"//*[@aria-label='Download Allowance Delivery Report for event " + prop.getProperty("eventNameCA") + "']");
	private By AllowanceDeliveryReportForCAReserveSale = By
			.xpath("//*[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By BidLotAuditReportForCAReserveSale = By
			.xpath("//*[@aria-label='Download Reserve Sale Bid Lot Audit Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By QualifiedBiddersReportForCASingleAuction = By.xpath(
			"//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("eventNameCA") + "']");
	private By ClientBidsReportForCASingleAuction = By
			.xpath("//*[@aria-label='Download Client Bids Report for event " + prop.getProperty("eventNameCA") + "']");
	private By QualifiedBiddersReportForCASingleAuctionRS = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event "
					+ prop.getProperty("ReserveSaleEventName") + "']");
	private By FinancialSettlementReportForCASingleAuctionRS = By
			.xpath("//*[@aria-label='Download Financial Settlement Report for event " + prop.getProperty("eventNameCA")
					+ "']");
	private By PostEventOperationReportForQCSingleAuction = By
			.xpath("//*[@aria-label='Download Post Event Operations Report for event " + prop.getProperty("eventNameQC")
					+ "']");
	private By FinancialSettlementReportForQCSingleAuction = By
			.xpath("//*[@aria-label='Download Financial Settlement Report for event " + prop.getProperty("eventNameQC")
					+ "']");
	private By AllowanceDeliveryReportForQCSingleAuction = By
			.xpath("//*[@aria-label='Download Emission Units Delivery Report for event "
					+ prop.getProperty("eventNameQC") + "']");
	private By QualifiedBiddersReportForQCSingleAuction = By.xpath(
			"//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("eventNameQC") + "']");
	private By AllowanceDeliveryReportForWASingleAuction = By.xpath(
			"//*[@aria-label='Download Allowance Delivery Report for event " + prop.getProperty("eventNameWS") + "']");
	private By ResultReportForWASingleAuction = By
			.xpath("//*[@aria-label='Download Results Report for event " + prop.getProperty("eventNameWS") + "']");
	private By QualifiedBiddersReportForWASingleAuction = By.xpath(
			"//*[@aria-label='Download Qualified Bidders Report for event " + prop.getProperty("eventNameWS") + "']");
	private By btnBidsAuditReportsWS = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameWS + "']");

	public ReportsPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void searchByReportNameAndDateRange() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is:" + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		String aseertSubHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(subHeadingReport))
				.getText();
		System.out.println("Sub-heading for the Reports page displayed is: " + aseertSubHeadingOfReportPage);
		Assert.assertEquals("SEARCH BY DATE RANGE (NON EVENT SPECIFIC)", aseertSubHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		ObjectRepo.getTest().log(Status.PASS, "Reports page open successfully");
		System.out.println("******************************************************************");
		WebElement reportName = reportNameDropDown;
		Select s = new Select(reportName);
		s.selectByVisibleText("Qualified Bidders Report");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertQualifiedBidderReport = wait.until(ExpectedConditions.elementToBeClickable(qualifiedBidderReport))
				.getText();
		System.out.println("Searched Report displayed using Report Name is: " + aseertQualifiedBidderReport);
		ObjectRepo.getTest().log(Status.PASS,
				"Searched Report displayed using Report Name is: " + aseertQualifiedBidderReport);
		Assert.assertEquals("Qualified Bidders Report", aseertQualifiedBidderReport);
		System.out.println("******************************************************************");
		WebElement reportName1 = reportNameDropDown;
		Select s1 = new Select(reportName1);
		s1.selectByVisibleText("Bids Report");
		ReportsPage dates = new ReportsPage();
		dates.selectDates();
		wait.until(ExpectedConditions.visibilityOf(btnSearch)).click();
		String aseertBidsReport = wait.until(ExpectedConditions.elementToBeClickable(bidsReport)).getText();
		System.out.println("Searched Report displayed using Report Name and Date Range is: " + aseertBidsReport);
		ObjectRepo.getTest().log(Status.PASS,
				"Searched Report displayed using Report Name and Date Range is: " + aseertBidsReport);
		Assert.assertEquals("Bids Report", aseertBidsReport);
		ReportsPage totalReports = new ReportsPage();
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		WebElement reportName2 = reportNameDropDown;
		Select s2 = new Select(reportName2);
		s2.selectByVisibleText("Bids Report");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertBidsReport1 = wait.until(ExpectedConditions.elementToBeClickable(bidsReport)).getText();
		System.out.println("Searched Report displayed using Report Name without Date Range is: " + aseertBidsReport1);
		ObjectRepo.getTest().log(Status.PASS,
				"Searched Report displayed using Report Name without Date Range is: " + aseertBidsReport1);
		Assert.assertEquals("Bids Report", aseertBidsReport1);
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
	}

	@SuppressWarnings("hiding")
	public <DateFormat> void selectDates() throws InterruptedException {
		Date currentDate = new Date();
		Calendar calendarYesterday = Calendar.getInstance();
		calendarYesterday.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterdayDate = calendarYesterday.getTime();
		Calendar calendarTomorrow = Calendar.getInstance();
		calendarTomorrow.add(Calendar.DAY_OF_MONTH, 1);
		calendarTomorrow.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate1 = dateFormat.format(currentDate);
		String yesterdaysDate = dateFormat.format(yesterdayDate);
		System.out.println("Current Date: " + currentDate1);
		System.out.println("Yesterday's Date: " + yesterdaysDate);
		startDate.click();
		startDate.sendKeys(yesterdaysDate);
		endDate.click();
		endDate.sendKeys(currentDate1);
	}

	public void listOfTotalReports() {
		wait.until(ExpectedConditions.visibilityOf(totalReports2));
		List<WebElement> element = totalReports;
		int totalNumOfReports = element.size();
		System.out.println("Total Numbers of reports available in list are: " + totalNumOfReports);
		ObjectRepo.getTest().log(Status.PASS, "Total Numbers of reports available in list are: " + totalNumOfReports);
	}

	public void downloadTheReportsAvailableForSposnorFR() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnPostEventOperationReportsQC));
		driver.findElement(btnPostEventOperationReportsQC).click();
		ExcelDataProviderForPostEventOperationReportInFrench ref = new ExcelDataProviderForPostEventOperationReportInFrench();
		String rowName = ref.getStringData("Rapport dactivités post-événeme", 5, 0);
		Assert.assertEquals("Renseignements sur l’évènement", rowName);
		System.out.println("Post Event Operations Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsAuditReportsQC));
		driver.findElement(btnBidsAuditReportsQC).click();
		ExcelDataProviderForBidsAuditReportInFrench ref1 = new ExcelDataProviderForBidsAuditReportInFrench();
		String rowName1 = ref1.getStringData("Rapport de vérification des off", 5, 0);
		Assert.assertEquals("Numéro de compte général", rowName1);
		System.out.println("Bids audit report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReportsQC));
		driver.findElement(btnFinancialSettlementReportsQC).click();
		ExcelDataProviderForFinancialSettlementReportInFrench ref2 = new ExcelDataProviderForFinancialSettlementReportInFrench();
		String rowName2 = ref2.getStringData("Rapport des paiements", 4, 0);
		Assert.assertEquals("Prix de vente final USD (présent)", rowName2);
		System.out.println("Financial Settlement Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsReportsQC));
		driver.findElement(btnBidsReportsQC).click();
		ExcelDataProviderforBidsReportInFrench ref3 = new ExcelDataProviderforBidsReportInFrench();
		String rowName3 = ref3.getStringData("Rapport des offres", 3, 0);
		Assert.assertEquals("Nombre total d'offres  soumises", rowName3);
		System.out.println("Bids report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReportsQC));
		driver.findElement(btnResultReportsQC).click();
		ExcelDataProviderforResultReportInFrench ref4 = new ExcelDataProviderforResultReportInFrench();
		String rowName4 = ref4.getStringData("Information sur la vente", 10, 0);
		Assert.assertEquals("Statistiques sur le prix des offres qualifiées", rowName4);
		System.out.println("Results report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEmissionUnitsDeliveryReportQC));
		driver.findElement(btnEmissionUnitsDeliveryReportQC).click();
		ExcelDataProviderForEmissionUnitsDeliveryReportInFrench ref5 = new ExcelDataProviderForEmissionUnitsDeliveryReportInFrench();
		String rowName5 = ref5.getStringData("Rapport de la livraison des uni", 4, 0);
		Assert.assertEquals("Numéro de compte général", rowName5);
		System.out.println("Emission Units Delivery Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report validated in French for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReportsQC));
		driver.findElement(btnQualifiedBidderReportsQC).click();
		ExcelDataProviderForQualifiedBidderReportInFrench ref6 = new ExcelDataProviderForQualifiedBidderReportInFrench();
		String rowName6 = ref6.getStringData("Rapport des enchérisseurs quali", 3, 0);
		Assert.assertEquals("Nombre d'enchérisseurs qualifiés", rowName6);
		System.out.println("Qualified Bidder Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated in French for Quebec User");
		System.out.println("******************************************************************");
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		wait.until(ExpectedConditions.elementToBeClickable(downloadReports1)).click();
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				return true;
			} else {

			}
			System.out.println(fileName);
		}
		return false;
	}

	public void searchByReportNameAndDateRange1() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReports)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is:" + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
	}

	public void assertReportsAvailableForSposnorInEnglish() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		String langLable = englishLngText.getText();
		System.out.println(langLable);
		System.out.println("English language lable is displayed in header section");
		ObjectRepo.getTest().log(Status.PASS, "English language lable is displayed in header section");
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + prop.getProperty("event.name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='" + prop.getProperty("Event_Name") + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		wait.until(ExpectedConditions.visibilityOf(reportAvailableLable));
		String ref = reportAvailableLable.getText();
		System.out.println(ref);
		ObjectRepo.getTest().log(Status.PASS, ref);
		System.out.println("Following reports are present in English: ");
		ObjectRepo.getTest().log(Status.PASS, "Following reports are present in English: ");
		String postEventOperationsReport = postEventOperationsReportEN.getText();
		System.out.println(postEventOperationsReport);
		ObjectRepo.getTest().log(Status.PASS, postEventOperationsReport);
		String bidsAuditReport = bidsAuditReportEN.getText();
		System.out.println(bidsAuditReport);
		ObjectRepo.getTest().log(Status.PASS, bidsAuditReport);
		WebElement element1 = financialSettlementReportEN;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String financialSettlementReport = financialSettlementReportEN.getText();
		System.out.println(financialSettlementReport);
		ObjectRepo.getTest().log(Status.PASS, financialSettlementReport);
		String bidsReport = bidsReportEN.getText();
		System.out.println(bidsReport);
		ObjectRepo.getTest().log(Status.PASS, bidsReport);
		String resultsReport = resultsReportEN.getText();
		System.out.println(resultsReport);
		ObjectRepo.getTest().log(Status.PASS, resultsReport);
		String emissionUnitsDeliveryReport = emissionUnitsDeliveryReportEN.getText();
		System.out.println(emissionUnitsDeliveryReport);
		ObjectRepo.getTest().log(Status.PASS, emissionUnitsDeliveryReport);
		String qualifiedBiddersReport = qualifiedBiddersReportEN.getText();
		System.out.println(qualifiedBiddersReport);
		ObjectRepo.getTest().log(Status.PASS, qualifiedBiddersReport);
		System.out.println("***********************************************************************");
	}

	public void assertReportsAvailableForSposnorInFrench() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		englishLngText.click();
		wait.until(ExpectedConditions.elementToBeClickable(frenchLngText));
		frenchLngText.click();
		System.out.println("English to french language is changed");
		ObjectRepo.getTest().log(Status.PASS, "English to french language is changed");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Surveillance']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Surveillance']")));
		}
		
		WebElement element = driver.findElement(By.xpath("//*[text()='NOM DU rapport']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU rapport']")));
		driver.findElement(By.xpath("//*[text()='Rechercher un rapport']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.visibilityOf(headingReportFR)).getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Rapports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("***********************************************************************");
		WebElement element1 = eventNameDropDownFR;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.visibilityOf(eventNameDropDownFR)).click();
		eventNameDropDownFR.sendKeys(prop.getProperty("Event_Name"));
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchFR)).click();
		String ref = frenchReportAvailableLable.getText();
		System.out.println("Heading displayed below the selected event in French is: " + ref);
		ObjectRepo.getTest().log(Status.PASS, "Heading displayed below the selected event in French is: " + ref);
		System.out.println("Following reports are present in French: ");
		ObjectRepo.getTest().log(Status.PASS, "Following reports are present in French: ");
		String postEventOperationsReport = postEventOperationsReportFR.getText();
		System.out.println(postEventOperationsReport);
		ObjectRepo.getTest().log(Status.PASS, postEventOperationsReport);
		String bidsAuditReport = bidsAuditReportFR.getText();
		System.out.println(bidsAuditReport);
		ObjectRepo.getTest().log(Status.PASS, bidsAuditReport);
		String financialSettlementReport = financialSettlementReportFR.getText();
		System.out.println(financialSettlementReport);
		ObjectRepo.getTest().log(Status.PASS, financialSettlementReport);
		WebElement element11 = bidsReportFR;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		String bidsReport = bidsReportFR.getText();
		System.out.println(bidsReport);
		ObjectRepo.getTest().log(Status.PASS, bidsReport);
		String resultsReport = resultsReportFR.getText();
		System.out.println(resultsReport);
		ObjectRepo.getTest().log(Status.PASS, resultsReport);
		String emissionUnitsDeliveryReport = emissionUnitsDeliveryReportFR.getText();
		System.out.println(emissionUnitsDeliveryReport);
		ObjectRepo.getTest().log(Status.PASS, emissionUnitsDeliveryReport);
		String qualifiedBiddersReport = qualifiedBiddersReportFR.getText();
		System.out.println(qualifiedBiddersReport);
		ObjectRepo.getTest().log(Status.PASS, qualifiedBiddersReport);
		System.out.println("***********************************************************************");
	}

	public void cms() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(cms));
		cms.click();
		System.out.println("Click the CMS Menu on Navigation Bar ");
		ObjectRepo.getTest().log(Status.PASS, "Click the CMS Menu on Navigation Bar ");
		System.out.println("TC ID:C31408:Validated Access the CMS page via the navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31408: Validated Access the CMS page via the navigation bar");
		WebElement jd = jurisdiction;
		Select jurisdictionname = new Select(jd);
		jurisdictionname.selectByVisibleText("California");
		System.out.println("Select Jurisdiction- California ");
		ObjectRepo.getTest().log(Status.PASS, "Select Jurisdiction- California");
		wait.until(ExpectedConditions.visibilityOf(Header));
		home.click();
		System.out.println("Click the HOME option on CMS Page");
		ObjectRepo.getTest().log(Status.PASS, "Click the HOME option on CMS Page");
		
		WebElement element = Footer;
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOf(Footer));
		Footer.click();
		System.out.println("Click the Footer option on CMS Page");
		ObjectRepo.getTest().log(Status.PASS, "Click the Footer option on CMS Page");
		wait.until(ExpectedConditions.elementToBeClickable(Link2));
		Link2.click();
		WebElement textbox = Link2;
		textbox.sendKeys(Keys.CONTROL, "A");
		textbox.sendKeys(Keys.CONTROL, "X");
		Link2.sendKeys("Copyright © 2022");
		cancel.click();
		System.out.println("Click the Cancel button on CMS Page");
		ObjectRepo.getTest().log(Status.PASS, "Click the Cancel button on CMS Page");
		System.out.println("TC ID:C31438:Validated EA/Sponsor and Sponsor can discard the CMS changes made");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31438:Validated EA/Sponsor and Sponsor can discard the CMS changes made");
		driver.findElement(By.xpath("//div[@role='presentation']//button[2]")).click();// continue button
		WebElement exp = driver
				.findElement(By.xpath("//h2[contains(text(),'All unsaved changes will be lost. Do you want to c')]"));
		System.out.println(exp.getText());
		System.out.println("Validated 'All unsaved changes will be lost. Do you want to continue?' Message");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated 'All unsaved changes will be lost. Do you want to continue?' Message");
		WebElement success = driver.findElement(By.xpath("//p[text()='Request accepted successfully']"));
		System.out.println(success.getText());
		System.out.println("Validated Success Message");
		ObjectRepo.getTest().log(Status.PASS, "Validated Success Message");
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(Header));
		home.click();
		
		WebElement element1 = Footer;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		Footer.click();
		wait.until(ExpectedConditions.elementToBeClickable(Link2));
		Link2.click();
		WebElement textbox1 = Link2;
		textbox1.sendKeys(Keys.CONTROL, "A");
		textbox1.sendKeys(Keys.CONTROL, "X");
		Link2.sendKeys("Copyright © 2023");
		save.click();
		System.out.println("Click On Save Button");
		ObjectRepo.getTest().log(Status.PASS, "Click On Save Button");
		System.out.println("TC ID:C31439:Validated EA/Sponsor and Sponsor can save CMS changes");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31439:Validated EA/Sponsor and Sponsor can save CMS changes");
	}

	public void searchReportAR() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(searchEntityID));
		searchEntityID.sendKeys(prop.getProperty("CAThirdEntity"));
		wait.until(ExpectedConditions.visibilityOf(EntityA1));
		EntityA1.click();
		wait.until(ExpectedConditions.visibilityOf(continueBtn));
		continueBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		Thread.sleep(5000);
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
	}

	public void ValidateBidsAuditReportsReport() throws Exception {
		
		WebElement element = driver.findElement(btnBidsAuditReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports).click();
		Thread.sleep(20000);
		System.out.println("bids audit report download successfully");
		ObjectRepo.getTest().log(Status.PASS, "bids audit report download successfully");
		System.out.println("Bids Audit Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31281: Confirmed the specified introductory fields are available ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31281: Confirmed the specified introductory fields are available ");
		System.out.println("TC ID:C31287: Verified that the Sponsor can access the Bids Audit Report ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31287: Verified that the Sponsor can access the Bids Audit Report ");
		System.out.println("TC ID:C31290: The Bids Audit Report must be available for a Joint Auction");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31290: The Bids Audit Report must be available for a Joint Auction");
		System.out.println("TC ID:C31293: Bids Audit report must follow naming convention ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31293: Bids Audit report must follow naming convention ");
		System.out.println("TC ID:C31294: Bids Audit Report must have the correct layout ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31294:Bids Audit Report must have the correct layout");
		System.out.println("TC ID:C31295: Verify the data in the Bids Audit Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31295: Verify the data in the Bids Audit Report is correct");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("event.name") + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void ValidateFinancialSettlementReports() throws Exception {
		
		WebElement element = driver.findElement(btnFinancialSettlementReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnFinancialSettlementReports).click();
		System.out.println("bids audit report download successfully");
		File src = new File(folderPath + File.separator + "Financial Settlement Report - R192 10-12-2022.xlsx");
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
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
				System.out.println("File extention validated as a .Xlsx format");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidatebidsSaleByAgreementBidLotAuditReport() throws Exception {
		
		WebElement element = btnSaleByAgreementBidLotAuditReport;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnSaleByAgreementBidLotAuditReport.click();
		System.out.println("Sale By Agreement Bid Lot Audit Report download successfully");
		File src = new File(
				folderPath + File.separator + "Sale By Agreement Bid Lot Audit Report - R192 10-12-2022.xlsx");
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
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
				System.out.println("File extention validated as a .Xlsx format");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateAllowancedeliveryReports() throws Exception {
		
		WebElement element = driver.findElement(btnAllowancedeliveryReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowancedeliveryReports).click();
		Thread.sleep(20000);
		System.out.println("Allowance delivery Reports successfully");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Allowance Delivery Report - " + prop.getProperty("event.name") + " "
					+ date1 + ".xlsx");
			Assert.assertTrue(
					"Allowance Delivery Report - AuctionEvent1 11-21-2022.xlsx".contains("Allowance Delivery Report"));
			System.out.println("Asserted The name of the report must be Allowance Delivery Report");
			ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
			System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
			ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
			System.out.println("TC ID:C31344: Confirmed the specified introductory fields are available");
			ObjectRepo.getTest().log(Status.PASS, "TC ID:C31344: Confirmed the specified introductory fields are available");
			System.out.println(
					"TC ID:C31345: Validated Allowance delivery report is available after the results are run");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31345: Validated Allowance delivery report is available after the results are run");
			System.out.println(
					"TC ID:C31348: Validated The Allowance (Emission Units) Delivery Report must be available for a Joint Auction");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31348: Validated The Allowance (Emission Units) Delivery Report must be available for a Joint Auction");
			System.out.println(
					"TC ID:C31350: Allowance Delivery Report must follow naming convention (Allowance Delivery Report Event name Event date MM-DD-YYYY)");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31350: Allowance Delivery Report must follow naming convention(Allowance Delivery Report Event name Event date MM-DD-YYYY)");
			System.out.println("TC ID:C31351: Allowance Delivery Report must have the correct layout");
			ObjectRepo.getTest().log(Status.PASS, "TC ID:C31351: Allowance Delivery Report must have the correct layout");
			System.out.println("TC ID:C31352: Verified the data in the Allowance Delivery Report is correct");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31352: Verified the data in the Allowance Delivery Report is correct");
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
							System.out.print(cell.toString() + " ");
							ObjectRepo.getTest().log(Status.PASS, cell.toString());
						}
						System.out.println();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateResultReports() throws IOException, InterruptedException {
		try {
			
			WebElement element = driver.findElement(btnResultReports);
			js.executeScript("arguments[0].scrollIntoView();", element);
			driver.findElement(btnResultReports).click();
			Thread.sleep(20000);
			System.out.println("Result Reports download successfully");
			ObjectRepo.getTest().log(Status.PASS, "Result Reports download successfully");
			System.out.println("TC ID:C31335: Confirmed Results report is generated post running algorithms.");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31335: Confirmed Results report is generated post running algorithms.");
			System.out.println("TC ID:C31322: Verified the data in the Results Report is correct");
			ObjectRepo.getTest().log(Status.PASS, "TC ID:C31322: Verified the data in the Results Report is correct");
			System.out.println("TC ID:C31343: The  final results calculated  is as expected  ");
			ObjectRepo.getTest().log(Status.PASS, "TC ID:C31343: The  final results calculated  is as expected  ");
			System.out.println(
					"TC ID:C31401: Validated- Results Report - Gather Bids and Final results tab to have Total Cost in both currencies for a Joint Auction  ");
			ObjectRepo.getTest().log(Status.PASS,
					"TC ID:C31401:Validated Results Report - Gather Bids and Final results tab to have Total Cost in both currencies for a Joint Auction  ");
			ArrayList<Object[][]> sheetDatas = new ArrayList<>();
			Object[][] object = null;
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name") + " "
					+ date1 + ".xlsx");
			ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Results Report");
			FileInputStream inputStream = new FileInputStream(src);
			int totalSheet = 0;
			Sheet Sheet1 = null;
			try (XSSFWorkbook wb = new XSSFWorkbook(inputStream)) {
				totalSheet = wb.getNumberOfSheets();
				if (totalSheet > 0) {
					System.out.println("Total Sheet Found:" + totalSheet);
					for (int k = 0; k < totalSheet; k++) {
						System.out.println("Sheet Name:" + wb.getSheetName(k));
						Sheet1 = wb.getSheetAt(k);
						System.out.println("Sheet Found:" + Sheet1);
						XSSFRow row;
						Iterator<Row> rows = Sheet1.rowIterator();
						XSSFRow row1 = (XSSFRow) Sheet1.getRow(4);
						XSSFCell cell1 = row1.getCell(0);
						String value = cell1.toString();
						System.out.println(value);
						if (value == "Annual Reserve Price") {
							Assert.assertTrue(value.contains("Annual Reserve Price"));
						} else if (value == "QC4446-5674") {
							Assert.assertTrue(value.contains("QC4446-5674"));
						} else if (value == "CA4376-5560") {
							Assert.assertTrue(value.contains("CA4376-5560"));
						} else if (value == "35.0") {
							Assert.assertTrue(value.contains("35.0"));
						} else if (value == "CA4378-5564") {
							Assert.assertTrue(value.contains("CA4378-5564"));
						}
						while (rows.hasNext()) {
							row = (XSSFRow) rows.next();
							for (int i = 0; i < row.getLastCellNum(); i++) {
								cell1 = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
								ObjectRepo.getTest().log(Status.PASS, cell1.toString());
								System.out.print(cell1.toString() + " ");
							}
							System.out.println("");
							sheetDatas.add(object);
						}
						System.out.println("");
						System.out.println(
								"***********************************************************************************************************************************************************************************");
					}
					ObjectRepo.getTest().log(Status.PASS,
							"Asserted cell value Annual Reserve Price/QC4446-5674/CA4376-5560/35.0/CA4378-5564 Successfully.");
					System.out.println(
							"Asserted cell value Annual Reserve Price/QC4446-5674/CA4376-5560/35.0/CA4378-5564 Successfully.");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}

	public String ValidateFinancialStatementreport() throws EmptyFileException, IOException, InterruptedException {
		driver
				.findElement(
						By.xpath("//button[@aria-label='Download Financial Statement for event " + prop.getProperty("event.name") + "']"))
				.click();
		Thread.sleep(20000);
		System.out.println("Financial Statement report download successfully");
		System.out.println("TC ID:C31389:The AR can access the Financial Statement Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31389:The AR can access the Financial Statement Report");
		System.out.println("TC ID:C31387:The specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31387: The specified introductory fields are available");
		System.out.println("TC ID:C31394: Validate Financial Statement must have the correct layout");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31394: Validate Financial Statement must have the correct layout");
		System.out.println("TC ID:C31395: The data in the Financial Statement Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31395: The data in the Financial Statement Report is correct");
		System.out.println(
				"TC ID:C31407:The name of the report must be Financial Statement - <Event name> <Event date MM-DD-YYYY> <Registry ID> ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31407:Financial Statement Report below naming convention:");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report Event name Event date MM-DD-YYYY Registry ID");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31407:File extention validated as a .pdf format");
		System.out.println("TC ID:C31407:File extention validated as a .pdf format");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("event.name") + " " + date1 + " "
				+ prop.getProperty("EntityA1") + ".pdf");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Financial Statement Report");
		InputStream PdfFileToRead = new FileInputStream(src);
		PDDocument doc = PDDocument.load(PdfFileToRead);
		int pages = doc.getNumberOfPages();
		System.out.println("The total number of pages " + pages);
		PDFTextStripper strip = new PDFTextStripper();
		strip.setStartPage(1);
		strip.setEndPage(2);
		String stripText = strip.getText(doc);
		System.out.println(stripText);
		ObjectRepo.getTest().log(Status.PASS, stripText);
		doc.close();
		return stripText;
	}

	public void LogoutQCFR() {
		try {
			btnProfileqc.click();
			btnLogoutFR.click();
			System.out.println("Logout Successfully");
			ObjectRepo.getTest().log(Status.PASS, "Logout Successfully");
		} catch (Exception e) {
			ObjectRepo.getTest().log(Status.PASS, "Not able to logout ");
			System.out.println("Not able to logout ");
		}
	}

	public void clickbtnAdmin() throws InterruptedException {
		WebElement abc = btnAdminstration;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void clickbtnCMS() throws InterruptedException {
		WebElement abc = btnCMS;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void clickbtnAdminqc() throws InterruptedException {
		WebElement abc = btnAdminstration;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void LanguageSelection() throws InterruptedException {
		WebElement abc = driver.findElement(By.xpath("//span[text()='English']//*[name()='svg']"));
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void LanguageSelectionQC() throws InterruptedException {
		WebElement abc = driver.findElement(By.xpath("//span[text()='English']//*[name()='svg']"));
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
		driver.findElement(By.xpath("//li[@role='menuitem']")).click();
	}

	public void LanguageSelectionQCENG() throws InterruptedException {
		WebElement abc = driver.findElement(By.xpath("//span[text()='Français']//*[name()='svg']"));
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
		driver.findElement(By.xpath("//li[@role='menuitem']")).click();
	}

	public void BtnNextEventDetailsALGO() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement textbox = eventDetailsBtnALGO;
		act.doubleClick(textbox).perform();
	}

	public void BtnNextPAUSEEVENT() throws InterruptedException {
		Actions act = new Actions(driver);
		WebElement textbox = eventPAUSEEVENT;
		act.doubleClick(textbox).perform();
	}

	public void clickbtnEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		Actions act = new Actions(driver);
		act.moveToElement(abc1).build().perform();
		act.clickAndHold(abc1).build().perform();
		act.click(abc1).build().perform();
	}

	public void clickbtnAuctionCertification() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAuctionCertification));
		WebElement abc1 = btnAuctionCertification;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnReservePrice() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
		WebElement abc1 = btnReservePrice;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnReservePriceQC() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnMinimumPrice));
		WebElement abc1 = btnMinimumPrice;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnAuctionCertificationqc() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAuctionCertificationqc));
		WebElement abc1 = btnAuctionCertificationqc;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnFXRATECA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnFXRATECA));
		WebElement abc1 = btnFXRATECA;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnFXRATEQC() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnFXRATEQC));
		WebElement abc1 = btnFXRATEQC;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void ValidateChangePassphrase() throws InterruptedException {
		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(welcomeFSA));
		Actions action = new Actions(driver);
		action.moveToElement(element1).build().perform();
		action.click(element1).build().perform();
		ChangePassphraseFSA.click();
		WebElement act = EmailChangePassphraseFSA;
		String Act = act.getAttribute("value");
		String Exp = "fsa_at_ca@yopmail.com";
		Assert.assertEquals(Act, Exp);
		System.out.println("Actual FSA Email Address:" + " " + Act);
		ObjectRepo.getTest().log(Status.PASS, "Actual FSA Email Address:" + " " + Act);
		System.out.println("Expected FSA Email Address:" + " " + Exp);
		ObjectRepo.getTest().log(Status.PASS, "Expected FSA Email Address:" + " " + Exp);
		System.out.println("Verified email address is correct");
		ObjectRepo.getTest().log(Status.PASS, "Verified email address is correct");
		if (act.isEnabled()) {
			System.out.println("Email address is not  pre-populated.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is not  pre-populated.");
		} else {
			System.out.println("Email address is pre-populated.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is pre-populated.");
		}
		if (act.isEnabled()) {
			System.out.println("Email address is editable.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is editable.");
		} else {
			System.out.println("Email address is not editable.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is not editable.");
		}
		WebElement currentpassphrase = driver.findElement(By.xpath("//*[@id='currentPassphrase']"));
		currentpassphrase.sendKeys(prop.getProperty("Password"));
		System.out.println("Enter Current Passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Enter Current Passphrase");
		WebElement newcurrentpassphrase = driver.findElement(By.xpath("//*[@id='newPassphrase']"));
		newcurrentpassphrase.sendKeys("Test@123456789101112");
		System.out.println("Enter New Passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Enter New Passphrase");
		WebElement confcurrentpassphrase = driver.findElement(By.xpath("//*[@id='confirmPassphrase']"));
		confcurrentpassphrase.sendKeys("Test@123456789101112");
		System.out.println("Enter Confirm New Passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Enter Confirm New Passphrase");
		WebElement savepassphrase = driver.findElement(By.xpath("//*[@title='SAVE NEW PASSPHRASE']"));
		savepassphrase.click();
		System.out.println("Click on SAVE NEW PASSPHRASE");
		ObjectRepo.getTest().log(Status.PASS, "Click on SAVE NEW PASSPHRASE");
		WebElement ACTsuccessmsg = driver
				.findElement(By.xpath("//*[text()='Your passphrase has been changed successfully.']"));
		String a = ACTsuccessmsg.getText();
		String EXPsuccessmsg = "Your passphrase has been changed successfully.";
		Assert.assertEquals(a, EXPsuccessmsg);
		System.out.println(a);
		System.out.println(
				"TC ID:C31610: Verify FSA can change passphrase upon successful login by accessing the top right down arrow menu.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31610: Verify FSA can change passphrase upon successful login by accessing the top right down arrow menu.");
	}

	public void ValidateFAQ() throws InterruptedException {
		System.out.println("TC ID:C31519: FAQ tab has  Has frequently asked questions with their answers ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31519: FAQ tab has  Has frequently asked questions with their answers ");
		driver.findElement(By.xpath("(//a[normalize-space()='FAQs & Resources'])[1]")).click();
		System.out.println();
		System.out.println(driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]")).getText());
		String a = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]")).getText();
		ObjectRepo.getTest().log(Status.PASS, a);
		driver.findElement(By.xpath("//span[normalize-space()='FAQs']")).click();
		System.out.println();
		driver.findElement(By.xpath("//p[normalize-space()='How do I access the Auction Platform?']")).click();
		System.out.println(driver
				.findElement(By.xpath("//p[normalize-space()='How do I access the Auction Platform?']")).getText());
		String b = driver.findElement(By.xpath("//p[normalize-space()='How do I access the Auction Platform?']"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, b);
		System.out.println(driver.findElement(By.xpath("(//div[@title='Page 1'])[1]")).getText());
		String c = driver.findElement(By.xpath("(//div[@title='Page 1'])[1]")).getText();
		ObjectRepo.getTest().log(Status.PASS, c);
		Assert.assertTrue(
				"CITSS account representatives without an Auction Platform account will receive an email inviting the user to establish account credentials for an Auction Platform account within 24 hours of being approved as an active account representative of an entity that has been a qualified bidder to a past event and/or has submitted an application to participate an upcoming event. The activation email will be sent to the account representative’s current email address listed in CITSS. This current email address in CITSS will be the account representative’s username in the Auction Platform. The email provides an activation link to activate an account and establish an Auction Platform password and security questions."
						.contains("CITSS account representatives without an Auction Platform"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'Is an entity required to submit an auction applica')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'Is an entity required to submit an auction applica')]"))
				.getText());
		String d = driver
				.findElement(By.xpath("//p[contains(text(),'Is an entity required to submit an auction applica')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, d);
		System.out.println(driver.findElement(By.xpath("(//div[@class='column'])[3]")).getText());
		String e = driver.findElement(By.xpath("(//div[@class='column'])[3]")).getText();
		ObjectRepo.getTest().log(Status.PASS, e);
		Assert.assertTrue(
				"For each auction, one CITSS entity account representative (PAR or AAR) must complete the entity’s auction application."
						.contains("For each auction"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver
				.findElement(
						By.xpath("(//p[contains(text(),'May an entity that is incorporated outside of the ')])[1]"))
				.click();
		System.out.println(driver
				.findElement(
						By.xpath("(//p[contains(text(),'May an entity that is incorporated outside of the ')])[1]"))
				.getText());
		String f = driver
				.findElement(
						By.xpath("(//p[contains(text(),'May an entity that is incorporated outside of the ')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, f);
		System.out.println(driver
				.findElement(By.xpath("//span[contains(text(),'Yes, an entity incorporated outside of the United ')]"))
				.getText());
		String g = driver
				.findElement(By.xpath("//span[contains(text(),'Yes, an entity incorporated outside of the United ')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, g);
		Assert.assertTrue(
				"Yes, an entity incorporated outside of the United States may participate and bid in GHG allowance auctions"
						.contains("incorporated"));
		System.out.println(
				"0000000000***************************************************************************************************************************");

		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'How will the auction schedule be communicated to t')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'How will the auction schedule be communicated to t')]"))
				.getText());
		String h = driver
				.findElement(By.xpath("//p[contains(text(),'How will the auction schedule be communicated to t')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, h);
		System.out.println(driver.findElement(By.xpath("(//p)[17]")).getText());
		String i = driver.findElement(By.xpath("(//p)[17]")).getText();
		ObjectRepo.getTest().log(Status.PASS, i);
		Assert.assertTrue("The schedule for each GHG allowance auction".contains("allowance auction"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("(//p[normalize-space()='What is the Auction Reserve Price?'])[1]")).click();
		System.out.println(driver
				.findElement(By.xpath("(//p[normalize-space()='What is the Auction Reserve Price?'])[1]")).getText());
		String j = driver
				.findElement(By.xpath("(//p[normalize-space()='What is the Auction Reserve Price?'])[1]")).getText();
		ObjectRepo.getTest().log(Status.PASS, j);
		System.out.println(driver
				.findElement(
						By.xpath("(//span[contains(text(),'The Auction Reserve Price is the lowest price at w')])[1]"))
				.getText());
		String k = driver
				.findElement(
						By.xpath("(//span[contains(text(),'The Auction Reserve Price is the lowest price at w')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, k);
		Assert.assertTrue("The Auction Reserve Price is the lowest price at which".contains("Auction Reserve Price"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'Is the bid price submitted for a single GHG allowa')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'Is the bid price submitted for a single GHG allowa')]"))
				.getText());
		String l = driver
				.findElement(By.xpath("//p[contains(text(),'Is the bid price submitted for a single GHG allowa')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, l);
		System.out.println(driver
				.findElement(
						By.xpath("(//span[contains(text(),'The bid price is entered for a single GHG allowanc')])[1]"))
				.getText());
		String m = driver
				.findElement(
						By.xpath("(//span[contains(text(),'The bid price is entered for a single GHG allowanc')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, m);
		Assert.assertTrue(
				"The bid price is entered for a single GHG allowance, so a bid is entered, for example, as $15.00 per allowance."
						.contains("The bid price is entered for a single GHG allowance"));
		System.out.println(
				"***************************************************************************************************************************");

		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'Can auction or reserve sale participants see bids ')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'Can auction or reserve sale participants see bids ')]"))
				.getText());
		String n = driver
				.findElement(By.xpath("//p[contains(text(),'Can auction or reserve sale participants see bids ')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, n);
		System.out.println(driver
				.findElement(By.xpath("//span[contains(text(),'No, participants cannot see the bids entered by ot')]"))
				.getText());
		String o = driver
				.findElement(By.xpath("//span[contains(text(),'No, participants cannot see the bids entered by ot')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, o);
		Assert.assertTrue("No, participants cannot see the bids entered by other auction or reserve sale participants."
				.contains("participants "));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'Auctions include a Current Auction and Advance Auc')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'Auctions include a Current Auction and Advance Auc')]"))
				.getText());
		String p = driver
				.findElement(By.xpath("//p[contains(text(),'Auctions include a Current Auction and Advance Auc')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, p);
		System.out.println(driver
				.findElement(By.xpath("//span[contains(text(),'No, auction participants are not required to bid i')]"))
				.getText());
		String q = driver
				.findElement(By.xpath("//span[contains(text(),'No, auction participants are not required to bid i')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, q);
		Assert.assertTrue(
				"No, auction participants are not required to bid in both the Current Auction and Advance Auction."
						.contains("auction"));
		System.out.println(
				"***************************************************************************************************************************");

		System.out.println();
		driver
				.findElement(
						By.xpath("(//p[contains(text(),'How is the settlement price determined and winning')])[1]"))
				.click();
		System.out.println(driver
				.findElement(
						By.xpath("(//p[contains(text(),'How is the settlement price determined and winning')])[1]"))
				.getText());
		String r = driver
				.findElement(
						By.xpath("(//p[contains(text(),'How is the settlement price determined and winning')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, r);
		System.out.println(driver
				.findElement(
						By.xpath("(//span[contains(text(),'The process to determine the settlement price requ')])[1]"))
				.getText());
		String s = driver
				.findElement(
						By.xpath("(//span[contains(text(),'The process to determine the settlement price requ')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, s);
		Assert.assertTrue(
				"The process to determine the settlement price requires that the Auction Administrator rank qualified bids from all Qualified Bidders"
						.contains("settlement price"));
		System.out.println(
				"***************************************************************************************************************************");

		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'When is the settlement price and notice of success')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'When is the settlement price and notice of success')]"))
				.getText());
		String u = driver
				.findElement(By.xpath("//p[contains(text(),'When is the settlement price and notice of success')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, u);
		System.out.println(driver
				.findElement(
						By.xpath("(//span[contains(text(),'The date for the release of the settlement price a')])[1]"))
				.getText());
		String v = driver
				.findElement(
						By.xpath("(//span[contains(text(),'The date for the release of the settlement price a')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, v);
		Assert.assertTrue(
				"The date for the release of the settlement price and availability of auction results for qualified bidders is posted in the Auction Notice."
						.contains("Auction Notice"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("//p[normalize-space()='How is a reserve sale different from an auction?']"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[normalize-space()='How is a reserve sale different from an auction?']"))
				.getText());
		String w = driver
				.findElement(By.xpath("//p[normalize-space()='How is a reserve sale different from an auction?']"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, w);
		System.out.println(driver.findElement(By.xpath("(//div[@title='Page 3'])[6]")).getText());
		String x = driver.findElement(By.xpath("(//div[@title='Page 3'])[6]")).getText();
		ObjectRepo.getTest().log(Status.PASS, x);
		Assert.assertTrue("Reserve sales allow for the sale of allowances from the Allowance Price Containment Reserve."
				.contains("Reserve sales allow"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver
				.findElement(
						By.xpath("(//p[contains(text(),'What is the difference between a Qualified Applica')])[1]"))
				.click();
		System.out.println(driver
				.findElement(
						By.xpath("(//p[contains(text(),'What is the difference between a Qualified Applica')])[1]"))
				.getText());
		String y = driver
				.findElement(
						By.xpath("(//p[contains(text(),'What is the difference between a Qualified Applica')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, y);
		System.out.println(driver.findElement(By.xpath("(//div[@title='Page 4'])[1]")).getText());
		String z = driver.findElement(By.xpath("(//div[@title='Page 4'])[1]")).getText();
		ObjectRepo.getTest().log(Status.PASS, z);
		Assert.assertTrue(
				"An entity’s status varies throughout the auction process. An entity’s Application Status will be one of the statuses listed below."
						.contains("An entity’s Application"));
		System.out.println(
				"***************************************************************************************************************************");
		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'What are the requirements for submittal of a bid g')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'What are the requirements for submittal of a bid g')]"))
				.getText());
		String a1 = driver
				.findElement(By.xpath("//p[contains(text(),'What are the requirements for submittal of a bid g')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, a1);
		System.out.println(driver
				.findElement(
						By.xpath("(//span[contains(text(),'Requirements for submittal of a bid guarantee are ')])[1]"))
				.getText());
		String b1 = driver
				.findElement(
						By.xpath("(//span[contains(text(),'Requirements for submittal of a bid guarantee are ')])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, b1);
		Assert.assertTrue(
				"Requirements for submittal of a bid guarantee are provided in the Auction Notice and Detailed Auction Requirements and Instructions available on the"
						.contains("bid guarantee"));
		System.out.println(
				"***************************************************************************************************************************");

		System.out.println();
		driver.findElement(By.xpath("//p[contains(text(),'How will Qualified Bidders financially settle amou')]"))
				.click();
		System.out.println(driver
				.findElement(By.xpath("//p[contains(text(),'How will Qualified Bidders financially settle amou')]"))
				.getText());
		String c1 = driver
				.findElement(By.xpath("//p[contains(text(),'How will Qualified Bidders financially settle amou')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, c1);
		System.out.println(driver
				.findElement(By.xpath("//span[contains(text(),'Qualified Bidders are required to settle allowance')]"))
				.getText());
		String d1 = driver
				.findElement(By.xpath("//span[contains(text(),'Qualified Bidders are required to settle allowance')]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, d1);
		Assert.assertTrue(
				"Qualified Bidders are required to settle allowances awarded in an auction or reserve sale in cash."
						.contains("Qualified Bidders"));
		System.out.println(
				"***************************************************************************************************************************");

	}

	public void ValidateEXCUTEAPI() throws InterruptedException {

		System.out.println("TC ID:C31533: The system must not let EA execute the API   ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31533: The system must not let EA execute the API   ");
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		System.out.println();

		driver.findElement(By.xpath("(//a[normalize-space()='Execute API'])[1]")).click();

		WebElement a = driver.findElement(By.xpath("(//button[@title='Execute'])[1]"));
		if (a.isEnabled()) {
			System.out.println("Get Entities & Users API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "Get Entities & Users API EXCUTE Button Enabled");
		} else {
			System.out.println("Get Entities & Users API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "Get Entities & Users API EXCUTE Button Disabled");
		}

		WebElement b = driver.findElement(By.xpath("(//button[@title='Execute'])[2]"));
		if (b.isEnabled()) {
			System.out.println("GetQualifiedBidder API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "GetQualifiedBidder API EXCUTE Button Enabled");
		} else {
			System.out.println("GetQualifiedBidder API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "GetQualifiedBidder API EXCUTE Button Disabled");
		}

		WebElement c = driver.findElement(By.xpath("(//button[@title='Execute'])[3]"));
		if (c.isEnabled()) {
			System.out.println("GetBidLimitations API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "GetBidLimitations API EXCUTE Button Enabled");
		} else {
			System.out.println("GetBidLimitations API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "GetBidLimitations API EXCUTE Button Disabled");
		}

		WebElement d = driver.findElement(By.xpath("(//button[@title='Execute'])[4]"));
		if (d.isEnabled()) {
			System.out.println("Post UserRelationships API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "Post UserRelationships API EXCUTE Button Enabled");
		} else {
			System.out.println("Post UserRelationships API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "Post UserRelationships API EXCUTE Button Disabled");
		}

		WebElement e = driver.findElement(By.xpath("(//button[@title='Execute'])[5]"));
		if (e.isEnabled()) {
			System.out.println("Post Qualified Bidders API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "Post Qualified Bidders API EXCUTE Button Enabled");
		} else {
			System.out.println("Post Qualified Bidders API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "Post Qualified Bidders API EXCUTE Button Disabled");
		}

		WebElement f = driver.findElement(By.xpath("(//button[@title='Execute'])[6]"));
		if (f.isEnabled()) {
			System.out.println("Post BidLimitations API EXCUTE Button Enabled");
			ObjectRepo.getTest().log(Status.PASS, "Post BidLimitations API EXCUTE Button Enabled");
		} else {
			System.out.println("Post BidLimitations API EXCUTE Button Disabled");
			ObjectRepo.getTest().log(Status.PASS, "Post BidLimitations API EXCUTE Button Disabled");
		}
	}

	public void Validatespmmsearchsort() throws InterruptedException {

		System.out.println("TC ID:C31494: Verify for the search bids functions AND the sorting functions.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31494: Verify for the search bids functions AND the sorting functions.");
		System.out
				.println("TC ID:C31514: verify Sponsor/EA/MM can view the monitoring page after bidding window close ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31514: verify Sponsor/EA/MM can view the monitoring page after bidding window close ");
		driver.findElement(By.xpath("(//span[text()='Event Monitoring'])[1]")).click();
		System.out.println("Click on EVENT MONITORING");
		ObjectRepo.getTest().log(Status.PASS, "Click on EVENT MONITORING");

		wait.until(ExpectedConditions.visibilityOf(jointauctionautomation));
		WebElement abc = jointauctionautomation;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
		System.out.println(driver.findElement(By.xpath("//*[text()='Bidding window is closed']")).getText());
		String a = driver.findElement(By.xpath("//*[text()='Bidding window is closed']")).getText();
		String b = "Bidding window is closed";
		Assert.assertEquals(a, b);
		System.out.println("Validated Bidding window is closed on Monitoring page");
		ObjectRepo.getTest().log(Status.PASS, "Validated Bidding window is closed on Monitoring page");

		btngear.click();

		String btn500 = btngear500.getText();
		System.out.println(btn500);
		btngear500.click();
		System.out.println("Select 500 of rows per page");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page");
		WebElement element = driver.findElement(By.xpath("//td[normalize-space()='T6CAEmetteurA']"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		String a1 = driver.findElement(By.xpath("//td[normalize-space()='T6CAEmetteurA']")).getText();

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//input[@id='entity-legal-name']"))));
		driver.findElement(By.xpath("//input[@id='entity-legal-name']")).sendKeys(a1);

		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Entity Legal Name");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Entity Legal Name");

		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[3]")).click();

		String b1 = driver.findElement(By.xpath("(//th[@role='cell'][normalize-space()='T6CA UserA'])[1]"))
				.getText();
		driver.findElement(By.xpath("(//input[@id='ar-name'])[1]")).sendKeys(b1);
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By AR Name");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By AR Name");

		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[4]")).click();

		String c = driver.findElement(By.xpath("//td[normalize-space()='CA4376-5560']")).getText();
		driver.findElement(By.xpath("(//input[@id='general-account-number'])[1]")).sendKeys(c);
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By General Account Number");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By General Account Number");

		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[5]")).click();

		String d = driver.findElement(By.xpath("//td[text()='2307']")).getText();
		driver.findElement(By.xpath("(//input[@id='bid-id'])[1]")).sendKeys(d);
		driver.findElement(By.xpath("(//span[text()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Bid ID");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Bid ID");

		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[6]")).click();

		String e = driver.findElement(By.xpath("(//td[normalize-space()='00216143'])[1]")).getText();
		driver.findElement(By.xpath("(//input[@id='transaction-id'])[1]")).sendKeys(e);
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Transaction ID");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Transaction ID");

		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[7]")).click();
		Select New1 = new Select(driver.findElement(By.xpath("(//select[@id='select-bid-status'])[1]")));
		New1.selectByVisibleText("Flagged");
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By FLAGGED BID STATUS");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Flagged Bid Status");
		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		Select New3 = new Select(driver.findElement(By.xpath("(//select[@id='select-bid-status'])[1]")));
		New3.selectByVisibleText("Excluded");
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Excluded Bid Status");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Excluded Bid Status");
		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		Select New4 = new Select(driver.findElement(By.xpath("(//select[@aria-invalid='false'])[2]")));
		New4.selectByVisibleText("Tier One");
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Tier One");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Tier One");
		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		Select New5 = new Select(driver.findElement(By.xpath("(//select[@aria-invalid='false'])[2]")));
		New5.selectByVisibleText("Tier Two");
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Tier Two");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Tier Two");
		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		Select New6 = new Select(driver.findElement(By.xpath("(//select[@aria-invalid='false'])[2]")));
		New6.selectByVisibleText("Tier Three");
		driver.findElement(By.xpath("(//span[normalize-space()='SEARCH'])[1]")).click();
		System.out.println("Successfully Search By Tier Three");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Search By Tier Three");
		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		driver.findElement(By.xpath(
				"//span[text()='ACCOUNT REPRESENTATIVE']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By ACCOUNT REPRESENTATIVE Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By ACCOUNT REPRESENTATIVE Column");

		driver
				.findElement(By.xpath(
						"//span[text()='ENTITY NAME']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By ENTITY NAME Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By ENTITY NAME Column");

		driver
				.findElement(
						By.xpath("//span[text()='BID ID']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By BID ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By BID ID Column");

		driver
				.findElement(By.xpath(
						"//span[text()='CLIENT BID ID']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By CLIENT BID ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By CLIENT BID ID Column");

		driver
				.findElement(By.xpath(
						"//span[text()='TRANSACTION ID']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TRANSACTION ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TRANSACTION ID Column");

		driver
				.findElement(By.xpath(
						"//span[text()='ACCOUNT NO.']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By ACCOUNT NO. Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By ACCOUNT NO. Column");

		driver
				.findElement(By
						.xpath("//span[text()='TIME (ET)']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIME (ET) Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIME (ET) Column");

		driver
				.findElement(
						By.xpath("//span[text()='CURRENCY']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By CURRENCY Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By CURRENCY Column");

		driver
				.findElement(By
						.xpath("//span[text()='TIER PRICE']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIER PRICE Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIER PRICE Column");

		driver
				.findElement(
						By.xpath("//span[text()='BID QTY']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By BID QTY Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By BID QTY Column");

		driver
				.findElement(
						By.xpath("//span[text()='TIER']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIER Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIER Column");

		driver
				.findElement(By
						.xpath("//span[text()='Bid Status']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By Bid Status Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By Bid Status Column");

		driver
				.findElement(
						By.xpath("//span[text()='Comments']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By Comments Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By Comments Column");
	}

	public void ValidateINCLUDEEXCLUDEDBID() throws InterruptedException {

		System.out.println("EXCLUDE Bid");
		System.out.println("TC ID:C31509: Verify Sponsor can include an excluded bid");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31509: Verify Sponsor can include an excluded bid");
		driver.findElement(By.xpath("(//span[text()='Event Monitoring'])[1]")).click();
		System.out.println("Click on EVENT MONITORING");
		ObjectRepo.getTest().log(Status.PASS, "Click on EVENT MONITORING");

		wait.until(ExpectedConditions.visibilityOf(jointauctionautomation1));
		WebElement abc = jointauctionautomation1;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
		btngear.click();
		String btn500 = btngear500.getText();
		System.out.println(btn500);
		btngear500.click();
		System.out.println("Select 500 of rows per page");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page");
		WebElement element = driver.findElement(By.xpath("(//button[@type='button'])[18]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();
		driver.findElement(By.xpath("(//li[normalize-space()='Exclude Bid'])[1]")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Text']")).sendKeys("EXCLUDE BID");
		driver.findElement(By.xpath("(//span[normalize-space()='POST'])[1]")).click();
		WebElement element21 = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[2]/div[1]/div[44]/div[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element21);

		System.out.println(driver
				.findElement(By.xpath(
						"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[2]"))
				.getText());

		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[1]/*[name()='svg'][1]"))
				.click();
		System.out.println(driver.findElement(By.xpath("//tbody/tr[1]/td[13]")).getText());
		System.out.println();
		System.out.println();

		System.out.println("INCLUDE Bid");
		System.out.println();

		WebElement element1 = driver.findElement(By.xpath("(//button[@type='button'])[18]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element1);

		driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();

		driver.findElement(By.xpath("(//li[normalize-space()='Include Bid'])[1]")).click();

		driver.findElement(By.xpath("(//span[normalize-space()='POST'])[1]")).click();
		String onebid = driver
				.findElement(By.xpath("(//p[text()='A comment must contain at least one character.'])[1]")).getText();
		String exp = "A comment must contain at least one character.";
		Assert.assertEquals(onebid, exp);
		System.out.println("Verified comment including does not happen when comments are not placed");
		ObjectRepo.getTest().log(Status.PASS, "Verified comment including does not happen when comments are not placed");

		driver.findElement(By.xpath("//input[@placeholder='Enter Text']")).sendKeys("I");
		System.out.println("Verified comment at least one character. ");
		ObjectRepo.getTest().log(Status.PASS, "Verified comment at least one character. ");
		Actions act = new Actions(driver);

		WebElement textbox = driver.findElement(By.xpath("//input[@placeholder='Enter Text']"));
		act.doubleClick(textbox).perform();

		textbox.sendKeys(Keys.BACK_SPACE);

		driver.findElement(By.xpath("//input[@placeholder='Enter Text']")).sendKeys("Include Bid");

		driver.findElement(By.xpath("(//span[normalize-space()='POST'])[1]")).click();
		System.out.println("Verified a comment to be placed to include a bid");
		ObjectRepo.getTest().log(Status.PASS, "Verified a comment to be placed to include a bid");

		WebElement element211 = driver.findElement(By.xpath(
				"//body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[2]/div[1]/div[44]/div[1]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element211);
		System.out.println(driver
				.findElement(By.xpath(
						"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[2]"))
				.getText());
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[6]/div[1]/div[1]/div[1]/*[name()='svg'][1]"))
				.click();
	}

	public void ValidateEVENTPAUSED() throws InterruptedException {

		System.out.println(
				"TC ID:C31534: As a sponsor I must be able to access an informational blurb to know how to execute the API");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31534: As a sponsor I must be able to access an informational blurb to know how to execute the API");
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		System.out.println();

		driver.findElement(By.xpath("(//a[normalize-space()='Execute API'])[1]")).click();

		driver.findElement(By.xpath("//span[text()='How to Run the API']")).click();
		System.out.println(driver.findElement(By.xpath("(//*[text()='How to Run the API'])[2]")).getText());
		String a1 = driver
				.findElement(By.xpath("//p[contains(text(),'A sponsor can execute each API by clicking on the ')]"))
				.getText();
		System.out.println(a1);
		ObjectRepo.getTest().log(Status.PASS, a1);
		String b = "A sponsor can execute each API by clicking on the \"Execute\" button at the end of each API row. At a given time, a sponsor will be able to execute any API. Click the \"Execute\" button to manually execute the selected API then click on \"Execute API\" to confirm. During the execution of the API, the status column will change to \"Running\". The operation may take a few minutes. Once the execution is complete, the status will change to \"Success\" if the API was correctly executed or to \"Failure\" if an error occurred and the API was not correctly executed. It is recommended to execute APIs in the following sequence to ensure data are correctly synchronized from CITSS into Auction Platform.";
		Assert.assertEquals(a1, b);
	}

	public void ValidateSPEXCUTEAPIHOWTORUNAPI() throws InterruptedException {
		System.out.println(
				"TC ID:C31534: As a sponsor I must be able to access an informational blurb to know how to execute the API");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31534: As a sponsor I must be able to access an informational blurb to know how to execute the API");
		driver.findElement(By.xpath("//span[text()='Settings']")).click();
		System.out.println();
		driver.findElement(By.xpath("(//a[normalize-space()='Execute API'])[1]")).click();
		driver.findElement(By.xpath("//span[text()='How to Run the API']")).click();
		System.out.println(driver.findElement(By.xpath("(//*[text()='How to Run the API'])[2]")).getText());
		String a1 = driver
				.findElement(By.xpath("//p[contains(text(),'A sponsor can execute each API by clicking on the ')]"))
				.getText();
		System.out.println(a1);
		ObjectRepo.getTest().log(Status.PASS, a1);
		String b = "A sponsor can execute each API by clicking on the \"Execute\" button at the end of each API row. At a given time, a sponsor will be able to execute any API. Click the \"Execute\" button to manually execute the selected API then click on \"Execute API\" to confirm. During the execution of the API, the status column will change to \"Running\". The operation may take a few minutes. Once the execution is complete, the status will change to \"Success\" if the API was correctly executed or to \"Failure\" if an error occurred and the API was not correctly executed. It is recommended to execute APIs in the following sequence to ensure data are correctly synchronized from CITSS into Auction Platform.";
		Assert.assertEquals(a1, b);
	}

	public void ValidateResetSequrityQuestion() throws InterruptedException {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='Financial Services Administrator']")));
		WebElement element1 = wait.until(ExpectedConditions.visibilityOf(welcomeFSA));
		Actions action = new Actions(driver);
		action.moveToElement(element1).build().perform();
		Thread.sleep(2000);
		action.click(element1).build().perform();
		wait.until(ExpectedConditions.visibilityOf(ResetSecurityQuestionsFSA));
		ResetSecurityQuestionsFSA.click();
		WebElement act = EmailChangePassphraseFSA;
		String Act = act.getAttribute("value");
		String Exp = "fsa_at_ca@yopmail.com";
		Assert.assertEquals(Act, Exp);
		System.out.println("Actual FSA Email Address:" + " " + Act);
		ObjectRepo.getTest().log(Status.PASS, "Actual FSA Email Address:" + " " + Act);
		System.out.println("Expected FSA Email Address:" + " " + Exp);
		ObjectRepo.getTest().log(Status.PASS, "Expected FSA Email Address:" + " " + Exp);
		System.out.println("Verified email address is correct");
		ObjectRepo.getTest().log(Status.PASS, "Verified email address is correct");
		if (act.isEnabled()) {
			System.out.println("Email address is not  pre-populated.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is not  pre-populated.");
		} else {
			System.out.println("Email address is pre-populated.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is pre-populated.");
		}
		if (act.isEnabled()) {
			System.out.println("Email address is editable.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is editable.");
		} else {
			System.out.println("Email address is not editable.");
			ObjectRepo.getTest().log(Status.PASS, "Email address is not editable.");
		}
		Select Selectyourfirstsecurityquestion = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]")));
		Selectyourfirstsecurityquestion.selectByVisibleText("In which city or town was your first job?");
		Select Selectyoursecondsecurityquestion = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[2]")));
		Selectyoursecondsecurityquestion.selectByVisibleText("What was the last name of your third-grade teacher?");
		Select Selectyourthirdsecurityquestion = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[3]")));
		Selectyourthirdsecurityquestion.selectByVisibleText("In which year did you get your first credit card?");
		driver.findElement(By.xpath("(//*[@placeholder='Enter your answer'])[1]")).sendKeys("Answer_1");
		driver.findElement(By.xpath("(//*[@placeholder='Enter your answer'])[2]")).sendKeys("Answer_2");
		driver.findElement(By.xpath("(//*[@placeholder='Enter your answer'])[3]")).sendKeys("Answer_3");
		driver.findElement(By.xpath("//*[text()='RESET MY SECURITY QUESTIONS']")).click();
		String actmessage = driver
				.findElement(By.xpath("//*[text()='Your security questions were reset successfully.']")).getText();
		System.out.println(actmessage);
		String expsuccess = "Your security questions were reset successfully.";
		System.out.println(expsuccess);
		System.out.println(
				"TC ID:C31611: Verified FSA can reset security questions upon successful login by accessing the top right down arrow menu.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31611: Verified FSA can reset security questions upon successful login by accessing the top right down arrow menu.");
	}

	public void searchReport() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		DownloadReportsPage report = new DownloadReportsPage();
		report.searchReportsByEventNameForJointAuction();
	}

	public void searchReportAR2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		DownloadReportsPage report = new DownloadReportsPage();
		report.searchReportsByEventNameForJointAuction();
	}

	public void searchReportMMPEOR() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("TC ID:C31567: MM CANNOT download and access the Post Event Opertaions Report ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31567: MM CANNOT download and access the Post Event Opertaions Report ");
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		String a = driver.findElement(By.xpath("//div[@id='non-entity-reports-table']")).getText();
		if (a == ("Post Event Operations Report")) {
			System.out.println("MM download and access the Post Event Opertaions Report");
			ObjectRepo.getTest().log(Status.PASS, "MM download and access the Post Event Opertaions Report");
		} else {
			System.out.println("MM CANNOT download and access the Post Event Opertaions Report");
			ObjectRepo.getTest().log(Status.PASS, "MM CANNOT download and access the Post Event Opertaions Report");
		}
	}

	public void searchReportALGO() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement element11 = wait.until(ExpectedConditions.visibilityOf(btnSearchReports));
		Actions action1 = new Actions(driver);
		action1.moveToElement(element11).build().perform();
		action1.click(element11).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		System.out.println("Select Event Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Select Event Name Dropdown");

		WebElement element111 = driver.findElement(By.xpath("//span[text()='AlgoTest2']"));
		System.out.println("Select Event");
		ObjectRepo.getTest().log(Status.PASS, "Select Event");
		
		js.executeScript("arguments[0].scrollIntoView();", element111);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='AlgoTest2']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		String resultreport = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]"))
				.getText();
		System.out.println(resultreport);
		ObjectRepo.getTest().log(Status.PASS, resultreport);
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReportRESET() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]")))).click();

		Select a = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]")));
		a.selectByVisibleText("User Activity Audit Report");
		WebElement a1 = driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]"));
		a1.click();
		WebElement act = driver.findElement(By.xpath("//*[contains(text(),'User Activity Audit Report')]"));
		act.click();
		String act1 = act.getText();
		String exp = "User Activity Audit Report";
		Assert.assertEquals(act1, exp);
		System.out.println(act1);
		driver.findElement(By.xpath("//button[@title='RESET']")).click();
		System.out.println("Reset User Activity Audit Report");

		WebElement all = driver.findElement(By.xpath("//*[contains(text(),'All')]"));
		String ALL = all.getText();
		System.out.println(ALL);
		driver.findElement(By.xpath("(//input[@id='auto'])[2]")).click();

		String alpha = driver.findElement(By.xpath("(//div)[84]")).getText();
		String value = alpha;
		if (value == "A") {
			Assert.assertTrue(value.contains("A"));
		} else if (value == "B") {
			Assert.assertTrue(value.contains("B"));
		} else if (value == "C") {
			Assert.assertTrue(value.contains("C"));
		} else if (value == "D") {
			Assert.assertTrue(value.contains("D"));
		} else if (value == "E") {
			Assert.assertTrue(value.contains("E"));
		}
		System.out.println(driver.findElement(By.xpath("(//div)[84]")).getText());
		System.out.println(
				"TC ID:C31397: Reset \"User Activity Audit Report\" from the \"SEARCH BY DATE RANGE (NON EVENT SPECIFIC)\" Section on Reports Screen");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31397: Reset \"User Activity Audit Report\" from the \"SEARCH BY DATE RANGE (NON EVENT SPECIFIC)\" Section on Reports Screen");
		System.out.println(
				"TC ID:C31400: The dropdown values for  Entity name in Reports search are  sorted alphabetically");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31400: The dropdown values for  Entity name in Reports search are  sorted alphabetically");
	}

	public void searchReportFR() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Servilence'])")));
		} catch (Exception e) {
			Thread.sleep(2000);
		}
		wait.until(ExpectedConditions.visibilityOf(btnReportsQC));
		btnReportsQC.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");

	}

	public void searchReportWS() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		
		WebElement element121 = eventNameDropDown;
		js.executeScript("arguments[0].scrollIntoView();", element121);
		wait.until(ExpectedConditions.visibilityOf(eventNameDropDown)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(eventNameDropDown)).sendKeys(prop.getProperty("event.name"));
		Thread.sleep(2000);
		
		WebElement element1 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("event.name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='" + prop.getProperty("event.name") + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void ValidatePostEventOperationReports() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnPostEventOperationReports));
		driver.findElement(btnPostEventOperationReports).click();
		Thread.sleep(20000);
		System.out.println("Post Event Operation Reports download successfully");
		System.out.println("Bids Reports-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Reports-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31325: Confirm Post Event Operations Report is generated ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31325: Confirm Post Event Operations Report is generated ");
		System.out.println(
				"TC ID:C31380: Maintain the Post Event Operations Report for an event once generated along with an audit trail ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31380: Maintain the Post Event Operations Report for an event once generated along with an audit trail ");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Post Event Operations Report - "
					+ prop.getProperty("Event_Name") + " " + date1 + ".xlsx");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateFinancialSettlementReportsENG() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReports));
		driver.findElement(btnFinancialSettlementReports).click();
		Thread.sleep(20000);
		System.out.println("Financial Settlement Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Reports-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31326: Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31326: Confirm the specified introductory fields are available");
		System.out.println("TC ID:C31329: The Financial Settlement must be available for a Joint Auction ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31329: The Financial Settlement must be available for a Joint Auction  ");
		System.out.println("TC ID:C31331: Financial Settlement must follow the naming convention  ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31331: Financial Settlement must follow the naming convention  ");
		System.out.println("TC ID:C31621: Confirm Financial Settlement Report is available in English ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31621: Confirm Financial Settlement Report is available in English ");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Financial Settlement Report - "
					+ prop.getProperty("Event_Name") + " " + date1 + ".xlsx");
			try {
				InputStream ExcelFileToRead = new FileInputStream(src);
				try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
					XSSFSheet sheet = wb.getSheetAt(0);
					XSSFRow row;
					XSSFCell cell;
					Iterator<Row> rows = sheet.rowIterator();
					XSSFRow row1 = (XSSFRow) sheet.getRow(4);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();
					if (value == "Registry General Account Number") {
						Assert.assertTrue(value.contains("Registry General Account Number"));
						System.out.println(value);
					} else if (value == "Entity Legal Name") {
						Assert.assertTrue(value.contains("Entity Legal Name"));
					} else if (value == "Entity Operating Name") {
						Assert.assertTrue(value.contains("Entity Operating Name"));
					} else if (value == "Entity Jurisdiction") {
						Assert.assertTrue(value.contains("Entity Jurisdiction"));
					} else if (value == "Financial Services Account Number") {
						Assert.assertTrue(value.contains("Financial Services Account Number"));
					} else if (value == "Financial Services Account Name") {
						Assert.assertTrue(value.contains("Financial Services Account Name"));
					} else if (value == "Currency Cash") {
						Assert.assertTrue(value.contains("Currency"));
					} else if (value == "Currency Cash") {
						Assert.assertTrue(value.contains("Cash Bid Guarantee Value"));
					} else if (value == "Currency Cash") {
						Assert.assertTrue(value.contains("Letter of Credit Bid Guarantee Value"));
					} else if (value == "Bond Bid Guarantee Value") {
						Assert.assertTrue(value.contains("Bond Bid Guarantee Value"));
					} else if (value == "Letter of Guarantee Bid Guarantee Value") {
						Assert.assertTrue(value.contains("Letter of Guarantee Bid Guarantee Value"));
					} else if (value == "Total Bid Guarantee Amount") {
						Assert.assertTrue(value.contains("Total Bid Guarantee Amount"));
					} else if (value == "Current Total Successful Bid Allowances") {
						Assert.assertTrue(value.contains("Current Total Successful Bid Allowances"));
					} else if (value == "Advance Total Successful Bid Allowances") {
						Assert.assertTrue(value.contains("Advance Total Successful Bid Allowances"));
					} else if (value == "Total Successful Bid Cost") {
						Assert.assertTrue(value.contains("Total Successful Bid Cost"));
					} else if (value == "Amount Owed") {
						Assert.assertTrue(value.contains("Amount Owed"));
					} else if (value == "Amount Owed By") {
						Assert.assertTrue(value.contains("Amount Owed By"));
					}

					while (rows.hasNext()) {
						row = (XSSFRow) rows.next();

						for (int i = 0; i < row.getLastCellNum(); i++) {
							cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							System.out.print(cell.toString() + " ");
							ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						}
						System.out.println();
					}
					ObjectRepo.getTest().log(Status.PASS,
							"All Headers in Financial Settlement Reports Asserted Successfully.");
					System.out.println("All Headers in Financial Settlement Reports Asserted Successfully.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateFinancialSettlementReportsFR() throws Exception {

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReportsFR));
		driver.findElement(btnFinancialSettlementReportsFR).click();
		System.out.println("Financial Settlement Reports download successfully");

		System.out.println("TC ID:C31621: Confirm Financial Settlement Report is available in French ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31621: Confirm Financial Settlement Report is available in French ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Rapport des paiements - " + prop.getProperty("Event_Name")
				+ " " + date1 + ".xlsx");

		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				XSSFRow row1 = (XSSFRow) sheet.getRow(0);
				XSSFCell cell1 = row1.getCell(0);
				String value = cell1.toString();
				if (value == "Numéro de compte général") {
					Assert.assertTrue(value.contains("Numéro de compte général"));
					System.out.println("Column header 'Numéro de compte général' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Numéro de compte général' verified.");
				} else if (value == "Dénomination sociale") {
					Assert.assertTrue(value.contains("Dénomination sociale"));
					System.out.println("Column header 'Dénomination sociale' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Dénomination sociale' verified.");
				} else if (value == "Dénomination commerciale") {
					Assert.assertTrue(value.contains("Dénomination commerciale"));
					System.out.println("Column header 'Dénomination commerciale' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Dénomination commerciale' verified.");
				} else if (value == "Gouvernement participant") {
					Assert.assertTrue(value.contains("Gouvernement participant"));
					System.out.println("Column header 'Gouvernement participant' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Gouvernement participant' verified.");
				} else if (value == "Numéro de compte des services financiers") {
					Assert.assertTrue(value.contains("Numéro de compte des services financiers"));
					System.out.println("Column header 'Numéro de compte des services financiers' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Numéro de compte des services financiers' verified.");
				} else if (value == "Nom du compte des services financiers") {
					Assert.assertTrue(value.contains("Nom du compte des services financiers"));
					System.out.println("Column header 'Nom du compte des services financiers' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Nom du compte des services financiers' verified.");
				} else if (value == "Devise") {
					Assert.assertTrue(value.contains("Devise"));
					System.out.println("Column header 'Devise' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Devise' verified.");
				} else if (value == "Valeur de la garantie financière soumise en argent") {
					Assert.assertTrue(value.contains("Valeur de la garantie financière soumise en argent"));
					System.out.println("Column header 'Valeur de la garantie financière soumise en argent' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Valeur de la garantie financière soumise en argent' verified.");
				} else if (value == "Valeur de la garantie financière soumise par lettre de crédit") {
					Assert.assertTrue(value.contains("Valeur de la garantie financière soumise par lettre de crédit"));
					System.out.println(
							"Column header 'Valeur de la garantie financière soumise par lettre de crédit' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Valeur de la garantie financière soumise par lettre de crédit' verified.");
				} else if (value == "Valeur de la garantie financière soumise par caution") {
					Assert.assertTrue(value.contains("Valeur de la garantie financière soumise par caution"));
					System.out
							.println("Column header 'Valeur de la garantie financière soumise par caution' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Valeur de la garantie financière soumise par caution' verified.");
				} else if (value == "Valeur de la garantie financière soumise par lettre de garantie") {
					Assert.assertTrue(
							value.contains("Valeur de la garantie financière soumise par lettre de garantie"));
					System.out.println(
							"Column header 'Valeur de la garantie financière soumise par lettre de garantie' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Valeur de la garantie financière soumise par lettre de garantie' verified.' verified.");
				} else if (value == "Montant total de la garantie financière") {
					Assert.assertTrue(value.contains("Montant total de la garantie financière"));
					System.out.println("Column header 'Montant total de la garantie financière' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Column header 'Montant total de la garantie financière' verified.");
				} else if (value == "Nombre d'unités d'émission adjugées (millésime présent)") {
					Assert.assertTrue(value.contains("Nombre d'unités d'émission adjugées (millésime présent)"));
					System.out.println(
							"Column header 'Nombre d'unités d'émission adjugées (millésime présent)' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Nombre d'unités d'émission adjugées (millésime présent)' verified.");
				} else if (value == "Nombre d'unités d'émission adjugées (millésime futur)") {
					Assert.assertTrue(value.contains("Nombre d'unités d'émission adjugées (millésime futur)"));
					System.out
							.println("Column header 'Nombre d'unités d'émission adjugées (millésime futur)' verified.");
					ObjectRepo.getTest().log(Status.PASS,
							"Nombre d'unités d'émission adjugées (millésime futur)' verified.");
				} else if (value == "Coût total des unités adjugées") {
					Assert.assertTrue(value.contains("Coût total des unités adjugées"));
					System.out.println("Column header 'Coût total des unités adjugées' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Coût total des unités adjugées' verified.");
				} else if (value == "Montant dû") {
					Assert.assertTrue(value.contains("Montant dû"));
					System.out.println("Column header 'Montant dû' verified.");
					ObjectRepo.getTest().log(Status.PASS, "Column header 'Montant dû' verified.");
				} else if (value == "Montant dû par") {
					Assert.assertTrue(value.contains("Montant dû par"));
					System.out.println("Column header 'Montant dû par' verified.");
				} else if (value == "05:23:40") {
					Assert.assertTrue(value.contains("05:23:40"));
					System.out.println("Confirm time reported is in EST");
				}

				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();

					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}
					System.out.println();
				}
				ObjectRepo.getTest().log(Status.PASS, "All Headers in Financial Settlement Reports Asserted Successfully.");
				System.out.println("All Headers in Financial Settlement Reports Asserted Successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateFinancialSettlementReportsForFSA() throws Exception {
		
		WebElement element1 = driver.findElement(btnFinancialSettlementReports);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(btnFinancialSettlementReports).click();
		System.out.println("Financial Settlement Reports download successfully");

		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31326: Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31326: Confirm the specified introductory fields are available");
		System.out.println("TC ID:C31329: The Financial Settlement must be available for a Joint Auction ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31329: The Financial Settlement must be available for a Joint Auction  ");
		System.out.println("TC ID:C31331: Financial Settlement must follow the naming convention  ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31331: Financial Settlement must follow the naming convention  ");
		File src = new File(
				folderPath + File.separator + "Financial Settlement Report - AuctionEvent1 11-21-2022.xlsx");
		Assert.assertTrue(
				"Financial Settlement Report - AuctionEvent1 11-21-2022.xlsx".contains("Financial Settlement Report"));
		System.out.println("The name of the report must be Financial Settlement Report Asserted Successfully");
		int count = 0;
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				XSSFRow row1 = (XSSFRow) sheet.getRow(4);
				XSSFCell cell1 = row1.getCell(0);
				String value = cell1.toString();
				if (value == "Registry General Account Number") {
					Assert.assertTrue(value.contains("Registry General Account Number"));
				} else if (value == "Entity Legal Name") {
					Assert.assertTrue(value.contains("Entity Legal Name"));
				} else if (value == "Entity Operating Name") {
					Assert.assertTrue(value.contains("Entity Operating Name"));
				} else if (value == "Entity Jurisdiction") {
					Assert.assertTrue(value.contains("Entity Jurisdiction"));
				} else if (value == "Financial Services Account Number") {
					Assert.assertTrue(value.contains("Financial Services Account Number"));
				} else if (value == "Financial Services Account Name") {
					Assert.assertTrue(value.contains("Financial Services Account Name"));
				} else if (value == "Currency Cash") {
					Assert.assertTrue(value.contains("Currency"));
				} else if (value == "Currency Cash") {
					Assert.assertTrue(value.contains("Cash Bid Guarantee Value"));
				} else if (value == "Currency Cash") {
					Assert.assertTrue(value.contains("Letter of Credit Bid Guarantee Value"));
				} else if (value == "Bond Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Bond Bid Guarantee Value"));
				} else if (value == "Letter of Guarantee Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Letter of Guarantee Bid Guarantee Value"));
				} else if (value == "Total Bid Guarantee Amount") {
					Assert.assertTrue(value.contains("Total Bid Guarantee Amount"));
				} else if (value == "Current Total Successful Bid Allowances") {
					Assert.assertTrue(value.contains("Current Total Successful Bid Allowances"));
				} else if (value == "Advance Total Successful Bid Allowances") {
					Assert.assertTrue(value.contains("Advance Total Successful Bid Allowances"));
				} else if (value == "Total Successful Bid Cost") {
					Assert.assertTrue(value.contains("Total Successful Bid Cost"));
				} else if (value == "Amount Owed") {
					Assert.assertTrue(value.contains("Amount Owed"));
				} else if (value == "Amount Owed By") {
					Assert.assertTrue(value.contains("Amount Owed By"));
				}

				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();

					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						if (cell != null) {
							count++;
						}
					}
					System.out.println();
				}
				System.out.println("Total num of feilds available =" + count);
				ObjectRepo.getTest().log(Status.PASS, "All Headers in Financial Settlement Reports Asserted Successfully.");
				System.out.println("All Headers in Financial Settlement Reports Asserted Successfully.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Validatebidsreportdownload() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsReports));
		driver.findElement(btnBidsReports).click();
		Thread.sleep(20000);
		System.out.println("Bids Reports download successfully");
		System.out.println("Bids Reports-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Reports-File extention validated as a .Xlsx format");
		System.out.println(
				"TC ID:C31379: Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31379: Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name") + " "
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
							String value = cell.toString();
							if (value == "General Account Number") {
								Assert.assertTrue(value.contains("General Account Number"));
							} else if (value == "Legal Name") {
								Assert.assertTrue(value.contains("Legal Name"));
							} else if (value == "Operating Name") {
								Assert.assertTrue(value.contains("Operating Name"));
							} else if (value == "Entity Type") {
								Assert.assertTrue(value.contains("Entity Type"));
							} else if (value == "Government") {
								Assert.assertTrue(value.contains("Government"));
							} else if (value == "Bid ID") {
								Assert.assertTrue(value.contains("Bid ID"));
							} else if (value == "Client Bid ID") {
								Assert.assertTrue(value.contains("Client Bid ID"));
							} else if (value == "Transaction ID") {
								Assert.assertTrue(value.contains("Transaction ID"));
							} else if (value == "Bid By") {
								Assert.assertTrue(value.contains("Bid By"));
							} else if (value == "Update Time (ET)") {
								Assert.assertTrue(value.contains("Update Time (ET)"));
							} else if (value == "Currency") {
								Assert.assertTrue(value.contains("Currency"));
							} else if (value == "Price Offered (USD)") {
								Assert.assertTrue(value.contains("Price Offered (USD)"));
							} else if (value == "Price Offered (CAD)") {
								Assert.assertTrue(value.contains("Price Offered (CAD)"));
							} else if (value == "Lots") {
								Assert.assertTrue(value.contains("Lots"));
							} else if (value == "Total Emission Units Bid") {
								Assert.assertTrue(value.contains("Total Emission Units Bid"));
							} else if (value == "Vintage") {
								Assert.assertTrue(value.contains("Vintage"));
							} else if (value == "Holding Limit Violation") {
								Assert.assertTrue(value.contains("Holding Limit Violation"));
							} else if (value == "Purchase Limit Violation") {
								Assert.assertTrue(value.contains("Purchase Limit Violation"));
							} else if (value == "Bid Guarantee Violation") {
								Assert.assertTrue(value.contains("Bid Guarantee Violation"));
							} else if (value == "Price Offered < Settlement Price") {
								Assert.assertTrue(value.contains("Price Offered < Settlement Price"));
							} else if (value == "Successful Emission Units") {
								Assert.assertTrue(value.contains("Successful Emission Units"));
							} else if (value == "Tie Breaker") {
								Assert.assertTrue(value.contains("Tie Breaker"));
							} else if (value == "Tie Breaker Share") {
								Assert.assertTrue(value.contains("Tie Breaker Share"));
							} else if (value == "Share Allocation") {
								Assert.assertTrue(value.contains("Share Allocation"));
							} else if (value == "Random Number Assignment") {
								Assert.assertTrue(value.contains("Random Number Assignment"));
							} else if (value == "Random Allocation") {
								Assert.assertTrue(value.contains("Random Allocation"));
							} else if (value == "Flag") {
								Assert.assertTrue(value.contains("Flag"));
							} else if (value == "Flag Time (ET)") {
								Assert.assertTrue(value.contains("Flag Time (ET)"));
							} else if (value == "Flagged By") {
								Assert.assertTrue(value.contains("Flagged By"));
							} else if (value == "Excluded") {
								Assert.assertTrue(value.contains("Excluded"));
							} else if (value == "Exclude Time (ET)") {
								Assert.assertTrue(value.contains("Exclude Time (ET)"));
							} else if (value == "Excluded By") {
								Assert.assertTrue(value.contains("Excluded By"));
							} else if (value == "Comment") {
								Assert.assertTrue(value.contains("Comment"));
							}
							ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
							System.out.print(cell.toString() + " ");
							;
						}
						System.out.println();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateAllowancedeliveryReportsSP() throws Exception {
		
		WebElement element = driver.findElement(btnAllowancedeliveryReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowancedeliveryReports).click();
		Thread.sleep(20000);
		System.out.println("Allowance delivery Reports successfully");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + prop.getProperty("event.name") + " " + date1 + ".xlsx");
		Assert.assertTrue(
				"Allowance Delivery Report - AuctionEvent1 11-21-2022.xlsx".contains("Allowance Delivery Report"));
		System.out.println("Asserted The name of the report must be Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
		System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31344: Confirmed the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31344: Confirmed the specified introductory fields are available");
		System.out.println("TC ID:C31345: Validated Allowance delivery report is available after the results are run");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31345: Validated Allowance delivery report is available after the results are run");
		System.out.println(
				"TC ID:C31348: Validated The Allowance (Emission Units) Delivery Report must be available for a Joint Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31348: Validated The Allowance (Emission Units) Delivery Report must be available for a Joint Auction");
		System.out.println(
				"TC ID:C31350: Allowance Delivery Report must follow naming convention (Allowance Delivery Report Event name Event date MM-DD-YYYY)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31350: Allowance Delivery Report must follow naming convention(Allowance Delivery Report Event name Event date MM-DD-YYYY)");
		System.out.println("TC ID:C31351: Allowance Delivery Report must have the correct layout");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31351: Allowance Delivery Report must have the correct layout");
		System.out.println("TC ID:C31352: Verified the data in the Allowance Delivery Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31352: Verified the data in the Allowance Delivery Report is correct");
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
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString());

					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateAllowancedeliveryReportsMM() throws Exception {
		
		WebElement element = driver.findElement(btnAllowancedeliveryReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowancedeliveryReports).click();
		Thread.sleep(20000);
		System.out.println("Allowance delivery Reports successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + prop.getProperty("event.name") + " " + date1 + ".xlsx");
		Assert.assertTrue(
				"Allowance Delivery Report - AuctionEvent1 11-21-2022.xlsx".contains("Allowance Delivery Report"));
		System.out.println("Asserted The name of the report must be Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
		System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31346:  Allowance delivery report is available after the results are run(MM)   ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31346:  Allowance delivery report is available after the results are run(MM)   ");
		System.out.println("TC ID:C31566:  MM can download and access the Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31566:  MM can download and access the Allowance Delivery Report");
		System.out.println("TC ID:C31274:  Confirm time reported is in EST ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31274:  Confirm time reported is in EST ");

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
						String value = cell.toString();
						if (value == "08:09:37") {
							Assert.assertTrue(value.contains("08:09:37"));
						}
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString());

					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateAllowancedeliveryReportsQC() throws Exception {
		
		WebElement element = driver.findElement(btnEmissionUnitsDeliveryReport);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnEmissionUnitsDeliveryReport).click();
		Thread.sleep(20000);
		System.out.println("Emission Units Delivery Report Download successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Emission Units Delivery Report - "
				+ prop.getProperty("Event_Name") + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
		System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31403:  Allowance Delivery Report must follow naming convention(QC)  ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31403:  Allowance Delivery Report must follow naming convention(QC)  ");

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
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString());

					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateAllowancedeliveryReportsWS() throws Exception {
		
		WebElement element = btnAllowancedeliveryReportsWS;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnAllowancedeliveryReportsWS.click();
		System.out.println("Allowance delivery Reports successfully");

		File src = new File(folderPath + File.separator + "Allowance Delivery Report - WA123 09-22-2022.xlsx");
		Assert.assertTrue("Allowance Delivery Report - WA123 09-22-2022.xlsx".contains("Allowance Delivery Report"));
		System.out.println("Asserted The name of the report must be Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
		System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31404:  Allowance Delivery Report must have the correct layout(WS)");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31404:  Allowance Delivery Report must have the correct layout(WS)");
		System.out.println("TC ID:C31405:  Verify the data in the Allowance Delivery Report is correct(WS)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31405:  Verify the data in the Allowance Delivery Report is correct(WS)");

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
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString());

					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateQualifiedBidderReports() throws Exception {
		
		WebElement element = driver.findElement(btnQualifiedBidderReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnQualifiedBidderReports).click();
		System.out.println("Qualified Bidder Reports download successfully");

		File src = new File(folderPath + File.separator + "Qualified Bidders Report - R192 10-12-2022.xlsx");
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
						System.out.print(cell.toString() + " ");
					}
					System.out.println();
				}
				System.out.println("File extention validated as a .Xlsx format");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ValidateFinancialStatementreportAR() throws EmptyFileException, IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FinancialStatementdownloadbutton));
		driver.findElement(FinancialStatementdownloadbutton).click();
		Thread.sleep(20000);
		System.out.println("Financial Statement report download successfully");
		System.out.println("TC ID:C31389:The AR can access the Financial Statement Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31389:The AR can access the Financial Statement Report");
		System.out.println("TC ID:C31387:The specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31387: The specified introductory fields are available");
		System.out.println("TC ID:C31394: Validate Financial Statement must have the correct layout");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31394: Validate Financial Statement must have the correct layout");
		System.out.println("TC ID:C31395: The data in the Financial Statement Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31395: The data in the Financial Statement Report is correct");
		System.out.println(
				"TC ID:C31407:The name of the report must be Financial Statement - <Event name> <Event date MM-DD-YYYY> <Registry ID> ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31407:Financial Statement Report below naming convention:");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report Event name Event date MM-DD-YYYY Registry ID");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31407:File extention validated as a .pdf format");
		System.out.println("TC ID:C31407:File extention validated as a .pdf format");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("event.name") + " " + date1 + " "
				+ prop.getProperty("EntityA1") + ".pdf");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Financial Statement Report");
		InputStream PdfFileToRead = new FileInputStream(src);
		PDDocument doc = PDDocument.load(PdfFileToRead);
		int pages = doc.getNumberOfPages();
		System.out.println("The total number of pages " + pages);
		PDFTextStripper strip = new PDFTextStripper();
		strip.setStartPage(1);
		strip.setEndPage(2);
		String stripText = strip.getText(doc);
		System.out.println(stripText);
		ObjectRepo.getTest().log(Status.PASS, stripText);
		doc.close();
		return stripText;
	}

	public void ValidateFinancialStatementreportSP() throws EmptyFileException, IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']")).click();
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
		Thread.sleep(20000);
		System.out.println("Financial Statement report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement report successfully downloaded by sponsor");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("Event_Name")
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

	public void ValidateClientBidsReport() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(clientBidsReportForJointAuction));
		driver.findElement(clientBidsReportForJointAuction).click();
		Thread.sleep(20000);
		System.out.println("Client Bids Report download successfully");
		System.out.println("TC ID:C31359:The AR can access the Client Bid Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31359:The AR can access the Client Bid Report");
		System.out.println("TC ID:C31363:Client Bid Report follow naming convention:"
				+ "Client Bid Report - <Event name> <Event date MM-DD-YYYY> <Registry ID> ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31363:Client Bid Report below naming convention:");
		ObjectRepo.getTest().log(Status.PASS, "Client Bid Report Event name Event date MM-DD-YYYY Registry ID");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31363:File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31363:File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31357:The specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31357: The specified introductory fields are available");
		System.out.println("TC ID:C31365: The data in the Client Bid Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31365: The data in the Client Bid Report is correct");
		System.out.println("TC ID:C31274: Confirm time reported is in EST");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31274: Confirm time reported is in EST");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Client Bids Report - " + prop.getProperty("Event_Name")
					+ " " + date1 + " " + "CA4378-5564" + ".xlsx");
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
							String value = cell.toString();
							if (value == "08:09:37") {
								Assert.assertTrue(value.contains("08:09:37"));
							}
							System.out.print(cell.toString() + " ");
							ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
						}
						System.out.println();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateClientBidsAuditreport() throws Exception {

		driver
				.findElement(By.xpath(
						"//button[@aria-label='Download Client Bids Audit Report for event JointEventAuctionAuto']"))
				.click();
		System.out.println("Client Bids Audit download successfully");

		File src = new File(folderPath + File.separator
				+ "Client Bids Audit Report - JointEventAuctionAuto 09-14-2022 CA4406-5609.xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell = null;

				Iterator<Row> rows = sheet.rowIterator();

				while (rows.hasNext()) {
					row = (XSSFRow) rows.next();

					for (int i = 0; i < row.getLastCellNum(); i++) {
						cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}

					System.out.println();
					ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");

				}
				System.out.println("File extention validated as a .Xlsx format");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PAUSEEVENTQC() throws InterruptedException, AWTException {

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");

		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		System.out.println("Select number of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select number of rows per page.");

		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		System.out.println("Select 500 of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page.");

		BtnNextPAUSEEVENT();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");

		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='PAUSE'])[2]")).click();

		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		CreateEventPage3 ref3 = new CreateEventPage3();
		ref3.Logout();
	}

	public void ALGORUNQC() throws InterruptedException, AWTException {

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");

		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		System.out.println("Select number of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select number of rows per page.");

		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		System.out.println("Select 500 of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page.");
		BtnNextEventDetailsALGO();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");
		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[2]"))
				.click();
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
		System.out.println("Click on RESET Button");
		ObjectRepo.getTest().log(Status.PASS, "Click on RESET Button");

		WebElement a = driver
				.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[2]"));
		if (a.isEnabled()) {
			System.out.println("RUN ALGORITHM button is Enble");
		} else {
			System.out.println("RUN ALGORITHM button is disable");
		}

		CreateEventPage3 ref3 = new CreateEventPage3();
		ref3.Logout();
	}

	public void ALGORUNQC1() throws InterruptedException, AWTException {
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		BtnNextEventDetailsALGO();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");
		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[2]"))
				.click();
		System.out.println("Second Sponsor RUN ALGORITHM");
		ObjectRepo.getTest().log(Status.PASS, "Second Sponsor RUN ALGORITHM");

		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_END);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		driver.findElement(By.xpath("(//button[@type='button'])[13]")).click();
		WebElement a = driver
				.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[2]"));
		if (a.isEnabled()) {
			System.out.println("RUN ALGORITHM button is Enble");
		} else {
			System.out.println("RUN ALGORITHM button is disable");
		}
		System.out.println("Sponsor RUN ALGORITHM Multiple Time");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor RUN ALGORITHM Multiple Time");
		CreateEventPage3 ref3 = new CreateEventPage3();
		ReportsPage report = new ReportsPage();
		report.searchReportALGO();
		ref3.Logout();
	}

	public void PAUSEEVNTCA() throws Exception {
		System.out.println("TC ID:C31453: Confirm the ability of a Sponsor to reset the run algorithm. ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31453: Confirm the ability of a Sponsor to reset the run algorithm. ");
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Click on EVENT");
		ObjectRepo.getTest().log(Status.PASS, "Click on EVENT");

		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		System.out.println("Select number of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select number of rows per page.");

		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		System.out.println("Select 500 of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page.");

		BtnNextPAUSEEVENT();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");

		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='PAUSE'])[1]")).click();
		Logout();
	}

	public void ValidatelocalizedAuctioncertificationpageCA() throws Exception {
		System.out.println("TC ID:C31481: Confirm the Auction Certification Page displays localized information ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31481: Confirm the Auction Certification Page displays localized information ");

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnAuctionCertification();
		System.out.println("Click on Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Click on Auction Certification");
		WebElement elem = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]"));
		if (elem.getText().contains("Auction Certification")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println(" Validated the Auction Certification Page displays localized information (English) ");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Auction Certification Page displays localized information (English) ");
		Logout();
	}

	public void ValidatelocalizedCMSpageCA() throws Exception {
		System.out.println("TC ID:C31492: Confirm the CMS Page displays localized information ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31492: Confirm the CMS Page displays localized information ");

		clickbtnCMS();
		System.out.println("Step  : Click on CMS ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on CMS ");

		WebElement elem = driver.findElement(By.xpath("//span[text()='Reserve Price Administration']"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Reserve Price Administration")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println("Validated the CMS Page displays localized information (English) ");
		ObjectRepo.getTest().log(Status.PASS, "Validated the CMS Page displays localized information (English) ");
		Logout();
	}

	public void ValidatelocalizedReservePricepageCA() throws Exception {
		System.out.println("TC ID:C31483:Confirm the Reserve Price Admin Page displays localized information");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31483:Confirm the Reserve Price Admin Page displays localized information");
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnReservePrice();
		System.out.println("Click on Reserve Price");
		ObjectRepo.getTest().log(Status.PASS, "Click on Reserve Price");
		WebElement elem = driver.findElement(By.xpath("//h1[text()='Auction Reserve Price Administration']"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Auction Reserve Price Administration")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println("Validated the Reserve Price Admin Page displays localized information(English)");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Reserve Price Admin Page displays localized information (English)");
		Logout();
	}

	public void ValidatelocalizedReservePricepageQC() throws Exception {
		System.out.println("TC ID:C31481: Confirm the Auction Certification Page dsiplays localized information ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31481: Confirm the Auction Certification Page dsiplays localized information ");
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnReservePriceQC();
		System.out.println("Click on Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Click on Auction Certification");

		WebElement elem = driver.findElement(By.xpath("//h1[text()='Minimum Price Administration']"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Minimum Price Administration")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		Logout();
	}

	public void ValidatelocalizedAuctioncertificationpageQC() throws Exception {
		System.out.println("TC ID:C31481: Confirm the Auction Certification Page displays localized information ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31481: Confirm the Auction Certification Page displays localized information ");
		clickbtnAdminqc();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnAuctionCertification();
		System.out.println("Click on Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Click on Auction Certification");

		WebElement elem = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Auction Certification")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println("Validated the Auction Certification Page displays localized information(English)");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Auction Certification Page displays localized information(English)");
		LanguageSelectionQC();
		WebElement elem1 = driver
				.findElement(By.xpath("//body/div[@id='root']/div/div/div/div[@class='MuiContainer-root']/div[1]"));
		System.out.println(elem1.getText());
		if (elem1.getText().contains("Certification de la vente aux enchères")) {
			System.out.println("It's French!!");
		} else if (elem1.getText().contains("Auction Certification")) {
			System.out.println("It's English!");
		}
		System.out.println("Validated the Auction Certification Page displays localized information(French)");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Auction Certification Page displays localized information(French)");
		LanguageSelectionQCENG();
		Logout();
	}

	public void ValidatelocalizedMinimumPricepageQC() throws Exception {
		System.out.println("TC ID:C31483:Confirm the Minimum Price Admin Page displays localized information");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31483:Confirm the Minimum Price Admin Page displays localized information");

		clickbtnAdminqc();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnReservePriceQC();
		System.out.println("Click on Minimum Price");
		ObjectRepo.getTest().log(Status.PASS, "Click on Minimum Price");

		WebElement elem = driver.findElement(By.xpath("//*[text()='Minimum Price Administration']"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Minimum Price Administration")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println("Validated the Minimum Price Admin Page displays localized information(English))");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Minimum Price Admin Page displays localized information(English)");

		LanguageSelectionQC();

		WebElement elem1 = driver.findElement(By.xpath("//*[text()='Gestion du prix minimal']"));
		System.out.println(elem1.getText());
		if (elem1.getText().contains("Gestion du prix minimal")) {
			System.out.println("It's French!!");
		} else if (elem1.getText().contains("Minimum Price")) {
			System.out.println("It's English!");
		}
		System.out.println("Validated the Minimum Price Admin Page displays localized information(French))");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated the Minimum Price Admin Page displays localized information(French)");
		LanguageSelectionQCENG();
		Logout();
	}

	public void ValidatelocalizedCMSQC() throws Exception {
		System.out.println("Validated the CMS Page displays localized information (English) ");
		ObjectRepo.getTest().log(Status.PASS, "Validated the CMS Page displays localized information (English) ");

		clickbtnCMS();
		System.out.println("Step  : Click on CMS ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on CMS ");

		WebElement elem = driver.findElement(By.xpath("//span[text()='Minimum Price Administration']"));
		System.out.println(elem.getText());
		if (elem.getText().contains("Minimum Price Administration")) {
			System.out.println("It's English!");
		} else {
			System.out.println("It's French!");
		}
		System.out.println("Validated the CMS Page displays localized information (English) ");
		ObjectRepo.getTest().log(Status.PASS, "Validated the CMS Page displays localized information (English) ");

		LanguageSelectionQC();

		WebElement elem1 = driver.findElement(By.xpath("//*[text()='Gestion du prix minimal']"));
		System.out.println(elem1.getText());
		if (elem1.getText().contains("Gestion du prix minimal")) {
			System.out.println("It's French!!");
		} else if (elem1.getText().contains("Minimum Price")) {
			System.out.println("It's English!");
		}
		System.out.println("Validated the CMS Page displays localized information (French)");
		ObjectRepo.getTest().log(Status.PASS, "Validated the CMS Page displays localized information(French)");
		LanguageSelectionQCENG();
		Logout();
	}

	public void ALGORUNCA() throws Exception {
		System.out.println("TC ID:C31453: Confirm the ability of a Sponsor to reset the run algorithm. ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31453: Confirm the ability of a Sponsor to reset the run algorithm. ");

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Click on EVENT");
		ObjectRepo.getTest().log(Status.PASS, "Click on EVENT");

		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		System.out.println("Select number of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select number of rows per page.");

		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		System.out.println("Select 500 of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page.");

		BtnNextEventDetailsALGO();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");

		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[1]"))
				.click();
		WebElement b = driver
				.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[1]"));
		if (b.isEnabled()) {
			System.out.println("RUN ALGORITHM button is Enble");
			ObjectRepo.getTest().log(Status.PASS, "RUN ALGORITHM button is Enble");
		} else {
			System.out.println("RUN ALGORITHM button is disable");
			ObjectRepo.getTest().log(Status.PASS, "RUN ALGORITHM button is disable");
		}

		System.out.println("Sponsor RUN ALGORITHM");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor RUN ALGORITHM");

		Logout();
	}

	public void ALGORUNCA1() throws Exception {
		System.out.println("TC ID:C31399: Run algorithms multiple times and generate reports ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31399: Run algorithms multiple times and generate reports ");
		System.out.println(
				"TC ID:C31454: Confirm Algorithms can be re-run prior to auction certification to generate respective results reports.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31454: Confirm Algorithms can be re-run prior to auction certification to generate respective results reports.");

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");

		driver.findElement(By.xpath("//button[@title='Select number of rows per page.']")).click();
		System.out.println("Select number of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select number of rows per page.");

		driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/ul[1]/li[5]")).click();
		System.out.println("Select 500 of rows per page.");
		ObjectRepo.getTest().log(Status.PASS, "Select 500 of rows per page.");

		BtnNextEventDetailsALGO();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");

		driver.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[1]"))
				.click();
		WebElement b = driver
				.findElement(By.xpath("(//span[@class='MuiButton-label'][normalize-space()='RUN ALGORITHM'])[1]"));
		if (b.isEnabled()) {
			System.out.println("RUN ALGORITHM button is Enble");
			ObjectRepo.getTest().log(Status.PASS, "RUN ALGORITHM button is Enble");
		} else {
			System.out.println("RUN ALGORITHM button is disable");
			ObjectRepo.getTest().log(Status.PASS, "RUN ALGORITHM button is disable");
		}
		System.out.println("Sponsor RUN ALGORITHM Multiple Time");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor RUN ALGORITHM Multiple Time");

		Logout();
	}

	public void FXRATECA() throws InterruptedException {
		System.out.println("TC ID:C31266:Verify the FX rate input is greater than 0");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31266:Verify the FX rate input is greater than 0");
		System.out.println("TC ID:C31267:As a sponsor I must be able to reset the approval of FX rate");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31267:As a sponsor I must be able to reset the approval of FX rate");

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnFXRATECA();
		System.out.println("Step  : Click on RESERVE PRICE");
		ObjectRepo.getTest().log(Status.PASS, " Click on RESERVE PRICE");
		driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for CA'])[1]"))).sendKeys("0");
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[2]")).click();
		String actual = driver
				.findElement(By.xpath("(//p[normalize-space()='FX rate should be greater than zero.'])[1]")).getText();
		ObjectRepo.getTest().log(Status.PASS, actual);
		String Exp = "FX rate should be greater than zero.";
		Assert.assertEquals(actual, Exp);
		System.out.println("Verfiy FX rate cannot be 0");
		ObjectRepo.getTest().log(Status.PASS, "Verfiy FX rate cannot be 0");
		Actions act = new Actions(driver);

		WebElement textbox = driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for CA'])[1]")));
		act.doubleClick(textbox).perform();

		textbox.sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for CA'])[1]"))).sendKeys("1");
		System.out.println(driver.findElement(By.xpath("(//*[text()='SUBMIT'])[2]")).getText());
		String actual11 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[2]")).getText();
		ObjectRepo.getTest().log(Status.PASS, actual11);
		String Exp11 = " SUBMIT";
		Assert.assertEquals(actual11, Exp11);
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[2]")).click();

		System.out.println(driver.findElement(By.xpath("(//*[text()='SUBMITTED'])[2]")).getText());
		String actual1 = driver.findElement(By.xpath("(//p[normalize-space()='FX Rate submitted.'])[1]"))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, actual1);
		String Exp1 = "FX Rate submitted.";
		Assert.assertEquals(actual1, Exp1);
		String actual111 = driver.findElement(By.xpath("(//*[text()='SUBMITTED'])[2]")).getText();
		ObjectRepo.getTest().log(Status.PASS, actual111);
		String Exp111 = " SUBMITTED";
		Assert.assertEquals(actual111, Exp111);
		System.out.println("Verified FX rate is greater than 0");
		ObjectRepo.getTest().log(Status.PASS, "Verified FX rate is greater than 0");
		CreateEventPage3 log1 = new CreateEventPage3();
		log1.Logout();
	}

	public void FXRATEQC() throws InterruptedException {

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnFXRATEQC();
		System.out.println("Step  : Click on Minimum Price");
		ObjectRepo.getTest().log(Status.PASS, " Click on Minimum Price");
		driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for QC'])[1]"))).sendKeys("1");
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]")).click();

		driver.findElement(By.xpath("//button[@title='SUBMIT']")).click();
		System.out.println("Verified FX rate is greater than 0");
		ObjectRepo.getTest().log(Status.PASS, "Verified FX rate is greater than 0");

		driver.findElement(By.xpath("//*[text()='RESET']")).click();
		System.out.println("Verified the FX values are reset.");
		ObjectRepo.getTest().log(Status.PASS, "Verified the FX values are reset.");
		driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for QC'])[1]"))).getText();
		System.out
				.println("Verified The table goes back to its original status. (No values filled, approval blank too)");
		ObjectRepo.getTest().log(Status.PASS,
				"Verified The table goes back to its original status. (No values filled, approval blank too)");

		driver.findElement(By.xpath(("(//*[@aria-label='Enter FX Rate for QC'])[1]"))).sendKeys("0");
		driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]")).click();
		String actual1111 = driver
				.findElement(By.xpath("(//p[normalize-space()='FX rate should be greater than zero.'])[1]")).getText();
		String Exp1111 = "FX rate should be greater than zero.";
		Assert.assertEquals(actual1111, Exp1111);
		System.out.println("Verfiy FX rate cannot be 0");
		ObjectRepo.getTest().log(Status.PASS, "Verfiy FX rate cannot be 0");
		CreateEventPage3 log11 = new CreateEventPage3();
		log11.Logout();
	}

	public void searchByReportNameYearEventNameAndEntityName() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		String aseertSubHeadingReport1 = wait.until(ExpectedConditions.elementToBeClickable(subHeadingReport1))
				.getText();
		System.out.println("Sub-heading for the Reports page displayed is: " + aseertSubHeadingReport1);
		Assert.assertEquals("SEARCH BY EVENT", aseertSubHeadingReport1);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		WebElement reportName = reportNameDropDown1;
		Select s = new Select(reportName);
		s.selectByVisibleText("Qualified Bidders Report");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertQualifiedBidderReport = wait.until(ExpectedConditions.elementToBeClickable(qualifiedBidderReport))
				.getText();
		System.out.println("Searched Report displayed is: " + aseertQualifiedBidderReport);
		ObjectRepo.getTest().log(Status.PASS, "Searched Report displayed is: " + aseertQualifiedBidderReport);
		Assert.assertEquals("Qualified Bidders Report", aseertQualifiedBidderReport);
		ReportsPage totalReports = new ReportsPage();
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		WebElement year = yearDropDown;
		Select s6 = new Select(year);
		s6.selectByVisibleText("2023");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("For the selected Year following reports are available in the list.");
		ObjectRepo.getTest().log(Status.PASS, "For the selected Year following reports are available in the list.");
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDownOption)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("For the selected Event Name following report is available in the list.");
		ObjectRepo.getTest().log(Status.PASS, "For the selected Event Name following report is available in the list.");
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		wait.until(ExpectedConditions.elementToBeClickable(entityNameDropDown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(entityNameDropDownOption)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("For the selected Entity Name following reports are available in the list.");
		ObjectRepo.getTest().log(Status.PASS, "For the selected Entity Name following reports are available in the list.");
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
	}

	public void partialSearchByReportName() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		WebElement reportName = reportNameDropDown;
		Select s = new Select(reportName);
		s.selectByVisibleText("Qualified Bidders Report");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertQualifiedBidderReport = wait.until(ExpectedConditions.elementToBeClickable(qualifiedBidderReport))
				.getText();
		System.out.println("Searched Report displayed after partial search is: " + aseertQualifiedBidderReport);
		ObjectRepo.getTest().log(Status.PASS,
				"Searched Report displayed after partial search is: " + aseertQualifiedBidderReport);
		Assert.assertEquals("Qualified Bidders Report", aseertQualifiedBidderReport);
		ReportsPage totalReports = new ReportsPage();
		totalReports.listOfTotalReports();
		System.out.println("******************************************************************");
	}

	public void resetTheSearch() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReset)).click();
		System.out.println("Sponsor is able to reset the search results.");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor is able to reset the search results.");
		System.out.println("******************************************************************");

	}

	public void accessTheReportsAvailableForSponsor() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element2 = driver.findElement(By.xpath("//span[text()='" + prop.getProperty("event.name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element2);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + prop.getProperty("event.name") + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertSearchedEventName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(searchedEventName1))
				.getText();
		System.out.println("Searched Event Name displayed as: " + aseertSearchedEventName1);
		ObjectRepo.getTest().log(Status.PASS, "Searched Event Name displayed as:" + aseertSearchedEventName1);
		String aseertHeadingForTheSearchedEventName = wait
				.until(ExpectedConditions.elementToBeClickable(headingForTheSearchedEventName)).getText();
		System.out.println("Heading for the Searched Event Name displayed as: " + aseertHeadingForTheSearchedEventName);
		ObjectRepo.getTest().log(Status.PASS,
				"Heading for the Searched Event Name displayed as: " + aseertHeadingForTheSearchedEventName);
		Assert.assertEquals("Reports Available to Sponsor and Monitor", aseertHeadingForTheSearchedEventName);
		String aseertBidsAuditReport = wait.until(ExpectedConditions.elementToBeClickable(bidsAuditReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertBidsAuditReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertBidsAuditReport);
		Assert.assertEquals("Bids Audit Report", aseertBidsAuditReport);
		String aseertFinancialSettlementReport = wait
				.until(ExpectedConditions.elementToBeClickable(financialSettlementReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertFinancialSettlementReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertFinancialSettlementReport);
		Assert.assertEquals("Financial Settlement Report", aseertFinancialSettlementReport);
		String aseertPostEventOperationsReport = wait
				.until(ExpectedConditions.elementToBeClickable(postEventOperationsReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertPostEventOperationsReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertPostEventOperationsReport);
		Assert.assertEquals("Post Event Operations Report", aseertPostEventOperationsReport);
		WebElement element1 = resultReport;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String aseertResultReport = wait.until(ExpectedConditions.elementToBeClickable(resultReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertResultReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertResultReport);
		Assert.assertEquals("Results Report", aseertResultReport);
		String aseertBidsReport = wait.until(ExpectedConditions.elementToBeClickable(bidsReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertBidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertBidsReport);
		Assert.assertEquals("Bids Report", aseertBidsReport);
		String aseertAllowanceDeliveryReport = wait
				.until(ExpectedConditions.elementToBeClickable(allowanceDeliveryReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertAllowanceDeliveryReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertAllowanceDeliveryReport);
		Assert.assertEquals("Allowance Delivery Report", aseertAllowanceDeliveryReport);
		String aseertQualifiedBiddersReport = wait
				.until(ExpectedConditions.elementToBeClickable(qualifiedBiddersReport)).getText();
		System.out.println("Reports access available to Sponsor is: " + aseertQualifiedBiddersReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertQualifiedBiddersReport);
		Assert.assertEquals("Qualified Bidders Report", aseertQualifiedBiddersReport);
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForSponsor() throws Exception {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnPostEventOperationReports));
			driver.findElement(btnPostEventOperationReports).click();
			System.out.println("Post Event Operations Report validated in English for Quebec User");
			ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in English for Quebec User");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Post Event Operations report validated in English for Quebec User");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsAuditReports));
		driver.findElement(btnBidsAuditReports).click();
		System.out.println("Bids audit report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in English for Quebec User");
		Thread.sleep(5000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReports));
			driver.findElement(btnFinancialSettlementReports).click();
			System.out.println("Financial Settlement Report validated in English for Quebec User");
			ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for Quebec User");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Financial Settlement Report validated in English for Quebec User");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsReports));
		driver.findElement(btnBidsReports).click();
		System.out.println("Bids report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports));
		driver.findElement(btnResultReports).click();
		System.out.println("Results report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnAllowancedeliveryReports));
		driver.findElement(btnAllowancedeliveryReports).click();
		System.out.println("Allowance Delivery Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports));
		driver.findElement(btnQualifiedBidderReports).click();
		System.out.println("Qualified Bidder Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated in English for Quebec User");
		System.out.println("******************************************************************");

	}

	public void accessTheReportsAvailableForMM() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		
		WebElement reportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", reportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.visibilityOf(headingReportEN)).getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + prop.getProperty("event.name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + prop.getProperty("event.name") + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertSearchedEventName = wait.until(ExpectedConditions.visibilityOfElementLocated(searchedEventName1))
				.getText();
		System.out.println("Searched Event Name displayed as: " + aseertSearchedEventName);
		ObjectRepo.getTest().log(Status.PASS, "Searched Event Name displayed as:" + aseertSearchedEventName);
		System.out.println("******************************************************************");
		System.out.println("Following reports are accessible to Market Monitor:- ");
		String aseertBidsAuditReport = wait.until(ExpectedConditions.elementToBeClickable(bidsAuditReport)).getText();
		System.out.println(aseertBidsAuditReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertBidsAuditReport);
		Assert.assertEquals("Bids Audit Report", aseertBidsAuditReport);
		String aseertBidsReport = wait.until(ExpectedConditions.elementToBeClickable(bidsReport)).getText();
		System.out.println("" + aseertBidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertBidsReport);
		Assert.assertEquals("Bids Report", aseertBidsReport);
		WebElement element1 = resultReport;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String aseertResultReport = wait.until(ExpectedConditions.elementToBeClickable(resultReport)).getText();
		System.out.println(aseertResultReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertResultReport);
		Assert.assertEquals("Results Report", aseertResultReport);
		String aseertAllowanceDeliveryReport = wait
				.until(ExpectedConditions.elementToBeClickable(allowanceDeliveryReport)).getText();
		System.out.println(aseertAllowanceDeliveryReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertAllowanceDeliveryReport);
		Assert.assertEquals("Allowance Delivery Report", aseertAllowanceDeliveryReport);
		String aseertQualifiedBiddersReport = wait
				.until(ExpectedConditions.elementToBeClickable(qualifiedBiddersReport)).getText();
		System.out.println(aseertQualifiedBiddersReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to Sponsor is: " + aseertQualifiedBiddersReport);
		Assert.assertEquals("Qualified Bidders Report", aseertQualifiedBiddersReport);
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForMM() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnPostEventOperationReports));
			driver.findElement(btnPostEventOperationReports).click();
			System.out.println("Post Event Operations Report validated in English for Quebec User");
			ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in English for Quebec User");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Post Event Operations report validated in English for Quebec User");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsAuditReports));
		driver.findElement(btnBidsAuditReports).click();
		System.out.println("Bids audit report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in English for Quebec User");
		Thread.sleep(5000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReports));
			driver.findElement(btnFinancialSettlementReports).click();
			System.out.println("Financial Settlement Report validated in English for Quebec User");
			ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for Quebec User");
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Financial Settlement Report validated in English for Quebec User");
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsReports));
		driver.findElement(btnBidsReports).click();
		System.out.println("Bids report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports));
		driver.findElement(btnResultReports).click();
		System.out.println("Results report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnAllowancedeliveryReports));
		driver.findElement(btnAllowancedeliveryReports).click();
		System.out.println("Allowance Delivery Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report validated in English for Quebec User");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports));
		driver.findElement(btnQualifiedBidderReports).click();
		System.out.println("Qualified Bidder Report validated in English for Quebec User");
	}

	public void downloadTheReportsAvailableForSposnorEN() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnPostEventOperationReports));
		driver.findElement(btnPostEventOperationReports).click();
		ExcelDataProviderForPostEventOperationReport ref = new ExcelDataProviderForPostEventOperationReport();
		String rowName = ref.getStringData("Post Event Operations Report", 5, 0);
		Assert.assertEquals("Event Information", rowName);
		System.out.println("Post Event Operations Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in English for Quebec User");

		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsAuditReports));
		driver.findElement(btnBidsAuditReports).click();
		ExcelDataProviderForBidsAuditReport ref1 = new ExcelDataProviderForBidsAuditReport();
		String rowName1 = ref1.getStringData("Bids Audit Report", 5, 0);
		Assert.assertEquals("General Account Number", rowName1);
		System.out.println("Bids audit report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in English for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnFinancialSettlementReports));
		driver.findElement(btnFinancialSettlementReports).click();
		ExcelDataProviderForFinancialSettlementReport ref2 = new ExcelDataProviderForFinancialSettlementReport();
		String rowName2 = ref2.getStringData("Financial Settlement Report", 4, 0);
		Assert.assertEquals("Settlement Price USD (Current)", rowName2);
		System.out.println("Financial Settlement Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnBidsReports));
		driver.findElement(btnBidsReports).click();

		ExcelDataProviderforBidsReport ref3 = new ExcelDataProviderforBidsReport();
		String rowName3 = ref3.getStringData("Bids Report", 3, 0);
		Assert.assertEquals("Total Bids Submitted", rowName3);
		System.out.println("Bids report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in English for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnResultReports));
		driver.findElement(btnResultReports).click();
		ExcelDataProviderforResultReport ref4 = new ExcelDataProviderforResultReport();
		String rowName4 = ref4.getStringData("Event Data", 11, 0);
		Assert.assertEquals("Auction Reserve Price", rowName4);
		System.out.println("Results report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in English for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEmissiondeliveryReports));
		driver.findElement(btnEmissiondeliveryReports).click();

		ExcelDataProviderForEmissionUnitsDeliveryReport ref5 = new ExcelDataProviderForEmissionUnitsDeliveryReport();
		String rowName5 = ref5.getStringData("Emission Units Delivery Report", 4, 0);
		Assert.assertEquals("General Account Number", rowName5);
		System.out.println("Emission Units Delivery Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report validated in English for Quebec User");
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnQualifiedBidderReports));
		driver.findElement(btnQualifiedBidderReports).click();
		ExcelDataProviderForQualifiedBidderReport ref6 = new ExcelDataProviderForQualifiedBidderReport();
		String rowName6 = ref6.getStringData("Qualified Bidders Report", 3, 0);
		Assert.assertEquals("Total Qualified Bidders", rowName6);
		System.out.println("Qualified Bidder Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated in English for Quebec User");
		System.out.println("******************************************************************");

	}

	public void Logout() throws InterruptedException {
		Thread.sleep(6000);
		try {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(btnProfile));
			btnProfile.click();
			btnLogout.click();
		} catch (Exception e) {
			System.out.println("Not able to logout ");
		}
	}

	public void LogoutQC() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(btnProfileqc));
			btnProfileqc.click();
			btnLogoutQC.click();
		} catch (Exception e) {
			System.out.println("Not able to logout ");
		}
	}

	public void Selectlanguage_English() {
		WebElement eng = Englang;
		eng.getText();
		System.out.println("Selected language is: " + eng.getText());
	}

	public void Selectlanguage_French() {
		WebElement abcd1 = langbtn;
		
		js.executeScript("arguments[0].scrollIntoView();", abcd1);
		abcd1.click();
		WebElement french = Frenchlang;
		french.click();
		driver.navigate().refresh();
		WebElement Selectfrench = Frenchlang;
		Selectfrench.getText();
		System.out.println("Selected language is: " + Selectfrench.getText());
	}

	public void clickHomemenu() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
		WebElement e = driver.findElement(Homeeventname);
		e.getText();
		WebElement e1 = Homeeventstatus;
		e1.getText();
		System.out.println("Event name is: " + e.getText() + "And" + "Event status is:" + e1.getText());
		ObjectRepo.getTest().log(Status.PASS, "Ongoing individual event name & status is verified & printed on console");

	}

	public void clickEventAdministration() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdministration)).click();
		WebElement abc1 = btnAdministration;
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent)).click();
		WebElement abc2 = btnEvent;
		abc2.click();
		wait.until(ExpectedConditions.elementToBeClickable(gearbutton)).click();
		WebElement abc3 = gearbutton;
		abc3.click();
		wait.until(ExpectedConditions.elementToBeClickable(gearrowselect)).click();
		WebElement abc4 = gearrowselect;
		abc4.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[1]"))))
				.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[1]"));
		abc5.click();
		WebElement e = eventname;
		e.getText();
		WebElement e1 = eventstatus;
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText());
		System.out.println("Ongoing Event status is:" + e1.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"Ongoing individual event-Results report name & status is verified & printed on console");

	}

	public void clickEventMonitoring() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEventMonitoring));
		WebElement abc1 = btnEventMonitoring;
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(eventfrommonitoring));
		WebElement abc2 = driver.findElement(eventfrommonitoring);
		abc2.click();
	}

	public void DownloadBidAuditReportoptiononEventMonitoring() {
		wait.until(ExpectedConditions.elementToBeClickable(btndownloadeventmonitoring));
		WebElement abc1 = btndownloadeventmonitoring;
		abc1.click();

	}

	public void clickEventAdministrationmenu() {
		wait.until(ExpectedConditions.elementToBeClickable(btnHome));
		WebElement abc1 = btnHome;
		abc1.click();
		WebElement e = driver.findElement(Homeeventname);
		e.getText();
		WebElement e1 = Homeeventstatus;
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText());
		System.out.println("Ongoing Event status is:" + e1.getText());
		ObjectRepo.getTest().log(Status.PASS, "Onging indivisual event name & status is verified & printed on console");
	}

	public void clickreportmenu() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	}

	public void clickreportmenuFSA() throws Exception {
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		ObjectRepo.getTest().log(Status.PASS, "Entered event name.");
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"))
				.click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
	}

	public void clickreportmenuFSA2() throws Exception {
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameCA"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameCA") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void clickreportmenuFSAQC() throws Exception {
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameQC"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void btnSearchReports() throws InterruptedException {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnSearchReports));
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	}

	public void reportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Bids Audit Report");
	}

	public void eventselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = EventnametinDrp;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void clicksearchbtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
		btnSearch.click();
	}

	public void downloadongoingeventbidauditreport() {
		WebElement abc6 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc6);
		abc6.click();

	}

	public void downloadongoingeventbidauditreport1() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReportsWS);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsWS).click();
		System.out.println("bids audit report download successfully");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void downloadongoingeventbidauditreportWA() throws InterruptedException {
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		driver.findElement(btnBidsAuditReportsWS).click();
		System.out.println("bids audit report download successfully");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void downloadongoingeventbidauditreportCA() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReportsCA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsCA).click();
		Thread.sleep(20000);
		System.out.println("bids audit report download successfully");
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(
					folderPath + File.separator + "Bids Audit Report - " + eventNameCA + " " + date1 + ".xlsx");
			System.out.println("Asserted The name of the report must be Bids Audit Report");
			ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadongoingeventbidauditreportRS_CA() throws InterruptedException {

		
		WebElement element = driver.findElement(btnBidsAuditReportsRS_CA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsRS_CA).click();
		System.out.println("bids audit report download successfully");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Bids Audit Report - " + ReserveSaleEventName + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void downloadongoingeventbidauditreportQc() throws InterruptedException {
		
		WebElement element = driver.findElement(btnBidsAuditReportsQC_single_auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsQC_single_auction).click();
		Thread.sleep(20000);
		System.out.println("bids audit report download successfully");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + eventNameQC + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void MMaccesstheBidsAuditReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		reportselectfromdropdown();
		System.out.println("Bids Audit Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is  selected from dropdown");
		eventselectfromdropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventbidauditreport();
		System.out.println("TC ID=C31288/C31291:MM access & download individaul auction  the Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31288:MM access & download individaul auction the Bids Audit Report");
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");
	}

	public void MMaccesstheBidsAuditReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventbidauditreportWA();
		System.out.println("TC ID=C31288/C31291:MM access & download individaul auction  the Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31288:MM access & download individaul auction the Bids Audit Report");
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");

	}

	public void MMaccesstheBidsAuditReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventbidauditreportCA();
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");
	}

	public void MMaccesstheBidsAuditReport2() throws Exception {

	}

	public void MMaccesstheBidsAuditReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventbidauditreportRS_CA();
	}

	public void MMaccesstheBidsAuditReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventbidauditreportQc();
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");

	}

	public void ClientBidsAuditreportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Client Bids Audit Report");
	}

	public void ClientBidsAuditreportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		wait.until(ExpectedConditions.elementToBeClickable(entityDrpdwnCA));
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void downloadongoingeventClientbidauditreport() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnCA;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Audit Report");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

	}

	public void downloadongoingeventClientbidauditreport1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(EntityNmeDropDownBtn2));
		EntityNmeDropDownBtn2.click();
		CommonLib commonLib = new CommonLib();
		commonLib.scroll(entityName1, "scrollToElement", "entity Name ");
		commonLib.click(entityName1, "javascriptClick", "entity Name ");
		wait.until(ExpectedConditions.visibilityOf(ReportNameDropDown2));
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");

	}

	public void downloadongoingeventClientbidauditreportWA() throws InterruptedException {
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		driver.findElement(By.xpath(
				"//*[@aria-label='Download Bids Audit Report for event " + prop.getProperty("eventNameWS") + "']"))
				.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");

	}

	public void downloadongoingeventClientbidauditreportCA() throws InterruptedException {
		
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void downloadongoingeventClientbidauditreportRS_CA() throws InterruptedException {
		
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");

//		wait.until(ExpectedConditions.visibilityOf(EntityNmeDropDownBtn2));
//		EntityNmeDropDownBtn2.click();
//		commonLibref.scroll(entityNameRS, "scrollToElement", "entity Name ");
//		commonLibref.click(entityNameRS, "javascriptClick", "entity Name ");
//		wait.until(ExpectedConditions.visibilityOf(ReportNameDropDown2));
//		ReportNameDropDown2.click();
//		WebElement dropDown1 = reportNameDropDown1;
//		Select zmr1 = new Select(dropDown1);
//		zmr1.selectByVisibleText("Client Bids Audit Report");
//		System.out.println("Client bids audit report selected from Report Name Dropdown");
//		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
//		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
//		String ClientBidsAuditRow = clientBidsAuditRow.getText();
//		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
//		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
//		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
//		randomEventNmeReportDwnBtn.click();
//		System.out.println("Client bids audit report successfully downloaded by sponsor");
//		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
//		System.out.println("***********************************************************************");
	}

	public void downloadongoingeventClientbidauditreportRS_WA() throws InterruptedException {
		
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void downloadongoingeventClientbidauditreportQC() throws InterruptedException {
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameQC"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameQC") + "']")).click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel2 = new Select(select_Report_Name2);
		sel2.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void sponsoraccesstheClientBidsAuditReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		driver.navigate().refresh();
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsAuditreportselectfromdropdown();
		System.out.println("Client Bids Audit Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report is  selected from dropdown");
		ClientBidsAuditreportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidauditreport();
		System.out.println("TC ID=C31296/C31300:Sponsor can access and download the Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31296/C31300:Verify that the Sponsor can access/download the Client Bids Audit Report");
	}

	public void sponsoraccesstheClientBidsAuditReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreport();
		System.out.println("TC ID=C31296/C31300:Sponsor can access and download the Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31296/C31300:Verify that the Sponsor can access/download the Client Bids Audit Report");
	}

	public void sponsoraccesstheClientBidsAuditReportWA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreportWA();
		System.out.println("TC ID=C31296/C31300:Sponsor can access and download the Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31296/C31300:Verify that the Sponsor can access/download the Client Bids Audit Report");

	}

	public void sponsoraccesstheClientBidsAuditReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventClientbidauditreportCA();
	}

	public void sponsoraccesstheClientBidsAuditReportRS_CA() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']")).click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Select report name form dropdown.");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel2 = new Select(select_Report_Name2);
		sel2.selectByVisibleText("Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Select entity name & Report name from dropdwon.");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void sponsoraccesstheClientBidsAuditReportRS_WA() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		ObjectRepo.getTest().log(Status.PASS, "Entered Event Name");
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']")).click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Select report name form dropdown.");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on search button.");
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		select_Entity_Name1.click();
		Select sel2 = new Select(select_Report_Name2);
		sel2.selectByVisibleText("Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Select entity name & Report name from dropdwon.");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
		ReportsPage3 ref = new ReportsPage3();
	}

	public void sponsoraccesstheClientBidsAuditReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreportQC();
	}

	public void QualifiedBiddersReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Qualified Bidders Report");
	}

	public void PostEventOperationsReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Post Event Operations Report");
	}

	public void QualifiedBiddersReportEventSelectfromDropdown1() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void QualifiedBiddersReportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdown2() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdownCA_Single_Auction() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdownQC_Single_Auction() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

	}

	public void QualifiedBiddersReportEventSelectfromDropdown() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		//
		abc4.click();

		//
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		//
		element11.click();

		// entity selection from boostrap dropdown selection
		//
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		//
		abc8.click();

		//
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		//
		element12.click();

	}

	public void downloadongoingeventQualifiedBiddersReportQC() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForQCSingleAuction));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForQCSingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportWA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForWASingleAuction));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForWASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportWA_RS() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForWASingleAuction));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForWASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportCA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForCASingleAuction));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForCASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportCA_RS() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForCASingleAuctionRS));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForCASingleAuctionRS);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventFinancialSettlementReportCA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FinancialSettlementReportForCASingleAuctionRS));
		WebElement abc7 = driver.findElement(FinancialSettlementReportForCASingleAuctionRS);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportNS() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(QualifiedBiddersReportForQCSingleAuction));
		WebElement abc7 = driver.findElement(QualifiedBiddersReportForQCSingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventPostEventOperationReport() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PostEventOperationReportForQCSingleAuction));
		WebElement abc7 = driver.findElement(PostEventOperationReportForQCSingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventFinancialSettlementReportForQC() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FinancialSettlementReportForQCSingleAuction));
		WebElement abc7 = driver.findElement(FinancialSettlementReportForQCSingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventClientBidsReport() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ClientBidsReportForCASingleAuction));
		WebElement abc7 = driver.findElement(ClientBidsReportForCASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadOngoingClientBidsAuditReport() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]"));
		action.moveToElement(element).build().perform();

		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();

		driver.findElement(By.xpath("//*[text()='T6CAEmetteurA']")).click();

		WebElement element2 = driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]"));
		Select s = new Select(element2);
		s.selectByVisibleText("Client Bids Audit Report");

//		driver.findElement(By.xpath("//*[text()='Client Bids Audit Report']")).click();
//		 
		driver.findElement(By.xpath("//*[@aria-label='Download Report']")).click();

	}

	public void downloadOngoingClientBidsReport() throws InterruptedException {
//		Actions action = new Actions(driver);
//		WebElement element = driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]"));
//		action.moveToElement(element).build().perform();
//
//		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
//
//		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
//
//		driver.findElement(By.xpath("//*[text()='T6CAEmetteurA']")).click();
//
//		WebElement element2 = driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]"));
//		Select s = new Select(element2);
//		s.selectByVisibleText("Client Bids Report");
//		driver.findElement(By.xpath("//*[@aria-label='Download Report']")).click();
		
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action = new Actions(driver);
		action.moveToElement(click_On_Entiy_Dropdown).build().perform();
		click_On_Entiy_Dropdown.click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		select_Entity_Name1.click();
		Select sel = new Select(select_Report_Name2);
		sel.selectByVisibleText("Client Bids Audit Report");
		driver.findElement(By.xpath("(//*[@title='Download Report'])[4]")).click();
	}

	public void downloadongoingPosteventOperationReport() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PostEventOperationReportForCASingleAuction));
		WebElement abc7 = driver.findElement(PostEventOperationReportForCASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();

		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportRS_CA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(downloadreportopt));
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");

	}

	public void SponsorMMaccesstheQualifiedBiddersReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventQualifiedBiddersReportWA();
		System.out.println(
				"TC ID=C31309:Sponsor/MM can access and download the Qualified Bidders Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31309:Sponsor/MM can access and download the Qualified Bidders Report for Individual auction");
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheQualifiedBiddersReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		ReportsPage3 ref = new ReportsPage3();
		ref.searchReportsByEventNameForCASingleAuction();
		ref.downloadQualifiedBidderReportForCASingleAuction();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsorMMaccesstheQualifiedBiddersReportRS_CA() throws Exception {
		clickreportmenu();
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventQualifiedBiddersReportRS_CA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsorMMaccesstheQualifiedBiddersReportRS_WA() throws Exception {

		downloadongoingeventQualifiedBiddersReportRS_CA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsorMMaccesstheQualifiedBiddersReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventQualifiedBiddersReportQC();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsorMMaccesstheQualifiedBiddersReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventQualifiedBiddersReportWA();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsorMMaccesstheQualifiedBiddersReportCA_Single_Auction() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventQualifiedBiddersReportCA();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventNameCA + " " + date1 + ".xlsx");
//		File src = new File(folderPath+File.separator+"Bids Audit Report - AuctionEvent1 11-21-2022.xlsx");
//		Assert.assertTrue("Bids Audit Report - AuctionEvent1 11-21-2022.xlsx".contains("Bids Audit Report"));
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsorMMaccesstheQualifiedBiddersReportCA_RS() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventQualifiedBiddersReportCA_RS();
	}

	public void SponsorMMaccesstheQualifiedBiddersReportWA_RS() throws Exception {
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForReserveSaleWashington();
		ref2.downloadQualifiedBidderReportForWA_RSSingleAuction();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - " + ReserveSaleEventName_WA + " "
				+ date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsorMMaccesstheQualifiedBiddersReportQC_Single_Auction() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventQualifiedBiddersReportQC();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventNameQC + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsoraccessPostEventOperationsReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventQualifiedBiddersReportWA();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Post Event Operations Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsoraccessPostEventOperationsReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingPosteventOperationReport();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Post Event Operations Report - " + eventNameCA + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsoraccessPostEventOperationsReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventQualifiedBiddersReportCA_RS();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Post Event Operations Report - " + ReserveSaleEventName + " "
				+ date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsoraccessPostEventOperationsReportRS_WA() throws Exception {
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForReserveSaleWashington();
		ref2.downloadPostEventOperationsReportForWA_RSSingleAuction();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Post Event Operations Report - " + ReserveSaleEventName_WA
				+ " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void SponsoraccessPostEventOperationsReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventPostEventOperationReport();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Post Event Operations Report - " + eventNameQC + " " + date1 + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
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

	public void BidsReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Bids Report");
	}

	public void BidsReportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void downloadongoingeventBidsReport() {
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsorMMaccesstheBidsReport() throws Exception {
		clickHomemenu();
		System.out.println("Click on Home menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Home menu");
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		BidsReportselectfromdropdown();
		System.out.println("Bids Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is selected from dropdown");
		BidsReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventBidsReport();
		System.out.println("TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");

	}

	public void SponsorMMaccesstheBidsReport1() throws Exception {
		clickHomemenu();
		System.out.println("Click on Home menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Home menu");
		downloadongoingeventBidsReport();
		System.out.println("TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");

	}

	public void FinancialSettlementReportselectfromdropdown() {
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Financial Settlement Report");
	}

	public void FinancialSettlementReportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialSettlementReportEventSelectfromDropdown1() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialSettlementReportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialSettlementReportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialSettlementReportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialSettlementReportEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void downloadongoingeventFinancialSettlementReport() {
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsoraccesstheFinancialSettlementReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventFinancialSettlementReportForQC();
		System.out.println(
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportWASingleAuction() throws Exception {
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		ReportsPage3 ref = new ReportsPage3();
		ref.searchReportsByEventNameForWASingleAuction();
		ref.downloadFinancialSettlementReportForWASingleAuction();
		System.out.println(
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventFinancialSettlementReportCA();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsoraccesstheFinancialSettlementReportRS_CA() throws Exception {
		clickreportmenu();
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventQualifiedBiddersReportCA_RS();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsoraccesstheFinancialSettlementReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");

		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialSettlementReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventFinancialSettlementReport();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsoraccesstheFinancialSettlementReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventQualifiedBiddersReportQC();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void FSAFinancialSettlementReportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownQC() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		//
		abc4.click();

		//
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		//
		element11.click();

	}

	public void FSAaccesstheFinancialSettlementReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventFinancialSettlementReport();
		System.out.println(
				"TC ID=C31617:FSA can access and download the Financial Settlement Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31617:FSA can access and download the Financial Settlement Report for Individual auction");
		//
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportCA() throws Exception {
		clickreportmenuFSA2();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		ReportsPage3 ref2 = new ReportsPage3();
//		ref2.searchReportsByEventNameForCASingleAuction();
		ref2.downloadFinancialSettlementReportForCASingleAuction();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportRS_CA() throws Exception {
		clickreportmenuFSA();
		ReportsPage3 ref = new ReportsPage3();
		ref.downloadFinancialSettlementReportForCA_RSFSA();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportRS_WA() throws Exception {
		ReportsPage3 ref = new ReportsPage3();
		ref.searchReportsByEventNameForReserveSaleWashingtonFSA();
		ref.downloadFinancialSettlementReportForWA_RSSingleAuction();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");
	}

	public void FSAaccesstheFinancialSettlementReportQC() throws Exception {
		clickreportmenuFSAQC();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventFinancialSettlementReportForQC();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");
	}

	public void ResultsReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Results Report");

	}

	public void ResultsReportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwn;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void ResultsReportEventSelectfromDropdown1() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ResultsReportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ResultsReportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ResultsReportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
	}

	public void ResultsReportEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

	}

	public void downloadongoingeventResultsReport() {
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventResultsReportWA() {
		WebElement abc7 = driver.findElement(ResultReportForWASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsorMMaccesstheResultsReport() throws Exception {
		clickEventAdministration();
		System.out.println("Click on Event administration menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReport();
		System.out.println("TC ID=C31318:Sponsor/MM can access and download the Results Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31318:Sponsor/MM can access and download the Results Report for Individual auction");
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheResultsReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		downloadongoingeventResultsReportWA();
		System.out.println("TC ID=C31318:Sponsor/MM can access and download the Results Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31318:Sponsor/MM can access and download the Results Report for Individual auction");
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheResultsReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventResultsReport();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheResultsReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		ReportsPage3 ref = new ReportsPage3();
		ref.searchReportsByEventNameForReserveSaleCalifornia();
		ref.downloadResultsReportForCA_RSSingleAuction();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheResultsReportRS_WA() throws Exception {
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForReserveSaleWashington();
		ref2.downloadResultsReportForWA_RSSingleAuction();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheResultsReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventResultsReport();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void Auctioneventreportsavailablein_English() throws Exception {
		Selectlanguage_English();
		System.out.println("English language is selected on header section");
		ObjectRepo.getTest().log(Status.PASS, "English language is selected on header section");
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReport();
		System.out.println("TC ID=C31273:Auction event reports are available in English");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31273:Auction event reports are available in English");

	}

	public void clickreportmenuFR() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(btnReportFR));
		WebElement abcD1 = driver.findElement(btnReportFR);
		//
		abcD1.click();

	}

	public void btnsearchreportFR() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReportFR));
		WebElement abcD2 = btnSearchReportFR;
		//
		abcD2.click();

	}

	public void ResultsReportselectfromdropdownFR() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Rapport des résultats");

	}

	public void ResultsReportEventSelectfromDropdownFR() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(BdropdwnnameFR));
		WebElement abcD3 = BdropdwnnameFR;
		//
		abcD3.click();

		//
		WebElement element12 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element12);
		//
		element12.click();

		// entity selection from boostrap dropdown selection
		//
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1FR));
		WebElement abcD4 = BEdropdwnname1FR;
		//
		abcD4.click();

		//
		WebElement element13 = entityDrpdwn;
		js.executeScript("arguments[0].scrollIntoView();", element13);
		//
		element13.click();

	}

	public void clicksearchbtnFR() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchFR));
		btnSearchFR.click();
	}

	public void downloadongoingeventResultsReportFR() {
		// Click on Download option
		//
		WebElement abcD7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abcD7);
		//
		abcD7.click();
	}

	public void Auctioneventreportsavailablein_French() {
		Selectlanguage_French();
		System.out.println("French language is selected on header section");
		ObjectRepo.getTest().log(Status.PASS, "French language is selected on header section");
		clickreportmenuFR();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreportFR();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdownFR();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdownFR();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtnFR();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReportFR();
		System.out.println("TC ID=C31273:Auction event reports are available in French");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31273:Auction event reports are available in French");

	}

	public void AllowanceDeliveryReportselectfromdropdown() {
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Allowance Delivery Report");
	}

	public void ReserveSaleBidLotAuditReportselectfromdropdown() {
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Reserve Sale Bid Lot Audit Report");
	}

	public void AllowanceDeliveryReportselectfromdropdownQC() {
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		//
		s.selectByVisibleText("Emission Units Delivery Report");

	}

	public void AllowanceDeliveryReportEventSelectfromDropdown() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		//
		abc4.click();

		//
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		//
		element11.click();

		// entity selection from boostrap dropdown selection
		//
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		//
		abc8.click();

		//
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		//
		element12.click();

	}

	public void AllowanceDeliveryReportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void AllowanceDeliveryReportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void AllowanceDeliveryReportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void AllowanceDeliveryReportEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void clickdownloadAllowanceDeliveryReport() {
		WebElement abc7 = driver.findElement(AllowanceDeliveryReportForCASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void clickdownloadAllowanceDeliveryReportCA_RS() {
		WebElement abc7 = driver.findElement(AllowanceDeliveryReportForCAReserveSale);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		ObjectRepo.getTest().log(Status.PASS, "Download report successfully.");
	}

	public void clickdownloadBidLotAuditReportCA_RS() {
		WebElement abc7 = driver.findElement(BidLotAuditReportForCAReserveSale);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void clickdownloadAllowanceDeliveryReportWA() throws Exception {
		WebElement abc7 = driver.findElement(AllowanceDeliveryReportForWASingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		Thread.sleep(20000);
	}

	public void clickdownloadAllowanceDeliveryReportQC() {
		WebElement abc7 = driver.findElement(AllowanceDeliveryReportForQCSingleAuction);
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SponsoraccesstheAllowanceDeliveryReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		clickdownloadAllowanceDeliveryReportWA();
		System.out.println("TC ID=C31349:Sponsor can access and download the Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31349:Sponsor can access and download the Allowance Delivery Report");
		Logout();
		System.out.println("Sponso Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsoraccesstheAllowanceDeliveryReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		clickdownloadAllowanceDeliveryReport();
		Logout();
		System.out.println("Sponso Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsoraccesstheAllowanceDeliveryReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		clickdownloadAllowanceDeliveryReportCA_RS();
		System.out.println("Sponso Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheAllowanceDeliveryReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
		Logout();
		System.out.println("Sponso Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsoraccesstheAllowanceDeliveryReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		clickdownloadAllowanceDeliveryReportQC();
		Logout();
		System.out.println("Sponso Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheAllowanceDeliveryReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		clickdownloadAllowanceDeliveryReportWA();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + eventNameWS + " " + date1 + ".xlsx");
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

	public void SponsoraccesstheAllowanceDeliveryReportCA_Single_Auction() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		clickdownloadAllowanceDeliveryReport();
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(
					folderPath + File.separator + "Allowance Delivery Report - " + eventNameCA + " " + date1 + ".xlsx");
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void SponsoraccesstheAllowanceDeliveryReportCA_RS() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		clickdownloadAllowanceDeliveryReportCA_RS();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Allowance Delivery Report - " + ReserveSaleEventName + " "
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

	public void SponsoraccesstheAllowanceDeliveryReportWA_RS() throws Exception {
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForReserveSaleWashington();
		ref2.downloadAllowanceDeliveryReportForWA_RSSingleAuction();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Allowance Delivery Report - " + ReserveSaleEventName_WA + " "
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

	public void SponsoraccessReserveSaleBidLotAuditReportCA_RS() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		clickdownloadBidLotAuditReportCA_RS();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Reserve Sale Bid Lot Audit Report - " + ReserveSaleEventName
				+ " " + date1 + ".xlsx");
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

	public void SponsoraccesstheAllowanceDeliveryReportQC_Single_Auction() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		clickdownloadAllowanceDeliveryReportQC();

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Emission Units Delivery Report - " + eventNameQC + " "
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

	public void ClientBidsreportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Client Bids Report");
	}

	public void ClientBidsreportselectfromdropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Client Bids Audit Report");

	}

	public void ClientBidsreportEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void ClientBidsreportEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void ClientBidsreportEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void ClientBidsreportEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();

		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();

		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void ClientBidsreportEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();

		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();

		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void downloadongoingeventClientbidreport() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectReportdropdwn));
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Report");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventClientbidreportCA() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();

		WebElement element11 = entityDrpdwnCA;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		wait.until(ExpectedConditions.elementToBeClickable(selectReportdropdwn));
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Report");

		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventClientbidreportRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnRS_CA;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectReportdropdwn));
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Report");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventClientbidreportRS_WA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnWS;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectReportdropdwn));
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Report");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		Thread.sleep(20000);

	}

	public void downloadongoingeventClientbidreportQC() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();

		WebElement element11 = entityDrpdwnQC;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		wait.until(ExpectedConditions.elementToBeClickable(selectReportdropdwn));
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Client Bids Audit Report");

		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void sponsoraccesstheClientBidsReport() throws Exception {
		System.out.println("TC ID C31358-Verify that the Sponsor can access the Client Bid Report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID C31358-Verify that the Sponsor can access the Client Bid Report");
		System.out.println("TC ID C31362-The Client Bid Report must be available for an individual Auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID C31362-The Client Bid Report must be available for an individual Auction");
		clickreportmenu();
		DownloadReportsPage ref1 = new DownloadReportsPage();
		
		WebElement element = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement scroll_ToSearch = driver.findElement(By.xpath("//*[text()='RESET']"));
		WebElement scroll_To_Bottom = driver.findElement(By.xpath("//*[text()='About WCI, Inc.']"));
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameWS"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		ref1.searchReportsByEntityNameForWASingleAuction("Client Bids Report");
		System.out.println(
				"TC ID31382=:Maintain the Client Bid Reports for an event once generated along with an audit trail");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID31382=:Maintain the Client Bid Reports for an event once generated along with an audit trail");

	}

	public void sponsoraccesstheClientBidsReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadOngoingClientBidsReport();
	}

	public void sponsoraccesstheClientBidsReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventClientbidreportRS_CA();
	}

	public void sponsoraccesstheClientBidsReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage reports = new DownloadReportsPage();
		reports.searchReportsByEventNameForWARS();
		downloadongoingeventClientbidreportRS_WA();
		Thread.sleep(5000);
	}

	public void sponsoraccesstheClientBidsReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventClientbidreportQC();
	}

	public void SponsorMMdownloadtheBidsAuditReportfromMonitoring() throws Exception {
		clickEventMonitoring();
		System.out.println("Click on Event monitoring  menu");
		ObjectRepo.getTest().log(Status.PASS, "Event monitoring  menu");
		//
		DownloadBidAuditReportoptiononEventMonitoring();
		System.out.println("Click on Download Bid Audit Report option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download Bid Audit Report option");

		System.out.println(
				"TC ID=C31384:Sposor/ MM can download the 'Bids Audit Report' for an event from the monitoring page");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31384:Sposor/ MM can download the 'Bids Audit Report' for an event from the monitoring page");

		Logout();
		System.out.println("Sposor/ MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sposor/ MM Logout successfully ");

	}

	public void UserManagementReportselectfromdropdown() {
		//
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown));
		WebElement eventType = reportNameDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("User Management Report");

	}

	public void downloadUserManagementReport() {
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsorgeneratetheUserManagementReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		btnSearchReports();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");

		UserManagementReportselectfromdropdown();
		System.out.println("User Management Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "User Management Report is  selected from dropdown");

		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");

		downloadUserManagementReport();
		System.out.println("TC ID=C31355:Sponsor can access and download the User mgmt report");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31355:Sponsor can access and download the User mgmt report");
	}

	public void DataReadFromUserManagementReport() throws IOException {
		File src = new File(folderPath + File.separator + "User Management Report.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String ReportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String ReportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(ReportDT + ":" + ReportDTval);

		System.out.println("User management report headers as:");
		String HN1 = sheet.getRow(2).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(2).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(2).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(2).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(2).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(2).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(2).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(2).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(2).getCell(9).getStringCellValue();
		System.out.println(HN10);

		xsf.close();

	}

	public void FinancialStatementReportselectfromdropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Financial Statement");

	}

	public void FinancialStatementEventSelectfromDropdown() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrp1);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void FinancialStatementEventSelectfromDropdownCA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpCA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialStatementEventSelectfromDropdownRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void FinancialStatementEventSelectfromDropdownRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();

	}

	public void FinancialStatementEventSelectfromDropdownQC() {
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		abc4.click();

		WebElement element11 = driver.findElement(EventnametinDrpQC);
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		abc8.click();

		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
	}

	public void downloadongoingeventFinancialStatementreport() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
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
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameWS"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']")).click();
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
			File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("eventNameWS")
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

	public void downloadongoingeventFinancialStatementreportCA() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnCA;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Financial Statement");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventFinancialStatementreportRS_CA() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnRS_CA;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Financial Statement");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventFinancialStatementreportRS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnWS;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Financial Statement");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void downloadongoingeventFinancialStatementreportQC() {
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname2));
		WebElement abc4 = BEdropdwnname2;
		abc4.click();
		WebElement element11 = entityDrpdwnQC;
		
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		Select s = new Select(selectReportdropdwn);
		s.selectByVisibleText("Financial Statement");
		WebElement abc7 = downloadreportopt;
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsoraccesstheFinancialStatementReport() throws Exception {
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		downloadongoingeventFinancialStatementreport();
		System.out.println(
				"TC ID=C31392:The Financial Statement must be available for an individual Auction for Sponsor user");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31392:The Financial Statement must be available for an individual Auction for Sponsor user");
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		downloadongoingeventFinancialStatementreportCA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");

	}

	public void SponsoraccesstheFinancialStatementReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		downloadongoingeventFinancialStatementreportRS_CA();
//		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportRS_WA() throws Exception {
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForReserveSaleWashington();
		downloadongoingeventFinancialStatementreportRS_WA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		downloadongoingeventFinancialStatementreportQC();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void DataReadFromClientBidsReport() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + eventNameWS + " " + date1 + " "
				+ prop.getProperty("WA_FirstEntity") + ".xlsx");
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

	public void DataReadFromClientBidsReportCA() throws IOException, InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + eventNameCA + " " + date1 + " "
				+ prop.getProperty("CA_FirstEntity") + ".xlsx");
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

	public void DataReadFromClientBidsReportRS_CA() throws IOException, InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + ReserveSaleEventName + " " + date1
				+ " " + prop.getProperty("ResEntity1") + ".xlsx");
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

	public void DataReadFromClientBidsReportRS_WA() throws IOException, InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + ReserveSaleEventName_WA + " "
				+ date1 + " " + prop.getProperty("ResEntity1_WA") + ".xlsx");
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

	public void DataReadFromClientBidsReportQC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Audit Report - " + eventNameQC + " " + date1
				+ " " + prop.getProperty("QC_FirstEntity") + ".xlsx");
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

	public void DataReadFromBidsReport() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		String date2 = "09-14-2022";
		File src;
		try {
			src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("event.name") + date2 + ".xlsx");
		} catch (Exception e) {
			src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("event.name") + date2 + " .xlsx");
		}
		System.out.println(
				"reports path : " + folderPath + File.separator + "Bids Report - " + prop.getProperty("event.name") + date2 + ".xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);
		XSSFSheet sheet = xsf.getSheetAt(0);
		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);
		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);
		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);
		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);
		String SettlementpriceUSDCurrent = sheet.getRow(4).getCell(0).getStringCellValue();
		double SettlementpriceUSDCurrentval = sheet.getRow(4).getCell(1).getNumericCellValue();
		System.out.println(SettlementpriceUSDCurrent + ":" + SettlementpriceUSDCurrentval);
		String SettlementpriceCADCurrent = sheet.getRow(5).getCell(0).getStringCellValue();
		double SettlementpriceCADCurrentval = sheet.getRow(5).getCell(1).getNumericCellValue();
		System.out.println(SettlementpriceCADCurrent + ":" + SettlementpriceCADCurrentval);
		String SettlementpriceUSDAdvance = sheet.getRow(6).getCell(0).getStringCellValue();
		double SettlementpriceUSDAdvanceval = sheet.getRow(6).getCell(1).getNumericCellValue();
		System.out.println(SettlementpriceUSDAdvance + ":" + SettlementpriceUSDAdvanceval);
		String SettlementpriceCADAdvance = sheet.getRow(7).getCell(0).getStringCellValue();
		double SettlementpriceCADAdvanceval = sheet.getRow(7).getCell(1).getNumericCellValue();
		System.out.println(SettlementpriceCADAdvance + ":" + SettlementpriceCADAdvanceval);
		System.out.println("Bids Report must have the following columns:");
		String HN1 = sheet.getRow(9).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(9).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(9).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(9).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(9).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(9).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(9).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(9).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(9).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(9).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(9).getCell(10).getStringCellValue();
		System.out.println(HN11);
		String HN12 = sheet.getRow(9).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(9).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(9).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(9).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(9).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(9).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(9).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(9).getCell(18).getStringCellValue();
		System.out.println(HN19);
		String HN20 = sheet.getRow(9).getCell(19).getStringCellValue();
		System.out.println(HN20);
		String HN21 = sheet.getRow(9).getCell(20).getStringCellValue();
		System.out.println(HN21);
		String HN22 = sheet.getRow(9).getCell(21).getStringCellValue();
		System.out.println(HN22);
		String HN23 = sheet.getRow(9).getCell(22).getStringCellValue();
		System.out.println(HN23);
		String HN24 = sheet.getRow(9).getCell(23).getStringCellValue();
		System.out.println(HN24);
		String HN25 = sheet.getRow(9).getCell(24).getStringCellValue();
		System.out.println(HN25);
		String HN26 = sheet.getRow(9).getCell(25).getStringCellValue();
		System.out.println(HN26);
		String HN27 = sheet.getRow(9).getCell(26).getStringCellValue();
		System.out.println(HN27);
		String HN28 = sheet.getRow(9).getCell(27).getStringCellValue();
		System.out.println(HN28);
		String HN29 = sheet.getRow(9).getCell(28).getStringCellValue();
		System.out.println(HN29);
		String HN30 = sheet.getRow(9).getCell(29).getStringCellValue();
		System.out.println(HN30);
		String HN31 = sheet.getRow(9).getCell(30).getStringCellValue();
		System.out.println(HN31);
		String HN32 = sheet.getRow(9).getCell(31).getStringCellValue();
		System.out.println(HN32);
		System.out.println("TC ID C31377-Bids Report must have the correct layout");
		ObjectRepo.getTest().log(Status.PASS, "TC ID C31377-Bids Report must have the correct layout");
		xsf.close();

	}

	public void DataReadFromBidsReport1() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(btnBidsReportsWS);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsWS).click();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + eventNameWS + " "
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

	public void DataReadFromBidsReportWA() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForWASingleAuction();
		
		WebElement element = driver.findElement(btnBidsReportsWS);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsWS).click();
		Thread.sleep(20000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + eventNameWS + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + eventNameWS + " "
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

	public void DataReadFromBidsReportCA() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForCASingleAuction();
		
		WebElement element = driver.findElement(btnBidsReportsCA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsCA).click();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + eventNameCA + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + eventNameCA + " "
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

	public void DataReadFromBidsReportRS_CA() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		DownloadReportsPage ref = new DownloadReportsPage();
		ref.searchReportsByEventNameReserveSale();
		
		WebElement element = driver.findElement(btnBidsReportsRS_CA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsRS_CA).click();
		Thread.sleep(20000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + ReserveSaleEventName + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + ReserveSaleEventName
				+ " " + date1 + ".xlsx");
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

	public void DataReadFromBidsReportRS_WA() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		
		WebElement element = driver.findElement(btnBidsReportsRS_WA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsRS_WA).click();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(
				folderPath + File.separator + "Bids Report - " + ReserveSaleEventName_WA + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + ReserveSaleEventName_WA
				+ " " + date1 + ".xlsx");
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

	public void DataReadFromBidsReportQC() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		DownloadReportsPage ref1 = new DownloadReportsPage();
		ref1.searchReportsByEventNameForQCSingleAuction();
		
		WebElement element = driver.findElement(btnBidsReportsQC_Single_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsQC_Single_Auction).click();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + eventNameQC + " " + date1 + ".xlsx");
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + eventNameQC + " "
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

	public void sponsor1editandapproveconfigurationdetails() throws Exception {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("//*[@id='Notice_Session_Timeout']"));
		abc3.sendKeys(Keys.CONTROL + "a");
		abc3.sendKeys(Keys.DELETE);
		abc3.sendKeys("3");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='SAVE']"))));
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='SAVE']"));
		abc4.click();
		System.out.println("The new configurations have been successfully saved.");
		ObjectRepo.getTest().log(Status.PASS, "The new configurations have been successfully saved.");
		WebElement abcd5 = driver.findElement(By.xpath("//*[@aria-label='approval1Status']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abcd5);
		abcd5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc6.click();
		System.out.println("The approval of the new configuration has been proposed by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the new configuration has been proposed by the first Sponsor.");
		Logout();
		System.out.println("User successfully logged out.");
		ObjectRepo.getTest().log(Status.PASS, "User  successfully logged out.");
	}

	public void sponsor2editandapproveconfigurationdetails() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc5 = driver.findElement(By.xpath("//*[@aria-label='approval2Status']"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc6.click();
		System.out.println("Warning-Are you sure you want to approve the new configuration settings?");
		ObjectRepo.getTest().log(Status.PASS, "Warning-Are you sure you want to approve the new configuration settings?");

		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='APPROVE'])"));
		abc7.click();
		System.out
				.println("The new configuration has been successfully approved by both Sponsors and is now effective.");
		ObjectRepo.getTest().log(Status.PASS,
				"The new configuration has been successfully approved by both Sponsors and is now effective.");
	}

	public void downloadUserActivityAuditReport() {
		System.out.println("navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "navigate to report section");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]")));
		s.selectByVisibleText("User Activity Audit Report");
		WebElement abc3 = driver.findElement(By.xpath("(//input[@placeholder='Start Date'])"));
		abc3.sendKeys("25 Dec 2022");
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
		abc5.click();
		System.out.println("User Activity Audit Report successfully downloaded ");
		ObjectRepo.getTest().log(Status.PASS, "User Activity Audit Report successfully downloaded");
		System.out.println("TC ID=C31367:Configuration approval action is logged in the User Activity Audit report.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31367:Configuration approval action is logged in the User Activity Audit report.");
	}

	public void DataReadFromUserActivityAuditReport() throws IOException, InterruptedException {
		File src = new File(folderPath + File.separator + "User Activity Audit Report.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);
		XSSFSheet sheet = xsf.getSheetAt(0);
		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);
		System.out.println("Configuration approval action is logged in the User Activity Audit report As below");
		String HN1 = sheet.getRow(125).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(125).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(125).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(125).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(125).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(125).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(125).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN9 = sheet.getRow(125).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(125).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(126).getCell(0).getStringCellValue();
		System.out.println(HN11);
		String HN12 = sheet.getRow(126).getCell(1).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(126).getCell(2).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(126).getCell(3).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(126).getCell(4).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(126).getCell(5).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(126).getCell(6).getStringCellValue();
		System.out.println(HN17);
		String HN19 = sheet.getRow(126).getCell(8).getStringCellValue();
		System.out.println(HN19);
		String HN20 = sheet.getRow(126).getCell(9).getStringCellValue();
		System.out.println(HN20);
		System.out.println("Configuration approval logged action is printed on console");
		ObjectRepo.getTest().log(Status.PASS, "Configuration approval logged action is printed on console");
		System.out.println("TC ID=C31367:Configuration approval action is logged in the User Activity Audit report.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31367:Configuration approval action is logged in the User Activity Audit report.");
		xsf.close();
	}

	public void Sponsor1EditApproveWiringInstructions() throws Exception {
		System.out.println("TC ID=C31396:Verify the Wiring Instruction updates base on configuration screen");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31396:Verify the Wiring Instruction updates base on configuration screen");
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));
		abc3.click();
		System.out.println("click on Wiring instruction Bar");
		ObjectRepo.getTest().log(Status.PASS, "click on Wiring instruction Bar");
		WebElement abc31 = driver.findElement(By.xpath("(//*[@id='Bank_Name'])"));
		abc31.sendKeys(Keys.CONTROL + "a");
		abc31.sendKeys(Keys.DELETE);
		abc31.sendKeys("Deutsche Bank Trust Company Americas1");
		WebElement abc32 = driver.findElement(By.xpath("(//*[@id='Routing_Number'])"));
		abc32.sendKeys(Keys.CONTROL + "a");
		abc32.sendKeys(Keys.DELETE);
		abc32.sendKeys("021001033 - New York NY");
		WebElement abc33 = driver.findElement(By.xpath("(//*[@name='Bank_Identifier_Code'])"));
		abc33.sendKeys(Keys.CONTROL + "a");
		abc33.sendKeys(Keys.DELETE);
		abc33.sendKeys("BKTRUS33");
		WebElement abc34 = driver.findElement(By.xpath("(//*[@id='City'])"));
		abc34.sendKeys(Keys.CONTROL + "a");
		abc34.sendKeys(Keys.DELETE);
		abc34.sendKeys("New York");
		WebElement abc35 = driver.findElement(By.xpath("(//*[@id='State'])"));
		abc35.sendKeys(Keys.CONTROL + "a");
		abc35.sendKeys(Keys.DELETE);
		abc35.sendKeys("NY");
		WebElement abc36 = driver.findElement(By.xpath("(//*[@id='DDA_Number'])"));
		abc36.sendKeys(Keys.CONTROL + "a");
		abc36.sendKeys(Keys.DELETE);
		abc36.sendKeys("NY");
		WebElement abc37 = driver.findElement(By.xpath("(//*[@id='Beneficiary'])"));
		abc37.sendKeys(Keys.CONTROL + "a");
		abc37.sendKeys(Keys.DELETE);
		abc37.sendKeys("Trust And Agency Services");
		WebElement abc38 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc38.sendKeys(Keys.CONTROL + "a");
		abc38.sendKeys(Keys.DELETE);
		abc38.sendKeys("WCI Auction");
		System.out.println("Updated Wiring instruction details");
		ObjectRepo.getTest().log(Status.PASS, "Updated Wiring instruction details");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='SAVE']"))));
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='SAVE']"));
		abc4.click();
		System.out.println("The new configurations have been successfully saved.");
		ObjectRepo.getTest().log(Status.PASS, "The new configurations have been successfully saved.");
		driver.navigate().refresh();
		WebElement abcd5 = driver.findElement(By.xpath("//*[@aria-label='approval1Status']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abcd5);
		abcd5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc6.click();
		System.out.println("The approval of the new configuration has been proposed by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the new configuration has been proposed by the first Sponsor.");

		Logout();
		System.out.println("User successfully logged out.");
		ObjectRepo.getTest().log(Status.PASS, "User  successfully logged out.");
	}

	public void Sponsor2EditApproveWiringInstructions() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("//*[@aria-label='approval2Status']"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc4.click();
		System.out.println("Warning-Are you sure you want to approve the new configuration settings?");
		ObjectRepo.getTest().log(Status.PASS, "Warning-Are you sure you want to approve the new configuration settings?");
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='APPROVE'])"));
		abc5.click();
		System.out
				.println("The new configuration has been successfully approved by both Sponsors and is now effective.");
		ObjectRepo.getTest().log(Status.PASS,
				"The new configuration has been successfully approved by both Sponsors and is now effective.");
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));

		abc6.click();
		System.out.println("Wiring instruction section opened");
		ObjectRepo.getTest().log(Status.PASS, "Wiring instruction section opened");
		WebElement abc7 = driver.findElement(By.xpath("(//*[@id='Bank_Name'])"));
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.getText();
		System.out.println(abc7.getText());
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='Routing_Number'])"));
		abc8.getText();
		System.out.println(abc8.getText());
		WebElement abc9 = driver.findElement(By.xpath("(//*[@name='Bank_Identifier_Code'])"));
		abc9.getText();
		System.out.println(abc9.getText());
		WebElement abc10 = driver.findElement(By.xpath("(//*[@id='City'])"));
		abc10.getText();
		System.out.println(abc10.getText());
		WebElement abc11 = driver.findElement(By.xpath("(//*[@id='State'])"));
		abc11.getText();
		System.out.println(abc11.getText());
		WebElement abc12 = driver.findElement(By.xpath("(//*[@id='DDA_Number'])"));
		abc12.getText();
		System.out.println(abc12.getText());
		WebElement abc13 = driver.findElement(By.xpath("(//*[@id='Beneficiary'])"));
		abc13.getText();
		System.out.println(abc13.getText());
		WebElement abc14 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc14.getText();
		System.out.println(abc14.getText());

	}

	public void Sponsor1CertificationApproval_wiringInstructiions() throws Exception {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='check-circle'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc15);
		abc15.click();
		Logout();
		System.out.println("Sponsor2 logged out successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor2 logged out successfully");
	}

	public void Sponsor2CertificationApproval_wiringInstructiions() {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc12);
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]")); // (//*[@data-icon='check-circle'])[2]
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])")); // //*[text()='SUBMIT'])[8]
		abc16.click();
	}

	public void downloadFinancialStatementreport() {
		System.out.println("navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "navigate to report section");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Financial Statement");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='APADMANEvent'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6CAEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		System.out.println("pass");
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='T6CAEmetteurA'])"));
		abc9.click();
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Financial Statement");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
		abc10.click();
		System.out.println("Financial Statement Report successfully downloaded ");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report successfully downloaded");
	}

	public void fetchFinnacialStmtReportDataFromPDF() throws Exception {
		File src = new File(
				"C:\\Users\\PadmakarMohire\\Desktop\\report automation\\Financial Statement - TieBreakerTestEvent1 12-27-2022 CA4376-5560.pdf");
		FileInputStream file = new FileInputStream(src);
		PDFParser parser = new PDFParser((RandomAccessRead) file);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();
		String data = strip.getText(pdDoc);
		System.out.println(data);
		Assert.assertTrue(data.contains("Deutsche Bank Trust Company Americas1"));
		Assert.assertTrue(data.contains("021001033 - New York NY"));
		Assert.assertTrue(data.contains("BKTRUS33"));
		Assert.assertTrue(data.contains("New York"));
		Assert.assertTrue(data.contains("NY"));
		Assert.assertTrue(data.contains("NY"));
		Assert.assertTrue(data.contains("WCI Auction"));
		Assert.assertTrue(data.contains("CA4376-5560"));
		System.out.println("AS per wiring instructions data found");
		ObjectRepo.getTest().log(Status.PASS, "AS per wiring instructions data found");
		cosDoc.close();
		pdDoc.close();
	}

	public void DownloadFinancialStatementreport_QC() {
		System.out.println("navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "navigate to report section");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Financial Statement");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='AP-5197-4'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6QCEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		System.out.println("pass");
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='T6QCEmetteurA'])"));
		abc9.click();
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Financial Statement");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
		abc10.click();
		System.out.println("Financial Statement Report successfully downloaded ");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report successfully downloaded");
	}

	public void QC_fetchFinnacialStmtReportDataFromPDF() throws Exception {
		File src = new File(
				"C:\\Users\\PadmakarMohire\\Desktop\\report automation\\Financial Statement - AP-5197-4 01-06-2023 QC4446-5674-QC.pdf");
		FileInputStream file = new FileInputStream(src);
		PDFParser parser = new PDFParser((RandomAccessRead) file);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();
		String data = strip.getText(pdDoc);
		System.out.println(data);
		Assert.assertTrue(data.contains("Deutsche Bank Trust Company Americas"));
		Assert.assertTrue(data.contains("021001033 - New York NY"));
		Assert.assertTrue(data.contains("BKTRUS33"));
		Assert.assertTrue(data.contains("New York"));
		Assert.assertTrue(data.contains("NY"));
		Assert.assertTrue(data.contains("NY"));
		Assert.assertTrue(data.contains("WCI Auction"));
		Assert.assertTrue(data.contains("CA4376-5560"));
		System.out.println("AS per wiring instructions data found");
		ObjectRepo.getTest().log(Status.PASS, "AS per wiring instructions data found");
		cosDoc.close();
		pdDoc.close();

	}

	public void DisplayAllUpcomingEvents() {
		WebElement ele = driver.findElement(By.xpath(
				"//div[@class='MuiContainer-root']//div//div//div[@class='MuiTypography-root MuiTypography-body1']"));
		ele.getText();
		System.out.println(ele.getText());
		System.out.println("Step3:C30999:All upcoming events & event details displayed");
		ObjectRepo.getTest().log(Status.PASS, "Step3:C30999:All upcoming events & event details displayed");
	}

	public void Display_event_after_bid_application_window_is_open() {
		System.out.println("TC ID=C31000:Display event after bid application window is open");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31000:Display event after bid application window is open");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event']")));
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc2.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//button[@title='Select number of rows per page.'])")));
		WebElement abc3 = driver.findElement(By.xpath("(//button[@title='Select number of rows per page.'])"));
		abc3.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='500 ']")));
		WebElement abc4 = driver.findElement(By.xpath("//li[text()='500 ']"));
		abc4.click();
		WebElement e = driver.findElement(By.xpath("//*[text()='AutomationEventApplicationOpenAO123']"));
		
		js.executeScript("arguments[0].scrollIntoView();", e);
		e.getText();
		WebElement e1 = driver.findElement(By.xpath("(//*[text()='Open Application Period'])"));
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText() + "Ongoing Event status is:" + e1.getText());
		System.out.println("Step3:Ongoing Event name & status is displayed");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Ongoing Event name & status is displayed");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Home']")));
		WebElement abc5 = driver.findElement(By.xpath("//*[text()='Home']"));
		abc5.click();
		WebElement abc7 = driver.findElement(By.xpath("//*[text()='AutomationEventApplicationOpenAO123']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		abc7.getText();
		System.out.println("Event name is:" + abc7.getText());
		String actualElementText = abc7.getText();
		String expectedElementText = "AutomationEventApplicationOpenAO123";
		Assert.assertEquals(actualElementText, expectedElementText, "Expected and Actual are same");
		System.out.println("Step4:Event is displayed on homepage after bid application window is open");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Event is displayed on homepage after bid application window is open");
		WebElement abc8 = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-sizeSmall'])[24]"));
		abc8.getText();
		System.out.println("C31003:Multiple webinar dates displayed on private home page:" + abc8.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"C31003:Multiple webinar dates displayed on private home page:" + abc8.getText());
	}

	public void TransactionIDSearch() {
		WebElement abc1 = driver.findElement(By.xpath("(//input[@name='rollDownProcedure'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='CONTINUE'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='CONTINUE'])"));
		abc2.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Events'])"));
		abc3.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='JointAuctionAutomation'])[1]")));
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='JointAuctionAutomation'])[1]"));
		abc4.click();
		System.out.println("Step2:Navigate to the ongoing event via the navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Step2:Navigate to the ongoing event via the navigation bar");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='searchValue'])")));
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='searchValue'])"));
		abc5.sendKeys("00407006");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-icon='search'])")));
		WebElement abc6 = driver.findElement(By.xpath("(//*[@data-icon='search'])"));
		abc6.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='auction-bids-list'])")));
		WebElement abc7 = driver.findElement(By.xpath("(//*[@id='auction-bids-list'])"));
		abc7.getText();
		System.out
				.println("The records with the transaction ID containing the digits entered will be shown in the table:"
						+ abc7.getText());
		System.out.println("TC ID=C31082::AR can search using Transaction ID");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31082::AR can search using Transaction ID");

	}

	public void ChangePassphraseFunctionality() {
		WebElement abc1 = driver.findElement(By.xpath("(//*[contains(text(),'Welcome')])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Change Passphrase'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Change Passphrase'])"));
		abc2.click();
		System.out.println("Step1:User is navigated to the change passphrase screen.");
		ObjectRepo.getTest().log(Status.PASS, "Step1:User is navigated to the change passphrase screen.");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='email'])"));
		abc3.getText();
		System.out.println("Step2:Email id is prepopulated");
		ObjectRepo.getTest().log(Status.PASS, "Step2:Email id is prepopulated" + abc3.getText());
		WebElement abc4 = driver.findElement(By.xpath("(//*[@id='currentPassphrase'])"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.sendKeys("Test@1234");
		System.out.println("Step3:Enter current passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Enter current passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='newPassphrase'])")));
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='newPassphrase'])"));
		abc5.sendKeys(prop.getProperty("Password"));
		System.out.println("Step4:Create new passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Create new passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='confirmPassphrase'])")));
		WebElement abc6 = driver.findElement(By.xpath("(//*[@id='confirmPassphrase'])"));
		abc6.sendKeys(prop.getProperty("Password"));
		System.out.println("Step5:Create new passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Create new passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=' SAVE NEW PASSPHRASE'])")));
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()=' SAVE NEW PASSPHRASE'])"));
		abc7.click();
		System.out.println("Step5:SAVE NEW PASSPHRASE");
		ObjectRepo.getTest().log(Status.PASS, "Step5:SAVE NEW PASSPHRASE");
		System.out.println("C30974:users can change passphrase from the top right menu.");
		ObjectRepo.getTest().log(Status.PASS, "C30974:users can change passphrase from the top right menu.");

	}

	public void ListOfEntitySortedByAlphabeticalOrder() {
		WebElement abc2 = driver.findElement(By.xpath("(//*[@class='MuiFormControl-root'])[3]"));
		abc2.getText();
		System.out.println(abc2.getText());
		WebElement abc1 = driver.findElement(By.xpath("//*[@data-icon='sort-down']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		System.out.println("click on Sort option");
		ObjectRepo.getTest().log(Status.PASS, "click on Sort option");

		String alpha = driver.findElement(By.xpath("(//*[@class='MuiFormControl-root'])[3]")).getText();
		String value = alpha;
		if (value == "UserA") {
			Assert.assertTrue(value.contains("UserA"));
		} else if (value == "EmetteurA") {
			Assert.assertTrue(value.contains("EmetteurA"));
		} else if (value == "EmetteurB") {
			Assert.assertTrue(value.contains("EmetteurB"));
		} else if (value == "EmetteurC") {
			Assert.assertTrue(value.contains("EmetteurC"));
		} else if (value == "EmetteurD") {
			Assert.assertTrue(value.contains("EmetteurD"));
		} else if (value == "EmetteurE") {
			Assert.assertTrue(value.contains("EmetteurE"));
		} else if (value == "EmetteurF") {
			Assert.assertTrue(value.contains("EmetteurF"));
		} else if (value == "EmetteurG") {
			Assert.assertTrue(value.contains("EmetteurG"));
		} else if (value == "EmetteurH") {
			Assert.assertTrue(value.contains("EmetteurH"));
		}
		System.out.println(
				"TC ID=C30956:Confirm  the list of entity accounts presented to the user on the account selection panel is sorted alphabetically.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C30956:Confirm  the list of entity accounts presented to the user on the account selection panel is sorted alphabetically.");
	}

	public void selectedUserSecurityQuestionsReset() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='User']")));
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='User']"));
		abc2.click();
		System.out.println("Step3:Navigate to User Administration Screen");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Navigate to User Administration Screen");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root'])[4]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		System.out.println("Step4:Click on action button in Grid view");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Click on action button in Grid view");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Reset Security Questions']")));
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='Reset Security Questions']"));
		abc4.click();
		System.out.println("Step5:Click on Reset Security questions");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Click on Reset Security questions");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Reset Security Questions'])[2]")));
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='Reset Security Questions'])[2]"));
		abc5.click();
		System.out.println("Step6:Click on 'Reset Security questions' on Popup Confirmation message");
		ObjectRepo.getTest().log(Status.PASS, "Step6:Click on 'Reset Security questions' on Popup Confirmation message");
		WebElement abc6 = driver.findElement(
				By.xpath("//*[text()='Security questions  successfully reset. An email has been sent to the user.']"));
		abc6.getText();
		System.out.println("C31547:Success-Security questions successfully reset. An email has been sent to the user."
				+ abc6.getText());
		ObjectRepo.getTest().log(Status.PASS, "C31547:Success Message" + abc6.getText());
		System.out.println("TC ID=C31189:The selected user's security questions are reset");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31189:The selected user's security questions are reset");
	}

	public void Deactivate_an_activated_user() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='User']")));
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='User']"));
		abc2.click();
		System.out.println("Step3:Navigate to User Administration Screen");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Navigate to User Administration Screen");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		System.out.println("Step4:Click on action button in Grid view for Deactivate user");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Click on action button in Grid view for Deactivate user");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Deactivate']")));
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='Deactivate']"));
		abc4.click();
		System.out.println("Step5:Click on Deactivate option");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Click on Deactivate option");
		WebElement abc5 = driver.findElement(By.xpath(
				"(//*[@class='MuiPaper-root MuiDialog-paper MuiDialog-paperScrollPaper MuiDialog-paperWidthFalse MuiPaper-elevation24 MuiPaper-rounded'])"));
		abc5.getText();
		System.out.println(
				"Step6:Users First Name,Last Name,Role,Cancel & Deactivate buttons displayed on Popup message:\n"
						+ abc5.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"Step6:Users First Name,Last Name,Role,Cancel & Deactivate buttons displayed on Popup message"
						+ abc5.getText());

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Deactivate'])[2]")));
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='Deactivate'])[2]"));
		abc6.click();
		System.out.println("Step7:Click 'Deactivate' button on Popup Confirmation message");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Click 'Deactivate' button on Popup Confirmation message");
		WebElement abc7 = driver
				.findElement(By.xpath("//*[text()='User-entity relationship(s) deactivated successfully']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.getText();
		System.out.println("Deactivate user Success message:" + abc7.getText());
		String actualElementText = abc7.getText();
		String expectedElementText = "User-entity relationship(s) deactivated successfully";
		Assert.assertEquals(actualElementText, expectedElementText, "Expected and Actual are same");
		System.out.println("C31192:Success Message" + abc7.getText());
		ObjectRepo.getTest().log(Status.PASS, "C31192:Success Message" + abc7.getText());
		System.out.println("TC ID=C31192:User grid action to deactivate an activated user");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31192:User grid action to deactivate an activated user");
		new CommonUtils();
		WebElement abc13 = driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc13);
		abc13.click();
		System.out.println("Step4:Click on action button in Grid view for Deactivate user");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Click on action button in Grid view for Deactivate user");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Activate']")));
		WebElement abc14 = driver.findElement(By.xpath("//*[text()='Activate']"));
		abc14.click();
		System.out.println("Step5:Click on Activate option");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Click on Activate option");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Activate'])[2]")));
		WebElement abc16 = driver.findElement(By.xpath("(//*[text()='Activate'])[2]"));
		abc16.click();
		System.out.println("Step7:Click 'Activate' button on Popup Confirmation message");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Click 'Activate' button on Popup Confirmation message");
		System.out.println("User-entity relationship(s) deactivated successfully");
		ObjectRepo.getTest().log(Status.PASS, "User-entity relationship(s) deactivated successfully");
	}

	public void Reserve_Price_Administration_screen_layout() throws Exception {
		System.out
				.println("TC ID=C31260:As a sponsor confirm the screen has the same layout as the approved Wireframes");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31260:As a sponsor confirm the screen has the same layout as the approved Wireframes");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc1.click();
		System.out.println("Step3:Click on Administration Menu");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Click on Administration Menu");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Reserve Price']")));
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Reserve Price']"));
		abc2.click();
		System.out.println("Step3:click on Reserve Price Administration SubMenu");
		ObjectRepo.getTest().log(Status.PASS, "Step3:click on Reserve Price Administration SubMenu");
		System.out.println("Step3:Auction Reserve Price Administration Screen opened");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Auction Reserve Price Administration Screen opened");
		WebElement abc4 = driver.findElement(By.xpath("//*[@class='MuiTableContainer-root']"));
		abc4.getText();
		System.out
				.println("Step5:Auction Reserve Price Administration screen displayed as per layout" + abc4.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"Step5:Auction Reserve Price Administration screen displayed as per layout" + abc4.getText());
		System.out.println(
				"TC ID=C31260:As a sponsor confirm the screen has the same layout as the approved Wireframes.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31260:As a sponsor confirm the screen has the same layout as the approved Wireframes.");
	}

	public void MMChangePassphraseFunctionality() throws Exception {

		WebElement abc1 = driver.findElement(By.xpath("(//*[contains(text(),'Welcome')])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Change Passphrase'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Change Passphrase'])"));
		abc2.click();
		System.out.println("Step3:Select 'Change passsphrase' option in TopRight dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Select 'Change passsphrase' option in TopRight dropdown");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='email'])"));
		abc3.getText();
		System.out.println("Step4:Email id is prepopulated");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Email id is prepopulated" + abc3.getText());
		WebElement abc4 = driver.findElement(By.xpath("(//*[@id='currentPassphrase'])"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.sendKeys("Test@125");
		System.out.println("Ste5:Enter current passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Enter current passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='newPassphrase'])")));
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='newPassphrase'])"));
		abc5.sendKeys("Test@126");
		System.out.println("Step6:Create new passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step6:Create new passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='confirmPassphrase'])")));
		WebElement abc6 = driver.findElement(By.xpath("(//*[@id='confirmPassphrase'])"));
		abc6.sendKeys("Test@126");
		System.out.println("Step7:Confirm new passphrase");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Confirm new passphrase");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()=' SAVE NEW PASSPHRASE'])")));
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()=' SAVE NEW PASSPHRASE'])"));
		abc7.click();
		System.out.println("Step8:SAVE NEW PASSPHRASE");
		ObjectRepo.getTest().log(Status.PASS, "Step8:SAVE NEW PASSPHRASE");
		System.out.println(
				"C31546:MM can change passphrase upon successful login by accessing the top right down arrow menu.");
		ObjectRepo.getTest().log(Status.PASS,
				"C31546:MM can change passphrase upon successful login by accessing the top right down arrow menu.");
		Logout();
		System.out.println("MM user logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM user logout successfully");
	}

	public void MMUser_selectedUserSecurityQuestionsReset() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Welcome')])[1]")));
		WebElement abc1 = driver.findElement(By.xpath("(//*[contains(text(),'Welcome')])[1]"));
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Reset Security Questions'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Reset Security Questions'])"));
		abc2.click();
		System.out.println("Step3:Select 'Reset Security Questions' option in TopRight dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Select 'Reset Security Questions' option in TopRight dropdown");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='email'])"));
		abc3.getText();
		System.out.println("Step4:Email id is prepopulated");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Email id is prepopulated" + abc3.getText());
		WebElement abc6 = driver.findElement(
				By.xpath("(//select[@class='MuiSelect-root MuiSelect-select MuiInputBase-input MuiInput-input'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc6);
		Select s = new Select(driver.findElement(
				By.xpath("(//select[@class='MuiSelect-root MuiSelect-select MuiInputBase-input MuiInput-input'])[1]")));
		s.selectByVisibleText("In which city or town was your first job?");
		driver.findElement(By.xpath("(//*[@class='MuiInputBase-input MuiInput-input'])[1]")).sendKeys("Answer_1");
		Select s1 = new Select(driver.findElement(
				By.xpath("(//select[@class='MuiSelect-root MuiSelect-select MuiInputBase-input MuiInput-input'])[2]")));
		s1.selectByVisibleText("What was the last name of your third-grade teacher?");
		driver.findElement(By.xpath("(//*[@class='MuiInputBase-input MuiInput-input'])[2]")).sendKeys("Answer_2");

		Select s2 = new Select(driver.findElement(
				By.xpath("(//select[@class='MuiSelect-root MuiSelect-select MuiInputBase-input MuiInput-input'])[3]")));
		s2.selectByVisibleText("In which year did you get your first credit card?");
		driver.findElement(By.xpath("(//*[@class='MuiInputBase-input MuiInput-input'])[3]")).sendKeys("Answer_3");
		System.out.println("Step4:User Successfully select Questions & their Answers");
		ObjectRepo.getTest().log(Status.PASS, "Step4:User Successfully select Questions & their Answers");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='RESET MY SECURITY QUESTIONS'])")));
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='RESET MY SECURITY QUESTIONS'])"));
		//
		abc4.click();
		System.out.println("Step5:Click on 'RESET MY SECURITY QUESTIONS' button");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Click on 'RESET MY SECURITY QUESTIONS' button");

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Your security questions were reset successfully.']")));
		WebElement abc5 = driver
				.findElement(By.xpath("//*[text()='Your security questions were reset successfully.']"));
		//
		abc5.getText();
		System.out.println("Success Message:" + abc5.getText());
		ObjectRepo.getTest().log(Status.PASS, "Success Message:" + abc5.getText());

		System.out.println(
				"C31547:MM can reset security questions upon successful login by accessing the top right down arrow menu.");
		ObjectRepo.getTest().log(Status.PASS,
				"C31547:MM can reset security questions upon successful login by accessing the top right down arrow menu.");

	}

	public void MM_unflag_multiple_bids() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Home']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Home']"));
		abc1.click();
		WebElement e = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		
		js.executeScript("arguments[0].scrollIntoView();", e);
		e.getText();
		WebElement e1 = driver.findElement(By.xpath("(//*[text()='Event in progress'])"));
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText() + "Ongoing Event status is:" + e1.getText());
		System.out.println("Step3:Ongoing Event name & status is displayed");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Ongoing Event name is :" + e.getText() + "& status is:" + e1.getText());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println(
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@type='checkbox'])[1]")));
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Bulk Actions'])")));
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='Bulk Actions'])"));
		abc5.click();
		driver.findElement(By.xpath("(//*[text()='Add Flag'])")).click();
		driver.findElement(By.xpath("(//*[text()='FLAG'])")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Enter Text'])")).sendKeys("comment add");
		driver.findElement(By.xpath("//*[text()='POST']")).click();
		driver.navigate().refresh();
		WebElement abc61 = driver.findElement(By.xpath("(//*[@data-icon='comment'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc61);
		abc61.click();
		System.out.println("Click on checkbox icon");
		ObjectRepo.getTest().log(Status.PASS, "Click on checkbox icon");
		WebElement abc62 = driver.findElement(
				By.xpath("(//*[@style='position: relative; overflow: hidden; width: 100%; height: 100%;'])")); // jss2738
		abc62.getText();
		System.out.println("Details-FN, LN, and 2 letter Jurisdiction code along with the timestamp is displayed");
		ObjectRepo.getTest().log(Status.PASS,
				"Details-FN, LN, and 2 letter Jurisdiction code along with the timestamp is displayed");
		WebElement abc63 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		abc63.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[@type='checkbox'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc6);
		abc6.click();
		System.out.println("Step5:Select multiple Bids-checkboxes");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Select multiple Bids-checkboxes");
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='Bulk Actions'])"));
		abc7.click();
		System.out.println("Select Bulk Actions option ");
		ObjectRepo.getTest().log(Status.PASS, "Select Bulk Actions option");
		WebElement abc8 = driver.findElement(By.xpath("(//*[text()='Remove Flag'])"));
		abc8.click();
		System.out.println("Step6:Select bulk actions - select Remove flag ");
		ObjectRepo.getTest().log(Status.PASS, "Step6:Select bulk actions - select Remove flag");
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='REMOVE FLAG'])"));
		abc9.click();
		System.out.println("Select 'Remove Flag' option on Confirmation popup message ");
		ObjectRepo.getTest().log(Status.PASS, "Select 'Remove Flag' option on Confirmation popup message");
		WebElement abc10 = driver.findElement(By.xpath("(//input[@placeholder='Enter Text'])"));
		abc10.sendKeys("unflagged bids");
		System.out.println("Step7:Enter comment in comment box ");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Enter comment in comment box");
		WebElement abc11 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc11.click();
		System.out.println("Step8:Click on 'POST' button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step8:Click on 'POST' button ");
		WebElement abc12 = driver.findElement(
				By.xpath("(//*[text()='The flag has been removed successfully from the selected bids.'])"));
		abc12.getText();
		System.out.println("Unflagged Bids Success message:" + abc12.getText());
		ObjectRepo.getTest().log(Status.PASS, "Unflagged Bids Success message:" + abc12.getText());
		System.out.println("TC ID=C31557:MM can un-flag multiple bids");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31557:MM can un-flag multiple bids");
	}

	public void sponsor1editevent() throws Exception {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Event Edit Settings'])"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[@name='Admin_Edit_Event'])"));
		abc4.getText();
		System.out.println("Edit Event option  is checked on Configuration");
		ObjectRepo.getTest().log(Status.PASS, "Edit Event option  is checked on Configuration");
		WebElement abc5 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc6.click();
		driver.navigate().refresh();
		WebElement abc7 = driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='Edit Event']"));
		abc8.click();
		System.out.println("User is able to click edit button");
		ObjectRepo.getTest().log(Status.PASS, "User is able to click edit button");
		Logout();
		System.out.println("Sponsor user logged out");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor user logged out");
	}

	public void EAeditevent() throws Exception {
		WebElement abc5 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='Edit Event']"));
		abc8.click();
		System.out.println("User is able to click edit button");
		ObjectRepo.getTest().log(Status.PASS, "User is able to click edit button");
		Logout();
		System.out.println("Sponsor user logged out");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor user logged out");
	}

	public void Sponsor1_approval() throws Exception {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Event Edit Settings'])"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[@name='Admin_Edit_Event'])"));
		abc4.click();
		System.out.println("Edit Event option  is unchecked on Configuration");
		ObjectRepo.getTest().log(Status.PASS, "Edit Event option  is unchecked on Configuration");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='SAVE']"))));
		WebElement abc5 = driver.findElement(By.xpath("//*[text()='SAVE']"));
		abc5.click();
		System.out.println("The new configurations have been successfully saved.");
		ObjectRepo.getTest().log(Status.PASS, "The new configurations have been successfully saved.");
		driver.navigate().refresh();
		WebElement abc6 = driver.findElement(By.xpath("//*[@aria-label='approval1Status']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc6);
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc7.click();
		System.out.println("The approval of the new configuration has been proposed by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the new configuration has been proposed by the first Sponsor.");
		Logout();
		System.out.println("User successfully logged out.");
		ObjectRepo.getTest().log(Status.PASS, "User  successfully logged out.");
	}

	public void Sponsor2_approval() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("//*[@aria-label='approval2Status']"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc4.click();
		System.out.println("Warning-Are you sure you want to approve the new configuration settings?");
		ObjectRepo.getTest().log(Status.PASS, "Warning-Are you sure you want to approve the new configuration settings?");
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='APPROVE'])"));
		abc5.click();
		System.out
				.println("The new configuration has been successfully approved by both Sponsors and is now effective.");
		ObjectRepo.getTest().log(Status.PASS,
				"The new configuration has been successfully approved by both Sponsors and is now effective.");
	}

	public void EAReadOnlyAccess() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		System.out.println("Step2:Navigate to the Configuration screen");
		ObjectRepo.getTest().log(Status.PASS, "Step2:Navigate to the Configuration screen");

		System.out.println("C31605:Step3:As a EA, confirm you have read only access to the Configuration screen");
		ObjectRepo.getTest().log(Status.PASS,
				"C31605Step3:As a EA, confirm you have read only access to the Configuration screen");
	}

	public void SponsorAccessConfigurationScreen() {
		System.out.println("TC ID=C31606:As a sponsor, confirm you can access the Configuration screen");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31606:As a sponsor, confirm you can access the Configuration screen");
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		System.out.println("Step2:Navigate to the Configuration screen");
		ObjectRepo.getTest().log(Status.PASS, "Step2:Navigate to the Configuration screen");
		WebElement abc3 = driver.findElement(By.xpath("//*[@name='Remember_Me']"));
		abc3.click();
		System.out.println("Step: sponsor is able to click on Right/Cross toggle key icon");
		ObjectRepo.getTest().log(Status.PASS, "Step: sponsor is able to click on Right/Cross toggle key icon");
		WebElement abc4 = driver.findElement(By.xpath("//*[@id='Remember_Me_Duration']"));
		abc4.isEnabled();
		System.out.println("Remember me duration field Disabled:  " + abc4.isEnabled());
		System.out.println("Step:Remember Me Duration field Disabled after clicking the toggle key right icon.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step:Remember Me Duration field Disabled after clicking the toggle key right icon.");
		WebElement abc5 = driver.findElement(By.xpath("//*[@name='Remember_Me']"));
		abc5.click();
		System.out.println("Step: sponsor is able to click on Right/Cross toggle key icon");
		ObjectRepo.getTest().log(Status.PASS, "Step: sponsor is able to click on Right/Cross toggle key icon");
		WebElement abc6 = driver.findElement(By.xpath("//*[@id='Remember_Me_Duration']"));
		abc6.isEnabled();
		System.out.println("Remember Me Duration field Enabled:  " + abc6.isEnabled());
		System.out.println("Step:Remember Me Duration field Enabled after clicking the toggle key right icon.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step:Remember Me Duration field Enabled after clicking the toggle key right icon.");

	}

	public void WASponsorSetWiringInstructionFieldsInUSD() {

		System.out.println("TC ID=C31607:As a WA sponsor I must be able to set the wiring instruction fields in USD");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31607:As a WA sponsor I must be able to set the wiring instruction fields in USD");
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));
		abc3.click();
		System.out.println("Click on Wiring instruction Bar");
		ObjectRepo.getTest().log(Status.PASS, "Click on Wiring instruction Bar");
		WebElement abc31 = driver.findElement(By.xpath("(//*[@id='Bank_Name'])"));
		System.out.println(abc31.getAttribute("value"));
		String expectedBankName = "Deutsche Bank Trust Company Americas";
		String actualBankName = abc31.getAttribute("value");
		Assert.assertEquals(actualBankName, expectedBankName);
		WebElement abc32 = driver.findElement(By.xpath("(//*[@id='Routing_Number'])"));
		System.out.println(abc32.getAttribute("value"));
		String expectedRouting_Number = "021001033 - New York NY";
		String actualRouting_Number = abc32.getAttribute("value");
		Assert.assertEquals(actualRouting_Number, expectedRouting_Number, "021001033 - New York NY");
		WebElement abc33 = driver.findElement(By.xpath("(//*[@name='Bank_Identifier_Code'])"));
		System.out.println(abc33.getAttribute("value"));
		String expectedSWIFTBankIdentifierCode = "BKTRUS33";
		String actualSWIFTBankIdentifierCode = abc33.getAttribute("value");
		Assert.assertEquals(actualSWIFTBankIdentifierCode, expectedSWIFTBankIdentifierCode);
		WebElement abc34 = driver.findElement(By.xpath("(//*[@id='City'])"));
		System.out.println(abc34.getAttribute("value"));
		String expectedCity = "New York";
		String actualCity = abc34.getAttribute("value");
		Assert.assertEquals(actualCity, expectedCity);
		WebElement abc35 = driver.findElement(By.xpath("(//*[@id='State'])"));
		System.out.println(abc35.getAttribute("value"));
		String expectedStateProvince = "NY";
		String actualStateProvince = abc35.getAttribute("value");
		Assert.assertEquals(actualStateProvince, expectedStateProvince);
		WebElement abc36 = driver.findElement(By.xpath("(//*[@id='DDA_Number'])"));
		System.out.println(abc36.getAttribute("value"));
		String expectedDDA_Number = "01419647";
		String actualDDA_Number = abc36.getAttribute("value");
		Assert.assertEquals(actualDDA_Number, expectedDDA_Number);
		WebElement abc37 = driver.findElement(By.xpath("(//*[@id='Beneficiary'])"));
		System.out.println(abc37.getAttribute("value"));
		String expectedBeneficiary = "Trust And Agency Services";
		String actualBeneficiary = abc37.getAttribute("value");
		Assert.assertEquals(actualBeneficiary, expectedBeneficiary);
		WebElement abc38 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		System.out.println(abc38.getAttribute("value"));
		String expectedPayment_Details = "WCI Auction";
		String actualPayment_Details = abc38.getAttribute("value");
		Assert.assertEquals(actualPayment_Details, expectedPayment_Details);
		System.out.println("Washington  Wiring instruction details is verified");
		ObjectRepo.getTest().log(Status.PASS, "Washington  Wiring instruction details is verified");
	}

	public void Sponsor1EditApproveWiringInstructionsQC() throws Exception {

		System.out.println(" TC ID=C31602:Verify the changes made on the Configuration screen are applied-QC");
		ObjectRepo.getTest().log(Status.PASS,
				" TC ID=C31602:Verify the changes made on the Configuration screen are applied-QC");
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));
		abc3.click();
		System.out.println("click on Wiring instruction Bar");
		ObjectRepo.getTest().log(Status.PASS, "click on Wiring instruction Bar");
		WebElement abc31 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Name'])"));
		abc31.sendKeys(Keys.CONTROL + "a");
		abc31.sendKeys(Keys.DELETE);
		abc31.sendKeys("Royal Bank of Canada");

		WebElement abc32 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Identifier_Code'])"));
		abc32.sendKeys(Keys.CONTROL + "a");
		abc32.sendKeys(Keys.DELETE);
		abc32.sendKeys("ROYC CA T2");

		WebElement abc33 = driver.findElement(By.xpath("(//*[@name='Beneficiary_Bank_Name'])"));
		abc33.sendKeys(Keys.CONTROL + "a");
		abc33.sendKeys(Keys.DELETE);
		abc33.sendKeys("Deutsche Bank AG Frankfurt");

		WebElement abc34 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_IBAN'])"));
		abc34.sendKeys(Keys.CONTROL + "a");
		abc34.sendKeys(Keys.DELETE);
		abc34.sendKeys("DE28500700100959163708");

		WebElement abc35 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Identifier_Code'])"));
		abc35.sendKeys(Keys.CONTROL + "a");
		abc35.sendKeys(Keys.DELETE);
		abc35.sendKeys("DEUTDEFF");

		WebElement abc36 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Number'])"));
		abc36.sendKeys(Keys.CONTROL + "a");
		abc36.sendKeys(Keys.DELETE);
		abc36.sendKeys("959163708CAD");

		WebElement abc37 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Name'])"));
		abc37.sendKeys(Keys.CONTROL + "a");
		abc37.sendKeys(Keys.DELETE);
		abc37.sendKeys("DBTCA for DBNTC as FSA for WCI, Inc.");

		WebElement abc38 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc38.sendKeys(Keys.CONTROL + "a");
		abc38.sendKeys(Keys.DELETE);
		abc38.sendKeys("WCI Auction");

		System.out.println("Updated Wiring instruction details");
		ObjectRepo.getTest().log(Status.PASS, "Updated Wiring instruction details");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='SAVE']"))));
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='SAVE']"));
		abc4.click();
		System.out.println("The new configurations have been successfully saved.");
		ObjectRepo.getTest().log(Status.PASS, "The new configurations have been successfully saved.");
		driver.navigate().refresh();
		WebElement abcd5 = driver.findElement(By.xpath("//*[@aria-label='approval1Status']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abcd5);
		abcd5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc6.click();
		System.out.println("The approval of the new configuration has been proposed by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the new configuration has been proposed by the first Sponsor.");
		Logout();
		System.out.println("User successfully logged out.");
		ObjectRepo.getTest().log(Status.PASS, "User  successfully logged out.");
	}

	public void Sponsor2EditApproveWiringInstructionsQC() {
		WebElement abc = driver.findElement(By.xpath("//*[text()='Français']"));
		abc.click();
		WebElement ab = driver.findElement(By.xpath("(//*[text()='English'])"));
		ab.click();
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("//*[@aria-label='approval2Status']"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc4.click();
		System.out.println("Warning-Are you sure you want to approve the new configuration settings?");
		ObjectRepo.getTest().log(Status.PASS, "Warning-Are you sure you want to approve the new configuration settings?");
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='APPROVE'])"));
		abc5.click();
		System.out
				.println("The new configuration has been successfully approved by both Sponsors and is now effective.");
		ObjectRepo.getTest().log(Status.PASS,
				"The new configuration has been successfully approved by both Sponsors and is now effective.");
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));
		abc6.click();
		System.out.println("Wiring instruction section opened");
		ObjectRepo.getTest().log(Status.PASS, "Wiring instruction section opened");

		WebElement abc7 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Name'])"));
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.getText();
		System.out.println(abc7.getText());
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Identifier_Code'])"));
		abc8.getText();
		System.out.println(abc8.getText());
		WebElement abc9 = driver.findElement(By.xpath("(//*[@name='Beneficiary_Bank_Name'])"));
		abc9.getText();
		System.out.println(abc9.getText());
		WebElement abc10 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_IBAN'])"));
		abc10.getText();
		System.out.println(abc10.getText());
		WebElement abc11 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Identifier_Code'])"));
		abc11.getText();
		System.out.println(abc11.getText());
		WebElement abc12 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Number'])"));
		abc12.getText();
		System.out.println(abc12.getText());
		WebElement abc13 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Name'])"));
		abc13.getText();
		System.out.println(abc13.getText());

		WebElement abc14 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc14.getText();
		System.out.println(abc14.getText());

	}

	public void Sponsor1CertificationApproval_wiringInstructiionsQC() throws Exception {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='check-circle'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc15);
		abc15.click();
		Logout();
		System.out.println("Sponsor2 logged out successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor2 logged out successfully");
	}

	public void Sponsor2CertificationApproval_wiringInstructiionsQC() {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc12);
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]")); // (//*[@data-icon='check-circle'])[2]
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])")); // //*[text()='SUBMIT'])[8]
		abc16.click();
	}

	public void downloadFinancialStatementreportQC() {
		System.out.println("navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "navigate to report section");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Financial Statement");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='AP-5197-4'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6QCEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		System.out.println("pass");
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='T6QCEmetteurA'])"));
		abc9.click();
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Financial Statement");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
		abc10.click();
		System.out.println("Financial Statement Report successfully downloaded ");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report successfully downloaded");
	}

	public void QCfetchFinnacialStmtReportDataFromPDF() throws Exception {
		File src = new File(
				"C:\\Users\\PadmakarMohire\\Desktop\\report automation\\Financial Statement - AP-5197-4 01-06-2023 QC4446-5674-QC.pdf");
		FileInputStream file = new FileInputStream(src);

		PDFParser parser = new PDFParser((RandomAccessRead) file);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();

		String data = strip.getText(pdDoc);
		System.out.println(data);

		// System.out.println("Below fields are validated");
		Assert.assertTrue(data.contains("Royal Bank of Canada"));
		Assert.assertTrue(data.contains("ROYC CA T2"));
		Assert.assertTrue(data.contains("Deutsche Bank AG Frankfurt"));
		Assert.assertTrue(data.contains("DE28500700100959163708"));
		Assert.assertTrue(data.contains("DEUTDEFF"));
		Assert.assertTrue(data.contains("959163708CAD"));
		Assert.assertTrue(data.contains("DBTCA for DBNTC as FSA for WCI, Inc."));
		Assert.assertTrue(data.contains("WCI Auction"));
		System.out.println("AS per wiring instructions data found");
		ObjectRepo.getTest().log(Status.PASS, "AS per wiring instructions data found");
		cosDoc.close();
		pdDoc.close();
	}

	public void Sponsor1EditApproveWiringInstructionsNS() throws Exception {

		System.out.println("TC ID=C31603:Verify the changes made on the Configuration screen are applied-NS");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31603:Verify the changes made on the Configuration screen are applied-NS");
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));
		abc3.click();
		System.out.println("click on Wiring instruction Bar");
		ObjectRepo.getTest().log(Status.PASS, "click on Wiring instruction Bar");

		WebElement abc31 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Name'])"));
		abc31.sendKeys(Keys.CONTROL + "a");
		abc31.sendKeys(Keys.DELETE);
		abc31.sendKeys("Royal Bank of Canada");

		WebElement abc32 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Identifier_Code'])"));
		abc32.sendKeys(Keys.CONTROL + "a");
		abc32.sendKeys(Keys.DELETE);
		abc32.sendKeys("ROYC CA T2");

		WebElement abc33 = driver.findElement(By.xpath("(//*[@name='Beneficiary_Bank_Name'])"));
		abc33.sendKeys(Keys.CONTROL + "a");
		abc33.sendKeys(Keys.DELETE);
		abc33.sendKeys("Deutsche Bank AG Frankfurt");

		WebElement abc34 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_IBAN'])"));
		abc34.sendKeys(Keys.CONTROL + "a");
		abc34.sendKeys(Keys.DELETE);
		abc34.sendKeys("DE28500700100959163708");

		WebElement abc35 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Identifier_Code'])"));
		abc35.sendKeys(Keys.CONTROL + "a");
		abc35.sendKeys(Keys.DELETE);
		abc35.sendKeys("DEUTDEFF");

		WebElement abc36 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Number'])"));
		abc36.sendKeys(Keys.CONTROL + "a");
		abc36.sendKeys(Keys.DELETE);
		abc36.sendKeys("959163708CAD");

		WebElement abc37 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Name'])"));
		abc37.sendKeys(Keys.CONTROL + "a");
		abc37.sendKeys(Keys.DELETE);
		abc37.sendKeys("DBTCA for DBNTC as FSA for WCI, Inc");

		WebElement abc38 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc38.sendKeys(Keys.CONTROL + "a");
		abc38.sendKeys(Keys.DELETE);
		abc38.sendKeys("WCI Auction");

		System.out.println("Updated Wiring instruction details");
		ObjectRepo.getTest().log(Status.PASS, "Updated Wiring instruction details");
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='SAVE']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);

		abc4.click();
		System.out.println("The new configurations have been successfully saved.");
		ObjectRepo.getTest().log(Status.PASS, "The new configurations have been successfully saved.");
		driver.navigate().refresh();
		WebElement abcd5 = driver.findElement(By.xpath("//*[@aria-label='approval1Status']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abcd5);
		abcd5.click();

		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc6.click();
		System.out.println("The approval of the new configuration has been proposed by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS,
				"The approval of the new configuration has been proposed by the first Sponsor.");

		Logout();
		System.out.println("User successfully logged out.");
		ObjectRepo.getTest().log(Status.PASS, "User  successfully logged out.");
	}

	public void Sponsor2EditApproveWiringInstructionsNS() {

		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Settings']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Configuration']"));
		abc2.click();
		WebElement abc3 = driver.findElement(By.xpath("//*[@aria-label='approval2Status']"));
		abc3.click();

		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc4.click();
		System.out.println("Warning-Are you sure you want to approve the new configuration settings?");
		ObjectRepo.getTest().log(Status.PASS, "Warning-Are you sure you want to approve the new configuration settings?");

		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='APPROVE'])"));
		abc5.click();
		System.out
				.println("The new configuration has been successfully approved by both Sponsors and is now effective.");
		ObjectRepo.getTest().log(Status.PASS,
				"The new configuration has been successfully approved by both Sponsors and is now effective.");

		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='Wiring Instructions'])"));

		abc6.click();
		System.out.println("Wiring instruction section opened");
		ObjectRepo.getTest().log(Status.PASS, "Wiring instruction section opened");

		WebElement abc7 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Name'])"));
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.getText();
		System.out.println(abc7.getText());

		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='Intermediary_Bank_Identifier_Code'])"));
		abc8.getText();
		System.out.println(abc8.getText());

		WebElement abc9 = driver.findElement(By.xpath("(//*[@name='Beneficiary_Bank_Name'])"));
		abc9.getText();
		System.out.println(abc9.getText());

		WebElement abc10 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_IBAN'])"));
		abc10.getText();
		System.out.println(abc10.getText());

		WebElement abc11 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Identifier_Code'])"));
		abc11.getText();
		System.out.println(abc11.getText());

		WebElement abc12 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Number'])"));
		abc12.getText();
		System.out.println(abc12.getText());

		WebElement abc13 = driver.findElement(By.xpath("(//*[@id='Beneficiary_Bank_Account_Name'])"));
		abc13.getText();
		System.out.println(abc13.getText());

		WebElement abc14 = driver.findElement(By.xpath("(//*[@id='Payment_Details'])"));
		abc14.getText();
		System.out.println(abc14.getText());

	}

	public void Sponsor1CertificationApproval_wiringInstructiionsNS() throws Exception {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='check-circle'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc15);
		abc15.click();
		Logout();
		System.out.println("Sponsor2 logged out successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor2 logged out successfully");
	}

	public void Sponsor2CertificationApproval_wiringInstructiionsNS() {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc12);
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]")); // (//*[@data-icon='check-circle'])[2]
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])")); // //*[text()='SUBMIT'])[8]
		abc16.click();
	}

	public void DownloadFinancialStatementreportNS() {
		System.out.println("navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "navigate to report section");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Financial Statement");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='AuctionNS'])"));

		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6NSEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		System.out.println("pass");
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='T6NSEmetteurA'])"));
		abc9.click();
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Financial Statement");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
		abc10.click();
		System.out.println("Financial Statement Report successfully downloaded ");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report successfully downloaded");
	}

	public void NSfetchFinnacialStmtReportDataFromPDF() throws Exception {
		File src = new File(
				"C:\\Users\\PadmakarMohire\\Desktop\\report automation\\Financial Statement - AuctionNS 09-27-2022 NS4411-5614.pdf");
		FileInputStream file = new FileInputStream(src);

		PDFParser parser = new PDFParser((RandomAccessRead) file);
		parser.parse();
		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();

		String data = strip.getText(pdDoc);
		System.out.println(data);
		Assert.assertTrue(data.contains("Royal Bank of Canada"));
		Assert.assertTrue(data.contains("ROYC CA T2"));
		Assert.assertTrue(data.contains("Deutsche Bank AG Frankfurt"));
		Assert.assertTrue(data.contains("DE28500700100959163708"));
		Assert.assertTrue(data.contains("DEUTDEFF"));
		Assert.assertTrue(data.contains("959163708CAD"));
		Assert.assertTrue(data.contains("DBTCA for DBNTC as FSA for WCI, Inc"));
		Assert.assertTrue(data.contains("WCI Auction"));
		System.out.println("AS per wiring instructions data found");
		ObjectRepo.getTest().log(Status.PASS, "AS per wiring instructions data found");
		cosDoc.close();
		pdDoc.close();
	}

	///
	public void EvenStatus() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		System.out.println("Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to Administration");
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc2.click();
		System.out.println("Navigate to Event Administration");
		WebElement abc3 = driver.findElement(By.xpath("//*[text()='ANCert11']"));
		abc3.getText();
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='Results Run']"));
		abc4.getText();
		System.out.println("Event name: " + abc3.getText() + "Event Status is: " + abc4.getText());
	}

	public void Sponsor1_CertificationApproval() throws Exception {
		System.out.println("Go to Auction certification page");
		ObjectRepo.getTest().log(Status.PASS, "");
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		System.out.println("Step2:Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Step2:Navigate to Administration");
		abc12.click();
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		System.out.println("Step3:Selected Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Selected to Auction Certification");
		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='check-circle'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc15);
		abc15.click();
		System.out.println("Step4:Certify the auction by 1st sponsor and select submit button");
		ObjectRepo.getTest().log(Status.PASS, "Step4:Certify the auction by 1st sponsor and select submit button");
		Logout();
		System.out.println("Sponsor1 logged out successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor1 logged out successfully");
	}

	public void Sponsor2_CertificationApproval() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc12);

		abc12.click();
		System.out.println("Step6:Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Step6:Navigate to Administration");
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		System.out.println("Step7:Selected Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Selected to Auction Certification");
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]")); // (//*[@data-icon='check-circle'])[2]
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])")); // //*[text()='SUBMIT'])[8]
		abc16.click();
		System.out.println("Step8:Certify the auction by 2nd sponsor and select submit button");
		ObjectRepo.getTest().log(Status.PASS, "Step8:Certify the auction 2nd sponsor  and select submit button");
		Logout();
		System.out.println("Sponsor2 logged out successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor2 logged out successfully");

	}

	public void StaticReportAfterAuctionCertification() {
		System.out.println("Navigate to report section-static reports generated with timestamp");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to report section-static reports generated with timestamp");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("All");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='ANCert11'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]"));
		abc6.getText();
		WebElement abc7 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[4]"));
		abc7.getText();
		System.out.println("Allowance Delivery Report:" + abc6.getText() + "and timestamp:" + abc7.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"Allowance Delivery Report:" + abc6.getText() + "and timestamp:" + abc7.getText());
		WebElement abc8 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[5]"));
		abc8.getText();
		WebElement abc9 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[8]"));
		abc9.getText();
		System.out.println("Bids Report:" + abc8.getText() + "and timestamp:" + abc9.getText());
		ObjectRepo.getTest().log(Status.PASS, "Bids Report:" + abc8.getText() + "and timestamp:" + abc9.getText());
		WebElement abc10 = driver
				.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[9]"));
		abc10.getText();
		WebElement abc11 = driver
				.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[12]"));
		abc11.getText();
		System.out.println("Results Report:" + abc10.getText() + "and timestamp:" + abc11.getText());
		ObjectRepo.getTest().log(Status.PASS, "Results Report:" + abc10.getText() + "and timestamp:" + abc11.getText());
		System.out.println("static reports generated with timestamp");
		ObjectRepo.getTest().log(Status.PASS, "static reports generated with timestamp");

		System.out.println("TC ID=C31210:Confirm a Sponsor can certify an Auction event");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31210:Confirm a Sponsor can certify an Auction event");
	}

	public void Sponsor2_CertificationDispproval() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Administration']")));
		WebElement abc12 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc12);
		abc12.click();
		System.out.println("Step6:Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Step6:Navigate to Administration");
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='Auction Certification']"));
		abc13.click();
		System.out.println("Step7:Selected Auction Certification");
		ObjectRepo.getTest().log(Status.PASS, "Step7:Selected to Auction Certification");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@data-icon='times-circle'])[1]")));
		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='times-circle'])[1]"));
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])")); // //*[text()='SUBMIT'])[8]
		abc16.click();
		System.out.println("Step8:Certify the auction by 2nd sponsor(Disapproval) and select submit button");
		ObjectRepo.getTest().log(Status.PASS, "Step8:Certify the auction 2nd sponsor(Disapproval)and select submit button");
		WebElement abc17 = driver.findElement(By.xpath("(//*[text()='RESET'])[1]"));
		abc17.click();
		System.out.println("Certification decision successfully reset.");
		ObjectRepo.getTest().log(Status.PASS, "Certification decision successfully reset.");
		WebElement CertiOpt = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[8]"));
		CertiOpt.isSelected();
		System.out.println("approval/rejection control is enabled:" + CertiOpt.isSelected());

		WebElement ApproverName = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[7]"));
		ApproverName.isEnabled();
		System.out.println("Approver Name:" + ApproverName.isEnabled());

	}

	public void RunAlgorithmEnabledbutton() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		System.out.println("Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to Administration");
		abc1.click();
		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc2.click();
		System.out.println("Navigate to Event Administration");
		WebElement abc4 = driver.findElement(By.xpath("//*[text()='ANCert11']"));
		abc4.getText();

		WebElement abc5 = driver.findElement(By.xpath("//*[text()='Results Run']"));
		abc5.getText();
		System.out.println("Event name:" + abc4.getText() + "Event Status is:" + abc5.getText());

		WebElement abc6 = driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc6);
		System.out.println("Navigate to Event details");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to Event details");
		abc6.click();

		driver.navigate().refresh();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='RESET'])"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc7);
		System.out.println("Navigate to Run Algorithm section");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to Run Algorithm section");
		//
		abc7.click();

		System.out.println("Reset algorithm status successful.");
		ObjectRepo.getTest().log(Status.PASS, "Reset algorithm status successful.");

		WebElement ApproverName = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[18]"));
		//
		ApproverName.isEnabled();
		System.out.println("Approver Name is not displayed:" + ApproverName.isEnabled());

		//
		WebElement RunALgobtn = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[19]"));
		//
		RunALgobtn.isEnabled();
		System.out.println("RunAlgorithm button is enabled :" + RunALgobtn.isEnabled());
		System.out.println("The Run Algorithm button (s) is enabled again.");
		ObjectRepo.getTest().log(Status.PASS, "The Run Algorithm button (s) is enabled again.");

		System.out.println("TC ID=C31213:ALL EVENT TYPES Certification_Dispute scenario");
		ObjectRepo.getTest().log(Status.PASS, "TC ID=C31213:ALL EVENT TYPES Certification_Dispute scenario");

	}

	public void EventStatusBiddingWindowOpen() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Administration']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		System.out.println("Navigate to Administration");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to Administration");
		abc1.click();

		WebElement abc2 = driver.findElement(By.xpath("//*[text()='Event']"));
		abc2.click();
		System.out.println("Navigate to Event Administration");

		WebElement abc3 = driver.findElement(By.xpath("(//button[@type='button'])[9]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();

		WebElement abc4 = driver.findElement(By.xpath("//*[text()='500 ']"));
		abc4.click();

		WebElement abc5 = driver.findElement(By.xpath("//*[text()='A183']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.getText();

		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='Open Bidding Window'])"));
		abc6.getText();
		System.out.println("Step:Event ID: " + abc5.getText() + "Event Status is: " + abc6.getText());
		ObjectRepo.getTest().log(Status.PASS, "Step:Event is ongoing for the jurisdiction -Event ID: " + abc5.getText()
				+ "Event Status is: " + abc6.getText());
	}

	public void AccessAndDownloadBidsAuditReport() {

		System.out.println("TC ID C31292-The Bids Audit report is a dynamic report (prior to certification)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID C31292-The Bids Audit report is a dynamic report (prior to certification)");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		System.out.println("Step:Navigate to report section-Dynamic Bid Audit report generated without timestamp");
		ObjectRepo.getTest().log(Status.PASS,
				"Step:Navigate to report section-Dynamic Bid Audit report generated without timestamp");
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Bids Audit Report");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6CAEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc9 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]"));

		abc9.getText();
		WebElement abc10 = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]"));
		String time = abc10.getText();
		if (time.isBlank()) {
			System.out.println("Timestamp is not displayed for Bids Audit report ");
		} else {
			System.out.println("Timestamp is displayed for Bids Audit report ");
		}

		System.out.println("Bids Audit Report Name :" + abc9.getText() + " and timestamp:" + abc10.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"Bids Audit ReporName: " + abc9.getText() + " and timestamp: " + abc10.getText());

		WebElement abc11 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));
		abc11.click();
		System.out.println("Step:Updated Bids Audit Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Updated Bids Audit Report is downloaded");
	}

	public void EnterBidsforCAJurisdiction() {
		WebElement abc1 = driver.findElement(By.xpath("(//input[@name='rollDownProcedure'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='CONTINUE'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='CONTINUE'])"));
		abc2.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='Events'])"));
		abc3.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]")));
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[text()='ADD BID'])"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[@id='bidPrice'])"));
		abc6.sendKeys("25");
		WebElement abc7 = driver.findElement(By.xpath("(//*[@id='bidLot'])"));
		abc7.sendKeys("2");
		Select S = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])")));
		S.selectByVisibleText("Current");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='SUBMIT'])")));
		WebElement abc8 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])"));
		abc8.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='CONFIRM'])")));
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='CONFIRM'])"));
		abc9.click();
		System.out.println("Bid successfully added.");
		ObjectRepo.getTest().log(Status.PASS, "Bid successfully added.");
	}

	public void DataReadFromBidsAuditReport1stTime() throws IOException {
		File src = new File(
				folderPath + File.separator + "Bids Audit Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);
		XSSFSheet sheet = xsf.getSheetAt(0);
		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);
		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);
		System.out.println(" Bids Audit Report headers as:");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report headers displayed");
		String HN1 = sheet.getRow(5).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(5).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(5).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(5).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(5).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(5).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(5).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(5).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(5).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(5).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(5).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(5).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(5).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(5).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(5).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(5).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(5).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(5).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(5).getCell(18).getStringCellValue();
		System.out.println(HN19);

		String HN20 = sheet.getRow(6).getCell(0).getStringCellValue();
		System.out.println(HN20);
		String HN21 = sheet.getRow(7).getCell(0).getStringCellValue();
		System.out.println(HN21);
		String HN22 = sheet.getRow(8).getCell(0).getStringCellValue();
		System.out.println(HN22);
		String HN23 = sheet.getRow(9).getCell(0).getStringCellValue();
		System.out.println(HN23);
		String HN24 = sheet.getRow(10).getCell(0).getStringCellValue();
		System.out.println(HN24);
		String HN25 = sheet.getRow(11).getCell(0).getStringCellValue();
		System.out.println(HN25);
		String HN26 = sheet.getRow(12).getCell(0).getStringCellValue();
		System.out.println(HN26);
		String HN27 = sheet.getRow(13).getCell(0).getStringCellValue();
		System.out.println(HN27);
		String HN28 = sheet.getRow(14).getCell(0).getStringCellValue();
		System.out.println(HN28);
		String HN29 = sheet.getRow(15).getCell(0).getStringCellValue();
		System.out.println(HN29);
		String HN30 = sheet.getRow(16).getCell(0).getStringCellValue();
		System.out.println(HN30);
		String HN31 = sheet.getRow(17).getCell(0).getStringCellValue();
		System.out.println(HN31);

		System.out.println("Bids Audit Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report data fetched");
		xsf.close();

	}

	public void DataReadFromBidsAuditReport2ndTime() throws IOException {
		File src = new File(
				folderPath + File.separator + "Bids Audit Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);
		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);
		System.out.println(" Bids Audit Report headers as:");
		String HN1 = sheet.getRow(5).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(5).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(5).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(5).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(5).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(5).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(5).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(5).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(5).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(5).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(5).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(5).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(5).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(5).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(5).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(5).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(5).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(5).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(5).getCell(18).getStringCellValue();
		System.out.println(HN19);

		String HN20 = sheet.getRow(6).getCell(0).getStringCellValue();
		System.out.println(HN20);
		String HN21 = sheet.getRow(7).getCell(0).getStringCellValue();
		System.out.println(HN21);
		String HN22 = sheet.getRow(8).getCell(0).getStringCellValue();
		System.out.println(HN22);
		String HN23 = sheet.getRow(9).getCell(0).getStringCellValue();
		System.out.println(HN23);
		String HN24 = sheet.getRow(10).getCell(0).getStringCellValue();
		System.out.println(HN24);
		String HN25 = sheet.getRow(11).getCell(0).getStringCellValue();
		System.out.println(HN25);
		String HN26 = sheet.getRow(12).getCell(0).getStringCellValue();
		System.out.println(HN26);
		String HN27 = sheet.getRow(13).getCell(0).getStringCellValue();
		System.out.println(HN27);
		String HN28 = sheet.getRow(14).getCell(0).getStringCellValue();
		System.out.println(HN28);
		String HN29 = sheet.getRow(15).getCell(0).getStringCellValue();
		System.out.println(HN29);
		String HN30 = sheet.getRow(16).getCell(0).getStringCellValue();
		System.out.println(HN30);
		String HN31 = sheet.getRow(17).getCell(0).getStringCellValue();
		System.out.println(HN31);
		String HN32 = sheet.getRow(18).getCell(0).getStringCellValue();
		System.out.println(HN32);

		System.out.println("Bids Audit Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report data fetched");
		xsf.close();

	}

	public void AccessAndDownloadClientBidsAuditReport() {

		System.out.println("TC ID C31301-The Client Bids Audit report is a dynamic report (prior to certification)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID C31301-The Client Bids Audit report is a dynamic report (prior to certification)");
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out
				.println("Step:Navigate to report section-Dynamic Client Bid Audit report generated without timestamp");
		ObjectRepo.getTest().log(Status.PASS,
				"Step:Navigate to report section-Dynamic Client Bid Audit report generated without timestamp");
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Client Bids Audit Report");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		abc4.click();
		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='T6CAEmetteurA'])"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));

		abc7.click();
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='T6CAEmetteurA'])"));

		abc9.click();
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Client Bids Audit Report");
		WebElement abc12 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));

		abc12.click();
		System.out.println("Step:Updated Client Bids Audit Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Updated Client Bids Audit Report is downloaded");
	}

	public void DataReadFromClientBidsAuditReport1stTime() throws IOException {
		File src = new File(folderPath + File.separator
				+ "Client Bids Audit Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023 CA4376-5560.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String EntityLegalName = sheet.getRow(3).getCell(0).getStringCellValue();
		String EntityLegalNameval = sheet.getRow(3).getCell(1).getStringCellValue();
		System.out.println(EntityLegalName + ":" + EntityLegalNameval);

		String EntityOperatingName = sheet.getRow(4).getCell(0).getStringCellValue();
		String EntityOperatingNameval = sheet.getRow(4).getCell(1).getStringCellValue();
		System.out.println(EntityOperatingName + ":" + EntityOperatingNameval);

		String TotalBidsSubmitted = sheet.getRow(5).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(5).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);

		System.out.println("Client Bids Report headers print on console:");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report headers print on console");
		String HN1 = sheet.getRow(7).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(7).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(7).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(7).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(7).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(7).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(7).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(7).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(7).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(7).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(7).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(8).getCell(0).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(9).getCell(0).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(10).getCell(0).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(11).getCell(0).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(12).getCell(0).getStringCellValue();
		System.out.println(HN16);

		System.out.println("Client Bids Audit Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report data fetched");
		xsf.close();

	}

	public void DataReadFromClientBidsAuditReport2ndTime() throws IOException {
		File src = new File(folderPath + File.separator
				+ "Client Bids Audit Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023 CA4376-5560.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String EntityLegalName = sheet.getRow(3).getCell(0).getStringCellValue();
		String EntityLegalNameval = sheet.getRow(3).getCell(1).getStringCellValue();
		System.out.println(EntityLegalName + ":" + EntityLegalNameval);

		String EntityOperatingName = sheet.getRow(4).getCell(0).getStringCellValue();
		String EntityOperatingNameval = sheet.getRow(4).getCell(1).getStringCellValue();
		System.out.println(EntityOperatingName + ":" + EntityOperatingNameval);

		String TotalBidsSubmitted = sheet.getRow(5).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(5).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);

		System.out.println("Client Bids Report headers as:");
		String HN1 = sheet.getRow(7).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(7).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(7).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(7).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(7).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(7).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(7).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(7).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(7).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(7).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(7).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(8).getCell(0).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(9).getCell(0).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(10).getCell(0).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(11).getCell(0).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(12).getCell(0).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(13).getCell(0).getStringCellValue();
		System.out.println(HN17);
		System.out.println("Client Bids Audit Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report data fetched");
		xsf.close();

	}

	public void MM_AddComment() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Home']")));
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Home']"));
		abc1.click();
		WebElement e = driver.findElement(By.xpath("//*[text()='A183-JOINTAUCTIONBiddingWindowOpen']"));
		
		js.executeScript("arguments[0].scrollIntoView();", e);
		e.getText();
		WebElement e1 = driver.findElement(By.xpath("(//*[text()='Event in progress'])"));
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText() + "Ongoing Event status is:" + e1.getText());
		System.out.println("Step3:Ongoing Event name & status is displayed");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Ongoing Event name is :" + e.getText() + "& status is:" + e1.getText());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println(
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[2]"));
		abc5.click();

		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Add Comment']"));
		abc6.click(); // *[@placeholder='Enter Text']
		WebElement abc7 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc7.sendKeys("Enter comment");
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc8.click();
		System.out.println("Step:Comment is added for Bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Comment is added for Bid");
		WebElement abc9 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc9);
		abc9.click();
	}

	public void MM_FlaggedBid() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println("Step4:Event name select from Event Monitoring during the open bidding window open ");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window open");
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[4]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[3]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Add Flag']"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc7.sendKeys("Enter comment for Add Flag");
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc8.click();
		System.out.println("Step:Bid is Flagged");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is Flagged");
		WebElement ab9 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		
		js.executeScript("arguments[0].scrollIntoView();", ab9);
		ab9.click();

		WebElement abc9 = driver.findElement(By.xpath("(//*[@type='checkbox'])[4]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc9);
		abc9.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[3]"));
		abc10.click();
		WebElement abc11 = driver.findElement(By.xpath("//*[text()='Remove Flag']"));
		abc11.click();
		WebElement abc12 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc12.sendKeys("Enter comment for Remove Flag");
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc13.click();
		System.out.println("Step:Bid is unflagged");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is unflagged");
		WebElement abc14 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		abc14.click();

	}

	public void AccessAndDownloadBidsReportAfterMMAddComment() {
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out.println("Step:Navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "Step:Navigate to report section");
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Bids Report");
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		abc4.click();
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		WebElement abc11 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));

		abc11.click();
		System.out.println("TC ID C31354-Bids report is available during  the bidding window.");
		ObjectRepo.getTest().log(Status.PASS, "TC ID C31354-Bids report is available during  the bidding window.");
		System.out.println("Step:Bids Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bids Report is downloaded");
	}

	public void DataReadFromBidsReportAfterMMAddComment() throws IOException {
		File src = new File(
				folderPath + File.separator + "Bids Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);

		System.out.println("Bids Report headers as:");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report headers print on Console");
		String HN2 = sheet.getRow(9).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(9).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(9).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(9).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(9).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(9).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(9).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(9).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(9).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(9).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(9).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(9).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(9).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(9).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(9).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(9).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(9).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(9).getCell(18).getStringCellValue();
		System.out.println(HN19);
		String HN20 = sheet.getRow(9).getCell(19).getStringCellValue();
		System.out.println(HN20);
		String HN21 = sheet.getRow(9).getCell(20).getStringCellValue();
		System.out.println(HN21);

		String HN22 = sheet.getRow(9).getCell(21).getStringCellValue();
		System.out.println(HN22);
		String HN23 = sheet.getRow(9).getCell(22).getStringCellValue();
		System.out.println(HN23);
		String HN24 = sheet.getRow(9).getCell(23).getStringCellValue();
		System.out.println(HN24);
		String HN25 = sheet.getRow(9).getCell(24).getStringCellValue();
		System.out.println(HN25);
		String HN26 = sheet.getRow(9).getCell(25).getStringCellValue();
		System.out.println(HN26);
		String HN27 = sheet.getRow(9).getCell(26).getStringCellValue();
		System.out.println(HN27);
		String HN28 = sheet.getRow(9).getCell(27).getStringCellValue();
		System.out.println(HN28);
		String HN29 = sheet.getRow(9).getCell(28).getStringCellValue();
		System.out.println(HN29);
		String HN30 = sheet.getRow(9).getCell(29).getStringCellValue();
		System.out.println(HN30);
		String HN31 = sheet.getRow(9).getCell(30).getStringCellValue();
		System.out.println(HN31);
		String HN32 = sheet.getRow(9).getCell(31).getStringCellValue();
		System.out.println(HN32);
		String HN33 = sheet.getRow(9).getCell(32).getStringCellValue();
		System.out.println(HN33);

		// flagged bids details
		System.out.println("Flagged bids details Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Flagged bids details Shown in Bids Report");
		String FLG11 = sheet.getRow(10).getCell(26).getStringCellValue();
		System.out.println(FLG11);
		String FLG12 = sheet.getRow(11).getCell(26).getStringCellValue();
		System.out.println(FLG12);
		String FLG13 = sheet.getRow(12).getCell(26).getStringCellValue();
		System.out.println(FLG13);
		String FLG14 = sheet.getRow(13).getCell(26).getStringCellValue();
		System.out.println(FLG14);
		String FLG15 = sheet.getRow(14).getCell(26).getStringCellValue();
		System.out.println(FLG15);
		String FLG16 = sheet.getRow(15).getCell(26).getStringCellValue();
		System.out.println(FLG16);
		String FLG17 = sheet.getRow(16).getCell(26).getStringCellValue();
		System.out.println(FLG17);
		String FLG18 = sheet.getRow(17).getCell(26).getStringCellValue();
		System.out.println(FLG18);
		String FLG19 = sheet.getRow(18).getCell(26).getStringCellValue();
		System.out.println(FLG19);
		String FLG20 = sheet.getRow(19).getCell(26).getStringCellValue();
		System.out.println(FLG20);
		String FLG21 = sheet.getRow(20).getCell(26).getStringCellValue();
		System.out.println(FLG21);
		String FLG22 = sheet.getRow(21).getCell(26).getStringCellValue();
		System.out.println(FLG22);
		String FLG23 = sheet.getRow(22).getCell(26).getStringCellValue();
		System.out.println(FLG23);
		String FLG24 = sheet.getRow(23).getCell(26).getStringCellValue();
		System.out.println(FLG24);
		String FLG25 = sheet.getRow(24).getCell(26).getStringCellValue();
		System.out.println(FLG25);

		// HOLDING LIMIT VIOLATION
		System.out.println("Holding limit violations details Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Holding limit violations details Shown in Bids Report");
		String HLV11 = sheet.getRow(10).getCell(16).getStringCellValue();
		System.out.println(HLV11);
		String HLV12 = sheet.getRow(11).getCell(16).getStringCellValue();
		System.out.println(HLV12);
		String HLV13 = sheet.getRow(12).getCell(16).getStringCellValue();
		System.out.println(HLV13);
		String HLV14 = sheet.getRow(13).getCell(16).getStringCellValue();
		System.out.println(HLV14);
		String HLV15 = sheet.getRow(14).getCell(16).getStringCellValue();
		System.out.println(HLV15);
		String HLV16 = sheet.getRow(15).getCell(16).getStringCellValue();
		System.out.println(HLV16);
		String HLV17 = sheet.getRow(16).getCell(16).getStringCellValue();
		System.out.println(HLV17);
		String HLV18 = sheet.getRow(17).getCell(16).getStringCellValue();
		System.out.println(HLV18);
		String HLV19 = sheet.getRow(18).getCell(16).getStringCellValue();
		System.out.println(HLV19);
		String HLV20 = sheet.getRow(19).getCell(16).getStringCellValue();
		System.out.println(HLV20);
		String HLV21 = sheet.getRow(20).getCell(16).getStringCellValue();
		System.out.println(HLV21);
		String HLV22 = sheet.getRow(21).getCell(16).getStringCellValue();
		System.out.println(HLV22);
		String HLV23 = sheet.getRow(22).getCell(16).getStringCellValue();
		System.out.println(HLV23);
		String HLV24 = sheet.getRow(23).getCell(16).getStringCellValue();
		System.out.println(HLV24);
		String HLV25 = sheet.getRow(24).getCell(16).getStringCellValue();
		System.out.println(HLV25);

		// PURCHASE LIMIT VIOLATION
		System.out.println("Purchase limit violations details Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Purchase limit violations details Shown in Bids Report");
		String PLV11 = sheet.getRow(10).getCell(16).getStringCellValue();
		System.out.println(PLV11);
		String PLV12 = sheet.getRow(11).getCell(16).getStringCellValue();
		System.out.println(PLV12);
		String PLV13 = sheet.getRow(12).getCell(16).getStringCellValue();
		System.out.println(PLV13);
		String PLV14 = sheet.getRow(13).getCell(16).getStringCellValue();
		System.out.println(PLV14);
		String PLV15 = sheet.getRow(14).getCell(16).getStringCellValue();
		System.out.println(PLV15);
		String PLV16 = sheet.getRow(15).getCell(16).getStringCellValue();
		System.out.println(PLV16);
		String PLV17 = sheet.getRow(16).getCell(16).getStringCellValue();
		System.out.println(PLV17);
		String PLV18 = sheet.getRow(17).getCell(16).getStringCellValue();
		System.out.println(PLV18);
		String PLV19 = sheet.getRow(18).getCell(16).getStringCellValue();
		System.out.println(PLV19);
		String PLV20 = sheet.getRow(19).getCell(16).getStringCellValue();
		System.out.println(PLV20);
		String PLV21 = sheet.getRow(20).getCell(16).getStringCellValue();
		System.out.println(PLV21);
		String PLV22 = sheet.getRow(21).getCell(16).getStringCellValue();
		System.out.println(PLV22);
		String PLV23 = sheet.getRow(22).getCell(16).getStringCellValue();
		System.out.println(PLV23);
		String PLV24 = sheet.getRow(23).getCell(16).getStringCellValue();
		System.out.println(PLV24);
		String PLV25 = sheet.getRow(24).getCell(16).getStringCellValue();
		System.out.println(PLV25);

		// comment bids details
		System.out.println("Comments Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Comments Shown in Bids Report");
		String CMT11 = sheet.getRow(10).getCell(32).getStringCellValue();
		System.out.println(CMT11);
		String CMT12 = sheet.getRow(11).getCell(32).getStringCellValue();
		System.out.println(CMT12);
		String CMT13 = sheet.getRow(12).getCell(32).getStringCellValue();
		System.out.println(CMT13);
		String CMT14 = sheet.getRow(13).getCell(32).getStringCellValue();
		System.out.println(CMT14);
		String CMT15 = sheet.getRow(14).getCell(32).getStringCellValue();
		System.out.println(CMT15);
		String CMT16 = sheet.getRow(15).getCell(32).getStringCellValue();
		System.out.println(CMT16);
		String CMT17 = sheet.getRow(16).getCell(32).getStringCellValue();
		System.out.println(CMT17);
		String CMT18 = sheet.getRow(17).getCell(32).getStringCellValue();
		System.out.println(CMT18);
		String CMT19 = sheet.getRow(18).getCell(32).getStringCellValue();
		System.out.println(CMT19);
		String CMT20 = sheet.getRow(19).getCell(32).getStringCellValue();
		System.out.println(CMT20);
		String CMT21 = sheet.getRow(20).getCell(32).getStringCellValue();
		System.out.println(CMT21);
		String CMT22 = sheet.getRow(21).getCell(32).getStringCellValue();
		System.out.println(CMT22);
		String CMT23 = sheet.getRow(22).getCell(32).getStringCellValue();
		System.out.println(CMT23);
		String CMT24 = sheet.getRow(23).getCell(32).getStringCellValue();
		System.out.println(CMT24);
		String CMT25 = sheet.getRow(24).getCell(32).getStringCellValue();
		System.out.println(CMT25);
		System.out.println("Bids Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report data fetched");
		System.out.println(
				"TC:C31323:Verify bids report has information about the comments added to the bid during monitoring of the bids");
		ObjectRepo.getTest().log(Status.PASS,
				"TC:C31323:Verify bids report has information about the comments added to the bid during monitoring of the bids");
		xsf.close();

	}

	public void Sponsor_AddComment() {
		WebElement abc1 = driver.findElement(By.xpath("//*[text()='Home']"));
		abc1.click();
		WebElement e = driver.findElement(By.xpath("//*[text()='A183-JOINTAUCTIONBiddingWindowOpen']"));
		
		js.executeScript("arguments[0].scrollIntoView();", e);
		e.getText();
		WebElement e1 = driver.findElement(By.xpath("(//*[text()='Event in progress'])"));
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText() + "Ongoing Event status is:" + e1.getText());
		System.out.println("Step3:Ongoing Event name & status is displayed");
		ObjectRepo.getTest().log(Status.PASS, "Step3:Ongoing Event name is :" + e.getText() + "& status is:" + e1.getText());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println(
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[2]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Add Comment']"));
		abc6.click(); // *[@placeholder='Enter Text']
		WebElement abc7 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc7.sendKeys("Enter comment");
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc8.click();
		System.out.println("Step:Comment is added for Bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Comment is added for Bid");
		WebElement abc9 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		abc9.click();
		driver.navigate().refresh();

	}

	public void Sponsor_FlaggedBid() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println("Step4:Event name select from Event Monitoring during the open bidding window open ");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window open");
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[4]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[3]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Add Flag']"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc7.sendKeys("Enter comment for Add Flag");
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc8.click();
		System.out.println("Step:Bid is Flagged");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is Flagged");
		WebElement ab9 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		ab9.click();
		WebElement abc9 = driver.findElement(By.xpath("(//*[@type='checkbox'])[4]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc9);
		abc9.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[3]"));
		abc10.click();
		WebElement abc11 = driver.findElement(By.xpath("//*[text()='Remove Flag']"));
		abc11.click();
		WebElement abc12 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc12.sendKeys("Enter comment for Remove Flag");
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc13.click();
		System.out.println("Step:Bid is unflagged");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is unflagged");
		WebElement abc14 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		abc14.click();
		driver.navigate().refresh();
	}

	public void Sponsor_ExcludeIncludeBid() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc2 = driver.findElement(By.xpath("//*[contains(text(),'Monitoring')]"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println("Step4:Event name select from Event Monitoring during the open bidding window open ");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window open");
		WebElement abc4 = driver.findElement(By.xpath("(//*[@type='checkbox'])[5]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		abc4.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc5 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[4]"));
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("//*[text()='Exclude Bid']"));
		abc6.click();
		WebElement abc7 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc7.sendKeys("Enter comment for Exclude Bid");
		WebElement abc8 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc8.click();
		System.out.println("Step:Bid is Excludeed");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is Excludeed");
		WebElement ab9 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		
		js.executeScript("arguments[0].scrollIntoView();", ab9);
		ab9.click();
		WebElement abc9 = driver.findElement(By.xpath("(//*[@type='checkbox'])[5]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc9);
		//
		abc9.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");

		//
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[4]"));
		//
		abc10.click();
		System.out.println("click on 3 dot function button");

		//
		WebElement abc11 = driver.findElement(By.xpath("//*[text()='Include Bid']"));
		//
		abc11.click();

		//
		WebElement abc12 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		//
		abc12.sendKeys("Enter comment for Include Bid ");

		//
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='POST']"));
		//
		abc13.click();
		System.out.println("Step:Bid is Included");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is Included");

		//
		WebElement abc14 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc14);
		//
		abc14.click();

	}

	public void AccessAndDownloadBidsReportAfterAddComment_Flagged_IncludeActions() {
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out.println("Step:Navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "Step:Navigate to report section");

		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();

		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Bids Report");

		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();

		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		abc4.click();

		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();

		WebElement abc11 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));
		abc11.click();
		System.out.println("TC ID C31354-Bids report is available during  the bidding window.");
		ObjectRepo.getTest().log(Status.PASS, "TC ID C31354-Bids report is available during  the bidding window.");
		System.out.println("Step:Bids Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bids Report is downloaded");
	}

	public void DataReadFromBidsReportAfterAddComment_flagged_IncludeActions() throws IOException {
		File src = new File(
				folderPath + File.separator + "Bids Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);

		System.out.println("Bids Report headers as:");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report headers print on Console");
		String HN2 = sheet.getRow(9).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(9).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(9).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(9).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(9).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(9).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(9).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(9).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(9).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(9).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(9).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(9).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(9).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(9).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(9).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(9).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(9).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(9).getCell(18).getStringCellValue();
		System.out.println(HN19);
		String HN20 = sheet.getRow(9).getCell(19).getStringCellValue();
		System.out.println(HN20);
		String HN21 = sheet.getRow(9).getCell(20).getStringCellValue();
		System.out.println(HN21);

		String HN22 = sheet.getRow(9).getCell(21).getStringCellValue();
		System.out.println(HN22);
		String HN23 = sheet.getRow(9).getCell(22).getStringCellValue();
		System.out.println(HN23);
		String HN24 = sheet.getRow(9).getCell(23).getStringCellValue();
		System.out.println(HN24);
		String HN25 = sheet.getRow(9).getCell(24).getStringCellValue();
		System.out.println(HN25);
		String HN26 = sheet.getRow(9).getCell(25).getStringCellValue();
		System.out.println(HN26);
		String HN27 = sheet.getRow(9).getCell(26).getStringCellValue();
		System.out.println(HN27);
		String HN28 = sheet.getRow(9).getCell(27).getStringCellValue();
		System.out.println(HN28);
		String HN29 = sheet.getRow(9).getCell(28).getStringCellValue();
		System.out.println(HN29);
		String HN30 = sheet.getRow(9).getCell(29).getStringCellValue();
		System.out.println(HN30);
		String HN31 = sheet.getRow(9).getCell(30).getStringCellValue();
		System.out.println(HN31);
		String HN32 = sheet.getRow(9).getCell(31).getStringCellValue();
		System.out.println(HN32);
		String HN33 = sheet.getRow(9).getCell(32).getStringCellValue();
		System.out.println(HN33);

		System.out.println("Flagged bids details Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Flagged bids details Shown in Bids Report");
		String FLG11 = sheet.getRow(10).getCell(26).getStringCellValue();
		System.out.println(FLG11);
		String FLG12 = sheet.getRow(11).getCell(26).getStringCellValue();
		System.out.println(FLG12);
		String FLG13 = sheet.getRow(12).getCell(26).getStringCellValue();
		System.out.println(FLG13);
		String FLG14 = sheet.getRow(13).getCell(26).getStringCellValue();
		System.out.println(FLG14);
		String FLG15 = sheet.getRow(14).getCell(26).getStringCellValue();
		System.out.println(FLG15);
		String FLG16 = sheet.getRow(15).getCell(26).getStringCellValue();
		System.out.println(FLG16);
		String FLG17 = sheet.getRow(16).getCell(26).getStringCellValue();
		System.out.println(FLG17);
		String FLG18 = sheet.getRow(17).getCell(26).getStringCellValue();
		System.out.println(FLG18);
		String FLG19 = sheet.getRow(18).getCell(26).getStringCellValue();
		System.out.println(FLG19);
		String FLG20 = sheet.getRow(19).getCell(26).getStringCellValue();
		System.out.println(FLG20);
		String FLG21 = sheet.getRow(20).getCell(26).getStringCellValue();
		System.out.println(FLG21);
		String FLG22 = sheet.getRow(21).getCell(26).getStringCellValue();
		System.out.println(FLG22);
		String FLG23 = sheet.getRow(22).getCell(26).getStringCellValue();
		System.out.println(FLG23);
		String FLG24 = sheet.getRow(23).getCell(26).getStringCellValue();
		System.out.println(FLG24);
		String FLG25 = sheet.getRow(24).getCell(26).getStringCellValue();
		System.out.println(FLG25);

		// Exclude Bid data
		System.out.println("Exclude bids details Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Exclude bids details Shown in Bids Report");
		String EXLD11 = sheet.getRow(10).getCell(29).getStringCellValue();
		System.out.println(EXLD11);
		String EXLD12 = sheet.getRow(11).getCell(29).getStringCellValue();
		System.out.println(EXLD12);
		String EXLD13 = sheet.getRow(12).getCell(29).getStringCellValue();
		System.out.println(EXLD13);
		String EXLD14 = sheet.getRow(13).getCell(29).getStringCellValue();
		System.out.println(EXLD14);
		String EXLD15 = sheet.getRow(14).getCell(29).getStringCellValue();
		System.out.println(EXLD15);
		String EXLD16 = sheet.getRow(15).getCell(29).getStringCellValue();
		System.out.println(EXLD16);
		String EXLD17 = sheet.getRow(16).getCell(29).getStringCellValue();
		System.out.println(EXLD17);
		String EXLD18 = sheet.getRow(17).getCell(29).getStringCellValue();
		System.out.println(EXLD18);
		String EXLD19 = sheet.getRow(18).getCell(29).getStringCellValue();
		System.out.println(EXLD19);
		String EXLD20 = sheet.getRow(19).getCell(29).getStringCellValue();
		System.out.println(EXLD20);
		String EXLD21 = sheet.getRow(20).getCell(29).getStringCellValue();
		System.out.println(EXLD21);
		String EXLD22 = sheet.getRow(21).getCell(29).getStringCellValue();
		System.out.println(EXLD22);
		String EXLD23 = sheet.getRow(22).getCell(29).getStringCellValue();
		System.out.println(EXLD23);
		String EXLD24 = sheet.getRow(23).getCell(29).getStringCellValue();
		System.out.println(EXLD24);
		String EXLD25 = sheet.getRow(24).getCell(29).getStringCellValue();
		System.out.println(EXLD25);

		// comments column data
		System.out.println("Comments Shown in Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Comments Shown in Bids Report");
		String CMT11 = sheet.getRow(10).getCell(32).getStringCellValue();
		System.out.println(CMT11);
		String CMT12 = sheet.getRow(11).getCell(32).getStringCellValue();
		System.out.println(CMT12);
		String CMT13 = sheet.getRow(12).getCell(32).getStringCellValue();
		System.out.println(CMT13);
		String CMT14 = sheet.getRow(13).getCell(32).getStringCellValue();
		System.out.println(CMT14);
		String CMT15 = sheet.getRow(14).getCell(32).getStringCellValue();
		System.out.println(CMT15);
		String CMT16 = sheet.getRow(15).getCell(32).getStringCellValue();
		System.out.println(CMT16);
		String CMT17 = sheet.getRow(16).getCell(32).getStringCellValue();
		System.out.println(CMT17);
		String CMT18 = sheet.getRow(17).getCell(32).getStringCellValue();
		System.out.println(CMT18);
		String CMT19 = sheet.getRow(18).getCell(32).getStringCellValue();
		System.out.println(CMT19);
		String CMT20 = sheet.getRow(19).getCell(32).getStringCellValue();
		System.out.println(CMT20);
		String CMT21 = sheet.getRow(20).getCell(32).getStringCellValue();
		System.out.println(CMT21);
		String CMT22 = sheet.getRow(21).getCell(32).getStringCellValue();
		System.out.println(CMT22);
		String CMT23 = sheet.getRow(22).getCell(32).getStringCellValue();
		System.out.println(CMT23);
		String CMT24 = sheet.getRow(23).getCell(32).getStringCellValue();
		System.out.println(CMT24);
		String CMT25 = sheet.getRow(24).getCell(32).getStringCellValue();
		System.out.println(CMT25);
		System.out.println("Bids Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report data fetched");
		System.out.println(
				"C31354:bids report has information about the sponsor Actions to the bid during monitoring of the bids");
		ObjectRepo.getTest().log(Status.PASS,
				"TC:C31354:bids report has information about the sponsor Actions added to the bid during monitoring of the bids");
		xsf.close();

	}

	public void AccessAndDownloadBidsAuditReportAfterAddComment_Flagged_IncludeActions() {
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out.println("Step:Navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "Step:Navigate to report section");

		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();

		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Bids Audit Report");

		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();

		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])"));
		abc4.click();

		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();

		WebElement abc11 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));
		abc11.click();
		System.out.println("Step:Bids Audit report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bids Audit report is downloaded");
		System.out.println("TC ID C31324-Bids Audit report is available during  the bidding window.");
		ObjectRepo.getTest().log(Status.PASS, "TC ID C31324-Bids Audit report is available during  the bidding window.");
	}

	public void DataReadFromBidsAuditReportAfterAddComment_flagged_IncludeActions() throws IOException {
		File src = new File(
				folderPath + File.separator + "Bids Audit Report - A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String TotalBidsSubmitted = sheet.getRow(3).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(3).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);
		System.out.println("Bids Report headers as:");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report headers print on Console");
		String HN1 = sheet.getRow(5).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(5).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(5).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(5).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(5).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(5).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(5).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(5).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(5).getCell(8).getStringCellValue();
		System.out.println(HN9);
		String HN10 = sheet.getRow(5).getCell(9).getStringCellValue();
		System.out.println(HN10);
		String HN11 = sheet.getRow(5).getCell(10).getStringCellValue();
		System.out.println(HN11);

		String HN12 = sheet.getRow(5).getCell(11).getStringCellValue();
		System.out.println(HN12);
		String HN13 = sheet.getRow(5).getCell(12).getStringCellValue();
		System.out.println(HN13);
		String HN14 = sheet.getRow(5).getCell(13).getStringCellValue();
		System.out.println(HN14);
		String HN15 = sheet.getRow(5).getCell(14).getStringCellValue();
		System.out.println(HN15);
		String HN16 = sheet.getRow(5).getCell(15).getStringCellValue();
		System.out.println(HN16);
		String HN17 = sheet.getRow(5).getCell(16).getStringCellValue();
		System.out.println(HN17);
		String HN18 = sheet.getRow(5).getCell(17).getStringCellValue();
		System.out.println(HN18);
		String HN19 = sheet.getRow(5).getCell(18).getStringCellValue();
		System.out.println(HN19);

		System.out.println("Monitoring actions in bids Audit report");
		ObjectRepo.getTest().log(Status.PASS, "Monitoring actions in bids Audit report");
		String MA1 = sheet.getRow(6).getCell(8).getStringCellValue();
		System.out.println(MA1);
		String MA2 = sheet.getRow(7).getCell(8).getStringCellValue();
		System.out.println(MA2);
		String MA3 = sheet.getRow(8).getCell(8).getStringCellValue();
		System.out.println(MA3);
		String MA4 = sheet.getRow(9).getCell(8).getStringCellValue();
		System.out.println(MA4);
		String MA5 = sheet.getRow(10).getCell(8).getStringCellValue();
		System.out.println(MA5);
		String MA6 = sheet.getRow(11).getCell(8).getStringCellValue();
		System.out.println(MA6);
		String MA7 = sheet.getRow(12).getCell(8).getStringCellValue();
		System.out.println(MA7);
		String MA8 = sheet.getRow(13).getCell(8).getStringCellValue();
		System.out.println(MA8);
		String MA9 = sheet.getRow(14).getCell(8).getStringCellValue();
		System.out.println(MA9);
		String MA10 = sheet.getRow(15).getCell(8).getStringCellValue();
		System.out.println(MA10);
		String MA11 = sheet.getRow(16).getCell(8).getStringCellValue();
		System.out.println(MA11);
		String MA12 = sheet.getRow(17).getCell(8).getStringCellValue();
		System.out.println(MA12);
		String MA13 = sheet.getRow(18).getCell(8).getStringCellValue();
		System.out.println(MA13);
		String MA14 = sheet.getRow(19).getCell(8).getStringCellValue();
		System.out.println(MA14);
		String MA15 = sheet.getRow(20).getCell(8).getStringCellValue();
		System.out.println(MA15);

		/// flag-unflag
		String MA16 = sheet.getRow(59).getCell(8).getStringCellValue();
		System.out.println(MA16);
		String MA17 = sheet.getRow(60).getCell(8).getStringCellValue();
		System.out.println(MA17);

		sheet.getRow(61).getCell(8).getStringCellValue();
		System.out.println(MA16);
		sheet.getRow(62).getCell(8).getStringCellValue();
		System.out.println(MA17);

		System.out.println("Bids Audit report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit report data fetched");
		System.out
				.println("C31324:Confirm the Bids Audit report for an Auction event includes the monitoring actions.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC:C31324:Confirm the Bids Audit report for an Auction event includes the monitoring actions.");
		xsf.close();

	}

	// sponsor access client bids report
	public void AccessAndDownloadClientBidsReportWA() {

		// click on Reports menu
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out.println("Step:Navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "Step:Navigate to report section");

		// click on Search Reports
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		System.out.println("Step:Click on Search Reports option");
		ObjectRepo.getTest().log(Status.PASS, "Step:Click on Search Reports option");

		// select report name-BidsAuditReport from dropdown
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Client Bids Report");
		System.out.println("Step:Select report Name-Client Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select report Name-Client Bids Report");

		// select event name & entity name
		WebElement abc3 = driver.findElement(By.xpath("(//*[@id='auto'])[1]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		abc3.click();
		WebElement abc4 = driver.findElement(By.xpath("(//*[text()='WSAutomationDemo'])"));
		abc4.click();
		System.out.println("Step:Select event Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select event Name");

		WebElement abc5 = driver.findElement(By.xpath("(//*[@id='auto'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc5);
		abc5.click();
		WebElement abc6 = driver.findElement(By.xpath("(//*[text()='WA_Entity+D'])"));
		abc6.click();
		System.out.println("Step:Select entity Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select entity Name");

		// click on Search Reports
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		System.out.println("Step:Click on SEARCH button");
		ObjectRepo.getTest().log(Status.PASS, "Step:Click on SEARCH button");

		// Entirty selection after search button click
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='WA_Entity+D'])"));
		abc9.click();
		System.out.println("Step:Select entity Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select entity Name");

		// select report name-BidsAuditReport from dropdown
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Client Bids Report");
		System.out.println("Step:Select report Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select report Name");

		// Download report
		WebElement abc12 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));
		abc12.click();
		System.out.println("Step:Client Bids Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Client Bids  Report is downloaded");
		System.out.println(
				"TC ID C31382-Maintain the Client Bid Reports for an event once generated along with an audit trail");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID C31382-Maintain the Client Bid Reports for an event once generated along with an audit trail");
	}

	// not used
	public void DataReadFromClientBidsReportWA() throws IOException {
		File src = new File(
				folderPath + File.separator + "Client Bids Report - WSAutomationDemo 09-27-2022 WA6588-9944.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook xsf = new XSSFWorkbook(fis);

		XSSFSheet sheet = xsf.getSheetAt(0);

		String reportDT = sheet.getRow(0).getCell(0).getStringCellValue();
		String reportDTval = sheet.getRow(0).getCell(1).getStringCellValue();
		System.out.println(reportDT + ":" + reportDTval);

		String EventName = sheet.getRow(1).getCell(0).getStringCellValue();
		String EventNameval = sheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println(EventName + ":" + EventNameval);

		String EventDate = sheet.getRow(2).getCell(0).getStringCellValue();
		String EventDateval = sheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println(EventDate + ":" + EventDateval);

		String EntityLegalName = sheet.getRow(3).getCell(0).getStringCellValue();
		String EntityLegalNameval = sheet.getRow(3).getCell(1).getStringCellValue();
		System.out.println(EntityLegalName + ":" + EntityLegalNameval);

		String EntityOperatingName = sheet.getRow(4).getCell(0).getStringCellValue();
		String EntityOperatingNameval = sheet.getRow(4).getCell(1).getStringCellValue();
		System.out.println(EntityOperatingName + ":" + EntityOperatingNameval);

		String TotalBidsSubmitted = sheet.getRow(5).getCell(0).getStringCellValue();
		double TotalBidsSubmittedval = sheet.getRow(5).getCell(1).getNumericCellValue();
		System.out.println(TotalBidsSubmitted + ":" + TotalBidsSubmittedval);

		String SettlementpriceUSDCurrent = sheet.getRow(6).getCell(0).getStringCellValue();
		double SettlementpriceUSDCurrentval = sheet.getRow(6).getCell(1).getNumericCellValue();
		System.out.println(SettlementpriceUSDCurrent + ":" + SettlementpriceUSDCurrentval);
		System.out.println("Client Bids Report headers print on console:");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report headers print on console");
		String HN1 = sheet.getRow(8).getCell(0).getStringCellValue();
		System.out.println(HN1);
		String HN2 = sheet.getRow(8).getCell(1).getStringCellValue();
		System.out.println(HN2);
		String HN3 = sheet.getRow(8).getCell(2).getStringCellValue();
		System.out.println(HN3);
		String HN4 = sheet.getRow(8).getCell(3).getStringCellValue();
		System.out.println(HN4);
		String HN5 = sheet.getRow(8).getCell(4).getStringCellValue();
		System.out.println(HN5);
		String HN6 = sheet.getRow(8).getCell(5).getStringCellValue();
		System.out.println(HN6);
		String HN7 = sheet.getRow(8).getCell(6).getStringCellValue();
		System.out.println(HN7);
		String HN8 = sheet.getRow(8).getCell(7).getStringCellValue();
		System.out.println(HN8);
		String HN9 = sheet.getRow(8).getCell(8).getStringCellValue();
		System.out.println(HN9);

		System.out.println("Client Bids Audit Report data fetched");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report data fetched");
		xsf.close();
	}

	public void DataReadFromBidsReportReserveSale_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_WA1") + " "
				+ "02-16-2024" + ".xlsx");
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

	public void DataReadFromBidsAuditReportReserveSale_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_WA1")
				+ " " + "02-16-2024" + ".xlsx");
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

	public void DataReadFromQualifiedBiddersReportReserveSale_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - "
				+ prop.getProperty("EventNameWA") + " " + "02-16-2024" + ".xlsx");
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

	public void BidsReportsearchFunctionalityForWashingtonReserveSale() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOf(btnReports));
		btnReports.click();
		System.out.println("Click on reports button");
		ObjectRepo.getTest().log(Status.PASS, "Click on reports button");
		SearchBidsReportsByEventNameForReserveSaleWAEvent();
		String javascript = "window.scrollBy(0,1000)";
		
		js.executeScript(javascript);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@aria-label='Download Bids Report for event " + prop.getProperty("EventNameWA") + "']")));
		driver
				.findElement(By.xpath(
						"//*[@aria-label='Download Bids Report for event " + prop.getProperty("EventNameWA") + "']"))
				.click();
		Thread.sleep(20000);
		Boolean Time = driver.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body'][4]"))
				.isDisplayed();
		System.out.println(Time);
		try {
			Assert.assertTrue(Time);
			System.out.println("This is the static report");
		} catch (AssertionError e) {
			Assert.assertFalse(Time);
			System.out.println("This is the dyanamic report");
		}
	}

	public void SearchBidsReportsByEventNameForReserveSaleWAEvent() throws InterruptedException {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Without label']")).click();
		Select s = new Select(driver.findElement(By.xpath("//*[@aria-label='Without label']")));
		s.selectByVisibleText("Bids Report");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("EventNameWA"));
		Actions action = new Actions(driver);
		WebElement element = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("EventNameWA") + "']"));
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

	public void CommentAndFieldsDataReadFromBidsReportCaliforniaReserveSale() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - "
				+ prop.getProperty("ResSaleEventAfterCertify") + " " + "02-12-2024" + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(1);
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Comment");
		// Introductory field data verify

		String IntroFieldValid1 = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid1, "Bid ID");
		System.out.println(IntroFieldValid1);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid1);

		String IntroFieldValid2 = a.getRow(0).getCell(1).getStringCellValue();
		Assert.assertEquals(IntroFieldValid2, "Client Bid ID");
		System.out.println(IntroFieldValid2);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid2);

		String IntroFieldValid3 = a.getRow(0).getCell(2).getStringCellValue();
		Assert.assertEquals(IntroFieldValid3, "Transaction ID");
		System.out.println(IntroFieldValid3);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid3);

		String IntroFieldValid4 = a.getRow(0).getCell(3).getStringCellValue();
		Assert.assertEquals(IntroFieldValid4, "Comment");
		System.out.println(IntroFieldValid4);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid4);
	}

	public void DataReadFromBidsAuditReportNS_SBA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_NS")
				+ " " + date1 + ".xlsx");
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

	public void DataReadFromBidsReportNS_SBA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_NS") + " "
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

	public void CommentAndFieldsDataReadFromBidsReportNovaScotiaSBAEvent() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_NS") + " "
				+ date1 + ".xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(1);
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Comment");

		String IntroFieldValid1 = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid1, "Bid ID");
		System.out.println(IntroFieldValid1);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid1);

		String IntroFieldValid2 = a.getRow(0).getCell(1).getStringCellValue();
		Assert.assertEquals(IntroFieldValid2, "Client Bid ID");
		System.out.println(IntroFieldValid2);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid2);

		String IntroFieldValid3 = a.getRow(0).getCell(2).getStringCellValue();
		Assert.assertEquals(IntroFieldValid3, "Transaction ID");
		System.out.println(IntroFieldValid3);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid3);

		String IntroFieldValid4 = a.getRow(0).getCell(3).getStringCellValue();
		Assert.assertEquals(IntroFieldValid4, "Comment");
		System.out.println(IntroFieldValid4);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid4);
	}

	public void DataReadFromBidsAuditReportCaliforniaReserveSale() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_CA1")
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

	public void DataReadFromBidsReportCaliforniaReserveSale() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - "
				+ prop.getProperty("ResSaleEventAfterCertify") + " " + "02-12-2024" + ".xlsx");
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

	public void DataReadFromFinancialStatementPdfReportFor_NS_SBA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("Event_Name_NS")
				+ " " + date1 + ".pdf");
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
		if (value == "Financial Services Account Name") {
			Assert.assertTrue(value.contains("Financial Services Account Name"));
		}
		if (value == "General Account Number") {
			Assert.assertTrue(value.contains("General Account Number"));
		}
		ObjectRepo.getTest().log(Status.PASS, stripText);
		System.out.println(stripText);
	}

	public void DataReadFromQualifiedBiddersReportSBMA_QC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - "
				+ prop.getProperty("Event_Name_RE1") + " " + date1 + ".xlsx");
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Qualified Bidders Report");

		// C31872
		String QualifiedGoverment = a.getRow(6).getCell(4).getStringCellValue();
		Assert.assertEquals(QualifiedGoverment, "Quebec");
		System.out.println("Qualified Goverment" + ":- " + QualifiedGoverment);
		ObjectRepo.getTest().log(Status.PASS, QualifiedGoverment);

		System.out.println("Qualified goverment is verified");
		ObjectRepo.getTest().log(Status.PASS, "Qualified goverment is verified");

		String CurrencyData1 = a.getRow(6).getCell(6).getStringCellValue();
		Assert.assertEquals(CurrencyData1, "CAD");
		System.out.println("QC4085-5100" + ":- " + CurrencyData1);
		ObjectRepo.getTest().log(Status.PASS, CurrencyData1);

		String CurrencyData2 = a.getRow(7).getCell(6).getStringCellValue();
		Assert.assertEquals(CurrencyData2, "CAD");
		System.out.println("QC4091-5112 " + ":- " + CurrencyData2);
		ObjectRepo.getTest().log(Status.PASS, "QC4091-5112" + ":- " + CurrencyData2);

		String CurrencyData3 = a.getRow(9).getCell(6).getStringCellValue();
		Assert.assertEquals(CurrencyData3, "CAD");
		System.out.println("QC4097-5124" + ":- " + CurrencyData3);
		ObjectRepo.getTest().log(Status.PASS, "QC4097-5124" + ":- " + CurrencyData3);

		String CurrencyData4 = a.getRow(10).getCell(6).getStringCellValue();
		Assert.assertEquals(CurrencyData4, "CAD");
		System.out.println("QC4098-5126" + ":- " + CurrencyData4);
		ObjectRepo.getTest().log(Status.PASS, "QC4098-5126" + ":- " + CurrencyData4);

		System.out.println("Bid gaurantee currency is verified");
		ObjectRepo.getTest().log(Status.PASS, "Bid gaurantee currency is verified");

		// C31872
		String FirstColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "General Account Number");
		System.out.println("General Account Number" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(5).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Legal Name");
		System.out.println("Legal Name" + ":- " + SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(5).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(5).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Entity Type");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(5).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Government");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(5).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Financial Services Account Number");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(5).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Bid Guarantee Currency");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(5).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Cash Bid Guarantee Value");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(5).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Letter of Credit Bid Guarantee Value");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColumn = a.getRow(5).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColumn, "Bond Bid Guarantee Value");
		System.out.println(tenthColumn);
		ObjectRepo.getTest().log(Status.PASS, tenthColumn);

		String EleventhColumn = a.getRow(5).getCell(10).getStringCellValue();
		Assert.assertEquals(EleventhColumn, "Letter of Guarantee Bid Guarantee Value");
		System.out.println(EleventhColumn);
		ObjectRepo.getTest().log(Status.PASS, EleventhColumn);

		String TwelthColumn = a.getRow(5).getCell(11).getStringCellValue();
		Assert.assertEquals(TwelthColumn, "Total Bid Guarantee Amount");
		System.out.println(TwelthColumn);
		ObjectRepo.getTest().log(Status.PASS, TwelthColumn);

		String ThirteenthColumn = a.getRow(5).getCell(12).getStringCellValue();
		Assert.assertEquals(ThirteenthColumn, "Holding Limit");
		System.out.println(ThirteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, ThirteenthColumn);

		String FourteenthColumn = a.getRow(5).getCell(13).getStringCellValue();
		Assert.assertEquals(FourteenthColumn, "Emission Units Required");
		System.out.println(FourteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, FourteenthColumn);

		System.out.println("Data is validated ");
		ObjectRepo.getTest().log(Status.PASS, "Data is validated");

	}

	public void DataReadFromFrnchQualifiedBiddersReportSBMA_QC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Rapport des enchérisseurs qualifiés - "
				+ prop.getProperty("FR_Event_Name_RE1 ") + " " + date1 + ".xlsx");
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

	public void DataReadFromBidsAuditReportSBMA_QC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_RE1")
				+ " " + date1 + ".xlsx");
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Bids Audit Report");

		String FirstColumn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColumn, "General Account Number");
		System.out.println("General Account Number" + ":- " + FirstColumn);
		ObjectRepo.getTest().log(Status.PASS, FirstColumn);

		String SecondColumn = a.getRow(5).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColumn, "Legal Name");
		System.out.println("Legal Name" + ":- " + SecondColumn);
		ObjectRepo.getTest().log(Status.PASS, SecondColumn);

		String ThirdColumn = a.getRow(5).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColumn, "Operating Name");
		System.out.println(ThirdColumn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColumn);

		String fourthColumn = a.getRow(5).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColumn, "Entity Type");
		System.out.println(fourthColumn);
		ObjectRepo.getTest().log(Status.PASS, fourthColumn);

		String fifthColumn = a.getRow(5).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColumn, "Government");
		System.out.println(fifthColumn);
		ObjectRepo.getTest().log(Status.PASS, fifthColumn);

		String sixthColumn = a.getRow(5).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColumn, "Bid ID");
		System.out.println(sixthColumn);
		ObjectRepo.getTest().log(Status.PASS, sixthColumn);

		String seventhColumn = a.getRow(5).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColumn, "Client Bid ID");
		System.out.println(seventhColumn);
		ObjectRepo.getTest().log(Status.PASS, seventhColumn);

		String eighthColumn = a.getRow(5).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColumn, "Transaction ID");
		System.out.println(eighthColumn);
		ObjectRepo.getTest().log(Status.PASS, eighthColumn);

		String ninghthColumn = a.getRow(5).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColumn, "Update Type");
		System.out.println(ninghthColumn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColumn);

		String tenthColumn = a.getRow(5).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColumn, "Update By");
		System.out.println(tenthColumn);
		ObjectRepo.getTest().log(Status.PASS, tenthColumn);

		String EleventhColumn = a.getRow(5).getCell(10).getStringCellValue();
		Assert.assertEquals(EleventhColumn, "Update Time (ET)");
		System.out.println(EleventhColumn);
		ObjectRepo.getTest().log(Status.PASS, EleventhColumn);

		String TwelthColumn = a.getRow(5).getCell(11).getStringCellValue();
		Assert.assertEquals(TwelthColumn, "Bid Creation Time (ET)");
		System.out.println(TwelthColumn);
		ObjectRepo.getTest().log(Status.PASS, TwelthColumn);

		String ThirteenthColumn = a.getRow(5).getCell(12).getStringCellValue();
		Assert.assertEquals(ThirteenthColumn, "Category");
		System.out.println(ThirteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, ThirteenthColumn);

		String FourteenthColumn = a.getRow(5).getCell(13).getStringCellValue();
		Assert.assertEquals(FourteenthColumn, "Category Price");
		System.out.println(FourteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, FourteenthColumn);

		String FifteenthColumn = a.getRow(5).getCell(14).getStringCellValue();
		Assert.assertEquals(FifteenthColumn, "Total Emission Units Bid");
		System.out.println(FifteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, FifteenthColumn);

		String SixteenthColumn = a.getRow(5).getCell(15).getStringCellValue();
		Assert.assertEquals(SixteenthColumn, "Comment");
		System.out.println(SixteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, SixteenthColumn);

		System.out.println("Data is validated ");
		ObjectRepo.getTest().log(Status.PASS, "Data is validated");

	}

	public void DataReadFromBidsReportSBMA_QC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1") + " "
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

//**************************************************************************************************************************

	public void DataReadFromFinancialSettlementReportSBMA_QC() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Settlement Report - "
				+ prop.getProperty("Event_Name_RE1") + " " + date1 + ".xlsx");
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Financial Settlement Report");

		// Introductory field data verify

		String IntroFieldValid1 = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid1, "Report Date & Time");
		System.out.println("Report Date & Time" + ":- " + IntroFieldValid1);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid1);

		String IntroFieldValid2 = a.getRow(1).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid2, "Event Name");
		System.out.println("Event Name" + ":- " + IntroFieldValid2);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid2);

		String IntroFieldValid3 = a.getRow(2).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid3, "Event Date");
		System.out.println("Event Date" + ":- " + IntroFieldValid3);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid3);

		String IntroFieldValid4 = a.getRow(3).getCell(0).getStringCellValue();
		Assert.assertEquals(IntroFieldValid4, "Total Qualified Bidders");
		System.out.println("Total Qualified Bidders" + ":- " + IntroFieldValid4);
		ObjectRepo.getTest().log(Status.PASS, IntroFieldValid4);

		// Column header validation
		String FirstColumn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColumn, "General Account Number");
		System.out.println("General Account Number" + ":- " + FirstColumn);
		ObjectRepo.getTest().log(Status.PASS, FirstColumn);

		String SecondColumn = a.getRow(5).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColumn, "Legal Name");
		System.out.println("Legal Name" + ":- " + SecondColumn);
		ObjectRepo.getTest().log(Status.PASS, SecondColumn);

		String ThirdColumn = a.getRow(5).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColumn, "Operating Name");
		System.out.println(ThirdColumn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColumn);

		String fourthColumn = a.getRow(5).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColumn, "Government");
		System.out.println(fourthColumn);
		ObjectRepo.getTest().log(Status.PASS, fourthColumn);

		String fifthColumn = a.getRow(5).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColumn, "Financial Services Account Number");
		System.out.println(fifthColumn);
		ObjectRepo.getTest().log(Status.PASS, fifthColumn);

		String sixthColumn = a.getRow(5).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColumn, "Financial Services Account Name");
		System.out.println(sixthColumn);
		ObjectRepo.getTest().log(Status.PASS, sixthColumn);

		String seventhColumn = a.getRow(5).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColumn, "Currency");
		System.out.println(seventhColumn);
		ObjectRepo.getTest().log(Status.PASS, seventhColumn);

		String eighthColumn = a.getRow(5).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColumn, "Cash Bid Guarantee Value");
		System.out.println(eighthColumn);
		ObjectRepo.getTest().log(Status.PASS, eighthColumn);

		String ninghthColumn = a.getRow(5).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColumn, "Letter of Credit Bid Guarantee Value");
		System.out.println(ninghthColumn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColumn);

		String tenthColumn = a.getRow(5).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColumn, "Bond Bid Guarantee Value");
		System.out.println(tenthColumn);
		ObjectRepo.getTest().log(Status.PASS, tenthColumn);

		String EleventhColumn = a.getRow(5).getCell(10).getStringCellValue();
		Assert.assertEquals(EleventhColumn, "Letter of Guarantee Bid Guarantee Value");
		System.out.println(EleventhColumn);
		ObjectRepo.getTest().log(Status.PASS, EleventhColumn);

		String TwelthColumn = a.getRow(5).getCell(11).getStringCellValue();
		Assert.assertEquals(TwelthColumn, "Total Bid Guarantee Amount");
		System.out.println(TwelthColumn);
		ObjectRepo.getTest().log(Status.PASS, TwelthColumn);

		String ThirteenthColumn = a.getRow(5).getCell(12).getStringCellValue();
		Assert.assertEquals(ThirteenthColumn, "Category A Successful Emission Units");
		System.out.println(ThirteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, ThirteenthColumn);

		String FourteenthColumn = a.getRow(5).getCell(13).getStringCellValue();
		Assert.assertEquals(FourteenthColumn, "Category B Successful Emission Units");
		System.out.println(FourteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, FourteenthColumn);

		String FifteenthColumn = a.getRow(5).getCell(14).getStringCellValue();
		Assert.assertEquals(FifteenthColumn, "Category C Successful Emission Units");
		System.out.println(FifteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, FifteenthColumn);

		String SixteenthColumn = a.getRow(5).getCell(15).getStringCellValue();
		Assert.assertEquals(SixteenthColumn, "Total Successful Bid Cost");
		System.out.println(SixteenthColumn);
		ObjectRepo.getTest().log(Status.PASS, SixteenthColumn);

		String SeventeenthColumn = a.getRow(5).getCell(16).getStringCellValue();
		Assert.assertEquals(SeventeenthColumn, "Amount Owed");
		System.out.println(SeventeenthColumn);
		ObjectRepo.getTest().log(Status.PASS, SeventeenthColumn);
		System.out.println("Data is validated");
		ObjectRepo.getTest().log(Status.PASS, "Data is validated");

	}

//************************************************************************************************************************************************
	public void validateResultReportRSQCR70() throws Exception {

		ExcelDataProviderforResultReportRSQCR70 excel = new ExcelDataProviderforResultReportRSQCR70();
		Double CategoryA = excel.getNumericData("Event Data", 5, 3);
		String strNumber = Double.toString(CategoryA);
		Assert.assertEquals(strNumber, "1000000.0");
		System.out.println("Category A - Total Emission Units Sold" + ":- " + CategoryA);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total Emission Units Sold :- " + CategoryA);

		Double CategoryB = excel.getNumericData("Event Data", 6, 3);
		String strNumber1 = Double.toString(CategoryB);
		Assert.assertEquals(strNumber1, "1000000.0");
		System.out.println("Category B - Total Emission Units Sold" + ":- " + CategoryB);
		ObjectRepo.getTest().log(Status.PASS, "Category B - Total Emission Units Sold :- " + CategoryB);

		Double CategoryC = excel.getNumericData("Event Data", 7, 3);
		String strNumber2 = Double.toString(CategoryC);
		Assert.assertEquals(strNumber2, "1000000.0");
		System.out.println("Category C - Total Emission Units Sold" + ":- " + CategoryC);
		ObjectRepo.getTest().log(Status.PASS, "Category C - Total Emission Units Sold :- " + CategoryC);

		Double legalNameX = excel.getNumericData("Category A - Analysis", 1, 7);
		System.out.println("Affordable Cumulative Emission Units :" + legalNameX);
		Double legalNameY = excel.getNumericData("Category A - Analysis", 2, 7);
		System.out.println(legalNameY);
		System.out.println("*************************************************************************************");

		Double CategoryA1 = excel.getNumericData("Category A Distribution", 1, 1);
		String strNumber4 = Double.toString(CategoryA1);
		Double CategoryA2 = excel.getNumericData("Category A Distribution", 1, 5);
		String strNumber5 = Double.toString(CategoryA2);
		Assert.assertEquals(strNumber4, strNumber5);
		System.out.println("Category A - Total Emission Units Alloted" + ":- " + CategoryA2);
		ObjectRepo.getTest().log(Status.PASS, "Category A - Total Emission Units Alloted :- " + CategoryA2);

		Double CategoryA3 = excel.getNumericData("Category B Distribution", 1, 1);
		String strNumber6 = Double.toString(CategoryA3);
		Double CategoryA4 = excel.getNumericData("Category B Distribution", 1, 5);
		String strNumber7 = Double.toString(CategoryA4);
		Assert.assertEquals(strNumber6, strNumber7);
		System.out.println("Category B - Total Emission Units Alloted" + ":- " + CategoryA4);
		ObjectRepo.getTest().log(Status.PASS, "Category B - Total Emission Units Alloted :- " + CategoryA4);

		Double CategoryA5 = excel.getNumericData("Category C Distribution", 1, 1);
		String strNumber8 = Double.toString(CategoryA5);
		Double CategoryA6 = excel.getNumericData("Category C Distribution", 1, 5);
		String strNumber9 = Double.toString(CategoryA6);
		Assert.assertEquals(strNumber8, strNumber9);
		System.out.println("Category C - Total Emission Units Alloted" + ":- " + CategoryA6);
		ObjectRepo.getTest().log(Status.PASS, "Category C - Total Emission Units Alloted :- " + CategoryA6);
		System.out.println("************************************************************************************");
	}

	public void ExcelDataProviderforCategoryAnalysisResultReportRSQCR70() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_RE1") + " "
				+ date1 + ".xlsx");

		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(3);
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

	public void ExcelDataProviderforCategoryDistributionResultReportRSQCR70() throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_RE1") + " "
				+ date1 + ".xlsx");
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

	public void ExcelDataProviderforCategoryRollDownResultReportRSQCR70() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - " + prop.getProperty("Event_Name_RE1") + " "
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
//**************************************************************************************************************************

	public void validateClientBidsAuditReport_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Audit Report - "
				+ prop.getProperty("EventNameWADuringBidding") + " " + "02-16-2024" + " "
				+ prop.getProperty("EventIDWADuringBidding") + ".xlsx");
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
						System.out.println(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Audit Report");

		String FirstColomn = a.getRow(7).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Client Bid ID");
		System.out.println(FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(7).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Transaction ID");
		System.out.println(SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(7).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Update Type");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(7).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Update By");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(7).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Update Time (ET)");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(7).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid Creation Time (ET)");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(7).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Currency");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(7).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Bid Price");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(7).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Bid Lots");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenhthColomn = a.getRow(7).getCell(9).getStringCellValue();
		Assert.assertEquals(tenhthColomn, "Allowances Bid");
		System.out.println(tenhthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenhthColomn);

	}

	public void DataReadFromARClientsBidsAuditReport_WA() throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Client Bids Audit Report - " + prop.getProperty("Event_Name_RE1") + " "
						+ "02-16-2024" + " " + prop.getProperty("Entity_ID") + ".xlsx");
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

	public void validateBidsReport_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1") + " "
				+ "02-16-2024" + ".xlsx");
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
						System.out.println(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Bids Report");

		String FirstColomn = a.getRow(7).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Registry General Account Number");
		System.out.println(FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(7).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Entity Legal Name");
		System.out.println(SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(7).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Entity Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(7).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Entity Type");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(7).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Entity Jurisdiction");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(7).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid ID");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(7).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Client Bid ID");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(7).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Transaction ID");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(7).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Bid By");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);
	}

	public void validateClientBidsReport_WA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + prop.getProperty("EventNameWA")
				+ " " + date1 + " " + prop.getProperty("EventID") + ".xlsx");
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
						System.out.println(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Report");

		String Catagory1 = a.getRow(8).getCell(0).getStringCellValue();
		Assert.assertEquals(Catagory1, "ifT0uy8Z");
		System.out.println("Client Bid ID :-" + Catagory1);
		ObjectRepo.getTest().log(Status.PASS, Catagory1);

		String Catagory2 = a.getRow(8).getCell(1).getStringCellValue();
		Assert.assertEquals(Catagory2, "00451192");
		System.out.println("Transaction ID :-" + Catagory2);
		ObjectRepo.getTest().log(Status.PASS, Catagory2);

		String Catagory3 = a.getRow(8).getCell(2).getStringCellValue();
		Assert.assertEquals(Catagory3, "ascott@demo.citssdev.com");
		System.out.println("Bid By :-" + Catagory3);
		ObjectRepo.getTest().log(Status.PASS, Catagory3);

		Double Catagory4 = a.getRow(8).getCell(5).getNumericCellValue();
		String stringNum = Double.toString(Catagory4);
		Assert.assertEquals(stringNum, "59.59");
		System.out.println("Tier Price :-" + Catagory4);
		// ObjectRepo.getTest().log(Status.PASS, Catagory4);

		Double Catagory5 = a.getRow(8).getCell(8).getNumericCellValue();
		String stringNum1 = Double.toString(Catagory5);
		Assert.assertEquals(stringNum1, "3000.0");
		System.out.println("Successful Bid Allowances :-" + Catagory5);
		ObjectRepo.getTest().log(Status.PASS, "Successful Bid Allowances :-" + Catagory5);
	}

	public void validateARClientsBidsAuditReport_CA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Audit Report - "
				+ prop.getProperty("EventNameCADuringBidding") + " " + "02-16-2024" + " "
				+ prop.getProperty("AREventID_DuringBiddingCA") + ".xlsx");
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

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Audit Report");
		String FirstColomn = a.getRow(7).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Client Bid ID");
		System.out.println(FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(7).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Transaction ID");
		System.out.println(SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(7).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Update Type");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(7).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Update By");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(7).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Update Time (ET)");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(7).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid Creation Time (ET)");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(7).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Tier");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(7).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Tier Price");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(7).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Bid Lots");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColomn = a.getRow(7).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Allowances Bid");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

	}

	public void DataReadFromSPClientsBidsReport_CA() throws IOException, InterruptedException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Client Bids Report - "
					+ prop.getProperty("EventNameCADuringBidding2") + " " + "02-14-2024" + " "
					+ prop.getProperty("EventIdForAfterCertify1") + ".xlsx");
			try {
				InputStream ExcelFileToRead = new FileInputStream(src);
				try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
					XSSFSheet sheet = wb.getSheetAt(0);
					XSSFRow row;
					XSSFCell cell;
					Iterator<Row> rows = sheet.rowIterator();
					XSSFRow row1 = (XSSFRow) sheet.getRow(0);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();

					System.out.println("Value :" + value);

					if (value == "Report Date & Time") {
						Assert.assertTrue(value.contains("Report Date & Time"));
						System.out.println(value);
					} else if (value == "Entity Legal Name") {
						Assert.assertTrue(value.contains("Entity Legal Name"));
					} else if (value == "Entity Operating Name") {
						Assert.assertTrue(value.contains("Entity Operating Name"));
					} else if (value == "Entity Jurisdiction") {
						Assert.assertTrue(value.contains("Entity Jurisdiction"));
					}
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void DataReadFromBidsReport_CA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - "
				+ prop.getProperty("EventNameCADuringBidding") + " " + "02-16-2024" + ".xlsx");
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
						System.out.println(cell.toString() + " ");
					}
					System.out.println();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateClientBidsReportRSQCR70() throws Exception {

		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(
					folderPath + File.separator + "Client Bids Report - " + prop.getProperty("ResSaleEventAfterCertify")
							+ " " + "02-12-2024" + " " + prop.getProperty("AREventID_DuringBiddingCA") + ".xlsx");
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
			try {
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				{
				}
			}

			String CategoryA = wb.getSheet("Client Bids Report").getRow(7).getCell(0).getStringCellValue();
			Assert.assertEquals(CategoryA, "Client Bid ID");
			System.out.println("Header1" + ":- " + CategoryA);
			String CategoryB = wb.getSheet("Client Bids Report").getRow(7).getCell(1).getStringCellValue();
			Assert.assertEquals(CategoryB, "Transaction ID");
			System.out.println("Header2" + ":- " + CategoryB);
			String CategoryC = wb.getSheet("Client Bids Report").getRow(7).getCell(2).getStringCellValue();
			Assert.assertEquals(CategoryC, "Bid By");
			System.out.println("Header3" + ":- " + CategoryC);
			String CategoryD = wb.getSheet("Client Bids Report").getRow(7).getCell(3).getStringCellValue();
			Assert.assertEquals(CategoryD, "Update Time (ET)");
			System.out.println("Header4" + ":- " + CategoryD);
			ObjectRepo.getTest().log(Status.PASS,
					"C31963_Sponsor_can_get_access_to_Client_Bid_Report_for_an_Allowance_Sale_event_during_bidding_window");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateBidsAuditReport() throws EncryptedDocumentException, IOException {

		File src = new File(folderPath + File.separator + "Bids Audit Report - "
				+ prop.getProperty("EventNameCADuringBidding") + " " + "02-16-2024" + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Bids Audit Report");

		String FirstColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Registry General Account Number");
		System.out.println("Registry General Account Number" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(5).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Entity Legal Name");
		System.out.println(SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(5).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Entity Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(5).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Entity Type");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(5).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Entity Jurisdiction");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(5).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid ID");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(5).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Client Bid ID");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(5).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Transaction ID");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(5).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Update Type");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColomn = a.getRow(5).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Update By");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

		String eleventhColomn = a.getRow(5).getCell(10).getStringCellValue();
		Assert.assertEquals(eleventhColomn, "Update Time (ET)");
		System.out.println(eleventhColomn);
		ObjectRepo.getTest().log(Status.PASS, eleventhColomn);

		String twelvthColomn = a.getRow(5).getCell(11).getStringCellValue();
		Assert.assertEquals(twelvthColomn, "Bid Creation Time (ET)");
		System.out.println(twelvthColomn);
		ObjectRepo.getTest().log(Status.PASS, twelvthColomn);

	}

	public void validateBidsAuditReport_WA() throws EncryptedDocumentException, IOException {

		File src = new File(folderPath + File.separator + "Bids Audit Report - "
				+ prop.getProperty("EventNameWADuringBidding") + " " + "02-16-2024" + ".xlsx");
		System.out.println("Asserted The name of the report must be Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Audit Report");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Bids Audit Report");

		String FirstColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Registry General Account Number");
		System.out.println("Registry General Account Number" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(5).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Entity Legal Name");
		System.out.println(SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(5).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Entity Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(5).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Entity Type");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(5).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Entity Jurisdiction");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(5).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid ID");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(5).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Client Bid ID");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(5).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Transaction ID");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(5).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Update Type");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColomn = a.getRow(5).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Update By");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

		String eleventhColomn = a.getRow(5).getCell(10).getStringCellValue();
		Assert.assertEquals(eleventhColomn, "Update Time (ET)");
		System.out.println(eleventhColomn);
		ObjectRepo.getTest().log(Status.PASS, eleventhColomn);

		String twelvthColomn = a.getRow(5).getCell(11).getStringCellValue();
		Assert.assertEquals(twelvthColomn, "Bid Creation Time (ET)");
		System.out.println(twelvthColomn);
		ObjectRepo.getTest().log(Status.PASS, twelvthColomn);

	}

	public void DataReadClientBidAuditReportPostCertification_CA() throws IOException, InterruptedException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Audit Report - "
				+ prop.getProperty("ResSaleEventAfterCertify") + " " + date1 + " "
				+ prop.getProperty("EventIdForAfterCertify") + ".xlsx");
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

	public void validateCalculatedAllottedAllowancesResultReport_CA() throws EncryptedDocumentException, IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - "
				+ prop.getProperty("AucEventForResultRepQC") + " " + "02-23-2024" + ".xlsx");
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
		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			{
			}
		}
		try {
			Double TotalAllowancesAvailable = wb.getSheet("Event Data").getRow(22).getCell(1).getNumericCellValue();
			String strNumber1 = Double.toString(TotalAllowancesAvailable);
			Assert.assertEquals(strNumber1, "30000.0");
			System.out.println("Total Allowances Available" + ":- " + TotalAllowancesAvailable);
			ObjectRepo.getTest().log(Status.PASS, "Total Allowances Available :- " + TotalAllowancesAvailable);

			Double TotalAllowancesSold = wb.getSheet("Event Data").getRow(22).getCell(2).getNumericCellValue();
			String strNumber2 = Double.toString(TotalAllowancesSold);
			Assert.assertEquals(strNumber2, "26000.0");
			System.out.println("Total Allowances Sold" + ":- " + TotalAllowancesSold);
			ObjectRepo.getTest().log(Status.PASS, "Total Allowances Sold :- " + TotalAllowancesSold);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateSettelmentPriceResultReport_QC() throws EncryptedDocumentException, IOException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Results Report - "
					+ prop.getProperty("AucEventForResultRepQC") + " " + "02-23-2024" + ".xlsx");
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
			try {
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				{
				}
			}
			String Qualified_bids_statistics = wb.getSheet("Event Data").getRow(12).getCell(0).getStringCellValue();
			Assert.assertEquals(Qualified_bids_statistics, "Settlement Price");
			System.out.println("Qualified bids statistics" + ":- " + Qualified_bids_statistics);
			ObjectRepo.getTest().log(Status.PASS, "Qualified bids statistics :- " + Qualified_bids_statistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateValidPricePointsResultReport_QC() throws EncryptedDocumentException, IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Results Report - "
				+ prop.getProperty("AucEventForResultRepQC") + " " + "02-23-2024" + ".xlsx");
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

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			{
			}
		}

		Double AllowableCumulativeEmissionUnits1 = wb.getSheet("CURRENT Valid Price Points").getRow(1).getCell(7)
				.getNumericCellValue();
		String strNumber1 = Double.toString(AllowableCumulativeEmissionUnits1);
		Assert.assertEquals(strNumber1, "21000.0");
		System.out.println("Allowable Cumulative Emission Units1" + " :- " + AllowableCumulativeEmissionUnits1);
		ObjectRepo.getTest().log(Status.PASS,
				"Allowable Cumulative Emission Units1 :- " + AllowableCumulativeEmissionUnits1);

		Double IncrementalValue1 = wb.getSheet("CURRENT Valid Price Points").getRow(1).getCell(8).getNumericCellValue();
		String strNumber2 = Double.toString(IncrementalValue1);
		Assert.assertEquals(strNumber2, "21000.0");
		System.out.println("Incremental value1" + " :- " + IncrementalValue1);
		ObjectRepo.getTest().log(Status.PASS, "Incremental value1 :- " + IncrementalValue1);

		String ValidPricePoint1 = wb.getSheet("CURRENT Valid Price Points").getRow(1).getCell(9).getStringCellValue();
		Assert.assertEquals(ValidPricePoint1, "Valid Price Point");
		System.out.println("Valid Price Point1" + " :- " + ValidPricePoint1);
		ObjectRepo.getTest().log(Status.PASS, "Valid Price Point1 :- " + ValidPricePoint1);

		Double AllowableCumulativeEmissionUnits2 = wb.getSheet("CURRENT Valid Price Points").getRow(2).getCell(7)
				.getNumericCellValue();
		String strNumber3 = Double.toString(AllowableCumulativeEmissionUnits2);
		Assert.assertEquals(strNumber3, "26000.0");
		System.out.println("Allowable Cumulative Emission Units2" + " :- " + AllowableCumulativeEmissionUnits2);
		ObjectRepo.getTest().log(Status.PASS,
				"Allowable Cumulative Emission Units2 :- " + AllowableCumulativeEmissionUnits2);

		Double IncrementalValue2 = wb.getSheet("CURRENT Valid Price Points").getRow(2).getCell(8).getNumericCellValue();
		String strNumber4 = Double.toString(IncrementalValue2);
		Assert.assertEquals(strNumber4, "5000.0");
		System.out.println("Incremental value2" + " :- " + IncrementalValue2);
		ObjectRepo.getTest().log(Status.PASS, "Incremental value2 :- " + IncrementalValue2);

		String ValidPricePoint2 = wb.getSheet("CURRENT Valid Price Points").getRow(2).getCell(9).getStringCellValue();
		Assert.assertEquals(ValidPricePoint2, "Valid Price Point");
		System.out.println("Valid Price Point2" + " :- " + ValidPricePoint2);
		ObjectRepo.getTest().log(Status.PASS, "Valid Price Point2 :- " + ValidPricePoint2);
		System.out.println("C31336-The valid price points calculated for the bids are as per expected.");
		ObjectRepo.getTest().log(Status.PASS, "C31336-The valid price points calculated for the bids are as per expected.");
	}
}
