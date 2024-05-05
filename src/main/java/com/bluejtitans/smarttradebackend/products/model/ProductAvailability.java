package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.users.model.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class ProductAvailability {
    @EmbeddedId
    private ProductAvailabilityId id = new ProductAvailabilityId();

    private int stock;
    private double price;

    public ProductAvailability(Product product, Seller seller, int stock, double price) {
        this.id.setProduct(product);
        this.id.setSeller(seller);
        this.stock = stock;
        this.price = price;
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public void setSeller(Seller seller) {
        this.id.setSeller(seller);
    }

    public ProductAvailability() {

    }
}

@Embeddable
@Getter
@Setter
class ProductAvailabilityId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public ProductAvailabilityId() {
    }

    public ProductAvailabilityId(Product product, Seller seller) {
        this.product = product;
        this.seller = seller;
    }
}