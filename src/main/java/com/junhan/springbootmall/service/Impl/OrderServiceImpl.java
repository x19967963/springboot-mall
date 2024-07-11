package com.junhan.springbootmall.service.Impl;

import com.junhan.springbootmall.dao.OrderDao;
import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.dao.UserDao;
import com.junhan.springbootmall.dto.*;
import com.junhan.springbootmall.model.Order;
import com.junhan.springbootmall.model.OrderItem;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.model.User;
import com.junhan.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public List<OrderDTO> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Order order : orderList) {
            OrderDTO orderDTO = convertToDTO(order); // Convert Order to OrderDTO
            List<OrderItemDTO> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            orderDTO.setOrderItems(orderItemList);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setCreatedDate(order.getCreatedDate());
        // Note: orderItems will be set separately after fetching from DAO
        return orderDTO;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

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
        //檢查User是否存在
        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("該userID{}不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (BuyItem buyItem: createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查商品是否存在、庫存是否足夠
            if (product == null) {
                log.warn("商品{}不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            Integer stock = product.getStock();
            if (stock == null || stock < buyItem.getQuantity()) {
                log.warn("商品{}庫存不足或庫存為null，無法購買，剩餘庫存{}，欲購買數量{}",
                        buyItem.getProductId(), stock, buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProductId(), stock - buyItem.getQuantity());


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
