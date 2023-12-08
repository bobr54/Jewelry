package by.bsuir.jewelry.services;

import by.bsuir.jewelry.dao.GemDao;
import by.bsuir.jewelry.dao.NecklaceDao;
import by.bsuir.jewelry.models.Necklace;

import java.util.List;

public class NecklaceService {
    private final NecklaceDao necklaceDao;

    public NecklaceService() {
        this.necklaceDao = new NecklaceDao();
    }

    public List<Necklace> getNecklaceByUser(Long userId){
       return necklaceDao.findNecklacesByUserId(userId);
    }
    public void addNecklace(Necklace necklace){
        necklaceDao.addNecklace(necklace);
    }
    //в этом методе надо будет тестировать deleteNecklace;
    public  List<Necklace> getAllNecklace(){
        return necklaceDao.getAllNecklace();
    }

    public void deleteNecklace(Necklace deleteNecklace){
        necklaceDao.deleteNecklace(deleteNecklace);
    }
}
