package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static envs.PropertyEnv.API_URL;

public class BaseApi {
    public static Response postRequest(String username, String token, BodyArgs bodyArgs){
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .post(API_URL);
    }
}
