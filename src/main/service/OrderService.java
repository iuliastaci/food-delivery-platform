package main.service;

import main.dao.OrderDAO;
import main.model.Order;
import main.model.OrderItem;
import main.model.OrderStatus;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public int placeOrder(int userId, int venueId, Date orderDate) {
        Order order = new Order();
        order.setUserId(userId);
        order.setVenueId(venueId);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(orderDate);
        order.setStatus(OrderStatus.PENDING);
        int orderId = orderDAO.placeOrder(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderDAO.addOrderItem(orderItem);
        return orderId;
    }

    public boolean addOrderItem(int orderId, int menuItemId, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(menuItemId);
        orderItem.setQuantity(quantity);
        return orderDAO.addOrderItem(orderItem);
    }

    public void viewOrderStatus(int userId) {
        orderDAO.viewOrderStatus(userId);
    }

    public void updateOrderStatus(int orderId, String status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

}
