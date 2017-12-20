package com.orderquest.order.models;

import com.orderquest.order.converters.RoundDouble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

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

    public Order(List<OrderItem> orderedItems) {
        this.netTotal = calculateNetTotal(orderedItems);
        this.total = calculateGrossTotal(orderedItems);
        this.tax = calculateTaxValue(this.total, this.netTotal);
    }

    private double calculateTaxValue(double total, double netTotal) {
        return total - netTotal;
    }

    private double calculateGrossTotal(List<OrderItem> orderedItems) {
        return orderedItems.stream().mapToDouble(y -> y.getTotal()).sum();
    }


    private double calculateNetTotal(List<OrderItem> orderedItems) {
        return orderedItems.stream().mapToDouble(w -> w.getNetTotal()).sum();
    }

}
