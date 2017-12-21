package com.orderquest.order;

import com.orderquest.order.converters.RoundDouble;
import com.orderquest.order.models.Order;
import com.orderquest.order.models.OrderItem;
import com.orderquest.order.repositories.OrderItemRepository;
import com.orderquest.order.repositories.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemsGenerator itemsGenerator;


    @Autowired
    private RoundDouble roundDouble;

    @Test
    public void generatorProduce100Items() {
        itemsGenerator.generateOrderItems(100);
        Assert.assertEquals(itemRepository.count(), 100);
    }

    @Test
    public void OrderIsEmpty() {
        Assert.assertTrue(orderRepository.count() == 0);
    }

    @Test
    public void OrderHasAllValues() {
        itemsGenerator.generateOrderItems(10);
        Order order = orderRepository.findOne(1L);
        Assert.assertNotNull(order);
        Assert.assertNotNull(order.getId());
        Assert.assertNotNull(order.getNetTotal());
        Assert.assertNotNull(order.getTax());
        Assert.assertNotNull(order.getTotal());
    }

    @Test
    public void OrderItemHasNotValues() {
        for (OrderItem item : itemRepository.findAll()) {
            Assert.assertNull(item.getNetTotal());
            Assert.assertNull(item.getTotal());
            Assert.assertNull(item.getId());
        }
    }

}
