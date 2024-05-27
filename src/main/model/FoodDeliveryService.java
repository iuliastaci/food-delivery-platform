package main.model;

import main.service.AuditLog;

public class FoodDeliveryService {
    public void addVenue(Venue venue) {
        logAction("addVenue");
    }

    public void placeOrder(Order order) {
        logAction("placeOrder");
    }

    private void logAction(String action) {
        AuditLog.logAction(action);
    }

    public void viewAuditLog() {
        AuditLog.viewAuditLog();
    }


}

