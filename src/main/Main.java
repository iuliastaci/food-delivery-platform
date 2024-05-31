package main;

import main.model.*;
import main.service.UserService;
import main.service.OrderService;
import main.service.VenueService;
import main.service.MenuItemService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            System.out.println("3. Place Order (Client only)");
            System.out.println("4. Add Restaurant (Owner only)");
            System.out.println("5. Add Menu Item (Owner only)");
            System.out.println("6. View Order Status");
            System.out.println("7. Update Order Status");
            System.out.println("8. List Restaurants");
            System.out.println("9. List Menu Items");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner, userService);
                    break;
                case 2:
                    authenticateUser(scanner, userService);
                    break;
                case 3:
                    placeOrder(scanner, orderService, venueService, menuItemService);
                    break;
                case 4:
                    addRestaurant(scanner, venueService);
                    break;
                case 5:
                    addMenuItem(scanner, menuItemService, venueService);
                    break;
                case 6:
                    viewOrderStatus(orderService);
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
        System.out.print("Enter your role (Client/Owner): ");
        String role = scanner.nextLine();

        User user;
        if ("Client".equalsIgnoreCase(role)) {
            user = new Client();
        } else if ("Owner".equalsIgnoreCase(role)) {
            user = new Owner();
        } else {
            System.out.println("Invalid role. Registration failed.");
            return;
        }

        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPassword(password);

        boolean success = userService.registerUser(user);
        if (success) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
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

    private static void placeOrder(Scanner scanner, OrderService orderService, VenueService venueService, MenuItemService menuItemService) {
        if (authenticatedUser != null && authenticatedUser.getRole() == Role.CLIENT) {
            // List all restaurants
            List<Venue> venues = venueService.listVenues();
            if (venues.isEmpty()) {
                System.out.println("No restaurants available.");
                return;
            }

            System.out.println("Available restaurants:");
            for (Venue venue : venues) {
                System.out.println(venue.getName());
            }

            // Prompt user to enter restaurant name
            System.out.print("Enter the name of the restaurant: ");
            String venueName = scanner.nextLine();
            Venue venue = venueService.getVenueByName(venueName);

            if (venue == null) {
                System.out.println("Restaurant not found.");
                return;
            }

            // List menu items for the selected restaurant
            List<MenuItem> menuItems = menuItemService.listMenuItems(venue.getVenueId());
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available for this restaurant.");
                return;
            }

            // Create a list to store the order items and quantities
            List<MenuItem> orderItems = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();
            double totalPrice = 0.0;

            boolean ordering = true;
            while (ordering) {
                System.out.println("Available menu items:");
                for (MenuItem menuItem : menuItems) {
                    System.out.println(menuItem.getItemId() + ". " + menuItem.getName() + " - $" + menuItem.getPrice());
                }

                // Prompt user to place order
                System.out.print("Enter item ID: ");
                int itemId = scanner.nextInt();
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                MenuItem selectedItem = null;
                for (MenuItem menuItem : menuItems) {
                    if (menuItem.getItemId() == itemId) {
                        selectedItem = menuItem;
                        break;
                    }
                }

                if (selectedItem != null) {
                    orderItems.add(selectedItem);
                    quantities.add(quantity);
                    totalPrice += selectedItem.getPrice() * quantity;
                } else {
                    System.out.println("Invalid item ID.");
                }

                // Prompt user if they want to add more items
                System.out.print("Do you want anything else? (yes/no): ");
                String response = scanner.nextLine();
                if ("no".equalsIgnoreCase(response)) {
                    ordering = false;
                }
            }

            // Display the total price
            System.out.println("Total price: $" + totalPrice);

            // Place the order
            boolean success = true;
            int orderId = orderService.placeOrder(authenticatedUser.getUserId(), venue.getVenueId(), new Date());
            if (orderId != -1) {
                for (int i = 0; i < orderItems.size(); i++) {
                    MenuItem item = orderItems.get(i);
                    int quantity = quantities.get(i);
                    success &= orderService.addOrderItem(orderId, item.getItemId(), quantity);
                }

                if (success) {
                    System.out.println("Order placed successfully. Order ID: " + orderId);
                } else {
                    System.out.println("Failed to place order.");
                }
            } else {
                System.out.println("Failed to create order.");
            }
        } else {
            System.out.println("Only authenticated clients can place orders.");
        }
    }

    private static void addRestaurant(Scanner scanner, VenueService venueService) {
        if (authenticatedUser != null && authenticatedUser.getRole() == Role.OWNER) {
            System.out.print("Enter restaurant name: ");
            String name = scanner.nextLine();
            System.out.print("Enter restaurant address: ");
            String address = scanner.nextLine();
            System.out.print("Enter restaurant phone number: ");
            String phoneNumber = scanner.nextLine();
            venueService.addVenue(name, address, phoneNumber, authenticatedUser.getUserId());
        } else {
            System.out.println("Only authenticated owners can add restaurants.");
        }
    }

    private static void addMenuItem(Scanner scanner, MenuItemService menuItemService, VenueService venueService) {
        if (authenticatedUser != null && authenticatedUser.getRole() == Role.OWNER){
            System.out.print("Enter restaurant name: ");
            String venueName = scanner.nextLine();

            // Verify if the venue exists
            Venue venue = venueService.getVenueByName(venueName);
                if (venue == null){
                    System.out.println("Restaurant not found.");
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
            System.out.println("Only authenticated owners can add menu items.");
        }
    }

    private static void viewOrderStatus(OrderService orderService) {
        if (authenticatedUser != null) {
            orderService.viewOrderStatus(authenticatedUser.getUserId());
        } else {
            System.out.println("You need to authenticate first.");
        }

    }

    private static void updateOrderStatus(Scanner scanner, OrderService orderService) {
        if (authenticatedUser != null && authenticatedUser.getRole() == Role.OWNER) {
            System.out.print("Enter order ID: ");
            int orderId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new status (PENDING/DONE): ");
            String status = scanner.nextLine();
            orderService.updateOrderStatus(orderId, status);
        } else {
            System.out.println("Only authenticated owners can update order status.");
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