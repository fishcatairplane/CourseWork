package UItests;

import org.testng.annotations.DataProvider;

public class LoginDP {
    @DataProvider(name="adminCredentials")
    public static Object [][] credentialsDataProvider(){
        return new Object[][]{
                {"admin", "admin", "http://localhost/"},
                {"", "", "http://localhost/login"},
                {"admin", "", "http://localhost/login"},
                {"", "admin", "http://localhost/login"},
                {"admin", "admin+", "http://localhost/?controller=AuthController&action=check"}
        };
    }
}
