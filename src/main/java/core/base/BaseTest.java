package core.base;

import core.config.ConfigProvider;
import core.handlers.AssertionHandler;
import core.logging.LoggerManager;
import core.request.RequestBuilder;
import core.handlers.ResponseHandler;
import core.utils.TestDataProvider;
import core.utils.EnvironmentManager;
import core.utils.Reporter;
import core.utils.RetryPolicy;
import core.handlers.ExceptionHandler;
import core.authentication.AuthenticationManager;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected static EnvironmentManager environmentManager;
    protected static TestDataProvider testDataProvider;

    protected LoggerManager logManager;
    protected RequestBuilder requestBuilder;
    protected ResponseHandler responseHandler;
    protected Reporter reporter;
    protected RetryPolicy retryPolicy;
    protected ExceptionHandler exceptionHandler;
    protected AuthenticationManager authenticationManager;

    @BeforeAll
    public static void setupClass() {
        testDataProvider = new TestDataProvider();
        EnvironmentManager.setEnvironment("test");
    }

    @BeforeEach
    public void setup() {
        logManager = new LoggerManager();
        requestBuilder = new RequestBuilder();
        responseHandler = new ResponseHandler();
        reporter = new Reporter();
        retryPolicy = new RetryPolicy();
        exceptionHandler = new ExceptionHandler();
        authenticationManager = new AuthenticationManager();

        String username = ConfigProvider.getProperty("username");
        String password = ConfigProvider.getProperty("password");
        authenticationManager.authenticate(username, password);
    }

    protected Response getRequest(String endpoint) {
        logManager.log("Sending GET request to: " + endpoint);
        return retryPolicy.executeWithRetry(() -> requestBuilder.getRequest(endpoint), 3);
    }

    protected Response postRequest(String endpoint, Object body) {
        logManager.log("Sending POST request to: " + endpoint);
        return retryPolicy.executeWithRetry(() -> requestBuilder.postRequest(endpoint, body), 3);
    }

    protected Response putRequest(String endpoint, Object body) {
        logManager.log("Sending PUT request to: " + endpoint);
        return retryPolicy.executeWithRetry(() -> requestBuilder.putRequest(endpoint, body), 3);
    }

    protected Response deleteRequest(String endpoint) {
        logManager.log("Sending DELETE request to: " + endpoint);
        return retryPolicy.executeWithRetry(() -> requestBuilder.deleteRequest(endpoint), 3);
    }
}
