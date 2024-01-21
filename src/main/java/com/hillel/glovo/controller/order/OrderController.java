package com.hillel.glovo.controller.order;

import com.hillel.glovo.controller.response.ApiResponse;
import com.hillel.glovo.dto.order.OrderDto;
import com.hillel.glovo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @CrossOrigin("*")
    @GetMapping()
    public ApiResponse<List<OrderDto>> getOrders() {
        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        List<OrderDto> orders = orderService.getOrders();
        if (!CollectionUtils.isEmpty(orders)) {
            response.setSuccess(true);
            response.setData(orders);
        }
        return response;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return (ResponseEntity<OrderDto>) ResponseEntity.notFound();
    }

    @CrossOrigin("*")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrderById(@PathVariable("orderId") Integer orderId, @RequestBody OrderDto orderDto) {
        orderService.update(orderId, orderDto);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("orderId") Integer orderId) {
        orderService.delete(orderId);
    }
}
