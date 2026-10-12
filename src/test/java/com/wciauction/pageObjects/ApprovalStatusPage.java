package com.wciauction.pageObjects;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.events.CreateEventPage;

import excelReadersForReports.ExcelDataProviderForActivityAuditReportForSP;
import excelReadersForReports.ExcelDataProviderForClientBidsAuditReportAR;
import excelReadersForReports.ExcelDataProviderForClientBidsAuditReportARInFrench;
import excelReadersForReports.ExcelDataProviderForClientBidsReportAR;
import excelReadersForReports.ExcelDataProviderForClientBidsReportARInFrench;
import excelReadersForReports.ExcelDataProviderForFinancialSettlementReportFSA;
import excelReadersForReports.ExcelDataProviderForFinancialSettlementReportFSAInFrench;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ApprovalStatusPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private Actions action;
	private String path;
//	@FindBy(xpath = "//*[@id='event-list']/table/tbody/tr[1]/td[9]/button")
//	private WebElement eventDetailsBtn1;
	private By eventDetailsBtn1 = By
			.xpath("//*[@id='event-list']/table/tbody/tr[1]/td[9]/button");

	private By eventDetailsBtnCA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_NameForCA") + "'])[1]");
	private By eventDetailsBtnCA2 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName") + "'])[1]");
	private By eventDetailsBtnR70 = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_Name_RE1") + "'])[1]");
	private By eventDetailsBtnRS_WA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName_WA") + "'])[1]");
	private By eventDetailsBtnQC = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_NameForQC") + "'])[1]");
	private By eventDetailsBtnWA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameWS") + "'])[1]");
	private By eventDetailsBtnCA_Single = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameCA") + "'])[1]");
	private By eventDetailsBtnQC_Single = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameQC") + "'])[1]");
	private By eventDetailsBtnQC_SBMA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("Event_NameForQC1") + "'])[1]");
	private By textBoxFxCal = By.xpath(
			"//*[text()='" + CreateEventPage.newEventName + "']//following::input[@aria-label='Enter FX Rate for CA'][1]");
	private By textBoxFxQc = By.xpath(
			"//*[text()='" + CreateEventPage.newEventName + "']//following::input[@aria-label='Enter FX Rate for QC'][1]");
	private By btnsubmitCal = By
			.xpath("(//*[text()='" + CreateEventPage.newEventName + "']/ancestor::tr/following-sibling::tr/td[4])[1]");
	private By btnsubmitqc = By.xpath("(//*[text()='" + CreateEventPage.newEventName + "'])/following::td[8]");
	
	@FindBy(xpath = "//*[@data-icon='angle-down']/parent::*[contains(text(),'Welcome,')]")
	private WebElement btnProfile;

	@FindBy(xpath = "(//*[@data-icon='angle-down'])[2]")
	private WebElement btnProfileForQc;

	@FindBy(xpath = "//*[@href='/reservepriceadministrations']")
	private WebElement btnReservePrice2;

	@FindBy(xpath = "//button[@title=\"SUBMIT\"]")
	private WebElement btnAlertSubmit;

	@FindBy(xpath = "//button[@title='Administration' and @type='button']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//*[@id='event-list']/table/tbody/tr[1]/td[9]/button")
	private WebElement eventDetailsBtn;
	
	private By eventDetailsBtnWA_RS = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("ReserveSaleEventName_WA") + "'])[1]");

	private By eventDetailsBtnWA_SA = By
			.xpath("(//*[@title='Go to details of Event " + prop.getProperty("eventNameWS") + "'])[1]");
	
	@FindBy(xpath = "//a[text()='Configuration']")
	private WebElement btnConfiguration;

	@FindBy(xpath = "//input[@id='Session_Timeout']")
	private WebElement inputSessionOutTime;

	@FindBy(xpath = "//span[text()='Settings']")
	private WebElement btnSettings;

	@FindBy(xpath = "//span[text()='Rechercher un rapport']")
	private WebElement btnSearchReportFR;

	@FindBy(xpath = "//*[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//h1[text()='Reports']")
	private WebElement headingReportEN;

	@FindBy(xpath = "//h1[text()='Rapports']")
	private WebElement headingReportFR;

	@FindBy(xpath = "//input[@placeholder='Tous']")
	private WebElement eventNameDropDownFR;

	@FindBy(xpath = "//span[text()='RECHERCHER']")
	private WebElement btnSearchFR;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement englishLngText;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement frenchLngText;

	@FindBy(xpath = "//td[text()='Facture']")
	private WebElement financialStatementReportFR;

	@FindBy(xpath = "//td[text()='Rapport de vérification des offres client']")
	private WebElement clientBidsAuditReportFR;

	@FindBy(xpath = "//td[text()='Rapport des offres client']")
	private WebElement clientBidsReportFR;

	@FindBy(xpath = "//td[text()='Rapport des paiements']")
	private WebElement financialSettlementReportFSAFR;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement downloadFinancialSettlementReportFSAInFR;

	@FindBy(xpath = "//h2[text()='USER ACTIVITY AUDIT REPORT']")
	private WebElement headingActivityReport;

	@FindBy(xpath = "//*[text()='Download']")
	private WebElement downLoadBtn;

	@FindBy(xpath = "//h3[text()='Configurations']")
	private WebElement headingConfig;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement langBtnEng;

	@FindBy(xpath = "//*[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement ApprovalMessage;

	@FindBy(xpath = "(//div[@class='MuiContainer-root'])[3]")
	private WebElement footer;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//button[@aria-label='approval1Status']//*[name()='svg'][2]")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "//button[@aria-label='approval2Status']//*[name()='svg'][2]")
	private WebElement btnSecondApproval;

	@FindBy(xpath = "//*[@name='approval2Status']")
	private WebElement btnRejectApproval;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//button[@title='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//span[text()='REJECT']")
	private WebElement rejectPopup;

	@FindBy(xpath = "//*[text()='Minimum Price']")
	private WebElement btnMinPrice;

	@FindBy(xpath = "//li[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "//button[@title='SUBMIT']")
	private WebElement btnConfirmSubmit;

	@FindBy(xpath = "//a[text()='Reports']")
	private WebElement btnReport;

	@FindBy(xpath = "//span[text()='Search Reports']")
	private WebElement btnSearchReport;

	@FindBy(xpath = "//input[@aria-label='Event Name']")
	private WebElement eventNameDropDown;

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement btnSearch;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[5]")
	private WebElement downloadFinancialStatementReportAR;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[6]")
	private WebElement downloadClientBidsAuditReportAR;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[7]")
	private WebElement downloadClientBidsReportAR;

	@FindBy(xpath = "//td[text()='Financial Statement']")
	private WebElement financialStatementReport;

	@FindBy(xpath = "//td[text()='Client Bids Audit Report']")
	private WebElement clientBidsAuditReport;

	@FindBy(xpath = "//td[text()='Client Bids Report']")
	private WebElement clientBidsReport;

	@FindBy(xpath = "//td[text()='Financial Settlement Report']")
	private WebElement financialSettlementReportFSA;

	@FindBy(xpath = "(//button[@type='button'])[4]")
	private WebElement downloadFinancialSettlementReportFSA;

	@FindBy(xpath = "//h1[text()='User Administration']")
	private WebElement userAdminPage;

	@FindBy(xpath = "//*[text()='USER ACTIVITY AUDIT REPORT']")
	private WebElement userActivityAudReportBtn;

	@FindBy(xpath = "//input[@placeholder='Start Date']")
	private WebElement selectActivityReportDate;

	@FindBy(xpath = "//input[@placeholder='End Date']")
	private WebElement selectActivityReportEndDate;

	@FindBy(xpath = "//button[@aria-label='Previous Month']")
	private WebElement selectActivityReportDatePreviousDate;

	@FindBy(xpath = "//div[@class='react-datepicker__current-month']")
	private WebElement firstActivityReportDateStartDate;

	@FindBy(xpath = "//span[text()='SAVE']")
	private WebElement btnSaveConfig;

	@FindBy(xpath = "//p[text()='The new configurations have been successfully saved.']")
	private WebElement saveConfigSuccessMessage;

	@FindBy(xpath = "//*[text()='You have been successfully logged out.']")
	private WebElement Logoutmessage;

	public ApprovalStatusPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void changeLangauge() {
		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
				langBtnFrench.click();
				wait.until(ExpectedConditions.visibilityOf(langBtnEng));
				langBtnEng.click();
				System.out.println("Langauge changed successfully from french to english ");
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Évènements']")));
				langBtnFrench.click();
				wait.until(ExpectedConditions.visibilityOf(langBtnEng));
				langBtnEng.click();
				System.out.println("Langauge changed successfully from french to english ");
			}
		
		} catch (Exception e) {
			System.out.println("English Langauge is allready present.");
		}
		
//		ExpectedConditions.elementToBeClickable(langBtnFrench);
//		langBtnFrench.click();
//		ExpectedConditions.elementToBeClickable(langBtnEng);
//		try {
//			langBtnEng.click();
//		} catch (Exception e) {
//			langBtnEng.click();
//		}
//		System.out.println("Langauge changed successfully from english to french.");
//		ObjectRepo.getTest().log(Status.PASS, "Language changed to English ");
	}

	public void firstApprovalWA_RS() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnWA_RS));
	
		WebElement element1 = driver.findElement(eventDetailsBtnWA_RS);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		wait.until(ExpectedConditions.visibilityOf(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
	}

	public void firstApprovalWASingleAuction() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnWA_SA));
		WebElement element1 = driver.findElement(eventDetailsBtnWA_SA);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		wait.until(ExpectedConditions.visibilityOf(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
	}
	public void firstApprovalforreservesalecal() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
	
		WebElement element1 = driver.findElement(eventDetailsBtnCA2);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnCA2).click();
		wait.until(ExpectedConditions.visibilityOf(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
	}

	public void firstApprovalWA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnWA));
	
		WebElement element1 = driver.findElement(eventDetailsBtnWA);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnWA).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
	}

	public void firstApprovalCA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnCA_Single));
		WebElement element1 = driver.findElement(eventDetailsBtnCA_Single);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnCA_Single).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
	}

	public void firstApprovalQC_RandomEventName() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn1));
	
		WebElement element1 = driver.findElement(eventDetailsBtn1);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtn1).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
