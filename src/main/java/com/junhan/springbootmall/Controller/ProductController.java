package com.junhan.springbootmall.Controller;

import com.junhan.springbootmall.constant.ProductCategory;
import com.junhan.springbootmall.dto.ProductRequest;
import com.junhan.springbootmall.model.Product;
import com.junhan.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search
            ){
        List<Product> productList;
        if (category != null && search != null) {
            // 如果都有類別和搜索條件，調用帶有類別和搜索條件的服務方法
            productList = productService.getProductsByCategoryAndSearch(category, search);
        } else if (category != null) {
            // 如果只有類別，調用僅根據類別查詢的服務方法
            productList = productService.getProductsByCategory(category);
        } else if (search != null) {
            // 如果只有搜索條件，調用僅根據搜索條件查詢的服務方法
            productList = productService.getProductsBySearch(search);
        } else {
            // 如果都沒有，調用獲取所有產品的服務方法
            productList = productService.getAllProducts();
        }

        return ResponseEntity.status(HttpStatus.OK).body(productList);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product =  productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                                 @PathVariable Integer productId) {
        //查詢更新商品是否存在，不存在則返回404狀態碼
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //更新商品數據內容
        productService.updateProduct(productId,productRequest);
        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
