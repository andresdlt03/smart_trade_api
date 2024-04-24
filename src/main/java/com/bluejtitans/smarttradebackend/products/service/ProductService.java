package com.bluejtitans.smarttradebackend.products.service;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProduct(String name) {
        return productRepository.findById(name).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(String name) {
        productRepository.deleteById(name);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public void updateProduct(String name, Product product) {
        Product p = productRepository.findById(name).orElse(null);
        if (p != null) {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setQuantity(product.getQuantity());
            p.setDescription(product.getDescription());
            p.setDataSheet(product.getDataSheet());
            p.setPhotos(product.getPhotos());
            productRepository.save(p);
        }
    }
}