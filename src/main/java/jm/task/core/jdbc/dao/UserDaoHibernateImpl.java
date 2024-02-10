package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import jm.task.core.jdbc.util.Util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    private Session session = null;

    public UserDaoHibernateImpl() {
        Util util = new Util();
        session = util.getSession();
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();

        String sqlQuery = """
                    CREATE TABLE IF NOT EXISTS Users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        lastName VARCHAR(50) NOT NULL,
                        age TINYINT NOT NULL
                    );""";

        session.createSQLQuery(sqlQuery).executeUpdate();

        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();

        String sqlQuery = "DROP TABLE IF EXISTS Users;";

        session.createSQLQuery(sqlQuery).executeUpdate();

        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);

        Transaction transaction = session.beginTransaction();

        session.save(user);

        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.remove(user);

        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        return session.createQuery("FROM User", User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();

        String hql = "DELETE FROM User";
        int rowsAffected = session.createQuery(hql).executeUpdate();

        transaction.commit();
    }
}
