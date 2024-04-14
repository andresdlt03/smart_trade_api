package entities.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import ourProducts.model.Product;
import entities.products.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    public void updateProduct(Long id, Product product) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            updateProductName(id, product.getName());
            updateProductPrice(id, product.getPrice());
            updateProductQuantity(id, product.getQuantity());
            updateProductDescription(id, product.getDescription());
            updateProductTechnicalSheet(id, product.getDataSheet());
            updateProductPhotos(id, product.getPhotos());
        }
    }
    //region update
    public void updateProductName(Long id, String name) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setName(name);
            productRepository.save(p);
        }
    }
    public void updateProductPrice(Long id, double price) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setPrice(price);
            productRepository.save(p);
        }
    }
    public void updateProductQuantity(Long id, int quantity) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setQuantity(quantity);
            productRepository.save(p);
        }
    }
    public void updateProductTechnicalSheet(Long id, String technicalSheet) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setDataSheet(technicalSheet);
            productRepository.save(p);
        }
    }
    public void updateProductDescription(Long id, String description) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setDescription(description);
            productRepository.save(p);
        }
    }
    public void updateProductPhotos(Long id, List<String> photos) {
        Product p = productRepository.findById(id).orElse(null);
        if (p != null) {
            p.setPhotos(photos);
            productRepository.save(p);
        }
    }
    //endregion
}