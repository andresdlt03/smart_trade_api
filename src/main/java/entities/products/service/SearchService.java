package entities.products.service;

import java.util.List;
import entities.products.model.*;

public interface SearchService {
    List<Product> searchProducts(String query, String category, Double minPrice, Double maxPrice);
}

