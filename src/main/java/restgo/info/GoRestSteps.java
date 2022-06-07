package restgo.info;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import restgo.constants.EndPoint;
import restgo.model.Pojo;

import java.util.HashMap;

public class GoRestSteps {
       @Step("Creating User with name: {0}, gender: {1}, email: {2}, status: {3}")
        public ValidatableResponse createUser(String name, String gender,
                                              String email, String status) {

            Pojo pojo = new Pojo();
            pojo.setName(name);
            pojo.setGender(gender);
            pojo.setEmail(email);
            pojo.setStatus(status);
            return SerenityRest.given().log().all()
                    .contentType(ContentType.JSON)
                    .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                    .body(pojo)
                    .when()
                    .post(EndPoint.CREATE_NEW_USER)
                    .then();
        }
        @Step("Getting User Id: {0}")
    public HashMap<String, Object> getUserById(int userId){
            HashMap<String, Object> userMap = SerenityRest.given().log().all()
                   .when()
                   .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                   .pathParam("userID",userId)
                   .get(EndPoint.UPDATE_USER_BY_ID)
                   .then()
                    .statusCode(200)
                    .extract().path("");
            return userMap;
        }
        @Step("Update user with userID: {0}, name: {1}, gender: {2}, email: {3}, status: {4}")
public ValidatableResponse udateNewUser(int userId,String name, String gender,
                                        String email, String status){
           Pojo pojo=new Pojo();
           pojo.setName(name);
           pojo.setGender(gender);
           pojo.setEmail(email);
           pojo.setStatus(status);
           return SerenityRest.given().log().all()
                   .header("ContentType","application/json")
                   .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                   .body(pojo)
                   .pathParam("userID",userId)
                   .when()
                   .put(EndPoint.UPDATE_USER_BY_ID)
                   .then();
        }
        @Step("Delete user by ID: {0}")
    public ValidatableResponse deleteUserById(int userId){
           return SerenityRest.given().log().all()
                   .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                   .pathParam("userID",userId)
                   .when()
                   .delete(EndPoint.DELETE_USER_BY_ID)
                   .then();

        }
        @Step("Get the user By Id: {0}")
    public ValidatableResponse getUserById(){
                    return SerenityRest.given().log().all()
                   .header("Authorization", "Bearer 75c987adac5bd1820e58fb137137e2474501428068d1ec0dd68ae05bcff564c9")
                   //.pathParam("userID",userId)
                   .when()
                   .get(EndPoint.GET_ALL_USERS)
                   .then();
        }
    }


