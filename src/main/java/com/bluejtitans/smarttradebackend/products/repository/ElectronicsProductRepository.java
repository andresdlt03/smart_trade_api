package com.bluejtitans.smarttradebackend.products.repository;

import com.bluejtitans.smarttradebackend.products.model.Technology;
import org.springframework.data.repository.CrudRepository;

public interface ElectronicsProductRepository extends CrudRepository<Technology, Long> {
}
