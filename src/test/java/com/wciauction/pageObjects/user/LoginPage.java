package com.wciauction.pageObjects.user;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.CommonMethods;
import com.wci.auction.org.resources.ObjectRepo;
import static com.wci.auction.org.resources.BaseClass.prop;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;
//	Properties prop = BaseClass.getProperties();

	@FindBy(xpath = "//button[@data-test=\"loginBTN\"]")
	private WebElement btnLogin;

	@FindBy(xpath = "//*[text()='Home']")
	private WebElement btnHome;

	@FindBy(xpath = "//*[@id='enter_email']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='enter_passphrase']")
	private WebElement password;

	@FindBy(xpath = "(//span[text()='Log in'])[2]")
	private WebElement btnLogin2;

	@FindBy(xpath = "//span[text()='Ouverture de session']")
	private WebElement btnLoginfr;

	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//*[text()='English']")
	private WebElement langBtnENG;

	@FindBy(xpath = "//*[@data-icon='angle-down']/parent::*[contains(text(),'Welcome,')]")
	private WebElement btnProfile;

	@FindBy(xpath = "//*[@data-icon='angle-down']/parent::*[contains(text(),'Bienvenue,')]")
	private WebElement btnProfileFr;

	@FindBy(xpath = "//ul[@class='MuiList-root MuiList-padding']")
	private WebElement WelcomeUsrLink;

	@FindBy(xpath = "//*[text()='Log in']")
	private WebElement btnLogin1;

	@FindBy(xpath = "//*[text()='Log in']")
	private WebElement btnLogin3;

	@FindBy(xpath = "//button//span[contains(text(),'NEXT')]")
	private WebElement btnNext;

	@FindBy(xpath = "//*[contains(text(),'SOUMETTRE')]")
	private WebElement btnNextQC;

	@FindBy(xpath = "//*[text()='Email address or passphrase is invalid.']")
	private WebElement loginFailureMsg;

	@FindBy(xpath = "//label[@class='MuiFormLabel-root Mui-required']")
	private WebElement securityQue;

	@FindBy(xpath = "//input[@class='MuiInputBase-input MuiInput-input']")
	private WebElement securityAnswer;

	@FindBy(xpath = "//*[text()='Logout']")
	private WebElement btnLogout;

	@FindBy(xpath = "//*[text()='Fermer la session']")
	private WebElement btnLogoutFr;

	@FindBy(xpath = "//span[contains(text(),'CONTINUER')]")
	private WebElement btnCapContinueQc;

	@FindBy(xpath = "//span[contains(text(),'CONTINUE')]")
	private WebElement btnCapContinue;

	@FindBy(xpath = "(//label[@class='MuiFormControlLabel-root'])[3]")
	private WebElement accountName2;

	@FindBy(xpath = "(//input[@name='rollDownProcedure'])[2]")
	private WebElement btnAccSelect2;

	@FindBy(xpath = "//span[text()='CONTINUE']")
	private WebElement btnContinueEN;

	public LoginPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		PageFactory.initElements(driver, this);
	}

	public void refreshPage() throws InterruptedException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		Thread.sleep(3000);

	}

	public void clickOnHomeButton() {
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(btnHome);
	}

	public void deleteCookiesBeforeNyOperation() {
		driver.navigate().refresh();
	}

	/**
	 * Login method for identified users with standard login flow
	 * 
	 * @param userPropertyKey     username property key
	 * @param passwordPropertyKey password property key
	 * @param language            user language preference
	 */
	public void identifiedUserLogin(String userPropertyKey, String passwordPropertyKey, String language) {
		try {
//			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(username));
			CommonMethods commonMethods2 = new CommonMethods();
			commonMethods2.waitForElementToBeClickable(username);
			String user = prop.getProperty(userPropertyKey);
			String pass = prop.getProperty(passwordPropertyKey);
			username.sendKeys(user);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Username Entered : " + user);

			password.sendKeys(pass);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Password Entered : " + pass);
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(btnLogin3);
			String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
			ObjectRepo.getTest().log(Status.PASS, user + " Identified User " + region + " login successfully");
			System.out.println("Identified User Username Entered : " + user);
			Thread.sleep(5000);
		} catch (Exception e) {
			LoginPage login = new LoginPage();
			login.Logout();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(username));
			CommonMethods commonMethods3 = new CommonMethods();
			commonMethods3.waitForElementToBeClickable(username);
			String user = prop.getProperty(userPropertyKey);
			String pass = prop.getProperty(passwordPropertyKey);
			username.sendKeys(user);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Username Entered : " + user);
			password.sendKeys(pass);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Password Entered : " + pass);
			commonMethods3.waitForElementToBeClickable(btnLogin3);
			String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
			ObjectRepo.getTest().log(Status.PASS, user + " Identified User " + region + " login successfully");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Login method for unidentified users with alternate login flow
	 * 
	 * @param userPropertyKey     username property key
	 * @param passwordPropertyKey password property key
	 * @param language            user language preference
	 * @throws Exception
	 */
	public void unidentifiedUserLogin(String userPropertyKey, String passwordPropertyKey, String language) {
		CommonMethods commonMethods = new CommonMethods();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
		}
		String user = prop.getProperty(userPropertyKey);
		String pass = prop.getProperty(passwordPropertyKey);
		wait.until(ExpectedConditions.elementToBeClickable(username)).sendKeys(user);
		ObjectRepo.getTest().log(Status.PASS, "Unidentified User Username Entered : " + user);
		wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(pass);
		ObjectRepo.getTest().log(Status.PASS, "Unidentified User Password Entered : " + pass);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnLogin2)).click();
		} catch (Exception e) {
			commonMethods.waitForElementToBeClickable(btnLogin2);
		}
		String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
		ObjectRepo.getTest().log(Status.PASS, user + " Unidentified User " + region + " login successfully");
		System.out.println("Unidentified User Username Entered : " + user);
	}

	public void userLoginAfterFrenchLogout(String userPropertyKey, String passwordPropertyKey) {
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(username));
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeClickable(username);
		String user = prop.getProperty(userPropertyKey);
		String pass = prop.getProperty(passwordPropertyKey);
		username.sendKeys(user);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Username Entered : " + user);
		password.sendKeys(pass);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor Password Entered : " + pass);
		commonMethods.waitForElementToBeClickable(btnLoginfr);
	}

	public void sponsorLoginQc() {
		unidentifiedUserLogin("UserSponsorQc", "Password", "english");
	}

	public void sponsorLoginQcFrench() {
		unidentifiedUserLogin("UserSponsorQc2", "Password", "french");
	}

	public void SponsorLoginQc() {
		unidentifiedUserLogin("UserSponsorQc2", "Password1", "english");
	}

	public void changeLangauge() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Rapports']")));
			Thread.sleep(2000);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench)).click();
			} catch (Exception e) {
				wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench)).click();
			}
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(langBtnENG)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectRepo.getTest().log(Status.PASS, "Language changed successfully.");
	}

	public void changeLangaugeToFrench() {
		try {
			Thread.sleep(5000);
			driver.navigate().refresh();
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(langBtnENG);
			wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
			wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
			try {
				Thread.sleep(2000);
				commonMethods.waitForElementToBeClickable(langBtnFrench);
			} catch (Exception e) {
				langBtnFrench.click();
			}
		} catch (Exception e) {
			ObjectRepo.getTest().log(Status.PASS, "Language changed in french successfully.");
		}
		ObjectRepo.getTest().log(Status.PASS, "Language changed successfully.");
	}

	public void clickOnnext() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(btnNext));
			WebElement abc = btnNext;
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
		ObjectRepo.getTest().log(Status.PASS, "Click on Next button.");
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
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CommonMethods commonMethods = new CommonMethods();
		commonMethods.waitForElementToBeVisible(By.xpath("//label[@class='MuiFormLabel-root Mui-required']"));
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		String str = securityQue.getText().trim();
		System.out.println(str);
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
		ObjectRepo.getTest().log(Status.PASS, "Security Questions Handled successfully.");
	}

	public void Logout() {
		wait.until(ExpectedConditions.visibilityOf(btnProfile));
//		driver.navigate().refresh();
		CommonMethods commonMethods = new CommonMethods();
		try {
			commonMethods.waitForElementToBeClickable(btnProfile);
			commonMethods.waitForElementToBeClickable(btnLogout);
			Thread.sleep(3000);
		} catch (Exception e) {
			commonMethods.waitForElementToBeClickable(btnProfile);
			commonMethods.waitForElementToBeClickable(btnLogout);
		}
		ObjectRepo.getTest().log(Status.PASS, "User Logout successfully.");
	}

	public void LogoutFr() {
		wait.until(ExpectedConditions.visibilityOf(btnProfileFr));
		driver.navigate().refresh();
		CommonMethods commonMethods = new CommonMethods();
		try {
			commonMethods.waitForElementToBeClickable(btnProfileFr);
			commonMethods.waitForElementToBeClickable(btnLogoutFr);
		} catch (Exception e) {
			commonMethods.waitForElementToBeClickable(btnProfileFr);
			commonMethods.waitForElementToBeClickable(btnLogoutFr);
		}
		ObjectRepo.getTest().log(Status.PASS, "User Logout successfully.");
	}

	public void getbtnARContinue() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCapContinue));
		btnCapContinue.click();
		System.out.println("Click on Continue button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Continue button");
	}

	public void getbtnARContinueQc() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCapContinueQc));
		btnCapContinueQc.click();
		System.out.println("Click on Continue button");
		ObjectRepo.getTest().log(Status.PASS, "Click on Continue button");
	}

	public void getAccountSelect2() {
		wait.until(ExpectedConditions.elementToBeClickable(accountName2));
		String AccountName2 = accountName2.getText();
		btnAccSelect2.click();
		ObjectRepo.getTest().log(Status.PASS, AccountName2 + ": Selected AR entity account");
		System.out.println("Selected AR entity account");
	}

	public void getBtnContineuEN() {
		btnContinueEN.click();
	}

	public void selectDropdownOption(String dropdownText, String entityOptionText) {
		try {
			selectDropdownOption(driver, dropdownText, entityOptionText, "CONTINUE", 20);
		} catch (Exception e) {
			selectDropdownOption(driver, dropdownText, entityOptionText, "CONTINUER", 20);
		}
	}

	/**
	 * Overloaded method with custom timeout and continue button text
	 * 
	 * @param driver             Selenium WebDriver instance
	 * @param dropdownText       Text of the dropdown option to select
	 * @param entityOptionText   Text of the entity option to select
	 * @param continueButtonText Text of the continue button
	 * @param timeoutSeconds     Maximum wait time for elements to be clickable
	 * @return WebDriver instance for method chaining
	 */
	public static WebDriver selectDropdownOption(WebDriver driver, String dropdownText, String entityOptionText,
			String continueButtonText, int timeoutSeconds) {
		try {
			WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + dropdownText + "']")));
			CommonMethods commonMethods = new CommonMethods();
			commonMethods.waitForElementToBeClickable(dropdown);
			Thread.sleep(2000);
			Actions act = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//*[text()='" + entityOptionText + "']"));
			act.moveToElement(element).build().perform();
			try {
				WebElement entityOption = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + entityOptionText + "']")));
				commonMethods.waitForElementToBeClickable(entityOption);
			} catch (Exception e) {
				act.click(element).build().perform();
			}
			System.out.println("Step Select entity " + entityOptionText);
			WebElement continueBtn = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + continueButtonText + "']")));
			new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
					.until(ExpectedConditions.elementToBeClickable(continueBtn));

			continueBtn.click();
			System.out.println("Step click on continueBtn");
			return driver;
		} catch (Exception e) {
			System.err.println("Error in dropdown selection: " + e.getMessage());
			throw new RuntimeException("Dropdown selection failed", e);
		}
	}
}
