package main.service;

import main.dao.OrderDAO;
import main.model.Order;
import main.model.OrderItem;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public void placeOrder(int userId, int venueId, int itemId, int quantity) {
        Order order = new Order();
        order.setUserId(userId);
        order.setVenueId(venueId);
        order.setStatus("Pending");
        int orderId = orderDAO.placeOrder(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(itemId);
        orderItem.setQuantity(quantity);
        orderDAO.addOrderItem(orderItem);
    }

    public void viewOrderStatus(int orderId) {
        orderDAO.viewOrderStatus(orderId);
    }

    public void updateOrderStatus(int orderId, String status) {
        orderDAO.updateOrderStatus(orderId, status);
    }
}
