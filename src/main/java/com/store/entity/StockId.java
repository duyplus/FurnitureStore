package com.store.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class StockId implements Serializable {
    @NotNull
    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;
}