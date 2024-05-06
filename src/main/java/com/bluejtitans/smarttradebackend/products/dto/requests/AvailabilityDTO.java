package com.bluejtitans.smarttradebackend.products.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailabilityDTO {
    private int stock;
    private double price;
    private String sellerEmail;
    private String productId;

    public AvailabilityDTO(int stock, double price, String sellerEmail, String productId) {
        this.stock = stock;
        this.price = price;
        this.sellerEmail = sellerEmail;
        this.productId = productId;
    }
}
