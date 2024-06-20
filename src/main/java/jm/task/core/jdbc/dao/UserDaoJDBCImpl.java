package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE IF NOT EXISTS USERS(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (25),last_name VARCHAR (30), age INT)";
        try (Connection connection = Util.getConnection();) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("я не прав");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS USERS";
        try (Connection connection = Util.getConnection();) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Я ошибся");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, last_name, age) VALUES(?, ?, ?);";
        try (Connection connection = Util.getConnection();) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("исправь");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Переделай пожалуйста");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        Statement statement = null;
        try (Connection connection = Util.getConnection();) {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE TABLE USERS";
        try (Connection connection = Util.getConnection();) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            try {
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Я не прав");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
