package core.authentication;

import lombok.Getter;

import static io.restassured.RestAssured.given;

public class AuthenticationManager {
    @Getter
    private String token;

    @Getter
    private static final AuthenticationManager instance = new AuthenticationManager();

    private AuthenticationManager() {
    }

    public void authenticate(String username, String password) {
        this.token = given()
                .auth().preemptive().basic(username, password)
                .when()
                .post("/authenticate")
                .then()
                .extract().path("token");
    }
}
