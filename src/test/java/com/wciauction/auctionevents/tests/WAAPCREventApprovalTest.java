package com.wciauction.auctionevents.tests;

import static constants.ThreadConstants.testCaseId;
//import static utilities.CommonMethods.folderPath;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;
import com.wciauction.pageObjects.auction.SingleAuctionCaliforniaJurisdictionPage;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.events.EventCreationUtils;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.user.LoginPage;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.EntityData;
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;
import dbConnection.DataBAseQueriesMethod;

public class WAAPCREventApprovalTest extends BaseClass {
	
	public static String EventName = EventUtils.apcrWashington;
	public static String currentEventId = EventUtils.apcrWashingtonID;
	
//	@Test
//	public void Reset_Citss_EventID()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		DataBAseQueriesMethod.setConnection();
//		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.updateCittsEventID("R319");
//	}

	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class) 
	public void Verify_APCR_Event_Creation_for_Washington_Jurisdiction_with_First_Level_Approval() throws Exception {
		testCaseId.set("67665");
		EventCreationUtils ref = new EventCreationUtils();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		System.out.println("Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		ref.createEventForWaReserveSaleEvent_REWA1( currentEventId,EventName);
		ObjectRepo.getTest().log(Status.PASS, "Event Created Successfully");
		gm.firstApprovalCA(EventName);
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Second_Level_Approval_for_APCR_Event_in_Washington_Jurisdiction() throws Exception {
		testCaseId.set("67666");
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		gm.secondApprovalCA(EventName);
		System.out.println("******************************************************************");
	}

	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Execute_API_Functionality_for_APCR_Event_in_Washington_Jurisdiction() throws InterruptedException, SQLException {
		testCaseId.set("67667");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		ref.executeAPIGetQualifiedBidder();
		getDriver().navigate().refresh();
		ref.executeAPIGetBidLimitations();
		ref.Logout();
	}
	
	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_First_Entity_in_APCR_Event_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("67668");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_EQ_AUP_WA_Userupdated , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.WA_6585_9938);
		gm.selectEventForBidding(EventName);
		gm.BiddingByARForAllownceSaleEvent("1000","Tier One");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
	}
	
	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Second_Entity_in_APCR_Event_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("67669");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_DJackson , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.WA_6588_9944);
		gm.selectEventForBidding(EventName);
		gm.BiddingByARForAllownceSaleEvent("1000","Tier Two");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed ");
	}
	
	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Third_Entity_in_APCR_Event_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("67670");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_causer , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.WA_6587_9942);
		gm.selectEventForBidding(EventName);
		gm.BiddingByARForAllownceSaleEvent("1000","Tier One");
		System.out.println("First bid placed ");
		ObjectRepo.getTest().log(Status.PASS, "First bid placed");
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Edits_Bidding_Window_Close_Time_and_Provides_First_Approval_for_APCR_Event_in_Washington_Jurisdiction() throws InterruptedException {
		testCaseId.set("67671");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(EventName);
		ref.closeBiddingUpdated();
		gm.CaliforniaJuridictionApprovalForEventCreation(EventName);
		System.out.println("******************************************************************");
	}
	
	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Provides_Second_Approval_for_Bidding_Window_Close_Time_of_APCR_Event_in_Washington_Jurisdiction () throws InterruptedException {
		testCaseId.set("67672");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.secondApproval(EventName);
	}
	
	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_First_Sponsor_of_Washington_Jurisdiction_for_APCR_Event() throws InterruptedException {
		testCaseId.set("67673");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(EventName);
		ref.confirmRunAlgorithmByFirstSponsor();
	}
	
	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_Second_Sponsor_of_Washington_Jurisdiction_for_APCR_Event() throws InterruptedException {
		testCaseId.set("67674");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(EventName);
		ref.confirmRunAlgorithmBySecondSponsor();
	}
	
	@Test(priority = 11, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_First_Sponsor_of_Washington_Jurisdiction_for_APCR_Event() throws InterruptedException {
		testCaseId.set("67675");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.ApprovedSbmandApcrCertificationButtonFirstSponsor("Allowance Price Containment Reserve Certification",EventName);
//		ref.FirstSponsorCertificationButton("Allowance Price Containment Reserve Certification");
	}
	
	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_Second_Sponsor_of_Washington_Jurisdiction_for_APCR_Event() throws InterruptedException {
		testCaseId.set("67676");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.acceptSbmaAndApcrCertificationButtonSecondSponsor("Allowance Price Containment Reserve Certification",EventName);
//		ref.secondSponsorCertificationButton("Allowance Price Containment Reserve Certification");
	}
	
	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Reports_are_Generated_after_Event_Certification_of_APCR_Event_for_Washington_Jurisdiction() throws InterruptedException {
		testCaseId.set("67677");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorWa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		gm.searchReport();
		gm.searchReportByEvent(EventName);
		gm.verifyAllReportsAreDisplayedWAAPCREvent();
	}
	
	
}
