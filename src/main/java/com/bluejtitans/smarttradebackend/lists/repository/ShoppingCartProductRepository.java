package com.bluejtitans.smarttradebackend.lists.repository;

import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ShoppingCartProductRepository extends CrudRepository<ShoppingCartProduct, Long> {
}
