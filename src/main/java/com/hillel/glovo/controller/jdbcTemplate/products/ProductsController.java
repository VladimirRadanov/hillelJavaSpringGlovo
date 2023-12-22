package com.hillel.glovo.controller.jdbcTemplate.products;

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
@RequestMapping("/api/v1/products")
@RestController
public class ProductsController {

    private final GlovoService glovoService;

    @GetMapping()
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

    @GetMapping("/{id}")
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

    @PostMapping()
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

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
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
