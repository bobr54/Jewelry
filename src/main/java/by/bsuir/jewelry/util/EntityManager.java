package by.bsuir.jewelry.util;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The {@code EntityManager} class provides static methods for common database operations
 * such as saving, updating, and deleting entities. It also includes a method for finding
 * entities based on specific criteria.
 *
 * <p>This class serves as a utility to simplify database interactions using Hibernate.
 * It encapsulates common operations and handles exceptions internally to provide a
 * convenient interface for working with entities in a Hibernate-based application.
 *
 * <p>Methods:
 * <ul>
 *   <li>{@link #saveEntity(Object)}: Saves a new entity to the database.</li>
 *   <li>{@link #updateEntity(Object)}: Updates an existing entity in the database.</li>
 *   <li>{@link #deleteEntity(int, Class)}: Deletes an entity from the database based on its ID.</li>
 *   <li>{@link #find(Class, Object, String)}: Finds an entity in the database based on a specific criteria field.</li>
 * </ul>
 *
 * <p>The class relies on Hibernate for session management and database operations.
 * It utilizes HibernateUtil to obtain the session factory.
 *
 * <p>Usage example:
 * <pre>{@code
 * // Save a new entity
 * MyEntity entity = new MyEntity();
 * EntityManager.saveEntity(entity);
 *
 * // Update an existing entity
 * entity.setName("Updated Name");
 * EntityManager.updateEntity(entity);
 *
 * // Delete an entity by ID
 * EntityManager.deleteEntity(1, MyEntity.class);
 *
 * // Find an entity by a specific criteria field
 * MyEntity foundEntity = EntityManager.find(MyEntity.class, "criteriaValue", "fieldName");
 * }</pre>
 */
public class EntityManager {

    /**
     * Saves a new entity to the database.
     *
     * @param entity The entity to be saved.
     * @return {@code true} if the entity is successfully saved, {@code false} otherwise.
     */
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

    /**
     * Updates an existing entity in the database.
     *
     * @param entity The entity to be updated.
     * @return {@code true} if the entity is successfully updated, {@code false} otherwise.
     */
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

    /**
     * Deletes an entity from the database based on its ID.
     *
     * @param id    The ID of the entity to be deleted.
     * @param clazz The class type of the entity.
     * @param <T>   The type of the entity.
     * @return {@code true} if the entity is successfully deleted, {@code false} otherwise.
     */
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

    /**
     * Finds an entity in the database based on a specific criteria field.
     *
     * @param clazz             The class type of the entity.
     * @param criteriaField    The value of the criteria field to search for.
     * @param criteriaFieldName The name of the criteria field in the entity.
     * @param <T>               The type of the entity.
     * @return The found entity or {@code null} if no entity is found.
     */
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

