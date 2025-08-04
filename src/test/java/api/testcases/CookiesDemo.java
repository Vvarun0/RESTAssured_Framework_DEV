package api.testcases;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo {
    @Test(priority = 1)
    void testcookies() {
        //expected to fail as cookie is not same
        given()

                .when()
                .get("https://www.google.com")
                .then()
                .cookie("AEC", "")
                .log().all();
    }

    @Test(priority = 2)
    void getAllCookiesInfo() {
        //expected to fail as cookie is not same
        Response res = given()

                .when()
                .get("https://www.google.com");

        //get single cookie information
//       System.out.println("AEC cookie value : "+res.getCookie("AEC"));

        //get all cookies information
        Map<String, String> cookies_values = res.getCookies();
        for (Map.Entry<String, String> entry : cookies_values.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
