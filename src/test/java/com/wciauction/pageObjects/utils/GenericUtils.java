package com.wciauction.pageObjects.utils;

import static com.wci.auction.org.resources.BaseClass.prop;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;

public class GenericUtils {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public static String path;
	public String date1;
	public static Actions action;

	@FindBy(xpath = "//*[@title='Log in']")
	private WebElement btnLogin;

	@FindBy(xpath = "//input[@id='enter_email']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='enter_passphrase']")
	private WebElement password;

	@FindBy(xpath = "(//span[text()='Log in'])[2]")
	private WebElement btnLogin2;
	
	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement btnLogin3;

	@FindBy(xpath = "//label[@class='MuiFormLabel-root Mui-required']")
	private WebElement securityQue;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInput-input']")
	private WebElement securityAnswer;

	@FindBy(xpath = "//span[contains(text(),'NEXT')]")
	private WebElement btnNext;

	@FindBy(xpath = "//span[contains(text(),'SOUMETTRE')]")
	private WebElement btnNextQC;

	@FindBy(xpath = "//*[text()='ADD BID']")
	private WebElement btnAddbid;

	@FindBy(xpath = "//input[@id='bidPrice']")
	private WebElement textBidPrice;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement textBidLots;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement dropdownvintage;

	@FindBy(xpath = "//select[@name='tier']")
	private WebElement dropdownTier;

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

	@FindBy(xpath = "//a[text()='Event']")
	private WebElement btnEvent;

	@FindBy(xpath = "//span[text()='Events']//parent::button")
	private WebElement DropDownEvents;

	@FindBy(xpath = "//*[@aria-label='approval2Status']")
	private WebElement btnApprovalQue;

	@FindBy(xpath = "//*[text()='Auction']")
	private WebElement Auction;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[1]")
	private WebElement btnSubmit1;

	@FindBy(xpath = "(//span[text()='SUBMIT'])[2]")
	private WebElement btnSubmit2;

	@FindBy(xpath = "//button[@title='APPROVE']")
	private WebElement popupBtn;

	@FindBy(xpath = "//select[@id='demo-customized-select']")
	private WebElement selectCatagory;

	@FindBy(xpath = "//button[@aria-label='approval1Status']")
	private WebElement btnfirstApproval;

	@FindBy(xpath = "//*[text()='The approval of the event creation has been proposed by the first sponsor.']")
	private WebElement ApprovalMessage;

	@FindBy(xpath = "//input[@id='eventNameInEnglish']")
	private WebElement eventName;

	@FindBy(xpath = "//*[contains(text(),'Monitoring')]")
	private WebElement btnEventMonitoring;

	@FindBy(xpath = "//a[@href=\"/report\"]")
	private WebElement btnReports;

	@FindBy(xpath = "//*[@data-icon=\"caret-right\"]")
	private WebElement btnSearchReports;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[2]")
	private WebElement reportTypeDropdown;

	@FindBy(xpath = "//*[@aria-label='Event Name']")
	private WebElement eventNameDropdown;

	@FindBy(xpath = "//span[text()='SEARCH']")
	private WebElement searchButton;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
	private WebElement yearDropdown;

	@FindBy(xpath = "//*[@aria-label='Entity Name']")
	private WebElement entityNameDropdown;

	@FindBy(xpath = "//td[text()=\"Post Event Operations Report\"]")
	private WebElement postEventOperationsReportText;

	@FindBy(xpath = "(//td[text()=\"Post Event Operations Report\"]/following::td)[3]")
	private WebElement generatedTimeForPostEventOperationsReport;

	@FindBy(xpath = "//td[text()=\"Bids Audit Report\"]")
	private WebElement bidsAuditReportText;

	@FindBy(xpath = "(//td[text()=\"Bids Audit Report\"]/following::td)[3]")
	private WebElement generatedTimeForBidsAuditReport;

	@FindBy(xpath = "//td[text()=\"Financial Settlement Report\"]")
	private WebElement financialSettlementReportText;

	@FindBy(xpath = "(//td[text()=\"Financial Settlement Report\"]/following::td)[3]")
	private WebElement generatedTimeForFinancialSettlementReport;

	@FindBy(xpath = "//td[text()=\"Bids Report\"]")
	private WebElement bidsReportText;

