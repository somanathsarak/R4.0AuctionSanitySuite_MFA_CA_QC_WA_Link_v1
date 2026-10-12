package com.wciauction.pageObjects.user;
 
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
 
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
 
public class ForgotPassphrasePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private Actions action;
	
	@FindBy(xpath = "//button[@data-test='loginBTN']")
	public WebElement btnLogin;
	
	@FindBy(xpath = "//*[text()='Forgot Passphrase']")
	private WebElement ForgotPassphrase_PublicHomePage;
 
	@FindBy(xpath = "//p[text()='An email with instructions to reset your passphrase has been sent.']")
	private WebElement SuccessMsg_ForgotPassphrase_PublicHomePage;
 
	@FindBy(xpath = "//*[@placeholder='Enter your email address']")
	private WebElement EmailAddressField_ForgotPassphrase_PublicHomePage;
 
	@FindBy(xpath = "//button//*[text()='Submit']")
	private WebElement SubmitBtn_ForgotPassphrase_PublicHomePage;
	
	@FindBy(xpath = "//*[@id='mail']")
	private WebElement MailContent;
	
	@FindBy(xpath = "//iframe[@name='ifmail']")
	private WebElement Frame_Mail;
	
	@FindBy(xpath = "//a[text()='Activation link']")
	private WebElement ActivationLink;
	
	@FindBy(xpath = "(//*[@id='demo-customized-select'])[1]")
	private WebElement StatusDropdown;
	
	@FindBy(xpath = "(//*[@id='demo-customized-select'])[2]")
	private WebElement JurisdictionDropdown;
	
	@FindBy(xpath = "//*[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']")
	public List<WebElement> threeDots;
	
	@FindBy(xpath = "(//*[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[2]")
	private WebElement second_Threedot;
	
	@FindBy(xpath = "(//*[@class='MuiList-root jss1008 MuiList-padding']/li)[1]")
	private WebElement ActivateOption;
	
	
	
	
	public ForgotPassphrasePage(){
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
		
	}
	
