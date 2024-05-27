package main.model;

import java.util.Objects;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int quantity;

    public OrderItem() {
    }
    public OrderItem(int orderItemId, int orderId, int itemId, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }
    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return getOrderItemId() == orderItem.getOrderItemId() && getOrderId() == orderItem.getOrderId() && getItemId() == orderItem.getItemId() && getQuantity() == orderItem.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderItemId(), getOrderId(), getItemId(), getQuantity());
    }
}
