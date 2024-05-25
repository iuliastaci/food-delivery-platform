package main;

public class User {
    private int userId;
    private String name;
    private String email;
    private String address;

    // Getters, setters, and constructors

    public static void registerUser(String name, String email, String address) {
        // Implementation to register user
        // Database logic here
        AuditLog.logAction("registerUser");
    }

    public static boolean authenticateUser(String email) {
        // Implementation to authenticate user
        // Database logic here
        AuditLog.logAction("authenticateUser");
        return true; // Or false if authentication fails
    }
}


