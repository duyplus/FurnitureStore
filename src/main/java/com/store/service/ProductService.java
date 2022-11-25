package com.store.service;

import com.store.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	List<Product> findAll();

	Optional<Product> findById(Integer id);

	Page<Product> findAll(Integer page, Integer limit);

	Page<Product> findByField(Integer page, Integer limit, String field, String name);
}