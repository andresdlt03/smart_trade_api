package com.bluejtitans.smarttradebackend.products.repository;

import org.springframework.data.repository.CrudRepository;
import com.bluejtitans.smarttradebackend.products.model.FashionProduct;

public interface FashionProductRepository extends CrudRepository<FashionProduct, Long> {
}