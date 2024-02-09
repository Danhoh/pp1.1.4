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
                    CREATE TABLE Users (
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

        String sqlQuery = "DROP TABLE Users;";

        session.createSQLQuery(sqlQuery).executeUpdate();

        transaction.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
