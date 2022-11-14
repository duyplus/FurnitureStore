package com.store.repository;

import com.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    List<Product> findByCategoryId(String s);
}