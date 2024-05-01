package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.Clothes;
import org.springframework.data.repository.CrudRepository;

public interface FashionProductRepository extends CrudRepository<Clothes, Long> {
}