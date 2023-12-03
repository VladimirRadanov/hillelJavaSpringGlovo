package com.hillel.glovo.service.order;

import com.hillel.glovo.model.Order;

import java.util.List;

public interface OrderService {

    public Order getOrder(Integer id);

    public List<Order> getOrders();

    public void addOrder(Order order);

    public void updateOrder(Integer id);

    public void deleteOrder(Integer id);

}