//		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "First Approval Done");
	}
	public void firstApprovalQC() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnQC_SBMA));
	
		WebElement element1 = driver.findElement(eventDetailsBtnQC_SBMA);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnQC_SBMA).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
//		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "First Approval Done");
	}

	public void firstApprovalQCSA() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnQC_Single));
	
		WebElement element1 = driver.findElement(eventDetailsBtnQC_Single);
		js.executeScript("arguments[0].scrollIntoView();", element1);
		driver.findElement(eventDetailsBtnQC_Single).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
//		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "First Approval Done");
	}
	public void SecondApprovalBySameJurisdiction() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnAdminstration.click();
//		
//		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		WebElement element = eventDetailsBtn;
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		Thread.sleep(5000);
		popupBtn.click();
		Thread.sleep(10000);
//		action.moveToElement(abc11).build().perform();
//		action.clickAndHold(abc11).build().perform();
//		action.click(abc11).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionR70() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnR70);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		WebElement abc11 = popupBtn;
		action.moveToElement(abc11).click().build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionForCalReaseerveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnCA2);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		popupBtn.click();
		Thread.sleep(15000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionR70ForCAreservesale() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnCA2);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		popupBtn.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionRS_WA() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnRS_WA);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		WebElement abc11 = popupBtn;
		action.moveToElement(abc11).click().build().perform();
		Thread.sleep(8000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionWS() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		WebElement abc1 = btnEvent;
		action.moveToElement(abc1).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnWA);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
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
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionCA_Single_Auction() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		WebElement abc = btnAdminstration;
		action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
