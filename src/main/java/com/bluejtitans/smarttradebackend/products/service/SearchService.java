package com.bluejtitans.smarttradebackend.products.service;

import com.bluejtitans.smarttradebackend.products.model.Product;
import java.util.List;

public interface SearchService {
    List<Product> searchProducts(String query);
}

