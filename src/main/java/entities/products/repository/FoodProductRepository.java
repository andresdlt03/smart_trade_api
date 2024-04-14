package entities.products.repository;

import org.springframework.data.repository.CrudRepository;

import entities.products.model.FoodProduct;

public interface FoodProductRepository extends CrudRepository<FoodProduct, Long> {
}
