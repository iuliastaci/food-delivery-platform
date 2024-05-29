package main.service;

import main.dao.MenuItemDAO;
import main.dao.VenueDAO;
import main.model.MenuItem;
import main.model.Venue;

import java.util.List;

public class MenuItemService {
    private MenuItemDAO menuItemDAO = new MenuItemDAO();

    public boolean addMenuItem(String venueName, String name, double price, String description) {
        VenueDAO venueDAO = new VenueDAO();
        Venue venue = venueDAO.getVenueByName(venueName);
        if (venue != null) {
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
