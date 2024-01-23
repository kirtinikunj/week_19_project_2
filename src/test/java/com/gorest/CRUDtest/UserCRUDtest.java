package com.gorest.CRUDtest;

import com.gorest.steps.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCRUDtest extends TestBase {

    static String name = "Ram";
    static String email = TestUtils.getRandomValue() + "ram11@gmail.com";
    static String gender = "male";
    static String status = "active";

    static int userId;


    @Steps
    UserSteps steps;

    @Title("This will crate new user")
    @Test
    public void test001() {

        ValidatableResponse response = steps.creatingUser(name, email, gender, status);
        response.statusCode(201);

        userId = response.extract().path("id");
        System.out.println("User ID created" + userId);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002(){

        ValidatableResponse response = steps.getUserById(userId);
               response.statusCode(200);
    }

    @Title("Verify if the user was updated")
    @Test //updating record
    public void test003(){
        name = "Shyam";
        ValidatableResponse  response = steps.updatingStore(userId, name, email, gender, status);
        response.statusCode(200);
    }

    @Title("update the user and verify if the user is updated")
    @Test
    public void test004() {
        steps.deleteUser(userId).statusCode(204);
        steps.getUser(userId).statusCode(404);
    }

}
