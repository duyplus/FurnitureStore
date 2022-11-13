package com.store.rest;

import com.store.entity.Brand;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/brand")
public class BrandRestController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public ResponseEntity<List<Brand>> getAll() {
        List<Brand> list = brandRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Brand>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Brand>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> getById(@PathVariable int id) {
        Brand findId = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Brand create(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity<Brand> update(@PathVariable("id") int id, @RequestBody Brand brand) {
        Brand findId = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id: " + id));
        brandRepository.save(brand);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Brand findId = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not exist with id: " + id));
        brandRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
