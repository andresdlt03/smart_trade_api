package com.bluejtitans.smarttradebackend.lists.repository;

import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//Esto es para pushear.
@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCartProduct, Long> {
}
