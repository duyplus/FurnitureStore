package com.store.service;

import java.util.List;

public interface StaffService {

    List<Staff> findAll();

    Staff findById(int id);

    Staff create(Staff staff);

    Staff update(Staff staff);

    void delete(int id);
}
