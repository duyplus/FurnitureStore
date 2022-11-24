package com.store.rest;

import com.store.entity.Staff;
import com.store.exception.ResourceNotFoundException;
import com.store.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/staff")
public class StaffRestController {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder pe;

    @GetMapping
    public ResponseEntity<List<Staff>> getAll() {
        List<Staff> list = staffRepository.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Staff>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Staff>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Staff> getById(@PathVariable int id) {
        Staff findId = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id: " + id));
        return ResponseEntity.ok(findId);
    }

    @PostMapping
    public Staff create(@RequestBody Staff staff) {
        staff.setPassword(pe.encode(staff.getPassword()));
        return staffRepository.save(staff);
    }

    @PutMapping("{id}")
    public ResponseEntity<Staff> update(@PathVariable("id") int id, @RequestBody Staff staff) {
        Staff findId = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id: " + id));
        staff.setPassword(pe.encode(staff.getPassword()));
        staffRepository.save(staff);
        return ResponseEntity.ok(findId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Staff findId = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not exist with id: " + id));
        staffRepository.delete(findId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
