package com.hillel.glovo.service.order;

import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.repository.jdbc.OrdersJDBCRepository;
import com.hillel.glovo.repository.memory.OrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private List<OrderDto> orderList = new OrderList().generateOrders();
    private final OrdersJDBCRepository ordersJDBCRepository;


    @Override
    public List<OrderDto> getOrders() {
//        return orderList;
        return ordersJDBCRepository.getOrders();
    }

    @Override
    public OrderDto getOrder(Integer id) {
//        return orderList.get(id - 1);
        return ordersJDBCRepository.getOrderById(id.intValue());
    }

    @Override
    public void save(OrderDto order) {
//        orderList.add(new OrderList().addOrder());
        ordersJDBCRepository.saveOrder(order);
    }

    @Override
    public void updateOrder(Integer id, OrderDto orderDto){
        ordersJDBCRepository.updateOrder(id, orderDto);
    }
//    public void updateOrder(Integer id) {
//        new OrderList().updateOrderId(orderList.get(id - 1));
//    }

    @Override
    public void deleteOrder(Integer id) {
//        orderList.remove(id - 1);
        ordersJDBCRepository.deleteOrder(id);
    }
}
