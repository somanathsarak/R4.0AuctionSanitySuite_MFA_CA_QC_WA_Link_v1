package com.wciauction.pageObjects.events;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
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
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.ClassPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.requiredData.UsersData;

import static constants.EventNameConstants.eventNameEng;
import static constants.EventNameConstants.eventNameQC;
import static com.wci.auction.org.resources.BaseClass.prop;

public class CreateEventPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public String randomString;
	public static String newEventName;
	private Actions action;

	public String date1;

	@FindBy(xpath = "//*[@aria-label='firstAuctionCertified']")
	private WebElement auctionCerButton;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithmWA;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement btnRunAlgorithmWA2;

	@FindBy(xpath = "//*[@aria-label='secondAuctionCertified']")
	private WebElement CA2certificationbutton;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement submitBtncertification;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement submitBtncertification2;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement submitBtncertificationsubmitpopup;

	@FindBy(xpath = "(//*[text()='Auction Certification'])[1]")
	private WebElement AuctionCertificationButton;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement btnContineu;

	@FindBy(xpath = "(//*[@name='vintages'])[1]")
	private WebElement dropDownVintage1;

	@FindBy(xpath = "(//*[@name='vintages'])[2]")
	private WebElement dropDownVintage2;

	@FindBy(xpath = "(//*[@name='vintages'])[3]")
	private WebElement dropDownVintage3;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[1]")
	private WebElement textBoxVintage1;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[2]")
	private WebElement textBoxVintage2;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[3]")
	private WebElement textBoxVintage3;

	@FindBy(xpath = "(//select[@name='vintageType'])[1]")
	private WebElement drpdownVintageType1;

	@FindBy(xpath = "(//select[@name='vintageType'])[2]")
	private WebElement drpdownVintageType2;

	@FindBy(xpath = "(//select[@name='vintageType'])[3]")
	private WebElement drpdownVintageType3;

	@FindBy(xpath = "(//select[@name='processingOrder'])[1]")
	private WebElement drpdownProCessOrder1;

	@FindBy(xpath = "(//select[@name='processingOrder'])[2]")
	private WebElement drpdownProCessOrder2;

	@FindBy(xpath = "(//select[@name='processingOrder'])[3]")
	private WebElement drpdownProCessOrder3;

	@FindBy(xpath = "//a[text()='User']")
	private WebElement btnUser;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
	private WebElement btnCreateEvent;

	@FindBy(xpath = "//input[@id='eventId']")
	private WebElement textBoxEventID;

	@FindBy(xpath = "//select[@name='eventType']")
	private WebElement eventTypeDropDown;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventName;

	@FindBy(xpath = "//div[@id='demo-customized-select']")
	private WebElement ParticipatingJurisdictions;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
	private WebElement btnCalifornia;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
	private WebElement btnNovaScotia;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[3]")
	private WebElement btnQuebec;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[4]")
	private WebElement btnWashinton;

	@FindBy(xpath = "//*[@id='eventNameInFrench']")
	private WebElement frenchEventName;

	@FindBy(xpath = "(//div[@id='demo-customized-select'])[2]")
	private WebElement currencyAcc;

	@FindBy(xpath = "(//*[@aria-label='Without label'])[3]")
	private WebElement currencyAccforreservesale;

	@FindBy(xpath = "//li[text()='Select']")
	private WebElement selectText;

	@FindBy(xpath = "((//input[@type=\"checkbox\"]//parent::span)//ancestor::li)[1]")
	private WebElement tagUSD;

	@FindBy(xpath = "((//input[@type=\"checkbox\"]//parent::span)//ancestor::li)[2]")
	private WebElement tagCAD;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement tagCADForNS;

	@FindBy(xpath = "//input[@name='USD']")
	private WebElement textBoxUSD;

	@FindBy(xpath = "//input[@placeholder='USD']")
	private WebElement textBoxUSD1;

	@FindBy(xpath = "//input[@name='CAD']")
	private WebElement textBoxCAD;

	@FindBy(xpath = "(//input[@name='eventBiddingPeriodOpen'])")
	private WebElement dateBiddingPeroidOpen;

	@FindBy(xpath = "//input[@name='eventBiddingPeriodClose']")
	private WebElement dateBiddingPeriodClose;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[6]")
	private WebElement PastdateFinantialSettlement;

	@FindBy(xpath = "//*[@name='eventApplicationOpen']")
	private WebElement dateEventAppOpen;

	@FindBy(xpath = "//input[@name='eventApplicationClose']")
	private WebElement dateEventAppClose;

	@FindBy(xpath = "//input[@name='biddingGuaranteeSubmissionDeadline']")
	private WebElement dateBidGuaranteesubDeadline;

	@FindBy(xpath = "//input[@name='resultsAvailable']")
	private WebElement dateBidResultAvail;

	@FindBy(xpath = "//input[@name='eventClosure']")
	private WebElement dateEventClosure;

	@FindBy(xpath = "//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense']")
	private WebElement PastdateBiddingPeroidOpen;

	@FindBy(xpath = "//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense']")
	private WebElement dateBiddingPeriodCloseError;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[2]")
	private WebElement PastdateBiddingPeriodClose;

	@FindBy(xpath = "//P[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense']")
	private WebElement BeforedateEventAppOpen;

	@FindBy(xpath = "//*[text()='The financial guarantee submission deadline date and time cannot be after the bidding period open date and time.']")
	private WebElement dateBidGuaranteesubDeadlineError;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[3]")
	private WebElement PastdateBidGuaranteesubDeadline;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[4]")
	private WebElement PastdateBidResultAvail;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[5]")
	private WebElement PastdateEventClosure;

	@FindBy(xpath = "//input[@name='financialSettlement']")
	private WebElement dateFinantialSettlement;

	@FindBy(xpath = "//select[@name='algorithm']")
	private WebElement dropdownAlgorithm;

	@FindBy(xpath = "//*[@id='event-list']/table/tbody/tr[1]/td[9]/button")
	private WebElement eventDetailsBtn;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "//span[contains(text(),'Welcome, ')]")
	private WebElement btnProfile;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//*[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement Submittedtext;

	@FindBy(xpath = "//select[@name='tiers']")
	private WebElement tiersDropDown;

	@FindBy(xpath = "//input[@name='tiersPrices']")
	private WebElement texttiersprices;

	@FindBy(xpath = "//input[@name='totalAllowancesAvailablePerTier']")
	private WebElement texttotalallowanceavail;

	@FindBy(xpath = "//select[@name='processingOrder']")
	private WebElement processingOrderDropDown;

	@FindBy(xpath = "//*[text()='Add New']")
	private WebElement btnAddNewTier;

	@FindBy(xpath = "//*[text()='You have been successfully logged out.']")
	private WebElement Logoutmessage;

	@FindBy(xpath = "(//select[@name='tiers'])[2]")
	private WebElement tiersDropDown2;

	@FindBy(xpath = "(//input[@name='tiersPrices'])[2]")
	private WebElement texttiersprices2;

	@FindBy(xpath = "(//input[@name='totalAllowancesAvailablePerTier'])[2]")
	private WebElement texttotalallowanceavail2;

	@FindBy(xpath = "(//select[@name='processingOrder'])[2]")
	private WebElement processingOrderDropDown2;

	@FindBy(xpath = "(//select[@name='tiers'])[3]")
	private WebElement tiersDropDown3;

	@FindBy(xpath = "(//input[@name='tiersPrices'])[3]")
	private WebElement texttiersprices3;

	@FindBy(xpath = "(//input[@name='totalAllowancesAvailablePerTier'])[3]")
	private WebElement texttotalallowanceavail3;

	@FindBy(xpath = "(//select[@name='processingOrder'])[3]")
	private WebElement processingOrderDropDown3;

	@FindBy(xpath = "//input[@name='rollDownProcedure']")
	private WebElement rollDownbids;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement includebids;

	@FindBy(xpath = "//*[text()='The event has been created successfully.']")
	private WebElement successmsg;

	@FindBy(xpath = "(//*[text()='Bidding Results'])[1]")
	private WebElement btnBiddingResult;

	@FindBy(xpath = "//a[@href='/auction/events/']")
	private WebElement btnGoBackToEvent;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithm1;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement btnRunAlgorithm2;

	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement rowsPerPage;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement rowsPerPage500;

	@FindBy(xpath = "//*[text()='Add New']")
	private WebElement addNewVintage;

	@FindBy(xpath = "//input[@name='resultsAvailable']")
	private WebElement resultsAvailable;

	@FindBy(xpath = "//input[@name='biddersConferenceDateTime']")
	private WebElement bidderConference;

	@FindBy(xpath = "//input[@name='financialSettlement']")
	private WebElement financialSettlementDateTime;

	@FindBy(xpath = "//span[text()='Edit Event']")
	private WebElement editEvent;

	@FindBy(xpath = "//button[@title='Go to details of Event R255-SBMA']")
	private WebElement biddingPeriodOpeneventDetailsButton;

	@FindBy(xpath = "//h1[text()='R255-SBMA']")
	private WebElement editedOpenEventText;

	@FindBy(xpath = "//button[@title='Go to details of Event FirstEvent']")
	private WebElement biddingPeriodClosedeventDetailsButton;

	@FindBy(xpath = "//h1[text()='FirstEvent']")
	private WebElement editedClosedEventText;

	@FindBy(xpath = "//button[@title='Go to details of Event Open Application Period']")
	private WebElement applicationPeriodOpendeventDetailsButton;

	@FindBy(xpath = "//h1[text()='Open Application Period']")
	private WebElement editedApplicationOpenEventText;

	@FindBy(xpath = "//button[@title='Go to details of Event Application Period Closed']")
	private WebElement applicationPeriodClosedEventDetailsButton;

	@FindBy(xpath = "//h1[text()='Application Period Closed']")
	private WebElement editedApplicationClosedEventText;

	@FindBy(xpath = "(//button[@title='Go to details of Event ThirdEvent'])[2]")
	private WebElement financialStatementDateCloseddEventDetailsButton;

	@FindBy(xpath = "//h1[text()='ThirdEvent']")
	private WebElement editedFinancialStatementDateClosedEventText;

	@FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
	private WebElement createNewEventButton;

	@FindBy(xpath = "//h1[text()='Event Creation']")
	private WebElement eventCreatePage;

	@FindBy(xpath = "//h1[text()='Event Administration']")
	private WebElement eventAdminPage;

	@FindBy(xpath = "//h2[text()='List of Events']")
	private WebElement listOfEventPage;

	@FindBy(xpath = "//option[text()='Sale by Mutual Agreement']")
	private WebElement eventTypeSBMA;

	@FindBy(xpath = "(//select[@name='tiers'])[1]")
	private WebElement tiersDropDown1;

	@FindBy(xpath = "//p[text()='Category Price should be greater than zero.']")
	private WebElement categoryPriceError;

	@FindBy(xpath = "//p[text()='Total Emission Units Available should be greater than zero.']")
	private WebElement emissionUnitsError;

	@FindBy(xpath = "//button[@title='Go to details of Event Results Available Date Closed']")
	private WebElement resultsAvailableDateClosedEventDetailsButton;

	@FindBy(xpath = "//h1[text()='Results Available Date Closed']")
	private WebElement editedresultsAvailableDateClosedEventText;

	@FindBy(xpath = "//button[@title='Go to details of Event SBMA 260']")
	private WebElement closedEventDetailsButton;

	@FindBy(xpath = "//h1[text()='SBMA 260']")
	private WebElement editedEventClosureText;

	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement btnCancel;

	@FindBy(xpath = "//span[text()='CLEAR FORM']")
	private WebElement btnClear;

	@FindBy(xpath = "//span[text()='CLEAR']")
	private WebElement btnClearSBA;

	@FindBy(xpath = "//button[@aria-label='search']")
	private WebElement btnSearch;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement statusCount;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[2]")
	private WebElement governmentCount;

	@FindBy(xpath = "//select[@id='age-native-simple']")
	private WebElement roleCount;

	@FindBy(xpath = "//input[@placeholder='Enter your search terms']")
	private WebElement enterSearchInput;

	@FindBy(xpath = "//span[text()='Reset filters']")
	private WebElement btnResetSearch;

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement btnHome;

	@FindBy(xpath = "//span[text()='EVENT TYPE']")
	private WebElement eventTypeColumn;

	@FindBy(xpath = "//span[text()='EVENT ID']")
	private WebElement eventIDColumn;

	@FindBy(xpath = "//span[text()='EVENT NAME']")
	private WebElement eventNameColumn;

	@FindBy(xpath = "//span[text()='EVENT DATE']")
	private WebElement eventDateColumn;

	@FindBy(xpath = "//th[text()='GOV']")
	private WebElement participatingJuriColumn;

	@FindBy(xpath = "//span[text()='CREATED BY']")
	private WebElement createdByColumn;

	@FindBy(xpath = "//span[text()='CREATION DATE']")
	private WebElement creationDateColumn;

	@FindBy(xpath = "//span[text()='EVENT STATUS']")
	private WebElement eventStatusColumn;

	@FindBy(xpath = "//input[@name='priceCeiling']")
	private WebElement priceCeiling;

	@FindBy(xpath = "//p[text()='The rejection of the event creation has been proposed by the first Sponsor.']")
	private WebElement disapprovalPopupMessage;

	@FindBy(xpath = "//p[text()='The creation of the event has been approved by both Sponsors.']")
	private WebElement approvalPopupMessage;

	@FindBy(xpath = "(//td[text()='Saved'])[1]")
	private WebElement eventStatus;

	@FindBy(xpath = "//input[@name='biddersConferenceDateTime']")
	private WebElement biddersConferenceDateTime;

	@FindBy(xpath = "(//input[@name='biddersConferenceDateTime'])[2]")
	private WebElement biddersConferenceDateTime1;

	@FindBy(xpath = "(//input[@name='biddersConferenceDateTime'])[3]")
	private WebElement biddersConferenceDateTime2;

	@FindBy(xpath = "//button[text()='Add Another Conference']")
	private WebElement addAnotherConference;

	@FindBy(xpath = "//label[text()='Bidders Conference']")
	private WebElement textBidderConference;

	@FindBy(xpath = "//th[text()='Webinar']")
	private WebElement webinar;

	@FindBy(xpath = "//div[text()='05 Sep 2022']")
	private WebElement webinarDate1;

	@FindBy(xpath = "//div[text()='10 Sep 2022']")
	private WebElement webinarDate2;

	@FindBy(xpath = "//div[text()='15 Sep 2022']")
	private WebElement webinarDate3;

	@FindBy(xpath = "//h2[text()='Upcoming Events']")
	private WebElement upcomingEventsText;

	@FindBy(xpath = "//p[text()='Category A']")
	private WebElement categoryA;

	@FindBy(xpath = "//p[text()='Category B']")
	private WebElement categoryB;

	@FindBy(xpath = "//p[text()='Category C']")
	private WebElement categoryC;

	@FindBy(xpath = "(//span[text()='CAD 100.00'])[1]")
	private WebElement categoryAValue;

	@FindBy(xpath = "(//span[text()='CAD 100.00'])[2]")
	private WebElement categoryBValue;

	@FindBy(xpath = "(//span[text()='CAD 100.00'])[3]")
	private WebElement categoryCValue;

	@FindBy(xpath = "//span[text()='Day']")
	private WebElement dayText;

	@FindBy(xpath = "//span[text()='Hours']")
	private WebElement hoursText;

	@FindBy(xpath = "//span[text()='Mins']")
	private WebElement minsText;

	@FindBy(xpath = "//th[text()='Application Period']")
	private WebElement applicatioPeriodText;

	@FindBy(xpath = "//th[text()='Results Published']")
	private WebElement resultsPublishedText;

	@FindBy(xpath = "//th[text()='Webinar']")
	private WebElement webinarTextText;

	@FindBy(xpath = "(//span[@aria-disabled='true'])[1]")
	private WebElement chkbxChangeAnnualCategoryPrice;

	@FindBy(xpath = "//a[@title='Home']")
	private WebElement homeMenu;

	@FindBy(xpath = "//p[text()='Annual Category Prices']")
	private WebElement annualCategoryPriceHomePage;

	@FindBy(xpath = "//p[text()='Category Prices']")
	private WebElement categoryPriceHomePage;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement chkbxChangeAnnualCategoryPriceOnEdit;

	@FindBy(xpath = "//input[@id='eventId']")
	private WebElement enterEventID;

	@FindBy(xpath = "//option[text()='Auction']")
	private WebElement eventTypeAuction;

	@FindBy(xpath = "//label[text()='Emission Units per Lot']")
	private WebElement emissionPerLot;

	@FindBy(xpath = "//input[@name='allowancesPerBidLot']")
	private WebElement defaultEmissionPerLot;

	@FindBy(xpath = "//*[@data-icon='minus']")
	private WebElement btnMinus;

	@FindBy(xpath = "(//*[@data-icon='minus'])[2]")
	private WebElement btnMinus1;

	@FindBy(xpath = "//span//*[@data-icon='plus']")
	private WebElement btnPlus;

	@FindBy(xpath = "(//span//*[@data-icon='plus'])[2]")
	private WebElement btnPlus1;

	@FindBy(xpath = "//label[text()='Limit Maximum Bids Allowed']")
	private WebElement limitMaxBidAllowed;

	@FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
	private WebElement isLimitMaxBidAllowedRadioBtn;

	@FindBy(xpath = "//input[@name='limitMaximumBidsAllowed']")
	private WebElement defaultLimitMaxBidAllowed;

	@FindBy(xpath = "//*[text()='The event has been edited successfully.']")
	private WebElement successEditMessage;

	@FindBy(xpath = "//button[@title='Go to details of Event SBMA 2.0 Upload Emission Units File']")
	private WebElement emissionUnitsUploadEventDetailsButton;

	@FindBy(xpath = "//input[@accept='.xls, .xlsx']")
	private WebElement uploadEmissionUnitsFile;

	@FindBy(xpath = "//button[@title='Go to details of Event Event needs dual sponsor approval']")
	private WebElement dualApprovalOnEditEventDetailsButton;

	@FindBy(xpath = "//input[@name='eventApplicationClose']")
	private WebElement eventApplicationCloseDate;

	@FindBy(xpath = "//button[@title='Monitoring']")
	private WebElement btnMonotoring;

	@FindBy(xpath = "//a[text()='SBMA 3']")
	private WebElement selectSBMA3EventFromEventDropdown;

	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement selectNumberOfRows;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement select500Rows;

	@FindBy(xpath = "(//tr[@role='checkbox'])[1]")
	private WebElement firstBidAssert;

	@FindBy(xpath = "//input[@id='transaction-id']")
	private WebElement searchByTransactionID;

	@FindBy(xpath = "//p[text()='The rejection of the event creation has been proposed by the first sponsor.']")
	private WebElement firstAppDis;

	@FindBy(xpath = "//p[text()='The approval of the event creation has been proposed by the second sponsor.']")
	private WebElement secondAppSuccess;

	@FindBy(xpath = "//p[text()='The rejection of the event creation has been proposed by the second sponsor.']")
	private WebElement secondAppDis;

	@FindBy(xpath = "//button[@aria-label='approval2Status']")
	private WebElement btnSecondApprovalCal;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")
	private WebElement btnApproveQue;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[3]")
	private WebElement btnApproveJuri;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnApproveCal;

	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement submitApprove;

	@FindBy(xpath = "//input[@name='allowancesPerBidLot']")
	private WebElement allowancesPerBidLotEdit;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-7']")
	private WebElement allowancesPerBidLotEditValue;

	@FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
	private WebElement radioLimitBidAllowedEdit;

	@FindBy(xpath = "//input[@name='limitMaximumBidsAllowed']")
	private WebElement defaultMaxBidAllowedEdit;

	@FindBy(xpath = "(//input[@name='limitMaximumBidsAllowed'])[1]")
	private WebElement defaultMaxBidAllowed;

	@FindBy(xpath = "(//input[@name='limitMaximumBidsAllowed'])[2]")
	private WebElement defaultMaxBidAllowed2;

	@FindBy(xpath = "(//input[@name='limitMaximumBidsAllowed'])[3]")
	private WebElement defaultMaxBidAllowed3;

	@FindBy(xpath = "//input[@name='isRollDownBids']")
	private WebElement radiobtnRollDownBidEdit;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement radiobtnCombinedEvaluationEdit;

	@FindBy(xpath = "//input[@name='processingOrder']")
	private WebElement radiobtnProcessingOrderEdit;

	@FindBy(xpath = "//input[@name='allowancesPerBidLot']")
	private WebElement textPerLotEdit;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement textCombinedEvaluationEdit;

	@FindBy(xpath = "//input[@name='isRollDownBids']")
	private WebElement textRollDownBidsEdit;

	@FindBy(xpath = "//input[@name='processingOrder']")
	private WebElement textProcessingOrderEdit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[1]")
	private WebElement textProcessingOrderValue;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[2]")
	private WebElement textMaxBidAllowedEdit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[3]")
	private WebElement textPerLotValue;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[4]")
	private WebElement textCombinedEvaluationValue;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[5]")
	private WebElement textMaxBidAllowed2Edit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[6]")
	private WebElement textMaxBidAllowedValue;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[7]")
	private WebElement textPerLotValue2;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[8]")
	private WebElement textCombinedEvaluationValue2;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[9]")
	private WebElement textMaxBidAllowed3Edit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[10]")
	private WebElement textMaxBidAllowedValue2;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[11]")
	private WebElement textPerLotValue3;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[12]")
	private WebElement textCombinedEvaluationValue3;

	@FindBy(xpath = "//*[text()='SAVE']")
	private WebElement btnSave;

	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement btnCancelEdit;

	@FindBy(xpath = "//p[text()='The event has been successfully edited.']")
	private WebElement successMessage;

	@FindBy(xpath = "//p[text()='Category Price should be greater than zero.']")
	private WebElement categoryPriceErrMsg;

	@FindBy(xpath = "//p[text()='Total Emission Units Available should be greater than zero.']")
	private WebElement emissionUnitsErrMsg;

	@FindBy(xpath = "//p[text()='Category Price should be greater than zero.']")
	private WebElement categoryPriceErrorMsg;

	@FindBy(xpath = "//p[text()='Total Emission Units Available should be greater than zero.']")
	private WebElement emissionUnitsErrorMsg;

	@FindBy(xpath = "//h1[text()='Event Administration']")
	private WebElement eventAdminHeading;

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeLink;

	@FindBy(xpath = "//span[text()='Event ID']")
	private WebElement eventIDText;

	@FindBy(xpath = "//span[text()='Event Type']")
	private WebElement eventTypeText;

	@FindBy(xpath = "//span[text()='Event Name']")
	private WebElement eventNameText;

	@FindBy(xpath = "//span[text()='Event Date']")
	private WebElement eventDateText;

	@FindBy(xpath = "//span[text()='Event Status']")
	private WebElement eventStatusText;

	@FindBy(xpath = "//span[text()='Participant Jurisdiction']")
	private WebElement participatingJuriText;

	@FindBy(xpath = "//span[text()='Created By']")
	private WebElement createdByText;

	@FindBy(xpath = "//span[text()='Creation Date']")
	private WebElement creationDateText;

	@FindBy(xpath = "//span[text()='Created By']")
	private WebElement btnCreatedBy;

	@FindBy(xpath = "//span[text()='Creation Date']")
	private WebElement btnCreationDate;

	@FindBy(xpath = "//input[@name='processingOrder']")
	private WebElement processingOrderEdit;

	@FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
	private WebElement limitMaxBidAllowedEdit;

	@FindBy(xpath = "//input[@name='limitMaximumBidsAllowed']")
	private WebElement maxBidsAllowedEdit;

	@FindBy(xpath = "//input[@name='allowancesPerBidLot']")
	private WebElement allowancesPerBidLotEditField;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement combinedEvaluationEdit;

	@FindBy(xpath = "//input[@name='isRollDownBids']")
	private WebElement rollDownBidsEdit;

	@FindBy(xpath = "//input[@id='eventJurisdiction']")
	private WebElement participatingJuriEdit;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventNameEnglishEdit;

	@FindBy(xpath = "//input[@id='eventNameInFrench']")
	private WebElement eventNameFrenchEdit;

	@FindBy(xpath = "//input[@name='categoryPrices']")
	private WebElement categoryPriceEditField;

	@FindBy(xpath = "//input[@name='totalEmissionUnitsAvailable']")
	private WebElement totalEmissionUnitsEditField;

	@FindBy(xpath = "//select[@name='tiers']")
	private WebElement tiersDropdownEdit;

	@FindBy(xpath = "(//select[contains(@class,'MuiNativeSelect-root')])[2]")
	private WebElement tiersDropdownEditField;

	@FindBy(xpath = "(//select[contains(@class,'MuiNativeSelect-root')])[3]")
	private WebElement tiersDropdown2EditField;

	@FindBy(xpath = "(//select[contains(@class,'MuiNativeSelect-root')])[4]")
	private WebElement tiersDropdown3EditField;

	@FindBy(xpath = "(//input[contains(@placeholder,'Enter')])[1]")
	private WebElement priceCeilingEdit;

	@FindBy(xpath = "//input[@name='customLimit']")
	private WebElement totalMaximumBidLimitperEntityEdit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[2]")
	private WebElement totalMaximumBidLimitperEntity2Edit;

	@FindBy(xpath = "(//input[@placeholder='Enter'])[3]")
	private WebElement totalMaximumBidLimitperEntity3Edit;

	@FindBy(xpath = "//input[@name='transactionFee']")
	private WebElement transactionFeeEdit;

	@FindBy(xpath = "(//input[@name='transactionFee'])[2]")
	private WebElement transaction;

	private By eventDetailsButton = By.xpath("(//*[@title='Go to details of Event " + eventNameEng + "'])[1]");
	private By eventDetailsBtnCA2 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName") + "'])[1]");
	private By eventDetailsBtnQCR70 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name_RE1") + "'])[1]");
	private By eventDetailsBtnWA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameWS") + "'])[1]");
	private By eventDetailsBtnCA_sinle_Auction = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameCA") + "'])[1]");
	private By eventDetailsBtnQC_sinle_Auction = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameQC") + "'])[1]");

	// Concatenated XPaths
	private By eventDetailsBtnRS_WA = By.xpath(
			"(//button[@aria-label='Go to details of Event " + prop.getProperty("ReserveSaleEventName_WA") + "'])[1]");
	private By eventDetailsBtnForQC = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_NameForQC") + "'])[1]");
	private By currentEvent = By.xpath("//*[@aria-label='Go to details of Event " + eventNameEng + "']");

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement buttonSearch;

	@FindBy(xpath = "//span[text()='RESET']")
	private WebElement buttonReset;

	@FindBy(xpath = "//h1[text()='FirstEvent']")
	private WebElement nameOfEventOpenedBySponsor;

	@FindBy(xpath = "//*[text()='Auction']")
	private WebElement eventType;

	@FindBy(xpath = "//*[text()='Quebec']")
	private WebElement eventJurisdiction;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnfirstReject;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//button[@aria-label='approval2Status']")
	private WebElement btnSecondApproval;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnRejectApproval;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//button[@title='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//span[text()='REJECT']")
	private WebElement rejectPopup;

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

	@FindBy(xpath = "//a[text()='Contact Us']")
	private WebElement contactMenu;

	@FindBy(xpath = "//*[@placeholder='Enter your full name']")
	private WebElement nameField;

	@FindBy(xpath = "//*[text()='About WCI, Inc.']")
	private WebElement scroll_To_Bottom;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	private WebElement emailField;

	@FindBy(xpath = "//*[@id='tinymce']")
	private WebElement message;

	@FindBy(xpath = "//span[text()='Send']")
	private WebElement send;

	@FindBy(xpath = "//*[text()='Thank you for your inquiry.']")
	private WebElement getSuccessMsg;

	@FindBy(xpath = "//*[text()='This field is required' and @class='MuiFormHelperText-root Mui-error']")
	private WebElement errorMsg;

	@FindBy(xpath = "//input[@placeholder='Enter your full name']")
	private WebElement name;

	@FindBy(xpath = "//input[@placeholder='Enter your phone with country code']")
	private WebElement phone;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	private WebElement email;

	@FindBy(xpath = "//*[text()='DISCARD']")
	private WebElement discard;

	@FindBy(xpath = "//*[text()='Are you sure you want to discard the information?']")
	private WebElement popUpMsg;

	@FindBy(xpath = "//*[text()='Continue']")
	private WebElement continueBtn;

	@FindBy(xpath = "//*[text()='Event ID']")
	private WebElement eventId;

	@FindBy(xpath = "//*[text()='Participating Jurisdictions']")
	private WebElement partJurdtn;

	@FindBy(xpath = "//*[text()='Event Name - English']")
	private WebElement evntNmeEN;

	@FindBy(xpath = "//*[text()='Event Name - French']")
	private WebElement evntNmeFR;

	@FindBy(xpath = "//option[text()='California']")
	private WebElement participateJurisdictionCAReservesale;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[3]")
	private WebElement processingOrderDltBtn3;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")
	private WebElement processingOrderDltBtn2;

	@FindBy(xpath = "//label[text()='Allowances Per Bid Lot']")
	private WebElement allowancesPerBidLot;

	@FindBy(xpath = "(//button[@type='button'])[6]")
	private WebElement allowancesPerBidLotPls;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	private WebElement allowancesPerBidLotMns;

	@FindBy(xpath = "//label[text()='Limit Maximum Bids Allowed']")
	private WebElement limitMaximumBidAllowed;

	@FindBy(xpath = "(//button[@type='button'])[8]")
	private WebElement limitMaximumBidAllowedPlus;

	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement limitMaximumBidAllowedMinus;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[1]")
	private WebElement limitMaximumBidAllowedRadioBtn;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1'])[2]")
	private WebElement eventID;

	@FindBy(xpath = "(//*[@focusable='false'])[5]")
	private WebElement btnRejectApproveCal;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnRejectApproveQue;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnRejectApprovalFirst;

	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement RejbtnSubmit2;

	@FindBy(xpath = "//*[@name='approval2Status']")
	private WebElement btnRejectApproval2;

	@FindBy(xpath = "//*[@name='approval1Status']")
	private WebElement btnRejectApprovalRet;

	@FindBy(xpath = "//label[text()='Event ID']")
	private WebElement detailsEventID;

	@FindBy(xpath = "//label[text()='Event Type']")
	private WebElement detailsEventType;

	@FindBy(xpath = "//label[text()='Participating Jurisdictions']")
	private WebElement detailsEventPJs;

	@FindBy(xpath = "//label[text()='Event Name - English']")
	private WebElement detailsEventNameEng;

	@FindBy(xpath = "//label[text()='Event Name - French']")
	private WebElement detailsEventNameFrench;

	@FindBy(xpath = "//input[@id='eventNameInFrench']")
	private WebElement eventNameFrench;

	@FindBy(xpath = "//label[text()='Participating Jurisdictions']")
	private WebElement PJs;

	@FindBy(xpath = "//option[text()='Nova Scotia']")
	private WebElement textPJs;

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[3]")
	private WebElement currencyAccWA;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[4]")
	private WebElement dropdowntiersWA;

	@FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[6]")
	private WebElement dropdowntiers2WA;

	@FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[8]")
	private WebElement dropdowntiers3WA;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[4]")
	private WebElement texttierspriceWA;

	@FindBy(xpath = "(//*[@placeholder=\"Enter\"])[6]")
	private WebElement texttiersprice2WA;

	@FindBy(xpath = "(//*[@placeholder=\"Enter\"])[8]")
	private WebElement texttiersprice3WA;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[10]")
	private WebElement entertiersprice3WA;

	@FindBy(xpath = "(//*[@placeholder=\"Enter\"])[9]")
	private WebElement texttotalallowanceWA3;

	@FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[5]")
	private WebElement texttotalallowanceWA;

	@FindBy(xpath = "(//input[@type='checkbox'])[1]")
	private WebElement btnLimitTotalAllowanceBidperEntity;

	@FindBy(xpath = "(//input[@name='customLimit'])[1]")
	private WebElement textTotalMaximumBidLimitperEntity;

	@FindBy(xpath = "(//*[@placeholder=\"Enter\"])[7]")
	private WebElement texttotalallowance2WA;

	@FindBy(xpath = "(//input[contains(@class,'MuiInputBase-input MuiInput-input')])[9]")
	private WebElement texttotalallowance3WA;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement btnLimitTotalAllowanceBidperEntity2WA;

	@FindBy(xpath = "(//input[@name='customLimit'])[2]")
	private WebElement textTotalMaximumBidLimitperEntity2WA;

	@FindBy(xpath = "(//input[@type='checkbox'])[3]")
	private WebElement btnLimitTotalAllowanceBidperEntity3WA;

	@FindBy(xpath = "(//input[@name='customLimit'])[3]")
	private WebElement textTotalMaximumBidLimitperEntity3WA;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[1]")
	private WebElement deletecategory1;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")
	private WebElement deletecategory2;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[1]")
	private WebElement errmsgcategory;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[1]")
	private WebElement errorallowance;

	@FindBy(xpath = "(//button[contains(@color,'primary')])[1]")
	private WebElement btnaddnew;

	@FindBy(xpath = "(//button[@type='button'])[6]")
	private WebElement IconDelTierRow;

	@FindBy(xpath = "(//select[@name='processingOrder'])[1]")
	private WebElement drpdownProCessOrder1WA;

	@FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[7]")
	private WebElement drpdownProCessOrder2WA;

	@FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[9]")
	private WebElement drpdownProCessOrder3WA;

	@FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
	private WebElement radiobtnLmtMaxBid;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[2])[2]")
	private WebElement btnPlusLimitMxBid;

	@FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
	private WebElement textLimitMxBid;

	@FindBy(xpath = "//input[@name='limitMaximumBidsAllowed']")
	private WebElement bydefaultLmtMaxBid;

	@FindBy(xpath = "//input[@name='allowancesPerBidLot']")
	private WebElement AllowancesPerBidLot;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[1])")
	private WebElement AllowancesPerBidLot1;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[2])")
	private WebElement AllowancesPerBidLot2;

	@FindBy(xpath = "//*[text()='Rolls down bids from higher price tier if lower price tier is undersubscribed']")
	private WebElement radioRollDown1;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement radiobtnCombnedEvaluation;

	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement btncancel;

	@FindBy(xpath = "//span[text()='CLEAR']")
	private WebElement btnclear;

	@FindBy(xpath = "//p[contains(text(),'Enter Event ID')]")
	private WebElement eventiderrormsg;

	@FindBy(xpath = "//h1[text()='Event Administration']")
	private WebElement eventadminpage;

	@FindBy(xpath = "(//button[@aria-label='Select number of rows per page.'])")
	private WebElement btngear;

	@FindBy(xpath = "(//li[text()='500 '])")
	private WebElement btngear500;

	@FindBy(xpath = "//tr[contains(@role,'checkbox')]")
	private List<WebElement> totalCountOfEvents;

	@FindBy(xpath = "//span[text()='EVENT TYPE']")
	private WebElement MenuBarEventType;

	@FindBy(xpath = "//span[text()='EVENT ID']")
	private WebElement MenuBarEventID;

	@FindBy(xpath = "//span[text()='EVENT NAME']")
	private WebElement MenuBarEventName;

	@FindBy(xpath = "//span[text()='EVENT DATE']")
	private WebElement MenuBarEventDate;

	@FindBy(xpath = "//th[text()='PJs']")
	private WebElement MenuBarPJs;

	@FindBy(xpath = "//span[text()='CREATION DATE']")
	private WebElement MenuBarCreationDate;

	@FindBy(xpath = "//span[text()='EVENT STATUS']")
	private WebElement MenuBarEventStatus;

	@FindBy(xpath = "//button[@aria-label='approval1Status']//span[@class='MuiIconButton-label']//*[name()='svg'][2]")
	private WebElement btnFirstApproval;

	@FindBy(xpath = "//span[text()='REJECT']")
	private WebElement rejectBtn;

	@FindBy(xpath = "//button[@aria-label='approval1Status']//span[@class='MuiIconButton-label']//*[name()='svg'][1]")
	private WebElement btnfirstDisApproval;

	@FindBy(xpath = "//button[@aria-label='approval2Status']//span[@class='MuiIconButton-label']//*[name()='svg'][1]")
	private WebElement btnSecondDisApproval;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root']")
	private WebElement closePopupEditEvent;

	@FindBy(xpath = "//p[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement firstAppSuccess;

	@FindBy(xpath = "//span[text()='APPROVED']")
	private WebElement eventAppStatus;

	@FindBy(xpath = "//p[text()='The event status has been approved.']")
	private WebElement alertEventStatus;

	@FindBy(xpath = "//p[text()='The rejection of the event creation has been proposed by the first Sponsor.']")
	private WebElement alertDisputPopup;

	@FindBy(xpath = "//p[text() = 'The creation of the event has been rejected by both Sponsors.']")
	private WebElement eventDisapprovalStatus;

	@FindBy(xpath = "//span[text()='Edit Event']")
	private WebElement btnEditEvent;

	@FindBy(xpath = "//p[text()='The event has been edited successfully.']")
	private WebElement alertEditEventSucess;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[1]")
	private WebElement validatetier1Allowances;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[2]")
	private WebElement validatetier2Allowances;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[3]")
	private WebElement validatetier3Allowances;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]")
	private WebElement validateTime;

	@FindBy(xpath = "//span[text()='Events']")
	private WebElement btnEventARLogin;

	@FindBy(xpath = "//a[text()='R232']")
	private WebElement selectEvent;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-7']")
	private WebElement bidGuaranteeAmt;

	public CreateEventPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void AuctionCertificationButtonca() throws InterruptedException {

		AuctionCertificationButton.click();
		WebElement element111 = auctionCerButton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		Thread.sleep(7000);
	}

	public void AuctionCertificationButtonCA2() throws InterruptedException {
		AuctionCertificationButton.click();
		WebElement element111 = CA2certificationbutton;
		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification2.click();
		submitBtncertificationsubmitpopup.click();
		Thread.sleep(10000);
	}

	public void runAlgorithm() throws InterruptedException {
		Thread.sleep(10000);
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA));
			btnRunAlgorithmWA.click();
			Thread.sleep(15000);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA2));
			btnRunAlgorithmWA2.click();
			Thread.sleep(15000);
		}
	}

	public void runAlgorithmWA() throws InterruptedException {
		Thread.sleep(7000);
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA));
			btnRunAlgorithmWA.click();
			Thread.sleep(10000);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA2));
			btnRunAlgorithmWA2.click();
			Thread.sleep(10000);
		}
	}

	public void runAlgorithmWA2() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA));
			btnRunAlgorithmWA.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA2));
			btnRunAlgorithmWA2.click();
		}
		wait.until(ExpectedConditions.visibilityOf(btnContineu));
		btnContineu.click();
		Thread.sleep(10000);
	}

	public void runAlgorithm2() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA));
			btnRunAlgorithmWA.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRunAlgorithmWA2));
			btnRunAlgorithmWA2.click();
		}
		wait.until(ExpectedConditions.visibilityOf(btnContineu));
		btnContineu.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//		WebElement element1 = btnRunAlgorithm2;
