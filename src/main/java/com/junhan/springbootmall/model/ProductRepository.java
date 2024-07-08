package com.junhan.springbootmall.model;

import com.junhan.springbootmall.constant.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    // 根據 ProductCategory 查找產品
    List<Product> findByCategory(ProductCategory category);
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.productName LIKE %:search%")
    List<Product> findByCategoryAndSearch(ProductCategory category, String search);
    // 根據名稱模糊查詢產品
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:search%")
    List<Product> findBySearch(String search);

}
