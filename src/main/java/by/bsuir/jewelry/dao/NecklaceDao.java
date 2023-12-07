package by.bsuir.jewelry.dao;

import by.bsuir.jewelry.models.Necklace;
import by.bsuir.jewelry.models.User;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class NecklaceDao {
    public boolean addNecklace(Necklace necklace) {
        return EntityManager.saveEntity(necklace);
    }
    public boolean deleteNecklace(int id) {
        return EntityManager.deleteEntity(id, Necklace.class);
    }
    public List<Necklace> findNecklacesByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Necklace> query = session.createQuery("FROM Necklace WHERE user.id = :userId", Necklace.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Necklace findNecklaceById(int id) {
        return EntityManager.find(Necklace.class, id, "id");
    }


}
