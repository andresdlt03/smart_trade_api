package com.bluejtitans.smarttradebackend.products.service;

import com.bluejtitans.smarttradebackend.exception.UserNotRegisteredException;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityBySeller;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityWithCategory;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductWithCategory;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductAvailabilityRepository productAvailabilityRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository, ProductAvailabilityRepository productAvailabilityRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productAvailabilityRepository = productAvailabilityRepository;
    }
    public void saveProduct(Product product, ProductAvailability productAvailability, String sellerEmail) throws RuntimeException {
        try {
            Optional<Product> p = productRepository.findById(product.getName());
            Seller seller = userRepository.findSellerById(sellerEmail);
            if (seller == null) throw new UserNotRegisteredException("Seller with email " + sellerEmail + " not found");
            // If product is already published by another seller, save the new availability information with the existing product
            if (p.isPresent()) {
                productAvailability.setProduct(p.get());
                productAvailability.setSeller(seller);
                if(productAvailability.getPrice() < p.get().getMinPrice()) {
                    p.get().setMinPrice(productAvailability.getPrice());
                    productRepository.save(p.get());
                }
                productAvailabilityRepository.save(productAvailability);
            }
            // If product is not published yet, create a new product and availability
            else {
                productAvailability.setProduct(product);
                productAvailability.setSeller(seller);
                product.setMinPrice(productAvailability.getPrice());
                productRepository.save(product);
                productAvailabilityRepository.save(productAvailability);
            }
        }
        catch (UserNotRegisteredException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Unknown error ocurred while saving product");
        }
    }

    public List<ProductWithCategory> getProductsByVerified(Boolean verified) {
        List<Product> products = productRepository.findAllByVerified(verified);
        if (products != null) {
            List<ProductWithCategory> productWithCategories = new ArrayList<>();
            for (Product product : products) {
                productWithCategories.add(new ProductWithCategory(product, product.getCategory()));
            }
            return productWithCategories;
        }
        else return new ArrayList<>();
    }

    public ProductWithCategory getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return new ProductWithCategory(product.get(), product.get().getCategory());
        }
        else throw new RuntimeException("Product with id " + productId + " not found");
    }

    public List<ProductAvailabilityBySeller> getProductAvailabilities(String productId) {
        List<ProductAvailability> productAvailabilities = productAvailabilityRepository.findAllByProductId(productId);
        if (productAvailabilities != null) {
            List<ProductAvailabilityBySeller> productAvailabilityBySellers = new ArrayList<>();
            for (ProductAvailability productAvailability : productAvailabilities) {
                productAvailabilityBySellers.add(new ProductAvailabilityBySeller(productAvailability.getPrice(), productAvailability.getStock(), productAvailability.getSeller().getEmail()));
            }
            return productAvailabilityBySellers;
        }
        else return new ArrayList<>();
    }

    public List<ProductAvailabilityWithCategory> getProductsBySeller(String sellerEmail) {
        List<ProductAvailability> productAvailabilities = productAvailabilityRepository.findAllBySellerId(sellerEmail);
        if (productAvailabilities != null) {
            List<ProductAvailabilityWithCategory> productAvailabilityWithCategories = new ArrayList<>();
            for (ProductAvailability productAvailability : productAvailabilities) {
                productAvailabilityWithCategories.add(new ProductAvailabilityWithCategory(productAvailability, productAvailability.getProduct().getCategory()));
            }
            return productAvailabilityWithCategories;
        }
        else return new ArrayList<>();
    }

    public void setProductVerification(String productId, Boolean verify) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            product.get().setVerified(verify);
            productRepository.save(product.get());
        }
        else throw new RuntimeException("Product with id " + productId + " not found");
    }
}