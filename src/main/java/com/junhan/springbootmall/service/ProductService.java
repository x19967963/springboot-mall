package com.junhan.springbootmall.service;

import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

public interface ProductService {
    public Product getProductById(Integer id);
    public Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
}
