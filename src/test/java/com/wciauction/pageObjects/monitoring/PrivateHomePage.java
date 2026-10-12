package com.wciauction.pageObjects.monitoring;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;

import static constants.ThreadConstants.driver;

public class PrivateHomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//*[text()='Français']")
	private WebElement langBtnFrench;

	@FindBy(xpath = "//span[text()='WCI, Inc. Auction Platform']")
	private WebElement pageTitleEngQc;

	@FindBy(xpath = "//li[text()='English']")
	private WebElement langBtnEng;

	@FindBy(xpath = "//span[text()='Administration']")
	private WebElement btnAdminstration;

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement btnHome;

	public PrivateHomePage() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
    }

	public void verifyPageTitleLanguage() {
		String FrenchTitle = driver.getTitle();
		if (FrenchTitle.equals("Plateforme de vente aux ench�res de WCI, inc.")) {
			System.out.println("Title is :" + FrenchTitle);
			System.out.println("Prefered langauge is French for quebec user");
			ObjectRepo.getTest().log(Status.PASS, "Prefered langauge is French for quebec user");
		}
	}

	public void changeLangauge() {
		wait.until(ExpectedConditions.elementToBeClickable(langBtnFrench));
		
		WebElement element = langBtnFrench;
		js.executeScript("arguments[0].scrollIntoView();", element);
		langBtnFrench.click();
		langBtnEng.click();
		System.out.println("Langauge changed successfully from french to english ");
		String EnglishTitle = pageTitleEngQc.getText();
		if (EnglishTitle.equals("WCI, Inc. Auction Platform")) {
			System.out.println("Title is :" + EnglishTitle);
			ObjectRepo.getTest().log(Status.PASS, " langauge changed from french to english successfully ");
		} else {
			System.out.println("Langauge is not set to english ");
		}
	}

	public void adminstrationNavigation() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdminstration));
		btnAdminstration.click();
	}

	public void HomeNavigation()  {
		wait.until(ExpectedConditions.elementToBeClickable(btnHome));
		btnHome.click();
	}

}
