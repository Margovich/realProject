package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class Util {
        public static final String URL = "jdbc:mysql://localhost:3306/preproject";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "Vlad322";
        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

