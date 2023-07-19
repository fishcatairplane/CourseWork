package UItests;

import UItests.UIObjects.Header;
import UItests.UIObjects.BoardPage;
import UItests.UIObjects.TaskSummary;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class LoginPage {
    public final SelenideElement userNameField = $x("//*[@id=\"form-username\"]");
    public final SelenideElement passwordField = $x("//*[@id='form-password']");
    public final SelenideElement signInBtn = $x("//*[@type='submit']");
    public final SelenideElement credsErrorAlert = $(".alert");

    @Step("Open the Login page")
    public LoginPage openLoginPage(){
        Selenide.open("");
        return new LoginPage();
    }

    @Step("User logs in the app with login {0} and password {1}")
    public Header loginByUser(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new Header();
    }
    @Step("User logs in the app")
    public BoardPage loginTask(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new BoardPage();
    }
    @Step("User logs in the app")
    public TaskSummary loginTaskSummary(String login, String password){
        getUserNameField().shouldBe(Condition.visible).sendKeys(login);
        getPasswordField().shouldBe(Condition.visible).sendKeys(password);
        getSignInBtn().shouldBe(Condition.visible).click();
        return new TaskSummary();
    }
}
