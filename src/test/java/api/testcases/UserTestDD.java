package api.testcases;

import api.endpoints.userEndPoint;
import api.payload.user;

import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserTestDD {

//    user userPayload;


    @Test(priority = 1 ,dataProvider = "AllData" ,dataProviderClass = DataProviders.class)
    public void testCreateUser(String UserID ,String UserName,String FirstName,String LastName,String Email,String Password,String Phone){
        System.out.println("CREATE USER ");

        user userPayload= userPayload = new user();

        userPayload.setId(Integer.parseInt(UserID));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);

        Response response = userEndPoint.CreateUser(userPayload);

        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 2 ,dataProvider = "UserNameData" ,dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName){

        Response response = userEndPoint.DeleteUser(userName);

        System.out.println("DELETE USER DATA");
        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);
    }


}
