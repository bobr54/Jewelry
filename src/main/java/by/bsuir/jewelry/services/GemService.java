package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.GemDao;
import by.bsuir.jewelry.models.Gem;

import java.util.List;


public class GemService {

    /**
     * The GemDao instance for interacting with gem data in the database.
     */
    private final GemDao gemDao;

    /**
     * Constructs a GemService instance and initializes the GemDao.
     */
    public GemService() {
        this.gemDao = new GemDao();
    }

    /**
     * Retrieves a list of all gems from the database using the GemDao.
     *
     * @return A list of Gem objects representing all gems in the database.
     */
    public List<Gem> getAllGems() {
        return gemDao.getAllGems();
    }

    /**
     * Adds a new gem to the database using the GemDao.
     *
     * @param gem The Gem object representing the new gem to be added.
     */
    public void addGem(Gem gem) {
        gemDao.addGem(gem);
    }

    /**
     * Retrieves a specific gem from the database based on its ID using the GemDao.
     *
     * @param id The ID of the gem to be retrieved.
     * @return The Gem object representing the gem with the provided ID.
     */
    public Gem getGemById(int id) {
        return gemDao.findGemById(id);
    }

    /**
     * Updates an existing gem in the database using the GemDao.
     *
     * @param newGem The Gem object representing the updated gem information.
     */
    public void updateGem(Gem newGem) {
        gemDao.updateGem(newGem);
    }

    /**
     * Deletes a gem from the database based on its ID using the GemDao.
     *
     * @param id The ID of the gem to be deleted.
     */
    public void deleteGem(int id) {
        gemDao.deleteGem(id);
    }
}
