package com.store.service;

import com.store.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

    Customer create(Customer customer);

    Customer update(Customer customer);

    void delete(int id);

    void updateToken(String token, String email) throws Exception;

    Customer getByToken(String token);

    void updatePassword(Customer customer, String newPassword);

    void changePassword(Customer customer, String newPassword);
}
