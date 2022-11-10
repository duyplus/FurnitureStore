package com.store.service;

import com.store.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer create(Customer customer);

    Customer update(Customer customer);

    void delete(int id);
}
