package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import jm.task.core.jdbc.model.User;

import java.util.Properties;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/pp";
    private static final String user = "root";
    private static final String password = "1234567890";
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = initSessionFactory();
    }

    private Util() {
    }

    private static SessionFactory initSessionFactory() {
        Properties properties = new Properties();

        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, url);
        properties.put(Environment.USER, user);
        properties.put(Environment.PASS, password);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(User.class);

        return sources.buildMetadata().buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
