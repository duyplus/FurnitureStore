package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Nationalized
    @Column(name = "street")
    private String street;

    @Size(max = 255)
    @Nationalized
    @Column(name = "city")
    private String city;

    @Size(max = 30)
    @Nationalized
    @Column(name = "state", length = 30)
    private String state;

    @Size(max = 6)
    @Column(name = "zip_code", length = 6)
    private String zipCode;
}