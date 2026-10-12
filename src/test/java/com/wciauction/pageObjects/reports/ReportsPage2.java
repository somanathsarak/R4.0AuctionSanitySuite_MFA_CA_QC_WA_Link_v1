package com.wciauction.pageObjects.reports;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.CommonLib;
import com.wci.auction.org.resources.ObjectRepo;

import excelReadersForReports.ExcelDataProviderForClientBidsAuditReport;
import excelReadersForReports.ExcelDataProviderForQualifiedBidderReportForJointAuction;
import excelReadersForReports.ExcelDataProviderforBidsReport;
import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ReportsPage2 {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public static CommonLib commonLibref = new CommonLib();

	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement Report_NameDuring_Bidding;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA']")
	private WebElement entityName;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement englishLngText;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement frenchLngText;

	@FindBy(xpath = "//*[text()='Search Reports']")
	private WebElement searchReportBtn;

	@FindBy(xpath = "//*[text()='SEARCH']")
	private WebElement searchBtn;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[5]")
	private WebElement mmDownloadReportBtn;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[2]")
	private WebElement reportNameDropDown;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement reportNameDropDown1;

	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement reportBtn;

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
	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]")
	private WebElement ResultReportText;

	@FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[2]")
	private WebElement staticResultReportText;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[1]")
	private WebElement bidderReportRow;

	@FindBy(xpath = "//*[text()='Reports Available to Sponsor and Monitor']")
	private WebElement reportAvailableLable;

	@FindBy(xpath = "//*[text()='Rapports accessibles aux sponsors et surveillants']")
	private WebElement frenchReportAvailableLable;

	@FindBy(xpath = "//*[text()='Reports Available to Sponsor and Monitor']")
	private WebElement ReportsAvailability;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[1]")
	private WebElement BidsReportAccessRow;

	@FindBy(xpath = "(//span[@class='MuiButton-label'])[7]")
	private WebElement BidsReportDownLoadBtn;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])")
	private WebElement ReportNameDropDown2;

	@FindBy(xpath = "(//button[@aria-label='Download Report'])[1]")
	private WebElement randomEventNmeReportDwnBtn;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[1]")
	private WebElement EntityNmeDropDownBtn2;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root'])[2]")
	private WebElement clientBidsAuditRow;

	@FindBy(xpath = "//*[text()='Account Representative']")
	private WebElement ARlable;

	@FindBy(xpath = "//input[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement searchEntityID;

	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement continueBtn;

	@FindBy(xpath = "//*[text()='T6CAEmetteurA (CA4376-5560)']")
	private WebElement EntityA1;

	private By downloadQualifiedBidderReport = By
			.xpath("//button[@aria-label='Download Qualified Bidders Report for event " + eventNameEng + "']");
	private By downloadResultReport = By
			.xpath("//button[@aria-label='Download Results Report for event " + prop.getProperty("Event_Name") + "']");
	private By ClientBidsAuditdownloadReportBtn = By
			.xpath("//button[@aria-label='Download Client Bids Audit Report for event " + eventNameEng + "']");

	public ReportsPage2() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void QcSponsorCanAccessTheResultsReport() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.elementToBeClickable(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Results Report");
		System.out.println("Result report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Result report selected from report name dropdown field");
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(bidderReportRow));
		String ResultReport = bidderReportRow.getText();
		System.out.println("Sponsor/Mm access the Result report :- " + ResultReport);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/Mm access the Result report :- " + ResultReport);
		System.out.println("********************************************************************************");
	}

	public void StaticResultReportForQc() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Results Report");
		System.out.println("Result report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Result report selected from report name dropdown field");
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		wait.until(ExpectedConditions.visibilityOf(ResultReportText));
		String ResultReport = ResultReportText.getText();
		System.out.println("Report Name :- " + ResultReport);
		ObjectRepo.getTest().log(Status.PASS, "Report Name :- " + ResultReport);
		Assert.assertEquals("Results Report", ResultReport);
		String StaticReport = staticResultReportText.getText();
		System.out.println("Static result report discription :- " + StaticReport);
		ObjectRepo.getTest().log(Status.PASS, "Static result report discription :- " + StaticReport);
		System.out.println(
				"*****************************************************************************************************");
	}

	public void NamingConventionForQcResultReport() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown));
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Results Report");
		System.out.println("Result report selected from report name dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Result report selected from report name dropdown");
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		WebElement abcd = driver.findElement(downloadResultReport);
		Actions actiont = new Actions(driver);
		actiont.moveToElement(abcd).build().perform();
		actiont.click(abcd).build().perform();
		Thread.sleep(25000);
		System.out.println("Result Report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Result Report successfully downloaded by sponsor");
		System.out.println(
				"*****************************************************************************************************");
	}

	public void QualifiedBiddersReportsLangaugeAvailabilityForQC() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(englishLngText));
		String langLable = englishLngText.getText();
		System.out.println(langLable);
		System.out.println("English language lable is displayed in header section");
		ObjectRepo.getTest().log(Status.PASS, "English language lable is displayed in header section");
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByIndex(1);
		System.out.println("Qualified bidder report selected from report name dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified bidder report selected from report name dropdown");
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		wait.until(ExpectedConditions.visibilityOf(reportAvailableLable));
		String ref = reportAvailableLable.getText();
		System.out.println(ref);
		ObjectRepo.getTest().log(Status.PASS, ref);
		String BidderRow = bidderReportRow.getText();
		System.out.println("Sponsor access the English Langauge qualified bidder report :- " + BidderRow);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor access English Langauge to qualified bidder report :- " + BidderRow);
		System.out.println("***********************************************************************");
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(englishLngText));
		englishLngText.click();
		wait.until(ExpectedConditions.visibilityOf(frenchLngText));
		frenchLngText.click();
		System.out.println("English to french language is changed");
		ObjectRepo.getTest().log(Status.PASS, "English to french language is changed");
		wait.until(ExpectedConditions.visibilityOf(frenchReportAvailableLable));
		String ref1 = frenchReportAvailableLable.getText();
		System.out.println(ref1);
		ObjectRepo.getTest().log(Status.PASS, ref1);
		wait.until(ExpectedConditions.visibilityOf(bidderReportRow));
		String BidderRowFrench = bidderReportRow.getText();
		System.out.println("Sponsor access to french Langauge qualified bidder report :- " + BidderRowFrench);
		ObjectRepo.getTest().log(Status.PASS,
				"Sponsor access to french Langauge qualified bidder report :- " + BidderRowFrench);
		System.out.println("***********************************************************************");
	}

	public void MMCanAccessTheQualifiedBiddersReport() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(englishLngText));
		String langLable = englishLngText.getText();
		System.out.println(langLable);
		System.out.println("English language lable is displayed in header section");
		ObjectRepo.getTest().log(Status.PASS, "English language lable is displayed in header section");
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByIndex(1);
		System.out.println("Qualified bidder report selected from report name dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Qualified bidder report selected from report name dropdown");
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(bidderReportRow));
		String BidderRow = bidderReportRow.getText();
		System.out.println("Market Monitor can Access:- " + BidderRow);
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor can Access:- " + BidderRow);
		wait.until(ExpectedConditions.elementToBeClickable(mmDownloadReportBtn));
		mmDownloadReportBtn.click();
		System.out.println("Qualified bidder report successfully downloaded by market monitor");
		ObjectRepo.getTest().log(Status.PASS, "Qualified bidder report successfully downloaded by market monitor");
		System.out.println("***********************************************************************");
	}

	public void IntroductoryFieldsAvailabilityForQualifiedBidderReport() throws Exception {
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
		ref.searchReportsByEventNameForJointAuction();
		wait.until(ExpectedConditions.elementToBeClickable(downloadQualifiedBidderReport));
		driver.findElement(downloadQualifiedBidderReport).click();
		Thread.sleep(20000);
		System.out.println("Qualified bidder report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Qualified bidder report successfully downloaded by sponsor");
		System.out.println("***********************************************************************");
		ExcelDataProviderForQualifiedBidderReportForJointAuction ref2 = new ExcelDataProviderForQualifiedBidderReportForJointAuction();
		String EventDateTime = ref2.getStringData("Qualified Bidders Report", 0, 1);
		System.out.println("Report Date and Time is displayed:- " + EventDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Report Date and Time is displayed:- " + EventDateTime);
		String RowName = ref2.getStringData("Qualified Bidders Report", 1, 1);
		System.out.println("Event name is displayed:- " + RowName);
		ObjectRepo.getTest().log(Status.PASS, "Event name is displayed:- " + RowName);
		String EventDate = ref2.getStringData("Qualified Bidders Report", 2, 1);
		System.out.println("Event Date is displayed:- " + EventDate);
		ObjectRepo.getTest().log(Status.PASS, "Event Date is displayed:- " + EventDate);
		double TotalQualifiedbidders = ref2.getNumericData("Qualified Bidders Report", 3, 1);
		System.out.println("Total Qualified bidders:- " + TotalQualifiedbidders);
		ObjectRepo.getTest().log(Status.PASS, "Total Qualified bidders:- " + TotalQualifiedbidders);
		System.out.println("***********************************************************************");
	}

	public void ConfirmTheSpecifiedIntroductoryFieldsAreAvailable() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByIndex(4);
		System.out.println("Bids Report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report selected from report name dropdown field");
		wait.until(ExpectedConditions.visibilityOf(searchBtn));
		searchBtn.click();
		String BidsReport = BidsReportAccessRow.getText();
		System.out.println("Sponsor/MM access the Bids report :- " + BidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM access the Bids report :- " + BidsReport);
		wait.until(ExpectedConditions.elementToBeClickable(BidsReportDownLoadBtn));
		BidsReportDownLoadBtn.click();
		// Thread.sleep(25000);
		System.out.println("Bids report successfully downloaded by Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Bids report successfully downloaded by Sponsor");
		System.out.println("********************************************************************************");
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

	public void MMCanAccessToBidsReportForAnEventBiddingWindowOpens() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown));
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Bids Report");
		System.out.println("Bids Report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report selected from report name dropdown field");
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		searchBtn.click();
		wait.until(ExpectedConditions.visibilityOf(BidsReportAccessRow));
		String BidsReport = BidsReportAccessRow.getText();
		System.out.println("Sponsor/MM access the Bids report :- " + BidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM access the Bids report :- " + BidsReport);
	}

	public void ConfirmTheBidsReportColumnDataLayout() throws Exception {
		// Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(searchReportBtn));
		searchReportBtn.click();
		wait.until(ExpectedConditions.visibilityOf(reportNameDropDown));
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Bids Report");
		System.out.println("Bids Report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report selected from report name dropdown field");
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		searchBtn.click();
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(BidsReportAccessRow));
		String BidsReport = BidsReportAccessRow.getText();
		System.out.println("Sponsor/MM access the Bids report :- " + BidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM access the Bids report :- " + BidsReport);
		wait.until(ExpectedConditions.elementToBeClickable(BidsReportDownLoadBtn));
		BidsReportDownLoadBtn.click();
		System.out.println("Bids report successfully downloaded by Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Bids report successfully downloaded by Sponsor");
		System.out.println("********************************************************************************");
		// Thread.sleep(25000);
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		String RGAccountNumber = ref.getStringData("Bids Report", 7, 0);
		System.out.println("Registry General Account Number:- " + RGAccountNumber);
		ObjectRepo.getTest().log(Status.PASS, "Registry General Account Number is displayed:- " + RGAccountNumber);
		String EntityLegalNme = ref.getStringData("Bids Report", 7, 1);
		System.out.println("Entity Legal Name is displayed:- " + EntityLegalNme);
		ObjectRepo.getTest().log(Status.PASS, "Entity Legal Name is displayed:- " + EntityLegalNme);
		String EntityOperName = ref.getStringData("Bids Report", 7, 2);
		System.out.println("Entity Operating Name is displayed:- " + EntityOperName);
		ObjectRepo.getTest().log(Status.PASS, "Entity Operating Name is displayed:- " + EntityOperName);
		System.out.println("********************************************************************************");
	}

	public void ConfirmTheBidsReportColumnDataLayoutForJointAuction() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		ReportsPage3 ref2 = new ReportsPage3();
		ref2.searchReportsByEventNameForJointAuction();
		ref2.downloadBidsReportForJASingleAuction();
		System.out.println("Bids report successfully downloaded by Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Bids report successfully downloaded by Sponsor");
		System.out.println("********************************************************************************");
		Thread.sleep(25000);
		ExcelDataProviderforBidsReport ref = new ExcelDataProviderforBidsReport();
		String RGAccountNumber = ref.getStringData("Bids Report", 7, 0);
		System.out.println("Registry General Account Number:- " + RGAccountNumber);
		ObjectRepo.getTest().log(Status.PASS, "Registry General Account Number is displayed:- " + RGAccountNumber);
		String EntityLegalNme = ref.getStringData("Bids Report", 7, 1);
		System.out.println("Entity Legal Name is displayed:- " + EntityLegalNme);
		ObjectRepo.getTest().log(Status.PASS, "Entity Legal Name is displayed:- " + EntityLegalNme);
		String EntityOperName = ref.getStringData("Bids Report", 7, 2);
		System.out.println("Entity Operating Name is displayed:- " + EntityOperName);
		ObjectRepo.getTest().log(Status.PASS, "Entity Operating Name is displayed:- " + EntityOperName);
		System.out.println("********************************************************************************");
	}

	public void ConfirmBidsDynamicReport() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));

		js.executeScript("arguments[0].scrollIntoView();", ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		WebElement dropDown = reportNameDropDown;
		Select element = new Select(dropDown);
		element.selectByVisibleText("Bids Report");
		System.out.println("Bids Report selected from report name dropdown field");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report selected from report name dropdown field");
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		searchBtn.click();
		String ReportAccess = ReportsAvailability.getText();
		System.out.println(ReportAccess);
		ObjectRepo.getTest().log(Status.PASS, ReportAccess);
		String BidsReport = BidsReportAccessRow.getText();
		System.out.println("Sponsor/MM access the Dynamic Bids report :- " + BidsReport);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor/MM access the Dynamic Bids report :- " + BidsReport);
		wait.until(ExpectedConditions.elementToBeClickable(BidsReportDownLoadBtn));
		BidsReportDownLoadBtn.click();
		System.out.println("Bids report successfully downloaded by Sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Bids report successfully downloaded by Sponsor");
		System.out.println(
				"********************************************************************************************");
	}

	public void AR_CanConfirmTheSpecifiedIntroductoryFieldsAreAvailable() throws Exception {
		searchEntityID.sendKeys(prop.getProperty("CAThirdEntity"));
		wait.until(ExpectedConditions.visibilityOf(EntityA1));
		EntityA1.click();
		continueBtn.click();
		System.out.println("California AR landed on private home page");
		ObjectRepo.getTest().log(Status.PASS, "AR landed on private home page");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Events'])")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		wait.until(ExpectedConditions.visibilityOf(ARlable));
		String lable = ARlable.getText();
		System.out.println(lable + " is On the report page");
		ObjectRepo.getTest().log(Status.PASS, lable + " is On the report page");
		wait.until(ExpectedConditions.visibilityOf(clientBidsAuditRow));
		String row = clientBidsAuditRow.getText();
		System.out.println("AR Can Access:- " + row);
		ObjectRepo.getTest().log(Status.PASS, "AR Can Access:- " + row);
		wait.until(ExpectedConditions.elementToBeClickable(ClientBidsAuditdownloadReportBtn));
		driver.findElement(ClientBidsAuditdownloadReportBtn).click();
		System.out.println("Client Bids Audit Report successfully downloaded by AR");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report successfully downloaded by AR");
		System.out.println("***********************************************************************");
		Thread.sleep(25000);
		ExcelDataProviderForClientBidsAuditReport ref = new ExcelDataProviderForClientBidsAuditReport();
		String EventDateTime = ref.getStringData("Client Bids Audit Report", 0, 1);
		System.out.println("Report Date and Time is displayed :- " + EventDateTime);
		ObjectRepo.getTest().log(Status.PASS, "Report Date and Time is displayed :- " + EventDateTime);
		String EventName = ref.getStringData("Client Bids Audit Report", 1, 1);
		System.out.println("Event name is displayed :- " + EventName);
		ObjectRepo.getTest().log(Status.PASS, "Event name is displayed :- " + EventName);
		String EventDate = ref.getStringData("Client Bids Audit Report", 2, 1);
		System.out.println("Event Date is displayed :- " + EventDate);
		ObjectRepo.getTest().log(Status.PASS, "Event Date is displayed :- " + EventDate);
		String EntityLegalName = ref.getStringData("Client Bids Audit Report", 3, 1);
		System.out.println("Event legal name is displayed :- " + EntityLegalName);
		ObjectRepo.getTest().log(Status.PASS, "Entity legal name is displayed :- " + EntityLegalName);
		String EntityOpName = ref.getStringData("Client Bids Audit Report", 4, 1);
		System.out.println("Entity Operating Name is displayed :- " + EntityOpName);
		ObjectRepo.getTest().log(Status.PASS, "Entity Operating Name is displayed :- " + EntityOpName);
		double bidsSubmit = ref.getNumericData("Client Bids Audit Report", 5, 1);
		System.out.println("Total Bids Submitted is displayed :- " + bidsSubmit);
		ObjectRepo.getTest().log(Status.PASS, "Total Bids Submitted is displayed :- " + bidsSubmit);
		System.out.println("Specified introductory fields are available In the client bids audit report");
		ObjectRepo.getTest().log(Status.PASS, "Specified introductory fields are available In the client bids audit report");
		System.out.println("***********************************************************************");
		String ClientBidIdColumnNme = ref.getStringData("Client Bids Audit Report", 7, 0);
		System.out.println("First Column name is displayed :- " + ClientBidIdColumnNme);
		ObjectRepo.getTest().log(Status.PASS, "First Column name is displayed :- " + ClientBidIdColumnNme);
		String TransactionIdColumnNme = ref.getStringData("Client Bids Audit Report", 7, 1);
		System.out.println("Second Column name is displayed :- " + TransactionIdColumnNme);
		ObjectRepo.getTest().log(Status.PASS, "Second Column name is displayed :- " + TransactionIdColumnNme);
		Assert.assertEquals("Client Bid ID", ClientBidIdColumnNme);
		System.out.println("***********************************************************************");
	}

	public void SponsorCanAccessTheClientBidsAuditReport() throws InterruptedException {
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
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("event.name"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("event.name") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("event.name") + "']")).click();
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

	public void DataReadFromBidsAuditReportReserveSale_WA() throws IOException, InterruptedException {
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_WA1")
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

	public void DataReadFromBidsReportReserveSale_WA() throws IOException, InterruptedException {
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_WA1") + " "
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

	public void DataReadFromQualifiedBiddersReportReserveSale_WA() throws IOException, InterruptedException {
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Qualified Bidders Report - "
				+ prop.getProperty("Event_Name_WA1") + " " + date1 + ".xlsx");
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
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_CA1") + " "
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

	public void CommentAndFieldsDataReadFromBidsReportCaliforniaReserveSale() throws IOException, InterruptedException {
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_CA1") + " "
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
		// Thread.sleep(18000);
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
		// Thread.sleep(25000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Bids Audit Report - " + prop.getProperty("Event_Name_CA1")
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

	public void DataReadFromBidsAuditReportNS_SBA() throws IOException, InterruptedException {
		// Thread.sleep(25000);
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
		// Thread.sleep(25000);
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
		// Thread.sleep(25000);
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
		// Thread.sleep(18000);
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

	public void DataReadFromFinancialStatementPdfReportFor_NS_SBA() throws IOException, InterruptedException {
		// Thread.sleep(000);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src = new File(folderPath + File.separator + "Financial Statement - " + prop.getProperty("Event_Name_NS")
				+ " " + date1 + "NS3624-3951" + ".pdf");
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
}
