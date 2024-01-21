/*
package com.hillel.glovo.repository.mappers;

import com.hillel.glovo.dto.order.ProductDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDtoRowMapper implements RowMapper<ProductDto> {

    @Override
    public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductDto productDto = new ProductDto();
        productDto.setId(rs.getInt("id"));
        productDto.setName(rs.getString("name"));
        productDto.setCost(rs.getDouble("cost"));

        return productDto;
    }
}
*/
