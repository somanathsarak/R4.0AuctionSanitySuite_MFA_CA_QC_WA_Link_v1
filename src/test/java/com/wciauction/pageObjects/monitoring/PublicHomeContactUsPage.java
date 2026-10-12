package com.wciauction.pageObjects.monitoring;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class PublicHomeContactUsPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
    
	
	@FindBy(xpath = "//a[text()='Contact Us']")
	private WebElement contactUsBtn;

	@FindBy(xpath = "//body[@id='tinymce']")
	private WebElement messageField;

	@FindBy(xpath = "//*[text()='Send']")
	private WebElement sendBtn;

	@FindBy(xpath = "//*[text()='Thank you for your inquiry.']")
	private WebElement getSuccessMsg;

	@FindBy(xpath = "//*[text()='Please enter a valid email address']")
	private WebElement emailErrorMsg;

	@FindBy(xpath = "(//*[text()='This field is required'])[1]")
	private WebElement errorMsg1;

	@FindBy(xpath = "(//*[text()='This field is required'])[2]")
	private WebElement errorMsg2;

	@FindBy(xpath = "//input[@placeholder='Enter your full name']")
	private WebElement name;

	@FindBy(xpath = "//input[@placeholder='Enter your phone with country code']")
	private WebElement phone;

	@FindBy(xpath = "//input[@placeholder='Enter your email address']")
	private WebElement email;
	
	public PublicHomeContactUsPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }

	public void ContactUsNameFieldValidation() throws InterruptedException {
		WebElement event = contactUsBtn;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		Thread.sleep(3000);
		action.clickAndHold(event).click().build().perform();
		System.out.println("Step : Click on the 'Contact Us' button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Contact Us' button");	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		Assert.assertTrue(sendBtn.isDisplayed(), "Element is not displayed on the page");
		System.out.println("User should be navigated to 'Contact Us' page.");
		ObjectRepo.getTest().log(Status.PASS, "User should be navigated to 'Contact Us' page.");	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");	
		WebElement element = sendBtn;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(3000);
		sendBtn.click();
		System.out.println("Step : Click on the 'Send' button on the Contact Us page.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Send' button on the Contact Us page.");	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		String name = errorMsg1.getText();
		Assert.assertTrue(errorMsg1.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Error message should be displayed = " + name);
		ObjectRepo.getTest().log(Status.PASS, "Error message should be displayed = " + name);	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");	
		String phone = errorMsg2.getText();
		Assert.assertTrue(errorMsg2.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Error message should be displayed = " + phone);
		ObjectRepo.getTest().log(Status.PASS, "Error message should be displayed = " + phone);	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");	
		email.sendKeys("a2");
		wait.until(ExpectedConditions.visibilityOf(emailErrorMsg));
		String emailmsg = emailErrorMsg.getText();
		Assert.assertTrue(emailErrorMsg.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Error message should be displayed = " + emailmsg);
		ObjectRepo.getTest().log(Status.PASS, "Error message should be displayed = " + emailmsg);	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");	
		email.sendKeys(Keys.BACK_SPACE);
		email.sendKeys(Keys.BACK_SPACE);
		String email = errorMsg2.getText();
		Assert.assertTrue(errorMsg2.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Error message should be displayed = " + email);
		ObjectRepo.getTest().log(Status.PASS, "Error message should be displayed = " + email);	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		String msg = errorMsg2.getText();
		Assert.assertTrue(errorMsg2.isDisplayed(), "Element is not displayed on the page");
		System.out.println("Error message should be displayed = " + msg);
		ObjectRepo.getTest().log(Status.PASS, "Error message should be displayed = " + msg);	
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}

	public void contactUsForm()  {
		wait.until(ExpectedConditions.elementToBeClickable(contactUsBtn));
		try {
			contactUsBtn.click();
		} catch (Exception e) {
			contactUsBtn.click();
		}
		WebElement element = name;
		js.executeScript("arguments[0].scrollIntoView();", element);
		name.sendKeys("testSoftware @123");
		phone.sendKeys("02024256123");
		email.sendKeys("test@yopmail.com");
		driver.switchTo().frame(0);
		messageField.sendKeys("WCI Auction Platform Pune");
		System.out.println("Filled up the all Mandatory fields");
		ObjectRepo.getTest().log(Status.PASS, "Filled up the all Mandatory fields");
		driver.switchTo().parentFrame();
		sendBtn.click();
		System.out.println("Step : Fill valid details in all the mandatory fields and click on send button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Fill valid details in all the mandatory fields and click on send button.");
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
		wait.until(ExpectedConditions.visibilityOf(getSuccessMsg));
		String msg = getSuccessMsg.getText();
		System.out.println("Success message should be displayed as :- " + msg);
		Assert.assertEquals("Thank you for your inquiry.", msg);
		ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed as :- " + msg);
		System.out.println("---------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "---------------------------------------------------------------");
	}
}