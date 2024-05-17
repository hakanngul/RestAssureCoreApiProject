package core.authentication;

import static io.restassured.RestAssured.given;

public class AuthenticationManager {
    private String token;

    public void authenticate(String username, String password) {
        this.token = given()
            .auth().preemptive().basic(username, password)
            .when()
            .post("/authenticate")
            .then()
            .extract().path("token");
    }

    public String getToken() {
        return token;
    }
}
