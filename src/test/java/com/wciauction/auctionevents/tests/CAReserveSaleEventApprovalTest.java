package com.wciauction.auctionevents.tests;

import static constants.ThreadConstants.testCaseId;

import java.sql.SQLException;
import org.testng.annotations.Test;
import com.wci.auction.org.resources.BaseClass;
import com.wciauction.pageObjects.auction.JointAuctionTieBreakerPage;
import com.wciauction.pageObjects.auction.SingleAuctionCaliforniaJurisdictionPage;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.events.EventCreationUtils;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.EntityData;
import com.wciauction.requiredData.EventUtil;
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;
import dbConnection.DataBAseQueriesMethod;

public class CAReserveSaleEventApprovalTest extends BaseClass {
	
	public static String currentEventName = EventUtils.reserveSaleCalifornia;
	public static String currentEventId = EventUtils.reserveSaleCaliforniaID;
	
	
//	@Test
//	public void resetCitssEventIDWS()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		DataBAseQueriesMethod.setConnection();
//		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.updateCittsEventID("R317");
//	}
//	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Reserve_Sale_Event_Creation_for_California_Jurisdiction_with_First_Level_Approval() throws Exception {
		testCaseId.set("67636");
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		GenericUtils gm = new GenericUtils();
		EventCreationUtils cm = new EventCreationUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		cm.createEventForCAReserveSaleEvent( currentEventId,  currentEventName);
		gm.firstApprovalCA(currentEventName);
	}
	
	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Second_Level_Approval_for_reserve_Sale_Event_in_California_Jurisdiction() throws Exception {
		testCaseId.set("67637");
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
	public void Verify_Execute_API_Functionality_for_Reserve_Sale_Event_in_California_Jurisdiction() throws InterruptedException, SQLException {
		testCaseId.set("67638");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		ref.executeAPIGetQualifiedBidder();
		getDriver().navigate().refresh();
		ref.executeAPIGetBidLimitations();
		ref.Logout();
	}
	
	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_First_Entity_in_Reserve_Sale_Event_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67639");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerNameRES , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_Entity1);
		gm.selectEventForBidding(currentEventName);
		Thread.sleep(40000);
		gm.BiddingByARReserveSale("1000","Tier Two");
	}
	
	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Second_Entity_in_Reserve_Sale_Event_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67640");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerNameRES , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_Entity2);
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARReserveSale("1000","Tier One");
		gm.BiddingByARReserveSale("1000","Tier Two");
	}
	
	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Raised_Bid_for_Third_Entity_in_Reserve_Sale_Event_for_California_Jurisdiction() throws Exception {
		testCaseId.set("67641");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerNameRES , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(EntityData.CA_Entity3);
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARReserveSale("1000","Tier One");
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Edits_Bidding_Window_Close_Time_and_Provides_First_Approval_for_Reserve_Sale_in_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67642");
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
	public void Verify_that_Sponsor_User_Provides_Second_Approval_for_Bidding_Window_Close_Time_of_Reserve_Sale_in_California_Jurisdiction () throws InterruptedException {
		testCaseId.set("67643");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.secondApproval(currentEventName);
	}

	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_First_Sponsor_of_California_Jurisdiction_for_Reserve_Sale_Event() throws InterruptedException {
		testCaseId.set("67644");
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
	public void Verify_Run_Algorithm_Functionality_by_Second_Sponsor_of_California_Jurisdiction_for_Reserve_Sale_Event() throws InterruptedException {
		testCaseId.set("67645");
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
	public void Verify_Event_Certification_Functionality_by_First_Sponsor_of_California_Jurisdiction_for_Reserve_Sale_Event() throws InterruptedException {
		testCaseId.set("67646");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.ApprovedSbmandApcrCertificationButtonFirstSponsor("Reserve Sale Certification",currentEventName);
	}

	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_Second_Sponsor_of_California_Jurisdiction_for_Reserve_Sale_Event() throws InterruptedException {
		testCaseId.set("67647");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.acceptSbmaAndApcrCertificationButtonSecondSponsor("Reserve Sale Certification",currentEventName);
//		ref.clickbtnAdmin();
//		ref.secondSponsorCertificationButton("Reserve Sale Certification");
	}
	
	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Reports_are_Generated_after_Event_Certification_of_Reserve_Sale_Event_for_California_Jurisdiction() throws InterruptedException {
		testCaseId.set("67648");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.userSponsorCa2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		gm.searchReport();
		gm.searchReportByEvent(currentEventName);
		gm.verifyAllReportsAreDisplayedCAReserveSale();
	}
	

	

}
