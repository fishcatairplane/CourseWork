package UItests.tests;

import API.ProjectApi;
import API.UserApi;
import UItests.UIObjects.BoardPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static API.UserRoles.ADMIN;
import static envs.Randomizer.getRandomInt;

public class CreateTask extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "Test";
    private String taskName = "Task"+getRandomInt();
    UserApi userApi = new UserApi();
    ProjectApi projectApi = new ProjectApi();

    @BeforeMethod
    @Step("Setup data")
    public void prepareDataForTest() {
        userId = userApi.createUser(USERNAME+getRandomInt(),PASSWORD);
        isRoleUpdated = userApi.updateUserRoleRequiredParam(userId, ADMIN.getRoles());
        username = userApi.getUserInfo(userId).getResult().getUsername();
        projectId = projectApi.createProjectRequiredParam(projectName+getRandomInt());
    }
    @Test
    @Step("Create task from the dropdown")
    public void addNewTaskFromDropdownTest(){
        SelenideElement addedTask = new BoardPage()
                .openProjectBoard(Integer.valueOf(projectId))
                .loginTask(username, PASSWORD)
                .addNewTaskDropdown()
                .createNewTaskFromDropdown(taskName)
                .addedTask(taskName).shouldBe(Condition.visible);
        addedTask.shouldHave(Condition.exactText(taskName));

    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup data")
    public void removeDataAfterTest(){
        projectApi.removeProject(projectId);
        userApi.removeUser(userId);
    }
}
