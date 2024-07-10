package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.dto.OrderDTO;
import com.junhan.springbootmall.dto.OrderItemDTO;
import com.junhan.springbootmall.model.Order;
import com.junhan.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderId);
    List<OrderItemDTO> getOrderItemsByOrderId(Integer orderId);
    Integer createOrder(Integer userId, Integer totalAmount);
    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
