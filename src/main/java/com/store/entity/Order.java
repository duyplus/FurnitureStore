package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "shipped_date")
    private Date shippedDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
}