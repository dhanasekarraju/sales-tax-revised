package model;

import java.math.BigDecimal;

public class Item {
    private final String name;
    private final BigDecimal price;
    private final boolean isExempt;
    private final boolean isImported;
    private final int quantity;

    public Item(String name, String price, boolean isExempt, boolean isImported, int quantity) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.isExempt = isExempt;
        this.isImported = isImported;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public boolean isExempt() { return isExempt; }
    public boolean isImported() { return isImported; }
    public int getQuantity() { return quantity; }
}