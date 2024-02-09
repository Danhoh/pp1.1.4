package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
//        dao.createUsersTable();
//        dao.dropUsersTable();
//        dao.saveUser("Daniil", "Khokhlov", (byte) 23);
//        dao.saveUser("Kirill", "Golouhov", (byte) 23);
//        dao.saveUser("Ilya", "Ageev", (byte) 23);
//        dao.saveUser("Georgiy", "Bogoslavcev", (byte) 23);
//        dao.removeUserById(2);
//        dao.cleanUsersTable();
        System.out.println(dao.getAllUsers());
    }
}
