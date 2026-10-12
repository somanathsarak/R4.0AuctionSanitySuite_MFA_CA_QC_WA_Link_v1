package com.wciauction.pageObjects.user;

import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
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

public class ChangePassphrasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	public String randomString;
	private static Actions action;
	public static String randomStringEmail;

	@FindBy(xpath = "//span[text()=\"Administration\"]")
	private WebElement administrationMenuFrench;

	@FindBy(xpath = "//a[@href=\"/useradministration\"]")
	private WebElement userOptionUnderAdministration;

	@FindBy(xpath = "//*[text()='Add New User']")
	private WebElement addNewUserButton;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//*[contains(text(),\"Check if email address is already associated to a platform user\")]")
	private WebElement verifyEmail;

	@FindBy(xpath = "//*[text()=\"This user does not exist and may be added.\"]")
	private WebElement verifyEmailSuccessMsg;

	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstNmeField;

	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement LastNmeField;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
	private WebElement userRoleDropDown;

	@FindBy(xpath = "//*[text()='ADD USER']")
	private WebElement addUserBtn;

	@FindBy(xpath = "//*[text()=\"The user has been added successfully.\"]")
	private WebElement addUserSuccessMsg;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement userStatusTable;

	@FindBy(xpath = "//*[@id='mail']")
	private WebElement MailContent;

	@FindBy(xpath = "//iframe[@name='ifmail']")
	private WebElement Frame_Mail;

	@FindBy(xpath = "//*[text()=\"Activation link\"]")
	private WebElement ActivationLink;

	@FindBy(xpath = "//*[@id=\"newPassphrase\"]")
	public WebElement nemwpasspharsefield;	

	@FindBy(xpath = "//*[@id=\"confirmPassphrase\"]")
	public WebElement confirmpasspharsefield;	

	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[2]")
	public WebElement dropdown1;

	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[3]")
	public WebElement dropdown2;

	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[4]")
	public WebElement dropdown3;

	@FindBy(xpath = "(//*[text()='Quel était le nom de famille de votre professeur de troisième année?'])[1]")
	public WebElement option1;

	@FindBy(xpath = "(//*[@placeholder=\"Enter your answer\"])[1]")
	public WebElement answer1;	

	@FindBy(xpath = "(//*[text()='Quel était le nom de famille de votre professeur de troisième année?'])[1]")
	public WebElement option2;

	@FindBy(xpath = "(//*[@placeholder=\"Enter your answer\"])[2]")
	public WebElement answer2;	

	@FindBy(xpath = "(//*[text()='En quelle année avez-vous eu votre première carte de crédit?'])[1]")
	public WebElement option3;

	@FindBy(xpath = "(//*[@placeholder=\"Enter your answer\"])[3]")
	public WebElement answer3;	

	@FindBy(xpath = "//*[@name=\"checkAgreement\"]")
	private WebElement checkbox;

	@FindBy(xpath = "//*[text()=\"SET UP MY CREDENTIALS\"]")
	public WebElement submit;	
	
	@FindBy(xpath = "//*[text()='RESET MY SECURITY QUESTIONS']")
	public WebElement resetMySecurityQueButton;	
	
	@FindBy(xpath = "//*[contains(text(),'You have successfully set up your account credentials.')]")
	public WebElement successMessage;
	
	@FindBy(xpath = "//*[contains(text(),'Your security questions were reset successfully.')]")
	public WebElement successMessageForChangeSecuritiQue;

	@FindBy(xpath = "(//*[contains(text(),'Welcome')])[1]")
	public WebElement welcomeDropdown;

	@FindBy(xpath = "//*[contains(text(),'Change Passphrase')]")
	public WebElement changePassphrase;
	
	@FindBy(xpath = "//*[contains(text(),'Reset Security Questions')]")
	public WebElement resetSecurityQue;

	@FindBy(xpath = "//*[@id=\"currentPassphrase\"]")
	public WebElement currentPassphraseField;

	@FindBy(xpath = "//*[contains(text(),' SAVE NEW PASSPHRASE')]")
	public WebElement changePassphraseButton;

	@FindBy(xpath = "	//*[contains(text(),'Your passphrase has been changed successfully.')]")
	private WebElement successMessageOnPopUp;
	
	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[1]")
	public WebElement dropdown1OnChangePassphrase;

	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[2]")
	public WebElement dropdown2OnChangePassphrase;

	@FindBy(xpath = "(//select[@id=\"demo-customized-select\"])[3]")
	public WebElement dropdown3OnChangePassphrase;

	public ChangePassphrasePage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void resetSecurityQuestions() throws InterruptedException {
		Thread.sleep(5000);
		Select select = new Select(dropdown1OnChangePassphrase);
		select.selectByVisibleText("In which city or town was your first job?");
		answer1.click();
		answer1.sendKeys("Answer_1");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", answer2);		
		wait.until(ExpectedConditions.elementToBeClickable(dropdown2OnChangePassphrase));
		Select select1 = new Select(dropdown2OnChangePassphrase);
		select1.selectByVisibleText("What was the last name of your third-grade teacher?");
		answer2.click();
		answer2.sendKeys("Answer_2");		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", answer3);		
		wait.until(ExpectedConditions.elementToBeClickable(dropdown3OnChangePassphrase));
		Select select2 = new Select(dropdown3OnChangePassphrase);
		select2.selectByVisibleText("In which year did you get your first credit card?");
		answer3.click();
		answer3.sendKeys("Answer_3");
		wait.until(ExpectedConditions.elementToBeClickable(resetMySecurityQueButton));
		resetMySecurityQueButton.click();
		System.out.println("Step : Set up new Security questions and their answers , and click on 'RESET MY SECURITY QUESTIONS' button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Set up new Security questions and their answers , and click on 'RESET MY SECURITY QUESTIONS' button.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(successMessageForChangeSecuritiQue));
		String success = successMessageForChangeSecuritiQue.getText();
		Assert.assertEquals(success, "Your security questions were reset successfully.");
		System.out.println("Success message should be displayed = " + success);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed = " + success);
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void changePassphrase() {
		wait.until(ExpectedConditions.elementToBeClickable(currentPassphraseField));
		Assert.assertTrue(currentPassphraseField.isDisplayed(), "Element is not displayed after waiting");
		System.out.println("User should be navigated to 'Change passphrase' page.");
		ObjectRepo.getTest().log(Status.PASS, "User should be navigated to 'Change passphrase' page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView();", currentPassphraseField);
		wait.until(ExpectedConditions.elementToBeClickable(currentPassphraseField));
		try {
			currentPassphraseField.click();
		} catch (Exception e) {
			currentPassphraseField.click();
			e.printStackTrace();
		}
		currentPassphraseField.sendKeys("Test@1234");
		wait.until(ExpectedConditions.elementToBeClickable(nemwpasspharsefield));
		nemwpasspharsefield.click();
		System.out.println("Enter New passphrase which does not meet minimum requirement");
		ObjectRepo.getTest().log(Status.PASS, "Enter New passphrase which does not meet minimum requirement");
		nemwpasspharsefield.sendKeys("Test@123");
		System.out.println("Enter confirm passphrase which does not meet minimum requirement");
		ObjectRepo.getTest().log(Status.PASS, "Enter confirm passphrase which does not meet minimum requirement");
		js.executeScript("arguments[0].scrollIntoView();", confirmpasspharsefield);
		confirmpasspharsefield.click();
		confirmpasspharsefield.sendKeys("Test@123");
		System.out.println("Passphrase which does not meet minimum requirement entered successfully");
		ObjectRepo.getTest().log(Status.PASS,
				"Passphrase which does not meet minimum requirement entered successfully");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", changePassphraseButton);
		changePassphraseButton.click();
		System.out.println("Step : Enter current , new passphrase, confirm new passphrase and click on 'Save New Passphrase' button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Enter current , new passphrase, confirm new passphrase and click on 'Save New Passphrase' button.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(successMessageOnPopUp));
		String success = successMessageOnPopUp.getText();
		Assert.assertEquals(success, "Your passphrase has been changed successfully.");
		System.out.println("Success message should be displayed as = " + success);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed as = " + success);
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}
	
	public void navigateChangeSecurityQuestions() {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(welcomeDropdown));
		try {
			welcomeDropdown.click();
		} catch (Exception e) {
			welcomeDropdown.click();
			e.printStackTrace();
		}
		System.out.println("Step : Click on the Welcome button at the top right side of the page.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the Welcome button at the top right side of the page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(resetSecurityQue));
		Assert.assertTrue(resetSecurityQue.isDisplayed(), "Element is not displayed after waiting");
		System.out.println("'Reset Security Question' options should be displayed in the dropdown.");
		ObjectRepo.getTest().log(Status.PASS, "'Reset Security Question' options should be displayed in the dropdown..");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(resetSecurityQue));
		resetSecurityQue.click();
		System.out.println("Step : Click on the 'Reset Security Questions' option");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Reset Security Questions' option");
	}

	public void navigateChangePassphrase() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(welcomeDropdown));
		try {
			welcomeDropdown.click();
		} catch (Exception e) {
			welcomeDropdown.click();
			e.printStackTrace();
		}
		System.out.println("Step : Click on the Welcome button at the top right side of the page.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the Welcome button at the top right side of the page.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(changePassphrase));
		Assert.assertTrue(changePassphrase.isDisplayed(), "Element is not displayed after waiting");
		System.out.println("'Change Passphrase' options should be displayed in the dropdown.");
		ObjectRepo.getTest().log(Status.PASS, "'Change Passphrase' options should be displayed in the dropdown..");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(changePassphrase));
		changePassphrase.click();
		System.out.println("Step : Click on the 'Change Passphrase' option");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Change Passphrase' option");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void navigateToAuction() {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
		}
		driver.get("https://www.qa-wci-auction.org/");
	}

	public void createUser() {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView();", nemwpasspharsefield);
		wait.until(ExpectedConditions.elementToBeClickable(nemwpasspharsefield));
		try {
			nemwpasspharsefield.click();
		} catch (Exception e) {
			nemwpasspharsefield.click();
			e.printStackTrace();
		}
		nemwpasspharsefield.sendKeys("Test@1234");
		confirmpasspharsefield.click();
		confirmpasspharsefield.sendKeys("Test@1234");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", checkbox);		

		Select select = new Select(dropdown1);
		select.selectByVisibleText("In which city or town was your first job?");
		answer1.click();
		answer1.sendKeys("Answer_1");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", answer2);		
		wait.until(ExpectedConditions.elementToBeClickable(dropdown2));
		Select select1 = new Select(dropdown2);
		select1.selectByVisibleText("What was the last name of your third-grade teacher?");
		answer2.click();
		answer2.sendKeys("Answer_2");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", checkbox);		
		wait.until(ExpectedConditions.elementToBeClickable(dropdown3));
		Select select2 = new Select(dropdown3);
		select2.selectByVisibleText("In which year did you get your first credit card?");
		answer3.click();
		answer3.sendKeys("Answer_3");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Actions act = new Actions(driver);
		act.moveToElement(checkbox).build().perform();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		checkbox.click();
		wait.until(ExpectedConditions.elementToBeClickable(submit));
		submit.click();
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToYopmail(String mailid) {
		driver.navigate().to("https://yopmail.com/en/");
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='login']")).sendKeys(Keys.CONTROL + "a");
		driver.findElement(By.xpath("//*[@id='login']")).sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//*[@id='login']")).sendKeys(mailid);
		driver.findElement(By.xpath("//*[@id='refreshbut']/button/i")).click();
		driver.switchTo().frame(Frame_Mail);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(MailContent));
		String mailContent = MailContent.getText();
		System.out.println(mailContent);
		System.out.println("mailContent :" + mailContent);
		ActivationLink.click();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
		}
	}

	public void AddNewUserFunctionality() throws InterruptedException {
		int length = 6;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		randomStringEmail = ("user" + generatedString + "@mailsac.com");
		emailField.sendKeys(randomStringEmail);
		wait.until(ExpectedConditions.elementToBeClickable(verifyEmail));
		verifyEmail.click();
		String EmailSuccessText = verifyEmailSuccessMsg.getText();
		System.out.println(EmailSuccessText);
		ObjectRepo.getTest().log(Status.PASS, EmailSuccessText);
		firstNmeField.sendKeys("Auto");
		LastNmeField.sendKeys("Test");
		WebElement element = userRoleDropDown;
		Select event = new Select(element);
		event.selectByIndex(1);
		ObjectRepo.getTest().log(Status.PASS, "Sponsor User Role selected from dropdown");
		wait.until(ExpectedConditions.elementToBeClickable(addUserBtn));
		addUserBtn.click();
		System.out.println("Step : Enter valid data and click on Add New User Button");
		ObjectRepo.getTest().log(Status.PASS, "Step : Enter valid data and click on Add New User Button");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		String userAddSuccessText = addUserSuccessMsg.getText();
		System.out.println(userAddSuccessText);
		ObjectRepo.getTest().log(Status.PASS,
				"The user has been added successfully message displayed and users activation link send on email");
		String zxc = userStatusTable.getText();
		System.out.println(zxc);
		System.out.println("User id : " + randomString);
		ObjectRepo.getTest().log(Status.PASS, "Added user info is displayed in the table " + userStatusTable);
	}

	public void clickingOnAddNewUserButtonPopup() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(addNewUserButton));
		waitForElementToBeClickable(addNewUserButton);
		System.out.println("Step : Click on the Add New User Button");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the Add New User Button");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}


	public void navigateOnUserAdministrationPage() {
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(administrationMenuFrench));
		waitForElementToBeClickable(administrationMenuFrench);
		System.out.println("Step : Click on the User Administration");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the User Administration");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(userOptionUnderAdministration));
		waitForElementToBeClickable(userOptionUnderAdministration);
		System.out.println("Step : Click on the User Option");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the User Option");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void waitForElementToBeClickable(WebElement element) {
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
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
				//				WebElement spinner = driver.findElement(By.id("appSpinner"));

				//				wait.until(ExpectedConditions.invisibilityOf(spinner));
			}
		}
	}

}
