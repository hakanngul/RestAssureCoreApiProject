package core.base;

import core.config.ConfigProvider;
import core.handlers.AssertionHandler;
import core.handlers.FluentResponse;
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
import org.junit.jupiter.api.*;

public abstract class BaseTest {

    protected static EnvironmentManager environmentManager;
    protected static TestDataProvider testDataProvider;

    protected LoggerManager logManager;
    protected RequestBuilder requestBuilder;
    protected ResponseHandler responseHandler;
    protected Reporter reporter;
    protected RetryPolicy retryPolicy;
    protected ExceptionHandler exceptionHandler;
    protected FluentResponse fluentResponse;
    protected AuthenticationManager authenticationManager;

    private Response response;
    private TestInfo testInfo;


    @BeforeAll
    public static void setupClass() {
        testDataProvider = TestDataProvider.getInstance();
        EnvironmentManager.setEnvironment("test");
        ConfigProvider.loadConfigurations("src/main/resources/config.properties");
    }

    @BeforeEach
    public void setup(TestInfo testInfo) throws Exception {
        this.testInfo = testInfo;
        logManager = new LoggerManager();
        requestBuilder = new RequestBuilder();
        responseHandler = new ResponseHandler();
        reporter = Reporter.getInstance();
        retryPolicy = new RetryPolicy();
        exceptionHandler = new ExceptionHandler();
        // authenticationManager = new AuthenticationManager();

        String username = ConfigProvider.getProperty("username");
        String password = ConfigProvider.getProperty("password");
        // authenticationManager.authenticate(username, password);
        reporter.startTest(testInfo.getDisplayName());
        reporter.log(testInfo.getTestClass().get().getSimpleName() + " - " + testInfo.getTestMethod().get().getName());
    }

    @AfterEach
    public void tearDown() {
        reporter.endTest();
    }


    protected void getRequest(String endpoint) {
        logManager.log("Sending GET request to: " + endpoint);
        reporter.log("Sending GET request to: " + endpoint + "with " + this.testInfo.getDisplayName());
        response = retryPolicy.executeWithRetry(() -> requestBuilder.getRequest(endpoint), 3);
        fluentResponse = new FluentResponse(response);
    }

    protected void postRequest(String endpoint, Object body) {
        logManager.log("Sending POST request to: " + endpoint);
        response = retryPolicy.executeWithRetry(() -> requestBuilder.postRequest(endpoint, body), 1);
        fluentResponse = new FluentResponse(response);
    }

    protected void putRequest(String endpoint, Object body) {
        logManager.log("Sending PUT request to: " + endpoint);
        response = retryPolicy.executeWithRetry(() -> requestBuilder.putRequest(endpoint, body), 3);
        fluentResponse = new FluentResponse(response);
    }

    protected void deleteRequest(String endpoint) {
        logManager.log("Sending DELETE request to: " + endpoint);
        response = retryPolicy.executeWithRetry(() -> requestBuilder.deleteRequest(endpoint), 3);
        fluentResponse = new FluentResponse(response);
    }
}
