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

    public List<Product> searchProducts(String query, String category, Double minPrice, Double maxPrice) {
        /*
        List<Product> res = productRepository.findAll();
        if (query != null && !query.isEmpty()) {
            if (category != null && !category.isEmpty()) {
                if (minPrice != null){
                    if (maxPrice != null)
                        res = productRepository.findByNameAndCategoryAndPriceRange(query, category, minPrice, maxPrice);
                    else
                        res = productRepository.findByNameAndCategoryAndMinPrice(query, category, minPrice);
                } else if (maxPrice != null)
                    res = productRepository.findByNameAndCategoryAndMaxPrice(query, category, maxPrice);
                else
                    res = productRepository.findByNameAndCategory(query, category);
            } else if (minPrice != null){
                if (maxPrice != null)
                    res = productRepository.findByNameAndPriceRange(query, minPrice, maxPrice);
                else
                    res = productRepository.findByNameAndMinPrice(query, minPrice);
            } else if (maxPrice != null)
                res = productRepository.findByNameAndMaxPrice(query, maxPrice);
            else
                res = productRepository.findByName(query);
        }

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
         */
        return null;
    }
}

