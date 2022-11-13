package com.store.rest;

import com.store.entity.OrderDetail;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/orderdetail")
public class OrderDetailRestController {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAll() {
        List<OrderDetail> list = orderDetailRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OrderDetail>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getById(@PathVariable int id) {
        OrderDetail findId = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetail not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public OrderDetail create(@RequestBody OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable("id") int id, @RequestBody OrderDetail orderDetail) {
        OrderDetail findId = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetail not exist with id: " + id));
        orderDetailRepository.save(orderDetail);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        OrderDetail findId = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderDetail not exist with id: " + id));
        orderDetailRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
