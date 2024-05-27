package main.model;

public class Venue {
    private int venueId;
    private String name;
    private String address;
    private String phoneNumber;
    public Venue() {
    }

    public Venue(int venueId, String name, String address, String phoneNumber) {
        this.venueId = venueId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVenueId() {
        return venueId;
    }

}
