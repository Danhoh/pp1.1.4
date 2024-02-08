package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
//        dao.createUsersTable();
//        dao.dropUsersTable();
        dao.saveUser("Daniil", "Khokhlov", (byte) 23);
    }
}
