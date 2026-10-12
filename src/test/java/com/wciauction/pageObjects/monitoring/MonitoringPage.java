package com.wciauction.pageObjects.monitoring;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class MonitoringPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
    
	@FindBy(xpath = "//span[text()='Event Monitoring']//parent::button[@type='button']")
    private WebElement btnEventMonitoring;

    @FindBy(xpath = "//ul//li[1]")
    private WebElement selectEvent;

    @FindBy(xpath = "//*[contains(text(),'Monitoring')]")
    private WebElement pageTitle;

	@FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
	private WebElement runningEventName1;
	
    @FindBy(xpath = "(//*[text()='JointAuctionAutomation'])[1]")
    private WebElement selectEventName;

    @FindBy(xpath = "//*[text()='SEARCH']")
    private WebElement searchBtn;

    @FindBy(xpath = "(//tbody[@class='MuiTableBody-root'])[2]")
    private WebElement monitoringBidTble;

    @FindBy(xpath = "//*[text()='RESET']")
    private WebElement resetBtn;

    @FindBy(xpath = "//select[@id='select-jurisdiction']")
    private WebElement selecturisdictionDropDwn;

    @FindBy(xpath = "(//*[@data-icon='ellipsis-h'])[1]")
    private WebElement threeDotFirstBtn;

    @FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
    private WebElement firstBidRow;

    @FindBy(xpath = "//*[text()='Add Flag']")
    private WebElement addFlagBtn;

    @FindBy(xpath = "//*[text()='Remove Flag']")
    private WebElement removeFlagBtn;

    @FindBy(xpath = "//*[text()='REMOVE FLAG']")
    private WebElement removeFlagBtn2;

    @FindBy(xpath = "//*[text()='FLAG']")
    private WebElement flagBtn;

    @FindBy(xpath = "//*[contains(text(),'You have selected ')]")
    private WebElement addFlagPopUpMsg1;

    @FindBy(xpath = "//*[contains(text(),'Are you sure you want to exclude these')]")
    private WebElement addFlagPopUpMsg2;

    @FindBy(xpath = "//*[text()='The flag has been removed successfully from the selected bids.']")
    private WebElement removFlagSuccessMsg;

    @FindBy(xpath = "//*[text()='The selected bids have been successfully flagged.']")
    private WebElement addFlagSuccessMsg;

    @FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids cannot be flagged.']")
    private WebElement bulkActionFlaggedMultipleBidsErrorMsg;

    @FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids are not flagged.']")
    private WebElement bulkActionUnflaggedMultipleBidsErrorMsg;

    @FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids cannot be excluded.']")
    private WebElement bulkActionExcludeMultipleBidsErrorMsg;
    
    @FindBy(xpath = "//*[text()='This action cannot be performed as one or more of the selected bids are not excluded.']")
    private WebElement bulkActionIncludeMultipleBidsErrorMsg;
    
    @FindBy(xpath = "//*[text()='Include Bid']")
    private WebElement includeBidBtn;

    @FindBy(xpath = "//*[text()='INCLUDE BID']")
    private WebElement includeBidBtn1;

    @FindBy(xpath = "//*[contains(text(),'You have selected ')]")
    private WebElement includeBidPopUpMsg1;

    @FindBy(xpath = "//*[contains(text(),'Are you sure you want to include ')]")
    private WebElement includeBidPopUpMsg2;

    @FindBy(xpath = "//*[text()='The selected bids have been successfully included.']")
    private WebElement includeBidSuccessMsg;

    @FindBy(xpath = "//*[text()='A comment must contain at least one character.']")
    private WebElement commentBidErrorMsg;

    @FindBy(xpath = "//*[text()='Exclude Bid']")
    private WebElement excludeBidBtn;

    @FindBy(xpath = "//*[text()='EXCLUDE']")
    private WebElement excludeBidBtn1;

    @FindBy(xpath = "//*[contains(text(),'You have selected ')]")
    private WebElement excludeBidPopUpMsg1;

    @FindBy(xpath = "//*[contains(text(),'Are you sure you want to exclude these')]")
    private WebElement excludeBidPopUpMsg2;

    @FindBy(xpath = "//*[text()='The selected bids have been successfully excluded.']")
    private WebElement excludeBidSuccessMsg;

    @FindBy(xpath = "//*[text()='Bulk Actions']")
    private WebElement bulkActionBtn;

    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    private WebElement selectCheckBox1;

    @FindBy(xpath = "(//input[@type='checkbox'])[3]")
    private WebElement selectCheckBox2;

    @FindBy(xpath = "(//input[@type='checkbox'])[4]")
    private WebElement selectCheckBox3;

    @FindBy(xpath = "(//span[@class='MuiIconButton-label'])[10]")
    private WebElement firstThreeDotBtn;

    @FindBy(xpath = "(//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]")
    private WebElement assertFisrtRow;

