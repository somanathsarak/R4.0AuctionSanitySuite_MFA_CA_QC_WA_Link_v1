package com.wciauction.auth.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;

import com.wci.auction.org.resources.BaseClass;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.user.ChangePassphrasePage;
import com.wciauction.pageObjects.user.LoginPageUpdated;
import com.wciauction.pageObjects.utils.TestEmailPage;

public class ChangePassphraseAndSecurityQuestionTest extends BaseClass {


	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Change_Passphrase_Functionality() throws InterruptedException {
		testCaseId.set("67715");
		LoginPageUpdated login = new LoginPageUpdated();
		ChangePassphrasePage change = new ChangePassphrasePage();
		login.unidentifiedUserLogin("UserSponsorCa", "Password", "English");
		login.handleSecurityQue();
		login.clickOnnext();
		change.navigateOnUserAdministrationPage();
		change.clickingOnAddNewUserButtonPopup();
		change.AddNewUserFunctionality();
//		change.navigateToYopmail(changePassphrase_POM.randomStringEmail);
		TestEmailPage tm = new TestEmailPage();
		tm.getFirstMailLink(ChangePassphrasePage.randomStringEmail);
    	Thread.sleep(3000);
    	getDriver().navigate().to(TestEmailPage.link);
    	Thread.sleep(30000);
		change.createUser();
//		change.navigateToAuction();
		try {
			login.refreshPage();
		} catch (Exception e) {
			login.Logout();
		}
		login.identifiedUserLogin(ChangePassphrasePage.randomStringEmail, "Test@1234", "English");
		login.handleSecurityQue();
		login.clickOnnext();
		change.navigateChangePassphrase();
		change.changePassphrase();
	}
	
	@Test(priority = 2, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_Change_Security_Questions_And_Answers_Functionality() throws InterruptedException {
		testCaseId.set("67716");
		LoginPageUpdated login = new LoginPageUpdated();
		ChangePassphrasePage change = new ChangePassphrasePage();
		login.unidentifiedUserLogin("UserSponsorCa", "Password", "English");
		login.handleSecurityQue();
		login.clickOnnext();
		change.navigateChangeSecurityQuestions();
		change.resetSecurityQuestions();
	}

}

