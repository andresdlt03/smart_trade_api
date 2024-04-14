package entities.products.service;

import java.util.List;
import ourProducts.model.*;

public interface SearchService {
    List<Product> searchProducts(String query, String category, Double minPrice, Double maxPrice);
}

