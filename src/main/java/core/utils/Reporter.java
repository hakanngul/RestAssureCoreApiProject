package core.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;

public class Reporter {
    // Public method to provide access to the instance
    @Getter
    private static final Reporter instance = new Reporter();
    private static final ExtentReports extent;
    private static ExtentTest test;

    static {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    // Private constructor to prevent instantiation
    private Reporter() {}

    public void startTest(String testName) {
        test = extent.createTest(testName);
    }

    public void log(String message) {
        test.info(message);
    }

    public void logPass(String message) {
        test.pass(message);
    }

    public void logFail(String message) {
        test.fail(message);
    }

    public void endTest() {
        extent.flush();
    }
}
