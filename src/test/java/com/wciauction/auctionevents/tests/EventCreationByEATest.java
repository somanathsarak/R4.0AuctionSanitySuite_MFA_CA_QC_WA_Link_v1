package com.wciauction.auctionevents.tests;

import static constants.ThreadConstants.testCaseId;
import java.io.IOException;
import org.testng.annotations.Test;
import com.wci.auction.org.resources.BaseClass;
import com.wciauction.pageObjects.events.CreateEventPage;
import com.wciauction.pageObjects.monitoring.PublicHomePage;

public class EventCreationByEATest extends BaseClass {
	
	@Test(priority = 0, retryAnalyzer = analyzer.RetryAnalyzer.class, groups = { "sanity" })
	public void Verify_Event_Creation_by_Event_Administrator() throws IOException {
		testCaseId.set("67717");
		PublicHomePage login = new PublicHomePage();
		CreateEventPage abc = new CreateEventPage();
		login.EALoginCa();
		login.handleSecurityQue();
		login.clickOnnext();
		abc.createEventForCal();
		abc.validatePopupforEventCreation();
	}
}
