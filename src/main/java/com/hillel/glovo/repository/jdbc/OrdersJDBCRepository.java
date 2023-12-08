package com.hillel.glovo.repository.jdbc;

import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.dto.ProductDto;
import com.hillel.glovo.repository.mappers.OrderDtoRowMapper;
import com.hillel.glovo.repository.mappers.ProductDtoRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersJDBCRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String SELECT_ORDER_BY_ID = "SELECT * FROM orders where id= ";
    private final String SELECT_ORDERS = "SELECT * FROM orders";
    private final String SAVE_ORDER = "INSERT INTO orders VALUES (?, ?, ?)";
    private final String UPDATE_ORDER = "UPDATE orders SET date = ?, cost = ? WHERE id = ?";
    private final String DELETE_ORDER_PRODUCTS = "DELETE FROM order_products WHERE order_id = ";
    private final String DELETE_ORDER = "DELETE FROM orders WHERE id = ";
    private final String SELECT_PRODUCTS_FOR_ORDER = "SELECT products.id, products.name, products.cost " +
            "FROM products " +
            "INNER JOIN order_products ON products.id = order_products.product_id " +
            "AND order_products.order_id = ";

    public OrderDto getOrderById(int id) {
        OrderDto orderDto = jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID + id, new OrderDtoRowMapper());
        orderDto.setProducts(jdbcTemplate.query(SELECT_PRODUCTS_FOR_ORDER + id, new ProductDtoRowMapper()));
        return orderDto;
    }

    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtos = jdbcTemplate.query(SELECT_ORDERS, new OrderDtoRowMapper());
        for (OrderDto orderDto: orderDtos) {
            orderDto.setProducts(jdbcTemplate.query(
                    SELECT_PRODUCTS_FOR_ORDER + orderDto.getId(),
                    new ProductDtoRowMapper()));
        }
        return orderDtos;
    }

    public void saveOrder(OrderDto orderDto) {
        jdbcTemplate.update(SAVE_ORDER,
                orderDto.getId(), orderDto.getDate(), orderDto.getCost());
    }

    public void updateOrder(Integer id, OrderDto orderDto) {
        jdbcTemplate.update(UPDATE_ORDER,
                orderDto.getDate(), orderDto.getCost(), id);
    }

    public void deleteOrder(Integer id) {
        jdbcTemplate.update(DELETE_ORDER_PRODUCTS + id);
        jdbcTemplate.update(DELETE_ORDER + id);
    }
}
