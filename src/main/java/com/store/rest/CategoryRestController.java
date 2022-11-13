package com.store.rest;

import com.store.entity.Category;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/category")
public class CategoryRestController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> list = categoryRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        Category findId = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable("id") int id, @RequestBody Category category) {
        Category findId = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        categoryRepository.save(category);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Category findId = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
        categoryRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
