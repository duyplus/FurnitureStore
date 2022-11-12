package com.store;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "H Furniture OpenAPI", version = "2.0", description = "A powerful yet easy-to-use suite of API developer tools."))
public class FurnitureStoreApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FurnitureStoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FurnitureStoreApplication.class, args);
    }
}
