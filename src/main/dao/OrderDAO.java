package main.dao;

import main.model.Order;
import main.model.OrderItem;
import main.db.BdConnection;
import main.model.OrderStatus;

import java.sql.*;

public class OrderDAO {
    public int placeOrder(Order order) {
        String sql = "INSERT INTO Orders (UserId, VenueId, OrderStatusId) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getVenueId());
            pstmt.setInt(3, order.getStatus().ordinal() + 1);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public boolean addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItems (OrderId, ItemId, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getItemId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void viewOrderStatus(int userId) {
        String sql = "SELECT OrderStatusId FROM Orders WHERE UserId = ? order by Date desc limit 1";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String status = rs.getInt("OrderStatusId") == 1 ? "PENDING" : "DONE";
                    System.out.println("Order Status: " + status);
                } else {
                    System.out.println("Order not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE Orders SET OrderStatusId = ? WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, OrderStatus.valueOf(status).ordinal() + 1);
            pstmt.setInt(2, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
