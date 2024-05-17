package core.handlers;

import io.restassured.response.Response;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResponseHandler {

    public String extractField(Response response, String field) {
        return response.jsonPath().getString(field);
    }

    public <T> T extractField(Response response, String field, Class<T> cls) {
        return response.jsonPath().getObject(field, cls);
    }

    public List<String> extractList(Response response, String field) {
        return response.jsonPath().getList(field);
    }

    public void validateStatusCode(Response response, int expectedStatusCode) {
        assertThat(response.getStatusCode(), is(expectedStatusCode));
    }

    public void validateFieldEquals(Response response, String field, Object expectedValue) {
        assertThat(response.jsonPath().get(field), is(expectedValue));
    }

    public void validateFieldContains(Response response, String field, String expectedValue) {
        assertThat(response.jsonPath().getString(field), containsString(expectedValue));
    }

    public void validateFieldNotNull(Response response, String field) {
        assertThat(response.jsonPath().get(field), is(notNullValue()));
    }

    public void validateFieldMatches(Response response, String field, String regex) {
        assertThat(response.jsonPath().getString(field), matchesPattern(regex));
    }
}
