package API.tests;

import API.ProjectApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static envs.Randomizer.getRandomInt;


public class CreateProject {
    private static final String PROJECTNAME = "project";
    private String projectId;
    ProjectApi projectApi = new ProjectApi();

    @Test
    @Step("API test create project")
    public void createProjectApi() {
        projectId = projectApi.createProjectRequiredParam(PROJECTNAME + getRandomInt());
        Assert.assertNotNull(projectId, "Project is not created");
        System.out.println(projectId);
    }

    @AfterMethod(alwaysRun = true)
    @Step("Cleanup test data")
    public void removeDataAfter(){
        ProjectApi.removeProject(projectId);
    }
}
