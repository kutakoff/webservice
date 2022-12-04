package com.kutakoff.demo.repos;

import com.kutakoff.demo.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private static Connection connection;

    static {
        String URL = "jdbc:postgresql://localhost:5432/first_db";
        String USERNAME = "postgres";
        String PASSWORD = "123123145";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Вернуть всех пользователей
     *
     * @return список всех пользователей
     */
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String SQL = "select " +
                    " p.\"id\"," +
                    " p.\"name\"" +
                    " from \"Person\" p";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
