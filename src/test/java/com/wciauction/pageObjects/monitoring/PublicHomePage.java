package com.wciauction.pageObjects.monitoring;

import java.util.ArrayList;
import com.wciauction.requiredData.UsersData;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class PublicHomePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench1;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement langBtnENG;

	@FindBy(xpath = "//*[@class='MuiToolbar-root MuiToolbar-dense']//a")
	private List<WebElement> menuTabs;

	@FindBy(xpath = "//button[contains(@class,'MuiButtonBase-root MuiButton-root MuiButton')]")
	private List<WebElement> menuButton;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement langBtnEng;

	@FindBy(xpath = "(//*[text()='Log in'])") // //*[text()='Log in']
	private WebElement btnLogin;

	@FindBy(xpath = "//button[@class=\"MuiButtonBase-root MuiIconButton-root\"]")
	private WebElement btnClose;

	@FindBy(xpath = "//*[@id='enter_email']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='enter_passphrase']")
	private WebElement password;

	@FindBy(xpath = "(//span[text()='Log in'])[2]")
	private WebElement btnLogin2;

	@FindBy(xpath = "//label[@class='MuiFormLabel-root Mui-required']")
	private WebElement securityQue;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInput-input']")
	private WebElement securityAnswer;

	@FindBy(xpath = "//button//span[contains(text(),'NEXT')]")
	private WebElement btnNext;

	@FindBy(xpath = "//span[contains(text(),'SOUMETTRE')]")
	private WebElement btnNextQC;

	// Shanmukh
