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

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venue venue)) return false;
        return getVenueId() == venue.getVenueId() && getName().equals(venue.getName()) && getAddress().equals(venue.getAddress()) && getPhoneNumber().equals(venue.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getVenueId(), getName(), getAddress(), getPhoneNumber());
    }
}
