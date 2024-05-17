package core.handlers;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AssertionHandler {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode(), is(expectedStatusCode));
    }

    public static void validateFieldEquals(Response response, String field, Object expectedValue) {
        assertThat(response.jsonPath().get(field), is(expectedValue));
    }

    public static void validateFieldContains(Response response, String field, String expectedValue) {
        assertThat(response.jsonPath().getString(field), containsString(expectedValue));
    }

    public static void validateFieldNotNull(Response response, String field) {
        assertThat(response.jsonPath().get(field), is(notNullValue()));
    }

    public static void validateFieldMatches(Response response, String field, String regex) {
        assertThat(response.jsonPath().getString(field), matchesPattern(regex));
    }

    public static void validateJsonSchema(Response response, String schemaPath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    }

    public static void validateResponseTime(Response response, long maxResponseTimeMillis) {
        assertThat(response.time(), lessThan(maxResponseTimeMillis));
    }

    public static void validateHeader(Response response, String headerName, String expectedValue) {
        assertThat(response.getHeader(headerName), is(expectedValue));
    }

    public static void validateHeaderExists(Response response, String headerName) {
        assertThat(response.getHeader(headerName), is(notNullValue()));
    }

    public static void validateResponseContains(Response response, String expectedValue) {
        assertThat(response.asString(), containsString(expectedValue));
    }

    public static void validateResponseNotEmpty(Response response) {
        assertThat(response.asString().trim(), is(not(emptyString())));
    }

    public static void validateFieldListSize(Response response, String field, int expectedSize) {
        assertThat(response.jsonPath().getList(field), hasSize(expectedSize));
    }
}
