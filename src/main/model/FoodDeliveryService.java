package main.model;

public class FoodDeliveryService {
    // Methods to handle the operations
    public void addVenue(Venue venue) {
        // Implementation
        logAction("addVenue");
    }

    public void placeOrder(Order order) {
        // Implementation
        logAction("placeOrder");
    }

    private void logAction(String action) {
        AuditLog.logAction(action);
    }

    public void viewAuditLog() {
        AuditLog.viewAuditLog();
    }


}

