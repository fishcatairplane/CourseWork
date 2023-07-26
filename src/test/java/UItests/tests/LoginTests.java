package UItests.tests;

import UItests.UIObjects.UserMenuDD;
import com.codeborne.selenide.WebDriverRunner;
import UItests.LoginDP;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import UItests.LoginPage;

import static envs.PropertyEnv.BASE_URL;

public class LoginTests extends BaseTest {
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "admin";
    private final static String URL = BASE_URL+"/login";

    @Test(dataProvider = "adminCredentials", dataProviderClass = LoginDP.class)
    @Step("Positive and Negative Login as Admin with username {0} and password {1}")
    public void loginTest(String username, String password, String url) {
        new LoginPage()
                .openLoginPage()
                .loginByUser(username, password);
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url
                , "The logIn was not successful");
    }

    @Test
    @Step("Login/LogOut as Admin")
    public void logoutTest() {
        new LoginPage()
                .openLoginPage()
                .loginByUser(USERNAME, PASSWORD);
        new UserMenuDD()
                .logOut();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), URL
                , "The logout was not successful");
    }
}
