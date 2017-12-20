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
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RoundDouble round;

    public void generateItems() {
        List<OrderItem> itemsGeneratator = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            OrderItem test = new OrderItem(
                    randomNumber(1.0d, 200d),
                    randomNumber(1, 200),
                    randomNumber(5, 23));
            orderItemRepository.save(test);
            itemsGeneratator.add(test);
        }
        Order generatedItems = new Order(itemsGeneratator);
        orderRepository.save(generatedItems);
        log.info("-------------------------------");
        for (OrderItem item : orderItemRepository.findAll()) {
            log.info(item.toString());
        }
        log.info("-------------------------------");
        log.info(orderRepository.findAll().toString());
        log.info("-------------------------------");

    }

    private double randomNumber(double min, double max) {
        return round.round((min + (max - min) * new Random().nextDouble()), 2);
    }

    private int randomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }


}
