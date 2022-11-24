package com.store.rest;

import com.store.entity.Product;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.ProductRepository;
import com.store.temporary.TopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/product")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> list = productRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product product) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        productRepository.save(product);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Product findId = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        productRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("getTopProduct/{date}")
    public ResponseEntity<List<TopProduct>> getTopProduct(@PathVariable String date) {
        List<TopProduct> listProduct = productRepository.getTopProduct(date);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<List<TopProduct>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TopProduct>>(listProduct, HttpStatus.OK);
    }
}
