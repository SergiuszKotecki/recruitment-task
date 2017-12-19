package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class Order {

    List<OrderItem> orderedItems = new ArrayList<>();
    private double netTotal;
    private double tax;
    private double total;

    public Order(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
        this.netTotal = calculateNetTotal(orderedItems);
        this.total = calculateGrosTotal(orderedItems);
        this.tax = calculateTaxValue(this.total, this.netTotal);
    }

    private double calculateTaxValue(double total, double netTotal) {
        return total - netTotal;
    }

    private double calculateGrosTotal(List<OrderItem> orderedItems) {
        return orderedItems.stream().mapToDouble(y -> y.getTotal()).sum();
    }


    private double calculateNetTotal(List<OrderItem> orderedItems) {
        return orderedItems.stream().mapToDouble(w -> w.getNetTotal()).sum();
    }

    public void addItemToCart(OrderItem item) {
        orderedItems.add(item);
    }

    public void deleteItem(OrderItem item) {
        orderedItems.remove(item);
    }

}
