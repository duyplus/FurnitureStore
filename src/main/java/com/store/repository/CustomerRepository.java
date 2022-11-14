package com.store.repository;

import com.store.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findUsernameByEmail(String email);

    Customer findByUsername(String username);

    Customer findByEmail(String email);

    Customer findByToken(String token);
}