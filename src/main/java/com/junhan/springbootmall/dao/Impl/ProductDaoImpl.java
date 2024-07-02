package com.junhan.springbootmall.dao.Impl;

import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }


}
