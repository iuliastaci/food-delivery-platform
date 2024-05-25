package main.dao;

import main.User;
import main.db.BdConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    public void registerUser(User user) {
        String sql = "INSERT INTO Users (name, email, address) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getAddress());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String email) {
        // Implement the logic to authenticate user
        // Example: Check if the email exists in the database
        return true; // Replace with actual logic
    }
}
