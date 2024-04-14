package entities.products.repository;

import ourProducts.model.Product;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategory(String category);
    List<Product> findByNameOrDescription(String query, String query1);
}
