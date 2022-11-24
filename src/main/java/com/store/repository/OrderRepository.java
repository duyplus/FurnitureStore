package com.store.repository;

import com.store.entity.Order;
import com.store.temporary.OrderDATE;
import com.store.temporary.TopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(String username);

    @Modifying
    @Query(value = "select  FORMAT(o.order_date,'MM-yyyy') as orderDate FROM orders o GROUP BY FORMAT(o.order_date,'MM-yyyy')", nativeQuery = true)
    List<OrderDATE> getOrderDate();

    @Modifying
    @Query(value = "SELECT o.id as id, SUM(od.list_price) as order_price, o.order_date as order_date,\n" +
            "\t\to.shipped_date as ship_date , c.username as customer_name\n" +
            "\tFROM orders o\n" +
            "\tJOIN order_details od ON o.id = od.order_id\n" +
            "\tJOIN customers c ON c.id = o.customer_id\n" +
            "\tJOIN products as p ON p.id = od.product_id\n" +
            "\tWHERE FORMAT(cast(o.order_date as date),'MM-yyyy') = ?1\n" +
            "\tGROUP BY o.id, o.order_date, o.shipped_date, c.username\n" +
            "\tORDER BY o.order_date DESC", nativeQuery = true)
    List<TopOrder> getTopOrder(String date);
}