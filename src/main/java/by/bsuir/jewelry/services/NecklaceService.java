package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.NecklaceDao;
import by.bsuir.jewelry.models.Necklace;

import java.util.List;

public class NecklaceService {

    /**
     * The NecklaceDao instance for interacting with necklace data in the database.
     */
    private final NecklaceDao necklaceDao;

    /**
     * Constructs a NecklaceService instance and initializes the NecklaceDao.
     */
    public NecklaceService() {
        this.necklaceDao = new NecklaceDao();
    }

    /**
     * Retrieves a list of necklaces associated with a specific user from the database.
     *
     * @param userId The ID of the user whose necklaces are to be retrieved.
     * @return A list of Necklace objects associated with the provided user ID.
     */
    public List<Necklace> getNecklaceByUser(Long userId) {
        return necklaceDao.findNecklacesByUserId(userId);
    }

    /**
     * Adds a new necklace to the database using the NecklaceDao.
     *
     * @param necklace The Necklace object representing the new necklace to be added.
     */
    public void addNecklace(Necklace necklace) {
        necklaceDao.addNecklace(necklace);
    }

    /**
     * Retrieves a list of all necklaces from the database using the NecklaceDao.
     *
     * @return A list of all Necklace objects in the database.
     */
    public List<Necklace> getAllNecklace() {
        return necklaceDao.getAllNecklace();
    }

    /**
     * Deletes a necklace from the database using the NecklaceDao.
     *
     * @param deleteNecklace The Necklace object representing the necklace to be deleted.
     */
    public void deleteNecklace(Necklace deleteNecklace) {
        necklaceDao.deleteNecklace(deleteNecklace);
    }
}
