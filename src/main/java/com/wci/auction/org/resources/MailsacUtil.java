package com.wci.auction.org.resources;
 
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
 
public class MailsacUtil {
 
    private static final String BASE_URL = "https://mailsac.com/api";
    private static final String API_KEY = "k_I3ixIGO3Av7Z6wFCY3FrCFZcRgzFe2UUcipM"; // Move to config
 
    /**
     * Fetches data from Mailsac for the given email and type.
     *
     * @param emailAddress The email address (e.g., "test@mailsac.com")
     * @param dataType     The type of data: messages, text, body, raw, headers, dirty
     * @param messageId    The message ID (null if dataType = messages)
     * @return Response object with the API result
     */
    public static Response getMailsacData(String emailAddress, String dataType, String messageId) {
        String endpoint;
        if (messageId == null) {
            endpoint = String.format("%s/addresses/%s/messages", BASE_URL, emailAddress);
        } else {
            endpoint = String.format("%s/%s/%s/%s", BASE_URL, dataType, emailAddress, messageId);
        }
        return given()
                .header("Mailsac-Key", API_KEY)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}