package com.wci.auction.org.resources;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WaitStatementLib {

	public static void implicitlyWaitForSecond(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public static void implicitlyWaitForMinute(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(time));
	}

	public static void explicitlyWaitForClickable(WebDriver driver, Duration time, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	public static void explicitlyWaitVisibilityOf(WebDriver driver, Duration time, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	public static void explicitlyWaitElementToBeSelected(WebDriver driver, Duration time, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeSelected(element));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	public static void explicitlyWaitInvisibilityOf(WebDriver driver, Duration time, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	public static void explicitlyWaitpresenceOfElementLocated(WebDriver driver, Duration time, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}

	public static void threadSleepOfMilliSecond(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}

	public static void pageLoadTime(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(4));
	}
	
	public static void setScriptTimeout(WebDriver driver, int seconds) {
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(seconds));
	}
}

