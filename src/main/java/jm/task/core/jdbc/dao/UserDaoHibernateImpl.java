package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {

//    В некоторых методах не отлавливаешь и не обрабатываешь потенциальные исключения,

//    Относительно комментария "// можно же опустить проверку, т.к. и без нее все равно произойдет ролбек?"
//    - произойдёт, но смысл проверки в том, чтобы не получить NPE при вызове session.remove(user);
//    (по хорошему надо выбрасывать соответствующее исключение, которое отражает контекст,
//    но об этом позже). Из метода getAllUsers возвращаешь null, что также чревато
//    последующим NPE, вместо этого возвращай пустую коллекцию. В методе cleanUsersTable
//    так же нужно делать ролбэк, так как DELETE относится к DML операторам

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        try (Session session = Util.openSession()) {
            transaction = session.beginTransaction();
            String sqlQuery = """
                    CREATE TABLE IF NOT EXISTS Users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        lastName VARCHAR(50) NOT NULL,
                        age TINYINT NOT NULL
                    );""";

            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        try (Session session = Util.openSession()) {
            transaction = session.beginTransaction();
            String sqlQuery = "DROP TABLE IF EXISTS Users;";

            session.createSQLQuery(sqlQuery).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = Util.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (HibernateException e1) {
            System.out.println(e1.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);

            if (user != null) {
                session.remove(user);
                transaction.commit();
            } else {
                transaction.rollback();
            }

        } catch (HibernateException e) {
            System.out.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM User";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
