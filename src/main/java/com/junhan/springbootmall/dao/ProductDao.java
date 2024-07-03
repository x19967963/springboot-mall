package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

public interface ProductDao {
    public Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
}
