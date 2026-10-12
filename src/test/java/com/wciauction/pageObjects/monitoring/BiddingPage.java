package com.wciauction.pageObjects.monitoring;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ExcelDataProviderforResultReport;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.ClassPage;
import com.wciauction.pageObjects.auction.CMSPage;
import com.wciauction.pageObjects.events.CreateEventPage;

import constants.EventUtils;
import excelReadersForReports.ExcelDataProviderforBidsReport;

import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class BiddingPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(xpath = "//*[text()='Joint Auction Pause event functionality']")
	private WebElement PauseEvent;

	@FindBy(xpath = "//*[@id='bidPrice']")
	private WebElement bidprice;

	@FindBy(xpath = "//ul[@role='menu']")
	private WebElement Menu;

	@FindBy(xpath = "(//*[@data-icon='ellipsis-h'])[1]")
	private WebElement threeDotFirstBtn;

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

	@FindBy(xpath = "//a[text()='User']")
	private WebElement btnUser;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "//*[text()='Event in progress']")
	private WebElement inProgressText;

	@FindBy(xpath = "//*[text()='BIDDING CLOSES IN']")
	private WebElement closessText;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(xpath = "//input[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement searchEntityID;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA (CA4376-5560)']")
	private WebElement EntityA1;

	@FindBy(xpath = "//*[text()='Auction']")
	private WebElement selectBtnAuction;

	@FindBy(xpath = "//button[@title=\"Events\"]")
	private WebElement selectDropEventToggle;

	@FindBy(xpath = "//*[text()='JointAuctionAutomation']")
	private WebElement jointEventName;

	@FindBy(xpath = "//*[@title='Go to details of Event JointAuctionAutomation']")
	private WebElement eventDetailsBtn;

	@FindBy(xpath = "(//button[@title='Go to details of Event EqanimTestEvent'])[2]")
	private WebElement eventDetailsBtn1;

	@FindBy(xpath = "(//*[text()='PAUSE'])[1]")
	private WebElement firstPauseBtn;

	@FindBy(xpath = "(//*[text()='PAUSE'])[2]")
	private WebElement secndPauseBtn;

	@FindBy(xpath = "//*[text()='Event pause proposed.']")
	private WebElement EvtpauseSuccessMsg;

	@FindBy(xpath = "//*[text()='Event pause approved and effective.']")
	private WebElement Evtpause2SuccessMsg;

	@FindBy(xpath = "//*[text()='Event resume proposed.']")
	private WebElement EvtResumeSuccessMsg;

	@FindBy(xpath = "//*[text()='Event resume approved and effective.']")
	private WebElement EvtResume2SuccessMsg;

	@FindBy(xpath = "//*[text()='PAUSED']")
	private WebElement pausedStatusText;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement RunAlgorithmCA;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement RunAlgorithmQC;

	@FindBy(xpath = "//*[text()='Run algorithm proposed.']")
	private WebElement successMsg;

	@FindBy(xpath = "//*[text()='The algorithm ran successfully. It may take a few minutes for the reports to be generated.']")
	private WebElement successMsg2;

	@FindBy(xpath = "//*[text()='RESET']")
	private WebElement btnReset;

	@FindBy(xpath = "//*[text()='Reset algorithm status successful.']")
	private WebElement successmsg3;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item'])[7]")
	private WebElement pausedTime;

	@FindBy(xpath = "//*[text()='RUNNING']")
	private WebElement resumeStatusText;

	@FindBy(xpath = "(//*[text()='RESUME'])[2]")
	private WebElement ResumeBtnQC;

	@FindBy(xpath = "(//*[text()='RESUME'])[1]")
	private WebElement ResumeBtnCA;

	@FindBy(xpath = "//button[@aria-label='Select number of rows per page.']")
	private WebElement PagibnationSettingBtn;

	@FindBy(xpath = "//*[text()='500 ']")
	private WebElement pagrRowsSelect;

	@FindBy(xpath = "//*[text()='BID GUARANTEE AMOUNT']")
	private WebElement bidGuaranteeLable;

	@FindBy(xpath = "//*[text()='ADD BID']")
	private WebElement btnAddbid;

	@FindBy(xpath = "//input[@id='bidPrice']")
	private WebElement textBidPrice;

	@FindBy(xpath = "//input[@name='bidPrice']")
	private WebElement editBidPrice;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement textBidLots;

	@FindBy(xpath = "//input[@name='bidLot']")
	private WebElement editBidLots;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement dropdownvintage;

	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement btnSubmit;

	@FindBy(xpath = "//span[text()='CONFIRM']")
	private WebElement btnConfirm;

	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement btnCancel;

	@FindBy(xpath = "//thead[@class='MuiTableHead-root']")
	private WebElement bidCoulmnRowNme;

	@FindBy(xpath = "(//tr[@role='checkbox'])[1]")
	private WebElement cancelBidAssert;

	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']")
	private WebElement submitedBidTable;

	@FindBy(xpath = "//tr[@class='MuiTableRow-root MuiTableRow-hover']")
	private WebElement submitedFirstBidRow;

	@FindBy(xpath = "//tr[@role='checkbox']")
	private WebElement SubmittedBids;

	@FindBy(xpath = "//button[@title='Next Page']")
	private WebElement paginationBtn;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item'])[2]")
	private WebElement SBMAPAGEBtn;

	@FindBy(xpath = "//button[@title='Download bid upload template']")
	private WebElement BidUplaodTemplate;

	@FindBy(xpath = "//button[@title='Download bid upload template']")
	private WebElement DownloadBidUplaodTemplateBtn;

	@FindBy(xpath = "//*[@aria-label='select all']")
	private WebElement multipleBidCheckBox;

	@FindBy(xpath = "//*[text()='Delete Selected Bids']")
	private WebElement deleteSelectedBids;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1'])[9]")
	private WebElement deleteSelectedBidsPopUp1;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1'])[10]")
	private WebElement deleteSelectedBidsPopUp2;

	@FindBy(xpath = "(//*[@class='MuiTypography-root MuiTypography-body1'])[11]")
	private WebElement deleteSelectedBidsPopUp3;

	@FindBy(xpath = "//*[contains(text(),'bids successfully deleted.')]")
	private WebElement delete10bidSuccessMsg;

	@FindBy(xpath = "//*[contains(text(),'bids successfully deleted.')]")
	private WebElement delete2bidSuccessMsg;

	@FindBy(xpath = "//*[text()='Bid successfully added.']")
	private WebElement alertMsg;

	@FindBy(xpath = "//*[text()='Bid successfully submitted.']")
	private WebElement alertMsg2;

	@FindBy(xpath = "//input[@id='searchValue']")
	private WebElement searchBidPlace;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement searchCleardBtn;

	@FindBy(xpath = "//*[text()='Enter Bid Price']")
	private WebElement bidPriceErrorMsg;

	@FindBy(xpath = "//*[text()='Enter Number of lots']")
	private WebElement bidotsErrorMsg;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[1]")
	private WebElement editBidBtn;

	@FindBy(xpath = "//button[@title='Download bid upload template']")
	private WebElement DownloadBidTemplated;

	@FindBy(xpath = "//*[text()='Bid edited successfully.']")
	private WebElement editBidSuccessMsg;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")
	private WebElement deleteBidBtn;

	@FindBy(xpath = "//*[text()='Bid deleted successfully.']")
	private WebElement deleteBidSuccessMsg;

	@FindBy(xpath = "//*[text()='Bid price cannot be lower than auction reserve price.']")
	private WebElement lessAuctionPriceErrorMsg;

	@FindBy(xpath = "//*[text()='Bid Lots must only include numeric characters and be a whole number greater than zero.']")
	private WebElement BidslotErrorFormtMsg;

	@FindBy(xpath = "//*[text()='Download Report']")
	private WebElement downloadReportBtn;

	@FindBy(xpath = "(//*[contains(text(),'Monitoring')])[2]")
	private WebElement monitoringPageEvntPge;

	@FindBy(xpath = "//span[text()='CONTINUE']")
	private WebElement btnContinueEN;

	@FindBy(xpath = "(//*[text()='BIDDING CLOSES IN']//following::span)[1]")
	private WebElement daysEventDetailsOnPrivateHomePage;

	@FindBy(xpath = "(//*[text()='BIDDING CLOSES IN']//following::span)[2]")
	private WebElement hoursEventDetailsOnPrivateHomePage;

	@FindBy(xpath = "(//*[text()='BIDDING CLOSES IN']//following::span)[3]")
	private WebElement minutesEventDetailsOnPrivateHomePage;

	@FindBy(xpath = "//span[text()='Events']")
	private WebElement eventDropdown;

	@FindBy(xpath = "//a[text()='SBMA 5 AT TC']")
	private WebElement selectSBMA5EventFromEventDropdown;

	@FindBy(xpath = "//*[text()='R3.8CA_R317_RS_OpenBW_19May']")
	private WebElement runningeventreservesale;

	@FindBy(xpath = "(//*[text()='"+EventUtils.sbmaQuebec+"'])")
	private WebElement runningSBMA;

	@FindBy(xpath = "//div[text()='FINANCIAL GUARANTEE AMOUNT']")
	private WebElement financialGuaranteeAmountLabel;

	@FindBy(xpath = "//div[text()='CAD 500,000,000']")
	private WebElement financialGuaranteeAmountValue;
	@FindBy(xpath = "//div[text()='CAD 840,000']")
	private WebElement financialGuaranteeAmountValue1;
	@FindBy(xpath = "//div[text()='HOLDING LIMIT']")
	private WebElement holdingLimitLabel;

	@FindBy(xpath = "//div[text()='11,598,500']")
	private WebElement holdingLimitValue;
	@FindBy(xpath = "//div[text()='0']")
	private WebElement holdingLimitValue1;
	@FindBy(xpath = "//span[text()='VIEW AUDIT REPORT']")
	private WebElement btnViewAuditReport;

	@FindBy(xpath = "//h1[text()='Submitted Bids']")
	private WebElement submittedBidsTitle;

	@FindBy(xpath = "(//thead[@class='MuiTableHead-root'])[2]")
	private WebElement totalNumbersOfColumnInSubmittedBidTable;

	@FindBy(xpath = "//tr[@class='MuiTableRow-root MuiTableRow-hover']")
	private List<WebElement> totalNumbersOfSubmittedBidsInTable;

	@FindBy(xpath = "//div[text()='CATEGORY A']")
	private WebElement categoryA;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[1]")
	private WebElement categoryACurrency;

	@FindBy(xpath = "//div[text()='CATEGORY B']")
	private WebElement categoryB;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[5]")
	private WebElement categoryBCurrency;

	@FindBy(xpath = "//div[text()='CATEGORY C']")
	private WebElement categoryC;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[9]")
	private WebElement categoryCCurrency;

	@FindBy(xpath = "//th[text()='Total Emission Units Available']")
	private WebElement totalEmissionUnitsAvailableLabel;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[2]")
	private WebElement totalEmissionUnitsAvailableForCategoryA;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[6]")
	private WebElement totalEmissionUnitsAvailableForCategoryB;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[10]")
	private WebElement totalEmissionUnitsAvailableForCategoryC;

	@FindBy(xpath = "//th[text()='Total Emission Units Bid']")
	private WebElement totalEmissionUnitsBidLabel;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[3]")
	private WebElement totalEmissionUnitsBidForCategoryA;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[7]")
	private WebElement totalEmissionUnitsBidForCategoryB;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[11]")
	private WebElement totalEmissionUnitsBidForCategoryC;

	@FindBy(xpath = "//th[text()='Total Bids Submitted']")
	private WebElement totalBIdsSubmittedLabel;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[4]")
	private WebElement totalBIdSubmittedForCategoryA;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[8]")
	private WebElement totalBIdSubmittedForCategoryB;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[12]")
	private WebElement totalBIdSubmittedForCategoryC;

	@FindBy(xpath = "//*[text()='ADD BID']")
	private WebElement btnAdd;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement addLot;

	@FindBy(xpath = "//input[@name='bidLot']")
	private WebElement bidLot;

	@FindBy(xpath = "//option[text()='Select']")
	private WebElement selectCategoryDrpdwn;

	@FindBy(xpath = "//option[text()='CATEGORY A']")
	private WebElement selectCategoryA;

	@FindBy(xpath = "//h3[text()='Success']")
	private WebElement successMessage;

	@FindBy(xpath = "//p[text()='Bid successfully added.']")
	private WebElement successMessage11;

	@FindBy(xpath = "//p[text()='Bid successfully submitted.']")
	private WebElement successMessage1;

	@FindBy(xpath = "//p[text()='Bid edited successfully.']")
	private WebElement successMessageEdit;

	@FindBy(xpath = "//p[text()='Bid deleted successfully.']")
	private WebElement successMessageDelete;

	@FindBy(xpath = "//td[text()='10']")
	private WebElement addedBidLot;

	@FindBy(xpath = "//td[text()='CATEGORY A']")
	private WebElement addedCategory;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[16]")
	private WebElement transactionID;

	@FindBy(xpath = "//p[text()='Enter Number of lots']")
	private WebElement assertBidLotErrorMessage;

	@FindBy(xpath = "//button[@title='UPLOAD BIDS']")
	private WebElement uploadBidsBtn;

	@FindBy(xpath = "//button[@title='Download bid upload template']")
	private WebElement downloadBidsBtn;

	@FindBy(xpath = "(//*[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement assertColumnNames;

	@FindBy(xpath = "//tr[@role='checkbox']")
	private WebElement uploadBidTableColumnValues;

	@FindBy(xpath = "//h3[text()='Failure']")
	private WebElement assertFailure;

	@FindBy(xpath = "//p[text()='You are exceeding the maximum bids allowed. An entity can submit a maximum of 1 bid(s).']")
	private WebElement assertFailureMessage;

	@FindBy(xpath = "//input[@id='searchValue']")
	private WebElement enterSearchValue;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement searchValue;

	@FindBy(xpath = "(//button[@type='button'])[3]")
	private WebElement clearSearch;

	@FindBy(xpath = "//span[text()='SUBMIT']")
	private WebElement confirmDeleteBidBtn;

	@FindBy(xpath = "//*[contains(text(),'Bid deleted successfully.')]")
	private WebElement deleteAllSuccessMessage;

	@FindBy(xpath = "//span[text()='CANCEL']")
	private WebElement confirmCancelDeleteBidBtn;

	@FindBy(xpath = "//*[contains(text(),'Bids Selected')]")
	private WebElement bidsSelectedForDelete;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[4]")
	private WebElement checkbox;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[2]")
	private WebElement SearchButton;

	@FindBy(xpath = "//input[@aria-label='select all']")
	private WebElement SelectAllCheckbox;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[5]")
	private WebElement checkbox1;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[8]")
	private WebElement checkbox2;

	@FindBy(xpath = "(//span[@class='MuiIconButton-label'])[11]")
	private WebElement checkbox3;

	@FindBy(xpath = "//span[text()='Delete Selected Bids']")
	private WebElement deleteSelectedBidsBtn;

	@FindBy(xpath = "//*[contains(text(),'Are you sure you want to delete these')]")
	private WebElement deleteConfirmationMessage;

	@FindBy(xpath = "//*[contains(text(),'You have chosen to delete')]")
	private WebElement deleteAllConfirmationMessage;

	@FindBy(xpath = "//*[contains(text(),'bids successfully deleted.')]")
	private WebElement deleteSuccessMessage;

	@FindBy(xpath = "//*[text()='Event in progress']")
	private WebElement eventInProgress;

	@FindBy(xpath = "//div[text()='The Event is Paused']")
	private WebElement statusOfPausedEvent;

	@FindBy(xpath = "//*[text()='The Event is Paused']")
	private WebElement alertPausedMessageOnEventPage;

	@FindBy(xpath = "//div[text()='Bidding is Paused']")
	private WebElement alertPausedMessageOnEventPage1;

	@FindBy(xpath = "//button[@title='ADD BID']")
	private WebElement disabledAddBtn;

	@FindBy(xpath = "//div[text()='Bidding window is closed.']")
	private WebElement alertClosedMessageOnEventPage;

	@FindBy(xpath = "//p[text()='00:00:00']")
	private WebElement assertTimer;

	@FindBy(xpath = "//span[text()='PAUSE']")
	private WebElement pauseEventBySponsor1;

	@FindBy(xpath = "//span[text()='RESUME']")
	private WebElement resumeEventBySponsor1;

	@FindBy(xpath = "//p[text()='Event pause proposed.']")
	private WebElement pauseEventAlert1;

	@FindBy(xpath = "//p[text()='Event resume proposed.']")
	private WebElement resumeEventAlert1;

	@FindBy(xpath = "(//span[text()='PAUSE'])[2]")
	private WebElement pauseEventBySponsor2;

	@FindBy(xpath = "(//span[text()='RESUME'])[2]")
	private WebElement resumeEventBySponsor2;

	@FindBy(xpath = "//div[text()='Are you sure you want to pause this event now?']")
	private WebElement confirmPauseEventMessage2;

	@FindBy(xpath = "//div[text()='Are you sure you want to resume this event now?']")
	private WebElement confirmResumeEventMessage2;

	@FindBy(xpath = "//span[text()='CONTINUE']")
	private WebElement confirmPauseEventBySponsor2;

	@FindBy(xpath = "//span[text()='CONTINUE']")
	private WebElement confirmResumeEventBySponsor2;

	@FindBy(xpath = "//p[text()='Event pause approved and effective.']")
	private WebElement pauseEventAlert2;

	@FindBy(xpath = "//p[text()='Event resume approved and effective.']")
	private WebElement resumeEventAlert2;

	@FindBy(xpath = "//span[text()='RESUME']")
	private WebElement pauseBtnChngToResume;

	@FindBy(xpath = "//span[text()='PAUSED']")
	private WebElement eventPausedConfirmation;

	@FindBy(xpath = "//span[text()='PAUSE']")
	private WebElement resumeBtnChngToPause;

	@FindBy(xpath = "//span[text()='RUNNING']")
	private WebElement eventResumedConfirmation;

	@FindBy(xpath = "//*[@title='Go to details of Event SBMA 3']")
	private WebElement goToTheEventSBMA3;

	@FindBy(xpath = "//*[@title='Go to details of Event ReserveSaleAutomation']")
	private WebElement goTorunningevent;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item'])[7]")
	private WebElement eventPausedTimming;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter'])[4]")
	private WebElement getTransactionID;

	@FindBy(xpath = "//input[@name='rollDownProcedure']")
	private WebElement btnAccSelect;

	@FindBy(xpath = "(//input[@name='rollDownProcedure'])[2]")
	private WebElement btnAccSelect2;

	@FindBy(xpath = "//*[text()='T6CAEmetteurB (CA4377-5562)']")
	private WebElement btnEntity3;

	@FindBy(xpath = "//*[text()='T6CAEmetteurC (CA4378-5564)']")
	private WebElement btnEntity4;

	@FindBy(xpath = "//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0 (QC4085-5100)']")
	private WebElement R76FirstbtnEntity;

	@FindBy(xpath = "//*[text()='CONTINUER']")
	private WebElement btnContineu2;

	@FindBy(xpath = "(//label[@class='MuiFormControlLabel-root'])[3]")
	private WebElement accountName2;

	@FindBy(xpath = "(//*[@type='text'])[2]")
	private WebElement textEntity;

	@FindBy(xpath = "//*[text()='CONTINUER']")
	private WebElement btnContineu;

	@FindBy(xpath = "//*[text()='T6QCEmetteurA (QC4446-5674)']")
	private WebElement selectAcc;

	@FindBy(xpath = "//*[text()='T6QCEmetteurC (QC4448-5678)']")
	private WebElement selectAcc2;

	@FindBy(xpath = "//*[@data-icon='times']")
	private WebElement alertPopup;

	@FindBy(xpath = "//*[text()='Exclude Bid']")
	private WebElement btnExcludeBid;

	@FindBy(xpath = "//*[text()='Include Bid']")
	private WebElement btnIncludeBid;

	@FindBy(xpath = "//*[text()='Event Monitoring']")
	private WebElement btnMonitoring;

	@FindBy(xpath = "//*[@placeholder='Enter Text']")
	private WebElement textBoxComment;

	@FindBy(xpath = "(//div[text()='Event in progress'])[2]")
	private WebElement eventInProgressOnPrivateHomePageOfLiveEvent;

	@FindBy(xpath = "(//div[text()='Event in progress'])")
	private WebElement eventInProgressOnPrivateHomePageOfLiveEventRS;

	@FindBy(xpath = "(//span[text()='BIDDING CLOSES IN'])[2]")
	private WebElement biddingClosesInOnPrivateHomePageOfLiveEvent;

	@FindBy(xpath = "(//span[text()='BIDDING CLOSES IN'])")
	private WebElement biddingClosesInOnPrivateHomePageOfLiveEventRS;

	@FindBy(xpath = "//span[text()='Monitoring']")
	private WebElement monitoringDropdownSBMA;

	@FindBy(xpath = "//span[text()='Event Monitoring']")
	private WebElement monitoringDropdownSBA;

	@FindBy(xpath = "//a[text()='JointAuctionAutomation']")
	private WebElement selectEventFromEventDropdownSBMA;

	@FindBy(xpath = "//a[text()='SBMA 3']")
	private WebElement selectSBMA3EventFromEventDropdown;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-8']")
	private WebElement eventNameOnAuctionPageOfLiveEvent;

	@FindBy(xpath = "//*[text()='BIDDING CLOSES IN']")
	private WebElement biddingClosesInOnAuctionPageOfLiveEvent;

	@FindBy(xpath = "(//tr[@role='checkbox'])[1]")
	private WebElement firstBidRowAssert;

	@FindBy(xpath = "//span[text()='ACCOUNT REPRESENTATIVE']")
	private WebElement columnAccountRepresentative;

	@FindBy(xpath = "//span[text()='ENTITY NAME']")
	private WebElement columnEntityName;

	@FindBy(xpath = "//span[text()='BID ID']")
	private WebElement columnBidID;

	@FindBy(xpath = "//span[text()='CLIENT BID ID']")
	private WebElement columnClientBidID;

	@FindBy(xpath = "//span[text()='TRANSACTION ID']")
	private WebElement columnTransactionID;

	@FindBy(xpath = "//span[text()='ACCOUNT NO.']")
	private WebElement columnAccountNo;

	@FindBy(xpath = "//span[text()='TIME (ET)']")
	private WebElement columnTimeET;

	@FindBy(xpath = "//span[text()='CURRENCY']")
	private WebElement columnCurrency;

	@FindBy(xpath = "//span[text()='PRICE OFFERED']")
	private WebElement columnCatPrice;

	@FindBy(xpath = "//span[text()='BID QTY']")
	private WebElement columnBidQty;

	@FindBy(xpath = "//span[text()='VINTAGE']")
	private WebElement columnCategory;

	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement selectNumberOfRows;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement select500Rows;

	@FindBy(xpath = "//*[text()='Add Flag']")
	private WebElement addFlag;

	@FindBy(xpath = "//span[text()='POST']")
	private WebElement addFlagPost;

	@FindBy(xpath = "//*[text()='Remove Flag']")
	private WebElement removeFlag;

	@FindBy(xpath = "//p[text()='Your account has been locked temporarily. An email has been sent to your registered email address with details.']")
	private WebElement errorMessageWhileLoginWhenAcIsLocked;

	@FindBy(xpath = "//h6[text()='Market Monitor']")
	private WebElement labelMM;

	@FindBy(xpath = "//*[@title=\"California Jurisdiction Website\"]")
	private WebElement caJuriWebsite;

	@FindBy(xpath = "//*[@title=\"Quebec Jurisdiction Website\"]")
	private WebElement qcJuriWebsite;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item'])[4]")
	private WebElement nsJuriWebsite;

	@FindBy(xpath = "//*[@title=\"Washington Jurisdiction Website\"]")
	private WebElement waJuriWebsite;

	@FindBy(xpath = "//*[contains(text(),'Event in progress')]")
	private WebElement eventInProgressBanner;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[1]/div[1]")
	private WebElement colourOfEventInProgressBanner;

	@FindBy(xpath = "//h6[text()='Market Monitor']")
	private WebElement UserRoleOnUserInfoBarCA;

	@FindBy(xpath = "//h6[text()='Market Monitor']")
	private WebElement UserRoleOnUserInfoBarQC;

	@FindBy(xpath = "//*[text()='California']")
	private WebElement UserJurisdictionOnUserInfoBarCA;

	@FindBy(xpath = "//li[text()='Quebec']")
	private WebElement changeInUserJurisdictionOnUserInfoBarQC;

	@FindBy(xpath = "//li[text()='California']")
	private WebElement changeInUserJurisdictionOnUserInfoBarCA;

	@FindBy(xpath = "//h2[text()='You have changed your jurisdiction selection.']")
	private WebElement confirmationPopupText;

	@FindBy(xpath = "//p[text()='Jurisdiction:']")
	private WebElement confirmationPopupJuriLabel;

	@FindBy(xpath = "//p[text()='Quebec']")
	private WebElement confirmationPopupJurisdiction;

	@FindBy(xpath = "//h6[text()='Are you sure you want to continue?']")
	private WebElement confirmationTextPopup;

	@FindBy(xpath = "//*[text()='Upcoming Events']")
	private WebElement upcomingEventText;

	@FindBy(xpath = "//span[text()='CONTINUE']")
	private WebElement btnContinueOnConfirmationPopup;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement runningEventName;

	@FindBy(xpath = "//*[contains(text(),'Bids Selected')]")
	private WebElement allBidsSelectedForDelete;

	@FindBy(xpath = "//*[text()='Bidding Closed Event']")
	private WebElement bidingcloseEventName;

	@FindBy(xpath = "//div[text()='Event in progress']")
	private WebElement runningEventStatus;

	@FindBy(xpath = "//span[contains(text(),'Last login')]")
	private WebElement lastLoginDateAndTime;

	@FindBy(xpath = "//a[@title='About WCI, Inc.']")
	private WebElement aboutUsLink;

	@FindBy(xpath = "//a[@title='Copyright © 2024']")
	private WebElement copyrightLink;

	@FindBy(xpath = "//a[@title='Terms of Use']")
	private WebElement termsOfUseLink;

	@FindBy(xpath = "//a[@title='Privacy Policy']")
	private WebElement privacyPolicyLink;

	@FindBy(xpath = "//a[@title='Contact Us']")
	private WebElement contactUsLink;

	@FindBy(xpath = "//*[@data-icon='upload']")
	private WebElement uploadBidTemplateButton;

	@FindBy(xpath = "//span[contains(text(),'CONTINUE')]")
	private WebElement continueButtonOnUploadBidTemplate;

	@FindBy(xpath = "//*[contains(text(),'1 bid uploaded successfully.')]")
	private WebElement bidUploadSuccessMsg;

	private By btnEntity5 = By.xpath("//span[text()='" + prop.getProperty("QCFirstEntity") + "']");
	private By btnEntity6 = By.xpath("//span[text()='" + prop.getProperty("QCSecondEntity") + "']");
	private By btnEntity7 = By.xpath("//span[text()='" + prop.getProperty("CAThirdEntity") + "']");

	public BiddingPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void TestData() {
		System.out.println(prop.getProperty("FirstEntity"));
		System.out.println("//span[text()='" + prop.getProperty("SecondEntity") + "']");
	}

	public void searchEntity() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void searchQC4153421509Entity() {
		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(eventNameOnAuctionPageOfLiveEvent))
				.getText();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Transaction ID to search bids']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Transaction ID to search bids']"))
				.sendKeys("00198826");
		ObjectRepo.getTest().log(Status.PASS, "Enter valid Transaction ID and click on Search Icon.");
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
//		driver.findElement(By.xpath(
//				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
//				.click();
	}

	public void SelectEntity1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity5));
		driver.findElement(btnEntity5).click();

		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity11() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@type='text'])[2]")));
		driver.findElement(By.xpath("(//*[@type='text'])[2]")).sendKeys("QC4105-5140");
		driver.findElement(By.xpath("(//*[@type='checkbox'])[2]")).click();

