package com.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.store.entity.Customer} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private Integer id;
    @Size(max = 100)
    @NotNull
    private String username;
    @Size(max = 255)
    @NotNull
    private String password;
    @Size(max = 255)
    @NotNull
    private String fullname;
    @Size(max = 15)
    @NotNull
    private String phone;
    @Size(max = 255)
    @NotNull
    private String email;
    private Date birthday;
    @Size(max = 255)
    private String street;
    @Size(max = 100)
    private String city;
    @Size(max = 255)
    private String image;
    @Size(max = 20)
    private String token;
}