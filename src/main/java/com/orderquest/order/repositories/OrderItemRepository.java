package com.orderquest.order.repositories;

import com.orderquest.order.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}