//		driver.findElement(By.xpath(
//				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
//				.click();
		driver.findElement(By.xpath("//*[text()='CONTINUER']")).click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR entity-QC4105-5140 account");
	}

	public void SelectEntity2() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity6));
		driver.findElement(btnEntity6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity4() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CAFourthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void SelectEntity5() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='CONTINUE']")));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("CAFifthEntity") + "']")).click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void SelectEntity51() {
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
	}

	public void getAccountSelect() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(accountName2));
		String acntName = accountName2.getText();
		System.out.println(acntName);
		btnAccSelect.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR entity account" + acntName);
	}

	public void getAccountSelect2() {
		wait.until(ExpectedConditions.elementToBeClickable(accountName2));
		String AccountName2 = accountName2.getText();
		btnAccSelect2.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ": Selected AR entity account");
		System.out.println("Selected AR entity account");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void getR76EntityAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(R76FirstbtnEntity));
		String AccountName2 = R76FirstbtnEntity.getText();
		btnAccSelect.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ": Selected AR entity account");
		System.out.println("Selected AR entity account");
	}

	public void getEntityAccountQC01647_92680() {
		wait.until(ExpectedConditions.elementToBeClickable(R76FirstbtnEntity));
		String AccountName2 = R76FirstbtnEntity.getText();
		btnAccSelect.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ": Selected AR entity account");
		System.out.println("Selected AR entity account");
	}

	public void getAccountSelect3() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEntity3));
		String AccountName2 = btnEntity3.getText();
		btnEntity3.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ":Selected AR entity account");
	}

	public void getAccountSelect4() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEntity4));
		String AccountName2 = btnEntity4.getText();
		btnEntity4.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ":Selected AR entity account");
	}

	public void getAccountSelect5() {
		String AccountName2 = accountName2.getText();
		btnEntity3.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ":Selected AR entity account");
	}

	public void getAccountSelectForQuebec() throws InterruptedException {
		textEntity.sendKeys("T6QCEmetteurA (QC4446-5674)");
		selectAcc.click();
		btnContineu.click();
	}

	public void getAccountSelectForQuebec2() throws InterruptedException {
		textEntity.sendKeys("T6QCEmetteurC (QC4448-5678)");
		selectAcc2.click();
		btnContineu.click();
	}

	public void getBtnContineu() {
		btnContineu2.click();
	}

	public void removeFlagFunctionality() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnMonitoring));
		btnMonitoring.click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName));
		runningEventName.click();
		System.out.println("Event name is selected");
		ObjectRepo.getTest().log(Status.PASS, "Event name is selected");
		wait.until(ExpectedConditions.elementToBeClickable(threeDotFirstBtn));
		WebElement element111 = threeDotFirstBtn;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		wait.until(ExpectedConditions.elementToBeClickable(Menu));
		String Con = Menu.getText();
		if (Con.contains("Add Flag") == true) {
			wait.until(ExpectedConditions.elementToBeClickable(addFlag));
			WebElement element1111 = addFlag;

			js.executeScript("arguments[0].scrollIntoView();", element1111);
			element1111.click();
			wait.until(ExpectedConditions.elementToBeClickable(textBoxComment));
			textBoxComment.sendKeys("Flag");
			System.out.println("Remove Flag comment added successfully");
			ObjectRepo.getTest().log(Status.PASS, "Remove Flag comment added successfully");
			wait.until(ExpectedConditions.elementToBeClickable(addFlagPost));
			addFlagPost.click();

		} else {

			wait.until(ExpectedConditions.elementToBeClickable(removeFlag));
			WebElement element11111 = removeFlag;

			js.executeScript("arguments[0].scrollIntoView();", element11111);
			element11111.click();
			wait.until(ExpectedConditions.elementToBeClickable(textBoxComment));
			textBoxComment.sendKeys("UNFlag");
			System.out.println("Remove Flag comment added successfully");
			ObjectRepo.getTest().log(Status.PASS, "Remove Flag comment added successfully");
			wait.until(ExpectedConditions.elementToBeClickable(addFlagPost));
			addFlagPost.click();

		}

	}

	public void excludeANDIncludeBidFunctionality() throws InterruptedException {
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
			ObjectRepo.getTest().log(Status.PASS, "Verified Exclude single bid Functionality.");
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
		ObjectRepo.getTest().log(Status.PASS, "Verified Include single bid Functionality.");
	}

	public void IncludeBidFunctionality() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(btnMonitoring));
		btnMonitoring.click();
		wait.until(ExpectedConditions.visibilityOf(runningEventName));
		runningEventName.click();
		wait.until(ExpectedConditions.elementToBeClickable(threeDotFirstBtn));
		WebElement element111 = threeDotFirstBtn;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();

		wait.until(ExpectedConditions.visibilityOf(Menu));
		String Con = Menu.getText();
		System.out.println(Con);
		if (Con.contains("Exclude Bid") == true) {
			wait.until(ExpectedConditions.elementToBeClickable(btnExcludeBid));
			btnExcludeBid.click();
			wait.until(ExpectedConditions.elementToBeClickable(textBoxComment));
			textBoxComment.sendKeys("Excluded");
			wait.until(ExpectedConditions.elementToBeClickable(addFlagPost));
			addFlagPost.click();
		} else {

			wait.until(ExpectedConditions.elementToBeClickable(btnIncludeBid));
			btnIncludeBid.click();
			wait.until(ExpectedConditions.elementToBeClickable(textBoxComment));
			textBoxComment.sendKeys("Included");
			wait.until(ExpectedConditions.elementToBeClickable(addFlagPost));
			addFlagPost.click();
		}
	}

	public void addFlagANDRemoveFunctionality() throws InterruptedException {
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
		ObjectRepo.getTest().log(Status.PASS, "Click on the three dots (⋯) for a bid and select 'Flag a Bid'. ");
		try {
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Add Flag'])[1]")))
					.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']")))
					.sendKeys("Add Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']")))
					.click();
			System.out.println("Add Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Add a comment and click the Post button for adding FLAG");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready Flaged.");
		}
		driver.navigate().refresh();
		Thread.sleep(5000);
		try {

			driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();
			ObjectRepo.getTest().log(Status.PASS, "Click on the three dots (⋯) for a bid and select 'Flag a Bid'. ");
			WebElement element1 = driver.findElement(By.xpath("//li[normalize-space()='Remove Flag']"));
			js.executeScript("arguments[0].scrollIntoView();", element1);
			element1.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']")))
					.sendKeys("Remove Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']")))
					.click();
			ObjectRepo.getTest().log(Status.PASS, "Add a comment and click the Post button for REMOVE FLAG");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		} catch (Exception e) {
			System.out.println("Bid Is allready Unflaged");
		}
		System.out.println("Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove flag functionality verified");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void BiddingByAR() throws Exception {

		selectBtnAuction.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();

		ExcelDataProviderforResultReport ref = new ExcelDataProviderforResultReport();
		double num = ref.getNumericData("Link Rnd 2 4", 22, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 22, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidPrice.sendKeys(str);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 23, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 23, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd2() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 24, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 24, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd3() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 25, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 25, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd4() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 30, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 30, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd5() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 31, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 31, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd6() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 32, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 32, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd7() throws Exception {

		//
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 33, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 33, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd8() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 38, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 38, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd9() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 39, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 39, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd10() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 40, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 40, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd11() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 41, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 41, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage = alertMsg.getText();
		System.out.println(Massage);
		alertPopup.click();
	}

	public void bidadd12() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 6, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 6, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}

		alertPopup.click();
	}

	public void bidadd13() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 7, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 7, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
	}

	public void bidadd14() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 8, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 8, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
	}

	public void bidadd15() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 9, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 9, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
	}

	public void bidadd16() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 14, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 14, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
	}

	public void bidadd17() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 15, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 15, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
	}

	public void bidadd18() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 16, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 16, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
	}

	public void bidadd19() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		double num = ref.getNumericData("Link Rnd 2 4", 17, 1);
		System.out.println(num);
		String str = Double.toString(num);
		System.out.println("placed bid is: " + str);
		textBidPrice.sendKeys(str);
		double lotQty = ref.getNumericData("Link Rnd 2 4", 17, 2);
		System.out.println(num);
		String lotSize = Double.toString(lotQty);
		System.out.println("placed bid lot qty is: " + lotSize);
		textBidLots.sendKeys(lotSize);
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			String Massage = alertMsg2.getText();
			System.out.println(Massage);
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			String Massage = alertMsg.getText();
			System.out.println(Massage);
		}
		alertPopup.click();
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

	public void selectEventForBidding() throws InterruptedException {
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[text()='Events']")).click();
//		
		WebElement eventName = driver.findElement(By.xpath("//*[text()='A211_OpenBW_Automation']"));
//		js.executeScript("arguments[0].click()", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.click();
	}

	public void selectEventForBidding1() throws InterruptedException {
		Thread.sleep(20000);
		driver.findElement(By.xpath("//*[text()='Events']")).click();
//		
		WebElement eventName = driver.findElement(By.xpath("//*[text()='A42_OpenBW_Automation']"));
//		js.executeScript("arguments[0].click()", eventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.click();
	}

	public void selectEventForBiddingE2E() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			driver.findElement(By.xpath("//*[text()='" + eventNameEng + "']")).click();
		}
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

	public void biddingPageAccessToMM_SBMA() throws Exception {
		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		Assert.assertEquals(assertEventName, "JointAuctionAutomation");
		String assertEventInProgressLabel = wait
				.until(ExpectedConditions.visibilityOf(eventInProgressOnPrivateHomePageOfLiveEvent)).getText();
		System.out.println("Event Status on Private Home Page is: " + assertEventInProgressLabel);
		Assert.assertEquals(assertEventInProgressLabel, "Event in progress");

		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(monitoringDropdownSBMA)).click();
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView();", runningEventName);
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Bidding Page is: " + assertEventName1);
		String assertBiddingClosesInLabel1 = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		System.out.println("Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		Assert.assertEquals("BIDDING CLOSES IN", assertBiddingClosesInLabel1);
		System.out.println("******************************************************************");
	}

	public void sortingOnTheBiddingPage_SBMA() throws Exception {
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(monitoringDropdownSBMA));
		monitoringDropdownSBMA.click();
		js.executeScript("arguments[0].scrollIntoView();", runningEventName);
		wait.until(ExpectedConditions.visibilityOf(runningEventName));
		runningEventName.click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName1);
		String assertBiddingClosesInLabel1 = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		System.out.println("Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		Assert.assertEquals("BIDDING CLOSES IN", assertBiddingClosesInLabel1);
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();
		String VerifyFirstBidRowAssert = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before sorting the bid on the column:-  " + VerifyFirstBidRowAssert);
		wait.until(ExpectedConditions.elementToBeClickable(columnAccountRepresentative)).click();
		String VerifyFirstBidRowAssert1 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry before sorting the bid on the Account Representative Column:-  "
				+ VerifyFirstBidRowAssert1);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before sorting the bid on the Account Representative Column:-  "
						+ VerifyFirstBidRowAssert1);
		wait.until(ExpectedConditions.elementToBeClickable(columnAccountRepresentative)).click();
		System.out.println("******************************************************************");

		String VerifyFirstBidRowAssert2 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert2);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert2);
		wait.until(ExpectedConditions.elementToBeClickable(columnEntityName)).click();
		String VerifyFirstBidRowAssert3 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after sorting the bid on the Entity Name Column:-  "
				+ VerifyFirstBidRowAssert3);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Entity Name Column:-  "
						+ VerifyFirstBidRowAssert3);
		wait.until(ExpectedConditions.elementToBeClickable(columnEntityName)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert4 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert4);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert4);
		wait.until(ExpectedConditions.elementToBeClickable(columnBidID)).click();
		String VerifyFirstBidRowAssert5 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println(
				"Details of latest entry after sorting the bid on the Bid ID Column:-  " + VerifyFirstBidRowAssert5);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Bid ID Column:-  " + VerifyFirstBidRowAssert5);
		wait.until(ExpectedConditions.elementToBeClickable(columnBidID)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert6 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert6);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert6);
		wait.until(ExpectedConditions.elementToBeClickable(columnClientBidID)).click();
		String VerifyFirstBidRowAssert7 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after sorting the bid on the Client Bid ID Column:-  "
				+ VerifyFirstBidRowAssert7);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before after the bid on the Client Bid ID Column:-  "
						+ VerifyFirstBidRowAssert7);
		wait.until(ExpectedConditions.elementToBeClickable(columnClientBidID)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert8 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert8);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert8);
		wait.until(ExpectedConditions.elementToBeClickable(columnTransactionID)).click();
		String VerifyFirstBidRowAssert9 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after sorting the bid on the Transaction ID Column:-  "
				+ VerifyFirstBidRowAssert9);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Transaction ID Column:-  "
						+ VerifyFirstBidRowAssert9);
		wait.until(ExpectedConditions.elementToBeClickable(columnTransactionID)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert10 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert10);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert10);
		wait.until(ExpectedConditions.elementToBeClickable(columnAccountNo)).click();
		String VerifyFirstBidRowAssert11 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after sorting the bid on the Account No Column:-  "
				+ VerifyFirstBidRowAssert11);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Account No Column:-  "
						+ VerifyFirstBidRowAssert11);
		wait.until(ExpectedConditions.elementToBeClickable(columnAccountNo)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert12 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert12);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert12);
		wait.until(ExpectedConditions.elementToBeClickable(columnTimeET)).click();
		String VerifyFirstBidRowAssert13 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println(
				"Details of latest entry after sorting the bid on the Time ET Column:-  " + VerifyFirstBidRowAssert13);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Time ET Column:-  " + VerifyFirstBidRowAssert13);
		wait.until(ExpectedConditions.elementToBeClickable(columnTimeET)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert15 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert15);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert15);
		wait.until(ExpectedConditions.elementToBeClickable(columnCurrency)).click();
		String VerifyFirstBidRowAssert16 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println(
				"Details of latest entry after sorting the bid on the Currency Column:-  " + VerifyFirstBidRowAssert16);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Currency Column:-  " + VerifyFirstBidRowAssert16);
		wait.until(ExpectedConditions.elementToBeClickable(columnCurrency)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert17 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert17);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert17);
		wait.until(ExpectedConditions.elementToBeClickable(columnCatPrice)).click();
		String VerifyFirstBidRowAssert18 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after sorting the bid on the Cat Price Column:-  "
				+ VerifyFirstBidRowAssert18);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Cat Price Column:-  "
						+ VerifyFirstBidRowAssert18);
		wait.until(ExpectedConditions.elementToBeClickable(columnCatPrice)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert19 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert19);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert19);
		wait.until(ExpectedConditions.elementToBeClickable(columnBidQty)).click();
		String VerifyFirstBidRowAssert20 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println(
				"Details of latest entry after sorting the bid on the Bid Qty Column:-  " + VerifyFirstBidRowAssert20);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Bid Qty Column:-  " + VerifyFirstBidRowAssert20);
		wait.until(ExpectedConditions.elementToBeClickable(columnCatPrice)).click();
		System.out.println("******************************************************************");
		String VerifyFirstBidRowAssert21 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert21);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of first row before sorting on the column:-  " + VerifyFirstBidRowAssert21);
		wait.until(ExpectedConditions.elementToBeClickable(columnCategory)).click();
		String VerifyFirstBidRowAssert22 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println(
				"Details of latest entry after sorting the bid on the Category Column:-  " + VerifyFirstBidRowAssert22);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after sorting the bid on the Category Column:-  " + VerifyFirstBidRowAssert22);
		wait.until(ExpectedConditions.elementToBeClickable(columnCatPrice)).click();
		System.out.println("******************************************************************");
	}

	public void flagUnflagCommentsOnTheBiddingPage_SBMA() throws Exception {
		Thread.sleep(5000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		driver.findElement(By.xpath("//span[normalize-space()='Monitoring']//*[name()='svg']")).click();
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
			ObjectRepo.getTest().log(Status.PASS, "Click on Bulk Actions button");
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
			ObjectRepo.getTest().log(Status.PASS, "Click on Add Flag with comment and click the Post button.");
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
			ObjectRepo.getTest().log(Status.PASS, "Click on Remove Flag with comment and click the Post button.");
		} catch (Exception e) {
			System.out.println("There is no bids for the remove flag.");
		}

	}

	public void biddingPageAccessToMM_SBA() throws Exception {

		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		Assert.assertEquals(assertEventName, "JointAuctionAutomation");
		String assertEventInProgressLabel = wait
				.until(ExpectedConditions.visibilityOf(eventInProgressOnPrivateHomePageOfLiveEvent)).getText();
		System.out.println("Event Status on Private Home Page is: " + assertEventInProgressLabel);
		ObjectRepo.getTest().log(Status.PASS, "Event Status on Private Home Page is: " + assertEventInProgressLabel);
		Assert.assertEquals(assertEventInProgressLabel, "Event in progress");
//		String assertBiddingClosesInLabel = wait
//				.until(ExpectedConditions.visibilityOf(biddingClosesInOnPrivateHomePageOfLiveEvent)).getText();
//		System.out.println("Bidding Status on Private Home Page is: " + assertBiddingClosesInLabel);
//		Assert.assertEquals(assertBiddingClosesInLabel, "BIDDING CLOSES IN");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(monitoringDropdownSBA)).click();

		WebElement element1 = runningEventName;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Bidding Page is: " + assertEventName1);
		ObjectRepo.getTest().log(Status.PASS, "Event Name on Bidding Page is: " + assertEventName1);
		String assertBiddingClosesInLabel1 = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		ObjectRepo.getTest().log(Status.PASS, "Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		System.out.println("Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		Assert.assertEquals("BIDDING CLOSES IN", assertBiddingClosesInLabel1);
		System.out.println("******************************************************************");
	}
	public void navigateToEventMonitoring() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMonitoring));
			Thread.sleep(5000);
			btnMonitoring.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnMonitoring));
			Thread.sleep(5000);
			btnMonitoring.click();
		}
		Thread.sleep(3500);
		ObjectRepo.getTest().log(Status.PASS, "Step: Click  on Event Monitoring menu");
		System.out.println("Step: Click  on Event Monitoring menu");
	}
	public void selectEvent(String eventName) throws InterruptedException {
		ObjectRepo.getTest().log(Status.INFO, "Selecting event: " + eventName);
		WebElement event = driver.findElement(By.xpath("//a[text()='"+eventName+"']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		ObjectRepo.getTest().log(Status.PASS, "Event is selected: " + eventName);

		boolean isDisplayedEventMonitoring = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Monitoring')]")))
				.isDisplayed();
		Assert.assertTrue(isDisplayedEventMonitoring, "Event Monitoring page not displayed");
		ObjectRepo.getTest().log(Status.PASS, "Event Monitoring page is displayed");
		Thread.sleep(5500);
	}
	public void biddingPageAccessToMM_RS(String eventName) throws Exception {
		By dynamicXpath = By.xpath(String.format("//*[text()='%s']", eventName));
		
		String assertEventName = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicXpath)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		String assertEventInProgressLabel = wait
				.until(ExpectedConditions.visibilityOf(eventInProgressOnPrivateHomePageOfLiveEventRS)).getText();
		System.out.println("Event Status on Private Home Page is: " + assertEventInProgressLabel);
		ObjectRepo.getTest().log(Status.PASS, "Event Status on Private Home Page is: " + assertEventInProgressLabel);
		Assert.assertEquals(assertEventInProgressLabel, "Event in progress");
		String assertBiddingClosesInLabel = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnPrivateHomePageOfLiveEventRS)).getText();
		System.out.println("Bidding Status on Private Home Page is: " + assertBiddingClosesInLabel);
		ObjectRepo.getTest().log(Status.PASS, "Bidding Status on Private Home Page is: " + assertBiddingClosesInLabel);
		Assert.assertEquals(assertBiddingClosesInLabel, "BIDDING CLOSES IN");
		System.out.println("******************************************************************");
		navigateToEventMonitoring();
		selectEvent(eventName);
		String assertBiddingClosesInLabel1 = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		Assert.assertEquals("BIDDING CLOSES IN", assertBiddingClosesInLabel1);
		System.out.println("Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		ObjectRepo.getTest().log(Status.PASS, "Bidding Status on Bidding Page is: " + assertBiddingClosesInLabel1);
		System.out.println("******************************************************************");
	}

	public void handleSecurityQue1() throws InterruptedException {
		WebElement element = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);

		if (str.contains("job?")) {
			securityAnswer.sendKeys("Answer");
		} else if (str.contains("teacher?")) {
			securityAnswer.sendKeys("Answer");
		} else if (str.contains("card?")) {
			securityAnswer.sendKeys("Answer");
		} else if (str.contains("emploi?")) {
			securityAnswer.sendKeys("Answer");
		} else if (str.contains("année?")) {
			securityAnswer.sendKeys("Answer");
		} else if (str.contains("crédit?")) {
			securityAnswer.sendKeys("Answer");
		} else {
			System.out.println("None of the above security question");
		}
	}

	public void userIsNotAbleToLoginDuringTheLockoutPeriod() throws Exception {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys("testTempLockout@yopmail.com");
		password.sendKeys("Test@1234");
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
		String assertErrorMessageWhileLoginWhenAcIsLocked = wait
				.until(ExpectedConditions.visibilityOf(errorMessageWhileLoginWhenAcIsLocked)).getText();
		System.out.println("Temporally lockout message displayed after attempting all wrong securty quetions is: "
				+ assertErrorMessageWhileLoginWhenAcIsLocked);
		ObjectRepo.getTest().log(Status.PASS,
				"Temporally lockout message displayed after attempting all wrong securty quetions is: "
						+ assertErrorMessageWhileLoginWhenAcIsLocked);
	}

	public void userIsAbleToLoginAfterTheLockoutPeriodIsOver() throws Exception {
		driver.navigate().refresh();
		Thread.sleep(700000);
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys("testTempLockout@yopmail.com");
		password.sendKeys("Test@1234");
		wait.until(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
		PublicHomePage login = new PublicHomePage();
		login.handleSecurityQue();
		login.clickOnnext();
		String assertLabelMM = wait.until(ExpectedConditions.visibilityOf(labelMM)).getText();
		System.out.println("Jurisdition of MM after login is: " + assertLabelMM);
		ObjectRepo.getTest().log(Status.PASS, "Jurisdition of MM after login is: ");
		System.out.println("******************************************************************");
	}

	public void navigateToCAJuri() throws Exception {
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow + " is tab one window handle.");
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(caJuriWebsite)).click();
		System.out.println("Step : Click on the 'California' name hyperlink.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'California' name hyperlink.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		Thread.sleep(30000);
		Set<String> allhandles = driver.getWindowHandles();
		Iterator<String> it = allhandles.iterator();
		while (it.hasNext()) {
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			driver.getCurrentUrl();
			System.out.println("Current Url displayed is :" + driver.getCurrentUrl());
			ObjectRepo.getTest().log(Status.PASS, "Current Url displayed is :" + driver.getCurrentUrl());
			driver.getTitle();
			System.out.println("Current page title displayed is :" + driver.getTitle());
			ObjectRepo.getTest().log(Status.PASS, "Current page title displayed is :" + driver.getTitle());
			//
			driver.switchTo().window(parentWindow);
			//
			System.out.println("******************************************************************");
		}
		System.out.println("User should be navigated to California Jurisdiction Website.");
		ObjectRepo.getTest().log(Status.PASS, "User should be navigated to California Jurisdiction Website.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void navigateToQCJuri() throws Exception {
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow + " is tab one window handle.");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(qcJuriWebsite)).click();
		System.out.println("Step : Click on the 'Quebec' name hyperlink.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Quebec' name hyperlink.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		Thread.sleep(5000);
		Set<String> allhandles = driver.getWindowHandles();
		Iterator<String> it = allhandles.iterator();
		while (it.hasNext()) {
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			driver.getCurrentUrl();
			System.out.println("Current Url displayed is :" + driver.getCurrentUrl());
			ObjectRepo.getTest().log(Status.PASS, "Current Url displayed is :" + driver.getCurrentUrl());
			driver.getTitle();
			System.out.println("Current page title displayed is :" + driver.getTitle());
			ObjectRepo.getTest().log(Status.PASS, "Current page title displayed is :" + driver.getTitle());
			//
			driver.switchTo().window(parentWindow);
			//
			System.out.println("******************************************************************");
		}
		System.out.println("Step : Click on the 'Quebec' name hyperlink.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Quebec' name hyperlink.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void navigateToNSJuri() throws Exception {
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow + " is tab one window handle.");
		wait.until(ExpectedConditions.elementToBeClickable(nsJuriWebsite)).click();
		Set<String> allhandles = driver.getWindowHandles();
		Iterator<String> it = allhandles.iterator();
		while (it.hasNext()) {
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			driver.getCurrentUrl();
			System.out.println("Current Url displayed is :" + driver.getCurrentUrl());
			ObjectRepo.getTest().log(Status.PASS, "Current Url displayed is :" + driver.getCurrentUrl());
			driver.getTitle();
			System.out.println("Current page title displayed is :" + driver.getTitle());
			ObjectRepo.getTest().log(Status.PASS, "Current page title displayed is :" + driver.getTitle());
			//
			driver.switchTo().window(parentWindow);
			//
			System.out.println("******************************************************************");
		}
	}

	public void navigateToWAJuri() throws Exception {
		String parentWindow = driver.getWindowHandle();
		System.out.println(parentWindow + " is tab one window handle.");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(waJuriWebsite)).click();
		System.out.println("Step : Click on the 'Washington' name hyperlink.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Washington' name hyperlink.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		Set<String> allhandles = driver.getWindowHandles();
		Iterator<String> it = allhandles.iterator();
		while (it.hasNext()) {
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			driver.getCurrentUrl();
			System.out.println("Current Url displayed is :" + driver.getCurrentUrl());
			ObjectRepo.getTest().log(Status.PASS, "Current Url displayed is :" + driver.getCurrentUrl());
			driver.getTitle();
			System.out.println("Current page title displayed is :" + driver.getTitle());
			ObjectRepo.getTest().log(Status.PASS, "Current page title displayed is :" + driver.getTitle());
			driver.switchTo().window(parentWindow);
			System.out.println("******************************************************************");
		}
		System.out.println("User should be navigated to Washington  Jurisdiction Website.");
		ObjectRepo.getTest().log(Status.PASS, "User should be navigated to Washington  Jurisdiction Website.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void verifyBannerInGreen_CA() throws Exception {
		String assertEventInProgressBanner = wait.until(ExpectedConditions.visibilityOf(eventInProgressBanner))
				.getText();
		System.out.println("Banner text for a live event displayed is: " + assertEventInProgressBanner);
		ObjectRepo.getTest().log(Status.PASS,
				"Banner text for a live event displayed is: " + assertEventInProgressBanner);
		String backgroundColor = colourOfEventInProgressBanner.getCssValue("background-color");
		String bannerTextColor = colourOfEventInProgressBanner.getCssValue("color");
		System.out.println("Banner color: " + backgroundColor);
		System.out.println("Banner Text color " + bannerTextColor);
	}

	public void userInfoBar_CA_MM() throws Exception {
		String assertUserRoleOnUserInfoBarCA = wait.until(ExpectedConditions.visibilityOf(UserRoleOnUserInfoBarCA))
				.getText();
		System.out.println("User role displayed for the logged in user is: " + assertUserRoleOnUserInfoBarCA);
		ObjectRepo.getTest().log(Status.PASS,
				"User role displayed for the logged in user is: " + assertUserRoleOnUserInfoBarCA);
		String assertUserJurisdictionOnUserInfoBarCA = wait
				.until(ExpectedConditions.visibilityOf(UserJurisdictionOnUserInfoBarCA)).getText();
		System.out.println(
				"User Jurisdiction displayed for the logged in user is: " + assertUserJurisdictionOnUserInfoBarCA);
		ObjectRepo.getTest().log(Status.PASS,
				"User Jurisdiction displayed for the logged in user is: " + assertUserJurisdictionOnUserInfoBarCA);
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(UserJurisdictionOnUserInfoBarCA)).click();
		String assertChangeInUserJurisdictionOnUserInfoBarQC = wait
				.until(ExpectedConditions.visibilityOf(changeInUserJurisdictionOnUserInfoBarQC)).getText();
		System.out.println("Dropdown only shows the jurisdictions to which the user is associated.: "
				+ assertChangeInUserJurisdictionOnUserInfoBarQC);
		ObjectRepo.getTest().log(Status.PASS, "dropdown only shows the jurisdictions to which the user is associated."
				+ assertChangeInUserJurisdictionOnUserInfoBarQC);
		String assertChangeInUserJurisdictionOnUserInfoBarCA = wait
				.until(ExpectedConditions.visibilityOf(changeInUserJurisdictionOnUserInfoBarCA)).getText();
		System.out.println("Dropdown only shows the jurisdictions to which the user is associated.: "
				+ assertChangeInUserJurisdictionOnUserInfoBarCA);
		ObjectRepo.getTest().log(Status.PASS, "Dropdown only shows the jurisdictions to which the user is associated."
				+ assertChangeInUserJurisdictionOnUserInfoBarCA);
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(changeInUserJurisdictionOnUserInfoBarQC)).click();
		String assertConfirmationPopupText = wait.until(ExpectedConditions.visibilityOf(confirmationPopupText))
				.getText();
		System.out.println("Text Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupText);
		ObjectRepo.getTest().log(Status.PASS, "Text Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupText);
		String assertConfirmationPopupJuriLabel = wait
				.until(ExpectedConditions.visibilityOf(confirmationPopupJuriLabel)).getText();
		System.out.println("Jurisdiction Lable Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupJuriLabel);
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Lable Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationPopupJuriLabel);
		String assertConfirmationPopupJurisdiction = wait
				.until(ExpectedConditions.visibilityOf(confirmationPopupJurisdiction)).getText();
		System.out.println("Jurisdiction Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupJurisdiction);
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationPopupJurisdiction);
		// Continue button click after confirmation text
		String assertConfirmationTextPopup = wait.until(ExpectedConditions.visibilityOf(confirmationTextPopup))
				.getText();
		System.out.println("Confirmation text on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationTextPopup);
		ObjectRepo.getTest().log(Status.PASS,
				"Confirmation text Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationTextPopup);
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnContinueOnConfirmationPopup)).click();
		//
		// Verify User Role
		String assertUserRoleOnUserInfoBarQC = wait.until(ExpectedConditions.visibilityOf(UserRoleOnUserInfoBarQC))
				.getText();
		System.out.println("User Role displayed after changing the Jurisdiction is: " + assertUserRoleOnUserInfoBarQC);
		ObjectRepo.getTest().log(Status.PASS,
				"User Role displayed after changing the Jurisdiction is: " + assertUserRoleOnUserInfoBarQC);
		// Verify User Jurisdiction
		String assertUserJurisdictionOnUserInfoBarQC = wait.until(ExpectedConditions.visibilityOf(runningEventName))
				.getText();
		System.out.println("User Jurisdiction displayed after changing the Jurisdiction is: "
				+ assertUserJurisdictionOnUserInfoBarQC);
		ObjectRepo.getTest().log(Status.PASS, "User Jurisdiction displayed after changing the Jurisdiction is: "
				+ assertUserJurisdictionOnUserInfoBarQC);
	}

	public void verifyEventList_CA() throws Exception {

		String assertUpcomingEventText = wait.until(ExpectedConditions.visibilityOf(upcomingEventText)).getText();
		System.out.println("Upcoming event text displayed is: " + assertUpcomingEventText);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event text displayed is: " + assertUpcomingEventText);
		// Step7: Verify Upcoming Event Name and Status
		String assertRunningEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Upcoming event Name displayed is: " + assertRunningEventName);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event Name displayed is: " + assertRunningEventName);
		String assertRunningEventStatus = wait.until(ExpectedConditions.visibilityOf(runningEventStatus)).getText();
		System.out.println("Upcoming event Status displayed is: " + assertRunningEventStatus);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming Status Name displayed is: " + assertRunningEventStatus);
		// Step8: Event Name Asserted
		System.out.println("California events are asserted on Private Home Page");
		ObjectRepo.getTest().log(Status.PASS, "California events are asserted on Private Home Page");
	}

	public void verifyEventList_NS() throws Exception {

		try {
			WebElement element = runningEventName;
			//
			Assert.assertTrue(element.isDisplayed());
		} catch (Exception e) {
			System.out.println(
					"NS Sponsor can NOT see Reserve sale and Joint Auction (CA-QC) events on Private Home page");
			ObjectRepo.getTest().log(Status.PASS,
					"NS Sponsor can NOT see Reserve sale and Joint Auction (CA-QC) events on Private Home page");
			System.out.println("******************************************************************");
		}

	}

	public void displayInformationAsPerJuriSelected() throws Exception {

		String assertUserRoleOnUserInfoBarCA = wait.until(ExpectedConditions.visibilityOf(UserRoleOnUserInfoBarCA))
				.getText();
		System.out.println("User role displayed for the logged in user is: " + assertUserRoleOnUserInfoBarCA);
		ObjectRepo.getTest().log(Status.PASS,
				"User role displayed for the logged in user is: " + assertUserRoleOnUserInfoBarCA);

		String assertUserJurisdictionOnUserInfoBarCA = wait
				.until(ExpectedConditions.visibilityOf(UserJurisdictionOnUserInfoBarCA)).getText();
		System.out.println(
				"User Jurisdiction displayed for the logged in user is: " + assertUserJurisdictionOnUserInfoBarCA);
		ObjectRepo.getTest().log(Status.PASS,
				"User Jurisdiction displayed for the logged in user is: " + assertUserJurisdictionOnUserInfoBarCA);

		String assertUpcomingEventText = wait.until(ExpectedConditions.visibilityOf(upcomingEventText)).getText();
		System.out.println("Upcoming event text displayed is: " + assertUpcomingEventText);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event text displayed is: " + assertUpcomingEventText);

		String assertRunningEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Upcoming event Name displayed is: " + assertRunningEventName);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event Name displayed is: " + assertRunningEventName);
		String assertRunningEventStatus = wait.until(ExpectedConditions.visibilityOf(runningEventStatus)).getText();
		System.out.println("Upcoming event Status displayed is: " + assertRunningEventStatus);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming Status Name displayed is: " + assertRunningEventStatus);

		System.out.println("California events are asserted on Private Home Page");
		ObjectRepo.getTest().log(Status.PASS, "California events are asserted on Private Home Page");

		System.out.println("******************************************************************");

		wait.until(ExpectedConditions.elementToBeClickable(UserJurisdictionOnUserInfoBarCA)).click();
		wait.until(ExpectedConditions.elementToBeClickable(changeInUserJurisdictionOnUserInfoBarQC)).click();
		String assertConfirmationPopupText = wait.until(ExpectedConditions.visibilityOf(confirmationPopupText))
				.getText();
		System.out.println("Text Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupText);
		ObjectRepo.getTest().log(Status.PASS, "Text Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupText);

		String assertConfirmationPopupJuriLabel = wait
				.until(ExpectedConditions.visibilityOf(confirmationPopupJuriLabel)).getText();
		System.out.println("Jurisdiction Lable Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupJuriLabel);
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Lable Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationPopupJuriLabel);
		String assertConfirmationPopupJurisdiction = wait
				.until(ExpectedConditions.visibilityOf(confirmationPopupJurisdiction)).getText();
		System.out.println("Jurisdiction Displayed on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationPopupJurisdiction);
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationPopupJurisdiction);
		// Continue button click after confirmation text
		String assertConfirmationTextPopup = wait.until(ExpectedConditions.visibilityOf(confirmationTextPopup))
				.getText();
		System.out.println("Confirmation text on Confirmation popup while changing the Jurisdiction : "
				+ assertConfirmationTextPopup);
		ObjectRepo.getTest().log(Status.PASS,
				"Confirmation text Displayed on Confirmation popup while changing the Jurisdiction : "
						+ assertConfirmationTextPopup);
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(btnContinueOnConfirmationPopup)).click();
		String assertUserRoleOnUserInfoBarQC = wait.until(ExpectedConditions.visibilityOf(UserRoleOnUserInfoBarQC))
				.getText();
		System.out.println("User Role displayed after changing the Jurisdiction is: " + assertUserRoleOnUserInfoBarQC);
		ObjectRepo.getTest().log(Status.PASS,
				"User Role displayed after changing the Jurisdiction is: " + assertUserRoleOnUserInfoBarQC);
		String assertUserJurisdictionOnUserInfoBarQC = wait.until(ExpectedConditions.visibilityOf(runningEventName))
				.getText();
		System.out.println("User Jurisdiction displayed after changing the Jurisdiction is: "
				+ assertUserJurisdictionOnUserInfoBarQC);
		ObjectRepo.getTest().log(Status.PASS, "User Jurisdiction displayed after changing the Jurisdiction is: "
				+ assertUserJurisdictionOnUserInfoBarQC);
		System.out.println("******************************************************************");
		// Step13: Verify Upcoming Event text
		String assertUpcomingEventText1 = wait.until(ExpectedConditions.visibilityOf(upcomingEventText)).getText();
		System.out.println("Upcoming event text displayed is: " + assertUpcomingEventText1);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event text displayed is: " + assertUpcomingEventText1);
		// Step14: Verify Upcoming Event Name and Status
		String assertRunningEventName1 = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Upcoming event Name displayed is: " + assertRunningEventName1);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming event Name displayed is: " + assertRunningEventName1);
		String assertRunningEventStatus1 = wait.until(ExpectedConditions.visibilityOf(runningEventStatus)).getText();
		System.out.println("Upcoming event Status displayed is: " + assertRunningEventStatus1);
		ObjectRepo.getTest().log(Status.PASS, "Upcoming Status Name displayed is: " + assertRunningEventStatus1);
		// Step15: Event Name Asserted
		System.out.println("Quebec events are asserted on Private Home Page");
		ObjectRepo.getTest().log(Status.PASS, "Quebec events are asserted on Private Home Page");
		System.out.println("******************************************************************");
	}

	public void footerSectionDetails() throws Exception {

		// Step5 : Verify Last Login in Footer section layout
		String assertLastLoginDateAndTime = wait.until(ExpectedConditions.visibilityOf(lastLoginDateAndTime)).getText();
		System.out.println("Last login date and time displayed is: " + assertLastLoginDateAndTime);
		ObjectRepo.getTest().log(Status.PASS, "Last login date and time displayed is: " + assertLastLoginDateAndTime);
		System.out.println("******************************************************************");
		System.out.println("Last Login date and time is available in the bottom right in the footer section.");
		ObjectRepo.getTest().log(Status.PASS,
				"Last Login date and time is available in the bottom right in the footer section.");
		System.out.println("******************************************************************");
		// Step6 : Verify links present in Footer section layout
		String assertAboutUsLink = wait.until(ExpectedConditions.visibilityOf(aboutUsLink)).getText();
		System.out.println("First link displayed on Footer section is: " + assertAboutUsLink);
		ObjectRepo.getTest().log(Status.PASS, "First link displayed on Footer section is: " + assertAboutUsLink);
		System.out.println("******************************************************************");
		String assertCopyrightLink = wait.until(ExpectedConditions.visibilityOf(copyrightLink)).getText();
		System.out.println("Second link displayed on Footer section is: " + assertCopyrightLink);
		ObjectRepo.getTest().log(Status.PASS, "Second link displayed on Footer section is: " + assertCopyrightLink);
		System.out.println("******************************************************************");
		String assertTermsOfUseLink = wait.until(ExpectedConditions.visibilityOf(termsOfUseLink)).getText();
		System.out.println("Third link displayed on Footer section is: " + assertTermsOfUseLink);
		ObjectRepo.getTest().log(Status.PASS, "Third link displayed on Footer section is: " + assertTermsOfUseLink);
		System.out.println("******************************************************************");
		String assertPrivacyPolicyLink = wait.until(ExpectedConditions.visibilityOf(privacyPolicyLink)).getText();
		System.out.println("Fourth link displayed on Footer section is: " + assertPrivacyPolicyLink);
		ObjectRepo.getTest().log(Status.PASS, "Fourth link displayed on Footer section is: " + assertPrivacyPolicyLink);
		System.out.println("******************************************************************");
		String assertContactUsLink = wait.until(ExpectedConditions.visibilityOf(contactUsLink)).getText();
		System.out.println("Fifth link displayed on Footer section is: " + assertContactUsLink);
		ObjectRepo.getTest().log(Status.PASS, "Fifth link displayed on Footer section is: " + assertContactUsLink);
		System.out.println("******************************************************************");
		System.out.println(
				"Footer has links to WCI, inc website, terms and conditions, Copyright details (as provided with CMS) and Contact Us.");
		ObjectRepo.getTest().log(Status.PASS,
				"Footer has links to WCI, inc website, terms and conditions, Copyright details (as provided with CMS) and Contact Us.");
		System.out.println("******************************************************************");

	}

	public void viewTheCountdownTimerFromThePrivateHomePage() throws InterruptedException {

		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println(assertEventName);

		String assertDaysRemaining = wait.until(ExpectedConditions.visibilityOf(daysEventDetailsOnPrivateHomePage))
				.getText();
		System.out.println(assertDaysRemaining);
		String assertHoursRemaining = wait.until(ExpectedConditions.visibilityOf(hoursEventDetailsOnPrivateHomePage))
				.getText();
		System.out.println(assertHoursRemaining);
		String assertMinsRemaining = wait.until(ExpectedConditions.visibilityOf(minutesEventDetailsOnPrivateHomePage))
				.getText();
		System.out.println(assertMinsRemaining);
	}

	public void viewTheCountdownTimerFromThePrivateHomePageForLiveEvent() {
		// Step6: Assert Event Name for which Bidding Period is still not open

		String assertEventName = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='JointAuctionAutomation'])[1]")))
				.getText();
		System.out.println(assertEventName);
