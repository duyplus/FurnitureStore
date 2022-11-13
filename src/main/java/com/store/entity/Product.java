package com.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "model_year", nullable = false)
    private Short modelYear;

    @NotNull
    @Column(name = "list_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal listPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}