package com.hillel.glovo.controller;

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
@RequestMapping("/api/v1")
@RestController
public class GlovoController {

    private final GlovoService glovoService;

    @GetMapping("/orders")
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

    @GetMapping("/orders/{id}")
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

    @PostMapping("/orders")
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

    @PutMapping("/orders/{id}")
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

    @DeleteMapping("/orders/{id}")
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

    @GetMapping("/products")
    public ApiResponse<List<ProductDto>> getProducts() {
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();
        List<ProductDto> productDtos = glovoService.getProducts();
        if (!CollectionUtils.isEmpty(productDtos)) {
            response.setSuccess(true);
            response.setData(productDtos);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @GetMapping("/products/{id}")
    public ApiResponse<ProductDto> getProductById(@PathVariable("id") Integer id) {
        ApiResponse<ProductDto> response = new ApiResponse<>();
        ProductDto productDto = glovoService.getProductById(id);
        if (productDto != null) {
            response.setSuccess(true);
            response.setData(productDto);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @PostMapping("/products")
    public ApiResponse<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> response = new ApiResponse<>();
        glovoService.createProduct(productDto);
        if (glovoService.getProductById(productDto.getId()).equals(productDto)) {
            response.setSuccess(true);
            response.setData(productDto);
            response.setMessages(Stream.of(HttpStatus.CREATED.toString()).toList());
        }
        return response;
    }

    @PutMapping("/products/{id}")
    public ApiResponse<ProductDto> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) {
        ApiResponse<ProductDto> response = new ApiResponse<>();
        glovoService.updateProduct(id, productDto);
        if (glovoService.getProductById(id).equals(productDto)) {
            response.setSuccess(true);
            response.setData(productDto);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

    @DeleteMapping("/products/{id}")
    public ApiResponse<ProductDto> deleteProduct(@PathVariable("id") Integer id) {
        ApiResponse<ProductDto> response = new ApiResponse<>();
        glovoService.deleteProduct(id);
        try {
            glovoService.getProductById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            response.setSuccess(true);
            response.setMessages(Stream.of(HttpStatus.OK.toString()).toList());
        }
        return response;
    }

}
