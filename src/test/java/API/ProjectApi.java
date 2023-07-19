package API;

import API.objects.ProjectCreate;
import API.objects.ProjectId;
import API.objects.ProjectName;
import API.objects.ProjectProperties;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static API.methods.Project.*;
import static envs.PropertyEnv.API_TOKEN;
import static envs.PropertyEnv.API_USERNAME;

public class ProjectApi extends BaseApi {
    public String createProjectRequiredParam(String projectName) {
        ProjectCreate args = ProjectCreate.builder()
                .name(projectName)
                .build();
        return createProjectBody(args);
    }

    private String createProjectBody(ProjectCreate args) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public static boolean removeProject(String projectId) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectId(Integer.valueOf(projectId)))
                .method(REMOVE_PROJECT)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public BodyResult<ProjectProperties> getProjectPropertiesByName(String projectName) {
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new ProjectName(projectName))
                .method(GET_PROJECT_BY_NAME)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<>() {});
    }
}