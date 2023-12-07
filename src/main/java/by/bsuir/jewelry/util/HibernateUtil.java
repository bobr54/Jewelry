package by.bsuir.jewelry.util;

import by.bsuir.jewelry.models.Gem;
import by.bsuir.jewelry.models.Necklace;
import by.bsuir.jewelry.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .addAnnotatedClass(Gem.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Necklace.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