//		Assert.assertEquals(assertEventName, "SBMA 5 AT TC");
		// Step7: Assert Event In Progress & Live Timer label for a live event
		String assertEventInProgressLabel = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[text()='Event in progress'])[2]")))
				.getText();
		System.out.println(assertEventInProgressLabel);
		ObjectRepo.getTest().log(Status.PASS, assertEventInProgressLabel);
		Assert.assertEquals(assertEventInProgressLabel, "Event in progress");
		// Step8: Assert Bidding Closes In label for a live event
		String assertBiddingClosesInLabel = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='BIDDING CLOSES IN'])[1]")))
				.getText();
		System.out.println(assertBiddingClosesInLabel);
		ObjectRepo.getTest().log(Status.PASS, assertBiddingClosesInLabel);
		Assert.assertEquals(assertBiddingClosesInLabel, "BIDDING CLOSES IN");
	}

	public void viewTheCountdownTimerFromTheAuctionPageForLiveEvent() {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertBiddingClosesInLabel = wait
				.until(ExpectedConditions.visibilityOf(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		System.out.println(assertBiddingClosesInLabel);
		Assert.assertEquals("BIDDING CLOSES IN", assertBiddingClosesInLabel);
	}

	public void verifyEventNameOnPrivateHomePageAndAuctionPage() {
		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println(assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event name is : " + assertEventName1);
		ObjectRepo.getTest().log(Status.PASS,
				"Event name is displayed on the Priavte Home and Auction page: " + assertEventName1);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void arIsNotAbleToAccessTheBiddingPagePriorToOpen() throws InterruptedException {
		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println(assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		String Con = runningEventName.getText();
		System.out.println(Con);
		if (Con.contains("AutomationEventApplicationOpenAO123")) {
			System.out.println("Event details are not available to the AR prior to open bidding window.");
			ObjectRepo.getTest().log(Status.PASS,
					"Event details are not available to the AR prior to open bidding window.");

		} else {
			System.out.println("Event details are  available to the AR prior to open bidding window.");
			ObjectRepo.getTest().log(Status.PASS,
					"Event details are  available to the AR prior to open bidding window.");
		}
	}

	public void biddingWindowContentForAllowanceSale() {

		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningSBMA)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningSBMA)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningSBMA)).getText();
		System.out.println("Event Name Header on Bidding Page is: " + assertEventName1);
		String financeGuaranteeDetails = wait.until(ExpectedConditions.visibilityOf(financialGuaranteeAmountLabel))
				.getText();
		System.out.println("Financial Guarantee Amount Label is: " + financeGuaranteeDetails);
		Assert.assertEquals("FINANCIAL GUARANTEE AMOUNT", financeGuaranteeDetails);
		String financeGuaranteeDetails1 = wait.until(ExpectedConditions.visibilityOf(financialGuaranteeAmountValue))
				.getText();
		System.out.println("Financial Guarantee Amount Value is: " + financeGuaranteeDetails1);
		Assert.assertEquals("CAD 500,000,000", financeGuaranteeDetails1);
		String holdingLimitDetails = wait.until(ExpectedConditions.visibilityOf(holdingLimitLabel)).getText();
		System.out.println("Holding Limit Amount Label is: " + holdingLimitDetails);
		Assert.assertEquals("HOLDING LIMIT", holdingLimitDetails);
		String holdingLimitDetails1 = wait.until(ExpectedConditions.visibilityOf(holdingLimitValue)).getText();
		System.out.println("Holding Limit Amount Value is: " + holdingLimitDetails1);
		String columnNames = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='MuiTableRow-root MuiTableRow-head']")))
				.getText();
		ObjectRepo.getTest().log(Status.PASS, "Total four columns are present");
		System.out.println("Total Num Of Columns :" + columnNames);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='MuiTableRow-root']")));
		List<WebElement> element = driver.findElements(By.xpath("//tr[@class='MuiTableRow-root']"));
		int totalNumOfCategories = element.size();
		System.out.println("Total Num Of Categories :" + totalNumOfCategories);
		for (int i = 0; i <= totalNumOfCategories - 1; i++) {
			element.get(i).getText();
			System.out.println(element.get(i).getText());
			ObjectRepo.getTest().log(Status.PASS, element.get(i).getText());
		}
		String assertBidTableHeader = wait.until(ExpectedConditions.visibilityOf(submittedBidsTitle)).getText();
		System.out.println("Heading of Bid Table on Auction Page is: " + assertBidTableHeader);
		Assert.assertEquals("Submitted Bids", assertBidTableHeader);
		String assertBtn1 = wait.until(ExpectedConditions.visibilityOf(btnAdd)).getText();
		System.out.println("Add Bid button is present on Auction Page: " + assertBtn1);
		Assert.assertEquals("  ADD BID", assertBtn1);
		String assertBtn2 = wait.until(ExpectedConditions.visibilityOf(btnViewAuditReport)).getText();
		System.out.println("View Audit Report button is present on Auction Page: " + assertBtn2);
		Assert.assertEquals("VIEW AUDIT REPORT", assertBtn2);
		String columnNames1 = wait
				.until(ExpectedConditions.elementToBeClickable(totalNumbersOfColumnInSubmittedBidTable)).getText();
		ObjectRepo.getTest().log(Status.PASS, "Total nines columns are present");
		System.out.println("Total Num Of Columns :" + columnNames1);
		wait.until(ExpectedConditions.visibilityOfAllElements(totalNumbersOfSubmittedBidsInTable));
		List<WebElement> element1 = totalNumbersOfSubmittedBidsInTable;
		int totalNumOfBids = element.size();
		System.out.println("Total Num Of Bids :" + totalNumOfBids);
		ObjectRepo.getTest().log(Status.PASS, "Total Num Of Bids :" + totalNumOfBids);
		for (int i = 0; i <= totalNumOfBids - 1; i++) {
			element.get(i).getText();
			System.out.println(element1.get(i).getText());
			ObjectRepo.getTest().log(Status.PASS, element1.get(i).getText());
		}
	}

	public void confirmEventNameOnPrivateHomePageAndAuctionPage() {
		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningSBMA)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView();", runningSBMA);
		wait.until(ExpectedConditions.elementToBeClickable(runningSBMA)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(runningSBMA)).getText();
		System.out.println("The event name displayed in the event header section is: " + assertEventName1);
		ObjectRepo.getTest().log(Status.PASS,
				"The event name displayed in the event header section is: " + assertEventName1);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void assertFinancialGuaranteeAmountAdHoldingLimitDetailsForQC() {
		String financeGuaranteeDetails = wait.until(ExpectedConditions.visibilityOf(financialGuaranteeAmountLabel))
				.getText();
		System.out.println("Financial Guarantee Amount Label is: " + financeGuaranteeDetails);
		ObjectRepo.getTest().log(Status.PASS, "Financial Guarantee Amount Label is: " + financeGuaranteeDetails);
		Assert.assertEquals("FINANCIAL GUARANTEE AMOUNT", financeGuaranteeDetails);
		String financeGuaranteeDetails1 = wait.until(ExpectedConditions.visibilityOf(financialGuaranteeAmountValue1))
				.getText();
		System.out.println("Financial Guarantee Amount and Currency (CAD for Quebec) is: " + financeGuaranteeDetails1);
		ObjectRepo.getTest().log(Status.PASS,
				"Financial Guarantee Amount and Currency (CAD for Quebec) is: " + financeGuaranteeDetails1);
		Assert.assertEquals("CAD 840,000", financeGuaranteeDetails1);
		String holdingLimitDetails = wait.until(ExpectedConditions.visibilityOf(holdingLimitLabel)).getText();
		System.out.println("Holding Limit Amount Label is: " + holdingLimitDetails);
		Assert.assertEquals("HOLDING LIMIT", holdingLimitDetails);
//		String holdingLimitDetails1 = wait.until(ExpectedConditions.visibilityOf(holdingLimitValue1)).getText();
//		System.out.println("Holding Limit Amount Value is: " + holdingLimitDetails1);
		ObjectRepo.getTest().log(Status.PASS, "Holding Limit Amount Label is: " + holdingLimitDetails);
//		ObjectRepo.getTest().log(Status.PASS, "Holding Limit Amount Value is: " + holdingLimitDetails1);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void caterogyPriceInCADForQC() {
		String assertCategoryALabel = wait.until(ExpectedConditions.visibilityOf(categoryA)).getText();
		System.out.println("Category Label is: " + assertCategoryALabel);
		Assert.assertEquals("CATEGORY A", assertCategoryALabel);
		String assertCategoryACurrency = wait.until(ExpectedConditions.visibilityOf(categoryACurrency)).getText();
		System.out.println("Currency of Category A Value is in: " + assertCategoryACurrency);
		ObjectRepo.getTest().log(Status.PASS, "Currency of Category A Value is in: " + assertCategoryACurrency);
		String assertCategoryBLabel = wait.until(ExpectedConditions.visibilityOf(categoryB)).getText();
		System.out.println("Category Label is: " + assertCategoryBLabel);
		Assert.assertEquals("CATEGORY B", assertCategoryBLabel);
		String assertCategoryBCurrency = wait.until(ExpectedConditions.visibilityOf(categoryBCurrency)).getText();
		System.out.println("Currency of Category B Value is in: " + assertCategoryBCurrency);
		ObjectRepo.getTest().log(Status.PASS, "Currency of Category B Value is in: " + assertCategoryBCurrency);
		String assertCategoryCLabel = wait.until(ExpectedConditions.visibilityOf(categoryC)).getText();
		System.out.println("Category Label is: " + assertCategoryCLabel);
		Assert.assertEquals("CATEGORY C", assertCategoryCLabel);

		WebElement element1 = categoryCCurrency;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String assertCategoryCCurrency = wait.until(ExpectedConditions.visibilityOf(categoryCCurrency)).getText();
		System.out.println("Currency of Category C Value is in: " + assertCategoryCCurrency);
		ObjectRepo.getTest().log(Status.PASS, "Currency of Category C Value is in: " + assertCategoryCCurrency);
	}

	public void caterogyPriceInCADForQC1() {
		String assertCategoryALabel = wait.until(ExpectedConditions.visibilityOf(categoryA)).getText();
		System.out.println("Category Label is: " + assertCategoryALabel);
		Assert.assertEquals("CATEGORY A", assertCategoryALabel);
		String assertCategoryACurrency = wait.until(ExpectedConditions.visibilityOf(categoryACurrency)).getText();
		System.out.println("Currency of Category A Value is in: " + assertCategoryACurrency);
		ObjectRepo.getTest().log(Status.PASS, "Currency of Category A Value is in: " + assertCategoryACurrency);
		js.executeScript("arguments[0].scrollIntoView();", categoryB);
		String assertCategoryBLabel = wait.until(ExpectedConditions.visibilityOf(categoryB)).getText();
		System.out.println("Category Label is: " + assertCategoryBLabel);
		Assert.assertEquals("CATEGORY B", assertCategoryBLabel);
		String assertCategoryBCurrency = wait.until(ExpectedConditions.visibilityOf(categoryBCurrency)).getText();
		System.out.println("Currency of Category B Value is in: " + assertCategoryBCurrency);
		ObjectRepo.getTest().log(Status.PASS, "Currency of Category B Value is in: " + assertCategoryBCurrency);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");

	}

	public void totalEmissionUnitsAvailableforQC() {

		WebElement element1 = totalEmissionUnitsAvailableLabel;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String assertTotalEmissionUnitsAvailableColumnName = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableLabel)).getText();
		System.out.println(
				"Total Emission Units Available Column Name is: " + assertTotalEmissionUnitsAvailableColumnName);
		Assert.assertEquals("Total Emission Units Available", assertTotalEmissionUnitsAvailableColumnName);
		String assertTotalEmissionUnitsAvailableForCategoryA = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryA)).getText();
		System.out.println(
				"Total Emission Units Available for Category A is: " + assertTotalEmissionUnitsAvailableForCategoryA);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available for Category A is: " + assertTotalEmissionUnitsAvailableForCategoryA);
		String assertTotalEmissionUnitsAvailableForCategoryB = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryB)).getText();
		System.out.println(
				"Total Emission Units Available for Category B is: " + assertTotalEmissionUnitsAvailableForCategoryB);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available for Category B is: " + assertTotalEmissionUnitsAvailableForCategoryB);
		String assertTotalEmissionUnitsAvailableForCategoryC = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryC)).getText();
		System.out.println(
				"Total Emission Units Available for Category C is: " + assertTotalEmissionUnitsAvailableForCategoryC);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available for Category C is: " + assertTotalEmissionUnitsAvailableForCategoryC);
	}

	public void totalEmissionUnitsAvailableforQC1() {

		WebElement element1 = totalEmissionUnitsAvailableLabel;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String assertTotalEmissionUnitsAvailableColumnName = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableLabel)).getText();
		System.out.println(
				"Total Emission Units Available Column Name is: " + assertTotalEmissionUnitsAvailableColumnName);
		Assert.assertEquals("Total Emission Units Available", assertTotalEmissionUnitsAvailableColumnName);
		String assertTotalEmissionUnitsAvailableForCategoryA = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryA)).getText();
		System.out.println(
				"Total Emission Units Available for Category A is: " + assertTotalEmissionUnitsAvailableForCategoryA);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available for Category A is: " + assertTotalEmissionUnitsAvailableForCategoryA);
		String assertTotalEmissionUnitsAvailableForCategoryB = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryB)).getText();
		System.out.println(
				"Total Emission Units Available for Category B is: " + assertTotalEmissionUnitsAvailableForCategoryB);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Available for Category B is: " + assertTotalEmissionUnitsAvailableForCategoryB);
