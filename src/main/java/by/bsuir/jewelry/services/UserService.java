package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.UserDao;
import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.util.EntityManager;

public class UserService {
    /**
     * The UserDao instance for interacting with user data in the database.
     */
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    /**
     * Retrieves a user by their login from the database.
     *
     * @param login The login of the user to retrieve.
     * @return The User object corresponding to the provided login.
     */
    public User getUserByLogin(String login) {
        return EntityManager.find(User.class, login, "login");
    }

    /**
     * Adds a new user to the database using the UserDao.
     *
     * @param newUser The User object representing the new user to be added.
     */
    public void addUser(User newUser) {
        userDao.addUser(newUser);
    }

    /**
     * Retrieves the role for user registration based on the presence of existing users in the database.
     *
     * @return The role to be assigned during user registration ("user" if users exist, "admin" otherwise).
     */
    public String getRegistrationRole() {
        return userDao.hasAnyUser() ? "user" : "admin";
    }

    /**
     * Retrieves a user by their ID from the database using the UserDao.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object corresponding to the provided ID.
     */
    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }
}
