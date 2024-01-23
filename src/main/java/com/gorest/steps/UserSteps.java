package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class UserSteps {

    @Step("Creating user")
    public ValidatableResponse creatingUser(String name, String email, String gender, String status){

        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
                .then().log().all();
    }

    @Step("Getting the new user id ")
    public ValidatableResponse getUserById(int Id) {

        return SerenityRest.given()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .pathParam("id",Id)
                .when()
                .get(EndPoints.USER_BY_ID)
                .then().log().all();
    }

    @Step("updating user detail")
    public ValidatableResponse updatingStore(int userId, String name, String email, String gender, String status){

        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);


        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("id", userId)
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .put(EndPoints.USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting user id")
    public ValidatableResponse deleteUser(int Id){
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .pathParam("id", Id)
                .when()
                .delete(EndPoints.USER_BY_ID)
                .then();
    }

    @Step("Getting user info")
    public ValidatableResponse getUser(int Id){
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .pathParam("id", Id)
                .when()
                .get(EndPoints.USER_BY_ID)
                .then();
    }

}
