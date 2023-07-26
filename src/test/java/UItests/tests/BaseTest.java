package UItests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static envs.PropertyEnv.BASE_URL;

public class BaseTest {
    @BeforeMethod
    @Step("Open browser window with size 1280x800")
    public void setUp(){
        String browser = System.getProperty("browser", "Chrome");
        String headless = System.getProperty("headless", "true");
        Configuration.browser = browser;
        Configuration.browserSize = "1280x800";
        Configuration.baseUrl=BASE_URL;
        if(headless.equals("true")){
            Configuration.headless = true;
        }
    }

    @AfterMethod
    @Step("Close browser")
    public void cleanUp(){
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}


