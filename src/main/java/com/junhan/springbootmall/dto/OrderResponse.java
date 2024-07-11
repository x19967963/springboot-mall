package com.junhan.springbootmall.dto;

import java.util.List;

public class OrderResponse {
    private List<OrderDTO> orders;
    private Integer totalCount;

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
