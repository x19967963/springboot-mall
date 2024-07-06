package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProducts();
    public Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
