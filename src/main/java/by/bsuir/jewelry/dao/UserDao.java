package by.bsuir.jewelry.dao;


import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    public boolean addUser(User user) {
        return EntityManager.saveEntity(user);
    }

    public boolean updateUser(User user) {
        return EntityManager.updateEntity(user);
    }

    public boolean deleteUser(int id) {
        return EntityManager.deleteEntity(id, User.class);
    }

    public User findUserById(Long id) {
        return EntityManager.find(User.class, id, "id");
    }


    public User findUserByLogin(String login) {
        return EntityManager.find(User.class, login, "login");
    }

    public boolean hasAnyUser() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(u) FROM User u", Long.class);
            return query.uniqueResult() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<User> showUsers() {
        return (List<User>) HibernateUtil.getSessionFactory().openSession().createQuery("From User").list();
    }


}
