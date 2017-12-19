package com.orderquest.order;

import com.orderquest.order.model.Order;
import com.orderquest.order.model.OrderItem;
import com.orderquest.order.repositories.OrderItemRepository;
import com.orderquest.order.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ItemsGenerator {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    private static final Logger log = LoggerFactory.getLogger(ItemsGenerator.class);


    public void generateItems() {
        List<OrderItem> itemsGeneratator = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderItem test = new OrderItem(
                    randomNumber(1.0d, 200d),
                    randomNumber(1, 2),
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
        log.info("");
    }


    private double randomNumber(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }

    private int randomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }


}
