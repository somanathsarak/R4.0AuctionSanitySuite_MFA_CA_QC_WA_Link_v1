package com.wciauction.monitoring.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;
import com.wciauction.pageObjects.monitoring.MonitoringPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;

public class MonitoringPageTest extends BaseClass {
	
	@Test(priority = 0, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Access_For_Sponsor_To_The_Monitoring_Pages() throws Exception {
		testCaseId.set("67694");
		PublicHomePage login = new PublicHomePage();
		MonitoringPage monitor = new MonitoringPage();
		ApprovalStatusPage ref = new ApprovalStatusPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		monitor.verifyEventMonitoring();
		ref.Logout();
		getDriver().manage().deleteAllCookies();
	}

	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_The_Access_For_Market_Monitor_To_The_Monitoring_Pages() throws Exception {
		testCaseId.set("67695");
		PublicHomePage login = new PublicHomePage();
		MonitoringPage monitor = new MonitoringPage();
		ApprovalStatusPage ref = new ApprovalStatusPage();
		login.MMLoginCa();
		login.handleSecurityQue();
		login.clickOnnext();
		monitor.verifyEventMonitoring();
		ref.Logout();
		getDriver().manage().deleteAllCookies();
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_That_The_Sponsor_Can_Exclude_And_Include_Multiple_Bids() throws Exception {
		testCaseId.set("67696");
		JointAuctionTieBreakerPage ref1 = new JointAuctionTieBreakerPage();
		ApprovalStatusPage ref = new ApprovalStatusPage();
		PublicHomePage test = new PublicHomePage();
		MonitoringPage select = new MonitoringPage();
		ref1.ARLoginQC3();
		ref1.handleSecurityQue();
		ref1.clickOnnext();
		ref1.SelectEntity1();
		ref1.selectEventForBiddingMonitoringtestcase1();
		ref1.BiddingByARforTestData1();
		ref1.BiddingByARforTestData2();
		ref.Logout();
		test.runtimesponsorLoginCa();
		test.handleSecurityQue();
		test.clickOnnext();
		select.SponsorCanExcludeMultipleBids();
	}
}
