package API.tests;

import API.UserApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static envs.Randomizer.getRandomInt;

public class RemoveUser {
    private static final String USERNAME = "user";
    private static final String PASSWORD = "123456";
    UserApi userApi = new UserApi();
    private String userId;
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        userId = userApi.createUser(USERNAME+getRandomInt(),PASSWORD);
        System.out.println(userId);
    }

    @Test
    @Step("API test checks positive case of removal a user")
    public void removeUserApi(){
        Boolean isDeleted = userApi.removeUser(userId);
        Assert.assertTrue(isDeleted.booleanValue(), "User is not deleted");
    }
}
