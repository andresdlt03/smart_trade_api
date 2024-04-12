package com.bluejtitans.smarttradebackend.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public void updateProduct(Long id, Product product) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            updateProductName(id, product.getName());
            updateProductPrice(id, product.getPrice());
            updateProductQuantity(id, product.getQuantity());
            updateProductDescription(id, product.getDescription());
            updateProductTechnicalSheet(id, product.getTechnicalSheet());
            updateProductImages(id, product.getImages());
        }
    }
    //region update
    public void updateProductName(Long id, String name) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setName(name);
            productRepository.save(p);
        }
    }
    public void updateProductPrice(Long id, double price) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setPrice(price);
            productRepository.save(p);
        }
    }
    public void updateProductQuantity(Long id, int quantity) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setQuantity(quantity);
            productRepository.save(p);
        }
    }
    public void updateProductTechnicalSheet(Long id, String technicalSheet) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setTechnicalSheet(technicalSheet);
            productRepository.save(p);
        }
    }
    public void updateProductDescription(Long id, String description) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setDescription(description);
            productRepository.save(p);
        }
    }
    public void updateProductImages(Long id, List<String> images) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setImages(images);
            productRepository.save(p);
        }
    }
    //endregion
}