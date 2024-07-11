package com.junhan.springbootmall.model;

import com.junhan.springbootmall.dto.OrderDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query("SELECT COUNT(o) FROM Order o WHERE o.userId = :userId")
    Integer countOrders(@Param("userId") Integer userId);
    @Query("SELECT new com.junhan.springbootmall.model.Order(o.orderId, o.userId, o.createdDate, o.lastModifiedDate, o.totalAmount) " +
            "FROM Order o WHERE o.userId = :userId ORDER BY o.createdDate DESC")
    List<Order> getOrdersByUserId(@Param("userId") Integer userId);


}
