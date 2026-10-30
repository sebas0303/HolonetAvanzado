package ut4.persistencia.lab3;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Carga hibernate.cfg.xml desde el classpath (src/main/resources)
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Fallo crítico en la inicialización de SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Cierra los pools de conexiones y cachés al finalizar el programa
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
