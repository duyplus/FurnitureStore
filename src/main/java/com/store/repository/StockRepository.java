package com.store.repository;

import com.store.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface StockRepository extends JpaRepository<Stock, Integer> {
}