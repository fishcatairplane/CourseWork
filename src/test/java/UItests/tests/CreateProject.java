package UItests.tests;

import API.ProjectApi;
import API.UserApi;
import UItests.UIObjects.OverviewPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;
import static envs.Randomizer.getRandomInt;
import static API.UserRoles.ADMIN;

public class CreateProject extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectName = "AutoTest"+getRandomInt();
    UserApi userApi = new UserApi();
    ProjectApi projectApi = new ProjectApi();

    @BeforeMethod
    @Step("Setup data")
    public void prepareDataForTest() {
        userId = userApi.createUser(USERNAME+getRandomInt(),PASSWORD);
        isRoleUpdated = userApi.updateUserRoleRequiredParam(userId, ADMIN.getRoles());
        username = userApi.getUserInfo(userId).getResult().getUsername();
    }

    @Test
    @Step("Create project from the Header")
    public void addNewProjectFromHeaderTest() {
        new OverviewPage()
                .openUserDashboardPage()
                .loginByUser(username, PASSWORD)
                .addNewProjectHeader()
                .createNewProject(projectName);

        String newProjectTitle = Selenide.title();
        Assertions.assertNotEquals(projectName, newProjectTitle);
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup data")
    public void removeDataAfterTest(){
        projectApi.removeProject(projectApi.getProjectPropertiesByName(projectName).getResult().getId());
        userApi.removeUser(userId);
    }
}
