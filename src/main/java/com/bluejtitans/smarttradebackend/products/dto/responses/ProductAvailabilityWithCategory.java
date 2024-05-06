package com.bluejtitans.smarttradebackend.products.dto.responses;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAvailabilityWithCategory {
    private String category;
    private double price;
    private int stock;
    private Product product;
    private String sellerEmail;

    public ProductAvailabilityWithCategory(ProductAvailability productAvailability, String category) {
        this.category = category;
        this.price = productAvailability.getPrice();
        this.stock = productAvailability.getStock();
        this.product = productAvailability.getProduct();
        this.sellerEmail = productAvailability.getSeller().getEmail();
    }
}
