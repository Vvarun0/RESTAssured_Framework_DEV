package api.endpoints;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class userEndPoint {

public static Response CreateUser(user payload){

    Response response =given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(payload)

            .when()
            .post(post_url);
    return response;

}
    public static Response GetUser(String userName){

        Response response =given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)

                .when()
                .get(get_url);
        return response;

    }
    public static Response UpdateUser(String userName, user payload){

        Response response =given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)

                .when()
                .put(put_url);
        return response;

    }
    public static Response DeleteUser(String userName){

        Response response =given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)


                .when()
                .delete(delete_url);
        return response;

    }
}
