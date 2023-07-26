package API.tests;

import API.ProjectApi;
import API.TaskApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static envs.Randomizer.getRandomInt;

public class RemoveTask {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    private Boolean isDeleted;
    ProjectApi projectApi = new ProjectApi();
    TaskApi taskApi = new TaskApi();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApi.createProjectRequiredParam(PROJECTNAME+getRandomInt());
        taskId = taskApi.createTaskRequiredParams(TASK_TITLE+getRandomInt(), Integer.valueOf(projectId));
    }

    @Test
    @Step("API test checks positive case of removal a task")
    public void removeTaskApiTest(){
        isDeleted = taskApi.removeTask(Integer.valueOf(taskId));
        Assert.assertTrue(isDeleted, "Task is not removed");
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        projectApi.removeProject(projectId);
    }
}
