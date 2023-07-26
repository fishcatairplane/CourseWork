package API;

import org.testng.annotations.DataProvider;

public class UserCredDP {
    @DataProvider(name="negativeUserCredentials")
    public static Object [][] userCredentialsDataProvider(){
        return new Object[][]{
                {"", "admin"},
                {"", ""},
                {"admin", "123456"}
        };
    }
}
