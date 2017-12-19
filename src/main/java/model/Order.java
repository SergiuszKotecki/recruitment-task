package model;

import java.util.ArrayList;
import java.util.List;


public class Order {
    List<OrderItem> orderedItems = new ArrayList<>();

    public void addItemToCart (OrderItem item) {
        orderedItems.add(item);
    }

    public void deleteItem (OrderItem item) {
        orderedItems.remove(item);
    }

}
