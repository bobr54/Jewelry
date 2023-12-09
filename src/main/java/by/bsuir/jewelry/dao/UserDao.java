package by.bsuir.jewelry.dao;


import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The UserDao class provides methods for interacting with the User entity in the database.
 * It encapsulates common database operations, such as adding, updating, and deleting users,
 * as well as finding users by their unique identifiers or login names.
 */
public class UserDao {

    /**
     * Adds a new user to the database.
     *
     * @param user The User object to be added.
     * @return True if the user was successfully added, false otherwise.
     */
    public boolean addUser(User user) {
        return EntityManager.saveEntity(user);
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user The User object to be updated.
     * @return True if the user was successfully updated, false otherwise.
     */
    public boolean updateUser(User user) {
        return EntityManager.updateEntity(user);
    }

    /**
     * Deletes a user from the database based on the specified user ID.
     *
     * @param id The ID of the user to be deleted.
     * @return True if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(int id) {
        return EntityManager.deleteEntity(id, User.class);
    }

    /**
     * Finds a user in the database based on the specified user ID.
     *
     * @param id The ID of the user to be found.
     * @return The User object if found, or null if not found.
     */
    public User findUserById(Long id) {
        return EntityManager.find(User.class, id, "id");
    }

    /**
     * Finds a user in the database based on the specified login name.
     *
     * @param login The login name of the user to be found.
     * @return The User object if found, or null if not found.
     */
    public User findUserByLogin(String login) {
        return EntityManager.find(User.class, login, "login");
    }

    /**
     * Checks if there is at least one user in the database.
     *
     * @return True if there is at least one user, false otherwise.
     */
    public boolean hasAnyUser() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(u) FROM User u", Long.class);
            return query.uniqueResult() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a list of all users in the database.
     *
     * @return A list of User objects representing all users in the database.
     */
    public List<User> showUsers() {
        return (List<User>) HibernateUtil.getSessionFactory().openSession().createQuery("From User").list();
    }


}
