package com.store.repository;

import com.store.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findUsernameByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.username = ?1")
    Customer findByUsername(String username);

    @Query("SELECT c FROM Customer c WHERE c.username = ?1 and c.password=?2")
    Customer getAccount(String username, String password);

    // Phuc vu viec gui mail
    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.token = ?1")
    public Customer findByToken(String token);
}