package com.wciauction.pageObjects.utils;
 
import java.util.List;
 
import org.testng.annotations.Test;
 
import com.wci.auction.org.resources.BaseClass;
import com.wci.auction.org.resources.MailsacUtil;
 
import io.restassured.response.Response;
 
 
public class TestEmailPage extends BaseClass {
 
	public static String link;
	
    public void getFirstMailData(String EmailID) {
        // 1. Get the list of messages for an inbox
        Response inboxResponse = MailsacUtil.getMailsacData(EmailID, "messages", null);
        inboxResponse.prettyPrint();
 
        // 2. Extract the first messageId
        String messageId = inboxResponse.jsonPath().getString("[0]._id");
        System.out.println("First Message ID: " + messageId);
 
        // 3. Get the plain text body of that email
        Response textResponse = MailsacUtil.getMailsacData(EmailID, "text", messageId);
        System.out.println("Email Text:\n" + textResponse.asString());
    }
    
    
    public void getFirstMailLink(String emailID) {
        // Get the list of messages for an inbox
        Response inboxResponse = MailsacUtil.getMailsacData(emailID, "messages", null);
 
        if (inboxResponse.jsonPath().getList("").isEmpty()) {
            System.out.println("No emails found for: " + emailID);
            return;
        }
 
        // Loop through messages to find one with a link
        link = null;
        int totalMessages = inboxResponse.jsonPath().getList("").size();
 
        for (int i = 0; i < totalMessages; i++) {
            // Fetch first link in "links" array for the message
            List<String> linksList = inboxResponse.jsonPath().getList("[" + i + "].links");
 
            if (linksList != null && !linksList.isEmpty()) {
                link = linksList.get(0); // Take the first link
                break;
            }
        }
 
        if (link != null) {
            System.out.println("Link found: " + link);
        } else {
            System.out.println("No link found in any email for: " + emailID);
        }
    }
 
    
    @Test
    public void getMailData() throws InterruptedException {
    	TestEmailPage ref = new TestEmailPage();
//    	ref.getFirstMailData("api_user_940868@mailsac.com");
    	ref.getFirstMailLink("api_user_940868@mailsac.com");
    	Thread.sleep(3000);
    	getDriver().navigate().to(link);
    	Thread.sleep(30000);
    }
    
}