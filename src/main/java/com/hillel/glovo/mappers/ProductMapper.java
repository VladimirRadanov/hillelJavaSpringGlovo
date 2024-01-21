package com.hillel.glovo.mappers;

import com.hillel.glovo.dto.order.ProductDto;
import com.hillel.glovo.model.jpa.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto dto);
    List<ProductDto> toProductDtoList(List<Product> products);
    List<Product> toProductList(List<ProductDto> dtos);
}
