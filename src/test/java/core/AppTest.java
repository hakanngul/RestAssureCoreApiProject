package core;

import core.base.BaseTest;
import core.config.ConfigProvider;
import core.handlers.AssertionHandler;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest extends BaseTest {

    @Test
    public void testGetRequestxx() {
        String endpoint = ConfigProvider.getBaseUrl() + "/posts/1";
//        Response response = getRequest(endpoint);

        // Doğrulamalar
//        responseHandler.validateStatusCode(response, 200);
//        responseHandler.validateFieldEquals(response, "userId", 1);
//        responseHandler.validateFieldEquals(response, "id", 1);
//        responseHandler.validateFieldNotNull(response, "title");
//        responseHandler.validateFieldNotNull(response, "body");

        getRequest(endpoint);
        fluentResponse.validateStatusCode(200);
        reporter.log("GET request test passed for endpoint: " + endpoint);
    }

    @Test
    public void testPostRequest() {
        // Using the fixed endpoint for JSONPlaceholder
        String endpoint = "https://fakestoreapi.com/products";

        // Example request body
        String requestBody = "{\n" +
                "    \"id\": 345345,\n" +
                "    \"title\": \"Hakan GUL Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops\",\n" +
                "    \"price\": 109.95,\n" +
                "    \"description\": \" Hakan GUL Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday\",\n" +
                "    \"category\": \"Hakan GUL men's clothing\",\n" +
                "    \"image\": \"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg\",\n" +
                "    \"rating\": {\n" +
                "        \"rate\": 3.9,\n" +
                "        \"count\": 120\n" +
                "    }\n" +
                "}";

        postRequest(endpoint, requestBody);

        fluentResponse.validateStatusCode(200);

        // Sending POST request
//        Response response = postRequest(endpoint, requestBody);

        // Validations based on the known response structure of JSONPlaceholder
//        responseHandler.validateStatusCode(response, 200);
//        responseHandler.validateFieldNotNull(response, "id");
//        responseHandler.validateFieldEquals(response, "status", "OK");
//        responseHandler.validateFieldEquals(response, "code", 200);

        // Reporting
        reporter.log("POST request test passed for endpoint: " + endpoint);
    }



    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("RepeatingTest")
    void customDisplayNamexx(RepetitionInfo repInfo, TestInfo testInfo) {
        int i = 3;
        System.out.println(testInfo.getDisplayName() + "-->" + repInfo.getCurrentRepetition());
        assertEquals(repInfo.getCurrentRepetition(), i);
    }
}
