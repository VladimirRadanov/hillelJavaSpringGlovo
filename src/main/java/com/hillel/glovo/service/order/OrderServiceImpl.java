package com.hillel.glovo.service.order;

import com.hillel.glovo.model.Order;
import com.hillel.glovo.repository.memory.OrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private List<Order> orderList = new OrderList().generateOrders();


    @Override
    public List<Order> getOrders() {
        return orderList;
    }

    @Override
    public Order getOrder(Integer id) {
        return orderList.get(id - 1);
    }

    @Override
    public void addOrder(Order order) {
        orderList.add(new OrderList().addOrder());
    }

    @Override
    public void updateOrder(Integer id) {
        new OrderList().updateOrderId(orderList.get(id - 1));
    }

    @Override
    public void deleteOrder(Integer id) {
        orderList.remove(id - 1);
    }
}
