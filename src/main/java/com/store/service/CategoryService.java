package com.store.service;

import com.store.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}
