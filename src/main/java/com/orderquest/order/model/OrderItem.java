package com.orderquest.order.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="ITEM_ID", unique = true, length = Integer.MAX_VALUE)
    private long id;

    @Column(name = "NET_PRICE")
    private double netPrice; //TODO: BigDecimal or other object for storage money

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "NET_TOTAL")
    private double netTotal;

    @Column(name = "TOTAL_ORDER")
    private double total;

    public OrderItem(long id, double netPrice, int quantity, double tax) {
        this.id = id;
        this.netPrice = netPrice;
        this.quantity = quantity;
        this.netTotal = calculateNetTotal(quantity, netPrice);
        this.total = calculateTotalPrice(tax, this.netTotal);
    }

    private double calculateTotalPrice(double tax, double netTotal) {
        return tax * netTotal;
    }

    private double calculateNetTotal(double quantity, double netPrice) {
        return quantity * netPrice;
    }
}