//		WebElement abc1 = btnEvent;
//		action.moveToElement(abc1).click().build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtnCA_Single));
		WebElement element = driver.findElement(eventDetailsBtnCA_Single);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		WebElement abc11 = popupBtn;
		action.moveToElement(abc11).click().build().perform();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void SecondApprovalBySameJurisdictionQC_Single_Auction() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		action = new Actions(driver);
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Adminstration button");
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Event button");
		WebElement element = driver.findElement(eventDetailsBtnQC_Single);
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnApprovalQue));
		btnSecondApproval.click();
		wait.until(ExpectedConditions.visibilityOf(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		WebElement abc11 = popupBtn;
		action.moveToElement(abc11).click().build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Same Jurisdiction");
	}

	public void secondApprovalForCA() throws IOException {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref = driver.findElement(eventDetailsBtnCA);
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).click().build().perform();
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
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");

	}

	public void secondApproval() {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
	
		WebElement element1 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		eventDetailsBtn.click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Navigate to particular event ");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
			WebElement element11 = btnApprovalQue;
			js.executeScript("arguments[0].scrollIntoView();", element11);
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
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		popupBtn.click();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
		driver.findElement(By.xpath("//*[@class='MuiButtonBase-root MuiIconButton-root']")).click();
	}

	public void secondApprovalWA() throws IOException, InterruptedException {
		WebElement ref = driver.findElement(eventDetailsBtnWA);
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).build().perform();
		action.click(ref).build().perform();
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
		popupBtn.click();
		Thread.sleep(10000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Washington jurisdiction");

	}

	public void secondApprovalCA() throws IOException, InterruptedException {
		WebElement ref = driver.findElement(eventDetailsBtnCA_Single);
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).build().perform();
		action.click(ref).build().perform();
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
//		action.moveToElement(abc1).build().perform();
//		action.click(abc1).build().perform();
		
		abc1.click();
		Thread.sleep(10000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by California jurisdiction");
	}

	public void secondApprovalForQC() throws IOException, InterruptedException {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref = eventDetailsBtn;
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).build().perform();
		action.click(ref).build().perform();
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
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void secondApprovalForQC_Single_Auction() throws IOException, InterruptedException {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref = driver.findElement(eventDetailsBtnQC_Single);
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		driver.findElement(eventDetailsBtnQC_Single).click();
//		Actions action = new Actions(driver);
//		action.moveToElement(ref).build().perform();
//		action.click(ref).build().perform();
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
		wait.until(ExpectedConditions.visibilityOf(popupBtn));
		WebElement abc1 = popupBtn;
		abc1.click();
		Thread.sleep(10000);
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");

	}

	public void navigateToCurrentEvent() throws IOException {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref = eventDetailsBtn;
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).build().perform();
		action.click(ref).build().perform();
		System.out.println("Navigate to particular event ");
	}

	public void navigateToCurrentEventQC() throws IOException {
		CreateEventPage create = new CreateEventPage();
		create.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement ref = driver.findElement(eventDetailsBtnQC);
	
		js.executeScript("arguments[0].scrollIntoView();", ref);
		Actions action = new Actions(driver);
		action.moveToElement(ref).build().perform();
		action.click(ref).build().perform();
		System.out.println("Navigate to particular event ");
	}

	public void secondApprovalForSingleAuction() {
		try {
			WebElement element1 = btnAdminstration;
			js.executeScript("arguments[0].scrollIntoView();", element1);
			wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
			btnAdminstration.click();
		} catch (Exception e1) {
			btnAdminstration.click();
		}
		btnEvent.click();
		
		WebElement element = footer;
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(footer));
		eventDetailsBtn.click();
		System.out.println("Navigate to particular event ");
		try {
			WebElement element2 = btnApprovalQue;
			js.executeScript("arguments[0].scrollIntoView();", element2);
			wait.until(ExpectedConditions.elementToBeClickable(btnApprovalQue));
			btnApprovalQue.click();
		} catch (Exception e) {
			//  
			btnApprovalQue.click();
		}
		try {
			btnSubmit1.click();
		} catch (Exception e) {
			btnSubmit2.click();
		}
		WebElement abc1 = popupBtn;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "Approval given by Quebec jurisdiction");
	}

	public void RejectsecondApproval() throws InterruptedException {
	
		btnAdminstration.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		wait.until(ExpectedConditions.elementToBeClickable(eventDetailsBtn));
		WebElement element2 = eventDetailsBtn;
		js.executeScript("arguments[0].scrollIntoView();", element2);
		eventDetailsBtn.click();
		System.out.println("Navigated to particular event ");
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
		action.click(abc1).build().perform();
		System.out.println("Second Sponsor rejected Event successfully");
		ObjectRepo.getTest().log(Status.PASS, "Second Sponsor rejected Event successfully");
	}

	public void Logout() {
	    try {
	        // First attempt: Standard Selenium click
	        try {
	            
	            js.executeScript("arguments[0].scrollIntoView();", btnProfile);
	            btnProfile.click();
	            Thread.sleep(2000);
	            wait.until(ExpectedConditions.visibilityOf(btnLogout)).click();
	            return; // Exit method if successful
	        } catch (Exception standardClickException) {
	            System.out.println("Standard click failed. Trying JavaScript click.");
	        }

	        // Second attempt: JavaScript click
	        try {
	            
	            js.executeScript("arguments[0].scrollIntoView();", btnProfile);
	            js.executeScript("arguments[0].click();", btnProfile);
	            Thread.sleep(2000);
	            js.executeScript("arguments[0].click();", btnLogout);
	            return; // Exit method if successful
	        } catch (Exception jsClickException) {
	            System.out.println("JavaScript click failed. Trying Actions class.");
	        }

	        // Third attempt: Actions class click
	        try {
	            Actions actions = new Actions(driver);
	            actions.moveToElement(btnProfile).click().build().perform();
	            Thread.sleep(2000);
	            actions.moveToElement(btnLogout).click().build().perform();
	        } catch (Exception actionsClickException) {
	            System.out.println("All click methods failed. Unable to logout.");
	            throw actionsClickException; // Re-throw to allow caller to handle
	        }
	    } catch (Exception e) {
	        System.out.println("Unexpected error during logout: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public void Logout2() throws Exception {
		try {
			Thread.sleep(10000);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnProfile));
				btnProfile.click();
			} catch (Exception e) {
				btnProfile.click();
			}
			wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
			btnLogout.click();
		} catch (InterruptedException e) {
		System.out.println("Not able to Logout.");
		}
		
	}

	public void LogoutQC() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			btnProfileForQc.click();
			btnLogout.click();
		} catch (Exception e) {
			System.out.println("Not able to logout ");
		}
	}

	public void LogoutAR() {
	
		WebElement element1 = btnProfile;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		WebElement abc1 = btnProfile;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.click(abc1).build().perform();
		btnLogout.click();
	}

	public void setFXrateQC() {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnMinPrice));
			btnMinPrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		}
		try {
		
			WebElement element = driver.findElement(textBoxFxQc);
			js.executeScript("arguments[0].scrollIntoView();", element);
			driver.findElement(textBoxFxQc).click();
			driver.findElement(textBoxFxQc).sendKeys(prop.getProperty("FXRate"));
		} catch (Exception e) {
		
			WebElement element = driver.findElement(textBoxFxCal);
			js.executeScript("arguments[0].scrollIntoView();", element);
			driver.findElement(textBoxFxCal).click();
			driver.findElement(textBoxFxCal).sendKeys(prop.getProperty("FXRate"));
		}
		System.out.println("fx rate set for quebec juridiction ");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal)).click();
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(btnAlertSubmit)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void setFXrateCal() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
			btnReservePrice.click();
		} catch (Exception e1) {
			wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice2));
			btnReservePrice2.click();
		}
		try {
		
			WebElement element = driver.findElement(textBoxFxCal);
			js.executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(textBoxFxCal));
			driver.findElement(textBoxFxCal).click();
			driver.findElement(textBoxFxCal).sendKeys(prop.getProperty("FXRate"));
		} catch (Exception e) {
		
			WebElement element = driver.findElement(textBoxFxQc);
			js.executeScript("arguments[0].scrollIntoView();", element);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxFxQc));
			driver.findElement(textBoxFxQc).click();
			driver.findElement(textBoxFxQc).sendKeys(prop.getProperty("FXRate"));
		}

		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitCal));
			driver.findElement(btnsubmitCal).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnsubmitqc));
			driver.findElement(btnsubmitqc).click();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title=\"SUBMIT\"]")));
			btnAlertSubmit.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fx rate set");
	}

	public void firstApproval2() {
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
	}

	public void firstApproval1() {
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
	}

	public void accessTheReportsAvailableForAR() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='Auction 187']"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Auction 187']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertSearchedEventName = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Auction 187 - [14 Sep 2022]']")))
				.getText();
		System.out.println("Searched Event Name displayed as: " + aseertSearchedEventName);
		ObjectRepo.getTest().log(Status.PASS, "Searched Event Name displayed as:" + aseertSearchedEventName);
		Assert.assertEquals("Auction 187 - [14 Sep 2022]", aseertSearchedEventName);
		System.out.println("******************************************************************");
		System.out.println("Following reports are accessible to AR:- ");
		String aseertFinancialStatementReport = wait
				.until(ExpectedConditions.elementToBeClickable(financialStatementReport)).getText();
		System.out.println(aseertFinancialStatementReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to AR is: " + aseertFinancialStatementReport);
		Assert.assertEquals("Financial Statement", aseertFinancialStatementReport);
		String aseertClientBidsAuditReport = wait.until(ExpectedConditions.elementToBeClickable(clientBidsAuditReport))
				.getText();
		System.out.println(aseertClientBidsAuditReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to AR is: " + aseertClientBidsAuditReport);
		Assert.assertEquals("Client Bids Audit Report", aseertClientBidsAuditReport);
		String aseertClientBidsReport = wait.until(ExpectedConditions.elementToBeClickable(clientBidsReport)).getText();
		System.out.println(aseertClientBidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to AR is: " + aseertClientBidsReport);
		Assert.assertEquals("Client Bids Report", aseertClientBidsReport);
		System.out.println("******************************************************************");
	}

	public void accessTheReportsAvailableForFSA() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnReport)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReport)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportEN))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Reports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDown)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='Auction 187']"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Auction 187']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearch)).click();
		String aseertSearchedEventName = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Auction 187 - [14 Sep 2022]']")))
				.getText();
		System.out.println("Searched Event Name displayed as: " + aseertSearchedEventName);
		ObjectRepo.getTest().log(Status.PASS, "Searched Event Name displayed as:" + aseertSearchedEventName);
		Assert.assertEquals("Auction 187 - [14 Sep 2022]", aseertSearchedEventName);
		System.out.println("******************************************************************");
		System.out.println("Following reports are accessible to FSA:- ");
		String aseertFinancialSettlementReportFSA = wait
				.until(ExpectedConditions.elementToBeClickable(financialSettlementReportFSA)).getText();
		System.out.println(aseertFinancialSettlementReportFSA);
		ObjectRepo.getTest().log(Status.PASS, "Reports access available to FSA is: " + aseertFinancialSettlementReportFSA);
		Assert.assertEquals("Financial Settlement Report", aseertFinancialSettlementReportFSA);
		System.out.println("******************************************************************");
	}

	public void accessTheReportsAvailableForFSAInFrench() throws Exception {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		englishLngText.click();
		frenchLngText.click();
		System.out.println("English to french language is changed");
		ObjectRepo.getTest().log(Status.PASS, "English to french language is changed");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReportFR)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportFR))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Rapports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDownFR)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='Auction 187']"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Auction 187']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchFR)).click();
		String aseertSearchedEventName = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Auction 187 - [14 Sep 2022]']")))
				.getText();
		System.out.println("Searched Event Name displayed as: " + aseertSearchedEventName);
		ObjectRepo.getTest().log(Status.PASS, "Searched Event Name displayed as:" + aseertSearchedEventName);
		Assert.assertEquals("Auction 187 - [14 Sep 2022]", aseertSearchedEventName);
		System.out.println("******************************************************************");
		System.out.println("Following reports are accessible to FSA:- ");
		String aseertFinancialSettlementReportFSAInFR = wait
				.until(ExpectedConditions.elementToBeClickable(financialSettlementReportFSAFR)).getText();
		System.out.println(aseertFinancialSettlementReportFSAInFR);
		ObjectRepo.getTest().log(Status.PASS,
				"Reports access available to FSA is: " + aseertFinancialSettlementReportFSAInFR);
		Assert.assertEquals("Rapport des paiements", aseertFinancialSettlementReportFSAInFR);
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForARInEN() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(downloadFinancialStatementReportAR));
			WebElement element = downloadFinancialStatementReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadFinancialStatementReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		path = folderPath + File.separator + "Financial Statement - Auction 187 09-14-2022 QC4446-5674.pdf";
		File file = new File(path);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		System.out.println(text);
		document.close();
		System.out.println("Financial Statement Report made available to users is not blank.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report made available to users is not blank.");
		System.out.println("Financial Statement Report validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement Report validated in English for Quebec AR");
		System.out.println("***********************************************************************");
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadClientBidsAuditReportAR));
			WebElement element = downloadClientBidsAuditReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadClientBidsAuditReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForClientBidsAuditReportAR ref1 = new ExcelDataProviderForClientBidsAuditReportAR();
		System.out.println("Client Bids Audit Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report have a title that is the same as the report name.");
		String rowNameCBAR = ref1.getStringData("Client Bids Audit Report", 7, 0);
		Assert.assertEquals("Client Bid ID", rowNameCBAR);
		System.out.println("Column name asserted is: " + rowNameCBAR);
		String entityJurisdiction = ref1.getStringData("Client Bids Audit Report", 3, 1);
		Assert.assertEquals("T6QCEmetteurA", entityJurisdiction);
		System.out.println("Displayed Legal Name is: " + entityJurisdiction);
		System.out.println("Client Bids Audit Report made available to users is not blank.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report made available to users is not blank.");
		System.out.println("Undefined is NOT populated in the Government/Jurisdiction column on the Reports page.");
		System.out.println("Client Bids Audit Report validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report validated in English for Quebec AR");
		System.out.println("******************************************************************");
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadClientBidsReportAR));
			WebElement element = downloadClientBidsReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadClientBidsReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForClientBidsReportAR ref2 = new ExcelDataProviderForClientBidsReportAR();
		System.out.println("Client Bids Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report have a title that is the same as the report name.");
		String rowNameCBR = ref2.getStringData("Client Bids Report", 9, 0);
		Assert.assertEquals("Client Bid ID ", rowNameCBR);
		System.out.println("Column name asserted is: " + rowNameCBAR);
		String entityJurisdiction1 = ref2.getStringData("Client Bids Report", 3, 1);
		Assert.assertEquals("T6QCEmetteurA", entityJurisdiction1);
		System.out.println("Displayed Legal Name is: " + entityJurisdiction1);
		System.out.println("Client Bids Report made available to users is not blank.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report made available to users is not blank.");
		System.out.println("Undefined is NOT populated in the Government/Jurisdiction column on the Reports page.");
		System.out.println("Client Bids Report validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report validated in English for Quebec AR");
		System.out.println("******************************************************************");
	}

	public void assertReportsAvailableForARInFrench() throws Exception {

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(englishLngText));
		englishLngText.click();
		frenchLngText.click();
		System.out.println("English to french language is changed");
		ObjectRepo.getTest().log(Status.PASS, "English to french language is changed");
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchReportFR)).click();
		String aseertHeadingOfReportPage = wait.until(ExpectedConditions.elementToBeClickable(headingReportFR))
				.getText();
		System.out.println("Heading for the Reports page displayed is: " + aseertHeadingOfReportPage);
		Assert.assertEquals("Rapports", aseertHeadingOfReportPage);
		System.out.println("Reports page open successfully");
		System.out.println("***********************************************************************");
		WebElement element1 = eventNameDropDownFR;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropDownFR)).click();
		WebElement element = driver.findElement(By.xpath("//span[text()='Auction 187']"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Auction 187']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSearchFR)).click();
		System.out.println("Following reports are present in French: ");
		ObjectRepo.getTest().log(Status.PASS, "Following reports are present in French: ");
		String assertFinancialStatementReportFR = financialStatementReportFR.getText();
		System.out.println(assertFinancialStatementReportFR);
		ObjectRepo.getTest().log(Status.PASS, assertFinancialStatementReportFR);
		String ssertClientBidsAuditReportFR = clientBidsAuditReportFR.getText();
		System.out.println(ssertClientBidsAuditReportFR);
		ObjectRepo.getTest().log(Status.PASS, ssertClientBidsAuditReportFR);
		String assertClientBidsReportFR = clientBidsReportFR.getText();
		System.out.println(assertClientBidsReportFR);
		ObjectRepo.getTest().log(Status.PASS, assertClientBidsReportFR);
		System.out.println("***********************************************************************");
	}

	public void downloadTheReportsAvailableForFSAInEN() throws Exception {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadFinancialSettlementReportFSA));
			WebElement element = downloadFinancialSettlementReportFSA;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadFinancialSettlementReportFSA;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForFinancialSettlementReportFSA ref1 = new ExcelDataProviderForFinancialSettlementReportFSA();
		System.out.println("Financial Settlement Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS,
				"Financial Settlement Report have a title that is the same as the report name.");
		String rowNameCBAR = ref1.getStringData("Financial Settlement Report", 4, 0);
		Assert.assertEquals("Settlement Price USD (Current)", rowNameCBAR);
		System.out.println("Field name asserted is: " + rowNameCBAR);
		System.out.println("Financial Settlement Report made available to users is not blank.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report made available to users is not blank.");
		System.out.println("Financial Settlement Report validated in English for CA FSA");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in English for CA FSA");
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForFSAInFR() throws Exception {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadFinancialSettlementReportFSAInFR));
			WebElement element = downloadFinancialSettlementReportFSAInFR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadFinancialSettlementReportFSAInFR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForFinancialSettlementReportFSAInFrench ref1 = new ExcelDataProviderForFinancialSettlementReportFSAInFrench();
		System.out.println("Financial Settlement Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS,
				"Financial Settlement Report have a title that is the same as the report name.");
		String rowNameCBAR = ref1.getStringData("Rapport des paiements", 4, 0);
		Assert.assertEquals("Prix de vente final USD (présent)", rowNameCBAR);
		System.out.println("Field name asserted is: " + rowNameCBAR);
		System.out.println("Financial Settlement Report made available to users is not blank.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report made available to users is not blank.");
		System.out.println("Financial Settlement Report validated in French for Quebec FSA");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report validated in French for Quebec FSA");
		System.out.println("******************************************************************");
	}

	public void downloadTheReportsAvailableForARFR() throws Exception {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadFinancialStatementReportAR));
			WebElement element = downloadFinancialStatementReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadFinancialStatementReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		path = folderPath + File.separator + "Facture - Auction 187 09-14-2022 QC4446-5674.pdf";
		File file = new File(path);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String text = pdfStripper.getText(document);
		System.out.println(text);
		document.close();
		System.out.println("Financial Statement validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement validated in English for Quebec AR");
		System.out.println("***********************************************************************");
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadClientBidsAuditReportAR));
			WebElement element = downloadClientBidsAuditReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadClientBidsAuditReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForClientBidsAuditReportARInFrench ref1 = new ExcelDataProviderForClientBidsAuditReportARInFrench();
		System.out.println("Client Bids Audit Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report have a title that is the same as the report name.");
		String rowNameCBAR = ref1.getStringData("Rapport de vérification des off", 7, 0);
		Assert.assertEquals("ID de l'offre pour le client", rowNameCBAR);
		System.out.println("Client Bids Audit Report validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report validated in English for Quebec AR");
		System.out.println("******************************************************************");
		try {

			wait.until(ExpectedConditions.elementToBeClickable(downloadClientBidsReportAR));
			WebElement element = downloadClientBidsReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		} catch (Exception e) {
			WebElement element = downloadClientBidsReportAR;
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.click().build().perform();
		}
		ExcelDataProviderForClientBidsReportARInFrench ref2 = new ExcelDataProviderForClientBidsReportARInFrench();
		System.out.println("Client Bids Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report have a title that is the same as the report name.");
		String rowNameCBR = ref2.getStringData("Rapport des offres client", 9, 0);
		Assert.assertEquals("ID de l'offre pour le client", rowNameCBR);
		System.out.println("Client Bids Report validated in English for Quebec AR");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report validated in English for Quebec AR");
		System.out.println("******************************************************************");
	}

	public void accessTheUserActivityAuditReport() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnUser();
		String assertuserAdminPage = wait.until(ExpectedConditions.elementToBeClickable(userAdminPage)).getText();
		Assert.assertEquals(assertuserAdminPage, "User Administration");
		System.out.println("Heading of the displayed page is : " + assertuserAdminPage);
		ObjectRepo.getTest().log(Status.PASS, "Heading of the displayed page is : " + assertuserAdminPage);
		wait.until(ExpectedConditions.elementToBeClickable(userActivityAudReportBtn));
		userActivityAudReportBtn.click();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		selectActivityReportDate.click();
		selectActivityReportDate.sendKeys(date1);
		System.out.println(date1 + " Date is selected from datepicker");
		ObjectRepo.getTest().log(Status.PASS, date1 + " Date is selected from datepicker");
		wait.until(ExpectedConditions.elementToBeClickable(downLoadBtn));
		downLoadBtn.click();
		System.out.println("******************************************************************");
	}

	public void downloadTheUserActivityAuditReport() throws Exception {
		ExcelDataProviderForActivityAuditReportForSP ref1 = new ExcelDataProviderForActivityAuditReportForSP();
		System.out.println("User Activity Audit Report have a title that is the same as the report name.");
		ObjectRepo.getTest().log(Status.PASS,
				"User Activity Audit Report have a title that is the same as the report name.");
		String reportDateAndTime = ref1.getStringData("User Activity Audit Report", 0, 0);
		Assert.assertEquals("Report Date & Time", reportDateAndTime);
		System.out.println("Displayed Report Date & Time label is: " + reportDateAndTime);
		String reportDateAndTimeValues = ref1.getStringData("User Activity Audit Report", 0, 1);
		System.out.println("Displayed Report Date & Time is: " + reportDateAndTimeValues);
		System.out.println("******************************************************************");
		String column1 = ref1.getStringData("User Activity Audit Report", 2, 0);
		Assert.assertEquals("User ID", column1);
		System.out.println("Displayed first column name is: " + column1);
		String column1Value = ref1.getStringData("User Activity Audit Report", 3, 0);
		System.out.println("Displayed first column value is: " + column1Value);
		System.out.println("******************************************************************");
		String column2 = ref1.getStringData("User Activity Audit Report", 2, 1);
		Assert.assertEquals("Entity Legal Name", column2);
		System.out.println("Displayed second column name is: " + column2);
		String column2Value;
		try {
			column2Value = ref1.getStringData("User Activity Audit Report", 3, 1);
			System.out.println("Displayed second column value is: " + column2Value);
		} catch (Exception e) {
			System.out.println("Displayed second column value is null.");
		}
		System.out.println("******************************************************************");
		String column3 = ref1.getStringData("User Activity Audit Report", 2, 2);
		Assert.assertEquals("First Name", column3);
		System.out.println("Displayed third column name is: " + column3);
		String column3Value = ref1.getStringData("User Activity Audit Report", 3, 2);
		System.out.println("Displayed third column value is: " + column3Value);
		System.out.println("******************************************************************");
		String column4 = ref1.getStringData("User Activity Audit Report", 2, 3);
		Assert.assertEquals("Last Name", column4);
		System.out.println("Displayed fourth column name is: " + column4);
		String column4Value = ref1.getStringData("User Activity Audit Report", 3, 3);
		System.out.println("Displayed fourth column value is: " + column4Value);
		System.out.println("******************************************************************");
		String column5 = ref1.getStringData("User Activity Audit Report", 2, 4);
		Assert.assertEquals("User Role", column5);
		System.out.println("Displayed fifth column name is: " + column5);
		String column5Value = ref1.getStringData("User Activity Audit Report", 3, 4);
		System.out.println("Displayed fifth column value is: " + column5Value);
		System.out.println("******************************************************************");
		String column6 = ref1.getStringData("User Activity Audit Report", 2, 5);
		Assert.assertEquals("IP Address", column6);
		System.out.println("Displayed sixth column name is: " + column6);
		String column6Value = ref1.getStringData("User Activity Audit Report", 3, 5);
		System.out.println("Displayed sixth column value is: " + column6Value);
		System.out.println("******************************************************************");
		String column7 = ref1.getStringData("User Activity Audit Report", 2, 6);
		Assert.assertEquals("Action", column7);
		System.out.println("Displayed seventh column name is: " + column7);
		String column7Value = ref1.getStringData("User Activity Audit Report", 3, 6);
		System.out.println("Displayed seventh column value is: " + column7Value);
		System.out.println("******************************************************************");
		String column8 = ref1.getStringData("User Activity Audit Report", 2, 7);
		Assert.assertEquals("Key Information", column8);
		System.out.println("Displayed eighth column name is: " + column8);
		String column8Value = ref1.getStringData("User Activity Audit Report", 3, 7);
		System.out.println("Displayed eighth column value is: " + column8Value);
		System.out.println("******************************************************************");
		String column9 = ref1.getStringData("User Activity Audit Report", 2, 8);
		Assert.assertEquals("Date/Time (ET)", column9);
		System.out.println("Displayed ninth column name is: " + column9);
		String column9Value = ref1.getStringData("User Activity Audit Report", 3, 8);
		System.out.println("Displayed ninth column value is: " + column9Value);
		System.out.println("******************************************************************");
		String column10 = ref1.getStringData("User Activity Audit Report", 2, 9);
		Assert.assertEquals("Country", column10);
		System.out.println("Displayed tenth column name is: " + column10);
		String column10Value = ref1.getStringData("User Activity Audit Report", 3, 9);
		System.out.println("Displayed tenth column value is: " + column10Value);
		System.out.println("******************************************************************");
	}

	public void clickbtnSettings() {
		WebElement abc = wait.until(ExpectedConditions.elementToBeClickable(btnSettings));
		action = new Actions(driver);
		action.moveToElement(abc).build().perform();
		action.clickAndHold(abc).build().perform();
		action.click(abc).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 1 : Click on Settings button");
	}

	public void clickbtnConfiguration() {
		wait.until(ExpectedConditions.elementToBeClickable(btnConfiguration));
		WebElement abc1 = btnConfiguration;
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, "step 2 : Click on Configuration button");
	}

	public void changeConfigurationSetting() {

		String assertHeadingConfig = wait.until(ExpectedConditions.elementToBeClickable(headingConfig)).getText();
		System.out.println("Heading for the Configuration page is: " + assertHeadingConfig);
		ObjectRepo.getTest().log(Status.PASS, "Heading for the Configuration page is: " + assertHeadingConfig);
		Assert.assertEquals("Configurations", assertHeadingConfig);
		WebElement element1 = inputSessionOutTime;
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(inputSessionOutTime)).click();
		WebElement element = inputSessionOutTime;
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.BACK_SPACE);
		wait.until(ExpectedConditions.elementToBeClickable(inputSessionOutTime)).sendKeys("35");
		wait.until(ExpectedConditions.elementToBeClickable(btnSaveConfig)).click();
		String assertSaveConfigSuccessMessage = wait
				.until(ExpectedConditions.elementToBeClickable(saveConfigSuccessMessage)).getText();
		System.out.println("Success message after saving the config details is: " + assertSaveConfigSuccessMessage);
		ObjectRepo.getTest().log(Status.PASS,
				"Success message after saving the config details is: " + assertSaveConfigSuccessMessage);
		Assert.assertEquals("The new configurations have been successfully saved.", assertSaveConfigSuccessMessage);
		driver.navigate().refresh();
	}

	public void firstApprovalConfiguration() {
		WebElement element = driver.findElement(By.xpath("(//button[@type='button'])[6]"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
	}

	public void secondApprovalConfiguration() {
		WebElement element = driver.findElement(By.xpath("(//button[@type='button'])[7]"));
	
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		driver.navigate().refresh();
	}

	public void accessTheUserActivityAuditReport1() throws Exception {
		CreateEventPage ref = new CreateEventPage();
		ref.clickbtnAdmin();
		ref.clickbtnUser();
		String assertuserAdminPage = wait.until(ExpectedConditions.elementToBeClickable(userAdminPage)).getText();
		Assert.assertEquals(assertuserAdminPage, "User Administration");
		System.out.println("Heading of the displayed page is : " + assertuserAdminPage);
		ObjectRepo.getTest().log(Status.PASS, "Heading of the displayed page is : " + assertuserAdminPage);
		wait.until(ExpectedConditions.elementToBeClickable(userActivityAudReportBtn));
		userActivityAudReportBtn.click();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		selectActivityReportDate.click();
		selectActivityReportDate.sendKeys("01 January 2013");
		@SuppressWarnings("unused")
		boolean previousDate;
		try {
			previousDate = selectActivityReportDatePreviousDate.isEnabled();
			System.out.println("The start date allows selection before 2013.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("The start date allows selection from 2013 onward.");
		}
		String assertFirstStartDate = wait
				.until(ExpectedConditions.elementToBeClickable(firstActivityReportDateStartDate)).getText();
		Assert.assertEquals(assertFirstStartDate, "January 2013");
		System.out.println("First start date/month starts from: " + assertFirstStartDate);
		System.out.println("The start date allows selection from 2013 onward.");
		ObjectRepo.getTest().log(Status.PASS, "The start date allows selection from 2013 onward.");
		System.out.println("******************************************************************");
		headingActivityReport.click();
		selectActivityReportDate.click();
		selectActivityReportDate.sendKeys(date1);
		System.out.println(date1 + " Start Date is selected from datepicker");
		ObjectRepo.getTest().log(Status.PASS, date1 + " Start Date is selected from datepicker");
		boolean assertEnable = wait.until(ExpectedConditions.elementToBeClickable(downLoadBtn)).isEnabled();
		if (assertEnable == true) {
			System.out.println("If End Date not provided, the current date will be considered as the end date.");
			ObjectRepo.getTest().log(Status.PASS,
					"If End Date not provided, the current date will be considered as the end date.");
		} else
			System.out.println("Download button is not enabled, please select End Date.");
		System.out.println("******************************************************************");
		selectActivityReportEndDate.click();
		selectActivityReportDate.sendKeys(date1);
		System.out.println(date1 + " End Date is selected from datepicker");
		ObjectRepo.getTest().log(Status.PASS, date1 + " End Date is selected from datepicker");
		System.out.println("The end date cannot be before start date.");
		ObjectRepo.getTest().log(Status.PASS, "The end date cannot be before start date.");
		System.out.println("******************************************************************");
		wait.until(ExpectedConditions.elementToBeClickable(downLoadBtn));
		downLoadBtn.click();
	}
}
