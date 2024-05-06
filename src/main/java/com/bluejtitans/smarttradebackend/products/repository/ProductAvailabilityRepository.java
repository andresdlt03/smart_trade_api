package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductAvailabilityRepository extends CrudRepository<ProductAvailability, String>{

    @Query("SELECT pa FROM ProductAvailability pa WHERE pa.id.product.name = ?1")
    List<ProductAvailability> findAllByProductId(String productId);

    @Query("SELECT pa FROM ProductAvailability pa WHERE pa.id.seller.email = ?1")
    List<ProductAvailability> findAllBySellerId(String sellerId);
}
