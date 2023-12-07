package by.bsuir.jewelry.auth;

import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.services.UserService;

import java.sql.SQLException;

public class Authorisation {


    public static boolean registerUser(String login, String password, String user) throws SQLException {

        UserService userService = new UserService();
        User userObj = userService.getUserByLogin(login);
        if(userObj != null){
            System.out.println("Пользователь с логином '" + login + "' уже существует.");
            return false;
        }
        String role;
        if (user.equals("user"))
        {
            role = "user";
        }else{
            role ="admin";
        }


        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(login, hashedPassword, role);
        userService.addUser(newUser);
        System.out.println("Пользователь с логином '" + login + "' добавлен.");
        return true;
    }


    public static AuthResult authenticateUser(String login, String password) {
        UserService userService = new UserService();
        User userObj = userService.getUserByLogin(login);
        if(userObj == null){
            System.out.println("Пользователь с логином '" + login + "' не найден.");
            return new AuthResult(-1L, false, "user");
        }
        Long idUser = userObj.getId();
        String role = userObj.getRole();
        String hashedPassword = userObj.getPassword();
        boolean isAuthenticated = BCrypt.checkpw(password, hashedPassword);
        return new AuthResult(idUser, isAuthenticated, role);
    }

}