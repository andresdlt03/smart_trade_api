package com.bluejtitans.smarttradebackend.products.controller;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.service.ProductService;
import com.bluejtitans.smarttradebackend.products.service.SearchService;
import com.bluejtitans.smarttradebackend.products.service.FilterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SearchService searchService;
    private final FilterService filterService;

    @Autowired
    public ProductController(ProductService productService, SearchService searchService, FilterService filterService) {
        this.productService = productService;
        this.searchService = searchService;
        this.filterService = filterService;
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
        @RequestParam(required = false) String query) {
        List<Product> products = searchService.searchProducts(query);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        List<Product> products = filterService.filterProducts(category, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}

