package com.wciauction.user.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;
import com.wci.auction.org.resources.BaseClass;
import com.wciauction.pageObjects.TestSuitesPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.user.ForgotPassphrasePage;
import com.wciauction.requiredData.UsersData;

public class UserAdministrationTest extends BaseClass {
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Sponsor_Can_Access_User_Administration_Screen_From_Contextual_Navigation_Bar() throws Exception {
		testCaseId.set("67707");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifySponsorCanAccessUserAdministrationScreen();
	}

	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Sponsor_Must_Be_Able_To_Search_The_Data_By_Email_ID() throws Exception {
		testCaseId.set("67708");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();		
		ref.searchTheDataByEmail(UsersData.userSponsorCa2);
	}

	@Test(priority = 3, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_The_Pagination_Feature_On_The_User_Administration_Page() throws Exception {
		testCaseId.set("67709");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();	
		ref.paginationCheck();
	}

	@Test(priority = 4, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_That_The_Selected_Users_Security_Questions_Are_Reset() throws Exception {
		testCaseId.set("67710");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifyUserSecurityQuestionsAreReset();
	}

	@Test(priority = 5, retryAnalyzer = analyzer.RetryAnalyzer.class)//Confirmation from Vishal
	public void verify_User_Grid_Displays_Only_One_Record_Per_Unique_User_Entity_Relation_For_AR_User_Record() throws Exception {
		testCaseId.set("67711");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifyOneRecordPerUniqueUserEntityRelationForARUserRecord();
		ref.verifyOneRecordForEachUserWithNoUserEntityRelationship();
	}

	@Test(priority = 6, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Add_New_User_Functionality_By_Sponsor() throws Exception {
		testCaseId.set("67712");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifyAddNewUserRole();
	}
	
	@Test(priority = 7, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Activate_User_Functionality() throws Exception {
		testCaseId.set("67713");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		ForgotPassphrasePage fp =new ForgotPassphrasePage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifySponsorCanAccessUserAdministrationScreen();
		fp.activateUser();
	}
	
	@Test(priority = 8, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Deactivate_User_Functionality() throws Exception {
		testCaseId.set("67714");
		PublicHomePage login = new PublicHomePage();
		TestSuitesPage ref = new TestSuitesPage();
		ForgotPassphrasePage fp =new ForgotPassphrasePage();
		login.sponsorLoginCa2();
		login.handleSecurityQue();
		login.clickOnnext();
		ref.verifySponsorCanAccessUserAdministrationScreen();
		fp.deactivateUser();
	}

}
