package com.store.repository;

import com.store.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface StaffRepository extends JpaRepository<Staff, Integer> {
}