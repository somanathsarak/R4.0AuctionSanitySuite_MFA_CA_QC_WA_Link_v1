package com.wciauction.auth.tests;

import static constants.ThreadConstants.testCaseId;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.ObjectRepo;
import com.wciauction.pageObjects.ApprovalStatusPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;
import com.wciauction.pageObjects.utils.GenericUtils;
import com.wciauction.requiredData.EntityData;

public class LoginTest extends BaseClass {
	
	@Test(priority = 1, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void verify_the_login_functionality_of_all_users() throws InterruptedException {
		testCaseId.set("67582");
		PublicHomePage ref = new PublicHomePage();
		ref.sponsorLoginCa2();
		Thread.sleep(5000);
		ref.handleSecurityQue();
		ref.clickOnnext();
		ApprovalStatusPage status = new ApprovalStatusPage();
		status.Logout();
		System.out.println("California Sponsor login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California Sponsor login successfully");
		System.out.println("-------------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------------");
		ref.FSALoginCa();
		Thread.sleep(5000);
		ref.handleSecurityQue();
		ref.clickOnnext();
		status.Logout();
		System.out.println("California FSA login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California FSA login successfully");
		System.out.println("-------------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------------");
		ref.EALoginCa();
		Thread.sleep(5000);
		ref.handleSecurityQue();
		ref.clickOnnext();
		status.Logout();
		System.out.println("California EA login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California EA login successfully");
		System.out.println("-------------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------------");
		ref.MMLoginCa();
		Thread.sleep(5000);
		ref.handleSecurityQue();
		ref.clickOnnext();
		status.Logout();
		System.out.println("California MM login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California MM login successfully");
		System.out.println("-------------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------------");
		ref.ARLoginCA();
		Thread.sleep(5000);
		ref.handleSecurityQue();
		ref.clickOnnext();
		GenericUtils gm = new GenericUtils();
		gm.SelectEntity(EntityData.CA_4406_5609);
		status.Logout();
		System.out.println("California AR login successfully");
		ObjectRepo.getTest().log(Status.PASS, "California AR login successfully");
		System.out.println("-------------------------------------------------------------------");
		ObjectRepo.getTest().log(Status.PASS, "-------------------------------------------------------------------");
	}

}
