package com.junhan.springbootmall.dao;

import com.junhan.springbootmall.constant.ProductCategory;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductsByCategory(ProductCategory category, String orderBy, String sort);

    public List<Product> getProductsBySearch(String search,String orderBy, String sort);

    public List<Product> getProductsByCategoryAndSearch(ProductCategory category, String search,String orderBy, String sort);

    public List<Product> getAllProducts(String orderBy, String sort);
    public Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void updateStock(Integer productId, Integer stock);
    void deleteProductById(Integer productId);
}
