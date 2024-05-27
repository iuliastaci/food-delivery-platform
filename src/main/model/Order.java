package main.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private int venueId;
    private Date orderDate;
    private String status;

    public Order() {
    }

    public Order(int orderId, int userId, int venueId, Date orderDate, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}


