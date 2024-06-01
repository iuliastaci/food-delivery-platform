package main.dao;

import main.db.BdConnection;
import main.model.MenuItem;
import main.service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO implements GenericDAO<MenuItem> {
    private final AuditService auditService = new AuditService();
    public void add(MenuItem menuItem) {
        String sql = "INSERT INTO MenuItems (VenueId, name, price, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuItem.getVenueId());
            pstmt.setString(2, menuItem.getName());
            pstmt.setDouble(3, menuItem.getPrice());
            pstmt.setString(4, menuItem.getDescription());
            pstmt.executeUpdate();
            auditService.logTransaction("ADD_MENU_ITEM", "Name=" + menuItem.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem read(int id) {
        MenuItem menuItem = null;
        String sql = "SELECT * FROM MenuItems WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                menuItem = new MenuItem();
                menuItem.setItemId(rs.getInt("Id"));
                menuItem.setVenueId(rs.getInt("VenueId"));
                menuItem.setName(rs.getString("Name"));
                menuItem.setPrice(rs.getDouble("Price"));
                menuItem.setDescription(rs.getString("Description"));
            }
            auditService.logTransaction("GET_MENU_ITEM", "MenuItemId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItem;
    }

    @Override
    public void update(MenuItem menuItem) {
        String sql = "UPDATE MenuItems SET VenueId = ?, Name = ?, Price = ?, Description = ? WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuItem.getVenueId());
            pstmt.setString(2, menuItem.getName());
            pstmt.setDouble(3, menuItem.getPrice());
            pstmt.setString(4, menuItem.getDescription());
            pstmt.setInt(5, menuItem.getItemId());
            pstmt.executeUpdate();
            auditService.logTransaction("UPDATE_MENU_ITEM", "MenuItemId=" + menuItem.getItemId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM MenuItems WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            auditService.logTransaction("DELETE_MENU_ITEM", "MenuItemId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM MenuItems";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setItemId(rs.getInt("Id"));
                menuItem.setVenueId(rs.getInt("VenueId"));
                menuItem.setName(rs.getString("Name"));
                menuItem.setPrice(rs.getDouble("Price"));
                menuItem.setDescription(rs.getString("Description"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    //Method for listing menu items by venue id
    public List<MenuItem> listMenuItems(int venueId) {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM MenuItems WHERE VenueId = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, venueId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setItemId(rs.getInt("Id"));
                menuItem.setVenueId(rs.getInt("VenueId"));
                menuItem.setName(rs.getString("Name"));
                menuItem.setPrice(rs.getDouble("Price"));
                menuItem.setDescription(rs.getString("Description"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public String getItemNameById(int itemId) {
        String sql = "SELECT Name FROM MenuItems WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if item not found
    }
}
