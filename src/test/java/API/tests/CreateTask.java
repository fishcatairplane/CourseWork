package API.tests;
import API.ProjectApi;
import API.TaskApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static envs.Randomizer.getRandomInt;
public class CreateTask {
    private static final String PROJECTNAME = "project";
    private static final String TASK_TITLE = "task";
    private String projectId;
    private String taskId;
    ProjectApi projectApi = new ProjectApi();
    TaskApi taskApi = new TaskApi();

    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApi.createProjectRequiredParam(PROJECTNAME+getRandomInt());
    }

    @Test
    @Step("API test checks positive case of creation a task")
    public void createTaskApi(){
        taskId = taskApi.createTaskRequiredParams(TASK_TITLE+getRandomInt(), Integer.valueOf(projectId));
        Assert.assertNotEquals(taskId, false, "Task is not created");
        System.out.println(taskId);
    }


    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfterTest(){
        taskApi.removeTask(Integer.valueOf(taskId));
        projectApi.removeProject(projectId);
    }
}
