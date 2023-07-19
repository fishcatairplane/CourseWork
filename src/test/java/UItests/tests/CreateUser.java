package UItests.tests;

import API.UserApi;
import UItests.LoginPage;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static envs.PropertyEnv.BASE_URL;
import static envs.Randomizer.getRandomInt;

public class CreateUser extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private final static String URL = BASE_URL+"/";
    private String userId;
    private String username;
    UserApi userApi = new UserApi();

    @BeforeMethod
    @Step("Setup data")
    public void prepareDataForTest() {
        userId = userApi.createUser(USERNAME+getRandomInt(),PASSWORD);
        username = userApi.getUserInfo(userId).getResult().getUsername();
    }

    @Test
    @Step("Login as newly created user")
    public void CreateUser() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(username, PASSWORD);
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                URL, "The logIn was not successful");
    }

    @Test
    @Step("Negative Login as newly created user")
    public void loginCreatedUserNegativeTest() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(username, "");
        Assert.assertNotEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                URL, "The logIn was successful");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup data")
    public void removeDataAfterTest(){
        userApi.removeUser(userId);
    }
}
