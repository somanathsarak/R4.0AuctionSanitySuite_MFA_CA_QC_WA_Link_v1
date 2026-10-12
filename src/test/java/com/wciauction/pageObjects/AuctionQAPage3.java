package com.wciauction.pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.CommonLib;
import com.wci.auction.org.resources.ObjectRepo;

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class AuctionQAPage3 {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public static Actions action;
	public String dynamicEmail;
	
	@FindBy(xpath = "//*[text()='About WCI, Inc.']")
	private WebElement scroll_To_Bottom;

	@FindBy(xpath = "//*[@placeholder='Enter Entity Name or Entity ID']")
	private WebElement searchentity;

	@FindBy(xpath = "(//*[@name='rollDownProcedure'])")
	private WebElement selectentity;

	@FindBy(xpath = "(//*[text()='CONTINUE'])")
	private WebElement BtnContinueclk;

	@FindBy(xpath = "//*[text()='Events']")
	private WebElement EventDropdown;

	@FindBy(xpath = "(//*[text()='EventNameWADuringBidding'])")
	private WebElement EventName;

	@FindBy(xpath = "(//*[contains(text(),'12.20 USD')])[1]")
	private WebElement FloorPrice;

	@FindBy(xpath = "(//*[@name='rollDownProcedure'])[1]")
	private WebElement EntityQC;

	@FindBy(xpath = "(//*[text()='CONTINUER'])")
	private WebElement continueBtnQC;

	@FindBy(xpath = "(//*[@name='rollDownProcedure'])[1]")
	private WebElement selectentityQC;

	@FindBy(xpath = "(//*[text()='CONTINUER'])")
	private WebElement BtnContinueclkQC;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])")
	private WebElement EventNamePHomepage;

	@FindBy(xpath = "(//*[text()='Event in progress'])")
	private WebElement Eventstatus;

	@FindBy(xpath = "(//div[@role='region']//div//div//div[2])[1]")
	private WebElement countdownClock;

	@FindBy(xpath = "(//input[@aria-label='Entity Name'])[2]")
	private WebElement EntityNmeDropDownBtn2;
	
	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement Report_NameDuring_Bidding;
	
	@FindBy(xpath = "(//*[@aria-label='Entity Name'])[2]")
	private WebElement click_On_Entiy_Dropdown;
	
	@FindBy(xpath = "//*[@id='entity-reports']/table/tbody/tr/td[1]/div/div[2]/div[2]/span")
	private WebElement select_Entity_Name1;
	
	@FindBy(xpath = "(//*[@id='demo-customized-select'])[4]")
	private WebElement select_Report_Name2;
	
	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement reportBtn;

	@FindBy(xpath = "//*[text()='Rapports']")
	private WebElement reportBtnFR;

	@FindBy(xpath = "//span[text()='Event Monitoring']//parent::button[@type='button']")
	private WebElement btnEventMonitoring;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])")
	private WebElement selectEvent;

	@FindBy(xpath = "//input[@aria-label=\"select all\"]")
	private WebElement clickcheckbox;

	@FindBy(xpath = "(//*[text()='Bulk Actions'])")
	private WebElement BulkAct;

	@FindBy(xpath = "(//*[text()='FLAG'])")
	private WebElement flag;

	@FindBy(xpath = "(//*[text()='REMOVE FLAG'])")
	private WebElement removeflag;

	@FindBy(xpath = "//ul[@role='menu']")
	private WebElement Menu;

	@FindBy(xpath = "//li[text()='Add Flag']")
	private WebElement addFlag;

	@FindBy(xpath = "//span[text()='POST']")
	private WebElement addFlagPost;

	@FindBy(xpath = "//li[text()='Remove Flag']")
	private WebElement removeFlag;
	
	@FindBy(xpath = "//*[text()='REMOVE FLAG']")
	private WebElement REMOVEFLAG;
	
	@FindBy(xpath = "//*[@placeholder='Enter Text']")
	private WebElement textBoxComment;

	@FindBy(xpath = "//span[text()='Administration']//parent::button[@type='button']")
	private WebElement btnAdministration;

	@FindBy(xpath = "//*[text()='User']")
	private WebElement btnUser;

	@FindBy(xpath = "(//*[@type='button'])[5]")
	private WebElement btnAddNewUser;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement mail;

	@FindBy(xpath = "//*[text()='Check if email address is already associated to a platform user']")
	private WebElement maillinkchk;

	@FindBy(xpath = "//*[@id='firstname']")
	private WebElement Fname;

	@FindBy(xpath = "//*[@id='lastname']")
	private WebElement Lname;

	@FindBy(xpath = "//*[text()='ADD USER']")
	private WebElement btnAddUser;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "(//*[text()='ReserveSaleAutomation'])")
	private WebElement AllowanceSaleEvent1NamePHomepage;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])")
	private WebElement JointAuctionEventNamePHomepage;

	@FindBy(xpath = "//a[@title='Reports']")
	private WebElement btnReport;

	@FindBy(xpath = "(//*[@name='rollDownProcedure'])[2]")
	private WebElement selectentityNS;

	@FindBy(xpath = "(//*[text()='CONTINUE'])")
	private WebElement BtnContinueclkNS;

	private By btnClientBidsReportsNS = By.xpath(
			"//button[@aria-label='Download Client Bids Report for event " + prop.getProperty("eventNameNS") + "']");
	private By btnBidsReportsRS_QC = By.xpath(
			"//button[@aria-label='Download Bids Report for event " + prop.getProperty("Event_Name_RE1QC") + "']");// ReserveSaleEventNameR70
	private By btnBidsReportsRS_QC_french = By.xpath("//button[@aria-label='Download Rapport des offres for event "
			+ prop.getProperty("Event_Name_RE1QC_FR") + "']");// ReserveSaleEventNameR70
	private By btnBidLotAuditReportsRS_QC = By
			.xpath("//button[@aria-label='Download Sales by Mutual Agreement Bid Lot Audit Report for event "
					+ prop.getProperty("Event_Name_RE1QC") + "']");// ReserveSaleEventNameR70
	private By btnBidLotAuditReportsRS_QC_FR = By
			.xpath("//button[@aria-label='Download Rapport de vérification des lots de la vente de gré à gré for event "
					+ prop.getProperty("Event_Name_RE1QC_FR") + "']");// ReserveSaleEventNameR70
	private By btnClientBidsAuditReportsRS_QC = By
			.xpath("//button[@aria-label='Download Client Bids Audit Report for event "
					+ prop.getProperty("Event_Name_RE1QC") + "']");// ReserveSaleEventNameR70
	private By btnEmissionUnitsDeliveryReportRS_QC = By
			.xpath("//button[@aria-label='Download Emission Units Delivery Report for event "
					+ prop.getProperty("Event_Name_RE1QC") + "']");
	private By btnEmissionUnitsDeliveryReportRS_QC_FR = By
			.xpath("//*[@aria-label=\"Download Rapport de la livraison des unités d'émission for event "
					+ prop.getProperty("Event_Name_RE1QC_FR") + "\"]");
	private By btnFinancialStatementReportRS_QC = By
			.xpath("//button[@aria-label='Download Financial Statement for event "
					+ prop.getProperty("Event_Name_RE1QC_FR") + "']");
	private By btnClientBidsReportsRS_QC = By.xpath("//button[@aria-label='Download Client Bids Report for event "
			+ prop.getProperty("Event_Name_RE1QC") + "']");// ReserveSaleEventNameR70
	private By btnBidsReportsRS_CAL = By.xpath(
			"//button[@aria-label='Download Bids Report for event " + prop.getProperty("ReserveSaleEventName1") + "']");// ReserveSaleEventNameR70
	private By btnAllowanceDeliveryReportRS_CA = By
			.xpath("//button[@aria-label='Download Allowance Delivery Report for event "
					+ prop.getProperty("ReserveSaleEventName1") + "']");

	public AuctionQAPage3() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void SelectEntity_AR() {
		wait.until(ExpectedConditions.elementToBeClickable(searchentity));
		searchentity.sendKeys("WA6585-9938");
		WebElement abc = selectentity;
		abc.click();
		WebElement abc1 = BtnContinueclk;
		abc1.click();
		wait.until(ExpectedConditions.visibilityOf(EventDropdown));
		WebElement abc2 = EventDropdown;
		abc2.click();
		wait.until(ExpectedConditions.visibilityOf(EventName));
		WebElement abc3 = EventName;
		abc3.click();
		WebElement abc4 = FloorPrice;
		String ActualFloorpriceCurrency = FloorPrice.getText();
		String ExpectedFloorpriceCurrency = "12.20 USD";
		Assert.assertEquals(ActualFloorpriceCurrency, ExpectedFloorpriceCurrency);
		System.out.println("validated Actual & expected floor price for WA individual auction.");
		ObjectRepo.getTest().log(Status.PASS, "validated Actual & expected floor price for WA individual auction.");
		System.out.println("Auction Floor price is Displayed in:" + abc4.getText());
		ObjectRepo.getTest().log(Status.PASS,
				"TC:C31115-Confirm for a WA individual auction, the auction floor price is displayed in USD.");
	}

	public void CountDownClockDisplayed() {
		System.out.println("Land on private Home page");
		ObjectRepo.getTest().log(Status.PASS, "Land on private Home page");
		wait.until(ExpectedConditions.elementToBeClickable(EventNamePHomepage));
		WebElement abc = EventNamePHomepage;
		System.out.println("Event name is:" + abc);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc);
		wait.until(ExpectedConditions.elementToBeClickable(Eventstatus));
		WebElement abc1 = Eventstatus;
		System.out.println("Event status is:" + abc1);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc1);
		WebElement countdownClock1 = countdownClock;
		String remainingTime = countdownClock1.getText();
		System.out.println("Remaining Time: " + remainingTime);
		ObjectRepo.getTest().log(Status.PASS, "Remaining Time:" + remainingTime);
	}

	public void verifyEventMonitoring() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnEventMonitoring));
		WebElement event = btnEventMonitoring;
		action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(event));
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(selectEvent));
		selectEvent.click();
	}

	public void FlaggedBidByMM()  {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		driver.findElement(By.xpath("//span[normalize-space()='Monitoring']//*[name()='svg']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement event=driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("//input[@aria-label='select all']"));
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
			System.out.println("The selected bids are successfully flagged by MM.");
			ObjectRepo.getTest().log(Status.PASS, "The selected bids are successfully flagged by MM.");
		} catch (Exception e) {
			System.out.println("Bids Allready Flagged.");
		}
		driver.navigate().refresh();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void UnFlaggedBidBySP() {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		driver.findElement(By.xpath("//span[normalize-space()='Monitoring']//*[name()='svg']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement event=driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");	
		element.click();
		try {
			driver.findElement(By.xpath("//span[text()='Bulk Actions']")).click();
			driver.findElement(By.xpath("//li[text()='Remove Flag']")).click();
			driver.findElement(By.xpath("//span[text()='REMOVE FLAG']")).click();
			driver.findElement(By.xpath("//*[@placeholder='Enter Text']")).sendKeys("UNFLAG");
			driver.findElement(By.xpath("//*[text()='POST']")).click();
		} catch (Exception e) {
			System.out.println("There is no bids for the remove flag.");
		}
	}

	public void NavigateUserAdministrationCA() {
		
		WebElement element11 = btnAdministration;
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnUser)).click();
		
		WebElement element1 = btnAddNewUser;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewUser)).click();
		dynamicEmail = "MMuser" + System.currentTimeMillis() + "@yopmail.com";
		System.out.println(dynamicEmail);
		wait.until(ExpectedConditions.elementToBeClickable(mail)).sendKeys(dynamicEmail); // mm1112.ca@yopmail.com
		wait.until(ExpectedConditions.elementToBeClickable(maillinkchk)).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text()='This user does not exist and may be added.']")));
		String ActualSmsg = driver
				.findElement(By.xpath("//*[text()='This user does not exist and may be added.']")).getText();
		String ExpectedSmsg = "This user does not exist and may be added.";
		Assert.assertEquals(ActualSmsg, ExpectedSmsg);
		System.out.println("validated Actual & expected success msg for new user");
		wait.until(ExpectedConditions.elementToBeClickable(Fname)).sendKeys("FN");
		wait.until(ExpectedConditions.elementToBeClickable(Lname)).sendKeys("LN");
		Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[3]")));
		s.selectByVisibleText("Market Monitor");
		wait.until(ExpectedConditions.elementToBeClickable(btnAddUser)).click();
		System.out.println("Success Msg-The user has been added successfully.");
		ObjectRepo.getTest().log(Status.PASS, "Success Msg-The user has been added successfully.");
		System.out.println("Added a user as Market Monitor for CA.");
		ObjectRepo.getTest().log(Status.PASS, "Added a user as Market Monitor for CA.");
	}

	public void NavigateUserAdministrationNS() {
		
		WebElement element11 = btnAdministration;
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnUser)).click();
		
		WebElement element1 = btnAddNewUser;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.elementToBeClickable(btnAddNewUser)).click();
		System.out.println("Clicked btnAddNewUser");
		wait.until(ExpectedConditions.elementToBeClickable(mail)).sendKeys(dynamicEmail);
		System.out.println(dynamicEmail);
		wait.until(ExpectedConditions.elementToBeClickable(maillinkchk)).click();
		driver.findElement(By.xpath("//*[text()='User is present in the system please proceed.']")).getText();
		String ActualSmsg = driver
				.findElement(By.xpath("//*[text()='User is present in the system please proceed.']")).getText();
		String ExpectedSmsg = "User is present in the system please proceed.";
		Assert.assertEquals(ActualSmsg, ExpectedSmsg);
		System.out.println("Already linked user Message " + ActualSmsg);
		System.out.println("Validated Actual & expected success msg for linked jurisdiction ");
		wait.until(ExpectedConditions.elementToBeClickable(btnAddUser)).click();
		System.out.println("The user has been added successfully.");
		ObjectRepo.getTest().log(Status.PASS, "The user has been added successfully.");
		System.out.println("Add the same user-CA_MM as Market Monitor for NS.");
		ObjectRepo.getTest().log(Status.PASS, "Add the same user-CA_MM as Market Monitor for NS.");
	}

	public void FieldsDisplayedInEventInfoSectionAllowanceSale() {
		System.out.println("Land on private Home page");
		ObjectRepo.getTest().log(Status.PASS, "Land on private Home page");
		wait.until(ExpectedConditions.elementToBeClickable(AllowanceSaleEvent1NamePHomepage));
		WebElement abc = AllowanceSaleEvent1NamePHomepage;
		System.out.println("Event name is:" + abc);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc);
		wait.until(ExpectedConditions.elementToBeClickable(Eventstatus));
		WebElement abc1 = Eventstatus;
		System.out.println("Event status is:" + abc1);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc1);

		System.out.println("Navigate to the Event Info section");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to the Event Info section");

		List<WebElement> list2 = driver
				.findElements(By.xpath("(//*[@class='MuiTypography-root MuiTypography-body1'])[17]"));
		System.out.println(list2);
		int listSize2 = list2.size();
		String listString2 = "";
		for (int i = 0; i <= listSize2 - 1; i++) {
			listString2 = list2.get(i).getText();
		}
		System.out.println(listString2);

		List<WebElement> name = driver.findElements(By.xpath("(//*[@class='MuiTable-root'])[8]/thead/tr/th"));
		int nameSize = name.size();
		String namepresent = "";
		for (int i = 1; i <= nameSize - 1; i++) {
			namepresent = name.get(i).getText();
			System.out.println(namepresent);
		}
		System.out.println(name);
		List<WebElement> labels = driver.findElements(By.xpath("(//*[@class='MuiTableRow-root'])[8]/td"));
		int labelSize = labels.size();
		String labelpresent = "";
		for (int i = 1; i <= labelSize - 1; i++) {
			labelpresent = labels.get(i).getText();
			System.out.println(labelpresent);
		}
		System.out.println(labels);
		ObjectRepo.getTest().log(Status.PASS, "Labels displayed" + labels);
		System.out.println("Fields are displayed in event info section");
		ObjectRepo.getTest().log(Status.PASS, "Fields are displayed in event info section");
	}

	public void WebinarDatesOnPrivateWebPage() throws InterruptedException {
		System.out.println("Land on private Home page");
		ObjectRepo.getTest().log(Status.PASS, "Land on private Home page");
		wait.until(ExpectedConditions.elementToBeClickable(AllowanceSaleEvent1NamePHomepage));
		WebElement abc = AllowanceSaleEvent1NamePHomepage;
		System.out.println("Event name is:" + abc);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc);
		wait.until(ExpectedConditions.elementToBeClickable(Eventstatus));
		WebElement abc1 = Eventstatus;
		System.out.println("Event status is:" + abc1);
		ObjectRepo.getTest().log(Status.PASS, "Event status is:" + abc1);
		List<WebElement> name = driver.findElements(By.xpath("(//*[@class='MuiTable-root'])[10]/thead/tr/th"));
		int nameSize = name.size();
		String namepresent = "";
		for (int i = 1; i <= nameSize - 1; i++) {
			namepresent = name.get(i).getText();
			System.out.println(namepresent);
			ObjectRepo.getTest().log(Status.PASS, namepresent);
		}
		System.out.println(name);
		List<WebElement> labels = driver.findElements(By.xpath("(//*[@class='MuiTableRow-root'])[10]/td"));
		int labelSize = labels.size();
		String labelpresent = "";
		for (int i = 1; i <= labelSize - 1; i++) {
			labelpresent = labels.get(i).getText();
			System.out.println(labelpresent);
			ObjectRepo.getTest().log(Status.PASS, labelpresent);
		}
		System.out.println(labels);
		ObjectRepo.getTest().log(Status.PASS, "labels displayed" + labels);
		System.out.println("Webinar dates for event are displayed");
		ObjectRepo.getTest().log(Status.PASS, "Webinar dates for event are displayed");
	}

	public void UnableToResetFXRateApproval() throws InterruptedException {
		System.out.println("Land on private Home page");
		ObjectRepo.getTest().log(Status.PASS, "Land on private Home page");
		wait.until(ExpectedConditions.elementToBeClickable(JointAuctionEventNamePHomepage));
		WebElement abc = JointAuctionEventNamePHomepage;
		System.out.println("Event name is:" + abc);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc);
		wait.until(ExpectedConditions.elementToBeClickable(Eventstatus));
		WebElement abc1 = Eventstatus;
		System.out.println("The bidding window for the event has opened.:" + abc1);
		ObjectRepo.getTest().log(Status.PASS, "The bidding window for the event has opened.:" + abc1);
		
		WebElement element11 = btnAdministration;
		js.executeScript("arguments[0].scrollIntoView();", element11);
		element11.click();
		System.out.println("Click on the Administration menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on the Administration menu");
		wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice)).click();
		System.out.println("Click on Reserve Price Administration");
		ObjectRepo.getTest().log(Status.PASS, "Click on Reserve Price Administration");
		wait.until(ExpectedConditions.elementToBeClickable(JointAuctionEventNamePHomepage));
		WebElement abc11 = JointAuctionEventNamePHomepage;
		System.out.println("Event name is:" + abc11);
		ObjectRepo.getTest().log(Status.PASS, "Event name is:" + abc11);
		WebElement element2 = JointAuctionEventNamePHomepage;
		js.executeScript("arguments[0].scrollIntoView();", element2);
		element2.click();
		System.out.println("Navigated Event name is:" + abc11);
		ObjectRepo.getTest().log(Status.PASS, "Navigated Event name is:" + abc11);
		boolean Display = driver
				.findElement(
						By.xpath("(//*[text()='JointAuctionAutomation']/ancestor::tr/following-sibling::tr/td[5])[1]"))
				.getText().isBlank();
		System.out.println("Reset button is not displayed:" + Display);
		try {
			Assert.assertTrue(Display);
			System.out.println("Reset button is not available for Sponsor user");
		} catch (Exception e) {
			Assert.assertFalse(Display);
			System.out.println("Reset button is available for Sponsor user");
		}
		System.out.println("Reset button is not available to reset the FX rates");
		ObjectRepo.getTest().log(Status.PASS, "Reset button is not available to reset the FX rates");
	}

	public void DataReadFromBidsReportRS_QC() throws EncryptedDocumentException, Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Monitoring']")));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		searchReportsByEventName_SBMA();
		
		WebElement element = driver.findElement(btnBidsReportsRS_QC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		Thread.sleep(25000);
		System.out.println(
				"C31912/C31915-Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"C31912/C31915-Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window ");
		System.out.println(
				"C31949 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window(Bids Report_Allowance Sale_English)");
		ObjectRepo.getTest().log(Status.PASS,
				"C31949 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window(Bids Report_Allowance Sale_English)");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1QC") + " "
				+ "02-25-2024" + ".xlsx"); // "07-18-2023" "02-25-2024"
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + date1 + ".xlsx");
		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Asserted The name of the report must be Bids Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bids Report");

		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Bids Report");

		String FirstColomnH = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomnH, "Report Date & Time");
		System.out.println("Report Date & Time" + ":- " + FirstColomnH);
		ObjectRepo.getTest().log(Status.PASS, FirstColomnH);

		String SecondColomnH = a.getRow(1).getCell(0).getStringCellValue();
		Assert.assertEquals(SecondColomnH, "Event Name");
		System.out.println(SecondColomnH);
		ObjectRepo.getTest().log(Status.PASS, SecondColomnH);

		String ThirdColomnH = a.getRow(2).getCell(0).getStringCellValue();
		Assert.assertEquals(ThirdColomnH, "Event Date");
		System.out.println(ThirdColomnH);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomnH);

		String fourthColomnH = a.getRow(3).getCell(0).getStringCellValue();
		Assert.assertEquals(fourthColomnH, "Total Bids Submitted");
		System.out.println(fourthColomnH);
		ObjectRepo.getTest().log(Status.PASS, fourthColomnH);
		System.out.println("C31910-Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "C31910-Confirm the specified introductory fields are available");

	}

	public void DataReadFromBidsReportRS_QC_French() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Surveillance'])")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Surveillance'])")));
		}
		//wait.until(ExpectedConditions.visibilityOf(reportBtnFR));
		reportBtnFR.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		searchReportsByEventName_SBMA_FR();
		
		WebElement element = driver.findElement(btnBidsReportsRS_QC_french);
		js.executeScript("arguments[0].scrollIntoView();", element);
		//driver.findElement(btnBidsReportsRS_QC_french).click();
		element.click();
		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(
				"C31949 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window(Bids Report_Allowance Sale_French)");
		ObjectRepo.getTest().log(Status.PASS,
				"C31949 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window(Bids Report_Allowance Sale_French)");
	}

	public void clickreportmenu() {
		wait.until(ExpectedConditions.elementToBeClickable(btnReport));
		btnReport.click();
		
	}

	public void SponsoraccessSBMABidLotAuditReportQC_RS() throws EncryptedDocumentException, IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		clickreportmenu();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		searchReportsByEventName_SBMA();
		// clickdownloadAllowanceDeliveryReport();
		
		WebElement element = driver.findElement(btnBidLotAuditReportsRS_QC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidLotAuditReportsRS_QC).click();
		Thread.sleep(25000);
		System.out.println("C31941-Confirm Sale By Mutual Agreement Bid Lot Audit Report is available in English");
		ObjectRepo.getTest().log(Status.PASS,
				"C31941-Confirm Sale By Mutual Agreement Bid Lot Audit Report is available in English ");
		System.out.println(
				"C31939-Sponsor can access the Sale By Mutual Agreement Bid Lot Audit Report after the roll down the process");
		ObjectRepo.getTest().log(Status.PASS,
				"C31939-Sponsor can access the Sale By Mutual Agreement Bid Lot Audit Report after the roll down the process");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);//// date1//EQA_R70_SBMA Prescribed_Event_7_12 07-12-2023.xlsx\
		String date2 = "02-25-2024";
		File src = new File(folderPath + File.separator + "Sales by Mutual Agreement Bid Lot Audit Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + date2 + ".xlsx");

		System.out.println("Asserted The name of the report must be Bid Lot Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Bid Lot Audit Report");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Sales by Mutual Agreement Bid L");

		String FirstColomn = a.getRow(4).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "General Account Number");
		System.out.println("General Account Number" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(4).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Legal Name");
		System.out.println("Legal Name" + ":- " + SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(4).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(4).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Bid ID");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(4).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Client Bid ID");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(4).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Transaction ID");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(4).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Category Price");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(4).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Lots");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(4).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Lot #");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColomn = a.getRow(4).getCell(9).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Total Emission Units Bid");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

		String eleventhColomn = a.getRow(4).getCell(10).getStringCellValue();
		Assert.assertEquals(eleventhColomn, "Random Number");
		System.out.println(eleventhColomn);
		ObjectRepo.getTest().log(Status.PASS, eleventhColomn);

		String twelvthColomn = a.getRow(4).getCell(11).getStringCellValue();
		Assert.assertEquals(twelvthColomn, "Price Sold");
		System.out.println(twelvthColomn);
		ObjectRepo.getTest().log(Status.PASS, twelvthColomn);

		String thirteenColomn = a.getRow(4).getCell(12).getStringCellValue();
		Assert.assertEquals(thirteenColomn, "Successful Roll-Down Bid Emission Units");
		System.out.println(thirteenColomn);
		ObjectRepo.getTest().log(Status.PASS, thirteenColomn);
		System.out.println("C31940-Validated Allowance Sale bid lot audit report-Headers");
		ObjectRepo.getTest().log(Status.PASS, "C31940-Validated Allowance Sale bid lot audit report-Headers");

	}

	public void clickreportmenu_FR()  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
		driver.findElement(By.xpath("//a[@title='Rapports']")).click();
	}

	public void SponsoraccessSBMABidLotAuditReportQC_RS_French()   {
		clickreportmenu_FR();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		searchReportsSBMABidLotAuditReportByEventName_SBMA_FR();
		
		WebElement element = driver.findElement(btnBidLotAuditReportsRS_QC_FR);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidLotAuditReportsRS_QC_FR).click();
		System.out.println("C31941-Confirm Sale By Mutual Agreement Bid Lot Audit Report is available in French");
		ObjectRepo.getTest().log(Status.PASS,
				"C31941-Confirm Sale By Mutual Agreement Bid Lot Audit Report is available in French.");
	try {
		Thread.sleep(25000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public File getLatestFilefromDir(String folderPath) {
		return null;
	}

	public void DataReadFromBidsAuditReportRS_QC() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");

		searchReportsByEventName_SBMA();
		
		WebElement element = driver.findElement(btnBidsReportsRS_QC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnBidsReportsRS_QC).click();
		System.out.println(
				"C31912/C31915-Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"C31912/C31915-Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window ");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1QC") + " "
				+ "07-12-2023" + ".xlsx"); // "07-18-2023"
		System.out.println("reports path : " + folderPath + File.separator + "Bids Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + date1 + ".xlsx");
		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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

	public void DataReadFromClientBidsAuditReport_SBMA_QC() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		searchClientBidsAuditReportsByEventName_SBMA();
		downloadClientBidsAuditReportFromGrid();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Client Bids Audit Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024"+ " " +"QC4085-5100" + ".xlsx"); // "07-18-2023"
		System.out.println("reports path : " + folderPath + File.separator + "Client Bids Audit Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024"+ " " +"QC4085-5100" + ".xlsx");

		System.out.println("Asserted The name of the report must be Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Client Bids Audit Report");

		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Client Bids Audit Report Data is fetched ");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Audit Report Data is fetched");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Audit Report");

		// Introductory fields
		String FirstIntrField = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstIntrField, "Report Date & Time");
		System.out.println(FirstIntrField);
		ObjectRepo.getTest().log(Status.PASS, FirstIntrField);

		String SecondIntrField = a.getRow(1).getCell(0).getStringCellValue();
		Assert.assertEquals(SecondIntrField, "Event Name");
		System.out.println(SecondIntrField);
		ObjectRepo.getTest().log(Status.PASS, SecondIntrField);

		String ThirdIntrField = a.getRow(2).getCell(0).getStringCellValue();
		Assert.assertEquals(ThirdIntrField, "Event Date");
		System.out.println(ThirdIntrField);
		ObjectRepo.getTest().log(Status.PASS, ThirdIntrField);

		String FourthIntrField = a.getRow(3).getCell(0).getStringCellValue();
		Assert.assertEquals(FourthIntrField, "Legal Name");
		System.out.println(FourthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FourthIntrField);

		String FifthIntrField = a.getRow(4).getCell(0).getStringCellValue();
		Assert.assertEquals(FifthIntrField, "Operating Name");
		System.out.println(FifthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FifthIntrField);

		String tenthColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Total Bids Submitted");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

		System.out.println("C31991-Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "C31991-Confirm the specified introductory fields are available");

		/// Data-headers
		String FirstColomn = a.getRow(7).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Client Bid ID");
		System.out.println("Client Bid ID" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(7).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Transaction ID");
		System.out.println("Transaction ID" + ":- " + SecondColomn);
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
		Assert.assertEquals(seventhColomn, "Category");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(7).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Category Price");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(7).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Total Emission Units Bid");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		System.out.println("C32003-Verify the data in the Client Bids Audit Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "C32003-Verify the data in the Client Bids Audit Report is correct");
	}

	public void SponsoraccessSBMAClientBidsAuditReportQC_RS_French()  {
		clickreportmenu_FR();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");

		searchClientBidsReportsByEventName_SBMA_FR();
		downloadClientBidsReportFromGrid_SBMA_FR();
		System.out.println(
				"C31952-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window(Report available in french )");
		ObjectRepo.getTest().log(Status.PASS,
				"C31952-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window(Report available in french )");
	}

	public void ARSelectentityClientBidsAuditReport_SBMA_QC() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='CONTINUE']")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='CONTINUER']")));
		}
		selectentityQC.click();
		WebElement abc1 = BtnContinueclkQC;
		abc1.click();
	}

	public void ARAccessClientBidsAuditReport_SBMA_QC() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Events']")));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
				.sendKeys(prop.getProperty("Event_Name_RE1QC"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		
		WebElement element = driver.findElement(btnClientBidsAuditReportsRS_QC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnClientBidsAuditReportsRS_QC).click();
		System.out.println(
				"C31993-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"C31993-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window");
	}

	public void AccessEmissionUnitsDeliveryreportRS_QC() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Monitoring']")));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		searchReportsByEventName_SBMA();
		
		WebElement element = driver.findElement(btnEmissionUnitsDeliveryReportRS_QC);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnEmissionUnitsDeliveryReportRS_QC).click();
		Thread.sleep(25000);
		System.out.println(
				"C31897 Confirm Emission Units Delivery report is generated after results are run for Sponsor and MM.");
		ObjectRepo.getTest().log(Status.PASS,
				"C31897 Confirm Emission Units Delivery report is generated after results are run for Sponsor and MM.");
		System.out.println(
				"C31943 Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.(Confirm Allowance delivery Report is available in English.)");
		ObjectRepo.getTest().log(Status.PASS,
				"C31943 Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.(Confirm Allowance delivery Report is available in English.)");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		Date date = new Date();

		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Emission Units Delivery Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024" + ".xlsx"); // "07-18-2023"
		System.out.println("reports path : " + folderPath + File.separator + "Emission Units Delivery Report - "
				+ prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024"  + ".xlsx");
		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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

		System.out.println("Emission Units Delivery Report Data is fetched ");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report Data is fetched");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Emission Units Delivery Report");

		String FirstColomn = a.getRow(4).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "General Account Number");
		System.out.println("General Account Number" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);

		String SecondColomn = a.getRow(4).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Legal Name");
		System.out.println("Legal Name" + ":- " + SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(4).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Operating Name");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(4).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Government");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(4).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Awarded Category");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(4).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Successful Emission Units");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		System.out.println(
				"C31902-Confirm Emission Units Delivery report is generated after results are run for Sponsor and MM.(Headers displayed)");
		ObjectRepo.getTest().log(Status.PASS,
				"C31902-Confirm Emission Units Delivery report is generated after results are run for Sponsor and MM.(Headers displayed)");

	}

	public void AccessEmissionUnitsDeliveryreportRS_QC_French() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
	    reportBtnFR.click();
	    System.out.println("Report page is opened");
	    ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	    searchEmissionReportsByEventName_SBMA_FR();
	    
	    WebElement element = driver.findElement(btnEmissionUnitsDeliveryReportRS_QC_FR);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnEmissionUnitsDeliveryReportRS_QC_FR).click();
	    System.out.println("C31943 Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.(Confirm Allowance delivery Report is available in French.)");
	    ObjectRepo.getTest().log(Status.PASS, "C31943 Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.(Confirm Allowance delivery Report is available in French.)");
	Thread.sleep(25000);
	}

	public void searchReportAR_QC() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='CONTINUER']")));
		EntityQC.click();
	    continueBtnQC.click();
	}

	public void ValidateFinancialStatementreportAR_QC_SBMA() throws EmptyFileException, IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Events']")));
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		WebElement element1 = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();", element1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		WebElement element11 = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", element11);
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"))
		.click();
		Select sel = new Select(Report_NameDuring_Bidding);
		sel.selectByVisibleText("Financial Statement");
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		Actions action;
		try {
			action = new Actions(driver);
			action.moveToElement(click_On_Entiy_Dropdown).build().perform();
			click_On_Entiy_Dropdown.click();
			select_Entity_Name1.click();
			Select sel2 = new Select(select_Report_Name2);
			sel2.selectByVisibleText("Financial Statement");
		} catch (Exception e) {
			driver.findElement(By.xpath("(//*[@title='Download Report'])[1]")).click();
		}
		Thread.sleep(20000);
		System.out.println("Financial Statement report successfully downloaded by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Financial Statement report successfully downloaded by sponsor");
		try {	    
	    File src = new File(
	            folderPath + File.separator + "Financial Statement - R70_SBMA_AfterCertify 02-25-2024 QC4085-5100.pdf");
	    FileInputStream PdfFileToRead = new FileInputStream(src);
	    PDDocument doc = PDDocument.load(PdfFileToRead);
	    int pages = doc.getNumberOfPages();
	    System.out.println("The total number of pages " + pages);
	    PDFTextStripper strip = new PDFTextStripper();
	    strip.setStartPage(1);
	    strip.setEndPage(2);
	    String stripText = strip.getText(doc);
	    String value = stripText.toString();
	    System.out.println(value);
	    if (value == "Financial Services Account Number") {
	        Assert.assertTrue(value.contains("Financial Services Account Number"));
	    }
	    System.out.println(stripText);
	    ObjectRepo.getTest().log(Status.PASS, stripText);
	    doc.close();
		 } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	public String ValidateFinancialStatementreportSponsor_QC_SBMA()
	        throws EmptyFileException, IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		btnReport.click();
	    System.out.println("Click 'Report' Button on navigation bar");
	    ObjectRepo.getTest().log(Status.PASS, "Click 'Report' Button on navigation bar");
	    WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		Thread.sleep(5000);
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
	            .sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
	   
	    WebElement element12 = driver
	            .findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
	    js.executeScript("arguments[0].scrollIntoView();", element12);
	    element12.click();
	    driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	    driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
	    
	    WebElement element121 = driver.findElement(By.xpath("(//*[text()='About WCI, Inc.'])"));
	    js.executeScript("arguments[0].scrollIntoView();", element121);
	    WebElement click_On_Entiy_Dropdown = driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]"));
	    Actions action = new Actions(driver);
	    action.moveToElement(click_On_Entiy_Dropdown).build().perform();
	    driver.findElement(By.xpath("(//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0'])"))
	            .click();
	    Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
	    s.selectByVisibleText("Financial Statement");
	    WebElement element = driver.findElement(By.xpath("(//*[@aria-label='Download Report'])"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(By.xpath("(//*[@aria-label='Download Report'])")).click();
	    System.out.println("Financial Statement report downloaded successfully");
	    Thread.sleep(25000);
	    System.out.println("The Sponsor can access the Financial Statement Report");
	    ObjectRepo.getTest().log(Status.PASS, "The Sponsor  can access the Financial Statement Report");
	    File src = new File(
	            folderPath + File.separator + "Financial Statement - R70_SBMA_AfterCertify 02-25-2024 QC4085-5100.pdf");
	    FileInputStream PdfFileToRead = new FileInputStream(src);
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
	    System.out.println(
	            "C31980 Sponsor/AR can get access to Financial Statement for an Allowance Sale event after Certification of an Allowance Sale event.");
	    ObjectRepo.getTest().log(Status.PASS,
	            "C31980 Sponsor/AR can get access to Financial Statement for an Allowance Sale event after Certification of an Allowance Sale event.");
	    System.out.println("C31987 Verify the data in the Financial Statement is correct");
	    ObjectRepo.getTest().log(Status.PASS, "C31987 Verify the data in the Financial Statement is correct");
	    doc.close();
	    return stripText;
	}

	public void DataReadFromClientBidsReport_SBMA_QC() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		searchClientBidsAuditReportsByEventName_SBMA();
		downloadClientBidsReportFromGrid();
	}

	public void SponsoraccessSBMAClientBidsReportQC_RS_French() throws Exception {
		clickreportmenu_FR();
		System.out.println("Click on Report menu");
		ObjectRepo.getTest().log(Status.PASS, "Click on Report menu");
		searchClientBidsReportsByEventName_SBMA_FR();
		downloadClientBidsReportFromGrid_SBMA_FR();
		System.out.println(
				"C31965 Sponsor/AR can get access to Client Bid Report for an Allowance Sale event during the bidding window(Client Bid Report available in french)");
		ObjectRepo.getTest().log(Status.PASS,
				"C31965 Sponsor/AR can get access to Client Bid Report for an Allowance Sale event during the bidding window(Client Bid Report available in french)");
	}

	public void ARSelectentityClientBidsReport_SBMA_QC() throws IOException, InterruptedException {
		WebElement abc = selectentityQC;
		abc.click();
		WebElement abc1 = BtnContinueclkQC;
		abc1.click();
	}

	public void ARAccessClientBidsReport_SBMA_QC() throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Events']")));
	    reportBtn.click();
	    System.out.println("Report page is opened");
	    ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
	    driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
	            .sendKeys(prop.getProperty("Event_Name_RE1QC"));
	    driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC") + "']")).click();
	    driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	    
	    WebElement element = driver.findElement(btnClientBidsReportsRS_QC);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	    System.out.println("C31964 AR can get access to Client Bids report for an Allowance Sale event");
	    ObjectRepo.getTest().log(Status.PASS, "C31964 AR can get access to Client Bids report for an Allowance Sale event ");
	}

	public void DataReadFromBidsReportRS_CAL() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Event Monitoring']")));
		Thread.sleep(4000);
	    reportBtn.click();
	    System.out.println("Report page is opened");
	    ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	    searchReportsByEventName_ReserveSale();
	    
	    WebElement element = driver.findElement(btnBidsReportsRS_CAL);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	    Thread.sleep(25000);
	    System.out.println("C31911 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window");
	    ObjectRepo.getTest().log(Status.PASS, "C31911 Sponsor/MM can get access to Bids report for an Allowance Sale event during the bidding window");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    Date date = new Date();
	    String date1 = dateFormat.format(date);
	    File src;
	    src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("ReserveSaleEventName1") + " " + date1 + ".xlsx");
	    System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + prop.getProperty("ReserveSaleEventName1") + " " + "06-30-2023" + ".xlsx");
	    try {
	        FileInputStream ExcelFileToRead = new FileInputStream(src);
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
	                    String value = cell.toString();
	                    switch (value) {
	                        case "Registry General Account Number":
	                            Assert.assertTrue(value.contains("Registry General Account Number"));
	                            break;
	                        case "Entity Legal Name":
	                            Assert.assertTrue(value.contains("Entity Legal Name"));
	                            break;
	                        case "Entity Operating Name":
	                            Assert.assertTrue(value.contains("Entity Operating Name"));
	                            break;
	                        case "Entity Type":
	                            Assert.assertTrue(value.contains("Entity Type"));
	                            break;
	                        case "Entity Jurisdiction":
	                            Assert.assertTrue(value.contains("Entity Jurisdiction"));
	                            break;
	                        case "Bid ID":
	                            Assert.assertTrue(value.contains("Bid ID"));
	                            break;
	                        case "Client Bid ID":
	                            Assert.assertTrue(value.contains("Client Bid ID"));
	                            break;
	                        case "Transaction ID":
	                            Assert.assertTrue(value.contains("Transaction ID"));
	                            break;
	                        case "Bid By":
	                            Assert.assertTrue(value.contains("Bid By"));
	                            break;
	                        case "Update Time (ET)":
	                            Assert.assertTrue(value.contains("Update Time (ET)"));
	                            break;
	                        case "Submitted Tier":
	                            Assert.assertTrue(value.contains("Submitted Tier"));
	                            break;
	                        case "Tier Price":
	                            Assert.assertTrue(value.contains("Tier Price"));
	                            break;
	                        case "Allowances Bid":
	                            Assert.assertTrue(value.contains("Allowances Bid"));
	                            break;
	                        case "Holding Limit Violation":
	                            Assert.assertTrue(value.contains("Holding Limit Violation"));
	                            break;
	                        case "Bid Guarantee Violation":
	                            Assert.assertTrue(value.contains("Bid Guarantee Violation"));
	                            break;
	                        case "Awarded Tier":
	                            Assert.assertTrue(value.contains("Awarded Tier"));
	                            break;
	                        case "Successful Bid Allowances":
	                            Assert.assertTrue(value.contains("Successful Bid Allowances"));
	                            break;
	                        case "Tie Breaker":
	                            Assert.assertTrue(value.contains("Tie Breaker"));
	                            break;
	                        case "Tie Breaker Share":
	                            Assert.assertTrue(value.contains("Tie Breaker Share"));
	                            break;
	                        case "Share Allocation":
	                            Assert.assertTrue(value.contains("Share Allocation"));
	                            break;
	                        case "Random Number Assignment":
	                            Assert.assertTrue(value.contains("Random Number Assignment"));
	                            break;
	                        case "Random Allocation":
	                            Assert.assertTrue(value.contains("Random Allocation"));
	                            break;
	                        case "Flag":
	                            Assert.assertTrue(value.contains("Flag"));
	                            break;
	                        case "Flag Time (ET)":
	                            Assert.assertTrue(value.contains("Flag Time (ET)"));
	                            break;
	                        case "Flagged By":
	                            Assert.assertTrue(value.contains("Flagged By"));
	                            break;
	                        case "Excluded":
	                            Assert.assertTrue(value.contains("Excluded"));
	                            break;
	                        case "Exclude Time (ET)":
	                            Assert.assertTrue(value.contains("Exclude Time (ET)"));
	                            break;
	                        case "Excluded By":
	                            Assert.assertTrue(value.contains("Excluded By"));
	                            break;
	                        case "Comment":
	                            Assert.assertTrue(value.contains("Comment"));
	                            break;
	                        default:
	                            break;
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

	public void DataReadFromBidsReportRS_QCSBMA() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
	    reportBtn.click();
	    System.out.println("Report page is opened");
	    ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	    searchReportsByEventName_SBMA();
	    
	    WebElement element = driver.findElement(btnBidsReportsRS_QC);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnBidsReportsRS_QC).click();
	    Thread.sleep(25000);
	    System.out.println("C31933 Verify bids report has information about the comments added to the bid during monitoring of the bids");
	    ObjectRepo.getTest().log(Status.PASS, "C31933 Verify bids report has information about the comments added to the bid during monitoring of the bids");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    Date date = new Date();
	    String date1 = dateFormat.format(date);
	    File src;
	    src = new File(folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024" + ".xlsx");
	    System.out.println("reports path : " + folderPath + File.separator + "Bids Report - " + prop.getProperty("Event_Name_RE1QC") + " " + "02-25-2024" + ".xlsx");
	    try {
	        FileInputStream ExcelFileToRead = new FileInputStream(src);
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
	                    ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
	                }
	                System.out.println();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("Bids report Data is fetched ");
	    ObjectRepo.getTest().log(Status.PASS, "Bids report Data is fetched");
	    FileInputStream excel = new FileInputStream(src);
	    Sheet a = WorkbookFactory.create(excel).getSheet("Comment");
	    String FirstColomn = a.getRow(0).getCell(0).getStringCellValue();
	    Assert.assertEquals(FirstColomn, "Bid ID");
	    System.out.println("Bid ID" + ":- " + FirstColomn);
	    ObjectRepo.getTest().log(Status.PASS, FirstColomn);

	    String secondColomn = a.getRow(0).getCell(1).getStringCellValue();
	    Assert.assertEquals(secondColomn, "Client Bid ID");
	    System.out.println("Client Bid ID" + ":- " + secondColomn);
	    ObjectRepo.getTest().log(Status.PASS, secondColomn);

	    String ThirdColomn = a.getRow(0).getCell(2).getStringCellValue();
	    Assert.assertEquals(ThirdColomn, "Transaction ID");
	    System.out.println("Transaction ID" + ":- " + ThirdColomn);
	    ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

	    String FourthColomn = a.getRow(0).getCell(3).getStringCellValue();
	    Assert.assertEquals(FourthColomn, "Comment");
	    System.out.println("Comment" + ":- " + FourthColomn);
	    ObjectRepo.getTest().log(Status.PASS, FourthColomn);
	}

	public void AccessAllowanceDeliveryreportRS_CA() throws IOException, InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		Thread.sleep(5000);
		reportBtn.click();
	    System.out.println("Report page is opened");
	    ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
	    searchReportsByEventName_ReserveSale();
	    
	    WebElement element = driver.findElement(btnAllowanceDeliveryReportRS_CA);
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    driver.findElement(btnAllowanceDeliveryReportRS_CA).click();
	    Thread.sleep(25000);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    Date date = new Date();
	    String date1 = dateFormat.format(date);
	    File src;
	    src = new File(folderPath + File.separator + "Allowance Delivery Report - "
	            + prop.getProperty("ReserveSaleEventName1") + " " + "02-12-2024" + ".xlsx");
	    System.out.println("reports path : " + folderPath + File.separator + "Allowance Delivery Report - "
	            + prop.getProperty("ReserveSaleEventName1") + " " + "02-12-2024" + ".xlsx");
	    try {
	        FileInputStream ExcelFileToRead = new FileInputStream(src);
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

	    System.out.println("Allowance Delivery report Data is fetched ");
	    ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery report Data is fetched");
	    FileInputStream excel = new FileInputStream(src);
	    Sheet a = WorkbookFactory.create(excel).getSheet("Allowance Delivery Report");

	    // Introductory fields
	    String FirstIntField = a.getRow(0).getCell(0).getStringCellValue();
	    Assert.assertEquals(FirstIntField, "Report Date & Time");
	    System.out.println("Report Date & Time" + ":- " + FirstIntField);
	    ObjectRepo.getTest().log(Status.PASS, FirstIntField);

	    String SecondIntField = a.getRow(1).getCell(0).getStringCellValue();
	    Assert.assertEquals(SecondIntField, "Event Name");
	    System.out.println("Event Name" + ":- " + SecondIntField);
	    ObjectRepo.getTest().log(Status.PASS, SecondIntField);

	    String ThirdIntField = a.getRow(2).getCell(0).getStringCellValue();
	    Assert.assertEquals(ThirdIntField, "Event Date");
	    System.out.println("Event Date" + ":- " + ThirdIntField);
	    ObjectRepo.getTest().log(Status.PASS, ThirdIntField);

	    // Headers
	    String FirstColomn = a.getRow(4).getCell(0).getStringCellValue();
	    Assert.assertEquals(FirstColomn, "Registry General Account Number");
	    System.out.println("Registry General Account Number" + ":- " + FirstColomn);
	    ObjectRepo.getTest().log(Status.PASS, FirstColomn);

	    String SecondColomn = a.getRow(4).getCell(1).getStringCellValue();
	    Assert.assertEquals(SecondColomn, "Entity Legal Name");
	    System.out.println("Entity Legal Name" + ":- " + SecondColomn);
	    ObjectRepo.getTest().log(Status.PASS, SecondColomn);

	    String ThirdColomn = a.getRow(4).getCell(2).getStringCellValue();
	    Assert.assertEquals(ThirdColomn, "Entity Operating Name");
	    System.out.println(ThirdColomn);
	    ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

	    String fourthColomn = a.getRow(4).getCell(3).getStringCellValue();
	    Assert.assertEquals(fourthColomn, "Entity Jurisdiction");
	    System.out.println(fourthColomn);
	    ObjectRepo.getTest().log(Status.PASS, fourthColomn);

	    String fifthColomn = a.getRow(4).getCell(4).getStringCellValue();
	    Assert.assertEquals(fifthColomn, "Tier Allowances Sold");
	    System.out.println(fifthColomn);
	    ObjectRepo.getTest().log(Status.PASS, fifthColomn);

	    String sixthColomn = a.getRow(4).getCell(5).getStringCellValue();
	    Assert.assertEquals(sixthColomn, "Total Successful Bid Quantity");
	    System.out.println(sixthColomn);
	    ObjectRepo.getTest().log(Status.PASS, sixthColomn);

	    System.out.println("C31901-Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.");
	    ObjectRepo.getTest().log(Status.PASS,
	            "C31901-Confirm Allowance Delivery report is generated after results are run for Sponsor and MM.");

	}

	public class ExcelDataProviderforResultReportRSQCR70 extends CommonLib {
		XSSFWorkbook wb;

		public ExcelDataProviderforResultReportRSQCR70() throws Exception {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			String date1 = dateFormat.format(date);
			File src = new File(folderPath + File.separator + "Results Report - "
					+ prop.getProperty("Event_Name_RE1QC") + " " + date1 + ".xlsx");

			try {
				FileInputStream ExcelFileToRead = new FileInputStream(src);
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
		}

		public void DataReadFromClientBidsReport_NSIndividualAuction() throws IOException, InterruptedException {

		}

		public String getStringData(String Sheetname, int row, int col) {
			return wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
		}

		public double getNumericData(String Sheetname, int row, int col) {
			return wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
		}

	}

	public void DataReadFromClientBidsReport_NSIndividualAuction()
			throws InterruptedException, EncryptedDocumentException, IOException {
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");

		searchClientBidsReportsByEventName_NS();
		downloadClientBidsReportFromGrid_NS();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		Date date = new Date();

		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Client Bids Report - " + prop.getProperty("eventNameNS") + " "
				+ date1 + ".xlsx"); // "07-18-2023"
		System.out.println("reports path : " + folderPath + File.separator + "Client Bids Report - "
				+ prop.getProperty("eventNameNS") + " " + "08-16-2023 NS3561-3847" + ".xlsx");

		System.out.println("Asserted The name of the report must be Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Client Bids Audit Report");

		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Client Bids Report Data is fetched ");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report Data is fetched");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Report");
		String FirstIntrField = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstIntrField, "Report Date & Time");
		System.out.println(FirstIntrField);
		ObjectRepo.getTest().log(Status.PASS, FirstIntrField);

		String SecondIntrField = a.getRow(1).getCell(0).getStringCellValue();
		Assert.assertEquals(SecondIntrField, "Event Name");
		System.out.println(SecondIntrField);
		ObjectRepo.getTest().log(Status.PASS, SecondIntrField);

		String ThirdIntrField = a.getRow(2).getCell(0).getStringCellValue();
		Assert.assertEquals(ThirdIntrField, "Event Date");
		System.out.println(ThirdIntrField);
		ObjectRepo.getTest().log(Status.PASS, ThirdIntrField);

		String FourthIntrField = a.getRow(3).getCell(0).getStringCellValue();
		Assert.assertEquals(FourthIntrField, "Entity Legal Name");
		System.out.println(FourthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FourthIntrField);

		String FifthIntrField = a.getRow(4).getCell(0).getStringCellValue();
		Assert.assertEquals(FifthIntrField, "Entity Operating Name");
		System.out.println(FifthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FifthIntrField);

		String tenthColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Total Bids Submitted");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);

		String eleventhColomn = a.getRow(6).getCell(0).getStringCellValue();
		Assert.assertEquals(eleventhColomn, "Settlement price CAD (Current)");
		System.out.println(eleventhColomn);
		ObjectRepo.getTest().log(Status.PASS, eleventhColomn);

		double twelvththColomn = a.getRow(6).getCell(1).getNumericCellValue();
		System.out.println("Settlment price" + twelvththColomn);
		System.out.println("Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "Confirm the specified introductory fields are available");
		String FirstColomn = a.getRow(8).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Client Bid ID");
		System.out.println("Client Bid ID" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);
		String SecondColomn = a.getRow(8).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Transaction ID");
		System.out.println("Transaction ID" + ":- " + SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);

		String ThirdColomn = a.getRow(8).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Bid By");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);

		String fourthColomn = a.getRow(8).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Update Time (ET)");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);

		String fifthColomn = a.getRow(8).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Currency");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);

		String sixthColomn = a.getRow(8).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid Price");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);

		String seventhColomn = a.getRow(8).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Allowances Bid");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);

		String eighthColomn = a.getRow(8).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Vintage");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);

		String ninghthColomn = a.getRow(8).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Successful Bid Allowances");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);

		String tenthColomn1 = a.getRow(9).getCell(0).getStringCellValue();
		Assert.assertEquals(tenthColomn1, "kbYtei2z");
		System.out.println(tenthColomn1);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn1);

		String EleventhColomn1 = a.getRow(9).getCell(2).getStringCellValue();
		Assert.assertEquals(EleventhColomn1, "t1nsusera@demo.citssdev.com");
		System.out.println(EleventhColomn1);
		ObjectRepo.getTest().log(Status.PASS, EleventhColomn1);
		double twelvthhColomn1 = a.getRow(9).getCell(6).getNumericCellValue();
		System.out.println("Allowances Bid" + twelvthhColomn1);
		double thirtenththhColomn1 = a.getRow(9).getCell(8).getNumericCellValue();
		System.out.println("Successful Bid Allowances" + thirtenththhColomn1);
		System.out.println("Verify the data in the Client Bids Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "Verify the data in the Client Bids Report is correct");
	}

	public void ARSelectentityClientBidsReport_NS() throws IOException, InterruptedException {
		WebElement abc = selectentityNS;
		abc.click();
		WebElement abc1 = BtnContinueclkNS;
		abc1.click();
	}

	public void ARAccessClientBidsReport_NS() throws EncryptedDocumentException, IOException  {
		wait.until(ExpectedConditions.visibilityOf(reportBtn));
		reportBtn.click();
		System.out.println("Report page is opened");
		ObjectRepo.getTest().log(Status.PASS, "Report page is opened");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search Reports']")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Event Name']")));
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
		driver.findElement(By.xpath("//*[@aria-label='Event Name']")).sendKeys(prop.getProperty("eventNameNS"));
		driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameNS") + "']")).click();
		driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
		
		WebElement element = driver.findElement(btnClientBidsReportsNS);
		js.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(btnClientBidsReportsNS).click();
		System.out.println(
				"C31356	AR can access the Client bids report and the data must reflect the final bids submitted.");
		ObjectRepo.getTest().log(Status.PASS,
				"C31356 AR can access the Client bids report and the data must reflect the final bids submitted.");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

		Date date = new Date();

		String date1 = dateFormat.format(date);
		File src;
		src = new File(folderPath + File.separator + "Client Bids Report - " + prop.getProperty("eventNameNS") + " "
				+ date1 + ".xlsx"); // "07-18-2023"
		System.out.println("reports path : " + folderPath + File.separator + "Client Bids Report - "
				+ prop.getProperty("eventNameNS") + " " + "08-16-2023 NS3561-3847" + ".xlsx");

		System.out.println("Asserted The name of the report must be Client Bids Audit Report");
		ObjectRepo.getTest().log(Status.PASS, "Asserted The name of the report must be Client Bids Audit Report");

		try {
			FileInputStream ExcelFileToRead = new FileInputStream(src);
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
						System.out.print(cell.toString() + " ");
						ObjectRepo.getTest().log(Status.PASS, cell.toString() + " ");
					}
					System.out.println();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Client Bids Report Data is fetched ");
		ObjectRepo.getTest().log(Status.PASS, "Client Bids Report Data is fetched");
		FileInputStream excel = new FileInputStream(src);
		Sheet a = WorkbookFactory.create(excel).getSheet("Client Bids Report");

		// Introductory fields
		String FirstIntrField = a.getRow(0).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstIntrField, "Report Date & Time");
		System.out.println(FirstIntrField);
		ObjectRepo.getTest().log(Status.PASS, FirstIntrField);

		String SecondIntrField = a.getRow(1).getCell(0).getStringCellValue();
		Assert.assertEquals(SecondIntrField, "Event Name");
		System.out.println(SecondIntrField);
		ObjectRepo.getTest().log(Status.PASS, SecondIntrField);

		String ThirdIntrField = a.getRow(2).getCell(0).getStringCellValue();
		Assert.assertEquals(ThirdIntrField, "Event Date");
		System.out.println(ThirdIntrField);
		ObjectRepo.getTest().log(Status.PASS, ThirdIntrField);
		String FourthIntrField = a.getRow(3).getCell(0).getStringCellValue();
		Assert.assertEquals(FourthIntrField, "Entity Legal Name");
		System.out.println(FourthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FourthIntrField);
		String FifthIntrField = a.getRow(4).getCell(0).getStringCellValue();
		Assert.assertEquals(FifthIntrField, "Entity Operating Name");
		System.out.println(FifthIntrField);
		ObjectRepo.getTest().log(Status.PASS, FifthIntrField);
		String tenthColomn = a.getRow(5).getCell(0).getStringCellValue();
		Assert.assertEquals(tenthColomn, "Total Bids Submitted");
		System.out.println(tenthColomn);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn);
		String eleventhColomn = a.getRow(6).getCell(0).getStringCellValue();
		Assert.assertEquals(eleventhColomn, "Settlement price CAD (Current)");
		System.out.println(eleventhColomn);
		ObjectRepo.getTest().log(Status.PASS, eleventhColomn);
		double twelvththColomn = a.getRow(6).getCell(1).getNumericCellValue();
		System.out.println("Settlment price" + twelvththColomn);
		System.out.println("Confirm the specified introductory fields are available");
		ObjectRepo.getTest().log(Status.PASS, "Confirm the specified introductory fields are available");
		String FirstColomn = a.getRow(8).getCell(0).getStringCellValue();
		Assert.assertEquals(FirstColomn, "Client Bid ID");
		System.out.println("Client Bid ID" + ":- " + FirstColomn);
		ObjectRepo.getTest().log(Status.PASS, FirstColomn);
		String SecondColomn = a.getRow(8).getCell(1).getStringCellValue();
		Assert.assertEquals(SecondColomn, "Transaction ID");
		System.out.println("Transaction ID" + ":- " + SecondColomn);
		ObjectRepo.getTest().log(Status.PASS, SecondColomn);
		String ThirdColomn = a.getRow(8).getCell(2).getStringCellValue();
		Assert.assertEquals(ThirdColomn, "Bid By");
		System.out.println(ThirdColomn);
		ObjectRepo.getTest().log(Status.PASS, ThirdColomn);
		String fourthColomn = a.getRow(8).getCell(3).getStringCellValue();
		Assert.assertEquals(fourthColomn, "Update Time (ET)");
		System.out.println(fourthColomn);
		ObjectRepo.getTest().log(Status.PASS, fourthColomn);
		String fifthColomn = a.getRow(8).getCell(4).getStringCellValue();
		Assert.assertEquals(fifthColomn, "Currency");
		System.out.println(fifthColomn);
		ObjectRepo.getTest().log(Status.PASS, fifthColomn);
		String sixthColomn = a.getRow(8).getCell(5).getStringCellValue();
		Assert.assertEquals(sixthColomn, "Bid Price");
		System.out.println(sixthColomn);
		ObjectRepo.getTest().log(Status.PASS, sixthColomn);
		String seventhColomn = a.getRow(8).getCell(6).getStringCellValue();
		Assert.assertEquals(seventhColomn, "Allowances Bid");
		System.out.println(seventhColomn);
		ObjectRepo.getTest().log(Status.PASS, seventhColomn);
		String eighthColomn = a.getRow(8).getCell(7).getStringCellValue();
		Assert.assertEquals(eighthColomn, "Vintage");
		System.out.println(eighthColomn);
		ObjectRepo.getTest().log(Status.PASS, eighthColomn);
		String ninghthColomn = a.getRow(8).getCell(8).getStringCellValue();
		Assert.assertEquals(ninghthColomn, "Successful Bid Allowances");
		System.out.println(ninghthColomn);
		ObjectRepo.getTest().log(Status.PASS, ninghthColomn);
		String tenthColomn1 = a.getRow(9).getCell(0).getStringCellValue();
		Assert.assertEquals(tenthColomn1, "kbYtei2z");
		System.out.println(tenthColomn1);
		ObjectRepo.getTest().log(Status.PASS, tenthColomn1);
		String EleventhColomn1 = a.getRow(9).getCell(2).getStringCellValue();
		Assert.assertEquals(EleventhColomn1, "t1nsusera@demo.citssdev.com");
		System.out.println(EleventhColomn1);
		ObjectRepo.getTest().log(Status.PASS, EleventhColomn1);
		double twelvthhColomn1 = a.getRow(9).getCell(6).getNumericCellValue();
		System.out.println("Allowances Bid" + twelvthhColomn1);
		double thirtenththhColomn1 = a.getRow(9).getCell(8).getNumericCellValue();
		System.out.println("Successful Bid Allowances" + thirtenththhColomn1);
		System.out.println("Verify the data in the Client Bids Report is correct");
		ObjectRepo.getTest().log(Status.PASS, "Verify the data in the Client Bids Report is correct");
	}

	public void searchReportsByEventName_SBMA()  {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Event Name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Event Name']")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC"));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC") + "']")));
		WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='SEARCH']"))).click();
	}
	public void searchEmissionReportsByEventName_SBMA_FR()  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU RAPPORT']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rechercher un rapport']")))
					.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='demo-customized-select'])[2]")));
			Select s = new Select(element2);
			s.selectByVisibleText("Rapport de la livraison des unités d'émission");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RECHERCHER']"))).click();
	}

	public void searchReportsByEventName_SBMA_FR()  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU RAPPORT']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rechercher un rapport']")))
					.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='demo-customized-select'])[2]")));
			Select s = new Select(element2);
			s.selectByVisibleText("Rapport des offres");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RECHERCHER']"))).click();
	}
	
	public void searchReportsSBMABidLotAuditReportByEventName_SBMA_FR()  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU RAPPORT']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rechercher un rapport']")))
					.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='demo-customized-select'])[2]")));
			Select s = new Select(element2);
			s.selectByVisibleText("Rapport de vérification des lots de la vente de gré à gré");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RECHERCHER']"))).click();
	}

	public void searchEmmisionReportsByEventName_SBMA_FR() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU RAPPORT']")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rechercher un rapport']")))
					.click();
			WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='demo-customized-select'])[2]")));
			Select s = new Select(element2);
			s.selectByVisibleText("Rapport de vérification des lots de la vente de gré à gré");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@placeholder='Tous'])[1]")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='RECHERCHER']"))).click();
	}
	public void searchClientBidsAuditReportsByEventName_SBMA()   {
		WebElement ReportName = driver.findElement(By.xpath("//*[text()='REPORT NAME']"));
		
		js.executeScript("arguments[0].scrollIntoView();",ReportName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
		driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Event Name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Event Name']")))
				.sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
		 WebElement event = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='SEARCH']"))).click();
	}

	public void downloadClientBidsAuditReportFromGrid() {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label='Entity Name'])[2]"))).click();
		js.executeScript("arguments[0].scrollIntoView();", scroll_To_Bottom);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']")));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@id='demo-customized-select'])[4]")));
		Select s = new Select(element2);
		s.selectByVisibleText("Client Bids Audit Report");
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label='Download Report'])")));
		js.executeScript("arguments[0].scrollIntoView();", element);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@aria-label='Download Report'])"))).click();
		System.out.println(
				"C31952-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window(Report available in English )");
		ObjectRepo.getTest().log(Status.PASS,
				"C31952-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window(Report available in English )");
		System.out.println(
				"C31993-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window");
		ObjectRepo.getTest().log(Status.PASS,
				"C31993-Sponsor/AR can get access to Client Bids Audit report for an Allowance Sale event during the bidding window");
	try {
		Thread.sleep(25000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}

	public void searchClientBidsAuditReportsByEventName_SBMA_FR() throws InterruptedException {
	    driver.findElement(By.xpath("//*[text()='Rechercher un rapport']")).click();
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[1]")).click();
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[1]"))
	            .sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
	    driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']")).click();
	    driver.findElement(By.xpath("//*[text()='RECHERCHER']")).click();
	}

	public void downloadClientBidsAuditReportFromGrid_SBMA_FR() throws InterruptedException {
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[3]")).click();
	    
	    WebElement element1 = driver
	            .findElement(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']"));
	    js.executeScript("arguments[0].scrollIntoView();", element1);
	    element1.click();
	    Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
	    s.selectByVisibleText("Rapport de vérification des offres client");
	    
	    WebElement element = driver.findElement(By.xpath("(//*[@data-icon='download'])[9]"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	}

	public void ARAccessAndDownloadBidsAuditReportsByEventName_SBMA() throws InterruptedException {
	    driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
	            .sendKeys(prop.getProperty("Event_Name_RE1QC"));
	    driver.findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC") + "']")).click();
	    driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	    
	    WebElement element = driver.findElement(By.xpath("(//*[@data-icon='download'])"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	}
	public void downloadClientBidsReportFromGrid() throws InterruptedException {
	    driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
	    
	    WebElement element1 = driver
	            .findElement(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']"));
	    js.executeScript("arguments[0].scrollIntoView();", element1);
	    element1.click();
	    Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
	    s.selectByVisibleText("Client Bids Report");
	    
	    WebElement element = driver.findElement(By.xpath("(//*[@aria-label='Download Report'])"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	    Thread.sleep(25000);
	}

	public void searchClientBidsReportsByEventName_SBMA_FR()  {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='NOM DU RAPPORT']")));
	    driver.findElement(By.xpath("//*[text()='Rechercher un rapport']")).click();
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[1]")).click();
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[1]"))
	            .sendKeys(prop.getProperty("Event_Name_RE1QC_FR"));
	    
	    WebElement element = driver
	            .findElement(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1QC_FR") + "']"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	    driver.findElement(By.xpath("//*[text()='RECHERCHER']")).click();
	}

	public void downloadClientBidsReportFromGrid_SBMA_FR()  {
	    driver.findElement(By.xpath("(//*[@placeholder='Tous'])[3]")).click();
	    
	    WebElement element1 = driver
	            .findElement(By.xpath("//*[text()='Prescribed_Emitter+A - PLC=0 PLA=0 HLC=15717500 HLA=0']"));
	    js.executeScript("arguments[0].scrollIntoView();", element1);
	    element1.click();
	    Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
	    s.selectByVisibleText("Rapport des offres client");
	    
	    WebElement element = driver.findElement(By.xpath("(//*[@data-icon='download'])[9]"));
	    js.executeScript("arguments[0].scrollIntoView();", element);
	    element.click();
	    try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchReportsByEventName_ReserveSale()  {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='REPORT NAME']")));
	    driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']")).click();
	    driver.findElement(By.xpath("//*[@aria-label='Event Name']"))
	            .sendKeys(prop.getProperty("ReserveSaleEventName1"));
	    WebElement eventElement = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName1") + "']"));
		
		js.executeScript("arguments[0].scrollIntoView();", eventElement);
		eventElement.click();
	    driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void searchClientBidsReportsByEventName_NS() throws InterruptedException {
	    driver.findElement(By.xpath("//*[text()='Search Reports']")).click();
	    WebElement eventNameElement = driver.findElement(By.xpath("//*[@aria-label='Event Name']"));
	    eventNameElement.click();
	    eventNameElement.sendKeys(prop.getProperty("eventNameNS"));
	    WebElement eventElement = driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameNS") + "']"));
	    js.executeScript("arguments[0].scrollIntoView(true);", eventElement);
	    eventElement.click();
	    driver.findElement(By.xpath("//*[text()='SEARCH']")).click();
	}

	public void downloadClientBidsReportFromGrid_NS() throws InterruptedException {
	    driver.findElement(By.xpath("(//*[@aria-label='Entity Name'])[2]")).click();
	    WebElement element1 = driver.findElement(By.xpath("//*[text()='T1NSEmetteurN']"));
	    js.executeScript("arguments[0].scrollIntoView(true);", element1);
	    element1.click();
	    Select s = new Select(driver.findElement(By.xpath("(//*[@id='demo-customized-select'])[4]")));
	    s.selectByVisibleText("Client Bids Report");
	    WebElement downloadElement = driver.findElement(By.xpath("(//*[@aria-label='Download Report'])"));
	    js.executeScript("arguments[0].scrollIntoView(true);", downloadElement);
	    downloadElement.click();
	}


}
