package com.wciauction.auctionevents.tests;

import static constants.ThreadConstants.testCaseId;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.monitoring.BiddingPage;
import com.wciauction.pageObjects.monitoring.MonitoringPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;

import constants.EventUtils;

public class BiddingTest extends BaseClass {
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Add_Flag_Functionality_for_Market_Monitor() throws InterruptedException {
		testCaseId.set("67679");
		PublicHomePage ref = new PublicHomePage();
		ref.MMLoginCa();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref1 = new BiddingPage();
		ref1.addFlagANDRemoveFunctionality();
		System.out.println("Add flag functionality verified");
		ObjectRepo.getTest().log(Status.PASS, "Add flag functionality verified by MM");
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Monitoring_Page_is_Accessible_for_Market_Monitor_User_During_Bidding_Window_Time_of_SBMA_Event() throws Exception {
		testCaseId.set("67680");
		PublicHomePage login = new PublicHomePage();
		login.MMLoginQc();
		System.out.println("Step1: Market Monitor Login successfully");
		login.handleSecurityQue();
		login.clickOnnextQC();
		login.changeLangauge();
		System.out.println("Step2: Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		BiddingPage biddingPageAccess = new BiddingPage();
		biddingPageAccess.biddingPageAccessToMM_SBMA();
		System.out.println("Market Monitor Can acces Monitoring Page for SBMA.");
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Can acces Monitoring Page for SBMA.");
		System.out.println("******************************************************************");
	}

	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_that_Market_Monitor_Can_Perform_Sorting_on_the_Bidding_Page() throws Exception {
		testCaseId.set("67681");
		PublicHomePage login = new PublicHomePage();
		login.MMLoginQc();
		System.out.println("Step1: Market Monitor Login successfully");
		login.handleSecurityQue();
		login.clickOnnextQC();
		login.changeLangauge1();
		System.out.println("Step2: Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		BiddingPage biddingPageAccess = new BiddingPage();
		biddingPageAccess.sortingOnTheBiddingPage_SBMA();
		System.out.println("Market Monitor can sort on the bidding page.");
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor can sort on the bidding page.");
		System.out.println("******************************************************************");
	}

	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Market_Monitor_Can_Flag_Unflag_and_Add_Comments_on_Multiple_Bids_in_SBMA_Event() throws Exception {
		testCaseId.set("67682");
		PublicHomePage login = new PublicHomePage();
		login.MMLoginQc();
		System.out.println("Step1: Market Monitor Login successfully");
		login.handleSecurityQue();
		login.clickOnnextQC();
		login.changeLangauge();
		System.out.println("Step2: Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		BiddingPage biddingPageAccess = new BiddingPage();
		biddingPageAccess.flagUnflagCommentsOnTheBiddingPage_SBMA();
		System.out.println("Market Monitor Can Flag Unflag and Comments on Multiple Bids.");
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Can Flag Unflag and Comments on Multiple Bids.");
		System.out.println("******************************************************************");
	}

	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Monitoring_Page_is_Accessible_for_Market_Monitor_User_During_Bidding_Window_of_Reserve_Sale_Event() throws Exception {
		testCaseId.set("67683");
		PublicHomePage login = new PublicHomePage();
		login.MMLoginCa();
		System.out.println("Step1: Market Monitor Login successfully");
		login.handleSecurityQue();
		login.clickOnnext();
		System.out.println("Step2: Security Question handled successfully");
		ObjectRepo.getTest().log(Status.PASS, "Security Answer sent Successfully");
		BiddingPage biddingPageAccess = new BiddingPage();
		biddingPageAccess.biddingPageAccessToMM_RS(EventUtils.reserveSaleCalifornia);
		System.out.println("Market Monitor Can acces Monitoring Page for Reserve Sale.");
		ObjectRepo.getTest().log(Status.PASS, "Market Monitor Can acces Monitoring Page for Reserve Sale.");
		System.out.println("******************************************************************");
	}

	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_View_Countdown_Timer_on_Private_Home_Page() throws Exception {
		testCaseId.set("67684");
		PublicHomePage ref = new PublicHomePage();
		ref.ARLoginQC();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage eventDeatils = new BiddingPage();
		eventDeatils.viewTheCountdownTimerFromThePrivateHomePage();
		System.out.println(
				"Countdown timer for an upcoming event (Auctions, Reserve Sale, SBMA and SBA) in the following format: Days Hours Minutes if more than 24 hours remaining.");
		ObjectRepo.getTest().log(Status.PASS,
				"Countdown timer for an upcoming event (Auctions, Reserve Sale, SBMA and SBA) in the following format: Days Hours Minutes if more than 24 hours remaining.");
	}

	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Name_Displayed_in_Header_Section_of_Event_Page() throws Exception {
		testCaseId.set("67685");
		PublicHomePage ref = new PublicHomePage();
		ref.ARLoginQC();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage eventDeatils = new BiddingPage();
		eventDeatils.verifyEventNameOnPrivateHomePageAndAuctionPage();
		System.out.println("The event name is displayed in the event header section.");
		ObjectRepo.getTest().log(Status.PASS, "The event name is displayed in the event header section.");
	}

	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_View_Reserve_Sale_Event_Details_on_Bidding_Page_During_Open_Bidding_Window()
			throws Exception {
		testCaseId.set("67686");
		PublicHomePage ref = new PublicHomePage();
		ref.ARLoginQC44();
		ref.handleSecurityQue();
		ref.clickOnnextQC();
		ref.handleCloseButton();
		Thread.sleep(3000);
		ref.changeLangaugeAR();
		BiddingPage eventDetails = new BiddingPage();
		eventDetails.confirmEventNameOnPrivateHomePageAndAuctionPage();
		eventDetails.assertFinancialGuaranteeAmountAdHoldingLimitDetailsForQC();
		eventDetails.caterogyPriceInCADForQC1();
		eventDetails.totalEmissionUnitsAvailableforQC1();
		eventDetails.totalEmissionUnitsBidforQC();
		eventDetails.totalBidsSubmittedforQC();
	}

	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Place_Bid_During_Open_Bidding_Window() throws Exception {
		testCaseId.set("67687");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage enterBid = new BiddingPage();
		enterBid.confirmARCanPlaceBid();
		enterBid.BiddingAR();
	}

	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Search_Bids_by_Transaction_ID() throws Exception {
		testCaseId.set("67688");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		ref2.searchQC4153421509Entity();
	}

	@Test(priority = 11, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Edit_Placed_Bids_During_Open_Bidding_Window() throws Exception {
		testCaseId.set("67689");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage enterBid = new BiddingPage();
		enterBid.editBid();
	}

	@Test(priority = 12, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Delete_Individual_Bid_During_Open_Bidding_Window() throws Exception {
		testCaseId.set("67690");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage enterBid = new BiddingPage();
		enterBid.deleteBid();
	}

	@Test(priority = 13, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Delete_All_Submitted_Bids_for_the_Entity() throws Exception {
		testCaseId.set("67691");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		ref2.searchQC4153421509Entity();
		BiddingPage enterBid = new BiddingPage();
		enterBid.deleteAllBids();
		enterBid.addBidAuction();
		enterBid.addBidAuction();
		enterBid.addBidAuction();
		enterBid.addBidAuction();
		enterBid.addBidAuction();
	}

	@Test(priority = 14, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_View_Event_Header_Bidding_Window_Timer_Event_Details_and_Submitted_Bids_List()
			throws InterruptedException {
		testCaseId.set("67692");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage abc = new BiddingPage();
		abc.SelectEventName();
		abc.EventNameAndBidGuaranteeViewOnBiddingPage();
		abc.listOfSubmittedBids();
	}

	@Test(priority = 15, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_AR_User_Can_Download_Latest_Bid_Template() throws InterruptedException, AWTException {
		testCaseId.set("67693");
		PublicHomePage ref = new PublicHomePage();
		ref.AR_LoginCA();
		ref.handleSecurityQue();
		ref.clickOnnext();
		BiddingPage ref2 = new BiddingPage();
		ref2.getAccountSelect2();
		ref2.getBtnContineuEN();
		BiddingPage abc = new BiddingPage();
		abc.SelectEventName();
		abc.downloadBidTemplate();
	}
	
}
