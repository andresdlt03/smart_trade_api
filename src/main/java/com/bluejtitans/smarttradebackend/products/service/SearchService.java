package com.bluejtitans.smarttradebackend.products.service;

import java.util.List;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchService(ProductRepository productRepository) { this.productRepository = productRepository; }
}

