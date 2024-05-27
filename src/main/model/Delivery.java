package main.model;

import java.util.Date;
import java.util.Objects;

public class Delivery {
    private int deliveryId;
    private int orderId;
    private Date deliveryDate;
    private String status;

    public Delivery() {
    }

    public Delivery(int deliveryId, int orderId, Date deliveryDate, String status) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }


    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", orderId=" + orderId +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery delivery)) return false;
        return getDeliveryId() == delivery.getDeliveryId() && getOrderId() == delivery.getOrderId() && Objects.equals(getDeliveryDate(), delivery.getDeliveryDate()) && Objects.equals(getStatus(), delivery.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeliveryId(), getOrderId(), getDeliveryDate(), getStatus());
    }
}