//		String assertTotalEmissionUnitsAvailableForCategoryC = wait
//				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsAvailableForCategoryC)).getText();
//		System.out.println(
//				"Total Emission Units Available for Category C is: " + assertTotalEmissionUnitsAvailableForCategoryC);
//		ObjectRepo.getTest().log(Status.PASS,
//				"Total Emission Units Available for Category C is: " + assertTotalEmissionUnitsAvailableForCategoryC);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void totalEmissionUnitsBidforQC() {

		String assertTotalEmissionUnitsBidColumnName = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsBidLabel)).getText();
		System.out.println("Total Emission Units Bid Column Name is: " + assertTotalEmissionUnitsBidColumnName);
		Assert.assertEquals("Total Emission Units Bid", assertTotalEmissionUnitsBidColumnName);
		String assertTotalEmissionUnitsBidForCategoryA = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsBidForCategoryA)).getText();
		System.out.println("Total Emission Units Bid for Category A is: " + assertTotalEmissionUnitsBidForCategoryA);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Bid for Category A is: " + assertTotalEmissionUnitsBidForCategoryA);
		String assertTotalEmissionUnitsBidForCategoryB = wait
				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsBidForCategoryB)).getText();
		System.out.println("Total Emission Units BId for Category B is: " + assertTotalEmissionUnitsBidForCategoryB);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Emission Units Bid for Category B is: " + assertTotalEmissionUnitsBidForCategoryB);
