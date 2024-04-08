package entities.products.controller;
import java.util.List;

import entities.products.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.products.model.*;
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final SearchService searchService;

    @Autowired
    public ProductController(ProductService productService, SearchService searchService) {
        this.productService = productService;
        this.searchService = searchService;
    }
    @GetMapping("/api/products/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        List<Product> products = searchService.searchProducts(query, category, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}

