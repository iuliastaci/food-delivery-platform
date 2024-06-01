package main.model;

import main.service.AuditService;

public class FoodDeliveryService {
    public void addVenue(Venue venue) {
        logAction("addVenue");
    }

    public void placeOrder(Order order) {
        logAction("placeOrder");
    }

    private void logAction(String action) {
        AuditService.logAction(action);
    }

    public void viewAuditLog() {
        AuditService.viewAuditLog();
    }


}

