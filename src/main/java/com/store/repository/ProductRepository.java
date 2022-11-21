package com.store.repository;

import com.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1")
    List<Product> findByCategoryId(String cid);

    @Modifying
    @Query(value = "SELECT TOP 8 * FROM (\n" +
            "    SELECT DISTINCT p.id, p.name, p.model_year, p.list_price, p.discount, p.image, p.description, p.brand_id, p.category_id \n" +
            "\tFROM order_details od\n" +
            "\tINNER JOIN products p ON od.product_id = p.id\n" +
            ") AS t\n" +
            "ORDER BY NEWID()", nativeQuery = true)
    List<Product> randProdInOd();

    @Modifying
    @Query(value = "SELECT TOP 8 * FROM (\n" +
            "    SELECT * FROM products\n" +
            "\tWHERE discount <= 20 AND discount > 0\n" +
            ") AS t\n" +
            "ORDER BY NEWID()", nativeQuery = true)
    List<Product> randProdLessThanOrEqual();
}