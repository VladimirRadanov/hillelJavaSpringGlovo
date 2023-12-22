package com.hillel.glovo.repository.data;

import com.hillel.glovo.entity.data.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("SELECT * FROM orders WHERE cost = :cost")
    List<Order> findOrdersByCustomCostRestriction(Double cost);

    Page<Order> findAll(Pageable pageable);
}
