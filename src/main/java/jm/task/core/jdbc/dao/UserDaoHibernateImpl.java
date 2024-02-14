package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;
import java.lang.Exception;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.openSession()) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sqlQuery = "DROP TABLE IF EXISTS Users;";

            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = new User(name, lastName, age);

                session.save(user);
                transaction.commit();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);

                if (user != null) { // можно же опустить проверку, т.к. и без нее все равно произойдет ролбек?
                    session.remove(user);
                    transaction.commit();
                } else {
                    transaction.rollback();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

                if (transaction != null) {
                    transaction.rollback();
                }
            }

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "DELETE FROM User";

            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
