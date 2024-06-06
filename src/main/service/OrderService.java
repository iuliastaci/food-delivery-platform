package main.service;

import main.dao.OrderDAO;
import main.exceptions.UnauthorizedException;
import main.model.Order;
import main.model.OrderItem;
import main.model.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    private static final OrderDAO orderDAO = new OrderDAO();

    public int placeOrder(int userId, int venueId, Date orderDate) {
        Order order = new Order();
        order.setUserId(userId);
        order.setVenueId(venueId);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(orderDate);
        int orderId = orderDAO.placeOrder(order);
        return orderId;
    }

    public boolean addOrderItem(int orderId, int menuItemId, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setItemId(menuItemId);
        orderItem.setQuantity(quantity);
        return orderDAO.addOrderItem(orderItem);
    }


    public Order viewOrder(int orderId){
        return orderDAO.read(orderId);
    }

    public void viewOrderStatus(int userId) {
        orderDAO.viewOrderStatus(userId);
    }

    public void deleteOrder(int orderId) {
        orderDAO.delete(orderId);
    }

    public List<Order> listOrders(String venueName, int userId) {
        List<Order> orders = orderDAO.listOrders(venueName);
        if(!orders.isEmpty()){
            orderDAO.checkOwner(venueName, userId);
        }
        return orders;
    }

    public static void updateOrderStatus(int orderId, String status) {
        orderDAO.updateOrderStatus(orderId, status);
    }

    public List<String> listOrderItems(int orderId) {
            return orderDAO.getOrderItems(orderId);
    }

    public ArrayList<Order> getOrdersStatus(int userId) {
        return orderDAO.getOrdersStatus(userId);
    }
}
