package com.store.repository;

import com.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    List<Order> findByUsername(String username);
}