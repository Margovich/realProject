package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (PreparedStatement preparedStatement = this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS users (ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(45) NOT NULL, LASTNAME VARCHAR(45) NOT NULL, AGE INT NOT NULL, PRIMARY KEY (ID));")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        String tableName = "users";

        try (PreparedStatement dropStatement = this.connection.prepareStatement("DROP TABLE IF EXISTS " + tableName + ";")) {
            dropStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO users(NAME, LASTNAME, AGE) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, lastname, (byte) age);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void cleanUsersTable() {

        try (PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
