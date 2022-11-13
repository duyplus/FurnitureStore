package com.store.rest;

import com.store.entity.Stock;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/stock")
public class StockRestController {
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        List<Stock> list = stockRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Stock>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Stock>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Stock> getById(@PathVariable int id) {
        Stock findId = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Stock create(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @PutMapping("{id}")
    public ResponseEntity<Stock> update(@PathVariable("id") int id, @RequestBody Stock stock) {
        Stock findId = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not exist with id: " + id));
        stockRepository.save(stock);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Stock findId = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not exist with id: " + id));
        stockRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
