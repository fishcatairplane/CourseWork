package UItests.UIObjects;

import UItests.LoginPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import UItests.UIObjects.NewProject;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public class Header {
    private final SelenideElement logo = $(".logo");
    private final SelenideElement title = $(".title");
    private final SelenideElement notificationBtn = $(".notification");
    private final SelenideElement addProjectBtn = $("[class='fa fa-plus fa-fw']");
    private final SelenideElement userMenuBtn = $(".avatar-letter");

    @Step("User clicks on Add project dropdown and selects Add New Project")
    public NewProject addNewProjectHeader(){
        getAddProjectBtn().shouldBe(Condition.visible).click();
        NewProject selectOpt = new ProjectMenuDD()
                .clickAddNewProject();
        return new NewProject();
    }
}
