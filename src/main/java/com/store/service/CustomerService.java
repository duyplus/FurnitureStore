package com.store.service;

import com.store.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer findByUsername(String username);

    Customer create(Customer customer);

    Customer update(Customer customer);

    void delete(int id);

    Customer findUsernameByEmail(String email);

    Customer findByEmail(String email);

    Customer findByToken(String token);

    void changePassword(Customer customer, String newPassword);
}
