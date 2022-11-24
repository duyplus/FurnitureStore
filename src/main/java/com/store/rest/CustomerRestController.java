package com.store.rest;

import com.store.entity.Customer;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder pe;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> list = customerRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        Customer findId = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        customer.setPassword(pe.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") int id, @RequestBody Customer customer) {
        Customer findId = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        customer.setPassword(pe.encode(customer.getPassword()));
        customerRepository.save(customer);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Customer findId = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        customerRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
