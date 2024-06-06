package main.service;

import main.dao.VenueDAO;
import main.model.Venue;
import main.validator.VenueValidator;

import java.util.List;

public class VenueService {
    private static final VenueDAO venueDAO = new VenueDAO();
    private static final VenueValidator validator = new VenueValidator();

    public void addVenue(String name, String address, String phoneNumber, int ownerId) {
        Venue venue = new Venue();
        venue.setName(name);
        venue.setAddress(address);
        venue.setPhoneNumber(phoneNumber);
        venue.setOwnerId(ownerId);
        validator.validate(venue);
        venueDAO.add(venue);
    }

    public List<Venue> listVenues() {
        return venueDAO.getAll();
    }

    public boolean venueExists(String name) {
        return venueDAO.venueExists(name);
    }

    public Venue getVenueByName(String name) {
        return venueDAO.getVenueByName(name);
    }
}
