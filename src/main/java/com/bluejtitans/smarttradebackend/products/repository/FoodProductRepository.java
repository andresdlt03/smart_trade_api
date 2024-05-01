package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodProductRepository extends CrudRepository<Food, Long> {
}
