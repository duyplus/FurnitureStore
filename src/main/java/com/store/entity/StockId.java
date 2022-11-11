package com.store.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockId implements Serializable {
    @NotNull
    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockId)) return false;
        StockId stockId = (StockId) o;
        return storeId.equals(stockId.storeId) && productId.equals(stockId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productId);
    }
}