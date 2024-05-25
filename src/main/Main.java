package main;

import main.dao.MenuItemDAO;
import main.dao.OrderDAO;
import main.dao.UserDAO;
import main.dao.VenueDAO;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        VenueDAO venueDAO = new VenueDAO();
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        OrderDAO orderDAO = new OrderDAO(); // Assuming you have OrderDAO implemented similarly

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
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setAddress(address);
                    userDAO.registerUser(user);
                    break;
                case 2:
                    System.out.print("Enter your email: ");
                    String userEmail = scanner.nextLine();
                    boolean authenticated = userDAO.authenticateUser(userEmail);
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
                    venue.setName(venueName);
                    venue.setAddress(venueAddress);
                    venue.setPhoneNumber(phoneNumber);
                    venueDAO.addVenue(venue);
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
                    menuItem.setVenueId(venueId);
                    menuItem.setName(itemName);
                    menuItem.setPrice(itemPrice);
                    menuItem.setDescription(itemDescription);
                    menuItemDAO.addMenuItem(menuItem);
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
                    Order order = new Order();
                    order.setUserId(userId);
                    order.setVenueId(orderVenueId);
                    order.setStatus("Pending");
                    int orderId = orderDAO.placeOrder(order); // Assuming placeOrder returns the generated order ID
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setItemId(itemId);
                    orderItem.setQuantity(quantity);
                    orderDAO.addOrderItem(orderItem);
                    break;
                case 6:
                    System.out.print("Enter order ID: ");
                    int viewOrderId = scanner.nextInt();
                    orderDAO.viewOrderStatus(viewOrderId);
                    break;
                case 7:
                    System.out.print("Enter order ID: ");
                    int updateOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new status (Pending/Delivered): ");
                    String status = scanner.nextLine();
                    orderDAO.updateOrderStatus(updateOrderId, status);
                    break;
                case 8:
                    List<Venue> venues = venueDAO.listVenues();
                    for (Venue v : venues) {
                        System.out.println(v);
                    }
                    break;
                case 9:
                    System.out.print("Enter restaurant ID: ");
                    int listVenueId = scanner.nextInt();
                    List<MenuItem> menuItems = menuItemDAO.listMenuItems(listVenueId);
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
