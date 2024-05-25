package main.dao;

import main.MenuItem;
import main.db.BdConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    public void addMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItems (venue_id, name, price, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuItem.getVenueId());
            pstmt.setString(2, menuItem.getName());
            pstmt.setDouble(3, menuItem.getPrice());
            pstmt.setString(4, menuItem.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MenuItem> listMenuItems(int venueId) {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM MenuItems WHERE venue_id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venueId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setItemId(rs.getInt("item_id"));
                    menuItem.setVenueId(rs.getInt("venue_id"));
                    menuItem.setName(rs.getString("name"));
                    menuItem.setPrice(rs.getDouble("price"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItems.add(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}
