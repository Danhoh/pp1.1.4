package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    final static private String url = "jdbc:mysql://localhost:3306/sakila";
    final static private String user = "root";
    final static private String password = "1234567890";
    final static private Connection connection = null;
    
    static {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Успешное подключение к базе данных MySQL!");
            // Здесь можно выполнять SQL-запросы и работать с результатами
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных MySQL: " + e.getMessage());

        }
    }
    public static void connect() {
        
    }
}
