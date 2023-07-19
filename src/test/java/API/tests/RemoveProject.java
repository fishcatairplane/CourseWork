package API.tests;
import API.ProjectApi;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static envs.Randomizer.getRandomInt;
public class RemoveProject {
    private static final String PROJECTNAME = "project";
    private String projectId;
    private Integer ownerId=getRandomInt();
    ProjectApi projectApi = new ProjectApi();
    @BeforeMethod
    @Step("Setup test data")
    public void prepareDataForTest() {
        projectId = projectApi.createProjectRequiredParam(PROJECTNAME+getRandomInt());
        System.out.println(projectId);
    }

    @Test
    @Step("API test checks positive case of removal a project")
    public void removeProjectApi(){
        Boolean isDeleted = projectApi.removeProject(projectId);
        Assert.assertTrue(isDeleted.booleanValue(), "Project is not deleted");
    }
}