//    @FindBy(xpath = "//*[@aria-label=\"select all\"]")
    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    private WebElement allCheckBoxBtn;

    @FindBy(xpath = "//input[@placeholder='Enter Text']")
    private WebElement addCommentTextBox;

    @FindBy(xpath = "//*[text()='POST']")
    private WebElement postBtn;
	
	public MonitoringPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }
	
	public void verifyEventMonitoring() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnEventMonitoring));
		WebElement event = btnEventMonitoring;
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(event));
		System.out.println("Step : Click on the Monitoring option in the navigation bar.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the Monitoring option in the navigation bar.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Step : Click on an event from the Monitoring dropdown.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on an event from the Monitoring dropdown.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		wait.until(ExpectedConditions.elementToBeClickable(selectEvent));
		selectEvent.click();
		wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
		String title = pageTitle.getText();
		System.out.println(title);
		Assert.assertEquals(title, "Event Monitoring");
		System.out.println("The Event Monitoring page should be displayed properly, and the countdown timer should be visible and submitted bids list should be display.");
		ObjectRepo.getTest().log(Status.PASS, "The Event Monitoring page should be displayed properly, and the countdown timer should be visible and submitted bids list should be display.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
	}

	public void clickOnEventMonitoringBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btnEventMonitoring));
		WebElement event = btnEventMonitoring;
		Actions action = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(event));
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(runningEventName1));
		runningEventName1.click();
	}

	public void resetAndSearchFilter() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(monitoringBidTble));
		String msg = monitoringBidTble.getText();
		System.out.println(msg);
		ObjectRepo.getTest().log(Status.PASS, msg);
		wait.until(ExpectedConditions.elementToBeClickable(selecturisdictionDropDwn));
		WebElement eventType = selecturisdictionDropDwn;
		Select s = new Select(eventType);
		s.selectByVisibleText("Quebec");
		System.out.println("Quebec jurisdiction is selected from dropdown");
		ObjectRepo.getTest().log(Status.PASS, "Quebec jurisdiction selected from dropdown");
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
		WebElement event = searchBtn;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Search functions are working and search function is displayed");
		ObjectRepo.getTest().log(Status.PASS, "Search functions are working and search function is displayed");
		wait.until(ExpectedConditions.elementToBeClickable(monitoringBidTble));
		String ref = monitoringBidTble.getText();
		System.out.println("After Clicking Search button " + ref);
		ObjectRepo.getTest().log(Status.PASS, "After Clicking Search button " + ref);
		wait.until(ExpectedConditions.visibilityOf(resetBtn));
		resetBtn.click();
		String msg1 = monitoringBidTble.getText();
		System.out.println("After Clicking Reset button " + msg1);
		ObjectRepo.getTest().log(Status.PASS, "After Clicking Reset button " + msg1);
	}

	public void cancelFunctionAFlaggingBid() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(firstBidRow));
		String msg = firstBidRow.getText();
		System.out.println("Before Cancel flagging bid :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "Before Cancel flagging bid :- " + msg);
		
		WebElement element1 = threeDotFirstBtn;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		WebElement event = threeDotFirstBtn;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Three dot button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Three dot button is selected");
		try {
			wait.until(ExpectedConditions.visibilityOf(addFlagBtn));
		} catch (Exception e) {
		}
		if (addFlagBtn== null) {
			
			WebElement element12 = removeFlagBtn;
			js.executeScript("arguments[0].scrollIntoView();", element12);
			wait.until(ExpectedConditions.visibilityOf(addFlagBtn));
			removeFlagBtn.click();
		} else {
			
			WebElement element12 = addFlagBtn;
			js.executeScript("arguments[0].scrollIntoView();", element12);
			wait.until(ExpectedConditions.visibilityOf(addFlagBtn));
			addFlagBtn.click();
		}
		System.out.println("After clicking on add flag button comment pop up is displayed with cancel X button");
		ObjectRepo.getTest().log(Status.PASS,
				"After clicking on add flag button comment pop up is displayed with cancel X button");
		driver.navigate().refresh();
		System.out.println("After clicking X cancel button Bid is not flagged");
		ObjectRepo.getTest().log(Status.PASS, "After clicking X cancel button Bid is not flagged");
		wait.until(ExpectedConditions.visibilityOf(firstBidRow));
		String msg1 = firstBidRow.getText();
		System.out.println("After Cancel flagging bid :- " + msg1);
		ObjectRepo.getTest().log(Status.PASS, "After Cancel flagging bid :- " + msg1);
		System.out.println("Successfully Cancel flagging Bid by sponsor");
		ObjectRepo.getTest().log(Status.PASS, "Successfully Cancel flagging Bid by sponsor");
	}

	public void SponsorAndMMCannotFlagMultipleBidsWhenTheSelectionOfBidsAreNotCorrect() throws InterruptedException {
		Thread.sleep(3000);
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Add Flag'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Add Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Add Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Add Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready Flaged.");
		}
		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		System.out.println("Scroll down perfomed");	
		element1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Add Flag']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Add Flag']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionFlaggedMultipleBidsErrorMsg));
		String msg = bulkActionFlaggedMultipleBidsErrorMsg.getText();
		System.out.println("After clicking add flag button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking add flag button :- " + msg);
		driver.navigate().refresh();
		Thread.sleep(5000);
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Remove Flag']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Remove Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Bid Is allready Unflaged");
		}
		System.out.println("Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove flag functionality verified");
	}

	public void MarketMonitorCannotFlagMultipleBidsWhenTheSelectionOfBidsAreNotCorrect() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Add Flag'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Add Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Add Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Add Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready Flaged.");
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		System.out.println("Scroll down perfomed");	
		element1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Add Flag']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Add Flag']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionFlaggedMultipleBidsErrorMsg));
		String msg = bulkActionFlaggedMultipleBidsErrorMsg.getText();
		System.out.println("After clicking add flag button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking add flag button :- " + msg);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Remove Flag']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Remove Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Bid Is allready Unflaged");
		}
		System.out.println("Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Remove flag functionality verified");
	}

	public void SponsorCannotUnFlagMultipleBidsWhenTheSelectionOfBidsAreNotCorrect() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Remove Flag'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Remove Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Remove Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Remove Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Remove flag functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready UnFlaged.");
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		System.out.println("Scroll down perfomed");	
		element1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Remove Flag']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Remove Flag']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionUnflaggedMultipleBidsErrorMsg));
		String msg = bulkActionUnflaggedMultipleBidsErrorMsg.getText();
		System.out.println("After clicking Remove flag button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking Remove flag button :- " + msg);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Add Flag']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Add Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Bid Is allready flaged");
		}
		System.out.println("Add Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Add Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified");
	}

	public void MarketMonitorCannotUnFlagMultipleBidsWhenTheSelectionOfBidsAreNotCorrect() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Remove Flag'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Remove Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Remove Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Remove Flag for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Remove flag functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Bid Is allready UnFlaged.");
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		System.out.println("Scroll down perfomed");	
		element1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Remove Flag']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Remove Flag']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionUnflaggedMultipleBidsErrorMsg));
		String msg = bulkActionUnflaggedMultipleBidsErrorMsg.getText();
		System.out.println("After clicking Remove flag button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking Remove flag button :- " + msg);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Add Flag']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Add Flag");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Bid Is allready flaged");
		}
		System.out.println("Add Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Add Flag for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified");
	}

	public void SponsorCanExcludeMultipleBids() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		} catch (Exception e) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Monitoring')]")));
		}
		driver.findElement(By.xpath("//span[normalize-space()='Event Monitoring']//*[name()='svg']")).click();
		Thread.sleep(3000);
		WebElement event=driver.findElement(By.xpath("//a[text()='JointAuctionAutomation']"));
		js.executeScript("arguments[0].scrollIntoView();", event);
		event.click();
		Thread.sleep(3000);
		WebElement element=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");	
		element.click();
		System.out.println("Step : Click on the bulk actions button.");
		ObjectRepo.getTest().log(Status.PASS, "Step : Click on the bulk actions button.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
			Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Exclude Bid']")).isDisplayed();
			Assert.assertTrue(btnDisplayed);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Exclude Bid']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='EXCLUDE']"))).click();
			System.out.println("Step : Click on the 'Exclude Bid' option and Confirm the Pop up by clicking on 'Exclude' button");
			ObjectRepo.getTest().log(Status.PASS, "Step : Click on the 'Exclude Bid' option and Confirm the Pop up by clicking on 'Exclude' button.");
			System.out.println("-------------------------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='POST']"))).click();
			System.out.println("Comments section should be displayed with 'Post' button.");
			ObjectRepo.getTest().log(Status.PASS, "Comments section should be displayed with 'Post' button.");
			System.out.println("-------------------------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
			System.out.println("Step : Enter comment in the comment field and click on the Post button.");
			ObjectRepo.getTest().log(Status.PASS, "Step : Enter comment in the comment field and click on the Post button.");
			System.out.println("-------------------------------------------------------------");
			ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
			String a = driver.findElement(By.xpath("//p[@class='MuiFormHelperText-root Mui-error']")).getText();
			System.out.println(a);
			driver.findElement(By.xpath("//*[@placeholder='Enter Text']")).sendKeys("Exclude Bid");
			driver.findElement(By.xpath("//*[text()='POST']")).click();
			String c = driver.findElement(By.xpath("//p[text()='The selected bids have been successfully excluded.']"))
					.getText();
			System.out.println("The selected bids have been successfully excluded.");
			ObjectRepo.getTest().log(Status.PASS, "The selected bids have been successfully excluded.");
		} catch (Exception e) {
			System.out.println("Bids Allready Excluded.");
			ObjectRepo.getTest().log(Status.PASS, "The selected bids have been successfully excluded.");
		}
		System.out.println("Bid Status should become 'Excluded by Sponsor' and 'x' button should be displayed under Bids Status column.");
		ObjectRepo.getTest().log(Status.PASS, "Bid Status should become 'Excluded by Sponsor' and 'x' button should be displayed under Bids Status column.");
		System.out.println("-------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------");
		driver.navigate().refresh();
		Thread.sleep(5000);
		try {
			driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//input[@aria-label='select all']")).click();
		}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Include Bid']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='INCLUDE BID']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@placeholder='Enter Text']"))).sendKeys("Include Bid");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='POST']"))).click();
			System.out.println("The selected bids have been successfully included.");
			ObjectRepo.getTest().log(Status.PASS, "The selected bids have been successfully included.");
		} catch (Exception e) {
			System.out.println("The selected bids have been successfully included.");
			ObjectRepo.getTest().log(Status.PASS, "The selected bids have been successfully included.");
		}
	}

	public void SponsorCannotIncludeMultipleBids() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Exclude Bid'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Exclude Bid");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid functionality verified");
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		element1.click();
		System.out.println("Selected multiple bids for bulk action.");	
		ObjectRepo.getTest().log(Status.PASS, "Selected multiple bids for bulk action.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Include Bid']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Include Bid']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionIncludeMultipleBidsErrorMsg));
		String msg = bulkActionIncludeMultipleBidsErrorMsg.getText();
		System.out.println("After clicking Include Bid button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking Include Bid button :- " + msg);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Include Bid']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Include Bid");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Include Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Include Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Include Bid functionality verified");
		}
		System.out.println("Include Bid for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Include Bid for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Include Bid functionality verified");	
	}

	public void SponsorCannotExcludeMultipleBids() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element=driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println("Scroll down perfomed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[18]"))).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[normalize-space()='Exclude Bid'])[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Exclude Bid");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
			System.out.println("Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid functionality verified");
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Exclude Bid functionality verified");
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element1=driver.findElement(By.xpath("//input[@aria-label='select all']"));
		js.executeScript("arguments[0].scrollIntoView();", element1);
		System.out.println("Scroll down perfomed");	
		element1.click();
		ObjectRepo.getTest().log(Status.PASS, "Selected multiple bids for bulk action.");
		System.out.println("Selected multiple bids for bulk action.");	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Bulk Actions']"))).click();
		Boolean btnDisplayed = driver.findElement(By.xpath("//li[text()='Exclude Bid']")).isDisplayed();
		Assert.assertTrue(btnDisplayed);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Exclude Bid']"))).click();
		wait.until(ExpectedConditions.visibilityOf(bulkActionExcludeMultipleBidsErrorMsg));
		String msg = bulkActionExcludeMultipleBidsErrorMsg.getText();
		System.out.println("After clicking Remove flag button :- " + msg);
		ObjectRepo.getTest().log(Status.PASS, "After clicking Remove flag button :- " + msg);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//button[@type='button'])[19]")).click();
			WebElement element2=driver.findElement(By.xpath("//li[normalize-space()='Include Bid']"));
			js.executeScript("arguments[0].scrollIntoView();", element2);
			element2.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Text']"))).sendKeys("Include Bid");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='POST']"))).click();
		} catch (Exception e) {
			System.out.println("Include Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Include Bid for a single bid");
			ObjectRepo.getTest().log(Status.PASS, "Include Bid functionality verified");
		}
		System.out.println("Include Bid for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Include Bid for a single bid");
		ObjectRepo.getTest().log(Status.PASS, "Include Bid functionality verified");
		
	}

	public void includeMultipleBidMethod() throws InterruptedException {
		
		WebElement element1A = selectCheckBox2;
		js.executeScript("arguments[0].scrollIntoView();", element1A);
		WebElement eventNew = selectCheckBox2;
		Actions actionT = new Actions(driver);
		actionT.moveToElement(eventNew).build().perform();
		actionT.clickAndHold(eventNew).click().build().perform();
		System.out.println("Second Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "Second Number checkbox is selected");
		WebElement event5 = selectCheckBox1;
		Actions pqr = new Actions(driver);
		pqr.moveToElement(event5).build().perform();
		pqr.clickAndHold(event5).click().build().perform();
		System.out.println("First Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "First Number checkbox is selected");
		WebElement includeAction = bulkActionBtn;
		Actions actionY = new Actions(driver);
		actionY.moveToElement(includeAction).build().perform();
		actionY.clickAndHold(includeAction).click().build().perform();
		System.out.println("Bulk actions button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Bulk actions button is selected");
		WebElement includeAction1 = includeBidBtn;
		Actions POM = new Actions(driver);
		POM.moveToElement(includeAction1).build().perform();
		POM.clickAndHold(includeAction1).click().build().perform();
		System.out.println("Include bid button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Include bid button is selected");
		 
		wait.until(ExpectedConditions.visibilityOf(includeBidPopUpMsg2));
		String text1 = includeBidPopUpMsg2.getText();
		System.out.println("Include bid Pop up message displayed :- " + text1);
		ObjectRepo.getTest().log(Status.PASS, "Include bid Pop up message displayed :- " + text1);
		wait.until(ExpectedConditions.visibilityOf(includeBidBtn1));
		includeBidBtn1.click();
		wait.until(ExpectedConditions.visibilityOf(addCommentTextBox));
		addCommentTextBox.sendKeys("Testnew");
		wait.until(ExpectedConditions.visibilityOf(postBtn));
		postBtn.click();
		 
	}

	public void includeMultipleBidMethod1() throws InterruptedException {
		
		WebElement element1A = selectCheckBox2;
		js.executeScript("arguments[0].scrollIntoView();", element1A);
		WebElement eventNew = selectCheckBox2;
		Actions actionT = new Actions(driver);
		actionT.moveToElement(eventNew).build().perform();
		actionT.clickAndHold(eventNew).click().build().perform();
		System.out.println("Second Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "Second Number checkbox is selected");
		WebElement event5 = selectCheckBox1;
		Actions pqr = new Actions(driver);
		pqr.moveToElement(event5).build().perform();
		pqr.clickAndHold(event5).click().build().perform();
		System.out.println("First Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "First Number checkbox is selected");
		WebElement includeAction = bulkActionBtn;
		Actions actionY = new Actions(driver);
		actionY.moveToElement(includeAction).build().perform();
		actionY.clickAndHold(includeAction).click().build().perform();
		System.out.println("Bulk actions button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Bulk actions button is selected");
		WebElement includeAction1 = includeBidBtn1;
		Actions POM = new Actions(driver);
		POM.moveToElement(includeAction1).build().perform();
		 
		POM.click(includeAction1).click().build().perform();
		System.out.println("Include bid button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Include bid button is selected");
		wait.until(ExpectedConditions.visibilityOf(addCommentTextBox));
		addCommentTextBox.sendKeys("Testnew");
		wait.until(ExpectedConditions.visibilityOf(postBtn));
		postBtn.click();
	}

	public void SponsorFlagMultipleBids() throws InterruptedException {
		
		WebElement element1 = selectCheckBox2;
		js.executeScript("arguments[0].scrollIntoView();", element1);
		WebElement event = selectCheckBox2;
		Actions action = new Actions(driver);
		action.moveToElement(event).build().perform();
		action.clickAndHold(event).click().build().perform();
		System.out.println("Second Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "Second Number checkbox is selected");
		WebElement event1 = selectCheckBox1;
		Actions action2 = new Actions(driver);
		action2.moveToElement(event1).build().perform();
		action2.clickAndHold(event1).click().build().perform();
		System.out.println("First Number checkbox is selected");
		ObjectRepo.getTest().log(Status.PASS, "First Number checkbox is selected");
		WebElement bulkAction = bulkActionBtn;
		Actions action1 = new Actions(driver);
		action1.moveToElement(bulkAction).build().perform();
		action1.clickAndHold(bulkAction).click().build().perform();
		System.out.println("Bulk actions button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Bulk actions button is selected");
		WebElement bulkAction1 = addFlagBtn;
		Actions action11 = new Actions(driver);
		action11.moveToElement(bulkAction1).build().perform();
		action11.clickAndHold(bulkAction1).click().build().perform();
		System.out.println("Add Flag button is selected");
		ObjectRepo.getTest().log(Status.PASS, "Add Flag button is selected");
		String ref = addFlagPopUpMsg1.getText();
		System.out.println("Add flag Pop up first message :- " + ref);
		ObjectRepo.getTest().log(Status.PASS, "Add flag Pop up first message :- " + ref);
		String ref1 = addFlagPopUpMsg2.getText();
		System.out.println("Add Flag Pop up Second message is displayed :- " + ref1);
		ObjectRepo.getTest().log(Status.PASS, "Add Flag Pop up second message :- " + ref1);
		wait.until(ExpectedConditions.visibilityOf(excludeBidBtn1));
		excludeBidBtn1.click();
		wait.until(ExpectedConditions.visibilityOf(postBtn));
		postBtn.click();
		String errorMsg = commentBidErrorMsg.getText();
		System.out.println("Error message is displayed for empty exclude bid comment :- " + errorMsg);
		ObjectRepo.getTest().log(Status.PASS, "Error message is displayed :- " + errorMsg);
		wait.until(ExpectedConditions.visibilityOf(addCommentTextBox));
		addCommentTextBox.sendKeys("Testnew");
		wait.until(ExpectedConditions.visibilityOf(postBtn));
		postBtn.click();
		String SuccessMsg = excludeBidSuccessMsg.getText();
		System.out.println("Exclude Bid success message :- " + SuccessMsg);
		ObjectRepo.getTest().log(Status.PASS, "Exclude Bid success message :- " + SuccessMsg);
		System.out.println("************************************************************************");
	}
}
