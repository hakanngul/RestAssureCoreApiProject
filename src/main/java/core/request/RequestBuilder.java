package core.request;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;

import core.authentication.AuthenticationManager;
import core.config.ConfigProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RequestBuilder {

    private RequestSpecification request;

    public RequestBuilder() throws Exception {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));
        request = buildRequest();
    }

    public Response getRequest(String endpoint) {
        return request.when().get(endpoint);
    }

    public Response postRequest(String endpoint, Object body) {
        return request.body(body).when().post(endpoint);
    }

    public Response putRequest(String endpoint, Object body) {
        return request.body(body).when().put(endpoint);
    }

    public Response deleteRequest(String endpoint) {
        return request.when().delete(endpoint);
    }

    private RequestSpecification buildRequest() throws Exception {
        try {
            if (request == null) {
                request = given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                        .spec(getRequestSpecBuilder()
                                .build());
            }
            return request;
        } catch (Exception e) {
            throw new Exception("Error building API request", e);
        }
    }

    private RequestSpecBuilder getRequestSpecBuilder() {
        var requestSpec = new RequestSpecBuilder();
        requestSpec.setBaseUri(ConfigProvider.getBaseUrl());
        requestSpec.addHeader("Authorization", ConfigProvider.getApiKey());
        requestSpec.addHeader("token", AuthenticationManager.getInstance().getToken());
        requestSpec.addHeader("Accept", "application/json");
        requestSpec.addHeader("Content-Type", "application/json");
        requestSpec.setContentType(ContentType.JSON);
        requestSpec.setAccept(ContentType.JSON);
        requestSpec.setConfig(RestAssured.config().decoderConfig(decoderConfig().useNoWrapForInflateDecoding(true)));
        return requestSpec;
    }
}