public void forgetPasphraseFunctionality(String email) {
		
	waituntil(ExpectedConditions.visibilityOf(btnLogin));
	btnLogin.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			ForgotPassphrase_PublicHomePage.click();
		} catch (Exception e) {
			js.executeScript("arguments[0].click();", ForgotPassphrase_PublicHomePage);
		}
		wait.until(ExpectedConditions.elementToBeClickable(EmailAddressField_ForgotPassphrase_PublicHomePage));
		Assert.assertEquals(EmailAddressField_ForgotPassphrase_PublicHomePage.isDisplayed(), true);
		ObjectRepo.getTest().log(Status.PASS, "'Forgot Passphrase' pop-up with email address field gets opened");
		ObjectRepo.getTest().log(Status.PASS, "Step:  Enter valid registered email address & click on 'SUBMIT' button.");
		EmailAddressField_ForgotPassphrase_PublicHomePage.sendKeys(email);
		
		try {
			Thread.sleep(3000);
			SubmitBtn_ForgotPassphrase_PublicHomePage.click();
		} catch (InterruptedException e) {
			SubmitBtn_ForgotPassphrase_PublicHomePage.click();
		}
		
		wait.until(ExpectedConditions.visibilityOf(SuccessMsg_ForgotPassphrase_PublicHomePage));
		Assert.assertEquals(SuccessMsg_ForgotPassphrase_PublicHomePage.getText(),
				"An email with instructions to reset your passphrase has been sent.");
		ObjectRepo.getTest().log(Status.PASS,
				"Toast success message is displayed as 'An email with instructions to reset your passphrase has been sent.'");
	}
 
	private void waituntil(ExpectedCondition<WebElement> visibilityOf) {
	// TODO Auto-generated method stub	
}
 
	public void forgetPasphraseFunctionality_Invalidemail(String email) {
		
		waituntil(ExpectedConditions.visibilityOf(btnLogin));
		btnLogin.click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				ForgotPassphrase_PublicHomePage.click();
			} catch (Exception e) {
				js.executeScript("arguments[0].click();", ForgotPassphrase_PublicHomePage);
			}
			wait.until(ExpectedConditions.elementToBeClickable(EmailAddressField_ForgotPassphrase_PublicHomePage));
			Assert.assertEquals(EmailAddressField_ForgotPassphrase_PublicHomePage.isDisplayed(), true);
			ObjectRepo.getTest().log(Status.PASS, "'Forgot Passphrase' pop-up with email address field gets opened");
			ObjectRepo.getTest().log(Status.PASS, "Step:  Enter invalid registered email address & click on 'SUBMIT' button.");
			EmailAddressField_ForgotPassphrase_PublicHomePage.sendKeys(email);
			
			try {
				Thread.sleep(3000);
				SubmitBtn_ForgotPassphrase_PublicHomePage.click();
			} catch (InterruptedException e) {
				SubmitBtn_ForgotPassphrase_PublicHomePage.click();
			}
			
			wait.until(ExpectedConditions.visibilityOf(SuccessMsg_ForgotPassphrase_PublicHomePage));
			Assert.assertEquals(SuccessMsg_ForgotPassphrase_PublicHomePage.getText(),
					"An email with instructions to reset your passphrase has been sent.");
			ObjectRepo.getTest().log(Status.PASS,
					"Toast success message is displayed as 'An email with instructions to reset your passphrase has been sent.'");
		}
	
	public void activateUser() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(StatusDropdown));
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", StatusDropdown);
		StatusDropdown.click();
		Select dropdown = new Select(StatusDropdown);
		dropdown.selectByIndex(2);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", second_Threedot);
		driver.findElement(By.xpath("(//*[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[1]")).click();	
		System.out.println("Step : Click on the three dots button in front of any inactive user.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the three dots button in front of any inactive user.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement activateOption = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@role='menuitem'])[1]")));
		WebElement resetSecurityOption = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@role='menuitem'])[2]")));
		if (activateOption.isDisplayed() && resetSecurityOption.isDisplayed()) {
			System.out.println("Activate' and 'Reset Security Questions' options should be displayed.");
			ObjectRepo.getTest().log(Status.PASS, "Activate' and 'Reset Security Questions' options should be displayed.");
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Expected options are missing in the dropdown.");
			return;
		}
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		activateOption.click();
		WebElement confirmActivateBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[text()='Activate'])[2]")));
		WebElement cancelBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Cancel']")));
		if (confirmActivateBtn.isDisplayed() && cancelBtn.isDisplayed()) {
			ObjectRepo.getTest().log(Status.PASS, "confirmation popup message is displayed with'ACTIVATE' &'CANCEL' button");
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Confirmation popup buttons are missing.");
			return;
		}
		confirmActivateBtn.click();
		System.out.println("Step : Click on the 'Activate' option and click on the 'Activate' button on the warning Pop Up");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Activate' option and click on the 'Activate' button on the warning Pop Up");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[text()='User-entity relationship(s) activated successfully']")));
		if (successMsg.isDisplayed()) {
			System.out.println("Success message should be displayed as = " + successMsg.getText());
			ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed as = " + successMsg.getText());
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Success message not displayed.");
			return;
		}
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}
	
	public void deactivateUser() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(StatusDropdown));
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", StatusDropdown);
		StatusDropdown.click();
		Select dropdown = new Select(StatusDropdown);
		dropdown.selectByIndex(1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Select juri = new Select(JurisdictionDropdown);
		juri.selectByIndex(1);
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", second_Threedot);
		driver.findElement(By.xpath("(//*[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[1]")).click();
		System.out.println("Step : Click on the three dots button in front of any Active user.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the three dots button in front of any Active user.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement activateOption = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@role='menuitem'])[1]")));
		WebElement resetSecurityOption = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@role='menuitem'])[2]"))); 
		if (activateOption.isDisplayed() && resetSecurityOption.isDisplayed()) {
			System.out.println("Deactivate' and 'Reset Security Questions' options should be displayed.");
			ObjectRepo.getTest().log(Status.PASS, "Deactivate' and 'Reset Security Questions' options should be displayed.");
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Expected options are missing in the dropdown.");
			return;
		}
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		activateOption.click();
		WebElement confirmActivateBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[text()='Deactivate'])[2]")));
		WebElement cancelBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Cancel']"))); 
		if (confirmActivateBtn.isDisplayed() && cancelBtn.isDisplayed()) {
			ObjectRepo.getTest().log(Status.PASS, "confirmation popup message is displayed with'ACTIVATE' &'CANCEL' button");
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Confirmation popup buttons are missing.");
			return;
		}
		confirmActivateBtn.click();
		System.out.println("Step : Click on the 'Deactivate' option and click on the 'Deactivate' button on the warning Pop Up.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Deactivate' option and click on the 'Deactivate' button on the warning Pop Up.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[text()='User-entity relationship(s) deactivated successfully']")));
		if (successMsg.isDisplayed()) {
			System.out.println("Success message should be displayed as = " + successMsg.getText());
			ObjectRepo.getTest().log(Status.PASS, "Success message should be displayed as = " + successMsg.getText());
		} else {
			ObjectRepo.getTest().log(Status.FAIL, "Success message not displayed.");
			return;
		}
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}
 
}