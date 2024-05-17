package core.handlers;

import core.utils.Reporter;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class FluentResponse {
    private final Response response;
    private static final Reporter reporter = Reporter.getInstance();

    public FluentResponse(Response response) {
        this.response = response;
    }

    public String extractField(String field) {
        return response.jsonPath().getString(field);
    }

    public <T> T extractField(String field, Class<T> cls) {
        return response.jsonPath().getObject(field, cls);
    }

    public List<String> extractList(String field) {
        return response.jsonPath().getList(field);
    }

    public FluentResponse validateStatusCode(int expectedStatusCode) {
        assertThat(response.getStatusCode(), is(expectedStatusCode));
        return this;
    }

    public FluentResponse validateFieldEquals(String field, Object expectedValue) {
        assertThat(response.jsonPath().get(field), is(expectedValue));
        return this;
    }

    public FluentResponse validateFieldContains(String field, String expectedValue) {
        assertThat(response.jsonPath().getString(field), containsString(expectedValue));
        return this;
    }

    public FluentResponse validateFieldNotNull(String field) {
        assertThat(response.jsonPath().get(field), is(notNullValue()));
        return this;
    }

    public FluentResponse validateFieldMatches(String field, String regex) {
        assertThat(response.jsonPath().getString(field), matchesPattern(regex));
        return this;
    }
}
