package com.store.repository;

import com.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface StoreRepository extends JpaRepository<Store, Integer> {
}