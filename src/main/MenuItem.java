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
}
