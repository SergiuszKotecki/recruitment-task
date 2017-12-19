package com.orderquest.order.repositories;

import com.orderquest.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long>{
}
