package com.junhan.springbootmall.model;

import com.junhan.springbootmall.dto.OrderItemDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "total_amount")
    private Integer totalAmount;
    @Column(name = "created_date")
    private LocalDateTime createdDate;



    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        if (this.createdDate == null) {
            this.createdDate = createdDate;
        }
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
