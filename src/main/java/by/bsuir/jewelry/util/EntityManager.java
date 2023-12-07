package by.bsuir.jewelry.util;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class EntityManager {

    public static boolean saveEntity(Object entity) {
        boolean isAdded = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            session.close();
            isAdded = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    public static boolean updateEntity(Object entity) {
        boolean isUpdated = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            session.close();
            isUpdated = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    public static <T> boolean deleteEntity(int id, Class<T> clazz) {
        boolean isDeleted = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            T entity = session.load(clazz, id);
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
            session.close();
            isDeleted = true;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    public static <F, T> T find(Class<T> clazz, F criteriaField, String criteriaFieldName) {
        T entity = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<T> companyRoot = criteriaQuery.from(clazz);
            criteriaQuery.select(companyRoot)
                    .where(criteriaBuilder.equal(companyRoot.get(criteriaFieldName), criteriaField));
            entity = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return entity;
    }
}

