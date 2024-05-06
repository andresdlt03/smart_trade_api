package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE p.verified = ?1")
    List<Product> findAllByVerified(boolean verified);
}
