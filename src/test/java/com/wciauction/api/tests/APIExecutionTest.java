package com.wciauction.api.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.SettingsPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;

public class APIExecutionTest extends BaseClass {
	
	@Test(priority = 0, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_that_API_Execution_Functionality() {
		testCaseId.set("67678");
		PublicHomePage ref = new PublicHomePage();
		ref.sponsorLoginCa();
		ref.handleSecurityQue();
		ref.clickOnnext();
		SettingsPage setting = new SettingsPage();
		setting.executeAPIGetQualifiedBidder();
		setting.getstatusAfterExecute1();
		setting.executeAPIGetBidLimitations();
		setting.getstatusAfterExecute2();
	}

}
