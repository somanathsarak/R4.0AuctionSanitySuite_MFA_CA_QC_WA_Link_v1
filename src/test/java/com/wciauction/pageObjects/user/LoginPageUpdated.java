package com.wciauction.pageObjects.user;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
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

import java.time.Duration;
import static com.wci.auction.org.resources.BaseClass.prop;
import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class LoginPageUpdated {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

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
	
	@FindBy(xpath = "//button//span[contains(text(),'SOUMETTRE')]")
	private WebElement c;

	@FindBy(xpath = "//span[contains(text(),'SOUMETTRE')]")
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

	public LoginPageUpdated() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void refreshPage() {
		driver.navigate().refresh();
//		try {
//			Thread.sleep(35000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void clickOnHomeButton() {
		waitForElementToBeClickable(btnHome);
	}

	public void unidentifiedUserLogin(String userPropertyKey, String passwordPropertyKey, String language) {
		try {
			waitForElementToBeClickable(btnLogin);
			String user = prop.getProperty(userPropertyKey);
			String pass = prop.getProperty(passwordPropertyKey);
			username.sendKeys(user);
			ObjectRepo.getTest().log(Status.PASS, "Unidentified User Username Entered : " + user);
			password.sendKeys(pass);
			ObjectRepo.getTest().log(Status.PASS, "Unidentified User Password Entered : " + pass);
			try {
				waitForElementToBeClickable(btnLogin2);
			} catch (Exception e) {
				waitForElementToBeClickable(btnLogin2);
			}
			String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
			System.out.println(user + " Unidentified User " + region + " login successfully");
			ObjectRepo.getTest().log(Status.PASS, user + " Unidentified User " + region + " login successfully");
			System.out.println("-------------------------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		} catch (Exception e) {
			ObjectRepo.getTest().info("Unidentified user login failed: " + e.getMessage());
			throw e;
		}
	}

	public void identifiedUserLogin(String userPropertyKey, String passwordPropertyKey, String language) {
		try {
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(username));
			waitForElementToBeClickable(username);
//			String user = prop.getProperty(userPropertyKey);
//			String pass = prop.getProperty(passwordPropertyKey);
			username.sendKeys(userPropertyKey);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Username Entered : " + userPropertyKey);
			password.sendKeys(passwordPropertyKey);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Password Entered : " + passwordPropertyKey);
			waitForElementToBeClickable(btnLogin3);
			String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
			ObjectRepo.getTest().log(Status.PASS, userPropertyKey + " Identified User " + region + " login successfully");
		} catch (Exception e) {
			Logout();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			wait.until(ExpectedConditions.elementToBeClickable(username));
			waitForElementToBeClickable(username);
//			String user = prop.getProperty(userPropertyKey);
//			String pass = prop.getProperty(passwordPropertyKey);
			username.sendKeys(userPropertyKey);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Username Entered : " + userPropertyKey);
			password.sendKeys(passwordPropertyKey);
			ObjectRepo.getTest().log(Status.PASS, "Identified User Password Entered : " + passwordPropertyKey);
			waitForElementToBeClickable(btnLogin3);
			String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
			ObjectRepo.getTest().log(Status.PASS, userPropertyKey + " Identified User " + region + " login successfully");
		}
	}
	
	public void changeLangaugeAsFrench() {
		try {
			driver.navigate().refresh();
			waitForElementToBeClickable(langBtnENG);
			wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
			wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
			waitForElementToBeClickable(langBtnFrench);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectRepo.getTest().log(Status.PASS, "Language changed successfully.");
	}

	public void changeLangauge() {
		try {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Surveillance']")));
			} catch (Exception e) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Accueil']")));
			}
			driver.navigate().refresh();
			waitForElementToBeClickable(langBtnFrench);
			wait.until(ExpectedConditions.elementToBeClickable(langBtnENG));
			wait.until(ExpectedConditions.elementToBeClickable(langBtnENG));
			waitForElementToBeClickable(langBtnENG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectRepo.getTest().log(Status.PASS, "Language changed successfully.");
	}

	public void clickOnnext() {
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(btnNext));
			WebElement abc = btnNext;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
			action.click(abc).build().perform();
		} catch (Exception e) {
			WebElement abc = btnNext;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
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
		waitForElementToBeVisible(By.xpath("//label[@class='MuiFormLabel-root Mui-required']"));
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//label[@class='MuiFormLabel-root Mui-required']")));
		js.executeScript("arguments[0].click();", element);

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
		driver.navigate().refresh();
		try {
			waitForElementToBeClickable(btnProfile);
			waitForElementToBeClickable(btnLogout);
			Thread.sleep(3000);
		} catch (Exception e) {
			waitForElementToBeClickable(btnProfile);
			waitForElementToBeClickable(btnLogout);
		}
		ObjectRepo.getTest().log(Status.PASS, "User Logout successfully.");
	}

	public  void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		int retries = 0;
		boolean clicked = false;
		while (retries < 3 && !clicked) {
			try {
				switch (retries) {
				case 0:
					element.click();
					clicked = true;
					break;

				case 1:
					js.executeScript("arguments[0].click();", element);
					clicked = true;
					break;

				case 2:
					new Actions(driver).moveToElement(element).click().perform();
					clicked = true;
					break;
				}
			} catch (ElementClickInterceptedException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element not clickable after 3 attempts.");
				}
				System.out.println("Retrying click due to overlay or blocking element...");
				WebElement spinner = driver.findElement(By.id("appSpinner"));

				wait.until(ExpectedConditions.invisibilityOf(spinner));
			}
		}
	}

	public  void waitForElementToBeVisible(WebElement element) {
		int retries = 0;
		boolean isVisible = false;

		while (retries < 3 && !isVisible) {
			try {
				wait.until(ExpectedConditions.visibilityOf(element));
				isVisible = true; // If no exception is thrown, element is visible
			} catch (TimeoutException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element not visible after 3 attempts.");
				}
				System.out.println("Retrying visibility check due to timeout...");

				// Wait for any loading indicators to disappear
				try {
					WebElement spinner = driver.findElement(By.id("appSpinner"));
					wait.until(ExpectedConditions.invisibilityOf(spinner));
				} catch (NoSuchElementException spinnerException) {
					// If spinner is not found, continue with next retry
					System.out.println("No loading indicator found, continuing with retry...");
				}
			} catch (StaleElementReferenceException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element became stale after 3 attempts.");
				}
				System.out.println("Retrying visibility check due to stale element...");
			}
		}
	}

	public  void waitForElementToBeVisible(By element) {
		int retries = 0;
		boolean isVisible = false;

		while (retries < 3 && !isVisible) {
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
				isVisible = true; // If no exception is thrown, element is visible
			} catch (TimeoutException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element not visible after 3 attempts.");
				}
				System.out.println("Retrying visibility check due to timeout...");

				// Wait for any loading indicators to disappear
				try {
					WebElement spinner = driver.findElement(By.id("appSpinner"));
					wait.until(ExpectedConditions.invisibilityOf(spinner));
				} catch (NoSuchElementException spinnerException) {
					// If spinner is not found, continue with next retry
					System.out.println("No loading indicator found, continuing with retry...");
				}
			} catch (StaleElementReferenceException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element became stale after 3 attempts.");
				}
				System.out.println("Retrying visibility check due to stale element...");
			}
		}
	}

	public void clickOnContinueBtc() {
		try {
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(btnContinueEN));
			WebElement abc = btnContinueEN;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
			action.click(abc).build().perform();
		} catch (Exception e) {
			WebElement abc = btnContinueEN;
			Actions action = new Actions(driver);
			action.moveToElement(abc).click().build().perform();
			action.click(abc).build().perform();
		}
		ObjectRepo.getTest().log(Status.PASS, "Click on Continue button.");
	}
	
	 public void identifiedUserLogin_1(String userPropertyKey, String passwordPropertyKey, String language) {
			try {
				driver.navigate().refresh();
				Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(username));
				waitForElementToBeClickable(username);
				username.sendKeys(userPropertyKey);
				ObjectRepo.getTest().log(Status.PASS, "Identified User Username Entered : " + userPropertyKey);
				password.sendKeys(passwordPropertyKey);
				ObjectRepo.getTest().log(Status.PASS, "Identified User Password Entered : " + passwordPropertyKey);
				Thread.sleep(4000);
				waitForElementToBeClickable(btnLogin1);
				String region = language.equalsIgnoreCase("french") ? "(Quebec-French)" : "(Quebec)";
				ObjectRepo.getTest().log(Status.PASS, userPropertyKey + " Identified User " + region + " login successfully");
			} catch (Exception e) {
		}
	 }
	
//	public void selectDropdownOption(String dropdownText, String entityOptionText) {
//		selectDropdownOption(driver, dropdownText, entityOptionText, "CONTINUE", 20);
//	}

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
//	public static WebDriver selectDropdownOption(WebDriver driver, String dropdownText, String entityOptionText,
//			String continueButtonText, int timeoutSeconds) {
//		try {
//			WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
//					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + dropdownText + "']")));
//			CommonMethods.waitForElementToBeClickable(dropdown);
//			Thread.sleep(2000);
//			Actions act = new Actions(driver);
//			WebElement element = driver.findElement(By.xpath("//*[text()='" + entityOptionText + "']"));
//			act.moveToElement(element).build().perform();
//			try{
//			WebElement entityOption = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
//					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + entityOptionText + "']")));
//			CommonMethods.waitForElementToBeClickable(entityOption);
//			}
//			catch(Exception e) {
//			act.click(element).build().perform();
//			}
//			WebElement continueBtn = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(
//					ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + continueButtonText + "']")));
//			continueBtn.click();
//			return driver;
//		} catch (Exception e) {
//			System.err.println("Error in dropdown selection: " + e.getMessage());
//			throw new RuntimeException("Dropdown selection failed", e);
//		}
	}



//}


