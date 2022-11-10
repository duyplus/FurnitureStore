package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class FurnitureStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(FurnitureStoreApplication.class, args);
    }
}
