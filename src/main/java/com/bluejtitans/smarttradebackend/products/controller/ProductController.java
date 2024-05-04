package com.bluejtitans.smarttradebackend.products.controller;
import java.util.List;

import com.bluejtitans.smarttradebackend.products.dto.ProductDTO;
import com.bluejtitans.smarttradebackend.products.model.IProduct;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.model.ProductFactory;
import com.bluejtitans.smarttradebackend.products.service.ProductService;
import com.bluejtitans.smarttradebackend.products.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping("/{productCategory}")
    public ResponseEntity<String> createProduct(@PathVariable String productCategory, @RequestBody ProductDTO productBody){
        try {
            Product product = (Product) ProductFactory.createProductFromDTO(productCategory, productBody);
            ProductAvailability productAvailability = new ProductAvailability();
            productAvailability.setPrice(productBody.getPrice());
            productAvailability.setStock(productBody.getStock());
            productService.saveProduct(product, productAvailability, productBody.getSellerEmail());

            return ResponseEntity.created(null).body(product.getName());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Unknown error ocurred while creating product");
        }
    }
}

