/*
package com.hillel.glovo.repository.mappers;

import com.hillel.glovo.dto.order.OrderDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDtoRowMapper implements RowMapper<OrderDto> {

    @Override
    public OrderDto mapRow(ResultSet rs, int rowMapper) throws SQLException {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(rs.getInt("id"));
        orderDto.setDate(rs.getDate("date").toLocalDate());
        orderDto.setCost(rs.getDouble("cost"));

        return orderDto;
    }

}
*/
