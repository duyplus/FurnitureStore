package com.store.service;

import com.store.entity.Customer;

public interface AuthenticationService {
    boolean sendResetMail(String email);

    Customer findByToken(String token);

    boolean changePassword(Customer customer);
}
