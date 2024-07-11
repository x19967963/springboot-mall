package com.junhan.springbootmall.Controller;

import com.junhan.springbootmall.dto.CreateOrderRequest;
import com.junhan.springbootmall.dto.OrderDTO;
import com.junhan.springbootmall.dto.OrderQueryParams;
import com.junhan.springbootmall.dto.OrderResponse;
import com.junhan.springbootmall.model.Order;
import com.junhan.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId, createOrderRequest);
        OrderDTO orderDTO = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<OrderResponse> getOrders(
            @PathVariable Integer userId){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);

        //取得order list
        List<OrderDTO> orderList = orderService.getOrders(orderQueryParams);

        //取得order總數
        Integer count = orderService.countOrder(orderQueryParams);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrders(orderList);
        orderResponse.setTotalCount(count);

        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);


    }


}
