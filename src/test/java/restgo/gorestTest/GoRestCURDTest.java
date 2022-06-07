package restgo.gorestTest;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import restgo.info.GoRestSteps;
import restgo.testBase.TestBase;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)

public class GoRestCURDTest extends TestBase {
    static String name = "Tenali Ramakrishna11";
    static String gender = "female";
    static String email = "tenali11.ramakrishna1@gmail.com";
    static String status = "active";
    static int userId;

    @Steps
    GoRestSteps goRestSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = goRestSteps.createUser(name, gender, email, status);
        response.log().all().statusCode(201);

    }

    @Title("This will get the user with Id")
    @Test
    public void test002() {
        HashMap<String, ?> userMap = goRestSteps.getUserById(userId);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println("userMap: " + userMap);
    }
    @Title("Update the user information and verify updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        goRestSteps.udateNewUser(userId, name, gender, email, status).log().all().statusCode(200);
        HashMap<String, Object> userMap = goRestSteps.getUserById(userId);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test004() {
        goRestSteps.deleteUserById(userId).statusCode(204);


    }


}
