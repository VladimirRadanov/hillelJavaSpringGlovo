package com.hillel.glovo.service.order;

import com.hillel.glovo.dto.OrderDto;

import java.util.List;

public interface OrderService {
    public OrderDto getOrder(Integer id);

    public List<OrderDto> getOrders();

    public void save(OrderDto order);

    public void updateOrder(Integer id, OrderDto orderDto);

    public void deleteOrder(Integer id);
}
