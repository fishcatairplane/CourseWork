package UItests.tests;

import API.ProjectApi;
import API.TaskApi;
import API.UserApi;
import UItests.UIObjects.TaskSummary;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static API.UserRoles.ADMIN;
import static envs.Randomizer.getRandomInt;

public class TaskTests extends BaseTest {
    private final static String USERNAME = "user";
    private final static String PASSWORD = "123456";
    private String userId;
    private String username;
    private Boolean isRoleUpdated;
    private String projectId;
    private String projectName = "Test";
    private String taskName = "Task"+getRandomInt();
    private String commentText = "This is the comment "+getRandomInt();
    private String taskId;
    UserApi userApi = new UserApi();
    ProjectApi projectApi = new ProjectApi();
    TaskApi taskApi = new TaskApi();

    @BeforeMethod
    @Step("Setup data")
    public void prepareDataForTest() {
        userId = userApi.createUser(USERNAME+getRandomInt(),PASSWORD);
        isRoleUpdated = userApi.updateUserRoleRequiredParam(userId, ADMIN.getRoles());
        username = userApi.getUserInfo(userId).getResult().getUsername();
        projectId = projectApi.createProjectRequiredParam(projectName+getRandomInt());
        taskId=taskApi.createTaskRequiredParams(taskName+getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("Remove a task")
    public void removeTaskTest(){
        SelenideElement addedTask = new TaskSummary()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickRemoveItem()
                .taskId(taskId);
        addedTask.should(Condition.disappear);

    }


    @Test
    @Step("Close this task")
    public void closeTaskTest(){
        SelenideElement taskStatus = new TaskSummary()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickCloseThisTask()
                .taskStatusClosed().shouldBe(Condition.visible);
        taskStatus.shouldHave(Condition.text("close"));
    }

    @Test
    @Step("Add the first comment")
    public void addFirstCommentTest(){
        SelenideElement comment = new TaskSummary()
                .openTaskSummaryPage(Integer.valueOf(taskId))
                .loginTaskSummary(username, PASSWORD)
                .clickAddCommentItem()
                .createFirstComment(commentText)
                .commentText(commentText).shouldBe(Condition.visible);
        comment.shouldHave(Condition.exactText(commentText));


    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup data")
    public void removeDataAfterTest(){
        taskApi.removeTask(Integer.valueOf(taskId));
        projectApi.removeProject(projectId);
        userApi.removeUser(userId);
    }
}
