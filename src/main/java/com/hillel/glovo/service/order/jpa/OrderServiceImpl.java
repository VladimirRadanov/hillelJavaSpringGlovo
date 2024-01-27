package com.hillel.glovo.service.order.jpa;

import com.hillel.glovo.converter.OrderConverter;
import com.hillel.glovo.dto.order.OrderDto;
import com.hillel.glovo.mappers.OrderMapper;
import com.hillel.glovo.model.jpa.Order;
import com.hillel.glovo.repository.order.spring_jpa.OrderRepository;
import com.hillel.glovo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow();
//        return orderConverter.fromModel(order);
        log.debug("Get order by id: " +id + " Order: "+ order);
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        Iterable<Order> orders = orderRepository.findAll();
        log.debug("Get all orders: " + orders);
//        return orderConverter.fromModel(orders);
        return orderMapper.toOrderDtoList(orders);
    }

    //    page - Page number
//    size - Page Size
//    sort - sort by(Order by column name)
//    direction - ASC / DESC
    @Override
    public Page<Order> getOrdersPage(Pageable pageable) {
        Page<Order> all = orderRepository.findAll(pageable);
        return all;
    }

    @Override
    public void save(OrderDto dto) {
//        Order order = orderConverter.toModel(dto);
        Order order = orderMapper.orderDtoToOrder(dto);
        orderRepository.save(order);
        log.debug("Order saved: " + order);
    }

    @Override
    public void update(Integer id, OrderDto dto) {
        Order old = orderRepository.findById(id).orElseThrow();
        Order updated = orderConverter.toModel(old, dto);
        Order saved = orderRepository.save(updated);
        log.debug("Order updated: " + saved);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
        log.info("Order with id: " + id + " is deleted");
    }
}
