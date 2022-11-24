package com.store.rest;

import com.store.entity.Role;
import com.store.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/role")
public class RoleRestController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        List<Role> list = roleRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Role>>(list, HttpStatus.OK);
    }

}