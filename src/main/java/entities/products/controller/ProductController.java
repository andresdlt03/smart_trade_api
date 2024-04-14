package entities.products.controller;
import java.util.List;

import entities.products.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import ourProducts.model.*;
import ourProducts.Factory.ProductFactory;

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
    @GetMapping()
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        List<Product> products = searchService.searchProducts(query, category, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/{productType}")
    public ResponseEntity<String> createProduct(@PathVariable String productType, @RequestBody String productJson){
        try {
            productService.createProduct(ProductFactory.createProduct(productType, productJson));
        } catch(JsonProcessingException e){
            return ResponseEntity.badRequest().body("Invalid user data: " + e.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Unknown exception occurred: " + e.getMessage());
        }
        return ResponseEntity.created(null).body("product.getName()");
    }
}