	@FindBy(xpath = "(//td[text()=\"Bids Report\"]/following::td)[3]")
	private WebElement generatedTimeForBidsReport;

	@FindBy(xpath = "//td[text()=\"Sales by Mutual Agreement Bid Lot Audit Report\"]")
	private WebElement salesbyMutualAgreementBidLotAuditReportText;

	@FindBy(xpath = "(//td[text()=\"Sales by Mutual Agreement Bid Lot Audit Report\"]/following::td)[3]")
	private WebElement generatedTimeForSalesbyMutualAgreementBidLotAuditReport;

	@FindBy(xpath = "//td[text()=\"Allowance Price Containment Reserve Bid Lot Audit Report\"]")
	private WebElement allowancePriceContainmentReserveBidLotAuditReportText;

	@FindBy(xpath = "(//td[text()=\"Allowance Price Containment Reserve Bid Lot Audit Report\"]/following::td)[3]")
	private WebElement generatedTimeForAllowancePriceContainmentReserveBidLotAuditReport;

	@FindBy(xpath = "//td[text()=\"Reserve Sale Bid Lot Audit Report\"]")
	private WebElement reserveSaleBidLotAuditReportText;

	@FindBy(xpath = "(//td[text()=\"Reserve Sale Bid Lot Audit Report\"]/following::td)[3]")
	private WebElement generatedTimeForReserveSaleBidLotAuditReport;

	@FindBy(xpath = "//td[text()=\"Emission Units Delivery Report\"]")
	private WebElement emissionUnitsDeliveryReportText;

	@FindBy(xpath = "(//td[text()=\"Emission Units Delivery Report\"]/following::td)[3]")
	private WebElement generatedTimeForEmissionUnitsDeliveryRepor;

	@FindBy(xpath = "//td[text()=\"Allowance Delivery Report\"]")
	private WebElement allowanceDeliveryReportText;

	@FindBy(xpath = "(//td[text()=\"Allowance Delivery Report\"]/following::td)[3]")
	private WebElement generatedTimeForAllowanceDeliveryReport;

	@FindBy(xpath = "//td[text()=\"Results Report\"]")
	private WebElement resultsReportText;

	@FindBy(xpath = "(//td[text()=\"Results Report\"]/following::td)[3]")
	private WebElement generatedTimeForResultsReport;

	@FindBy(xpath = "//td[text()=\"Qualified Bidders Report\"]")
	private WebElement qualifiedBiddersReportText;

	@FindBy(xpath = "(//td[text()=\"Qualified Bidders Report\"]/following::td)[3]")
	private WebElement generatedTimeForQualifiedBiddersReport;


