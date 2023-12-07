package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.GemDao;
import by.bsuir.jewelry.models.Gem;

import java.util.List;


public class GemService {

    private final GemDao gemDao;

    public GemService() {
        this.gemDao = new GemDao();
    }

    public List<Gem> getAllGems(){
        return gemDao.getAllGems();
    }

    public void addGem(Gem gem) {
        gemDao.addGem(gem);
    }
    public Gem getGemById(int id){
        return gemDao.findGemById(id);
    }
    public void updateGem(Gem newGem){
        gemDao.updateGem(newGem);
    }
    public void deleteGem(int id){
        gemDao.deleteGem(id);
    }
}
