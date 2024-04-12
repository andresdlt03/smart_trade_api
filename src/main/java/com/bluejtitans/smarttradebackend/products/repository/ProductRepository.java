package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price BETWEEN ?2 AND ?3")
    List<Product> findByCategoryAndPriceRange(String category, double minPrice, double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price >= ?2")
    List<Product> findByCategoryAndMinPrice(String category, double minPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1 AND p.price <= ?2")
    List<Product> findByCategoryAndMaxPrice(String category, double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.price >= ?1")
    List<Product> findByMinPrice(double minPrice);
    @Query("SELECT p FROM Product p WHERE p.price >= ?1")
    List<Product> findByMaxPrice(double maxPrice);
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findByCategory(String category);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Product> findByName(String name);
}
