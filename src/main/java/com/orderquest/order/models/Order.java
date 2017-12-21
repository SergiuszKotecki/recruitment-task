package com.orderquest.order.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID", unique = true, length = Integer.MAX_VALUE)
    private long id;

    @Column(name="NET_TOTAL")
    private double netTotal;

    @Column(name="TAX_TOTAL")
    private double tax;

    @Column(name="GROSS_TOTAL")
    private double total;


}
