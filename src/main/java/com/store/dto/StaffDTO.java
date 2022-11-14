package com.store.dto;

import com.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.store.entity.Staff} entity
 */
@AllArgsConstructor
@Getter
public class StaffDTO implements Serializable {
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
    @Size(max = 255)
    @NotNull
    private final String email;
    @Size(max = 15)
    private final String phone;
    private final Short active;
    @NotNull
    private final Store store;
    private final Integer managerId;
}