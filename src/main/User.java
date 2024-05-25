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

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}


