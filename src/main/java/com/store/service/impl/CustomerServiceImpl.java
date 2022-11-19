package com.store.service.impl;

import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import com.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateToken(String token, String email) throws Exception {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            customer.setToken(token);
            customerRepository.save(customer);
        } else {
            throw new Exception("Cannot find any account with email: " + email);
        }
    }

    @Override
    public Customer getByToken(String token) {
        return customerRepository.findByToken(token);
    }

    @Override
    public void updatePassword(Customer customer, String newPassword) {
        customer.setPassword(newPassword);
        customer.setToken("token");
        customerRepository.save(customer);
    }

    @Override
    public void changePassword(Customer customer, String newPassword) {
        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }
}
