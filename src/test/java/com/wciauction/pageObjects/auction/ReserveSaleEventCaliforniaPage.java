package com.wciauction.pageObjects.auction;

import java.io.IOException;
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

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;

import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class ReserveSaleEventCaliforniaPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//select[@name='tier']")
	private WebElement selectTier;

	@FindBy(xpath = "//input[@id='bidLot']")
	private WebElement textBidLot;

	@FindBy(xpath = "//button//span[text()='ADD BID']")
	private WebElement btnAddbid;

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

	@FindBy(xpath = "//*[text()='Bid successfully added.']")
	private WebElement alertMsg;

	@FindBy(xpath = "//*[text()='Bid successfully submitted.']")
	private WebElement alertMsg1;

	@FindBy(xpath = "//*[@data-icon='times']")
	private WebElement alertPopup;

	private By btnEntity5 = By.xpath("//span[text()='" + prop.getProperty("CAFirstEntity") + "']");
	private By btnEntity6 = By.xpath("//span[text()='" + prop.getProperty("CASecondEntity") + "']");
	private By btnEntity7 = By.xpath("//span[text()='" + prop.getProperty("CAThirdEntity") + "']");
	private By ResSaleEntity1 = By.xpath("//span[contains(text(),'" + prop.getProperty("ResEntity1") + "')]");
	private By ResSaleEntity2 = By.xpath("//span[contains(text(),'" + prop.getProperty("ResEntity2") + "')]");
	private By ResSaleEntity3 = By.xpath("//span[contains(text(),'" + prop.getProperty("ResEntity3") + "')]");
	private By ResSaleEntity4 = By.xpath("//span[contains(text(),'" + prop.getProperty("ResEntity4") + "')]");
	private By ResSaleEntity5 = By.xpath("//span[contains(text(),'" + prop.getProperty("ResEntity5") + "')]");
	private By ResSaleR70Entity1 = By
			.xpath("//span[contains(text(),'" + prop.getProperty("QCFirstEntity_RE1") + "')]");
	private By ResSaleR70Entity2 = By
			.xpath("//span[contains(text(),'" + prop.getProperty("QCSecondEntity_RE2") + "')]");
	private By ResSaleR70Entity3 = By
			.xpath("//span[contains(text(),'" + prop.getProperty("QCThirdEntity_RE3") + "')]");
	private By ResSaleR70Entity4 = By
			.xpath("//span[contains(text(),'" + prop.getProperty("QCForthEntity_RE4") + "')]");
	private By ResSaleR70Entity5 = By
			.xpath("//span[contains(text(),'" + prop.getProperty("QCFifthEntity_RE5") + "')]");

	public ReserveSaleEventCaliforniaPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
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

	public void SelectEntity1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity5));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
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
				.sendKeys(prop.getProperty("FourthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void SelectEntity5() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FifthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
	}

	public void CASelectEntity1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity5));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void CASelectEntity2() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity1));
		driver.findElement(ResSaleEntity1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void CASelectEntity1ForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity1));
		driver.findElement(ResSaleEntity1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity2 account");
	}

	public void CASelectEntity2ForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity2));
		driver.findElement(ResSaleEntity2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity3 account");
	}

	public void CASelectEntity3ForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity3));
		driver.findElement(ResSaleEntity3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void CASelectEntity4ForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity4));
		driver.findElement(ResSaleEntity4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void CASelectEntity5ForReserveSale() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleEntity5));
		driver.findElement(ResSaleEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity5 account");
	}

	public void WASelectEntity1ForReserveSale() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("ResEntity1_WA"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='rollDownProcedure']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity2 account");
	}

	public void WASelectEntity2ForReserveSale() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("ResEntity2_WA"));
		driver.findElement(By.xpath("//input[@name='rollDownProcedure']")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity3 account");
	}

	public void WASelectEntity3ForReserveSale() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("ResEntity3_WA"));
		driver.findElement(By.xpath("//input[@name='rollDownProcedure']")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void WASelectEntity4ForReserveSale() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("ResEntity4_WA"));
		driver.findElement(By.xpath("//input[@name='rollDownProcedure']")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void WASelectEntity5ForReserveSale() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("ResEntity5_WA"));
		driver.findElement(By.xpath("//input[@name='rollDownProcedure']")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity5 account");
	}

	public void CASelectEntity3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void CASelectEntity4() {
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

	public void CASelectEntity5() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CAFifthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
	}

	public void QCSelectEntity1() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity5));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void QCSelectEntity2() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity6));
		driver.findElement(btnEntity6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void QCSelectEntity3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void QCSelectEntity4() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FourthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void QCSelectEntity5() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FifthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
	}

	public void BiddingByARforTestData1() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price2"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price3"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
		textBidPrice.sendKeys(prop.getProperty("Bid_Price4"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void selectEventForBidding() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("eventNameEng") + "']")));
			driver.findElement(By.xpath("//*[text()='" + eventNameEng + "']")).click();
			System.out.println("Navigae to particular Event: " + eventNameEng);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameEng);
		}
	}

	public void selectEventForBiddingForReserveSale() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Reserve Sale']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']")));
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName") + "']"))
					.click();
			System.out.println("Navigae to particular Event: " + ReserveSaleEventName);
			ObjectRepo.getTest().log(Status.PASS,
					"Navigae to particular Event: " + prop.getProperty("ReserveSaleEventName") + "");
		}
	}

	public void selectEventForBiddingForReserveSaleR70() throws InterruptedException {
		driver.findElement(By.xpath("//*[text()='Events']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("Event_Name_RE1") + "']")));
		driver.findElement(By.xpath("//a[text()='" + prop.getProperty("Event_Name_RE1") + "']")).click();
		System.out.println("Navigae to particular Event: " + prop.getProperty("Event_Name_RE1"));
		ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + prop.getProperty("Event_Name_RE1") + "");
	}

	public void selectEventForBiddingForReserveSaleRS_WA() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//*[text()='Reserve Sale']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']")));
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("ReserveSaleEventName_WA") + "']"))
					.click();
			System.out.println("Navigae to particular Event: " + ReserveSaleEventName_WA);
			ObjectRepo.getTest().log(Status.PASS,
					"Navigae to particular Event: " + prop.getProperty("ReserveSaleEventName_WA") + "");
		}
	}

	public void bidUploadTemplate1() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUpload1.exe");
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
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUpload2.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
		String successmsg = driver.findElement(By.xpath("//*[contains(text(),'bids uploaded successfully.')]"))
				.getText();
		System.out.println("Total no of bids placed using template: " + successmsg);
		ObjectRepo.getTest().log(Status.PASS, "Total no of bids placed using template: " + successmsg);

	}

	public void bidUploadTemplate3() throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='UPLOAD BIDS']")));
		WebElement uploadElement = driver.findElement(By.xpath("//*[@title='UPLOAD BIDS']"));
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUpload3.exe");
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
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUpload4.exe");
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
		uploadElement.click();
		Runtime.getRuntime().exec("C:\\Users\\Geeta\\Desktop\\AutoIT\\BidUpload5.exe");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='CONTINUE']")));
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		System.out.println("File uploaded successfully ");
		String successmsg = driver.findElement(By.xpath("//*[contains(text(),'bids uploaded successfully.')]"))
				.getText();
		System.out.println("Total no of bids placed using template: " + successmsg);
		ObjectRepo.getTest().log(Status.PASS, "Total no of bids placed using template: " + successmsg);

	}

	public void BiddingByARforTestData5() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price5"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData6() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price6"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData7() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price7"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData8() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price8"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData9() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		wait.until(ExpectedConditions.elementToBeClickable(alertPopup)).click();
	}

	public void BiddingByARforTestData10() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price10"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot0"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price11"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price12"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price13"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price14"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price15"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price16"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price17"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price18"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		String Massage2;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidPrice.sendKeys(prop.getProperty("Bid_Price19"));
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1ForReserveSale() throws Exception {
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot1"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot2"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot3"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData1ForReserveSale_WA() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot1_WA"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2ForReserveSale_WA() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot2_WA"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3ForReserveSale_WA() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot3_WA"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4ForReserveSale_WA() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot4_WA"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5ForReserveSale_WA() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot5_WA"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2ForReserveSale() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot4"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot5"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot6"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3ForReserveSale() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot7"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot8"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot9"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4ForReserveSale() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot10"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot11"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot12"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5ForReserveSale() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot13"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot14"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("RSbid_Lot15"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void ReserveSaleEventBiddingForEntity1() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("bid_Lot1"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot2"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot3"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void ReserveSaleEventBiddingForEntity2() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("bid_Lot4"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot5"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot6"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void ReserveSaleEventBiddingForEntity3() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("bid_Lot7"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot8"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot9"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void ReserveSaleEventBiddingForEntity4() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("bid_Lot10"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot11"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot12"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void ReserveSaleEventBiddingForEntity5() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("bid_Lot13"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("Tier One");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot14"));
		WebElement element1 = selectTier;
		Select s1 = new Select(element1);
		s1.selectByVisibleText("Tier Two");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		Thread.sleep(7000);
		btnAddbid.click();
		textBidLot.sendKeys(prop.getProperty("bid_Lot15"));
		WebElement element2 = selectTier;
		Select s2 = new Select(element2);
		s2.selectByVisibleText("Tier Three");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void CASelectEntity1ForReserveSaleR70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity1));
		driver.findElement(ResSaleR70Entity1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity2 account");
	}

	public void CASelectEntity2ForReserveSaleR70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity2));
		driver.findElement(ResSaleR70Entity2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity2 account");
	}

	public void CASelectEntity3ForReserveSaleR70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity3));
		driver.findElement(ResSaleR70Entity3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity3 account");
	}

	public void CASelectEntity4ForReserveSaleR70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity4));
		driver.findElement(ResSaleR70Entity4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity4 account");
	}

	public void CASelectEntity5ForReserveSaleR70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity5));
		driver.findElement(ResSaleR70Entity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity5 account");
	}

	public void BiddingByARforTestData1ForReserveSaleR70() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("Bid_Lot1__RE1"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("CATEGORY A");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2ForReserveSaleR70() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("Bid_Lot1__RE2"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("CATEGORY A");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3ForReserveSaleR70() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("Bid_Lot1__RE3"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("CATEGORY B");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData4ForReserveSaleR70() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("Bid_Lot1__RE4"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("CATEGORY C");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData5ForReserveSaleR70() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.tagName("body")).sendKeys(Keys.DOWN);
		try {
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
			btnAddbid.click();
		} catch (Exception e) {
			btnAddbid.click();
		}
		textBidLot.sendKeys(prop.getProperty("Bid_Lot1__RE4"));
		WebElement element0 = selectTier;
		Select s = new Select(element0);
		s.selectByVisibleText("CATEGORY C");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void QCSelectEntityForSBMA_R70() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ResSaleR70Entity3));
		driver.findElement(ResSaleR70Entity3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity3 account");
	}

}
