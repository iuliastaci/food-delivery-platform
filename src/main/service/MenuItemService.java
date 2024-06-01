package main.service;

import main.dao.MenuItemDAO;
import main.dao.VenueDAO;
import main.model.MenuItem;
import main.model.Venue;

import java.awt.*;
import java.util.List;

public class MenuItemService {
    private final MenuItemDAO menuItemDAO = new MenuItemDAO();

    public boolean addMenuItem(String venueName, String name, double price, String description) {
        VenueDAO venueDAO = new VenueDAO();
        Venue venue = venueDAO.getVenueByName(venueName);
        if (venue != null) {
            MenuItem menuItem = new MenuItem();
            menuItem.setVenueId(venue.getVenueId());
            menuItem.setName(name);
            menuItem.setPrice(price);
            menuItem.setDescription(description);
            menuItemDAO.add(menuItem);
            return true;
        } else {
            return false;
        }
    }

    public List<MenuItem> listMenuItems(int venueId) {
        return menuItemDAO.listMenuItems(venueId);
    }

    public MenuItem getMenuItemById(int itemId) {
        return menuItemDAO.read(itemId);
    }

    public boolean updateMenuItem(int itemId, String venueName, String name, double price, String description) {
        MenuItem menuItem = menuItemDAO.read(itemId);
        if (menuItem != null) {
            VenueDAO venueDAO = new VenueDAO();
            Venue venue = venueDAO.getVenueByName(venueName);
            if(venue != null){
                menuItem.setName(name);
                menuItem.setPrice(price);
                menuItem.setDescription(description);
                menuItemDAO.update(menuItem);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMenuItem(int itemId) {
        MenuItem menuItem = menuItemDAO.read(itemId);
        if(menuItem != null){
            menuItemDAO.delete(itemId);
            return true;
        }
        return false;
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemDAO.getAll();
    }

    public String getItemNameById(int itemId) {
        return menuItemDAO.getItemNameById(itemId);
    }
}
