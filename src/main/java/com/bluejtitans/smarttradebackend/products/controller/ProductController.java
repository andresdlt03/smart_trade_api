package com.bluejtitans.smarttradebackend.products.controller;

import com.bluejtitans.smarttradebackend.exception.InvalidProductFormatException;
import com.bluejtitans.smarttradebackend.exception.UserNotRegisteredException;
import com.bluejtitans.smarttradebackend.products.dto.requests.AvailabilityDTO;
import com.bluejtitans.smarttradebackend.products.dto.requests.InfoDTO;
import com.bluejtitans.smarttradebackend.products.dto.requests.ProductDTO;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityBySeller;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityWithCategory;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductWithCategory;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.model.ProductFactory;
import com.bluejtitans.smarttradebackend.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) { this.productService = productService; }

    @PostMapping("/{productCategory}")
    public ResponseEntity<String> createProduct(@PathVariable String productCategory, @RequestBody ProductDTO productBody){
        InfoDTO productInfoDTO = productBody.info;
        AvailabilityDTO productAvailabilityDTO = productBody.availability;
        try {
            Product product = (Product) ProductFactory.createProductFromDTO(productCategory, productInfoDTO);
            ProductAvailability productAvailability = new ProductAvailability();
            productAvailability.setPrice(productAvailabilityDTO.getPrice());
            productAvailability.setStock(productAvailabilityDTO.getStock());
            productService.saveProduct(product, productAvailability, productAvailabilityDTO.getSellerEmail());
            return ResponseEntity.created(null).body(product.getName());
        } catch(UserNotRegisteredException | InvalidProductFormatException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("An unknown error occurred while creating the product");
        }
    }

    @GetMapping("/unverified")
    public ResponseEntity<List<ProductWithCategory>> getUnverifiedProducts() {
        return ResponseEntity.ok(productService.getProductsByVerified(false));
    }

    @GetMapping("/verified")
    public ResponseEntity<List<ProductWithCategory>> getVerifiedProducts() {
        return ResponseEntity.ok(productService.getProductsByVerified(true));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductWithCategory> getProductById(@PathVariable String productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/{productId}/availabilities")
    public ResponseEntity<List<ProductAvailabilityBySeller>> getProductAvailabilities(@PathVariable String productId){
        return ResponseEntity.ok(productService.getProductAvailabilities(productId));
    }

    @GetMapping("/seller/{sellerEmail}")
    public ResponseEntity<List<ProductAvailabilityWithCategory>> getProductsBySeller(@PathVariable String sellerEmail){
        return ResponseEntity.ok(productService.getProductsBySeller(sellerEmail));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> verifyProduct(@PathVariable String productId, @RequestBody VerifyProductRequest verifyProductRequest) {
        try {
            productService.setProductVerification(productId, verifyProductRequest.getVerify());
            return ResponseEntity.ok("Product " + productId + " verified");
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.badRequest().body("An unknown error occurred while verifying the product");
        }
    }
}

