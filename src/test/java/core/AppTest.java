package core;

import core.base.BaseTest;
import core.config.ConfigProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class AppTest extends BaseTest {

    @Test
    public void testGetRequest() {
        String endpoint = ConfigProvider.getProperty("base.url") + "/posts/1";
        Response response = getRequest(endpoint);

        // Doğrulamalar
        responseHandler.validateStatusCode(response, 200);
        responseHandler.validateFieldEquals(response, "userId", 1);
        responseHandler.validateFieldEquals(response, "id", 1);
        responseHandler.validateFieldNotNull(response, "title");
        responseHandler.validateFieldNotNull(response, "body");

        // Raporlama
        reporter.startTest("GET request test");
        reporter.log("GET request test passed for endpoint: " + endpoint);
        reporter.endTest();
    }

    @Test
    public void testPostRequest() {
        String endpoint = ConfigProvider.getProperty("base.url") + "/posts";
        String requestBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = postRequest(endpoint, requestBody);

        // Doğrulamalar
        responseHandler.validateStatusCode(response, 201);
        responseHandler.validateFieldEquals(response, "title", "foo");
        responseHandler.validateFieldEquals(response, "body", "bar");
        responseHandler.validateFieldEquals(response, "userId", 1);

        // Raporlama
        reporter.startTest("POST request test");
        reporter.log("POST request test passed for endpoint: " + endpoint);
        reporter.endTest();
    }
}
