package com.wciauction.auctionevents.tests;

import static constants.ThreadConstants.testCaseId;

import java.sql.SQLException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;
import com.wciauction.pageObjects.auction.SingleAuctionCaliforniaJurisdictionPage;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.EntityData;
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;
import dbConnection.DataBAseQueriesMethod;

public class SingleAuctionApprovalCATest extends BaseClass {
	
	public static String currentEventName = EventUtils.singleAuctionCalifornia;
	public static String currentEventId = EventUtils.singleAuctionCaliforniaID;
	
//	@Test
//	public void resetCitssEventIDWS()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		DataBAseQueriesMethod.setConnection();
//		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.updateCittsEventID("A85");
//	}
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Single_Auction_Event_Creation_for_California_Jurisdiction_and_First_Level_Approval() throws Exception {
		testCaseId.set("67583");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		ref.createEventForCal( currentEventId,  currentEventName);
		gm.firstApprovalCA(currentEventName);
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Second_Level_Approval_for_Single_Auction_Event_in_California_Jurisdiction() throws Exception {
		testCaseId.set("67584");
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		gm.secondApprovalCA(currentEventName);
		System.out.println("******************************************************************");
	}
	
	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_the_Execute_API_Functionality_for_California_Jurisdiction() throws InterruptedException, SQLException {
		testCaseId.set("67585");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		ref.executeAPIGetQualifiedBidder();
		getDriver().navigate().refresh();
		ref.executeAPIGetBidLimitations();
		ref.Logout();
	}
	
	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_First_Entity_in_Single_Auction_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67586");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_VJones , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_3456_4862);
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByAR("16","1");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
		gm.BiddingByAR("20","2");
		System.out.println("Second bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Second bid placed ");
		gm.BiddingByAR("30","3");
		System.out.println("Third bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Third bid placed");
		gm.BiddingByAR("40","4");
		System.out.println("Fourth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Fourth bid placed");
	}
	
	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Second_Entity_in_Single_Auction_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67587");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_TMiller , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_3463_4870);
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByAR("16","1");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
		gm.BiddingByAR("20","2");
		System.out.println("Second bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Second bid placed ");
		gm.BiddingByAR("30","3");
		System.out.println("Third bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Third bid placed");
		gm.BiddingByAR("40","4");
		System.out.println("Fourth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Fourth bid placed");
	}
	
	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Third_Entity_in_Single_Auction_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67588");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_SJones , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_3462_4873);
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByAR("16","1");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
		gm.BiddingByAR("20","2");
		System.out.println("Second bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Second bid placed ");
		gm.BiddingByAR("30","3");
		System.out.println("Third bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Third bid placed");
		gm.BiddingByAR("40","4");
		System.out.println("Fourth bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "Fourth bid placed");
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Edits_Bidding_Window_Close_Time_and_Provides_First_Approval_for_Single_Auction_in_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67589");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.closeBiddingUpdated();
		gm.CaliforniaJuridictionApprovalForEventCreation(currentEventName);
		System.out.println("******************************************************************");
	}

	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Provides_Second_Approval_for_Bidding_Window_Close_Time_of_Single_Auction_in_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67590");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.secondApproval(currentEventName);
	}

	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_First_Sponsor_of_California_Jurisdiction_for_Single_Auction_Event() throws InterruptedException {
		testCaseId.set("67591");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmByFirstSponsor();
	}

	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_Second_Sponsor_of_California_Jurisdiction_for_Single_Auction_Event() throws InterruptedException {
		testCaseId.set("67592");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmBySecondSponsor();
	}

	@Test(priority = 11, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_first_Sponsor_of_California_Jurisdiction_for_Single_Auction_Event() throws InterruptedException {
		testCaseId.set("67593");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.ApprovedRSCertificationButtonFirstSponsor("Auction Certification",currentEventName);
	}

	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_Second_Sponsor_of_California_Jurisdiction_for_Single_Auction_Event() throws InterruptedException {
		testCaseId.set("67594");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.acceptRSCertificationButtonSecondSponsor("Auction Certification",currentEventName);
	}
	
	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Reports_are_Generated_after_Event_Certification_of_Single_Auction_for_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67595");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		gm.searchReport();
		gm.searchReportByEvent(currentEventName);
		gm.verifyAllReportsAreDisplayedCASingleAuction();
	}
}
