
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<DeliveryOrder> orders = new ArrayList<>();
    static int nextId = 1;

    static class DeliveryOrder {
        int id;
        String customerName;
        int qtySlim;
        int qtyRound;
        String status;

        DeliveryOrder(int id, String customerName,
                      int qtySlim, int qtyRound, String status) {
            this.id = id;
            this.customerName = customerName;
            this.qtySlim = qtySlim;
            this.qtyRound = qtyRound;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addDeliveryOrder();
                    break;
                case 2:
                    viewActiveOrders();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println(
                            "Invalid choice! Please select 1, 2, or 3."
                    );
            }

        } while (choice != 3);

        sc.close();
    }

    static void displayMenu() {
        System.out.println("\n==============================================");
        System.out.println("       WATER REFILLING STATION ORDER SYSTEM");
        System.out.println("==============================================");
        System.out.println("[1] Add Delivery Order");
        System.out.println("[2] View Active Orders");
        System.out.println("[3] Exit System");
        System.out.println("==============================================");
    }

    static void addDeliveryOrder() {
        System.out.println("\n--- ADD DELIVERY ORDER ---");

        String customerName;

        do {
            System.out.print("Enter Customer Name: ");
            customerName = sc.nextLine().trim();

            if (customerName.isEmpty()) {
                System.out.println("Customer name cannot be empty!");
            }
        } while (customerName.isEmpty());

        int qtySlim = readNonNegativeInt(
                "Enter Quantity (Slim Containers): "
        );

        int qtyRound = readNonNegativeInt(
                "Enter Quantity (Round Containers): "
        );

        String status;

        while (true) {
            System.out.print(
                    "Enter Status (PENDING / DELIVERED): "
            );
            status = sc.nextLine().trim().toUpperCase();

            if (status.equals("PENDING")
                    || status.equals("DELIVERED")) {
                break;
            }

            System.out.println(
                    "Invalid status! Enter PENDING or DELIVERED."
            );
        }

        DeliveryOrder order = new DeliveryOrder(
                nextId, customerName, qtySlim, qtyRound, status
        );

        orders.add(order);
        nextId++;

        System.out.println("\nSUCCESS: Water delivery order logged!");
    }

    static void viewActiveOrders() {
        System.out.println("\n==============================================================");
        System.out.println("                    ACTIVE WATER ORDERS");
        System.out.println("==============================================================");

        System.out.printf(
                "%-4s | %-18s | %-9s | %-10s | %-10s%n",
                "ID", "CUSTOMER NAME", "QTY SLIM", "QTY ROUND", "STATUS"
        );

        System.out.println(
                "-----+--------------------+-----------+------------+-----------"
        );

        int count = 0;

        for (DeliveryOrder order : orders) {
            System.out.printf(
                    "%-4d | %-18s | %-9d | %-10d | %-10s%n",
                    order.id,
                    order.customerName,
                    order.qtySlim,
                    order.qtyRound,
                    order.status
            );

            count++;
        }

        System.out.println(
                "-----+--------------------+-----------+------------+-----------"
        );

        System.out.println("Total Records Displayed: " + count);
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    static int readNonNegativeInt(String prompt) {
        while (true) {
            int number = readInt(prompt);

            if (number >= 0) {
                return number;
            }

            System.out.println(
                    "Quantity cannot be negative. Please try again."
            );
        }
    }
}