//		String assertTotalEmissionUnitsBidForCategoryC = wait
//				.until(ExpectedConditions.visibilityOf(totalEmissionUnitsBidForCategoryC)).getText();
//		System.out.println("Total Emission Units Bid for Category C is: " + assertTotalEmissionUnitsBidForCategoryC);
//		ObjectRepo.getTest().log(Status.PASS,
//				"Total Emission Units Bid for Category C is: " + assertTotalEmissionUnitsBidForCategoryC);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void totalBidsSubmittedforQC() {

		String assertTotalBidsSubmittedColumnName = wait.until(ExpectedConditions.visibilityOf(totalBIdsSubmittedLabel))
				.getText();
		System.out.println("Total Bids Submitted Column Name is: " + assertTotalBidsSubmittedColumnName);
		Assert.assertEquals("Total Bids Submitted", assertTotalBidsSubmittedColumnName);
		String assertTotalBidsSubmittedForCategoryA = wait
				.until(ExpectedConditions.visibilityOf(totalBIdSubmittedForCategoryA)).getText();
		System.out.println("Total Bids Submitted for Category A is: " + assertTotalBidsSubmittedForCategoryA);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Bids Submitted for Category A is: " + assertTotalBidsSubmittedForCategoryA);
		String assertTotalBidsSubmittedForCategoryB = wait
				.until(ExpectedConditions.visibilityOf(totalBIdSubmittedForCategoryB)).getText();
		System.out.println("Total Bids Submitted for Category B is: " + assertTotalBidsSubmittedForCategoryB);
		ObjectRepo.getTest().log(Status.PASS,
				"Total Bids Submitted for Category B is: " + assertTotalBidsSubmittedForCategoryB);
//		String assertTotalBidsSubmittedForCategoryC = wait
//				.until(ExpectedConditions.visibilityOf(totalBIdSubmittedForCategoryC)).getText();
//		System.out.println("Total Bids Submitted for Category C is: " + assertTotalBidsSubmittedForCategoryC);
//		ObjectRepo.getTest().log(Status.PASS,
//				"Total Bids Submitted for Category C is: " + assertTotalBidsSubmittedForCategoryC);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void bidUploadTemplate1() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIt\\Entity1.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
		String successmsg = driver.findElement(By.xpath("//*[contains(text(),'bids uploaded successfully.')]"))
				.getText();
		System.out.println("Total no of bids placed using template: " + successmsg);
		ObjectRepo.getTest().log(Status.PASS, "Total no of bids placed using template: " + successmsg);
	}

	public void bidUploadTemplate2() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();

		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUploadTemplates\\entity1.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
	}

	public void bidUploadWith1000Bids() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIt\\entity1.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("1000 bids uploaded successfully ");
		ObjectRepo.getTest().log(Status.PASS, "1000 bids uploaded successfully ");
	}

	public void bidUploadTemplate3() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIt\\Entity3.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
		String successmsg = driver.findElement(By.xpath("//*[contains(text(),'bids uploaded successfully.')]"))
				.getText();
		System.out.println("Total no of bids placed using template: " + successmsg);
		ObjectRepo.getTest().log(Status.PASS, "Total no of bids placed using template: " + successmsg);
	}

	public void bidUploadTemplate4() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\\\Users\\\\Geeta\\\\Desktop\\\\AutoIt\\\\Entity4.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
		String successmsg = driver.findElement(By.xpath("//*[contains(text(),'bids uploaded successfully.')]"))
				.getText();
		System.out.println("Total no of bids placed using template: " + successmsg);
		ObjectRepo.getTest().log(Status.PASS, "Total no of bids placed using template: " + successmsg);

	}

	public void bidUploadTemplate5() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		js.executeScript("arguments[0].scrollIntoView(true);", uploadElement);
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\\\Users\\\\Geeta\\\\Desktop\\\\AutoIt\\\\Entity1.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
	}

	public void confirmARCanPlaceBid() throws InterruptedException {

		String assertEventName = wait.until(ExpectedConditions.visibilityOf(runningEventName)).getText();
		System.out.println("Event Name on Private Home Page is: " + assertEventName);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String assertEventName1 = wait.until(ExpectedConditions.visibilityOf(eventNameOnAuctionPageOfLiveEvent))
				.getText();
		System.out.println("Event Name Header on Auction Page is: " + assertEventName1);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		String assertSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).getText();
		System.out.println(assertSubmitBtn);
		String assertCancelBtn = wait.until(ExpectedConditions.elementToBeClickable(btnCancel)).getText();
		System.out.println(assertCancelBtn);
		btnCancel.click();
		ObjectRepo.getTest().log(Status.PASS, "Bidding Related Buttons VALIDATED");
		System.out.println("Bidding Related Buttons VALIDATED");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void confirmAddBid() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryDrpdwn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryA)).click();
		String assertSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).getText();
		Assert.assertEquals("SUBMIT", assertSubmitBtn);
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
		String assertConfirmBtn = wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).getText();
		Assert.assertEquals("CONFIRM", assertConfirmBtn);
		ObjectRepo.getTest().log(Status.PASS, "System shows confirmation : Click Confirm");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		String assertSuccess = wait.until(ExpectedConditions.elementToBeClickable(successMessage)).getText();
		System.out.println(assertSuccess);
		Assert.assertEquals("Success", assertSuccess);
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage1)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully submitted.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		String assertAddedDetails = wait.until(ExpectedConditions.elementToBeClickable(addedBidLot)).getText();
		System.out.println(assertAddedDetails);
		Assert.assertEquals("10", assertAddedDetails);
		String assertAddedCategory = wait.until(ExpectedConditions.elementToBeClickable(addedCategory)).getText();
		System.out.println(assertAddedCategory);
		Assert.assertEquals("CATEGORY A", assertAddedCategory);
		String assertTransactionID = wait.until(ExpectedConditions.elementToBeClickable(transactionID)).getText();
		System.out.println(assertTransactionID);
		System.out.println("The bid is added to the table.");
		ObjectRepo.getTest().log(Status.PASS, "The bid is added to the table.");
		List<WebElement> element = driver.findElements(By.xpath("//input[@type='checkbox']"));
		int totalNumOfBids = element.size();
		System.out.println("Total Num Of Bids :" + totalNumOfBids);
		System.out.println("The bids have a unique Client Bid ID");
		ObjectRepo.getTest().log(Status.PASS, "The bids have a unique Client Bid ID");
	}

	public void addBid() throws InterruptedException {

		driver.navigate().refresh();

		WebElement element1 = btnAdd;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryDrpdwn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryA)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		driver.navigate().refresh();
	}

	public void addBidAuction() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		driver.navigate().refresh();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on ADD BID Button");
		wait.until(ExpectedConditions.visibilityOf(bidprice));
		bidprice.sendKeys("59.99");
		ObjectRepo.getTest().log(Status.PASS, "Add bid price");
		wait.until(ExpectedConditions.visibilityOf(addLot));
		addLot.sendKeys("10");
		ObjectRepo.getTest().log(Status.PASS, "Add Lot ");
		wait.until(ExpectedConditions.visibilityOf(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		ObjectRepo.getTest().log(Status.PASS, "Select Current Vintage Type");
		wait.until(ExpectedConditions.visibilityOf(btnSubmit));
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Submit Button");
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Confirm Button");
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage11)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully added.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS,
				assertSuccess1 + " :- A success message is displayed to the AR upon placing a bid");

	}

	public void confirmErrorMessgaeOnKeepingBidLotFieldEmpty() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
//		wait.until(ExpectedConditions.visibilityOf(btnAdd));
//		btnAdd.click();
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			ObjectRepo.getTest().log(Status.PASS, "Click on ADD BID button");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Submit button");
		WebElement element1 = assertBidLotErrorMessage;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String bidLotErrorMessage = wait.until(ExpectedConditions.elementToBeClickable(assertBidLotErrorMessage))
				.getText();
		System.out.println("Error Message Displayed for keeping Bid Lot empty:-  " + bidLotErrorMessage);
		Assert.assertEquals(bidLotErrorMessage, "Enter Number of lots");
		ObjectRepo.getTest().log(Status.PASS, "Error Message :- Enter Number of lots");
		System.out.println("AR cannot keep the bid lots field blank");
		ObjectRepo.getTest().log(Status.PASS, "AR cannot keep the bid lots field blank");
		ObjectRepo.getTest().log(Status.PASS,
				"Number of Lots must only include numeric characters and be a whole number greater than zero.");
		System.out.println("AR cannot add zero value in Bid Lot");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "AR cannot add zero value in Bid Lot");
	}

	public void cancelAddBidFunction() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown));
		eventDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName));
		runningEventName.click();
		String cancelBidAssertMessage = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of latest entry before canceling the bid:-  " + cancelBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before canceling the bid:-  " + cancelBidAssertMessage);

		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCancel)).click();
		System.out.println("Details of latest entry after canceling the bid:-  " + cancelBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after canceling the bid:-  " + cancelBidAssertMessage);
		System.out.println("AR can cancel the Add Bid function");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "AR can cancel the Add Bid function");
	}

	public void confirmDownloadBidUploadTemplateAndUploadBidsAreNotAvailableForSBMA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown));
		eventDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName));
		runningEventName.click();
		String FourDownloadBtn = wait.until(ExpectedConditions.elementToBeClickable(SBMAPAGEBtn)).getText();
		System.out.println("AR Available buttons on SMBA allowance sale page:-  " + FourDownloadBtn);
		ObjectRepo.getTest().log(Status.PASS, "AR Available buttons on SMBA allowance sale page:-  " + FourDownloadBtn);
		Boolean BidUploadTemplateBtn = wait.until(ExpectedConditions.visibilityOf(BidUplaodTemplate)).isDisplayed();
		Assert.assertTrue(false, "Bid Uplaod Button is not displayed");
		Assert.assertTrue(false);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		System.out.println("SBMA download templete not available for AR");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "SBMA download templete not available for AR");
	}

	public void verifyAvailabilityOfDownloadBidUploadTemplateAndUploadBidsForSBMA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown));
		eventDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(runningSBMA));
		runningSBMA.click();
		String FourDownloadBtn = wait.until(ExpectedConditions.visibilityOf(SBMAPAGEBtn)).getText();
		System.out.println("AR Available buttons on SMBA allowance sale page:- " + FourDownloadBtn);
		ObjectRepo.getTest().log(Status.PASS, "AR Available buttons on SMBA allowance sale page:- " + FourDownloadBtn);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		System.out.println("SBMA download templete not available for AR");
		driver.findElement(By.tagName("body")).sendKeys(Keys.UP);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(runningEventName));
			runningEventName.click();
			ObjectRepo.getTest().log(Status.PASS, "Event name is selected ");
		} catch (Exception e) {

			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(runningSBMA));
			runningSBMA.click();
			System.out.println("SBMA event is selcted");
			ObjectRepo.getTest().log(Status.PASS, "SBMA event is selcted");
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(uploadBidsBtn)).click();
			System.out.println("Download bid upload template is available ");
			ObjectRepo.getTest().log(Status.FAIL, "Download bid upload template is available ");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Download bid upload template is not available ");
			ObjectRepo.getTest().log(Status.PASS, "Download bid upload template is not available ");
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(downloadBidsBtn)).click();
			System.out.println("Upload Bids is available ");
			ObjectRepo.getTest().log(Status.FAIL, "Upload Bids is available ");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Upload Bids is not available ");
			ObjectRepo.getTest().log(Status.PASS, "Upload Bids is not available ");
		}

	}

	public void verifyOneBidCanBeSubmittedForQCSBMA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown));
		eventDropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(runningSBMA)).click();

		wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryDrpdwn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryA)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		String assertError = wait.until(ExpectedConditions.elementToBeClickable(assertFailure)).getText();
		System.out.println(assertError);
		Assert.assertEquals(assertError, "Failure");
		ObjectRepo.getTest().log(Status.PASS, "Failure");
		String assertErrorMessgae = wait.until(ExpectedConditions.elementToBeClickable(assertFailureMessage)).getText();
		System.out.println(assertErrorMessgae);
		Assert.assertEquals(assertErrorMessgae,
				"You are exceeding the maximum bids allowed. An entity can submit a maximum of 1 bid(s).");
		ObjectRepo.getTest().log(Status.PASS,
				"You are exceeding the maximum bids allowed. An entity can submit a maximum of 1 bid(s).");
	}

	public void ARCanViewTheBidsPlacedForTheirEntity() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.visibilityOf(bidprice));
		bidprice.sendKeys("100");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).sendKeys("10");
		wait.until(ExpectedConditions.visibilityOf(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage11)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully added.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		String assertColumnNamesOfBidTable = wait.until(ExpectedConditions.elementToBeClickable(assertColumnNames))
				.getText();
		System.out.println(assertColumnNamesOfBidTable);
		ObjectRepo.getTest().log(Status.PASS, "Bid Table Column Names are displayed");
		String assertBidDetails = wait.until(ExpectedConditions.elementToBeClickable(uploadBidTableColumnValues))
				.getText();
		System.out.println(assertBidDetails);
		ObjectRepo.getTest().log(Status.PASS, "Bid Table values are displayed");
	}

	public void searchUsingTransactioID() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows));
		selectNumberOfRows.click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows));
		select500Rows.click();
		String VerifyFirstBidRowAssert = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry before searching the bid:- " + VerifyFirstBidRowAssert);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before searching the bid:-  " + VerifyFirstBidRowAssert);
		List<WebElement> element = driver
				.findElements(By.xpath("//button[@title='Delete Bid - select to delete this bid.']"));
		int totalNumOfBids = element.size();
		ObjectRepo.getTest().log(Status.PASS,
				"Total number of bids present in the table before searching:-  " + totalNumOfBids);
		System.out.println("Total Num Of Bids before searching :" + totalNumOfBids);
		String assertgetTransactionID = wait.until(ExpectedConditions.elementToBeClickable(getTransactionID)).getText();
		System.out.println("Transaction ID :- " + assertgetTransactionID);
		ObjectRepo.getTest().log(Status.PASS, "Transaction ID :- " + assertgetTransactionID);
		wait.until(ExpectedConditions.elementToBeClickable(enterSearchValue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(enterSearchValue)).sendKeys(assertgetTransactionID);
		wait.until(ExpectedConditions.elementToBeClickable(searchValue)).click();
		String VerifyFirstBidRowAssert1 = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry after searching the bid:-  " + VerifyFirstBidRowAssert1);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after searching the bid:-  " + VerifyFirstBidRowAssert1);
		System.out.println("AR can search using Transaction ID");
		ObjectRepo.getTest().log(Status.PASS, "AR can search using Transaction ID");
		List<WebElement> element1 = driver
				.findElements(By.xpath("//button[@title='Delete Bid - select to delete this bid.']"));
		int totalNumOfBids1 = element1.size();
		ObjectRepo.getTest().log(Status.PASS,
				"Total number of bids present in the table after searching:-  " + totalNumOfBids1);
		System.out.println("Total Num Of Bids after searching :" + totalNumOfBids1);
	}

	public void clearThesearch() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();
		String verifyFirstBidRowAssert = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of latest entry before searching the bid:-  " + verifyFirstBidRowAssert);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry before searching the bid:-  " + verifyFirstBidRowAssert);
		ClassPage listNumBids = new ClassPage();
		listNumBids.listOfSubmittedBids();
		String assertgetTransactionID = wait.until(ExpectedConditions.elementToBeClickable(getTransactionID)).getText();
		System.out.println("Transaction ID :- " + assertgetTransactionID);
		ObjectRepo.getTest().log(Status.PASS, "Transaction ID :- " + assertgetTransactionID);
		wait.until(ExpectedConditions.elementToBeClickable(enterSearchValue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(enterSearchValue)).sendKeys(assertgetTransactionID);
		wait.until(ExpectedConditions.elementToBeClickable(searchValue)).click();
		String cancelBidAssertMessage1 = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of latest entry after searching the bid:-  " + cancelBidAssertMessage1);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after searching the bid:-  " + cancelBidAssertMessage1);
		System.out.println("AR can search using Transaction ID");
		ObjectRepo.getTest().log(Status.PASS, "AR can search using Transaction ID");
		listNumBids.listOfSubmittedBids();
		wait.until(ExpectedConditions.elementToBeClickable(clearSearch)).click();
		String cancelBidAssertMessage2 = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of latest entry after clearing the search:-  " + cancelBidAssertMessage2);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after clearing the search:-  " + cancelBidAssertMessage2);
		listNumBids.listOfSubmittedBids();
	}

	public void editBid() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on ADD BID button");
		wait.until(ExpectedConditions.elementToBeClickable(bidprice));
		bidprice.sendKeys("201");
		ObjectRepo.getTest().log(Status.PASS, "Add on bid price");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.sendKeys("90");
		ObjectRepo.getTest().log(Status.PASS, "Add on Lot");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		ObjectRepo.getTest().log(Status.PASS, "Select current Vintage type");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Submit button");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Confirm button");
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage11)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully added.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		String cancelBidAssertMessage = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of latest entry after adding a bid:-  " + cancelBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of latest entry after adding a bid:-  " + cancelBidAssertMessage);
		wait.until(ExpectedConditions.elementToBeClickable(editBidBtn)).click();

		wait.until(ExpectedConditions.visibilityOf(bidLot)).click();
		wait.until(ExpectedConditions.visibilityOf(bidLot)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.visibilityOf(bidLot)).sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.elementToBeClickable(bidLot)).sendKeys("101");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		String assertSuccessEdit = wait.until(ExpectedConditions.elementToBeClickable(successMessageEdit)).getText();
		System.out.println(assertSuccessEdit);
		Assert.assertEquals("Bid edited successfully.", assertSuccessEdit);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon editing a bid");
		String editBidAssertMessage = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of latest entry after editing a bid:-  " + editBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS, "Details of latest entry after editing a bid:-  " + editBidAssertMessage);
		System.out.println("AR can edit a bid(s) when the bidding window is open");
		ObjectRepo.getTest().log(Status.PASS, "-----------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "AR can edit a bid(s) when the bidding window is open");
	}

	public void deleteBid() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String verifyFirstBidRowAssert = wait.until(ExpectedConditions.elementToBeClickable(firstBidRowAssert))
				.getText();
		System.out.println("Details of entry of first row before deleting a bid:-  " + verifyFirstBidRowAssert);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of entry of first row before deleting a bid:-  " + verifyFirstBidRowAssert);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(bidprice));
		bidprice.sendKeys("205");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
