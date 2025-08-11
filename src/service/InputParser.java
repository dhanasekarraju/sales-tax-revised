package service;

import model.Item;
import model.ShoppingBasket;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static ShoppingBasket parse(List<String> inputs, List<Boolean> exemptStatuses, List<Boolean> importedStatuses) {
        ShoppingBasket basket = new ShoppingBasket();
        
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            if (input.trim().isEmpty()) continue;
            
            String[] parts = input.split(" at ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid format: " + input);
            }
            
            String[] quantityAndName = parts[0].split(" ");
            int quantity = Integer.parseInt(quantityAndName[0]);
            String name = String.join(" ", 
                Arrays.copyOfRange(quantityAndName, 1, quantityAndName.length));
        
            boolean isExempt = exemptStatuses.get(i);
            boolean isImported = importedStatuses.get(i);
            
            basket.addItem(new Item(
                name,
                parts[1],
                isExempt,
                isImported,
                quantity
            ));
        }
        
        return basket;
    }
}