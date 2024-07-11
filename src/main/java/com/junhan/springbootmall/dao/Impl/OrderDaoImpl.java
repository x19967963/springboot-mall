package com.junhan.springbootmall.dao.Impl;

import com.junhan.springbootmall.dao.OrderDao;
import com.junhan.springbootmall.dto.CreateOrderRequest;
import com.junhan.springbootmall.dto.OrderDTO;
import com.junhan.springbootmall.dto.OrderItemDTO;
import com.junhan.springbootmall.dto.OrderQueryParams;
import com.junhan.springbootmall.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        Integer total = orderRepository.countOrders(orderQueryParams.getUserId());
        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        return orderRepository.getOrdersByUserId(orderQueryParams.getUserId());
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(Integer orderId) {
        List<Object[]> results = orderItemRepository.getOrderItemsByOrderId(orderId);
        List<OrderItemDTO> orderItems = new ArrayList<>();
        for (Object[] result : results) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setOrderItemId((Integer) result[0]);
            orderItemDTO.setOrderId((Integer) result[1]);
            orderItemDTO.setProductId((Integer) result[2]);
            orderItemDTO.setQuantity((Integer) result[3]);
            orderItemDTO.setAmount((Integer) result[4]);
            orderItemDTO.setProductName((String) result[5]);
            orderItemDTO.setImageUrl((String) result[6]);
            orderItems.add(orderItemDTO);
        }
        return orderItems;
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setLastModifiedDate(LocalDateTime.now());
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            OrderItem orderItemAll = new OrderItem();
            orderItemAll.setOrderId(orderId);
            orderItemAll.setAmount(orderItem.getAmount());
            orderItemAll.setQuantity(orderItem.getQuantity());
            orderItemAll.setProductId(orderItem.getProductId());
            orderItemRepository.save(orderItemAll);
        }

    }
}
