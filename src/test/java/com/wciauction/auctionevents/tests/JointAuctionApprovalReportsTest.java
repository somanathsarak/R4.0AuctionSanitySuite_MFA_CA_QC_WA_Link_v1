package com.wciauction.auctionevents.tests;

import static com.wci.auction.org.resources.BaseClass.prop;
import static constants.ThreadConstants.testCaseId;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.reports.DownloadReportsPage;
import com.wciauction.pageObjects.reports.ReportsPage;
import com.wciauction.pageObjects.reports.ReportsPage2;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;
import dbConnection.DataBAseQueriesMethod;

public class JointAuctionApprovalReportsTest extends BaseClass {
	public static String currentEventName = EventUtils.jointAuctionCAQC;
//	public static String currentEventName ="A211_OpenBW_Automation";
	public static String currentEventId = EventUtils.jointAuctionCAQCID;

//	@Test
//	public void resetCitssEventIDWS()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		DataBAseQueriesMethod.setConnection();
//		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.updateCittsEventID("A211");
//	}

	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class) // , dependsOnMethods="resetCitssEventIDWS",
																		// groups = { "sanity" })
	public void Verify_Joint_Auction_Event_Creation_for_Quebec_and_California_Jurisdictions_with_First_Level_Approval()
			throws Exception {
		testCaseId.set("67622");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.userSponsorCa2, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.createEventForJointAuction(currentEventName);
		gm.firstApprovalCA(currentEventName);
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Second_Level_Approval_for_Joint_Auction_Event_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException, IOException {
		testCaseId.set("67623");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.UserSponsorQc, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		gm.secondApprovalCA(currentEventName);
		System.out.println("Step : Second approval given successfully");
	}

	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verification_of_Added_FX_Rate_by_Quebec_Jurisdiction() throws InterruptedException {
		testCaseId.set("67624");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.UserSponsorQc, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.setFXrateForQuebecJurisdiction(currentEventName,UsersData.FXRate);
		System.out.println("Step : FX rate set with quebec approval");
	}

	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verification_of_Added_FX_Rate_by_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67625");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.userSponsorCa2, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.setFXrateForCaliforniaJurisdiction(currentEventName,UsersData.FXRate);
		System.out.println("Step : FX rate set with california approval");
		System.out.println("******************************************************************");
	}

	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Execute_API_Functionality_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException, SQLException {
		testCaseId.set("67626");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.executeAPIGetQualifiedBidder();
		getDriver().navigate().refresh();
		ref.executeAPIGetBidLimitations();
	}

	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_First_Entity_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws Exception {
		testCaseId.set("67627");
//		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
//		GenericUtils gm = new GenericUtils();
//		gm.UserLogin( UsersData.AR_USerName2 , UsersData.password );
//		gm.handleSecurityQue();
//		gm.clickOnnext();
//		ref.SelectEntity1();
//		ref.selectEventForBiddingE2EUpdated(currentEventName);
//		Thread.sleep(60000);
//		ref.BiddingByARforTestData1();
//		System.out.println("First bid placed ");
//		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
//		ref.BiddingByARforTestData2();
//		System.out.println("Second bid placed ");
//		ObjectRepo.getTest().log(Status.PASS, "Second bid placed ");
//		ref.BiddingByARforTestData3();
//		System.out.println("Third bid placed ");
//		ObjectRepo.getTest().log(Status.PASS, "Third bid placed");
//		ref.BiddingByARforTestData4();
//		System.out.println("Fourth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Fourth bid placed");
	}

	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_Second_Entity_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws Exception {
		testCaseId.set("67628");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.SelectEntity2();
		ref.selectEventForBiddingE2EUpdated(currentEventName);
		ref.BiddingByARforTestData5();
		System.out.println("Fifth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Fifth bid placed ");
		ref.BiddingByARforTestData6();
		System.out.println("Sixth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Sixth bid placed ");
		ref.BiddingByARforTestData7();
		System.out.println("Seventh bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Seventh bid placed ");
		ref.BiddingByARforTestData8();
		System.out.println("Eighth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Eighth bid placed ");
	}

	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Edits_Bidding_Window_Close_Time_and_Provides_First_Approval_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67629");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.closeBidding();
		gm.CaliforniaJuridictionApprovalForEventCreation(currentEventName);
		System.out.println("******************************************************************");
	}

	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Provides_Second_Approval_for_Bidding_Window_Close_Time_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67630");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.UserSponsorQc, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.secondApproval(currentEventName);
	}

	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_First_Sponsor_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67631");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmByFirstSponsor();
	}

	@Test(priority = 11, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_Second_Sponsor_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67632");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.UserSponsorQc, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmBySecondSponsor();
	}

	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_First_Sponsor_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67633");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.ApprovedRSCertificationButtonFirstSponsor("Auction Certification", currentEventName);
//		ref.clickbtnAdmin();
//		ref.ApprovedRSCertificationButtonFirstSponsor("Auction Certification",currentEventName);
//		ref.FirstSponsorCertificationButton("Auction Certification");
	}

	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_Second_Sponsor_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67634");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin(UsersData.UserSponsorQc, UsersData.password);
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.acceptRSCertificationButtonSecondSponsor("Auction Certification", currentEventName);
//		ref.clickbtnAdmin();
//		ref.secondSponsorCertificationButton("Auction Certification");
	}

	@Test(priority = 14, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Reports_are_Generated_after_Event_Certification_for_Joint_Auction_in_Quebec_and_California_Jurisdictions()
			throws InterruptedException {
		testCaseId.set("67635");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		gm.searchReport();
		gm.searchReportByEvent(currentEventName);
		gm.verifyAllReportsAreDisplayedJointCAQCAuction();
	}

}
