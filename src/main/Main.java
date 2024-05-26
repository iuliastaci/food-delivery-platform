package main;

import main.model.User;
import main.service.UserService;
import main.service.OrderService;
import main.service.VenueService;
import main.service.MenuItemService;

import java.util.Scanner;

public class Main {
    private static User authenticatedUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        VenueService venueService = new VenueService();
        MenuItemService menuItemService = new MenuItemService();

        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Register User");
            System.out.println("2. Authenticate User");
            System.out.println("3. Place Order");
            System.out.println("4. Add Restaurant");
            System.out.println("5. Add Menu Item");
            System.out.println("6. View Order Status");
            System.out.println("7. Update Order Status");
            System.out.println("8. List Restaurants");
            System.out.println("9. List Menu Items");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner, userService);
                    break;
                case 2:
                    authenticateUser(scanner, userService);
                    break;
                case 3:
                    placeOrder(scanner, orderService);
                    break;
                case 4:
                    addRestaurant(scanner, venueService);
                    break;
                case 5:
                    addMenuItem(scanner, menuItemService, venueService);
                    break;
                case 6:
                    viewOrderStatus(scanner, orderService);
                    break;
                case 7:
                    updateOrderStatus(scanner, orderService);
                    break;
                case 8:
                    listRestaurants(venueService);
                    break;
                case 9:
                    listMenuItems(scanner, menuItemService);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPassword(password);
        userService.registerUser(user);
    }

    private static void authenticateUser(Scanner scanner, UserService userService) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        boolean authenticated = userService.authenticateUser(email, password);
        if (authenticated) {
            authenticatedUser = userService.getUserByEmail(email);
            System.out.println("Authentication successful.");
        } else {
            authenticatedUser = null;
            System.out.println("Authentication failed.");
        }
    }

    private static void placeOrder(Scanner scanner, OrderService orderService) {
        if (authenticatedUser != null) {
            System.out.print("Enter restaurant ID: ");
            int venueId = scanner.nextInt();
            System.out.print("Enter item ID: ");
            int itemId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            orderService.placeOrder(authenticatedUser.getUserId(), venueId, itemId, quantity);
        } else {
            System.out.println("You need to authenticate first.");
        }
    }

    private static void addRestaurant(Scanner scanner, VenueService venueService) {
        if (authenticatedUser != null) {
            System.out.print("Enter restaurant name: ");
            String name = scanner.nextLine();
            System.out.print("Enter restaurant address: ");
            String address = scanner.nextLine();
            System.out.print("Enter restaurant phone number: ");
            String phoneNumber = scanner.nextLine();
            venueService.addVenue(name, address, phoneNumber);
        } else {
            System.out.println("You need to authenticate first.");
        }
    }

    private static void addMenuItem(Scanner scanner, MenuItemService menuItemService, VenueService venueService) {
        if (authenticatedUser != null) {
            System.out.print("Enter restaurant name: ");
            String venueName = scanner.nextLine();

            // Verify if the venue exists
            if (!venueService.venueExists(venueName)) {
                System.out.println("Restaurant not found: " + venueName);
                return; // Return immediately to main menu
            }

            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            System.out.print("Enter item price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter item description: ");
            String description = scanner.nextLine();
            boolean success = menuItemService.addMenuItem(venueName, name, price, description);
            if (success) {
                System.out.println("Menu item added successfully.");
            } else {
                System.out.println("Failed to add menu item for restaurant: " + venueName);
            }
        } else {
            System.out.println("You need to authenticate first.");
        }
    }

    private static void viewOrderStatus(Scanner scanner, OrderService orderService) {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        orderService.viewOrderStatus(orderId);
    }

    private static void updateOrderStatus(Scanner scanner, OrderService orderService) {
        if (authenticatedUser != null) {
            System.out.print("Enter order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new status (Pending/Delivered): ");
            String status = scanner.nextLine();
            orderService.updateOrderStatus(orderId, status);
        } else {
            System.out.println("You need to authenticate first.");
        }
    }

    private static void listRestaurants(VenueService venueService) {
        venueService.listVenues().forEach(System.out::println);
    }

    private static void listMenuItems(Scanner scanner, MenuItemService menuItemService) {
        System.out.print("Enter restaurant ID: ");
        int venueId = scanner.nextInt();
        menuItemService.listMenuItems(venueId).forEach(System.out::println);
    }
}
