package com.wciauction.monitoring.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.monitoring.BiddingPage;
import com.wciauction.pageObjects.monitoring.PublicHomeContactUsPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.user.ForgotPassphrasePage;

public class PublicHomePageTest extends BaseClass {
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Public_Home_Page_Footer() throws Exception {
		testCaseId.set("67697");
		PublicHomePage home = new PublicHomePage();
		home.verify_Footer();
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Public_Home_Page_FAQ_and_Resources_Section() {
		testCaseId.set("67698");
		PublicHomePage home = new PublicHomePage();
		home.verify_FAQAndResources();
	}
	
	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Field_Validation_on_Contact_Us_page() throws InterruptedException {
		testCaseId.set("67699");
		PublicHomeContactUsPage abc = new PublicHomeContactUsPage();
		abc.ContactUsNameFieldValidation();
	}

	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_the_Email_is_Sent_with_Message_For_Contact_Us() {
		testCaseId.set("67700");
		PublicHomeContactUsPage abc = new PublicHomeContactUsPage();
		abc.contactUsForm();
	}

	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Contact_Us_Form_Data_is_Prepopulated_for_Logged_In_User() throws Exception {
		testCaseId.set("67701");
		CreateEventPage login = new CreateEventPage();
		CreateEventPage login1 = new CreateEventPage();
		login.loginSponsorCalifornia();
		login.handleSecurityQue();
		login.clickOnnext();
		login1.contactUsPage();
	}

	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Contact_Us_Functionality_for_Logged_In_User() throws InterruptedException {
		testCaseId.set("67702");
		CreateEventPage login = new CreateEventPage();
		CreateEventPage login3 = new CreateEventPage();
		login.loginSponsorCalifornia();
		login.handleSecurityQue();
		login.clickOnnext();
		login3.contactUsForm();
	}

	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Contact_Us_Functionality_for_public_User() throws InterruptedException {
		testCaseId.set("67703");
		CreateEventPage login = new CreateEventPage();
		login.HappyPathcontactUsPage();
	}
	
	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Navigation_to_California_Jurisdiction_Website() throws Exception {
		testCaseId.set("67704");
		BiddingPage juriWebsite = new BiddingPage();
		juriWebsite.navigateToCAJuri();
	}

	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Navigation_to_Quebec_Jurisdiction_Website() throws Exception {
		testCaseId.set("67705");
		BiddingPage juriWebsite = new BiddingPage();
		juriWebsite.navigateToQCJuri();
	}

	@Test(priority = 10, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Navigation_to_Washington_Jurisdiction_Website() throws Exception {
		testCaseId.set("67706");
		BiddingPage juriWebsite = new BiddingPage();
		juriWebsite.navigateToWAJuri();
	}
}
