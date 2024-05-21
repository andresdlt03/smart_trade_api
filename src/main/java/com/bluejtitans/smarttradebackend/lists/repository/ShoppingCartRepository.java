package com.bluejtitans.smarttradebackend.lists.repository;

import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCartProduct, Long> {
}
