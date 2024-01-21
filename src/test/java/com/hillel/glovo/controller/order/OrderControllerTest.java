package com.hillel.glovo.controller.order;

import com.hillel.glovo.dto.order.OrderDto;
import com.hillel.glovo.dto.order.ProductDto;
import com.hillel.glovo.mappers.OrderMapper;
import com.hillel.glovo.model.jpa.Order;
import com.hillel.glovo.model.jpa.Product;
import com.hillel.glovo.repository.order.spring_jpa.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class OrderControllerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    Order order = Order.builder()
            .date(LocalDate.now())
            .cost(31.1)
            .product(List.of(Product.builder()
                    .cost(22.2)
                    .name("test")
                    .build()))
            .build();

    OrderDto orderDto = OrderDto.builder()
            .date(LocalDate.now())
            .cost(25.5)
            .products(List.of(ProductDto.builder()
                    .name("product test")
                    .cost(25.5)
                    .build()))
            .build();

    @Test
    void shouldGetOrderById() {
        Order savedOrder = orderRepository.save(order);

        OrderDto result = restTemplate.getForObject("http://localhost:" + port + "/api/v1/orders/" + savedOrder.getId(), OrderDto.class);

        Assertions.assertEquals(savedOrder.getId(), result.getId());
        Assertions.assertEquals(savedOrder.getDate(), result.getDate());
        Assertions.assertEquals(savedOrder.getCost(), result.getCost());
        Assertions.assertEquals(orderMapper.orderToOrderDto(savedOrder).getProducts(), result.getProducts());
    }

    @Test
    void shouldDeleteOrderById() {
        Order savedOrder = orderRepository.save(order);
        orderRepository.deleteById(savedOrder.getId());

        OrderDto result = restTemplate.getForObject("http://localhost:" + port + "/api/v1/orders/" + savedOrder.getId(), OrderDto.class);

        Assertions.assertNull(result.getId());
        Assertions.assertNull(result.getDate());
        Assertions.assertNull(result.getCost());
        Assertions.assertNull(result.getProducts());
    }

    @Test
    void shouldCreateOrder() {
        int numberOfRecords = orderRepository.findAll().size();

        restTemplate.postForObject("http://localhost:" + port + "/api/v1/orders/", orderDto, Order.class);

        Optional<Order> lastOrder = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().findFirst();

        Assertions.assertEquals(++numberOfRecords, orderRepository.findAll().size());
        Assertions.assertEquals(orderDto.getCost(), lastOrder.get().getCost());
        Assertions.assertEquals(orderDto.getDate(), lastOrder.get().getDate());
    }
}