//	private By allLink = By.xpath("//a");
	@FindBy(xpath = "//a[contains(text(),'Home')]")
	private WebElement btnHome;

	@FindBy(xpath = "//a[contains(@title,'About WCI, Inc.')]")
	private WebElement btnAboutWCI;

	@FindBy(xpath = "//*[contains(text(),'Copyright © 2021')]")
	private WebElement btnCopyRight;

	@FindBy(xpath = "//a[contains(@title,'Terms of Use')]")
	private WebElement btnTermsofUse;

	@FindBy(xpath = "//a[contains(@title,'Privacy Policy')]")
	private WebElement btnPrivacyPolicy;

	@FindBy(xpath = "//a[contains(@title,'Contact Us')]")
	private WebElement btnContactUs;

	@FindBy(xpath = "//div[contains(@class,'MuiExpansionPanelDetails-root jss510')]")
	private WebElement eventDetails;

	@FindBy(xpath = "//*[contains(text(),'FAQs & Resources')]")
	private WebElement btnFAQResources;

	@FindBy(xpath = "//a[contains(text(),'California')]")
	private WebElement selectCAL;

	@FindBy(xpath = "//span[text()='FAQs & Resources - ']")
	private WebElement FAQ;

	@FindBy(xpath = "//a[contains(text(),'Quebec')]")
	private WebElement selectQUE;

	@FindBy(xpath = "//span[@class='jss570']")
	private WebElement FAQQUE;

	@FindBy(xpath = "//a[contains(text(),'Nova Scotia')]")
	private WebElement selectNS;

	@FindBy(xpath = "//span[@class='jss570']")
	private WebElement FAQNS;

	@FindBy(xpath = "//a[contains(text(),'Washington')]")
	private WebElement selectWS;

	@FindBy(xpath = "//span[@class='jss570']")
	private WebElement FAQWS;

	@FindBy(xpath = "//span[contains(text(),'RESOURCES')]")
	private WebElement btnResources;

	@FindBy(xpath = "(//button[1]//span[contains(text(),'FAQs')])[2]")
	private WebElement btnFAQ;

	@FindBy(xpath = "//span[contains(text(),'Send')]")
	private WebElement btnSend;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your full name')]")
	private WebElement textName;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your phone with country code')]")
	private WebElement textPhone;

	@FindBy(xpath = "//input[contains(@placeholder,'Enter your email address')]")
	private WebElement textEmail;

	@FindBy(xpath = "//*[@id='tinymce']")
	private WebElement textMsg;

	@FindBy(xpath = "//*[contains(text(),'DISCARD')]")
	private WebElement btnDiscard;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	private WebElement btnCancel;

	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	private WebElement btnContinue;

	@FindBy(xpath = "//a[contains(text(),'Contact Us')]")
	private WebElement btnContact;

	@FindBy(xpath = "//*[text()='[Auction Date] Joint Auction #[Auction #] Notice']")
	private WebElement btnTextAuctionDate;

	@FindBy(xpath = "//p[contains(text(),'Detailed Auction Requirements')]")
	private WebElement btnTextDeatiledAuction;

	@FindBy(xpath = "//p[contains(text(),'Auction Example')]")
	private WebElement btnTextAuctionEx;

	@FindBy(xpath = "//p[contains(text(),' Summary Results Report')]")
	private WebElement btnTextSummaryReport;

	@FindBy(xpath = "//*[text()='Reports']")
	private WebElement btnReports;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	private WebElement btnBidsQualifiedBidderReports;

	@FindBy(xpath = "(//button[@type='button'])[6]")
	private WebElement btnBidsAuditReports;

	@FindBy(xpath = "(//button[@type='button'])[7]")
	private WebElement btnBidsReports;

	@FindBy(xpath = "//*[text()='Email address or passphrase is invalid.']")
	private WebElement loginFailureMsg;

	@FindBy(xpath = "//*[text()='Forgot Passphrase']")
	private WebElement forgotPassphraseBtn;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	private WebElement emailAdressField;

	@FindBy(xpath = "(//*[text()='Reserve Sale Certification'])[1]")
	private WebElement RSCertificationButton;

	@FindBy(xpath = "//*[@aria-label='secondAuctionCertified']")
	private WebElement CA2certificationbutton;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement submitBtncertification;

	@FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
	private WebElement submitBtncertificationsubmitpopup;

	@FindBy(xpath = "(//*[text()='Reserve Sale Certification'])[1]")
	private WebElement ReservesaleCertificationbuttonRSCA1;

	@FindBy(xpath = "(//*[text()='Sale Certification'])[1]")
	private WebElement ReservesaleCertificationbuttonRSQC1;

	@FindBy(xpath = "(//*[text()='Allowance Price Containment Reserve Certification'])[1]")
	private WebElement ReservesaleCertificationbuttonRS_WA1;

	@FindBy(xpath = "(//*[@aria-label='firstAuctionCertified'])[1]")
	private WebElement EventCertificationbuttonRSCA;

	@FindBy(xpath = "(//*[text()='SUBMIT'])[1]")
	private WebElement EventCertificationsubmitbuttonRSCA;

	@FindBy(xpath = "(//*[@aria-label='firstAuctionCertified'])[1]")
	private WebElement EventCertificationbuttonRSCA2;

	@FindBy(xpath = "(//*[@aria-label='secondAuctionCertified'])[1]")
	private WebElement EventCertificationbuttonRSCA3;

	public PublicHomePage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js = BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void ARLoginWS1() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("WAARusername1"));
			password.sendKeys(prop.getProperty("WAARPassword"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("WAARusername1"));
			password.sendKeys(prop.getProperty("WAARPassword"));
			btnLogin2.click();
			ObjectRepo.getTest().log(Status.PASS, "AR california login successfully");
		}
	}

	public void sponsorLoginWS2() throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UserSponsorWS2"));
		password.sendKeys("Test@1234");
		btnLogin2.click();
		System.out.println("Washington sponsor login successfully");
	}

	public void runTimeSponsorLoginWS() throws Exception {
		username.sendKeys(prop.getProperty("UserSponsorWS2"));
		password.sendKeys("Test@1234");
		btnLogin2.click();
		System.out.println("Washington sponsor login successfully");
	}

	public void EventCertificationbuttonRSCA1() throws InterruptedException {
		ReservesaleCertificationbuttonRSCA1.click();
		WebElement element111 = EventCertificationbuttonRSCA;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		EventCertificationsubmitbuttonRSCA.click();
		Thread.sleep(5000);
	}

	public void EventCertificationbuttonRSQC1() {
		ReservesaleCertificationbuttonRSQC1.click();
		EventCertificationbuttonRSCA.click();
		EventCertificationsubmitbuttonRSCA.click();

	}

	public void EventCertificationbuttonRS_WA1() {
		ReservesaleCertificationbuttonRS_WA1.click();
		WebElement element111 = EventCertificationbuttonRSCA;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		EventCertificationsubmitbuttonRSCA.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void EventCertificationbuttonRSCA2() {
		RSCertificationButton.click();
		WebElement element111 = CA2certificationbutton;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		submitBtncertificationsubmitpopup.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void EventCertificationbuttonRSQC2() {
		ReservesaleCertificationbuttonRSQC1.click();
		EventCertificationbuttonRSCA3.click();
		EventCertificationsubmitbuttonRSCA.click();

	}

	public void EventCertificationbuttonRS_WA2() {
		ReservesaleCertificationbuttonRS_WA1.click();
		WebElement element111 = EventCertificationbuttonRSCA3;

		js.executeScript("arguments[0].scrollIntoView();", element111);
		element111.click();
		submitBtncertification.click();
		submitBtncertificationsubmitpopup.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void changeLangauge() {
		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Évènements']")));
			}
			langBtnFrench.click();
			wait.until(ExpectedConditions.visibilityOf(langBtnEng));
			langBtnEng.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Langauge changed successfully from french to english ");
	}

	public void changeLangaugeAR() {
		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Évènements']")));
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
			}
			langBtnFrench.click();
			wait.until(ExpectedConditions.visibilityOf(langBtnEng));
			langBtnEng.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Langauge changed successfully from french to english ");
	}

	public void changeLangauge1() {
		try {
			Thread.sleep(4000);
			langBtnFrench.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(langBtnEng));
			langBtnEng.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(langBtnEng));
			langBtnEng.click();
		}
		System.out.println("Langauge changed successfully from french to english ");
	}

	public void changeLangaugetoFR() {
		try {
			Thread.sleep(4000);
			langBtnENG.click();
			Thread.sleep(2000);
			langBtnFrench1.click();
			System.out.println("Langauge changed successfully from english to french");
		} catch (Exception e) {
			System.out.println("Already French Available.");
		}
	}

	public void sponsorLoginCa2() {
		try {
			Thread.sleep(5000);
			ExpectedConditions.elementToBeClickable(btnLogin);
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(UsersData.userSponsorCa2);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + UsersData.userSponsorCa2);
		password.sendKeys(UsersData.password);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + UsersData.password);
		ExpectedConditions.elementToBeClickable(btnLogin2);
		try {
			btnLogin2.click();
		} catch (Exception e) {
			btnLogin2.click();
		}
		System.out.println("" + UsersData.userSponsorCa2 + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + UsersData.userSponsorCa2 + " Sponsor (California) login successfully");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void sponsorLoginCa() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}

		username.sendKeys(UsersData.userSponsorCa);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered: " + UsersData.userSponsorCa);

		password.sendKeys(UsersData.password);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered: " + UsersData.password);

		wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
		btnLogin2.click();

		System.out.println(UsersData.userSponsorCa + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS, UsersData.userSponsorCa + " Sponsor (California) login successfully");
	}

	public void runtimesponsorLoginCa() {
		username.sendKeys(UsersData.userSponsorCa);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + UsersData.userSponsorCa);
		password.sendKeys(UsersData.password);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + UsersData.password);
		btnLogin.click();
		System.out.println("" + UsersData.userSponsorCa + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + UsersData.userSponsorCa + " Sponsor (California) login successfully");
	}

	public void runtimesponsorLoginMM_CA() {
		username.sendKeys(prop.getProperty("UnameMMCa"));
		ObjectRepo.getTest().log(Status.PASS,
				"Market Monitoring Username Entered  : " + prop.getProperty("UserSponsorCa2"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitoring Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
	}

	public void secondSponsorLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		username.sendKeys(prop.getProperty("UserSponsorCa2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorCa2"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorCa2") + " Sponsor (California) login successfully");
	}

	public void sponsorLoginQC2() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UserSponsorQc2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQC2"));
		password.sendKeys(prop.getProperty("Password1"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
			btnLogin2.click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
			btnLogin2.click();
		}
		System.out.println("" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorQC2") + " Sponsor (Quebec) login successfully");
	}

	public void sponsorLoginQc() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UserSponsorQc"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
	}

	public void RuntimeSponsorLoginQc() {
		wait.until(ExpectedConditions.visibilityOf(username));
		username.sendKeys(prop.getProperty("UserSponsorQc2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc2"));
		password.sendKeys(prop.getProperty("Password1"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorQc2") + " Sponsor (Quebec) login successfully");
	}

	public void RuntimeSponsorLoginNS() {
		username.sendKeys(prop.getProperty("UserSponsorNS2"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
	}

	public void sponsorLoginNs() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		username.sendKeys(prop.getProperty("UserSponsorNS"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS"));
		password.sendKeys(prop.getProperty("NSPassword"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("NSPassword"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorNS") + " Sponsor (Nova Scotia) login successfully");
	}

	public void sponsorLoginNs2() {
		try {
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UserSponsorNS2"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorNS2"));
			password.sendKeys(prop.getProperty("Password1"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password1"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UserSponsorNS2") + " Sponsor (Nova Scotia) login successfully");
		}
	}

	public void sponsorLoginWS() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UserSponsorWS"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UserSponsorWS") + " Sponsor (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorWS") + " Sponsor (Washington) login successfully");
	}

	public void FSALoginCa() {
		try {
			username.sendKeys(UsersData.UnameFSACa);
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + UsersData.UnameFSACa);
			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + UsersData.password);
			Thread.sleep(2000);
			btnLogin.click();
			System.out.println("" + UsersData.UnameFSACa + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + UsersData.UnameFSACa + " FSA (California) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(UsersData.UnameFSACa);
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + UsersData.UnameFSACa);
			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + UsersData.password);
			btnLogin2.click();
			System.out.println("" + UsersData.UnameFSACa + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + UsersData.UnameFSACa + " FSA (California) login successfully");
		}

	}

	public void FSALoginQc() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UnameFSAQc"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
	}

	public void FSALoginQc2() {
		try {
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAQc"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAQc"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAQc") + " FSA (Quebec) login successfully");
		}
	}

	public void FSALoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameFSANS"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSANS"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSANS") + " FSA (Nova Scotia) login successfully");
		}
	}

	public void FSALoginWs() {
		username.sendKeys(prop.getProperty("UnameFSAWS"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSAWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
	}

	public void FSALoginWs1() {
		try {
			username.sendKeys(prop.getProperty("UnameFSAWS"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitor Username Entered  : " + prop.getProperty("UnameFSAWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("California MM login successfully");
			System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSAWS"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitor Username Entered  : " + prop.getProperty("UnameFSAWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSAWS") + " FSA (Washington) login successfully");
		}
	}

	public void accesstoUsers() {
		List<WebElement> list = menuTabs;
		int menu = list.size();
		for (int i = 0; i <= menu - 1; i++) {
			list.get(i).click();
			String str = list.get(i).getText();
			ObjectRepo.getTest().log(Status.PASS, "verified EventAdmin MenuTabs: " + str);
		}
		PublicHomePage pm = new PublicHomePage();
		pm.verify_PrivatePage_MenuBottons();
	}

	public void MMLoginCa() {
		try {
			username.sendKeys(UsersData.UnameMMCa);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered: " + UsersData.UnameMMCa);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered: " + UsersData.password);

			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
				btnLogin.click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
				btnLogin.click();
			}

			System.out.println("California MM login successfully");
			System.out.println(UsersData.UnameMMCa + " Market Monitor (California) login successfully");

			ObjectRepo.getTest().log(Status.PASS,
					UsersData.UnameMMCa + " Market Monitor (California) login successfully");

		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();

			wait.until(ExpectedConditions.elementToBeClickable(username));
			username.sendKeys(UsersData.UnameMMCa);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered: " + UsersData.UnameMMCa);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered: " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.UnameMMCa + " Market Monitor (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					UsersData.UnameMMCa + " Market Monitor (California) login successfully");
		}
	}

	public void MMLoginCa2() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}

		username.sendKeys(prop.getProperty("UnameMMCa"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMCa"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("California MM login successfully");
		System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMCa") + " Market Monitor  (California) login successfully");
	}

	public void MMLoginQc() {
		try {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
				btnLogin.click();
			} catch (Exception e) {
				btnLogin.click();
			}

			username.sendKeys(UsersData.UnameMMQc);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered: " + UsersData.UnameMMQc);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered: " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.UnameMMQc + " Market Monitor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.UnameMMQc + " Market Monitor (Quebec) login successfully");

		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();

			username.sendKeys(UsersData.UnameMMQc);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered: " + UsersData.UnameMMQc);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered: " + UsersData.password);

			btnLogin2.click();

			ObjectRepo.getTest().log(Status.PASS, "Quebec MM login successfully");
			System.out.println(UsersData.UnameMMQc + " Market Monitor (Quebec) login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.UnameMMQc + " Market Monitor (Quebec) login successfully");
		}
	}

	public void MMLoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameMMNS"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitor Username Entered  : " + prop.getProperty("UnameMMNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMNS"));
			ObjectRepo.getTest().log(Status.PASS,
					"Market Monitor Username Entered  : " + prop.getProperty("UnameMMNS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMNS") + " Market Monitor (Nova Scotia) login successfully");
		}
	}

	public void MMLoginWs() {
		username.sendKeys(prop.getProperty("UnameMMWS"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
	}

	public void MMLoginWs1() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameMMWS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UnameMMWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		} catch (Exception e) {
			username.sendKeys(prop.getProperty("UnameMMWS"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UnameMMWS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameMMWS") + " Market Monitor (Washington) login successfully");
		}
	}

	public void EALoginCa() {
		try {
			username.sendKeys(UsersData.UnameEACa);
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + UsersData.UnameEACa);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + UsersData.password);

			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
				btnLogin.click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
				btnLogin.click();
			}

			System.out.println(UsersData.UnameEACa + " Event Admin (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.UnameEACa + " Event Admin (California) login successfully");

		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();

			username.sendKeys(UsersData.UnameEACa);
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + UsersData.UnameEACa);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.UnameEACa + " Event Admin (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.UnameEACa + " Event Admin (California) login successfully");
		}

		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void EALoginQc() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UnameEAQc"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAQc"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameEAQc") + "Event Admin (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameEAQc") + " Event Admin (Quebec) login successfully");
	}

	public void EALoginQc2() {
		try {
			wait.until(ExpectedConditions.visibilityOf(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UnameEAQc"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAQc"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("UnameEAQc") + "Event Admin (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameEAQc") + " Event Admin (Quebec) login successfully");
	}

	public void EALoginNs() {
		try {
			username.sendKeys(prop.getProperty("UnameEANS"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameEANS") + "Event Admin (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEANS") + " Event Admin (Nova Scotia) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameEANS"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEANS"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));

			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameEANS") + "Event Admin (Nova Scotia) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameEANS") + " Event Admin (Nova Scotia) login successfully");
		}
	}

	public void EALoginWs() {
		username.sendKeys(prop.getProperty("UnameEAWS"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Username Entered  : " + prop.getProperty("UnameEAWS"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Event Admin Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UnameEAWS") + "Event Admin (Washington) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameEAWS") + " Event Admin (Washington) login successfully");
	}

	public void ARLoginCA() {
		try {
			username.sendKeys(UsersData.AR_USerName1);
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + UsersData.AR_USerName1);
			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "AR Password Entered  : " + UsersData.password);
			Thread.sleep(2000);
			btnLogin.click();
			System.out.println("" + UsersData.AR_USerName1 + " AR (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + UsersData.AR_USerName1 + " AR (California) login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(UsersData.AR_USerName1);
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + UsersData.AR_USerName1);
			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "AR Password Entered  : " + UsersData.password);
			btnLogin2.click();
			System.out.println("" + UsersData.AR_USerName1 + " AR (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + UsersData.AR_USerName1 + " AR (California) login successfully");
		}
	}

	public void ARLoginCAForReserveSale() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1RES") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES") + " Account Representative login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName1RES") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES") + " Account Representative login successfully");
		}
	}

	public void ARLoginCAForReserveSaleR70() {
		wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		btnLogin.click();
		username.sendKeys(prop.getProperty("AR_USerName1_RE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1_RE1"));
		password.sendKeys(prop.getProperty("AR_PassWord"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("AR_USerName1_RE1") + "Account Representative login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("AR_USerName1_RE1") + " Account Representative login successfully");
	}

	public void ARLoginWAForReserveSale() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES_WA"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES_WA"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
			btnLogin2.click();
			System.out
					.println("" + prop.getProperty("AR_USerName1RES_WA") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES_WA") + " Account Representative login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1RES_WA"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1RES_WA"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out
					.println("" + prop.getProperty("AR_USerName1RES_WA") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1RES_WA") + " Account Representative login successfully");
		}
	}

	public void ARLoginQCPED() {
		try {

			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("AR_USerName1_RE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1_RE1"));
		password.sendKeys(prop.getProperty("AR_PassWord"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("AR_USerName1_RE1") + "Account Representative login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("AR_USerName1_RE1") + " Account Representative login successfully");

	}

	public void ARLoginQC() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();

			username.sendKeys(UsersData.AR_USerName2);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Username Entered: " + UsersData.AR_USerName2);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered: " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.AR_USerName2 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					UsersData.AR_USerName2 + " Account Representative login successfully");

		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();

			username.sendKeys(UsersData.AR_USerName1);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Username Entered: " + UsersData.AR_USerName1);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered: " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.AR_USerName1 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					UsersData.AR_USerName1 + " Account Representative login successfully");
		}
	}

	public void ARLoginQC11() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName5"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName5"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName5") + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName5") + " Account Representative login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName5"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}
	}

	public void ARLoginQC_For_Single_Auction() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("Account_Representative_Qubec"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("Account_Representative_Qubec"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("Account_Representative_Qubec")
					+ "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS, "" + prop.getProperty("Account_Representative_Qubec")
					+ " Account Representative login successfully");
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
			btnLogin.click();
			username.sendKeys(prop.getProperty("Account_Representative_Qubec"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}
	}

	public void clickOnnext() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnNext));
			WebElement abc = btnNext;
			Actions action = new Actions(driver);
			action.moveToElement(abc).build().perform();
			action.click(abc).build().perform();
		} catch (Exception e) {
			WebElement abc = btnNext;
			Actions action = new Actions(driver);
			action.moveToElement(abc).build().perform();
			action.click(abc).build().perform();
		}
		System.out.println("Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void clickOnnextQC() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnNextQC));
			WebElement abc = btnNextQC;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
			action.click(abc).build().perform();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnNext));
			WebElement abc = btnNext;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
			action.click(abc).build().perform();
		}

	}

	public void handleSecurityQue() {
//		try {
//			Thread.sleep(9000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
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
		ObjectRepo.getTest().log(Status.PASS, "Handeled Security Questions.");
	}

	public void noOfBrockenLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int totalNoOfLinks = links.size();
		for (int i = 0; i <= totalNoOfLinks; i++) {
			ArrayList<String> brokenLinks = new ArrayList<String>();
			ArrayList<String> ActiveLinks = new ArrayList<String>();
			if (links.get(i).getAttribute("href").contains(null)) {
				brokenLinks.addAll(brokenLinks);
				System.out.println(brokenLinks);
			} else {
				ActiveLinks.addAll(ActiveLinks);
				System.out.println(ActiveLinks);
			}
		}
	}

	public void getVerifyAllBrokenLinks() {
		driver.findElement(By.tagName("a")).click();
	}

	public void getbtnHome() {
		wait.until(ExpectedConditions.elementToBeClickable(btnHome));
		btnHome.click();
	}

	public void getbtnAboutWCI() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAboutWCI));
		btnAboutWCI.click();
	}

	public void getbtnCopyRight() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCopyRight));
		btnCopyRight.click();
	}

	public void getbtnTermsofUse() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTermsofUse));
		new Actions(driver).moveToElement(btnTermsofUse).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnTermsofUse.click();
	}

	public void getbtnPrivacyPolicy() {
		wait.until(ExpectedConditions.elementToBeClickable(btnPrivacyPolicy));
		btnPrivacyPolicy.click();
	}

	public void getbtnContactUs() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContactUs));
		btnContactUs.click();
	}

	public void geteventDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(eventDetails));
		eventDetails.click();
	}

	public void getbtnFAQResources() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFAQResources));
		btnFAQResources.click();
	}

	public void getselectCAL() {
		wait.until(ExpectedConditions.elementToBeClickable(selectCAL));
		Assert.assertTrue(selectCAL.isDisplayed(), "Element is not displayed on the page");
		System.out.println(
				"Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed = California");
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed = California");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement abc1 = selectCAL;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		abc1.click();
		System.out.println("Step: Click on the Jurisdiction name hyperlink one by one.");
		ObjectRepo.getTest().log(Status.PASS, "Step: Click on the Jurisdiction name hyperlink one by one.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void getFAQCAL() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQ));
		FAQ.getText();
	}

	public void getselectQUE() {
		wait.until(ExpectedConditions.elementToBeClickable(selectQUE));
		Assert.assertTrue(selectQUE.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed = Quebec");
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed = Quebec");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		selectQUE.click();
	}

	public void getFAQQUE() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQQUE));
		FAQQUE.getText();
	}

	public void getselectNS() {
		wait.until(ExpectedConditions.elementToBeClickable(selectNS));
		selectNS.click();
	}

	public void getFAQNS() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQNS));
		FAQNS.getText();
	}

	public void getselectWS() {
		wait.until(ExpectedConditions.elementToBeClickable(selectWS));
		Assert.assertTrue(selectWS.isDisplayed(), "Element is not displayed on the page");
		System.out.println(
				"Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed. = Washington");
		ObjectRepo.getTest().log(Status.PASS,
				"Jurisdiction Dropdown  with  Jurisdiction Name in hyperlink should be displayed. = Washington");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		selectWS.click();
	}

	public void getFAQWS() {
		wait.until(ExpectedConditions.elementToBeClickable(FAQWS));
		FAQWS.getText();
	}

	public void getbtnResources() {
		btnResources.click();
	}

	public void getbtnFAQ() {
		btnFAQ.click();
	}

	public void getbtnSend() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSend));
		btnSend.click();
	}

	public void gettextName() {
		textName.sendKeys("testdata123");
	}

	public void gettextPhone() {
		textPhone.sendKeys("9860745624");
	}

	public void gettextEmail() {

		WebElement element = textEmail;
		js.executeScript("arguments[0].scrollIntoView();", element);
		textEmail.sendKeys("test56@gmail.com");
	}

	public void gettextMsg() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDiscard));

		js.executeScript("arguments[0].scrollIntoView();", btnDiscard);
		wait.until(ExpectedConditions.elementToBeClickable(btnDiscard));
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='tox-edit-area__iframe']")));
		driver.switchTo().frame(element);
		textMsg.sendKeys("testdescription");
		driver.switchTo().parentFrame();
	}

	public void getbtnDiscard() {
		wait.until(ExpectedConditions.elementToBeClickable(btnDiscard));
		btnDiscard.click();
	}

	public void getbtnCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCancel));
		btnCancel.click();
	}

	public void getbtnContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
		btnContinue.click();
	}

	public void getbtnContact() {
		wait.until(ExpectedConditions.elementToBeClickable(btnContact));
		Actions action = new Actions(driver);
		action.moveToElement(btnContact).click().build().perform();
	}

	public void brokenLinksOnAWebPage() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int totallinks = links.size();
		System.out.println("Total number of links available on publicHomepage: " + totallinks);
		Iterator<WebElement> it = links.iterator();
		String url = "";
		int count = 0;
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				count++;
				System.out.println(url);
			}
		}
		System.out.println("No of broken links present: " + count);
	}

	public void verify_Footer() {
		System.out.println("Step : Observe the footer of the page.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Observe the footer of the page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement element = driver.findElement(By.xpath("//*[text()='About the WCI, Inc. Auction Platform']"));
		ExpectedConditions.elementToBeClickable(element);
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();
		} catch (Exception e) {
			WebElement element2 = driver.findElement(By.xpath("//a[contains(@title,'Terms of Use')]"));

			js.executeScript("arguments[0].scrollIntoView();", element);
			element2.click();
		}
		String parentWindID = driver.getWindowHandle();
		Set<String> WindHandlesID = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID) {
			if (!WindowHandle.equals(parentWindID)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID);
			}
		}
		System.out.println(
				"Following options with hyperlink should be displayed in the footer of the page = AboutWCI,Inc.");
		ObjectRepo.getTest().log(Status.PASS,
				"Following options with hyperlink should be displayed in the footer of the page = AboutWCI,Inc.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getbtnTermsofUse();
		String parentWindID1 = driver.getWindowHandle();
		Set<String> WindHandlesID1 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID1) {
			if (!WindowHandle.equals(parentWindID1)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID1);
			}
		}
		System.out.println(
				"Following options with hyperlink should be displayed in the footer of the page = Terms of Use");
		ObjectRepo.getTest().log(Status.PASS,
				"Following options with hyperlink should be displayed in the footer of the page = Terms of Use");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getbtnPrivacyPolicy();
		String parentWindID2 = driver.getWindowHandle();
		Set<String> WindHandlesID2 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID2) {
			if (!WindowHandle.equals(parentWindID2)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID2);
			}
		}
		System.out.println(
				"Following options with hyperlink should be displayed in the footer of the page = Privacy Policy");
		ObjectRepo.getTest().log(Status.PASS,
				"Following options with hyperlink should be displayed in the footer of the page = Privacy Policy");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getbtnContactUs();
		System.out
				.println("Following options with hyperlink should be displayed in the footer of the page = Contact Us");
		ObjectRepo.getTest().log(Status.PASS,
				"Following options with hyperlink should be displayed in the footer of the page = Contact Us");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getbtnHome();
		System.out.println("Following options with hyperlink should be displayed in the footer of the page = Home");
		ObjectRepo.getTest().log(Status.PASS,
				"Following options with hyperlink should be displayed in the footer of the page = Home");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void verify_FAQAndResources() {
		wait.until(ExpectedConditions.elementToBeClickable(btnFAQResources));
		try {
			btnFAQResources.click();
		} catch (Exception e) {
			btnFAQResources.click();
		}
		System.out.println("Step : Click on the 'FAQs & Resources' button at the right side top of the page.");
		ObjectRepo.getTest().log(Status.PASS,
				"Step : Click on the 'FAQs & Resources' button at the right side top of the page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getselectCAL();
		String expt = "FAQs & Resources - California";
		String act = FAQ.getText();
		Assert.assertEquals(expt, act);
		getbtnResources();
		getbtnFAQ();
		System.out.println(
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - California");
		ObjectRepo.getTest().log(Status.PASS,
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - California");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement ele = btnFAQResources;
		Actions ac = new Actions(driver);
		ac.moveToElement(ele).click().build().perform();
		getselectQUE();
		String expt1 = "FAQs & Resources - Quebec";
		String act1 = FAQ.getText();
		Assert.assertEquals(expt1, act1);
		getbtnResources();
		getbtnFAQ();
		System.out.println(
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - Quebec");
		ObjectRepo.getTest().log(Status.PASS,
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - Quebec");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement var = btnFAQResources;
		Actions a = new Actions(driver);
		try {
			a.moveToElement(var).build().perform();
			a.click(var).build().perform();
		} catch (Exception e) {
			a.moveToElement(var).build().perform();
			a.click(var).build().perform();
		}
		getselectWS();
		String expt3 = "FAQs & Resources - Washington";
		String act3 = FAQ.getText();
		Assert.assertEquals(expt3, act3);
		getbtnResources();
		getbtnFAQ();
		System.out.println(
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - Quebec");
		ObjectRepo.getTest().log(Status.PASS,
				"User should be navigated to FAQs & Resources page for that particular jurisdiction like FAQs & Resources - Quebec");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		getbtnHome();
		ObjectRepo.getTest().log(Status.PASS, "Back to HomePage");
	}

	public void verify_ContactUs() {
		getbtnContact();
		gettextName();
		gettextPhone();
		gettextEmail();
		gettextMsg();
		getbtnSend();
	}

	public void getbtnTextAuctionDate() {
		wait.until(ExpectedConditions.visibilityOf(btnTextAuctionDate));
		btnTextAuctionDate.click();
	}

	public void getbtnTextDeatiledAuction() {
		WebElement element = btnTextDeatiledAuction;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();

	}

	public void getbtnTextAuctionEx() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTextAuctionEx));
		btnTextAuctionEx.click();
	}

	public void getbtnTextSummaryReport() {
		wait.until(ExpectedConditions.elementToBeClickable(btnTextSummaryReport));
		btnTextSummaryReport.click();
	}

	public void verify_noticeSection() {
		driver.navigate().refresh();

		js.executeScript("window.scrollBy(0,-150)");
		wait.until(ExpectedConditions.elementToBeClickable(btnTextAuctionDate));
		String str = btnTextAuctionDate.getText();
		System.out.println("Notice Section Data:" + str);
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + str);
		getbtnTextAuctionDate();
		String parentWindID3 = driver.getWindowHandle();
		Set<String> WindHandlesID3 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID3) {
			if (!WindowHandle.equals(parentWindID3)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID3);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, " verified link -TextAuctionDate");
		String DeatiledAuction = btnTextDeatiledAuction.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + DeatiledAuction);
		getbtnTextDeatiledAuction();
		String parentWindID2 = driver.getWindowHandle();
		Set<String> WindHandlesID2 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID2) {
			if (!WindowHandle.equals(parentWindID2)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID2);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "verified link -TextDeatiledAuction");
		String AuctionEx = btnTextAuctionEx.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + AuctionEx);
		getbtnTextAuctionEx();
		String parentWindID1 = driver.getWindowHandle();
		Set<String> WindHandlesID1 = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID1) {
			if (!WindowHandle.equals(parentWindID1)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID1);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, " verified link -TextAuctionEx");
		String SummaryReport = btnTextSummaryReport.getText();
		ObjectRepo.getTest().log(Status.PASS, "Notice Section Data:" + SummaryReport);
		getbtnTextSummaryReport();
		String parentWindID = driver.getWindowHandle();
		Set<String> WindHandlesID = driver.getWindowHandles();
		for (String WindowHandle : WindHandlesID) {
			if (!WindowHandle.equals(parentWindID)) {
				driver.switchTo().window(WindowHandle);
				driver.close();
				driver.switchTo().window(parentWindID);
			}
		}
		ObjectRepo.getTest().log(Status.PASS, "verified link -TextSummaryReport");
	}

	public void downloadReports() {
		btnReports.click();
		btnBidsQualifiedBidderReports.click();
		btnBidsAuditReports.click();
		btnBidsReports.click();
	}

	public void verify_PrivatePage_MenuBottons() {
		List<WebElement> listOne = menuButton;
		int menubar = listOne.size();
		for (int i = 0; i <= menubar - 1; i++) {
			listOne.get(i).click();
			String str = listOne.get(i).getText();
			ObjectRepo.getTest().log(Status.PASS, "verified sponsor MenuButtons: " + str);
		}
	}

	public void ARLoginQC2() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC4() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName4"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName4"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName4") + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName4") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC44() {
		try {
			btnLogin.click();

			username.sendKeys(UsersData.AR_USerName44);
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + UsersData.AR_USerName44);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered  : " + UsersData.password);
			Thread.sleep(10000);
			btnLogin2.click();

			System.out.println(UsersData.AR_USerName44 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					UsersData.AR_USerName44 + " Account Representative login successfully");

		} catch (Exception e) {
			btnLogin.click();

			username.sendKeys(UsersData.AR_USerName44);
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + UsersData.AR_USerName44);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered  : " + UsersData.password);

			btnLogin2.click();

			System.out.println(UsersData.AR_USerName44 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					UsersData.AR_USerName44 + " Account Representative login successfully");
		}
	}

	public void ARLoginQC1() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName3"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName3"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName3") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName3") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName3"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName3"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName3") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName3") + " Account Representative login successfully");
		}
	}

	public void ARLoginQC3() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));

			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		}
	}

	public void ARLoginQCForSBMA_R70() {
		btnLogin.click();
		username.sendKeys(prop.getProperty("AR_USerName1_RE1"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1_RE1"));
		password.sendKeys(prop.getProperty("AR_PassWord"));
		ObjectRepo.getTest().log(Status.PASS,
				"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
		btnLogin2.click();
		System.out.println("" + prop.getProperty("AR_USerName1_RE1") + "Account Representative login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("AR_USerName1_RE1") + " Account Representative login successfully");
	}

	public void runtimesponsorLoginQc2() {
		try {
			btnLogin.click();
		} catch (Exception e) {
			btnLogin.click();
		}
		username.sendKeys(prop.getProperty("UserSponsorQc"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered  : " + prop.getProperty("UserSponsorQc"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("" + prop.getProperty("UserSponsorQc") + "Sponsor (Quebec) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UserSponsorQc") + " Sponsor (Quebec) login successfully");
	}

	public void RuntimeARLoginQC() {
		try {
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName2"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName2"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));

			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName2") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName2") + " Account Representative login successfully");
		}
	}

	public void sponsorUserloginFunctionality() {
		btnLogin.click();
		username.sendKeys("sponsor.ca@yopmail.com");
		password.sendKeys("password");
		btnLogin2.click();
		wait.until(ExpectedConditions.elementToBeClickable(loginFailureMsg));
		String FailureMsg = loginFailureMsg.getText();
		System.out.println("Error message displayed " + FailureMsg);
		ObjectRepo.getTest().log(Status.PASS, "Error message displayed :-" + FailureMsg);
	}

	public void forgotPassphrase() {
		btnLogin.click();
		System.out.println("Login pop up module is opened");
		ObjectRepo.getTest().log(Status.PASS, "Login pop up module is opened");
		wait.until(ExpectedConditions.elementToBeClickable(forgotPassphraseBtn));
		forgotPassphraseBtn.click();
		System.out.println("Email address textfield is displayed on screen");
		ObjectRepo.getTest().log(Status.PASS, "Email address textfield is displayed on screen");
		emailAdressField.sendKeys("sponsor.ca@yopmail.com");
		System.out.println("After selecting the forgot passphrase link the user is able to input an email address.");
		ObjectRepo.getTest().log(Status.PASS,
				"After selecting the forgot passphrase link the user is able to input an email address.");
	}

	public void ARLoginNS() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1_NS"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("AR_USerName1_NS"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("AR_USerName1_NS") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("AR_USerName1_NS") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("AR_USerName1_NS"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			btnLogin2.click();
		}
	}

	public void RuntimeMMLoginCa2() {
		username.sendKeys(prop.getProperty("UnameMMCa"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Username Entered  : " + prop.getProperty("UnameMMCa"));
		password.sendKeys(prop.getProperty("Password"));
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Password Entered  : " + prop.getProperty("Password"));
		btnLogin.click();
		System.out.println("California MM login successfully");
		System.out.println("" + prop.getProperty("UnameMMCa") + " Market Monitor (California) login successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"" + prop.getProperty("UnameMMCa") + " Market Monitor  (California) login successfully");
	}

	public void RuntimeFSALoginCa() {
		try {
			username.sendKeys(prop.getProperty("UnameFSACa"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin.click();
			System.out.println("" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("UnameFSACa"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Username Entered  : " + prop.getProperty("UnameFSACa"));
			password.sendKeys(prop.getProperty("Password"));
			ObjectRepo.getTest().log(Status.PASS, "FSA Password Entered  : " + prop.getProperty("Password"));
			btnLogin2.click();
			System.out.println("" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("UnameFSACa") + " FSA (California) login successfully");
		}

	}

	public void AR_LoginCA() {
		try {
			btnLogin.click();

			username.sendKeys(UsersData.ARUSerName1);
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + UsersData.ARUSerName1);

			password.sendKeys(UsersData.password);
			Thread.sleep(2000);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered  : " + UsersData.password);

			try {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
				btnLogin2.click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(btnLogin2));
				btnLogin2.click();
			}

			System.out.println(UsersData.ARUSerName1 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.ARUSerName1 + " Account Representative login successfully");

		} catch (Exception e) {
			btnLogin.click();

			username.sendKeys(UsersData.ARUSerName1);
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + UsersData.ARUSerName1);

			password.sendKeys(UsersData.password);
			ObjectRepo.getTest().log(Status.PASS, "Account Representative Password Entered  : " + UsersData.password);

			try {
				btnLogin2.click();
			} catch (Exception e1) {
				btnLogin2.click();
			}

			System.out.println(UsersData.ARUSerName1 + " Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS, UsersData.ARUSerName1 + " Account Representative login successfully");
		}
	}

	public void AR_LoginNSPriorBid() {
		try {
			btnLogin.click();
			username.sendKeys(prop.getProperty("ARUserNamePriorBid"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("ARUserNamePriorBid"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin2.click();
			System.out
					.println("" + prop.getProperty("ARUserNamePriorBid") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("ARUserNamePriorBid") + " Account Representative login successfully");
		} catch (Exception e) {
			btnLogin.click();
			username.sendKeys(prop.getProperty("ARUserNamePriorBid"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Username Entered  : " + prop.getProperty("ARUserNamePriorBid"));
			password.sendKeys(prop.getProperty("AR_PassWord"));
			ObjectRepo.getTest().log(Status.PASS,
					"Account Representative Password Entered  : " + prop.getProperty("AR_PassWord"));
			btnLogin.click();
			System.out
					.println("" + prop.getProperty("ARUserNamePriorBid") + "Account Representative login successfully");
			ObjectRepo.getTest().log(Status.PASS,
					"" + prop.getProperty("ARUserNamePriorBid") + " Account Representative login successfully");
		}
	}

	public void handleCloseButton() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnClose)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnClose)).click();
		}
	}
}
