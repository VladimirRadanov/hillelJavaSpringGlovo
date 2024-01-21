/*
package com.hillel.glovo.service;

import com.hillel.glovo.dto.order.OrderDto;
import com.hillel.glovo.dto.order.ProductDto;
import com.hillel.glovo.repository.jdbc.JDBCRepository;
import com.hillel.glovo.repository.memory.OrderList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GlovoServiceImpl implements GlovoService {
    private List<OrderDto> orderList = new OrderList().generateOrders();
    private final JDBCRepository jdbcRepository;


    @Override
    public List<OrderDto> getOrders() {
//        return orderList;
        return jdbcRepository.getOrders();
    }

    @Override
    public OrderDto getOrderById(Integer id) {
//        return orderList.get(id - 1);
        return jdbcRepository.getOrderById(id.intValue());
    }

    @Override
    public void createOrder(OrderDto order) {
//        orderList.add(new OrderList().addOrder());
        jdbcRepository.createOrder(order);
    }

    @Override
    public void updateOrder(Integer id, OrderDto orderDto) {
        jdbcRepository.updateOrder(id, orderDto);
    }
//    public void updateOrder(Integer id) {
//        new OrderList().updateOrderId(orderList.get(id - 1));
//    }

    @Override
    public void deleteOrder(Integer id) {
//        orderList.remove(id - 1);
        jdbcRepository.deleteOrder(id);
    }

    @Override
    public List<ProductDto> getProducts() {
        return jdbcRepository.getProducts();
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return jdbcRepository.getProductById(id);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        jdbcRepository.createProduct(productDto);
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        jdbcRepository.updateProduct(id, productDto);
    }

    @Override
    public void deleteProduct(Integer id) {
        jdbcRepository.deleteProduct(id);
    }
}
*/
