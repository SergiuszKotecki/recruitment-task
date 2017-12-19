package com.orderquest.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OrderApplication.class, args);
		context.getBean(ItemsGenerator.class).generateItems();
	}
}
