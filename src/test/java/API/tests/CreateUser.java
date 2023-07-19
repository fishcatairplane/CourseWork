package API.tests;

import API.UserApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static envs.Randomizer.getRandomInt;

public class CreateUser{
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    UserApi userApi = new UserApi();
    private String userId;
    @Test
    @Step("API test checks positive case of creation a user")
    public void createUserApi(){
        userId = userApi.createUser(USERNAME+getRandomInt(), PASSWORD);
        Assert.assertNotEquals(userId, false, "User is not created");
        System.out.println(userId);
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        userApi.removeUser(userId);
    }

}
