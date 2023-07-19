package UItests.UIObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import UItests.UIObjects.NewProject;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
@Getter
public class ProjectMenuDD extends Header{
    private final SelenideElement projectMenuDropdown = $(".dropdown-submenu-open");
    private final SelenideElement addNewProjectMenuItem = $(".dropdown-submenu-open>li:nth-of-type(1)");
    private final SelenideElement addNewPersonalProjMenuItem = $(".dropdown-submenu-open>li:nth-of-type(2)");

    @Step("User clicks on Add new project dropdown menu item")
    public NewProject clickAddNewProject(){
        getAddNewProjectMenuItem().shouldBe(Condition.visible).click();
        return new NewProject();
    }
}
