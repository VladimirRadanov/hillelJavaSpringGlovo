package com.hillel.glovo.repository.order.spring_jpa;

import com.hillel.glovo.model.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
