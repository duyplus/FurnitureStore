package com.store.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.entity.Order;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.OrderRepository;
import com.store.service.OrderService;
import com.store.temporary.OrderDATE;
import com.store.temporary.TopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/order")
public class OrderRestController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> list = orderRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getById(@PathVariable int id) {
        Order findId = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

//    @PostMapping
//    public Order create(@RequestBody Order order) {
//        return orderRepository.save(order);
//    }

    @PostMapping
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }

    @PutMapping("{id}")
    public ResponseEntity<Order> update(@PathVariable("id") int id, @RequestBody Order order) {
        Order findId = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + id));
        orderRepository.save(order);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Order findId = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id: " + id));
        orderRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("date")
    public List<OrderDATE> getOrderDate() {
        return orderRepository.getOrderDate();
    }

    @GetMapping("getTopOrder/{date}")
    public ResponseEntity<List<TopOrder>> getTopProduct(@PathVariable String date) {
        List<TopOrder> listOrder = orderRepository.getTopOrder(date);
        if (listOrder.isEmpty()) {
            return new ResponseEntity<List<TopOrder>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TopOrder>>(listOrder, HttpStatus.OK);
    }
}
