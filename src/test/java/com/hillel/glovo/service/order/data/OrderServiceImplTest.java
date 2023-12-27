package com.hillel.glovo.service.order.data;

import com.hillel.glovo.converter.OrderConverter;
import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.dto.ProductDto;
import com.hillel.glovo.entity.data.Order;
import com.hillel.glovo.repository.data.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private static final int ORDER_ID = 111;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderConverter orderConverter;

    @Mock
    private Order order;
    @Mock
    private List<Order> orders = new ArrayList<>();

    private OrderDto dto;
    private List<OrderDto> dtos = new ArrayList<>();

    @BeforeEach
    public void init() {
        dto = new OrderDto();
        dto.setId(ORDER_ID);
        dto.setCost(11.1);
        dto.setDate(LocalDate.now());
        dto.setProducts(List.of(new ProductDto()));


        dtos.add(dto);
    }

    @Test
    public void shouldReturnOrderById() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderConverter.fromModel(order)).thenReturn(dto);

        OrderDto result = orderService.getOrderById(ORDER_ID);

        verify(orderRepository).findById(ORDER_ID);
        verify(orderConverter).fromModel(order);

        assertNotNull(result);
        assertEquals(ORDER_ID, result.getId());
    }

    @Test
    public void shouldNotReturnOrderById() {
        assertThrows(NoSuchElementException.class, () -> {
            orderService.getOrderById(ORDER_ID);
        });
    }

    @Test
    public void shouldReturnOrders() {
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderConverter.fromModel(orders)).thenReturn(dtos);

        List<OrderDto> result = orderService.getOrders();

        verify(orderRepository).findAll();
        verify(orderConverter).fromModel(orders);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void shouldSaveOrder() {
        when(orderConverter.toModel(dto)).thenReturn(order);
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderConverter.fromModel(order)).thenReturn(dto);

        dto.setId(ORDER_ID + 1);
        orderService.save(dto);
        OrderDto result = orderService.getOrderById(dto.getId());

        verify(orderConverter).toModel(dto);

        assertEquals(
                dto.getCost(),
                result.getCost()
        );
    }

    @Test
    public void shouldUpdateOrder() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderConverter.fromModel(order)).thenReturn(dto);
        when(orderConverter.toModel(order, dto)).thenReturn(order);

        dto.setCost(99);
        orderService.update(ORDER_ID, dto);
        OrderDto result = orderService.getOrderById(ORDER_ID);

        verify(orderConverter).toModel(order, dto);

        assertEquals(
                dto.getCost(),
                result.getCost()
        );
    }

    @Test
    public void shouldDeleteOrderById() {
        orderRepository.deleteById(ORDER_ID);

        verify(orderRepository).deleteById(ORDER_ID);

        assertThrows(NoSuchElementException.class, () -> {
            orderService.getOrderById(ORDER_ID);
        });
    }

}