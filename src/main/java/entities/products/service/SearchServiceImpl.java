package entities.products.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import entities.products.model.*;
import entities.products.repository.ProductRepository;

@Service
public class SearchServiceImpl implements SearchService {

    private final ProductRepository productRepository;

    @Autowired
    public SearchServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query, String category, Double minPrice, Double maxPrice) {
        if (query != null && !query.isEmpty()) {
            return productRepository.findByNameOrDescription(query, query);
        } else if (category != null && !category.isEmpty()) {
            return productRepository.findByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } else {
            return productRepository.findAll();
        }
    }
}