//		js.executeScript("arguments[0].scrollIntoView();", element1);
//		element1.click();
//		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
//	}

	public void selectEventTypeFromDropDown1() {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		try {
			s.selectByVisibleText("Sale by Mutual Agreement");
		} catch (Exception e) {
			e.printStackTrace();
			s.selectByVisibleText("Sale by Agreement");
		}
	}

	public void selectTiers1_New() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier One");
		} catch (Exception e) {
			s.selectByVisibleText("Category A");
		}
		texttiersprices.sendKeys("100");
		texttotalallowanceavail.sendKeys("100000");
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);

		s1.selectByVisibleText("1");
	}

	public void selectTiers2_New() {
		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Two");
		} catch (Exception e) {
			s.selectByVisibleText("Category B");
		}
		texttiersprices2.sendKeys("100");
		texttotalallowanceavail2.sendKeys("100000");
		WebElement element2 = processingOrderDropDown2;
		Select s2 = new Select(element2);

		s2.selectByVisibleText("2");
	}

	public void getCurrentEvent() {

		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(currentEvent);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(currentEvent).click();
	}

	public void getCurrentEventWA() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnWA);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnWA).click();
	}

	public void getCurrentEventCA_Single_Auction() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnCA_sinle_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnCA_sinle_Auction).click();
	}

	public void getCurrentEventQC_Single_Auction() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnQC_sinle_Auction);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnQC_sinle_Auction).click();
	}

	public void getCurrentEventForResSaleCal() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnCA2);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnCA2).click();
	}

	public void getCurrentEventForResSaleQCR70() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnQCR70);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnQCR70).click();
	}

	public void getCurrentEventForResSale_WA() {
		WebElement element1 = driver.findElement(eventDetailsBtnRS_WA);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnRS_WA).click();
	}

	public void getCurrentEventForIndividualAuctionCal() {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement element1 = driver.findElement(eventDetailsBtnCA2);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnCA2).click();
	}

	public void generateString() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomString = ("Event_" + generatedString);
	}

	public void createEventForQue() throws IOException {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdictionForQue();
		currencyAccForQue();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventForJointAuction() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdictionForJointAuction();
		currencyAccForjointAuction();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventJointAuctionForARTestcases() throws IOException {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();

		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdictionForJointAuction();
		// EVENT DETAILS
		currencyAccForjointAuction();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventForCal() throws IOException {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventforsingleAuction() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForQC();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForSingleAuctionWasington();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventForReserveSaleForCAforTC() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();

		generateString();
		gettextBoxEventForCA();
		selectReserveSaleEventTypeforCAFromDropDown();
		enterEventNameForCA();
		enterfrenchEventName();
		currencyAccForReserveSaleForCA();
		selectTiers1();
		selectTiers2();
		selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDatesReservesaleCA1();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");

	}

	public void createEventForReserveSaleForCA() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForCA1();
		selectReserveSaleEventTypeforCAFromDropDown();
		enterEventNameForCA2();
		enterfrenchEventNameForCalReserveSale();
		currencyAccForReserveSaleForCA();
		selectTiers1();
		selectTiers2();
		selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", btnSave);
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", btnSave);
		} catch (Exception e) {
			actions.moveToElement(btnSave).perform();
			Thread.sleep(2000);
			actions.click(btnSave).perform();
		}
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");

	}

	public void createEventForReserveSaleForCA2() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForCA1();
		selectReserveSaleEventTypeforCAFromDropDown();
		enterEventNameForCA3();
		enterfrenchEventNameForCalReserveSale();
		currencyAccForReserveSaleForCA();
		selectTiers1();
		selectTiers2();
		selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDatesReservesaleCA1();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");

	}

	public void createEventForReserveSaleForWA() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForWA();
		selectReserveSaleEventTypeforWAFromDropDown();
		enterEventNameForWA_RS();
		enterfrenchEventNameForWAReserveSale();
		currencyAccForReserveSaleForCA();
		selectTiers1RS_WA();
		selectTiers2RS_WA();
		selectTiers3RS_WA();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.CONTROL + "A");
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys("999");
		try {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		} catch (Exception e) {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		}
		js.executeScript("arguments[0].scrollIntoView();", btnSave);
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");

	}

	public void createEventForReserveSaleForQC() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		gettextBoxEventForQC();
		selectReserveSaleEventTypeforQCFromDropDown();
		enterEventNameForQC();
		enterfrenchEventName();
		currencyAccForReserveSaleForQC();
		selectTiers1();
		selectTiers2();
		selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		WebElement element = driver.findElement(By.xpath("//*[text()='About WCI, Inc.']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		js.executeScript("arguments[0].scrollIntoView();", btnSave);
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
	}

	public void createEventforsingleAuctionNovaScotia() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdictionForSingleAuction();
		currencyAccForSingleAuctionNovaScotia();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectDates();
		selectAlgorithm();
	}

	public void createEventwithPastDatesForApplication() throws IOException {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectPastDatesForApplication();
		selectAlgorithm();
	}

	public void createEventwithPastDatesForApplication1() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		selectPastDatesForApplication1();
		// selectPastDatesForApplication2();
		// selectDates();
		// selectAlgorithm();
	}

	public void createEventwithMismatchOpenCloseDatesForApplication() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		ApplicationCloseDateandTimeAreBeforeTheApplicationOpenDateandTime();
	}

	public void createEventwithMismatchOpenCloseBiddingDatesForApplication() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		BiddingPeriodCloseDateandTimeAreBeforeTheBiddingOpenDateandTime();
	}

	public void createEventwithMismatchBidGauranteeandBiddingOpenDatesForApplication() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		selectEventTypeFromDropDown();
		enterEventName();
		selectJurisdiction();
		currencyAccForCal();
		selectVintage();
		selectVintageType();
		selectProcessingOrder();
		BidGuaranteeSubmissionDeadlineDateandTimeAfterTheBiddingPeriodOpenDateandTime();
	}

	public void CaliforniaJuridictionApprovalForEventCreation() {
		// wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));

		try {
			ExpectedConditions.elementToBeClickable(eventDetailsBtn);
			eventDetailsBtn.click();
		} catch (Exception e) {
			eventDetailsBtn.click();
		}

//		WebElement element1 = eventDetailsBtn;
//		js.executeScript("arguments[0].scrollIntoView();", element1);
//		eventDetailsBtn.click();
		wait.until(ExpectedConditions.visibilityOf(btnApproveCal));
		btnApproveCal.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(Submittedtext));
		String text = Submittedtext.getText();
		Assert.assertEquals(text, "The approval of the event creation has been proposed by the first sponsor.");
		System.out.println("First Sponsor approved succesfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by california Sponsor");
		driver.navigate().refresh();
		btnProfile.click();
		btnLogout.click();
		ObjectRepo.getTest().log(Status.PASS, "California Sponsor logout Successfully");
		System.out.println("California Sponsor logout Successfully");
	}

	public void quebecJuridictionApprovalForEventCreation() {
		WebElement element1 = driver.findElement(eventDetailsBtnForQC);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnForQC));
		element1.click();
		wait.until(ExpectedConditions.visibilityOf(btnApproveCal));
		btnApproveCal.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		System.out.println("First Sponsor approved succesfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by california Sponsor");
	}

	public void NSJuridictionApprovalForEventCreation() throws InterruptedException {
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		action.click(element1).build().perform();
		wait.until(ExpectedConditions.visibilityOf(btnApproveCal));
		btnApproveCal.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		System.out.println("First Sponsor approved succesfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by california Sponsor");
	}

	public void clickbtnAdmin() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		WebElement abc = wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		abc.click();
		System.out.println("Step: Click on the Administration Menu");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the Administration Menu");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void clickbtnEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		System.out.println("Step: Click on the Event option");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the Event option");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void clickbtnCreateEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateEvent));
		btnCreateEvent.click();
		System.out.println("Step: Click on the Create New Event button");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the Create New Event button");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void gettextBoxEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String eventid = randomString = ("Event_" + generatedString);
		textBoxEventID.sendKeys(eventid);
		System.out.println("Step: Entered Citts Event ID");
		ObjectRepo.getTest().log(Status.PASS, "Step: Entered Citts Event ID");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void gettextBoxEventByCittsID() {
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID"));
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
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

	public void gettextBoxEventForCA1() {
		js.executeScript("arguments[0].scrollIntoView();", textBoxEventID);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(randomString);
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
	}

	public void gettextBoxEventForR70() {
		js.executeScript("arguments[0].scrollIntoView();", textBoxEventID);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(prop.getProperty("CITSS_Event_ID_RE1"));
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
	}

	public void gettextBoxEventForWA() {
		js.executeScript("arguments[0].scrollIntoView();", textBoxEventID);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(randomString);
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
	}

	public void gettextBoxEventForQC() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String eventid = randomString = ("Event_" + generatedString);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(eventid);
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Entered Citts Event ID");
	}

	public void selectEventTypeFromDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("Auction");
		System.out.println("Step: Select Event Type From DropDown");
		ObjectRepo.getTest().log(Status.PASS, "Step: Select Event Type From DropDown");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void selectEventTypeFromDropDownForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("Allowance Price Containment Reserve");
	}

	public void selectVintage2() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage2));
		WebElement dropdownvint2 = dropDownVintage2;
		Select select2 = new Select(dropdownvint2);
		select2.selectByVisibleText("2023");
		textBoxVintage2.sendKeys("10000");
	}

	public void selectVintageType2() {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownVintageType2));
		WebElement dropdownvintType2 = drpdownVintageType2;
		Select select2 = new Select(dropdownvintType2);
		select2.selectByVisibleText("Advance");
	}

	public void selectProcessingOrder2() {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder2));
		WebElement dropdownProOrder2 = drpdownProCessOrder2;
		Select select2 = new Select(dropdownProOrder2);
		select2.selectByVisibleText("2");
	}

	public void selectVintage3() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage3));
		WebElement dropdownvint3 = dropDownVintage3;
		Select select3 = new Select(dropdownvint3);
		select3.selectByVisibleText("2024");
		textBoxVintage3.sendKeys("5000");
	}

	public void selectVintageType3() {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownVintageType3));
		WebElement dropdownvintType3 = drpdownVintageType3;
		Select select3 = new Select(dropdownvintType3);
		select3.selectByVisibleText("Advance");
	}

	public void selectProcessingOrder3() {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder3));
		WebElement dropdownProOrder3 = drpdownProCessOrder3;
		Select select3 = new Select(dropdownProOrder3);
		select3.selectByVisibleText("2");
	}

	public void selectReserveSaleEventTypeforCAFromDropDown() throws InterruptedException {
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		try {
			s.selectByVisibleText("Reserve Sale");
		} catch (Exception e) {
			s.selectByVisibleText("Sale by Mutual Agreement");
		}
		ObjectRepo.getTest().log(Status.PASS, "step 5 : Select Reserve Sale Event From Dropdown");
	}

	public void selectReserveSaleEventTypeforWAFromDropDown() throws InterruptedException {
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		try {
			s.selectByVisibleText("Allowance Price Containment Reserve");
		} catch (Exception e) {
			s.selectByVisibleText("Sale by Mutual Agreement");
		}
		ObjectRepo.getTest().log(Status.PASS, "step 5 : Select Reserve Sale Event From Dropdown");
	}

	public void selectReserveSaleEventTypeforQCFromDropDown() {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		try {
			s.selectByVisibleText("Sale by Mutual Agreement");
		} catch (Exception e) {
			s.selectByVisibleText("Reserve Sale");
		}
		ObjectRepo.getTest().log(Status.PASS, "step 5 : Select Reserve Sale Event From Dropdown");
	}

	public void enterEventName() throws IOException {
		newEventName = RandomEventNameGenerator.generateAndUpdateEventName();
		System.out.println("Generated event name: " + newEventName);
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(newEventName);
		System.out.println("Step: Enter event name in english");
		ObjectRepo.getTest().log(Status.PASS, "Step: Enter event name in english");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void enterEventNameForARTestCases() {
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		WebElement element = eventName;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		System.out.println("Event name is :--" + "ARTestCasesEvent");
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys("ARTestCasesEvent");
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterEventNameForCA() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys(eventNameEng + randomString);
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterEventNameForCA2() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void enterEventNameForCA3() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys("ReserveSaleEventName" + randomString);
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterEventNameForRE1_R70() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys(prop.getProperty("Event_Name_RE1"));
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterEventNameForWA_RS() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterEventNameForQC() {
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.sendKeys(prop.getProperty("Event_NameForQC"));
		ObjectRepo.getTest().log(Status.PASS, "step 6 : Entered event name in english");
	}

	public void enterfrenchEventName() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("FrenchEvent");
		ObjectRepo.getTest().log(Status.PASS, "Entered event name in french");
	}

	public void enterfrenchEventNameForWAReserveSale() {
		js.executeScript("arguments[0].scrollIntoView();", frenchEventName);
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		frenchEventName.click();
		frenchEventName.sendKeys(prop.getProperty("ReserveSaleEventName_WA"));
		ObjectRepo.getTest().log(Status.PASS, "step 7 : Entered event name in french");
	}

	public void enterfrenchEventNameForCalReserveSale() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "step 7 : Entered event name in french");
	}

	public void enterfrenchEventNameForReserveSaleR70() {
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "step 7 : Entered event name in french");
	}

	public void enterfrenchEventNameForQueSingleAuction() {
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "step 7 : Entered event name in french");
	}

	public void enterfrenchEventNameReserveSaleEventName() {
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(prop.getProperty("ReserveSaleEventName"));
		ObjectRepo.getTest().log(Status.PASS, "step 7 : Entered event name in french");
	}

	public void selectJurisdiction() {
		wait.until(ExpectedConditions.visibilityOf(ParticipatingJurisdictions));
		wait.until(ExpectedConditions.elementToBeClickable(ParticipatingJurisdictions));
		ParticipatingJurisdictions.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnCalifornia));
			btnCalifornia.click();
		} catch (Exception e) {
			btnWashinton.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("ABC");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Step: Select jurisdiction");
		ObjectRepo.getTest().log(Status.PASS, "Step: Select jurisdiction");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void selectJurisdictionForJointAuction() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(ParticipatingJurisdictions));
		ParticipatingJurisdictions.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnCalifornia)).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnQuebec)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnWashinton)).click();
		}
		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("Événement test");
	}

	public void selectJurisdictionForQue() {
//		WebElement abc3 = ParticipatingJurisdictions;
//		js.executeScript("arguments[0].scrollIntoView();", abc3);
//		wait.until(ExpectedConditions.elementToBeClickable(ParticipatingJurisdictions));
//		ParticipatingJurisdictions.click();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(btnQuebec));
//			btnQuebec.click();
//		} catch (Exception e) {
//			wait.until(ExpectedConditions.elementToBeClickable(btnCalifornia));
//			btnCalifornia.click();
//		}
//		wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
//		WebElement abc2 = frenchEventName;
//		Actions action = new Actions(driver);
//		action.moveToElement(abc2).build().perform();
//		action.clickAndHold(abc2).build().perform();
//		action.click(abc2).build().perform();
//		frenchEventName.sendKeys(eventNameEng);
		ParticipatingJurisdictions.click();
		try {
			btnQuebec.click();
		} catch (Exception e) {
			btnQuebec.click();
		}
		WebElement abc2 = frenchEventName;
		Actions action = new Actions(driver);
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys(eventNameQC);
	}

	public void selectJurisdictionForSingleAuction() {
		wait.until(ExpectedConditions.elementToBeClickable(ParticipatingJurisdictions));
		ParticipatingJurisdictions.click();
		try {
			btnNovaScotia.click();
		} catch (Exception e) {

			btnWashinton.click();
		}
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("ABC");
	}

	public void currencyAccForQue() {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAcc));
		WebElement abc3 = currencyAcc;
		action.moveToElement(abc3).click().build().perform();
		try {
			tagCAD.click();
		} catch (Exception e) {
			driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		}
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).sendKeys(Keys.ESCAPE);
		WebElement abc4 = textBoxCAD;
		action.moveToElement(abc4).click().build().perform();
		action.clickAndHold(abc4).build().perform();
		action.click(abc4).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD"));
	}

	public void currencyAccForjointAuction() {
		try {
			WebElement abc3 = currencyAcc;
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagUSD));
		tagUSD.click();
		wait.until(ExpectedConditions.elementToBeClickable(tagCAD));
		tagCAD.click();
		WebElement abc4 = textBoxUSD;
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD"));
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(prop.getProperty("FloorPriceCAD"));
	}

	public void currencyAccForCal() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonLib commonLib = new CommonLib();
		commonLib.scroll(currencyAcc, "scrollToElement", "currencyAcc");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		wait.until(ExpectedConditions.elementToBeClickable(currencyAcc));
		try {
			action.moveToElement(currencyAcc).click().build().perform();
		} catch (Exception e) {
			action.moveToElement(currencyAcc).click().build().perform();
		}
