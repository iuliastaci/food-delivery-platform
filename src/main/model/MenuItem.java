package main.model;

import java.util.Objects;

public class MenuItem {
    private int itemId;
    private int venueId;
    private String name;
    private double price;
    private String description;

    public MenuItem() {
    }
    public MenuItem(int itemId, int venueId, String name, double price, String description) {
    this.itemId = itemId;
    this.venueId = venueId;
    this.name = name;
    this.price = price;
    this.description = description;
}

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId=" + itemId +
                ", venueId=" + venueId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem menuItem)) return false;
        return getItemId() == menuItem.getItemId() && getVenueId() == menuItem.getVenueId() && Double.compare(getPrice(), menuItem.getPrice()) == 0 && Objects.equals(getName(), menuItem.getName()) && Objects.equals(getDescription(), menuItem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getVenueId(), getName(), getPrice(), getDescription());
    }
}
