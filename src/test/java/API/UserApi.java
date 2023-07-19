package API;
import API.objects.UserCreate;
import API.objects.UserUpdate;
import API.objects.UserId;
import API.objects.UserProperty;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import static API.UserRoles.ADMIN;
import static envs.PropertyEnv.API_TOKEN;
import static envs.PropertyEnv.API_USERNAME;
import static API.methods.User.*;


public class UserApi extends BaseApi{
    public String createUser(String username, String password){

        UserCreate args = UserCreate.builder()
                .username(username)
                .password(password)
                .role(ADMIN.getRoles())
                .build();

        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        BodyResult bodyResult = response.as(BodyResult.class);
        return bodyResult.getResult().toString();
    }

    public boolean removeUser(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(REMOVE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return (boolean) response.as(BodyResult.class).getResult();
    }

    public BodyResult<UserProperty> getUserInfo(String userId){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(new UserId(Integer.valueOf(userId)))
                .method(GET_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        return response.as(new TypeRef<>() {});
    }

    public boolean updateUserRoleRequiredParam(String userId, String userRole){
        UserUpdate args = UserUpdate.builder()
                .id(Integer.valueOf(userId))
                .role(userRole)
                .build();
        return createUserBody(args);
    }

    private boolean createUserBody(UserUpdate args){
        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(UPDATE_USER)
                .build();
        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        return (boolean) response.as(BodyResult.class).getResult();
    }
}

