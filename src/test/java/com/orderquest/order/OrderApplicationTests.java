package com.orderquest.order;

import com.orderquest.order.models.Order;
import com.orderquest.order.models.OrderItem;
import com.orderquest.order.repositories.OrderItemRepository;
import com.orderquest.order.repositories.OrderRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemsGenerator itemsGenerator;

    @Test
    public void itemOrderNetTotalHas100Tax23andTotalVAT123() {
        OrderItem orderItem = itemsGenerator.generateItem(23, 0.01, 10000);
        // 1000*0.01 = 100 netTotal
        // 100*1.23% = 123 grossTotal
        Assert.assertThat(100d, Matchers.equalTo(orderItem.getNetTotal()));
        Assert.assertThat(123d, Matchers.equalTo(orderItem.getTotal()));
    }

    @Test
    public void OrderTotalHasCorrectCalc() {
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem item = itemsGenerator.generateItem(23, 0.01, 100);
        OrderItem item2 = itemsGenerator.generateItem(23, 0.02, 100);
        OrderItem item3 = itemsGenerator.generateItem(23, 0.03, 100);
        //(0.01 * 100) + (0.01 * 100) + (0.01 * 100) = 6 netTotal
        // 6  x 1.23 = 7,38 grossTotal

        orderItems.add(item);
        orderItems.add(item2);
        orderItems.add(item3);
        Order order = itemsGenerator.generateOrderItem(orderItems);

        Assert.assertThat(6d, Matchers.equalTo(order.getNetTotal()));
        Assert.assertThat(7.38d, Matchers.equalTo(order.getTotal()));
    }

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
