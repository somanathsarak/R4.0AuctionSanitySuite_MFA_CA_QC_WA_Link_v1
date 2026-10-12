package com.wci.auction.org.resources;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.testrailmanager.TestRailManager;
import static constants.ThreadConstants.testCaseId;

public class ExtentReporterNG  implements ITestListener, ISuiteListener {
    
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static final Logger logger = LogManager.getLogger(ExtentReporterNG.class);
    private static final String REPORT_PATH = System.getProperty("user.dir") + "/ExecutionReports";
    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/ScreenShots";
    
    @Override
    public void onStart(ISuite suite) {
        // Initialize ExtentReports only once at suite level
        if (extent == null) {
            initializeExtentReports();
        }
    }
    
    @Override
    public void onFinish(ISuite suite) {
        // Flush the reports at suite level
        if (extent != null) {
            extent.flush();
        }
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        // Only process actual test methods, not configuration methods
        if (isTestMethod(result)) {
            String testName = result.getMethod().getMethodName();
            String className = result.getTestClass().getName();
            
            ExtentTest test = extent.createTest(testName, "Test: " + testName);
            test.assignCategory(className);
            extentTest.set(test);
            
            System.out.println("Starting test: " + testName);
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        if (isTestMethod(result) && extentTest.get() != null) {
            extentTest.get().log(Status.PASS, "Test passed successfully");
            System.out.println("Test passed: " + result.getMethod().getMethodName());
        }
        TestRailManager.addResultsForTestCase(
        	    testCaseId.get(),
        	    TestRailManager.TEST_CASE_PASS_STATUS,
        	    "✅ Test case executed successfully through automation." +
        	    " | Method Name: " + result.getName() +
        	    " | Status: PASSED" +
        	    " | Executed By: " + System.getProperty("user.name") +
        	    " | Operating System: " + System.getProperty("os.name")
        	);

    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        if (isTestMethod(result) && extentTest.get() != null) {
            extentTest.get().log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
            
            // Add screenshot on failure
            try {
                String testName = result.getMethod().getMethodName();
                BaseClass.takeScreenshot(testName + "_failed");
                String screenshotPath = System.getProperty("user.dir") + "/ScreenShots/" + testName + "_failed.png";
                if (new File(screenshotPath).exists()) {
                    extentTest.get().addScreenCaptureFromPath(screenshotPath);
                }
            } catch (Exception e) {
                System.out.println("Failed to attach screenshot: " + e.getMessage());
            }
            
            System.out.println("Test failed: " + result.getMethod().getMethodName());
            TestRailManager.addResultsForTestCase(
            	    testCaseId.get(),
            	    TestRailManager.TEST_CASE_FAIL_STATUS,
            	    "❌ Test case execution failed during automation." +
            	    " | Method Name: " + result.getName() +
            	    " | Status: FAILED" +
            	    " | Executed By: " + System.getProperty("user.name") +
            	    " | Operating System: " + System.getProperty("os.name")
            	);

        }
       
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        if (isTestMethod(result) && extentTest.get() != null) {
            extentTest.get().log(Status.SKIP, "Test Retry: " + result.getThrowable().getMessage());
            System.out.println("Test Retry: " + result.getMethod().getMethodName());
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        if (isTestMethod(result) && extentTest.get() != null) {
            extentTest.get().log(Status.WARNING, "Test failed but within success percentage");
        }
    }
    
    @Override
    public void onFinish(ITestContext context) {
        // Clean up thread local
        extentTest.remove();
    }
    
    private void initializeExtentReports() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "ExtentReport_" + timeStamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/ExecutionReports/" + reportName;
        
        // Create directories using the new method
        createDirectories();
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        System.out.println("ExtentReports initialized. Report will be saved at: " + reportPath);
    }
    
    private boolean isTestMethod(ITestResult result) {
        // Check if the method is a test method (not a configuration method)
        return result.getMethod().isTest();
    }
    
    // Add method to get current ExtentTest instance
    public static ExtentTest getCurrentTest() {
        return extentTest.get();
    }
    
    public static void createDirectories() {
        try {
            File reportDir = new File(REPORT_PATH);
            File screenshotDir = new File(SCREENSHOT_PATH);
            if (reportDir.exists()) {
                FileUtils.cleanDirectory(reportDir);
            }
            if (screenshotDir.exists()) {
                FileUtils.cleanDirectory(screenshotDir);
            }
            FileUtils.forceMkdir(reportDir);
            FileUtils.forceMkdir(screenshotDir);

            logger.info("Cleared and recreated report and screenshot directories.");
        } catch (IOException e) {
            logger.error("Failed to create/clean directories", e);
        }
    }
}
