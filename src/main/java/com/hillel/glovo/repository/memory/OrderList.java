package com.hillel.glovo.repository.memory;

import com.hillel.glovo.dto.OrderDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private List<OrderDto> orderList = new ArrayList<>();
    private ProductList productList = new ProductList();
    private static int id = 3;

    public List<OrderDto> generateOrders() {
        orderList.add(new OrderDto(1, LocalDate.now(), 10, productList.getProducts()));
        orderList.add(new OrderDto(2, LocalDate.now(), 20, productList.getProducts()));
        orderList.add(new OrderDto(3, LocalDate.now(), 30, productList.getProducts()));
        return orderList;
    }

    public OrderDto addOrder() {
        return new OrderDto(++id, LocalDate.now(),10, productList.getProducts());
    }

    public OrderDto updateOrderId(OrderDto order) {
        int newId = order.getId() + 100;
        order.setId(newId);
        return order;
    }
}


