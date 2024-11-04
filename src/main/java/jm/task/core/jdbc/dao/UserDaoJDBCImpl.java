package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = this.getConnection();

    public UserDaoJDBCImpl() throws SQLException {
    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n  ID INT NOT NULL AUTO_INCREMENT,\n  NAME VARCHAR(45) NOT NULL,\n  LASTNAME VARCHAR(45) NOT NULL,\n  AGE INT NOT NULL,\n  PRIMARY KEY (ID)\n);";

        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String tableName = "users";
        String tableCheck = "SHOW TABLES LIKE '" + tableName + "';";
        String sql = "DROP TABLE " + tableName + ";";

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(tableCheck);
            if (resultSet.next()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = this.connection.createStatement();
            try {
                statement.executeUpdate("INSERT INTO users(NAME, LASTNAME, AGE) VALUES('" + name + "', '" + lastName + "', '" + age + "')");
            } catch (Throwable t) {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Throwable t2) {
                        t.addSuppressed(t2);
                    }
                }

                throw t;
            }

            if (statement != null) {
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                User user = new User(name, lastname, (byte)age);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            String sql = "DELETE FROM users";
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
