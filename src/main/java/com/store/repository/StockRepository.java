package com.store.repository;

import com.store.entity.Stock;
import com.store.entity.StockId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, StockId> {
}