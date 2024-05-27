package main.service;

import main.dao.VenueDAO;
import main.model.Venue;

import java.util.List;

public class VenueService {
    private VenueDAO venueDAO = new VenueDAO();

    public void addVenue(String name, String address, String phoneNumber) {
        Venue venue = new Venue();
        venue.setName(name);
        venue.setAddress(address);
        venue.setPhoneNumber(phoneNumber);
        venueDAO.addVenue(venue);
    }

    public List<Venue> listVenues() {
        return venueDAO.listVenues();
    }

    public boolean venueExists(String name) {
        return venueDAO.venueExists(name);
    }
}
