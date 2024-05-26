package main.service;

import main.dao.MenuItemDAO;
import main.dao.VenueDAO;
import main.model.MenuItem;
import main.model.Venue;

import java.util.List;

public class MenuItemService {
    private MenuItemDAO menuItemDAO = new MenuItemDAO();
    private VenueDAO venueDAO = new VenueDAO();

    public boolean addMenuItem(String venueName, String name, double price, String description) {
        if (venueDAO.venueExists(venueName)) {
            Venue venue = venueDAO.getVenueByName(venueName);
            MenuItem menuItem = new MenuItem();
            menuItem.setVenueId(venue.getVenueId());
            menuItem.setName(name);
            menuItem.setPrice(price);
            menuItem.setDescription(description);
            menuItemDAO.addMenuItem(menuItem);
            return true;
        } else {
            return false;
        }
    }

    public List<MenuItem> listMenuItems(int venueId) {
        return menuItemDAO.listMenuItems(venueId);
    }
}
