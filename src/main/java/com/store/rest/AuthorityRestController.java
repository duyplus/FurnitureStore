package com.store.rest;

import com.store.entity.Authority;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authority")
public class AuthorityRestController {
    @Autowired
    AuthorityRepository authorityRepository;

    @GetMapping
    public ResponseEntity<List<Authority>> getAll() {
        List<Authority> list = authorityRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Authority>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Authority>>(list, HttpStatus.OK);
    }

    @PostMapping
    public Authority create(@RequestBody Authority authority) {
        return authorityRepository.save(authority);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Authority findId = authorityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Authority not exist with id: " + id));
        authorityRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
