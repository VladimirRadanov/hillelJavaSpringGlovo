package com.hillel.glovo.service;

import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.dto.ProductDto;

import java.util.List;

public interface GlovoService {
    public List<OrderDto> getOrders();

    public OrderDto getOrderById(Integer id);

    public void createOrder(OrderDto order);

    public void updateOrder(Integer id, OrderDto orderDto);

    public void deleteOrder(Integer id);

    public List<ProductDto> getProducts();

    public ProductDto getProductById(Integer id);

    public void createProduct(ProductDto productDto);

    public void updateProduct(Integer id, ProductDto productDto);

    public void deleteProduct(Integer id);
}
