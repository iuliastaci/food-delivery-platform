package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BdConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String USER = "root";
    private static final String PASSWORD = "Java#2024";

    private BdConnection() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }
}
