package by.bsuir.jewelry.dao;

import by.bsuir.jewelry.models.Gem;
import by.bsuir.jewelry.util.EntityManager;
import by.bsuir.jewelry.util.HibernateUtil;

import java.util.List;

public class GemDao {

    public boolean addGem(Gem gem) {
        return EntityManager.saveEntity(gem);
    }

    public boolean updateGem(Gem gem) {
        return EntityManager.updateEntity(gem);
    }

    public boolean deleteGem(int id) {
        return EntityManager.deleteEntity(id, Gem.class);
    }

    public Gem findGemById(int id) {
        return EntityManager.find(Gem.class, id, "id");
    }


   // public Gem findGemByLogin(String login) {
//        return EntityManager.find(Gem.class, login, "login");
//    }

    public List<Gem> getAllGems() {
        return (List<Gem>) HibernateUtil.getSessionFactory().openSession().createQuery("From Gem").list();
    }

}
