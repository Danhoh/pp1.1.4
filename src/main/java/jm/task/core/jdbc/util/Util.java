package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String url = "jdbc:mysql://localhost:3306/pp";
    private final String user = "root";
    private final String password = "1234567890";
    private Connection connection = null;

    public Util() {
    }

    public void initConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
