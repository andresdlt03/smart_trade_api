package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.FoodProduct;
import org.springframework.data.repository.CrudRepository;

public interface FoodProductRepository extends CrudRepository<FoodProduct, Long> {
}
