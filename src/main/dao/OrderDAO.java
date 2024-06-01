package main.dao;

import main.model.Order;
import main.model.OrderItem;
import main.db.BdConnection;
import main.model.OrderStatus;
import main.model.Role;
import main.service.AuditService;
import main.service.OrderService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderDAO implements GenericDAO<Order>{
    private final AuditService auditService = new AuditService();
    @Override
    public void add(Order order) {
        String sql = "INSERT INTO Orders (UserId, VenueId, OrderStatusId) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getVenueId());
            pstmt.setInt(3, order.getStatus().ordinal() + 1);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
                auditService.logTransaction("PLACE_ORDER", "OrderId=" + order.getOrderId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order read(int id){
        String sql = "SELECT * FROM Orders WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    Order order = new Order();
                    order.setOrderId(rs.getInt("Id"));
                    order.setUserId(rs.getInt("UserId"));
                    order.setVenueId(rs.getInt("VenueId"));
                    order.setStatus(OrderStatus.values()[rs.getInt("OrderStatusId") - 1]);
                    return order;
                }
            }
            auditService.logTransaction("GET_ORDER", "OrderId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Order order){
        String sql = "UPDATE Orders SET UserID = ?, VenueId = ?, OrderStatusId = ? WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getVenueId());
            pstmt.setInt(3, order.getStatus().ordinal() + 1);
            pstmt.setInt(4, order.getOrderId());
            pstmt.executeUpdate();
            auditService.logTransaction("UPDATE_ORDER", "OrderId=" + order.getOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Orders WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            auditService.logTransaction("DELETE_ORDER", "OrderId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll(){
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try(Connection conn = BdConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("Id"));
                order.setUserId(rs.getInt("UserId"));
                order.setVenueId(rs.getInt("VenueId"));
                order.setStatus(OrderStatus.values()[rs.getInt("OrderStatusId") - 1]);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public int placeOrder(Order order) {
        add(order);
        return order.getOrderId();
    }
    public boolean addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItems (OrderId, ItemId, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getItemId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.executeUpdate();
            auditService.logTransaction("ADD_ORDER_ITEM", "OrderId=" + orderItem.getOrderId());
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
            auditService.logTransaction("UPDATE_ORDER_STATUS", "OrderId=" + orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> listOrders(String venueName) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE VenueId = (SELECT VenueId FROM Venues WHERE Name = ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venueName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("Id"));
                    order.setUserId(rs.getInt("UserId"));
                    order.setVenueId(rs.getInt("VenueId"));
                    order.setStatus(OrderStatus.values()[rs.getInt("OrderStatusId") - 1]);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<String> getOrderItems(int orderId) {
        List<String> itemNames = new ArrayList<>();
        String sql = "SELECT MenuItems.Name FROM OrderItems " +
                "JOIN MenuItems ON OrderItems.ItemId = MenuItems.Id " +
                "WHERE OrderItems.OrderId = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    itemNames.add(rs.getString("Name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemNames;
    }
}
