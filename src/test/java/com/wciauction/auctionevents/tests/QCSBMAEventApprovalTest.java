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
import com.wciauction.requiredData.UsersData;

import constants.EventUtils;
import dbConnection.DataBAseQueriesMethod;

public class QCSBMAEventApprovalTest extends BaseClass {
	
	public static String currentEventName = EventUtils.sbmaQuebec;
	public static String currentEventID = EventUtils.sbmaQuebecID;

//	@Test
//	public void resetCitssEventIDWS()
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		DataBAseQueriesMethod.setConnection();
//		DataBAseQueriesMethod ref = new DataBAseQueriesMethod();
//		ref.updateCittsEventID("R70");
//	}
	
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_SBMA_Event_Creation_for_Quebec_Jurisdiction_with_First_Level_Approval() throws Exception {
		testCaseId.set("67649");
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		GenericUtils gm = new GenericUtils();
		EventCreationUtils cm = new EventCreationUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		cm.createEventForSBMAEvent( currentEventID,  currentEventName);
		gm.firstApprovalCA(currentEventName);
	}
	
	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Second_Level_Approval_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67650");
		SingleAuctionCaliforniaJurisdictionPage ref = new SingleAuctionCaliforniaJurisdictionPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		CreateEventPage ref1 = new CreateEventPage();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		gm.secondApprovalCA(currentEventName);
		System.out.println("******************************************************************");
	}
	
	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Execute_API_Functionality_for_SBMA_Event_in_Quebec_Jurisdiction() throws InterruptedException, SQLException {
		testCaseId.set("67651");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		ref.executeAPIGetQualifiedBidder();
		getDriver().navigate().refresh();
		ref.executeAPIGetBidLimitations();
	}
	
	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class)
	public void Verify_upload_Emmission_Units_File_Functionality_for_SBMA_Event_in_Quebec_Jurisdiction() throws InterruptedException  {	
		testCaseId.set("67652");
		EventCreationUtils ref1 = new EventCreationUtils();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		ref.handleSecurityQue();
		ref.clickOnnext();
		ref1.clickbtnAdmin();
		ref1.clickbtnEvent();
		ref1.getCurrentEvent(currentEventName);
		String path = System.getProperty("user.dir")+ "\\FilesAutoIT\\R70_Limit upload template SBMA Prescribed 1 - MA.xlsx";
		ref1.uploadEmissionUnitsFile(path);
		System.out.println("Emission Units File Uploaded Successfully");
	}
	
	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_First_Entity_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67653");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName4 , UsersData.password );
		gm.handleSecurityQue();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickOnnextQC();
		gm.SelectEntity(EntityData.QCFirstEntity_RE1);
		ref.changeLangauge();
		gm.selectEventForBidding(currentEventName);
		Thread.sleep(40000);
		gm.BiddingByARSBMA("500000","CATEGORY A");
	}
	
	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_Second_Entity_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67654");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName4 , UsersData.password );
		gm.handleSecurityQue();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickOnnextQC();
		gm.SelectEntity(EntityData.QCSecondEntity_RE2);
		ref.changeLangauge();
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARSBMA("1000000","CATEGORY A");
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_Third_Entity_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67655");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName4 , UsersData.password );
		gm.handleSecurityQue();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickOnnextQC();
		gm.SelectEntity(EntityData.QCThirdEntity_RE3);
		ref.changeLangauge();
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARSBMA("500000","CATEGORY B");
	}
	
	
	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_Fourth_Entity_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67656");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName4 , UsersData.password );
		gm.handleSecurityQue();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickOnnextQC();
		gm.SelectEntity(EntityData.QCForthEntity_RE4);
		ref.changeLangauge();
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARSBMA("500000","CATEGORY C");
	}
	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_AR_User_Raised_Bid_for_Fifth_Entity_for_SBMA_Event_in_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("67657");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.AR_USerName4 , UsersData.password );
		gm.handleSecurityQue();
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		ref.clickOnnextQC();
		gm.SelectEntity(EntityData.QCFifthEntity_RE5);
		ref.changeLangauge();
		gm.selectEventForBidding(currentEventName);
		gm.BiddingByARSBMA("500000","CATEGORY C");
	}
	
	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Edits_Bidding_Window_Close_Time_and_Provides_First_Approval_for_SBMA_Event_in_Quebec_Jurisdiction() throws InterruptedException {
		testCaseId.set("67658");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.closeBiddingUpdated();
		gm.CaliforniaJuridictionApprovalForEventCreation(currentEventName);
		System.out.println("******************************************************************");
	}

	@Test(priority = 11, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Sponsor_User_Provides_Second_Approval_for_Bidding_Window_Close_Time_of_SBMA_Event_in_Quebec_Jurisdiction () throws InterruptedException {
		testCaseId.set("67659");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.secondApproval(currentEventName);
	}

	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_First_Sponsor_of_Quebec_Jurisdiction_for_SBMA_Event() throws InterruptedException {
		testCaseId.set("67660");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmByFirstSponsor();
	}

	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Run_Algorithm_Functionality_by_Second_Sponsor_of_Quebec_Jurisdiction_for_SBMA_Event() throws InterruptedException {
		testCaseId.set("67661");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.clickbtnAdmin();
		ref.clickbtnEvent();
		gm.getCurrentEvent(currentEventName);
		ref.confirmRunAlgorithmBySecondSponsor();
	}

	@Test(priority = 14, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_First_Sponsor_of_Quebec_Jurisdiction_for_SBMA_Event() throws InterruptedException {
		testCaseId.set("67662");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.ApprovedSbmandApcrCertificationButtonFirstSponsor("Sale Certification",currentEventName);
//		ref.clickbtnAdmin();
//		ref.SBMACertificationButtonQC();
	}

	@Test(priority = 15, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Certification_Functionality_by_Second_Sponsor_of_Quebec_Jurisdiction_for_SBMA_Event() throws InterruptedException {
		testCaseId.set("67663");
		JointAuctionTieBreakerPage ref = new JointAuctionTieBreakerPage();
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ref.acceptSbmaAndApcrCertificationButtonSecondSponsor("Sale Certification",currentEventName);
//		ref.clickbtnAdmin();
//		ref.SBMACertificationButtonSecondSponcer();
	}
	
	@Test(priority = 16, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Reports_are_Generated_after_Event_Certification_of_SBMA_Event_for_Quebec_Jurisdiction() throws InterruptedException {
		testCaseId.set("67664");
		GenericUtils gm = new GenericUtils();
		gm.UserLogin( UsersData.UserSponsorQc2 , UsersData.password );
		gm.handleSecurityQue();
		gm.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		gm.searchReport();
		gm.searchReportByEvent(currentEventName);
		gm.verifyAllReportsAreDisplayedQCSBMAEvent();
	}
	

}
