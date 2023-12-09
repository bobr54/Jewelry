package by.bsuir.jewelry.auth;

import by.bsuir.jewelry.exceptions.RegisterException;
import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.services.UserService;

import java.sql.SQLException;
/**
 * The {@code Authorisation} class provides methods for user registration and authentication.
 * It interacts with the {@link UserService} to manage user-related operations.
 */
public class Authorisation {

    /**
     * Registers a new user with the provided login, password, and user role.
     *
     * @param login    The login username of the user.
     * @param password The password for the user account.
     * @param user     The role of the user, either "user" or "admin".
     * @return {@code true} if the registration is successful, {@code false} otherwise.
     * @throws SQLException If an SQL exception occurs during user registration.
     */
    public static boolean registerUser(String login, String password, String user) throws SQLException, RegisterException {
        UserService userService = new UserService();
        User userObj = userService.getUserByLogin(login);

        if (userObj != null) {
            throw new RegisterException("User with login '" + login + "' already exists.");
        }

        String role = user.equals("user") ? "user" : "admin";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(login, hashedPassword, role);
        userService.addUser(newUser);

        System.out.println("User with login '" + login + "' has been added.");
        return true;
    }

    /**
     * Authenticates a user with the provided login and password.
     *
     * @param login    The login username of the user.
     * @param password The password for the user account.
     * @return An {@link AuthResult} object containing authentication results.
     */
    public static AuthResult authenticateUser(String login, String password) {
        UserService userService = new UserService();
        User userObj = userService.getUserByLogin(login);

        if (userObj == null) {
            System.out.println("User with login '" + login + "' not found.");
            return new AuthResult(-1L, false, "user");
        }

        Long userId = userObj.getId();
        String role = userObj.getRole();
        String hashedPassword = userObj.getPassword();
        boolean isAuthenticated = BCrypt.checkpw(password, hashedPassword);

        return new AuthResult(userId, isAuthenticated, role);
    }
}