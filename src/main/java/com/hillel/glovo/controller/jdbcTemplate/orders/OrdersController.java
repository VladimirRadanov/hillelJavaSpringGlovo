package com.hillel.glovo.controller.jdbcTemplate.orders;

import com.hillel.glovo.controller.response.ApiResponse;
import com.hillel.glovo.dto.OrderDto;
import com.hillel.glovo.dto.ProductDto;
import com.hillel.glovo.service.GlovoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrdersController {

    private final GlovoService glovoService;

    @GetMapping()
    public ApiResponse<List<OrderDto>> getOrders() {
        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        List<OrderDto> orderDtos = glovoService.getOrders();
        if (!CollectionUtils.isEmpty(orderDtos)) {
            response.setSuccess(true);
            response.setData(orderDtos);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDto> getOrderById(@PathVariable("id") Integer id) {
        ApiResponse<OrderDto> response = new ApiResponse<>();
        OrderDto orderDto = glovoService.getOrderById(id);
        if (orderDto != null) {
            response.setSuccess(true);
            response.setData(orderDto);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @PostMapping()
    public ApiResponse<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> response = new ApiResponse<>();
        glovoService.createOrder(orderDto);
        if (glovoService.getOrderById(orderDto.getId()).getDate().equals(orderDto.getDate()) &&
                glovoService.getOrderById(orderDto.getId()).getCost() == orderDto.getCost()) {
            response.setSuccess(true);
            response.setData(orderDto);
            response.setMessages(Stream.of(HttpStatus.CREATED.toString()).toList());
        }
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDto> updateOrder(@PathVariable("id") Integer id, @RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> response = new ApiResponse<>();
        glovoService.updateOrder(id, orderDto);
        if (glovoService.getOrderById(id).getDate().equals(orderDto.getDate()) &&
                glovoService.getOrderById(id).getCost() == orderDto.getCost()) {
            response.setSuccess(true);
            response.setData(orderDto);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<OrderDto> deleteOrder(@PathVariable("id") Integer id) {
        ApiResponse<OrderDto> response = new ApiResponse<>();
        glovoService.deleteOrder(id);
        try {
            glovoService.getOrderById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            response.setSuccess(true);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

}
