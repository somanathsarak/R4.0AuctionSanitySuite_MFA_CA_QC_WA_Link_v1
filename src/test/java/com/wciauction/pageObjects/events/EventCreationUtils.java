package com.wciauction.pageObjects.events;

//import static com.wci.auction.org.resources.listeners.wait;
import static constants.EventNameConstants.*;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

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
import com.wci.auction.org.resources.ObjectRepo;
import static com.wci.auction.org.resources.BaseClass.prop;

public class EventCreationUtils {

	private  WebDriver driver;
	private  WebDriverWait wait;
	private  Actions action;
	private  JavascriptExecutor js;

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//*[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//input[@name='rollDownProcedure']")
	private WebElement rollDownbids;

	@FindBy(xpath = "//span[text()='CREATE NEW EVENT']")
	private WebElement btnCreateEvent;

	@FindBy(xpath = "//input[@id='eventId']")
	private WebElement textBoxEventID;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[1]")
	private WebElement eventTypeDropDown;

	@FindBy(xpath = "(//*[@data-icon='trash-alt'])[3]")
	private WebElement deleteTier;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventName;

	@FindBy(xpath = "//input[@id='eventNameInFrench']")
	private WebElement eventNameFr;

	@FindBy(xpath = "(//*[@id=\"demo-customized-select\"])[3]")
	private WebElement currencyAccCa;

	@FindBy(xpath = "//div[@id='demo-customized-select']")
	private WebElement ParticipatingJurisdictions;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[2]")
	private WebElement btnCalifornia;

	@FindBy(xpath = "//*[@id='menu-participatingJurisdictions']//ul/li[3]")
	private WebElement btnQuebec;

	@FindBy(xpath = "(//*[text()='Washington'])[2]")
	WebElement btnWashinton;

	@FindBy(xpath = "(//div[@id='demo-customized-select'])[1]")
	private WebElement ParticipatingJurisdictionsWa;

	@FindBy(xpath = "//*[@id='eventNameInFrench']")
	private WebElement frenchEventName;

	@FindBy(xpath = "(//div[@id='demo-customized-select'])[2]")
	private WebElement currencyAcc;

	@FindBy(xpath = "//*[text()='USD']")
	private WebElement tagUSD;

	@FindBy(xpath = "//*[text()='CAD']")
	private WebElement tagCAD;

	@FindBy(xpath = "//input[@name='USD']")
	private WebElement textBoxUSD;

	@FindBy(xpath = "//input[@name='CAD']")
	private WebElement textBoxCAD;

	@FindBy(xpath = "(//*[@name='vintages'])[1]")
	private WebElement dropDownVintage1;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[1]")
	private WebElement textBoxVintage1;

	@FindBy(xpath = "(//select[@name='vintageType'])[1]")
	private WebElement drpdownVintageType1;

	@FindBy(xpath = "(//select[@name='vintageType'])[2]")
	private WebElement drpdownVintageType2;

	@FindBy(xpath = "(//*[@name='vintages'])[2]")
	private WebElement dropDownVintage2;

	@FindBy(xpath = "(//input[@name='vintagesAllowancesAvailable'])[2]")
	private WebElement textBoxVintage2;

	@FindBy(xpath = "(//select[@name='processingOrder'])[1]")
	private WebElement drpdownProCessOrder1;

	@FindBy(xpath = "(//select[@name='processingOrder'])[2]")
	private WebElement drpdownProCessOrder2;

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

	@FindBy(xpath = "//select[@name='algorithm']")
	private WebElement dropdownAlgorithm;

	@FindBy(xpath = "//span[text()=\"SAVE\"]/parent::button")
	private WebElement btnSave;

	@FindBy(xpath = "//*[text()='The event has been created successfully.']")
	private WebElement successmsg;

	@FindBy(xpath = "//*[@aria-label='approval1Status']")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnSecondApproval;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//*[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement ApprovalMessage;

	@FindBy(xpath = "//*[@data-icon='check-circle']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//*[text()='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//*[text()='Minimum Price']")
	private WebElement btnMinPrice;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "//*[@href='/reservepriceadministrations']")
	private WebElement btnReservePrice2;
	
	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement rowsPerPage;
 
	@FindBy(xpath = "//*[text()='500 ']")
	private WebElement selectRowsNum;

