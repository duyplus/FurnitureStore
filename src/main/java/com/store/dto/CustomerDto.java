package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.store.entity.Customer} entity
 */
@AllArgsConstructor
@Getter
public class CustomerDto implements Serializable {
    private final Integer id;
    @Size(max = 255)
    @NotNull
    private final String fullname;
    @Size(max = 15)
    @NotNull
    private final String phone;
    @Size(max = 255)
    @NotNull
    private final String email;
    @Size(max = 255)
    private final String street;
    @Size(max = 100)
    private final String city;
    @Size(max = 30)
    private final String state;
    @Size(max = 6)
    private final String zipCode;
}