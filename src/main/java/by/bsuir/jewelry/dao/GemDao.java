package by.bsuir.jewelry.dao;

import by.bsuir.jewelry.models.Gem;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;

import java.util.List;
/**
 * The GemDao class provides methods for interacting with the Gem entity in the database.
 * It encapsulates common database operations, such as adding, updating, deleting, and finding gems,
 * as well as retrieving a list of all gems in the database.
 */
public class GemDao {

    /**
     * Adds a new gem to the database.
     *
     * @param gem The Gem object to be added.
     * @return True if the gem was successfully added, false otherwise.
     */
    public boolean addGem(Gem gem) {
        return EntityManager.saveEntity(gem);
    }

    /**
     * Updates an existing gem in the database.
     *
     * @param gem The Gem object to be updated.
     * @return True if the gem was successfully updated, false otherwise.
     */
    public boolean updateGem(Gem gem) {
        return EntityManager.updateEntity(gem);
    }

    /**
     * Deletes a gem from the database based on the specified gem ID.
     *
     * @param id The ID of the gem to be deleted.
     * @return True if the gem was successfully deleted, false otherwise.
     */
    public boolean deleteGem(int id) {
        return EntityManager.deleteEntity(id, Gem.class);
    }

    /**
     * Finds a gem in the database based on the specified gem ID.
     *
     * @param id The ID of the gem to be found.
     * @return The Gem object if found, or null if not found.
     */
    public Gem findGemById(int id) {
        return EntityManager.find(Gem.class, id, "id");
    }

    /**
     * Retrieves a list of all gems in the database.
     *
     * @return A list of Gem objects representing all gems in the database.
     */
    public List<Gem> getAllGems() {
        return (List<Gem>) HibernateUtil.getSessionFactory().openSession().createQuery("From Gem").list();
    }
}

