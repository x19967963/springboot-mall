package com.junhan.springbootmall.service.Impl;

import com.junhan.springbootmall.constant.ProductCategory;
import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProductsByCategory(ProductCategory category, String orderBy, String sort) {
        return productDao.getProductsByCategory(category, orderBy, sort);
    }

    @Override
    public List<Product> getProductsBySearch(String search, String orderBy, String sort) {
        return productDao.getProductsBySearch(search, orderBy, sort);
    }

    @Override
    public List<Product> getProductsByCategoryAndSearch(ProductCategory category, String search,String orderBy, String sort) {
        return productDao.getProductsByCategoryAndSearch(category, search, orderBy, sort);
    }

    @Override
    public List<Product> getAllProducts(String orderBy, String sort) {
        return productDao.getAllProducts(orderBy, sort);
    }

    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }
}
