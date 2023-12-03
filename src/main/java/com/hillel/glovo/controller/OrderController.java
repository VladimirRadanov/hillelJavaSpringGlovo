package com.hillel.glovo.controller;

import com.hillel.glovo.model.Order;
import com.hillel.glovo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    @PostMapping()
    public void createOrder() {
        orderService.addOrder(null);
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable("id") int id) {
        orderService.updateOrder(id);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
    }
}
