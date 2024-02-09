package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import jm.task.core.jdbc.model.User;

import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String url = "jdbc:mysql://localhost:3306/pp";
    private final String user = "root";
    private final String password = "1234567890";

    private Properties properties = new Properties();

    private StandardServiceRegistry registry = null;

    private MetadataSources sources = null;

    private SessionFactory sessionFactory = null;

    private Session session = null;
    public Util() {
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, url);
        properties.put(Environment.USER, user);
        properties.put(Environment.PASS, password);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.SHOW_SQL, "true");

        registry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();

        sources = new MetadataSources(registry);
        sources.addAnnotatedClass(User.class); // Добавляем класс сущности

        sessionFactory = sources.buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }



    public Session getSession() {
        return session;
    }
}
