package com.store.service.impl;

import com.store.dto.CustomerDTO;
import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }

    public Customer save(CustomerDTO userDto) {
        Customer customer = new Customer();
        customer.setUsername(userDto.getUsername());
        customer.setPassword(userDto.getPassword());
        customer.setFullname(userDto.getFullname());
        customer.setPhone(userDto.getPhone());
        customer.setEmail(userDto.getEmail());
        return customerRepository.save(customer);
    }
}