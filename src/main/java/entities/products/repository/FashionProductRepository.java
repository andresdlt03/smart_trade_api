package entities.products.repository;

import org.springframework.data.repository.CrudRepository;
import entities.products.model.FashionProduct;

public interface FashionProductRepository extends CrudRepository<FashionProduct, Long> {
}