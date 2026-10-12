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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;

import static constants.EventNameConstants.*;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class WAAuctionBiddingPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(xpath = "//button//span[text()='ADD BID']")
	private WebElement btnAddbid;
	
	@FindBy(xpath = "//button//span[text()='UPLOAD BIDS']")
	private WebElement btnUPLOADBIDS;

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

	@FindBy(xpath = "//*[text()='CONTINUER']")
	private WebElement CONTINUER_Button;
	
	@FindBy(xpath = "//*[text()='CONTINUE']")
	private WebElement CONTINUE_Button;
	
	
	private By btnEntity5 = By.xpath("//span[text()='" + prop.getProperty("FirstEntity") + "']");
	private By btnEntity6 = By.xpath("//span[text()='" + prop.getProperty("SecondEntity") + "']");
	private By btnEntity7 = By.xpath("//span[text()='" + prop.getProperty("ThirdEntity") + "']");
	private By btnEntityWA1 = By.xpath("//span[text()='" + prop.getProperty("WA_FirstEntity") + "']");
	private By btnEntityWA2 = By.xpath("//span[text()='" + prop.getProperty("WA_SecondEntity") + "']");
	private By btnEntityWA3 = By.xpath("//span[text()='" + prop.getProperty("WA_ThirdEntity") + "']");
	private By btnEntityWA4 = By.xpath("//span[text()='" + prop.getProperty("WA_FourthEntity") + "']");
	private By btnEntityWA5 = By.xpath("//span[text()='" + prop.getProperty("WA_FifthEntity") + "']");
	private By btnEntityCA1 = By.xpath("//span[text()='T6CAEmetteurA (" + prop.getProperty("CA_FirstEntity") + ")']");
	private By btnEntityCA2 = By.xpath("//span[text()='T6CAEmetteurB (" + prop.getProperty("CA_SecondEntity") + ")']");
	private By btnEntityCA3 = By.xpath("//span[text()='T6CAEmetteurC (" + prop.getProperty("CA_ThirdEntity") + ")']");
	private By btnEntityCA4 = By.xpath("//span[text()='T6CAEmetteurD (" + prop.getProperty("CA_FourthEntity") + ")']");
	private By btnEntityCA5 = By.xpath("//span[text()='T6CAEmetteurE (" + prop.getProperty("CA_FifthEntity") + ")']");
	private By btnEntityQC1 = By.xpath("//span[text()='T6QCEmetteurB (" + prop.getProperty("QC_FirstEntity") + ")']");
	private By btnEntityQC2 = By.xpath("//span[text()='T6QCEmetteurE (" + prop.getProperty("QC_SecondEntity") + ")']");
	private By btnEntityQC3 = By.xpath("//span[text()='T6QCEmetteurK (" + prop.getProperty("QC_ThirdEntity") + ")']");
	private By btnEntityQC4 = By.xpath("//span[text()='T6QCEmetteurL (" + prop.getProperty("QC_FourthEntity") + ")']");
	private By btnEntityQC5 = By.xpath("//span[text()='T6QCPPMA (" + prop.getProperty("QC_FifthEntity") + ")']");

	public WAAuctionBiddingPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }

	public void searchEntity() {
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FirstEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
	}

	public void SelectEntity1()   {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity5));
		driver.findElement(btnEntity5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity1_WA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("WA_FirstEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityWA1));
		driver.findElement(btnEntityWA1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Washington Entity by AR");
	}

	public void SelectEntity2_WA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("WA_SecondEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityWA2));
		driver.findElement(btnEntityWA2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Washington Entity by AR");
	}

	public void SelectEntity3_WA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("WA_ThirdEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityWA3));
		driver.findElement(btnEntityWA3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Washington Entity by AR");
	}

	public void SelectEntity4_WA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("WA_FourthEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityWA4));
		driver.findElement(btnEntityWA4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Washington Entity by AR");
	}

	public void SelectEntity5_WA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("WA_FifthEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityWA5));
		driver.findElement(btnEntityWA5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Washington Entity by AR");
	}

	public void SelectEntity1_CA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CA_FirstEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityCA1));
		driver.findElement(btnEntityCA1).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected California Entity by AR");
	}

	public void SelectEntity2_CA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CA_SecondEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityCA2));
		driver.findElement(btnEntityCA2).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected California Entity by AR");
	}

	public void SelectEntity3_CA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CA_ThirdEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityCA3));
		driver.findElement(btnEntityCA3).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected California Entity by AR");
	}

	public void SelectEntity4_CA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CA_FourthEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityCA4));
		driver.findElement(btnEntityCA4).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected California Entity by AR");
	}

	public void SelectEntity5_CA()   {
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("CA_FifthEntity"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityCA5));
		driver.findElement(btnEntityCA5).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected California Entity by AR");
	}

	public void SelectEntity1_QC()   {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityQC1));
		driver.findElement(btnEntityQC1).click();
		try {
			wait.until(ExpectedConditions.visibilityOf(CONTINUER_Button));
			CONTINUER_Button.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(CONTINUE_Button));
			CONTINUE_Button.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Selected Quebec Entity by AR");
	}

	public void SelectEntity2_QC()   {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityQC2));
		driver.findElement(btnEntityQC2).click();
		try {
			wait.until(ExpectedConditions.visibilityOf(CONTINUER_Button));
			CONTINUER_Button.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(CONTINUE_Button));
			CONTINUE_Button.click();
		}
		ObjectRepo.getTest().log(Status.PASS, "Selected Quebec Entity by AR");
	}

	public void SelectEntity3_QC()   {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityQC3));
		driver.findElement(btnEntityQC3).click();
		wait.until(ExpectedConditions.visibilityOf(CONTINUER_Button));
		CONTINUER_Button.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Quebec Entity by AR");
	}

	public void SelectEntity4_QC()   {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityQC4));
		driver.findElement(btnEntityQC4).click();
		wait.until(ExpectedConditions.visibilityOf(CONTINUER_Button));
		CONTINUER_Button.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Quebec Entity by AR");
	}

	public void SelectEntity5_QC()   {
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnEntityQC5));
		driver.findElement(btnEntityQC5).click();
		wait.until(ExpectedConditions.visibilityOf(CONTINUER_Button));
		CONTINUER_Button.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected Quebec Entity by AR");
	}

	public void SelectEntity2()   {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity6));
		driver.findElement(btnEntity6).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity3()   {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(btnEntity7));
		driver.findElement(btnEntity7).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR1 entity1 account");
	}

	public void SelectEntity4() {
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
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
				.visibilityOfAllElementsLocatedBy(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']")));
		driver.findElement(By.xpath("//input[@placeholder='Enter Entity Name or Entity ID']"))
				.sendKeys(prop.getProperty("FifthEntity"));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		driver.findElement(By.xpath(
				"(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]"))
				.click();
		ObjectRepo.getTest().log(Status.PASS, ":Selected AR2 entity6 account");
	}

	public void BiddingByARforTestData1()   {
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
		textBidPrice.sendKeys(prop.getProperty("Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA1()   {
		try {
			Thread.sleep(12000);
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData2()   {
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

	public void BiddingByARforTestData_WA2()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price2"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA3()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price3"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA4()   {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		WebElement element = btnAddbid;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		try {
			action.click(element).build().perform();
		} catch (Exception e) {
			element.click();
		}
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price4"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA5()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price5"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA6()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price6"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA7()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price7"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA8()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price8"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA9()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price9"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA10()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price10"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA11()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price11"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot11"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA12()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price12"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot12"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA13()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price13"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot13"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA14()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price14"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot14"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA15()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price15"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot15"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA16()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price16"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot16"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA17()   {
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price17"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot17"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA18()   {
		try {
			Thread.sleep(4000);
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price18"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot18"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA19()   {
		try {
			Thread.sleep(4000);
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price19"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot19"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_WA20()   {
		try {
			Thread.sleep(4000);
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
		textBidPrice.sendKeys(prop.getProperty("WA_Bid_Price20"));
		textBidLots.sendKeys(prop.getProperty("WA_Bid_Lot20"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA1()   {
		try {
			Thread.sleep(17000);
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA2()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price2"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA3()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price3"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA4()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price4"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA5()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price5"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA6()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price6"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA7()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price7"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA8()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price8"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA9()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price9"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA10()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price10"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA11()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price11"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot11"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA12()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price12"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot12"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA13()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price13"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot13"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA14()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price14"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot14"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA15()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price15"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot15"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA16()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price16"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot16"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA17()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price17"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot17"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA18()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price18"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot18"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA19()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price19"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot19"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_CA20()   {
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
		textBidPrice.sendKeys(prop.getProperty("CA_Bid_Price20"));
		textBidLots.sendKeys(prop.getProperty("CA_Bid_Lot20"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
		String Massage2 = alertMsg.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC1()   {
		try {
			Thread.sleep(17000);
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price1"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot1"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC2()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price2"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot2"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC3()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price3"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot3"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC4()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price4"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot4"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC5()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price5"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot5"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC6()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price6"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot6"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC7()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price7"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot7"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC8()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price8"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot8"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC9()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price9"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot9"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC10()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price10"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot10"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC11()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price11"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot11"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC12()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price12"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot12"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC13()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price13"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot13"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC14()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price14"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot14"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC15()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price15"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot15"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC16()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price16"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot16"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC17()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price17"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot17"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC18()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price18"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot18"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC19()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price19"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot19"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData_QC20()   {
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
		textBidPrice.sendKeys(prop.getProperty("QC_Bid_Price20"));
		textBidLots.sendKeys(prop.getProperty("QC_Bid_Lot20"));
		dropdownvintage.sendKeys("Current");
		btnSubmit.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnConfirm));
		btnConfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(alertMsg1));
		String Massage2 = alertMsg1.getText();
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData3()   {
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

	public void BiddingByARforTestData4()   {
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

	public void selectEventForBidding()   {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			driver.findElement(By.xpath("//*[text()='" + eventNameEng + "']")).click();
			System.out.println("Navigae to particular Event: " + eventNameEng);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameEng);
		}
	}

	public void selectEventForBiddingWA()   {
//		try {
//			driver.findElement(By.xpath("//*[text()='Auction']")).click();
//		} catch (Exception e) {
//			driver.findElement(By.xpath("//*[text()='Events']")).click();
//			driver.findElement(By.xpath("//*[text()='" + eventNameWS + "']")).click();
//			System.out.println("Navigae to particular Event: " + eventNameWS);
//			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameWS);
//		}
		try {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
		}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']")));
			driver.findElement(By.xpath("//*[text()='" + prop.getProperty("eventNameWS") + "']"))
					.click();
			System.out.println("Navigae to particular Event: " + eventNameWS);
			ObjectRepo.getTest().log(Status.PASS,
					"Navigae to particular Event: " + prop.getProperty("eventNameWS") + "");
		
	}

	public void selectEventForBiddingCA()   {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + eventNameCA + "']")));
			driver.findElement(By.xpath("//*[text()='" + eventNameCA + "']")).click();
			System.out.println("Navigae to particular Event: " + eventNameCA);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameCA);
		}
	}

	public void selectEventForBiddingQC()   {
		try {
			driver.findElement(By.xpath("//*[text()='Auction']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text()='Events']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + eventNameQC + "']")));
			driver.findElement(By.xpath("//*[text()='" + eventNameQC + "']")).click();
			System.out.println("Navigae to particular Event: " + eventNameQC);
			ObjectRepo.getTest().log(Status.PASS, "Navigae to particular Event: " + eventNameQC);
		}
	}

	public void bidUploadTemplate1()  throws IOException  {
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

	public void bidUploadTemplate2()  throws IOException  {
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

	public void bidUploadTemplate3()  throws IOException  {
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

	public void bidUploadTemplate4()  throws IOException  {
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

	public void bidUploadTemplate5() throws IOException    {
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

	public void BiddingByARforTestData5()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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

	public void BiddingByARforTestData6()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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

	public void BiddingByARforTestData7()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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

	public void BiddingByARforTestData8()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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

	public void BiddingByARforTestData9()   {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
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

	public void BiddingByARforTestData10()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData11()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData12()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData13()   {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData14()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData15()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData16()   {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData17()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData18()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData19()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(alertMsg));
			Massage2 = alertMsg.getText();
		}
		System.out.println(Massage2);
		alertPopup.click();
	}

	public void BiddingByARforTestData20()   {
		wait.until(ExpectedConditions.visibilityOf(btnAddbid));
		btnAddbid.click();
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
}