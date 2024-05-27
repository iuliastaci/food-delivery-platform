package main.model;

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
}