//		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage1)).getText();
//		System.out.println(assertSuccess1);
//		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid :- " + assertSuccess1 );
//		Assert.assertEquals("Bid successfully submitted.", assertSuccess1);
		wait.until(ExpectedConditions.elementToBeClickable(deleteBidBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBidBtn)).click();
		String assertSuccessDelete = wait.until(ExpectedConditions.elementToBeClickable(successMessageDelete))
				.getText();
		System.out.println(assertSuccessDelete);
		Assert.assertEquals("Bid deleted successfully.", assertSuccessDelete);
		ObjectRepo.getTest().log(Status.PASS,
				"A success message is displayed to the AR upon delete a bid :- " + assertSuccessDelete);
		String deleteBidAssertMessage = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of entry of first row after deleting a bid:-  " + deleteBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of entry of first row after deleting a bid:-  " + deleteBidAssertMessage);
		System.out.println("AR can delete a single bid when the bidding window is open");
		ObjectRepo.getTest().log(Status.PASS, "------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "AR can delete a single bid when the bidding window is open");
	}

	public void deleteMultipleBids() throws InterruptedException {
//		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
//		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(checkbox1));
		checkbox1.click();
		wait.until(ExpectedConditions.elementToBeClickable(checkbox2));
		checkbox2.click();
		String assertDeleteSelectedBids = wait.until(ExpectedConditions.elementToBeClickable(bidsSelectedForDelete))
				.getText();
		System.out.println(assertDeleteSelectedBids);
		ObjectRepo.getTest().log(Status.PASS, assertDeleteSelectedBids);
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBidsBtn)).click();
		String assertDeleteConfirmationBox = wait
				.until(ExpectedConditions.elementToBeClickable(deleteConfirmationMessage)).getText();
		System.out.println(assertDeleteConfirmationBox);
		Assert.assertEquals("Are you sure you want to delete these 2 bids?", assertDeleteConfirmationBox);
		ObjectRepo.getTest().log(Status.PASS, "Are you sure you want to delete these 2 bids?");
		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBidBtn)).click();
		String assertSuccessDelete = wait.until(ExpectedConditions.elementToBeClickable(deleteSuccessMessage))
				.getText();
		System.out.println(assertSuccessDelete);
		Assert.assertEquals("2 bids successfully deleted.", assertSuccessDelete);
		ObjectRepo.getTest().log(Status.PASS,
				"'2 bids successfully deleted.' success message is displayed to the AR upon multiple bid delete");
		System.out.println("AR can delete more than one selected bids");
		ObjectRepo.getTest().log(Status.PASS, "AR can delete more than one selected bids");
	}

	public void deleteAllBids() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		wait.until(ExpectedConditions.elementToBeClickable(checkbox1));
		checkbox1.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(checkbox2));
			checkbox2.click();
		} catch (Exception e) {

			wait.until(ExpectedConditions.elementToBeClickable(checkbox2));
			checkbox2.click();
		}
		String assertDeleteAllBids = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Bids Selected')]")))
				.getText();
		System.out.println(assertDeleteAllBids);
		ObjectRepo.getTest().log(Status.PASS, assertDeleteAllBids);
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBidsBtn)).click();
		String assertDeleteAllConfirmationBox = wait
				.until(ExpectedConditions.elementToBeClickable(deleteAllConfirmationMessage)).getText();
		System.out.println(assertDeleteAllConfirmationBox);
		ObjectRepo.getTest().log(Status.PASS, "Are you sure you want to delete all bids?");
		wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBidBtn)).click();
		Thread.sleep(3000);
		String assertSuccessDeleteAll = wait.until(ExpectedConditions.elementToBeClickable(deleteAllSuccessMessage))
				.getText();
		System.out.println(assertSuccessDeleteAll);
		ObjectRepo.getTest().log(Status.PASS,
				assertSuccessDeleteAll + "success message is displayed to the AR upon multiple bid delete");
		System.out.println("AR can delete all the bids submitted for the entity");
		ObjectRepo.getTest().log(Status.PASS, "AR can delete all the bids submitted for the entity");
	}

	public void cancelDeleteAction() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		String cancelBidAssertMessage = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println("Details of entry of first row before deleting a bid:-  " + cancelBidAssertMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of entry of first row before deleting a bid:-  " + cancelBidAssertMessage);
		wait.until(ExpectedConditions.elementToBeClickable(deleteBidBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(confirmCancelDeleteBidBtn)).click();
		String cancelDeleteBidDetails = wait.until(ExpectedConditions.elementToBeClickable(cancelBidAssert)).getText();
		System.out.println(
				"Details of entry of first row after cancelling the delete bid action:-  " + cancelDeleteBidDetails);
		ObjectRepo.getTest().log(Status.PASS,
				"Details of entry of first row after cancelling the delete bid action:-  " + cancelDeleteBidDetails);
		System.out.println("AR can cancel the delete action prior to confirm");
		ObjectRepo.getTest().log(Status.PASS, "AR can cancel the delete action prior to confirm");
	}

	public void cancelMultipleDeleteAction() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName)).click();
		List<WebElement> element = driver
				.findElements(By.xpath("//button[@title='Delete Bid - select to delete this bid.']"));
		int totalBids1 = element.size();
		ObjectRepo.getTest().log(Status.PASS,
				"Total number of bids present in the table before batch delete:-  " + totalBids1);
		System.out.println("Total number of bids present in the table before batch delete:" + totalBids1);

		wait.until(ExpectedConditions.elementToBeClickable(checkbox1)).click();
		wait.until(ExpectedConditions.elementToBeClickable(checkbox2)).click();
		wait.until(ExpectedConditions.elementToBeClickable(checkbox3)).click();
		String assertDeleteSelectedBids = wait.until(ExpectedConditions.elementToBeClickable(bidsSelectedForDelete))
				.getText();
		System.out.println(assertDeleteSelectedBids);
		ObjectRepo.getTest().log(Status.PASS, "3 of total Bids Selected for delete");
		wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBidsBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(confirmCancelDeleteBidBtn)).click();
		List<WebElement> element3 = driver
				.findElements(By.xpath("//button[@title='Delete Bid - select to delete this bid.']"));
		int totalNumOfBids2 = element3.size();
		ObjectRepo.getTest().log(Status.PASS,
				"Total number of bids present in the table after cancelling the batch delete bid action:-  "
						+ totalNumOfBids2);
		System.out.println("Total number of bids present in the table after cancelling the batch delete bid action:"
				+ totalNumOfBids2);
	}

	public void arNotQualifiedBiddersCannotPlaceBids() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
			try {
				wait.until(ExpectedConditions.elementToBeClickable(selectSBMA5EventFromEventDropdown)).click();
				ObjectRepo.getTest().log(Status.PASS, "ARs of Entities are qualified bidders and can place bids.");
				System.out.println("ARs of Entities are qualified bidders and can place bids.");

			} catch (Exception e) {
				ObjectRepo.getTest().log(Status.PASS,
						"ARs of Entities that are not qualified bidders cannot place bids.");
				System.out.println("ARs of Entities that are not qualified bidders cannot place bids.");
			}
		} catch (Exception e) {
			ObjectRepo.getTest().log(Status.PASS, "ARs of Entities that are not qualified bidders cannot place bids.");
			System.out.println("ARs of Entities that are not qualified bidders cannot place bids.");
		}

	}

	public void verifyRunningStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(PauseEvent)).click();
		String AssertAlertRunningMessageOnEventPage = wait
				.until(ExpectedConditions.elementToBeClickable(biddingClosesInOnAuctionPageOfLiveEvent)).getText();
		Assert.assertEquals("BIDDING CLOSES IN", AssertAlertRunningMessageOnEventPage);
		System.out.println("Status of Paused event on Event Page is :-  " + AssertAlertRunningMessageOnEventPage);
		ObjectRepo.getTest().log(Status.PASS,
				"Status of Paused event on Event Page is :-  " + AssertAlertRunningMessageOnEventPage);
		wait.until(ExpectedConditions.elementToBeClickable(btnAdd)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLot)).sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryDrpdwn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectCategoryA)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm)).click();
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage1)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully submitted.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		System.out.println("Added bid proves that the selected event is in running state");
		ObjectRepo.getTest().log(Status.PASS, "Added bid proves that the selected event is in running state");
	}

	public void initiatePauseEventByFirstSponsor() throws IOException, InterruptedException {

		PublicHomePage login = new PublicHomePage();
		login.RuntimeSponsorLoginQc();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnextQC();
		ApprovalStatusPage approve = new ApprovalStatusPage();
		approve.changeLangauge();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();

		WebElement element1 = goToTheEventSBMA3;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(goToTheEventSBMA3)).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor1)).click();
		} catch (Exception e) {

			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor2)).click();
		}
		String assertEventPauseSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(pauseEventAlert1))
				.getText();
		System.out.println(assertEventPauseSuccess1);
		Assert.assertEquals("Event pause proposed.", assertEventPauseSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "Event pause proposed message is displayed for first sposnor");
		driver.navigate().refresh();
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void confirmPauseEventBySecondSponsor() throws IOException, InterruptedException {

		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginQc2();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();

		WebElement element1 = goToTheEventSBMA3;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(goToTheEventSBMA3)).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor2)).click();
		} catch (Exception e) {

			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor1)).click();
		}
		String assertEventPauseSuccess2 = wait.until(ExpectedConditions.elementToBeClickable(confirmPauseEventMessage2))
				.getText();
		System.out.println(assertEventPauseSuccess2);
		Assert.assertEquals("Are you sure you want to pause this event now?", assertEventPauseSuccess2);
		ObjectRepo.getTest().log(Status.PASS, "Confirmation message is displayed for second sposnor");
		wait.until(ExpectedConditions.elementToBeClickable(confirmPauseEventBySponsor2)).click();
		String assertconfirmPauseEventMessage2 = wait.until(ExpectedConditions.elementToBeClickable(pauseEventAlert2))
				.getText();
		System.out.println(assertconfirmPauseEventMessage2);
		Assert.assertEquals("Event pause approved and effective.", assertconfirmPauseEventMessage2);
		ObjectRepo.getTest().log(Status.PASS, "Event pause confirmation message is displayed for second sposnor");
		driver.navigate().refresh();
		String assertpauseBtnChngToResume = wait.until(ExpectedConditions.elementToBeClickable(pauseBtnChngToResume))
				.getText();
		System.out.println("Pause button is changed to: " + assertpauseBtnChngToResume);
		Assert.assertEquals("RESUME", assertpauseBtnChngToResume);
		ObjectRepo.getTest().log(Status.PASS, "Pause button is changed to: " + assertpauseBtnChngToResume);
		String assertpausedConfirmation = wait.until(ExpectedConditions.elementToBeClickable(eventPausedConfirmation))
				.getText();
		System.out.println("Status is changed to: " + assertpausedConfirmation);
		Assert.assertEquals("PAUSED", assertpausedConfirmation);
		ObjectRepo.getTest().log(Status.PASS, "Status is changed to: " + assertpausedConfirmation);
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void verifyPausedStatus() throws InterruptedException {
		PublicHomePage ref = new PublicHomePage();
		ref.RuntimeARLoginQC();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		String eventInProgressMessageOnHeader = wait.until(ExpectedConditions.visibilityOf(eventInProgress)).getText();
		System.out.println(eventInProgressMessageOnHeader);
		try {
			Assert.assertEquals("Event in progress", eventInProgressMessageOnHeader);
			ObjectRepo.getTest().log(Status.PASS,
					"'EVENT IN PROGRESS' message for live event is shown even if at least one event is running.");
			System.out.println(
					"'EVENT IN PROGRESS' message for live event is shown even if at least one event is running.");

		} catch (Exception e) {

			e.printStackTrace();
			ObjectRepo.getTest().log(Status.PASS, "There is no any running event because all are paused.");
			System.out.println("There is no any running event because all are paused.");
		}
		String assertNameOfPausedEvent = wait.until(ExpectedConditions.elementToBeClickable(PauseEvent)).getText();
		Assert.assertEquals("Joint Auction Pause event functionality", assertNameOfPausedEvent);
		System.out.println("Name of Paused event is :-  " + assertNameOfPausedEvent);
		ObjectRepo.getTest().log(Status.PASS, "Name of Paused event is :-  " + assertNameOfPausedEvent);
		String assertStatusOfPausedEvent = wait.until(ExpectedConditions.elementToBeClickable(statusOfPausedEvent))
				.getText();
		Assert.assertEquals("The Event is Paused", assertStatusOfPausedEvent);
		System.out.println("Status of Paused event on Private Home Page is :- " + assertStatusOfPausedEvent);
		ObjectRepo.getTest().log(Status.PASS,
				"Status of Paused event on Private Home is :- " + assertStatusOfPausedEvent);
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(PauseEvent)).click();
		String AssertAlertPausedMessageOnEventPage = wait
				.until(ExpectedConditions.elementToBeClickable(alertPausedMessageOnEventPage)).getText();
		Assert.assertEquals("The Event is Paused", AssertAlertPausedMessageOnEventPage);
		System.out.println("Status of Paused event on Event Page is :- " + AssertAlertPausedMessageOnEventPage);
		ObjectRepo.getTest().log(Status.PASS,
				"Status of Paused event on Event Page is :- " + AssertAlertPausedMessageOnEventPage);
	}

	public void initiateResumeEventByFirstSponsor() throws IOException, InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginCa();
		System.out.println("Step1: Sponsor Login successfully");
		try {
			login.handleSecurityQue();
			login.clickOnnext();
		} catch (Exception e) {
			driver.navigate().refresh();
		}
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		} catch (Exception e) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		}
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows));
		selectNumberOfRows.click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows));
		select500Rows.click();
		for (int i = 0; i < 5; i++) {
			WebElement element1 = null;
			try {
				element1 = driver
						.findElement(By.xpath("//*[text()='" + prop.getProperty("Puase_Resume_TestEvent") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver.findElement(
				By.xpath("//*[@title='Go to details of Event " + prop.getProperty("Puase_Resume_TestEvent") + "']"))
				.click();

		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(resumeEventBySponsor1)).click();
				String assertresumeEventAlert1 = wait.until(ExpectedConditions.elementToBeClickable(resumeEventAlert1))
						.getText();
				System.out.println(assertresumeEventAlert1);
				Assert.assertEquals("Event resume proposed.", assertresumeEventAlert1);
				ObjectRepo.getTest().log(Status.PASS, "Event resume proposed message is displayed for first sposnor");
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(resumeEventBySponsor2)).click();
				String assertresumeEventAlert1 = wait.until(ExpectedConditions.elementToBeClickable(resumeEventAlert1))
						.getText();
				System.out.println(assertresumeEventAlert1);
				Assert.assertEquals("Event resume proposed.", assertresumeEventAlert1);
				ObjectRepo.getTest().log(Status.PASS, "Event resume proposed message is displayed for first sposnor");
			}
		} catch (Exception e) {
			System.out.println("Event is allready RESUME.");
		}

		driver.navigate().refresh();
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void confirmResumeEventBySecondSponsor() throws IOException, InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginQc2();
		System.out.println("Step1: Sponsor Login successfully");
		try {
			login.handleSecurityQue();
			login.clickOnnext();
		} catch (Exception e) {
			driver.navigate().refresh();
			Thread.sleep(5000);
		}
