package main;

import java.util.ArrayList;
import java.util.List;

public class Venue {
    private int venueId;
    private String name;
    private String address;
    private String phoneNumber;

    // Getters, setters, and constructors

    public void addVenue(String name, String address, String phoneNumber) {
        // Implementation to add venue to database
        // Database logic here
        AuditLog.logAction("addVenue");
    }

    public static List<Venue> listVenues() {
        // Initialize the venues list
        List<Venue> venues = new ArrayList<>();
        // Database logic here to populate venues
        AuditLog.logAction("listVenues");
        return venues;
    }
}
