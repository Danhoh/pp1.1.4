package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private String url = "jdbc:mysql://localhost:3306/pp";
    private String user = "root";
    private String password = "1234567890";
    private Connection connection = null;

    public Util() {
    }

    public Util(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void initConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Успешное подключение к базе данных MySQL!");
        // Здесь можно выполнять SQL-запросы и работать с результатами
    }

    public Connection getConnection() {
        return connection;
    }
}
