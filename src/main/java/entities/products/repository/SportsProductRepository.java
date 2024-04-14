package entities.products.repository;

import org.springframework.data.repository.CrudRepository;

import entities.products.model.SportsProduct;

public interface SportsProductRepository extends CrudRepository<SportsProduct, Long> {
}
