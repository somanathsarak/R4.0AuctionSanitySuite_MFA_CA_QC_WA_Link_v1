package com.wciauction.pageObjects.user;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;

import static constants.ThreadConstants.driver;

public class AdministrationPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	@FindBy(xpath = "//a[text()='User']")
	private WebElement selectUser;

	@FindBy(xpath = "//span[text()='Add New User']")
	private WebElement btnAddUser;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement EnterEmail;

	@FindBy(xpath = "//span[contains(text(),'Check if email address is already associated')]")
	private WebElement btnTermsofUse;

	@FindBy(xpath = "(//p[contains(text(),'')])[5]")
	private WebElement textErrorMsg;

	public AdministrationPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public void getselectUser() throws InterruptedException {
		selectUser.click();
	}

	public void getbtnAddUser() throws InterruptedException {
		btnAddUser.click();
	}

	public void getEnterEmail() throws InterruptedException {
		EnterEmail.sendKeys("seededsponsor.qc@yopmail.com");
	}

	public void getbtnTermsofUse() throws InterruptedException {
		btnTermsofUse.click();
	}

	public void gettextErrorMsg() throws InterruptedException {
		textErrorMsg.click();
	}

	public void verify_Login_and_logout_functionality_for_EA() throws InterruptedException {
		PublicHomePage login = new PublicHomePage();
		login.EALoginQc();
		login.handleSecurityQue();
		login.clickOnnext();
		ObjectRepo.getTest().log(Status.PASS, "Quebec Event Admin login successfully");
		ApprovalStatusPage ref = new ApprovalStatusPage();
		ref.Logout();
	}
}
