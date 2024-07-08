package com.junhan.springbootmall.dao.Impl;

import com.junhan.springbootmall.constant.ProductCategory;
import com.junhan.springbootmall.dao.ProductDao;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProductsByCategoryAndSearch(ProductCategory category, String search, String orderBy, String sort) {
        List<Product> products = productRepository.findByCategoryAndSearch(category, search);
        return sortProducts(products, orderBy, sort);
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory category, String orderBy, String sort) {
        List<Product> products = productRepository.findByCategory(category);
        return sortProducts(products, orderBy, sort);
    }

    @Override
    public List<Product> getProductsBySearch(String search, String orderBy, String sort) {
        List<Product> products = productRepository.findBySearch(search);
        return sortProducts(products, orderBy, sort);
    }

    @Override
    public List<Product> getAllProducts(String orderBy, String sort) {
        List<Product> products = (List<Product>) productRepository.findAll();
        return sortProducts(products, orderBy, sort);
    }

    private List<Product> sortProducts(List<Product> products, String orderBy, String sort) {
        Comparator<Product> comparator;

        switch (orderBy.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(Product::getProductName);
                break;
            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case "created_date":
            default:
                comparator = Comparator.comparing(Product::getCreatedDate);
                break;
        }

        if ("desc".equalsIgnoreCase(sort)) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);
        return products;
    }



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
        Product productValid =  productRepository.findById(productId).orElse(null);
        if (productValid != null){
            product.setProductId(productId);
            productRepository.save(product);
        }

    }

    @Override
    public void deleteProductById(Integer productId) {
        productRepository.deleteById(productId);
    }
}
