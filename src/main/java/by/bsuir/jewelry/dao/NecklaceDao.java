package by.bsuir.jewelry.dao;

import by.bsuir.jewelry.models.Necklace;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * The NecklaceDao class provides methods for interacting with the Necklace entity in the database.
 * It encapsulates common database operations, such as adding, deleting, and finding necklaces,
 * as well as retrieving all necklaces or necklaces associated with a specific user.
 */
public class NecklaceDao {

    /**
     * Adds a new necklace to the database.
     *
     * @param necklace The Necklace object to be added.
     * @return True if the necklace was successfully added, false otherwise.
     */
    public boolean addNecklace(Necklace necklace) {
        return EntityManager.saveEntity(necklace);
    }

    /**
     * Deletes a necklace from the database based on the specified necklace ID.
     *
     * @param id The ID of the necklace to be deleted.
     * @return True if the necklace was successfully deleted, false otherwise.
     */
    public boolean deleteNecklace(int id) {
        return EntityManager.deleteEntity(id, Necklace.class);
    }

    /**
     * Finds necklaces in the database based on the specified user ID.
     *
     * @param userId The ID of the user for whom necklaces are to be found.
     * @return A list of Necklace objects associated with the specified user.
     */
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

    /**
     * Finds a necklace in the database based on the specified necklace ID.
     *
     * @param id The ID of the necklace to be found.
     * @return The Necklace object if found, or null if not found.
     */
    public Necklace findNecklaceById(int id) {
        return EntityManager.find(Necklace.class, id, "id");
    }

    /**
     * Retrieves a list of all necklaces in the database.
     *
     * @return A list of Necklace objects representing all necklaces in the database.
     */
    public List<Necklace> getAllNecklace() {
        return (List<Necklace>) HibernateUtil.getSessionFactory().openSession().createQuery("From Necklace").list();
    }

    /**
     * Deletes a necklace and its associated gem quantities from the database.
     *
     * @param deleteNecklace The Necklace object to be deleted.
     */
    public void deleteNecklace(Necklace deleteNecklace) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Delete entries from the necklace_gems table associated with the necklace
            session.createNativeQuery("DELETE FROM necklace_gems WHERE necklace_id = :necklaceId")
                    .setParameter("necklaceId", deleteNecklace.getId())
                    .executeUpdate();

            // Delete the necklace itself
            Query query = session.createQuery("DELETE FROM Necklace WHERE id = :necklaceId");
            query.setParameter("necklaceId", deleteNecklace.getId());
            query.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
