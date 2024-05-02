package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.users.model.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductAvailability {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private int quantity;
    private double price;

    public ProductAvailability() {

    }
}
