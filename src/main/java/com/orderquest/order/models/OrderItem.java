package com.orderquest.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID", unique = true, length = Integer.MAX_VALUE)
    private long id;

    @Column(name = "NET_PRICE")
    private double netPrice; //TODO: BigDecimal or other object for storage money

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "NET_TOTAL")
    private double netTotal;

    @Column(name = "TOTAL_ORDER")
    private double total;
}
