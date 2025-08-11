import model.Receipt;
import model.ShoppingBasket;
import service.InputParser;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        List<Boolean> exemptStatuses = new ArrayList<>();
        List<Boolean> importedStatuses = new ArrayList<>();
        
        System.out.println("=== Shopping Basket Input ===");
        System.out.println("Enter items one by one (type 'done' to finish)");
        
        while (true) {
            try {
                System.out.print("\nItem name: ");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("done")) break;
                if (name.isEmpty()) {
                    System.out.println("⚠️ Name cannot be empty!");
                    continue;
                }

                int quantity = getValidNumber(scanner, "Quantity: ", 1, 1000);
                BigDecimal price = getValidPrice(scanner);
                boolean isExempt = getYesNo(scanner, "Is exempt? (y/n): ");
                boolean isImported = getYesNo(scanner, "Is imported? (y/n): ");

                inputs.add(String.format("%d %s at %.2f", quantity, name, price));
                exemptStatuses.add(isExempt);
                importedStatuses.add(isImported);
                System.out.println("✅ Item added!");
                
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
        if (!inputs.isEmpty()) {
            ShoppingBasket basket = InputParser.parse(inputs, exemptStatuses, importedStatuses);
            new Receipt(basket).print();
        } else {
            System.out.println("No items entered. Exiting...");
        }
    }

    private static int getValidNumber(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    System.out.printf("⚠️ Please enter between %d-%d\n", min, max);
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number");
            }
        }
    }

    private static BigDecimal getValidPrice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Price: ");
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid price (e.g., 12.99)");
            }
        }
    }

    private static boolean getYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("⚠️ Please enter 'y' or 'n'");
        }
    }
}