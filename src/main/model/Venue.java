package main.model;

public class Venue {
    private int venueId;
    private String name;
    private String address;
    private String phoneNumber;
    private int ownerId;

    public Venue() {
    }

    public Venue(int venueId, String name, String address, String phoneNumber, int ownerId) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ownerId = ownerId;
    }
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
}