	public GenericUtils() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void UserLogin(String UserName, String Password) throws InterruptedException {
		Thread.sleep(5000);
		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
			}
			username.sendKeys(UserName);
			password.sendKeys(Password);
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin2)).click();
			//			btnLogin2.click();
			// ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(UserName);
			password.sendKeys(Password);
			//			btnLogin.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
		}
		ObjectRepo.getTest().log(Status.PASS, "User Login Successfully.");

	}

	public void runTimeUserLogin(String UserName, String Password) throws InterruptedException {
		Thread.sleep(5000);
		username.sendKeys(UserName);
		password.sendKeys(Password);
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin3)).click();
		ObjectRepo.getTest().log(Status.PASS, "User Login Successfully.");
	}

	public void handleSecurityQue() {
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);
		try {
			Thread.sleep(8000);
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
		ObjectRepo.getTest().log(Status.PASS, "Security Questions Handeled Successfully.");
	}

	public void clickOnnext() {
		WebElement abc = btnNext;
		Actions action = new Actions(driver);
		action.moveToElement(abc).click().build().perform();
		action.click(abc).build().perform();
	}

	public void firstApprovalCA(String eventName) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		ObjectRepo.getTest().log(Status.PASS, "The approval of the event creation has been proposed by the first sponsor.");
		Assert.assertEquals(alert, "The approval of the event creation has been proposed by the first sponsor.");
	}

	public void secondApprovalCA(String eventName) throws IOException, InterruptedException {
		WebElement element = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigate to particular event ");
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
		abc1.click();
		Thread.sleep(15000);
		System.out.println("Second Approval given by Sponsor.");
		ObjectRepo.getTest().log(Status.PASS, "Second Approval given by Sponsor.");
	}

	public void SelectEntity(String entityID) {
		try {
			WebElement entityName = driver.findElement(By.xpath("//span[text()='" + entityID + "']"));
			wait.until(ExpectedConditions.visibilityOf(entityName));
			entityName.click();
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
			.click();
			ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
		} catch (Exception e) {
			WebElement entityNameSerachBox = driver
					.findElement(By.xpath("//*[@placeholder='Enter Entity Name or Entity ID']"));
			entityNameSerachBox.sendKeys(entityID);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebElement entityName = driver.findElement(By.xpath("//input[@name='rollDownProcedure']"));
			entityName.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.findElement(By.xpath(
					"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
			.click();
			ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
		}
	}

	public void selectEventForBidding(String eventname) throws InterruptedException {
		Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Events'])")));
		driver.findElement(By.xpath("//*[text()='Events']")).click();
		Thread.sleep(3000);
		WebElement eventName = driver.findElement(By.xpath("(//*[text()='" + eventname + "'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(eventName));
		eventName.click();
		System.out.println("Navigate to particular Event: " + eventname);
		ObjectRepo.getTest().log(Status.PASS, "Navigate to particular Event: " + eventname);
		System.out.println("step Event Select "+ eventname);
		ObjectRepo.getTest().log(Status.PASS, "Event Selected : "+ eventname);
	}

	public void BiddingByAR(String bidPrice, String bidLot) throws IOException, InterruptedException {
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(20000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(bidPrice);
		ObjectRepo.getTest().log(Status.PASS, "Bid price entered successfully.");
		textBidLots.sendKeys(bidLot);
		ObjectRepo.getTest().log(Status.PASS, "Bid Lot entered successfully.");
		dropdownvintage.sendKeys("Current");
		ObjectRepo.getTest().log(Status.PASS, "User selected 'Vintage' from the dropdown menu.");
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Submit button successfully.");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Confirm button successfully.");
		try {
			String Massage2 = alertMsg.getText();
			System.out.println(Massage2);
		} catch (Exception e) {
			String Massage2 = alertMsg2.getText();
			System.out.println(Massage2);
		}
		alertPopup.click();
	}	

	public void BiddingByARForAllownceSaleEvent(String bidLot, String Category) throws IOException, InterruptedException {
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(20000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLots.sendKeys(bidLot);
		ObjectRepo.getTest().log(Status.PASS, "Bid Lot entered successfully.");
		WebElement element0 = selectCatagory;
		Select s = new Select(element0);
		s.selectByVisibleText(Category);
		ObjectRepo.getTest().log(Status.PASS, "User selected 'Category' from the dropdown menu.");
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Submit button successfully.");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Confirm button successfully.");
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void getCurrentEvent(String eventName) throws InterruptedException {
		WebElement element = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Successfully navigated to the selected event.");
	}

	public void CaliforniaJuridictionApprovalForEventCreation(String eventName) throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		Thread.sleep(3000);
		ref.clickbtnAdmin();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
			btnEvent.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
			btnEvent.click();
		}
		WebElement element = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		//		WebElement ref1 = driver.findElement(eventDetailsBtnReport);
		js.executeScript("arguments[0].scrollIntoView();", element);
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
		System.out.println("Navigate to particular event ");
		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");
		System.out.println("Navigate to particular event ");
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		Thread.sleep(2000);		
		wait.until(ExpectedConditions.elementToBeClickable(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
		ObjectRepo.getTest().log(Status.PASS, "The first approval for editing the event has been granted.");
	}

	public void firstApprovalForEditEvent(String eventName) throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		Thread.sleep(3000);
		//		ref.clickbtnAdmin();
		//		try {
		//			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		//			btnEvent.click();
		//		} catch (Exception e) {
		//			wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		//			btnEvent.click();
		//		}
		//		WebElement element = driver
		//				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		////		WebElement ref1 = driver.findElement(eventDetailsBtnReport);
		//		js.executeScript("arguments[0].scrollIntoView();", element);
		//		action = new Actions(driver);
		//		action.moveToElement(element).build().perform();
		//		action.click(element).build().perform();
		//		System.out.println("Navigate to particular event ");
		//		ObjectRepo.getTest().log(Status.PASS, "Navigated to particular event ");
		//		System.out.println("Navigate to particular event ");
		wait.until(ExpectedConditions.elementToBeClickable(btnfirstApproval));
		btnfirstApproval.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit1));
		btnSubmit1.click();
		wait.until(ExpectedConditions.elementToBeClickable(ApprovalMessage));
		String alert = ApprovalMessage.getText();
		System.out.println(alert);
	}

	public void secondApproval(String eventName) throws InterruptedException {
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickbtnAdmin();
		wait.until(ExpectedConditions.elementToBeClickable(btnEvent));
		btnEvent.click();
		WebElement element = driver
				.findElement(By.xpath("(//*[@title='Go to details of Event " + eventName + "'])[1]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
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
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Second approval given successfully");
		ObjectRepo.getTest().log(Status.PASS, "The second approval has been successfully granted.");
	}

	public void BiddingByARReserveSale(String bidLot, String tier) throws IOException, InterruptedException {
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(20000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
			ObjectRepo.getTest().log(Status.PASS, "Clicked on Add Bid button.");
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLots.sendKeys(bidLot);
		ObjectRepo.getTest().log(Status.PASS, "Bid Lots entered successfully.");
		Select sel = new Select(dropdownTier);
		sel.selectByVisibleText(tier);
		ObjectRepo.getTest().log(Status.PASS, "User selected 'Tier' from the dropdown menu.");
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Submit button successfully.");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Confirm button successfully.");
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void enterEventName(String jointAuctionEventName) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		LocalTime myDateObj = LocalTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH/mm");
		String formattedDate = myDateObj.format(myFormatObj);
		System.out.println("Current time :" + formattedDate);
		//		newEventName = RandomEventNameGenerator.generateAndUpdateEventName();
		//		System.out.println("Generated event name: " + newEventName);
		js.executeScript("arguments[0].scrollIntoView();", eventName);
		eventName.sendKeys(jointAuctionEventName);
	}

	public void BiddingByARSBMA(String bidLot, String tier) throws IOException, InterruptedException {
		try {
			Thread.sleep(17000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(20000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Add Bid button.");
		textBidLots.sendKeys(bidLot);
		ObjectRepo.getTest().log(Status.PASS, "Bid Lots entered successfully.");
		Select sel = new Select(dropdownTier);
		sel.selectByVisibleText(tier);
		ObjectRepo.getTest().log(Status.PASS, "User selected 'Tier' from the dropdown menu.");
		btnSubmit.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Submit button successfully.");
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Confirm button successfully.");
		String Massage2 = alertMsg2.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void searchReport() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEventMonitoring));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnReports)).click();
		ObjectRepo.getTest().log(Status.PASS, "Navigate to reports page.");
		try {
			//			wait.until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
			Thread.sleep(10000);
			waitForElementToBeClickable(btnSearchReports);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnSearchReports)).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
		}
	}

	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));

		final int MAX_ATTEMPTS = 3;
		boolean clicked = false;

		for (int attempt = 0; attempt < MAX_ATTEMPTS && !clicked; attempt++) {
			try {
				switch (attempt) {
				case 0:
					// Strategy 1: Regular Selenium click
					element.click();
					break;

				case 1:
					// Strategy 2: Actions API click with move to element
					new Actions(driver)
					.moveToElement(element)
					.click()
					.build()
					.perform();
					break;

				case 2:
					// Strategy 3: JavaScript click
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
					break;
				}

				// If we reach here without exception, click was successful
				clicked = true;

			} catch (ElementClickInterceptedException e) {
				// If this is the last attempt and it failed, throw an exception
				if (attempt == MAX_ATTEMPTS - 1) {
					throw new RuntimeException("Element not clickable after " + MAX_ATTEMPTS + " attempts using different strategies.", e);
				}

				// Otherwise wait a moment before the next attempt
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public void searchReportByEvent(String eventName) {
		wait.until(ExpectedConditions.elementToBeClickable(eventNameDropdown));
		eventNameDropdown.click();
		eventNameDropdown.sendKeys(eventName);
		ObjectRepo.getTest().log(Status.PASS, "The event name was entered in the search field.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].click();",
				driver.findElement(By.xpath("//*[text()='"+eventName+ "']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", searchButton);
		try {
			searchButton.click();
		} catch (Exception e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(searchButton).click().build().perform();
		}
		ObjectRepo.getTest().log(Status.PASS, "Clicked on Search buttom.");
	}


	public void verifyAllReportsAreDisplayedCAReserveSale() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		Assert.assertTrue(reserveSaleBidLotAuditReportText.isDisplayed(), "Reserve Sale Bid Lot Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForReserveSaleBidLotAuditReport.isDisplayed(), "Generated time for Reserve Sale Bid Lot Audit Report is not displayed");
		String generatedTime3 = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime3.isEmpty(), "Reserve Sale Bid Lot Audit Report is empty");
		System.out.println("Reserve Sale Bid Lot Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Reserve Sale Bid Lot Audit Report is generated for download.");

		Assert.assertTrue(allowanceDeliveryReportText.isDisplayed(), "Allowance Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForAllowanceDeliveryReport.isDisplayed(), "Generated time for Allowance Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForAllowanceDeliveryReport.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Allowance Delivery Report is empty");
		System.out.println("Allowance Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedCASingleAuction() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		Assert.assertTrue(allowanceDeliveryReportText.isDisplayed(), "Allowance Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForAllowanceDeliveryReport.isDisplayed(), "Generated time for Allowance Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForAllowanceDeliveryReport.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Allowance Delivery Report is empty");
		System.out.println("Allowance Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedQCSingleAuction() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");
		js.executeScript("arguments[0].scrollIntoView();", qualifiedBiddersReportText);
		Assert.assertTrue(emissionUnitsDeliveryReportText.isDisplayed(), "Emission Units Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForEmissionUnitsDeliveryRepor.isDisplayed(), "Generated time for Emission Units Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForEmissionUnitsDeliveryRepor.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Emission Units Delivery Report is empty");
		System.out.println("Emission Units Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedWASingleAuction() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));

		//		Assert.assertTrue(postEventOperationsReportText.isDisplayed(), "Post Event Operations Report Text is not displayed");
		//		Assert.assertTrue(generatedTimeForPostEventOperationsReport.isDisplayed(), "Generated time for Post Event Operations Report is not displayed");
		//		String generatedTime0 = generatedTimeForPostEventOperationsReport.getText().trim();
		//	    Assert.assertFalse(generatedTime0.isEmpty(), "Generated time for Post Event Operations Report is empty");
		//		System.out.println("Post Event Operations Report is generated for download.");
		//		ObjectRepo.getTest().log(Status.PASS, "Post Event Operations Report is generated for download.");

		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		Assert.assertTrue(allowanceDeliveryReportText.isDisplayed(), "Allowance Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForAllowanceDeliveryReport.isDisplayed(), "Generated time for Allowance Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForAllowanceDeliveryReport.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Allowance Delivery Report is empty");
		System.out.println("Allowance Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedQCSBMAEvent() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		//		Assert.assertTrue(salesbyMutualAgreementBidLotAuditReportText.isDisplayed(), "Sales by Mutual Agreement Bid Lot Audit Report Text is not displayed");
		//		Assert.assertTrue(generatedTimeForSalesbyMutualAgreementBidLotAuditReport.isDisplayed(), "Generated time for Sales by Mutual Agreement Bid Lot Audit Report is not displayed");
		//		String generatedTime3 = generatedTimeForSalesbyMutualAgreementBidLotAuditReport.getText().trim();
		//	    Assert.assertFalse(generatedTime3.isEmpty(), "Sales by Mutual Agreement Bid Lot Audit Report is empty");
		//		System.out.println("Sales by Mutual Agreement Bid Lot Audit Report is generated for download.");
		//		ObjectRepo.getTest().log(Status.PASS, "Sales by Mutual Agreement Bid Lot Audit Report is generated for download.");
		js.executeScript("arguments[0].scrollIntoView();", qualifiedBiddersReportText);
		Assert.assertTrue(emissionUnitsDeliveryReportText.isDisplayed(), "Emission Units Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForEmissionUnitsDeliveryRepor.isDisplayed(), "Generated time for Emission Units Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForEmissionUnitsDeliveryRepor.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Emission Units Delivery Report is empty");
		System.out.println("Emission Units Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Emission Units Delivery Report is generated for download.");
		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");
		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedWAAPCREvent() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		//		Assert.assertTrue(allowancePriceContainmentReserveBidLotAuditReportText.isDisplayed(), "Allowance Price Containment Reserve Bid Lot Audit Report Text is not displayed");
		//		Assert.assertTrue(generatedTimeForAllowancePriceContainmentReserveBidLotAuditReport.isDisplayed(), "Generated time for Allowance Price Containment Reserve Bid Lot Audit Report is not displayed");
		//		String generatedTime3 = generatedTimeForAllowancePriceContainmentReserveBidLotAuditReport.getText().trim();
		//	    Assert.assertFalse(generatedTime3.isEmpty(), "Allowance Price Containment Reserve Bid Lot Audit Report is empty");
		//		System.out.println("Allowance Price Containment Reserve Bid Lot Audit Report is generated for download.");
		//		ObjectRepo.getTest().log(Status.PASS, "Allowance Price Containment Reserve Bid Lot Audit Report is generated for download.");

		Assert.assertTrue(allowanceDeliveryReportText.isDisplayed(), "Allowance Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForAllowanceDeliveryReport.isDisplayed(), "Generated time for Allowance Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForAllowanceDeliveryReport.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Allowance Delivery Report is empty");
		System.out.println("Allowance Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

	public void verifyAllReportsAreDisplayedJointCAQCAuction() {
		wait.until(ExpectedConditions.visibilityOf(bidsAuditReportText));
		Assert.assertTrue(bidsAuditReportText.isDisplayed(), "Bids Audit Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsAuditReport.isDisplayed(), "Generated time for Bids Audit Report is not displayed");
		String generatedTime = generatedTimeForBidsAuditReport.getText().trim();
		Assert.assertFalse(generatedTime.isEmpty(), "Generated time for Bids Audit Reportt is empty");
		System.out.println("Bids Audit Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Audit Report is generated for download.");

		Assert.assertTrue(financialSettlementReportText.isDisplayed(), "Financial Settlement Report Text is not displayed");
		Assert.assertTrue(generatedTimeForFinancialSettlementReport.isDisplayed(), "Generated time for Financial Settlement Report is not displayed");
		String generatedTime1 = generatedTimeForFinancialSettlementReport.getText().trim();
		Assert.assertFalse(generatedTime1.isEmpty(), "Financial Settlement Report is empty");
		System.out.println("Financial Settlement Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Financial Settlement Report is generated for download.");

		Assert.assertTrue(bidsReportText.isDisplayed(), "Bids Report Text is not displayed");
		Assert.assertTrue(generatedTimeForBidsReport.isDisplayed(), "Generated time for Bids Report is not displayed");
		String generatedTime2 = generatedTimeForBidsReport.getText().trim();
		Assert.assertFalse(generatedTime2.isEmpty(), "Bids Report is empty");
		System.out.println("Bids Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Bids Report is generated for download.");

		Assert.assertTrue(allowanceDeliveryReportText.isDisplayed(), "Allowance Delivery Report Text is not displayed");
		Assert.assertTrue(generatedTimeForAllowanceDeliveryReport.isDisplayed(), "Generated time for Allowance Delivery Report is not displayed");
		String generatedTime4 = generatedTimeForAllowanceDeliveryReport.getText().trim();
		Assert.assertFalse(generatedTime4.isEmpty(), "Allowance Delivery Report is empty");
		System.out.println("Allowance Delivery Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Allowance Delivery Report is generated for download.");

		Assert.assertTrue(resultsReportText.isDisplayed(), "Results Report Text is not displayed");
		Assert.assertTrue(generatedTimeForResultsReport.isDisplayed(), "Generated time for Results Report is not displayed");
		String generatedTime5 = generatedTimeForResultsReport.getText().trim();
		Assert.assertFalse(generatedTime5.isEmpty(), "Results Report is empty");
		System.out.println("Results Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Results Report is generated for download.");

		Assert.assertTrue(qualifiedBiddersReportText.isDisplayed(), "Qualified Bidders Report Text is not displayed");
		Assert.assertTrue(generatedTimeForQualifiedBiddersReport.isDisplayed(), "Generated time for Qualified Bidders Report is not displayed");
		System.out.println("Qualified Bidders Report is generated for download.");
		ObjectRepo.getTest().log(Status.PASS, "Qualified Bidders Report is generated for download.");
	}

}
