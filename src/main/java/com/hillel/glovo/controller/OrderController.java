package com.hillel.glovo.controller;

import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable("id") Integer id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/order")
    public void saveOrder(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDto orderDto) {
        orderService.updateOrder(id, orderDto);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
    }
}
