package com.bluejtitans.smarttradebackend.products.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductAvailabilityBySeller {
    private double price;
    private int stock;
    private String sellerEmail;

    public ProductAvailabilityBySeller(double price, int stock, String sellerEmail) {
        this.price = price;
        this.stock = stock;
        this.sellerEmail = sellerEmail;
    }
}
