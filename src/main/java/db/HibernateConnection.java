package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    private static HibernateConnection hibernateConnection;
    private final Configuration configuration;

    private HibernateConnection() {
        configuration = new Configuration();
    }

    public static HibernateConnection getHibernateConnection() {
        if (hibernateConnection == null) {
            hibernateConnection = new HibernateConnection();
        }
        return hibernateConnection;
    }

    public SessionFactory getSessionFactory() {
        configuration.addAnnotatedClass(model.Customer.class);
        configuration.addAnnotatedClass(model.Item.class);
        configuration.addAnnotatedClass(model.Orders.class);
        configuration.configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }
}
