package com.bluejtitans.smarttradebackend.orders.repository;

import com.bluejtitans.smarttradebackend.lists.model.Wishlist;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrderByClientEmail(String clientEmail);
    List<Order> findAllByClientEmail(String clientEmail);
}
