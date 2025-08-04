package api.testcases;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Authentications {
    @Test(priority = 1)
    void testBasicAuthentication() {
        //basic/digest/preemptive - we use username and password to authenticate , internal algorithm is different
        given()
                .auth().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 2)
    void testDigestAuthentication() {
        //digest - we use username and password to authenticate
        given()
                .auth().digest("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 3)
    void testPreemptiveAuthentication() {
        //preemptive(with basic) - we use username and password to authenticate
        given()
                .auth().preemptive().basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().all();

    }

    @Test(priority = 4)
    void testBearerAuthentication() {
//       bearer token - we will create our own token

        String bearerToken = "";
        given()
                .headers("Authorization","Bearer "+bearerToken)
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200).log().all();

    }
    @Test(priority = 5)
    void testOath1Authentication() {
//      Oath1 have a lot of parameters


        given()
                .auth().oauth("consumerkey","consumersecret","accesstoken","tokensecret")
                .when()
                .get()
                .then()
                .statusCode(200).log().all();

    }

    @Test(priority = 6)
    void testOath2Authentication() {
//      Oath2 only 1 parameter is passed
        given()
                .auth().oauth2("")
                .when()
                .get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test(priority = 7)
    void testAPIKeyAuthentication() {
//      API Key need to be generated based on some username password ,
//      pass it as query parameter(depends on API dev rules)
        given()
                .queryParam("appid","apikey")
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();

    }



}
