package com.orderquest.order;

import com.orderquest.order.converters.RoundDouble;
import com.orderquest.order.models.Order;
import com.orderquest.order.models.OrderItem;
import com.orderquest.order.repositories.OrderItemRepository;
import com.orderquest.order.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Slf4j
@Component
public class ItemsGenerator {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoundDouble round;

    public void generateOrderItems(int nItems) {
        List<OrderItem> orderItems = new ArrayList<>();
        int tax = randomNumber(5, 23);
        for (int i = 0; i < nItems; i++) {
            OrderItem item = generateItem(tax, randomNumber(1d, 200d), randomNumber(1, 50));
            orderItems.add(item);
            orderItemRepository.save(item);
        }
        orderRepository.save(generateOrderItem(orderItems));
    }

    private OrderItem generateItem(int tax, double price, int quantity) {
        double netTotal = calculateNetTotal(quantity, price);
        double total = calculateTotalPrice(tax, netTotal);
        return new OrderItem(0, price, quantity, netTotal, total);
    }

    private Order generateOrderItem(List<OrderItem> orderItems) {
        double total = round.round((orderItems.stream().mapToDouble(y -> y.getTotal()).sum()), 2);
        double netTotal = round.round((orderItems.stream().mapToDouble(w -> w.getNetTotal()).sum()), 2);
        double tax = round.round((total - netTotal), 2);

        return new Order(0, netTotal, tax, total);
    }

    private double calculateTotalPrice(int tax, double netTotal) {
        return round.round((tax * netTotal), 2);
    }

    private double calculateNetTotal(double quantity, double netPrice) {
        return round.round((quantity * netPrice), 2);
    }

    private double randomNumber(double min, double max) {
        return round.round((min + (max - min) * new Random().nextDouble()), 2);
    }

    private int randomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }


}
