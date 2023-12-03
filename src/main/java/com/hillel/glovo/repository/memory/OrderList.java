package com.hillel.glovo.repository.memory;

import com.hillel.glovo.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private List<Order> orderList = new ArrayList<>();
    private ProductList productList = new ProductList();
    private static int id = 3;

    public List<Order> generateOrders() {
        orderList.add(new Order(1, LocalDate.now(), 10, productList.getProducts()));
        orderList.add(new Order(2, LocalDate.now(), 20, productList.getProducts()));
        orderList.add(new Order(3, LocalDate.now(), 30, productList.getProducts()));
        return orderList;
    }

    public Order addOrder() {
        return new Order(++id, LocalDate.now(),10, productList.getProducts());
    }

    public Order updateOrderId(Order order) {
        int newId = order.getId() + 100;
        order.setId(newId);
        return order;
    }
}


