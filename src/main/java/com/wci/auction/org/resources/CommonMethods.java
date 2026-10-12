package com.wci.auction.org.resources;

import java.io.File;
import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonMethods {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;
	private static final int TIMEOUT_SECONDS = 60;
	private static final int RETRY_DELAY_MS = 500; // Delay between retries
	private static File parentDirectory;
	public static String folderPath;
	
	public CommonMethods() {
		this.driver = BaseClass.getDriver();
		this.wait = BaseClass.getWait();
		this.js=BaseClass.getJs();
		PageFactory.initElements(driver, this);
	}

	public static void initializeFolder() {
		String parentDirectoryPath = System.getProperty("user.dir") + File.separator + "directory";
		parentDirectory = new File(parentDirectoryPath);
		if (!parentDirectory.exists()) {
			return;
		}
		String newFolderName = UUID.randomUUID().toString();
		File folder = new File(parentDirectory, newFolderName);
		if (folder.mkdir()) {
			folderPath = folder.getAbsolutePath();
			System.out.println("folderPath : "+ folderPath);
		}
	}

	public static boolean deleteFolder(File folder) {
		if (!folder.exists()) {
			return true;
		}
		File[] contents = folder.listFiles();
		if (contents != null) {
			for (File file : contents) {
				if (file.isDirectory()) {
					deleteFolder(file);
				} else {
					file.delete();
				}
			}
		}
		return folder.delete();
	}

	public static void deleteFolder() {
		if (!parentDirectory.exists()) {
			return;
		}
		File[] folders = parentDirectory.listFiles(File::isDirectory);
		if (folders != null) {
			for (File folder : folders) {
				deleteFolder(folder);
			}
		}
	}

	public  void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		int retries = 0;
		boolean clicked = false;
		while (retries < 3 && !clicked) {
			try {
				// Add small delay for Mac stability
				Thread.sleep(RETRY_DELAY_MS);
				
				switch (retries) {
			     case 0:
			         element.click();
			         clicked = true;
			         break;

			     case 1:
			         ((JavascriptExecutor) driver)
			             .executeScript("arguments[0].click();", element);
			         clicked = true;
			         break;

			     case 2:
			         new Actions(driver)
			             .moveToElement(element)
			             .click()
			             .perform();
			         clicked = true;
			         break;
			 }
			} catch (ElementClickInterceptedException | InterruptedException e) {
				retries++;
				if (retries == 3) {
					throw new RuntimeException("Element not clickable after 3 attempts: " + e.getMessage());
				}
				System.out.println("Retrying click due to overlay or blocking element... (Attempt " + (retries + 1) + ")");
				try {
					WebElement spinner = driver.findElement(By.id("appSpinner"));
					wait.until(ExpectedConditions.invisibilityOf(spinner));
				} catch (NoSuchElementException spinnerException) {
					// Spinner not found, just wait a bit
					try {
						Thread.sleep(RETRY_DELAY_MS);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				}
			}
		}
	}
		
	public  void waitForElementToBeVisible(WebElement element) {
	       int retries = 0;
	       boolean isVisible = false;
	       
	       while (retries < 3 && !isVisible) {
	           try {
	               // Add small delay for Mac stability
	               Thread.sleep(RETRY_DELAY_MS);
	               
	               WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
	               localWait.until(ExpectedConditions.visibilityOf(element));
	               isVisible = true; // If no exception is thrown, element is visible
	           } catch (TimeoutException e) {
	               retries++;
	               if (retries == 3) {
	                   throw new RuntimeException("Element not visible after 3 attempts: " + e.getMessage());
	               }
	               System.out.println("Retrying visibility check due to timeout... (Attempt " + (retries + 1) + ")");
	               
	               // Wait for any loading indicators to disappear
	               try {
	                   WebElement spinner = driver.findElement(By.id("appSpinner"));
	                   WebDriverWait spinnerWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
	                   spinnerWait.until(ExpectedConditions.invisibilityOf(spinner));
	               } catch (NoSuchElementException spinnerException) {
	                   // If spinner is not found, continue with next retry
	                   System.out.println("No loading indicator found, continuing with retry...");
	                   try {
	                       Thread.sleep(RETRY_DELAY_MS);
	                   } catch (InterruptedException ie) {
	                       Thread.currentThread().interrupt();
	                   }
	               }
	           } catch (StaleElementReferenceException e) {
	               retries++;
	               if (retries == 3) {
	                   throw new RuntimeException("Element became stale after 3 attempts: " + e.getMessage());
	               }
	               System.out.println("Retrying visibility check due to stale element... (Attempt " + (retries + 1) + ")");
	           } catch (InterruptedException e) {
	               Thread.currentThread().interrupt();
	               throw new RuntimeException("Thread interrupted during wait: " + e.getMessage());
	           }
	       }
	   }
	
	public  void waitForElementToBeVisible(By element) {
	       int retries = 0;
	       boolean isVisible = false;
	       
	       while (retries < 3 && !isVisible) {
	           try {
	               // Add small delay for Mac stability
	               Thread.sleep(RETRY_DELAY_MS);
	               
	               WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
	               localWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	               isVisible = true; // If no exception is thrown, element is visible
	           } catch (TimeoutException e) {
	               retries++;
	               if (retries == 3) {
	                   throw new RuntimeException("Element not visible after 3 attempts: " + e.getMessage());
	               }
	               System.out.println("Retrying visibility check due to timeout... (Attempt " + (retries + 1) + ")");
	               
	               // Wait for any loading indicators to disappear
	               try {
	                   WebElement spinner = driver.findElement(By.id("appSpinner"));
	                   WebDriverWait spinnerWait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
	                   spinnerWait.until(ExpectedConditions.invisibilityOf(spinner));
	               } catch (NoSuchElementException spinnerException) {
	                   // If spinner is not found, continue with next retry
	                   System.out.println("No loading indicator found, continuing with retry...");
	                   try {
	                       Thread.sleep(RETRY_DELAY_MS);
	                   } catch (InterruptedException ie) {
	                       Thread.currentThread().interrupt();
	                   }
	               }
	           } catch (StaleElementReferenceException e) {
	               retries++;
	               if (retries == 3) {
	                   throw new RuntimeException("Element became stale after 3 attempts: " + e.getMessage());
	               }
	               System.out.println("Retrying visibility check due to stale element... (Attempt " + (retries + 1) + ")");
	           } catch (InterruptedException e) {
	               Thread.currentThread().interrupt();
	               throw new RuntimeException("Thread interrupted during wait: " + e.getMessage());
	           }
	       }
	   }
}
