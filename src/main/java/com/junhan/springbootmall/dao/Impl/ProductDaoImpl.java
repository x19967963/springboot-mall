package com.junhan.springbootmall.dao.Impl;

import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Product product = convertToProduct(productRequest);
        productRepository.save(product);
        return product.getProductId();
    }

    private Product convertToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setLastModifiedDate(LocalDateTime.now());
        return product;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setLastModifiedDate(LocalDateTime.now());  // 使用 LocalDateTime.now() 設置當前日期為最後修改日期
        //判斷目前更新的id是否有在資料庫，確保更新資料庫而非創立
        Product product1 =  productRepository.findById(productId).orElse(null);
        if (product1 != null){
            product.setProductId(productId);
            productRepository.save(product);
        }

    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }
}
