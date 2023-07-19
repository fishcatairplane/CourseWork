package UItests.UIObjects;

import UItests.LoginPage;
import UItests.UIObjects.Header;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class ProjectPage extends Header {
    @Step("Open Projects page")
    public LoginPage openProjectSummaryPage(){
        Selenide.open("/projects");
        return new LoginPage();
    }
}
