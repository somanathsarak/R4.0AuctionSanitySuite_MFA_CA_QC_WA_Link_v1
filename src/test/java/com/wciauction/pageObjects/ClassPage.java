package com.wciauction.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wci.auction.org.resources.BaseClass;


public class ClassPage {

	String date1;
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(xpath = "(//input[@name='eventBiddingPeriodOpen'])")
	private WebElement dateBiddingPeroidOpen;

	@FindBy(xpath = "//*[@name='eventBiddingPeriodClose']")
	private WebElement dateBiddingPeriodClose;

	@FindBy(xpath = "//*[@name='eventApplicationOpen']")
	private WebElement dateEventAppOpen;

	@FindBy(xpath = "//input[@name='eventApplicationClose']")
	private WebElement dateEventAppClose;

	@FindBy(xpath = "//button[@title='Select number of rows per page.']")
	private WebElement selectNumberOfRows;

	@FindBy(xpath = "//li[text()='500 ']")
	private WebElement select500Rows;

	@FindBy(xpath = "//input[@name='financialSettlement']")
	private WebElement financialSettlementDateTime;

	public ClassPage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }
	
	public void listOfSubmittedBids() {
		wait.until(ExpectedConditions.elementToBeClickable(selectNumberOfRows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(select500Rows)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@role='checkbox']")));
		List<WebElement> element = driver.findElements(By.xpath("//tr[@role='checkbox']"));
		int totalNumOfBids = element.size();
		System.out.println("Total Numbers of bids available in grid are :" + totalNumOfBids);
	}

	public <DateFormat> void selectDatesSBMA() throws InterruptedException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = new Date();
		date1 = dateFormat.format(date);
		dateBiddingPeroidOpen.click();
		dateBiddingPeroidOpen.sendKeys(date1 + "16:20");
		dateBiddingPeriodClose.click();
		dateBiddingPeriodClose.sendKeys(date1 + "18:20");
		dateEventAppOpen.click();
		dateEventAppOpen.sendKeys(date1 + "00:20");
		dateEventAppClose.click();
		dateEventAppClose.sendKeys(date1 + "015:20");
		financialSettlementDateTime.click();
		financialSettlementDateTime.sendKeys(date1 + "15:20");
	}

}
