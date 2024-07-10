package com.junhan.springbootmall.service;

import com.junhan.springbootmall.dto.CreateOrderRequest;
import com.junhan.springbootmall.dto.OrderDTO;
import com.junhan.springbootmall.model.Order;

public interface OrderService {
    OrderDTO getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
