package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.UserDao;
import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.util.EntityManager;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User getUserByLogin(String login) {
        return EntityManager.find(User.class, login, "login");
    }

    public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    public String getRegistrationRole(){
        return userDao.hasAnyUser() ? "user" : "admin";
    }

    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }

}
