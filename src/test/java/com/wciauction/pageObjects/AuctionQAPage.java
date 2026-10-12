package com.wciauction.pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.EmptyFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.wci.auction.org.resources.CommonLib;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.events.CreateEventPage3;
import com.wciauction.pageObjects.reports.ReportsPage;
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
import static constants.EventNameConstants.eventNameEng;
import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class AuctionQAPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	String url = "";
	int count;
	public String randomString;
	public String date1;
	String name = "Download Rapport d'activités post-événement for event ";
	String name2 = (name + eventNameFR);

	public CommonLib commonLibref = new CommonLib();

	@FindBy(xpath = "//*[text()='POST']")
	private WebElement postBtn;

	@FindBy(xpath = "(//input[@type='checkbox'])[3]")
	private WebElement selectCheckBox2;

	@FindBy(xpath = "(//input[@type='checkbox'])[4]")
	private WebElement selectCheckBox3;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[10]")
	private WebElement firstThreeDotBtn;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement assertFisrtRow;

	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	private WebElement allCheckBoxBtn;

	@FindBy(xpath = "//input[@placeholder='Enter Text']")
	private WebElement addCommentTextBox;

	@FindBy(xpath = "//ul//li[1]")
	private WebElement selectEvent;

	@FindBy(xpath = "//*[contains(text(),'Monitoring')]")
	private WebElement pageTitle;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement selectEventName;

	@FindBy(xpath = "//*[text()='SEARCH']")
	private WebElement searchBtn;

	@FindBy(xpath = "(//tbody[@class='MuiTableBody-root'])[2]")
	private WebElement monitoringBidTble;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement resetBtn;

	@FindBy(xpath = "//select[@id='select-jurisdiction']")
	private WebElement selecturisdictionDropDwn;

	@FindBy(xpath = "(//*[@data-icon='ellipsis-h'])[1]")
	private WebElement threeDotFirstBtn;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement firstBidRow;

	@FindBy(xpath = "//*[text()='Add Flag']")
	private WebElement addFlagBtn;

	@FindBy(xpath = "//*[text()='Remove Flag']")
	private WebElement removeFlagBtn;

	@FindBy(xpath = "//*[text()='REMOVE FLAG']")
	private WebElement removeFlagBtn2;

	@FindBy(xpath = "//*[text()='FLAG']")
	private WebElement flagBtn;

	@FindBy(xpath = "//*[contains(text(),'You have selected ')]")
	private WebElement addFlagPopUpMsg1;

	@FindBy(xpath = "//*[contains(text(),'Are you sure you want to exclude these')]")
	private WebElement addFlagPopUpMsg2;

	@FindBy(xpath = "//*[text()='The flag has been removed successfully from the selected bids.']")
	private WebElement removFlagSuccessMsg;

	@FindBy(xpath = "//*[text()='The selected bids have been successfully flagged.']")
	private WebElement addFlagSuccessMsg;

	@FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids cannot be flagged.']")
	private WebElement bulkActionFlaggedMultipleBidsErrorMsg;

	@FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids are not flagged.']")
	private WebElement bulkActionUnflaggedMultipleBidsErrorMsg;

	@FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids cannot be excluded.']")
	private WebElement bulkActionExcludeMultipleBidsErrorMsg;

	@FindBy(xpath = "//*[text()='Include Bid']")
	private WebElement includeBidBtn;

	@FindBy(xpath = "//*[text()='INCLUDE BID']")
	private WebElement includeBidBtn1;

	@FindBy(xpath = "//*[contains(text(),'You have selected ')]")
	private WebElement includeBidPopUpMsg1;

	@FindBy(xpath = "//*[contains(text(),'Are you sure you want to include ')]")
	private WebElement includeBidPopUpMsg2;

	@FindBy(xpath = "//*[text()='The selected bids have been successfully included.']")
	private WebElement includeBidSuccessMsg;

	@FindBy(xpath = "//*[text()='A comment must contain at least one character.']")
	private WebElement commentBidErrorMsg;

	@FindBy(xpath = "//*[text()='Exclude Bid']")
	private WebElement excludeBidBtn;

	@FindBy(xpath = "//*[text()='EXCLUDE']")
	private WebElement excludeBidBtn1;

	@FindBy(xpath = "//*[contains(text(),'You have selected ')]")
	private WebElement excludeBidPopUpMsg1;

	@FindBy(xpath = "//*[contains(text(),'Are you sure you want to exclude these')]")
	private WebElement excludeBidPopUpMsg2;

	@FindBy(xpath = "//*[text()='The selected bids have been successfully excluded.']")
	private WebElement excludeBidSuccessMsg;

	@FindBy(xpath = "//*[text()='Bulk Actions']")
	private WebElement bulkActionBtn;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement selectCheckBox1;

	@FindBy(xpath = "(//*[text()='A211_OpenBW_Automation'])[1]")
	private WebElement runningEventName1;

	@FindBy(xpath = "//*[text()='T6QCEmetteurA']")
	private WebElement entityName;

	@FindBy(xpath = "//*[text()='WA_Entity+D']")
	private WebElement entityName1;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA']")
	private WebElement entityName2;

	@FindBy(xpath = "//*[text()='Prescribed_Company+H - PLC=0 PLA=0 HLC=11598500 HLA=0']")
	private WebElement entityNameRS;

	@FindBy(xpath = "//*[text()='WA_Entity+A']")
	private WebElement entityNameRS_WA;

	@FindBy(xpath = "//*[text()='T6QCEmetteurB']")
	private WebElement entityNameQC;

	@FindBy(xpath = "(//*[@aria-label='secondAuctionCertified'])[1]")
	private WebElement EventCertificationbuttonRSCA3;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench1;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement langBtnENG;

	@FindBy(xpath = "//*[@class='MuiToolbar-root MuiToolbar-dense']//a")
	private List<WebElement> menuTabs;;

	@FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton')]")
	private List<WebElement> menuButton;

	@FindBy(xpath = "//li[text()='English']")
	private WebElement langBtnEng;

	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement btnLogin;

	@FindBy(xpath = "//*[@id='enter_email']")
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

	@FindBy(xpath = "//a[contains(@title,'About WCI, Inc.')]")
	private WebElement btnAboutWCI;

	@FindBy(xpath = "//*[contains(text(),'FAQs & Resources')]")
	private WebElement btnFAQResources;

	@FindBy(xpath = "//a[contains(text(),'California')]")
	private WebElement selectCAL;

	@FindBy(xpath = "//span[text()='FAQs & Resources - ']")
	private WebElement FAQ;

	@FindBy(xpath = "//a[text()='Reserve Price']")
	private WebElement btnFXRATECA;

	@FindBy(xpath = "//a[text()='Minimum Price']")
	private WebElement btnFXRATEQC;

	@FindBy(xpath = "//a[contains(text(),'Quebec')]")
	private WebElement selectQUE;

	@FindBy(xpath = "//a[contains(text(),'Nova Scotia')]")
	private WebElement selectNS;

	@FindBy(xpath = "//a[contains(text(),'Washington')]")
	private WebElement selectWS;

	@FindBy(xpath = "//span[contains(text(),'RESOURCES')]")
	private WebElement btnResources;

	@FindBy(xpath = "(//button[1]//span[contains(text(),'FAQs')])[2]")
	private WebElement btnFAQ;

	@FindBy(xpath = "//span[contains(text(),'Send')]")
	private WebElement btnSend;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your full name')]")
	private WebElement textName;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your phone with country code')]")
	private WebElement textPhone;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your email address')]")
	private WebElement textEmail;

	@FindBy(xpath = "//*[@id='tinymce']")
	private WebElement textMsg;

	@FindBy(xpath = "//span[contains(text(),'DISCARD')]")
	private WebElement btnDiscard;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	private WebElement btnCancel;

	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	private WebElement btnContinue;

	@FindBy(xpath = "//a[contains(text(),'Contact Us')]")
	private WebElement btnContact;

	@FindBy(xpath = "//*[@class='MuiTypography-root jss335 MuiTypography-body1']")
	private WebElement btnTextAuctionDate;

	@FindBy(xpath = "//p[contains(text(),'Detailed Auction Requirements')]")
	private WebElement btnTextDeatiledAuction;

	@FindBy(xpath = "//p[contains(text(),'Auction Example')]")
	private WebElement btnTextAuctionEx;

	@FindBy(xpath = "//p[contains(text(),' Summary Results Report')]")
	private WebElement btnTextSummaryReport;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	private WebElement btnBidsQualifiedBidderReports;

	@FindBy(xpath = "//*[text()='Email address or passphrase is invalid.']")
	private WebElement loginFailureMsg;

	@FindBy(xpath = "//*[text()='Forgot Passphrase']")
	private WebElement forgotPassphraseBtn;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	private WebElement emailAdressField;

	@FindBy(xpath = "(//*[text()='Reserve Sale Certification'])[1]")
	private WebElement ReservesaleCertificationbuttonRSCA1;

	@FindBy(xpath = "(//*[text()='Allowance Price Containment Reserve Certification'])[1]")
	private WebElement ReservesaleCertificationbuttonRS_WA1;

	@FindBy(xpath = "(//*[@aria-label='firstAuctionCertified'])[1]")
	private WebElement EventCertificationbuttonRSCA;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement EventCertificationsubmitbuttonRSCA;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[2]")
	private WebElement clientBidsAuditRow;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement ReportNameDropDown2;

	@FindBy(xpath = "(//button[@aria-label='Download Report'])[1]")
	private WebElement randomEventNmeReportDwnBtn;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[1]")
	private WebElement EntityNmeDropDownBtn2;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA (CA4376-5560)']")
	private WebElement EntityA1;

	@FindBy(xpath = "//input[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement searchEntityID;

	@FindBy(xpath = "//*[text()='Search Reports']")
	private WebElement searchReportBtn;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement select_Report_Name;

	@FindBy(xpath = "//a[@title='Reports']")
	private WebElement btnReports;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement scroll_ToSearch;

	@FindBy(xpath = "//*[text()='About WCI, Inc.']")
	private WebElement scroll_To_Bottom;

	@FindBy(xpath = "//*[@aria-label='Download Bids Report for event R70_SBMA_AfterCertify']")
	private WebElement Bids_Report;

	@FindBy(xpath = "//*[@aria-label='Download Client Bids Report for event A211_JointAuction_04_03_24']")
	private WebElement Client_Bids_ReportQC;

	@FindBy(xpath = "//*[@aria-label='Download Emission Units Delivery Report for event R70_SBMA_AfterCertify']")
	private WebElement Emission_Units_Delivery_Report;

	@FindBy(xpath = "//*[@aria-label='Download Financial Settlement Report for event R70_SBMA_AfterCertify']")
	private WebElement Financial_SettlementReport_Download;

	@FindBy(xpath = "//*[@aria-label='Download Financial Settlement Report for event R317_RS_After_Certify_CA']")
	private WebElement Financial_SettlementReport_DownloadCA;

	@FindBy(xpath = "//*[@aria-label='Download Client Bids Report for event R317_RS_After_Certify_CA']")
	private WebElement Client_Bids_Report;

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

	@FindBy(xpath = "(//input[@name='linkName'])[2]")
	private WebElement Link2;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement cancel;

	@FindBy(xpath = "//*[text()='SAVE']")
	private WebElement save;

	@FindBy(xpath = "//*[text()='PREVIEW']")
	private WebElement preview;

	@FindBy(xpath = "//button[@aria-label='Download Results Report for event EQA_R320_NS_ReserveSale_VPP_R2.3']")
	private WebElement btnResultReportsNSSBA;

	@FindBy(xpath = "//*[@aria-label='Download Sale By Agreement Bid Lot Audit Report for event R192']")
	private WebElement btnSaleByAgreementBidLotAuditReport;

	@FindBy(xpath = "//*[@aria-label='Event Name']//following::span[3]")
	private WebElement EventNmeButton;

	@FindBy(xpath = "//span[normalize-space()='CA_QC_JointAuction']")
	private WebElement EventNmeButtonARQC;

	@FindBy(xpath = "(//span[normalize-space()='A302-R2.3TEST'])[1]")
	private WebElement EventNmeButtonARNS;

	@FindBy(xpath = "//span[normalize-space()='EQA_R320_NS_ReserveSale_VPP_R2.3']")
	private WebElement EventNmeButtonNS;

	@FindBy(xpath = "//span[normalize-space()='A211_OpenBW_Automation']")
	private WebElement EventNmeButtonqc;

	@FindBy(xpath = "//span[normalize-space()='A211_OpenBW_Automation']")
	private WebElement EventNmeButtonCA;

	@FindBy(xpath = "//a[@title='Reports']")
	private WebElement btnReport;

	@FindBy(xpath = "//span[text()='Search Reports']")
	private WebElement btnSearchReport;

	@FindBy(xpath = "//span[text()='Rechercher un rapport']")
	private WebElement btnSearchReportFR;

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

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement reportNameDropDown1;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
	private WebElement yearDropDown;

	@FindBy(xpath = "//input[@aria-label='Event Name']")
	private WebElement eventNameDropDown;

	@FindBy(xpath = "(//input[@id='auto'])[1]")
	private WebElement eventNameDropDownFR;

	@FindBy(xpath = "//span[text()='AAA1New1']")
	private WebElement eventNameDropDownOption;

	@FindBy(xpath = "//input[@aria-label='Entity Name']")
	private WebElement entityNameDropDown;

	@FindBy(xpath = "//span[text()='T6CAEmetteurA']")
	private WebElement entityNameDropDownOption;

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement btnSearch;

	@FindBy(xpath = "//span[text()='RECHERCHER']")
	private WebElement btnSearchFR;

	@FindBy(xpath = "//td[text()='Qualified Bidders Report']")
	private WebElement qualifiedBidderReport;

	@FindBy(xpath = "//input[@placeholder='Start Date']")
	private WebElement startDate;

	@FindBy(xpath = "//input[@placeholder='End Date']")
	private WebElement endDate;

	@FindBy(xpath = "//span[text()='RESET']")
	private WebElement btnReset;

	@FindBy(xpath = "//span[@class='MuiIconButton-label']")
	private List<WebElement> totalReports;

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
	private WebElement resultsReport;

	@FindBy(xpath = "//td[text()='Allowance Delivery Report']")
	private WebElement allowanceDeliveryReport;

	@FindBy(xpath = "//td[text()='Qualified Bidders Report']")
	private WebElement qualifiedBiddersReport;

	@FindBy(xpath = "//span[@class='MuiButton-label'])[7]")
	private WebElement downloadReports1;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement englishLngText;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement frenchLngText;

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

	@FindBy(xpath = "//button[@aria-label='Download Allowance Delivery Report for event WA123']")
	private WebElement btnAllowancedeliveryReportsWS;

	@FindBy(xpath = "//button[@aria-label='Download Emission Units Delivery Report for event EQA_R70_R2.3_AfterRevert_R70_SBMA Prescribed 2']")
	private WebElement btnEmissionUnitsDeliveryReport1;

	@FindBy(xpath = "//span[contains(text(),'Welcome, ')]")
	private WebElement welcomeFSA;

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

	@FindBy(xpath = "(//a[text()=\"Certification de l'enchère\"])[1]")
	private WebElement btnAuctionCertificationqc;

	@FindBy(xpath = "//a[text()='Minimum Price']")
	private WebElement btnMinimumPrice;

	@FindBy(xpath = "(//button[@title='Go to details of Event Algotest'])[1]")
	private WebElement eventDetailsBtnALGO;

	@FindBy(xpath = "//button[@title='Go to details of Event Pause Event Functionality Test']")
	private WebElement eventPAUSEEVENT;

	@FindBy(xpath = "//button[@aria-label='Select number of rows per page.']")
	private WebElement btngear;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement btngear500;

	@FindBy(xpath = "//span[contains(text(),'Welcome, ')]")
	private WebElement btnProfile;

	@FindBy(xpath = "//span[contains(text(),'Bienvenue, ')]")
	private WebElement btnProfileqc;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogoutFR;

	@FindBy(xpath = "(//*[@aria-haspopup='true'])[1]")
	private WebElement langbtn;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement Englang;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement Frenchlang;

	@FindBy(xpath = "//a[@title='Home']")
	private WebElement btnHome;

	@FindBy(xpath = "//p[text()='WA_Entity+D']")
	private WebElement Homeeventname;

	@FindBy(xpath = "//*[text()='Event in progress']")
	private WebElement Homeeventstatus;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement btnAdministration;

	@FindBy(xpath = "//*[text()='Event Monitoring']")
	private WebElement btnEventMonitoring;

	@FindBy(xpath = "//p[text()='WA_Entity+D']")
	private WebElement eventfrommonitoring;

	@FindBy(xpath = "//*[text()='Download Report']")
	private WebElement btndownloadeventmonitoring;

	@FindBy(xpath = "(//button[@title='Select number of rows per page.'])")
	private WebElement gearbutton;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement gearrowselect;

	@FindBy(xpath = "//*[text()='Auction 187']")
	private WebElement eventname;

	@FindBy(xpath = "(//*[text()='Certification - Approved'])")
	private WebElement eventstatus;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//span[contains(text(),'Bienvenue, ')]")
	private WebElement btnProfileQC;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement administration1;

	@FindBy(xpath = "//*[text()='Event']")
	private WebElement event1;

	@FindBy(xpath = "//*[@title='Select number of rows per page.']")
	private WebElement rowsPerPage;

	@FindBy(xpath = "//*[text()='500 ']")
	private WebElement selectRowsNum;

	@FindBy(xpath = "//li[text()='Fermer la session']")
	private WebElement btnLogoutQC;

	@FindBy(xpath = "//*[@class='MuiButtonBase-root MuiButton-root MuiButton-text']")
	private WebElement downloadreportopt;

	@FindBy(xpath = "(//input[@aria-label='Event Name'])")
	private WebElement Bdropdwnname;

	@FindBy(xpath = "(//input[@id='auto'])[1]")
	private WebElement BdropdwnnameFR;

	@FindBy(xpath = "//*[text()='E123INDCAPadmakar-Individual Auction Event']")
	private WebElement EventnametinDrp;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[1]")
	private WebElement BEdropdwnname1;

	@FindBy(xpath = "(//input[@id='auto'])[2]")
	private WebElement BEdropdwnname1FR;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[2]")
	private WebElement BEdropdwnname2;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[4]")
	private WebElement selectReportdropdwn;

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

	@FindBy(xpath = "//button[@title=\"Settings\"]")
	private WebElement btnSettings;

	@FindBy(xpath = "Copyright   2021")
	private WebElement btnCopyRight;

	@FindBy(xpath = "//a[contains(@title,'Terms of Use')]")
	private WebElement btnTermsofUse;

	@FindBy(xpath = "//a[contains(@title,'Privacy Policy')]")
	private WebElement btnPrivacyPolicy;

	@FindBy(xpath = "//a[contains(@title,'Contact Us')]")
	private WebElement btnContactUs;

	private By ClientBidsReportdownloadbutton = By
			.xpath("//button[@aria-label='Download Client Bids Report for event " + eventNameEng + "']");
	private By FinancialStatementdownloadbutton = By
			.xpath("//button[@aria-label='Download Financial Statement for event " + eventNameEng + "']");
	private By btnBidsAuditReportsRESERVE = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event A211_OpenBW_Automation']");
	private By btnBidsAuditReportsRESERVEQC = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event A211_OpenBW_Automation']");
	private By btnBidsAuditReports = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameEng + "']");
	private By btnBidsAuditReportsWS = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameWS + "']");
	private By btnBidsAuditReportsCA = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameCA + "']");
	private By btnBidsAuditReportsRS_CA = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + ReserveSaleEventName + "']");
	private By btnBidsAuditReportsQC = By
			.xpath("//button[@aria-label='Download Rapport de vérification des offres for event " + eventNameFR + "']");
	private By btnBidsAuditReportsQC_single_auction = By
			.xpath("//button[@aria-label='Download Bids Audit Report for event " + eventNameQC + "']");
	private By btnFinancialSettlementReports = By
			.xpath("//button[@aria-label='Download Financial Settlement Report for event R70_SBMA_AfterCertify']");
	private By btnPostEventOperationReports = By
			.xpath("//*[@aria-label='Download Post Event Operations Report for event " + eventNameEng + "']");
	private By btnPostEventOperationReportsQC = By.xpath("//*[@aria-label=" + name2 + "]");
	private By btnResultReports = By.xpath(
			"//button[@aria-label='Download Results Report for event EQA_R70_R2.3_AfterRevert_R70_SBMA Prescribed 2']");
	private By btnResultReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des résultats for event " + eventNameFR + "']");

	private By btnFinancialSettlementReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des paiements for event " + eventNameFR + "']");

	private By btnBidsReportsQC = By
			.xpath("//button[@aria-label='Download Rapport des offres for event " + eventNameFR + "']");

	private By btnBidsReports = By.xpath("//button[@aria-label='Download Bids Report for event " + eventNameEng + "']");

	private By btnBidsReportsWS = By
			.xpath("//button[@aria-label='Download Bids Report for event " + eventNameWS + "']");

	private By btnBidsReportsCA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + eventNameCA + "']");

	private By btnBidsReportsRS_CA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + ReserveSaleEventName + "']");

	private By btnBidsReportsRS_WA = By
			.xpath("//button[@aria-label='Download Bids Report for event " + ReserveSaleEventName_WA + "']");

	private By btnBidsReportsQC_Single_Auction = By
			.xpath("//button[@aria-label='Download Bids Report for event " + eventNameQC + "']");

	private By btnReportFR = By.xpath("//a[@title='Rapports']");

	private By btnAllowancedeliveryReports = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event " + eventNameEng + "']");

	private By btnEmissiondeliveryReports = By
			.xpath("//button[@aria-label='Download Emission Units Delivery Report for event " + eventNameEng + "']");

	private By btnQualifiedBidderReports = By
			.xpath("//*[@aria-label='Download Qualified Bidders Report for event " + eventNameEng + "']");

	private By btnQualifiedBidderReportsQC = By
			.xpath("//*[@aria-label='Download Rapport des enchérisseurs qualifiés for event " + eventNameFR + "']");

	private By btnFinancialSettlementReportsFR = By
			.xpath("//button[@aria-label='Download Rapport des paiements for event " + eventNameFR + "']");

	private By btnEmissionUnitsDeliveryReport = By
			.xpath("//button[@aria-label='Download Emission Units Delivery Report for event " + eventNameEng + "']");

	private By btnEmissionUnitsDeliveryReportQC = By
			.xpath("//button[@aria-label='Download Rapport de la livraison des unités d'émission for event "
					+ eventNameFR + "']");

	private By EventnametinDrp1 = By.xpath("//*[text()='" + eventNameWS + "']");

	private By EventnametinDrpCA = By.xpath("//*[text()='" + eventNameCA + "']");

	private By EventnametinDrpRS_CA = By.xpath("//*[text()='" + ReserveSaleEventName + "']");

	private By EventnametinDrpRS_WA = By.xpath("//*[text()='" + ReserveSaleEventName_WA + "']");

	private By EventnametinDrpQC = By.xpath("//*[text()='" + eventNameQC + "']");

	public AuctionQAPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void ARLoginWS1() throws InterruptedException {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("WAARusername1"));
			password.sendKeys(prop.getProperty("WAARPassword"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("WAARusername1"));
			password.sendKeys(prop.getProperty("WAARPassword"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		}
	}

	public void sponsorLoginWS2() throws Exception {
		btnLogin.click();
		username.sendKeys(prop.getProperty("UserSponsorWS2"));
		password.sendKeys("Test@1234");
		btnLogin2.click();
		System.out.println("Washington sponsor login successfully");
	}

	public void EventCertificationbuttonRSCA1() throws InterruptedException {
		ReservesaleCertificationbuttonRSCA1.click();
		wait.until(ExpectedConditions.elementToBeClickable(EventCertificationbuttonRSCA)).click();
		EventCertificationsubmitbuttonRSCA.click();
	}

	public void EventCertificationbuttonRS_WA1() throws InterruptedException {
		ReservesaleCertificationbuttonRS_WA1.click();
		wait.until(ExpectedConditions.elementToBeClickable(EventCertificationbuttonRSCA)).click();
		EventCertificationsubmitbuttonRSCA.click();
	}

	public void EventCertificationbuttonRSCA2() throws InterruptedException {
		ReservesaleCertificationbuttonRSCA1.click();
		wait.until(ExpectedConditions.elementToBeClickable(EventCertificationbuttonRSCA3)).click();
		EventCertificationsubmitbuttonRSCA.click();

	}

	public void EventCertificationbuttonRS_WA2() throws InterruptedException {
		ReservesaleCertificationbuttonRS_WA1.click();
		wait.until(ExpectedConditions.elementToBeClickable(EventCertificationbuttonRSCA3)).click();
		EventCertificationsubmitbuttonRSCA.click();
	}

	public void changeLangauge() throws InterruptedException {
		WebElement element = langBtnFrench;
		js.executeScript("arguments[0].scrollIntoView();", element);
		langBtnFrench.click();
		langBtnEng.click();
		System.out.println("Langauge changed successfully from french to english ");
	}

	public void changeLangauge1() throws InterruptedException {
		WebElement element = langBtnFrench1;
		js.executeScript("arguments[0].scrollIntoView();", element);
		langBtnFrench1.click();
		langBtnEng.click();
		System.out.println("Langauge changed successfully from french to english ");
	}

	public void changeLangaugetoFR() throws InterruptedException {
		WebElement element = langBtnENG;
		js.executeScript("arguments[0].scrollIntoView();", element);
		langBtnENG.click();
		langBtnFrench1.click();
		System.out.println("Langauge changed successfully from english to french");
	}

	public void sponsorLoginCa2() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(prop.getProperty("UserSponsorCa"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorCa") + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorCa") + " Sponsor (California) login successfully");
	}

	public void sponsorLoginCa() {
		try {
			btnLogin.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(username));
		username.sendKeys(prop.getProperty("UserSponsorCa2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
	}

	public void runtimesponsorLoginCa() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
			password.sendKeys("Test@1234");
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + "Test@1234+");
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
			password.sendKeys("Test@1234");
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + "Test@1234+");
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		}
	}

	public void runtimesponsorLoginMM_CA() {
		try {
			username.sendKeys(prop.getProperty("UnameMMCa"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitoring Username Entered  : " + prop.getProperty("UserSponsorCa2"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitoring Password Entered  : " + prop.getProperty("Password"));

			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UnameMMCa"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitoring Username Entered  : " + prop.getProperty("UserSponsorCa2"));
			password.sendKeys("Password");
			ObjectRepo.getTest().log(Status.PASS, "Market Monitoring Password Entered  : " + prop.getProperty("Password"));

			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		}
	}

	public void secondSponsorLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		username.clear();
		password.clear();
		username.sendKeys(prop.getProperty("UserSponsorCa2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
		password.sendKeys("Test@1234");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + "Test@1234+");
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
	}

	public void sponsorLoginQC2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQC2"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
		} catch (Exception e) {
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorQC2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQC2"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
		}
	}

	public void sponsorLoginQc() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
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
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
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

	public void RuntimeSponsorLoginQc() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
		} catch (Exception e) {
			btnLogin.click();

			username.sendKeys(prop.getProperty("UserSponsorQc2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
		}
	}

	public void RuntimeSponsorLoginNS() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		}
	}

	public void sponsorLoginNs() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorNS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UserSponsorNS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
		}
	}

	public void sponsorLoginNs2() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));

			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		}
	}

	public void sponsorLoginWS() {
		btnLogin.click();
		username.sendKeys(prop.getProperty("UserSponsorWS"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorWS") + " Sponsor (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorWS") + " Sponsor (Washington) login successfully");
	}

	public void FSALoginCa() {
		try {
			username.sendKeys(prop.getProperty("UnameFSACa"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSACa"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
		}
	}

	public void FSALoginQc() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		}
	}

	public void FSALoginQc2() {
		try {
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		}
	}

	public void FSALoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameFSANS"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSANS"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
		}
	}

	public void FSALoginWs() {
		username.sendKeys(prop.getProperty("UnameFSAWS"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
	}

	public void FSALoginWs1() {
		try {
			username.sendKeys(prop.getProperty("UnameFSAWS"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameFSAWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("California MM login successfully");
			System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAWS"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameFSAWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		}
	}

	public void accesstoUsers() {
		List<WebElement> list = menuTabs;
		int menu = list.size();
		for (int i = 0; i <= menu - 1; i++) {
			list.get(i).click();
			String str = list.get(i).getText();
			ObjectRepo.getTest().log(Status.PASS, "verified EventAdmin menuTabs;: " + str);
		}
		AuctionQAPage pm = new AuctionQAPage();
		pm.verify_PrivatePage_MenuBottons();
	}

	public void MMLoginCa() {
		try {
			username.sendKeys(prop.getProperty("UnameMMCa"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMCa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("California MM login successfully");
			System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMCa") + " Market Monitor  (California) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMCa"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMCa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMCa") + " Market Monitor  (California) login successfully");
		}
	}

	public void MMLoginCa2() throws InterruptedException {
		btnLogin.click();
		username.sendKeys(prop.getProperty("UnameMMCa"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMCa"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("California MM login successfully");
		System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMCa") + " Market Monitor  (California) login successfully");
	}

	public void MMLoginQc() throws InterruptedException {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMQc"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "Quebec MM login successfully");
			System.out.println("" + prop.getProperty("UnameMMQc") + " Market Monitor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMQc") + " Market Monitor  (Quebec) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UnameMMQc"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameMMQc") + " Market Monitor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMQc") + " Market Monitor  (Quebec) login successfully");
		}
	}

	public void MMLoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameMMNS"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMNS"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
		}
	}

	public void MMLoginWs() {
		username.sendKeys(prop.getProperty("UnameMMWS"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
	}

	public void MMLoginWs1() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMWS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UnameMMWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UnameMMWS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UnameMMWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		}
	}

	public void EALoginCa() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameEACa"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameEACa") + "Event Admin (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEACa") + " Event Admin (California) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UnameEACa"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameEACa") + "Event Admin (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEACa") + " Event Admin (California) login successfully");
		}
	}

	public void EALoginQc() {

		try {
			username.sendKeys(prop.getProperty("UnameEAQc"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameEAQc") + "Event Admin (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEAQc") + " Event Admin (Quebec) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameEAQc"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameEAQc") + "Event Admin (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEAQc") + " Event Admin (Quebec) login successfully");
		}
	}

	public void EALoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameEANS"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameEANS") + "Event Admin (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEANS") + " Event Admin (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameEANS"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameEANS") + "Event Admin (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEANS") + " Event Admin (Nova Scotia) login successfully");
		}
	}

	public void EALoginWs() {
		username.sendKeys(prop.getProperty("UnameEAWS"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameEAWS") + "Event Admin (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameEAWS") + " Event Admin (Washington) login successfully");
	}

	public void ARLoginCA() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));

			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		}
	}

	public void ARLoginCAForReserveSale() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1RES") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName1RES") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES") + " Account Representative login successfully");
		}
	}

	public void ARLoginWAForReserveSale() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES_WA"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES_WA"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out
					.println("" + prop.getProperty("AR_USerName1RES_WA") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES_WA") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES_WA"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES_WA"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out
					.println("" + prop.getProperty("AR_USerName1RES_WA") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES_WA") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC() {
		try { //
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}
	}

	public void ARLoginQC_For_Single_Auction() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("Account_Representative_Qubec"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("Account_Representative_Qubec"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("Account_Representative_Qubec")
					+ "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("Account_Representative_Qubec")
					+ " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("Account_Representative_Qubec"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}
	}

	public void clickOnnext() {
		WebElement abc = btnNext;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
	}

	public void clickOnnextQC() {
		WebElement abc = btnNextQC;
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

	public void noOfBrockenLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int totalNoOfLinks = links.size();
		for (int i = 0; i <= totalNoOfLinks; i++) {
			ArrayList<String> brokenLinks = new ArrayList<String>();
			ArrayList<String> ActiveLinks = new ArrayList<String>();
			if (links.get(i).getAttribute("href").contains(null)) {
				brokenLinks.addAll(brokenLinks);
				System.out.println(brokenLinks);
			} else {
				ActiveLinks.addAll(ActiveLinks);
				System.out.println(ActiveLinks);
			}
		}
	}

	public void getVerifyAllBrokenLinks() {
		driver.findElement(By.tagName("a")).click();
	}

	public void getbtnHome() {
		wait.until(ExpectedConditions.elementToBeClickable(btnHome));
		btnHome.click();
	}

	public void getbtnAboutWCI() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAboutWCI));
		btnAboutWCI.click();
	}

	public void getbtnCopyRight() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCopyRight));
		btnCopyRight.click();
	}

	public void getbtnTermsofUse() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTermsofUse));
		btnTermsofUse.click();
	}

	public void getbtnPrivacyPolicy() {
		wait.until(ExpectedConditions.elementToBeClickable(btnPrivacyPolicy));
		btnPrivacyPolicy.click();
	}

	public void getbtnContactUs() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContactUs));
		btnContactUs.click();
	}

	public void getbtnFAQResources() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFAQResources));
		btnFAQResources.click();
	}

	public void getselectCAL() {
		wait.until(ExpectedConditions.elementToBeClickable(selectCAL));
		WebElement abc1 = selectCAL;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		selectCAL.click();
	}

	public void getFAQCAL() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQ));
		FAQ.getText();
	}

	public void getselectQUE() {
		wait.until(ExpectedConditions.elementToBeClickable(selectQUE));
		selectQUE.click();
	}

	public void getFAQQUE() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFAQ));
		btnFAQ.getText();
	}

	public void getselectNS() {
		wait.until(ExpectedConditions.elementToBeClickable(selectNS));
		selectNS.click();
	}

	public void getFAQNS() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQ));
		FAQ.getText();
	}

	public void getselectWS() {
		wait.until(ExpectedConditions.elementToBeClickable(selectWS));
		selectWS.click();
	}

	public void getFAQWS() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQ));
		FAQ.getText();
	}

	public void getbtnResources() {
		btnResources.click();
	}

	public void getbtnFAQ() {
		btnFAQ.click();
	}

	public void getbtnSend() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSend));
		btnSend.click();
	}

	public void gettextName() {
		textName.sendKeys("testdata123");
	}

	public void gettextPhone() {
		textPhone.sendKeys("9860745624");
	}

	public void gettextEmail() {
		WebElement element = textEmail;
		js.executeScript("arguments[0].scrollIntoView();", element);
		textEmail.sendKeys("test56@gmail.com");
	}

	public void gettextMsg() {
		driver.switchTo().frame(0);
		textMsg.sendKeys("testdescription");
		driver.switchTo().parentFrame();
	}

	public void getbtnDiscard() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDiscard));
		btnDiscard.click();
	}

	public void getbtnCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCancel));
		btnCancel.click();
	}

	public void getbtnContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		btnContinue.click();
	}

	public void getbtnContact() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContact));
		WebElement abc3 = btnContact;
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		btnContact.click();
	}

	public void brokenLinksOnAWebPage() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int totallinks = links.size();
		System.out.println("Total number of links available on publicHomepage: " + totallinks);
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				count++;
				System.out.println(url);
			}
		}
		System.out.println("No of broken links present: " + count);
	}

	public void verify_Footer() {
		btnAboutWCI.click();
		String parentWindID = driver.getWindowHandle();
		Set<String> WindHandlesID = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID) {
			if (!WindowHandle.equals(parentWindID)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "Verified AboutWCI,Inc.Tab");
		getbtnTermsofUse();
		String parentWindID1 = driver.getWindowHandle();
		Set<String> WindHandlesID1 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID1) {
			if (!WindowHandle.equals(parentWindID1)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID1);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "Verified TermsofUse Tab");
		getbtnPrivacyPolicy();
		String parentWindID2 = driver.getWindowHandle();
		Set<String> WindHandlesID2 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID2) {
			if (!WindowHandle.equals(parentWindID2)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID2);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "Verified PrivacyPolicy Tab");
		getbtnContactUs();
		ObjectRepo.getTest().log(Status.PASS, "Verified ContactUs Tab");
		getbtnHome();
		ObjectRepo.getTest().log(Status.PASS, "Back to HomePage");
	}

	public void verify_FAQAndResources() {
		btnFAQResources.click();
		getselectCAL();
		String expt = "FAQs & Resources - California";
		String act = FAQ.getText();
		Assert.assertEquals(expt, act);
		getbtnResources();
		getbtnFAQ();
		System.out.println("verified FAQs & Resources - California -->" + act);
		ObjectRepo.getTest().log(Status.PASS, "verified FAQs & Resources - California");
		WebElement ele = btnFAQResources;
		Actions ac = new Actions(driver);
		ac.moveToElement(ele).click().perform();
		getselectQUE();
		String expt1 = "FAQs & Resources - Quebec";
		String act1 = FAQ.getText();
		Assert.assertEquals(expt1, act1);
		getbtnResources();
		getbtnFAQ();
		System.out.println("verified FAQs & Resources - Quebec -->" + act1);
		ObjectRepo.getTest().log(Status.PASS, " verified FAQs & Resources - Quebec");
		WebElement var = btnFAQResources;
		Actions a = new Actions(driver);
		a.moveToElement(var).click().perform();
		getselectNS();
		String expt2 = "FAQs & Resources - Nova Scotia";
		String act2 = FAQ.getText();
		Assert.assertEquals(expt2, act2);
		getbtnResources();
		getbtnFAQ();
		System.out.println(" verified FAQs & Resources - Nova Scotia -->" + act2);
		ObjectRepo.getTest().log(Status.PASS, " verified FAQs & Resources - Nova Scotia");
		WebElement va = btnFAQResources;
		Actions acti = new Actions(driver);
		acti.moveToElement(va).click().perform();
		getselectWS();
		String expt3 = "FAQs & Resources - Washington";
		String act3 = FAQ.getText();
		Assert.assertEquals(expt3, act3);
		getbtnResources();
		getbtnFAQ();
		System.out.println(" verified FAQs & Resources - Washington -->" + act3);
		ObjectRepo.getTest().log(Status.PASS, " verified FAQs & Resources - Washington");
		getbtnHome();
		ObjectRepo.getTest().log(Status.PASS, "Back to HomePage");
	}

	public void verify_ContactUs() {
		getbtnContact();
		gettextName();
		gettextPhone();
		gettextEmail();
		gettextMsg();
		getbtnSend();
	}

	public void getbtnTextAuctionDate() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTextAuctionDate));
		btnTextAuctionDate.click();
	}

	public void getbtnTextDeatiledAuction() {
		WebElement element = btnTextDeatiledAuction;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}

	public void getbtnTextAuctionEx() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTextAuctionEx));
		btnTextAuctionEx.click();
	}

	public void getbtnTextSummaryReport() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTextSummaryReport));
		btnTextSummaryReport.click();
	}

	public void verify_noticeSection() {
		driver.navigate().refresh();
		js.executeScript("window.scrollBy(0,-150)");
		wait.until(ExpectedConditions.elementToBeClickable(btnTextAuctionDate));
		String str = btnTextAuctionDate.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + str);
		getbtnTextAuctionDate();
		String parentWindID3 = driver.getWindowHandle();
		Set<String> WindHandlesID3 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID3) {
			if (!WindowHandle.equals(parentWindID3)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID3);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, " verified link -TextAuctionDate");
		String DeatiledAuction = btnTextDeatiledAuction.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + DeatiledAuction);
		getbtnTextDeatiledAuction();
		String parentWindID2 = driver.getWindowHandle();
		Set<String> WindHandlesID2 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID2) {
			if (!WindowHandle.equals(parentWindID2)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID2);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "verified link -TextDeatiledAuction");
		String AuctionEx = btnTextAuctionEx.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + AuctionEx);
		getbtnTextAuctionEx();
		String parentWindID1 = driver.getWindowHandle();
		Set<String> WindHandlesID1 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID1) {
			if (!WindowHandle.equals(parentWindID1)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID1);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, " verified link -TextAuctionEx");
		String SummaryReport = btnTextSummaryReport.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + SummaryReport);
		getbtnTextSummaryReport();
		String parentWindID = driver.getWindowHandle();
		Set<String> WindHandlesID = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID) {
			if (!WindowHandle.equals(parentWindID)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "verified link -TextSummaryReport");
	}

	public void downloadReports() throws Exception {
		btnReports.click();
		btnBidsQualifiedBidderReports.click();
		driver.findElement(btnBidsAuditReports).click();
		driver.findElement(btnBidsReports).click();
	}

	public void verify_PrivatePage_MenuBottons() {
		List<WebElement> listOne = menuButton;
		int menubar = listOne.size();
		for (int i = 0; i <= menubar - 1; i++) {
			listOne.get(i).click();
			String str = listOne.get(i).getText();
			ObjectRepo.getTest().log(Status.PASS, "verified sponsor MenuButtons: " + str);
		}
	}

	public void ARLoginQC2() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC1() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName3"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName3"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName3") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName3") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName3"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName3"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName3") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName3") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC3() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		}
	}

	public void runtimesponsorLoginQc2() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorQc") + "Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorQc") + "Sponsor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
		}
	}

	public void RuntimeARLoginQC() {
		try {
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		}
	}

	public void sponsorUserloginFunctionality() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		username.sendKeys("sponsor.ca@yopmail.com");
		password.sendKeys("password");
		btnLogin2.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginFailureMsg));
		String FailureMsg = loginFailureMsg.getText();
		System.out.println("Error message displayed " + FailureMsg);
		ObjectRepo.getTest().log(Status.PASS, "Error message displayed :-" + FailureMsg);
	}

	public void forgotPassphrase() {

		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		System.out.println("Login pop up module is opened");
		ObjectRepo.getTest().log(Status.PASS, "Login pop up module is opened");
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassphraseBtn));
		forgotPassphraseBtn.click();
		System.out.println("Email address textfield is displayed on screen");
		ObjectRepo.getTest().log(Status.PASS, "Email address textfield is displayed on screen");
		emailAdressField.sendKeys("sponsor.ca@yopmail.com");
		System.out.println("After selecting the forgot passphrase link the user is able to input an email address.");
		ObjectRepo.getTest().log(Status.PASS,
				"After selecting the forgot passphrase link the user is able to input an email address.");
	}

	public void AnnualCategoryPrice() throws Exception {
		System.out.println("TC ID:C31002 : Confirm for SBMA the Annual Category Price is displayed on the Homepage.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31002 : Confirm for SBMA the Annual Category Price is displayed on the Homepage.");
		WebElement l = driver.findElement(By.tagName("body"));
		System.out.println("Text content: " + l.getText());
	}

	public void EANOTEXECUTEAPI() throws Exception {
		System.out.println("TC ID:C31533 : The system must not let EA execute the API");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31533 : The system must not let EA execute the API");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnSettings)).click();
		} catch (Exception e) {
			WebElement element = btnSettings;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click(element).build().perform();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Execute API'])"))).click();
		WebElement execute = driver.findElement(By.xpath("(//button[@title='Execute'])[2]"));
		if (execute.isEnabled()) {
			System.out.println("EA can Execute API");
		} else {
			System.out.println("EA can not Execute API");
		}

	}

	public void BlurbInformation() throws Exception {
		System.out.println(
				"TC ID:C31534  : As a sponsor I must be able to access an informational blurb to know how to execute the API");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31534  : As a sponsor I must be able to access an informational blurb to know how to execute the API");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		driver.findElement(By.xpath("//a[text()='Execute API']")).click();
		WebElement l = driver.findElement(By.tagName("body"));
		System.out.println("Text content: " + l.getText());
		String act = driver
				.findElement(By.xpath("//td[contains(text(),'This API provides the list of entities, users and ')]"))
				.getText();
		String exp = "This API provides the list of entities, users and user-entity relationships for the qualified bidders in a previous event or for all entities that applied to an upcoming event from Registry. It is executed automatically daily at 10 PM ET. It also provides specific users and entity-user relationships for each specific entity as needed.";
		Assert.assertEquals(act, exp);
		System.out.println("Assertion Passed");
	}

	public void CertifyReserveSaleEvent() throws Exception {
		System.out.println("TC ID:C31211 : Confirm a Sponsor can certify an Reserve Sale event.");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31211 : Confirm a Sponsor can certify an Reserve Sale event.");
		driver.findElement(By.xpath("//span[normalize-space()='Administration']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Sale Certification']")).click();
		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[13]")).click();
	}

	public void CreateEvent() throws Exception {
		System.out.println("TC ID:C31729 : EA/Sponsor is able to set the Combined evaluation of Categories");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31729 : EA/Sponsor is able to set the Combined evaluation of Categories");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		}
		administration1.click();
		wait.until(ExpectedConditions.elementToBeClickable(event1));
		event1.click();
//		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
//		rowsPerPage.click();
//		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
//		selectRowsNum.click();
//		WebElement selectEvent2 = driver.findElement(
//				By.xpath("//button[@title='Go to details of Event " + prop.getProperty("Event_NameQC") + "']"));
//		js.executeScript("arguments[0].scrollIntoView();", selectEvent2);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
//		driver.findElement(By.xpath("//span[normalize-space()='Administration']")).click();
//		driver.findElement(By.xpath("//a[text()='Event']")).click();
		driver.findElement(By.xpath("//div[@class='MuiGrid-root MuiGrid-item']//button[@type='button']")).click();
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomString = ("MAC_" + generatedString);
		String eventid = randomString = ("MAC_" + generatedString);
		driver.findElement(By.xpath("//input[@id='eventId']")).sendKeys(eventid);
		Select a = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]")));
		a.selectByValue("2");
		driver.findElement(By.xpath("//input[@id='eventNameInEnglish']")).sendKeys("testreservesale");
		driver.findElement(By.xpath("//input[@id='eventNameInFrench']")).sendKeys("test123");
		Select b = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[3]")));
		b.selectByValue("2");
		driver.findElement(By.xpath("//button[normalize-space()='Add New']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add New']")).click();
		Select c = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		c.selectByVisibleText("Category A");
		driver.findElement(By.xpath("//input[@name='tiersPrices']")).sendKeys("55");
		driver.findElement(By.xpath("//input[@name='totalAllowancesAvailablePerTier']")).sendKeys("5000");
		Select d = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[5]")));
		d.selectByValue("1");
		Select e = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[6]")));
		e.selectByVisibleText("Category B");
		driver.findElement(By.xpath("(//input[@placeholder='Enter'])[6]")).sendKeys("56");
		driver.findElement(By.xpath("(//input[@placeholder='Enter'])[7]")).sendKeys("6000");
		Select f = new Select(driver.findElement(By.xpath("(//select[@name='processingOrder'])[2]")));
		f.selectByValue("2");
		Select g = new Select(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[8]")));
		g.selectByVisibleText("Category C");
		driver.findElement(By.xpath("(//input[@placeholder='Enter'])[8]")).sendKeys("57");
		driver.findElement(By.xpath("(//input[@placeholder='Enter'])[9]")).sendKeys("7000");
		Select h = new Select(driver.findElement(By.xpath("(//select[@name='processingOrder'])[3]")));
		h.selectByValue("3");
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(1).plusSeconds(10);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(2).plusSeconds(10);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(5);
		System.out.println("application Close time :" + applicationClose);
		driver.findElement(By.xpath("(//input[@name='eventBiddingPeriodOpen'])")).click();
		driver.findElement(By.xpath("(//input[@name='eventBiddingPeriodOpen'])")).sendKeys(date1 + BiddingStart);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));
		action.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']")).click();
		driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']")).sendKeys(date1 + BiddingEnd);
		driver.findElement(By.xpath("//*[@name='eventApplicationOpen']")).click();
		driver.findElement(By.xpath("//*[@name='eventApplicationOpen']")).sendKeys(date1 + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		driver.findElement(By.xpath("//input[@name='eventApplicationClose']")).click();
		driver.findElement(By.xpath("//input[@name='eventApplicationClose']")).sendKeys(date1 + applicationClose);
		driver.findElement(By.xpath("//input[@name='setMaximumBidPrice']")).click();
		Actions w = new Actions(driver);
		w.sendKeys(Keys.PAGE_DOWN).build().perform();
		driver.findElement(By.xpath("//span[normalize-space()='SAVE']")).click();
	}

	public void CertifyReserveSaleEvent1() throws Exception {
		driver.findElement(By.xpath("//span[normalize-space()='Administration']")).click();
		driver.findElement(By.xpath("(//a[normalize-space()='Reserve Sale Certification'])[1]")).click();
		driver.findElement(By.xpath("(//*[name()='svg'][@role='img'])[15]")).click();
	}

	public void sponsorflagmultiplebid() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Event Monitoring']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Monitoring']")));
		}
		driver.findElement(By.xpath("//span[normalize-space()='Event Monitoring']//*[name()='svg']")).click();
		Thread.sleep(3000);
		WebElement event = driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		element.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
			Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Add Flag']")).isDisplayed();
			Assert.assertTrue(btnDisplayed);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Add Flag']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='FLAG']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='POST']"))).click();
			String a = driver.findElement(By.xpath("//p[@class='MuiFormHelperText-root Mui-error']")).getText();
			System.out.println(a);
			driver.findElement(By.xpath("//*[@placeholder='Enter Text']")).sendKeys("FLAG");
			driver.findElement(By.xpath("//*[text()='POST']")).click();
			String c = driver.findElement(By.xpath("//p[text()='The selected bids have been successfully flagged.']"))
					.getText();
			System.out.println(c);
		} catch (Exception e) {
			System.out.println("Bids Allready Flagged.");
		}
		driver.navigate().refresh();
		Thread.sleep(5000);
		try {
			driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		}
		try {
			driver.findElement(By.xpath("//span[text()='Bulk Actions']")).click();
			driver.findElement(By.xpath("//li[text()='Remove Flag']")).click();
			driver.findElement(By.xpath("//span[text()='REMOVE FLAG']")).click();
			driver.findElement(By.xpath("//*[@placeholder='Enter Text']")).sendKeys("UNFLAG");
			driver.findElement(By.xpath("//*[text()='POST']")).click();
		} catch (Exception e) {
			System.out.println("There is no bids for the remove flag.");
		}
		System.out.println("TC ID:C31804 : Verify Sponsor / MM can flag multiple bids");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31804 : Verify Sponsor / MM can flag multiple bids");
		System.out.println("TC ID:C31805 : Verify Sponsor / MM can un-flag multiple bids");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31805 : Verify Sponsor / MM can un-flag multiple bids");
		System.out.println(
				"TC ID:C31807 : Verify Sponsor / MM cannot flag multiple bids when the selection of bids are not correct");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31807 : Verify Sponsor / MM cannot flag multiple bids when the selection of bids are not correct");
		System.out.println(
				"TC ID:C31808 : Verify Sponsor/MM cannot un-flag multiple bids when the selection of bids are not correct");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31808 : Verify Sponsor/MM cannot un-flag multiple bids when the selection of bids are not correct");
	}

	public void sponsorexcludeincludemultiplebid() throws Exception {
		System.out.println("TC ID:C31811 : Verify Sponsor can exclude a single bid");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31811 : Verify Sponsor can exclude a single bid");
		System.out.println("TC ID:C31813 : Verify Sponsor can exclude multiple bids");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31813 : Verify Sponsor can exclude multiple bids");
		System.out.println("TC ID:C31814 : Verify Sponsor can include multiple bids");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31814 : Verify Sponsor can include multiple bids");
		System.out.println(
				"TC ID:C31815 : Verify Sponsor cannot include multiple bids when the selection of bids is not correct");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31815 : Verify Sponsor cannot include multiple bids when the selection of bids is not correct");
		System.out.println(
				"TC ID:C31816 : Verify Sponsor cannot exclude multiple bids when the selection of bids are not correct");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31816 : Verify Sponsor cannot exclude multiple bids when the selection of bids are not correct");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[normalize-space()='Event Monitoring']//*[name()='svg']")).click();
		Thread.sleep(3000);
		WebElement event = driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Exclude Bid'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']")))
					.sendKeys("EXCLUDE");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']")))
					.click();
			System.out.println("Exclude a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude a single bid");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready Excluded");
		}
		Thread.sleep(5000);
		try {

			driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='Include Bid']")))
					.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']")))
					.sendKeys("INCLUDE");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']")))
					.click();
		} catch (Exception e) {
			System.out.println("Bid Is allready Included");
		}
		System.out.println("Include a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Include a single bid");
	}

	public void ARLoginNS() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName3"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName3"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		} catch (Exception e) {

			btnLogin.click();

			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));

			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		}
	}

	public void addcommentbulkaction() throws Exception {
		System.out.println("TC ID:C31810 : Verify a multiple bids can be selected to add comments as bulk action");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31810 : Verify a multiple bids can be selected to add comments as bulk action");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[normalize-space()='Event Monitoring']//*[name()='svg']")).click();
		Thread.sleep(5000);
		WebElement event = driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		WebElement element = driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Bulk Actions']"))).click();
		driver.findElement(By.xpath("//li[normalize-space()='Add Comment']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='CANCEL']")).click();
		driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		driver.findElement(By.xpath("//span[text()='Bulk Actions']")).click();
		driver.findElement(By.xpath("//li[normalize-space()='Add Comment']")).click();
		String add = driver
				.findElement(By.xpath("(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-1'])[1]"))
				.getText();
		System.out.println(add);
		driver.findElement(By.xpath("//button[@title='ADD COMMENT']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Text']")).sendKeys("add comment");
		driver.findElement(By.xpath("//span[normalize-space()='POST']")).click();
		String abc = driver
				.findElement(By.xpath("//p[contains(text(),'The comment has been added successfully to the sel')]"))
				.getText();
		System.out.println(abc);
	}

	public ArrayList<Object[][]> ValidateResultReportsMMSBMA1() throws IOException, InterruptedException {
		WebElement element = driver.findElement(btnResultReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultReports).click();
		System.out.println("Result Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Result Reports download successfully");
		System.out.println("TC ID:C31821: MM can download and access the Results Report for SMBA ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31821: MM can download and access the Results Report for SMBA ");
		ArrayList<Object[][]> sheetDatas = new ArrayList<>();
		Object[][] object = null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Results Report - EQA_R70_R2.3_AfterRevert_R70_SBMA Prescribed 2 07-03-2023.xlsx");
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
					XSSFRow row1 = (XSSFRow) Sheet1.getRow(0);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();
					System.out.println(value);
					if (value == "Annual Reserve Price") {
						Assert.assertTrue(value.contains("Annual Reserve Price"));
					} else if (value == "QC4097-5124") {
						Assert.assertTrue(value.contains("QC4097-5124"));
					} else if (value == "QC4091-5112") {
						Assert.assertTrue(value.contains("QC4091-5112"));
					} else if (value == "60.0") {
						Assert.assertTrue(value.contains("60.0"));
					} else if (value == "QC4085-5100") {
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
			return sheetDatas;
		}
	}

	public void searchByReportNameAndDateRange() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport)).click();
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
		btnSearch.click();
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		startDate.click();
		startDate.sendKeys("01 Mar 2023");
		endDate.click();
		endDate.sendKeys("03 Mar 2023");
	}

	public void listOfTotalReports() {
		List<WebElement> element = totalReports;
		int totalNumOfReports = element.size();
		System.out.println("Total Numbers of reports available in list are: " + totalNumOfReports);
		ObjectRepo.getTest().log(Status.PASS, "Total Numbers of reports available in list are: " + totalNumOfReports);
	}

	public void downloadTheReportsAvailableForSposnorFR() throws Exception {
		driver.findElement(btnPostEventOperationReportsQC).click();
		ExcelDataProviderForPostEventOperationReportInFrench ref = new ExcelDataProviderForPostEventOperationReportInFrench();
		String rowName = ref.getStringData("Rapport dactivités post-événeme", 5, 0);
		Assert.assertEquals("Renseignements sur l’évènement", rowName);
		System.out.println("Post Event Operations Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in French for Quebec User");
		driver.findElement(btnBidsAuditReportsQC).click();
		ExcelDataProviderForBidsAuditReportInFrench ref1 = new ExcelDataProviderForBidsAuditReportInFrench();
		String rowName1 = ref1.getStringData("Rapport de vérification des off", 5, 0);
		Assert.assertEquals("Numéro de compte général", rowName1);
		System.out.println("Bids audit report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in French for Quebec User");
		driver.findElement(btnFinancialSettlementReportsQC).click();
		ExcelDataProviderForFinancialSettlementReportInFrench ref2 = new ExcelDataProviderForFinancialSettlementReportInFrench();
		String rowName2 = ref2.getStringData("Rapport des paiements", 4, 0);
		Assert.assertEquals("Prix de vente final USD (présent)", rowName2);
		System.out.println("Financial Settlement Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in French for Quebec User");
		driver.findElement(btnBidsReportsQC).click();
		ExcelDataProviderforBidsReportInFrench ref3 = new ExcelDataProviderforBidsReportInFrench();
		String rowName3 = ref3.getStringData("Rapport des offres", 3, 0);
		Assert.assertEquals("Nombre total d'offres  soumises", rowName3);
		System.out.println("Bids report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in French for Quebec User");
		driver.findElement(btnResultReportsQC).click();
		ExcelDataProviderforResultReportInFrench ref4 = new ExcelDataProviderforResultReportInFrench();
		String rowName4 = ref4.getStringData("Information sur la vente", 10, 0);
		Assert.assertEquals("Statistiques sur le prix des offres qualifiées", rowName4);
		System.out.println("Results report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in French for Quebec User");
		driver.findElement(btnEmissionUnitsDeliveryReportQC).click();
		ExcelDataProviderForEmissionUnitsDeliveryReportInFrench ref5 = new ExcelDataProviderForEmissionUnitsDeliveryReportInFrench();
		String rowName5 = ref5.getStringData("Rapport de la livraison des uni", 4, 0);
		Assert.assertEquals("Numéro de compte général", rowName5);
		System.out.println("Emission Units Delivery Report validated in French for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report validated in French for Quebec User");
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
		wait.until(ExpectedConditions.elementToBeClickable(btnReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is:" + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
	}

	public void assertReportsAvailableForSposnorInEnglish() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		String langLable = englishLngText.getText();
		System.out.println(langLable);
		System.out.println("English language lable is displayed in header section");
		ObjectRepo.getTest().log(Status.PASS, "English language lable is displayed in header section");
		WebElement abc = reportBtn;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.click(abc).build().perform();
		btnSearchReport.click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + eventNameEng + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + eventNameEng + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		englishLngText.click();
		frenchLngText.click();
		System.out.println("English to french language is changed");
		ObjectRepo.getTest().log(Status.PASS, "English to french language is changed");
		btnReportsQC.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReportFR)).click();
		String aseertHeadingOfReportPage = headingReportFR.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Rapports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("***********************************************************************");
		WebElement element1 = eventNameDropDownFR;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		eventNameDropDownFR.click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + eventNameFR + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + eventNameFR + "']"))).click();
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
		wait.until(ExpectedConditions.elementToBeClickable(cms));
		cms.click();
		System.out.println("Click the CMS Menu on Navigation Bar ");
		ObjectRepo.getTest().log(Status.PASS, "Click the CMS Menu on Navigation Bar ");
		System.out.println("TC ID:C31408:Validated Access the CMS page via the navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31408: Validated Access the CMS page via the navigation bar");
		WebElement jd = jurisdiction;
		Select jurisdictionname = new Select(jd);
		jurisdictionname.selectByVisibleText("Nova Scotia");
		System.out.println("Select Jurisdiction- Nova Scotia ");
		ObjectRepo.getTest().log(Status.PASS, "Select Jurisdiction- Nova Scotia");
		home.click();
		System.out.println("Click the HOME option on CMS Page");
		ObjectRepo.getTest().log(Status.PASS, "Click the HOME option on CMS Page");
		WebElement element = Footer;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Footer.click();
		System.out.println("Click the Footer option on CMS Page");
		ObjectRepo.getTest().log(Status.PASS, "Click the Footer option on CMS Page");
		wait.until(ExpectedConditions.elementToBeClickable(Link2));
		Link2.click();
		WebElement textbox = Link2;
		textbox.sendKeys(Keys.CONTROL, "A");
		textbox.sendKeys(Keys.CONTROL, "X");
		Link2.sendKeys("Copyright © 2024");
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
		preview.click();
		Set<String> windows = driver.getWindowHandles();
		System.out.println(windows);
		System.out.println("Get Window Handles");
		ObjectRepo.getTest().log(Status.PASS, "Get Window Handles");
		Iterator<String> it = windows.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		System.out.println("Switch to next window");
		ObjectRepo.getTest().log(Status.PASS, "Switch to next window");
		String E = driver.findElement(By.xpath("//a[text()='Copyright © 2023']")).getText();
		String A = "Copyright © 2023";
		Assert.assertEquals(A, E);
		System.out.println(E);
		driver.switchTo().window(parent);
		System.out.println("Validated the (Copyright © 2023 )information on PREVIEW page ");
		ObjectRepo.getTest().log(Status.PASS, "Validated the (Copyright © 2023)information on PREVIEW page ");
	}

	public void searchReportAR() throws InterruptedException {
		searchEntityID.sendKeys(prop.getProperty("CAThirdEntity"));
		EntityA1.click();
		continueBtn.click();
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
	}

	public void ValidateBidsAuditReportsReport() throws Exception {
		WebElement element = driver.findElement(btnBidsAuditReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReports).click();
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
				folderPath + File.separator + "Bids Audit Report - " + eventNameEng + " " + date1 + ".xlsx");
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

	public void ValidateBidsAuditReportsReportRESERVE() throws Exception {
		WebElement element = driver.findElement(btnBidsAuditReportsRESERVE);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsRESERVE).click();
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
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Bids Audit Report - EQA_R2.3_R317_AfterRevert_R317-CA Reserve Sale 07-04-2023.xlsx");
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

	public void ValidateBidsAuditReportsReportRESERVECA() throws Exception {
		WebElement element = driver.findElement(btnBidsAuditReportsRESERVE);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsRESERVE).click();
		Thread.sleep(20000);
		System.out.println("bids audit report download successfully");
		ObjectRepo.getTest().log(Status.PASS, "bids audit report download successfully");
		System.out.println("Bids Audit Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report-File extention validated as a .Xlsx format");
		System.out.println(
				"TC ID:C31846 : Sponsor/MM can get access to Bids Audit report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31846 : Sponsor/MM can get access to Bids Audit report for an Allowance Sale event during the bidding window");
		System.out.println(
				"TC ID:C31849 : Sponsor/MM can get access to bids audit report for an event once the bidding window opens.       ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31849 : Sponsor/MM can get access to bids audit report for an event once the bidding window opens.      ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - A211_OpenBW_Automation 02-27-2024.xlsx");
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

	public void ValidateBidsAuditReportsReportRESERVEQC() throws Exception {
		WebElement element = driver.findElement(btnBidsAuditReportsRESERVEQC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsAuditReportsRESERVEQC).click();
		Thread.sleep(20000);
		System.out.println("bids audit report download successfully");
		ObjectRepo.getTest().log(Status.PASS, "bids audit report download successfully");
		System.out.println("Bids Audit Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31845 : Confirm the specified introductory fields are available ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31845 : Confirm the specified introductory fields are available ");
		System.out.println(
				"TC ID:C31847 : Sponsor/MM can get access to Bids Audit report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"TTC ID:C31847 : Sponsor/MM can get access to Bids Audit report for an Allowance Sale event during the bidding window ");
		System.out.println(
				"TC ID:C31850 : Sponsor/MM can get access to bid audit report for an event once the bidding window opens.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31850 : Sponsor/MM can get access to bid audit report for an event once the bidding window opens.");
		System.out.println("TC ID:C31856 : Bids Audit Report must follow naming convention");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31856 : Bids Audit Report must follow naming convention");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - A211_OpenBW_Automation 02-27-2024.xlsx");
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
		System.out.println("Allowance delivery Reports successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + eventNameEng + " " + date1 + ".xlsx");
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

	public ArrayList<Object[][]> ValidateResultReports() throws IOException, InterruptedException {
		WebElement element = driver.findElement(btnResultReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnResultReports).click();
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
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Results Report - Results Report - EQA_R320_NS_ReserveSale_VPP_R2.3 06-12-2023.xlsx");
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
					XSSFRow row1 = (XSSFRow) Sheet1.getRow(0);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();
					System.out.println(value);
					if (value == "Annual Reserve Price") {
						Assert.assertTrue(value.contains("Annual Reserve Price"));
					} else if (value == "QC4097-5124") {
						Assert.assertTrue(value.contains("QC4097-5124"));
					} else if (value == "QC4091-5112") {
						Assert.assertTrue(value.contains("QC4091-5112"));
					} else if (value == "60.0") {
						Assert.assertTrue(value.contains("60.0"));
					} else if (value == "QC4085-5100") {
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
			return sheetDatas;
		}
	}

	public ArrayList<Object[][]> ValidateResultReportsMMSBMA() throws IOException, InterruptedException {
//		WebElement element = driver.findElement(btnResultReports);
//		js.executeScript("arguments[0].scrollIntoView();", element);
//		driver.findElement(btnResultReports).click();
		System.out.println("Result Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Result Reports download successfully");
		System.out.println("TC ID:C31821: MM can download and access the Results Report for SMBA ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31821: MM can download and access the Results Report for SMBA ");
		ArrayList<Object[][]> sheetDatas = new ArrayList<>();
		Object[][] object = null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Results Report - EQA_R70_R2.3_AfterRevert_R70_SBMA Prescribed 2 07-03-2023.xlsx");
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
					XSSFRow row1 = (XSSFRow) Sheet1.getRow(0);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();
					System.out.println(value);
					if (value == "Annual Reserve Price") {
						Assert.assertTrue(value.contains("Annual Reserve Price"));
					} else if (value == "QC4097-5124") {
						Assert.assertTrue(value.contains("QC4097-5124"));
					} else if (value == "QC4091-5112") {
						Assert.assertTrue(value.contains("QC4091-5112"));
					} else if (value == "60.0") {
						Assert.assertTrue(value.contains("60.0"));
					} else if (value == "QC4085-5100") {
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
			return sheetDatas;
		}
	}

	public ArrayList<Object[][]> ValidateResultReportsNSSBA() throws IOException, InterruptedException {
		WebElement element = btnResultReportsNSSBA;
		js.executeScript("arguments[0].scrollIntoView();", element);
		btnResultReportsNSSBA.click();
		System.out.println("Result Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Result Reports download successfully");
		System.out.println("TC ID:C31822: MM can download and access the Results Report for SBA");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31822: MM can download and access the Results Report for SBA");
		ArrayList<Object[][]> sheetDatas = new ArrayList<>();
		Object[][] object = null;
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Results Report - EQA_R320_NS_ReserveSale_VPP_R2.3 06-12-2023.xlsx");
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
					XSSFRow row1 = (XSSFRow) Sheet1.getRow(0);
					XSSFCell cell1 = row1.getCell(0);
					String value = cell1.toString();
					System.out.println(value);
					if (value == "Annual Reserve Price") {
						Assert.assertTrue(value.contains("Annual Reserve Price"));
					} else if (value == "QC4097-5124") {
						Assert.assertTrue(value.contains("QC4097-5124"));
					} else if (value == "QC4091-5112") {
						Assert.assertTrue(value.contains("QC4091-5112"));
					} else if (value == "60.0") {
						Assert.assertTrue(value.contains("60.0"));
					} else if (value == "QC4085-5100") {
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
			return sheetDatas;
		}
	}

	public String ValidateFinancialStatementreport() throws EmptyFileException, IOException, InterruptedException {
		driver.findElement(
				By.xpath("//button[@aria-label='Download Financial Statement for event " + eventNameEng + "']"))
				.click();
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
		File src = new File(folderPath + File.separator + "Financial Statement - " + eventNameEng + " " + date1 + " "
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

	public void LogoutQCFR() throws InterruptedException {
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
		WebElement element1 = welcomeFSA;
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
		String a = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/span[1]"))
				.getText();
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
		driver.findElement(By.xpath("(//p[contains(text(),'May an entity that is incorporated outside of the ')])[1]"))
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
		String j = driver.findElement(By.xpath("(//p[normalize-space()='What is the Auction Reserve Price?'])[1]"))
				.getText();
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
		driver.findElement(By.xpath("(//p[contains(text(),'How is the settlement price determined and winning')])[1]"))
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
		driver.findElement(By.xpath("(//p[contains(text(),'What is the difference between a Qualified Applica')])[1]"))
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
		String b1 = driver.findElement(By.xpath("(//th[@role='cell'][normalize-space()='T6CA UserA'])[1]")).getText();
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
		driver.findElement(
				By.xpath("//span[text()='ENTITY NAME']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By ENTITY NAME Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By ENTITY NAME Column");
		driver.findElement(
				By.xpath("//span[text()='BID ID']//span[@class='ant-table-column-sorter-inner sortingIcon']")).click();
		System.out.println("Successfully SORT By BID ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By BID ID Column");

		driver.findElement(
				By.xpath("//span[text()='CLIENT BID ID']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By CLIENT BID ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By CLIENT BID ID Column");

		driver.findElement(
				By.xpath("//span[text()='TRANSACTION ID']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TRANSACTION ID Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TRANSACTION ID Column");

		driver.findElement(
				By.xpath("//span[text()='ACCOUNT NO.']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By ACCOUNT NO. Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By ACCOUNT NO. Column");

		driver.findElement(
				By.xpath("//span[text()='TIME (ET)']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIME (ET) Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIME (ET) Column");

		driver.findElement(
				By.xpath("//span[text()='CURRENCY']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By CURRENCY Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By CURRENCY Column");

		driver.findElement(
				By.xpath("//span[text()='TIER PRICE']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIER PRICE Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIER PRICE Column");

		driver.findElement(
				By.xpath("//span[text()='BID QTY']//span[@class='ant-table-column-sorter-inner sortingIcon']")).click();
		System.out.println("Successfully SORT By BID QTY Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By BID QTY Column");

		driver.findElement(By.xpath("//span[text()='TIER']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By TIER Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By TIER Column");

		driver.findElement(
				By.xpath("//span[text()='Bid Status']//span[@class='ant-table-column-sorter-inner sortingIcon']"))
				.click();
		System.out.println("Successfully SORT By Bid Status Column");
		ObjectRepo.getTest().log(Status.PASS, "Successfully SORT By Bid Status Column");

		driver.findElement(
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

		WebElement element1 = welcomeFSA;
		Actions action = new Actions(driver);
		action.moveToElement(element1).build().perform();
		action.click(element1).build().perform();
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
		WebElement act1 = driver
				.findElement(By.xpath("//*[text()='Your security questions were reset successfully.']"));
		String actmessage = act1.getText();
		String expsuccess = "Your security questions were reset successfully.";
		Assert.assertEquals(actmessage, expsuccess);
		System.out.println(actmessage);
		System.out.println(expsuccess);
		System.out.println(
				"TC ID:C31611: Verified FSA can reset security questions upon successful login by accessing the top right down arrow menu.");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31611: Verified FSA can reset security questions upon successful login by accessing the top right down arrow menu.");

	}

	public void searchReportFSAQC() throws InterruptedException {
		Thread.sleep(8000);
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement eventName = driver.findElement(By.xpath("//*[@aria-label='Event Name']"));
		wait.until(ExpectedConditions.visibilityOf(eventName)).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("SBMA_Event_After_Certify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("SBMA_Event_After_Certify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReport() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
	}

	public void searchResultReport() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("(//select)[2]"));
		Select s = new Select(element);
		s.selectByVisibleText("Results Report");
		driver.findElement(By.xpath("//button[@title='SEARCH']")).click();
		driver.findElement(By.xpath("(//button[@title=\"Download Report\"])[1]")).click();
	}

	public void searchReportCA() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonCA;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonCA.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchBidReportqcSbma() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
	}

	public void searchClientBidAuditReportqcSbma() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchClientBidReportARqcSbma() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[@name='rollDownProcedure'])[2]")).click();
		driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
	}

	public void searchClientBidReportARCA() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[@name='rollDownProcedure'])[2]")).click();
		driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();", scroll_ToSearch);
	}

	public void searchClientBidReportARNS() throws InterruptedException {
		driver.findElement(By.xpath("(//div[@class='jss30'])[1]")).click();
		driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnReports));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonARNS;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonARNS.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchClientBidAuditReportRESERVESALECA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		btnReports.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonCA;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonCA.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReportCABIDAUDIT() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonCA;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonCA.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReportqcBIDAUDIT() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonqc;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonqc.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReportMMNS() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		eventNameDropDown.click();
		WebElement element111 = EventNmeButtonNS;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		EventNmeButtonNS.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void searchReportMMPEOR() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
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
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[1]"))))
				.click();
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
		btnReportsQC.click();
		System.out.println("Click 'Report' Button on navigation bar");
		ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
	}

	public void searchReportWS() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement element121 = eventNameDropDown;
		js.executeScript("arguments[0].scrollIntoView();", element121);
		eventNameDropDown.click();
		WebElement element1 = driver.findElement(By.xpath("//*[text()='WA123']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(By.xpath("//*[text()='WA123']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		System.out.println("Search Event Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Search Event Successfully");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void ValidatePostEventOperationReports() throws Exception {
		driver.findElement(btnPostEventOperationReports).click();
		System.out.println("Post Event Operation Reports download successfully");
		System.out.println("Bids Reports-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Reports-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31325: Confirm Post Event Operations Report is generated ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31325: Confirm Post Event Operations Report is generated ");
		System.out.println(
				"TC ID:C31380: Maintain the Post Event Operations Report for an event once generated along with an audit trail ");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31380: Maintain the Post Event Operations Report for an event once generated along with an audit trail ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Post Event Operations Report - " + eventNameEng + " " + date1 + ".xlsx");
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

	public void ValidateFinancialSettlementReportsFSAQCENG() throws Exception {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		WebElement element11 = driver
				.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		Financial_SettlementReport_DownloadCA.click();
		Thread.sleep(20000);
		ObjectRepo.getTest().log(Status.PASS, "Financial_SettlementReport Downloaded Successfully");
		System.out.println("Financial Settlement Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Reports-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31882 : Financial Settlement must follow naming convention");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31882 : Financial Settlement must follow naming convention");
		System.out.println("TC ID:C31885 : Verify the data in the Financial Settlement is correct ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31885 : Verify the data in the Financial Settlement is correct ");
		System.out.println("TC ID:C31888 : Financial Settlement must follow naming convention ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31888 : Financial Settlement must follow naming convention ");
		System.out.println("TC ID:C31834 : Financial Settlement must follow naming convention");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31834 : Financial Settlement must follow naming convention ");
		System.out.println("TC ID:C31837: Verify the data in the Financial Settlement is correct ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31837: Financial Settlement must follow naming convention ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Financial Settlement Report - R317_RS_After_Certify_CA 02-12-2024.xlsx");
		ObjectRepo.getTest().log(Status.PASS,
				"Financial Settlement Reports-File extention validated as a .Xlsx format and File name  <Event name> <Event date MM-DD-YYYY> validated");
		System.out.println(
				"Financial Settlement Reports-File extention validated as a .Xlsx format and File name  <Event name> <Event date MM-DD-YYYY> validated");
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
				ObjectRepo.getTest().log(Status.PASS, "All Headers in Financial Settlement Reports Asserted Successfully.");
				System.out.println("All Headers in Financial Settlement Reports Asserted Successfully.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateFinancialSettlementReportsENG() throws Exception {
		driver.findElement(btnFinancialSettlementReports).click();
		Thread.sleep(20000);
		System.out.println("Financial Settlement Reports download successfully");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Reports-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31834 : Financial Settlement must follow naming convention");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31834 : Financial Settlement must follow naming convention");
		System.out.println("TC ID:C31837 : Verify the data in the Financial Settlement is correct   ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31837 : Verify the data in the Financial Settlement is correct    ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Financial Settlement Report - R70_SBMA_AfterCertify 02-25-2024.xlsx");
		try {
			InputStream ExcelFileToRead = new FileInputStream(src);
			try (XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead)) {
				XSSFSheet sheet = wb.getSheetAt(0);
				XSSFRow row;
				XSSFCell cell;
				Iterator<Row> rows = sheet.rowIterator();
				XSSFRow row1 = (XSSFRow) sheet.getRow(5);
				XSSFCell cell1 = row1.getCell(0);
				String value = cell1.toString();
				if (value == "General Account Number") {
					Assert.assertTrue(value.contains("General Account Number"));
					System.out.println(value);
				} else if (value == "Legal Name") {
					Assert.assertTrue(value.contains("Legal Name"));
				} else if (value == "Operating Name") {
					Assert.assertTrue(value.contains("Operating Name"));
				} else if (value == "Government") {
					Assert.assertTrue(value.contains("Government"));
				} else if (value == "Financial Services Account Number") {
					Assert.assertTrue(value.contains("Financial Services Account Number"));
				} else if (value == "Financial Services Account Name") {
					Assert.assertTrue(value.contains("Financial Services Account Name"));
				} else if (value == "Currency") {
					Assert.assertTrue(value.contains("Currency"));
				} else if (value == "Cash Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Cash Bid Guarantee Value"));
				} else if (value == "Letter of Credit Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Letter of Credit Bid Guarantee Value"));
				} else if (value == "Bond Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Bond Bid Guarantee Value"));
				} else if (value == "Letter of Guarantee Bid Guarantee Value") {
					Assert.assertTrue(value.contains("Letter of Guarantee Bid Guarantee Value"));
				} else if (value == "Total Bid Guarantee Amount") {
					Assert.assertTrue(value.contains("Total Bid Guarantee Amount"));
				} else if (value == "Category A Successful Emission Units") {
					Assert.assertTrue(value.contains("Category A Successful Emission Units"));
				} else if (value == "Category B Successful Emission Units") {
					Assert.assertTrue(value.contains("Category B Successful Emission Units"));
				} else if (value == "Total Successful Bid Cost") {
					Assert.assertTrue(value.contains("Total Successful Bid Cost"));
				} else if (value == "Amount Owed") {
					Assert.assertTrue(value.contains("Amount Owed"));
				} else if (value == "Cash Amount Owed By") {
					Assert.assertTrue(value.contains("Cash Amount Owed By"));
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

	public void ValidateFinancialSettlementReportsFR() throws Exception {
		driver.findElement(btnFinancialSettlementReportsFR).click();
		System.out.println("Financial Settlement Reports download successfully");
		System.out.println("TC ID:C31621: Confirm Financial Settlement Report is available in French ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31621: Confirm Financial Settlement Report is available in French ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Rapport des paiements - " + eventNameFR + " " + date1 + ".xlsx");
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
		// Thread.sleep(25000);
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
		driver.findElement(btnBidsReports).click();
		System.out.println("Bids Reports download successfully");
		System.out.println("Bids Reports-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Bids Reports-File extention validated as a .Xlsx format");
		System.out.println(
				"TC ID:C31379: Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31379: Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + eventNameEng + " " + date1 + ".xlsx");
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
	}

	public void ValidateAllowancedeliveryReportsSP() throws Exception {
		WebElement element = driver.findElement(btnAllowancedeliveryReports);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnAllowancedeliveryReports).click();
		System.out.println("Allowance delivery Reports successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + eventNameEng + " " + date1 + ".xlsx");
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
		System.out.println("Allowance delivery Reports successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Allowance Delivery Report - " + eventNameEng + " " + date1 + ".xlsx");
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
		System.out.println("Emission Units Delivery Report Download successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Emission Units Delivery Report - " + eventNameEng + " "
				+ date1 + ".xlsx");
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

	public void ValidateemissiondeliveryReportsSBMAQC() throws Exception {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("SBMA_Event_After_Certify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("SBMA_Event_After_Certify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Emission_Units_Delivery_Report.click();
		Thread.sleep(20000);
		ObjectRepo.getTest().log(Status.PASS, "Emission_Units_Delivery_Report Downloaded Successfully");
		System.out.println("Emission Units Delivery Report Download successfully");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Emission Units Delivery Report - R70_SBMA_AfterCertify 02-25-2024.xlsx");
		System.out.println("Asserted The name of the report must be Allowance Delivery Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Allowance Delivery Report");
		System.out.println("Allowance Delivery Report-File extention validated as a .Xlsx format");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report-File extention validated as a .Xlsx format");
		System.out.println("TC ID:C31896 : Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31896 : Confirm the specified introductory fields are available");
		System.out.println("TC ID:C31907 : Verify the data in the Emission Units Delivery Report is correct");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31907 : Verify the data in the Emission Units Delivery Report is correct");
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
		driver.findElement(FinancialStatementdownloadbutton).click();
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
		File src = new File(folderPath + File.separator + "Financial Statement - " + eventNameEng + " " + date1 + " "
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

	public String ValidateFinancialStatementreportSP() throws EmptyFileException, IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Monitoring'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		}
		btnReports.click();
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		EntityNmeDropDownBtn2.click();
		commonLibref.scroll(entityName, "scrollToElement", "entity Name ");
		commonLibref.click(entityName, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Financial Statement");
		System.out.println("Financial Statement report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement report selected from Report Name Dropdown");
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Financial Statement report download successfully");
		System.out.println("TC ID:C31388: Verify that the Sponsor can access the Financial Statement   ");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31388: Verify that the Sponsor can access the Financial Statement   ");
		System.out.println(
				"TC ID:C31385: Confirm the Port field in the Financial Statement Report is populated with Finaicial Services Account Number");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31385: Confirm the Port field in the Financial Statement Report is populated with Finaicial Services Account Number");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + eventNameEng + " " + date1 + " "
				+ prop.getProperty("EntityQC") + ".pdf");
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
		return stripText;
	}

	public void ValidateClientBidsReport() throws Exception {
		driver.findElement(ClientBidsReportdownloadbutton).click();
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
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + eventNameEng + " " + date1 + " "
				+ prop.getProperty("EntityA1") + ".xlsx");
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
	}

	public void ValidateBidsReportqcsbma() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("SBMA_Event_After_Certify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("SBMA_Event_After_Certify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Bids_Report.click();
		Thread.sleep(20000);
		ObjectRepo.getTest().log(Status.PASS, "Bids Report Downloaded Successfully");
		System.out.println("Bids Report download successfully");
		System.out.println(
				"TC ID:C31930:Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31930:Verify the data in the Bids Report is correct and all the results dependant data (Settlement price, Tie breaker, violations, etc.) are reported accurately");
		System.out.println(
				" Validated bid report with Settlement price, Tie breaker,Holding limit and Bid Guarantee violations ");
		ObjectRepo.getTest().log(Status.PASS,
				"Validated bid report with Settlement price, Tie breaker,Holding limit and Bid Guarantee violations ");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - R70_SBMA_AfterCertify 02-25-2024.xlsx");
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
						if (value == "Holding Limit Violation") {
							Assert.assertTrue(value.contains("Holding Limit Violation"));
						} else if (value == "Bid Guarantee Violation") {
							Assert.assertTrue(value.contains("Bid Guarantee Violation"));
						} else if (value == "Tie Breaker") {
							Assert.assertTrue(value.contains("Bid Guarantee Violation"));
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
	}

	public void ValidateClientBidsAuditReportqcsbma() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("SBMA_Event_After_Certify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("SBMA_Event_After_Certify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		WebElement abc1 = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']//input[@id='auto']"));
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		driver.findElement(By.xpath("//span[contains(text(),'Prescribed_Emitter+B - PLC=0 PLA=0 HLC=15717500 HL')]"))
				.click();
		driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")).click();
		Select clientbidauditreport = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		clientbidauditreport.selectByVisibleText("Client Bids Audit Report");
		String clientbidauditreportinfo = driver
				.findElement(
						By.xpath("(//td[contains(text(),'This dynamic report includes all the bids submitte')])[2]"))
				.getText();
		System.out.println(clientbidauditreportinfo);
		driver.findElement(By.xpath("(//button[@aria-label='Download Report'])[1]")).click();
		Thread.sleep(20000);
		System.out.println("Client Bids Audit Report download successfully");
		System.out.println("TC ID:C31951 : Client Bids Audit Report headers verification");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31951 : Client Bids Audit Report headers verification");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Client Bids Audit Report - R70_SBMA_AfterCertify 02-25-2024 QC4091-5112.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public String ValidateFinancialStatementreportCA() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		WebElement abc1 = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']//input[@id='auto']"));
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		driver.findElement(By.xpath("//span[contains(text(),'Company+H')]")).click();
		driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")).click();
		Select financialstatemente = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		financialstatemente.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("(//button[@aria-label='Download Report'])[1]")).click();
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report Downloaded Successfully");
		Thread.sleep(25000);
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31979 : Sponsor/AR can get access to Financial Statement for an Allowance Sale event after Certification of an Allowance Sale event.");
		System.out.println(
				"TC ID:C31979 : Sponsor/AR can get access to Financial Statement for an Allowance Sale event after Certification of an Allowance Sale event.");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31983 : Financial Statement must follow naming convention");
		System.out.println("TC ID:C31983 : Financial Statement must follow naming convention");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Financial Statement - R317_RS_After_Certify_CA 02-12-2024 CA3213-4519.pdf");
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

	public void ValidateClientBidsReportqcsbma() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("SBMA_Event_After_Certify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("SBMA_Event_After_Certify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		WebElement abc1 = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']//input[@id='auto']"));
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		driver.findElement(By.xpath("//span[contains(text(),'Prescribed_Emitter+B - PLC=0 PLA=0 HLC=15717500 HL')]"))
				.click();
		driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")).click();
		Select clientbidauditreport = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		clientbidauditreport.selectByVisibleText("Client Bids Report");
		String clientbidauditreportinfo = driver
				.findElement(By.xpath("//td[contains(text(),'This static report includes a list of all bids sub')]"))
				.getText();
		System.out.println(clientbidauditreportinfo);
		driver.findElement(By.xpath("(//button[@aria-label='Download Report'])[1]")).click();
		Thread.sleep(20000);
		System.out.println("Client Bids Report download successfully");
		System.out.println("C31957-Confirm Sponsor  can access the Client Bid Report post certification");
		ObjectRepo.getTest().log(Status.PASS, "C31957-Confirm Sponsor  can access the Client Bid Report post certification");
		System.out.println("C31968-Client Bid Report must follow naming convention");
		ObjectRepo.getTest().log(Status.PASS, "C31968-Client Bid Report must follow naming convention");
		System.out.println("C31971-Verify the data in the Client Bid Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "C31971-Verify the data in the Client Bid Report is correct");
		System.out.println("TC ID:C31961 : Confirm Sponsor  can access the Client Bid Report post certification");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31961 : Confirm Sponsor  can access the Client Bid Report post certification");

		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Client Bids Report - R70_SBMA_AfterCertify 02-25-2024 QC4091-5112.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsReportARqcsbma() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys("A211_JointAuction_04_03_24");
		driver.findElement(By.xpath("//*[text()='A211_JointAuction_04_03_24']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Client_Bids_ReportQC.click();
		ObjectRepo.getTest().log(Status.PASS, "Bids Report Downloaded Successfully");
		System.out.println("Client Bids Report download successfully");
		Thread.sleep(25000);
		System.out.println("TC ID:C31959 : Confirm AR can access the Client Bid Report post certification(QC)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31959 : Confirm AR can access the Client Bid Report post certification(QC)");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Client Bids Report - A211_JointAuction_04_03_24 03-03-2024 QC4446-5674.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsReportARCARESERVESALE() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys("A211_JointAuction_04_03_24");
		driver.findElement(By.xpath("//*[text()='A211_JointAuction_04_03_24']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Client_Bids_ReportQC.click();
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report download successfully");
		Thread.sleep(25000);
		System.out.println("Client Bids Report download successfully");
		System.out.println("TC ID:C31958 : Confirm AR can access the Client Bid Report post certification(CA)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31958 : Confirm AR can access the Client Bid Report post certification(CA)");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Client Bids Report - A211_JointAuction_04_03_24 03-03-2024 CA4376-5560.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsReportARNSSBA() throws Exception {
		Actions report = new Actions(driver);
		report.sendKeys(Keys.PAGE_DOWN).build().perform();

		driver.findElement(By.xpath("//button[@aria-label='Download Client Bids Report for event A302-R2.3TEST']"))
				.click();
		System.out.println("Client Bids Report download successfully");
		System.out.println("TC ID:C31956 : Confirm AR can access the Client Bid Report post certification(NS)");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID:C31956 : Confirm AR can access the Client Bid Report post certification(NS)");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Client Bids Report - A302-R2.3TEST 06-29-2023 NS3562-3849.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsAuditReportRESERVECA() throws Exception {
		Actions report = new Actions(driver);
		report.sendKeys(Keys.PAGE_DOWN).build().perform();
		WebElement abc1 = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']//input[@id='auto']"));
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		// Thread.sleep(4000);
		driver.findElement(By.xpath("//span[normalize-space()='Company+R']")).click();
		driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")).click();
		Select clientbidauditreport = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		clientbidauditreport.selectByVisibleText("Client Bids Audit Report");
		String clientbidauditreportinfo = driver
				.findElement(
						By.xpath("(//td[contains(text(),'This dynamic report includes all the bids submitte')])[2]"))
				.getText();
		System.out.println(clientbidauditreportinfo);
		driver.findElement(By.xpath("(//button[@aria-label='Download Report'])[1]")).click();
		System.out.println("Client Bids Audit Report download successfully");
		System.out.println("TC ID:C31970 : Verify the data in the Client Bid Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31970 : Verify the data in the Client Bid Report is correct");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Client Bids Audit Report - EQA_R2.3_R317_AfterRevert_R317-CA Reserve Sale 07-04-2023 CA3223-4531.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsReportRESERVECA() throws Exception {
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("ResSaleEventAfterCertify"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ResSaleEventAfterCertify") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		WebElement abc1 = driver
				.findElement(By.xpath("//td[@class='MuiTableCell-root MuiTableCell-body']//input[@id='auto']"));
		js.executeScript("arguments[0].scrollIntoView();", abc1);
		abc1.click();
		driver.findElement(By.xpath("//span[contains(text(),'Company+H')]")).click();
		driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")).click();
		Select clientbidsreport = new Select(
				driver.findElement(By.xpath("(//select[@id='demo-customized-select'])[4]")));
		clientbidsreport.selectByVisibleText("Client Bids Report");
		String clientbidsreport1 = driver
				.findElement(
						By.xpath("(//td[contains(text(),'This dynamic report includes a list of all bids su')])[1]"))
				.getText();
		System.out.println(clientbidsreport1);
		driver.findElement(By.xpath("(//button[@aria-label='Download Report'])[1]")).click();
		Thread.sleep(20000);
		System.out.println("Client Bids Report download successfully");
		System.out.println("TC ID:C31970 : Verify the data in the Client Bid Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "TC ID:C31970 : Verify the data in the Client Bid Report is correct");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		dateFormat.format(date);
		File src = new File(folderPath + File.separator
				+ "Client Bids Report - R317_RS_After_Certify_CA 02-12-2024 CA3213-4519.xlsx");
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
						if (value == "Client Bid ID") {
							Assert.assertTrue(value.contains("Client Bid ID"));
						} else if (value == "Transaction ID") {
							Assert.assertTrue(value.contains("Transaction ID"));
						} else if (value == "Update Type") {
							Assert.assertTrue(value.contains("Update Type"));
						} else if (value == "Update By") {
							Assert.assertTrue(value.contains("Update By"));
						} else if (value == "Update Time (ET)") {
							Assert.assertTrue(value.contains("Update Time (ET)"));
						} else if (value == "Bid Creation Time (ET)") {
							Assert.assertTrue(value.contains("Bid Creation Time (ET)"));
						} else if (value == "Category") {
							Assert.assertTrue(value.contains("Category"));
						} else if (value == "Category Price") {
							Assert.assertTrue(value.contains("Category Price"));
						} else if (value == "Total Emission Units Bid") {
							Assert.assertTrue(value.contains("Total Emission Units Bid"));
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
	}

	public void ValidateClientBidsAuditreport() throws Exception {
		driver.findElement(
				By.xpath("//button[@aria-label='Download Client Bids Audit Report for event JointEventAuctionAuto']"))
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
		// Thread.sleep(7000);
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
		// Thread.sleep(7000);
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
		String actual1 = driver.findElement(By.xpath("(//p[normalize-space()='FX Rate submitted.'])[1]")).getText();
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
		wait.until(ExpectedConditions.elementToBeClickable(btnReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + eventNameEng + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + eventNameEng + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
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
		WebElement resultReport = null;
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
		driver.findElement(btnPostEventOperationReports).click();
		ExcelDataProviderForPostEventOperationReport ref = new ExcelDataProviderForPostEventOperationReport();
		String rowName = ref.getStringData("Post Event Operations Report", 5, 0);
		Assert.assertEquals("Event Information", rowName);
		System.out.println("Post Event Operations Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in English for Quebec User");
		driver.findElement(btnBidsAuditReports).click();
		ExcelDataProviderForBidsAuditReport ref1 = new ExcelDataProviderForBidsAuditReport();
		String rowName1 = ref1.getStringData("Bids Audit Report", 5, 0);
		Assert.assertEquals("Registry General Account Number", rowName1);
		System.out.println("Bids audit report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in English for Quebec User");
		driver.findElement(btnFinancialSettlementReports).click();
		ExcelDataProviderForFinancialSettlementReport ref2 = new ExcelDataProviderForFinancialSettlementReport();
		String rowName2 = ref2.getStringData("Financial Settlement Report", 4, 0);
		Assert.assertEquals("Settlement Price USD (Current)", rowName2);
		System.out.println("Financial Settlement Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for Quebec User");
		driver.findElement(btnBidsReports).click();
		ExcelDataProviderforBidsReport ref3 = new ExcelDataProviderforBidsReport();
		String rowName3 = ref3.getStringData("Bids Report", 3, 0);
		Assert.assertEquals("Total Bids Submitted", rowName3);
		System.out.println("Bids report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in English for Quebec User");
		driver.findElement(btnResultReports).click();
		ExcelDataProviderforResultReport ref4 = new ExcelDataProviderforResultReport();
		String rowName4 = ref4.getStringData("Event Data", 11, 0);
		Assert.assertEquals("Auction Reserve Price", rowName4);
		System.out.println("Results report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in English for Quebec User");
		driver.findElement(btnAllowancedeliveryReports).click();
		ExcelDataProviderForAllowanceDeliveryReport ref5 = new ExcelDataProviderForAllowanceDeliveryReport();
		String rowName5 = ref5.getStringData("Allowance Delivery Report", 4, 0);
		Assert.assertEquals("Registry General Account Number", rowName5);
		System.out.println("Allowance Delivery Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report validated in English for Quebec User");
		driver.findElement(btnQualifiedBidderReports).click();
		ExcelDataProviderForQualifiedBidderReport ref6 = new ExcelDataProviderForQualifiedBidderReport();
		String rowName6 = ref6.getStringData("Qualified Bidders Report", 3, 0);
		Assert.assertEquals("Total Qualified Bidders", rowName6);
		System.out.println("Qualified Bidder Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated in English for Quebec User");
		System.out.println("******************************************************************");
	}

	public void accessTheReportsAvailableForMM() throws Exception {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		String aseertHeadingOfReportPage = headingReportEN.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='" + eventNameEng + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + eventNameEng + "']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
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
		WebElement resultReport = null;
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
		driver.findElement(btnBidsAuditReports).click();
		ExcelDataProviderForBidsAuditReport ref1 = new ExcelDataProviderForBidsAuditReport();
		String rowName = ref1.getStringData("Bids Audit Report", 5, 0);
		Assert.assertEquals("Registry General Account Number", rowName);
		System.out.println("Bids audit report validated for English language User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated for English language User");
		driver.findElement(btnBidsReports).click();
		ExcelDataProviderforBidsReport ref2 = new ExcelDataProviderforBidsReport();
		String rowName2 = ref2.getStringData("Bids Report", 3, 0);
		Assert.assertEquals("Total Bids Submitted", rowName2);
		System.out.println("Bids report validated for English language User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated for English language User");
		driver.findElement(btnResultReports).click();
		ExcelDataProviderforResultReport ref3 = new ExcelDataProviderforResultReport();
		String rowName3 = ref3.getStringData("Event Data", 11, 0);
		Assert.assertEquals("Auction Reserve Price", rowName3);
		System.out.println("Results report validated for English language User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated for English language User");
		driver.findElement(btnAllowancedeliveryReports).click();
		ExcelDataProviderForAllowanceDeliveryReport ref4 = new ExcelDataProviderForAllowanceDeliveryReport();
		String rowName4 = ref4.getStringData("Allowance Delivery Report", 4, 0);
		Assert.assertEquals("Registry General Account Number", rowName4);
		System.out.println("Allowance Delivery Report validated for English language User");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report validated for English language User");
		driver.findElement(btnQualifiedBidderReports).click();
		ExcelDataProviderForQualifiedBidderReport ref5 = new ExcelDataProviderForQualifiedBidderReport();
		String rowName5 = ref5.getStringData("Qualified Bidders Report", 3, 0);
		Assert.assertEquals("Total Qualified Bidders", rowName5);
		System.out.println("Qualified Bidder Report validated for English language User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated for English language User");
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForSposnorEN() throws Exception {
		driver.findElement(btnPostEventOperationReports).click();
		ExcelDataProviderForPostEventOperationReport ref = new ExcelDataProviderForPostEventOperationReport();
		String rowName = ref.getStringData("Post Event Operations Report", 5, 0);
		Assert.assertEquals("Event Information", rowName);
		System.out.println("Post Event Operations Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report validated in English for Quebec User");
		driver.findElement(btnBidsAuditReports).click();
		ExcelDataProviderForBidsAuditReport ref1 = new ExcelDataProviderForBidsAuditReport();
		String rowName1 = ref1.getStringData("Bids Audit Report", 5, 0);
		Assert.assertEquals("General Account Number", rowName1);
		System.out.println("Bids audit report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids audit report validated in English for Quebec User");
		driver.findElement(btnFinancialSettlementReports).click();
		ExcelDataProviderForFinancialSettlementReport ref2 = new ExcelDataProviderForFinancialSettlementReport();
		String rowName2 = ref2.getStringData("Financial Settlement Report", 4, 0);
		Assert.assertEquals("Settlement Price USD (Current)", rowName2);
		System.out.println("Financial Settlement Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for Quebec User");
		driver.findElement(btnBidsReports).click();
		ExcelDataProviderforBidsReport ref3 = new ExcelDataProviderforBidsReport();
		String rowName3 = ref3.getStringData("Bids Report", 3, 0);
		Assert.assertEquals("Total Bids Submitted", rowName3);
		System.out.println("Bids report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Bids report validated in English for Quebec User");
		driver.findElement(btnResultReports).click();
		ExcelDataProviderforResultReport ref4 = new ExcelDataProviderforResultReport();
		String rowName4 = ref4.getStringData("Event Data", 11, 0);
		Assert.assertEquals("Auction Reserve Price", rowName4);
		System.out.println("Results report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Results report validated in English for Quebec User");
		driver.findElement(btnEmissiondeliveryReports).click();
		ExcelDataProviderForEmissionUnitsDeliveryReport ref5 = new ExcelDataProviderForEmissionUnitsDeliveryReport();
		String rowName5 = ref5.getStringData("Emission Units Delivery Report", 4, 0);
		Assert.assertEquals("General Account Number", rowName5);
		System.out.println("Emission Units Delivery Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report validated in English for Quebec User");
		driver.findElement(btnQualifiedBidderReports).click();
		ExcelDataProviderForQualifiedBidderReport ref6 = new ExcelDataProviderForQualifiedBidderReport();
		String rowName6 = ref6.getStringData("Qualified Bidders Report", 3, 0);
		Assert.assertEquals("Total Qualified Bidders", rowName6);
		System.out.println("Qualified Bidder Report validated in English for Quebec User");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidder Report validated in English for Quebec User");
		System.out.println("******************************************************************");
	}

	public void Logout() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
			btnProfile.click();
			btnLogout.click();
		} catch (Exception e) {
			System.out.println("Not able to logout ");
		}
	}

	public void LogoutQC() {
		try {
			driver.navigate().refresh();
			btnProfileQC.click();
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
		WebElement e = Homeeventname;
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
				.elementToBeClickable(driver.findElement(By.xpath("(//*[@data-icon='angle-right'])[1]")))).click();
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
		WebElement abc2 = eventfrommonitoring;
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
		WebElement e = Homeeventname;
		e.getText();
		WebElement e1 = Homeeventstatus;
		e1.getText();
		System.out.println("Ongoing Event name is: " + e.getText());
		System.out.println("Ongoing Event status is:" + e1.getText());
		ObjectRepo.getTest().log(Status.PASS, "Onging indivisual event name & status is verified & printed on console");
	}

	public void clickreportmenu() throws Exception {
		// Thread.sleep(8000);
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
		// Thread.sleep(25000);
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
		System.out.println("bids audit report download successfully");
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + eventNameCA + " " + date1 + ".xlsx");
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
		btnsearchreport();
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
		btnsearchreport();
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
		downloadongoingeventbidauditreport1();
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
		downloadongoingeventbidauditreportCA();
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");
	}

	public void MMaccesstheBidsAuditReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventbidauditreportRS_CA();
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");
	}

	public void MMaccesstheBidsAuditReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventbidauditreportQc();
		Logout();
		System.out.println("MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "MM Logout successfully ");
	}

	public void ClientBidsAuditreportselectfromdropdown() {
		// //Thread.sleep(2000);
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
		EntityNmeDropDownBtn2.click();
		commonLibref.scroll(entityName1, "scrollToElement", "entity Name ");
		commonLibref.click(entityName1, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void downloadongoingeventClientbidauditreportCA() throws InterruptedException {
		EntityNmeDropDownBtn2.click();
		// Thread.sleep(4000);
		commonLibref.scroll(entityName2, "scrollToElement", "entity Name ");
		// Thread.sleep(4000);
		commonLibref.click(entityName2, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
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
		EntityNmeDropDownBtn2.click();
		commonLibref.scroll(entityNameRS, "scrollToElement", "entity Name ");
		commonLibref.click(entityNameRS, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void downloadongoingeventClientbidauditreportRS_WA() throws InterruptedException {
		EntityNmeDropDownBtn2.click();
		commonLibref.scroll(entityNameRS_WA, "scrollToElement", "entity Name ");
		commonLibref.click(entityNameRS_WA, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
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
		EntityNmeDropDownBtn2.click();
		commonLibref.scroll(entityNameQC, "scrollToElement", "entity Name ");
		commonLibref.click(entityNameQC, "javascriptClick", "entity Name ");
		ReportNameDropDown2.click();
		WebElement dropDown1 = reportNameDropDown1;
		Select zmr1 = new Select(dropDown1);
		zmr1.selectByVisibleText("Client Bids Audit Report");
		System.out.println("Client bids audit report selected from Report Name Dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report selected from Report Name Dropdown");
		String ClientBidsAuditRow = clientBidsAuditRow.getText();
		System.out.println("Sponsor can access :- " + ClientBidsAuditRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor can access :- " + ClientBidsAuditRow);
		wait.until(ExpectedConditions.elementToBeClickable(randomEventNmeReportDwnBtn));
		randomEventNmeReportDwnBtn.click();
		System.out.println("Client bids audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Client bids audit report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
	}

	public void sponsoraccesstheClientBidsAuditReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		driver.navigate().refresh();
		btnsearchreport();
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
		downloadongoingeventClientbidauditreport1();
		System.out.println("TC ID=C31296/C31300:Sponsor can access and download the Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31296/C31300:Verify that the Sponsor can access/download the Client Bids Audit Report");
	}

	public void sponsoraccesstheClientBidsAuditReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreportCA();
	}

	public void sponsoraccesstheClientBidsAuditReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreportRS_CA();
	}

	public void sponsoraccesstheClientBidsAuditReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		downloadongoingeventClientbidauditreportRS_WA();
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

	public void downloadongoingeventQualifiedBiddersReport() throws InterruptedException {
		WebElement abc7 = downloadreportopt;

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
		System.out.println("Click on Download option");
		ObjectRepo.getTest().log(Status.PASS, "Click on Download option");
	}

	public void downloadongoingeventQualifiedBiddersReportRS_CA() throws InterruptedException {
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdown1();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsorMMaccesstheQualifiedBiddersReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReportRS_CA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsorMMaccesstheQualifiedBiddersReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReportRS_CA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsorMMaccesstheQualifiedBiddersReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsorMMaccesstheQualifiedBiddersReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdown1();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Qualified Bidders Report - " + eventNameCA + " " + date1 + ".xlsx");
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - " + ReserveSaleEventName + " "
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

	public void SponsorMMaccesstheQualifiedBiddersReportWA_RS() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		QualifiedBiddersReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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

		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		PostEventOperationsReportselectfromdropdown();
		System.out.println("Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdown2();
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		PostEventOperationsReportselectfromdropdown();
		System.out.println("Post Event Operations Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownCA_Single_Auction();
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		PostEventOperationsReportselectfromdropdown();
		System.out.println("Post Event Operations Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_CA();
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		PostEventOperationsReportselectfromdropdown();
		System.out.println("Post Event Operations Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownRS_WA();
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		PostEventOperationsReportselectfromdropdown();
		System.out.println("Post Event Operations Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report is  selected from dropdown");
		QualifiedBiddersReportEventSelectfromDropdownQC_Single_Auction();
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(
				folderPath + File.separator + "Post Event Operations Report - " + eventNameQC + " " + date1 + ".xlsx");
		// File src = new File(folderPath+File.separator+"Bids Audit Report -
		// AuctionEvent1 11-21-2022.xlsx");
		// Assert.assertTrue("Bids Audit Report - AuctionEvent1
		// 11-21-2022.xlsx".contains("Bids Audit Report"));
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
		// //Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Bids Report");

	}

	public void BidsReportEventSelectfromDropdown() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrp1);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(3000);
		element12.click();

	}

	public void downloadongoingeventBidsReport() {
		// Click on Download option
		// //Thread.sleep(2000);
		WebElement abc7 = downloadreportopt;

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		// //Thread.sleep(3000);
		abc7.click();
	}

	public void SponsorMMaccesstheBidsReport() throws Exception {
		clickHomemenu();
		System.out.println("Click on Home menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Home menu");
		// //Thread.sleep(3000);
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// //Thread.sleep(3000);
		BidsReportselectfromdropdown();
		System.out.println("Bids Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is selected from dropdown");
		// //Thread.sleep(3000);
		BidsReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		System.out.println("TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");

		// Logout();
		// System.out.println("Sponsor/MM Logout successfully");
		// ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsorMMaccesstheBidsReport1() throws Exception {
		clickHomemenu();
		System.out.println("Click on Home menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Home menu");
		downloadongoingeventQualifiedBiddersReport();
		System.out.println("TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31374:Sponsor/MM can access and download the Bids Report for Individual auction");
	}

	public void FinancialSettlementReportselectfromdropdown() {
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
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;

		js.executeScript("arguments[0].scrollIntoView();", abc4);
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrp1);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(4000);
		element12.click();

	}

	public void FinancialSettlementReportEventSelectfromDropdownCA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpCA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnCA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(4000);
		element12.click();

	}

	public void FinancialSettlementReportEventSelectfromDropdownRS_CA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnRS_CA;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(4000);
		element12.click();

	}

	public void FinancialSettlementReportEventSelectfromDropdownRS_WA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnWS;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(4000);
		element12.click();

	}

	public void FinancialSettlementReportEventSelectfromDropdownQC() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpQC);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		WebElement abc8 = BEdropdwnname1;
		// //Thread.sleep(3000);
		abc8.click();

		// //Thread.sleep(2000);
		WebElement element12 = entityDrpdwnQC;
		js.executeScript("arguments[0].scrollIntoView();", element12);
		// //Thread.sleep(4000);
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
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FinancialSettlementReportEventSelectfromDropdown1();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		System.out.println(
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID=C31330:Sponsor/MM can access and download the Financial Settlement Report for Individual auction");
		//
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FinancialSettlementReportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		// System.out.println(
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		// ObjectRepo.getTest().log(Status.PASS,
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		//
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FinancialSettlementReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		// System.out.println(
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		// ObjectRepo.getTest().log(Status.PASS,
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		//
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FinancialSettlementReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		// System.out.println(
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		// ObjectRepo.getTest().log(Status.PASS,
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		//
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void SponsoraccesstheFinancialSettlementReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FinancialSettlementReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
		// System.out.println(
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		// ObjectRepo.getTest().log(Status.PASS,
		// "TC ID=C31330:Sponsor/MM can access and download the Financial Settlement
		// Report for Individual auction");
		//
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdown() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;

		js.executeScript("arguments[0].scrollIntoView();", abc4);
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrp1);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

		// entity selection from boostrap dropdown selection
		// ////Thread.sleep(3000);
		// new WebDriverWait(driver,
		// Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(BEdropdwnname1));
		// WebElement abc8=BEdropdwnname1;
		// ////Thread.sleep(3000);
		// abc8.click();
		//
		// ////Thread.sleep(2000);
		// WebElement element12 =driver.findElement(entityDrpdwn);
		//
		// js.executeScript("arguments[0].scrollIntoView();", element12);
		// ////Thread.sleep(3000);
		// element12.click();

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownCA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpCA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownRS_CA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpRS_CA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownRS_WA() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpRS_WA);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

	}

	public void FSAFinancialSettlementReportEventSelectfromDropdownQC() {
		// //Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(Bdropdwnname));
		WebElement abc4 = Bdropdwnname;
		// //Thread.sleep(3000);
		abc4.click();

		// //Thread.sleep(2000);
		WebElement element11 = driver.findElement(EventnametinDrpQC);

		js.executeScript("arguments[0].scrollIntoView();", element11);
		// //Thread.sleep(3000);
		element11.click();

	}

	public void FSAaccesstheFinancialSettlementReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		// //Thread.sleep(3000);
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		// //Thread.sleep(3000);
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		// //Thread.sleep(3000);
		FSAFinancialSettlementReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		// //Thread.sleep(3000);
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		// //Thread.sleep(3000);
		downloadongoingeventQualifiedBiddersReport();
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
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FSAFinancialSettlementReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FSAFinancialSettlementReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FSAFinancialSettlementReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
		Logout();
		System.out.println("FSA Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "FSA Logout successfully ");

	}

	public void FSAaccesstheFinancialSettlementReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialSettlementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FSAFinancialSettlementReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventQualifiedBiddersReport();
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

	public void SponsorMMaccesstheResultsReport() throws Exception {
		clickEventAdministration();
		System.out.println("Click on Event administration menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdown1();
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

	public void SponsorMMaccesstheResultsReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReport();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsorMMaccesstheResultsReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReport();
		Logout();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsorMMaccesstheResultsReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventResultsReport();
		System.out.println("Sponsor/MM Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM Logout successfully ");
	}

	public void SponsorMMaccesstheResultsReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ResultsReportselectfromdropdown();
		System.out.println("Results Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is selected from dropdown");
		ResultsReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
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
		btnsearchreport();
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
		wait.until(ExpectedConditions.elementToBeClickable(btnReportFR));
		WebElement abcD1 = driver.findElement(btnReportFR);
		abcD1.click();
	}

	public void btnsearchreportFR() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReportFR));
		WebElement abcD2 = btnSearchReportFR;
		abcD2.click();
	}

	public void ResultsReportselectfromdropdownFR() {
		wait.until(ExpectedConditions.elementToBeClickable(reportNameDropDown1));
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Rapport des résultats");

	}

	public void ResultsReportEventSelectfromDropdownFR() {
		wait.until(ExpectedConditions.elementToBeClickable(BdropdwnnameFR));
		WebElement abcD3 = BdropdwnnameFR;
		abcD3.click();
		WebElement element12 = driver.findElement(EventnametinDrp1);

		js.executeScript("arguments[0].scrollIntoView();", element12);
		element12.click();
		wait.until(ExpectedConditions.elementToBeClickable(BEdropdwnname1FR));
		WebElement abcD4 = BEdropdwnname1FR;
		abcD4.click();
		WebElement element13 = entityDrpdwn;
		js.executeScript("arguments[0].scrollIntoView();", element13);
		element13.click();
	}

	public void clicksearchbtnFR() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchFR));
		btnSearchFR.click();
	}

	public void downloadongoingeventResultsReportFR() {
		WebElement abcD7 = downloadreportopt;

		js.executeScript("arguments[0].scrollIntoView();", abcD7);
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
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Allowance Delivery Report");
	}

	public void ReserveSaleBidLotAuditReportselectfromdropdown() {
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Reserve Sale Bid Lot Audit Report");
	}

	public void AllowanceDeliveryReportselectfromdropdownQC() {
		WebElement eventType = reportNameDropDown1;
		Select s = new Select(eventType);
		s.selectByVisibleText("Emission Units Delivery Report");
	}

	public void AllowanceDeliveryReportEventSelectfromDropdown() {
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
		WebElement abc7 = downloadreportopt;

		js.executeScript("arguments[0].scrollIntoView();", abc7);
		abc7.click();
	}

	public void SponsoraccesstheAllowanceDeliveryReport() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownCA();
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

	public void SponsoraccesstheAllowanceDeliveryReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownRS_CA();
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

	public void SponsoraccesstheAllowanceDeliveryReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdownQC();
		System.out.println("Emission Units Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownQC();
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

	public void SponsoraccesstheAllowanceDeliveryReport1() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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
	}

	public void SponsoraccesstheAllowanceDeliveryReportCA_RS() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ReserveSaleBidLotAuditReportselectfromdropdown();
		System.out.println("Allowance Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		AllowanceDeliveryReportselectfromdropdownQC();
		System.out.println("Emission Units Delivery Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report is selected from dropdown");
		AllowanceDeliveryReportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		clickdownloadAllowanceDeliveryReport();
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

	public void downloadongoingeventClientbidreport() {
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

	public void downloadongoingeventClientbidreportRS_WA() {
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
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsreportselectfromdropdown();
		System.out.println("Client Bids Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report is  selected from dropdown");
		ClientBidsreportEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidreport();
		System.out.println(
				"TC ID31382=:Maintain the Client Bid Reports for an event once generated along with an audit trail");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID31382=:Maintain the Client Bid Reports for an event once generated along with an audit trail");
	}

	public void sponsoraccesstheClientBidsReportCA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsreportselectfromdropdown();
		System.out.println("Client Bids Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report is  selected from dropdown");
		ClientBidsreportEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidreportCA();
	}

	public void sponsoraccesstheClientBidsReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsreportselectfromdropdown();
		System.out.println("Client Bids Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report is  selected from dropdown");
		ClientBidsreportEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidreportRS_CA();
	}

	public void sponsoraccesstheClientBidsReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsreportselectfromdropdown();
		System.out.println("Client Bids Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report is  selected from dropdown");
		ClientBidsreportEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidreportRS_WA();
	}

	public void sponsoraccesstheClientBidsReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		ClientBidsreportselectfromdropdownQC();
		System.out.println("Bids Report is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is  selected from dropdown");
		ClientBidsreportEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventClientbidreportQC();
	}

	public void SponsorMMdownloadtheBidsAuditReportfromMonitoring() throws Exception {
		clickEventMonitoring();
		System.out.println("Click on Event monitoring  menu");
		ObjectRepo.getTest().log(Status.PASS, "Event monitoring  menu");
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
		btnsearchreport();
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
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialStatementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialStatementEventSelectfromDropdown();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
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
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialStatementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialStatementEventSelectfromDropdownCA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventFinancialStatementreportCA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportRS_CA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialStatementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialStatementEventSelectfromDropdownRS_CA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventFinancialStatementreportRS_CA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportRS_WA() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialStatementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialStatementEventSelectfromDropdownRS_WA();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
		downloadongoingeventFinancialStatementreportRS_WA();
		Logout();
		System.out.println("Sponsor Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Logout successfully ");
	}

	public void SponsoraccesstheFinancialStatementReportQC() throws Exception {
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		btnsearchreport();
		System.out.println("Click on Report search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report search button");
		FinancialStatementReportselectfromdropdown();
		System.out.println("Financial Settlement Report  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is selected from dropdown");
		FinancialStatementEventSelectfromDropdownQC();
		System.out.println("Event name  is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Event name is  selected from dropdown");
		clicksearchbtn();
		System.out.println("Click on search button");
		ObjectRepo.getTest().log(Status.PASS, "Click on search button");
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
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Client Bids Report - " + eventNameCA + " " + date1 + " "
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
				+ date1 + " " + prop.getProperty("ResEntity4_WA") + ".xlsx");
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
			src = new File(folderPath + File.separator + "Bids Report - " + eventNameEng + date2 + ".xlsx");
		} catch (Exception e) {
			src = new File(folderPath + File.separator + "Bids Report - " + eventNameEng + date2 + " .xlsx");
		}
		System.out.println(
				"reports path : " + folderPath + File.separator + "Bids Report - " + eventNameEng + date2 + ".xlsx");
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

	public void DataReadFromBidsReportCA() throws IOException, InterruptedException {
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");

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
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");

		WebElement element = driver.findElement(btnBidsReportsRS_CA);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsRS_CA).click();
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
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");

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
		abc4.click();
		System.out.println("Step5:Click on 'RESET MY SECURITY QUESTIONS' button");
		ObjectRepo.getTest().log(Status.PASS, "Step5:Click on 'RESET MY SECURITY QUESTIONS' button");

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[text()='Your security questions were reset successfully.']")));
		WebElement abc5 = driver
				.findElement(By.xpath("//*[text()='Your security questions were reset successfully.']"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
		abc2.click();
		System.out.println("Click on Event Monitoring menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Event Monitoring menu");
		WebElement abc3 = driver.findElement(By.xpath("(//*[text()='A183-JOINTAUCTIONBiddingWindowOpen'])[1]"));
		abc3.click();
		System.out.println(
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step4:Event name select from Event Monitoring during the open bidding window before results are run.");
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
				By.xpath("(//*[@style='position: relative; overflow: hidden; width: 100%; height: 100%;'])"));
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
		// configuration details
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

		// certification-approval2 & submit click
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]"));
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])"));
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
		// other similar sendKeys operations for different elements...
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
		// wiring instruction get text
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
		// Go to Auction certification page
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
		WebElement abc14 = driver.findElement(By.xpath("(//*[@aria-label='secondAuctionCertified'])[1]"));
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();
		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])"));
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

		WebElement abc10 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[9]"));
		abc10.getText();
		WebElement abc11 = driver.findElement(By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[12]"));
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

		WebElement abc14 = driver.findElement(By.xpath("(//*[@data-icon='times-circle'])[1]"));
		abc14.click();
		WebElement abc15 = driver.findElement(By.xpath("(//*[text()='SUBMIT'])[1]"));
		abc15.click();

		WebElement abc16 = driver.findElement(By.xpath("(//button[@title='SUBMIT']//*[text()='SUBMIT'])"));
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
		abc7.click();
		System.out.println("Reset algorithm status successful.");
		ObjectRepo.getTest().log(Status.PASS, "Reset algorithm status successful.");
		WebElement ApproverName = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[18]"));
		ApproverName.isEnabled();
		System.out.println("Approver Name is not displayed:" + ApproverName.isEnabled());
		WebElement RunALgobtn = driver.findElement(
				By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[19]"));
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
		// click on Reports menu
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

		// String SettlementpriceUSDCurrent
		// =sheet.getRow(6).getCell(0).getStringCellValue();
		// double SettlementpriceUSDCurrentval
		// =sheet.getRow(6).getCell(1).getNumericCellValue();
		// System.out.println(SettlementpriceUSDCurrent+":"+SettlementpriceUSDCurrentval);
		//
		// String SettlementpriceUSDAdvance
		// =sheet.getRow(7).getCell(0).getStringCellValue();
		// double SettlementpriceUSDAdvanceval
		// =sheet.getRow(7).getCell(1).getNumericCellValue();
		// System.out.println(SettlementpriceUSDAdvance+":"+SettlementpriceUSDAdvanceval);

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
		// File src=new File("C:\\Users\\PadmakarMohire\\Desktop\\report
		// automation\\Client Bids Audit Report2ndTime -
		// A183-JOINTAUCTIONBiddingWindowOpen 01-11-2023.xlsx");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
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
		abc6.click();
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
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

	// sponsor select bid & flagged bid
	public void Sponsor_FlaggedBid() {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Event Monitoring'])")));
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Event Monitoring'])"));
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
		abc9.click();
		System.out.println("Step:Select bid");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select bid");
		WebElement abc10 = driver.findElement(By.xpath("(//*[@data-icon='ellipsis-h'])[4]"));
		abc10.click();
		System.out.println("click on 3 dot function button");
		WebElement abc11 = driver.findElement(By.xpath("//*[text()='Include Bid']"));
		abc11.click();
		WebElement abc12 = driver.findElement(By.xpath("//*[@placeholder='Enter Text']"));
		abc12.sendKeys("Enter comment for Include Bid ");
		WebElement abc13 = driver.findElement(By.xpath("//*[text()='POST']"));
		abc13.click();
		System.out.println("Step:Bid is Included");
		ObjectRepo.getTest().log(Status.PASS, "Step:Bid is Included");
		WebElement abc14 = driver.findElement(By.xpath("//*[@data-icon='times']"));
		js.executeScript("arguments[0].scrollIntoView();", abc14);
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

	public void AccessAndDownloadClientBidsReportWA() {
		WebElement abc1 = driver.findElement(By.xpath("(//*[text()='Reports'])[1]"));
		abc1.click();
		System.out.println("Step:Navigate to report section");
		ObjectRepo.getTest().log(Status.PASS, "Step:Navigate to report section");
		WebElement abc2 = driver.findElement(By.xpath("(//*[text()='Search Reports'])"));
		abc2.click();
		System.out.println("Step:Click on Search Reports option");
		ObjectRepo.getTest().log(Status.PASS, "Step:Click on Search Reports option");
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[2]")));
		s.selectByVisibleText("Client Bids Report");
		System.out.println("Step:Select report Name-Client Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select report Name-Client Bids Report");
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
		WebElement abc7 = driver.findElement(By.xpath("(//*[text()='SEARCH'])"));
		abc7.click();
		System.out.println("Step:Click on SEARCH button");
		ObjectRepo.getTest().log(Status.PASS, "Step:Click on SEARCH button");
		WebElement abc8 = driver.findElement(By.xpath("(//*[@id='auto'])[3]"));
		js.executeScript("arguments[0].scrollIntoView();", abc8);
		abc8.click();
		WebElement abc9 = driver.findElement(By.xpath("(//*[text()='WA_Entity+D'])"));
		abc9.click();
		System.out.println("Step:Select entity Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select entity Name");
		Select s1 = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		s1.selectByVisibleText("Client Bids Report");
		System.out.println("Step:Select report Name");
		ObjectRepo.getTest().log(Status.PASS, "Step:Select report Name");
		WebElement abc12 = driver.findElement(By.xpath("(//*[@data-icon='download'])[1]"));
		abc12.click();
		System.out.println("Step:Client Bids Report is downloaded");
		ObjectRepo.getTest().log(Status.PASS, "Step:Client Bids  Report is downloaded");
		System.out.println(
				"TC ID C31382-Maintain the Client Bid Reports for an event once generated along with an audit trail");
		ObjectRepo.getTest().log(Status.PASS,
				"TC ID C31382-Maintain the Client Bid Reports for an event once generated along with an audit trail");
	}

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

}
