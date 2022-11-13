package com.store.rest;

import com.store.entity.Store;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/store")
public class StoreRestController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping
    public ResponseEntity<List<Store>> getAll() {
        List<Store> list = storeRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Store>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Store>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Store> getById(@PathVariable int id) {
        Store findId = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Store create(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @PutMapping("{id}")
    public ResponseEntity<Store> update(@PathVariable("id") int id, @RequestBody Store store) {
        Store findId = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not exist with id: " + id));
        storeRepository.save(store);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Store findId = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not exist with id: " + id));
        storeRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
