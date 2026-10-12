package com.wciauction.pageObjects.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import com.wci.auction.org.resources.ObjectRepo;

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class CreateEventPage3 {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public String randomString;
	public static Actions action;
	String date1;
	
	@FindBy(xpath = "//*[text()='You have been successfully logged out.']")
    private WebElement Logoutmessage;

    @FindBy(xpath = "//span[text()='Administration']")
    private WebElement btnAdminstration;

    @FindBy(xpath = "//a[text()='Event']")
    private WebElement btnEvent;

    @FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
    private WebElement btnCreateEvent;

    @FindBy(xpath = "(//input[@name='eventBiddingPeriodOpen'])")
    private WebElement dateBiddingPeroidOpen;

    @FindBy(xpath = "//*[@name='eventApplicationOpen']")
    private WebElement dateEventAppOpen;

    @FindBy(xpath = "//input[@name='eventApplicationClose']")
    private WebElement dateEventAppClose;

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

    @FindBy(xpath = "//input[@id='eventId']")
    private WebElement textBoxEventID;

    @FindBy(xpath = "//select[@name='eventType']")
    private WebElement eventTypeDropDown;

    @FindBy(xpath = "//input[@id='eventNameInEnglish']")
    private WebElement eventName;

    @FindBy(xpath = "//div[@id='demo-customized-select']")
    private WebElement ParticipatingJurisdictions;

    @FindBy(xpath = "//label[text()='Participating Jurisdictions']")
    private WebElement PJs;

    @FindBy(xpath = "//option[text()='Nova Scotia']")
    private WebElement textPJs;
    
    @FindBy(xpath = "//*[@id='eventNameInFrench']")
    private WebElement frenchEventName;

    @FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
    private WebElement btnNovaScotia;

    @FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
    private WebElement currencyAccNS;

    @FindBy(xpath = "(//select[@id='demo-customized-select'])[4]")
    private WebElement dropdowncategoryNS;

    @FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[6]")
    private WebElement dropdowncategory2NS;

    @FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[8]")
    private WebElement dropdowncategory3NS;

    @FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[4]")
    private WebElement textcategorypriceNS;

    @FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[6]")
    private WebElement textcategoryprice2NS;

    @FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[8]")
    private WebElement textcategoryprice3NS;

    @FindBy(xpath = "(//input[@class='MuiInputBase-input MuiInput-input'])[5]")
    private WebElement texttotalallowanceNS;

    @FindBy(xpath = "(//input[contains(@class,'MuiInputBase-input MuiInput-input')])[7]")
    private WebElement texttotalallowance2NS;

    @FindBy(xpath = "(//input[contains(@class,'MuiInputBase-input MuiInput-input')])[9]")
    private WebElement texttotalallowance3NS;

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[1]")
    private WebElement deletecategory1;

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root'])[2]")
    private WebElement deletecategory2;

    @FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[1]")
    private WebElement errmsgcategory;

    @FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-marginDense'])[1]")
    private WebElement errorallowance;

    @FindBy(xpath = "//*[text()='Add New']")
    private WebElement btnaddnew;

    @FindBy(xpath = "(//select[@name='processingOrder'])[1]")
    private WebElement drpdownProCessOrder1NS;

    @FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[7]")
    private WebElement drpdownProCessOrder2NS;

    @FindBy(xpath = "(//select[contains(@id,'demo-customized-select')])[9]")
    private WebElement drpdownProCessOrder3NS;

    @FindBy(xpath = "//input[@name='eventBiddingPeriodOpen']")
    private WebElement dateeventBiddingPeriodOpenNS;

    @FindBy(xpath = "//input[@name='eventBiddingPeriodClose']")
    private WebElement dateeventBiddingPeriodCloseNS;

    @FindBy(xpath = "//input[@name='eventApplicationOpen']")
    private WebElement dateeventApplicationOpenNS;

    @FindBy(xpath = "//input[@name='eventApplicationClose']")
    private WebElement dateeventApplicationCloseNS;

    @FindBy(xpath = "//*[@name='eventBiddingPeriodClose']")
    private WebElement dateBiddingPeriodClose;

    @FindBy(xpath = "//input[@name='eventClosure']")
    private WebElement dateEventClosure;

    @FindBy(xpath = "//input[@name='isLimitMaximumBidsAllowed']")
    private WebElement radiobtnLmtMaxBid;

    @FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[1])[2]")
    private WebElement btnMinusLmtMxBid;

    @FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[2])[2]")
    private WebElement btnPlusLimitMxBid;

    @FindBy(xpath = "//input[@name='limitMaximumBidsAllowed']")
    private WebElement textLimitMxBid;

    @FindBy(xpath = "(//input[@value='1000'])[2]")
    private WebElement bydefaultLmtMaxBid;

    @FindBy(xpath = "//input[@name='allowancesPerBidLot']")
    private WebElement AllowancesPerBidLot;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[1]")
    private WebElement AllowancesPerBidLot1;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-spacing-xs-3']//button[2]")
    private WebElement AllowancesPerBidLot2;

    @FindBy(xpath = "//input[@name='rollDownProcedure']")
    private WebElement radioRollDown1;

    @FindBy(xpath = "//input[@name='setMaximumBidPrice']")
    private WebElement radiobtnCombnedEvaluation;

    @FindBy(xpath = "//span[text()='SAVE']")
    private WebElement btnSave;

    @FindBy(xpath = "//span[text()='CANCEL']")
    private WebElement btncancel;

    @FindBy(xpath = "//span[text()='CLEAR']")
    private WebElement btnclear;

    @FindBy(xpath = "//p[contains(text(),'Enter Event ID')]")
    private WebElement eventiderrormsg;

    @FindBy(xpath = "//h1[text()='Event Administration']")
    private WebElement eventadminpage;

    @FindBy(xpath = "//p[text()='The event has been successfully created']")
    private WebElement alertPopup;

    @FindBy(xpath = "//button[@aria-label='Select number of rows per page.']")
    private WebElement btngear;

    @FindBy(xpath = "//li[text()='500 ']")
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

    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[9]/button[1]")
    private WebElement eventDetailsBtn;

    @FindBy(xpath = "//button[@aria-label='approval1Status']//span[@class='MuiIconButton-label']//*[name()='svg'][2]")
    private WebElement btnFirstApproval;

    @FindBy(xpath = "//button[@aria-label='approval2Status']//span[@class='MuiIconButton-label']//*[name()='svg'][2]")
    private WebElement btnSecondApproval;

    @FindBy(xpath = "//span[text()='REJECT']")
    private WebElement rejectBtn;

    @FindBy(xpath = "//button[@aria-label='approval1Status']//span[@class='MuiIconButton-label']//*[name()='svg'][1]")
    private WebElement btnRejectApproval1;

    @FindBy(xpath = "//button[@aria-label='approval2Status']//span[@class='MuiIconButton-label']//*[name()='svg'][1]")
    private WebElement btnRejectApproval2;

    @FindBy(xpath = "//button[@aria-label='approval2Status']//span[@class='MuiIconButton-label']//*[name()='svg'][1]")
    private WebElement btnSecondDisApproval;

    @FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
    private WebElement btnSubmit1;

    @FindBy(xpath = "//*[text()='SUBMIT']")
    private WebElement btnSubmit2;

    @FindBy(xpath = "//button[@title='APPROVE']")
    private WebElement popupBtn;

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

    @FindBy(xpath = "//tr[@class='MuiTableRow-root'][1]")
    private WebElement validateCatAAllowances;

    @FindBy(xpath = "//tr[@class='MuiTableRow-root'][2]")
    private WebElement validateCatBAllowances;

    @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]")
    private WebElement validateTime;

    @FindBy(xpath = "//span[text()='Events']")
    private WebElement btnEventARLogin;

    @FindBy(xpath = "//a[text()='R232']")
    private WebElement selectEvent;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-7']")
    private WebElement bidGuaranteeAmt;

    @FindBy(xpath = "//span[contains(text(),'Welcome, ')]")
    private WebElement btnProfile;

    @FindBy(xpath = "//li[text()='Logout']")
    private WebElement btnLogout;

    @FindBy(xpath = "//*[text()='Reports']")
    private WebElement report;

    @FindBy(xpath = "//span[text()='Search Reports']")
    private WebElement searchreport;

    @FindBy(xpath = "(//select[@id='demo-customized-select'])[1]")
    private WebElement reportnameselect;

    @FindBy(xpath = "(//*[@value='9945ea261007f8b0f0f3d01c098a9750'][text()='Qualified Bidders Report'])[1]")
    private WebElement QualifiedBiddersReport;

    @FindBy(xpath = "(//*[@value='e342728e4268d0d0f68c1ab060f091c2'][text()='Client Bids Audit Report'])[1]")
    private WebElement ClientBidsAuditReport;

    @FindBy(xpath = "(//*[@value='7a812a609ab879093543360f6deaaed6'][text()='Client Bids Audit Report'])[1]")
    private WebElement BidsReport;

    @FindBy(xpath = "(//*[@value='8d93b305580bd47a1292bad13b1016b9'][text()='Bids Audit Report'])[1]")
    private WebElement BidsAuditReport;

    @FindBy(xpath = "(//*[@value='217fd7ac10f7a7b85b1ff64af532cf69'][text()='User Activity Audit Report'])[1]")
    private WebElement UserActivityAuditReport;

    @FindBy(xpath = "(//*[@value='4348b2de84313209c578ba32b8a32598'][text()='Auction Summary Report'])[1]")
    private WebElement AuctionSummaryReport;

    @FindBy(xpath = "(//*[@value='51278c71f5887f67c1aa1df6b0c0b3a7'][text()='Results Report'])[1]")
    private WebElement ResultsReport;

    @FindBy(xpath = "(//*[@value='51abc8d13bbec13cd3a2db7e7a9b7f1c'][text()='Allowance Delivery Report'])[1]")
    private WebElement AllowanceDeliveryReport;

    @FindBy(xpath = "(//*[@value='47ed130dfd72c5f00390a0ffd2444b0b'][text()='Financial Settlement Report'])[1]")
    private WebElement FinancialSettlementReport;

    @FindBy(xpath = "(//*[@value='d20df46f27b731897b493070fa2c2b33'][text()='Financial Statement'])[1]")
    private WebElement FinancialStatement;

    @FindBy(xpath = "(//*[@value='2dece2740d94a65c5e2134724b632c2d'][text()='Client Bids Report'])[1]")
    private WebElement ClientBidsReport;

    @FindBy(xpath = "(//*[@value='aa1ae89693becefcabbf15ec590daacc'][text()='Post Event Operations Report'])[1]")
    private WebElement PostEventOperationsReport;

    @FindBy(xpath = "(//*[@value='543f2edbf74cfe30eff01df4ff0a2e1f'][text()='Sale By Agreement Bid Lot Audit Report'])[1]")
    private WebElement SaleByAgreementBidLotAuditReport;

    @FindBy(xpath = "(//*[@value='5ef1378f84d8c302938a57dd95305202'][text()='User Management Report'])[1]")
    private WebElement UserManagementReport;

    
	public CreateEventPage3() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
    }
	

	public void clickreport() throws InterruptedException {
		WebElement abc = report;
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void reportnameselect() throws InterruptedException {
		WebElement abc = reportnameselect;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void clicksearchreport() throws InterruptedException {
		WebElement abc = searchreport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void QualifiedBiddersReport() throws InterruptedException {
		WebElement abc = QualifiedBiddersReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void ClientBidsAuditReport() throws InterruptedException {
		WebElement abc = ClientBidsAuditReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void BidsReport() throws InterruptedException {
		WebElement abc = BidsReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void BidsAuditReport() throws InterruptedException {
		WebElement abc = BidsAuditReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void UserActivityAuditReport() throws InterruptedException {
		WebElement abc = UserActivityAuditReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void AuctionSummaryReport() throws InterruptedException {
		WebElement abc = AuctionSummaryReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void ResultsReport() throws InterruptedException {
		WebElement abc = ResultsReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void AllowanceDeliveryReport() throws InterruptedException {
		WebElement abc = AllowanceDeliveryReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void FinancialSettlementReport() throws InterruptedException {
		WebElement abc = FinancialSettlementReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void FinancialStatement() throws InterruptedException {
		WebElement abc = FinancialStatement;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void ClientBidsReport() throws InterruptedException {
		WebElement abc = ClientBidsReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void PostEventOperationsReport() throws InterruptedException {
		WebElement abc = PostEventOperationsReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void SaleByAgreementBidLotAuditReport() throws InterruptedException {
		WebElement abc = SaleByAgreementBidLotAuditReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void UserManagementReport() throws InterruptedException {
		WebElement abc = UserManagementReport;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void Report() throws InterruptedException {
		clickreport();
	}

	public void generateString() {
		int length = 12;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomString = ("Event_" + generatedString);
	}

	public void clickbtnAdmin() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
	}

	public void clickbtnEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
	}

	public void clickbtnCreateEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateEvent));
		btnCreateEvent.click();
	}

	public void getEventBasics() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(detailsEventID));
		detailsEventID.getText();
		System.out.println(
				"On Event Creation Page Got Event Basics : " + detailsEventID.getText());
		wait.until(ExpectedConditions.visibilityOf(detailsEventType));
		detailsEventType.getText();
		wait.until(ExpectedConditions.visibilityOf(detailsEventType));
		System.out.println(detailsEventType.getText());
		wait.until(ExpectedConditions.visibilityOf(detailsEventPJs));
		detailsEventPJs.getText();
		System.out.println(detailsEventPJs.getText());
		wait.until(ExpectedConditions.visibilityOf(detailsEventNameEng));
		detailsEventNameEng.getText();
		System.out.println(detailsEventNameEng.getText());
		wait.until(ExpectedConditions.visibilityOf(detailsEventNameFrench));
		detailsEventNameFrench.getText();
		System.out.println(detailsEventNameFrench.getText());
	}

	public void gettextBoxEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		textBoxEventID.sendKeys(randomString);
	}

	public void selectEventTypeFromDropDown() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		WebElement eventType = eventTypeDropDown;
		Select s = new Select(eventType);
		s.selectByVisibleText("Sale by Agreement");
	}

	public void validatePJs() {
		PJs.getText();
		System.out.println("participating juridiction name : " + PJs.getText());
		textPJs.getText();
		System.out.println("participating juridiction name : " + textPJs.getText());
	}

	public void enterEventName() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH-mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		eventName.sendKeys(date1 + "_" + formattedDate + "_EventAutomation");
	}

	public void enterFrenchEventName() throws InterruptedException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH-mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		frenchEventName.sendKeys(date1 + "_" + formattedDate + "_EventAutomation");// eventNameFrench
	}

	public void selectJurisdictionForSingleAuction() throws InterruptedException {
		ParticipatingJurisdictions.click();
		try {
			btnNovaScotia.click();
		} catch (Exception e) {
			 
			btnNovaScotia.click();
		}
		WebElement abc2 = frenchEventName;
		action.moveToElement(abc2).build().perform();
		action.clickAndHold(abc2).build().perform();
		action.click(abc2).build().perform();
		frenchEventName.sendKeys("ABC");
	}

	public void selectCurrencyAccNS() {
		WebElement abc3 = currencyAccNS;
		Select CADcurr = new Select(abc3);
		CADcurr.selectByVisibleText("CAD");
	}

	public void currencyAccForSingleAuctionWasington() throws InterruptedException {

		try {
			 
			WebElement abc3 = currencyAccNS;
			action.moveToElement(abc3).click().build().perform();
			 
			action.click(abc3).build().perform();
			 
			action.click(abc3).build().perform();
		} catch (Exception e) {
			 
			currencyAccNS.click();
		}
	}
	public void errorCategoryNS() throws InterruptedException {
		 
		WebElement errcategorydropdown = dropdowncategoryNS;
		Select abcd1 = new Select(errcategorydropdown);
		abcd1.selectByVisibleText("Category A");
		textcategorypriceNS.sendKeys("0");
		texttotalallowanceNS.sendKeys("123");
		WebElement processorderdropdown = drpdownProCessOrder1NS;
		Select abc66 = new Select(processorderdropdown);
		abc66.selectByVisibleText("1");
	}

	public void errMsgCategory() throws InterruptedException {
		 
		System.out.println("Error message for category prices :" + errmsgcategory.getText());
	}

	public void errorAllowanceNS() throws InterruptedException {
		WebElement errcategorydropdown = dropdowncategoryNS;
		Select abcd1 = new Select(errcategorydropdown);
		abcd1.selectByVisibleText("Category A"); // category A
		textcategorypriceNS.sendKeys("12");
		texttotalallowanceNS.sendKeys("0");
		WebElement processorderdropdown = drpdownProCessOrder1NS;
		Select abc67 = new Select(processorderdropdown);
		abc67.selectByVisibleText("1");
	}

	public void errorMsgAllowance() throws InterruptedException {
		WebElement abc68 = errorallowance;
		System.out.println(abc68.getText());
		String actualabc68 = errorallowance.getText();
		String expectedabc68 = "Total Allowances Available should be > 0";
		Assert.assertEquals(actualabc68, expectedabc68);
		System.out.println(
				"Error Message for Total Allowance available :" + errorallowance.getText());
	}

	public void selectDropDownCategoryNS() throws InterruptedException {

		 
		wait.until(ExpectedConditions.elementToBeClickable(dropdowncategoryNS));
		WebElement categorydropdown = dropdowncategoryNS;
		Select catdrop1 = new Select(categorydropdown);
		catdrop1.selectByVisibleText("Category A"); // category A
		// total allowance 1
		WebElement processorderdropdown = drpdownProCessOrder1NS;
		Select abc66 = new Select(processorderdropdown);
		abc66.selectByVisibleText("1");
		textcategorypriceNS.sendKeys("12");
		texttotalallowanceNS.sendKeys("1234");
		// ADD NEW ROW

		
		WebElement element1 = btnaddnew;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD1 = btnaddnew;
		action.moveToElement(abcD1).build().perform();
		action.click(abcD1).build().perform();
		WebElement categorydropdown2 = dropdowncategory2NS;
		Select catdrop2 = new Select(categorydropdown2);
		catdrop2.selectByVisibleText("Category B"); // CATEGORY B
		textcategoryprice2NS.sendKeys("12");
		texttotalallowance2NS.sendKeys("1234"); // total allowance 2
		WebElement processorderdropdown2 = drpdownProCessOrder2NS;// drpdownProCessOrder2NS
		Select abc77 = new Select(processorderdropdown2);
		abc77.selectByVisibleText("2");
		
		WebElement element2 = btnaddnew;
		js.executeScript("arguments[0].scrollIntoView();", element2);
		wait.until(ExpectedConditions.elementToBeClickable(btnaddnew));
		WebElement abcD3 = btnaddnew;
		action.moveToElement(abcD3).build().perform();
		action.click(abcD3).build().perform();
		WebElement categorydropdown3 = dropdowncategory3NS;
		Select catdrop3 = new Select(categorydropdown3);
		catdrop3.selectByVisibleText("Category C"); // CATEGORY C
		textcategoryprice3NS.sendKeys("12");
		texttotalallowance3NS.sendKeys("1234"); // total allowance 3
		WebElement processorderdropdown3 = drpdownProCessOrder3NS;
		Select abc88 = new Select(processorderdropdown3);
		abc88.selectByVisibleText("3");
	}

	public void selectdeletecategory1() throws InterruptedException {
		deletecategory1.click();
		 
		deletecategory2.click();
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(6).plusSeconds(30);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(0).plusMinutes(12).plusSeconds(40);
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

	public void selectPastDatesForApplication() throws InterruptedException {
		dateeventBiddingPeriodOpenNS.click();
		dateeventBiddingPeriodOpenNS.sendKeys("12 May 2022 15:40");
		dateeventBiddingPeriodCloseNS.click();
		dateeventBiddingPeriodCloseNS.sendKeys("12 May 2022 15:45");
		dateeventApplicationOpenNS.click();
		dateeventApplicationOpenNS.sendKeys("02 May 2022 15:38");
		dateeventApplicationCloseNS.click();
		dateeventApplicationCloseNS.sendKeys("02 May 2022 15:39");
		dateEventClosure.click();
		dateEventClosure.sendKeys("12 May 2022 15:50");
	}

	public void disableRadioRollDown() {
		boolean disRadiobtn = radioRollDown1.isEnabled();
		System.out.println(
				"Status of Radio button 'Roll-Down Procedure :" + "True=Enable||False=Disable" + "   " + disRadiobtn);
	}

	public void selectRadioRollDown() throws InterruptedException {
		WebElement radiobtnrolldown = radioRollDown1;
		radiobtnrolldown.click();
		boolean radiobtn = radioRollDown1.isSelected();
		System.out.println("Status of Selected Radio button 'Roll-Down Procedure' :" + radiobtn);
	}

	public void selectRadioBtnCombinedEvaluation() throws InterruptedException {
		WebElement radiobtnCombnEvalu = radiobtnCombnedEvaluation;
		boolean radiobtn2 = radiobtnCombnedEvaluation.isDisplayed();
		System.out.println("Status of Selected Radio button 'Combined Evaluation of Category' :" + radiobtn2);
		radiobtnCombnEvalu.click();
	}

	public void selectradioLimitMaximumBidAllowance() throws InterruptedException {
		WebElement radioLimitBid = radiobtnLmtMaxBid;
		radioLimitBid.click();
		boolean radiobtn1 = radiobtnLmtMaxBid.isSelected();
		System.out.println("Selected Radio button 'Limit Maximum Bids Allowed' :" + radiobtn1);
		String abc21 = bydefaultLmtMaxBid.getAttribute("value");
		System.out.println("Default value for Limit Maximum Bids Allowed : " + abc21);
	}

	public void limitMxBidAllowed() throws InterruptedException {
		 
		textLimitMxBid.click();
		 
		Actions act = new Actions(driver);
		 
		WebElement textbox = bydefaultLmtMaxBid;
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textLimitMxBid.click();
		WebElement textbox2 = textLimitMxBid;
		act.doubleClick(textbox2).perform();
		 
		textLimitMxBid.sendKeys("2");// ------------------------------edit event
		 
		btnMinusLmtMxBid.click();
		System.out.println("The Sponsor can click on the -(Minus) button to reduce to 1.");
		textLimitMxBid.click();
		WebElement textbox3 = textLimitMxBid;
		act.doubleClick(textbox3).perform();
		 
		textLimitMxBid.sendKeys("9998");// --------------------------edit event
		 
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		btnPlusLimitMxBid.click();
		System.out.println("The Sponsor can click on the +(Plus) button to increase to 10000.");
	}

	public void AllowancesPerBidLot() throws InterruptedException {
		Actions act = new Actions(driver);
		 
		WebElement textbox = AllowancesPerBidLot;
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		textbox.sendKeys(Keys.BACK_SPACE);
		AllowancesPerBidLot.click();
		WebElement textbox2 = AllowancesPerBidLot;
		act.doubleClick(textbox2).perform();
		 
		AllowancesPerBidLot.sendKeys("2");// --------------------------edit event
		 
		AllowancesPerBidLot1.click();
		System.out.println("The Sponsor can click on the -(Minus) button to reduce to 1.");
		AllowancesPerBidLot.click();
		WebElement textbox3 = AllowancesPerBidLot;
		act.doubleClick(textbox3).perform();
		 
		AllowancesPerBidLot.sendKeys("9998");// --------------------------edit event
		 
		AllowancesPerBidLot2.click();
		AllowancesPerBidLot2.click();
		AllowancesPerBidLot2.click();
		System.out.println("The Sponsor can click on the +(Plus) button to increase to 10000.");

	}

	public void selectbtnSave() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
	}

	public void selectbtnCancel() throws InterruptedException {

		btncancel.getText();
		System.out.println("Event CANCEL :" + btnclear.getText());

		btncancel.click();

	}

	public void selectbtnClear() throws InterruptedException {

		btnclear.getText();
		System.out.println("Event CLEAR :" + btnclear.getText());

		btnclear.click();
	}

	public void eventIDErrorMsg() {

		eventiderrormsg.click();
		System.out.println("Event cleared validation message : " + eventiderrormsg.getText());

	}

	public void validatePopupforEventCreation() throws InterruptedException {
		 
		wait.until(ExpectedConditions.visibilityOf(alertPopup));
		System.out.println("Event created successfully validation: " + alertPopup.getText());
		String actalert = alertPopup.getText();
		System.out.println(actalert);
		String expectalert = "The event has been successfully created ";
		Assert.assertEquals(expectalert, "The event has been successfully created ");
		System.out.println("Event Created Successfully by Sponsor ");
	}

	public void eventAdminPage() {
		WebElement eventadminpage1 = eventadminpage;
		eventadminpage1.getText();
		System.out.println("Event CANCEL Validation : " + eventadminpage.getText());
	}

	public void clickOnGearControl() throws InterruptedException {
		 

		wait.until(ExpectedConditions.elementToBeClickable(btngear));
		btngear.click();
		 
		String btn500 = btngear500.getText();
		System.out.println(btn500);
		wait.until(ExpectedConditions.elementToBeClickable(btngear500));
		btngear500.click();
	}

	private void totalEventLists1() throws InterruptedException {
		 
		List<WebElement> list = totalCountOfEvents;
		int totalEvents = list.size();
		System.out.println("Total no of event before event creation :" + totalEvents);
		for (int i = 0; i < totalEvents; i++) {
			String eventName = list.get(i).getText();
			System.out.println(eventName);
		}
	}

	public void totalEventsAfterCreation() {
		List<WebElement> list2 = totalCountOfEvents;
		int totalEvents2 = list2.size();
		System.out.println("Total no of event after event creation :" + totalEvents2);
		for (int j = 0; j < totalEvents2; j++) {
			String eventName2 = list2.get(j).getText();
			System.out.println(eventName2);
		}
	}
	public void menuBarListOfEvent() throws InterruptedException {
		 
		String actMenuBarEventType1 = MenuBarEventType.getText();
		System.out.println(actMenuBarEventType1);
		String expectMenuBarEventType1 = MenuBarEventType.getText();
		Assert.assertEquals(actMenuBarEventType1, expectMenuBarEventType1);
		String actMenuBarEventID =MenuBarEventID.getText();
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

	public void BtnNextEventDetails() throws InterruptedException {
		 
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		WebElement textbox = eventDetailsBtn;
		textbox.click();
	}

	public void firstApproval() throws InterruptedException {
		
		 
		try {
			 
			WebElement element1 = btnFirstApproval;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnFirstApproval));
			btnFirstApproval.click();
		} catch (Exception e) {
			 
			wait.until(ExpectedConditions.visibilityOf(btnFirstApproval));
			btnFirstApproval.click();
		}
		try {
			 
			wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
			btnSubmit1.click();
		} catch (Exception e) {
			 
			wait.until(ExpectedConditions.visibilityOf(btnSubmit2));
			btnSubmit2.click();
		}
		wait.until(ExpectedConditions.visibilityOf(firstAppSuccess));
		WebElement firstApprovalSuccess = firstAppSuccess;
		firstApprovalSuccess.getText();
		System.out.println(firstApprovalSuccess.getText());
		String expectFirstAppSuccess = "The approval of the event creation has been proposed by the first sponsor. ";
		Assert.assertEquals(expectFirstAppSuccess,
				"The approval of the event creation has been proposed by the first sponsor. ");

	}
	public void secondApproval() throws InterruptedException {
		
		 
		System.out.println("Navigate to particular event ");
		try {
			WebElement element1 =btnSecondApproval;
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

	public void RejectfirstApproval1() throws InterruptedException {

		
		 
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		 
		btnEvent.click();
		 
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		WebElement textbox = eventDetailsBtn;
		textbox.click();
		System.out.println("Navigate to particular event ");
		try {
			WebElement element1 = btnRejectApproval1;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnRejectApproval1));
			btnRejectApproval1.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnRejectApproval1));
			btnRejectApproval1.click();
		}
		try {
			 
			btnSubmit1.click();
		} catch (Exception e) {
			 
			btnSubmit2.click();
		}
		 
		wait.until(ExpectedConditions.visibilityOf(alertDisputPopup));
		alertDisputPopup.getText();
		System.out.println(alertDisputPopup.getText());
		ObjectRepo.getTest().log(Status.PASS, "The rejection of the event creation has been proposed by the first Sponsor.");
		System.out.println("First Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "First Sponsor rejected Event successfully");
		 
	}

	public void RejectsecondApproval() throws InterruptedException {
		
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on Event ");
		BtnNextEventDetails();
		System.out.println("Step : Click on The 'Go to details of Event ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on The 'Go to details of Event ");
		try {
			WebElement element1 = btnRejectApproval2;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnRejectApproval2));
			btnRejectApproval2.click();
		} catch (Exception e) {
			 
			wait.until(ExpectedConditions.visibilityOf(btnRejectApproval2));
			btnRejectApproval2.click();
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

	public void checkStatus() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(eventAppStatus));
		String stat1 = eventAppStatus.getText();
		Assert.assertEquals(stat1, "APPROVED");
		System.out.println("Event status : " + eventAppStatus.getText());
	}

	public void checkStatus2() {
		wait.until(ExpectedConditions.visibilityOf(alertEventStatus));
		String stat2 = alertEventStatus.getText();
		Assert.assertEquals(stat2, "The event status has been approved.");
		System.out.println("Event Approval success message : " + alertEventStatus.getText());
	}
	public void validatePrices() throws InterruptedException {
		String sbc = validateCatAAllowances.getText();
		Assert.assertEquals(sbc, "Category A CAD 12.00 1,234 1");
		System.out.println("Category A details :" + validateCatAAllowances.getText());

		String sbc1 = validateCatBAllowances.getText();
		Assert.assertEquals(sbc1, "Category B CAD 12.00 1,234 2");
		System.out.println("Category B details :" + validateCatBAllowances.getText());

	}

	public void validatetime() throws InterruptedException {
		validateTime.getText();

	}
	public void selectbtnEditEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnEditEvent));
		btnEditEvent.click();
		System.out.println("Step : Click on The Edit Event button ");
		Actions act = new Actions(driver);
		WebElement element = driver
				.findElement(By.xpath("(//input[@class='MuiInputBase-input MuiInput-input'])[4]"));
		act.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(textcategorypriceNS));
		WebElement textbox = textcategorypriceNS;
		act.moveToElement(textbox).build().perform();
		act.doubleClick(textbox).perform();
		textbox.sendKeys(Keys.BACK_SPACE);
		textcategorypriceNS.sendKeys("100");// ---------------------------EDIT EVENT
		btnSave.click();
		wait.until(ExpectedConditions.visibilityOf(alertEditEventSucess));
		alertEditEventSucess.getText();
		System.out.println("Edit event Success Message :" + alertEditEventSucess.getText());
		closePopupEditEvent.click();
	}
	public void selectEventDetails() throws InterruptedException {
		btnEventARLogin.click();
		selectEvent.click();
		bidGuaranteeAmt.getText();
		System.out.println("The Auction Reserve price for NS is displayed only in CAD : "
				+ bidGuaranteeAmt.getText());
	}

	public void Logout() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnProfile)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
		wait.until(ExpectedConditions.visibilityOf(Logoutmessage));
		String alert = Logoutmessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "You have been successfully logged out.");
	}
	public void createEvent() throws Exception {
		clickbtnAdmin();
		System.out.println("Step 1: Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2: Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 5: Click on Create New Event Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Click on Create New Event Button  ");
		generateString();
		gettextBoxEvent(); // Event Id
		System.out.println("Step 6: Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event ID  ");
		selectEventTypeFromDropDown();
		 
		System.out.println("Step 7: Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Event Type  ");
		enterEventName();
		enterFrenchEventName();
		System.out.println("Step 8: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter the Event Name  ");
		// EVENT DETAILS
		selectCurrencyAccNS();
		System.out.println("Step 9: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDropDownCategoryNS(); // category //allowance //processing order
		System.out.println("Step 10: Select the Category Type ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Category Type");
		selectDates();
		System.out.println("Step 12: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Select the Dates ");
		selectRadioRollDown();
		System.out.println("Step 13: Select the Roll Down radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Select the Roll Down radio button ");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
	}

	public void createEvent1() throws Exception {
		clickbtnAdmin();
		System.out.println("Step 1: Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step 2: Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		 
		clickOnGearControl();// clickOnGearControl //SELECT 500 ROW
		System.out.println("Step 3: Click on 'Select 500 Number Of Rows Per Page");
		ObjectRepo.getTest().log(Status.PASS, "Step 3: Click on 'Select 500 Number Of Rows Per Page ");
		totalEventLists1();
		System.out.println("Step 4: Nova Scotia complete list of Event before event creation  get successfully ");
		ObjectRepo.getTest().log(Status.PASS,
				"Step 4: Nova Scotia complete list of Event before event creation  get successfully ");
		 
		clickbtnCreateEvent();
		System.out.println("Step 5: Click on Create New Event Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Click on Create New Event Button  ");
		// EVENT BASICS
		generateString();
		gettextBoxEvent(); // Event Id
		System.out.println("Step 6: Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event ID  ");
		selectEventTypeFromDropDown();
		 
		System.out.println("Step 7: Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Event Type  ");
		enterEventName();
		enterFrenchEventName();
		System.out.println("Step 8: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Enter the Event Name  ");
		// EVENT DETAILS
		selectCurrencyAccNS();// currencyAcc
		System.out.println("Step 9: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Select the Currency Accepted ");
		selectDropDownCategoryNS(); // category //allowance //processing order
		System.out.println("Step 10: Select the Category Type ");
		ObjectRepo.getTest().log(Status.PASS, "Step 10: Select the Category Type");
		selectDates();
		System.out.println("Step 12: Select the Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step 12: Select the Dates ");
		selectRadioRollDown();
		System.out.println("Step 13: Select the Roll Down radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Select the Roll Down radio button ");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 13: Click on  the Limit Maximum Bids Allowed radio button ");
		selectbtnSave();
		System.out.println("Step 16: Click on the Save button ");
		ObjectRepo.getTest().log(Status.PASS, "Step 16: Click on the Save button ");
		 
		validatePopupforEventCreation();
		System.out.println("Step 17: Validate popup - Event created successfully");
		ObjectRepo.getTest().log(Status.PASS, "Step 17: Validate popup - Event created successfully ");
		 
		clickOnGearControl();// clickOnGearControl //SELECT 500 ROW
		System.out.println("Step 18: Select 500 rows per page ");
		ObjectRepo.getTest().log(Status.PASS, "Step 18: Select 500 rows per page ");
		totalEventsAfterCreation();
		System.out.println("Step 19: Nova Scotia Complete list of Event After event creation  get successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"Step 19: Nova Scotia Complete list of Event After event creation  get successfully ");

	}
	public void firstApprovalEvent() throws InterruptedException { // event details page
		driver.navigate().refresh();
		 
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on Event ");
		BtnNextEventDetails();
		System.out.println("Step : Click on The 'Go to details of Event ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on The 'Go to details of Event ");
		System.out.println("Step : Validate the Categoty prices, Total allowances per category ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Validate the Categoty prices, Total allowances per category ");
		validatetime();
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
	public void secondApprovalEvent() throws InterruptedException {
		ObjectRepo.getTest().log(Status.PASS, " Nova Scotia Second sponsor login successfully");
		 
		clickbtnAdmin();
		System.out.println("Step : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step: Click on Administration ");
		clickbtnEvent();
		System.out.println("Step : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Step : Click on Event ");
		BtnNextEventDetails();
		System.out.println("Step : Click on Event Details Button");
		ObjectRepo.getTest().log(Status.PASS, "Step :  Click on Event Details Button");
		secondApproval();
		System.out.println("Step : Nova Scotia Second approval given successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Step : Nova Scotia Second approval given successfully ");
		 
		checkStatus();
		checkStatus2();
		 
		validatetime();
		System.out.println("Event Creation time : " + validateTime.getText());
		System.out.println(
				"Step : Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		ObjectRepo.getTest().log(Status.PASS,
				" Validate that User is able to see the change in times reflected by the changing of statuses for those events.");
		System.out.println("Nova Scotia Logout successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Nova Scotia Second Logout successfully ");
	}
	public void reApprovalEvent() throws InterruptedException {
		 
		BtnNextEventDetails();
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

	public void DisapprovalEvent2() throws InterruptedException { /// ------------dispute event
		
		 
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
		ObjectRepo.getTest().log(Status.PASS, "Step 3 : Click on Create New Event Button");
		generateString();
		gettextBoxEvent();
		System.out.println("Step  : Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 4 : Enter the Event ID  ");
		selectEventTypeFromDropDown();
		System.out.println("Step  : Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5 : Select the Event Type  ");
		enterEventName();
		System.out.println("Step  : Enter the Event Name  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6 : Enter the Event Name  ");
		// // EVENT DETAILS
		selectCurrencyAccNS();
		System.out.println("Step  : Select Currency Accepted   ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :  Select Currency Accepted ");
		disableRadioRollDown(); // ------------------------------------------------------------disable Roll Down
		System.out.println("Step  : Check the status of Radio-Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the status of Radio-Button  ");
		selectDropDownCategoryNS();
		System.out.println("Step : Select Type of Category  ");
		ObjectRepo.getTest().log(Status.PASS, "Step :  Select Type of Category  ");
		 
		selectDates();
		System.out.println("Step  : Enter the Event Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Enter the Event Dates ");
		// selectRadioBtnCombinedEvaluation();
		selectRadioRollDown();// ----------------------------------------------------------------------Roll
								// Down
		System.out.println("Step  : Select the Radio Button 'Roll Down'");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Select the Radio Button 'Roll Down'");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step  : Click on Radio Button 'Limit Maximum Bids Allowed'  ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Click on Radio Button 'Limit Maximum Bids Allowed'");
		System.out.println("Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'  ");
		selectbtnCancel();
		System.out.println("Nova Scotia event cancelled successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Nova Scotia event cancelled successfully");
		eventAdminPage();
		Logout();
	}
	public void Eventclear() throws Exception {
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		generateString();
		gettextBoxEvent(); // Event Id
		System.out.println("Step : Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, " Enter the Event ID  ");
		 
		selectEventTypeFromDropDown();
		System.out.println("Step : Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Event Type  ");
		enterEventName();
		 
		System.out.println("Step : Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, " Enter the Event Name  ");
		// // EVENT DETAILS
		selectCurrencyAccNS();// currencyAcc
		System.out.println("Step  : Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, " Select the Currency Accepted ");
		selectDropDownCategoryNS();
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
	public void completelistofevents() throws InterruptedException {
		clickbtnAdmin();
		ObjectRepo.getTest().log(Status.PASS, "Step 1: Click on Administration ");
		clickbtnEvent();
		ObjectRepo.getTest().log(Status.PASS, "Step 2: Click on Event ");
		clickOnGearControl();
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
		gettextBoxEvent(); // Event Id
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		selectCurrencyAccNS();// currencyAcc
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Currency Accepted ");
		// -------------------------TO CHECK ALLOWANCE AVAILABLE ERROR MESSAGE
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
		gettextBoxEvent(); // Event Id
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		selectCurrencyAccNS();// currencyAcc
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Select the Currency Accepted ");
		errorCategoryNS();
		ObjectRepo.getTest().log(Status.PASS, "Step 14: Enter invalid Category Prices ");
		selectbtnSave();
		ObjectRepo.getTest().log(Status.PASS, "Step 9: Click on Save Button ");
		 
		errMsgCategory();
		ObjectRepo.getTest().log(Status.PASS, "Step 16: Got Error Message for Category Prices ");

	}

	public void getEventCreationPageBasicDetails() throws InterruptedException {
		 
		clickbtnAdmin();
		System.out.println("Step 1 : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Administration ");
		 
		clickbtnEvent();
		System.out.println("Step 2 : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step 3 : Click on Event Creation Page ");
		ObjectRepo.getTest().log(Status.PASS, " Click on Event Creation Page ");
		WebElement scrollToBottom = driver.findElement(By.xpath("//*[text()='About WCI, Inc.']"));
		
		js.executeScript("arguments[0].scrollIntoView();", scrollToBottom);
		 
		getEventBasics();
		System.out.println("Step 4 : On the Event creation screen, basic fields Get Successfully");
		ObjectRepo.getTest().log(Status.PASS, " On the Event creation screen, basic fields Get Successfully ");
	}

	public void LimitMxNoOfBids() throws InterruptedException {
		 
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
		gettextBoxEvent(); // Event Id
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		 
		validatePJs();// -----------------------------------------participating jurisdiction
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		enterFrenchEventName();
		 
		System.out.println("Step 7: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Enter the Event Name  ");
		selectCurrencyAccNS();// currencyAcc
		System.out.println("Step 8: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Select the Currency Accepted ");
		selectDropDownCategoryNS(); // category //allowance //processing order
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
		 
		// -------------------------------------------------------------------limit max
		// bid allowed
		limitMxBidAllowed();
		// limitMxBidAllowed();
		System.out.println("Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed' ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Limit Maximum Bids Allowed'");
	}

	public void getBiddingPageDetails() throws InterruptedException {
		 
		selectEventDetails();
	}

	public void limitthenumberofallowancesperbidLOT() throws InterruptedException {
		 
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
		gettextBoxEvent(); // Event Id
		ObjectRepo.getTest().log(Status.PASS, "Step 4: Enter the Event ID ");
		selectEventTypeFromDropDown();
		ObjectRepo.getTest().log(Status.PASS, "Step 5: Select the Event Type ");
		enterEventName();
		ObjectRepo.getTest().log(Status.PASS, "Step 6: Enter the Event Name (English) ");
		enterFrenchEventName();
		 
		System.out.println("Step 7: Enter the Event Name   ");
		ObjectRepo.getTest().log(Status.PASS, "Step 7: Enter the Event Name  ");
		selectCurrencyAccNS();// currencyAcc
		System.out.println("Step 8: Select the Currency Accepted  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 8: Select the Currency Accepted ");
		selectDropDownCategoryNS();
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

	public void CombinedEvaluationofCategories() throws Exception {
		clickbtnAdmin();
		System.out.println("Step  : Click on Administration ");
		ObjectRepo.getTest().log(Status.PASS, " Step 1 : Click on Administration ");
		clickbtnEvent();
		System.out.println("Step  : Click on Event");
		ObjectRepo.getTest().log(Status.PASS, " Step 2 : Click on Event ");
		clickbtnCreateEvent();
		System.out.println("Step  : Click on Create New Event Button");
		ObjectRepo.getTest().log(Status.PASS, "Step 3 : Click on Create New Event Button");
		generateString();
		gettextBoxEvent(); // Event Id
		System.out.println("Step  : Enter the Event ID  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 4 : Enter the Event ID  ");
		selectEventTypeFromDropDown();
		System.out.println("Step  : Select the Event Type  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 5 : Select the Event Type  ");
		enterEventName();
		System.out.println("Step  : Enter the Event Name  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6 : Enter the Event Name  ");
		enterFrenchEventName();
		System.out.println("Step  : Enter the French Event Name  ");
		ObjectRepo.getTest().log(Status.PASS, "Step 6 : Enter the French Event Name  ");
		selectCurrencyAccNS();
		System.out.println("Step  : Select Currency Accepted   ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :  Select Currency Accepted ");
		disableRadioRollDown();
		System.out.println("Step  : Check the status of Radio-Button  ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the status of Radio-Button  ");
		selectDropDownCategoryNS();
		System.out.println("Step : Select Type of Category  ");
		ObjectRepo.getTest().log(Status.PASS, "Step :  Select Type of Category  ");
		 
		selectDates();
		System.out.println("Step  : Enter the Event Dates ");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Enter the Event Dates ");
		selectRadioBtnCombinedEvaluation();
		System.out.println("Step  : Select the Radio Button 'Roll Down'");
		ObjectRepo.getTest().log(Status.PASS, "Step  :Select the Radio Button 'Roll Down'");
		selectradioLimitMaximumBidAllowance();
		System.out.println("Step  : Click on Radio Button 'Limit Maximum Bids Allowed'  ");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Click on Radio Button 'Limit Maximum Bids Allowed'");
		System.out.println("Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'");
		ObjectRepo.getTest().log(Status.PASS, "Step  : Check the value entered in textbox of 'Allowances Per Bid Lot'  ");
		selectbtnSave();
		System.out.println("Nova Scotia event Saved successfully ");
		ObjectRepo.getTest().log(Status.PASS, "Nova Scotia event Saved successfully");
		Logout();

	}

}
