package com.bluejtitans.smarttradebackend.products.service;

import com.bluejtitans.smarttradebackend.products.model.Product;
import java.util.List;

public interface FilterService {
    List<Product> filterProducts(String category, Double minPrice, Double maxPrice);
}

