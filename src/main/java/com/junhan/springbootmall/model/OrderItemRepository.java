package com.junhan.springbootmall.model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    @Query(value = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url " +
            "FROM order_item AS oi " +
            "LEFT JOIN product AS p ON oi.product_id = p.product_id " +
            "WHERE oi.order_id = :orderId", nativeQuery = true)
    List<Object[]> getOrderItemsByOrderId(@Param("orderId") Integer orderId);
}
