package com.store.repository;

import com.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
//    List<Order> findByUsername(String username);
}