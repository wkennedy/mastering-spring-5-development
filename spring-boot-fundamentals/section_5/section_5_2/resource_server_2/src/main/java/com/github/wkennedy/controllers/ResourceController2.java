package com.github.wkennedy.controllers;

import com.github.wkennedy.entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ResourceController2 {

    @GetMapping("/")
    public String index() {
        return "Hello from Resource 2!";
    }

    @GetMapping("/order")
    public Order getOrder() {
        Order order = new Order();
        order.setOrderID(UUID.randomUUID().toString());
        order.setProduct("Acme Portal");
        order.setQuantity(1);

        return order;
    }
}
