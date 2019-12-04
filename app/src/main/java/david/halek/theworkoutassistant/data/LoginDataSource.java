package david.halek.theworkoutassistant.data;

import david.halek.theworkoutassistant.data.model.LoggedInUser;

import java.io.IOException;

import static david.halek.theworkoutassistant.Database.ConnectionClass.validateLogin;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            int userId = validateLogin(username, password);
            if (userId >= 0) {
                LoggedInUser user = new LoggedInUser(userId, username);
                return new Result.Success<>(user);
            }
            else return new Result.Error(new IOException("Invalid Credentials"));
//            LoggedInUser fakeUser =
//                    new LoggedInUser(9999,
//
//                            "Jane Doe");
//            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
