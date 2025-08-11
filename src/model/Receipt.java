package model;

import service.TaxCalculator;
import java.math.BigDecimal;

public class Receipt {
    private final ShoppingBasket basket;
    private final TaxCalculator taxCalculator = new TaxCalculator();

    public Receipt(ShoppingBasket basket) {
        this.basket = basket;
    }

    public void print() {
        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        System.out.println("┌────────────────────────────────────────────┐");
        System.out.printf("│ %-20s %10s %10s │\n", "Item", "Price", "Tax");
        System.out.println("├────────────────────────────────────────────┤");

        for (Item item : basket.getItems()) {
            BigDecimal unitTax = taxCalculator.calculateUnitTax(item);
            BigDecimal itemTax = unitTax.multiply(BigDecimal.valueOf(item.getQuantity()));
            BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).add(itemTax);

            System.out.printf("│ %-20s %10.2f %10.2f │\n",
                    item.getQuantity() + " " + item.getName(),
                    item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())),
                    itemTax);

            totalTax = totalTax.add(itemTax);
            totalAmount = totalAmount.add(itemTotal);
        }

        System.out.println("├────────────────────────────────────────────┤");
        System.out.printf("│ %-20s %10s %10.2f │\n", "SUBTOTAL", "",
                basket.getItems().stream()
                        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.printf("│ %-20s %10s %10.2f │\n", "TAX", "", totalTax);
        System.out.printf("│ %-20s %10s %10.2f │\n", "TOTAL", "", totalAmount);
        System.out.println("└────────────────────────────────────────────┘");
    }
}