package main;

import main.AuditLog;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private int itemId;
    private int venueId;
    private String name;
    private double price;
    private String description;

    // Getters, setters, and constructors

    public void addMenuItem(int venueId, String name, double price, String description) {
        // Implementation to add menu item
        // Database logic here
        AuditLog.logAction("addMenuItem");
    }

    public static List<MenuItem> listMenuItems(int venueId) {
        // Initialize the menuItems list
        List<MenuItem> menuItems = new ArrayList<>();

        // Implementation to list menu items
        // Database logic here to populate menuItems

        AuditLog.logAction("listMenuItems");
        return menuItems;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
