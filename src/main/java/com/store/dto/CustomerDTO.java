package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.store.entity.Customer} entity
 */
@AllArgsConstructor
@Getter
public class CustomerDTO implements Serializable {
    private final Integer id;
    @Size(max = 100)
    @NotNull
    private final String username;
    @Size(max = 255)
    @NotNull
    private final String password;
    @Size(max = 255)
    @NotNull
    private final String fullname;
    @Size(max = 15)
    @NotNull
    private final String phone;
    @Size(max = 255)
    @NotNull
    private final String email;
    private final Date birthday;
    @Size(max = 255)
    private final String street;
    @Size(max = 100)
    private final String city;
    @Size(max = 255)
    private final String image;
    @Size(max = 20)
    private final String token;
}