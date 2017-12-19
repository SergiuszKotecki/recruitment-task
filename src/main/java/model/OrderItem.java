package model;

import lombok.Data;

@Data
public class OrderItem {

    private long id;
    private double netPrice; //TODO: BigDecimal or other object for storage money
    private int quantity;
    private double netTotal;
    private double total;

    public OrderItem(long id, double netPrice, int quantity, double tax) {
        this.id = id;
        this.netPrice = netPrice;
        this.quantity = quantity;
        this.netTotal = calculateNetTotal(quantity, netPrice);
        this.total = calculateTotalPrice(tax, this.netTotal);
    }

    private double calculateTotalPrice(double tax, double netTotal) {
        return tax*netTotal;
    }

    private double calculateNetTotal (double quantity, double netPrice){
        return quantity*netPrice;
    }
}
