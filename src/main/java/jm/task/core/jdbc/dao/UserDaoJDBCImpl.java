package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jm.task.core.jdbc.util.Util;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;
    private Util util;
    public UserDaoJDBCImpl() {
        util = new Util();

        try {
            util.initConnection();
            connection = util.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void createUsersTable() {
        try {
            String sqlQuery = """
                    CREATE TABLE Users (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        lastName VARCHAR(50) NOT NULL,
                        age TINYINT NOT NULL
                    );""";

            statement.execute(sqlQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            String sqlQuery = "DROP TABLE Users;";
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sqlQuery = """
                DELETE FROM Users WHERE id = ?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        String sqlQuery = """
                DELETE FROM Users;
                """;
        try {
            statement.execute(sqlQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