//		currencyAcc.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(tagUSD).click().build().perform();
		tagUSD.sendKeys(Keys.ESCAPE);
		wait.until(ExpectedConditions.elementToBeClickable(textBoxUSD));
		commonLib.click(textBoxUSD, "javascriptClick", "textBoxUSD");
		wait.until(ExpectedConditions.elementToBeClickable(textBoxUSD));
		textBoxUSD.sendKeys(prop.getProperty("FloorPriceUSD"));
	}

	public void currencyAccForReserveSaleForCA() {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAccforreservesale));
		WebElement element = currencyAccforreservesale;
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
//		action.moveToElement(element).click().build().perform();
		Select s = new Select(element);
		s.selectByVisibleText("USD");
		ObjectRepo.getTest().log(Status.PASS, "step 8 : Currency selected for a particular juridiction");
	}

	public void currencyAccForReserveSaleForQC() {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAccforreservesale));
		WebElement element = currencyAccforreservesale;
		element.click();
		Select s = new Select(element);
		s.selectByVisibleText("CAD");
		ObjectRepo.getTest().log(Status.PASS, "step 8 : Currency selected for a particular juridiction");
	}

	public void selectTiers1() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier One");
		} catch (Exception e) {
			s.selectByVisibleText("Category A");
		}
		texttiersprices.sendKeys(prop.getProperty("TierPrice1"));
		texttotalallowanceavail.sendKeys(prop.getProperty("TotalAllowances1"));
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("1");
		btnAddNewTier.click();
	}

	public void selectTiers1_R70() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier One");
		} catch (Exception e) {
			s.selectByVisibleText("Category A");
		}
		texttiersprices.sendKeys(prop.getProperty("PriceOffered_Category_A_RE1"));
		texttotalallowanceavail.sendKeys(prop.getProperty("TotalAllowances_Category_A_RE1"));
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);

		s1.selectByVisibleText("1");
		btnAddNewTier.click();
	}

	public void selectTiers2_R70() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown2));
		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Two");
		} catch (Exception e) {
			s.selectByVisibleText("Category B");
		}
		texttiersprices2.sendKeys(prop.getProperty("PriceOffered_Category_B_RE1"));
		texttotalallowanceavail2.sendKeys(prop.getProperty("TotalAllowances_Category_B_RE1"));
		WebElement element1 = processingOrderDropDown2;
		Select s1 = new Select(element1);

		s1.selectByVisibleText("2");
		btnAddNewTier.click();
	}

	public void selectTiers3_R70() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown3));
		WebElement element = tiersDropDown3;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Three");
		} catch (Exception e) {
			s.selectByVisibleText("Category C");
		}
		texttiersprices3.sendKeys(prop.getProperty("PriceOffered_Category_C_RE1"));
		texttotalallowanceavail3.sendKeys(prop.getProperty("TotalAllowances_Category_C_RE1"));
		WebElement element1 = processingOrderDropDown3;
		Select s1 = new Select(element1);

		s1.selectByVisibleText("3");
		btnAddNewTier.click();
	}

	public void selectTiers1RS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier One");
		} catch (Exception e) {
			s.selectByVisibleText("Category A");
		}
		texttiersprices.sendKeys(prop.getProperty("TierPrice1_WA"));
		texttotalallowanceavail.sendKeys(prop.getProperty("TotalAllowances1_WA"));
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);

		s1.selectByVisibleText("1");
		btnAddNewTier.click();
	}

	public void selectTiers2() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown2));
		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Two");
		} catch (Exception e) {
			s.selectByVisibleText("Category B");
		}
		texttiersprices2.sendKeys(prop.getProperty("TierPrice2"));
		texttotalallowanceavail2.sendKeys(prop.getProperty("TotalAllowances2"));
		WebElement element2 = processingOrderDropDown2;
		Select s2 = new Select(element2);

		s2.selectByVisibleText("2");
		btnAddNewTier.click();
	}

	public void selectTiers2RS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown2));
		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Two");
		} catch (Exception e) {
			s.selectByVisibleText("Category B");
		}
		texttiersprices2.sendKeys(prop.getProperty("TierPrice2_WA"));
		texttotalallowanceavail2.sendKeys(prop.getProperty("TotalAllowances2_WA"));
		WebElement element2 = processingOrderDropDown2;
		Select s2 = new Select(element2);

		s2.selectByVisibleText("2");
		btnAddNewTier.click();
	}

	public void selectTiers3() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown3));
		WebElement element = tiersDropDown3;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Three");
		} catch (Exception e) {
			s.selectByVisibleText("Category C");
		}
		texttiersprices3.sendKeys(prop.getProperty("TierPrice3"));
		texttotalallowanceavail3.sendKeys(prop.getProperty("TotalAllowances3"));
		WebElement element3 = processingOrderDropDown3;
		Select s3 = new Select(element3);

		s3.selectByVisibleText("3");
	}

	public void selectTiers3RS_WA() {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown3));
		WebElement element = tiersDropDown3;
		Select s = new Select(element);
		try {
			s.selectByVisibleText("Tier Three");
		} catch (Exception e) {
			s.selectByVisibleText("Category C");
		}
		texttiersprices3.sendKeys(prop.getProperty("TierPrice3_WA"));
		texttotalallowanceavail3.sendKeys(prop.getProperty("TotalAllowances3_WA"));
		WebElement element3 = processingOrderDropDown3;
		Select s3 = new Select(element3);

		s3.selectByVisibleText("3");
	}

	public void currencyAccForSingleAuctionWasington() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(currencyAcc));
			currencyAcc.click();
		} catch (Exception e) {
			currencyAcc.click();
		}
		Thread.sleep(2000);
		WebElement abc4 = tagUSD;
		action.moveToElement(abc4).build().perform();
		action.click(abc4).build().perform();
		try {
			textBoxUSD.click();
		} catch (Exception e) {
			Thread.sleep(2000);
			WebElement abc5 = textBoxUSD;
			action.clickAndHold(abc5).build().perform();
			action.click(abc5).build().perform();
		}
		textBoxUSD1.sendKeys("11.15");
	}

	public void currencyAccForSingleAuctionNovaScotia() {
		try {
			WebElement abc3 = currencyAcc;
			action.moveToElement(abc3).click().build().perform();
		} catch (Exception e) {

			currencyAcc.click();
		}
		WebElement abc5 = tagCADForNS;
		action.moveToElement(abc5).build().perform();
		tagCADForNS.click();

		WebElement abc4 = textBoxCAD;
		js.executeScript("arguments[0].scrollIntoView();", abc4);
		action.moveToElement(abc4).click().build().perform();
		textBoxCAD.click();
		textBoxCAD.sendKeys("11.15");
	}

	public void selectVintage() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage1));
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2023");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances"));
	}

	public void selectVintageType() {
		wait.until(ExpectedConditions.elementToBeClickable(dropDownVintage1));
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText("Current");
	}

	public void selectProcessingOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
	}

	public void selectDatesForARTestcases() {
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
		LocalTime BiddingStart = time.plusHours(12).plusMinutes(6).plusSeconds(10);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(12).plusMinutes(12).plusSeconds(40);
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
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + applicationClose);
	}

	public void selectDates() {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		String date1 = dateFormat.format(date);
		System.out.println("todays date :" + date1);
		String date2 = dateFormat.format(tomorrow);
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
		LocalTime resultsAvailable = time.plusHours(2).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(4).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(3).plusMinutes(40);

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
		js.executeScript("arguments[0].scrollIntoView(true);", dateEventAppOpen);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + " " + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + " " + applicationClose);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateBidResultAvail.click();
		dateBidResultAvail.sendKeys(date2 + " " + resultsAvailable);
		dateBidResultAvail.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateEventClosure.click();
		dateEventClosure.sendKeys(date2 + " " + eventClosure);
		dateEventClosure.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateFinantialSettlement.click();
		dateFinantialSettlement.sendKeys(date2 + " " + financialSettlement);
	}

	public void selectDatesReservesaleCA1() {
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
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void selectDatesReservesaleCA() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		String date1 = dateFormat.format(date);
		System.out.println("todays date :" + date1);
		String date2 = dateFormat.format(tomorrow);
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

	public void selectDatesReservesaleWA() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1); // Adding 1 day to current date
		Date tomorrow = calendar.getTime();
		String date1 = dateFormat.format(date); // Today's date
		String date2 = dateFormat.format(tomorrow); // Tomorrow's date
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);
		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(6).plusSeconds(20);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15).plusSeconds(40);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(6);
		System.out.println("application Close time :" + applicationClose);
		LocalTime bidGuaranteeSubmission = time.minusHours(0).plusMinutes(2).minusSeconds(0);
		System.out.println("bidGuaranteeSubmission  time :" + bidGuaranteeSubmission);

		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(6).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(5).plusMinutes(40);

		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);

		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + " " + BiddingStart);

		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
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
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']"))
				.sendKeys(date1 + " " + bidGuaranteeSubmission);

		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + " " + applicationClose);

		dateBidResultAvail.click();
		dateBidResultAvail.sendKeys(date2 + " " + resultsAvailable);
		dateEventClosure.click();
		dateEventClosure.sendKeys(date2 + " " + eventClosure);
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(dateFinantialSettlement).click().perform();
		} catch (Exception e) {
			dateFinantialSettlement.click();
		}
		dateFinantialSettlement.sendKeys(date2 + " " + financialSettlement);
	}

	public void selectPastDatesForApplication() {
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
		LocalTime BiddingStart = time.plusHours(0).minusMinutes(10).plusSeconds(40);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(42).plusSeconds(40);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(5);
		System.out.println("application Close time :" + applicationClose);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + BiddingStart);
		String GetDateTimeErrormsg = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).getText();
		System.out.println("Bidding period open Past Date and Time :- " + GetDateTimeErrormsg);

		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//input[@name='eventBiddingPeriodClose']"));
		action.moveToElement(element).build().perform();

		wait.until(ExpectedConditions.elementToBeClickable(dateBiddingPeriodClose));
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + BiddingEnd);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		try {
//			Actions actions = new Actions(driver);
			action.moveToElement(dateEventAppOpen).click().perform();
		} catch (Exception e) {
			dateEventAppOpen.click();
		}
		dateEventAppOpen.sendKeys(date1 + applicationOpen);
		driver.findElement(By.xpath("//input[@name='biddingGuaranteeSubmissionDeadline']")).click();
		
		try {
//			Actions actions = new Actions(driver);
			action.moveToElement(dateEventAppClose).click().perform();
		} catch (Exception e) {
			dateEventAppClose.click();
		}
		dateEventAppClose.sendKeys(date1 + applicationClose);
	}

	public void selectPastDatesForApplication1() {
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
		String DateTime = date1 + " " + formattedDate;
		System.out.println("Current Date & time :" + DateTime);
		ObjectRepo.getTest().log(Status.PASS, "Current Date & time :" + DateTime);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);

		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).minusMinutes(10);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).minusMinutes(9);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(30);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(25);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).minusMinutes(10);
		;
		LocalTime resultsAvailable = time.plusHours(0).minusMinutes(6);
		LocalTime eventClosure = time.plusHours(0).minusMinutes(6);
		LocalTime financialSettlement = time.plusHours(0).minusMinutes(4);

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
		dateBidResultAvail.sendKeys(date1 + " " + resultsAvailable);

		dateEventClosure.click();
		dateEventClosure.sendKeys(date1 + " " + eventClosure);

		dateFinantialSettlement.click();
		dateFinantialSettlement.sendKeys(date1 + " " + financialSettlement);

		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		try {
			driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		System.out.println("SAVE button is clickable");
		ObjectRepo.getTest().log(Status.PASS, "SAVE button is clickable");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Past bidding period open date and time
		String GetDateTime = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).getAttribute("value");
		System.out.println("Bidding period open Past Date and Time :- " + GetDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period open Past Date and Time :- " + GetDateTime);
		String GetPastError = wait.until(ExpectedConditions.visibilityOf(PastdateBiddingPeroidOpen)).getText();
		System.out.println("Bidding period open Past Date and Time error message :- " + GetPastError);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period open Past Date and Time error message :- " + GetPastError);
		ObjectRepo.getTest().log(Status.PASS, "C54467 Test case pass");
		Assert.assertEquals("The bidding period open date and time cannot be in the past.", GetPastError);
		System.out.println("****************************************************************************");

		// Past bidding period Close date and time
		String GetCloseDateTime = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeriodClose))
				.getAttribute("value");
		System.out.println("Bidding period Close Past Date and Time :- " + GetCloseDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period open close Past Date and Time :- " + GetCloseDateTime);
		String GetPastCloseDateError = wait.until(ExpectedConditions.visibilityOf(PastdateBiddingPeriodClose))
				.getText();
		System.out.println("Bidding period Close Past Date and Time error message :- " + GetPastCloseDateError);
		ObjectRepo.getTest().log(Status.PASS,
				"Bidding period Close Past Date and Time error message :- " + GetPastCloseDateError);
		ObjectRepo.getTest().log(Status.PASS, "C54485 Test case pass");
		Assert.assertEquals("The bidding period close date and time cannot be in the past.", GetPastCloseDateError);
		System.out.println("****************************************************************************");

		String GetBidGauranteeeDateTime = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadline))
				.getAttribute("value");
		System.out.println("Bid Guarantee Submission Deadline Date and Time :- " + GetBidGauranteeeDateTime);
		ObjectRepo.getTest().log(Status.PASS,
				"Bid Guarantee Submission Deadline Date and Time :- " + GetBidGauranteeeDateTime);
		String GetPastBidGauranteeeDateError = wait
				.until(ExpectedConditions.visibilityOf(PastdateBidGuaranteesubDeadline)).getText();
		System.out.println("The financial guarantee submission deadline Past date and time error message :- "
				+ GetPastBidGauranteeeDateError);
		ObjectRepo.getTest().log(Status.PASS,
				"The financial guarantee submission deadline Past date and time error message :- "
						+ GetPastBidGauranteeeDateError);
		ObjectRepo.getTest().log(Status.PASS, "C54471 Test case pass");
		Assert.assertEquals("The financial guarantee submission deadline date and time cannot be in the past.",
				GetPastBidGauranteeeDateError);
		System.out.println("****************************************************************************");

		String GetResultsDateTime = wait.until(ExpectedConditions.visibilityOf(dateBidResultAvail))
				.getAttribute("value");
		System.out.println("Results available date and time :- " + GetResultsDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Results available date and time :- " + GetResultsDateTime);
		String GetPastResultsAvalableDateError = wait.until(ExpectedConditions.visibilityOf(PastdateBidResultAvail))
				.getText();
		System.out.println("Results available Past date and time error message :- " + GetPastResultsAvalableDateError);
		ObjectRepo.getTest().log(Status.PASS,
				"Results available Past date and time error message :- " + GetPastResultsAvalableDateError);
		Assert.assertEquals("The results available date and time cannot be in the past.",
				GetPastResultsAvalableDateError);
		ObjectRepo.getTest().log(Status.PASS, "C54490 Test case pass");
		System.out.println("****************************************************************************");

		String GetClosedEventDateTime = wait.until(ExpectedConditions.visibilityOf(dateEventClosure))
				.getAttribute("value");
		System.out.println("Event closure date and time :- " + GetClosedEventDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Event closure date and time :- " + GetClosedEventDateTime);
		String GetPastClosedEventDateError = wait.until(ExpectedConditions.visibilityOf(PastdateEventClosure))
				.getText();
		System.out.println("Event closure Past date and time error message :- " + GetPastClosedEventDateError);
		ObjectRepo.getTest().log(Status.PASS,
				"Event closure Past date and time error message :- " + GetPastClosedEventDateError);
		Assert.assertEquals("The event closure date and time cannot be in the past.", GetPastClosedEventDateError);
		ObjectRepo.getTest().log(Status.PASS, "C54498 Test case pass");
		System.out.println("****************************************************************************");

		String GetFinancialSettlementDateTime = wait.until(ExpectedConditions.visibilityOf(dateFinantialSettlement))
				.getAttribute("value");
		System.out.println("Financial settlement date and time :- " + GetFinancialSettlementDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Financial settlement date and time :- " + GetFinancialSettlementDateTime);
		String GetPastGetFinancialSettlementDateTimeDateError = wait
				.until(ExpectedConditions.visibilityOf(PastdateFinantialSettlement)).getText();
		System.out.println("Financial settlement Past date and time error message :- "
				+ GetPastGetFinancialSettlementDateTimeDateError);
		ObjectRepo.getTest().log(Status.PASS, "Financial settlement Past date and time error message :- "
				+ GetPastGetFinancialSettlementDateTimeDateError);
		ObjectRepo.getTest().log(Status.PASS, "C54507 Test case pass");
		Assert.assertEquals("The financial settlement date and time cannot be in the past.",
				GetPastGetFinancialSettlementDateTimeDateError);
		System.out.println("****************************************************************************");
	}

	public void ApplicationCloseDateandTimeAreBeforeTheApplicationOpenDateandTime() {
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
		String DateTime = date1 + " " + formattedDate;
		System.out.println("Current Date & time :" + DateTime);
		ObjectRepo.getTest().log(Status.PASS, "Current Date & time :" + DateTime);
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
		LocalTime applicationClose = time.minusHours(0).minusMinutes(18);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3);
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

		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		try {
			driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		System.out.println("SAVE button is clickable");
		ObjectRepo.getTest().log(Status.PASS, "SAVE button is clickable");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String GetDateTime = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).getAttribute("value");
		System.out.println("Application period open Date and Time :- " + GetDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Application period open Date and Time :- " + GetDateTime);
		System.out.println("****************************************************************************");

		String GetDateTime1 = wait.until(ExpectedConditions.visibilityOf(dateEventAppClose)).getAttribute("value");
		System.out.println("Application period close Date and Time :- " + GetDateTime1);
		ObjectRepo.getTest().log(Status.PASS, "Application period close Date and Time :- " + GetDateTime1);
		String GetAppCloseError = wait.until(ExpectedConditions.visibilityOf(BeforedateEventAppOpen)).getText();
		System.out.println("Application period close before open Date and Time error message :- " + GetAppCloseError);
		ObjectRepo.getTest().log(Status.PASS,
				"Application period close before open Date and Time error message :- " + GetAppCloseError);
		Assert.assertEquals(
				"The event application close date and time must be after the event application open date and time.",
				GetAppCloseError);
		ObjectRepo.getTest().log(Status.PASS, "C54503 Test case pass");
		System.out.println("****************************************************************************");
	}

	public void BiddingPeriodCloseDateandTimeAreBeforeTheBiddingOpenDateandTime() {
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
		String DateTime = date1 + " " + formattedDate;
		System.out.println("Current Date & time :- " + DateTime);
		ObjectRepo.getTest().log(Status.PASS, "Current Date & time :- " + DateTime);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);

		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(4);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(1);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(05);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3);
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

		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		try {
			driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		System.out.println("SAVE button is clickable");
		ObjectRepo.getTest().log(Status.PASS, "SAVE button is clickable");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String GetDateTime = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).getAttribute("value");
		System.out.println("Bidding period open Date and Time :- " + GetDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period open Date and Time :- " + GetDateTime);
		System.out.println("****************************************************************************");

		String GetDateTime1 = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeriodClose)).getAttribute("value");
		System.out.println("Bidding period close date and time :- " + GetDateTime1);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period close date and time :- " + GetDateTime1);
		String GetBidCloseError = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeriodCloseError)).getText();
		System.out
				.println("Bidding period close before open bidding Date and Time error message :- " + GetBidCloseError);
		ObjectRepo.getTest().log(Status.PASS,
				"Bidding period close before open bidding Date and Time error message :- " + GetBidCloseError);
		Assert.assertEquals(
				"The bidding period close date and time must be after the bidding period open date and time.",
				GetBidCloseError);
		ObjectRepo.getTest().log(Status.PASS, "C54486 Test case pass");
		System.out.println("****************************************************************************");
	}

	public void BidGuaranteeSubmissionDeadlineDateandTimeAfterTheBiddingPeriodOpenDateandTime() {
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
		String DateTime = date1 + " " + formattedDate;
		System.out.println("Current Date & time :- " + DateTime);
		ObjectRepo.getTest().log(Status.PASS, "Current Date & time :- " + DateTime);
		String hrs = formattedDate.substring(0, 2);
		String min = formattedDate.substring(3, 5);
		int num = Integer.valueOf(min);
		int num2 = Integer.valueOf(hrs);

		LocalTime time = LocalTime.of(num2, num);
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(4);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(1);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.minusHours(0).minusMinutes(10);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.minusHours(0).minusMinutes(05);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(9);
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

		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		try {
			driver.findElement(By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		System.out.println("SAVE button is clickable");
		ObjectRepo.getTest().log(Status.PASS, "SAVE button is clickable");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String GetDateTime = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).getAttribute("value");
		System.out.println("Bidding period open Date and Time :- " + GetDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Bidding period open Date and Time :- " + GetDateTime);
		System.out.println("****************************************************************************");

		String GetDateTime1 = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadline))
				.getAttribute("value");
		System.out.println("Financial guarantee submission deadline date and time :- " + GetDateTime1);
		ObjectRepo.getTest().log(Status.PASS, "Financial guarantee submission deadline date and time :- " + GetDateTime1);
		String GetBidGauranteeError = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadlineError))
				.getText();
		System.out.println(
				"Financial guarantee submission deadline Date and Time After open bidding Date and Time error message :- "
						+ GetBidGauranteeError);
		ObjectRepo.getTest().log(Status.PASS,
				"Financial guarantee submission deadline Date and Time After open bidding Date and Time error message :- "
						+ GetBidGauranteeError);
		Assert.assertEquals(
				"The financial guarantee submission deadline date and time cannot be after the bidding period open date and time.",
				GetBidGauranteeError);
		ObjectRepo.getTest().log(Status.PASS, "C54486 Test case pass");
		System.out.println("****************************************************************************");
	}

	public void selectAlgorithm() {
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
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Step: Fill all the mandatory fields and Click on the 'Save' button at the bottom of the page.");
		ObjectRepo.getTest().log(Status.PASS, "Step: Fill all the mandatory fields and Click on the 'Save' button at the bottom of the page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void validatePopupforEventCreation() {
		wait.until(ExpectedConditions.elementToBeClickable(successmsg));
		String alert = successmsg.getText();
		Assert.assertEquals(alert, "The event has been created successfully.");
		System.out.println("Success message should be displayed as = " + alert);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed as = " + alert);
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void clickbtnUser() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnUser));
			btnUser.click();
			ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on User button");
		} catch (Exception e) {
			btnAdminstration.click();
			btnUser.click();
			ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on User button");
		}
		System.out.println("Step: Click on the User option");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the User option");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void addMoreVintages() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectEventTypeFromDropDown();
		ref.enterEventName();
		ref.selectJurisdiction();
		ref.currencyAccForCal();
		ref.selectVintage();
		ref.selectVintageType();
		ref.selectProcessingOrder();
		ref.selectVintage2();
		ref.selectVintageType2();
		ref.selectProcessingOrder2();
		addNewVintage.click();
		ref.selectVintage3();
		ref.selectVintageType3();
		ref.selectProcessingOrder3();
	}

	public void ValidatesValidatesCategotyPrices() throws Exception {
		CreateEventPage ref = new CreateEventPage();

		wait.until(ExpectedConditions.elementToBeClickable(createNewEventButton)).click();
		String eventCreationPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventCreatePage))
				.getText();
		System.out.println(eventCreationPageAssertion);
		Assert.assertEquals("Event Creation", eventCreationPageAssertion);
		System.out.println("Step3: 'Event creation page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event creation page successfully open");
		ref.selectEventTypeFromDropDown1();
		String assertAuctionEventType1 = wait.until(ExpectedConditions.elementToBeClickable(eventTypeSBMA)).getText();
		System.out.println(assertAuctionEventType1);
		Assert.assertEquals("Sale by Mutual Agreement", assertAuctionEventType1);
		ref.generateString();
		ref.gettextBoxEvent();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		WebElement element = tiersDropDown1;
		Select s = new Select(element);
		s.selectByVisibleText("Category A");
		texttiersprices.sendKeys("0");
		texttotalallowanceavail.sendKeys("1000");
		ref.selectProcessingOrder();
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		String assertCategoryPriceErrorMessage = wait.until(ExpectedConditions.elementToBeClickable(categoryPriceError))
				.getText();
		System.out.println(assertCategoryPriceErrorMessage);
		Assert.assertEquals("Category Price should be greater than zero.", assertCategoryPriceErrorMessage);
		System.out.println("Step4: Category Price should be greater than zero while creating an event.");
		ObjectRepo.getTest().log(Status.PASS, "Category Price should be greater than zero while creating an event");
	}

	public void ValidatesTotalEmissionsUnitsPerCategory() throws Exception {
		CreateEventPage ref = new CreateEventPage();

		wait.until(ExpectedConditions.elementToBeClickable(createNewEventButton)).click();
		String eventCreationPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventCreatePage))
				.getText();
		System.out.println(eventCreationPageAssertion);
		Assert.assertEquals("Event Creation", eventCreationPageAssertion);
		System.out.println("Step3: 'Event creation page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event creation page successfully open");
		ref.selectEventTypeFromDropDown1();
		String assertAuctionEventType1 = wait.until(ExpectedConditions.elementToBeClickable(eventTypeSBMA)).getText();
		System.out.println(assertAuctionEventType1);
		Assert.assertEquals("Sale by Mutual Agreement", assertAuctionEventType1);
		ref.generateString();
		ref.gettextBoxEvent();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		WebElement element = tiersDropDown1;
		Select s = new Select(element);
		s.selectByVisibleText("Category A");
		texttiersprices.sendKeys("10");
		texttotalallowanceavail.sendKeys("0");
		ref.selectProcessingOrder();
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		String assertTotalEmissionUnitsAvailableErrorMessage = wait
				.until(ExpectedConditions.elementToBeClickable(emissionUnitsError)).getText();
		System.out.println(assertTotalEmissionUnitsAvailableErrorMessage);
		Assert.assertEquals("Total Emission Units Available should be greater than zero.",
				assertTotalEmissionUnitsAvailableErrorMessage);
		System.out
				.println("Step4: Total Emission Units Available should be greater than zero while creating an event.");
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available should be greater than zero while creating an event.");
	}

	public void editBiddingPeriodOpenEvent() throws Exception {

		WebElement element1 = biddingPeriodOpeneventDetailsButton;
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String editedBiddingPeriodOpenEvent = wait.until(ExpectedConditions.elementToBeClickable(editedOpenEventText))
				.getText();
		System.out.println(editedBiddingPeriodOpenEvent);
		Assert.assertEquals("R255-SBMA", editedBiddingPeriodOpenEvent);
		System.out.println("Step3: 'Event with Bidding Period Open edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Bidding Period Open edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement element2 = dateBiddingPeroidOpen;
		action.moveToElement(element2).build().perform();
		Boolean abc = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).isEnabled();
		if (abc.equals(true)) {
			System.out.println("Step4: Bidding Period Open date can be changed when Bidding Period is open");
			ObjectRepo.getTest().log(Status.FAIL, "Bidding Period Open date can be changed when Bidding Period is open");
		} else {
			System.out.println("Step4: Bidding Period Open date can not be changed when Bidding Period is open");
			ObjectRepo.getTest().log(Status.PASS, "Bidding Period Open date can not be changed when Bidding Period is open");
		}
		WebElement elementc = dateEventAppOpen;
		action.moveToElement(elementc).build().perform();
		Boolean applperiodopen = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).isEnabled();
		if (applperiodopen.equals(true)) {
			System.out.println(
					"Step5: Application Period Open date can be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Open date can be changed when Results Available date is closed");
		} else {
			System.out.println(
					"Step5: Application Period Open date can not be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Open date can not be changed when Results Available date is closed");
		}
		WebElement elementd = dateEventAppClose;
		action.moveToElement(elementd).build().perform();
		Boolean applperiodclosed = wait.until(ExpectedConditions.visibilityOf(dateEventAppClose)).isEnabled();
		if (applperiodclosed.equals(true)) {
			System.out.println(
					"Step6: Application Period Close date can be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Close date can be changed when Results Available date is Closed");
		} else {
			System.out.println(
					"Step6: Application Period Close date can not be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Close date can not be changed when Results Available date is Closed");
		}

	}

	public void editBiddingPeriodClosedEvent() throws Exception {

		WebElement element1 = biddingPeriodClosedeventDetailsButton;
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String editedBiddingPeriodClosedEvent = wait
				.until(ExpectedConditions.elementToBeClickable(editedClosedEventText)).getText();
		System.out.println(editedBiddingPeriodClosedEvent);
		Assert.assertEquals("FirstEvent", editedBiddingPeriodClosedEvent);
		System.out.println("Step3: 'Event with Bidding Period Closed edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Bidding Period Closed edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement elementc = dateEventAppOpen;
		action.moveToElement(elementc).build().perform();
		Boolean applperiodopen = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).isEnabled();
		if (applperiodopen.equals(true)) {
			System.out.println("Step4: Application Period Open date can be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Open date can be changed when when Bidding Period is closed");
		} else {
			System.out.println("Step4: Application Period Open date can not be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Open date can not be changed when Bidding Period is closed");
		}
		WebElement elementd = dateEventAppClose;
		action.moveToElement(elementd).build().perform();
		Boolean applperiodclosed = wait.until(ExpectedConditions.visibilityOf(dateEventAppClose)).isEnabled();
		if (applperiodclosed.equals(true)) {
			System.out.println("Step5: Application Period Close date can be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Close date can be changed when when Bidding Period is closed");
		} else {
			System.out.println("Step5: Application Period Close date can not be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Close date can not be changed when Bidding Period is closed");
		}
		Boolean bidperiosopen = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).isEnabled();
		if (bidperiosopen.equals(true)) {
			System.out.println("Step6: Bidding Period Open date can be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.FAIL, "Bidding Period Open date can be changed when Bidding Period is closed");
		} else {
			System.out.println("Step6: Bidding Period Open date can not be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Bidding Period Open date can not be changed when Bidding Period is closed");
		}
		WebElement element2 = dateBiddingPeriodClose;
		action.moveToElement(element2).build().perform();
		Boolean abc = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeriodClose)).isEnabled();
		if (abc.equals(true)) {
			System.out.println("Step7: Bidding Period Close date can be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.FAIL, "Bidding Period Close date can be changed when Bidding Period is closed");
		} else {
			System.out.println("Step7: Bidding Period Close date can not be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Bidding Period Close date can not be changed when Bidding Period is closed");
		}
		WebElement elementf = bidderConference;
		action.moveToElement(elementf).build().perform();
		Boolean bidderconfer = wait.until(ExpectedConditions.visibilityOf(bidderConference)).isEnabled();
		if (bidderconfer.equals(true)) {
			System.out.println("Step8: Bidder Conference date can be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.FAIL, "Bidder Conference date can not be changed when Bidding Period is closed");
		} else {
			System.out.println("Step8: Bidder Conference date can not be changed when Bidding Period is closed");
			ObjectRepo.getTest().log(Status.PASS, "Bidder Conference date can not be changed when Bidding Period is closed");
		}
		WebElement elemente = dateBidGuaranteesubDeadline;
		action.moveToElement(elemente).build().perform();
		Boolean finguarnateesub = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadline)).isEnabled();
		if (finguarnateesub.equals(true)) {
			System.out.println(
					"Step9: Financial Guarantee Submission Deadline date can be changed when Event Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can be changed when Event Available date is Closed");
		} else {
			System.out.println(
					"Step9: Financial Guarantee Submission Deadline date can not be changed when Event Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can not be changed when Event Available date is Closed");
		}

	}

	public void editApplicationPeriodOpensEvent() throws Exception {

		WebElement element1 = applicationPeriodOpendeventDetailsButton;
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String editedApplicationPeriodOpenEvent = wait
				.until(ExpectedConditions.elementToBeClickable(editedApplicationOpenEventText)).getText();
		System.out.println(editedApplicationPeriodOpenEvent);
		Assert.assertEquals("Open Application Period", editedApplicationPeriodOpenEvent);
		System.out.println("Step3: 'Event with Application Period Open edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Application Period Open edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement element3 = dateEventAppOpen;
		action.moveToElement(element3).build().perform();
		Boolean abc = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).isEnabled();
		if (abc.equals(true)) {
			System.out.println("Step4: Application Period Open date can be changed when Application Period is open");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Open date can be changed when Application Period is open");
		} else {
			System.out
					.println("Step4: Application Period Open date can not be changed when Application Period is open");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Open date can not be changed when Application Period is open");
		}
	}

	public void editApplicationPeriodClosedEvent() throws Exception {

		WebElement element1 = applicationPeriodClosedEventDetailsButton;
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String editedApplicationPeriodClosedEvent = wait
				.until(ExpectedConditions.elementToBeClickable(editedApplicationClosedEventText)).getText();
		System.out.println(editedApplicationPeriodClosedEvent);
		Assert.assertEquals("Application Period Closed", editedApplicationPeriodClosedEvent);
		System.out.println("Step3: 'Event with Application Period Closed edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Application Period Closed edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement element4 = dateEventAppClose;
		action.moveToElement(element4).build().perform();
		Boolean abc = wait.until(ExpectedConditions.visibilityOf(dateEventAppClose)).isEnabled();
		if (abc.equals(true)) {
			System.out.println("Step4: Application Period Close date can be changed when Application Period is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Close date can be changed when Application Period is Closed");

		} else {
			System.out.println(
					"Step4: Application Period Close date can not be changed when Application Period is Closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Close date can not be changed when Application Period is Closed");
		}

		WebElement elementc = dateEventAppOpen;
		action.moveToElement(elementc).build().perform();
		Boolean applperiodopen = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).isEnabled();
		if (applperiodopen.equals(true)) {
			System.out.println("Step5: Application Period Open date can be changed when Application Period is Closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Open date can be changed when Application Period is Closed");

		} else {
			System.out.println(
					"Step5: Application Period Open date can not be changed when Application Period is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Open date can not be changed when Application Period is Closed");
		}
	}

	public void editFinancialGuaranteeSubmissionDeadlineClosedEvent() throws Exception {

		WebElement element1 = financialStatementDateCloseddEventDetailsButton;
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String editedFinancialGuaranteeSubmissionDeadlineDateClosedEvent = wait
				.until(ExpectedConditions.visibilityOf(editedFinancialStatementDateClosedEventText)).getText();
		System.out.println(editedFinancialGuaranteeSubmissionDeadlineDateClosedEvent);
		Assert.assertEquals("ThirdEvent", editedFinancialGuaranteeSubmissionDeadlineDateClosedEvent);
		System.out
				.println("Step3: 'Event with Financial Guarantee Submission Deadline date Closed edited successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"Event with Financial Guarantee Submission Deadline date Closed edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement element4 = dateBidGuaranteesubDeadline;
		action.moveToElement(element4).build().perform();
		Boolean abc = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadline)).isEnabled();
		if (abc.equals(true)) {
			System.out.println("Step4: Financial Guarantee Submission Deadline date can be changed when it is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can be changed when it is Closed");

		} else {
			System.out.println(
					"Step4: Financial Guarantee Submission Deadline date can not be changed when it is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can not be changed when it is Closed");
		}
	}

	public void editResultsAvailableDateClosedEvent() throws Exception {
		WebElement elementp = resultsAvailableDateClosedEventDetailsButton;
		Actions action = new Actions(driver);
		action.click(elementp).build().perform();
		String editedResultsAvailableDateClosedEvent = wait
				.until(ExpectedConditions.visibilityOf(editedresultsAvailableDateClosedEventText)).getText();
		System.out.println(editedResultsAvailableDateClosedEvent);
		Assert.assertEquals("Results Available Date Closed", editedResultsAvailableDateClosedEvent);
		System.out.println("Step3: 'Event with Results Available Date is Closed edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Results Available Date is Closed edited successfully");
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		WebElement elementa = dateBiddingPeroidOpen;
		action.moveToElement(elementa).build().perform();
		Boolean bidperiosopen = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeroidOpen)).isEnabled();
		if (bidperiosopen.equals(true)) {
			System.out.println("Step4: Bidding Period Open date can be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Bidding Period Open date can be changed when Results Available date is closed");

		} else {
			System.out.println(
					"Step4: Bidding Period Open date can not be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Bidding Period Open date can not be changed when Results Available date is closed");
		}
		WebElement elementb = dateBiddingPeriodClose;
		action.moveToElement(elementb).build().perform();
		Boolean bidperiodclose = wait.until(ExpectedConditions.visibilityOf(dateBiddingPeriodClose)).isEnabled();
		if (bidperiodclose.equals(true)) {
			System.out.println("Step5: Bidding Period Close date can be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Bidding Period Close date can be changed when Results Available date is closed");

		} else {
			System.out.println(
					"Step5: Bidding Period Close date can not be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Bidding Period Close date can not be changed when Results Available date is closed");
		}

		WebElement elementc = dateEventAppOpen;
		action.moveToElement(elementc).build().perform();
		Boolean applperiodopen = wait.until(ExpectedConditions.visibilityOf(dateEventAppOpen)).isEnabled();
		if (applperiodopen.equals(true)) {
			System.out.println(
					"Step6: Application Period Open date can be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Open date can be changed when Results Available date is closed");

		} else {
			System.out.println(
					"Step6: Application Period Open date can not be changed when Results Available date is closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Open date can not be changed when Results Available date is closed");
		}

		WebElement elementd = dateEventAppClose;
		action.moveToElement(elementd).build().perform();
		Boolean applperiodclosed = wait.until(ExpectedConditions.visibilityOf(dateEventAppClose)).isEnabled();
		if (applperiodclosed.equals(true)) {
			System.out.println(
					"Step7: Application Period Close date can be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Application Period Close date can be changed when Results Available date is Closed");

		} else {
			System.out.println(
					"Step7: Application Period Close date can not be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Application Period Close date can not be changed when Results Available date is Closed");
		}

		WebElement elemente = dateBidGuaranteesubDeadline;
		action.moveToElement(elemente).build().perform();
		Boolean finguarnateesub = wait.until(ExpectedConditions.visibilityOf(dateBidGuaranteesubDeadline)).isEnabled();
		if (finguarnateesub.equals(true)) {
			System.out.println(
					"Step8: Financial Guarantee Submission Deadline date can be changed when Event Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can be changed when Event Available date is Closed");

		} else {
			System.out.println(
					"Step8: Financial Guarantee Submission Deadline date can not be changed when Event Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Financial Guarantee Submission Deadline date can not be changed when Event Available date is Closed");
		}

		WebElement elementf = bidderConference;
		action.moveToElement(elementf).build().perform();
		Boolean bidderconfer = wait.until(ExpectedConditions.visibilityOf(bidderConference)).isEnabled();
		if (bidderconfer.equals(true)) {
			System.out.println("Step9: Bidder Conference date can be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.FAIL,
					"Bidder Conference date can not be changed when iResults Available date is Closed");

		} else {
			System.out
					.println("Step9: Bidder Conference date can not be changed when Results Available date is Closed");
			ObjectRepo.getTest().log(Status.PASS,
					"Bidder Conference date can not be changed when Results Available date is Closed");
		}

		WebElement elementg = resultsAvailable;
		action.moveToElement(elementg).build().perform();
		Boolean resultsavailable = wait.until(ExpectedConditions.visibilityOf(resultsAvailable)).isEnabled();
		if (resultsavailable.equals(false)) {
			System.out.println("Step10: Results Available date can not be changed when it is Closed");
			ObjectRepo.getTest().log(Status.FAIL, "Results Available date can not be changed when it is Closed");

		} else {
			System.out.println("Step10: Results Available date can be changed when it is Closed");
			ObjectRepo.getTest().log(Status.PASS, "Results Available date can be changed when it is Closed");
		}
	}

	public void editClosedEvent() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		WebElement elementp = closedEventDetailsButton;
		Actions action = new Actions(driver);
		action.click(elementp).build().perform();
		String editedClosedEvent = wait.until(ExpectedConditions.visibilityOf(editedEventClosureText)).getText();
		System.out.println(editedClosedEvent);
		Assert.assertEquals("SBMA 260", editedClosedEvent);
		System.out.println("Step3: 'Event with Closed status edited successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event with Closed status edited successfully");
		try {
			wait.until(ExpectedConditions.invisibilityOf(editEvent));
			System.out.println("Step4: Closed event can not be edited");
			ObjectRepo.getTest().log(Status.PASS, "Closed event can not be edited");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Step4: Closed event can be edited");
			ObjectRepo.getTest().log(Status.FAIL, "Closed event can be edited");
		}
	}

	public void cancelEvent() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectEventTypeFromDropDown1();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectDates();
		wait.until(ExpectedConditions.elementToBeClickable(btnCancel)).click();

	}

	public void clearEvent() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectEventTypeFromDropDown1();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		wait.until(ExpectedConditions.elementToBeClickable(btnClear)).click();
	}

	public void clearSBAEvent() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectEventTypeFromDropDown1();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForSingleAuctionNovaScotia();
		ref.selectTiers1_New();
		ref.selectDates();
		wait.until(ExpectedConditions.elementToBeClickable(btnClearSBA)).click();
	}

	public void searchBoxCanBeCleared() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnUser();
		String assertstatusCount = wait.until(ExpectedConditions.elementToBeClickable(statusCount)).getText();
		System.out.println("Total count for User's Status before Search is : " + assertstatusCount);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Status before Search is : " + assertstatusCount);
		String assertgovernmentCount = wait.until(ExpectedConditions.elementToBeClickable(governmentCount)).getText();
		System.out.println("Total count for User's Government before Search is : " + assertgovernmentCount);
		ObjectRepo.getTest().log(Status.PASS,
				"Total count for User's Government before Search is : " + assertgovernmentCount);
		String assertroleCount = wait.until(ExpectedConditions.elementToBeClickable(roleCount)).getText();
		System.out.println("Total count for User's Role before Search is : " + assertroleCount);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Role before Search is : " + assertroleCount);
		wait.until(ExpectedConditions.elementToBeClickable(enterSearchInput)).click();

		wait.until(ExpectedConditions.elementToBeClickable(enterSearchInput)).sendKeys("sponsor");

		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();

		String assertstatusCount1 = wait.until(ExpectedConditions.elementToBeClickable(statusCount)).getText();
		System.out.println("Total count for User's Status after Search is : " + assertstatusCount1);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Status after Search is : " + assertstatusCount1);
		String assertgovernmentCount1 = wait.until(ExpectedConditions.elementToBeClickable(governmentCount)).getText();
		System.out.println("Total count for User's Government after Search is : " + assertgovernmentCount1);
		ObjectRepo.getTest().log(Status.PASS,
				"Total count for User's Government after Search is : " + assertgovernmentCount);
		String assertroleCount1 = wait.until(ExpectedConditions.elementToBeClickable(roleCount)).getText();
		System.out.println("Total count for User's Role after Search is : " + assertroleCount1);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Role after Search is : " + assertroleCount);
		wait.until(ExpectedConditions.elementToBeClickable(btnResetSearch)).click();
		String assertstatusCount2 = wait.until(ExpectedConditions.elementToBeClickable(statusCount)).getText();
		System.out.println("Total count for User's Status after Reset Search is : " + assertstatusCount2);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Status after Reset Search is : " + assertstatusCount1);
		String assertgovernmentCount2 = wait.until(ExpectedConditions.elementToBeClickable(governmentCount)).getText();
		System.out.println("Total count for User's Government after Reset Search is : " + assertgovernmentCount2);
		ObjectRepo.getTest().log(Status.PASS,
				"Total count for User's Government after Reset Search is : " + assertgovernmentCount);
		String assertroleCount2 = wait.until(ExpectedConditions.elementToBeClickable(roleCount)).getText();
		System.out.println("Total count for User's Role after Reset Search is : " + assertroleCount2);
		ObjectRepo.getTest().log(Status.PASS, "Total count for User's Role after Reset Search is : " + assertroleCount);

	}

	public void editEvent() throws Exception {

		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		WebElement element = driver.findElement(eventDetailsButton);
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).click();
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(texttotalallowanceavail)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.elementToBeClickable(texttotalallowanceavail)).sendKeys("00000");
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		String message = wait.until(ExpectedConditions.elementToBeClickable(successEditMessage)).getText();
		System.out.println(message);
		Assert.assertEquals("The event has been edited successfully.", message);
		System.out.println("Step: Event Edited Successfully");
		ObjectRepo.getTest().log(Status.PASS, "Event Edited Successfully");
	}

	public void listOfEvents() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		String eventAdminPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventAdminPage)).getText();
		System.out.println(eventAdminPageAssertion);
		Assert.assertEquals("Event Administration", eventAdminPageAssertion);
		System.out.println("Step4: 'Event administration page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event administration page successfully open");

		String listOfEventPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(listOfEventPage))
				.getText();
		System.out.println(listOfEventPageAssertion);
		Assert.assertEquals("List of Events", listOfEventPageAssertion);
		System.out.println("Step5: 'List Of Events page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "List Of Events page successfully open");
		String eventTypeColumnName = wait.until(ExpectedConditions.visibilityOf(eventTypeColumn)).getText();
		System.out.println(eventTypeColumnName);
		Assert.assertEquals("EVENT TYPE", eventTypeColumnName);
		String eventIDColumnName = wait.until(ExpectedConditions.visibilityOf(eventIDColumn)).getText();
		System.out.println(eventIDColumnName);
		Assert.assertEquals("EVENT ID", eventIDColumnName);
		String eventNameColumnName = wait.until(ExpectedConditions.visibilityOf(eventNameColumn)).getText();
		System.out.println(eventNameColumnName);
		Assert.assertEquals("EVENT NAME", eventNameColumnName);
		String eventDateColumnName = wait.until(ExpectedConditions.visibilityOf(eventDateColumn)).getText();
		System.out.println(eventDateColumnName);
		Assert.assertEquals("EVENT DATE", eventDateColumnName);
		String pjColumnName = wait.until(ExpectedConditions.visibilityOf(participatingJuriColumn)).getText();
		System.out.println(pjColumnName);
		Assert.assertEquals("GOV", pjColumnName);
		String createdBYColumnName = wait.until(ExpectedConditions.visibilityOf(createdByColumn)).getText();
		System.out.println(createdBYColumnName);
		Assert.assertEquals("CREATED BY", createdBYColumnName);
		String creationDateColumnName = wait.until(ExpectedConditions.visibilityOf(creationDateColumn)).getText();
		System.out.println(creationDateColumnName);
		Assert.assertEquals("CREATION DATE", creationDateColumnName);
		String eventStatusColumnName = wait.until(ExpectedConditions.visibilityOf(eventStatusColumn)).getText();
		System.out.println(eventStatusColumnName);
		Assert.assertEquals("EVENT STATUS", eventStatusColumnName);
	}

	public void verifyRollDownEnability() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1_New();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are able to select Rolldown procedure option when only one category is selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"AUP users are able to select Rolldown procedure option when only one category is selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when only one category is selected");
			ObjectRepo.getTest().log(Status.PASS,
					"Rolldown procedure cannot be selected when only one category is selected");
		}

		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewTier)).click();
		ref.selectTiers2_New();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when more than one categories are selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"Rolldown procedure cannot be selected when more than one categories are selected");
		}

	}

	public void verifyCombinedEvaluationOfCategoriesSelection() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectTiers2();
		ref.selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
			System.out.println("AUP users are able to select the Combined Evaluation of Categories checkbox");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are able to select the Combined Evaluation of Categories checkbox");

		} catch (Exception e) {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
			System.out.println("AUP users are not able to select the Combined Evaluation of Categories checkbox");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are not able to select the Combined Evaluation of Categories checkbox");
		}
	}

	public void verifyThePriceCeilingUnitsRadioButtonSelection() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectTiers2();
		ref.selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			priceCeiling.click();
			System.out.println("AUP users are able to select the Price Ceiling radio button");
			ObjectRepo.getTest().log(Status.PASS, "AUP users are able to select the Price Ceiling radio button");

		} catch (Exception e) {
			ObjectRepo.getTest().log(Status.FAIL, "step 11 : AUP users are not able to select the Price Ceiling option");
			System.out.println("AUP users are not able to select the Price Ceiling");

		}
	}

	public void verifyDisapprovaFromSponsor() throws Exception {
		String popupMessageReject = wait.until(ExpectedConditions.elementToBeClickable(disapprovalPopupMessage))
				.getText();
		System.out.println(popupMessageReject);
		Assert.assertEquals("The rejection of the event creation has been proposed by the first Sponsor.",
				popupMessageReject);
		System.out.println("Event is rejected succesfully by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event is rejected succesfully by the first Sponsor.");
		CreateEventPage ref = new CreateEventPage();
		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		String verifyEventStatus = wait.until(ExpectedConditions.elementToBeClickable(eventStatus)).getText();
		System.out.println(verifyEventStatus);
		Assert.assertEquals("Saved", verifyEventStatus);
		System.out.println("Event is in Saved status when it is rejected by the first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event is in Saved status when it is rejected by the first Sponsor.");
	}

	@SuppressWarnings("hiding")
	public <DateFormat> void selectDatesBidderConference() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + "18:20");
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + "22:20");
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + "00:20");
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + "17:20");
	}

	public void createEventForReserveSaleForQCMultipleBidderConference() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectTiers2();
		ref.selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		wait.until(ExpectedConditions.elementToBeClickable(biddersConferenceDateTime)).click();
		biddersConferenceDateTime.sendKeys("05 Sept 2022 03:33");
		wait.until(ExpectedConditions.elementToBeClickable(textBidderConference)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addAnotherConference)).click();
		wait.until(ExpectedConditions.elementToBeClickable(biddersConferenceDateTime1)).click();
		biddersConferenceDateTime1.sendKeys("10 Sept 2022 04:44");
		wait.until(ExpectedConditions.elementToBeClickable(textBidderConference)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addAnotherConference)).click();
		wait.until(ExpectedConditions.elementToBeClickable(biddersConferenceDateTime2)).click();
		biddersConferenceDateTime2.sendKeys("15 Sept 2022 04:44");
		System.out.println("Entered dates for all the mandatory fields");
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
		ApprovalStatusPage ref1 = new ApprovalStatusPage();
		ref1.firstApprovalQC();
	}

	public void verifyMultipalBidderConferenceDate() throws Exception {
		String popupMessageApprove = wait.until(ExpectedConditions.elementToBeClickable(approvalPopupMessage))
				.getText();
		System.out.println(popupMessageApprove);
		Assert.assertEquals("The creation of the event has been approved by both Sponsors.", popupMessageApprove);
		System.out.println("Event is created and succesfully approved by the both the Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event is created and succesfully approved by the both the Sponsor.");
		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
		String webinarLabelText = wait.until(ExpectedConditions.elementToBeClickable(webinar)).getText();
		System.out.println(webinarLabelText);
		Assert.assertEquals("Webinar", webinarLabelText);
		String FirstWebinarDate = wait.until(ExpectedConditions.elementToBeClickable(webinarDate1)).getText();
		System.out.println(FirstWebinarDate);
		Assert.assertEquals("05 Sep 2022", FirstWebinarDate);
		String secondWebinarDate = wait.until(ExpectedConditions.elementToBeClickable(webinarDate2)).getText();
		System.out.println(secondWebinarDate);
		Assert.assertEquals("10 Sep 2022", secondWebinarDate);

		String thirdWebinarDate = wait.until(ExpectedConditions.elementToBeClickable(webinarDate3)).getText();
		System.out.println(thirdWebinarDate);
		Assert.assertEquals("15 Sep 2022", thirdWebinarDate);

		System.out
				.println("EA/Sponsor are able to see multiple details in webinar field of Homepage for created event.");
		ObjectRepo.getTest().log(Status.PASS,
				"EA/Sponsor are able to see multiple details in webinar field of Homepage for created event.");
	}

	public void createEventForReserveSaleForQCSingleBidderConference() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectTiers2();
		ref.selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDatesBidderConference();
		wait.until(ExpectedConditions.elementToBeClickable(biddersConferenceDateTime)).click();
		biddersConferenceDateTime.sendKeys("05 Sept 2022 03:33");
		wait.until(ExpectedConditions.elementToBeClickable(textBidderConference)).click();
		System.out.println("Entered dates for all the mandatory fields");
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
		ApprovalStatusPage ref1 = new ApprovalStatusPage();
		ref1.firstApprovalQC();
	}

	public void verifySingleBidderConference() throws Exception {
		// Verify the approval from both the Sponsor

		String popupMessageApprove = wait.until(ExpectedConditions.elementToBeClickable(approvalPopupMessage))
				.getText();
		System.out.println(popupMessageApprove);
		Assert.assertEquals("The creation of the event has been approved by both Sponsors.", popupMessageApprove);
		System.out.println("Event is created and succesfully approved by the both the Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event is created and succesfully approved by the both the Sponsor.");
		// Verify the status Webinar dates entered during event creation
		// CreateEvent ref = new CreateEvent(driver,prop);
		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();

		String webinarLabelText = wait.until(ExpectedConditions.elementToBeClickable(webinar)).getText();
		System.out.println(webinarLabelText);
		Assert.assertEquals("Webinar", webinarLabelText);

		String FirstWebinarDate = wait.until(ExpectedConditions.elementToBeClickable(webinarDate1)).getText();
		System.out.println(FirstWebinarDate);
		Assert.assertEquals("05 Sep 2022", FirstWebinarDate);

		System.out.println(
				"EA/Sponsor are able to see the webinar details in webinar field of Homepage for created event.");
		ObjectRepo.getTest().log(Status.PASS,
				"EA/Sponsor are able to see webinar details in webinar field of Homepage for created event.");
	}

	public void verifyEventDetailsAfterCreation() throws Exception {
		String popupMessageApprove = wait.until(ExpectedConditions.elementToBeClickable(approvalPopupMessage))
				.getText();
		System.out.println(popupMessageApprove);
		Assert.assertEquals("The creation of the event has been approved by both Sponsors.", popupMessageApprove);
		System.out.println("Event is created and succesfully approved by the both the Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event is created and succesfully approved by the both the Sponsor.");
		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();

		String webinarLabelText = wait.until(ExpectedConditions.elementToBeClickable(webinar)).getText();
		System.out.println(webinarLabelText);
		Assert.assertEquals("Webinar", webinarLabelText);
		String FirstWebinarDate = wait.until(ExpectedConditions.elementToBeClickable(webinarDate1)).getText();
		System.out.println(FirstWebinarDate);
		Assert.assertEquals("05 Sep 2022", FirstWebinarDate);

		String upcomingEventTextAssert = wait.until(ExpectedConditions.elementToBeClickable(upcomingEventsText))
				.getText();
		System.out.println(upcomingEventTextAssert);
		Assert.assertEquals("Upcoming Events", upcomingEventTextAssert);

		String firstEventTextAssert = wait.until(ExpectedConditions.elementToBeClickable(eventNameText)).getText();
		System.out.println(firstEventTextAssert);
		Assert.assertEquals("First Event", firstEventTextAssert);

		String daysTextAssert = wait.until(ExpectedConditions.elementToBeClickable(dayText)).getText();
		System.out.println(daysTextAssert);
		Assert.assertEquals("Day", daysTextAssert);

		String hoursTextAssert = wait.until(ExpectedConditions.elementToBeClickable(hoursText)).getText();
		System.out.println(hoursTextAssert);
		Assert.assertEquals("Hours", hoursTextAssert);
		String minsTextAssert = wait.until(ExpectedConditions.elementToBeClickable(minsText)).getText();
		System.out.println(minsTextAssert);
		Assert.assertEquals("Mins", minsTextAssert);

		String categoryATextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryA)).getText();
		System.out.println(categoryATextAssert);
		Assert.assertEquals("Category A", categoryATextAssert);

		String categoryBTextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryB)).getText();
		System.out.println(categoryBTextAssert);
		Assert.assertEquals("Category B", categoryBTextAssert);

		String categoryCTextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryC)).getText();
		System.out.println(categoryCTextAssert);
		Assert.assertEquals("Category C", categoryCTextAssert);

		String categoryAValueTextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryAValue)).getText();
		System.out.println(categoryAValueTextAssert);
		Assert.assertEquals("CAD 100.00", categoryAValueTextAssert);

		String categoryBValueTextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryBValue)).getText();
		System.out.println(categoryBValueTextAssert);
		Assert.assertEquals("CAD 100.00", categoryBValueTextAssert);

		String categoryCValueTextAssert = wait.until(ExpectedConditions.elementToBeClickable(categoryCValue)).getText();
		System.out.println(categoryCValueTextAssert);
		Assert.assertEquals("CAD 100.00", categoryCValueTextAssert);

		String eventDateTextAssert = wait.until(ExpectedConditions.elementToBeClickable(eventDateText)).getText();
		System.out.println(eventDateTextAssert);
		Assert.assertEquals("Event Date", eventDateTextAssert);

		String applicatioPeriodTextAssert = wait.until(ExpectedConditions.elementToBeClickable(applicatioPeriodText))
				.getText();
		System.out.println(applicatioPeriodTextAssert);
		Assert.assertEquals("Application Period", applicatioPeriodTextAssert);

		String resultsPublishedTextAssert = wait.until(ExpectedConditions.elementToBeClickable(resultsPublishedText))
				.getText();
		System.out.println(resultsPublishedTextAssert);
		Assert.assertEquals("Results Published", resultsPublishedTextAssert);

		String webinarTextTextAssert = wait.until(ExpectedConditions.elementToBeClickable(webinarTextText)).getText();
		System.out.println(webinarTextTextAssert);
		Assert.assertEquals("Webinar", webinarTextTextAssert);

		System.out.println(
				"EA/Sponsor are able to see the webinar details in webinar field of Homepage for created event.");
		ObjectRepo.getTest().log(Status.PASS,
				"EA/Sponsor are able to see webinar details in webinar field of Homepage for created event.");
	}

	public void verifyChangeAnnuaCategoryPricesToCategoryPriceOnAssertion() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ref.selectTiers2();
		ref.selectTiers3();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		Boolean chkbx = wait.until(ExpectedConditions.elementToBeClickable(chkbxChangeAnnualCategoryPrice)).isEnabled();
		if (chkbx.equals(false)) {
			System.out.println("Step4: Change Annual Category Prices to Category Price on Homepage is active");
			ObjectRepo.getTest().log(Status.FAIL,
					"Change Annual Category Prices to Category Price on Homepage should not be enabled");

		} else {
			System.out.println(
					"Step4: Change Annual Category Prices to Category Price checkbox is unchecked at event creation.");
			ObjectRepo.getTest().log(Status.PASS,
					"Change Annual Category Prices to Category Price checkbox is unchecked at event creation.");
		}
		ref.selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
		ApprovalStatusPage ref1 = new ApprovalStatusPage();
		ref1.firstApprovalQC();
	}

	public void verifyUploadEmissionsUnitsFile() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1_New();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");

		ref.selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
		ApprovalStatusPage ref1 = new ApprovalStatusPage();
		ref1.firstApprovalQC();
	}

	public void verifyUploadEmissionUnitsFileLabel() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(emissionUnitsUploadEventDetailsButton)).click();
		WebElement uploadFile = uploadEmissionUnitsFile;
		action.click(uploadFile).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(uploadFile))
				.sendKeys("C:/Manish/Emission units Required.xls");
	}

	public void verifyChangeAnnualCategoryPricestoCategoryPriceOnEdit() throws Exception {

		ObjectRepo.getTest().log(Status.PASS,
				"Step6: Change Annual Category Prices to Category Price checkbox cannot be checked during event creation till the SBMA event is approved by both Sponsors.");
		System.out.println(
				"Change Annual Category Prices to Category Price checkbox cannot be checked during event creation till the SBMA event is approved by both Sponsors.");
		wait.until(ExpectedConditions.elementToBeClickable(homeMenu)).click();
		String annualCategoryPrice = wait.until(ExpectedConditions.elementToBeClickable(annualCategoryPriceHomePage))
				.getText();
		System.out.println(annualCategoryPrice);
		Assert.assertEquals("Annual Category Prices", annualCategoryPrice);
		ObjectRepo.getTest().log(Status.PASS,
				"Step7: Change Annual Category Prices to Category Price can not be changed while creating an event");
		System.out
				.println("Change Annual Category Prices to Category Price can not be changed while creating an event");
		driver.navigate().refresh();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		WebElement element = driver.findElement(eventDetailsButton);
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();

		WebElement abc = chkbxChangeAnnualCategoryPriceOnEdit;
		js.executeScript("arguments[0].scrollIntoView();", abc);
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.click(abc).build().perform();
		ObjectRepo.getTest().log(Status.PASS,
				"Step8: Checkbox Change Annual Category Prices to Category Price can be checked only upon edit of the SBMA event.");
		System.out.println(
				"Checkbox Change Annual Category Prices to Category Price can be checked only upon edit of the SBMA event.");
		btnSave.click();
		ApprovalStatusPage approve = new ApprovalStatusPage();
		approve.firstApproval1();

		approve.Logout();
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginQc2();
		approve.secondApproval();

		wait.until(ExpectedConditions.elementToBeClickable(homeMenu)).click();

		String categoryPrice = wait.until(ExpectedConditions.elementToBeClickable(categoryPriceHomePage)).getText();
		System.out.println(categoryPrice);
		Assert.assertEquals("Category Prices", categoryPrice);
		ObjectRepo.getTest().log(Status.PASS,
				"Step9: Change Annual Category Prices to Category Price is changed after editing an event");
		System.out.println("Change Annual Category Prices to Category Price is changed after editing an event");

	}

	public void verifyAlphanumericEventID() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(createNewEventButton)).click();
		String eventCreationPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventCreatePage))
				.getText();
		System.out.println(eventCreationPageAssertion);
		Assert.assertEquals("Event Creation", eventCreationPageAssertion);
		System.out.println("Step3: 'Event creation page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event creation page successfully open");
		CreateEventPage ref = new CreateEventPage();
		ref.selectEventTypeFromDropDown();
		String assertAuctionEventType = wait.until(ExpectedConditions.elementToBeClickable(eventTypeAuction)).getText();
		System.out.println(assertAuctionEventType);
		Assert.assertEquals("Auction", assertAuctionEventType);
		wait.until(ExpectedConditions.elementToBeClickable(enterEventID)).click();
		wait.until(ExpectedConditions.elementToBeClickable(enterEventID)).sendKeys("A601");
		System.out.println("Step4: Sponsor is able to enter alphanumeric values in the Event ID field for Auction.");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor is able to enter alphanumeric values in the Event ID field AUction.");
		WebElement abc = btnClear;
		Actions action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.click(abc).build().perform();
		ref.selectEventTypeFromDropDown1();
		String assertSBMAEventType = wait.until(ExpectedConditions.elementToBeClickable(eventTypeSBMA)).getText();
		System.out.println(assertSBMAEventType);
		Assert.assertEquals("Sale by Mutual Agreement", assertSBMAEventType);
		wait.until(ExpectedConditions.elementToBeClickable(enterEventID)).click();
		wait.until(ExpectedConditions.elementToBeClickable(enterEventID)).sendKeys("R601");
		System.out.println("Step5: Sponsor is able to enter alphanumeric values in the Event ID field for SBMA.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor is able to enter alphanumeric values in the Event ID field for SBMA.");
	}

	public void verifyEventNeedsDualSponsorApproval() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage)).click();
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage500)).click();
		wait.until(ExpectedConditions.elementToBeClickable(dualApprovalOnEditEventDetailsButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();
		String applCloseDate = wait.until(ExpectedConditions.elementToBeClickable(eventApplicationCloseDate))
				.getAttribute("value");
		System.out.println(applCloseDate);
		ObjectRepo.getTest().log(Status.PASS, "Application Close Date before edit an event is" + applCloseDate);
		System.out.println("Application Close Date before edit an event is" + applCloseDate);
		wait.until(ExpectedConditions.elementToBeClickable(eventApplicationCloseDate)).click();
		eventApplicationCloseDate.clear();
		String editApplCloseDate = "29 Dec 2022 06:25";
		eventApplicationCloseDate.sendKeys(editApplCloseDate);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		ApprovalStatusPage fApproval = new ApprovalStatusPage();
		fApproval.firstApproval2();
		ApprovalStatusPage signedOff = new ApprovalStatusPage();
		signedOff.Logout();
	}

	public void verifyEventNeedsDualSponsorApproval1() throws Exception {
		PublicHomePage login = new PublicHomePage();
		login.RuntimeSponsorLoginQc();
		login.handleSecurityQue();
		login.clickOnnextQC();
		ApprovalStatusPage approve = new ApprovalStatusPage();
		approve.changeLangauge();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		approve.SecondApprovalBySameJurisdiction();
		wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();

		wait.until(ExpectedConditions.elementToBeClickable(dualApprovalOnEditEventDetailsButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(editEvent)).click();

		String newApplCloseDate = wait.until(ExpectedConditions.elementToBeClickable(eventApplicationCloseDate))
				.getAttribute("value");
		System.out.println(newApplCloseDate);

		ObjectRepo.getTest().log(Status.PASS, "Application Close Date after edit an event is" + newApplCloseDate);
		System.out.println("Application Close Date after edit an event is" + newApplCloseDate);
	}

	public void numberOfEmissionUnitsPerBidSponsor() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();

		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		System.out.println("Entered dates for all the mandatory fields");
		wait.until(ExpectedConditions.elementToBeClickable(emissionPerLot));
		String emissionPerLotLabelAssert = emissionPerLot.getText();
		System.out.println(emissionPerLotLabelAssert);
		Assert.assertEquals("Emission Units per Lot", emissionPerLotLabelAssert);

		wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot));
		String defaultEmissionPerLotLabelAssert = defaultEmissionPerLot.getAttribute("value");
		System.out.println(defaultEmissionPerLotLabelAssert);
		Assert.assertEquals("1000", defaultEmissionPerLotLabelAssert);

		System.out.println("By default the value is 1000 for Emission Units per bid lot");
		ObjectRepo.getTest().log(Status.PASS, "By default the value is 1000 for Emission Units per bid lot");
		for (int i = 0; i <= 2; i++) {
			defaultEmissionPerLot.sendKeys(Keys.BACK_SPACE);
		}
		for (int i = 0; i <= 2; i++) {
			btnMinus.click();
			{
				String defaultEmissionPerLotLabelAssertOnEdit = defaultEmissionPerLot.getAttribute("value");
				System.out.println(defaultEmissionPerLotLabelAssertOnEdit);
				Assert.assertEquals("1", defaultEmissionPerLotLabelAssertOnEdit);
				System.out.println("The Sponsor can click on the - button to reduce to 1.");
				ObjectRepo.getTest().log(Status.PASS, "The Sponsor can click on the - button to reduce to 1.");
			}
//			wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot)).click();
			wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot)).sendKeys("0000");

			for (i = 0; i <= 3; i++) {
				btnPlus.click();

				System.out.println("one more emmision unit added");
			}
			String defaultEmissionPerLotLabelAssertOnEdit1 = defaultEmissionPerLot.getAttribute("value");
			System.out.println(defaultEmissionPerLotLabelAssertOnEdit1);

			try {
				defaultEmissionPerLotLabelAssertOnEdit1 = defaultEmissionPerLot.getAttribute("value");
				Assert.assertEquals(defaultEmissionPerLotLabelAssertOnEdit1, "10004");
				System.out.println("The Sponsor can click on the + button beyond 10000.");
				ObjectRepo.getTest().log(Status.FAIL, "The Sponsor can click on the + button beyond 10000.");

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("The Sponsor can click on the + button to increase to 10000.");
				ObjectRepo.getTest().log(Status.PASS, "The Sponsor can click on the + button to increase to 10000.");
			}
		}
	}

	public void numberOfEmissionUnitsPerBidEA() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();

		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		System.out.println("Entered dates for all the mandatory fields");
		wait.until(ExpectedConditions.elementToBeClickable(emissionPerLot));
		String emissionPerLotLabelAssert = emissionPerLot.getText();
		System.out.println(emissionPerLotLabelAssert);
		Assert.assertEquals("Emission Units per Lot", emissionPerLotLabelAssert);

		wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot));
		String defaultEmissionPerLotLabelAssert = defaultEmissionPerLot.getAttribute("value");
		System.out.println(defaultEmissionPerLotLabelAssert);
		Assert.assertEquals("1000", defaultEmissionPerLotLabelAssert);

		System.out.println("By default the value is 1000 for Emission Units per bid lot");
		ObjectRepo.getTest().log(Status.PASS, "By default the value is 1000 for Emission Units per bid lot");
		for (int i = 0; i <= 2; i++) {
			defaultEmissionPerLot.sendKeys(Keys.BACK_SPACE);
		}
		for (int i = 0; i <= 2; i++) {
			btnMinus.click();
			{
				String defaultEmissionPerLotLabelAssertOnEdit = defaultEmissionPerLot.getAttribute("value");
				System.out.println(defaultEmissionPerLotLabelAssertOnEdit);
				Assert.assertEquals("1", defaultEmissionPerLotLabelAssertOnEdit);
				System.out.println("The EA can click on the - button to reduce to 1.");
				ObjectRepo.getTest().log(Status.PASS, "The EA can click on the - button to reduce to 1.");
			}
//				wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot)).click();
			wait.until(ExpectedConditions.elementToBeClickable(defaultEmissionPerLot)).sendKeys("0000");

			for (i = 0; i <= 3; i++) {
				btnPlus.click();

				System.out.println("one more emmision unit added");
			}
			String defaultEmissionPerLotLabelAssertOnEdit1 = defaultEmissionPerLot.getAttribute("value");
			System.out.println(defaultEmissionPerLotLabelAssertOnEdit1);

			try {
				defaultEmissionPerLotLabelAssertOnEdit1 = defaultEmissionPerLot.getAttribute("value");
				Assert.assertEquals(defaultEmissionPerLotLabelAssertOnEdit1, "10004");
				System.out.println("The EA can click on the + button beyond 10000.");
				ObjectRepo.getTest().log(Status.FAIL, "The EA can click on the + button beyond 10000.");

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("The EA can click on the + button to increase to 10000.");
				ObjectRepo.getTest().log(Status.PASS, "The EA can click on the + button to increase to 10000.");
			}
		}
	}

	public void limitNumberOfEmissionUnitsPerBidSponsor() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();

		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		// Assert Limit Maximum Bid Allowed label
		System.out.println("Entered dates for all the mandatory fields");
		wait.until(ExpectedConditions.elementToBeClickable(limitMaxBidAllowed));
		String limitMaxBidAllowedLabelAssert = limitMaxBidAllowed.getText();
		System.out.println(limitMaxBidAllowedLabelAssert);
		Assert.assertEquals("Limit Maximum Bids Allowed", limitMaxBidAllowedLabelAssert);

		// Click on the Limit Maximum Bid Allowed radio button to Activate

		isLimitMaxBidAllowedRadioBtn.click();
		System.out.println("Sponsor clicks on the radio button to activate the Limit Maximum Bids allowed.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor clicks on the radio button to activate the Limit Maximum Bids allowed.");

		// Click on the Limit Maximum Bid Allowed radio button to Deactivate

		isLimitMaxBidAllowedRadioBtn.click();
		// wait.until(ExpectedConditions.elementToBeClickable(isLimitMaxBidAllowedRadioBtn)).click();
		System.out.println("Sponsor clicks on the radio button to deactivate the Limit Maximum Bids allowed.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor clicks on the radio button to deactivate the Limit Maximum Bids allowed.");

		// Assert default limit max bid allowed

		isLimitMaxBidAllowedRadioBtn.click();
		// wait.until(ExpectedConditions.elementToBeClickable(isLimitMaxBidAllowedRadioBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(defaultLimitMaxBidAllowed));
		String defaultLimitMaxBidAllowedAssert = defaultLimitMaxBidAllowed.getAttribute("value");
		System.out.println(defaultLimitMaxBidAllowedAssert);
		Assert.assertEquals("1000", defaultLimitMaxBidAllowedAssert);

		System.out.println("On activating, Sponsor gets a model where the limit is by default 1000.");
		ObjectRepo.getTest().log(Status.PASS, "On activating, Sponsor gets a model where the limit is by default 1000.");

		// Assert Sponsor can click on the - button to reduce to 1.
		for (int i = 0; i <= 2; i++) {
			defaultLimitMaxBidAllowed.sendKeys(Keys.BACK_SPACE);

		}
		for (int i = 0; i <= 2; i++) {
			btnMinus1.click();

			{
				String defaultLimitMaxBidAllowedAssertOnEdit = defaultLimitMaxBidAllowed.getAttribute("value");
				System.out.println(defaultLimitMaxBidAllowedAssertOnEdit);
				Assert.assertEquals("1", defaultLimitMaxBidAllowedAssertOnEdit);
				System.out.println("The Sponsor can click on the - button to reduce to 1.");
				ObjectRepo.getTest().log(Status.PASS, "The Sponsor can click on the - button to reduce to 1.");
			}
			wait.until(ExpectedConditions.elementToBeClickable(defaultLimitMaxBidAllowed)).sendKeys("0000");

			for (i = 0; i <= 3; i++) {
				btnPlus1.click();

				System.out.println("one more emmision unit added");
			}
			String defaultLimitMaxBidAllowedAssertOnEdit1 = defaultLimitMaxBidAllowed.getAttribute("value");
			System.out.println(defaultLimitMaxBidAllowedAssertOnEdit1);

			try {
				defaultLimitMaxBidAllowedAssertOnEdit1 = defaultLimitMaxBidAllowed.getAttribute("value");
				Assert.assertEquals(defaultLimitMaxBidAllowedAssertOnEdit1, "10004");
				System.out.println("The Sponsor can click on the + button beyond 10000.");
				ObjectRepo.getTest().log(Status.FAIL, "The Sponsor can click on the + button beyond 10000.");

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("The Sponsor can click on the + button to increase to 10000.");
				ObjectRepo.getTest().log(Status.PASS, "The Sponsor can click on the + button to increase to 10000.");
			}
		}
	}

	public void limitNumberOfEmissionUnitsPerBidEA() throws Exception {

		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();

		ref.generateString();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforQCFromDropDown();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		ref.selectTiers1();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		ref.selectDates();
		System.out.println("Entered dates for all the mandatory fields");
		wait.until(ExpectedConditions.elementToBeClickable(limitMaxBidAllowed));
		String limitMaxBidAllowedLabelAssert = limitMaxBidAllowed.getText();
		System.out.println(limitMaxBidAllowedLabelAssert);
		Assert.assertEquals("Limit Maximum Bids Allowed", limitMaxBidAllowedLabelAssert);
		isLimitMaxBidAllowedRadioBtn.click();
		System.out.println("EA clicks on the radio button to activate the Limit Maximum Bids allowed.");
		ObjectRepo.getTest().log(Status.PASS, "EA clicks on the radio button to activate the Limit Maximum Bids allowed.");
		isLimitMaxBidAllowedRadioBtn.click();
		System.out.println("EA clicks on the radio button to deactivate the Limit Maximum Bids allowed.");
		ObjectRepo.getTest().log(Status.PASS, "EA clicks on the radio button to deactivate the Limit Maximum Bids allowed.");
		isLimitMaxBidAllowedRadioBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(defaultLimitMaxBidAllowed));
		String defaultLimitMaxBidAllowedAssert = defaultLimitMaxBidAllowed.getAttribute("value");
		System.out.println(defaultLimitMaxBidAllowedAssert);
		Assert.assertEquals("1000", defaultLimitMaxBidAllowedAssert);

		System.out.println("On activating, EA gets a model where the limit is by default 1000.");
		ObjectRepo.getTest().log(Status.PASS, "On activating, EA gets a model where the limit is by default 1000.");
		for (int i = 0; i <= 2; i++) {
			defaultLimitMaxBidAllowed.sendKeys(Keys.BACK_SPACE);

		}
		for (int i = 0; i <= 2; i++) {
			btnMinus1.click();

			{
				String defaultLimitMaxBidAllowedAssertOnEdit = defaultLimitMaxBidAllowed.getAttribute("value");
				System.out.println(defaultLimitMaxBidAllowedAssertOnEdit);
				Assert.assertEquals("1", defaultLimitMaxBidAllowedAssertOnEdit);
				System.out.println("The EA can click on the - button to reduce to 1.");
				ObjectRepo.getTest().log(Status.PASS, "The EA can click on the - button to reduce to 1.");
			}
			wait.until(ExpectedConditions.elementToBeClickable(defaultLimitMaxBidAllowed)).sendKeys("0000");

			for (i = 0; i <= 3; i++) {
				btnPlus1.click();

				System.out.println("one more emmision unit added");
			}
			String defaultLimitMaxBidAllowedAssertOnEdit1 = defaultLimitMaxBidAllowed.getAttribute("value");
			System.out.println(defaultLimitMaxBidAllowedAssertOnEdit1);

			try {
				defaultLimitMaxBidAllowedAssertOnEdit1 = defaultLimitMaxBidAllowed.getAttribute("value");
				Assert.assertEquals(defaultLimitMaxBidAllowedAssertOnEdit1, "10004");
				System.out.println("The EA can click on the + button beyond 10000.");
				ObjectRepo.getTest().log(Status.FAIL, "The EA can click on the + button beyond 10000.");

			} catch (Exception e) {

				e.printStackTrace();
				System.out.println("The EA can click on the + button to increase to 10000.");
				ObjectRepo.getTest().log(Status.PASS, "The EA can click on the + button to increase to 10000.");
			}
		}
	}

	public void clearThesearchForMMOnMonotoringPage() {
		wait.until(ExpectedConditions.elementToBeClickable(btnMonotoring)).click();
		WebElement element1 = selectSBMA3EventFromEventDropdown;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(selectSBMA3EventFromEventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();
		String verifyFirstBidAssert = wait.until(ExpectedConditions.elementToBeClickable(firstBidAssert)).getText();
		System.out.println("Details of latest entry before searching the bid:-  " + verifyFirstBidAssert);
		ObjectRepo.getTest().log(Status.PASS, "Details of latest entry before searching the bid:-  " + verifyFirstBidAssert);
		listOfSubmittedBids();
		wait.until(ExpectedConditions.elementToBeClickable(searchByTransactionID)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchByTransactionID)).sendKeys("00820112");
		wait.until(ExpectedConditions.elementToBeClickable(buttonSearch)).click();
		String verifyFirstBidAssert1 = wait.until(ExpectedConditions.elementToBeClickable(firstBidAssert)).getText();
		System.out.println("Details of latest entry after searching the bid:-  " + verifyFirstBidAssert1);
		ObjectRepo.getTest().log(Status.PASS, "Details of latest entry after searching the bid:-  " + verifyFirstBidAssert1);
		System.out.println("MM can search using Transaction ID");
		ObjectRepo.getTest().log(Status.PASS, "MM can search using Transaction ID");
		listOfSubmittedBids();
		wait.until(ExpectedConditions.elementToBeClickable(buttonReset)).click();
		String verifyFirstBidAssert2 = wait.until(ExpectedConditions.elementToBeClickable(firstBidAssert)).getText();
		System.out.println("Details of latest entry after clearing the search:-  " + verifyFirstBidAssert2);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after clearing the search:-  " + verifyFirstBidAssert2);

		listOfSubmittedBids();
	}

	public void listOfSubmittedBids() {

		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@role='checkbox']")));
		List<WebElement> element = driver.findElements(By.xpath("//tr[@role='checkbox']"));
		int totalNumOfBids = element.size();
		System.out.println("Total Numbers of bids available in grid are :" + totalNumOfBids);
	}

	public void enterFinancialSettlementTimeAndDate() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		wait.until(ExpectedConditions.elementToBeClickable(createNewEventButton)).click();
		String eventCreationPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventCreatePage))
				.getText();
		System.out.println(eventCreationPageAssertion);
		Assert.assertEquals("Event Creation", eventCreationPageAssertion);
		System.out.println("Step3: 'Event creation page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event creation page successfully open");
		ref.selectEventTypeFromDropDown1();
		String assertAuctionEventType1 = wait.until(ExpectedConditions.elementToBeClickable(eventTypeSBMA)).getText();
		System.out.println(assertAuctionEventType1);
		Assert.assertEquals("Sale by Mutual Agreement", assertAuctionEventType1);
		ref.generateString();
		ref.gettextBoxEvent();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		WebElement element = tiersDropDown1;
		Select s = new Select(element);
		s.selectByVisibleText("Category A");
		texttiersprices.sendKeys("10");
		texttotalallowanceavail.sendKeys("10000");
		ref.selectProcessingOrder();
		ClassPage ref1 = new ClassPage();
		ref1.selectDatesSBMA();
		priceCeiling.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		System.out.println(
				"Sponsor is able to enter the  time when the Financial Settlement for event will be available on the AUP.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor is able to enter the  time when the Financial Settlement for event will be available on the AUP.");
	}

	@SuppressWarnings("hiding")
	public <DateFormat> void selectDatesSBMA() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + "16:20");
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + "18:20");
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + "00:20");
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + "015:20");

		financialSettlementDateTime.click();
		financialSettlementDateTime.sendKeys(date1 + "15:20");
	}

	public void createSBMAEventByUser() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		wait.until(ExpectedConditions.elementToBeClickable(createNewEventButton)).click();
		String eventCreationPageAssertion = wait.until(ExpectedConditions.elementToBeClickable(eventCreatePage))
				.getText();
		System.out.println(eventCreationPageAssertion);
		Assert.assertEquals("Event Creation", eventCreationPageAssertion);
		System.out.println("Step3: 'Event creation page successfully open");
		ObjectRepo.getTest().log(Status.PASS, "Event creation page successfully open");
		ref.selectEventTypeFromDropDown1();
		String assertAuctionEventType1 = wait.until(ExpectedConditions.elementToBeClickable(eventTypeSBMA)).getText();
		System.out.println(assertAuctionEventType1);
		Assert.assertEquals("Sale by Mutual Agreement", assertAuctionEventType1);
		ref.generateString();
		ref.gettextBoxEvent();
		ref.enterEventName();
		ref.enterfrenchEventName();
		ref.currencyAccForReserveSaleForQC();
		WebElement element = tiersDropDown1;
		Select s = new Select(element);
		s.selectByVisibleText("Category A");
		texttiersprices.sendKeys("10");
		texttotalallowanceavail.sendKeys("10000");
		ref.selectProcessingOrder();
		ClassPage ref1 = new ClassPage();
		ref1.selectDatesSBMA();
		priceCeiling.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSave)).click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		System.out.println("Admin user is able to create an SBMA event suceccfully.");
		ObjectRepo.getTest().log(Status.PASS, "Admin user is able to create an SBMA event suceccfully.");
		System.out.println("******************************************************************");
	}

	public void viewSBMAEventDetailsCreatedByEA() throws Exception {
		WebElement element1 = driver.findElement(eventDetailsButton);
		Actions action = new Actions(driver);
		action.click(element1).build().perform();
		String assertNameOfEventOpenedBySponsor = wait
				.until(ExpectedConditions.elementToBeClickable(nameOfEventOpenedBySponsor)).getText();
		Assert.assertEquals("FirstEvent", assertNameOfEventOpenedBySponsor);
		System.out.println("Event Name displayed to Sponsor is: " + assertNameOfEventOpenedBySponsor);
		ObjectRepo.getTest().log(Status.PASS, "Event Name displayed to Sponsor is: " + assertNameOfEventOpenedBySponsor);
		String assertEventType = wait.until(ExpectedConditions.elementToBeClickable(eventType)).getText();
		Assert.assertEquals("Sale by Mutual Agreement", assertEventType);
		System.out.println("Event Type displayed to Sponsor is: " + assertEventType);
		ObjectRepo.getTest().log(Status.PASS, "Event Type displayed to Sponsor is: " + assertEventType);
		String assertEventJurisdiction = wait.until(ExpectedConditions.elementToBeClickable(eventJurisdiction))
				.getText();
		Assert.assertEquals("Quebec", assertEventJurisdiction);
		System.out.println("Event Jurisdiction displayed to Sponsor is: " + assertEventJurisdiction);
		ObjectRepo.getTest().log(Status.PASS, "Event Jurisdiction displayed to Sponsor is: " + assertEventJurisdiction);
		System.out.println(
				"Sponsor is able to view all the details which were entered by the Event Admin as part of Event Creation.");
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor is able to view all the details which were entered by the Event Admin as part of Event Creation.");
		System.out.println("******************************************************************");
	}

	public void firstRejectionPOM() {

		driver.navigate().refresh();
		WebElement element = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();

		btnfirstReject.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		System.out.println("Event approval is rejected by first Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Event approval is rejected by first Sponsor.");
		System.out.println("******************************************************************");
	}

	public void RejectsecondApprovalPOM() {
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		eventDetailsBtn.click();
		System.out.println("Navigate to particular event ");
		try {

			WebElement element1 = btnApprovalQue;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
			btnApprovalQue.click();
			btnRejectApproval.click();
		} catch (Exception e) {

			btnApprovalQue.click();

			btnApprovalQue.click();
		}
		try {

			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}

		WebElement abc1 = rejectPopup;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();

		System.out.println("Second Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Sponsor rejected Event successfully");
		System.out.println("******************************************************************");
	}

	public void editEventSBMA() {
		driver.navigate().refresh();
		WebElement element = editEvent;
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		WebElement element1 = btnSave;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		String message = wait.until(ExpectedConditions.elementToBeClickable(successEditMessage)).getText();
		Assert.assertEquals("The event has been edited successfully.", message);
		ObjectRepo.getTest().log(Status.PASS, "The event has been edited successfully.");
		System.out.println("The event has been edited successfully.");
		System.out.println("******************************************************************");
		driver.navigate().refresh();
	}

	public void loginSponsorQC() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			wait.until(ExpectedConditions.visibilityOf(btnLogin2));
			btnLogin2.click();
			System.out.println("Quebec sponsor2 login successfully");
			ObjectRepo.getTest().log(Status.PASS, "Quebec sponsor2 login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(username));
			username.clear();
			password.clear();
			username.sendKeys(prop.getProperty("UserSponsorQc"));
			password.sendKeys(prop.getProperty("Password"));
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
			System.out.println("Quebec sponsor2 login successfully");
			ObjectRepo.getTest().log(Status.PASS, "Quebec sponsor2 login successfully");
		}
	}

	public void loginSponsorCalifornia() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(UsersData.userSponsorCa2);
			password.sendKeys(UsersData.password);
			wait.until(ExpectedConditions.visibilityOf(btnLogin2));
			btnLogin2.click();
			System.out.println("California sponsor login successfully");
			ObjectRepo.getTest().log(Status.PASS, "California sponsor login successfully");
		} catch (Exception e) {

			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(UsersData.userSponsorCa2);
			password.sendKeys(UsersData.password);
			wait.until(ExpectedConditions.visibilityOf(btnLogin2));
			btnLogin2.click();
			System.out.println("California sponsor login successfully");
		}
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

	public void contactUsPage() throws InterruptedException  {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Event Monitoring']")));
		js.executeScript("arguments[0].scrollIntoView(true);", contactMenu);
		contactMenu.click();
		System.out.println("Step : Scroll down to bottom of the page and click on the 'Contact Us'.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Scroll down to bottom of the page and click on the 'Contact Us'.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(nameField));
		Assert.assertTrue(nameField.isDisplayed(), "Element is not displayed on the page");
		System.out.println("User should be navigated to 'Contact Us' page.");
		ObjectRepo.getTest().log(Status.PASS, "User should be navigated to 'Contact Us' page.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		System.out.println("Step : Observe Name ,Phone number and  Email address fields.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Observe Name ,Phone number and  Email address fields.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		String abc1 = nameField.getAttribute("value");
		String abc2 = driver.findElement(By.xpath("//input[@placeholder='Enter your email address']"))
				.getAttribute("value");
		System.out.println(abc2);
		Boolean sbc = nameField.isEnabled();
		Assert.assertFalse(sbc);
		Boolean sbc1 = emailField.isEnabled();
		Assert.assertFalse(sbc1);
		System.out.println("Name ,Phone number and  Email address fields should be prefilled for logged in users.");
		ObjectRepo.getTest().log(Status.PASS, "Name ,Phone number and  Email address fields should be prefilled for logged in users.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void contactUsForm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(contactMenu));
		Thread.sleep(2000);
		WebElement element1 = contactMenu;
		action.moveToElement(element1).build().perform();
		Thread.sleep(2000);
		try {
			contactMenu.click();
		} catch (Exception e) {
			contactMenu.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on Contact Us Button");
		wait.until(ExpectedConditions.visibilityOf(phone));
		WebElement element = phone;
		js.executeScript("arguments[0].scrollIntoView();", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phone.sendKeys("+919146444781");

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement frmeelement = driver.findElement(By.xpath("//iframe[@class=\"tox-edit-area__iframe\"]"));
		driver.switchTo().frame(frmeelement);
		WebElement enterMassege = message;
		wait.until(ExpectedConditions.elementToBeClickable(enterMassege)).click();
		enterMassege.sendKeys("WCI Auction Platform Pune");
		driver.switchTo().parentFrame();
		send.click();
		System.out.println("Step : Fill all the mandatory fields and click on Send button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Fill all the mandatory fields and click on Send button.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(getSuccessMsg));
		String msg = getSuccessMsg.getText();
		Assert.assertEquals(msg, "Thank you for your inquiry.");
		System.out.println("Success message should be displayed." + msg);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed." + msg);
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(contactMenu));
		contactMenu.click();
		phone.sendKeys("");
		ObjectRepo.getTest().log(Status.PASS, "Mandatory field keep as empty");
		driver.switchTo().frame(0);
		message.sendKeys("");
		driver.switchTo().parentFrame();
		send.click();
		String msg1 = errorMsg.getText();
		Assert.assertEquals("This field is required", msg1);
		System.out.println(msg1);
		ObjectRepo.getTest().log(Status.PASS, msg1);
	}

	public void HappyPathcontactUsPage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(contactMenu));
		try {
			contactMenu.click();
		} catch (Exception e) {
			contactMenu.click();
		}
		System.out.println("Step : Click on the 'Contact Us' button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Contact Us' button.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(name));
		name.sendKeys("Eqanim 1");
		ObjectRepo.getTest().log(Status.PASS, "Name field accepted successfully");
		email.sendKeys("abc@eqanim.com");
		ObjectRepo.getTest().log(Status.PASS, "Email field accepted successfully");
		phone.sendKeys("02024256123");
		Thread.sleep(6000);
		ObjectRepo.getTest().log(Status.PASS, "Phone field accepted successfully");
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.elementToBeClickable(message)).click();
		wait.until(ExpectedConditions.elementToBeClickable(message)).sendKeys("WCI Auction Platform Pune");
		ObjectRepo.getTest().log(Status.PASS, "Message field accepted successfully");
		driver.switchTo().parentFrame();
		wait.until(ExpectedConditions.elementToBeClickable(send)).click();
		System.out.println("Step : Fill valid data in Name ,Phone number, Email address and Message fields and Click on 'Send' button");
		ObjectRepo.getTest().log(Status.PASS, "Step : Fill valid data in Name ,Phone number, Email address and Message fields and Click on 'Send' button");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(getSuccessMsg));
		Thread.sleep(3000);
		String msg = getSuccessMsg.getText();
		Assert.assertEquals(msg, "Thank you for your inquiry.");
		System.out.println("Success message should be displayed." + msg);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed." + msg);
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void NegativePathcontactUsPage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(contactMenu));
		contactMenu.click();
		wait.until(ExpectedConditions.visibilityOf(name));
		name.sendKeys("1223e");
		ObjectRepo.getTest().log(Status.PASS, "Name field is required");
		email.sendKeys("swww@gmail.com");
		ObjectRepo.getTest().log(Status.PASS, "Email field is required");
		phone.sendKeys("223333fffff");
		ObjectRepo.getTest().log(Status.PASS, "Phone field is required");
		ObjectRepo.getTest().log(Status.PASS, "Message field is required");
		send.click();
		ObjectRepo.getTest().log(Status.PASS, "All mandatory fields are required");

		wait.until(ExpectedConditions.visibilityOf(errorMsg));
		String msg = errorMsg.getText();
		System.out.println(msg);
		Assert.assertEquals("This field is required", msg);
		ObjectRepo.getTest().log(Status.PASS, msg);
	}

	public void contactUsDiscardForm() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1 = contactMenu;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Contact Us Button");
		wait.until(ExpectedConditions.visibilityOf(phone));
		WebElement element = phone;
		js.executeScript("arguments[0].scrollIntoView();", element);
		phone.sendKeys("02024256123");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().frame(0);
		WebElement enterMassege = message;
		wait.until(ExpectedConditions.visibilityOf(enterMassege));
		enterMassege.sendKeys("WCI Auction Platform Pune");
		ObjectRepo.getTest().log(Status.PASS, "Fill up the all Mandatory fields");
		driver.switchTo().parentFrame();
		discard.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Discard Button");
		String msg = popUpMsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Discard Pop Up message: " + msg);
		continueBtn.click();
		System.out.println("All fields are discard");
		ObjectRepo.getTest().log(Status.PASS, "All fields are discard");
	}

	public void contactUsDiscardFormForUnknownUser() throws InterruptedException {

		ObjectRepo.getTest().log(Status.PASS, "URL Launch Successfully ");

		wait.until(ExpectedConditions.elementToBeClickable(contactMenu));
		// Click on the Contact us Button
		contactMenu.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on the Public Homepage Contact Us Button");
		WebElement element = name;
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.visibilityOf(name));
		name.sendKeys("Eqanim");
		phone.sendKeys("02024256123");
		email.sendKeys("auction@wci6799.co.in");
		driver.switchTo().frame(0);
		message.sendKeys("WCI Auction Platform Pune");
		driver.switchTo().parentFrame();
		Actions action = new Actions(driver);
		WebElement element2 = discard;
		action.moveToElement(element2).build().perform();
		discard.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Discard Button");
		String msg = popUpMsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Discard Pop Up message: " + msg);
		continueBtn.click();
		System.out.println("All fields are discard");
		ObjectRepo.getTest().log(Status.PASS, "All fields are discard");

	}

	public void DisplayEventBasicsectionwithfields() {
		wait.until(ExpectedConditions.visibilityOf(eventId));
		String msg = eventId.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Event Id Textfield is displayed");
		String msg1 = eventType.getText();
		System.out.println(msg1);
		ObjectRepo.getTest().log(Status.PASS, "Event Type SelectDropDown Field is displayed");
		String msg2 = partJurdtn.getText();
		System.out.println(msg2);
		ObjectRepo.getTest().log(Status.PASS, "Participating Jurisdictions Namefield is displayed");
		String msg3 = evntNmeEN.getText();
		System.out.println(msg3);
		ObjectRepo.getTest().log(Status.PASS, "Event Name - English Textfield is displayed");
		String msg4 = evntNmeFR.getText();
		System.out.println(msg4);
		ObjectRepo.getTest().log(Status.PASS, "Event Name - French Textfield is displayed");

	}

	public void selectJurisdictionForReserveSale() {
		wait.until(ExpectedConditions.visibilityOf(participateJurisdictionCAReservesale));
		String msg = participateJurisdictionCAReservesale.getText();
		System.out.println(msg);
		Assert.assertEquals("California", msg);
		System.out.println("By default california jurisdiction is get selected for reserve sale");
		ObjectRepo.getTest().log(Status.PASS,
				"step 6 : By default california jurisdiction is get selected for reserve sale");
	}

	public void deleteTheTierRowBeingOfferedForReserveSale() {
		wait.until(ExpectedConditions.visibilityOf(processingOrderDltBtn3)).click();
		System.out.println("Selected 3rd Processing offered tier row is deleted");
		ObjectRepo.getTest().log(Status.PASS, "Selected 3rd Processing offered tier row is deleted");
		wait.until(ExpectedConditions.visibilityOf(processingOrderDltBtn2)).click();
		System.out.println("Selected 2nd Processing offered tier row is deleted");
		ObjectRepo.getTest().log(Status.PASS, "Selected 2nd Processing offered tier row is deleted");
	}

	public void allowancesPerBidLotAdditionalSettingPlusMinus() {
		WebElement abc3 = allowancesPerBidLot;
		js.executeScript("arguments[0].scrollIntoView();", abc3);
		wait.until(ExpectedConditions.visibilityOf(allowancesPerBidLot));
		String abc = allowancesPerBidLot.getText();
		System.out.println(abc);
		Assert.assertEquals("Allowances Per Bid Lot", abc);
		String abc1 = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[6]"))
				.getAttribute("value");
		System.out.println("Emission Units per bid lot by default value Displayed is 1000");
		Assert.assertEquals("1000", abc1);
		ObjectRepo.getTest().log(Status.PASS, "Emission Units per bid lot by default value Displayed is 1000.");
		wait.until(ExpectedConditions.visibilityOf(allowancesPerBidLotPls)).click();
		ObjectRepo.getTest().log(Status.PASS, "Allowance per Bid lot increase (plus+) button is clickable ");
		ObjectRepo.getTest().log(Status.PASS, "Allowance per Bid lot Value increased to 1");
		allowancesPerBidLotMns.click();
		String abc2 = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[6]"))
				.getAttribute("value");
		System.out.println(abc2);
		Assert.assertEquals("1000", abc2);
		ObjectRepo.getTest().log(Status.PASS, "Allowance per Bid lot Value decreased to 1");

	}

	public void limitMaximumBidsAllowedFunctionality() {
		WebElement ab = limitMaximumBidAllowed;
		js.executeScript("arguments[0].scrollIntoView();", ab);
		wait.until(ExpectedConditions.visibilityOf(limitMaximumBidAllowed));
		String abc = limitMaximumBidAllowed.getText();
		System.out.println(abc);
		Assert.assertEquals("Limit Maximum Bids Allowed", abc);
		wait.until(ExpectedConditions.visibilityOf(limitMaximumBidAllowedRadioBtn)).click();
		boolean text = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[7]"))
				.isEnabled();
		System.out.println(text);
		Assert.assertTrue(text);
		System.out.println("Allowed Maximum Bids Limit Moddel is displayed");
		ObjectRepo.getTest().log(Status.PASS,
				"Limit Maximum Bids Allowed Default Increases and Decreases Moddel is Dispalyed ");
		String abc1 = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[6]"))
				.getAttribute("value");
		System.out.println("Sponsor is able to View Allowed Maximum Bids limit by default value is 1000");
		Assert.assertEquals("1000", abc1);
		ObjectRepo.getTest().log(Status.PASS, "Emission Units per bid lot by default value Displayed is 1000.");
		limitMaximumBidAllowedPlus.click();
		String abc2 = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[7]"))
				.getAttribute("value");
		System.out.println(abc2);
		Assert.assertEquals("1001", abc2);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Able to Allowed Maximum limit per Bid lot Value increased to 1");
		limitMaximumBidAllowedMinus.click();
		String abc3 = driver.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[7]"))
				.getAttribute("value");
		System.out.println(abc3);
		Assert.assertEquals("1000", abc3);
		ObjectRepo.getTest().log(Status.PASS, "Allowed maximum limit per Bid lot Value decreased to 1");
	}

	public void sponsorViewTheAllFieldsWereEventCreatedByEventAdnin() throws InterruptedException {
		WebElement element1 = driver
				.findElement(By.xpath("//*[@id='event-list']/table/tbody/tr[1]/td[9]/button"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		String msg = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(msg);
		System.out.println("Sponsor View the event Name on screen");
		String msg1 = eventId.getText();
		System.out.println(msg1);
		System.out.println("Sponsor View the event ID on screen were event created by event Admin");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor View the event screen were event created by event Admin");
	}

	public void firstSPOnsorApproval() {

		driver.findElement(By.xpath("(//*[@aria-label='approval1Status'])")).click();
		btnSubmit1.click();
		System.out.println("First approval given successfully By Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by CA jurisdiction");
	}

	public void firstSPOnsorEventRejection() {
		WebElement element = driver
				.findElement(By.xpath("//*[@id=\"event-list\"]/table/tbody/tr[1]/td[9]/button"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(btnRejectApprovalFirst));
			btnRejectApprovalFirst.click();
			btnRejectApprovalRet.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnRejectApproval));
			btnRejectApproval.click();
			btnRejectApproval2.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		System.out.println("First Rejection approval given successfully By Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Rejection Approval given by CA jurisdiction");
	}

	public void SecondApprovalBySameJurisdiction() throws IOException {
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginCa();
		System.out.println("Step1: California SponsorLogin Successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		eventDetailsBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSecondApproval));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(popupBtn));
		popupBtn.click();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by CA jurisdiction");
	}

	public void SecondSponsorEventRejected() throws InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginCa();
		System.out.println("Step1: California Sponsor Login Successfully");
		try {
			login.handleSecurityQue();
			login.clickOnnext();
		} catch (Exception e1) {
			System.out.println("");
		}

		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");

		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();

		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		eventDetailsBtn.click();
		ObjectRepo.getTest().log(Status.PASS, "step 3 : Click on Event details toggle");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnRejectApprovalFirst));
			btnRejectApprovalFirst.click();
			btnRejectApprovalRet.click();

		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnRejectApproval));
			btnRejectApproval.click();

			btnRejectApproval2.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "step 4 : Click on Second Rejected approvel status button");
		wait.until(ExpectedConditions.elementToBeClickable(RejbtnSubmit2));
		RejbtnSubmit2.click();
		ObjectRepo.getTest().log(Status.PASS, "step 5 : Click on Submit2 button");
		wait.until(ExpectedConditions.visibilityOf(rejectPopup));
		WebElement abc11 = rejectPopup;
		ObjectRepo.getTest().log(Status.PASS, "step 5 : Click on Reject button");
		action.moveToElement(abc11).build().perform();
		action.clickAndHold(abc11).build().perform();
		action.click(abc11).build().perform();
		System.out.println("Second approval Rejected successfully By Sponsor ");
		ObjectRepo.getTest().log(Status.PASS, "Rejected Approval given by CA jurisdiction");
		ObjectRepo.getTest().log(Status.PASS, "Event Status Should be Displayed to be Rejected");
	}

	public void RollDownProcessForReserveSaleNewEvent() throws IOException, InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.sponsorLoginCa2();
		System.out.println("Step1: California Sponsor Login Successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforCAFromDropDown();
		ref.enterEventName();
		ref.currencyAccForReserveSaleForCA();
		CreateEventPage abc = new CreateEventPage();
		abc.selectTiers1();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are able to select Rolldown procedure option when only one category is selected");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are able to select Rolldown procedure option when only one category is selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when only one category is selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"Rolldown procedure cannot be selected when only one category is selected");
		}
		abc.selectTiers2();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when more than one categories are selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"Rolldown procedure cannot be selected when more than one categories are selected");
		}
	}

	public void EARollDownProcessForReserveSaleNewEvent() throws IOException, InterruptedException {

		PublicHomePage login = new PublicHomePage();
		login.EALoginCa();
		System.out.println("Step1: California Event Admin Login Successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforCAFromDropDown();
		ref.enterEventName();
		ref.currencyAccForReserveSaleForCA();
		CreateEventPage abc = new CreateEventPage();
		abc.selectTiers1();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are able to select Rolldown procedure option when only one category is selected");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are able to select Rolldown procedure option when only one category is selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when only one category is selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"Rolldown procedure cannot be selected when only one category is selected");
		}
		abc.selectTiers2();
		try {
			rollDownbids.click();
			System.out.println(
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
			ObjectRepo.getTest().log(Status.PASS,
					"AUP users are only able to select Rolldown procedure coption when there are more than one category selected");
		} catch (Exception e) {
			System.out.println("Rolldown procedure cannot be selected when more than one categories are selected");
			ObjectRepo.getTest().log(Status.FAIL,
					"Rolldown procedure cannot be selected when more than one categories are selected");
		}
	}

	public void CombinedEvaluationOfTiersForReserveSaleEvent() throws IOException, InterruptedException {

		PublicHomePage login = new PublicHomePage();
		login.sponsorLoginCa2();
		System.out.println("Step1: California Sponsor Login Successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforCAFromDropDown();
		ref.enterEventName();
		ref.currencyAccForReserveSaleForCA();
		CreateEventPage abc = new CreateEventPage();
		abc.selectTiers1();
		abc.selectTiers2();
		includebids.click();
		System.out.println("Sponsor Selected Include bids option");
		ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
	}

	public void EACombinedEvaluationOfTiersForReserveSaleEvent() throws IOException, InterruptedException {

		PublicHomePage login = new PublicHomePage();
		login.EALoginCa();
		System.out.println("Step1: California Event Admin Login Successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		ref.clickbtnCreateEvent();
		ref.gettextBoxEvent();
		ref.selectReserveSaleEventTypeforCAFromDropDown();
		ref.enterEventName();
		ref.currencyAccForReserveSaleForCA();
		CreateEventPage abc = new CreateEventPage();
		abc.selectTiers1();
		abc.selectTiers2();
		includebids.click();
		System.out.println("Sponsor Selected Include bids option");
		ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
	}

	public void getEventBasics() {
		// ----------------------------------------------------event basics

		wait.until(ExpectedConditions.elementToBeClickable(detailsEventID));
		detailsEventID.getText();
		System.out.println("On Event Creation Page Got Event Basics : " + detailsEventID.getText());
		detailsEventType.getText();
		System.out.println(detailsEventType.getText());
		detailsEventPJs.getText();
		System.out.println(detailsEventPJs.getText());
		detailsEventNameEng.getText();
		System.out.println(detailsEventNameEng.getText());
		detailsEventNameFrench.getText();
		System.out.println(detailsEventNameFrench.getText());
	}

	public void validatePJs() {
		PJs.getText();
		System.out.println("participating juridiction name : " + PJs.getText());
		textPJs.getText();
		System.out.println("participating juridiction name : " + textPJs.getText());
	}

	public void enterFrenchEventName() {

		eventNameFrench.sendKeys("EventAuto");// eventNameFrench
	}

	public void selectCurrencyAccWA() {
		WebElement abc3 = currencyAccWA;
		Actions action = new Actions(driver);
		action.moveToElement(abc3).build().perform();
		action.click(abc3).build().perform();
		driver.findElement(By.xpath("(//*[@type='checkbox'])[2]")).click();
	}

	public void selectCurrencyAccWA2() {
		WebElement abc3 = currencyAccWA;
		Select CADcurr = new Select(abc3);
		CADcurr.selectByVisibleText("USD");
	}

	public void errorCategoryNS() { // error message
		WebElement errcategorydropdown = dropdowntiersWA;
		Select abcd1 = new Select(errcategorydropdown);
		abcd1.selectByVisibleText("Category A"); // category A
		texttierspriceWA.sendKeys("0");
		texttotalallowanceWA.sendKeys("123");
		WebElement processorderdropdown = drpdownProCessOrder1WA;
		Select abc66 = new Select(processorderdropdown);
		abc66.selectByVisibleText("1");
	}

	public void errMsgCategory() { // category price should be greater than zero

		System.out.println("Error message for category prices :" + errmsgcategory.getText());
	}

	public void errorAllowanceNS() { // error message
		WebElement errcategorydropdown = dropdowntiersWA;
		Select abcd1 = new Select(errcategorydropdown);
		abcd1.selectByVisibleText("Category A"); // category A
		texttierspriceWA.sendKeys("12");
		texttotalallowanceWA.sendKeys("0");
		WebElement processorderdropdown = drpdownProCessOrder1WA;
		Select abc67 = new Select(processorderdropdown);
		abc67.selectByVisibleText("1");
	}

	public void errorMsgAllowance() { // category price should be greater than zero

		WebElement abc68 = errorallowance;
		System.out.println(abc68.getText());
		String actualabc68 = errorallowance.getText();
		String expectedabc68 = "Total Allowances Available should be > 0";
		Assert.assertEquals(actualabc68, expectedabc68);
		System.out.println("Error Message for Total Allowance available :" + errorallowance.getText());
	}

	public void selectDropDownTiersWA() { // ADD
		WebElement tiersdropdown = dropdowntiersWA;
		Select tierdrop1 = new Select(tiersdropdown);
		tierdrop1.selectByVisibleText("Tier One"); // Tier One
		WebElement processorderdropdown = drpdownProCessOrder1WA;
		Select abc66 = new Select(processorderdropdown);
		abc66.selectByVisibleText("1");
		texttierspriceWA.sendKeys("12");
		texttotalallowanceWA.sendKeys("1234");

		// ADD NEW ROW
		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD1 = btnaddnew;
		action.moveToElement(abcD1).build().perform();
		action.click(abcD1).build().perform();
		System.out.println("Tier Row is added");
		wait.until(ExpectedConditions.elementToBeClickable(IconDelTierRow));
		WebElement abcD2 = IconDelTierRow;
		action.moveToElement(abcD2).build().perform();
		action.click(abcD2).build().perform();
		System.out.println("Tier Row is deleted");
		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD3 = btnaddnew;
		action.moveToElement(abcD3).build().perform();
		action.click(abcD3).build().perform();
		System.out.println("Again Tier Row is added");
		WebElement tiersdropdown2 = dropdowntiers2WA;
		Select tiersdrop2 = new Select(tiersdropdown2);
		tiersdrop2.selectByVisibleText("Tier Two"); // Tiers 2
		texttiersprice2WA.sendKeys("12");
		texttotalallowance2WA.sendKeys("1234"); // total allowance 2
		WebElement processorderdropdown2 = drpdownProCessOrder2WA;// drpdownProCessOrder2NS
		Select abc77 = new Select(processorderdropdown2);
		abc77.selectByVisibleText("2");
		WebElement tiesrdropdown3 = dropdowntiers3WA;
		Select tiesdrop3 = new Select(tiesrdropdown3);
		tiesdrop3.selectByVisibleText("Tier Three"); // Tiers 3
		texttiersprice3WA.sendKeys("12");
		texttotalallowance3WA.sendKeys("1234"); // total allowance 3
		WebElement processorderdropdown3 = drpdownProCessOrder3WA;
		Select abc88 = new Select(processorderdropdown3);
		abc88.selectByVisibleText("3");
	}

	public void selectDropDownTiersWA1() { // ADD
		WebElement tiersdropdown = dropdowntiersWA;
		Select tierdrop1 = new Select(tiersdropdown);
		tierdrop1.selectByVisibleText("Tier One"); // Tier One
		WebElement processorderdropdown = drpdownProCessOrder1WA;
		Select abc66 = new Select(processorderdropdown);
		abc66.selectByVisibleText("1");
		texttierspriceWA.sendKeys("12");
		texttotalallowanceWA.sendKeys("1234");
		btnLimitTotalAllowanceBidperEntity.click();
		textTotalMaximumBidLimitperEntity.click();
		textTotalMaximumBidLimitperEntity.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity.sendKeys("1234");
		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD1 = btnaddnew;
		action.moveToElement(abcD1).build().perform();
		action.click(abcD1).build().perform();
		System.out.println("Tier Row is added");
		WebElement tiersdropdown2 = dropdowntiers2WA;
		Select tiersdrop2 = new Select(tiersdropdown2);
		tiersdrop2.selectByVisibleText("Tier Two"); // Tiers 2
		texttiersprice2WA.sendKeys("12");
		texttotalallowance2WA.sendKeys("1234"); // total allowance 2
		btnLimitTotalAllowanceBidperEntity2WA.click();
		textTotalMaximumBidLimitperEntity2WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity2WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity2WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity2WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity2WA.sendKeys("1234");
		WebElement processorderdropdown2 = drpdownProCessOrder2WA;// drpdownProCessOrder2NS
		Select abc77 = new Select(processorderdropdown2);
		abc77.selectByVisibleText("2");

		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD4 = btnaddnew;
		action.moveToElement(abcD4).build().perform();
		action.click(abcD4).build().perform();
		WebElement tiesrdropdown3 = dropdowntiers3WA;
		Select tiesdrop3 = new Select(tiesrdropdown3);
		tiesdrop3.selectByVisibleText("Tier Three"); // Tiers 3
		entertiersprice3WA.sendKeys("12");
		texttotalallowanceWA3.sendKeys("1234"); // total allowance 3
		btnLimitTotalAllowanceBidperEntity3WA.click();
		textTotalMaximumBidLimitperEntity3WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity3WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity3WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity3WA.sendKeys(Keys.BACK_SPACE);
		textTotalMaximumBidLimitperEntity3WA.sendKeys("1234");
		WebElement processorderdropdown3 = drpdownProCessOrder3WA;
		Select abc88 = new Select(processorderdropdown3);
		abc88.selectByVisibleText("3");
	}

	public void selectdeletecategory1() {
		deletecategory1.click();
		deletecategory2.click();
	}

	public void disableRadioRollDown() {// ------------------------------------------------DISABLE ROLL DOWN
		boolean disRadiobtn = radioRollDown1.isDisplayed();
		System.out.println("Status of Radio button 'Roll-Down Procedure' :" + disRadiobtn);
	}

	public void selectRadioRollDown() {
		WebElement radiobtnrolldown = radioRollDown1;
		radiobtnrolldown.click();
		boolean radiobtn = radioRollDown1.isSelected();
		System.out.println("Status of Selected Radio button 'Roll-Down Procedure' :" + radiobtn);
	}

	public void selectRadioBtnCombinedEvaluation() {

		WebElement radiobtnCombnEvalu = radiobtnCombnedEvaluation;
		radiobtnCombnEvalu.click();
		boolean radiobtn2 = radiobtnCombnedEvaluation.isSelected();
		System.out.println("Status of Selected Radio button 'Combined Evaluation of Tiers' :" + radiobtn2);
	}

	public void DeselectRadioBtnCombinedEvaluation() {

		WebElement radiobtnCombnEvalu = radiobtnCombnedEvaluation;
		radiobtnCombnEvalu.click();
		boolean radiobtn2 = radiobtnCombnedEvaluation.isSelected();
		System.out.println("Status of DeSelected Radio button 'Combined Evaluation of Tiers' :" + radiobtn2);
	}

	public void selectradioLimitMaximumBidAllowance() throws InterruptedException {
		WebElement radioLimitBid = radiobtnLmtMaxBid;
		radioLimitBid.click();
		boolean radiobtn1 = textLimitMxBid.isSelected();
		System.out.println("Selected Radio button 'Limit Maximum Bids Allowed' :" + radiobtn1);
		String abc21 = bydefaultLmtMaxBid.getAttribute("value");
		System.out.println("Default value for Limit Maximum Bids Allowed : " + abc21);

	}

	public void limitMxBidAllowed() throws InterruptedException {
		WebElement textbox = bydefaultLmtMaxBid;
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		bydefaultLmtMaxBid.sendKeys("230");// --------------------------edit event
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		System.out.println("The Sponsor can click on the +(Plus) button to increase to 10000.");
	}

	public void AllowancesPerBidLot() {
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(AllowancesPerBidLot));
		WebElement textbox = AllowancesPerBidLot;
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(AllowancesPerBidLot));
		AllowancesPerBidLot.click();
		WebElement textbox2 = AllowancesPerBidLot;
		act.doubleClick(textbox2).perform();
		AllowancesPerBidLot.sendKeys("2");// --------------------------edit event
		AllowancesPerBidLot1.click();
		System.out.println("The Sponsor can click on the -(Minus) button to reduce to 1.");
		AllowancesPerBidLot.click();
		WebElement textbox3 = AllowancesPerBidLot;
		act.doubleClick(textbox3).perform();
		wait.until(ExpectedConditions.visibilityOf(AllowancesPerBidLot));
		AllowancesPerBidLot.sendKeys("9998");// --------------------------edit event
		wait.until(ExpectedConditions.visibilityOf(AllowancesPerBidLot2));
		AllowancesPerBidLot2.click();
		AllowancesPerBidLot2.click();
		AllowancesPerBidLot2.click();
		System.out.println("The Sponsor can click on the +(Plus) button to increase to 10000.");
	}

	public void selectbtnSave() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
	}

	public void selectbtnCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(btncancel));
		btncancel.click();
	}

	public void selectbtnClear() {
		btnClear.getText();
		System.out.println("Event CLEAR :" + btnClear.getText());

		btnClear.click();
	}

	public void eventIDErrorMsg() {
		eventiderrormsg.click();
		System.out.println("Event cleared validation message : " + eventiderrormsg.getText());
	}

	public void eventAdminPage() {
		WebElement eventadminpage1 = eventadminpage;
		eventadminpage1.getText();
		System.out.println("Event CANCEL Validation : " + eventadminpage.getText());
	}

	public void clickOnGearControl() {
		wait.until(ExpectedConditions.elementToBeClickable(btngear));
		btngear.click();
		wait.until(ExpectedConditions.elementToBeClickable(btngear500));
		String btn500 = btngear500.getText();
		System.out.println(btn500);
		wait.until(ExpectedConditions.elementToBeClickable(btngear500));
		btngear500.click();
	}

	private void totalEventLists1() { // count the total number of events created
		List<WebElement> list = totalCountOfEvents;
		int totalEvents = list.size();
		System.out.println("Total no of event before event creation :" + totalEvents);
		for (int i = 0; i < totalEvents; i++) {
			list.get(i).getText();
		}
	}

	public void totalEventsAfterCreation() {
		List<WebElement> list2 = totalCountOfEvents;
		int totalEvents2 = list2.size();
		System.out.println("Total no of event after event creation :" + totalEvents2);
		for (int j = 0; j < totalEvents2; j++) {
			list2.get(j).getText();
		}
	}

	public void menuBarListOfEvent() {
		String actMenuBarEventType1 = MenuBarEventType.getText();
		System.out.println(actMenuBarEventType1);
		String expectMenuBarEventType1 = MenuBarEventType.getText();
		Assert.assertEquals(actMenuBarEventType1, expectMenuBarEventType1);
		String actMenuBarEventID = MenuBarEventID.getText();
		System.out.println(actMenuBarEventID);
		String expectMenuBarEventID = "EVENT ID";
		Assert.assertEquals(expectMenuBarEventID, actMenuBarEventID);
		String actMenuBarEventName = MenuBarEventName.getText();
		System.out.println(MenuBarEventName.getText());
		String expectMenuBarEventType3 = "EVENT NAME";
		Assert.assertEquals(actMenuBarEventName, expectMenuBarEventType3);
		String actMenuBarEventType4 = MenuBarEventDate.getText();
		System.out.println(actMenuBarEventType4);
		String expectMenuBarEventType4 = "EVENT DATE";
		Assert.assertEquals(actMenuBarEventType4, expectMenuBarEventType4);
		String actMenuBarEventType5 = MenuBarPJs.getText();
		System.out.println(actMenuBarEventType5);
		String expectMenuBarEventType5 = "PJs";
		Assert.assertEquals(actMenuBarEventType5, expectMenuBarEventType5);
		String actMenuBarEventType6 = MenuBarEventDate.getText();
		System.out.println(actMenuBarEventType6);
		String expectMenuBarEventType6 = "EVENT DATE";
		Assert.assertEquals(actMenuBarEventType6, expectMenuBarEventType6);
		String actMenuBarEventType7 = MenuBarCreationDate.getText();
		System.out.println(actMenuBarEventType7);
		String expectMenuBarEventType7 = "CREATION DATE";
		Assert.assertEquals(actMenuBarEventType7, expectMenuBarEventType7);
		String actMenuBarEventType8 = MenuBarEventStatus.getText();
		System.out.println(actMenuBarEventType8);
		String expectMenuBarEventType8 = "EVENT STATUS";
		Assert.assertEquals(actMenuBarEventType7, expectMenuBarEventType8);
		System.out.println("EVENT ADMINISTRATION PAGE - COLUMN TITLE GET SUCCESSFULLY ");
	}

	public void BtnNextEventDetails() { // go to event details page button-
		new Actions(driver);
		WebElement textbox = eventDetailsBtn;
		textbox.click();
	}

	public void firstApproval() {
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		try {
			action.click(element1).build().perform();
		} catch (Exception e) {
			action.click(element1).build().perform();
		}
		try {
			WebElement element2 = btnfirstApproval;
			js.executeScript("arguments[0].scrollIntoView();", element2);
			wait.until(ExpectedConditions.elementToBeClickable(btnFirstApproval));
			btnfirstApproval.click();
		} catch (Exception e) {
			btnfirstApproval.click();
		}
		try {
			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}
		WebElement firstApprovalSuccess = firstAppSuccess;
		firstApprovalSuccess.getText();
		System.out.println(firstApprovalSuccess.getText());
		String expectFirstAppSuccess = "The approval of the event creation has been proposed by the first sponsor. ";
		Assert.assertEquals(expectFirstAppSuccess,
				"The approval of the event creation has been proposed by the first sponsor. ");

	}

	public void secondApproval() {
		System.out.println("Navigate to particular event ");
		try {
			WebElement element1 = btnSecondApproval;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnSecondApproval));
			btnSecondApproval.click();
		} catch (Exception e) {
			btnSecondApproval.click();
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
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void RejectfirstApproval1() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		Actions act = new Actions(driver);
		WebElement textbox = eventDetailsBtn;
		act.doubleClick(textbox).perform();

		textbox.sendKeys(Keys.ENTER);
		selectbtnEditEvent();
		System.out.println("Navigate to particular event ");
		try {

			WebElement element1 = btnfirstDisApproval;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnfirstDisApproval));
			btnfirstDisApproval.click();
		} catch (Exception e) {

			btnfirstDisApproval.click();
		}
		try {

			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}
		alertDisputPopup.getText();
		System.out.println(alertDisputPopup.getText());
		ObjectRepo.getTest().log(Status.PASS, "The rejection of the event creation has been proposed by the first Sponsor.");
		System.out.println("First Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "First Sponsor rejected Event successfully");
	}

	public void RejectsecondApproval() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		new Actions(driver);
		WebElement textbox = eventDetailsBtn;
		textbox.click();
		System.out.println("Navigate to particular event ");
		try {

			WebElement element1 = btnSecondDisApproval;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnSecondDisApproval));
			btnSecondDisApproval.click();
		} catch (Exception e) {

			btnSecondDisApproval.click();
		}
		try {

			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}
		WebElement abc1 = rejectBtn;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();

		System.out.println("Second Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Sponsor rejected Event successfully");

	}

	public void checkStatus() {
		String stat1 = eventAppStatus.getText();
		Assert.assertEquals(stat1, "APPROVED");
		System.out.println("Event status : " + eventAppStatus.getText());
	}

	public void checkStatus2() {
		String stat2 = alertEventStatus.getText();
		Assert.assertEquals(stat2, "The event status has been approved.");
		System.out.println("Event Approval success message : " + alertEventStatus.getText());
	}

	public void validatePrices() {
		String sbc = validatetier1Allowances.getText();
		Assert.assertEquals(sbc, "Tier One USD 12.00 1,234 1");
		System.out.println("Tier One details :" + validatetier1Allowances.getText());
		String sbc1 = validatetier2Allowances.getText();
		Assert.assertEquals(sbc1, "Tier Two USD 12.00 1,234 2");
		System.out.println("Tier Two details :" + validatetier2Allowances.getText());
		String sbc2 = validatetier3Allowances.getText();
		Assert.assertEquals(sbc2, "Tier Three USD 12.00 1,234 3");
		System.out.println("Tier Three details :" + validatetier3Allowances.getText());
	}

	public void selectbtnEditEvent() {

		btnEditEvent.click();
		System.out.println("Step : Click on The Edit Event button ");
		Actions act = new Actions(driver);
		WebElement textbox = eventName;
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.BACK_SPACE);
		eventName.sendKeys("EVENTAUTOMATIONDEMOmac");// ---------------------------EDIT EVENT
		btnSave.click();
		alertEditEventSucess.getText();
		System.out.println("Edit event Success Message :" + alertEditEventSucess.getText());
		closePopupEditEvent.click();
	}

	public void selectEventDetails() {
		btnEventARLogin.click();
		selectEvent.click();
		bidGuaranteeAmt.getText();
		System.out.println("The Auction Reserve price for NS is displayed only in CAD : " + bidGuaranteeAmt.getText());
	}

	public void Logout() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
			btnProfile.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
			btnLogout.click();
		} catch (Exception e) {
			System.out.println("Not able to logout.");
		}
	}

	public void createEvent() throws Exception {

		clickbtnAdmin();
		System.out.println("Step 1: Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2: Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 5: Click on CREATE NEW EVENT Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Click on Create New Event Button  ");
		generateString();
		gettextBoxEvent();
		System.out.println("Step 6: Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event ID  ");
		selectEventTypeFromDropDownForReserveSale();
		System.out.println("Step 7: Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Event Type  ");
		enterEventName();
		enterFrenchEventName();
		System.out.println("Step 8: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter the Event Name  ");
		selectCurrencyAccWA2();// currencyAcc
		System.out.println("Step 9: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDropDownTiersWA(); // Tiers //allowance //processing order
		System.out.println("Step 10: Select the Tiers Type ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Tiers Type");
		selectDates();
		System.out.println("Step 11: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 11: Select the Dates ");
		AllowancesPerBidLot();
		System.out.println("Step 12: Select Number of allowances per bid lot ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Select Number of allowances per bid lot ");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		limitMxBidAllowed();
		System.out.println("Step 14: select Limit Maximum Bids Allowed ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: select Limit Maximum Bids Allowed ");
		selectRadioRollDown();
		System.out.println("Step 15: Select the Roll Down radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 15: Select the Roll Down radio button ");
		selectbtnSave();
		System.out.println("Step 16: Click on the Save button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 16: Click on the Save button ");
		validatePopupforEventCreation();
		System.out.println("Step 17: Validate popup - The event has been created successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Step 17: Validate popup - The event has been created successfully. ");
		Logout();
		System.out.println("Washington Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Washington Logout successfully ");
	}

	public void createEventWATC() throws Exception {
		clickbtnAdmin();
		System.out.println("Step 1: Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2: Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 5: Click on CREATE NEW EVENT Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Click on Create New Event Button  ");
		generateString();
		gettextBoxEvent();
		System.out.println("Step 6: Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event ID  ");
		selectEventTypeFromDropDownForReserveSale();
		System.out.println("Step 7: Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Event Type  ");
		enterEventName();
		enterFrenchEventName();
		System.out.println("Step 8: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter the Event Name  ");
		selectCurrencyAccWA2();// currencyAcc
		System.out.println("Step 9: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectTiers1RS_WA();
		selectTiers2RS_WA();
		selectTiers3RS_WA();
		System.out.println("Step 10: Select the Tiers Type ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Tiers Type");
		selectDates();
		ObjectRepo.getTest().log(Status.PASS, "step 11 : Entered dates for all the mandatory fields");
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys(Keys.CONTROL + "A");
		driver.findElement(By.xpath("//input[@name='allowancesPerBidLot']")).sendKeys("999");
		try {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 12 : Selected include bids option");
		} catch (Exception e) {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 12 : Selected Roll down bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");
		System.out.println("Step 17: Validate popup - The event has been created successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Step 17: Validate popup - The event has been created successfully. ");
		Logout();
		System.out.println("Washington Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Washington Logout successfully ");
	}

	public void CombinedevaluationofTiers() throws Exception {

		clickbtnAdmin();
		System.out.println("Step 1: Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2: Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");

		clickbtnCreateEvent();
		System.out.println("Step 3: Click on CREATE NEW EVENT Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Click on Create New Event Button  ");

		generateString();
		gettextBoxEvent();
		System.out.println("Step 4: Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event ID  ");
		selectEventTypeFromDropDown();
		driver.findElement(By.xpath("(//div[@id='demo-customized-select'])[1]")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		System.out.println("Step 5: Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Event Type  ");
		enterEventName();
		System.out.println("Step 6: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter the Event Name  ");
		selectCurrencyAccWA();// currencyAcc
		WebElement element = eventNameFrench;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		enterFrenchEventName();
		driver.findElement(By.xpath("//*[@placeholder='USD']")).click();
		driver.findElement(By.xpath("//*[@placeholder='USD']")).sendKeys("10");
		System.out.println("Step 7: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDropDownTiersWA(); // Tiers //allowance //processing order
		System.out.println("Step 8: Select the Tiers Type ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Tiers Type");
		selectDates();
		System.out.println("Step 9: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Select the Dates ");
		AllowancesPerBidLot();
		System.out.println("Step 10: Select Number of allowances per bid lot ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Select Number of allowances per bid lot ");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 11: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		limitMxBidAllowed();
		System.out.println("Step 12: select Limit Maximum Bids Allowed ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: select Limit Maximum Bids Allowed ");

		selectRadioBtnCombinedEvaluation();
		System.out.println("Step 13: Select/Activate combined evaluation of tiers radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Select/Activate combined evaluation of tiers radio button ");

		DeselectRadioBtnCombinedEvaluation();
		System.out.println("Step 14: Deselect/Deactivate combined evaluation of tiers radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Deselect/Deactivate combined evaluation of tiers radio button ");

		selectRadioBtnCombinedEvaluation();
		System.out.println("Step 15: Select/Activate combined evaluation of tiers radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Select/Activate combined evaluation of tiers radio button ");

		selectbtnSave();
		System.out.println("Step 14: Click on the Save button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 16: Click on the Save button ");

		validatePopupforEventCreation();
		System.out.println("Step 15: Validate popup - The event has been created successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Step 17: Validate popup - The event has been created successfully. ");

		Logout();
		System.out.println("Washington Logout successfully");
		ObjectRepo.getTest().log(Status.PASS, "Washington Logout successfully ");
	}

	public void firstApprovalEvent() throws IOException { // event details page
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on Event ");
		BtnNextEventDetails();
		System.out.println("Step : Click on The 'Go to details of Event ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on The 'Go to details of Event ");
		validatePrices();
		System.out.println("Step : Validate the Tiers prices, Total allowances per tiers ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Validate the Tiers prices, Total allowances per tiers ");
		System.out.println("Event Creation time : " + validateTime.getText());
		System.out.println(
				"Step : Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		ObjectRepo.getTest().log(Status.PASS,
				" Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		selectbtnEditEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on The Edit Event button ");
		firstApproval();// firstApproval
		System.out.println("Step : First Approval Given Successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Step : First Approval Given Successfully ");
		System.out.println(" Nova Scotia Logout Successfully ");
		ObjectRepo.getTest().log(Status.PASS, " Nova Scotia Logout successfully");
	}

	public void secondApprovalEvent() throws IOException {
		ObjectRepo.getTest().log(Status.PASS, " Nova Scotia Second sponsor login successfully");

		clickbtnAdmin();
		System.out.println("Step : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Step : Click on Event ");
		// event details validation

		BtnNextEventDetails();
		System.out.println("Step : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, "Step :  Click on Event Details Button");

		secondApproval();
		System.out.println("Step : Washington Second approval given successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Nova Scotia Second approval given successfully ");

		checkStatus();
		checkStatus2();

		System.out.println("Event Creation time : " + validateTime.getText());
		System.out.println(
				"Step : Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		ObjectRepo.getTest().log(Status.PASS,
				" Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		System.out.println("Washington Logout successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Washington Second Logout successfully ");
	}

	public void reApprovalEvent() throws IOException {

		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		BtnNextEventDetails();
		validatePrices();
		System.out.println("Step  : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, " Step  : Click on Event Details Button ");
		selectbtnEditEvent();
		ObjectRepo.getTest().log(Status.PASS, " Step  : Event Edit Successfully ");
		firstApproval();
		System.out.println("Step  : First Re-Approval Given Successfully");
		ObjectRepo.getTest().log(Status.PASS, " First Sponsor Re-Approve Event Successfully ");
		Logout();
		System.out.println("Nova Scotia Second Logout successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Nova Scotia Second Logout successfully ");
	}

	public void DisapprovalEvent2() {

		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		Actions act = new Actions(driver);
		WebElement textbox = eventDetailsBtn;
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.ENTER);
		System.out.println("Navigate to particular event ");
		try {

			WebElement element1 = btnSecondDisApproval;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnSecondDisApproval));
			btnSecondDisApproval.click();
		} catch (Exception e) {

			btnSecondDisApproval.click();
		}
		try {

			btnSubmit1.click();
		} catch (Exception e) {

			btnSubmit2.click();
		}

		WebElement abc1 = rejectBtn;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();

		alertDisputPopup.getText();
		System.out.println(alertDisputPopup.getText());

		eventDisapprovalStatus.getText();
		System.out.println(eventDisapprovalStatus.getText());
		System.out.println("Second Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Sponsor rejected Event successfully");

	}

	public void Eventcancel() throws Exception {
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step 1 : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Step 2 : Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step  : Click on Create New Event Button");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on Create New Event Button");
		generateString();
		gettextBoxEvent();
		System.out.println("Step  : Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Enter the Event ID  ");
		selectEventTypeFromDropDownForReserveSale();
		System.out.println("Step  : Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Select the Event Type  ");
		enterEventName();
		System.out.println("Step  : Enter the Event Name  ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Enter the Event Name  ");
		selectCurrencyAccWA();// currencyAcc--CAD
		System.out.println("Step  : Select Currency Accepted   ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :  Select Currency Accepted ");
		selectDropDownTiersWA1();
		System.out.println("Step : Select Type of Tiers  ");
		ObjectRepo.getTest().log(Status.PASS, "Step :  Select Type of Tiers  ");
		selectDates();
		System.out.println("Step  : Enter the Event Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Enter the Event Dates ");
		selectRadioRollDown();// ----------------------------------------------------------------------Roll
		System.out.println("Step  : Select the Radio Button 'Roll Down'");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Select the Radio Button 'Roll Down'");
		AllowancesPerBidLot();
		System.out.println("Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'  ");
		selectbtnCancel();
		System.out.println("Washington event cancelled successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Washington event cancelled successfully");
		eventAdminPage();
		Logout();
	}

	// EVENT CLEAR --------------------------------
	public void Eventclear() throws Exception {
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent();
		System.out.println("Step : Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, " Enter the Event ID  ");
		selectEventTypeFromDropDown();
		System.out.println("Step : Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Event Type  ");
		enterEventName();

		System.out.println("Step : Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, " Enter the Event Name  ");
		selectCurrencyAccWA();// currencyAcc
		System.out.println("Step  : Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Currency Accepted ");
		selectDropDownTiersWA();
		System.out.println("Step : Select the Category Type ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Category Type ");
		selectDates();
		System.out.println("Step : Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Dates ");
		selectdeletecategory1();
		System.out.println("Step : Click on Delete Category ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Delete Category");
		disableRadioRollDown();
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step : Select the Radio Button 'Limit Maximum Bid Allowances' ");
		ObjectRepo.getTest().log(Status.PASS, "  Select the Radio Button 'Limit Maximum Bid Allowances' ");
		selectRadioBtnCombinedEvaluation();
		System.out.println("Step : Select the Radio Button for Combined Evaluation of Category  ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Radio Button for Combined Evaluation of Category   ");
		selectbtnClear();
		System.out.println("Step : Click on Clear Button  ");
		ObjectRepo.getTest().log(Status.PASS, "  Click on Clear Button  ");
		selectbtnSave();
		System.out.println("Step :  Click on Save Button   ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Save Button ");
		eventIDErrorMsg();
		System.out.println("Step :  Error message validated  ");
		ObjectRepo.getTest().log(Status.PASS, " Error message validated ");

		Logout();
	}

	public void completelistofevents() throws IOException { // event details page
		clickbtnAdmin();
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickOnGearControl();// clickOnGearControl
		ObjectRepo.getTest().log(Status.PASS, "Step 3: Click on 'Select Number Of Rows Per Page ");
		menuBarListOfEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Get the Tittle Of the Column on Event Administration  ");
		totalEventLists1();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Get Total Number Of Events ");
		Actions act123 = new Actions(driver);
		WebElement textbox = eventDetailsBtn;
		act123.click().perform();
		textbox.sendKeys(Keys.ENTER);
		eventDetailsBtn.click();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Click on The 'Go to details of Event ");
		validatePrices();
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Validate the Category Prices and Total Allowance Available ");
		Logout();
		ObjectRepo.getTest().log(Status.PASS, "Logout Successfully ");
	}

	public void validateErrMsgAllowance() throws Exception {
		clickbtnAdmin();
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickbtnCreateEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 3: Click on Create Event Button ");
		generateString();
		gettextBoxEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");

		selectCurrencyAccWA();// currencyAcc
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Currency Accepted ");
		errorAllowanceNS();
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter invalid Total Allowance Available ");

		selectbtnSave();
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Click on Save Button ");

		errorMsgAllowance();
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Got Error Message for Total Allowance Available ");
	}

	public void validateErrMsgCategory() throws Exception {
		clickbtnAdmin();
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickbtnCreateEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 3: Click on Create Event Button ");
		generateString();
		gettextBoxEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		selectCurrencyAccWA();// currencyAcc
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Currency Accepted ");
		errorCategoryNS();
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Enter invalid Category Prices ");
		selectbtnSave();
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Click on Save Button ");
		errMsgCategory();
		ObjectRepo.getTest().log(Status.PASS, "Step 16: Got Error Message for Category Prices ");
		selectbtnClear();
		ObjectRepo.getTest().log(Status.PASS, "Step 11: Click on Clear Button ");
	}

	public void getEventCreationPageBasicDetails() throws IOException {
		clickbtnAdmin();
		System.out.println("Step 1 : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2 : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 3 : Click on Event Creation Page ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event Creation Page ");
		getEventBasics();
		System.out.println("Step 4 : On the Event creation screen, basic fields Get Successfully displayed");
		ObjectRepo.getTest().log(Status.PASS, " On the Event creation screen, basic fields Get Successfully displayed");
	}

	public void LimitMxNoOfBids() throws IOException, InterruptedException {
		clickbtnAdmin();
		System.out.println("Step 1 : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2 : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 3 : Click on Event Creation Page ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event Creation Page ");
		generateString();
		gettextBoxEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		validatePJs();// -----------------------------------------participating jurisdiction
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		enterFrenchEventName();
		System.out.println("Step 7: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Enter the Event Name  ");
		selectCurrencyAccWA();// currencyAcc
		System.out.println("Step 8: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Select the Currency Accepted ");
		selectDropDownTiersWA(); // category //allowance //processing order
		System.out.println("Step 9: Select the Currency Accepted ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDates();
		System.out.println("Step 10: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Dates ");
		selectRadioRollDown();
		System.out.println("Step 11: Select the Roll Down radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 11: Select the Roll Down radio button ");

		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 12: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Click on  the Limit Maximum Bids Allowed radio button ");
		limitMxBidAllowed();
		System.out.println("Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed' ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed'");
	}

	public void getBiddingPageDetails() {

		selectEventDetails();
	}

	public void limitthenumberofallowancesperbidLOT() throws IOException, InterruptedException {

		clickbtnAdmin();
		System.out.println("Step 1 : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2 : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 3 : Click on Event Creation Page ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event Creation Page ");
		generateString();
		gettextBoxEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		enterFrenchEventName();
		System.out.println("Step 7: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Enter the Event Name  ");
		selectCurrencyAccWA();// currencyAcc
		System.out.println("Step 8: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Select the Currency Accepted ");
		selectDropDownTiersWA(); // category //allowance //processing order
		System.out.println("Step 9: Select the Currency Accepted ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDates();
		System.out.println("Step 10: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Dates ");
		selectRadioRollDown();
		System.out.println("Step 11: Select the Roll Down radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 11: Select the Roll Down radio button ");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 12: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Click on  the Limit Maximum Bids Allowed radio button ");
		AllowancesPerBidLot();// ---------------------------------------------------allowances per bid lot
		System.out.println("Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed' ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed'");
	}

	public void CaliforniaJuridictionRejectFirstApprovalForEventCreationByFirstSponsor() {
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		action.click(element1).build().perform();

		String msg = driver.findElement(By.xpath("//*[text()='Approvals by Jurisdictions for Event Creation']"))
				.getText();
		System.out.println(msg);
		wait.until(ExpectedConditions.visibilityOf(btnRejectApproveCal));
		btnRejectApproveCal.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();

		System.out.println("First Sponsor Rejected succesfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval Rejected by california Sponsor");
		driver.navigate().refresh();
		btnProfile.click();
		btnLogout.click();
		ObjectRepo.getTest().log(Status.PASS, "California Sponsor logout Successfully");
		System.out.println("California Sponsor logout Successfully");
	}

	public void QuebecJuridictionRejectedForEventCreation() {
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		action.click(element1).build().perform();
		wait.until(ExpectedConditions.visibilityOf(btnRejectApproveQue));
		btnRejectApproveQue.click();
		btnRejectApproveQue.click();
		try {
			wait.until(ExpectedConditions.visibilityOf(btnSubmit2));
			btnSubmit2.click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//button//span[text()='SUBMIT']")).click();
		}

		System.out.println("Second Sponsor Rejected succesfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval Rejected by Quebec Sponsor");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
		btnProfile.click();
		btnLogout.click();
		ObjectRepo.getTest().log(Status.PASS, "Quebec Sponsor logout Successfully");
		System.out.println("Quebec Sponsor logout Successfully");
	}

	public void createEventForReserveSaleForR70() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		generateString();
		gettextBoxEventForR70();
		selectReserveSaleEventTypeforQCFromDropDown();
		enterEventNameForRE1_R70();
		enterfrenchEventNameForReserveSaleR70();
		currencyAccForReserveSaleForQC();
		selectTiers1_R70();
		selectTiers2_R70();
		selectTiers3_R70();
		ObjectRepo.getTest().log(Status.PASS, "step 9 : Selected 3 tiers");
		selectDatesReservesaleCA1();
		ObjectRepo.getTest().log(Status.PASS, "step 10 : Entered dates for all the mandatory fields");
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			includebids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected include bids option");
		}
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		String msg = successmsg.getText();
		System.out.println(msg);
		Assert.assertEquals("The event has been created successfully.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Step 12 : The reserve sale event has been created successfully");

	}
}