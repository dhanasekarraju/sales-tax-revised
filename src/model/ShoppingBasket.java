package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private final List<Item> items;

    public ShoppingBasket() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
