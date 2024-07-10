package com.junhan.springbootmall.service.Impl;

import com.junhan.springbootmall.dao.OrderDao;
import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.dto.BuyItem;
import com.junhan.springbootmall.dto.CreateOrderRequest;
import com.junhan.springbootmall.dto.OrderDTO;
import com.junhan.springbootmall.dto.OrderItemDTO;
import com.junhan.springbootmall.model.Order;
import com.junhan.springbootmall.model.OrderItem;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    @Override
    public OrderDTO getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItemDTO> orderItemList = orderDao.getOrderItemsByOrderId(orderId);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setUserId(order.getUserId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setCreatedDate(order.getCreatedDate());
        orderDTO.setOrderItems(orderItemList);
        return orderDTO;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem: createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總價格
            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount += amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }


        Integer orderID = orderDao.createOrder(userId, totalAmount);
        orderDao.createOrderItems(orderID, orderItemList);
        return orderID;
    }
}
