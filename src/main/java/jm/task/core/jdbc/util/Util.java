package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class Util {
        public Connection getConnection() {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/preproject", "root", "Vlad322");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
