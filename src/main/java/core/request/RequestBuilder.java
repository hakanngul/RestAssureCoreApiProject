package core.request;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class RequestBuilder {
    public Response getRequest(String endpoint) {
        return given().when().get(endpoint);
    }

    public Response postRequest(String endpoint, Object body) {
        return given().body(body).when().post(endpoint);
    }

    public Response putRequest(String endpoint, Object body) {
        return given().body(body).when().put(endpoint);
    }

    public Response deleteRequest(String endpoint) {
        return given().when().delete(endpoint);
    }
}
