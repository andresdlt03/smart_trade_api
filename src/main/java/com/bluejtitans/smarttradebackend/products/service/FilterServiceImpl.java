package com.bluejtitans.smarttradebackend.products.service;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    private final ProductRepository productRepository;

    @Autowired
    public FilterServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> filterProducts(String category, Double minPrice, Double maxPrice) {
        List<Product> res = productRepository.findAll();
        if (category != null && !category.isEmpty()) {
            if (minPrice != null){
                if (maxPrice != null)
                    res = productRepository.findByCategoryAndPriceRange(category, minPrice, maxPrice);
                else
                    res = productRepository.findByCategoryAndMinPrice(category, minPrice);
            } else if (maxPrice != null)
                res = productRepository.findByCategoryAndMaxPrice(category, maxPrice);
            else
                res = productRepository.findByCategory(category);
        }
        if (minPrice != null){
            if (maxPrice != null)
                res = productRepository.findByPriceBetween(minPrice, maxPrice);
            else
                res = productRepository.findByMinPrice(minPrice);
        }
        if (maxPrice != null)
            res = productRepository.findByMaxPrice(maxPrice);

        return res;
    }
}