//	@FindBy(xpath = "//td[p[contains(text(), 'A297_ReportsValidationTests655it4')]]/following-sibling::td//input[contains(@aria-label, 'Enter FX Rate for CA')]")
//	private WebElement textBoxFxCal;
//
//	@FindBy(xpath = "//td[p[contains(text(), 'A297_ReportsValidationTests655it4')]]/parent::tr//following-sibling::tr[1]//input[contains(@aria-label, 'Enter FX Rate for QC')]")
//	private WebElement textBoxFxQc;
//
//	@FindBy(xpath = "(//td[div//input[@aria-label='Enter FX Rate for QC']]/following-sibling::td//button[contains(@class, 'MuiButton-containedPrimary')])[1]")
//	private WebElement btnsubmitqc;
//
//	@FindBy(xpath = "(//td[div//input[@aria-label='Enter FX Rate for CA']]/following-sibling::td//button[contains(@class, 'MuiButton-containedPrimary')])[1]")
//	private WebElement btnsubmitCal;

	@FindBy(xpath = "//*[text()='FX Rate submitted.']")
	private WebElement QCFxRateText;

	@FindBy(xpath = "//button[@title='SUBMIT']")
	private WebElement btnConfirmSubmit;

	@FindBy(xpath = "//button[@title='Settings']")
	private WebElement btnSettings;

	@FindBy(xpath = "//a[text()='Execute API']")
	private WebElement btnExecuteAPI;

	@FindBy(xpath = "//th[text()=\"GetQualifiedBidder\"]/following-sibling::td[5]//button")
	private WebElement APIGetQualifiedBidder;

	@FindBy(xpath = "//th[text()=\"GetBidLimitations\"]/following-sibling::td[5]//button")
	private WebElement APIGetBidLimitations;

	@FindBy(xpath = "//span[text()='Execute API']")
	private WebElement APIexecutionPopupbtn;

	@FindBy(xpath = "//*[@placeholder=\"Enter Entity Name or Entity ID\"]")
	private WebElement entityNameInput;

	@FindBy(xpath = "(//*[@type=\"checkbox\"])[2]")
	private WebElement secondCheckbox;

	@FindBy(xpath = "(//*[@class=\"MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary\"])[2]")
	private WebElement submitButton;

	private By biddingWindowOpenTimer = By.xpath("//*[text()='BIDDING CLOSES IN']");

	@FindBy(xpath = "//button[@type=\"button\"]//child::span[text()='ADD BID']")
	private WebElement btnAddbid;

	@FindBy(xpath = "//*[@id=\"bidLot\"]")
	private WebElement textBidLot;

	@FindBy(xpath = "//*[@name=\"tier\"]")
	private WebElement selectTier;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement selectCatagory;

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

	@FindBy(xpath = "//*[contains(text(),'Bid successfully')]")
	private WebElement alertMsg;

	@FindBy(xpath = "//*[text()='Bid successfully submitted.']")
	private WebElement alertMsg2;

	@FindBy(xpath = "//button//*[@data-icon='times']")
	private WebElement alertPopup;

	@FindBy(xpath = "//*[text()='Auction']")
	private WebElement Auction;

	@FindBy(xpath = "//span[text()='Events']//parent::button")
	private WebElement DropDownEvents;

	public static By eventnameonbidpage_PE4 = By
			.xpath("//a[text()='" + eventName_PE4 + "' and @href=\"/biddingscreen\"]");

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[1]")
	private WebElement btnRunAlgorithmWA;

	@FindBy(xpath = "(//*[text()='RUN ALGORITHM'])[2]")
	private WebElement btnRunAlgorithmWA2;

	@FindBy(xpath = "(//*[text()='Auction Certification'])[1]")
	private WebElement AuctionCertificationButton;

	@FindBy(xpath = "((//span[text()='A47_jointAuctionAutomationE2E2']//ancestor::td)//following-sibling::td[8]//*[@data-icon=\"check-circle\"])[1]")
	private WebElement auctionCerButton;

	@FindBy(xpath = "((//span[text()='A47_jointAuctionAutomationE2E2']//ancestor::td)//following-sibling::td[9]//button[@name=\"approval1Submit\"])[1]")
	private WebElement submitBtncertification;

	@FindBy(xpath = "((//span[text()='A47_jointAuctionAutomationE2E2']//ancestor::td)//following-sibling::td[8]//*[@data-icon=\"check-circle\"])[2]")
	private WebElement QCcertificationbutton;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement btnContineu;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement submitBtncertificationsubmitpopup;

	@FindBy(xpath = "//input[@placeholder=\"Inscrire le nom ou l'ID de l'entité\"]")
	private WebElement entitySearchBox;

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	private WebElement entityCheckbox;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]")
	private WebElement confirmButton;

	@FindBy(xpath = "//*[@name='tiers']")
	private WebElement tiersDropDown;

	@FindBy(xpath = "//*[@name=\"tiersPrices\"]")
	private WebElement texttiersprices;

	@FindBy(xpath = "//*[@name=\"totalAllowancesAvailablePerTier\"]")
	private WebElement texttotalallowanceavail;

	@FindBy(xpath = "//*[@name=\"processingOrder\"]")
	private WebElement processingOrderDropDown;

	@FindBy(xpath = "//*[text()='Add New']")
	private WebElement btnAddNewTier;

	@FindBy(xpath = "(//*[@name='tiers'])[2]")
	private WebElement tiersDropDown2;

	@FindBy(xpath = "(//*[@name='tiers'])[3]")
	private WebElement tiersDropDown3;

	@FindBy(xpath = "(//*[@name=\"tiersPrices\"])[2]")
	private WebElement texttiersprices2;

	@FindBy(xpath = "(//*[@name=\"tiersPrices\"])[3]")
	private WebElement texttiersprices3;

	@FindBy(xpath = "(//*[@name=\"totalAllowancesAvailablePerTier\"])[2]")
	private WebElement texttotalallowanceavail2;

	@FindBy(xpath = "(//*[@name=\"totalAllowancesAvailablePerTier\"])[3]")
	private WebElement texttotalallowanceavail3;

	@FindBy(xpath = "(//*[@name=\"processingOrder\"])[2]")
	private WebElement processingOrderDropDown2;

	@FindBy(xpath = "(//*[@name=\"processingOrder\"])[3]")
	private WebElement processingOrderDropDown3;

	@FindBy(xpath = "//*[@name='allowancesPerBidLot']")
	private WebElement EmissionUnitsPerLot;

	@FindBy(xpath = "//input[@name='setMaximumBidPrice']")
	private WebElement includebids;

	@FindBy(xpath = "//*[text()='UPLOAD']")
	private WebElement UploadEmissionUnits;

	@FindBy(xpath = "(//*[text()='UPLOAD'])[2]")
	private WebElement UploadEmissionUnitsPopup;

	@FindBy(xpath = "//*[contains(text(),'records were uploaded successfully.)']")
	private WebElement FileUploadSuccessMessageR70;

	@FindBy(xpath = "//a[text()='Sale Certification']")
	private WebElement SBMACertificationButton;

	 public static String EventName2;
	 
	public EventCreationUtils() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		action = new Actions(driver);
		js = (JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}

	public void clickbtnAdmin() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration)).click();
			ObjectRepo.getTest().log(Status.PASS, "Click on Adminstration button");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration)).click();
			ObjectRepo.getTest().log(Status.PASS, "Click on Adminstration button");
		}
		System.out.println("Step Click on Adminstration menu");
	}

	public void clickbtnEvent() {
		try {
//			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent)).click();
		}
		System.out.println("Step Click on Event Option");
	}

	public void clickbtnCreateEvent() {
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnCreateEvent);
	}

	public void enterEventId(String eventId) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", textBoxEventID);
		WebElement eventIdInput = wait.until(ExpectedConditions.elementToBeClickable(textBoxEventID));
		eventIdInput.sendKeys(eventId);
		ObjectRepo.getTest().log(Status.PASS, "CITSS Event Id Entered : " + eventId);
	}

	public void selectEventTypeFromDropDown(String Eventtype) {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(eventTypeDropDown));
		Select select = new Select(dropdown);
		select.selectByVisibleText(Eventtype);
	}
	

	public void enterEventName(String eventNametoBeEnter) {
		wait.until(ExpectedConditions.elementToBeClickable(eventName)).sendKeys(eventNametoBeEnter);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventNametoBeEnter);
	}
	
	public void enterEventName2() {
		wait.until(ExpectedConditions.elementToBeClickable(eventName)).sendKeys(EventName2);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + EventName2);
	}

	public void selectJurisdictionForJointAuction(String frenchEventname) {
		wait.until(ExpectedConditions.elementToBeClickable(ParticipatingJurisdictions)).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnCalifornia)).click();
			wait.until(ExpectedConditions.elementToBeClickable(btnQuebec)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnWashinton)).click();
		}
		WebElement frenchNameInput = wait.until(ExpectedConditions.elementToBeClickable(frenchEventName));
		
		action.moveToElement(frenchNameInput).clickAndHold().click().build().perform();
		frenchNameInput.sendKeys(frenchEventname);
	}

	public void currencyAccForjointAuction(String currencyUSD, String currencyCAD) throws InterruptedException {
		try {
			WebElement abc3 = currencyAcc;
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagUSD)).click();
		wait.until(ExpectedConditions.elementToBeClickable(tagCAD)).click();
		WebElement abc4 = textBoxUSD;
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(currencyUSD);
		WebElement abc5 = textBoxCAD;
		action.moveToElement(abc5).click().build().perform();
		action.click(abc5).build().perform();
		textBoxCAD.sendKeys(currencyCAD);
	}

	public void selectVintage(String year, String totalAllowances) {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText(year);
		textBoxVintage1.sendKeys(totalAllowances);
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE4"));
	}

	public void selectVintage_PE1(String year, String totalAllowances, String year2, String totalAllowances2) {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText(year);
		textBoxVintage1.sendKeys(totalAllowances);
		WebElement dropdownvint2 = dropDownVintage2;
		Select select2 = new Select(dropdownvint2);
		select2.selectByVisibleText(year2);
		textBoxVintage2.sendKeys(totalAllowances2);
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

	public void selectProcessingOrder(String firstProcessingOrder) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText(firstProcessingOrder);
		WebElement element = driver.findElement(By.xpath("(//*[@data-icon='trash-alt'])[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}

	public void selectProcessingOrder_PE1(String firstProcessingOrder, String secondProcessingOrder)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText(firstProcessingOrder);
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder2));
		WebElement dropdownProOrder2 = drpdownProCessOrder2;
		Select select2 = new Select(dropdownProOrder2);
		select2.selectByVisibleText(secondProcessingOrder);
//		WebElement element = driver.findElement(By.xpath("(//*[@data-icon='trash-alt'])[2]"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView();", element);
//		Actions action = new Actions(driver);
//		action.moveToElement(element).build().perform();
//		action.click(element).build().perform();
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(biddingStartTime);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.plusHours(0).plusMinutes(0);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.plusHours(0).plusMinutes(1);
		System.out.println("application Close time :" + applicationClose);
		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3).plusSeconds(0);
		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(8).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(6).plusMinutes(40);

		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);

		WebElement bidPeriodOpen = dateBiddingPeroidOpen;
		JavascriptExecutor js = (JavascriptExecutor) driver;
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

	public void selectDatesForReserve() throws InterruptedException {
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(5).plusSeconds(40);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(100);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.plusHours(0).minusMinutes(1);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.plusHours(0).plusMinutes(1);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(3).plusSeconds(0);

		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(8).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(6).plusMinutes(40);

		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);

		WebElement bidPeriodOpen = dateBiddingPeroidOpen;
		JavascriptExecutor js = (JavascriptExecutor) driver;
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
		dateEventAppClose.click();
	}

	public void selectAlgorithm() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(dropdownAlgorithm));
		WebElement dropdownAlg = dropdownAlgorithm;
		Select select1 = new Select(dropdownAlg);
		select1.selectByVisibleText("Algorithm for Individual/Joint Auctions");
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnSave);
		Thread.sleep(3000);
		commonMethods.waitForElementToBeVisible(successmsg);
		String alert = successmsg.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The event has been created successfully.");
	}

	public void californiaJuridictionApprovalForEventCreation(String createdEventName) {
		WebElement element1 = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + createdEventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonMethods commonMethods2 = new CommonMethods();
		commonMethods2.waitForElementToBeClickable(element1);
		js.executeScript("arguments[0].scrollIntoView();", btnfirstApproval);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnfirstApproval);
		commonMethods.waitForElementToBeClickable(btnSubmit1);
		commonMethods.waitForElementToBeVisible(ApprovalMessage);
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		ObjectRepo.getTest().log(Status.PASS, "The approval of the event creation has been proposed by the first sponsor.");
	}

	/**
	 * Handles the Quebec jurisdiction approval process for a newly created event.
	 *
	 * @param createdEventName Name of the event that requires approval
	 * @throws InterruptedException If thread sleep is interrupted
	 */
	public void quebecJuridictionApprovalForEventCreation(String createdEventName) throws InterruptedException {
		clickbtnAdmin();
		clickbtnEvent();
		findEventName(createdEventName);
		WebElement ref1 = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + createdEventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", ref1);
		Thread.sleep(2000);
		CommonMethods commonMethods2 = new CommonMethods();
		commonMethods2.waitForElementToBeClickable(ref1);
		System.out.println("Navigate to particular event ");
		try {
			CommonMethods commonMethods3 = new CommonMethods();
			commonMethods3.waitForElementToBeClickable(btnApprovalQue);
		} catch (Exception e) {
			CommonMethods commonMethods4 = new CommonMethods();
			commonMethods4.waitForElementToBeClickable(btnApprovalQue);
		}
		try {
			CommonMethods commonMethods5 = new CommonMethods();
			commonMethods5.waitForElementToBeClickable(btnSubmit1);
		} catch (Exception e) {
			CommonMethods commonMethods6 = new CommonMethods();
			commonMethods6.waitForElementToBeClickable(btnSubmit2);
		}
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(popupBtn);
		Thread.sleep(10000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void setFXrateForQuebecJurisdiction(String eventName, String qcFxRate) {
		wait.until(ExpectedConditions.visibilityOf(btnAdminstration));
		driver.navigate().refresh();
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
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		CommonMethods commonMethods3 = new CommonMethods();
		commonMethods3.waitForElementToBeClickable(btnAdminstration);
		try {
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice)).click();
		}
//		CommonMethods.waitForElementToBeClickable(btnReservePrice);
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

	/**
	 * Executes the Get Qualified Bidder API call through the UI
	 * 
	 * @throws InterruptedException if the execution is interrupted
	 */
	public void executeAPIGetQualifiedBidder() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(btnSettings);
			wait.until(ExpectedConditions.elementToBeClickable(btnExecuteAPI));
			btnExecuteAPI.click();
			js.executeScript("arguments[0].scrollIntoView();", APIGetQualifiedBidder);
			commonMethods.waitForElementToBeClickable(APIGetQualifiedBidder);
			Thread.sleep(4000);
			commonMethods.waitForElementToBeClickable(APIexecutionPopupbtn);
			Thread.sleep(3000);
			String successMessage = "Get Qualified bidder API executed successfully";
			System.out.println(successMessage);
			ObjectRepo.getTest().log(Status.PASS, successMessage);
		} catch (Exception e) {
			String errorMessage = "Failed to execute Get Qualified bidder API: " + e.getMessage();
			System.err.println(errorMessage);
			ObjectRepo.getTest().log(Status.FAIL, errorMessage);
			throw new RuntimeException(errorMessage, e);
		}
	}

	public void executeAPIGetBidLimitations() {
		try {
			WebDriverWait apiWait = new WebDriverWait(driver, Duration.ofSeconds(120));
			apiWait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(APIGetBidLimitations)));
			WebElement bidLimitations = APIGetBidLimitations;
			js.executeScript("arguments[0].scrollIntoView();", bidLimitations);
			bidLimitations.click();
			wait.until(ExpectedConditions.elementToBeClickable(APIexecutionPopupbtn)).click();
			Thread.sleep(10000);
			System.out.println("Get Bid Limitation API Executed successfully");
			ObjectRepo.getTest().log(Status.PASS, "Get Bid Limitation API Executed successfully");
		} catch (Throwable e) {
			throw new RuntimeException("Failed to execute Get Bid Limitations API", e);
		}
	}

	public void SelectEntity(String entityId) {
		try {
			wait.until(ExpectedConditions.visibilityOf(entityNameInput));
			entityNameInput.sendKeys(entityId);
			js.executeScript("arguments[0].click();", entityCheckbox);
			js.executeScript("arguments[0].click();", confirmButton);
			ObjectRepo.getTest().log(Status.PASS, entityId + " :Entity Account Selected By Account Representative");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(entityNameInput));
			entityNameInput.sendKeys(entityId);
			js.executeScript("arguments[0].click();", entityCheckbox);
			js.executeScript("arguments[0].click();", confirmButton);
			ObjectRepo.getTest().log(Status.PASS, entityId + " :Entity Account Selected By Account Representative");
		}
		System.out.println("Step Entity selected "+entityId);
	}

	public void BiddingByARforTestData(String bidPrice, String bidLot, String vintageType) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		} catch (Exception e) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnAddbid);
		textBidPrice.sendKeys(bidPrice);
		textBidLots.sendKeys(bidLot);
		dropdownvintage.sendKeys(vintageType);
		commonMethods.waitForElementToBeClickable(btnSubmit);
		commonMethods.waitForElementToBeClickable(btnConfirm);
		commonMethods.waitForElementToBeVisible(alertMsg);
		String CAMassage2 = alertMsg.getText();
		System.out.println(CAMassage2);
		commonMethods.waitForElementToBeClickable(alertPopup);
	}

	public void BiddingByARforTestData2_PE3() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData3_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData4_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData5_PE3() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData6_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData7_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData8_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData9_PE3() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price9_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot9_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData10_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot10_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData11_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot11_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData12_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot12_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData13_PE3() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot13_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData14_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot14_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData15_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot15_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData16_PE3() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16_Current_PE3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot16_Current_PE3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();

		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void SelectQuebecEntity(String entityId) {
		wait.until(ExpectedConditions.elementToBeClickable(entitySearchBox));
		entitySearchBox.sendKeys(entityId);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		entityCheckbox.click();
CommonMethods commonMethods = new CommonMethods();
		//		wait.until(ExpectedConditions.elementToBeClickable(entityCheckbox));
//		CommonMethods.waitForElementToBeClickable(entityCheckbox);
		commonMethods.waitForElementToBeClickable(confirmButton);
	}

	public void selectEventForBidding(String eventname) {
		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(DropDownEvents)).click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(DropDownEvents)).click();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement element = driver.findElement(By.xpath("(//a[text()='" + eventname + "'])[1]"));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Thread.sleep(3000);
			action.click(element).build().perform();
		} catch (Exception fallbackException) {
			wait.until(ExpectedConditions.elementToBeClickable(Auction));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Auction);
		}
		System.out.println("step Event Select "+ eventname);
	}

	public void selectEventForBiddingJointAuction(String eventname) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(DropDownEvents));
		DropDownEvents.click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("(//a[text()='" + eventname + "'])[1]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		Thread.sleep(3000);
		action.click(element).build().perform();
	}

	public void BiddingByARforTestData17_PE3() throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);

		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
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
		Thread.sleep(3000);
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
		Thread.sleep(2000);
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
		Thread.sleep(3000);
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
		Thread.sleep(2000);
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
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void BiddingByARforTestData20_PE4() throws Exception {

		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		Thread.sleep(2000);
		textBidPrice.sendKeys(prop.getProperty("Bid_Price20_Current_PE4"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot20_Current_PE4"));
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
		Thread.sleep(3000);
		alertPopup.click();
	}

	public void getCurrentEvent(String eventname) throws InterruptedException {
		WebElement ref1 = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventname + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", ref1);
		Thread.sleep(3000);
		action = new Actions(driver);
		action.moveToElement(ref1).build().perform();
		action.click(ref1).build().perform();
		System.out.println("Navigated to Particular Event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");
	}

	public void closeBidding() {

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
		LocalTime biddingEnd = time.plusMinutes(2).plusSeconds(0);
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
		ObjectRepo.getTest().log(Status.PASS, "Bidding End Time changed to : " + formattedCurrentDate + biddingEndFormatted);
		driver.findElement(By.xpath("//*[text()='SAVE']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().refresh();
	}

	public void runAlgorithm2() throws InterruptedException {
		Thread.sleep(30000);
		try {
			try {
				Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA)).click();
			} catch (Exception e) {
				Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA)).click();
			}
		} catch (Exception e) {
			try {
				Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA2)).click();
			} catch (Exception e1) {
				Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA2)).click();
			}
		}
		Thread.sleep(15000);
	}

	public void AuctionCertification(String eventName) throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(AuctionCertificationButton)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(AuctionCertificationButton)).click();
		}
		Thread.sleep(5000);
		WebElement element111 = driver.findElement(By.xpath("((//span[text()='" + eventName
				+ "']//ancestor::td)//following-sibling::td[8]//*[@data-icon='check-circle'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(element111));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView();", element111);
		try {
			element111.click();
		} catch (Exception e) {
			element111.click();
		}
		Thread.sleep(2000);
		WebElement btnSubmit = driver.findElement(By.xpath("((//span[text()='" + eventName
				+ "']//ancestor::td)//following-sibling::td[9]//button[@name=\"approval1Submit\"])[1]"));
		try {
			btnSubmit.click();
		} catch (Exception e) {
			btnSubmit.click();
		}
		Thread.sleep(7000);
	}

	public void AuctionCertificationConfirmSubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(submitBtncertificationsubmitpopup));
		submitBtncertificationsubmitpopup.click();
		Thread.sleep(5000);
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Auction Certification by Quebec Sponsor");
	}

	public void runAlgorithm() throws InterruptedException {
		Thread.sleep(7000);
		try {
			try {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA)).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnContineu)).click();
				Thread.sleep(15000);
			} catch (Exception e) {
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA)).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnContineu)).click();
				Thread.sleep(15000);
			}
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA2)).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnContineu)).click();
				Thread.sleep(15000);
			} catch (InterruptedException e1) {
				wait.until(ExpectedConditions.elementToBeClickable(btnRunAlgorithmWA2)).click();
				wait.until(ExpectedConditions.elementToBeClickable(btnContineu)).click();
				Thread.sleep(15000);
			}
		}
	}

	public void createEventForJointAuction_A47() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE4"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE4);
		selectJurisdictionForJointAuction(eventName_PE4);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE4"), prop.getProperty("FloorPriceCAD_PE4"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE4"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A48() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE5"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE5);
		selectJurisdictionForJointAuction(eventName_PE5);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE5"), prop.getProperty("FloorPriceCAD_PE5"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE5"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A49() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE6"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE6);
		selectJurisdictionForJointAuction(eventName_PE6);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE6"), prop.getProperty("FloorPriceCAD_PE6"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE6"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A46() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE3"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE3);
		selectJurisdictionForJointAuction(eventName_PE3);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE3"), prop.getProperty("FloorPriceCAD_PE3"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE3"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A50() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE7"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE7);
		selectJurisdictionForJointAuction(eventName_PE7);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE7"), prop.getProperty("FloorPriceCAD_PE7"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE7"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A51() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE8"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE8);
		selectJurisdictionForJointAuction(eventName_PE8);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE8"), prop.getProperty("FloorPriceCAD_PE8"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE8"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A56() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE9"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE9);
		selectJurisdictionForJointAuction(eventName_PE9);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE9"), prop.getProperty("FloorPriceCAD_PE9"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE9"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void selectVintage_PE1() {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText("2024");
		textBoxVintage1.sendKeys(prop.getProperty("TotalAllowances_Current_PE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PE1"));
		WebElement dropdownvint2 = dropDownVintage2;
		Select select1 = new Select(dropdownvint2);
		select1.selectByVisibleText("2028");
		textBoxVintage2.sendKeys(prop.getProperty("TotalAllowances_Advance_PE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Advance Event Vintage Entered : " + prop.getProperty("TotalAllowances_Advance_PE1"));
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

	public void createEventForJointAuction_A42() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE1"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE1);
		selectJurisdictionForJointAuction(eventName_PE1);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE1"), prop.getProperty("FloorPriceCAD_PE1"));
		selectVintage_PE1("2024", prop.getProperty("TotalAllowances_Current_PE1"), "2028",
				prop.getProperty("TotalAllowances_Advance_PE1"));
		selectVintageType_PE1();
		selectProcessingOrder_PE1("1", "2");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A57() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE10"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE10);
		selectJurisdictionForJointAuction(eventName_PE10);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE10"), prop.getProperty("FloorPriceCAD_PE10"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE10"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A297() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE11"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE11);
		selectJurisdictionForJointAuction(eventName_PE11);
		currencyAccForjointAuction(prop.getProperty("AnnualAuctionReservePriceUSD"),
				prop.getProperty("AnnualAuctionReservePriceCAD"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE11"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void SelectEntity_Updated(String entityName) {
		WebElement element = driver.findElement(By.xpath("//*[text()='" + entityName + "']"));
		try {
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(element);
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
					.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + entityName + "']")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
					.click();
		}
		System.out.println("Step Entity selected "+entityName);
	}

	public void enterEventNameFr_PEQc1(String eventNameFrench) {
		eventNameFr.sendKeys(eventNameFrench);
		ObjectRepo.getTest().log(Status.PASS, "Event Name Entered : " + eventNameFrench);
	}

	public void currencyAccForQcSBMAEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAccCa));
		currencyAccCa.click();
		try {
			WebElement abc3 = currencyAccCa;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAccCa.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagCAD));
		tagCAD.click();
	}

	public void selectCategoryA_R70(String PriceOffCatA, String TotalAllowanceA, String text) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));

		WebElement element = tiersDropDown;
		Select s = new Select(element);
		s.selectByVisibleText("Category A");
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices));
		texttiersprices.click();
		texttiersprices.sendKeys(PriceOffCatA);
		texttotalallowanceavail.sendKeys(TotalAllowanceA);

		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown));
		processingOrderDropDown.click();
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(text);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewTier));
		btnAddNewTier.click();
	}

	public void selectCategoryB_R70(String PriceOffCatB, String TotalAllowanceB, String text) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown2));

		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		s.selectByVisibleText("Category B");
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices2));
		texttiersprices2.click();
		texttiersprices2.sendKeys(PriceOffCatB);
		texttotalallowanceavail2.sendKeys(TotalAllowanceB);

		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown2));
		processingOrderDropDown2.click();
		WebElement element1 = processingOrderDropDown2;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(text);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewTier));
		btnAddNewTier.click();

	}

	public void selectCategoryC_R70(String PriceOffCatC, String TotalAllowanceC, String text) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown3));

		WebElement element = tiersDropDown3;
		Select s = new Select(element);
		s.selectByVisibleText("Category C");
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices3));
		texttiersprices3.click();
		texttiersprices3.sendKeys(PriceOffCatC);
		texttotalallowanceavail3.sendKeys(TotalAllowanceC);

		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown3));
		processingOrderDropDown3.click();
		WebElement element1 = processingOrderDropDown3;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(text);

	}

	public void selectEmissionUnitsPerLot_PEQc(String lotSize) throws Exception {
		WebElement elementA = EmissionUnitsPerLot;
		Actions action = new Actions(driver);
		action.moveToElement(elementA).build().perform();
		action.doubleClick(elementA).build().perform();
		Thread.sleep(2000);
		EmissionUnitsPerLot.sendKeys(lotSize);
		Thread.sleep(2000);
	}

	public void selectCombinedEvaluationOfTiers_PEQc() throws Exception {
		WebElement element = includebids;
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		act.click(element).build().perform();

		WebElement savebtn = btnSave;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", savebtn);
		Thread.sleep(1000);
		savebtn.click();
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeVisible(successmsg);
		String alert = successmsg.getText();
		System.out.println(alert);
		// Assert.assertEquals(alert, "The event has been created successfully.");
		driver.navigate().refresh();
	}

	public void createEventForSBMA_R70() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA2")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc1); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc1); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA2"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA2"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA2"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA2"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA2"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA2"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void createEventForJointAuction_A298() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE12"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE12);
		selectJurisdictionForJointAuction(eventName_PE12);
		currencyAccForjointAuction(prop.getProperty("AnnualAuctionReservePriceUSD"),
				prop.getProperty("AnnualAuctionReservePriceCAD"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE12"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A45() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PE2"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PE2);
		selectJurisdictionForJointAuction(eventName_PE2);
		currencyAccForjointAuction(prop.getProperty("FloorPriceUSD_PE2"), prop.getProperty("FloorPriceCAD_PE2"));
		selectVintage("2024", prop.getProperty("TotalAllowances_Current_PE2"));
		selectVintageType();
		selectProcessingOrder("1");
		selectDates(6);
		selectAlgorithm();
	}

	public void createEventForSBMA_R69() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA1")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(prop.getProperty("Event_Name_SBMA1")); // Event Name English
		enterEventNameFr_PEQc1(prop.getProperty("Event_Name_SBMA1")); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA1"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA1"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA1"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA1"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA1"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA1"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void createEventForSBMA_R71() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA3")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc2); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc2); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA3"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA3"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA3"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA3"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA3"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA3"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void uploadEmissionUnitsFile(String filePath1) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(UploadEmissionUnits));
		UploadEmissionUnits.click();
		// Copy file path to clipboard
		String filePath = filePath1;
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		try {
			// Use Robot to paste and press Enter
			Robot robot = new Robot();
			Thread.sleep(2000); // Wait for dialog to appear

			// Paste (CTRL + V)
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// Press Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(UploadEmissionUnitsPopup));
		UploadEmissionUnitsPopup.click();
		Thread.sleep(8000);
//		wait.until(ExpectedConditions.visibilityOfAllElements(FileUploadSuccessMessageR70));
	}

	public void selectJurisdictionWashingtonAuctionEvent() throws InterruptedException {
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(ParticipatingJurisdictionsWa);
		commonMethods.waitForElementToBeClickable(btnWashinton);
		Thread.sleep(2000);
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("(//div[@id='demo-customized-select'])[2]"));
		new Actions(driver).moveToElement(element).click().build().perform();
	}

	public void currencyAccForWashingtonAuctionEvent(String floorPrice) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAcc));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", btnAddNewTier);
		Thread.sleep(2000);
		currencyAcc.click();
		try {
			WebElement abc3 = currencyAcc;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAcc.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagUSD));
		tagUSD.click();
		wait.until(ExpectedConditions.elementToBeClickable(textBoxUSD));
		WebElement abc4 = textBoxUSD;
		Actions action = new Actions(driver);
		action.moveToElement(abc4).click().build().perform();
		action.click(abc4).build().perform();
		textBoxUSD.sendKeys(floorPrice);
	}

	public void selectVintages(String vintageYear1, String vintagetype1, String vintageYear2, String vintagetype2) {
		WebElement dropdownvint = dropDownVintage1;
		Select select = new Select(dropdownvint);
		select.selectByVisibleText(vintageYear1);
		textBoxVintage1.sendKeys(vintagetype1);
		ObjectRepo.getTest().log(Status.PASS,
				"Current Event Vintage Entered : " + prop.getProperty("TotalAllowances_Current_PEWa2"));
		WebElement dropdownvint2 = dropDownVintage2;
		Select select1 = new Select(dropdownvint2);
		select1.selectByVisibleText(vintageYear2);
		textBoxVintage2.sendKeys(vintagetype2);
		ObjectRepo.getTest().log(Status.PASS,
				"Advance Event Vintage Entered : " + prop.getProperty("TotalAllowances_Advance_PEWa2"));
	}

	public void selectVintageTypes(String vintagetype1, String vintagetype2) {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownVintageType1));
		WebElement dropdownvintType = drpdownVintageType1;
		Select select = new Select(dropdownvintType);
		select.selectByVisibleText(vintagetype1);
		wait.until(ExpectedConditions.elementToBeClickable(drpdownVintageType2));
		WebElement dropdownvintType1 = drpdownVintageType2;
		Select select1 = new Select(dropdownvintType1);
		select1.selectByVisibleText(vintagetype2);
	}

	public void selectProcessingOrders() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder1));
		WebElement dropdownProOrder = drpdownProCessOrder1;
		Select select = new Select(dropdownProOrder);
		select.selectByVisibleText("1");
		wait.until(ExpectedConditions.elementToBeClickable(drpdownProCessOrder2));
		WebElement dropdownProOrder1 = drpdownProCessOrder2;
		Select select1 = new Select(dropdownProOrder1);
		select1.selectByVisibleText("2");
	}

	// prop.getProperty("AnnualAuctionFloorPriceA278")

	public void createEventForJointAuction_A278() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PEWa2"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PEWa2);
		selectJurisdictionWashingtonAuctionEvent();
		currencyAccForWashingtonAuctionEvent(prop.getProperty("AnnualAuctionFloorPriceA278"));
		selectVintages("2024", prop.getProperty("TotalAllowances_Current_PEWa2"), "2025",
				prop.getProperty("TotalAllowances_Advance_PEWa2"));
		selectVintageTypes("Current", "Advance");
		selectProcessingOrders();
		selectDates(4);
		selectAlgorithm();
	}

	public void createEventForJointAuction_A218() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_PEWa"));
		selectEventTypeFromDropDown("Auction");
		enterEventName(eventName_PEWa);
		selectJurisdictionWashingtonAuctionEvent();
		currencyAccForWashingtonAuctionEvent(prop.getProperty("AnnualAuctionFloorPrice"));
		selectVintages("2024", prop.getProperty("TotalAllowances_Current_PEWa"), "2025",
				prop.getProperty("TotalAllowances_Advance_PEWa"));
		selectVintageTypes("Current", "Advance");
		selectProcessingOrders();
		selectDates(4);
		selectAlgorithm();
	}

	public void selectTiers1_For_APCR(String Tier1, String PriceOffTierA, String TotalAllowance1,
			String ProcessingOrder) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown;
		Select s = new Select(element);
		s.selectByVisibleText(Tier1);
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices));
		texttiersprices.click();
		texttiersprices.sendKeys(PriceOffTierA);
		texttotalallowanceavail.sendKeys(TotalAllowance1);
		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown));
		processingOrderDropDown.click();
		WebElement element1 = processingOrderDropDown;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(ProcessingOrder);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewTier));
		btnAddNewTier.click();
	}

	public void selectTiers2_For_APCR(String Tier2, String PriceOffTierB, String TotalAllowance2,
			String ProcessingOrder) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown));
		WebElement element = tiersDropDown2;
		Select s = new Select(element);
		s.selectByVisibleText(Tier2);
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices2));
		texttiersprices2.click();
		texttiersprices2.sendKeys(PriceOffTierB);
		texttotalallowanceavail2.sendKeys(TotalAllowance2);
		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown2));
		processingOrderDropDown2.click();
		WebElement element1 = processingOrderDropDown2;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(ProcessingOrder);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewTier));
		btnAddNewTier.click();
	}

	public void selectTiers3_For_APCR(String Tier3, String PriceOffTierC, String TotalAllowance3,
			String ProcessingOrder) {
		wait.until(ExpectedConditions.elementToBeClickable(tiersDropDown2));
		WebElement element = tiersDropDown3;
		Select s = new Select(element);
		s.selectByVisibleText(Tier3);
		wait.until(ExpectedConditions.elementToBeClickable(texttiersprices3));
		texttiersprices3.click();
		texttiersprices3.sendKeys(PriceOffTierC);
		texttotalallowanceavail3.sendKeys(TotalAllowance3);
		wait.until(ExpectedConditions.elementToBeClickable(processingOrderDropDown3));
		processingOrderDropDown3.click();
		WebElement element1 = processingOrderDropDown3;
		Select s1 = new Select(element1);
		// Thread.sleep(2000);
		s1.selectByVisibleText(ProcessingOrder);
	}

	public void selectDates_PECa() throws InterruptedException {
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
		LocalTime BiddingStart = time.plusHours(0).plusMinutes(5);
		System.out.println("Bidding start time :" + BiddingStart);
		LocalTime BiddingEnd = time.plusHours(2).plusMinutes(15);
		System.out.println("Bidding end time :" + BiddingEnd);
		LocalTime applicationOpen = time.plusHours(0).plusMinutes(0);
		System.out.println("application open time :" + applicationOpen);
		LocalTime applicationClose = time.plusHours(0).plusMinutes(3);
		System.out.println("application Close time :" + applicationClose);

		LocalTime bidGuaranteeSubmissionDeadline = time.plusHours(0).plusMinutes(4);
		;
		LocalTime resultsAvailable = time.plusHours(4).plusMinutes(40);
		LocalTime eventClosure = time.plusHours(8).plusMinutes(40);
		LocalTime financialSettlement = time.plusHours(6).plusMinutes(40);

		System.out.println("Bid Guarantee Submission Deadline: " + bidGuaranteeSubmissionDeadline);
		System.out.println("Results Available: " + resultsAvailable);
		System.out.println("Event Closure: " + eventClosure);
		System.out.println("Financial Settlement: " + financialSettlement);

		WebElement bidPeriodOpen = dateBiddingPeroidOpen;
		JavascriptExecutor js = (JavascriptExecutor) driver;
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

	public void selectEmissionUnitsPerLot_PEQc() throws Exception {
		WebElement elementA = EmissionUnitsPerLot;
		Actions action = new Actions(driver);
		action.moveToElement(elementA).build().perform();
		action.doubleClick(elementA).build().perform();
		Thread.sleep(2000);
		EmissionUnitsPerLot.sendKeys("1");
		Thread.sleep(2000);
	}

	public void currencyAccForQC_SBMA_Event() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAccCa));
		currencyAccCa.click();
		try {
			WebElement abc3 = currencyAccCa;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAccCa.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagCAD));
		tagCAD.click();
	}
	public void currencyAccForCaReserveSaleEvent() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(currencyAccCa));
		currencyAccCa.click();
		try {
			WebElement abc3 = currencyAccCa;
			Actions action = new Actions(driver);
			action.moveToElement(abc3).build().perform();
			action.click(abc3).build().perform();
		} catch (Exception e) {
			currencyAccCa.click();
		}
		wait.until(ExpectedConditions.elementToBeClickable(tagUSD));
		tagUSD.click();
	}

	public String randomEventName() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder();
	    java.util.Random random = new java.util.Random();
 
	    for (int i = 0; i < 6; i++) {
	        sb.append(characters.charAt(random.nextInt(characters.length())));
	    }
	    EventName2 = "R319_APCR" + "_Event_" + sb.toString();
	    System.out.println("EventName: "+EventName2);
	    return EventName2;
	}
	
	public void createEventForWaReserveSaleEvent_REWA1(String eventId,String EventName ) throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(eventId); // Event Id
		selectEventTypeFromDropDown("Allowance Price Containment Reserve");
		randomEventName();
		enterEventName(EventName);
		currencyAccForCaReserveSaleEvent();
		selectTiers1_For_APCR("Tier One","59.59","3,000", "1");
		selectTiers2_For_APCR("Tier Two","45.84","3,000", "2");
		selectTiers3_For_APCR("Tier Three","59.59","3,000", "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc();
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void selectRollDownProcedure_PECa() throws Exception {
		try {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		} catch (Exception e) {
			rollDownbids.click();
			ObjectRepo.getTest().log(Status.PASS, "step 11 : Selected Roll down bids option");
		}
		Thread.sleep(2000);

		WebElement savebtn = btnSave;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", savebtn);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeVisible(successmsg);
		String alert = successmsg.getText();
		System.out.println(alert);
		// Assert.assertEquals(alert, "The event has been created successfully.");
		driver.navigate().refresh();
	}
	public void selectRollDownProcedure_SBMAQC() throws Exception {
	
		Thread.sleep(2000);

		WebElement savebtn = btnSave;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", savebtn);
		wait.until(ExpectedConditions.elementToBeClickable(btnSave));
		btnSave.click();
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeVisible(successmsg);
		String alert = successmsg.getText();
		System.out.println(alert);
		// Assert.assertEquals(alert, "The event has been created successfully.");
		driver.navigate().refresh();
	}
	public void createEventForWaReserveSaleEvent_RCA317() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_RE1Ca")); // Event Id
		selectEventTypeFromDropDown("Reserve Sale");
		enterEventName(eventName_PECa);
		currencyAccForCaReserveSaleEvent();
		selectTiers1_For_APCR("Tier One", prop.getProperty("PriceOffered_Tier_one_RE1Ca"),
				prop.getProperty("TotalAllowances_Tier_one_RE1Ca"), "1");
		selectTiers2_For_APCR("Tier Two", prop.getProperty("PriceOffered_Tier_two_RE1Ca"),
				prop.getProperty("TotalAllowances_Tier_two_RE1Ca"), "2");
		try {
			deleteTier.click();
		} catch (Exception e) {
			deleteTier.click();
		}
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc();
		selectRollDownProcedure_PECa();
	}

	public void washingtonJuridictionApprovalForEventCreation(String createdEventName) throws InterruptedException {
		clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		CommonMethods commonMethods2 = new CommonMethods();
		commonMethods2.waitForElementToBeClickable(btnEvent);
		WebElement ref1 = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + createdEventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", ref1);
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(ref1);
		System.out.println("Navigate to particular event ");
		try {
			commonMethods.waitForElementToBeClickable(btnApprovalQue);
		} catch (Exception e) {
			commonMethods.waitForElementToBeClickable(btnApprovalQue);
		}
		try {
			commonMethods.waitForElementToBeClickable(btnSubmit1);
		} catch (Exception e) {
			commonMethods.waitForElementToBeClickable(btnSubmit2);
		}
		commonMethods.waitForElementToBeClickable(popupBtn);
		Thread.sleep(3000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Washington jurisdiction");
		driver.navigate().refresh();
	}

	public void BiddingByARforTestDataForSBMA(String bidLot, String Category) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(biddingWindowOpenTimer));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		} catch (Exception e) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			js.executeScript("arguments[0].scrollIntoView();", btnAddbid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			e1.printStackTrace();
		}
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnAddbid);
		textBidLot.sendKeys(bidLot);
		WebElement element0 = selectCatagory;
		Select s = new Select(element0);
		s.selectByVisibleText(Category);
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Message;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg2));
			Message = alertMsg2.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Message = alertMsg.getText();
		}
		System.out.println(Message);
		commonMethods.waitForElementToBeClickable(alertPopup);
	}

	public void createEventForSBMA_R72() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA4")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc3); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc3); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA4"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA4"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA4"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA4"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA4"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA4"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void createEventForSBMA_R76() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA7")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc6); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc6); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA7"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA7"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA7"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA7"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA7"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA7"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void createEventForSBMA_R73() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA5")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc4); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc4); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA5"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA5"), "1"); 
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA5"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA5"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA5"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA5"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}

	public void createEventForWaReserveSaleEvent_REWA2() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_REWA2")); // Event Id
		selectEventTypeFromDropDown("Allowance Price Containment Reserve");
		enterEventName(eventName_REWA2);
		currencyAccForCaReserveSaleEvent();
		selectTiers1_For_APCR("Tier One", prop.getProperty("PriceOffered_Tier_one_REWA2"),
				prop.getProperty("TotalAllowances_Tier_one_REWA2"), "1");
		selectTiers2_For_APCR("Tier Two", prop.getProperty("PriceOffered_Tier_two_REWA2"),
				prop.getProperty("TotalAllowances_Tier_two_REWA2"), "2");
		try {
			deleteTier.click();
		} catch (Exception e) {
			deleteTier.click();
		}
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc();
		selectRollDownProcedure_PECa();
	}

	public void EventCertification(String CertificationButton, String eventName) throws InterruptedException {
		WebElement element01 = driver.findElement(By.xpath("//*[text()='" + CertificationButton + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(element01));
		try {
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(element01);
		} catch (Exception e) {
			element01.click();
		}
		Thread.sleep(5000);
		driver.navigate().refresh();
		try {
			WebElement element111 = driver.findElement(By.xpath("(//*[text()='" + eventName
					+ "']//ancestor::tr)//td[8]//div//button[@aria-label=\"firstAuctionCertified\"]"));
			wait.until(ExpectedConditions.elementToBeClickable(element111));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView();", element111);
			element111.click();
		} catch (Exception e) {
			WebElement element112 = driver.findElement(By.xpath("(//*[text()='" + eventName
					+ "']//ancestor::tr)//td[8]//div//button[@aria-label=\"secondAuctionCertified\"]"));
			wait.until(ExpectedConditions.elementToBeClickable(element112));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].scrollIntoView();", element112);
			element112.click();
		}
		Thread.sleep(2000);
		try {
			WebElement btnSubmit1 = driver.findElement(By.xpath("((//*[text()='" + eventName
					+ "']//ancestor::tr)//td[9]//div//button[@name=\"approval1Submit\"])[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
			btnSubmit1.click();
		} catch (Exception e) {
			WebElement btnSubmit2 = driver.findElement(By.xpath("((//*[text()='" + eventName
					+ "']//ancestor::tr)//td[9]//div//button[@name=\"approval1Submit\"])[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(btnSubmit2));
			btnSubmit2.click();
		}
		Thread.sleep(7000);
	}

	public void createEventForSBMA_R74() throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(prop.getProperty("CITSS_Event_ID_SBMA6")); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName_PEQc5); // Event Name English
		enterEventNameFr_PEQc1(eventName_PEQc5); // Event Name French
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70(prop.getProperty("PriceOffered_CategoryA_SBMA6"),
				prop.getProperty("TotalAllowances_CategoryA_SBMA6"), "1");
		selectCategoryB_R70(prop.getProperty("PriceOffered_CategoryB_SBMA6"),
				prop.getProperty("TotalAllowances_CategoryB_SBMA6"), "2");
		selectCategoryC_R70(prop.getProperty("PriceOffered_CategoryC_SBMA6"),
				prop.getProperty("TotalAllowances_CategoryC_SBMA6"), "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc("1");
		selectCombinedEvaluationOfTiers_PEQc();
	}
	
	public void findEventName(String EventName) {
		wait.until(ExpectedConditions.elementToBeClickable(rowsPerPage));
		rowsPerPage.click();
		wait.until(ExpectedConditions.elementToBeClickable(selectRowsNum));
		selectRowsNum.click();
		for (int i = 0; i < 5; i++) {
			driver.findElement(By.xpath("//*[text()='" + EventName + "']"));
		}
	}
	
	public void createEventForCAReserveSaleEvent(String eventId , String eventName) throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(eventId); // Event Id
		selectEventTypeFromDropDown("Reserve Sale");
		enterEventName(eventName);
		currencyAccForCaReserveSaleEvent();
		selectTiers1_For_APCR("Tier One", "45.84", "2000", "1");
		selectTiers2_For_APCR("Tier Two", "59.59", "5000", "2");
		try {
			deleteTier.click();
		} catch (Exception e) {
			deleteTier.click();
		}
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc();
		selectRollDownProcedure_PECa();
	}
	public void createEventForSBMAEvent(String eventId , String eventName) throws Exception {
		clickbtnAdmin();
		clickbtnEvent();
		clickbtnCreateEvent();
		enterEventId(eventId); // Event Id
		selectEventTypeFromDropDown("Sale by Mutual Agreement");
		enterEventName(eventName);
		enterEventNameFr_PEQc1(eventName);
		currencyAccForQC_SBMA_Event();
		currencyAccForQcSBMAEvent();
		selectCategoryA_R70("55",
				"1000000", "1");
		selectCategoryB_R70("60",
				"1000000", "2");
		selectCategoryC_R70("65",
				"1000000", "3");
		selectDatesForReserve();
		Thread.sleep(2000);
		selectEmissionUnitsPerLot_PEQc();
		selectRollDownProcedure_SBMAQC();
	}
	
}
