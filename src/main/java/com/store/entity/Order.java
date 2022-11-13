package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", columnDefinition = "tinyint not null")
    private Short status;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @Column(name = "shipped_date")
    private Instant shippedDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}