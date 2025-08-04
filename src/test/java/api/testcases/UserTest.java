package api.testcases;

import api.endpoints.userEndPoint;
import api.payload.user;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class UserTest {

    Faker faker;
    user userPayload;
    public static Logger logger;

    @BeforeClass
    public void generateTestData(){
        faker = new Faker();
        userPayload = new user();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger= LogManager.getLogger("UserTest");
    }
    @Test(priority = 1)
    public void testCreateUser(){
        Response response = userEndPoint.CreateUser(userPayload);
        System.out.println("CREATE USER ");
        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Create User executed");
    }
    @Test(priority = 2)
    public void testgetUser(){
        Response response = userEndPoint.GetUser(this.userPayload.getUsername());
        System.out.println("GET USER DATA ");
        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("Get user data  executed");
    }
    @Test(priority = 3)
    public void testUpdateUser(){
        userPayload.setFirstName(faker.name().firstName());
        Response response = userEndPoint.UpdateUser(this.userPayload.getUsername(),userPayload);

        System.out.println("UPDATE USER DATA");
        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println("CHECK THE RESPONSE POST UPDATE");
        System.out.println("UPDATED USER DETAILS : "+ response.getBody().prettyPrint());
        logger.info("Update User");
    }
    @Test(priority = 4)
    public void testDeleteUser(){

        Response response = userEndPoint.DeleteUser(this.userPayload.getUsername());

        System.out.println("DELETE USER DATA");
        //log response
        response.then().log().all();

        //validation
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("Delete User");
    }


}
