package main.model;

import java.util.Date;
import java.util.Objects;

public class Order {
    private int orderId;
    private int userId;
    private int venueId;
    private Date orderDate;
    private OrderStatus status;

    public Order() {
    }

    public Order(int orderId, int userId, int venueId, Date orderDate, OrderStatus status) {
        this.orderId = orderId;
        this.userId = userId;
        this.venueId = venueId;
        this.orderDate = orderDate;
        this.status = status;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", venueId=" + venueId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getOrderId() == order.getOrderId() && getUserId() == order.getUserId() && getVenueId() == order.getVenueId() && Objects.equals(getOrderDate(), order.getOrderDate()) && Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getUserId(), getVenueId(), getOrderDate(), getStatus());
    }
}


