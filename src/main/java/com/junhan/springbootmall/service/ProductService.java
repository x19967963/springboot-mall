package com.junhan.springbootmall.service;

import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProductById(Integer id);
    public Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
