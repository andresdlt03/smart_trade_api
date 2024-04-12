package com.bluejtitans.smarttradebackend.products.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;

@Service
public class SearchServiceImpl implements SearchService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query) {
        if (query != null && !query.isEmpty()) {
            return productRepository.findByName(query);
        } else {
            return productRepository.findAll();
        }
    }
}

