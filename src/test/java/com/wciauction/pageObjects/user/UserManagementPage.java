package com.wciauction.pageObjects.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
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

import static com.wci.auction.org.resources.CommonMethods.folderPath;

public class UserManagementPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//*[text()='User']")
	private WebElement btnUser;

	@FindBy(xpath = "//*[text()='Reserve Price']")
	private WebElement btnReservePrice;

	@FindBy(xpath = "(//div[@class='MuiGrid-root MuiGrid-item'])[4]")
	private WebElement AddNewUserBtn;

	@FindBy(xpath = "//*[text()='USER ACTIVITY AUDIT REPORT']")
	private WebElement userActivityAudReportBtn;

	@FindBy(xpath = "//*[text()='USER MGMT REPORT']")
	private WebElement userMNGMNTBtn;

	@FindBy(xpath = "//input[@placeholder='Start Date']")
	private WebElement selectActivityReportDate;

	@FindBy(xpath = "//*[text()='Download']")
	private WebElement downLoadBtn;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement AddedUserTableRow1;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root']")
	private WebElement ReservePricePopUpXBtn;

	@FindBy(xpath = "//*[text()='How Auction Reserve Price Administration Works']")
	private WebElement ReservePriceAdminStrationBtn;

	@FindBy(xpath = "//*[text()='Check if email address is already associated to a platform user']")
	private WebElement verifyEmail;

	@FindBy(xpath = "//*[text()='This user does not exist and may be added.']")
	private WebElement verifyEmailSuccessMsg;

	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstNmeField;

	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement LastNmeField;

	@FindBy(xpath = "(//select[@id='demo-customized-select'])[3]")
	private WebElement userRoleDropDown;

	@FindBy(xpath = "//*[text()='ADD USER']")
	private WebElement addUserBtn;

	@FindBy(xpath = "//*[text()='Cancel']")
	private WebElement cancelBtn;

	@FindBy(xpath = "//*[text()='The user has been added successfully.']")
	private WebElement addUserSuccessMsg;

	@FindBy(xpath = "//*[text()='This email address is already associated to an Auction Platform user.']")
	private WebElement existindAddUserFailureMsg;

	@FindBy(xpath = "//*[text()='This email address is already associated to an Auction Platform Sponsor user in Quebec.']")
	private WebElement QcExistindAddUserFailureMsg;

	@FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
	private WebElement userStatusTable;

	@FindBy(xpath = "//*[text()='Add a New User']")
	private WebElement AddNewUserHeadrLBL;

	@FindBy(xpath = "(//*[@class='MuiFormLabel-root Mui-required'])[1]")
	private WebElement EmailAdressText;

	@FindBy(xpath = "(//*[@class='MuiFormLabel-root Mui-required'])[2]")
	private WebElement firstName;

	@FindBy(xpath = "(//*[@class='MuiFormLabel-root Mui-required'])[3]")
	private WebElement lastName;

	@FindBy(xpath = "(//*[@class='MuiFormLabel-root Mui-required'])[4]")
	private WebElement roleText;

	@FindBy(xpath = "//*[text()='Email is not valid']")
	private WebElement emailErrorMsg;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-filled'])[1]")
	private WebElement firstNmeErrorMsg;

	@FindBy(xpath = "(//p[@class='MuiFormHelperText-root Mui-error MuiFormHelperText-filled'])[2]")
	private WebElement lastNmeErrorMsg;

	 public UserManagementPage() {
		 this.driver = BaseClass.getDriver();
			this.wait = BaseClass.getWait();
			this.js=BaseClass.getJs();
			PageFactory.initElements(driver, this);
	    }
	 
	public void clickAnUserBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnUser));
		WebElement abc1 = btnUser;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, " Click on User button");
	}

	public void clickAnReservePriceBtn() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnReservePrice));
		WebElement abc1 = btnReservePrice;
		Actions action = new Actions(driver);
		action.moveToElement(abc1).build().perform();
		action.clickAndHold(abc1).build().perform();
		action.click(abc1).build().perform();
		ObjectRepo.getTest().log(Status.PASS, " Click on Reserve Price button");
	}

	public void AddNewUserFunctionality() throws InterruptedException {
		String userText = AddNewUserBtn.getText();
		System.out.println(userText);
		ObjectRepo.getTest().log(Status.PASS, "Add new user button available on User Administration Screen");
		wait.until(ExpectedConditions.elementToBeClickable(AddNewUserBtn));
		AddNewUserBtn.click();
		System.out.println("Add new user page is displayed with fields");
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text = AddNewUserHeadrLBL.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text1 = EmailAdressText.getText();
		System.out.println(text1);
		ObjectRepo.getTest().log(Status.PASS, "Email Adress field is displayed");
		String text2 = firstName.getText();
		System.out.println(text2);
		ObjectRepo.getTest().log(Status.PASS, "First Name field is displayed");
		String text3 = lastName.getText();
		System.out.println(text3);
		ObjectRepo.getTest().log(Status.PASS, "Last Name field is displayed");
		String text4 = roleText.getText();
		System.out.println(text4);
		ObjectRepo.getTest().log(Status.PASS, "User Role field is displayed");
		int length = 6;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String randomString = ("user" + generatedString + "@yopmail.com");
		emailField.sendKeys(randomString);
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
		String userAddSuccessText = addUserSuccessMsg.getText();
		System.out.println(userAddSuccessText);
		ObjectRepo.getTest().log(Status.PASS,
				"The user has been added successfully message displayed and users activation link send on email");
		String zxc = userStatusTable.getText();
		System.out.println(zxc);
		ObjectRepo.getTest().log(Status.PASS, "Added user info is displayed in the table " + userStatusTable);
	}

	public void tryingExistingAddedUserFunctionality() throws InterruptedException {
		String tableRow1 = AddedUserTableRow1.getText();
		System.out.println("Before trying added existing user " + tableRow1);
		ObjectRepo.getTest().log(Status.PASS, "Before trying added existing user " + tableRow1);
		String userText = AddNewUserBtn.getText();
		System.out.println(userText);
		ObjectRepo.getTest().log(Status.PASS, "Add new user button available on User Administration Screen");
		wait.until(ExpectedConditions.elementToBeClickable(AddNewUserBtn));
		AddNewUserBtn.click();
		System.out.println("Add new user page is displayed with fields");
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text = AddNewUserHeadrLBL.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text1 = EmailAdressText.getText();
		System.out.println(text1);
		ObjectRepo.getTest().log(Status.PASS, "Email Adress field is displayed");
		String text2 = firstName.getText();
		System.out.println(text2);
		ObjectRepo.getTest().log(Status.PASS, "First Name field is displayed");
		String text3 = lastName.getText();
		System.out.println(text3);
		ObjectRepo.getTest().log(Status.PASS, "Last Name field is displayed");
		String text4 = roleText.getText();
		System.out.println(text4);
		ObjectRepo.getTest().log(Status.PASS, "User Role field is displayed");
		emailField.sendKeys("seededsponsor.ca@yopmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(verifyEmail));
		verifyEmail.click();
		String failureMsg = existindAddUserFailureMsg.getText();
		System.out.println(failureMsg);
		ObjectRepo.getTest().log(Status.PASS, failureMsg);
		String tableRow2 = AddedUserTableRow1.getText();
		System.out.println("After trying added existing user " + tableRow2);
		ObjectRepo.getTest().log(Status.PASS, "After trying added existing user " + tableRow2);
		Assert.assertEquals(tableRow1, tableRow2);
		System.out.println("Exsting user is not getting added in the platform again.");
	}

	public void CancelAddUser() throws InterruptedException {
		String tableRow1 = AddedUserTableRow1.getText();
		System.out.println("Before Cancel add user :" + tableRow1);
		ObjectRepo.getTest().log(Status.PASS, "Before cancel add user :" + tableRow1);
		String userText = AddNewUserBtn.getText();
		System.out.println(userText);
		ObjectRepo.getTest().log(Status.PASS, "Add new user button available on User Administration Screen");
		wait.until(ExpectedConditions.elementToBeClickable(AddNewUserBtn));
		AddNewUserBtn.click();
		System.out.println("Add new user page is displayed with fields");
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text = AddNewUserHeadrLBL.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text1 = EmailAdressText.getText();
		System.out.println(text1);
		ObjectRepo.getTest().log(Status.PASS, "Email Adress field is displayed");
		String text2 = firstName.getText();
		System.out.println(text2);
		ObjectRepo.getTest().log(Status.PASS, "First Name field is displayed");
		String text3 = lastName.getText();
		System.out.println(text3);
		ObjectRepo.getTest().log(Status.PASS, "Last Name field is displayed");
		String text4 = roleText.getText();
		System.out.println(text4);
		ObjectRepo.getTest().log(Status.PASS, "User Role field is displayed");
		emailField.sendKeys("autotesteqanim@yopmail.com");
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
		wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
		cancelBtn.click();
		String tableRow2 = AddedUserTableRow1.getText();
		System.out.println("After Cancel add user :" + tableRow2);
		ObjectRepo.getTest().log(Status.PASS, "After cancel add user :" + tableRow2);
		Assert.assertEquals(tableRow1, tableRow2);
		System.out.println("After clicking cancel button user does not added in the auction platform ");
		ObjectRepo.getTest().log(Status.PASS, "After clicking cancel button user does not added in the auction platform");
	}

	public void addUserFieldValidation() throws InterruptedException {
		String tableRow1 = AddedUserTableRow1.getText();
		System.out.println("Before Cancel add user :" + tableRow1);
		ObjectRepo.getTest().log(Status.PASS, "Before cancel add user :" + tableRow1);
		String userText = AddNewUserBtn.getText();
		System.out.println(userText);
		ObjectRepo.getTest().log(Status.PASS, "Add new user button available on User Administration Screen");
		wait.until(ExpectedConditions.elementToBeClickable(AddNewUserBtn));
		AddNewUserBtn.click();
		System.out.println("Add new user page is displayed with fields");
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text = AddNewUserHeadrLBL.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		emailField.sendKeys("auto");
		String emailText = emailErrorMsg.getText();
		System.out.println(emailText + " Error message is displayed");
		ObjectRepo.getTest().log(Status.PASS, " Error message is displayed");
		emailField.sendKeys(Keys.BACK_SPACE);
		emailField.sendKeys(Keys.BACK_SPACE);
		emailField.sendKeys(Keys.BACK_SPACE);
		emailField.sendKeys(Keys.BACK_SPACE);
		emailField.sendKeys("autotesteqanim@yopmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(verifyEmail));
		verifyEmail.click();
		String EmailSuccessText = verifyEmailSuccessMsg.getText();
		System.out.println(EmailSuccessText);
		ObjectRepo.getTest().log(Status.PASS, EmailSuccessText);
		firstNmeField.sendKeys("@@@@@");
		LastNmeField.sendKeys("@@@@");
		wait.until(ExpectedConditions.elementToBeClickable(addUserBtn));
		addUserBtn.click();
		String text1 = firstNmeErrorMsg.getText();
		System.out.println(text1 + " Error message is displayed");
		ObjectRepo.getTest().log(Status.PASS, text1 + " Error message is displayed");
		String text2 = lastNmeErrorMsg.getText();
		System.out.println(text2 + " Error message is displayed");
		ObjectRepo.getTest().log(Status.PASS, text2 + " Error message is displayed");
	}

	public void HowReservePriceWorks() {
		wait.until(ExpectedConditions.elementToBeClickable(ReservePriceAdminStrationBtn));
		ReservePriceAdminStrationBtn.click();
		System.out.println(
				"It opens a pop up window with text explaining the same How Auction Reserve Price Administration Works info is displayed");
		ObjectRepo.getTest().log(Status.PASS,
				"It opens a pop up window with text explaining the same How Auction Reserve Price Administration Works info is displayed");
		ReservePricePopUpXBtn.click();
		System.out.println("After Clicking on (X) closes the window.");
		ObjectRepo.getTest().log(Status.PASS, "After Clicking on (X) closes the window.");

	}

	public void addExistingUserForAnotherJurisdiction() throws InterruptedException {
		String tableRow1 = AddedUserTableRow1.getText();
		System.out.println("Before trying added existing user :- " + tableRow1);
		ObjectRepo.getTest().log(Status.PASS, "Before trying added existing user :- " + tableRow1);
		String userText = AddNewUserBtn.getText();
		System.out.println(userText);
		ObjectRepo.getTest().log(Status.PASS, "Add new user button available on User Administration Screen");
		wait.until(ExpectedConditions.elementToBeClickable(AddNewUserBtn));
		AddNewUserBtn.click();
		System.out.println("Add new user page is displayed with fields");
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text = AddNewUserHeadrLBL.getText();
		System.out.println(text);
		ObjectRepo.getTest().log(Status.PASS, "Add new user page is displayed with fields");
		String text1 = EmailAdressText.getText();
		System.out.println(text1);
		ObjectRepo.getTest().log(Status.PASS, "Email Adress field is displayed");
		String text2 = firstName.getText();
		System.out.println(text2);
		ObjectRepo.getTest().log(Status.PASS, "First Name field is displayed");
		String text3 = lastName.getText();
		System.out.println(text3);
		ObjectRepo.getTest().log(Status.PASS, "Last Name field is displayed");
		String text4 = roleText.getText();
		System.out.println(text4);
		ObjectRepo.getTest().log(Status.PASS, "User Role field is displayed");
		emailField.sendKeys("seededsponsor.qc@yopmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(verifyEmail));
		verifyEmail.click();
		String failureMsg = QcExistindAddUserFailureMsg.getText();
		System.out.println(failureMsg);
		ObjectRepo.getTest().log(Status.PASS, failureMsg);
		String tableRow2 = AddedUserTableRow1.getText();
		System.out.println("After trying added existing user :- " + tableRow2);
		ObjectRepo.getTest().log(Status.PASS, "After trying added existing user :- " + tableRow2);
		Assert.assertEquals(tableRow1, tableRow2);
		System.out.println("Another Exsting Jurisdiction user is not getting added in the platform again.");
	}

	public void DownloadTheUserActivityAuditReport() throws InterruptedException {
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
		System.out.println("Sponsor downloaded the User activity audit report");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor downloaded the User activity audit report");
	}

	public void DownloadTheUserManagementReport() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(userMNGMNTBtn));
		userMNGMNTBtn.click();
		System.out.println("Sponsor downloaded the User Management report");
		ObjectRepo.getTest().log(Status.PASS, "Sponsor downloaded the User Management report");
	}
}