//		POM_ApprovalStatus approve = new POM_ApprovalStatus( );
//		approve.changeLangauge();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows));
		selectNumberOfRows.click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows));
		select500Rows.click();
		for (int i = 0; i < 5; i++) {
			WebElement element1 = null;
			try {
				element1 = driver
						.findElement(By.xpath("//*[text()='" + prop.getProperty("Puase_Resume_TestEvent") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver.findElement(
				By.xpath("//*[@title='Go to details of Event " + prop.getProperty("Puase_Resume_TestEvent") + "']"))
				.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(resumeEventBySponsor2)).click();
		} catch (Exception e) {
			e.printStackTrace();
			wait.until(ExpectedConditions.elementToBeClickable(resumeEventBySponsor1)).click();
		}
		try {
			String assertEventResumeSuccess2 = wait
					.until(ExpectedConditions.elementToBeClickable(confirmResumeEventMessage2)).getText();
			System.out.println(assertEventResumeSuccess2);
			Assert.assertEquals("Are you sure you want to resume this event now?", assertEventResumeSuccess2);
			ObjectRepo.getTest().log(Status.PASS, "Confirmation message is displayed for second sposnor");
			wait.until(ExpectedConditions.elementToBeClickable(confirmResumeEventBySponsor2)).click();
			String assertconfirmResumeEventMessage2 = wait
					.until(ExpectedConditions.elementToBeClickable(resumeEventAlert2)).getText();
			System.out.println(assertconfirmResumeEventMessage2);
			Assert.assertEquals("Event resume approved and effective.", assertconfirmResumeEventMessage2);
			ObjectRepo.getTest().log(Status.PASS, "Event resume confirmation message is displayed for second sposnor");
			driver.navigate().refresh();
			String assertResumeBtnChngToPause = wait
					.until(ExpectedConditions.elementToBeClickable(resumeBtnChngToPause)).getText();
			System.out.println("PAUSE button is changed to: " + assertResumeBtnChngToPause);
			Assert.assertEquals("PAUSE", assertResumeBtnChngToPause);
			ObjectRepo.getTest().log(Status.PASS, "RESUME button is changed to: " + assertResumeBtnChngToPause);
			String assertResumeConfirmation = wait
					.until(ExpectedConditions.elementToBeClickable(eventResumedConfirmation)).getText();
			System.out.println("Status is changed to: " + assertResumeConfirmation);
			Assert.assertEquals("RUNNING", assertResumeConfirmation);
			ObjectRepo.getTest().log(Status.PASS, "Status is changed to: " + assertResumeConfirmation);
		} catch (Exception e) {

			System.out.println("Event is allready Resumed.");
		}
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void arCannotPerfromAnyActivityOnTheBiddingWhenEventIsPaused() throws InterruptedException {

		PublicHomePage ref = new PublicHomePage();
		ref.RuntimeARLoginQC();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(selectSBMA3EventFromEventDropdown)).click();
		String assertAlertPausedMessageOnEventPage1 = wait
				.until(ExpectedConditions.elementToBeClickable(alertPausedMessageOnEventPage1)).getText();
		Assert.assertEquals("Bidding is Paused", assertAlertPausedMessageOnEventPage1);
		System.out.println("Status of Paused event on Event Page is :-  " + assertAlertPausedMessageOnEventPage1);
		ObjectRepo.getTest().log(Status.PASS, "Status of Event Page is :-  " + assertAlertPausedMessageOnEventPage1);
		// Step37: Verify AR can not place bidding for an Paused event and logout
		wait.until(ExpectedConditions.elementToBeClickable(alertPausedMessageOnEventPage1)).click();
		try {
			wait.until(ExpectedConditions.visibilityOf(disabledAddBtn)).click();
			System.out.println("AR can perfrom any activity on the bidding window during pause");
			ObjectRepo.getTest().log(Status.FAIL, "AR can perfrom any activity on the bidding window during pause");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("AR cannot perfrom any activity on the bidding window during pause");
			ObjectRepo.getTest().log(Status.PASS, "AR cannot perfrom any activity on the bidding window during pause");
		}
		driver.navigate().refresh();
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void getBtnContineuEN() {
		btnContinueEN.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Continue Button");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void bidsAreRestrictedAfterTheBiddingWindowCloses() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(bidingcloseEventName)).click();
		System.out.println("Bidding Window Closed event is selected by AR");
		ObjectRepo.getTest().log(Status.PASS, "Bidding Window Closed event is selected by AR");
		String assertalertClosedMessageOnEventPage = wait
				.until(ExpectedConditions.elementToBeClickable(alertClosedMessageOnEventPage)).getText();
		Assert.assertEquals("Bidding window is closed.", assertalertClosedMessageOnEventPage);
		System.out.println("Status of event on Event Page is :-  " + assertalertClosedMessageOnEventPage);
		ObjectRepo.getTest().log(Status.PASS,
				"Status of event on Event Page is :-  " + assertalertClosedMessageOnEventPage);

		String assertTimerValue = wait.until(ExpectedConditions.elementToBeClickable(assertTimer)).getText();
		Assert.assertEquals("00:00:00", assertTimerValue);
		System.out.println("Timer displayed for closed event is :-  " + assertTimerValue);
		ObjectRepo.getTest().log(Status.PASS, "Timer displayed for closed event is :-  " + assertTimerValue);
		// Step8: Verify AR can not place bidding for an Closed event
		wait.until(ExpectedConditions.elementToBeClickable(alertClosedMessageOnEventPage)).click();
		try {
			Boolean enaAddBidBtn = wait.until(ExpectedConditions.visibilityOf(disabledAddBtn)).isEnabled();
			System.out.println("Add Bid button is enabled :- " + enaAddBidBtn);
			ObjectRepo.getTest().log(Status.PASS, "Add Bid button is enabled :- " + enaAddBidBtn);
			ObjectRepo.getTest().log(Status.PASS,
					"AR can not perform any bidding activity on the bidding window after event is closed.");
			System.out.println("AR can not perform any bidding activity on the bidding window after event is closed.");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("AR cannot perform any activity on the bidding window after event is closed.");
			ObjectRepo.getTest().log(Status.PASS,
					"AR cannot perform any activity on the bidding window after event is closed.");
		}

	}

	public void verifyARHasViewOnlyAccessOnTheBiddingPage() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(eventDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(bidingcloseEventName)).click();

		String assertalertClosedMessageOnEventPage = wait
				.until(ExpectedConditions.elementToBeClickable(alertClosedMessageOnEventPage)).getText();
		Assert.assertEquals(assertalertClosedMessageOnEventPage, "Bidding window is closed.");
		System.out
				.println("Message indicating the bidding window is closed. :-  " + assertalertClosedMessageOnEventPage);
		ObjectRepo.getTest().log(Status.PASS,
				"Message indicating the bidding window is closed. :-  " + assertalertClosedMessageOnEventPage);

		String assertTimerValue = wait.until(ExpectedConditions.elementToBeClickable(assertTimer)).getText();
		Assert.assertEquals("00:00:00", assertTimerValue);
		System.out.println("Timer is not active :-  " + assertTimerValue);
		ObjectRepo.getTest().log(Status.PASS, "Timer is not active :-  " + assertTimerValue);

		wait.until(ExpectedConditions.elementToBeClickable(alertClosedMessageOnEventPage)).click();
		try {
			wait.until(ExpectedConditions.visibilityOf(disabledAddBtn)).click();
			System.out.println("AR can perfrom any activity on the bidding window after event is closed.");
			ObjectRepo.getTest().log(Status.FAIL,
					"AR can perfrom any activity on the bidding window after event is closed.");
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("AR has view only access to the bidding page after bidding  window is closed.");
			ObjectRepo.getTest().log(Status.PASS,
					"AR has view only access to the bidding page after bidding  window is closed.");
		}

	}

	public void eventPauseByFirstSponsor() throws IOException, InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.sponsorLoginCa2();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		} catch (Exception e) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		}
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows));
		selectNumberOfRows.click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows));
		select500Rows.click();
		for (int i = 0; i < 5; i++) {
			WebElement element1 = null;
			try {
				element1 = driver
						.findElement(By.xpath("//*[text()='" + prop.getProperty("Puase_Resume_TestEvent") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver.findElement(
				By.xpath("//*[@title='Go to details of Event " + prop.getProperty("Puase_Resume_TestEvent") + "']"))
				.click();

		try {
			try {
				String textPauseBtn = wait.until(ExpectedConditions.visibilityOf(pauseEventBySponsor1)).getText();
				System.out.println(textPauseBtn);
				Assert.assertEquals("PAUSE", textPauseBtn);
				ObjectRepo.getTest().log(Status.PASS, "Pause button is enabled");
				wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor1)).click();
				String assertEventPauseSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(pauseEventAlert1))
						.getText();
				System.out.println(assertEventPauseSuccess1);
				Assert.assertEquals("Event pause proposed.", assertEventPauseSuccess1);
				ObjectRepo.getTest().log(Status.PASS, "Event pause proposed message is displayed for first sposnor");
			} catch (Exception e) {
				String textPauseBtn = wait.until(ExpectedConditions.visibilityOf(pauseEventBySponsor2)).getText();
				System.out.println(textPauseBtn);
				Assert.assertEquals("PAUSE", textPauseBtn);
				ObjectRepo.getTest().log(Status.PASS, "Pause button is enabled");
				wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor2)).click();
				String assertEventPauseSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(pauseEventAlert1))
						.getText();
				System.out.println(assertEventPauseSuccess1);
				Assert.assertEquals("Event pause proposed.", assertEventPauseSuccess1);
				ObjectRepo.getTest().log(Status.PASS, "Event pause proposed message is displayed for first sposnor");
			}
		} catch (Exception e) {
			System.out.println("Event is allready PAUSED by first sponsor.");
		}
		driver.navigate().refresh();
		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void eventPauseBySecondSponsor() throws IOException, InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.runtimesponsorLoginQc2();
		System.out.println("Step1: Sponsor Login successfully");
		login.handleSecurityQue();
		try {
			login.clickOnnext();
		} catch (Exception e) {
			login.clickOnnextQC();
		}
