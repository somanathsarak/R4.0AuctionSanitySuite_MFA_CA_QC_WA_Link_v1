package com.wciauction.cms.tests;
 
import static constants.ThreadConstants.testCaseId;
import org.testng.annotations.Test;
import com.wci.auction.org.resources.BaseClass;
import com.wciauction.pageObjects.auction.CMSPage;
import com.wciauction.pageObjects.user.LoginPageUpdated;
import com.wciauction.pageObjects.utils.GenericUtils;
 
public class CMS_Test extends BaseClass {
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Sponsor_and_verify_by_AR_for_California_Jurisdiction() throws Exception {
		testCaseId.set("70761");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UserSponsorCa2"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentFourthNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin("asmith@demo.citssdev.com","Test@1234");
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity("Company+H (CA3213-4519)");
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UserSponsorCa2"),prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Sponsor_and_verify_by_AR_for_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("70762");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UserSponsorQc2"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentFourthNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("AR_USerName2"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(prop.getProperty("QCFirstEntity"));
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UserSponsorQc2"), prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Sponsor_and_verify_by_AR_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("70763");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UserSponsorWS2"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentThirdNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin("oscott@demo.citssdev.com", prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity("WAGMPorg+O (WA6651-10082)");
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UserSponsorWS2"), prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Event_Administrator_and_verify_by_AR_for_California_Jurisdiction() throws Exception {
		testCaseId.set("70764");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UnameEACa"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentFourthNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin("asmith@demo.citssdev.com","Test@1234");
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity("Company+H (CA3213-4519)");
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UnameEACa"), prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Event_Administrator_and_verify_by_AR_for_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("70765");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UnameEAQc"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentFourthNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("AR_USerName2"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity(prop.getProperty("QCFirstEntity"));
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UnameEAQc"), prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_Upload_CMS_file_functionality_by_Event_Administrator_and_verify_by_AR_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("70766");
		LoginPageUpdated login = new LoginPageUpdated();
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UnameEAWS"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.clickingOnDddDocumentButtonOfNotices();
		ref1.editTheNoticesDocumentThirdNewAdded();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
		login.Logout();
		gm.runTimeUserLogin("oscott@demo.citssdev.com", prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		gm.SelectEntity("WAGMPorg+O (WA6651-10082)");
		ref1.validatePdfDownload();
		login.Logout();
		gm.runTimeUserLogin(prop.getProperty("UnameEAWS"), prop.getProperty("Password"));
		ref1.navigateOnCMSPage();
		ref1.clickingOnHomeSectionUnderCMS();
		ref1.clickingOnPublicationsPaneSectionUnderCMS();
		ref1.deleteAddedDocumentFromNotices();
		ref1.clickingOnSaveButton();
		ref1.clickingOnPublishButton();
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_All_Page_access_for_Event_Administrator_for_California_Jurisdiction() throws Exception {
		testCaseId.set("70767");
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UnameEACa"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.validateHomePage();
		ref1.validateReportPage();
		ref1.validateEventCreationPage();
		ref1.validateEventPage();
		ref1.validateUsersPage();
		ref1.validateReservePricePage();
		ref1.validateReserveSaleCertificationPage();
		ref1.validateAuctionCertificationPage();
		ref1.validateExecuteAPIPage();
		ref1.validateCMSPage();
		ref1.validateConfigurationsPage();
	}
	
	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_All_Page_access_for_Event_Administrator_for_Quebec_Jurisdiction() throws Exception {
		testCaseId.set("70768");
//		GenericUtils gm = new GenericUtils();
//		CMSPage ref1 = new CMSPage();
//		gm.UserLogin(prop.getProperty("UnameEAQc"), prop.getProperty("Password"));
//		gm.handleSecurityQue();
//		gm.clickOnnext();
//		ref1.validateHomePage();
//		ref1.validateReportPageQC();
//		ref1.validateEventCreationPage();
//		ref1.validateEventPage();
//		ref1.validateUsersPage();
//		ref1.validateReservePricePageQC();
//		ref1.validateSaleCertificationPage();
//		ref1.validateAuctionCertificationPageQC();
//		ref1.validateExecuteAPIPage();
//		ref1.validateCMSPage();
//		ref1.validateConfigurationsPage();
	}
	
	@Test(priority = 9, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verification_of_All_Page_access_for_Event_Administrator_for_Washington_Jurisdiction() throws Exception {
		testCaseId.set("70769");
		GenericUtils gm = new GenericUtils();
		CMSPage ref1 = new CMSPage();
		gm.UserLogin(prop.getProperty("UnameEAWS"), prop.getProperty("Password"));
		gm.handleSecurityQue();
		gm.clickOnnext();
		ref1.validateHomePageWA();
		ref1.validateReportPageQC();
		ref1.validateEventCreationPage();
		ref1.validateEventPage();
		ref1.validateUsersPage();
		ref1.validateReservePricePageWA();
		ref1.validateReserveSaleCertificationPageWA();
		ref1.validateAuctionCertificationPage();
		ref1.validateExecuteAPIPageWA();
		ref1.validateCMSPage();
		ref1.validateConfigurationsPage();
	}
 
}