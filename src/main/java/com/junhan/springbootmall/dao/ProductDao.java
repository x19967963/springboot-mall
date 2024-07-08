package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.constant.ProductCategory;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductsByCategory(ProductCategory category);

    public List<Product> getProductsBySearch(String search);

    public List<Product> getProductsByCategoryAndSearch(ProductCategory category, String search);

    public List<Product> getAllProducts();
    public Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
