package com.store.service.impl;

import com.store.entity.Product;
import com.store.repository.ProductRepository;
import com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository dao;

	Boolean count = true;

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Page<Product> findAll(Integer page, Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		return dao.findAll(pageable);
	}

	@Override
	public Page<Product> findByField(Integer page, Integer limit, String field, String name) {
		if (field.equals("")) {
			Pageable pageable = PageRequest.of(page, limit);
			return dao.findAll(pageable);
		} else {
			if (count) {
				count = false;
				Pageable pageable = PageRequest.of(page, limit, Sort.by(Direction.ASC, field));
				return dao.findAll(pageable);
			} else {
				count = true;
				Pageable pageable = PageRequest.of(page, limit, Sort.by(Direction.DESC, field));
				return dao.findAll(pageable);
			}
		}
	}
}