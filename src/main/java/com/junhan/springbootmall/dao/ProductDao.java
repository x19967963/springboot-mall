package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.model.Product;

public interface ProductDao {
    public Product getProductById(Integer id);
}
