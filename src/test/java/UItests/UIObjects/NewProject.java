package UItests.UIObjects;

import UItests.UIObjects.Header;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import UItests.UIObjects.OverviewPage;

import static com.codeborne.selenide.Selenide.*;

public class NewProject {
    private String projectNameAdded;
    public SelenideElement newProjectModal() {
        return $("#modal-content");
    }
    public SelenideElement projectNameField() {
        return $x("//*[@id='form-name']");
    }
    public SelenideElement projectIdentifierField() {
        return $x("//*[@id='form-identifier']");
    }
    public SelenideElement projectNameErrorAlert() {
        return $(".form-errors");
    }
    public SelenideElement columnTaskLimitCheckbox() {
        return $x("//*[@name='per_swimlane_task_limits']");
    }
    public SelenideElement taskLimitField() {
        return $("input[name='task_limit']");
    }
    public SelenideElement duplicateProjectDropdown() {
        return $x("//select");
    }
    public SelenideElement duplicateFromOption(String projectNameAdded) {
        return $x("//option[contains(text(),'"+projectNameAdded+"')]");
    }
    public SelenideElement saveBtn() {
        return $("button[type='submit']");
    }
    public SelenideElement cancelBtn() {
        return $(".form-actions>a");
    }

    @Step("User fills in a project name and clicks on Save button")
    public Summary createNewProject(String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        saveBtn().shouldBe(Condition.visible).click();
        return new Summary();
    }
    @Step("User creates a new project with a project name and identifier")
    public Summary createNewProject(String projectName, String identifier){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        projectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        saveBtn().shouldBe(Condition.visible).click();
        return new Summary();
    }

    @Step("User creates a new project with a project name, identifier and task limit")
    public Summary createNewProject(String projectName, String identifier, String taskLimit){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        projectIdentifierField().shouldBe(Condition.visible).sendKeys(identifier);
        taskLimitField().shouldBe(Condition.visible).sendKeys(taskLimit);
        saveBtn().shouldBe(Condition.visible).click();
        return new Summary();
    }

    @Step("User creates a new project with a project name and duplicating objects from the existing project")
    public Summary createNewProjectDuplicated (String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        duplicateProjectDropdown().shouldBe(Condition.visible);
        duplicateFromOption(projectNameAdded).click();
        saveBtn().shouldBe(Condition.visible).click();
        return new Summary();
    }

    @Step("User fills in a project name and clicks on Cancel button")
    public Header cancelCreateNewProject(String projectName){
        projectNameField().shouldBe(Condition.visible).sendKeys(projectName);
        cancelBtn().shouldBe(Condition.visible).click();
        return new Header();
    }
}
