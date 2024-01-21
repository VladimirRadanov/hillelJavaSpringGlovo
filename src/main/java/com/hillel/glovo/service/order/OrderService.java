package com.hillel.glovo.service.order;

import com.hillel.glovo.dto.order.OrderDto;
import com.hillel.glovo.model.jpa.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Integer id);

    List<OrderDto> getOrders();

    Page<Order> getOrdersPage(Pageable pageable);

    void save(OrderDto dto);

    void update(Integer id, OrderDto dto);

    void delete(Integer id);
}
