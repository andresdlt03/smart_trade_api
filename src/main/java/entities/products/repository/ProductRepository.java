package entities.products.repository;

import ourProducts.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //region with name
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.category = ?2 AND p.price BETWEEN ?3 AND ?4")
    List<Product> findByNameAndCategoryAndPriceRange(String name, String category, double minPrice, double maxPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.category = ?2 AND p.price >= ?3")
    List<Product> findByNameAndCategoryAndMinPrice(String name, String category, double minPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.category = ?2 AND p.price <= ?3")
    List<Product> findByNameAndCategoryAndMaxPrice(String name, String category, double minPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.category = ?2")
    List<Product> findByNameAndCategory(String name, String category);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.price BETWEEN ?2 AND ?3")
    List<Product> findByNameAndPriceRange(String name, double minPrice, double maxPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.price >= ?2")
    List<Product> findByNameAndMinPrice(String name, double minPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%')) AND p.price <= ?2")
    List<Product> findByNameAndMaxPrice(String name, double maxPrice);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Product> findByName(String name);
    //endregion

    //region with category
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price BETWEEN ?2 AND ?3")
    List<Product> findByCategoryAndPriceRange(String category, double minPrice, double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price >= ?2")
    List<Product> findByCategoryAndMinPrice(String category, double minPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price <= ?2")
    List<Product> findByCategoryAndMaxPrice(String category, double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findByCategory(String category);
    //endregion

    //region only price
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.price >= ?1")
    List<Product> findByMinPrice(double minPrice);
    @Query("SELECT p FROM Product p WHERE p.price >= ?1")
    List<Product> findByMaxPrice(double maxPrice);
    //endregion
}