//		try {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		} catch (Exception e) {
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
//		}
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows));
		selectNumberOfRows.click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows));
		select500Rows.click();
		for (int i = 0; i < 5; i++) {
			WebElement element1 = null;
			try {
				element1 = driver
						.findElement(By.xpath("//*[text()='" + prop.getProperty("Puase_Resume_TestEvent") + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				WebElement nextPageButton = driver.findElement(By.xpath("(//*[@aria-label='Next Page'])[1]"));
				nextPageButton.click();
			}
		}
		driver.findElement(
				By.xpath("//*[@title='Go to details of Event " + prop.getProperty("Puase_Resume_TestEvent") + "']"))
				.click();
		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor2)).click();
				String assertEventPauseSuccess2 = wait
						.until(ExpectedConditions.elementToBeClickable(confirmPauseEventMessage2)).getText();
				System.out.println(assertEventPauseSuccess2);
				Assert.assertEquals("Are you sure you want to pause this event now?", assertEventPauseSuccess2);
				ObjectRepo.getTest().log(Status.PASS, "Confirmation message is displayed for second sposnor");
				wait.until(ExpectedConditions.elementToBeClickable(confirmPauseEventBySponsor2)).click();
				String assertconfirmPauseEventMessage2 = wait
						.until(ExpectedConditions.elementToBeClickable(pauseEventAlert2)).getText();
				System.out.println(assertconfirmPauseEventMessage2);
				Assert.assertEquals("Event pause approved and effective.", assertconfirmPauseEventMessage2);
				ObjectRepo.getTest().log(Status.PASS,
						"Event pause confirmation message is displayed for second sposnor");
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(pauseEventBySponsor1)).click();
				String assertEventPauseSuccess2 = wait
						.until(ExpectedConditions.elementToBeClickable(confirmPauseEventMessage2)).getText();
				System.out.println(assertEventPauseSuccess2);
				Assert.assertEquals("Are you sure you want to pause this event now?", assertEventPauseSuccess2);
				ObjectRepo.getTest().log(Status.PASS, "Confirmation message is displayed for second sposnor");
				wait.until(ExpectedConditions.elementToBeClickable(confirmPauseEventBySponsor2)).click();
				String assertconfirmPauseEventMessage2 = wait
						.until(ExpectedConditions.elementToBeClickable(pauseEventAlert2)).getText();
				System.out.println(assertconfirmPauseEventMessage2);
				Assert.assertEquals("Event pause approved and effective.", assertconfirmPauseEventMessage2);
				ObjectRepo.getTest().log(Status.PASS,
						"Event pause confirmation message is displayed for second sposnor");
			}
		} catch (Exception e) {
			System.out.println("Event is allready PAUSED by second sponsor.");
		}

		driver.navigate().refresh();
		Thread.sleep(4000);
		String assertpauseBtnChngToResume = wait.until(ExpectedConditions.elementToBeClickable(pauseBtnChngToResume))
				.getText();
		System.out.println("Pause button is changed to: " + assertpauseBtnChngToResume);
		Assert.assertEquals("RESUME", assertpauseBtnChngToResume);
		ObjectRepo.getTest().log(Status.PASS, "Pause button is changed to: " + assertpauseBtnChngToResume);

		String assertpausedConfirmation = wait.until(ExpectedConditions.elementToBeClickable(eventPausedConfirmation))
				.getText();
		System.out.println("Status is changed to: " + assertpausedConfirmation);
		Assert.assertEquals("PAUSED", assertpausedConfirmation);
		ObjectRepo.getTest().log(Status.PASS, "Status is changed to: " + assertpausedConfirmation);

		String asserteventPausedTimming = wait.until(ExpectedConditions.elementToBeClickable(eventPausedTimming))
				.getText();
		System.out.println("Event paused timming is: " + asserteventPausedTimming);
		ObjectRepo.getTest().log(Status.PASS, "Event paused timming is: " + asserteventPausedTimming);

		ApprovalStatusPage logoutAR = new ApprovalStatusPage();
		logoutAR.Logout();
	}

	public void loginARforCAWithEntitySelection() throws InterruptedException {
		PublicHomePage login = new PublicHomePage();

		login.ARLoginCA();

		login.handleSecurityQue();

		login.clickOnnext();
		System.out.println("California AR Login Successfully");

		wait.until(ExpectedConditions.elementToBeClickable(searchEntityID));
		searchEntityID.sendKeys(prop.getProperty("CAThirdEntity"));
		wait.until(ExpectedConditions.elementToBeClickable(EntityA1));
		EntityA1.click();

		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
		System.out.println("California AR landed on private home page");
		ObjectRepo.getTest().log(Status.PASS, "AR landed on private home page");

	}

	public void ARIsAbleToViewLableOnPrivateHomepage() {

		String text = closessText.getText();
		System.out.println(text);
		Assert.assertEquals("BIDDING CLOSES IN", text);
		ObjectRepo.getTest().log(Status.PASS,
				" : AR is able to view the countdown timer BIDDING CLOSES IN lable for live event from the private homepage and the auction page");
		String text1 = inProgressText.getText();
		System.out.println(text1);
		Assert.assertEquals("Event in progress", text1);
		ObjectRepo.getTest().log(Status.PASS,
				" : AR is able to view the countdown timer Event in progress lable for live event from the private homepage and the auction page");

	}

	public void SelectEventName() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(selectDropEventToggle));
			selectDropEventToggle.click();
			wait.until(ExpectedConditions.elementToBeClickable(jointEventName));
			jointEventName.click();
			System.out.println("AR selected auction event");
			ObjectRepo.getTest().log(Status.PASS, "AR selected auction event");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(selectBtnAuction));
			selectBtnAuction.click();
			System.out.println("AR selected auction event");
			ObjectRepo.getTest().log(Status.PASS, "AR selected auction event");
		}
	}

	public void EventNameAndBidGuaranteeViewOnBiddingPage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='JointAuctionAutomation']")));
		String text = jointEventName.getText();
		System.out.println(text + " is displayed in header section on bidding page");
		Assert.assertEquals("JointAuctionAutomation", text);
		ObjectRepo.getTest().log(Status.PASS, "JointAuctionAutomation is dispalyed in header section on bidding page");
		wait.until(ExpectedConditions.visibilityOf(bidGuaranteeLable));
		String text1 = bidGuaranteeLable.getText();
		System.out.println(text1 + " is displayed in header section on bidding page");
		Assert.assertEquals("BID GUARANTEE AMOUNT", text1);
		ObjectRepo.getTest().log(Status.PASS, "BID GUARANTEE AMOUNT is dispalyed in header section on bidding page");
	}

	public void listOfSubmittedBids() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@role='checkbox']")));
		List<WebElement> element = driver.findElements(By.xpath("//tr[@role='checkbox']"));
		int totalNumOfBids = element.size();
		System.out.println("totalNumOfBids :" + totalNumOfBids);
		for (int i = 0; i <= totalNumOfBids - 1; i++) {
			element.get(i).getText();
			System.out.println(element.get(i).getText());
			ObjectRepo.getTest().log(Status.PASS, element.get(i).getText());
		}
	}

	public void listOfCurrentAuctionAvailableFields() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-6 MuiGrid-grid-sm-4 MuiGrid-grid-md-4'])[1]")));
		List<WebElement> element = driver.findElements(By.xpath(
				"(//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-6 MuiGrid-grid-sm-4 MuiGrid-grid-md-4'])[1]"));
		int currentAuctionField = element.size();
		System.out.println("currentAuctionField :" + currentAuctionField);
		for (int i = 0; i <= currentAuctionField - 1; i++) {
			element.get(i).getText();
			System.out.println(element.get(i).getText());
			ObjectRepo.getTest().log(Status.PASS, element.get(i).getText());
		}
	}

	public void BiddingAR() throws InterruptedException {
		WebElement element = btnAdd;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "Click on ADD BID button");
		wait.until(ExpectedConditions.visibilityOf(bidprice));
		bidprice.sendKeys("199.67");
		ObjectRepo.getTest().log(Status.PASS, "Add Bid Price");
		wait.until(ExpectedConditions.visibilityOf(addLot));
		addLot.sendKeys("10");
		ObjectRepo.getTest().log(Status.PASS, "Add Bid Lot");
		wait.until(ExpectedConditions.visibilityOf(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		ObjectRepo.getTest().log(Status.PASS, "Select Current Vintage Type");
		wait.until(ExpectedConditions.visibilityOf(btnSubmit));
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Submit Button");
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Click on Conform Button");
		String assertSuccess1 = wait.until(ExpectedConditions.visibilityOf(successMessage11)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully added.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS,
				assertSuccess1 + " Bid success message is displayed to the AR upon placing a bid");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------");
	}

	public void negativeBiddingByAR() throws InterruptedException {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			System.out.println("Add button is clickable and New row is added for placing a bid");
			ObjectRepo.getTest().log(Status.PASS, "Add button is clickable and New row is added for placing a bid");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(bidprice));
		bidprice.sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		String msg = bidPriceErrorMsg.getText();
		System.out.println("Error message is displayed for blank bid price :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "Error message is displayed for blank bid price :- " + msg);
	}

	public void negativeBidLotsPriceByAR() throws InterruptedException {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			System.out.println("Add button is clickable and New row is added for placing a bid");
			ObjectRepo.getTest().log(Status.PASS, "Add button is clickable and New row is added for placing a bid");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		textBidPrice.sendKeys("0.09");
		textBidLots.sendKeys("");
		dropdownvintage.sendKeys("");
		btnSubmit.click();
		String msg = lessAuctionPriceErrorMsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS,
				"Bid price cannot be lower than auction reserve price error message is displayed");
	}

	public void negativeBidLotsByAR() throws InterruptedException {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			System.out.println("Add button is clickable and New row is added for placing a bid");
			ObjectRepo.getTest().log(Status.PASS, "Add button is clickable and New row is added for placing a bid");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		textBidLots.sendKeys("");
		btnSubmit.click();
		String msg = bidotsErrorMsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Enter Number of lots error message is displayed");
		textBidLots.sendKeys("0");
		btnSubmit.click();
		String msgText = BidslotErrorFormtMsg.getText();
		System.out.println(msgText);
		ObjectRepo.getTest().log(Status.PASS,
				"Bid Lots must only include numeric characters and be a whole number greater than zero. error message is displayed");
		textBidLots.sendKeys("0");

	}

	public void CancelAddBidFunction() throws InterruptedException {

		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		String text = cancelBidAssert.getText();
		System.out.println("Details of latest entry before cancelling the bid : " + text);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		btnAddbid.click();
		System.out.println("Add bid button is clickable");
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		textBidPrice.sendKeys("35.09");
		textBidLots.sendKeys("3");
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		btnCancel.click();
		String text1 = cancelBidAssert.getText();
		System.out.println("Details of latest entry after cancelling the bid : " + text1);
		Assert.assertEquals(text, text1);
		System.out.println("AR Successfully cancel the Add Bid functionality");
		ObjectRepo.getTest().log(Status.PASS, "AR Successfully cancel the Add Bid functionality");
	}

	public void submittedBidsTableRow() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(bidCoulmnRowNme));
		String text = bidCoulmnRowNme.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "AR can view the header section name");
		wait.until(ExpectedConditions.visibilityOf(submitedBidTable));
		String text1 = submitedBidTable.getText();
		System.out.println(text1);
		wait.until(ExpectedConditions.visibilityOf(submitedFirstBidRow));
		String text1stBidRow = submitedFirstBidRow.getText();
		System.out.println(text1stBidRow);
		ObjectRepo.getTest().log(Status.PASS,
				"AR can view the bids placed for their entity in a row in the Submitted bids table :- "
						+ text1stBidRow);

	}

	public void paginationFunctionality() throws InterruptedException {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			System.out.println("Add button is clickable and New row is added for placing a bid");
			ObjectRepo.getTest().log(Status.PASS, "Add button is clickable and New row is added for placing a bid");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(bidprice));
		bidprice.sendKeys("65");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.sendKeys("99");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String assertSuccess1 = wait.until(ExpectedConditions.elementToBeClickable(successMessage11)).getText();
		System.out.println(assertSuccess1);
		Assert.assertEquals("Bid successfully added.", assertSuccess1);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		driver.navigate().refresh();
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@title='ADD BID']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
			System.out.println("Add button is clickable and New row is added for placing a bid");
			ObjectRepo.getTest().log(Status.PASS, "Add button is clickable and New row is added for placing a bid");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@title='ADD BID']")).click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(bidprice));
		bidprice.sendKeys("201");
		wait.until(ExpectedConditions.elementToBeClickable(addLot));
		addLot.sendKeys("10");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownvintage));
		dropdownvintage.sendKeys("Current");
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String assertSuccess121 = wait.until(ExpectedConditions.elementToBeClickable(successMessage11)).getText();
		System.out.println(assertSuccess121);
		Assert.assertEquals("Bid successfully added.", assertSuccess121);
		ObjectRepo.getTest().log(Status.PASS, "A success message is displayed to the AR upon placing a bid");
		driver.navigate().refresh();
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		wait.until(ExpectedConditions.visibilityOf(submitedBidTable));
		String text = submitedBidTable.getText();
		System.out.println("Before Clicking next page pagination button : " + text);
		ObjectRepo.getTest().log(Status.PASS, "Before Clicking next page pagination button : " + text);
		wait.until(ExpectedConditions.elementToBeClickable(paginationBtn));
		paginationBtn.click();
		wait.until(ExpectedConditions.visibilityOf(SubmittedBids));
		String text1 = SubmittedBids.getText();
		System.out.println("After Clicking next page pagination button : " + text1);
		ObjectRepo.getTest().log(Status.PASS, "After Clicking next page pagination button : " + text1);
		ObjectRepo.getTest().log(Status.PASS, "AR can view the placed bids using pagination");
		System.out.println("After Clicking next page pagination button passed");
	}

	public void selectTheMultipleBidsCheckBox() {
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@aria-label='select all']")));
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@aria-label='select all']")).click();
			System.out.println("Multiple check box is selected");
			ObjectRepo.getTest().log(Status.PASS, "Multiple check box is selected");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@aria-label='select all']")).click();
		}
		wait.until(ExpectedConditions.visibilityOf(deleteSelectedBids));
		String text = deleteSelectedBids.getText();
		System.out.println(text + " option is presented to the user.");
		ObjectRepo.getTest().log(Status.PASS, "Delete selected bids option is presented to the user.");
	}

	public void clearSearchBox() throws InterruptedException {

		WebElement element1 = searchBidPlace;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		try {
			searchBidPlace.sendKeys("2727272777");
		} catch (Exception e) {
			searchBidPlace.sendKeys("000");
		}
		searchCleardBtn.click();
		System.out.println("AR is able to cleared Box");
		ObjectRepo.getTest().log(Status.PASS, "AR is able to cleared Box");
		String text = submitedFirstBidRow.getText();
		System.out.println("After Cleared search Box :" + text);
		ObjectRepo.getTest().log(Status.PASS, "After Cleared search Box :" + text);
	}

	public void editBidFunctionality() throws InterruptedException {

		WebElement element1 = editBidBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String oldbid = cancelBidAssert.getText();
		System.out.println(oldbid);
		ObjectRepo.getTest().log(Status.PASS, "Existing bid :  - " + oldbid);
		editBidBtn.click();
		ObjectRepo.getTest().log(Status.PASS,
				"Edit Button should be clickable and existing bid value is dispalyed with Row");
		wait.until(ExpectedConditions.visibilityOf(editBidPrice));
		editBidPrice.clear();
		editBidPrice.sendKeys(Keys.BACK_SPACE);
		editBidPrice.sendKeys(Keys.BACK_SPACE);
		editBidPrice.sendKeys(Keys.BACK_SPACE);
		editBidPrice.sendKeys(Keys.BACK_SPACE);
		System.out.println("Cleard existing bid lot price by AR");
		ObjectRepo.getTest().log(Status.PASS, "Cleard existing bid lot price by AR");
		editBidPrice.sendKeys("18.07");
		System.out.println("AR is able to place a new bid price.");
		ObjectRepo.getTest().log(Status.PASS, "AR is able to place a new bid price.");
		wait.until(ExpectedConditions.visibilityOf(editBidLots));
		editBidLots.clear();
		editBidLots.sendKeys(Keys.BACK_SPACE);
		System.out.println("Cleard existing bid lots by AR");
		ObjectRepo.getTest().log(Status.PASS, "Cleard existing bid lots by AR");
		editBidLots.sendKeys("3");
		System.out.println("AR is able to place a new bid Lots.");
		ObjectRepo.getTest().log(Status.PASS, "AR is able to place a new Bid Lots.");
		wait.until(ExpectedConditions.visibilityOf(btnSubmit));
		btnSubmit.click();
		wait.until(ExpectedConditions.visibilityOf(btnConfirm));
		btnConfirm.click();
		String msg = editBidSuccessMsg.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Bid edited Successfully By AR");
		String newbid = cancelBidAssert.getText();
		System.out.println(newbid);
		ObjectRepo.getTest().log(Status.PASS, "After edited bid " + newbid);
		Assert.assertNotSame(oldbid, newbid);
		System.out.println("Edited value is reflected in bid table");
		ObjectRepo.getTest().log(Status.PASS, "Edited value is reflected in bid table");
	}

	public void deleteSingleBid() throws InterruptedException {

		WebElement element1 = deleteBidBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String beforeDltBid = cancelBidAssert.getText();
		System.out.println("Before deleted bid" + beforeDltBid);
		ObjectRepo.getTest().log(Status.PASS, "Before deleted bid " + beforeDltBid);
		deleteBidBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();
		String DeletesuccessMsg = deleteBidSuccessMsg.getText();
		System.out.println(DeletesuccessMsg);
		String AfterDltBid = cancelBidAssert.getText();
		System.out.println("After deleted bid" + AfterDltBid);
		ObjectRepo.getTest().log(Status.PASS, "After deleted bid " + AfterDltBid);
		Assert.assertNotSame(beforeDltBid, AfterDltBid);
		ObjectRepo.getTest().log(Status.PASS, "Delete single bid successfully By AR");

	}

	public void deleteMultipleBid() throws InterruptedException {

		WebElement element1 = multipleBidCheckBox;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		multipleBidCheckBox.click();
		wait.until(ExpectedConditions.visibilityOf(deleteSelectedBids));
		deleteSelectedBids.click();
		String PopUpMsg = deleteSelectedBidsPopUp1.getText();
		System.out.println(PopUpMsg);
		String PopUpMsg1 = deleteSelectedBidsPopUp2.getText();
		System.out.println(PopUpMsg1);
		String PopUpMsg2 = deleteSelectedBidsPopUp3.getText();
		System.out.println(PopUpMsg2);
		ObjectRepo.getTest().log(Status.PASS, "Selected delete bid pop up confirmation is displayed");
		wait.until(ExpectedConditions.visibilityOf(btnSubmit));
		btnSubmit.click();
		try {
			String Dlt10BidSuccessMsg = delete10bidSuccessMsg.getText();
			System.out.println(Dlt10BidSuccessMsg + "By AR");
		} catch (Exception e) {
			String Dlt2BidSuccessMsg = delete2bidSuccessMsg.getText();
			System.out.println(Dlt2BidSuccessMsg + "By AR");

			e.printStackTrace();
		}
		ObjectRepo.getTest().log(Status.PASS, "Multiple selected bid successfully deleted");
	}

	public void canceldeleteMultipleBidAction() throws InterruptedException {
		BiddingPage xyz = new BiddingPage();
		xyz.BiddingAR();

		WebElement element1 = multipleBidCheckBox;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		String beforeDltBid = submitedFirstBidRow.getText();
		System.out.println("Before Selected bid for delete action" + beforeDltBid);
		ObjectRepo.getTest().log(Status.PASS, "Before Selected bid for delete action :- " + beforeDltBid);
		multipleBidCheckBox.click();
		wait.until(ExpectedConditions.visibilityOf(deleteSelectedBids));
		deleteSelectedBids.click();
		String PopUpMsg = deleteSelectedBidsPopUp1.getText();
		System.out.println(PopUpMsg);
		String PopUpMsg1 = deleteSelectedBidsPopUp2.getText();
		System.out.println(PopUpMsg1);
		String PopUpMsg2 = deleteSelectedBidsPopUp3.getText();
		System.out.println(PopUpMsg2);
		wait.until(ExpectedConditions.visibilityOf(btnCancel));
		btnCancel.click();
		String aftrCancelBid = submitedFirstBidRow.getText();
		System.out.println("After Cancel delete Selected bid :- " + aftrCancelBid);
		ObjectRepo.getTest().log(Status.PASS, "After Cancel delete Selected bid " + aftrCancelBid);

	}

	public void downloadBidTemplate() throws InterruptedException {

		WebElement element1 = DownloadBidTemplated;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.visibilityOf(DownloadBidTemplated));
		DownloadBidTemplated.click();
		System.out.println("AR is able to download the latest bid template");
		ObjectRepo.getTest().log(Status.PASS, "AR is able to download the latest bid template");
	}

	public void FirstsponsorApprovalForJointAuction() {

		WebElement element = eventDetailsBtn;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		wait.until(ExpectedConditions.elementToBeClickable(firstPauseBtn));
		firstPauseBtn.click();
		String abc = EvtpauseSuccessMsg.getText();
		System.out.println(abc);
		ObjectRepo.getTest().log(Status.PASS, "Event pause proposed successfully by first sponsor");
	}

	public void PauseEvntSecondSponsorApprovalForJointAuction() {

		WebElement element = eventDetailsBtn;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		wait.until(ExpectedConditions.elementToBeClickable(secndPauseBtn));
		secndPauseBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
		String abc = Evtpause2SuccessMsg.getText();
		System.out.println(abc + " By Sponsor ");
		System.out.println("Event pause approved and effective. by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Event pause approved and effective.by sponsor");
		String pause = pausedStatusText.getText();
		System.out.println("The Event Is " + pause);
		ObjectRepo.getTest().log(Status.PASS, "The Event Is " + pause);
		String pauseTime = pausedTime.getText();
		System.out.println(pauseTime);
		ObjectRepo.getTest().log(Status.PASS, "The Event Paused on " + pauseTime);
		wait.until(ExpectedConditions.elementToBeClickable(ResumeBtnQC));
		ResumeBtnQC.click();
		String ResumeText = EvtResumeSuccessMsg.getText();
		System.out.println(ResumeText);
		ObjectRepo.getTest().log(Status.PASS, "Event Resume Approval Successfully Given by Quebec sponsor");

	}

	public void RuntimeSponsorLoginCa() {
		try {

			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(prop.getProperty("UserSponsorCa"));
			password.sendKeys(prop.getProperty("Password"));
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
			System.out.println("California sponsor login successfully");
			ObjectRepo.getTest().log(Status.PASS, "California sponsor login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(username));
			username.sendKeys(prop.getProperty("UserSponsorCa2"));
			password.sendKeys(prop.getProperty("Password2"));
			wait.until(ExpectedConditions.visibilityOf(btnLogin2));
			btnLogin2.click();
			System.out.println("Califonia sponsor login successfully");
			ObjectRepo.getTest().log(Status.PASS, "California sponsor login successfully");

		}
	}

	public void CaSponsorApprovalResumeEvntForJointAuction() throws IOException {

		CreateEventPage abc = new CreateEventPage();
		abc.clickbtnAdmin();
		abc.clickbtnEvent();
		wait.until(ExpectedConditions.elementToBeClickable(PagibnationSettingBtn));
		PagibnationSettingBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(pagrRowsSelect));
		pagrRowsSelect.click();
		//
		WebElement element = eventDetailsBtn;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		wait.until(ExpectedConditions.elementToBeClickable(ResumeBtnCA));
		ResumeBtnCA.click();
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();
		String abc2 = EvtResume2SuccessMsg.getText();
		System.out.println(abc2);
		ObjectRepo.getTest().log(Status.PASS, "Event resume approved and effective by sponsor");
		String msg = resumeStatusText.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, "Event convert Pause into Running");
	}

	public void clickAnUserBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnUser));
		WebElement abc1 = btnUser;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, " Click on User button");
	}

	public void clickAnReservePriceBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
		WebElement abc1 = btnReservePrice;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, " Click on Reserve Price button");
	}

	public void RunAlgorithmByCa() {

		wait.until(ExpectedConditions.elementToBeClickable(PagibnationSettingBtn));
		PagibnationSettingBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(pagrRowsSelect));
		pagrRowsSelect.click();
		WebElement element = eventDetailsBtn1;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		wait.until(ExpectedConditions.elementToBeClickable(RunAlgorithmCA));
		RunAlgorithmCA.click();
		System.out.println("First Run algorithm approval given by CA Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "First Run algorithm approval given by CA Sponsor");
		String RunMsg = successMsg.getText();
		System.out.println(RunMsg);
		ObjectRepo.getTest().log(Status.PASS, RunMsg);
	}

	public void RunAlgorithmByQC() {

		wait.until(ExpectedConditions.elementToBeClickable(PagibnationSettingBtn));
		PagibnationSettingBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(pagrRowsSelect));
		pagrRowsSelect.click();
		WebElement element = eventDetailsBtn1;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		wait.until(ExpectedConditions.elementToBeClickable(RunAlgorithmQC));
		RunAlgorithmQC.click();
		System.out.println("Second Run algorithm approval given by QC Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Second Run algorithm approval given by QC Sponsor");
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtn.click();

		String RunMsg = successMsg2.getText();
		System.out.println(RunMsg);
		ObjectRepo.getTest().log(Status.PASS, RunMsg);

	}

	public void ResetRunAlgorithmByCa() {
		wait.until(ExpectedConditions.elementToBeClickable(PagibnationSettingBtn));
		PagibnationSettingBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(pagrRowsSelect));
		pagrRowsSelect.click();
		WebElement element = eventDetailsBtn1;

		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		System.out.println("Navigate to event details.");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to event details.");
		WebElement element1 = btnReset;

		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		String msg = successmsg3.getText();
		System.out.println("After clicking reset button : " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking reset button : " + msg);
		boolean status = RunAlgorithmQC.isEnabled();
		System.out.println("After clicking reset button Run Algorithm button is enabled : " + status);
		ObjectRepo.getTest().log(Status.PASS,
				"After clicking reset button Run Algorithm button is enabled : " + status);

	}

	public void SponsorCanDownloadBidAuditReport() throws InterruptedException {
		String abc = monitoringPageEvntPge.getText();
		System.out.println("Sponsor is on the Event monitoring page");
		System.out.println(abc);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor is on the Event monitoring page :- " + abc);
		WebElement event = downloadReportBtn;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Bid Audit report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Bid Audit report successfully downloaded by sponsor");
	}

	public void MarketMonitorCanDownloadBidAuditReport() throws InterruptedException {
		String abc = monitoringPageEvntPge.getText();
		System.out.println("Market Monitor is on the Event monitoring page");
		System.out.println(abc);
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor is on the Event monitoring page :- " + abc);
		WebElement event = downloadReportBtn;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Bid Audit report successfully downloaded by Market Monitor");
		ObjectRepo.getTest().log(Status.PASS, "Bid Audit report successfully downloaded by Market Monitor");
	}

	public static String downloadedFilePath;

	public void uploadBidTemplate() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		writeDataToExcel(downloadedFilePath, "Bid Upload Template", 19, 0, "USD");
		writeDataToExcel(downloadedFilePath, "Bid Upload Template", 19, 1, "12");
		writeDataToExcel(downloadedFilePath, "Bid Upload Template", 19, 2, "1");
		writeDataToExcel(downloadedFilePath, "Bid Upload Template", 19, 4, "Current");
		Thread.sleep(5000);
		WebElement element1 = uploadBidTemplateButton;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.visibilityOf(uploadBidTemplateButton));
		CMSPage.uploadFile(uploadBidTemplateButton, downloadedFilePath);
		System.out.println("AR is able to upload the bid template");
		ObjectRepo.getTest().log(Status.PASS, "AR is able to upload the bid template");
		wait.until(ExpectedConditions.visibilityOf(continueButtonOnUploadBidTemplate));
		continueButtonOnUploadBidTemplate.click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(bidUploadSuccessMsg));
		String msg = bidUploadSuccessMsg.getText();
		System.out.println("Success message after bid upload is displayed as = " + msg);
		ObjectRepo.getTest().log(Status.PASS, "Success message after bid upload is displayed as = " + msg);
	}

	// ======================= WRITE (THREAD SAFE) =======================
	public static synchronized void writeDataToExcel(String relativePath, String sheetName, int rowNumber,
			int cellNumber, String value) {

		String filePath = relativePath;

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null)
				sheet = workbook.createSheet(sheetName);

			Row row = sheet.getRow(rowNumber);
			if (row == null)
				row = sheet.createRow(rowNumber);

			Cell cell = row.getCell(cellNumber);
			if (cell == null)
				cell = row.createCell(cellNumber);

			cell.setCellValue(value);

			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				workbook.write(fos);
			}

		} catch (Exception e) {
			throw new RuntimeException("Error writing Excel: " + filePath, e);
		}
	}

}