package entities.products.repository;

import org.springframework.data.repository.CrudRepository;
import entities.products.model.ElectronicsProduct;

public interface ElectronicsProductRepository extends CrudRepository<ElectronicsProduct, Long> {
}
