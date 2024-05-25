package main;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodDeliveryService service = new FoodDeliveryService();

        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Register User");
            System.out.println("2. Authenticate User");
            System.out.println("3. Add Restaurant");
            System.out.println("4. Add Menu Item");
            System.out.println("5. Place Order");
            System.out.println("6. View Order Status");
            System.out.println("7. Update Order Status");
            System.out.println("8. List Restaurants");
            System.out.println("9. List Menu Items");
            System.out.println("10. View Audit Log");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    String address = scanner.nextLine();
                    User.registerUser(name, email, address);
                    break;
                case 2:
                    System.out.print("Enter your email: ");
                    String userEmail = scanner.nextLine();
                    boolean authenticated = User.authenticateUser(userEmail);
                    System.out.println("Authentication " + (authenticated ? "successful" : "failed"));
                    break;
                case 3:
                    System.out.print("Enter restaurant name: ");
                    String venueName = scanner.nextLine();
                    System.out.print("Enter restaurant address: ");
                    String venueAddress = scanner.nextLine();
                    System.out.print("Enter restaurant phone number: ");
                    String phoneNumber = scanner.nextLine();
                    Venue venue = new Venue();
                    venue.addVenue(venueName, venueAddress, phoneNumber);
                    break;
                case 4:
                    System.out.print("Enter restaurant ID: ");
                    int venueId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter item description: ");
                    String itemDescription = scanner.nextLine();
                    MenuItem menuItem = new MenuItem();
                    menuItem.addMenuItem(venueId, itemName, itemPrice, itemDescription);
                    break;
                case 5:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter restaurant ID: ");
                    int orderVenueId = scanner.nextInt();
                    System.out.print("Enter item ID: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    Order.placeOrder(userId, orderVenueId, itemId, quantity);
                    break;
                case 6:
                    System.out.print("Enter order ID: ");
                    int orderId = scanner.nextInt();
                    Order.viewOrderStatus(orderId);
                    break;
                case 7:
                    System.out.print("Enter order ID: ");
                    int updateOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new status (Pending/Delivered): ");
                    String status = scanner.nextLine();
                    Order.updateOrderStatus(updateOrderId, status);
                    break;
                case 8:
                    List<Venue> venues = Venue.listVenues();
                    for (Venue v : venues) {
                        System.out.println(v);
                    }
                    break;
                case 9:
                    System.out.print("Enter restaurant ID: ");
                    int listVenueId = scanner.nextInt();
                    List<MenuItem> menuItems = MenuItem.listMenuItems(listVenueId);
                    for (MenuItem mi : menuItems) {
                        System.out.println(mi);
                    }
                    break;
                case 10:
                    AuditLog.viewAuditLog();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


